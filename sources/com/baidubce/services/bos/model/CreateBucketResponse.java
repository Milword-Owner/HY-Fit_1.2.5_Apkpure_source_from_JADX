package com.baidubce.services.bos.model;

public class CreateBucketResponse {
    private String location = null;
    private String name = null;

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String str) {
        this.location = str;
    }
}
