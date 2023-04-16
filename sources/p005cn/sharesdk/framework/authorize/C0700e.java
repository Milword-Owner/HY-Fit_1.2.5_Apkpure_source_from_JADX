package p005cn.sharesdk.framework.authorize;

import android.content.Intent;

/* renamed from: cn.sharesdk.framework.authorize.e */
/* compiled from: SSOProcessor */
public abstract class C0700e {

    /* renamed from: a */
    protected C0699d f187a;

    /* renamed from: b */
    protected int f188b;

    /* renamed from: c */
    protected SSOListener f189c;

    /* renamed from: a */
    public abstract void mo10203a();

    /* renamed from: a */
    public void mo10204a(int i, int i2, Intent intent) {
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo10547a(Intent intent) {
    }

    public C0700e(C0699d dVar) {
        this.f187a = dVar;
        this.f189c = dVar.mo10536a().getSSOListener();
    }

    /* renamed from: a */
    public void mo10546a(int i) {
        this.f188b = i;
    }
}
