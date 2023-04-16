package com.baidubce.services.bos.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.services.bos.callback.BosProgressCallback;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

public class PutSuperObjectRequest extends GenericObjectRequest {
    private static final long PART_SIZE = 5242880;
    private static final int THREADS_NUM = 5;
    private AtomicLong currentSize;
    private File file;
    private AtomicBoolean isSuperObjectUploadCancelled;
    private int nThreads;
    private Map<Integer, Long> partNum_current;
    private long partSize;
    private BosProgressCallback progressCallback;
    private long totalSize;
    private String uploadId;

    public BosProgressCallback getProgressCallback() {
        return this.progressCallback;
    }

    public <T extends PutSuperObjectRequest> void setProgressCallback(BosProgressCallback<T> bosProgressCallback) {
        this.progressCallback = bosProgressCallback;
    }

    public void cancel() {
        this.isSuperObjectUploadCancelled.set(true);
        super.cancel();
    }

    public PutSuperObjectRequest(String str, String str2, File file2) {
        this(str, str2, file2, PART_SIZE, 5);
    }

    public PutSuperObjectRequest(String str, String str2, File file2, long j) {
        this(str, str2, file2, j, 5);
    }

    public PutSuperObjectRequest(String str, String str2, File file2, int i) {
        this(str, str2, file2, PART_SIZE, i);
    }

    public PutSuperObjectRequest(String str, String str2, File file2, long j, int i) {
        super(str, str2);
        this.isSuperObjectUploadCancelled = new AtomicBoolean(false);
        this.progressCallback = null;
        this.partNum_current = new HashMap();
        this.file = file2;
        this.partSize = j;
        this.nThreads = i;
    }

    public long getPartSize() {
        return this.partSize;
    }

    public void setPartSize(long j) {
        this.partSize = j;
    }

    public PutSuperObjectRequest withPartSize(long j) {
        setPartSize(j);
        return this;
    }

    public PutSuperObjectRequest withKey(String str) {
        setKey(str);
        return this;
    }

    public PutSuperObjectRequest withBucketName(String str) {
        setBucketName(str);
        return this;
    }

    public PutSuperObjectRequest withRequestCredentials(BceCredentials bceCredentials) {
        setRequestCredentials(bceCredentials);
        return this;
    }

    public File getFile() {
        return this.file;
    }

    public void setFile(File file2) {
        this.file = file2;
    }

    public PutSuperObjectRequest withFile(File file2) {
        setFile(file2);
        return this;
    }

    public int getnThreads() {
        return this.nThreads;
    }

    public void setnThreads(int i) {
        this.nThreads = i;
    }

    public PutSuperObjectRequest withnThreads(int i) {
        setnThreads(i);
        return this;
    }

    public AtomicBoolean getIsSuperObjectUploadCancelled() {
        return this.isSuperObjectUploadCancelled;
    }

    public void setIsSuperObjectUploadCancelled(AtomicBoolean atomicBoolean) {
        this.isSuperObjectUploadCancelled = atomicBoolean;
    }

    public PutSuperObjectRequest withIsSuperObjectUploadCanced(AtomicBoolean atomicBoolean) {
        setIsSuperObjectUploadCancelled(atomicBoolean);
        return this;
    }

    public String getUploadId() {
        return this.uploadId;
    }

    public void setUploadId(String str) {
        this.uploadId = str;
    }

    public PutSuperObjectRequest withUploadId(String str) {
        setUploadId(str);
        return this;
    }

    public void initSuperFileTask(long j, int i) {
        this.currentSize = new AtomicLong(0);
        setTotalSize(j);
        for (int i2 = 1; i2 < i + 1; i2++) {
            this.partNum_current.put(Integer.valueOf(i2), 0L);
        }
    }

    public void setCurrentSize(long j) {
        this.currentSize.set(j);
    }

    public void setCurrentSize(int i, long j) {
        this.partNum_current.put(Integer.valueOf(i), Long.valueOf(j));
    }

    public long getCurrentSize() {
        return this.currentSize.get();
    }

    public long getCurrentSize(int i) {
        if (i < 0 || i > this.partNum_current.size()) {
            return 0;
        }
        return this.partNum_current.get(Integer.valueOf(i)).longValue();
    }

    public void addCurrentSize(long j) {
        this.currentSize.addAndGet(j);
    }

    public void setTotalSize(long j) {
        this.totalSize = j;
    }

    public long getTotalSize() {
        return this.totalSize;
    }

    public long getUpdateCurrentSize(int i, long j) {
        long longValue = j - this.partNum_current.get(Integer.valueOf(i)).longValue();
        this.partNum_current.put(Integer.valueOf(i), Long.valueOf(j));
        return this.currentSize.addAndGet(longValue);
    }
}
