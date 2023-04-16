package com.mob.commons.dialog;

import com.mob.OperationCallback;
import com.mob.commons.C2212a;
import com.mob.commons.MobProduct;
import com.mob.commons.dialog.entity.InternalPolicyUi;
import com.mob.tools.MobLog;
import com.mob.tools.log.NLog;

/* renamed from: com.mob.commons.dialog.a */
/* compiled from: AuthDialogManager */
public class C2297a {

    /* renamed from: a */
    private static C2297a f2122a;

    /* renamed from: b */
    private MobProduct f2123b;

    private C2297a() {
    }

    /* renamed from: a */
    public static C2297a m2464a() {
        if (f2122a == null) {
            synchronized (C2297a.class) {
                if (f2122a == null) {
                    f2122a = new C2297a();
                }
            }
        }
        return f2122a;
    }

    /* renamed from: a */
    public void mo29075a(MobProduct mobProduct, InternalPolicyUi internalPolicyUi, OperationCallback<Boolean> operationCallback) {
        try {
            MobLog.getInstance().mo29768d("canIContinueBusiness()", new Object[0]);
            this.f2123b = mobProduct;
            boolean c = C2212a.m1961c();
            NLog instance = MobLog.getInstance();
            instance.mo29768d("====> ppNece: " + c, new Object[0]);
            if (c) {
                boolean d = C2212a.m1963d();
                NLog instance2 = MobLog.getInstance();
                instance2.mo29768d("====> ppGrtd: " + d, new Object[0]);
                if (d) {
                    if (operationCallback != null) {
                        operationCallback.onComplete(true);
                    }
                } else if (operationCallback != null) {
                    operationCallback.onComplete(false);
                }
            } else if (operationCallback != null) {
                operationCallback.onComplete(true);
            }
        } catch (Throwable th) {
            MobLog.getInstance().mo29772e(th);
            if (operationCallback != null) {
                operationCallback.onFailure(th);
            }
        }
    }
}
