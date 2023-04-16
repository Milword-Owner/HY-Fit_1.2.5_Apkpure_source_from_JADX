package com.baidubce.util;

import com.baidu.mobstat.Config;
import com.baidubce.BceConfig;
import com.baidubce.Protocol;
import com.baidubce.http.Headers;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import kotlin.UByte;
import okhttp3.Request;
import okhttp3.Response;

public class HttpUtils {
    private static boolean HTTP_VERBOSE = Boolean.parseBoolean(System.getProperty("bce.sdk.http", "false"));
    private static String[] PERCENT_ENCODED_STRINGS = new String[256];
    private static BitSet URI_UNRESERVED_CHARACTERS = new BitSet();

    static {
        for (int i = 97; i <= 122; i++) {
            URI_UNRESERVED_CHARACTERS.set(i);
        }
        for (int i2 = 65; i2 <= 90; i2++) {
            URI_UNRESERVED_CHARACTERS.set(i2);
        }
        for (int i3 = 48; i3 <= 57; i3++) {
            URI_UNRESERVED_CHARACTERS.set(i3);
        }
        URI_UNRESERVED_CHARACTERS.set(45);
        URI_UNRESERVED_CHARACTERS.set(46);
        URI_UNRESERVED_CHARACTERS.set(95);
        URI_UNRESERVED_CHARACTERS.set(126);
        int i4 = 0;
        while (true) {
            String[] strArr = PERCENT_ENCODED_STRINGS;
            if (i4 < strArr.length) {
                strArr[i4] = String.format("%%%02X", new Object[]{Integer.valueOf(i4)});
                i4++;
            } else {
                return;
            }
        }
    }

    public static String normalizePath(String str) {
        return normalize(str).replace("%2F", BceConfig.BOS_DELIMITER);
    }

    public static String normalize(String str) {
        if (StringUtils.isEmpty(str)) {
            return "";
        }
        try {
            StringBuilder sb = new StringBuilder();
            for (byte b : str.getBytes("UTF-8")) {
                BitSet bitSet = URI_UNRESERVED_CHARACTERS;
                byte b2 = b & UByte.MAX_VALUE;
                if (bitSet.get(b2)) {
                    sb.append((char) b);
                } else {
                    sb.append(PERCENT_ENCODED_STRINGS[b2]);
                }
            }
            return sb.toString();
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String generateHostHeader(URI uri) {
        String host = uri.getHost();
        if (!isUsingNonDefaultPort(uri)) {
            return host;
        }
        return host + Config.TRACE_TODAY_VISIT_SPLIT + uri.getPort();
    }

    public static boolean isUsingNonDefaultPort(URI uri) {
        String lowerCase = uri.getScheme().toLowerCase();
        int port = uri.getPort();
        if (port <= 0) {
            return false;
        }
        if (lowerCase.equals(Protocol.HTTP.toString())) {
            if (port != Protocol.HTTP.getDefaultPort()) {
                return true;
            }
            return false;
        } else if (!lowerCase.equals(Protocol.HTTPS.toString()) || port == Protocol.HTTPS.getDefaultPort()) {
            return false;
        } else {
            return true;
        }
    }

    public static String getCanonicalQueryString(Map<String, String> map, boolean z) {
        if (map.isEmpty()) {
            return "";
        }
        ArrayList arrayList = new ArrayList();
        for (Map.Entry next : map.entrySet()) {
            if (!z || !Headers.AUTHORIZATION.equalsIgnoreCase((String) next.getKey())) {
                String str = (String) next.getKey();
                CheckUtils.isNotNull(str, "parameter key should not be null");
                String str2 = (String) next.getValue();
                if (str2 != null) {
                    arrayList.add(normalize(str) + '=' + normalize(str2));
                } else if (z) {
                    arrayList.add(normalize(str) + '=');
                } else {
                    arrayList.add(normalize(str));
                }
            }
        }
        Collections.sort(arrayList);
        return JoinerUtils.m1833on("&", (List<String>) arrayList);
    }

    public static URI appendUri(URI uri, String... strArr) {
        StringBuilder sb = new StringBuilder(uri.toASCIIString());
        for (String str : strArr) {
            if (str != null && str.length() > 0) {
                String normalizePath = normalizePath(str);
                if (normalizePath.startsWith(BceConfig.BOS_DELIMITER)) {
                    if (sb.charAt(sb.length() - 1) == '/') {
                        sb.setLength(sb.length() - 1);
                    }
                } else if (sb.charAt(sb.length() - 1) != '/') {
                    sb.append('/');
                }
                sb.append(normalizePath);
            }
        }
        try {
            return new URI(sb.toString());
        } catch (URISyntaxException e) {
            throw new RuntimeException("Unexpected error", e);
        }
    }

    public static void printRequest(Request request) {
        if (HTTP_VERBOSE) {
            BLog.info("\n-------------> ");
            BLog.info(request.method() + " " + request.url() + "");
            okhttp3.Headers headers = request.headers();
            for (int i = 0; i < headers.size(); i++) {
                BLog.info(headers.name(i) + Config.TRACE_TODAY_VISIT_SPLIT + headers.value(i));
            }
        }
    }

    public static void printResponse(Response response) {
        if (HTTP_VERBOSE) {
            BLog.info("\n<------------- ");
            BLog.info(response.code() + " - " + response.message());
            okhttp3.Headers headers = response.headers();
            for (int i = 0; i < headers.size(); i++) {
                BLog.info(headers.name(i) + Config.TRACE_TODAY_VISIT_SPLIT + headers.value(i));
            }
        }
    }
}
