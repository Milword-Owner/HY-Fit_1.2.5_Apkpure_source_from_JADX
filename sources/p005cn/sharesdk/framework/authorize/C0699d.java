package p005cn.sharesdk.framework.authorize;

import android.content.Intent;

/* renamed from: cn.sharesdk.framework.authorize.d */
/* compiled from: SSOAuthorizeActivity */
public class C0699d extends C0696a {

    /* renamed from: b */
    protected SSOListener f185b;

    /* renamed from: c */
    private C0700e f186c;

    /* renamed from: a */
    public void mo10544a(SSOListener sSOListener) {
        this.f185b = sSOListener;
    }

    public void onCreate() {
        this.f186c = this.f181a.getSSOProcessor(this);
        C0700e eVar = this.f186c;
        if (eVar == null) {
            finish();
            AuthorizeListener authorizeListener = this.f181a.getAuthorizeListener();
            if (authorizeListener != null) {
                authorizeListener.onError(new Throwable("Failed to start SSO for " + this.f181a.getPlatform().getName()));
                return;
            }
            return;
        }
        eVar.mo10546a(32973);
        this.f186c.mo10203a();
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        this.f186c.mo10204a(i, i2, intent);
    }

    public void onNewIntent(Intent intent) {
        this.f186c.mo10547a(intent);
    }
}
