package p005cn.sharesdk.framework.utils;

import android.util.Base64;
import com.baidubce.http.Headers;
import com.mob.tools.network.KVPair;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import kotlin.text.Typography;

/* renamed from: cn.sharesdk.framework.utils.b */
/* compiled from: Oauth1Signer */
public class C0801b {

    /* renamed from: a */
    private C0804b f598a = new C0804b();

    /* renamed from: b */
    private C0805c f599b = new C0805c("-._~", false);

    /* renamed from: cn.sharesdk.framework.utils.b$a */
    /* compiled from: Oauth1Signer */
    public enum C0803a {
        HMAC_SHA1,
        PLAINTEXT
    }

    /* renamed from: cn.sharesdk.framework.utils.b$b */
    /* compiled from: Oauth1Signer */
    public static class C0804b {

        /* renamed from: a */
        public String f604a;

        /* renamed from: b */
        public String f605b;

        /* renamed from: c */
        public String f606c;

        /* renamed from: d */
        public String f607d;

        /* renamed from: e */
        public String f608e;
    }

    /* renamed from: a */
    public void mo10923a(String str, String str2, String str3) {
        C0804b bVar = this.f598a;
        bVar.f604a = str;
        bVar.f605b = str2;
        bVar.f608e = str3;
    }

    /* renamed from: a */
    public C0804b mo10917a() {
        return this.f598a;
    }

    /* renamed from: a */
    public ArrayList<KVPair<String>> mo10919a(String str, ArrayList<KVPair<String>> arrayList) throws Throwable {
        return mo10920a(str, arrayList, C0803a.HMAC_SHA1);
    }

    /* renamed from: a */
    public ArrayList<KVPair<String>> mo10920a(String str, ArrayList<KVPair<String>> arrayList, C0803a aVar) throws Throwable {
        return m661a(str, "POST", arrayList, aVar);
    }

    /* renamed from: b */
    public ArrayList<KVPair<String>> mo10924b(String str, ArrayList<KVPair<String>> arrayList) throws Throwable {
        return mo10925b(str, arrayList, C0803a.HMAC_SHA1);
    }

    /* renamed from: b */
    public ArrayList<KVPair<String>> mo10925b(String str, ArrayList<KVPair<String>> arrayList, C0803a aVar) throws Throwable {
        return m661a(str, "GET", arrayList, aVar);
    }

    /* renamed from: c */
    public ArrayList<KVPair<String>> mo10926c(String str, ArrayList<KVPair<String>> arrayList, C0803a aVar) throws Throwable {
        return m661a(str, "PUT", arrayList, aVar);
    }

    /* renamed from: a */
    public void mo10922a(String str, String str2) {
        C0804b bVar = this.f598a;
        bVar.f606c = str;
        bVar.f607d = str2;
    }

    /* renamed from: a */
    private ArrayList<KVPair<String>> m661a(String str, String str2, ArrayList<KVPair<String>> arrayList, C0803a aVar) throws Throwable {
        String str3;
        long currentTimeMillis = System.currentTimeMillis();
        int i = C08021.f600a[aVar.ordinal()];
        String str4 = null;
        if (i == 1) {
            SecretKeySpec secretKeySpec = new SecretKeySpec((mo10918a(this.f598a.f605b) + Typography.amp + mo10918a(this.f598a.f607d)).getBytes("utf-8"), "HMAC-SHA1");
            Mac instance = Mac.getInstance("HMAC-SHA1");
            instance.init(secretKeySpec);
            String b = m662b(m660a(currentTimeMillis, arrayList, "HMAC-SHA1"));
            str4 = new String(Base64.encode(instance.doFinal((str2 + Typography.amp + mo10918a(str) + Typography.amp + mo10918a(b)).getBytes("utf-8")), 0)).trim();
            str3 = "HMAC-SHA1";
        } else if (i != 2) {
            str3 = null;
        } else {
            str4 = mo10918a(this.f598a.f605b) + Typography.amp + mo10918a(this.f598a.f607d);
            str3 = "PLAINTEXT";
        }
        ArrayList<KVPair<String>> a = m659a(currentTimeMillis, str3);
        a.add(new KVPair("oauth_signature", str4));
        return a;
    }

    /* renamed from: cn.sharesdk.framework.utils.b$1 */
    /* compiled from: Oauth1Signer */
    static /* synthetic */ class C08021 {

        /* renamed from: a */
        static final /* synthetic */ int[] f600a = new int[C0803a.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        static {
            /*
                cn.sharesdk.framework.utils.b$a[] r0 = p005cn.sharesdk.framework.utils.C0801b.C0803a.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f600a = r0
                int[] r0 = f600a     // Catch:{ NoSuchFieldError -> 0x0014 }
                cn.sharesdk.framework.utils.b$a r1 = p005cn.sharesdk.framework.utils.C0801b.C0803a.HMAC_SHA1     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = f600a     // Catch:{ NoSuchFieldError -> 0x001f }
                cn.sharesdk.framework.utils.b$a r1 = p005cn.sharesdk.framework.utils.C0801b.C0803a.PLAINTEXT     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: p005cn.sharesdk.framework.utils.C0801b.C08021.<clinit>():void");
        }
    }

    /* renamed from: a */
    public String mo10918a(String str) {
        return str == null ? "" : this.f599b.escape(str);
    }

    /* renamed from: a */
    private ArrayList<KVPair<String>> m660a(long j, ArrayList<KVPair<String>> arrayList, String str) {
        HashMap hashMap = new HashMap();
        if (arrayList != null) {
            Iterator<KVPair<String>> it = arrayList.iterator();
            while (it.hasNext()) {
                KVPair next = it.next();
                hashMap.put(mo10918a(next.name), mo10918a((String) next.value));
            }
        }
        ArrayList<KVPair<String>> a = m659a(j, str);
        if (a != null) {
            Iterator<KVPair<String>> it2 = a.iterator();
            while (it2.hasNext()) {
                KVPair next2 = it2.next();
                hashMap.put(mo10918a(next2.name), mo10918a((String) next2.value));
            }
        }
        String[] strArr = new String[hashMap.size()];
        int i = 0;
        for (Map.Entry key : hashMap.entrySet()) {
            strArr[i] = (String) key.getKey();
            i++;
        }
        Arrays.sort(strArr);
        ArrayList<KVPair<String>> arrayList2 = new ArrayList<>();
        for (String str2 : strArr) {
            arrayList2.add(new KVPair(str2, hashMap.get(str2)));
        }
        return arrayList2;
    }

    /* renamed from: b */
    private String m662b(ArrayList<KVPair<String>> arrayList) {
        if (arrayList == null || arrayList.size() <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        Iterator<KVPair<String>> it = arrayList.iterator();
        while (it.hasNext()) {
            KVPair next = it.next();
            if (i > 0) {
                sb.append(Typography.amp);
            }
            sb.append(next.name);
            sb.append('=');
            sb.append((String) next.value);
            i++;
        }
        return sb.toString();
    }

    /* renamed from: a */
    private ArrayList<KVPair<String>> m659a(long j, String str) {
        ArrayList<KVPair<String>> arrayList = new ArrayList<>();
        arrayList.add(new KVPair("oauth_consumer_key", this.f598a.f604a));
        arrayList.add(new KVPair("oauth_signature_method", str));
        arrayList.add(new KVPair("oauth_timestamp", String.valueOf(j / 1000)));
        arrayList.add(new KVPair("oauth_nonce", String.valueOf(j)));
        arrayList.add(new KVPair("oauth_version", "1.0"));
        String str2 = this.f598a.f606c;
        if (str2 != null && str2.length() > 0) {
            arrayList.add(new KVPair("oauth_token", str2));
        }
        return arrayList;
    }

    /* renamed from: a */
    public ArrayList<KVPair<String>> mo10921a(ArrayList<KVPair<String>> arrayList) {
        StringBuilder sb = new StringBuilder("OAuth ");
        Iterator<KVPair<String>> it = arrayList.iterator();
        int i = 0;
        while (it.hasNext()) {
            KVPair next = it.next();
            if (i > 0) {
                sb.append(',');
            }
            String a = mo10918a((String) next.value);
            sb.append(next.name);
            sb.append("=\"");
            sb.append(a);
            sb.append("\"");
            i++;
        }
        ArrayList<KVPair<String>> arrayList2 = new ArrayList<>();
        arrayList2.add(new KVPair(Headers.AUTHORIZATION, sb.toString()));
        arrayList2.add(new KVPair(Headers.CONTENT_TYPE, "application/x-www-form-urlencoded"));
        return arrayList2;
    }
}
