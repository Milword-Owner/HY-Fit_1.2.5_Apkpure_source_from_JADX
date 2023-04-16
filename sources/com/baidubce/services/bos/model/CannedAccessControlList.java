package com.baidubce.services.bos.model;

public enum CannedAccessControlList {
    Private("private"),
    PublicRead("public-read"),
    PublicReadWrite("public-read-write");
    
    private final String cannedAclHeader;

    private CannedAccessControlList(String str) {
        this.cannedAclHeader = str;
    }

    public String toString() {
        return this.cannedAclHeader;
    }
}
