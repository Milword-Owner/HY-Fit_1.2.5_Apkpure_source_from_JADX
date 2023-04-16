package com.mob.commons.p024b;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import com.mob.commons.C2312k;
import com.mob.commons.p024b.C2277f;

/* renamed from: com.mob.commons.b.a */
/* compiled from: ASUS */
public class C2270a extends C2277f {
    public C2270a(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Intent mo29040a() {
        Intent intent = new Intent(C2312k.m2575a(76));
        intent.setComponent(new ComponentName(C2312k.m2575a(77), C2312k.m2575a(78)));
        return intent;
    }

    /* renamed from: a */
    public C2277f.C2281c mo29041a(IBinder iBinder) {
        C2277f.C2281c cVar = new C2277f.C2281c();
        cVar.f2084d = mo29047a(C2312k.m2575a(71), iBinder, C2312k.m2575a(79), 2, new String[0]);
        IBinder iBinder2 = iBinder;
        cVar.f2082b = mo29047a(C2312k.m2575a(69), iBinder2, C2312k.m2575a(79), 3, new String[0]);
        cVar.f2085e = mo29047a(C2312k.m2575a(70), iBinder2, C2312k.m2575a(79), 4, new String[0]);
        cVar.f2083c = mo29047a(C2312k.m2575a(75), iBinder2, C2312k.m2575a(79), 5, new String[0]);
        boolean z = true;
        if (mo29046a("isSupported", iBinder, C2312k.m2575a(79), 1) == 0) {
            z = false;
        }
        cVar.f2081a = z;
        return cVar;
    }
}
