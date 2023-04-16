package p005cn.sharesdk.framework.loopshare;

import android.content.Context;
import android.content.SharedPreferences;

/* renamed from: cn.sharesdk.framework.loopshare.a */
/* compiled from: MobLinkSP */
public class C0761a {

    /* renamed from: a */
    private SharedPreferences f360a;

    /* renamed from: b */
    private SharedPreferences.Editor f361b = this.f360a.edit();

    public C0761a(Context context, String str) {
        this.f360a = context.getSharedPreferences(str, 0);
    }

    /* renamed from: a */
    public void mo10767a(String str, Object obj) {
        if (obj instanceof String) {
            this.f361b.putString(str, (String) obj);
        } else if (obj instanceof Integer) {
            this.f361b.putInt(str, ((Integer) obj).intValue());
        } else if (obj instanceof Boolean) {
            this.f361b.putBoolean(str, ((Boolean) obj).booleanValue());
        } else if (obj instanceof Float) {
            this.f361b.putFloat(str, ((Float) obj).floatValue());
        } else if (obj instanceof Long) {
            this.f361b.putLong(str, ((Long) obj).longValue());
        } else {
            this.f361b.putString(str, obj.toString());
        }
        this.f361b.commit();
    }

    /* renamed from: b */
    public Object mo10768b(String str, Object obj) {
        if (obj instanceof String) {
            return this.f360a.getString(str, (String) obj);
        }
        if (obj instanceof Integer) {
            return Integer.valueOf(this.f360a.getInt(str, ((Integer) obj).intValue()));
        }
        if (obj instanceof Boolean) {
            return Boolean.valueOf(this.f360a.getBoolean(str, ((Boolean) obj).booleanValue()));
        }
        if (obj instanceof Float) {
            return Float.valueOf(this.f360a.getFloat(str, ((Float) obj).floatValue()));
        }
        if (obj instanceof Long) {
            return Long.valueOf(this.f360a.getLong(str, ((Long) obj).longValue()));
        }
        return this.f360a.getString(str, (String) null);
    }
}
