package p005cn.sharesdk.framework;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.text.TextUtils;
import com.baidu.mobstat.Config;
import com.facebook.internal.ServerProtocol;
import com.mob.MobSDK;
import com.mob.commons.ForbThrowable;
import com.mob.tools.log.NLog;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.ResHelper;
import java.lang.reflect.Field;
import java.util.HashMap;
import p005cn.sharesdk.framework.AgreementDialog;
import p005cn.sharesdk.framework.Platform;
import p005cn.sharesdk.framework.loopshare.MoblinkActionListener;
import p005cn.sharesdk.framework.p007b.p008a.C0713e;
import p005cn.sharesdk.framework.utils.SSDKLog;
import p005cn.sharesdk.onekeyshare.OnekeyShare;

/* renamed from: cn.sharesdk.framework.f */
/* compiled from: PlatformImpl */
public class C0735f {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public Platform f308a;

    /* renamed from: b */
    private PlatformDb f309b;
    /* access modifiers changed from: private */

    /* renamed from: c */
    public C0730d f310c;

    /* renamed from: d */
    private int f311d;

    /* renamed from: e */
    private int f312e;

    /* renamed from: f */
    private boolean f313f;

    /* renamed from: g */
    private boolean f314g = true;

    /* renamed from: h */
    private boolean f315h;

    /* renamed from: b */
    private String m346b(int i) {
        if (i == 1) {
            return "ACTION_AUTHORIZING";
        }
        if (i == 2) {
            return "ACTION_GETTING_FRIEND_LIST";
        }
        switch (i) {
            case 5:
                return "ACTION_SENDING_DIRECT_MESSAGE";
            case 6:
                return "ACTION_FOLLOWING_USER";
            case 7:
                return "ACTION_TIMELINE";
            case 8:
                return "ACTION_USER_INFOR";
            case 9:
                return "ACTION_SHARE";
            case 10:
                return "ACTION_GETTING_BILATERAL_LIST";
            case 11:
                return "ACTION_GETTING_FOLLOWER_LIST";
            default:
                return "ACTION_CUSTOMER";
        }
    }

    public C0735f(Platform platform) {
        this.f308a = platform;
        String name = platform.getName();
        this.f309b = new PlatformDb(name, platform.getVersion());
        mo10665a(name);
        this.f310c = new C0730d();
        C0728c.m322a();
    }

    /* renamed from: a */
    public void mo10665a(String str) {
        try {
            this.f311d = ResHelper.parseInt(String.valueOf(ShareSDK.getDevinfo(str, "Id")).trim());
        } catch (Throwable unused) {
            if (!(this.f308a instanceof CustomPlatform)) {
                NLog b = SSDKLog.m645b();
                b.mo29768d(this.f308a.getName() + " failed to parse Id, this will cause method getId() always returens 0", new Object[0]);
            }
        }
        try {
            this.f312e = ResHelper.parseInt(String.valueOf(ShareSDK.getDevinfo(str, "SortId")).trim());
        } catch (Throwable unused2) {
            if (!(this.f308a instanceof CustomPlatform)) {
                NLog b2 = SSDKLog.m645b();
                b2.mo29768d(this.f308a.getName() + " failed to parse SortId, this won't cause any problem, don't worry", new Object[0]);
            }
        }
        String devinfo = ShareSDK.getDevinfo(str, "Enable");
        if (devinfo == null) {
            this.f315h = true;
            if (!(this.f308a instanceof CustomPlatform)) {
                NLog b3 = SSDKLog.m645b();
                b3.mo29768d(this.f308a.getName() + " failed to parse Enable, this will cause platform always be enable", new Object[0]);
            }
        } else {
            this.f315h = ServerProtocol.DIALOG_RETURN_SCOPES_TRUE.equals(devinfo.trim());
        }
        this.f308a.initDevInfo(str);
    }

    /* renamed from: a */
    public int mo10657a() {
        return this.f311d;
    }

    /* renamed from: b */
    public int mo10670b() {
        return this.f312e;
    }

    /* renamed from: a */
    public void mo10664a(PlatformActionListener platformActionListener) {
        this.f310c.mo10652a(platformActionListener);
    }

    /* renamed from: c */
    public PlatformActionListener mo10674c() {
        return this.f310c.mo10649a();
    }

    /* renamed from: d */
    public boolean mo10678d() {
        return this.f309b.isValid();
    }

    /* renamed from: a */
    public void mo10668a(boolean z) {
        this.f313f = z;
    }

    /* renamed from: e */
    public boolean mo10679e() {
        return this.f313f;
    }

    /* renamed from: f */
    public boolean mo10680f() {
        return this.f315h;
    }

    /* renamed from: a */
    private String m341a(int i) {
        return "ShareSDK_" + this.f308a.getName() + Config.replace + m346b(i);
    }

    /* access modifiers changed from: private */
    /* renamed from: j */
    public boolean m351j() {
        if (ShareSDK.m107a()) {
            String a = mo10658a(this.f308a.getPlatformId(), "covert_url", (String) null);
            if (a != null) {
                a.trim();
            }
            this.f314g = !"false".equals(a);
            this.f308a.setNetworkDevinfo();
            return true;
        }
        try {
            if (!ShareSDK.m108b()) {
                return false;
            }
            String a2 = mo10658a(this.f308a.getPlatformId(), "covert_url", (String) null);
            if (a2 != null) {
                a2.trim();
            }
            this.f314g = !"false".equals(a2);
            this.f308a.setNetworkDevinfo();
            return true;
        } catch (Throwable th) {
            SSDKLog.m645b().mo29787w(th);
            return false;
        }
    }

    /* renamed from: a */
    public String mo10658a(int i, String str, String str2) {
        String a = ShareSDK.m101a(i, str);
        if (!TextUtils.isEmpty(a) && !"null".equals(a)) {
            return a;
        }
        Platform platform = this.f308a;
        return platform.getDevinfo(platform.getName(), str2);
    }

    /* renamed from: a */
    public void mo10662a(int i, Object obj) {
        this.f310c.mo10650a(this.f308a, i, obj);
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public void mo10671b(int i, Object obj) {
        Object obj2;
        String str = null;
        if (i == 1) {
            C0730d dVar = this.f310c;
            if (dVar != null) {
                dVar.onComplete(this.f308a, 1, (HashMap<String, Object>) null);
            }
        } else if (i != 2) {
            switch (i) {
                case 6:
                    this.f308a.follow((String) obj);
                    return;
                case 7:
                    Object[] objArr = (Object[]) obj;
                    this.f308a.timeline(((Integer) objArr[0]).intValue(), ((Integer) objArr[1]).intValue(), (String) objArr[2]);
                    return;
                case 8:
                    Platform platform = this.f308a;
                    if (obj != null) {
                        str = (String) obj;
                    }
                    platform.userInfor(str);
                    return;
                case 9:
                    final Platform.ShareParams shareParams = (Platform.ShareParams) obj;
                    HashMap<String, Object> map = shareParams.toMap();
                    for (Field field : shareParams.getClass().getFields()) {
                        if (map.get(field.getName()) == null) {
                            field.setAccessible(true);
                            try {
                                obj2 = field.get(shareParams);
                            } catch (Throwable th) {
                                SSDKLog.m645b().mo29787w(th);
                                obj2 = null;
                            }
                            if (obj2 != null) {
                                map.put(field.getName(), obj2);
                            }
                        }
                    }
                    C0730d dVar2 = this.f310c;
                    if (dVar2 instanceof C0730d) {
                        dVar2.mo10651a(this.f308a, shareParams);
                    }
                    try {
                        if (shareParams.getLoopshareCustomParams().size() <= 0 || shareParams.getLoopshareCustomParams() == null) {
                            this.f308a.doShare(shareParams);
                            return;
                        } else if (this.f308a.getName().equals("QQ")) {
                            if (!TextUtils.isEmpty(shareParams.getTitleUrl())) {
                                ShareSDK.mobLinkGetMobID(shareParams.getLoopshareCustomParams(), new MoblinkActionListener() {
                                    public void onResult(Object obj) {
                                        if (!TextUtils.isEmpty(Uri.parse(shareParams.getTitleUrl()).getEncodedQuery())) {
                                            Platform.ShareParams shareParams = shareParams;
                                            shareParams.setTitleUrl(shareParams.getTitleUrl() + "&mobid=" + obj);
                                        } else {
                                            Platform.ShareParams shareParams2 = shareParams;
                                            shareParams2.setTitleUrl(shareParams.getTitleUrl() + "?mobid=" + obj);
                                        }
                                        new Thread() {
                                            public void run() {
                                                super.run();
                                                C0735f.this.f308a.doShare(shareParams);
                                            }
                                        }.start();
                                    }

                                    public void onError(Throwable th) {
                                        if (C0735f.this.f310c != null) {
                                            C0735f.this.f310c.onError(C0735f.this.f308a, 9, th);
                                        }
                                    }
                                });
                                return;
                            } else if (this.f310c != null) {
                                this.f310c.onError(this.f308a, 9, new Throwable("TitleUrl cannot be empty if setLoopshareCustomParams is used in QQ"));
                                return;
                            } else {
                                return;
                            }
                        } else if (!TextUtils.isEmpty(shareParams.getUrl())) {
                            if (this.f310c != null) {
                                ShareSDK.mobLinkGetMobID(shareParams.getLoopshareCustomParams(), new MoblinkActionListener() {
                                    public void onResult(Object obj) {
                                        if (!TextUtils.isEmpty(Uri.parse(shareParams.getUrl()).getEncodedQuery())) {
                                            Platform.ShareParams shareParams = shareParams;
                                            shareParams.setUrl(shareParams.getUrl() + "&mobid=" + obj);
                                        } else {
                                            Platform.ShareParams shareParams2 = shareParams;
                                            shareParams2.setUrl(shareParams.getUrl() + "?mobid=" + obj);
                                        }
                                        new Thread() {
                                            public void run() {
                                                super.run();
                                                C0735f.this.f308a.doShare(shareParams);
                                            }
                                        }.start();
                                    }

                                    public void onError(Throwable th) {
                                        if (C0735f.this.f310c != null) {
                                            C0735f.this.f310c.onError(C0735f.this.f308a, 9, th);
                                        }
                                    }
                                });
                                return;
                            }
                            return;
                        } else if (this.f310c != null) {
                            this.f310c.onError(this.f308a, 9, new Throwable("SetUrl cannot be empty if setLoopshareCustomParams is used"));
                            return;
                        } else {
                            return;
                        }
                    } catch (Throwable th2) {
                        SSDKLog.m645b().mo29768d("PlatformImpl platform.doshare() " + th2, new Object[0]);
                        return;
                    }
                default:
                    Object[] objArr2 = (Object[]) obj;
                    this.f308a.doCustomerProtocol(String.valueOf(objArr2[0]), String.valueOf(objArr2[1]), i, (HashMap) objArr2[2], (HashMap) objArr2[3]);
                    return;
            }
        } else {
            Object[] objArr3 = (Object[]) obj;
            this.f308a.getFriendList(((Integer) objArr3[0]).intValue(), ((Integer) objArr3[1]).intValue(), (String) objArr3[2]);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m342a(C0730d dVar, int i) {
        if (dVar != null) {
            dVar.onError(this.f308a, i, new ForbThrowable());
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public void m347b(C0730d dVar, int i) {
        if (dVar != null) {
            dVar.onError(this.f308a, i, new Throwable("'appkey' is illegal"));
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public void mo10675c(final int i, final Object obj) {
        new Thread(m341a(i)) {
            public void run() {
                try {
                    if (!MobSDK.isForb() && C0690a.m118a()) {
                        int isAuth = MobSDK.isAuth();
                        if (isAuth == 1 || isAuth == 2) {
                            boolean unused = C0735f.this.m351j();
                            SSDKLog.m645b().mo29786w("用户使用的是无弹框隐私版本 newThreadJob 001");
                        }
                    }
                } catch (Throwable th) {
                    NLog b = SSDKLog.m645b();
                    b.mo29786w("newThreadJob  " + th);
                }
            }
        }.start();
        new Thread() {
            /* JADX WARNING: Can't wrap try/catch for region: R(3:18|19|38) */
            /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
                p005cn.sharesdk.framework.ProvicyCanContinue.m98a().mo10464a(new p005cn.sharesdk.framework.C0735f.C07414.C07421(r4));
             */
            /* JADX WARNING: Code restructure failed: missing block: B:38:?, code lost:
                return;
             */
            /* JADX WARNING: Missing exception handler attribute for start block: B:18:0x0077 */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                    r4 = this;
                    boolean r0 = com.mob.MobSDK.isForb()     // Catch:{ Throwable -> 0x00cb }
                    if (r0 == 0) goto L_0x0015
                    cn.sharesdk.framework.f r0 = p005cn.sharesdk.framework.C0735f.this     // Catch:{ Throwable -> 0x00cb }
                    cn.sharesdk.framework.f r1 = p005cn.sharesdk.framework.C0735f.this     // Catch:{ Throwable -> 0x00cb }
                    cn.sharesdk.framework.d r1 = r1.f310c     // Catch:{ Throwable -> 0x00cb }
                    int r2 = r3     // Catch:{ Throwable -> 0x00cb }
                    r0.m342a((p005cn.sharesdk.framework.C0730d) r1, (int) r2)     // Catch:{ Throwable -> 0x00cb }
                    goto L_0x00e7
                L_0x0015:
                    int r0 = com.mob.MobSDK.isAuth()     // Catch:{ Throwable -> 0x0077 }
                    r1 = 1
                    if (r0 == r1) goto L_0x003f
                    r1 = 2
                    if (r0 == r1) goto L_0x003f
                    cn.sharesdk.framework.f r0 = p005cn.sharesdk.framework.C0735f.this     // Catch:{ Throwable -> 0x0077 }
                    cn.sharesdk.framework.d r0 = r0.f310c     // Catch:{ Throwable -> 0x0077 }
                    if (r0 == 0) goto L_0x00e7
                    cn.sharesdk.framework.f r0 = p005cn.sharesdk.framework.C0735f.this     // Catch:{ Throwable -> 0x0077 }
                    cn.sharesdk.framework.d r0 = r0.f310c     // Catch:{ Throwable -> 0x0077 }
                    cn.sharesdk.framework.f r1 = p005cn.sharesdk.framework.C0735f.this     // Catch:{ Throwable -> 0x0077 }
                    cn.sharesdk.framework.Platform r1 = r1.f308a     // Catch:{ Throwable -> 0x0077 }
                    int r2 = r3     // Catch:{ Throwable -> 0x0077 }
                    com.mob.commons.dialog.PolicyThrowable r3 = new com.mob.commons.dialog.PolicyThrowable     // Catch:{ Throwable -> 0x0077 }
                    r3.<init>()     // Catch:{ Throwable -> 0x0077 }
                    r0.onError(r1, r2, r3)     // Catch:{ Throwable -> 0x0077 }
                    goto L_0x00e7
                L_0x003f:
                    boolean r0 = p005cn.sharesdk.framework.C0690a.m118a()     // Catch:{ Throwable -> 0x0077 }
                    if (r0 != 0) goto L_0x0054
                    cn.sharesdk.framework.f r0 = p005cn.sharesdk.framework.C0735f.this     // Catch:{ Throwable -> 0x0077 }
                    cn.sharesdk.framework.f r1 = p005cn.sharesdk.framework.C0735f.this     // Catch:{ Throwable -> 0x0077 }
                    cn.sharesdk.framework.d r1 = r1.f310c     // Catch:{ Throwable -> 0x0077 }
                    int r2 = r3     // Catch:{ Throwable -> 0x0077 }
                    r0.m347b((p005cn.sharesdk.framework.C0730d) r1, (int) r2)     // Catch:{ Throwable -> 0x0077 }
                    goto L_0x00e7
                L_0x0054:
                    cn.sharesdk.framework.f r0 = p005cn.sharesdk.framework.C0735f.this     // Catch:{ Throwable -> 0x0077 }
                    cn.sharesdk.framework.Platform r0 = r0.f308a     // Catch:{ Throwable -> 0x0077 }
                    int r1 = r3     // Catch:{ Throwable -> 0x0077 }
                    java.lang.Object r2 = r4     // Catch:{ Throwable -> 0x0077 }
                    boolean r0 = r0.checkAuthorize(r1, r2)     // Catch:{ Throwable -> 0x0077 }
                    if (r0 == 0) goto L_0x00e7
                    cn.sharesdk.framework.f r0 = p005cn.sharesdk.framework.C0735f.this     // Catch:{ Throwable -> 0x0077 }
                    int r1 = r3     // Catch:{ Throwable -> 0x0077 }
                    java.lang.Object r2 = r4     // Catch:{ Throwable -> 0x0077 }
                    r0.mo10671b((int) r1, (java.lang.Object) r2)     // Catch:{ Throwable -> 0x0077 }
                    com.mob.tools.log.NLog r0 = p005cn.sharesdk.framework.utils.SSDKLog.m645b()     // Catch:{ Throwable -> 0x0077 }
                    java.lang.String r1 = "用户使用的是无弹框隐私版本 newThreadJob 002"
                    r0.mo29786w((java.lang.String) r1)     // Catch:{ Throwable -> 0x0077 }
                    goto L_0x00e7
                L_0x0077:
                    cn.sharesdk.framework.ProvicyCanContinue r0 = p005cn.sharesdk.framework.ProvicyCanContinue.m98a()     // Catch:{ Throwable -> 0x0084 }
                    cn.sharesdk.framework.f$4$1 r1 = new cn.sharesdk.framework.f$4$1     // Catch:{ Throwable -> 0x0084 }
                    r1.<init>()     // Catch:{ Throwable -> 0x0084 }
                    r0.mo10464a(r1)     // Catch:{ Throwable -> 0x0084 }
                    goto L_0x00e7
                L_0x0084:
                    r0 = move-exception
                    boolean r1 = p005cn.sharesdk.framework.C0690a.m118a()     // Catch:{ Throwable -> 0x00cb }
                    if (r1 != 0) goto L_0x0099
                    cn.sharesdk.framework.f r1 = p005cn.sharesdk.framework.C0735f.this     // Catch:{ Throwable -> 0x00cb }
                    cn.sharesdk.framework.f r2 = p005cn.sharesdk.framework.C0735f.this     // Catch:{ Throwable -> 0x00cb }
                    cn.sharesdk.framework.d r2 = r2.f310c     // Catch:{ Throwable -> 0x00cb }
                    int r3 = r3     // Catch:{ Throwable -> 0x00cb }
                    r1.m347b((p005cn.sharesdk.framework.C0730d) r2, (int) r3)     // Catch:{ Throwable -> 0x00cb }
                    goto L_0x00b2
                L_0x0099:
                    cn.sharesdk.framework.f r1 = p005cn.sharesdk.framework.C0735f.this     // Catch:{ Throwable -> 0x00cb }
                    cn.sharesdk.framework.Platform r1 = r1.f308a     // Catch:{ Throwable -> 0x00cb }
                    int r2 = r3     // Catch:{ Throwable -> 0x00cb }
                    java.lang.Object r3 = r4     // Catch:{ Throwable -> 0x00cb }
                    boolean r1 = r1.checkAuthorize(r2, r3)     // Catch:{ Throwable -> 0x00cb }
                    if (r1 == 0) goto L_0x00b2
                    cn.sharesdk.framework.f r1 = p005cn.sharesdk.framework.C0735f.this     // Catch:{ Throwable -> 0x00cb }
                    int r2 = r3     // Catch:{ Throwable -> 0x00cb }
                    java.lang.Object r3 = r4     // Catch:{ Throwable -> 0x00cb }
                    r1.mo10671b((int) r2, (java.lang.Object) r3)     // Catch:{ Throwable -> 0x00cb }
                L_0x00b2:
                    com.mob.tools.log.NLog r1 = p005cn.sharesdk.framework.utils.SSDKLog.m645b()     // Catch:{ Throwable -> 0x00cb }
                    java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00cb }
                    r2.<init>()     // Catch:{ Throwable -> 0x00cb }
                    java.lang.String r3 = "用户使用的是非隐私版本 newThreadJob 002 "
                    r2.append(r3)     // Catch:{ Throwable -> 0x00cb }
                    r2.append(r0)     // Catch:{ Throwable -> 0x00cb }
                    java.lang.String r0 = r2.toString()     // Catch:{ Throwable -> 0x00cb }
                    r1.mo29786w((java.lang.String) r0)     // Catch:{ Throwable -> 0x00cb }
                    goto L_0x00e7
                L_0x00cb:
                    r0 = move-exception
                    com.mob.tools.log.NLog r1 = p005cn.sharesdk.framework.utils.SSDKLog.m645b()
                    java.lang.StringBuilder r2 = new java.lang.StringBuilder
                    r2.<init>()
                    java.lang.String r3 = "new Thread(getThreadName(action)) "
                    r2.append(r3)
                    r2.append(r0)
                    java.lang.String r0 = r2.toString()
                    r2 = 0
                    java.lang.Object[] r2 = new java.lang.Object[r2]
                    r1.mo29768d(r0, r2)
                L_0x00e7:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: p005cn.sharesdk.framework.C0735f.C07414.run():void");
            }
        }.start();
    }

    /* renamed from: a */
    public void mo10663a(final Platform.ShareParams shareParams) {
        if (shareParams == null) {
            C0730d dVar = this.f310c;
            if (dVar != null) {
                dVar.onError(this.f308a, 9, new NullPointerException());
                return;
            }
            return;
        }
        new Thread(m341a(1)) {
            public void run() {
                try {
                    C0735f.this.f308a.subscribeAuth(shareParams);
                    SSDKLog.m645b().mo29768d(OnekeyShare.SHARESDK_TAG, "subscribeAuth start on PlatformImpl");
                } catch (Throwable th) {
                    NLog b = SSDKLog.m645b();
                    b.mo29768d(OnekeyShare.SHARESDK_TAG, " subscribeAuth catch " + th);
                }
            }
        }.start();
    }

    /* renamed from: a */
    public void mo10669a(final String[] strArr) {
        if (!MobSDK.isGppVer() || C0713e.m196a().mo10603h("gpp_ver_sent")) {
            m349b(strArr);
            return;
        }
        AgreementDialog agreementDialog = new AgreementDialog();
        agreementDialog.mo10242a((AgreementDialog.OnDialogDismiss) new AgreementDialog.OnDialogDismiss() {
            public void consent() {
                C0735f.this.m349b(strArr);
            }

            public void refuse() {
                if (C0735f.this.f310c != null) {
                    C0735f.this.f310c.onError(C0735f.this.f308a, 21, new NullPointerException());
                }
            }
        });
        agreementDialog.show(MobSDK.getContext(), (Intent) null);
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public void m349b(final String[] strArr) {
        new Thread(m341a(1)) {
            public void run() {
                try {
                    if (!MobSDK.isForb() && C0690a.m118a()) {
                        int isAuth = MobSDK.isAuth();
                        if (isAuth == 1 || isAuth == 2) {
                            boolean unused = C0735f.this.m351j();
                            SSDKLog.m645b().mo29786w("用户使用的是无弹框隐私版本 authorize 001");
                        }
                    }
                } catch (Throwable th) {
                    NLog b = SSDKLog.m645b();
                    b.mo29768d("authorize(final String[] permissions) " + th, new Object[0]);
                }
            }
        }.start();
        new Thread() {
            /* JADX WARNING: Can't wrap try/catch for region: R(3:16|17|33) */
            /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
                p005cn.sharesdk.framework.ProvicyCanContinue.m98a().mo10464a(new p005cn.sharesdk.framework.C0735f.C07468.C07471(r4));
             */
            /* JADX WARNING: Code restructure failed: missing block: B:33:?, code lost:
                return;
             */
            /* JADX WARNING: Missing exception handler attribute for start block: B:16:0x0059 */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                    r4 = this;
                    boolean r0 = com.mob.MobSDK.isForb()     // Catch:{ Throwable -> 0x009d }
                    r1 = 1
                    if (r0 == 0) goto L_0x0014
                    cn.sharesdk.framework.f r0 = p005cn.sharesdk.framework.C0735f.this     // Catch:{ Throwable -> 0x009d }
                    cn.sharesdk.framework.f r2 = p005cn.sharesdk.framework.C0735f.this     // Catch:{ Throwable -> 0x009d }
                    cn.sharesdk.framework.d r2 = r2.f310c     // Catch:{ Throwable -> 0x009d }
                    r0.m342a((p005cn.sharesdk.framework.C0730d) r2, (int) r1)     // Catch:{ Throwable -> 0x009d }
                    goto L_0x00b9
                L_0x0014:
                    int r0 = com.mob.MobSDK.isAuth()     // Catch:{ Throwable -> 0x0059 }
                    if (r0 == r1) goto L_0x003b
                    r2 = 2
                    if (r0 == r2) goto L_0x003b
                    cn.sharesdk.framework.f r0 = p005cn.sharesdk.framework.C0735f.this     // Catch:{ Throwable -> 0x0059 }
                    cn.sharesdk.framework.d r0 = r0.f310c     // Catch:{ Throwable -> 0x0059 }
                    if (r0 == 0) goto L_0x00b9
                    cn.sharesdk.framework.f r0 = p005cn.sharesdk.framework.C0735f.this     // Catch:{ Throwable -> 0x0059 }
                    cn.sharesdk.framework.d r0 = r0.f310c     // Catch:{ Throwable -> 0x0059 }
                    cn.sharesdk.framework.f r2 = p005cn.sharesdk.framework.C0735f.this     // Catch:{ Throwable -> 0x0059 }
                    cn.sharesdk.framework.Platform r2 = r2.f308a     // Catch:{ Throwable -> 0x0059 }
                    com.mob.commons.dialog.PolicyThrowable r3 = new com.mob.commons.dialog.PolicyThrowable     // Catch:{ Throwable -> 0x0059 }
                    r3.<init>()     // Catch:{ Throwable -> 0x0059 }
                    r0.onError(r2, r1, r3)     // Catch:{ Throwable -> 0x0059 }
                    goto L_0x00b9
                L_0x003b:
                    boolean r0 = p005cn.sharesdk.framework.C0690a.m118a()     // Catch:{ Throwable -> 0x0059 }
                    if (r0 != 0) goto L_0x004d
                    cn.sharesdk.framework.f r0 = p005cn.sharesdk.framework.C0735f.this     // Catch:{ Throwable -> 0x0059 }
                    cn.sharesdk.framework.f r2 = p005cn.sharesdk.framework.C0735f.this     // Catch:{ Throwable -> 0x0059 }
                    cn.sharesdk.framework.d r2 = r2.f310c     // Catch:{ Throwable -> 0x0059 }
                    r0.m347b((p005cn.sharesdk.framework.C0730d) r2, (int) r1)     // Catch:{ Throwable -> 0x0059 }
                    goto L_0x00b9
                L_0x004d:
                    cn.sharesdk.framework.f r0 = p005cn.sharesdk.framework.C0735f.this     // Catch:{ Throwable -> 0x0059 }
                    cn.sharesdk.framework.Platform r0 = r0.f308a     // Catch:{ Throwable -> 0x0059 }
                    java.lang.String[] r2 = r3     // Catch:{ Throwable -> 0x0059 }
                    r0.doAuthorize(r2)     // Catch:{ Throwable -> 0x0059 }
                    goto L_0x00b9
                L_0x0059:
                    cn.sharesdk.framework.ProvicyCanContinue r0 = p005cn.sharesdk.framework.ProvicyCanContinue.m98a()     // Catch:{ Throwable -> 0x0066 }
                    cn.sharesdk.framework.f$8$1 r2 = new cn.sharesdk.framework.f$8$1     // Catch:{ Throwable -> 0x0066 }
                    r2.<init>()     // Catch:{ Throwable -> 0x0066 }
                    r0.mo10464a(r2)     // Catch:{ Throwable -> 0x0066 }
                    goto L_0x00b9
                L_0x0066:
                    r0 = move-exception
                    boolean r2 = p005cn.sharesdk.framework.C0690a.m118a()     // Catch:{ Throwable -> 0x009d }
                    if (r2 != 0) goto L_0x0079
                    cn.sharesdk.framework.f r2 = p005cn.sharesdk.framework.C0735f.this     // Catch:{ Throwable -> 0x009d }
                    cn.sharesdk.framework.f r3 = p005cn.sharesdk.framework.C0735f.this     // Catch:{ Throwable -> 0x009d }
                    cn.sharesdk.framework.d r3 = r3.f310c     // Catch:{ Throwable -> 0x009d }
                    r2.m347b((p005cn.sharesdk.framework.C0730d) r3, (int) r1)     // Catch:{ Throwable -> 0x009d }
                    goto L_0x0084
                L_0x0079:
                    cn.sharesdk.framework.f r1 = p005cn.sharesdk.framework.C0735f.this     // Catch:{ Throwable -> 0x009d }
                    cn.sharesdk.framework.Platform r1 = r1.f308a     // Catch:{ Throwable -> 0x009d }
                    java.lang.String[] r2 = r3     // Catch:{ Throwable -> 0x009d }
                    r1.doAuthorize(r2)     // Catch:{ Throwable -> 0x009d }
                L_0x0084:
                    com.mob.tools.log.NLog r1 = p005cn.sharesdk.framework.utils.SSDKLog.m645b()     // Catch:{ Throwable -> 0x009d }
                    java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x009d }
                    r2.<init>()     // Catch:{ Throwable -> 0x009d }
                    java.lang.String r3 = "用户使用的是非隐私版本 authorize 002 "
                    r2.append(r3)     // Catch:{ Throwable -> 0x009d }
                    r2.append(r0)     // Catch:{ Throwable -> 0x009d }
                    java.lang.String r0 = r2.toString()     // Catch:{ Throwable -> 0x009d }
                    r1.mo29786w((java.lang.String) r0)     // Catch:{ Throwable -> 0x009d }
                    goto L_0x00b9
                L_0x009d:
                    r0 = move-exception
                    com.mob.tools.log.NLog r1 = p005cn.sharesdk.framework.utils.SSDKLog.m645b()
                    java.lang.StringBuilder r2 = new java.lang.StringBuilder
                    r2.<init>()
                    java.lang.String r3 = "new Thread Platform.ACTION_AUTHORIZING "
                    r2.append(r3)
                    r2.append(r0)
                    java.lang.String r0 = r2.toString()
                    r2 = 0
                    java.lang.Object[] r2 = new java.lang.Object[r2]
                    r1.mo29768d(r0, r2)
                L_0x00b9:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: p005cn.sharesdk.framework.C0735f.C07468.run():void");
            }
        }.start();
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:16:0x0049 */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo10672b(final p005cn.sharesdk.framework.Platform.ShareParams r4) {
        /*
            r3 = this;
            boolean r0 = com.mob.MobSDK.isGppVer()     // Catch:{ Throwable -> 0x004c }
            if (r0 == 0) goto L_0x0028
            cn.sharesdk.framework.b.a.e r0 = p005cn.sharesdk.framework.p007b.p008a.C0713e.m196a()     // Catch:{ Throwable -> 0x004c }
            java.lang.String r1 = "gpp_ver_sent"
            boolean r0 = r0.mo10603h(r1)     // Catch:{ Throwable -> 0x004c }
            if (r0 != 0) goto L_0x0028
            cn.sharesdk.framework.AgreementDialog r0 = new cn.sharesdk.framework.AgreementDialog     // Catch:{ Throwable -> 0x004c }
            r0.<init>()     // Catch:{ Throwable -> 0x004c }
            cn.sharesdk.framework.f$9 r1 = new cn.sharesdk.framework.f$9     // Catch:{ Throwable -> 0x004c }
            r1.<init>(r4)     // Catch:{ Throwable -> 0x004c }
            r0.mo10242a((p005cn.sharesdk.framework.AgreementDialog.OnDialogDismiss) r1)     // Catch:{ Throwable -> 0x004c }
            android.content.Context r4 = com.mob.MobSDK.getContext()     // Catch:{ Throwable -> 0x004c }
            r1 = 0
            r0.show(r4, r1)     // Catch:{ Throwable -> 0x004c }
            return
        L_0x0028:
            r0 = 9
            if (r4 != 0) goto L_0x003d
            cn.sharesdk.framework.d r4 = r3.f310c     // Catch:{ Throwable -> 0x004c }
            if (r4 == 0) goto L_0x003c
            cn.sharesdk.framework.d r4 = r3.f310c     // Catch:{ Throwable -> 0x004c }
            cn.sharesdk.framework.Platform r1 = r3.f308a     // Catch:{ Throwable -> 0x004c }
            java.lang.NullPointerException r2 = new java.lang.NullPointerException     // Catch:{ Throwable -> 0x004c }
            r2.<init>()     // Catch:{ Throwable -> 0x004c }
            r4.onError(r1, r0, r2)     // Catch:{ Throwable -> 0x004c }
        L_0x003c:
            return
        L_0x003d:
            boolean r1 = r4.getOpenCustomEven()     // Catch:{ Throwable -> 0x0049 }
            if (r1 != 0) goto L_0x0049
            r1 = 3
            cn.sharesdk.framework.Platform r2 = r3.f308a     // Catch:{ Throwable -> 0x0049 }
            p005cn.sharesdk.framework.ShareSDK.logDemoEvent(r1, r2)     // Catch:{ Throwable -> 0x0049 }
        L_0x0049:
            r3.mo10675c(r0, r4)     // Catch:{ Throwable -> 0x004c }
        L_0x004c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p005cn.sharesdk.framework.C0735f.mo10672b(cn.sharesdk.framework.Platform$ShareParams):void");
    }

    /* renamed from: b */
    public void mo10673b(String str) {
        mo10675c(6, str);
    }

    /* renamed from: a */
    public void mo10666a(String str, int i, int i2) {
        mo10675c(7, new Object[]{Integer.valueOf(i), Integer.valueOf(i2), str});
    }

    /* renamed from: c */
    public void mo10676c(String str) {
        mo10675c(8, str);
    }

    /* renamed from: a */
    public void mo10661a(int i, int i2, String str) {
        mo10675c(2, new Object[]{Integer.valueOf(i), Integer.valueOf(i2), str});
    }

    /* renamed from: a */
    public void mo10667a(String str, String str2, short s, HashMap<String, Object> hashMap, HashMap<String, String> hashMap2) {
        mo10675c(s | 655360, new Object[]{str, str2, hashMap, hashMap2});
    }

    /* renamed from: g */
    public PlatformDb mo10681g() {
        return this.f309b;
    }

    /* renamed from: h */
    public void mo10682h() {
        this.f309b.removeAccount();
    }

    /* renamed from: a */
    public String mo10660a(String str, boolean z) {
        long currentTimeMillis = System.currentTimeMillis();
        if (!this.f314g) {
            NLog b = SSDKLog.m645b();
            b.mo29775i("getShortLintk use time: " + (System.currentTimeMillis() - currentTimeMillis), new Object[0]);
            return str;
        } else if (TextUtils.isEmpty(str)) {
            NLog b2 = SSDKLog.m645b();
            b2.mo29775i("getShortLintk use time: " + (System.currentTimeMillis() - currentTimeMillis), new Object[0]);
            return str;
        } else {
            String a = ShareSDK.m104a(str, z, this.f308a.getPlatformId(), m352k());
            NLog b3 = SSDKLog.m645b();
            b3.mo29775i("getShortLintk use time: " + (System.currentTimeMillis() - currentTimeMillis), new Object[0]);
            return a;
        }
    }

    /* renamed from: k */
    private String m352k() {
        StringBuilder sb = new StringBuilder();
        try {
            if ("TencentWeibo".equals(this.f308a.getName())) {
                SSDKLog.m645b().mo29775i("user id %s ==>>", mo10681g().getUserName());
                sb.append(Data.urlEncode(mo10681g().getUserName(), "utf-8"));
            } else {
                sb.append(Data.urlEncode(mo10681g().getUserId(), "utf-8"));
            }
            sb.append("|");
            sb.append(Data.urlEncode(mo10681g().get("secretType"), "utf-8"));
            sb.append("|");
            sb.append(Data.urlEncode(mo10681g().get("gender"), "utf-8"));
            sb.append("|");
            sb.append(Data.urlEncode(mo10681g().get("birthday"), "utf-8"));
            sb.append("|");
            sb.append(Data.urlEncode(mo10681g().get("educationJSONArrayStr"), "utf-8"));
            sb.append("|");
            sb.append(Data.urlEncode(mo10681g().get("workJSONArrayStr"), "utf-8"));
        } catch (Throwable th) {
            SSDKLog.m645b().mo29787w(th);
        }
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    /* renamed from: i */
    public PlatformActionListener mo10683i() {
        return this.f310c;
    }

    /* renamed from: d */
    public String mo10677d(String str) {
        return ShareSDK.m103a(str);
    }

    /* renamed from: a */
    public String mo10659a(Bitmap bitmap) {
        return ShareSDK.m102a(bitmap);
    }
}
