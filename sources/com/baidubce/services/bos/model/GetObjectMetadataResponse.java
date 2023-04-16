package com.baidubce.services.bos.model;

public class GetObjectMetadataResponse extends BosResponse {
    private ObjectMetadata objectMetadata = new ObjectMetadata();

    public ObjectMetadata getObjectMetadata() {
        return this.objectMetadata;
    }

    public void setObjectMetadata(ObjectMetadata objectMetadata2) {
        this.objectMetadata = objectMetadata2;
    }
}
