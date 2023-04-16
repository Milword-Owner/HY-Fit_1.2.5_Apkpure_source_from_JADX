package com.hjq.toast;

import android.app.Application;

final class SupportToast extends BaseToast {
    private final ToastHelper mToastHelper;

    SupportToast(Application application) {
        super(application);
        this.mToastHelper = new ToastHelper(this, application);
    }

    public void show() {
        this.mToastHelper.show();
    }

    public void cancel() {
        this.mToastHelper.cancel();
    }
}
