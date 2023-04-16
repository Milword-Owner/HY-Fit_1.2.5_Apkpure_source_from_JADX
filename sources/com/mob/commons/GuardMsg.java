package com.mob.commons;

import android.text.TextUtils;
import com.baidu.mobstat.Config;
import com.mob.guard.C2335e;
import com.mob.tools.proguard.PrivateMemberKeeper;
import com.mob.tools.utils.Hashon;
import java.util.HashMap;

public class GuardMsg implements PrivateMemberKeeper {
    private String goalPkgName;
    private String hostPkgName;

    /* renamed from: id */
    private String f1730id;
    private String info;
    private boolean isSynchronousPublish;
    private int masterBigger;
    private long timestamp;
    private int version;

    public String toJson() {
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("id", getId());
            hashMap.put("version", Integer.valueOf(getVersion()));
            hashMap.put("timestamp", Long.valueOf(getTimestamp()));
            hashMap.put(Config.LAUNCH_INFO, getInfo());
            hashMap.put("hostPkgName", getHostPkgName());
            hashMap.put("goalPkgName", getGoalPkgName());
            hashMap.put("masterBigger", Integer.valueOf(getMasterBigger()));
            hashMap.put("isSynchronousPublish", Boolean.valueOf(isSynchronousPublish()));
            return new Hashon().fromHashMap(hashMap);
        } catch (Throwable th) {
            C2335e.m2675b().mo29769d(th);
            return null;
        }
    }

    public void toObj(String str, GuardMsg guardMsg) {
        try {
            if (!TextUtils.isEmpty(str)) {
                if (guardMsg == null) {
                    guardMsg = new GuardMsg();
                }
                HashMap fromJson = new Hashon().fromJson(str);
                int intValue = ((Integer) fromJson.get("version")).intValue();
                int intValue2 = ((Integer) fromJson.get("masterBigger")).intValue();
                boolean booleanValue = ((Boolean) fromJson.get("isSynchronousPublish")).booleanValue();
                guardMsg.setId((String) fromJson.get("id"));
                guardMsg.setVersion(intValue);
                guardMsg.setTimestamp(((Long) fromJson.get("timestamp")).longValue());
                guardMsg.setInfo((String) fromJson.get(Config.LAUNCH_INFO));
                guardMsg.setHostPkgName((String) fromJson.get("hostPkgName"));
                guardMsg.setGoalPkgName((String) fromJson.get("goalPkgName"));
                guardMsg.setMasterBigger(intValue2);
                guardMsg.setSynchronousPublish(booleanValue);
            }
        } catch (Throwable th) {
            C2335e.m2675b().mo29769d(th);
        }
    }

    public String getId() {
        return this.f1730id;
    }

    public void setId(String str) {
        this.f1730id = str;
    }

    public int getVersion() {
        return this.version;
    }

    public void setVersion(int i) {
        this.version = i;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(long j) {
        this.timestamp = j;
    }

    public String getInfo() {
        return this.info;
    }

    public void setInfo(String str) {
        this.info = str;
    }

    public String getHostPkgName() {
        return this.hostPkgName;
    }

    public void setHostPkgName(String str) {
        this.hostPkgName = str;
    }

    public String getGoalPkgName() {
        return this.goalPkgName;
    }

    public void setGoalPkgName(String str) {
        this.goalPkgName = str;
    }

    public int getMasterBigger() {
        return this.masterBigger;
    }

    public void setMasterBigger(int i) {
        this.masterBigger = i;
    }

    public boolean isSynchronousPublish() {
        return this.isSynchronousPublish;
    }

    public void setSynchronousPublish(boolean z) {
        this.isSynchronousPublish = z;
    }

    public String toString() {
        return "GuardMsg{ id= '" + this.f1730id + '\'' + ", version = " + this.version + ", timestamp = " + this.timestamp + ", info = '" + this.info + '\'' + ", hostPkgName ='" + this.hostPkgName + '\'' + ", goalPkgName ='" + this.goalPkgName + '\'' + ", masterBigger =" + this.masterBigger + ", isSynchronousPublish =" + this.isSynchronousPublish + '}';
    }
}
