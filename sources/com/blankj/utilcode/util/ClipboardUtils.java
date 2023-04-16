package com.blankj.utilcode.util;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;

public final class ClipboardUtils {
    private ClipboardUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static void copyText(CharSequence charSequence) {
        ((ClipboardManager) Utils.getApp().getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText(Utils.getApp().getPackageName(), charSequence));
    }

    public static void copyText(CharSequence charSequence, CharSequence charSequence2) {
        ((ClipboardManager) Utils.getApp().getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText(charSequence, charSequence2));
    }

    public static void clear() {
        ((ClipboardManager) Utils.getApp().getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText((CharSequence) null, ""));
    }

    public static CharSequence getLabel() {
        CharSequence label;
        ClipDescription primaryClipDescription = ((ClipboardManager) Utils.getApp().getSystemService("clipboard")).getPrimaryClipDescription();
        if (primaryClipDescription == null || (label = primaryClipDescription.getLabel()) == null) {
            return "";
        }
        return label;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0018, code lost:
        r0 = r0.getItemAt(0).coerceToText(com.blankj.utilcode.util.Utils.getApp());
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.CharSequence getText() {
        /*
            android.app.Application r0 = com.blankj.utilcode.util.Utils.getApp()
            java.lang.String r1 = "clipboard"
            java.lang.Object r0 = r0.getSystemService(r1)
            android.content.ClipboardManager r0 = (android.content.ClipboardManager) r0
            android.content.ClipData r0 = r0.getPrimaryClip()
            if (r0 == 0) goto L_0x0028
            int r1 = r0.getItemCount()
            if (r1 <= 0) goto L_0x0028
            r1 = 0
            android.content.ClipData$Item r0 = r0.getItemAt(r1)
            android.app.Application r1 = com.blankj.utilcode.util.Utils.getApp()
            java.lang.CharSequence r0 = r0.coerceToText(r1)
            if (r0 == 0) goto L_0x0028
            return r0
        L_0x0028:
            java.lang.String r0 = ""
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.blankj.utilcode.util.ClipboardUtils.getText():java.lang.CharSequence");
    }

    public static void addChangedListener(ClipboardManager.OnPrimaryClipChangedListener onPrimaryClipChangedListener) {
        ((ClipboardManager) Utils.getApp().getSystemService("clipboard")).addPrimaryClipChangedListener(onPrimaryClipChangedListener);
    }

    public static void removeChangedListener(ClipboardManager.OnPrimaryClipChangedListener onPrimaryClipChangedListener) {
        ((ClipboardManager) Utils.getApp().getSystemService("clipboard")).removePrimaryClipChangedListener(onPrimaryClipChangedListener);
    }
}
