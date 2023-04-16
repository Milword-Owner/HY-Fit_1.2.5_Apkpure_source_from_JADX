package com.baidu.mobstat;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.text.TextUtils;
import com.baidu.mobstat.C1003cd;

/* renamed from: com.baidu.mobstat.by */
public class C0995by {
    /* renamed from: a */
    public static void m1487a(Context context, final C1001cb cbVar) {
        Intent intent = new Intent("com.uodis.opendevice.OPENIDS_SERVICE");
        intent.setPackage("com.huawei.hwid");
        context.bindService(intent, new ServiceConnection() {
            public void onServiceDisconnected(ComponentName componentName) {
            }

            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                try {
                    C1003cd a = C1003cd.C1004a.m1503a(iBinder);
                    if (!TextUtils.isEmpty(a.mo11732a()) && cbVar != null) {
                        cbVar.mo11731a(a.mo11732a());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 1);
    }
}
