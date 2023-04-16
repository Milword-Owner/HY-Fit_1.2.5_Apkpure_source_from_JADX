package p005cn.sharesdk.framework;

import android.text.TextUtils;
import com.baidu.mobstat.Config;
import com.mob.MobSDK;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.SharePrefrenceHelper;
import java.util.HashMap;
import p005cn.sharesdk.framework.utils.SSDKLog;

/* renamed from: cn.sharesdk.framework.PlatformDb */
public class PlatformDb {
    private static final String DB_NAME = "cn_sharesdk_weibodb";
    private String platformNname;
    private int platformVersion;

    /* renamed from: sp */
    private SharePrefrenceHelper f150sp = new SharePrefrenceHelper(MobSDK.getContext());

    public PlatformDb(String str, int i) {
        SharePrefrenceHelper sharePrefrenceHelper = this.f150sp;
        sharePrefrenceHelper.open("cn_sharesdk_weibodb_" + str, i);
        this.platformNname = str;
        this.platformVersion = i;
    }

    public void put(String str, String str2) {
        this.f150sp.putString(str, str2);
    }

    public String get(String str) {
        return this.f150sp.getString(str);
    }

    public String getToken() {
        return this.f150sp.getString("token");
    }

    public void putToken(String str) {
        this.f150sp.putString("token", str);
    }

    public String getTokenSecret() {
        return this.f150sp.getString("secret");
    }

    public void putTokenSecret(String str) {
        this.f150sp.putString("secret", str);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(4:3|4|5|8) */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0011, code lost:
        return 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:?, code lost:
        return (long) r2.f150sp.getInt("expiresIn");
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0009 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long getExpiresIn() {
        /*
            r2 = this;
            java.lang.String r0 = "expiresIn"
            com.mob.tools.utils.SharePrefrenceHelper r1 = r2.f150sp     // Catch:{ Throwable -> 0x0009 }
            long r0 = r1.getLong(r0)     // Catch:{ Throwable -> 0x0009 }
            goto L_0x0013
        L_0x0009:
            com.mob.tools.utils.SharePrefrenceHelper r1 = r2.f150sp     // Catch:{ Throwable -> 0x0011 }
            int r0 = r1.getInt(r0)     // Catch:{ Throwable -> 0x0011 }
            long r0 = (long) r0
            goto L_0x0013
        L_0x0011:
            r0 = 0
        L_0x0013:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p005cn.sharesdk.framework.PlatformDb.getExpiresIn():long");
    }

    public void putExpiresIn(long j) {
        this.f150sp.putLong("expiresIn", Long.valueOf(j));
        this.f150sp.putLong("expiresTime", Long.valueOf(System.currentTimeMillis()));
    }

    public long getExpiresTime() {
        return this.f150sp.getLong("expiresTime") + (getExpiresIn() * 1000);
    }

    public int getPlatformVersion() {
        return this.platformVersion;
    }

    public String getPlatformNname() {
        return this.platformNname;
    }

    public void putUserId(String str) {
        this.f150sp.putString("userID", str);
    }

    public String getUserId() {
        String string = this.f150sp.getString("userID");
        return TextUtils.isEmpty(string) ? this.f150sp.getString("weibo") : string;
    }

    public String getUserName() {
        return this.f150sp.getString("nickname");
    }

    public String getUserIcon() {
        return this.f150sp.getString("icon");
    }

    public void removeAccount() {
        this.f150sp.clear();
    }

    public String exportData() {
        try {
            HashMap hashMap = new HashMap();
            hashMap.putAll(this.f150sp.getAll());
            return new Hashon().fromHashMap(hashMap);
        } catch (Throwable th) {
            SSDKLog.m645b().mo29769d(th);
            return null;
        }
    }

    public void importData(String str) {
        try {
            HashMap fromJson = new Hashon().fromJson(str);
            if (fromJson != null) {
                this.f150sp.putAll(fromJson);
            }
        } catch (Throwable th) {
            SSDKLog.m645b().mo29769d(th);
        }
    }

    public boolean isValid() {
        String token = getToken();
        if (token == null || token.length() <= 0) {
            return false;
        }
        if (getExpiresIn() != 0 && getExpiresTime() <= System.currentTimeMillis()) {
            return false;
        }
        return true;
    }

    public String getUserGender() {
        String string = this.f150sp.getString("gender");
        if ("0".equals(string)) {
            return Config.MODEL;
        }
        if ("1".equals(string)) {
            return "f";
        }
        return null;
    }
}
