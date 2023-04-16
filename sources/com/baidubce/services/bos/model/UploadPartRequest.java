package com.baidubce.services.bos.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.services.bos.callback.BosProgressCallback;
import com.baidubce.util.CheckUtils;
import java.io.InputStream;

public class UploadPartRequest extends GenericUploadRequest {
    private Long crc32;
    private InputStream inputStream;
    private String md5Digest;
    private int partNumber;
    private long partSize;
    private BosProgressCallback progressCallback = null;

    public UploadPartRequest() {
    }

    public UploadPartRequest(String str, String str2, String str3, int i, long j, InputStream inputStream2) {
        super(str, str2, str3);
        setPartNumber(i);
        setPartSize(j);
        setInputStream(inputStream2);
    }

    public UploadPartRequest withRequestCredentials(BceCredentials bceCredentials) {
        setRequestCredentials(bceCredentials);
        return this;
    }

    public UploadPartRequest withBucketName(String str) {
        setBucketName(str);
        return this;
    }

    public UploadPartRequest withKey(String str) {
        setKey(str);
        return this;
    }

    public UploadPartRequest withUploadId(String str) {
        setUploadId(str);
        return this;
    }

    public int getPartNumber() {
        return this.partNumber;
    }

    public void setPartNumber(int i) {
        CheckUtils.checkArgument(i > 0, "partNumber should be positive, but is %s", Integer.valueOf(i));
        this.partNumber = i;
    }

    public UploadPartRequest withPartNumber(int i) {
        setPartNumber(i);
        return this;
    }

    public long getPartSize() {
        return this.partSize;
    }

    public void setPartSize(long j) {
        CheckUtils.checkArgument(j >= 0, "partSize should not be negative.");
        this.partSize = j;
    }

    public UploadPartRequest withPartSize(long j) {
        setPartSize(j);
        return this;
    }

    public Long getCrc32() {
        return this.crc32;
    }

    public void setCrc32(Long l) {
        this.crc32 = l;
    }

    public UploadPartRequest withCrc32(Long l) {
        setCrc32(l);
        return this;
    }

    public String getMd5Digest() {
        return this.md5Digest;
    }

    public void setMd5Digest(String str) {
        this.md5Digest = str;
    }

    public UploadPartRequest withMD5Digest(String str) {
        setMd5Digest(str);
        return this;
    }

    public InputStream getInputStream() {
        return this.inputStream;
    }

    public void setInputStream(InputStream inputStream2) {
        CheckUtils.isNotNull(inputStream2, "inputStream should not be null.");
        this.inputStream = inputStream2;
    }

    public UploadPartRequest withInputStream(InputStream inputStream2) {
        setInputStream(inputStream2);
        return this;
    }

    public BosProgressCallback getProgressCallback() {
        return this.progressCallback;
    }

    public <T extends UploadPartRequest> void setProgressCallback(BosProgressCallback<T> bosProgressCallback) {
        this.progressCallback = bosProgressCallback;
    }

    public <T extends UploadPartRequest> UploadPartRequest withProgressCallback(BosProgressCallback<T> bosProgressCallback) {
        this.progressCallback = bosProgressCallback;
        return this;
    }
}
