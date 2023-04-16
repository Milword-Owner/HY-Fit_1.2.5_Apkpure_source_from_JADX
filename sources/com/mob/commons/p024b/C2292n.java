package com.mob.commons.p024b;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import com.mob.commons.C2312k;
import com.mob.commons.p024b.C2277f;

/* renamed from: com.mob.commons.b.n */
/* compiled from: Zte */
public class C2292n extends C2277f {
    public C2292n(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Intent mo29040a() {
        m2425j();
        Intent intent = new Intent();
        intent.setClassName(C2312k.m2575a(138), C2312k.m2575a(140));
        intent.setAction(C2312k.m2575a(144));
        intent.putExtra(C2312k.m2575a(142), this.f2070b);
        return intent;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public C2277f.C2281c mo29041a(IBinder iBinder) {
        C2277f.C2281c cVar = new C2277f.C2281c();
        cVar.f2082b = mo29047a(C2312k.m2575a(69), iBinder, C2312k.m2575a(145), 3, new String[0]);
        cVar.f2081a = m2426k();
        return cVar;
    }

    /* renamed from: j */
    private void m2425j() {
        try {
            Intent intent = new Intent();
            intent.setClassName(C2312k.m2575a(138), C2312k.m2575a(139));
            intent.setAction(C2312k.m2575a(141));
            intent.putExtra(C2312k.m2575a(142), this.f2070b);
            intent.putExtra(C2312k.m2575a(143), true);
            if (this.f2069a.startService(intent) == null) {
            }
        } catch (Throwable th) {
            C2272c.m2344a().mo29044a(th);
        }
    }

    /* renamed from: k */
    private boolean m2426k() {
        try {
            this.f2069a.getPackageManager().getPackageInfo(C2312k.m2575a(138), 0);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }
}
