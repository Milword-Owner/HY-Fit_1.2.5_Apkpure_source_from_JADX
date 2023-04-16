package com.baidubce.services.bos.model;

import com.baidubce.auth.BceCredentials;

public class GetBucketAclRequest extends GenericBucketRequest {
    public GetBucketAclRequest(String str) {
        super(str);
    }

    public GetBucketAclRequest withRequestCredentials(BceCredentials bceCredentials) {
        setRequestCredentials(bceCredentials);
        return this;
    }

    public GetBucketAclRequest withBucketName(String str) {
        setBucketName(str);
        return this;
    }
}
