package com.hjq.toast;

import android.widget.Toast;

public class ToastInterceptor implements IToastInterceptor {
    public boolean intercept(Toast toast, CharSequence charSequence) {
        return charSequence == null || "".equals(charSequence.toString());
    }
}
