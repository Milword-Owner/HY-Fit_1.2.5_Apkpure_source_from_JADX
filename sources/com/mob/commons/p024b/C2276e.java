package com.mob.commons.p024b;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.text.TextUtils;
import com.mob.commons.C2312k;
import com.mob.commons.p024b.C2277f;

/* renamed from: com.mob.commons.b.e */
/* compiled from: Huawei */
public class C2276e extends C2277f {
    public C2276e(Context context) {
        super(context);
    }

    /* renamed from: b */
    public synchronized String mo29045b() {
        return mo29057i();
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Intent mo29040a() {
        Intent intent = new Intent(C2312k.m2575a(80));
        intent.setPackage(C2312k.m2575a(81));
        return intent;
    }

    /* renamed from: a */
    public C2277f.C2281c mo29041a(IBinder iBinder) {
        String a = C2312k.m2575a(82);
        C2277f.C2281c cVar = new C2277f.C2281c();
        cVar.f2082b = mo29047a(C2312k.m2575a(69), iBinder, a, 1, new String[0]);
        mo29046a(C2312k.m2575a(83), iBinder, a, 2);
        cVar.f2081a = !TextUtils.isEmpty(cVar.f2082b);
        return cVar;
    }
}
