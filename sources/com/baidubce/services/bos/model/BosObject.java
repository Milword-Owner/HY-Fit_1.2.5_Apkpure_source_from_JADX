package com.baidubce.services.bos.model;

import com.baidubce.services.bos.BosObjectInputStream;
import java.io.Closeable;
import java.io.IOException;

public class BosObject implements Closeable {
    private String bucketName = null;
    private String key = null;
    private BosObjectInputStream objectContent;
    private ObjectMetadata objectMetadata = new ObjectMetadata();

    public String getBucketName() {
        return this.bucketName;
    }

    public void setBucketName(String str) {
        this.bucketName = str;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String str) {
        this.key = str;
    }

    public ObjectMetadata getObjectMetadata() {
        return this.objectMetadata;
    }

    public void setObjectMetadata(ObjectMetadata objectMetadata2) {
        this.objectMetadata = objectMetadata2;
    }

    public BosObjectInputStream getObjectContent() {
        return this.objectContent;
    }

    public void setObjectContent(BosObjectInputStream bosObjectInputStream) {
        this.objectContent = bosObjectInputStream;
    }

    public String toString() {
        return "BosObject [bucketName=" + this.bucketName + ", key=" + this.key + ", metadata=" + this.objectMetadata + "]";
    }

    public void close() throws IOException {
        if (getObjectContent() != null) {
            getObjectContent().close();
        }
    }
}
