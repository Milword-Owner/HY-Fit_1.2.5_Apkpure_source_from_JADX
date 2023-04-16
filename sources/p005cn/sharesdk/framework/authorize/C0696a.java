package p005cn.sharesdk.framework.authorize;

import android.content.Context;
import android.content.Intent;
import com.mob.MobSDK;
import com.mob.tools.FakeActivity;

/* renamed from: cn.sharesdk.framework.authorize.a */
/* compiled from: AbstractAuthorizeActivity */
public class C0696a extends FakeActivity {

    /* renamed from: a */
    protected AuthorizeHelper f181a;

    /* renamed from: a */
    public void mo10537a(AuthorizeHelper authorizeHelper) {
        this.f181a = authorizeHelper;
        super.show(MobSDK.getContext(), (Intent) null);
    }

    public void show(Context context, Intent intent) {
        throw new RuntimeException("This method is deprecated, use show(AuthorizeHelper, Intent) instead");
    }

    /* renamed from: a */
    public AuthorizeHelper mo10536a() {
        return this.f181a;
    }
}
