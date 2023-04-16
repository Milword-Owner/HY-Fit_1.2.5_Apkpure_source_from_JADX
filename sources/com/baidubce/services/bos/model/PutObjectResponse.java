package com.baidubce.services.bos.model;

import com.baidubce.model.AbstractBceResponse;

public class PutObjectResponse extends AbstractBceResponse {
    private Long crc32;
    private String eTag;
    private String serverCallbackReturnBody;

    public String getETag() {
        return this.eTag;
    }

    public void setETag(String str) {
        this.eTag = str;
    }

    public Long getCrc32() {
        return this.crc32;
    }

    public void setCrc32(Long l) {
        this.crc32 = l;
    }

    public String getServerCallbackReturnBody() {
        return this.serverCallbackReturnBody;
    }

    public void setServerCallbackReturnBody(String str) {
        this.serverCallbackReturnBody = str;
    }
}
