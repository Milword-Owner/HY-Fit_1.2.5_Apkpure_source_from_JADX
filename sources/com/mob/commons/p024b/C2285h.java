package com.mob.commons.p024b;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import com.mob.commons.C2312k;
import com.mob.commons.p024b.C2277f;

/* renamed from: com.mob.commons.b.h */
/* compiled from: MotoLennovo */
public class C2285h extends C2277f {
    /* access modifiers changed from: protected */
    /* renamed from: d */
    public long mo29052d() {
        return 3000;
    }

    public C2285h(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Intent mo29040a() {
        Intent intent = new Intent();
        intent.setClassName(C2312k.m2575a(84), C2312k.m2575a(85));
        return intent;
    }

    /* renamed from: a */
    public C2277f.C2281c mo29041a(IBinder iBinder) {
        String a = C2312k.m2575a(86);
        C2277f.C2281c cVar = new C2277f.C2281c();
        boolean z = false;
        IBinder iBinder2 = iBinder;
        String str = a;
        cVar.f2082b = mo29047a(C2312k.m2575a(69), iBinder2, str, 1, new String[0]);
        cVar.f2085e = mo29047a(C2312k.m2575a(70), iBinder2, str, 4, this.f2070b);
        cVar.f2084d = mo29047a(C2312k.m2575a(71), iBinder2, str, 2, new String[0]);
        cVar.f2083c = mo29047a(C2312k.m2575a(75), iBinder2, str, 5, this.f2070b);
        if (mo29046a(C2312k.m2575a(74), iBinder, a, 3) != 0) {
            z = true;
        }
        cVar.f2081a = z;
        return cVar;
    }
}
