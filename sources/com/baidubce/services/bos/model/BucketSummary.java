package com.baidubce.services.bos.model;

import java.util.Date;

public class BucketSummary {
    private Date creationDate = null;
    private String location = null;
    private String name = null;

    public BucketSummary() {
    }

    public BucketSummary(String str, String str2) {
        this.name = str;
        this.location = str2;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String str) {
        this.location = str;
    }

    public void setCreationDate(Date date) {
        this.creationDate = date;
    }

    public String toString() {
        return "Bucket [name=" + this.name + ", creationDate=" + this.creationDate + "]";
    }
}
