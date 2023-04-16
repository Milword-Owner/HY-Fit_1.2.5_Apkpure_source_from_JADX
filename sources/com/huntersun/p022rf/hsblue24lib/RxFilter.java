package com.huntersun.p022rf.hsblue24lib;

import com.baidu.mobstat.Config;
import kotlin.UByte;

/* renamed from: com.huntersun.rf.hsblue24lib.RxFilter */
public final class RxFilter {
    protected static final int INVALID_USERID = -1;
    private static final int user_data_length = 13;
    private String deviceAddress = null;
    private String deviceName = null;
    private byte[] userData = new byte[0];
    private byte[] userDataMask = new byte[0];
    private int userId = -1;

    public RxFilter setDeviceAddress(byte[] bArr) {
        this.deviceAddress = HsString.getHexString(bArr, Config.TRACE_TODAY_VISIT_SPLIT);
        return this;
    }

    public RxFilter setUserId(byte[] bArr) {
        if (bArr == null) {
            this.userId = -1;
        } else {
            this.userId = bArr[0] & UByte.MAX_VALUE;
            this.userId = (this.userId << 8) + (bArr[1] & UByte.MAX_VALUE);
        }
        return this;
    }

    private RxFilter setUserData(byte[] bArr) {
        if (bArr == null) {
            this.userData = new byte[0];
            this.userDataMask = new byte[0];
        } else {
            this.userData = new byte[13];
            for (int i = 0; i < bArr.length; i++) {
                getUserData()[i] = bArr[i];
            }
        }
        return this;
    }

    private RxFilter setUserDataMask(byte[] bArr) {
        if (bArr == null) {
            this.userDataMask = new byte[0];
        } else {
            this.userDataMask = new byte[13];
            for (int i = 0; i < bArr.length; i++) {
                getUserDataMask()[i] = bArr[i];
            }
        }
        return this;
    }

    public RxFilter setDeviceName(String str) {
        if (str == null || str.isEmpty()) {
            this.deviceName = null;
        } else {
            this.deviceName = str;
        }
        return this;
    }

    /* access modifiers changed from: protected */
    public String getDeviceAddress() {
        return this.deviceAddress;
    }

    /* access modifiers changed from: protected */
    public int getUserId() {
        return this.userId;
    }

    /* access modifiers changed from: protected */
    public byte[] getUserData() {
        return this.userData;
    }

    /* access modifiers changed from: protected */
    public byte[] getUserDataMask() {
        return this.userDataMask;
    }

    /* access modifiers changed from: protected */
    public String getDeviceName() {
        return this.deviceName;
    }
}
