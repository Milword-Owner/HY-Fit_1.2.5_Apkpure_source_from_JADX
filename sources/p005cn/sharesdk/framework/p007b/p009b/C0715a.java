package p005cn.sharesdk.framework.p007b.p009b;

import com.google.android.gms.fitness.FitnessStatusCodes;

/* renamed from: cn.sharesdk.framework.b.b.a */
/* compiled from: ApiEvent */
public class C0715a extends C0717c {

    /* renamed from: c */
    private static int f225c;

    /* renamed from: d */
    private static long f226d;

    /* renamed from: a */
    public int f227a;

    /* renamed from: b */
    public String f228b;

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public String mo10612a() {
        return "[API]";
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public int mo10614b() {
        return FitnessStatusCodes.NEEDS_OAUTH_PERMISSIONS;
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public int mo10615c() {
        return 50;
    }

    public String toString() {
        return super.toString() + '|' + this.f227a + '|' + this.f228b;
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public long mo10616d() {
        return (long) f225c;
    }

    /* access modifiers changed from: protected */
    /* renamed from: e */
    public long mo10617e() {
        return f226d;
    }

    /* access modifiers changed from: protected */
    /* renamed from: f */
    public void mo10618f() {
        f225c++;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo10613a(long j) {
        f226d = j;
    }
}
