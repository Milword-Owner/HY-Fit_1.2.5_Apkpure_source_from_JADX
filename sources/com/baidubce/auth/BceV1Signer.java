package com.baidubce.auth;

import android.annotation.SuppressLint;
import com.baidubce.BceConfig;
import com.baidubce.http.Headers;
import com.baidubce.internal.InternalRequest;
import com.baidubce.util.BLog;
import com.baidubce.util.CheckUtils;
import com.baidubce.util.DateUtils;
import com.baidubce.util.HashUtils;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JoinerUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import okhttp3.internal.http.HttpMethod;

@SuppressLint({"NewApi", "DefaultLocale"})
public class BceV1Signer implements Signer {
    private static final Set<String> defaultHeadersToSign = new HashSet();

    static {
        defaultHeadersToSign.add(Headers.HOST.toLowerCase());
        defaultHeadersToSign.add(Headers.CONTENT_LENGTH.toLowerCase());
        defaultHeadersToSign.add(Headers.CONTENT_TYPE.toLowerCase());
        defaultHeadersToSign.add(Headers.CONTENT_MD5.toLowerCase());
    }

    public void sign(InternalRequest internalRequest, BceCredentials bceCredentials) {
        sign(internalRequest, bceCredentials, (SignOptions) null);
    }

    public void sign(InternalRequest internalRequest, BceCredentials bceCredentials, SignOptions signOptions) {
        CheckUtils.isNotNull(internalRequest, "request should not be null.");
        if (bceCredentials != null) {
            if (signOptions == null) {
                if (internalRequest.getSignOptions() != null) {
                    signOptions = internalRequest.getSignOptions();
                } else {
                    signOptions = SignOptions.DEFAULT;
                }
            }
            String accessKeyId = bceCredentials.getAccessKeyId();
            String secretKey = bceCredentials.getSecretKey();
            internalRequest.addHeader(Headers.HOST, HttpUtils.generateHostHeader(internalRequest.getUri()));
            String name = internalRequest.getHttpMethod().name();
            boolean z = HttpMethod.requiresRequestBody(name) || HttpMethod.permitsRequestBody(name);
            if (internalRequest.getHeaders().get(Headers.CONTENT_LENGTH) == null && internalRequest.getContent() == null && z) {
                internalRequest.addHeader(Headers.CONTENT_LENGTH, "0");
            }
            if (bceCredentials instanceof BceSessionCredentials) {
                internalRequest.addHeader(Headers.BCE_SECURITY_TOKEN, ((BceSessionCredentials) bceCredentials).getSessionToken());
            }
            Date timestamp = signOptions.getTimestamp();
            if (timestamp == null) {
                timestamp = new Date();
            }
            String on = JoinerUtils.m1834on(BceConfig.BOS_DELIMITER, BceConfig.BCE_AUTH_VERSION, accessKeyId, DateUtils.alternateIso8601DateFormat(timestamp), Integer.valueOf(signOptions.getExpirationInSeconds()));
            String sha256Hex = HashUtils.sha256Hex(secretKey, on);
            String canonicalURIPath = getCanonicalURIPath(internalRequest.getUri().getPath());
            String canonicalQueryString = HttpUtils.getCanonicalQueryString(internalRequest.getParameters(), true);
            SortedMap<String, String> headersToSign = getHeadersToSign(internalRequest.getHeaders(), signOptions.getHeadersToSign());
            String canonicalHeaders = getCanonicalHeaders(headersToSign);
            String lowerCase = signOptions.getHeadersToSign() != null ? JoinerUtils.m1834on(";", headersToSign.keySet().toArray()).trim().toLowerCase() : "";
            String on2 = JoinerUtils.m1834on("\n", internalRequest.getHttpMethod(), canonicalURIPath, canonicalQueryString, canonicalHeaders);
            String on3 = JoinerUtils.m1834on(BceConfig.BOS_DELIMITER, on, lowerCase, HashUtils.sha256Hex(sha256Hex, on2));
            BLog.debug("CanonicalRequest:{}\tAuthorization:{}", (Object) on2.replace("\n", "[\\n]"), (Object) on3);
            internalRequest.addHeader(Headers.AUTHORIZATION, on3);
        }
    }

    private String getCanonicalURIPath(String str) {
        if (str == null) {
            return BceConfig.BOS_DELIMITER;
        }
        if (str.startsWith(BceConfig.BOS_DELIMITER)) {
            return HttpUtils.normalizePath(str);
        }
        return BceConfig.BOS_DELIMITER + HttpUtils.normalizePath(str);
    }

    private String getCanonicalHeaders(SortedMap<String, String> sortedMap) {
        if (sortedMap.isEmpty()) {
            return "";
        }
        ArrayList arrayList = new ArrayList();
        for (Map.Entry next : sortedMap.entrySet()) {
            String str = (String) next.getKey();
            if (str != null) {
                String str2 = (String) next.getValue();
                if (str2 == null) {
                    str2 = "";
                }
                arrayList.add(HttpUtils.normalize(str.trim().toLowerCase()) + ':' + HttpUtils.normalize(str2.trim()));
            }
        }
        Collections.sort(arrayList);
        return JoinerUtils.m1833on("\n", (List<String>) arrayList);
    }

    private SortedMap<String, String> getHeadersToSign(Map<String, String> map, Set<String> set) {
        TreeMap treeMap = new TreeMap();
        if (set != null) {
            HashSet hashSet = new HashSet();
            for (String trim : set) {
                hashSet.add(trim.trim().toLowerCase());
            }
            set = hashSet;
        }
        for (Map.Entry next : map.entrySet()) {
            String str = (String) next.getKey();
            if (next.getValue() != null && !((String) next.getValue()).isEmpty()) {
                if ((set == null && isDefaultHeaderToSign(str)) || (set != null && set.contains(str.toLowerCase()) && !Headers.AUTHORIZATION.equalsIgnoreCase(str))) {
                    treeMap.put(str, next.getValue());
                }
            }
        }
        return treeMap;
    }

    private boolean isDefaultHeaderToSign(String str) {
        String lowerCase = str.trim().toLowerCase();
        return lowerCase.startsWith(Headers.BCE_PREFIX) || defaultHeadersToSign.contains(lowerCase);
    }
}
