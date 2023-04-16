package com.baidubce.services.bos.model;

import com.baidubce.model.AbstractBceResponse;

public class BosResponse extends AbstractBceResponse {
    private String serverCallbackReturnBody;

    public BosResponse() {
        this.metadata = new BosResponseMetadata();
    }

    public BosResponseMetadata getMetadata() {
        return (BosResponseMetadata) this.metadata;
    }

    public String getServerCallbackReturnBody() {
        return this.serverCallbackReturnBody;
    }

    public void setServerCallbackReturnBody(String str) {
        this.serverCallbackReturnBody = str;
    }
}
