package com.baidubce.services.bos.model;

import com.baidubce.auth.BceCredentials;

public class DoesBucketExistRequest extends GenericBucketRequest {
    public DoesBucketExistRequest(String str) {
        super(str);
    }

    public DoesBucketExistRequest withRequestCredentials(BceCredentials bceCredentials) {
        setRequestCredentials(bceCredentials);
        return this;
    }

    public DoesBucketExistRequest withBucketName(String str) {
        setBucketName(str);
        return this;
    }
}
