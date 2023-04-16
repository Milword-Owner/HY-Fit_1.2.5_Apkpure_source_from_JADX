package p005cn.sharesdk.framework.p006a;

import android.text.TextUtils;
import com.mob.tools.network.HTTPPart;
import com.mob.tools.network.KVPair;
import com.mob.tools.network.NetworkHelper;
import com.mob.tools.network.RawNetworkCallback;
import java.util.ArrayList;
import p005cn.sharesdk.framework.ShareSDK;

/* renamed from: cn.sharesdk.framework.a.b */
/* compiled from: SSDKNetworkHelper */
public class C0693b extends NetworkHelper {

    /* renamed from: a */
    private static C0693b f172a;

    private C0693b() {
    }

    /* renamed from: a */
    public static C0693b m120a() {
        if (f172a == null) {
            f172a = new C0693b();
        }
        return f172a;
    }

    /* renamed from: a */
    public String mo10493a(String str, ArrayList<KVPair<String>> arrayList, String str2, int i) throws Throwable {
        return mo10494a(str, arrayList, (ArrayList<KVPair<String>>) null, (NetworkHelper.NetworkTimeOut) null, str2, i);
    }

    /* renamed from: a */
    public String mo10494a(String str, ArrayList<KVPair<String>> arrayList, ArrayList<KVPair<String>> arrayList2, NetworkHelper.NetworkTimeOut networkTimeOut, String str2, int i) throws Throwable {
        m121a(str2, i);
        return super.httpGet(str, arrayList, arrayList2, networkTimeOut);
    }

    /* renamed from: b */
    public String mo10496b(String str, ArrayList<KVPair<String>> arrayList, String str2, int i) throws Throwable {
        return mo10490a(str, arrayList, (KVPair<String>) null, str2, i);
    }

    /* renamed from: a */
    public String mo10490a(String str, ArrayList<KVPair<String>> arrayList, KVPair<String> kVPair, String str2, int i) throws Throwable {
        return mo10492a(str, arrayList, kVPair, (ArrayList<KVPair<String>>) null, str2, i);
    }

    /* renamed from: a */
    public String mo10492a(String str, ArrayList<KVPair<String>> arrayList, KVPair<String> kVPair, ArrayList<KVPair<String>> arrayList2, String str2, int i) throws Throwable {
        return mo10491a(str, arrayList, kVPair, arrayList2, (NetworkHelper.NetworkTimeOut) null, str2, i);
    }

    /* renamed from: a */
    public String mo10491a(String str, ArrayList<KVPair<String>> arrayList, KVPair<String> kVPair, ArrayList<KVPair<String>> arrayList2, NetworkHelper.NetworkTimeOut networkTimeOut, String str2, int i) throws Throwable {
        m121a(str2, i);
        return super.httpPost(str, arrayList, kVPair, arrayList2, networkTimeOut);
    }

    /* renamed from: a */
    public void mo10495a(String str, ArrayList<KVPair<String>> arrayList, HTTPPart hTTPPart, RawNetworkCallback rawNetworkCallback, String str2, int i) throws Throwable {
        m121a(str2, i);
        super.rawPost(str, arrayList, hTTPPart, rawNetworkCallback, (NetworkHelper.NetworkTimeOut) null);
    }

    /* renamed from: b */
    public String mo10497b(String str, ArrayList<KVPair<String>> arrayList, ArrayList<KVPair<String>> arrayList2, NetworkHelper.NetworkTimeOut networkTimeOut, String str2, int i) throws Throwable {
        m121a(str2, i);
        return super.jsonPost(str, arrayList, arrayList2, networkTimeOut);
    }

    /* renamed from: a */
    private void m121a(String str, int i) {
        if (!TextUtils.isEmpty(str) && i > 0) {
            ShareSDK.logApiEvent(str, i);
        }
    }
}
