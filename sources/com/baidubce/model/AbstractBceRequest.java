package com.baidubce.model;

import com.baidubce.auth.BceCredentials;
import okhttp3.Call;

public abstract class AbstractBceRequest {
    private Call call;
    private boolean canceled = false;
    private BceCredentials credentials;

    public abstract AbstractBceRequest withRequestCredentials(BceCredentials bceCredentials);

    public BceCredentials getRequestCredentials() {
        return this.credentials;
    }

    public void setRequestCredentials(BceCredentials bceCredentials) {
        this.credentials = bceCredentials;
    }

    public void cancel() {
        Call call2 = this.call;
        if (call2 != null) {
            call2.cancel();
        }
        this.canceled = true;
    }

    public boolean getCanceled() {
        return this.canceled;
    }

    public boolean isCanceled() {
        Call call2 = this.call;
        if (call2 == null) {
            return this.canceled;
        }
        return call2.isCanceled();
    }

    public void setCall(Call call2) {
        this.call = call2;
    }
}
