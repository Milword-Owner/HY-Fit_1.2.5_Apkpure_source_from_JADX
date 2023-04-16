package p005cn.sharesdk.framework;

import p005cn.sharesdk.framework.authorize.AuthorizeHelper;
import p005cn.sharesdk.framework.authorize.AuthorizeListener;
import p005cn.sharesdk.framework.authorize.C0699d;
import p005cn.sharesdk.framework.authorize.C0700e;
import p005cn.sharesdk.framework.authorize.C0702g;
import p005cn.sharesdk.framework.authorize.SSOListener;

/* renamed from: cn.sharesdk.framework.e */
/* compiled from: PlatformHelper */
public abstract class C0734e implements AuthorizeHelper {

    /* renamed from: a */
    protected Platform f305a;

    /* renamed from: b */
    private AuthorizeListener f306b;

    /* renamed from: c */
    private SSOListener f307c;

    public C0700e getSSOProcessor(C0699d dVar) {
        return null;
    }

    public C0734e(Platform platform) {
        this.f305a = platform;
    }

    public Platform getPlatform() {
        return this.f305a;
    }

    /* renamed from: c */
    public int mo10656c() {
        return this.f305a.getPlatformId();
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public void mo10655b(AuthorizeListener authorizeListener) {
        this.f306b = authorizeListener;
        C0702g gVar = new C0702g();
        gVar.mo10552a(this.f306b);
        gVar.mo10537a(this);
    }

    public AuthorizeListener getAuthorizeListener() {
        return this.f306b;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo10654a(SSOListener sSOListener) {
        this.f307c = sSOListener;
        C0699d dVar = new C0699d();
        dVar.mo10544a(sSOListener);
        dVar.mo10537a(this);
    }

    public SSOListener getSSOListener() {
        return this.f307c;
    }
}
