package p005cn.sharesdk.framework.p007b.p009b;

import com.mob.MobSDK;

/* renamed from: cn.sharesdk.framework.b.b.c */
/* compiled from: BaseEvent */
public abstract class C0717c {

    /* renamed from: e */
    public long f235e;

    /* renamed from: f */
    public String f236f;

    /* renamed from: g */
    public String f237g;

    /* renamed from: h */
    public int f238h;

    /* renamed from: i */
    public String f239i;

    /* renamed from: j */
    public int f240j;

    /* renamed from: k */
    public String f241k;

    /* renamed from: l */
    public String f242l;

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract String mo10612a();

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract void mo10613a(long j);

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public abstract int mo10614b();

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public abstract int mo10615c();

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public abstract long mo10616d();

    /* access modifiers changed from: protected */
    /* renamed from: e */
    public abstract long mo10617e();

    /* access modifiers changed from: protected */
    /* renamed from: f */
    public abstract void mo10618f();

    /* renamed from: g */
    public boolean mo10620g() {
        int b = mo10614b();
        int c = mo10615c();
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - mo10617e() >= ((long) b)) {
            mo10613a(currentTimeMillis);
            return true;
        } else if (mo10616d() < ((long) c)) {
            return true;
        } else {
            return false;
        }
    }

    /* renamed from: h */
    public void mo10621h() {
        mo10618f();
    }

    public String toString() {
        return mo10612a() + ':' + this.f235e + '|' + this.f236f + '|' + MobSDK.getAppkey() + '|' + this.f237g + '|' + this.f238h + '|' + this.f239i + '|' + this.f240j + '|' + this.f241k;
    }
}
