package com.baidubce.services.bos.model;

public class PartETag {
    private String eTag;
    private int partNumber;

    public PartETag() {
    }

    public PartETag(int i, String str) {
        setPartNumber(i);
        setETag(str);
    }

    public int getPartNumber() {
        return this.partNumber;
    }

    public void setPartNumber(int i) {
        this.partNumber = i;
    }

    public PartETag withPartNumber(int i) {
        setPartNumber(i);
        return this;
    }

    public String getETag() {
        return this.eTag;
    }

    public void setETag(String str) {
        this.eTag = str;
    }

    public PartETag withETag(String str) {
        setETag(str);
        return this;
    }

    public int hashCode() {
        String str = this.eTag;
        return (((str == null ? 0 : str.hashCode()) + 31) * 31) + this.partNumber;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PartETag partETag = (PartETag) obj;
        String str = this.eTag;
        if (str == null) {
            if (partETag.eTag != null) {
                return false;
            }
        } else if (!str.equals(partETag.eTag)) {
            return false;
        }
        return this.partNumber == partETag.partNumber;
    }

    public String toString() {
        return "PartETag [partNumber=" + this.partNumber + ", eTag=" + this.eTag + "]";
    }
}
