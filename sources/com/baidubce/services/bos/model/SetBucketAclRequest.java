package com.baidubce.services.bos.model;

import com.baidubce.auth.BceCredentials;
import java.util.List;

public class SetBucketAclRequest extends GenericBucketRequest {
    private List<Grant> accessControlList;
    private CannedAccessControlList cannedAcl;

    public SetBucketAclRequest(String str, CannedAccessControlList cannedAccessControlList) {
        super(str);
        setCannedAcl(cannedAccessControlList);
    }

    public SetBucketAclRequest(String str, List<Grant> list) {
        super(str);
        setAccessControlList(list);
    }

    public SetBucketAclRequest withRequestCredentials(BceCredentials bceCredentials) {
        setRequestCredentials(bceCredentials);
        return this;
    }

    public SetBucketAclRequest withBucketName(String str) {
        setBucketName(str);
        return this;
    }

    public CannedAccessControlList getCannedAcl() {
        return this.cannedAcl;
    }

    public void setCannedAcl(CannedAccessControlList cannedAccessControlList) {
        this.cannedAcl = cannedAccessControlList;
    }

    public SetBucketAclRequest withCannedAcl(CannedAccessControlList cannedAccessControlList) {
        setCannedAcl(cannedAccessControlList);
        return this;
    }

    public List<Grant> getAccessControlList() {
        return this.accessControlList;
    }

    public void setAccessControlList(List<Grant> list) {
        this.accessControlList = list;
    }

    public SetBucketAclRequest withAccessControlList(List<Grant> list) {
        setAccessControlList(list);
        return this;
    }
}
