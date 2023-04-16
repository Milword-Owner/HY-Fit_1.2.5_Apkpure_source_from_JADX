package com.baidubce.model;

public class User {
    private String displayName;

    /* renamed from: id */
    private String f1484id;

    public User() {
    }

    public User(String str, String str2) {
        setId(str);
        setDisplayName(str2);
    }

    public String getId() {
        return this.f1484id;
    }

    public void setId(String str) {
        this.f1484id = str;
    }

    public User withId(String str) {
        setId(str);
        return this;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public void setDisplayName(String str) {
        this.displayName = str;
    }

    public User withDisplayName(String str) {
        setDisplayName(str);
        return this;
    }

    public int hashCode() {
        String str = this.displayName;
        int i = 0;
        int hashCode = ((str == null ? 0 : str.hashCode()) + 31) * 31;
        String str2 = this.f1484id;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode + i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        User user = (User) obj;
        String str = this.displayName;
        if (str == null) {
            if (user.displayName != null) {
                return false;
            }
        } else if (!str.equals(user.displayName)) {
            return false;
        }
        String str2 = this.f1484id;
        if (str2 == null) {
            if (user.f1484id != null) {
                return false;
            }
        } else if (!str2.equals(user.f1484id)) {
            return false;
        }
        return true;
    }

    public String toString() {
        return "User [id=" + this.f1484id + ", displayName=" + this.displayName + "]";
    }
}
