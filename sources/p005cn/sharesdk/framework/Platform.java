package p005cn.sharesdk.framework;

import android.graphics.Bitmap;
import java.util.HashMap;
import p005cn.sharesdk.framework.p007b.p009b.C0720f;

/* renamed from: cn.sharesdk.framework.Platform */
public abstract class Platform {
    public static final int ACTION_AUTHORIZING = 1;
    protected static final int ACTION_CUSTOMER = 655360;
    public static final int ACTION_FOLLOWING_USER = 6;
    protected static final int ACTION_GETTING_BILATERAL_LIST = 10;
    protected static final int ACTION_GETTING_FOLLOWER_LIST = 11;
    public static final int ACTION_GETTING_FRIEND_LIST = 2;
    public static final int ACTION_SENDING_DIRECT_MESSAGE = 5;
    public static final int ACTION_SHARE = 9;
    public static final int ACTION_TIMELINE = 7;
    public static final int ACTION_USER_INFOR = 8;
    public static final int CUSTOMER_ACTION_MASK = 65535;
    public static final int GGP_REFUSE = 21;
    public static final int INSTAGRAM_FRIEND = 13;
    public static final int KAKAO_COMMERCE_TEMPLATE = 18;
    public static final int KAKAO_CUSTOM_TEMPLATE = 20;
    public static final int KAKAO_FEED_TEMPLATE = 16;
    public static final int KAKAO_TEXT_TEMPLATE = 19;
    public static final int KAKAO_URL_TEMPLATE = 17;
    public static final int OPEN_WXMINIPROGRAM = 12;
    public static final int QQ_MINI_PROGRAM = 15;
    public static final int SHARE_APPS = 7;
    public static final int SHARE_EMOJI = 9;
    public static final int SHARE_FILE = 8;
    public static final int SHARE_IMAGE = 2;
    public static final int SHARE_LINKCARD = 14;
    public static final int SHARE_MUSIC = 5;
    public static final int SHARE_TEXT = 1;
    public static final int SHARE_VIDEO = 6;
    public static final int SHARE_WEBPAGE = 4;
    public static final int SHARE_WXMINIPROGRAM = 11;
    public static final int SHARE_ZHIFUBAO = 10;

    /* renamed from: a */
    private C0735f f148a = new C0735f(this);
    /* access modifiers changed from: protected */

    /* renamed from: db */
    public final PlatformDb f149db = this.f148a.mo10681g();
    /* access modifiers changed from: protected */
    public PlatformActionListener listener = this.f148a.mo10683i();

    /* access modifiers changed from: protected */
    public abstract boolean checkAuthorize(int i, Object obj);

    /* access modifiers changed from: protected */
    public abstract void doAuthorize(String[] strArr);

    /* access modifiers changed from: protected */
    public abstract void doCustomerProtocol(String str, String str2, int i, HashMap<String, Object> hashMap, HashMap<String, String> hashMap2);

    /* access modifiers changed from: protected */
    public abstract void doShare(ShareParams shareParams);

    /* access modifiers changed from: protected */
    public abstract HashMap<String, Object> filterFriendshipInfo(int i, HashMap<String, Object> hashMap);

    /* access modifiers changed from: protected */
    public abstract C0720f.C0721a filterShareContent(ShareParams shareParams, HashMap<String, Object> hashMap);

    /* access modifiers changed from: protected */
    public abstract void follow(String str);

    /* access modifiers changed from: protected */
    public abstract HashMap<String, Object> getBilaterals(int i, int i2, String str);

    /* access modifiers changed from: protected */
    public abstract HashMap<String, Object> getFollowers(int i, int i2, String str);

    /* access modifiers changed from: protected */
    public abstract HashMap<String, Object> getFollowings(int i, int i2, String str);

    /* access modifiers changed from: protected */
    public abstract void getFriendList(int i, int i2, String str);

    public abstract String getName();

    /* access modifiers changed from: protected */
    public abstract int getPlatformId();

    public abstract int getVersion();

    public abstract boolean hasShareCallback();

    /* access modifiers changed from: protected */
    public abstract void initDevInfo(String str);

    public boolean isClientValid() {
        return false;
    }

    /* access modifiers changed from: protected */
    public abstract void setNetworkDevinfo();

    /* access modifiers changed from: protected */
    public abstract void timeline(int i, int i2, String str);

    /* access modifiers changed from: protected */
    public abstract void userInfor(String str);

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo10414a() {
        this.f148a.mo10668a(false);
        this.f148a.mo10665a(getName());
    }

    /* access modifiers changed from: protected */
    public void copyDevinfo(String str, String str2) {
        ShareSDK.m106a(str, str2);
    }

    /* access modifiers changed from: protected */
    public void copyNetworkDevinfo(int i, int i2) {
        ShareSDK.m105a(i, i2);
    }

    public String getDevinfo(String str) {
        return getDevinfo(getName(), str);
    }

    public String getDevinfo(String str, String str2) {
        return ShareSDK.getDevinfo(str, str2);
    }

    /* access modifiers changed from: protected */
    public String getNetworkDevinfo(String str, String str2) {
        return getNetworkDevinfo(getPlatformId(), str, str2);
    }

    /* access modifiers changed from: protected */
    public String getNetworkDevinfo(int i, String str, String str2) {
        return this.f148a.mo10658a(i, str, str2);
    }

    public int getId() {
        return this.f148a.mo10657a();
    }

    public int getSortId() {
        return this.f148a.mo10670b();
    }

    public void setPlatformActionListener(PlatformActionListener platformActionListener) {
        this.f148a.mo10664a(platformActionListener);
    }

    public PlatformActionListener getPlatformActionListener() {
        return this.f148a.mo10674c();
    }

    public boolean isAuthValid() {
        return this.f148a.mo10678d();
    }

    public void SSOSetting(boolean z) {
        this.f148a.mo10668a(z);
    }

    public boolean isSSODisable() {
        return this.f148a.mo10679e();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public boolean mo10418b() {
        return this.f148a.mo10680f();
    }

    public void authorize() {
        authorize((String[]) null);
    }

    public void authorize(String[] strArr) {
        this.f148a.mo10669a(strArr);
    }

    public void subscribeAuth(ShareParams shareParams) {
        this.f148a.mo10663a(shareParams);
    }

    /* access modifiers changed from: protected */
    public void innerAuthorize(int i, Object obj) {
        this.f148a.mo10662a(i, obj);
    }

    public void share(ShareParams shareParams) {
        this.f148a.mo10672b(shareParams);
    }

    public void followFriend(String str) {
        this.f148a.mo10673b(str);
    }

    public void getTimeLine(String str, int i, int i2) {
        this.f148a.mo10666a(str, i, i2);
    }

    public void showUser(String str) {
        this.f148a.mo10676c(str);
    }

    public void listFriend(int i, int i2, String str) {
        this.f148a.mo10661a(i, i2, str);
    }

    public void customerProtocol(String str, String str2, short s, HashMap<String, Object> hashMap, HashMap<String, String> hashMap2) {
        this.f148a.mo10667a(str, str2, s, hashMap, hashMap2);
    }

    /* access modifiers changed from: protected */
    public void afterRegister(int i, Object obj) {
        this.f148a.mo10671b(i, obj);
    }

    public PlatformDb getDb() {
        return this.f149db;
    }

    public void removeAccount(boolean z) {
        this.f148a.mo10682h();
        ShareSDK.removeCookieOnAuthorize(z);
    }

    public String getShortLintk(String str, boolean z) {
        return this.f148a.mo10660a(str, z);
    }

    /* access modifiers changed from: protected */
    public String uploadImageToFileServer(String str) {
        return this.f148a.mo10677d(str);
    }

    /* access modifiers changed from: protected */
    public String uploadImageToFileServer(Bitmap bitmap) {
        return this.f148a.mo10659a(bitmap);
    }

    /* renamed from: cn.sharesdk.framework.Platform$ShareParams */
    public static class ShareParams extends InnerShareParams {
        public ShareParams() {
        }

        public ShareParams(HashMap<String, Object> hashMap) {
            super(hashMap);
        }

        public ShareParams(String str) {
            super(str);
        }
    }
}
