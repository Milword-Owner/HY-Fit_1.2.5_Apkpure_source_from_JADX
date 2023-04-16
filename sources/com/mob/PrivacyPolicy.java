package com.mob;

import android.text.TextUtils;
import android.util.Base64;
import com.baidu.mobstat.Config;
import com.mob.tools.MobLog;
import com.mob.tools.proguard.EverythingKeeper;
import com.mob.tools.proguard.PublicMemberKeeper;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.DeviceHelper;
import java.io.Serializable;
import org.json.JSONObject;

public class PrivacyPolicy implements EverythingKeeper, Serializable {
    private String content;
    private int ppVersion;
    private long timestamp;
    private String title;

    public interface OnPolicyListener extends PublicMemberKeeper {
        void onComplete(PrivacyPolicy privacyPolicy);

        void onFailure(Throwable th);
    }

    public PrivacyPolicy() {
    }

    public PrivacyPolicy(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                setTimestamp(jSONObject.optLong("timestamp"));
                setTitle(decrypt(jSONObject.optString("title")));
                setContent(decrypt(jSONObject.optString("content")));
                String decrypt = decrypt(jSONObject.optString("ppVersion"));
                if (!TextUtils.isEmpty(decrypt)) {
                    setPpVersion(Integer.parseInt(decrypt.trim()));
                }
            } catch (Throwable th) {
                MobLog.getInstance().mo29769d(th);
            }
        }
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public int getPpVersion() {
        return this.ppVersion;
    }

    public void setPpVersion(int i) {
        this.ppVersion = i;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(long j) {
        this.timestamp = j;
    }

    private String decrypt(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            String appkey = MobSDK.getAppkey();
            String packageName = DeviceHelper.getInstance(MobSDK.getContext()).getPackageName();
            return new String(Data.AES128Decode(Data.rawMD5(appkey + Config.TRACE_TODAY_VISIT_SPLIT + packageName + Config.TRACE_TODAY_VISIT_SPLIT + getTimestamp()), Base64.decode(str, 0)), "UTF-8");
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
            return null;
        }
    }
}
