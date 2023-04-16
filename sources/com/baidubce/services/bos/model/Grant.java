package com.baidubce.services.bos.model;

import java.util.List;

public class Grant {
    private List<Grantee> grantee;
    private List<Permission> permission;

    public Grant() {
    }

    public Grant(List<Grantee> list, List<Permission> list2) {
        setGrantee(list);
        setPermission(list2);
    }

    public List<Grantee> getGrantee() {
        return this.grantee;
    }

    public void setGrantee(List<Grantee> list) {
        this.grantee = list;
    }

    public Grant withGrantee(List<Grantee> list) {
        setGrantee(list);
        return this;
    }

    public List<Permission> getPermission() {
        return this.permission;
    }

    public void setPermission(List<Permission> list) {
        this.permission = list;
    }

    public Grant withPermission(List<Permission> list) {
        setPermission(list);
        return this;
    }

    public int hashCode() {
        List<Grantee> list = this.grantee;
        int i = 0;
        int hashCode = ((list == null ? 0 : list.hashCode()) + 31) * 31;
        List<Permission> list2 = this.permission;
        if (list2 != null) {
            i = list2.hashCode();
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
        Grant grant = (Grant) obj;
        List<Grantee> list = this.grantee;
        if (list == null) {
            if (grant.grantee != null) {
                return false;
            }
        } else if (!list.equals(grant.grantee)) {
            return false;
        }
        return equalList(this.permission, grant.permission);
    }

    private boolean equalList(List list, List list2) {
        if (list.size() != list2.size()) {
            return false;
        }
        for (Object contains : list) {
            if (!list2.contains(contains)) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        return "Grant [grantee=" + this.grantee + ", permission=" + this.permission + "]";
    }
}
