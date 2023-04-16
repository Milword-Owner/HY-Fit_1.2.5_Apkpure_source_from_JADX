package com.baidubce.model;

import com.baidubce.BceResponseMetadata;
import com.baidubce.http.BceHttpResponse;

public class AbstractBceResponse {
    private BceHttpResponse httpResponse;
    protected BceResponseMetadata metadata = new BceResponseMetadata();

    public void setHttpResponse(BceHttpResponse bceHttpResponse) {
        this.httpResponse = bceHttpResponse;
    }

    public BceHttpResponse getHttpResponse() {
        return this.httpResponse;
    }

    public BceResponseMetadata getMetadata() {
        return this.metadata;
    }
}
