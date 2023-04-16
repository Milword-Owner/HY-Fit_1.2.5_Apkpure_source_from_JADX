package com.baidubce.services.bos.model;

import com.baidubce.auth.BceCredentials;

public class GetObjectMetadataRequest extends GenericObjectRequest {
    public GetObjectMetadataRequest(String str, String str2) {
        super(str, str2);
    }

    public GetObjectMetadataRequest withRequestCredentials(BceCredentials bceCredentials) {
        setRequestCredentials(bceCredentials);
        return this;
    }

    public GetObjectMetadataRequest withBucketName(String str) {
        setBucketName(str);
        return this;
    }

    public GetObjectMetadataRequest withKey(String str) {
        setKey(str);
        return this;
    }
}
