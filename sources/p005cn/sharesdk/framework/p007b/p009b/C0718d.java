package p005cn.sharesdk.framework.p007b.p009b;

import com.google.android.gms.fitness.FitnessStatusCodes;

/* renamed from: cn.sharesdk.framework.b.b.d */
/* compiled from: DemoEvent */
public class C0718d extends C0717c {

    /* renamed from: d */
    private static int f243d;

    /* renamed from: m */
    private static long f244m;

    /* renamed from: a */
    public String f245a;

    /* renamed from: b */
    public int f246b;

    /* renamed from: c */
    public String f247c = "";

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public String mo10612a() {
        return "[EVT]";
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public int mo10614b() {
        return FitnessStatusCodes.NEEDS_OAUTH_PERMISSIONS;
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public int mo10615c() {
        return 30;
    }

    public String toString() {
        return super.toString() + '|' + this.f245a + '|' + this.f246b + '|' + this.f247c;
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public long mo10616d() {
        return (long) f243d;
    }

    /* access modifiers changed from: protected */
    /* renamed from: e */
    public long mo10617e() {
        return f244m;
    }

    /* access modifiers changed from: protected */
    /* renamed from: f */
    public void mo10618f() {
        f243d++;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo10613a(long j) {
        f244m = j;
    }
}
