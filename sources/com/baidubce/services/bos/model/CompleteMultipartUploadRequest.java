package com.baidubce.services.bos.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.util.CheckUtils;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CompleteMultipartUploadRequest extends GenericUploadRequest {
    private ObjectMetadata objectMetadata;
    private List<PartETag> partETags;
    private String process;

    public CompleteMultipartUploadRequest() {
        this.objectMetadata = new ObjectMetadata();
    }

    public CompleteMultipartUploadRequest(String str, String str2, String str3, List<PartETag> list) {
        this(str, str2, str3, list, (ObjectMetadata) null);
    }

    public CompleteMultipartUploadRequest(String str, String str2, String str3, List<PartETag> list, ObjectMetadata objectMetadata2) {
        super(str, str2, str3);
        this.objectMetadata = new ObjectMetadata();
        this.partETags = list;
        this.objectMetadata = objectMetadata2;
    }

    public CompleteMultipartUploadRequest withRequestCredentials(BceCredentials bceCredentials) {
        setRequestCredentials(bceCredentials);
        return this;
    }

    public CompleteMultipartUploadRequest withBucketName(String str) {
        setBucketName(str);
        return this;
    }

    public CompleteMultipartUploadRequest withKey(String str) {
        setKey(str);
        return this;
    }

    public CompleteMultipartUploadRequest withUploadId(String str) {
        setUploadId(str);
        return this;
    }

    public ObjectMetadata getObjectMetadata() {
        return this.objectMetadata;
    }

    public void setObjectMetadata(ObjectMetadata objectMetadata2) {
        this.objectMetadata = objectMetadata2;
    }

    public CompleteMultipartUploadRequest withObjectMetadata(ObjectMetadata objectMetadata2) {
        setObjectMetadata(objectMetadata2);
        return this;
    }

    public List<PartETag> getPartETags() {
        return this.partETags;
    }

    public void setPartETags(List<PartETag> list) {
        CheckUtils.isNotNull(list, "partETags should not be null.");
        for (int i = 0; i < list.size(); i++) {
            PartETag partETag = list.get(i);
            CheckUtils.isNotNull(partETag, "partETags[%s] should not be null.", Integer.valueOf(i));
            int partNumber = partETag.getPartNumber();
            CheckUtils.checkArgument(partNumber > 0, "partNumber should be positive. partETags[%s].partNumber:%s", Integer.valueOf(i), Integer.valueOf(partNumber));
            CheckUtils.isNotNull(partETag.getETag(), "partETags[%s].eTag should not be null.", Integer.valueOf(i));
        }
        Collections.sort(list, new Comparator<PartETag>() {
            public int compare(PartETag partETag, PartETag partETag2) {
                return partETag.getPartNumber() - partETag2.getPartNumber();
            }
        });
        int i2 = 0;
        int i3 = 0;
        while (i2 < list.size()) {
            int partNumber2 = list.get(i2).getPartNumber();
            CheckUtils.checkArgument(partNumber2 != i3, "Duplicated partNumber %s.", Integer.valueOf(partNumber2));
            i2++;
            i3 = partNumber2;
        }
        this.partETags = list;
    }

    public CompleteMultipartUploadRequest withPartETags(List<PartETag> list) {
        setPartETags(list);
        return this;
    }

    public String getProcess() {
        return this.process;
    }

    public void setProcess(String str) {
        this.process = str;
    }
}
