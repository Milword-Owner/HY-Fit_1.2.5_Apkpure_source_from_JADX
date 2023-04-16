package p005cn.sharesdk.framework.p007b.p008a;

import android.text.TextUtils;
import com.facebook.internal.ServerProtocol;
import com.mob.MobSDK;
import com.mob.tools.utils.SharePrefrenceHelper;

/* renamed from: cn.sharesdk.framework.b.a.e */
/* compiled from: SharePrefrenceUtil */
public class C0713e {

    /* renamed from: b */
    private static C0713e f220b;

    /* renamed from: a */
    private SharePrefrenceHelper f221a = new SharePrefrenceHelper(MobSDK.getContext());

    private C0713e() {
        this.f221a.open("share_sdk", 1);
    }

    /* renamed from: a */
    public static C0713e m196a() {
        if (f220b == null) {
            f220b = new C0713e();
        }
        return f220b;
    }

    /* renamed from: b */
    public long mo10587b() {
        return this.f221a.getLong("service_time");
    }

    /* renamed from: c */
    public boolean mo10593c() {
        String string = this.f221a.getString("upload_device_info");
        if (TextUtils.isEmpty(string)) {
            return true;
        }
        return Boolean.parseBoolean(string);
    }

    /* renamed from: d */
    public boolean mo10595d() {
        String string = this.f221a.getString("upload_user_info");
        if (TextUtils.isEmpty(string)) {
            return true;
        }
        return Boolean.parseBoolean(string);
    }

    /* renamed from: e */
    public boolean mo10597e() {
        String string = this.f221a.getString("trans_short_link");
        if (TextUtils.isEmpty(string)) {
            return false;
        }
        return Boolean.parseBoolean(string);
    }

    /* renamed from: f */
    public int mo10598f() {
        String string = this.f221a.getString("upload_share_content");
        if (ServerProtocol.DIALOG_RETURN_SCOPES_TRUE.equals(string)) {
            return 1;
        }
        return "false".equals(string) ? -1 : 0;
    }

    /* renamed from: g */
    public boolean mo10601g() {
        String string = this.f221a.getString("open_login_plus");
        if (TextUtils.isEmpty(string)) {
            return false;
        }
        return Boolean.parseBoolean(string);
    }

    /* renamed from: h */
    public boolean mo10602h() {
        String string = this.f221a.getString("open_sina_link_card");
        if (TextUtils.isEmpty(string)) {
            return false;
        }
        return Boolean.parseBoolean(string);
    }

    /* renamed from: a */
    public void mo10582a(String str) {
        this.f221a.putString("trans_short_link", str);
    }

    /* renamed from: b */
    public void mo10589b(String str) {
        this.f221a.putString("upload_device_info", str);
    }

    /* renamed from: c */
    public void mo10591c(String str) {
        this.f221a.putString("upload_user_info", str);
    }

    /* renamed from: d */
    public void mo10594d(String str) {
        this.f221a.putString("upload_share_content", str);
    }

    /* renamed from: e */
    public void mo10596e(String str) {
        this.f221a.putString("open_login_plus", str);
    }

    /* renamed from: f */
    public void mo10599f(String str) {
        this.f221a.putString("open_sina_link_card", str);
    }

    /* renamed from: g */
    public void mo10600g(String str) {
        SharePrefrenceHelper sharePrefrenceHelper = this.f221a;
        sharePrefrenceHelper.putString("buffered_snsconf_" + MobSDK.getAppkey(), str);
    }

    /* renamed from: i */
    public String mo10605i() {
        SharePrefrenceHelper sharePrefrenceHelper = this.f221a;
        return sharePrefrenceHelper.getString("buffered_snsconf_" + MobSDK.getAppkey());
    }

    /* renamed from: a */
    public void mo10586a(boolean z) {
        this.f221a.putBoolean("gpp_ver_sent", Boolean.valueOf(z));
    }

    /* renamed from: a */
    public void mo10581a(long j) {
        this.f221a.putLong("device_time", Long.valueOf(j));
    }

    /* renamed from: j */
    public Long mo10607j() {
        return Long.valueOf(this.f221a.getLong("device_time"));
    }

    /* renamed from: b */
    public void mo10590b(boolean z) {
        this.f221a.putBoolean("connect_server", Boolean.valueOf(z));
    }

    /* renamed from: k */
    public boolean mo10609k() {
        return this.f221a.getBoolean("connect_server");
    }

    /* renamed from: b */
    public void mo10588b(long j) {
        this.f221a.putLong("connect_server_time", Long.valueOf(j));
    }

    /* renamed from: l */
    public Long mo10610l() {
        return Long.valueOf(this.f221a.getLong("connect_server_time"));
    }

    /* renamed from: c */
    public void mo10592c(boolean z) {
        this.f221a.putBoolean("sns_info_buffered", Boolean.valueOf(z));
    }

    /* renamed from: m */
    public boolean mo10611m() {
        return this.f221a.getBoolean("sns_info_buffered");
    }

    /* renamed from: h */
    public boolean mo10603h(String str) {
        return this.f221a.getBoolean(str);
    }

    /* renamed from: a */
    public void mo10584a(String str, Long l) {
        this.f221a.putLong(str, l);
    }

    /* renamed from: i */
    public long mo10604i(String str) {
        return this.f221a.getLong(str);
    }

    /* renamed from: a */
    public void mo10583a(String str, int i) {
        this.f221a.putInt(str, Integer.valueOf(i));
    }

    /* renamed from: j */
    public int mo10606j(String str) {
        return this.f221a.getInt(str);
    }

    /* renamed from: a */
    public void mo10585a(String str, Object obj) {
        this.f221a.put(str, obj);
    }

    /* renamed from: k */
    public Object mo10608k(String str) {
        return this.f221a.get(str);
    }
}
