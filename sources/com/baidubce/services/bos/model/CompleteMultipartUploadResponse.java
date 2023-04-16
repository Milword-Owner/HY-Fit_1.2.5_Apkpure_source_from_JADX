package com.baidubce.services.bos.model;

public class CompleteMultipartUploadResponse extends BosResponse {
    private String bucketName;
    private Long crc32;
    private String eTag;
    private String key;
    private String location;
    private String serverCallbackReturnBody;

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String str) {
        this.location = str;
    }

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

    public String getETag() {
        return this.eTag;
    }

    public void setETag(String str) {
        this.eTag = str;
    }

    public Long getCrc32() {
        return this.crc32;
    }

    public void setCrc32(Long l) {
        this.crc32 = l;
    }

    public String getServerCallbackReturnBody() {
        return this.serverCallbackReturnBody;
    }

    public void setServerCallbackReturnBody(String str) {
        this.serverCallbackReturnBody = str;
    }
}
