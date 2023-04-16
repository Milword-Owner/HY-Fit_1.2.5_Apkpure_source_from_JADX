package com.mob.commons.p024b;

import android.content.ContentProviderClient;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import com.mob.commons.C2312k;
import com.mob.commons.p024b.C2277f;

/* renamed from: com.mob.commons.b.i */
/* compiled from: Nubia */
public class C2286i extends C2277f {

    /* renamed from: c */
    private Uri f2096c = Uri.parse(C2312k.m2575a(118));

    public C2286i(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public C2277f.C2281c mo29050c() {
        C2277f.C2281c cVar = new C2277f.C2281c();
        cVar.f2081a = m2407j();
        cVar.f2083c = m2404a(C2312k.m2575a(102), this.f2070b);
        cVar.f2082b = m2404a(C2312k.m2575a(100), (String) null);
        cVar.f2085e = m2404a(C2312k.m2575a(101), this.f2070b);
        return cVar;
    }

    /* renamed from: a */
    private String m2404a(String str, String str2) {
        Bundle b = m2406b(str, str2);
        if (m2405a(b)) {
            return b.getString(C2312k.m2575a(122));
        }
        if (b != null) {
            return b.getString(C2312k.m2575a(123));
        }
        return null;
    }

    /* renamed from: j */
    private boolean m2407j() {
        Bundle b = m2406b(C2312k.m2575a(120), (String) null);
        if (m2405a(b)) {
            return b.getBoolean(C2312k.m2575a(121), true);
        }
        return false;
    }

    /* renamed from: b */
    private Bundle m2406b(String str, String str2) {
        try {
            if (Build.VERSION.SDK_INT >= 17) {
                ContentProviderClient acquireUnstableContentProviderClient = this.f2069a.getContentResolver().acquireUnstableContentProviderClient(this.f2096c);
                Bundle call = acquireUnstableContentProviderClient.call(str, str2, (Bundle) null);
                if (acquireUnstableContentProviderClient == null) {
                    return call;
                }
                if (Build.VERSION.SDK_INT >= 24) {
                    acquireUnstableContentProviderClient.close();
                    return call;
                }
                acquireUnstableContentProviderClient.release();
                return call;
            } else if (Build.VERSION.SDK_INT >= 11) {
                return this.f2069a.getContentResolver().call(this.f2096c, str, str2, (Bundle) null);
            } else {
                return null;
            }
        } catch (Throwable th) {
            C2272c.m2344a().mo29044a(th);
            return null;
        }
    }

    /* renamed from: a */
    private boolean m2405a(Bundle bundle) {
        if (bundle == null) {
            return false;
        }
        try {
            return bundle.getInt(C2312k.m2575a(119), -1) == 0;
        } catch (Throwable unused) {
            return false;
        }
    }
}
