package p005cn.sharesdk.framework.p007b.p009b;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Base64;
import com.facebook.appevents.internal.ViewHierarchyConstants;
import com.google.android.gms.fitness.FitnessStatusCodes;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.Hashon;
import java.util.ArrayList;
import java.util.HashMap;
import p005cn.sharesdk.framework.utils.SSDKLog;

/* renamed from: cn.sharesdk.framework.b.b.f */
/* compiled from: ShareEvent */
public class C0720f extends C0717c {

    /* renamed from: o */
    private static int f251o;

    /* renamed from: p */
    private static long f252p;

    /* renamed from: a */
    public int f253a;

    /* renamed from: b */
    public String f254b;

    /* renamed from: c */
    public String f255c;

    /* renamed from: d */
    public C0721a f256d = new C0721a();

    /* renamed from: m */
    public String f257m;

    /* renamed from: n */
    public String[] f258n;

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public String mo10612a() {
        return "[SHR]";
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public int mo10614b() {
        return FitnessStatusCodes.NEEDS_OAUTH_PERMISSIONS;
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public int mo10615c() {
        return 30;
    }

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append('|');
        sb.append(this.f253a);
        sb.append('|');
        sb.append(this.f254b);
        sb.append('|');
        sb.append(TextUtils.isEmpty(this.f255c) ? "" : this.f255c);
        String[] strArr = this.f258n;
        if (strArr == null || strArr.length <= 0) {
            str = "";
        } else {
            str = "[\"" + TextUtils.join("\",\"", this.f258n) + "\"]";
        }
        sb.append('|');
        sb.append(str);
        sb.append('|');
        C0721a aVar = this.f256d;
        if (aVar != null) {
            try {
                String encodeToString = Base64.encodeToString(Data.AES128Encode(this.f236f.substring(0, 16), aVar.toString()), 0);
                if (encodeToString.contains("\n")) {
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
        if (!TextUtils.isEmpty(this.f257m)) {
            try {
                String encodeToString2 = Base64.encodeToString(Data.AES128Encode(this.f236f.substring(0, 16), this.f257m), 0);
                if (!TextUtils.isEmpty(encodeToString2) && encodeToString2.contains("\n")) {
                    encodeToString2 = encodeToString2.replace("\n", "");
                }
                sb.append(encodeToString2);
            } catch (Throwable th2) {
                SSDKLog.m645b().mo29787w(th2);
            }
        }
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public long mo10616d() {
        return (long) f251o;
    }

    /* access modifiers changed from: protected */
    /* renamed from: e */
    public long mo10617e() {
        return f252p;
    }

    /* access modifiers changed from: protected */
    /* renamed from: f */
    public void mo10618f() {
        f251o++;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo10613a(long j) {
        f252p = j;
    }

    /* renamed from: cn.sharesdk.framework.b.b.f$a */
    /* compiled from: ShareEvent */
    public static class C0721a {

        /* renamed from: a */
        public String f259a = "";

        /* renamed from: b */
        public String f260b;

        /* renamed from: c */
        public ArrayList<String> f261c = new ArrayList<>();

        /* renamed from: d */
        public ArrayList<String> f262d = new ArrayList<>();

        /* renamed from: e */
        public ArrayList<String> f263e = new ArrayList<>();

        /* renamed from: f */
        public ArrayList<Bitmap> f264f = new ArrayList<>();

        /* renamed from: g */
        public HashMap<String, Object> f265g;

        public String toString() {
            HashMap hashMap = new HashMap();
            if (!TextUtils.isEmpty(this.f260b)) {
                this.f260b = this.f260b.trim().replaceAll("\r", "");
                this.f260b = this.f260b.trim().replaceAll("\n", "");
                this.f260b = this.f260b.trim().replaceAll("\r\n", "");
            }
            hashMap.put(ViewHierarchyConstants.TEXT_KEY, this.f260b);
            hashMap.put("url", this.f261c);
            ArrayList<String> arrayList = this.f262d;
            if (arrayList != null && arrayList.size() > 0) {
                hashMap.put("imgs", this.f262d);
            }
            if (this.f265g != null) {
                hashMap.put("attch", new Hashon().fromHashMap(this.f265g));
            }
            return new Hashon().fromHashMap(hashMap);
        }
    }
}
