package com.clj.fastble.exception.hanlder;

import com.clj.fastble.exception.BleException;
import com.clj.fastble.exception.ConnectException;
import com.clj.fastble.exception.GattException;
import com.clj.fastble.exception.NotFoundDeviceException;
import com.clj.fastble.exception.OtherException;
import com.clj.fastble.exception.TimeoutException;

public abstract class BleExceptionHandler {
    /* access modifiers changed from: protected */
    public abstract void onConnectException(ConnectException connectException);

    /* access modifiers changed from: protected */
    public abstract void onGattException(GattException gattException);

    /* access modifiers changed from: protected */
    public abstract void onNotFoundDeviceException(NotFoundDeviceException notFoundDeviceException);

    /* access modifiers changed from: protected */
    public abstract void onOtherException(OtherException otherException);

    /* access modifiers changed from: protected */
    public abstract void onTimeoutException(TimeoutException timeoutException);

    public BleExceptionHandler handleException(BleException bleException) {
        if (bleException != null) {
            if (bleException instanceof ConnectException) {
                onConnectException((ConnectException) bleException);
            } else if (bleException instanceof GattException) {
                onGattException((GattException) bleException);
            } else if (bleException instanceof TimeoutException) {
                onTimeoutException((TimeoutException) bleException);
            } else if (bleException instanceof NotFoundDeviceException) {
                onNotFoundDeviceException((NotFoundDeviceException) bleException);
            } else {
                onOtherException((OtherException) bleException);
            }
        }
        return this;
    }
}
