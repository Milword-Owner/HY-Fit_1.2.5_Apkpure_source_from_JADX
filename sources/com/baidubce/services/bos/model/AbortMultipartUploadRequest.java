package com.baidubce.services.bos.model;

import com.baidubce.auth.BceCredentials;

public class AbortMultipartUploadRequest extends GenericUploadRequest {
    public AbortMultipartUploadRequest(String str, String str2, String str3) {
        super(str, str2, str3);
    }

    public AbortMultipartUploadRequest withRequestCredentials(BceCredentials bceCredentials) {
        setRequestCredentials(bceCredentials);
        return this;
    }

    public AbortMultipartUploadRequest withBucketName(String str) {
        setBucketName(str);
        return this;
    }

    public AbortMultipartUploadRequest withKey(String str) {
        setKey(str);
        return this;
    }

    public AbortMultipartUploadRequest withUploadId(String str) {
        setUploadId(str);
        return this;
    }
}
