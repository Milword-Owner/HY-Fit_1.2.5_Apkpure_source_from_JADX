package com.baidubce.services.bos.model;

import com.baidubce.auth.BceCredentials;

public class ListObjectsRequest extends GenericBucketRequest {
    private String delimiter;
    private String marker;
    private int maxKeys;
    private String prefix;

    public ListObjectsRequest(String str) {
        this(str, (String) null);
    }

    public ListObjectsRequest(String str, String str2) {
        super(str);
        this.maxKeys = -1;
        this.prefix = str2;
    }

    public ListObjectsRequest withRequestCredentials(BceCredentials bceCredentials) {
        setRequestCredentials(bceCredentials);
        return this;
    }

    public ListObjectsRequest withBucketName(String str) {
        setBucketName(str);
        return this;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public void setPrefix(String str) {
        this.prefix = str;
    }

    public ListObjectsRequest withPrefix(String str) {
        setPrefix(str);
        return this;
    }

    public String getMarker() {
        return this.marker;
    }

    public void setMarker(String str) {
        this.marker = str;
    }

    public ListObjectsRequest withMarker(String str) {
        setMarker(str);
        return this;
    }

    public String getDelimiter() {
        return this.delimiter;
    }

    public void setDelimiter(String str) {
        this.delimiter = str;
    }

    public ListObjectsRequest withDelimiter(String str) {
        setDelimiter(str);
        return this;
    }

    public int getMaxKeys() {
        return this.maxKeys;
    }

    public void setMaxKeys(int i) {
        this.maxKeys = i;
    }

    public ListObjectsRequest withMaxKeys(int i) {
        setMaxKeys(i);
        return this;
    }
}
