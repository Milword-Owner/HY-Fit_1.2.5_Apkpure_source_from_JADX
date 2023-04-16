package com.baidubce.services.bos.model;

import com.baidubce.auth.BceCredentials;

public class ListPartsRequest extends GenericUploadRequest {
    private int maxParts = -1;
    private int partNumberMarker;

    public ListPartsRequest(String str, String str2, String str3) {
        super(str, str2, str3);
    }

    public ListPartsRequest withRequestCredentials(BceCredentials bceCredentials) {
        setRequestCredentials(bceCredentials);
        return this;
    }

    public ListPartsRequest withBucketName(String str) {
        setBucketName(str);
        return this;
    }

    public ListPartsRequest withKey(String str) {
        setKey(str);
        return this;
    }

    public ListPartsRequest withUploadId(String str) {
        setUploadId(str);
        return this;
    }

    public int getMaxParts() {
        return this.maxParts;
    }

    public void setMaxParts(int i) {
        this.maxParts = i;
    }

    public ListPartsRequest withMaxParts(int i) {
        this.maxParts = i;
        return this;
    }

    public int getPartNumberMarker() {
        return this.partNumberMarker;
    }

    public void setPartNumberMarker(int i) {
        this.partNumberMarker = i;
    }

    public ListPartsRequest withPartNumberMarker(int i) {
        setPartNumberMarker(i);
        return this;
    }
}
