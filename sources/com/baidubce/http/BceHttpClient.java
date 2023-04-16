package com.baidubce.http;

import android.annotation.SuppressLint;
import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.auth.Signer;
import com.baidubce.callback.BceProgressCallback;
import com.baidubce.internal.InternalRequest;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.util.CheckUtils;
import com.baidubce.util.HttpUtils;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;
import okio.Okio;
import okio.Source;

@SuppressLint({"NewApi"})
public class BceHttpClient {
    private static final HttpClientFactory httpClientFactory = new HttpClientFactory();
    /* access modifiers changed from: private */
    public final BceClientConfiguration config;
    private long diffMillis;
    private OkHttpClient httpClient;
    private final Signer signer;

    public BceHttpClient(BceClientConfiguration bceClientConfiguration, Signer signer2) {
        this(bceClientConfiguration, httpClientFactory.createHttpClient(bceClientConfiguration), signer2);
    }

    public BceHttpClient(BceClientConfiguration bceClientConfiguration, OkHttpClient okHttpClient, Signer signer2) {
        this.diffMillis = 0;
        CheckUtils.isNotNull(bceClientConfiguration, "config should not be null.");
        CheckUtils.isNotNull(signer2, "signer should not be null.");
        this.config = bceClientConfiguration;
        this.httpClient = okHttpClient;
        this.signer = signer2;
    }

    public <T extends AbstractBceRequest> OkHttpClient addResponseProgressCallback(final T t, final BceProgressCallback<T> bceProgressCallback) {
        return this.httpClient.newBuilder().addNetworkInterceptor(new Interceptor() {
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Response proceed = chain.proceed(chain.request());
                return proceed.newBuilder().body(new BceServiceResponseBody(proceed.body(), t, bceProgressCallback)).build();
            }
        }).build();
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.baidubce.internal.InternalRequest, com.baidubce.internal.InternalRequest<M>] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <T extends com.baidubce.model.AbstractBceResponse, M extends com.baidubce.model.AbstractBceRequest> T execute(com.baidubce.internal.InternalRequest<M> r2, java.lang.Class<T> r3, com.baidubce.http.handler.HttpResponseHandler[] r4) {
        /*
            r1 = this;
            r0 = 0
            com.baidubce.model.AbstractBceResponse r2 = r1.execute(r2, r3, r4, r0)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidubce.http.BceHttpClient.execute(com.baidubce.internal.InternalRequest, java.lang.Class, com.baidubce.http.handler.HttpResponseHandler[]):com.baidubce.model.AbstractBceResponse");
    }

    /* JADX WARNING: Removed duplicated region for block: B:42:0x00fa  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0102  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0138  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x0168  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x01a4  */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x01ab A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <T extends com.baidubce.model.AbstractBceResponse, M extends com.baidubce.model.AbstractBceRequest> T execute(com.baidubce.internal.InternalRequest<M> r20, java.lang.Class<T> r21, com.baidubce.http.handler.HttpResponseHandler[] r22, com.baidubce.callback.BceProgressCallback<M> r23) {
        /*
            r19 = this;
            r1 = r19
            r2 = r20
            r3 = r22
            com.baidubce.BceClientConfiguration r0 = r1.config
            java.lang.String r0 = r0.getUserAgent()
            java.lang.String r4 = "User-Agent"
            r2.addHeader(r4, r0)
            com.baidubce.BceClientConfiguration r0 = r1.config
            java.lang.String r0 = r0.getAcceptEncoding()
            java.lang.String r4 = "Accept-Encoding"
            r2.addHeader(r4, r0)
            com.baidubce.BceClientConfiguration r0 = r1.config
            com.baidubce.auth.BceCredentials r0 = r0.getCredentials()
            com.baidubce.auth.BceCredentials r4 = r20.getCredentials()
            if (r4 == 0) goto L_0x002c
            com.baidubce.auth.BceCredentials r0 = r20.getCredentials()
        L_0x002c:
            r4 = r0
            r6 = 0
            java.lang.String r0 = ""
            r9 = r0
            r11 = r6
            r10 = 1
            r13 = 0
        L_0x0035:
            java.util.Calendar r0 = java.util.Calendar.getInstance()     // Catch:{ BceServiceException -> 0x0128, BceClientException -> 0x0123, Exception -> 0x00e7 }
            long r11 = r0.getTimeInMillis()     // Catch:{ BceServiceException -> 0x0128, BceClientException -> 0x0123, Exception -> 0x00e7 }
            java.util.Date r0 = new java.util.Date     // Catch:{ BceServiceException -> 0x0128, BceClientException -> 0x0123, Exception -> 0x00e7 }
            long r14 = r1.diffMillis     // Catch:{ BceServiceException -> 0x0128, BceClientException -> 0x0123, Exception -> 0x00e7 }
            long r14 = r14 + r11
            r0.<init>(r14)     // Catch:{ BceServiceException -> 0x0128, BceClientException -> 0x0123, Exception -> 0x00e7 }
            com.baidubce.auth.SignOptions r14 = r20.getSignOptions()     // Catch:{ BceServiceException -> 0x0128, BceClientException -> 0x0123, Exception -> 0x00e7 }
            if (r14 != 0) goto L_0x004e
            com.baidubce.auth.SignOptions r14 = com.baidubce.auth.SignOptions.DEFAULT     // Catch:{ BceServiceException -> 0x0128, BceClientException -> 0x0123, Exception -> 0x00e7 }
            goto L_0x0052
        L_0x004e:
            com.baidubce.auth.SignOptions r14 = r20.getSignOptions()     // Catch:{ BceServiceException -> 0x0128, BceClientException -> 0x0123, Exception -> 0x00e7 }
        L_0x0052:
            r14.setTimestamp(r0)     // Catch:{ BceServiceException -> 0x0128, BceClientException -> 0x0123, Exception -> 0x00e7 }
            r2.setSignOptions(r14)     // Catch:{ BceServiceException -> 0x0128, BceClientException -> 0x0123, Exception -> 0x00e7 }
            long r14 = r1.diffMillis     // Catch:{ BceServiceException -> 0x0128, BceClientException -> 0x0123, Exception -> 0x00e7 }
            int r16 = (r14 > r6 ? 1 : (r14 == r6 ? 0 : -1))
            if (r16 == 0) goto L_0x0067
            java.lang.String r14 = "x-bce-date"
            java.lang.String r0 = com.baidubce.util.DateUtils.alternateIso8601DateFormat(r0)     // Catch:{ BceServiceException -> 0x0128, BceClientException -> 0x0123, Exception -> 0x00e7 }
            r2.addHeader(r14, r0)     // Catch:{ BceServiceException -> 0x0128, BceClientException -> 0x0123, Exception -> 0x00e7 }
        L_0x0067:
            if (r4 == 0) goto L_0x006e
            com.baidubce.auth.Signer r0 = r1.signer     // Catch:{ BceServiceException -> 0x0128, BceClientException -> 0x0123, Exception -> 0x00e7 }
            r0.sign(r2, r4)     // Catch:{ BceServiceException -> 0x0128, BceClientException -> 0x0123, Exception -> 0x00e7 }
        L_0x006e:
            okhttp3.OkHttpClient r0 = r1.httpClient     // Catch:{ BceServiceException -> 0x0128, BceClientException -> 0x0123, Exception -> 0x00e7 }
            r14 = r23
            okhttp3.Request r15 = r1.createHttpRequest(r2, r14)     // Catch:{ BceServiceException -> 0x00e5, BceClientException -> 0x00e3, Exception -> 0x00e1 }
            com.baidubce.model.AbstractBceRequest r5 = r20.getRequest()     // Catch:{ BceServiceException -> 0x00e5, BceClientException -> 0x00e3, Exception -> 0x00e1 }
            boolean r5 = r5 instanceof com.baidubce.services.bos.model.GetObjectRequest     // Catch:{ BceServiceException -> 0x00e5, BceClientException -> 0x00e3, Exception -> 0x00e1 }
            if (r5 == 0) goto L_0x0095
            com.baidubce.model.AbstractBceRequest r0 = r20.getRequest()     // Catch:{ BceServiceException -> 0x00e5, BceClientException -> 0x00e3, Exception -> 0x00e1 }
            com.baidubce.model.AbstractBceRequest r5 = r20.getRequest()     // Catch:{ BceServiceException -> 0x00e5, BceClientException -> 0x00e3, Exception -> 0x00e1 }
            com.baidubce.services.bos.model.GetObjectRequest r5 = (com.baidubce.services.bos.model.GetObjectRequest) r5     // Catch:{ BceServiceException -> 0x00e5, BceClientException -> 0x00e3, Exception -> 0x00e1 }
            com.baidubce.services.bos.callback.BosProgressCallback r5 = r5.getProgressCallback()     // Catch:{ BceServiceException -> 0x00e5, BceClientException -> 0x00e3, Exception -> 0x00e1 }
            okhttp3.OkHttpClient r0 = r1.addResponseProgressCallback(r0, r5)     // Catch:{ BceServiceException -> 0x00e5, BceClientException -> 0x00e3, Exception -> 0x00e1 }
            java.lang.String r5 = "getObject"
            com.baidubce.util.BLog.debug(r5)     // Catch:{ BceServiceException -> 0x00e5, BceClientException -> 0x00e3, Exception -> 0x00e1 }
        L_0x0095:
            okhttp3.Call r0 = r0.newCall(r15)     // Catch:{ BceServiceException -> 0x00e5, BceClientException -> 0x00e3, Exception -> 0x00e1 }
            com.baidubce.model.AbstractBceRequest r5 = r20.getRequest()     // Catch:{ BceServiceException -> 0x00e5, BceClientException -> 0x00e3, Exception -> 0x00e1 }
            if (r5 == 0) goto L_0x00b9
            com.baidubce.model.AbstractBceRequest r5 = r20.getRequest()     // Catch:{ BceServiceException -> 0x00e5, BceClientException -> 0x00e3, Exception -> 0x00e1 }
            r5.setCall(r0)     // Catch:{ BceServiceException -> 0x00e5, BceClientException -> 0x00e3, Exception -> 0x00e1 }
            com.baidubce.model.AbstractBceRequest r5 = r20.getRequest()     // Catch:{ BceServiceException -> 0x00e5, BceClientException -> 0x00e3, Exception -> 0x00e1 }
            boolean r5 = r5.getCanceled()     // Catch:{ BceServiceException -> 0x00e5, BceClientException -> 0x00e3, Exception -> 0x00e1 }
            if (r5 != 0) goto L_0x00b1
            goto L_0x00b9
        L_0x00b1:
            com.baidubce.BceClientException r0 = new com.baidubce.BceClientException     // Catch:{ BceServiceException -> 0x00e5, BceClientException -> 0x00e3, Exception -> 0x00e1 }
            java.lang.String r5 = "Request is canceled!"
            r0.<init>(r5)     // Catch:{ BceServiceException -> 0x00e5, BceClientException -> 0x00e3, Exception -> 0x00e1 }
            throw r0     // Catch:{ BceServiceException -> 0x00e5, BceClientException -> 0x00e3, Exception -> 0x00e1 }
        L_0x00b9:
            okhttp3.Response r0 = r0.execute()     // Catch:{ BceServiceException -> 0x00e5, BceClientException -> 0x00e3, Exception -> 0x00e1 }
            com.baidubce.http.BceHttpResponse r5 = new com.baidubce.http.BceHttpResponse     // Catch:{ BceServiceException -> 0x00e5, BceClientException -> 0x00e3, Exception -> 0x00e1 }
            r5.<init>(r0)     // Catch:{ BceServiceException -> 0x00e5, BceClientException -> 0x00e3, Exception -> 0x00e1 }
            java.lang.String r0 = "Date"
            java.lang.String r9 = r5.getHeader(r0)     // Catch:{ BceServiceException -> 0x00e5, BceClientException -> 0x00e3, Exception -> 0x00e1 }
            java.lang.Object r0 = r21.newInstance()     // Catch:{ BceServiceException -> 0x00e5, BceClientException -> 0x00e3, Exception -> 0x00e1 }
            com.baidubce.model.AbstractBceResponse r0 = (com.baidubce.model.AbstractBceResponse) r0     // Catch:{ BceServiceException -> 0x00e5, BceClientException -> 0x00e3, Exception -> 0x00e1 }
            int r15 = r3.length     // Catch:{ BceServiceException -> 0x00e5, BceClientException -> 0x00e3, Exception -> 0x00e1 }
            r8 = 0
        L_0x00d0:
            if (r8 >= r15) goto L_0x00e0
            r6 = r3[r8]     // Catch:{ BceServiceException -> 0x00e5, BceClientException -> 0x00e3, Exception -> 0x00e1 }
            boolean r6 = r6.handle(r5, r0)     // Catch:{ BceServiceException -> 0x00e5, BceClientException -> 0x00e3, Exception -> 0x00e1 }
            if (r6 == 0) goto L_0x00db
            goto L_0x00e0
        L_0x00db:
            int r8 = r8 + 1
            r6 = 0
            goto L_0x00d0
        L_0x00e0:
            return r0
        L_0x00e1:
            r0 = move-exception
            goto L_0x00ea
        L_0x00e3:
            r0 = move-exception
            goto L_0x0126
        L_0x00e5:
            r0 = move-exception
            goto L_0x012b
        L_0x00e7:
            r0 = move-exception
            r14 = r23
        L_0x00ea:
            com.baidubce.model.AbstractBceRequest r5 = r20.getRequest()
            if (r5 == 0) goto L_0x0102
            com.baidubce.model.AbstractBceRequest r5 = r20.getRequest()
            boolean r5 = r5.isCanceled()
            if (r5 == 0) goto L_0x0102
            com.baidubce.BceClientException r5 = new com.baidubce.BceClientException
            java.lang.String r6 = "Request is canceled!"
            r5.<init>(r6, r0)
            goto L_0x0153
        L_0x0102:
            if (r13 == 0) goto L_0x0107
            java.lang.String r5 = "Unable to execute backup HTTP request，caused by: "
            goto L_0x0109
        L_0x0107:
            java.lang.String r5 = "Unable to execute HTTP request，caused by: "
        L_0x0109:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            r6.append(r5)
            java.lang.String r5 = r0.getMessage()
            r6.append(r5)
            java.lang.String r5 = r6.toString()
            com.baidubce.BceClientException r6 = new com.baidubce.BceClientException
            r6.<init>(r5, r0)
            r5 = r6
            goto L_0x0153
        L_0x0123:
            r0 = move-exception
            r14 = r23
        L_0x0126:
            r5 = r0
            goto L_0x0153
        L_0x0128:
            r0 = move-exception
            r14 = r23
        L_0x012b:
            r5 = r0
            com.baidubce.ErrorCode r0 = com.baidubce.ErrorCode.REQUEST_TIME_TOO_SKEWED
            java.lang.String r6 = r5.getErrorCode()
            boolean r0 = r0.equals(r6)
            if (r0 == 0) goto L_0x0153
            java.util.Date r0 = com.baidubce.util.DateUtils.parseRfc822Date(r9)
            java.lang.String r6 = ""
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0153
            if (r0 == 0) goto L_0x0153
            monitor-enter(r19)
            long r6 = r0.getTime()     // Catch:{ all -> 0x0150 }
            long r6 = r6 - r11
            r1.diffMillis = r6     // Catch:{ all -> 0x0150 }
            monitor-exit(r19)     // Catch:{ all -> 0x0150 }
            goto L_0x0153
        L_0x0150:
            r0 = move-exception
            monitor-exit(r19)     // Catch:{ all -> 0x0150 }
            throw r0
        L_0x0153:
            java.lang.String r0 = "Unable to execute HTTP request"
            com.baidubce.util.BLog.warn(r0)
            com.baidubce.BceClientConfiguration r0 = r1.config
            com.baidubce.http.RetryPolicy r0 = r0.getRetryPolicy()
            long r6 = r1.getDelayBeforeNextRetryInMillis(r2, r5, r10, r0)
            r17 = 0
            int r0 = (r6 > r17 ? 1 : (r6 == r17 ? 0 : -1))
            if (r0 >= 0) goto L_0x017f
            com.baidubce.BceClientConfiguration r0 = r1.config
            com.baidubce.http.RetryPolicy r0 = r0.getRetryPolicy()
            int r0 = r0.getMaxErrorRetry()
            if (r0 <= 0) goto L_0x017e
            boolean r0 = r20.useBackupURI()
            if (r0 == 0) goto L_0x017e
            r6 = 1
            r13 = 1
            goto L_0x017f
        L_0x017e:
            throw r5
        L_0x017f:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r5 = "Retriable error detected, will retry in "
            r0.append(r5)
            r0.append(r6)
            java.lang.String r5 = " ms, attempt number: "
            r0.append(r5)
            r0.append(r10)
            java.lang.String r0 = r0.toString()
            com.baidubce.util.BLog.warn(r0)
            java.lang.Thread.sleep(r6)     // Catch:{ InterruptedException -> 0x01b1 }
            com.baidubce.internal.RestartableInputStream r0 = r20.getContent()
            if (r0 == 0) goto L_0x01ab
            com.baidubce.internal.RestartableInputStream r0 = r20.getContent()
            r0.restart()
        L_0x01ab:
            int r10 = r10 + 1
            r6 = r17
            goto L_0x0035
        L_0x01b1:
            r0 = move-exception
            r2 = r0
            com.baidubce.BceClientException r0 = new com.baidubce.BceClientException
            java.lang.String r3 = "Delay interrupted"
            r0.<init>(r3, r2)
            goto L_0x01bc
        L_0x01bb:
            throw r0
        L_0x01bc:
            goto L_0x01bb
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidubce.http.BceHttpClient.execute(com.baidubce.internal.InternalRequest, java.lang.Class, com.baidubce.http.handler.HttpResponseHandler[], com.baidubce.callback.BceProgressCallback):com.baidubce.model.AbstractBceResponse");
    }

    /* access modifiers changed from: protected */
    public long getDelayBeforeNextRetryInMillis(InternalRequest internalRequest, BceClientException bceClientException, int i, RetryPolicy retryPolicy) {
        int i2 = i - 1;
        if (i2 >= retryPolicy.getMaxErrorRetry()) {
            return -1;
        }
        return Math.min(retryPolicy.getMaxDelayInMillis(), retryPolicy.getDelayBeforeNextRetryInMillis(bceClientException, i2));
    }

    /* access modifiers changed from: protected */
    public <T extends AbstractBceRequest> Request createHttpRequest(InternalRequest<T> internalRequest, BceProgressCallback<T> bceProgressCallback) {
        String aSCIIString = internalRequest.getUri().toASCIIString();
        String canonicalQueryString = HttpUtils.getCanonicalQueryString(internalRequest.getParameters(), false);
        if (canonicalQueryString.length() > 0) {
            aSCIIString = aSCIIString + "?" + canonicalQueryString;
        }
        Request.Builder url = new Request.Builder().url(aSCIIString);
        if (internalRequest.getHttpMethod() == HttpMethodName.GET) {
            url.get();
        } else if (internalRequest.getHttpMethod() == HttpMethodName.PUT) {
            if (internalRequest.getContent() != null) {
                url.put(new BceServiceRequestBody(internalRequest, bceProgressCallback));
            } else {
                url.put(RequestBody.create((MediaType) null, new byte[0]));
            }
        } else if (internalRequest.getHttpMethod() == HttpMethodName.POST) {
            if (internalRequest.getContent() != null) {
                url.post(new BceServiceRequestBody(internalRequest, bceProgressCallback));
            } else {
                url.post(RequestBody.create((MediaType) null, new byte[0]));
            }
        } else if (internalRequest.getHttpMethod() == HttpMethodName.DELETE) {
            url.delete();
        } else if (internalRequest.getHttpMethod() == HttpMethodName.HEAD) {
            url.head();
        } else {
            throw new BceClientException("Unknown HTTP method name: " + internalRequest.getHttpMethod());
        }
        for (Map.Entry next : internalRequest.getHeaders().entrySet()) {
            if (!((String) next.getKey()).equalsIgnoreCase(Headers.CONTENT_LENGTH) && !((String) next.getKey()).equalsIgnoreCase(Headers.HOST)) {
                url.addHeader((String) next.getKey(), (String) next.getValue());
            }
        }
        return url.build();
    }

    private class BceServiceRequestBody<T extends AbstractBceRequest> extends RequestBody {
        private BceProgressCallback<T> callback;
        private long length;
        private MediaType mediaType;
        private T request;
        private InputStream restartableInputStream;

        BceServiceRequestBody(InternalRequest<T> internalRequest, BceProgressCallback<T> bceProgressCallback) {
            if (internalRequest.getContent() != null) {
                this.mediaType = MediaType.parse(internalRequest.getHeaders().get(Headers.CONTENT_TYPE));
                this.restartableInputStream = internalRequest.getContent();
                this.length = getContentLength(internalRequest);
                this.callback = bceProgressCallback;
                this.request = internalRequest.getRequest();
            }
        }

        BceServiceRequestBody(InternalRequest<T> internalRequest) {
            if (internalRequest.getContent() != null) {
                this.mediaType = MediaType.parse(internalRequest.getHeaders().get(Headers.CONTENT_TYPE));
                this.restartableInputStream = internalRequest.getContent();
                this.length = getContentLength(internalRequest);
                this.callback = null;
                this.request = internalRequest.getRequest();
            }
        }

        public MediaType contentType() {
            return this.mediaType;
        }

        public void writeTo(BufferedSink bufferedSink) throws IOException {
            long contentLength = contentLength();
            Source source = Okio.source(this.restartableInputStream);
            long j = 0;
            while (j < contentLength) {
                long read = source.read(bufferedSink.buffer(), Math.min(contentLength - j, BceHttpClient.this.config.getUploadSegmentPart()));
                if (read == -1) {
                    break;
                }
                long j2 = j + read;
                bufferedSink.flush();
                BceProgressCallback<T> bceProgressCallback = this.callback;
                if (bceProgressCallback != null) {
                    bceProgressCallback.onProgress(this.request, j2, contentLength);
                }
                j = j2;
            }
            if (source != null) {
                source.close();
            }
        }

        public long contentLength() throws IOException {
            return this.length;
        }

        private long getContentLength(InternalRequest<T> internalRequest) {
            String str = internalRequest.getHeaders().get(Headers.CONTENT_LENGTH);
            if (str != null) {
                return Long.parseLong(str);
            }
            return 0;
        }
    }
}
