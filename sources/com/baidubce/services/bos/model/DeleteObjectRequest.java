package com.baidubce.services.bos.model;

import com.baidubce.auth.BceCredentials;

public class DeleteObjectRequest extends GenericObjectRequest {
    public DeleteObjectRequest(String str, String str2) {
        super(str, str2);
    }

    public DeleteObjectRequest withRequestCredentials(BceCredentials bceCredentials) {
        setRequestCredentials(bceCredentials);
        return this;
    }

    public DeleteObjectRequest withBucketName(String str) {
        setBucketName(str);
        return this;
    }

    public DeleteObjectRequest withKey(String str) {
        setKey(str);
        return this;
    }
}
