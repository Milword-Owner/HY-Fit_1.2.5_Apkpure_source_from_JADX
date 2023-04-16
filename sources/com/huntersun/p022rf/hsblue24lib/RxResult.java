package com.huntersun.p022rf.hsblue24lib;

/* renamed from: com.huntersun.rf.hsblue24lib.RxResult */
public final class RxResult {
    public static final boolean Enable_RecordInTheAir = true;
    private String deviceAddress;
    private String deviceName;
    private byte[] record;
    private byte[] recordInTheAir;
    private int rssi;
    private long timestampNanos;
    private byte[] userData;
    private int userId;

    public String getDeviceName() {
        return this.deviceName;
    }

    /* access modifiers changed from: protected */
    public void setDeviceName(String str) {
        this.deviceName = str;
    }

    public byte[] getDeviceAddress() {
        return HsString.ParseHexString(this.deviceAddress.substring(3));
    }

    /* access modifiers changed from: protected */
    public void setDeviceAddress(String str) {
        this.deviceAddress = str;
    }

    public byte[] getUserId() {
        int i = this.userId;
        if (i == -1) {
            return null;
        }
        return new byte[]{(byte) (i >>> 8), (byte) i};
    }

    /* access modifiers changed from: package-private */
    public void setUserId(int i) {
        this.userId = i;
    }

    public byte[] getUserData() {
        return this.userData;
    }

    /* access modifiers changed from: protected */
    public void setUserData(byte[] bArr) {
        this.userData = bArr;
    }

    public int getRssi() {
        return this.rssi;
    }

    /* access modifiers changed from: protected */
    public void setRssi(int i) {
        this.rssi = i;
    }

    public long getTimestampNanos() {
        return this.timestampNanos;
    }

    /* access modifiers changed from: protected */
    public void setTimestampNanos(long j) {
        this.timestampNanos = j;
    }

    @Deprecated
    public byte[] getRecordInTheAir() {
        return this.recordInTheAir;
    }

    /* access modifiers changed from: protected */
    public void setRecordInTheAir(byte[] bArr) {
        this.recordInTheAir = bArr;
    }

    @Deprecated
    public byte[] getRecord() {
        return this.record;
    }

    /* access modifiers changed from: protected */
    public void setRecord(byte[] bArr) {
        this.record = bArr;
    }
}
