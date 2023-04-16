package com.clj.fastble.exception.hanlder;

import com.clj.fastble.exception.ConnectException;
import com.clj.fastble.exception.GattException;
import com.clj.fastble.exception.NotFoundDeviceException;
import com.clj.fastble.exception.OtherException;
import com.clj.fastble.exception.TimeoutException;
import com.clj.fastble.utils.BleLog;

public class DefaultBleExceptionHandler extends BleExceptionHandler {
    private static final String TAG = "BleExceptionHandler";

    /* access modifiers changed from: protected */
    public void onConnectException(ConnectException connectException) {
        BleLog.e(TAG, connectException.getDescription());
    }

    /* access modifiers changed from: protected */
    public void onGattException(GattException gattException) {
        BleLog.e(TAG, gattException.getDescription());
    }

    /* access modifiers changed from: protected */
    public void onTimeoutException(TimeoutException timeoutException) {
        BleLog.e(TAG, timeoutException.getDescription());
    }

    /* access modifiers changed from: protected */
    public void onNotFoundDeviceException(NotFoundDeviceException notFoundDeviceException) {
        BleLog.e(TAG, notFoundDeviceException.getDescription());
    }

    /* access modifiers changed from: protected */
    public void onOtherException(OtherException otherException) {
        BleLog.e(TAG, otherException.getDescription());
    }
}
