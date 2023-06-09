package com.baidubce.services.bos.model;

import com.baidubce.auth.BceCredentials;

public class ListMultipartUploadsRequest extends GenericBucketRequest {
    private String delimiter;
    private String keyMarker;
    private int maxUploads;
    private String prefix;

    public ListMultipartUploadsRequest(String str) {
        this(str, (String) null);
    }

    public ListMultipartUploadsRequest(String str, String str2) {
        super(str);
        this.maxUploads = -1;
        this.prefix = str2;
    }

    public ListMultipartUploadsRequest withRequestCredentials(BceCredentials bceCredentials) {
        setRequestCredentials(bceCredentials);
        return this;
    }

    public ListMultipartUploadsRequest withBucketName(String str) {
        setBucketName(str);
        return this;
    }

    public int getMaxUploads() {
        return this.maxUploads;
    }

    public void setMaxUploads(int i) {
        this.maxUploads = i;
    }

    public ListMultipartUploadsRequest withMaxUploads(int i) {
        this.maxUploads = i;
        return this;
    }

    public String getKeyMarker() {
        return this.keyMarker;
    }

    public void setKeyMarker(String str) {
        this.keyMarker = str;
    }

    public ListMultipartUploadsRequest withKeyMarker(String str) {
        this.keyMarker = str;
        return this;
    }

    public String getDelimiter() {
        return this.delimiter;
    }

    public void setDelimiter(String str) {
        this.delimiter = str;
    }

    public ListMultipartUploadsRequest withDelimiter(String str) {
        setDelimiter(str);
        return this;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public void setPrefix(String str) {
        this.prefix = str;
    }

    public ListMultipartUploadsRequest withPrefix(String str) {
        setPrefix(str);
        return this;
    }
}
