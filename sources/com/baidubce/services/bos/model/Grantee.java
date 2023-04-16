package com.baidubce.services.bos.model;

public class Grantee {

    /* renamed from: id */
    private String f1485id;

    public Grantee() {
    }

    public Grantee(String str) {
        setId(str);
    }

    public String getId() {
        return this.f1485id;
    }

    public void setId(String str) {
        this.f1485id = str;
    }

    public Grantee withId(String str) {
        setId(str);
        return this;
    }

    public int hashCode() {
        String str = this.f1485id;
        return 31 + (str == null ? 0 : str.hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Grantee grantee = (Grantee) obj;
        String str = this.f1485id;
        if (str == null) {
            if (grantee.f1485id != null) {
                return false;
            }
        } else if (!str.equals(grantee.f1485id)) {
            return false;
        }
        return true;
    }

    public String toString() {
        return "Grantee [id=" + this.f1485id + "]";
    }
}
