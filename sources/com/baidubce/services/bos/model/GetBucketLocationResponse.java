package com.baidubce.services.bos.model;

public class GetBucketLocationResponse extends BosResponse {
    private String locationConstraint = "";

    public String getLocationConstraint() {
        return this.locationConstraint;
    }

    public void setLocationConstraint(String str) {
        this.locationConstraint = str;
    }
}
