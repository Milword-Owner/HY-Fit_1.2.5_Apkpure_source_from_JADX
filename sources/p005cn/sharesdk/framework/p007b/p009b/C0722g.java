package p005cn.sharesdk.framework.p007b.p009b;

import android.text.TextUtils;
import com.google.android.gms.fitness.FitnessStatusCodes;
import p005cn.sharesdk.framework.p007b.p008a.C0713e;

/* renamed from: cn.sharesdk.framework.b.b.g */
/* compiled from: StartEvent */
public class C0722g extends C0717c {

    /* renamed from: a */
    private static int f266a;

    /* renamed from: b */
    private static long f267b;

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public String mo10612a() {
        return "[RUN]";
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public int mo10614b() {
        return FitnessStatusCodes.NEEDS_OAUTH_PERMISSIONS;
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public int mo10615c() {
        return 5;
    }

    /* renamed from: g */
    public boolean mo10620g() {
        C0713e a = C0713e.m196a();
        f266a = a.mo10606j("insertRunEventCount");
        f267b = a.mo10604i("lastInsertRunEventTime");
        return super.mo10620g();
    }

    /* renamed from: h */
    public void mo10621h() {
        super.mo10621h();
        C0713e a = C0713e.m196a();
        a.mo10584a("lastInsertRunEventTime", Long.valueOf(f267b));
        a.mo10583a("insertRunEventCount", f266a);
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public long mo10616d() {
        return (long) f266a;
    }

    /* access modifiers changed from: protected */
    /* renamed from: e */
    public long mo10617e() {
        return f267b;
    }

    /* access modifiers changed from: protected */
    /* renamed from: f */
    public void mo10618f() {
        f266a++;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo10613a(long j) {
        f267b = j;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append('|');
        if (!TextUtils.isEmpty(this.f242l)) {
            sb.append(this.f242l);
        }
        return sb.toString();
    }
}
