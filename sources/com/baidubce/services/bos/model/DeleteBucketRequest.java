package com.baidubce.services.bos.model;

import com.baidubce.auth.BceCredentials;

public class DeleteBucketRequest extends GenericBucketRequest {
    public DeleteBucketRequest(String str) {
        super(str);
    }

    public DeleteBucketRequest withRequestCredentials(BceCredentials bceCredentials) {
        setRequestCredentials(bceCredentials);
        return this;
    }

    public DeleteBucketRequest withBucketName(String str) {
        setBucketName(str);
        return this;
    }
}
