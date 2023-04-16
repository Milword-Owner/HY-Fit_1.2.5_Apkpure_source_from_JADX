package com.baidubce;

import com.baidubce.auth.BceV1Signer;
import com.baidubce.http.BceHttpClient;
import com.baidubce.http.handler.HttpResponseHandler;
import java.net.URI;
import java.net.URISyntaxException;

public abstract class AbstractBceClient {
    public static final String DEFAULT_CONTENT_TYPE = "application/json; charset=utf-8";
    public static final String DEFAULT_SERVICE_DOMAIN = "baidubce.com";
    public static final String URL_PREFIX = "v1";
    public BceHttpClient client;
    protected BceClientConfiguration config;
    private URI endpoint;
    private HttpResponseHandler[] responseHandlers;
    private String serviceId = computeServiceId();

    public boolean isRegionSupported() {
        return true;
    }

    public AbstractBceClient(BceClientConfiguration bceClientConfiguration, HttpResponseHandler[] httpResponseHandlerArr) {
        this.config = bceClientConfiguration;
        this.endpoint = computeEndpoint();
        this.client = new BceHttpClient(bceClientConfiguration, new BceV1Signer());
        this.responseHandlers = httpResponseHandlerArr;
    }

    public URI getEndpoint() {
        return this.endpoint;
    }

    public String getServiceId() {
        return this.serviceId;
    }

    public BceHttpClient getClient() {
        return this.client;
    }

    public void setClient(BceHttpClient bceHttpClient) {
        this.client = bceHttpClient;
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.baidubce.internal.InternalRequest, com.baidubce.internal.InternalRequest<M>] */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <T extends com.baidubce.model.AbstractBceResponse, M extends com.baidubce.model.AbstractBceRequest> T invokeHttpClient(com.baidubce.internal.InternalRequest<M> r2, java.lang.Class<T> r3) {
        /*
            r1 = this;
            r0 = 0
            com.baidubce.model.AbstractBceResponse r2 = r1.invokeHttpClient(r2, r3, r0)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidubce.AbstractBceClient.invokeHttpClient(com.baidubce.internal.InternalRequest, java.lang.Class):com.baidubce.model.AbstractBceResponse");
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [com.baidubce.internal.InternalRequest, com.baidubce.internal.InternalRequest<M>] */
    /* JADX WARNING: type inference failed for: r5v0, types: [com.baidubce.callback.BceProgressCallback<M>, com.baidubce.callback.BceProgressCallback] */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Unknown variable types count: 2 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <T extends com.baidubce.model.AbstractBceResponse, M extends com.baidubce.model.AbstractBceRequest> T invokeHttpClient(com.baidubce.internal.InternalRequest<M> r3, java.lang.Class<T> r4, com.baidubce.callback.BceProgressCallback<M> r5) {
        /*
            r2 = this;
            java.util.Map r0 = r3.getHeaders()
            java.lang.String r1 = "Content-Type"
            boolean r0 = r0.containsKey(r1)
            if (r0 != 0) goto L_0x0011
            java.lang.String r0 = "application/json; charset=utf-8"
            r3.addHeader(r1, r0)
        L_0x0011:
            java.util.Map r0 = r3.getHeaders()
            java.lang.String r1 = "Date"
            boolean r0 = r0.containsKey(r1)
            if (r0 != 0) goto L_0x0024
            java.lang.String r0 = com.baidubce.util.DateUtils.rfc822DateFormat()
            r3.addHeader(r1, r0)
        L_0x0024:
            com.baidubce.http.BceHttpClient r0 = r2.client
            com.baidubce.http.handler.HttpResponseHandler[] r1 = r2.responseHandlers
            com.baidubce.model.AbstractBceResponse r3 = r0.execute(r3, r4, r1, r5)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidubce.AbstractBceClient.invokeHttpClient(com.baidubce.internal.InternalRequest, java.lang.Class, com.baidubce.callback.BceProgressCallback):com.baidubce.model.AbstractBceResponse");
    }

    private String computeServiceId() {
        String name = getClass().getPackage().getName();
        String str = AbstractBceClient.class.getPackage().getName() + ".services.";
        if (name.startsWith(str)) {
            String substring = name.substring(str.length());
            if (substring.indexOf(46) == -1) {
                String name2 = getClass().getName();
                String str2 = name + '.' + Character.toUpperCase(substring.charAt(0)) + substring.substring(1) + "Client";
                if (name2.equals(str2)) {
                    return substring;
                }
                throw new IllegalStateException("Invalid class name " + name2 + ", " + str2 + " expected");
            }
            throw new IllegalStateException("The client class should be put in package like " + str + "XXX");
        }
        throw new IllegalStateException("Unrecognized prefix for the client package : " + name + ", '" + str + "' expected");
    }

    private URI computeEndpoint() {
        String endpoint2 = this.config.getEndpoint();
        if (endpoint2 == null) {
            try {
                if (isRegionSupported()) {
                    endpoint2 = String.format("%s://%s.%s.%s", new Object[]{this.config.getProtocol(), this.serviceId, this.config.getRegion(), DEFAULT_SERVICE_DOMAIN});
                } else {
                    endpoint2 = String.format("%s://%s.%s", new Object[]{this.config.getProtocol(), this.serviceId, DEFAULT_SERVICE_DOMAIN});
                }
            } catch (URISyntaxException e) {
                throw new IllegalArgumentException("Invalid endpoint." + endpoint2, e);
            }
        }
        return new URI(endpoint2);
    }
}
