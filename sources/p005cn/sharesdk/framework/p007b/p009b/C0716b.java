package p005cn.sharesdk.framework.p007b.p009b;

import android.text.TextUtils;
import android.util.Base64;
import com.google.android.gms.fitness.FitnessStatusCodes;
import com.mob.tools.utils.Data;
import p005cn.sharesdk.framework.utils.SSDKLog;

/* renamed from: cn.sharesdk.framework.b.b.b */
/* compiled from: AuthEvent */
public class C0716b extends C0717c {

    /* renamed from: m */
    private static int f229m;

    /* renamed from: n */
    private static long f230n;

    /* renamed from: a */
    public int f231a;

    /* renamed from: b */
    public String f232b;

    /* renamed from: c */
    public String f233c;

    /* renamed from: d */
    public String f234d;

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public String mo10612a() {
        return "[AUT]";
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public int mo10614b() {
        return FitnessStatusCodes.NEEDS_OAUTH_PERMISSIONS;
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public int mo10615c() {
        return 5;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append('|');
        sb.append(this.f231a);
        sb.append('|');
        sb.append(this.f232b);
        sb.append('|');
        if (!TextUtils.isEmpty(this.f234d)) {
            try {
                String encodeToString = Base64.encodeToString(Data.AES128Encode(this.f236f.substring(0, 16), this.f234d), 0);
                if (!TextUtils.isEmpty(encodeToString) && encodeToString.contains("\n")) {
                    encodeToString = encodeToString.replace("\n", "");
                }
                sb.append(encodeToString);
            } catch (Throwable th) {
                SSDKLog.m645b().mo29769d(th);
            }
        }
        sb.append('|');
        if (!TextUtils.isEmpty(this.f242l)) {
            sb.append(this.f242l);
        }
        sb.append('|');
        if (!TextUtils.isEmpty(this.f233c)) {
            sb.append(this.f233c);
        }
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public long mo10616d() {
        return (long) f229m;
    }

    /* access modifiers changed from: protected */
    /* renamed from: e */
    public long mo10617e() {
        return f230n;
    }

    /* access modifiers changed from: protected */
    /* renamed from: f */
    public void mo10618f() {
        f229m++;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo10613a(long j) {
        f230n = j;
    }
}
