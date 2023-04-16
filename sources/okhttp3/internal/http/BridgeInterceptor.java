package okhttp3.internal.http;

import com.baidubce.http.Headers;
import java.io.IOException;
import java.util.List;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.internal.Util;
import okhttp3.internal.Version;
import okio.GzipSource;
import okio.Okio;
import okio.Source;

public final class BridgeInterceptor implements Interceptor {
    private final CookieJar cookieJar;

    public BridgeInterceptor(CookieJar cookieJar2) {
        this.cookieJar = cookieJar2;
    }

    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder newBuilder = request.newBuilder();
        RequestBody body = request.body();
        if (body != null) {
            MediaType contentType = body.contentType();
            if (contentType != null) {
                newBuilder.header(Headers.CONTENT_TYPE, contentType.toString());
            }
            long contentLength = body.contentLength();
            if (contentLength != -1) {
                newBuilder.header(Headers.CONTENT_LENGTH, Long.toString(contentLength));
                newBuilder.removeHeader(Headers.TRANSFER_ENCODING);
            } else {
                newBuilder.header(Headers.TRANSFER_ENCODING, "chunked");
                newBuilder.removeHeader(Headers.CONTENT_LENGTH);
            }
        }
        boolean z = false;
        if (request.header(Headers.HOST) == null) {
            newBuilder.header(Headers.HOST, Util.hostHeader(request.url(), false));
        }
        if (request.header("Connection") == null) {
            newBuilder.header("Connection", "Keep-Alive");
        }
        if (request.header(Headers.ACCEPT_ENCODING) == null && request.header(Headers.RANGE) == null) {
            z = true;
            newBuilder.header(Headers.ACCEPT_ENCODING, "gzip");
        }
        List<Cookie> loadForRequest = this.cookieJar.loadForRequest(request.url());
        if (!loadForRequest.isEmpty()) {
            newBuilder.header("Cookie", cookieHeader(loadForRequest));
        }
        if (request.header(Headers.USER_AGENT) == null) {
            newBuilder.header(Headers.USER_AGENT, Version.userAgent());
        }
        Response proceed = chain.proceed(newBuilder.build());
        HttpHeaders.receiveHeaders(this.cookieJar, request.url(), proceed.headers());
        Response.Builder request2 = proceed.newBuilder().request(request);
        if (z && "gzip".equalsIgnoreCase(proceed.header(Headers.CONTENT_ENCODING)) && HttpHeaders.hasBody(proceed)) {
            GzipSource gzipSource = new GzipSource(proceed.body().source());
            request2.headers(proceed.headers().newBuilder().removeAll(Headers.CONTENT_ENCODING).removeAll(Headers.CONTENT_LENGTH).build());
            request2.body(new RealResponseBody(proceed.header(Headers.CONTENT_TYPE), -1, Okio.buffer((Source) gzipSource)));
        }
        return request2.build();
    }

    private String cookieHeader(List<Cookie> list) {
        StringBuilder sb = new StringBuilder();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                sb.append("; ");
            }
            Cookie cookie = list.get(i);
            sb.append(cookie.name());
            sb.append('=');
            sb.append(cookie.value());
        }
        return sb.toString();
    }
}
