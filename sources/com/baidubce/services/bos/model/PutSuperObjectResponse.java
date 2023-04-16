package com.baidubce.services.bos.model;

public class PutSuperObjectResponse extends CompleteMultipartUploadResponse {
    private boolean isUploadPart = true;
    private String serverCallbackReturnBody;

    public String getServerCallbackReturnBody() {
        return this.serverCallbackReturnBody;
    }

    public void setServerCallbackReturnBody(String str) {
        this.serverCallbackReturnBody = str;
    }

    public boolean getIsUploadPart() {
        return this.isUploadPart;
    }

    public void setIsUploadPart(boolean z) {
        this.isUploadPart = z;
    }
}
