package com.baidubce.services.bos.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.util.CheckUtils;
import java.io.File;
import java.io.InputStream;

public class AppendObjectRequest extends PutObjectRequest {
    private Long offset;

    public AppendObjectRequest(String str, String str2, File file) {
        this(str, str2, file, (InputStream) null, new ObjectMetadata());
        CheckUtils.isNotNull(file, "file should not be null.");
    }

    public AppendObjectRequest(String str, String str2, File file, ObjectMetadata objectMetadata) {
        this(str, str2, file, (InputStream) null, objectMetadata);
        CheckUtils.isNotNull(file, "file should not be null.");
        CheckUtils.isNotNull(objectMetadata, "metadata should not be null.");
    }

    public AppendObjectRequest(String str, String str2, InputStream inputStream) {
        this(str, str2, (File) null, inputStream, new ObjectMetadata());
        CheckUtils.isNotNull(inputStream, "inputStream should not be null.");
    }

    public AppendObjectRequest(String str, String str2, InputStream inputStream, ObjectMetadata objectMetadata) {
        this(str, str2, (File) null, inputStream, objectMetadata);
        CheckUtils.isNotNull(inputStream, "inputStream should not be null.");
        CheckUtils.isNotNull(objectMetadata, "metadata should not be null.");
    }

    private AppendObjectRequest(String str, String str2, File file, InputStream inputStream, ObjectMetadata objectMetadata) {
        super(str, str2, file, inputStream, objectMetadata);
    }

    public Long getOffset() {
        return this.offset;
    }

    public void setOffset(Long l) {
        this.offset = l;
    }

    public AppendObjectRequest withOffset(Long l) {
        setOffset(l);
        return this;
    }

    public AppendObjectRequest withObjectMetadata(ObjectMetadata objectMetadata) {
        setObjectMetadata(objectMetadata);
        return this;
    }

    public AppendObjectRequest withFile(File file) {
        setFile(file);
        return this;
    }

    public AppendObjectRequest withInputStream(InputStream inputStream) {
        setInputStream(inputStream);
        return this;
    }

    public AppendObjectRequest withRequestCredentials(BceCredentials bceCredentials) {
        setRequestCredentials(bceCredentials);
        return this;
    }

    public AppendObjectRequest withBucketName(String str) {
        setBucketName(str);
        return this;
    }

    public AppendObjectRequest withKey(String str) {
        setKey(str);
        return this;
    }
}
