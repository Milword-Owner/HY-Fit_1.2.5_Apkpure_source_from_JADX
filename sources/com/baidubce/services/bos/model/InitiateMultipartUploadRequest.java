package com.baidubce.services.bos.model;

import com.baidubce.auth.BceCredentials;

public class InitiateMultipartUploadRequest extends GenericObjectRequest {
    private ObjectMetadata objectMetadata = new ObjectMetadata();
    private String storageClass;

    public InitiateMultipartUploadRequest(String str, String str2) {
        super(str, str2);
    }

    public InitiateMultipartUploadRequest withRequestCredentials(BceCredentials bceCredentials) {
        setRequestCredentials(bceCredentials);
        return this;
    }

    public InitiateMultipartUploadRequest withBucketName(String str) {
        setBucketName(str);
        return this;
    }

    public InitiateMultipartUploadRequest withKey(String str) {
        setKey(str);
        return this;
    }

    public ObjectMetadata getObjectMetadata() {
        return this.objectMetadata;
    }

    public void setObjectMetadata(ObjectMetadata objectMetadata2) {
        this.objectMetadata = objectMetadata2;
    }

    public InitiateMultipartUploadRequest withMetadata(ObjectMetadata objectMetadata2) {
        setObjectMetadata(objectMetadata2);
        return this;
    }

    public String getStorageClass() {
        return this.storageClass;
    }

    public void setStorageClass(String str) {
        this.storageClass = str;
    }

    public InitiateMultipartUploadRequest withStorageClass(String str) {
        setStorageClass(str);
        return this;
    }
}
