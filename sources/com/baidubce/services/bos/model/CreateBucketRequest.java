package com.baidubce.services.bos.model;

import com.baidubce.auth.BceCredentials;

public class CreateBucketRequest extends GenericBucketRequest {
    public CreateBucketRequest(String str) {
        super(str);
    }

    public CreateBucketRequest withRequestCredentials(BceCredentials bceCredentials) {
        setRequestCredentials(bceCredentials);
        return this;
    }

    public CreateBucketRequest withBucketName(String str) {
        setBucketName(str);
        return this;
    }
}
