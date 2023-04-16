package p005cn.sharesdk.framework.p007b;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Base64;
import com.baidu.mobstat.Config;
import com.facebook.internal.ServerProtocol;
import com.facebook.share.internal.ShareConstants;
import com.facebook.share.widget.ShareDialog;
import com.huayu.tzc.utils.DateUtil;
import com.mob.MobSDK;
import com.mob.tools.utils.BitmapHelper;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.ResHelper;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPOutputStream;
import p005cn.sharesdk.framework.p007b.p008a.C0711c;
import p005cn.sharesdk.framework.p007b.p008a.C0713e;
import p005cn.sharesdk.framework.p007b.p009b.C0716b;
import p005cn.sharesdk.framework.p007b.p009b.C0717c;
import p005cn.sharesdk.framework.p007b.p009b.C0720f;
import p005cn.sharesdk.framework.utils.SSDKLog;
import p015io.reactivex.annotations.SchedulerSupport;

/* renamed from: cn.sharesdk.framework.b.a */
/* compiled from: EventManager */
public class C0708a {

    /* renamed from: a */
    private static C0708a f211a;

    /* renamed from: b */
    private C0723c f212b = new C0723c();

    /* renamed from: c */
    private DeviceHelper f213c = DeviceHelper.getInstance(MobSDK.getContext());

    /* renamed from: d */
    private C0713e f214d = C0713e.m196a();

    /* renamed from: e */
    private boolean f215e = true;

    /* renamed from: a */
    public static C0708a m166a() {
        if (f211a == null) {
            f211a = new C0708a();
        }
        return f211a;
    }

    private C0708a() {
    }

    /* renamed from: a */
    public void mo10564a(String str) {
        if (this.f212b != null && !TextUtils.isEmpty(str)) {
            this.f212b.mo10626a(str);
        }
    }

    /* renamed from: b */
    public void mo10567b() {
        try {
            String networkType = this.f213c.getNetworkType();
            if (SchedulerSupport.NONE.equals(networkType)) {
                return;
            }
            if (!TextUtils.isEmpty(networkType)) {
                long longValue = this.f214d.mo10610l().longValue();
                long currentTimeMillis = System.currentTimeMillis();
                Calendar instance = Calendar.getInstance();
                instance.setTimeInMillis(longValue);
                int i = instance.get(5);
                instance.setTimeInMillis(currentTimeMillis);
                int i2 = instance.get(5);
                if (currentTimeMillis - longValue >= DateUtil.DAY_MILL_SECONDS || i != i2) {
                    HashMap<String, Object> a = this.f212b.mo10623a();
                    this.f214d.mo10590b(a.containsKey("res") ? ServerProtocol.DIALOG_RETURN_SCOPES_TRUE.equals(String.valueOf(a.get("res"))) : true);
                    if (a != null && a.size() > 0) {
                        this.f214d.mo10588b(System.currentTimeMillis());
                    }
                }
            }
        } catch (Throwable th) {
            SSDKLog.m645b().mo29769d(th);
        }
    }

    /* renamed from: c */
    public void mo10569c() {
        HashMap hashMap;
        HashMap hashMap2;
        try {
            String networkType = this.f213c.getNetworkType();
            if (SchedulerSupport.NONE.equals(networkType)) {
                return;
            }
            if (!TextUtils.isEmpty(networkType)) {
                if (this.f214d.mo10609k()) {
                    this.f214d.mo10581a(System.currentTimeMillis());
                    HashMap<String, Object> c = this.f212b.mo10633c();
                    if (!c.containsKey("status") || ResHelper.parseInt(String.valueOf(c.get("status"))) != -200) {
                        if (c.containsKey("timestamp")) {
                            this.f214d.mo10584a("service_time", Long.valueOf(System.currentTimeMillis() - ResHelper.parseLong(String.valueOf(c.get("timestamp")))));
                        }
                        if (c.containsKey("switchs") && (hashMap2 = (HashMap) c.get("switchs")) != null) {
                            String valueOf = String.valueOf(hashMap2.get(Config.DEVICE_PART));
                            String valueOf2 = String.valueOf(hashMap2.get(ShareDialog.WEB_SHARE_DIALOG));
                            String valueOf3 = String.valueOf(hashMap2.get("auth"));
                            String valueOf4 = String.valueOf(hashMap2.get("backflow"));
                            String valueOf5 = String.valueOf(hashMap2.get("loginplus"));
                            String valueOf6 = String.valueOf(hashMap2.get("linkcard"));
                            this.f214d.mo10589b(valueOf);
                            this.f214d.mo10594d(valueOf2);
                            this.f214d.mo10591c(valueOf3);
                            this.f214d.mo10582a(valueOf4);
                            this.f214d.mo10596e(valueOf5);
                            this.f214d.mo10599f(valueOf6);
                        }
                        if (c.containsKey("serpaths") && (hashMap = (HashMap) c.get("serpaths")) != null) {
                            String valueOf7 = String.valueOf(hashMap.get("defhost"));
                            String valueOf8 = String.valueOf(hashMap.get("defport"));
                            if (!TextUtils.isEmpty(valueOf7)) {
                                if (!TextUtils.isEmpty(valueOf8)) {
                                    if (!"443".equals(valueOf8)) {
                                        if (!"80".equals(valueOf8)) {
                                            C0723c cVar = this.f212b;
                                            cVar.mo10631b(MobSDK.checkRequestUrl(valueOf7) + Config.TRACE_TODAY_VISIT_SPLIT + valueOf8);
                                        }
                                    }
                                    this.f212b.mo10631b(MobSDK.checkRequestUrl(valueOf7));
                                }
                            }
                            HashMap hashMap3 = new HashMap();
                            if (hashMap.containsKey("assigns")) {
                                HashMap hashMap4 = (HashMap) hashMap.get("assigns");
                                if (hashMap4 != null) {
                                    if (hashMap4.size() != 0) {
                                        for (String str : hashMap4.keySet()) {
                                            HashMap hashMap5 = (HashMap) hashMap4.get(str);
                                            String valueOf9 = String.valueOf(hashMap5.get("host"));
                                            String valueOf10 = String.valueOf(hashMap5.get("port"));
                                            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(valueOf9) && !TextUtils.isEmpty(valueOf10)) {
                                                hashMap3.put(str, "http://" + valueOf9 + Config.TRACE_TODAY_VISIT_SPLIT + valueOf10);
                                            }
                                        }
                                        this.f212b.mo10628a((HashMap<String, String>) hashMap3);
                                        return;
                                    }
                                }
                                this.f212b.mo10628a((HashMap<String, String>) null);
                                return;
                            }
                            return;
                        }
                        return;
                    }
                    SSDKLog.m645b().mo29768d((String) c.get("error"), new Object[0]);
                }
            }
        } catch (Throwable th) {
            SSDKLog.m645b().mo29769d(th);
        }
    }

    /* renamed from: a */
    public void mo10563a(C0717c cVar) {
        try {
            if (this.f214d.mo10609k()) {
                if (cVar instanceof C0716b) {
                    m170a((C0716b) cVar);
                } else if (cVar instanceof C0720f) {
                    m171a((C0720f) cVar);
                }
                if (!this.f214d.mo10593c()) {
                    cVar.f242l = null;
                }
                long b = this.f214d.mo10587b();
                if (b == 0) {
                    b = this.f212b.mo10630b();
                }
                cVar.f235e = System.currentTimeMillis() - b;
                this.f212b.mo10625a(cVar);
            }
        } catch (Throwable th) {
            SSDKLog.m645b().mo29769d(th);
        }
    }

    /* renamed from: a */
    private void m170a(C0716b bVar) throws Throwable {
        boolean d = this.f214d.mo10595d();
        String str = bVar.f233c;
        if (!d || TextUtils.isEmpty(str)) {
            bVar.f234d = null;
            bVar.f233c = null;
            return;
        }
        bVar.f233c = Data.Base64AES(str, bVar.f236f.substring(0, 16));
    }

    /* renamed from: a */
    private void m171a(C0720f fVar) throws Throwable {
        int f = this.f214d.mo10598f();
        boolean d = this.f214d.mo10595d();
        C0720f.C0721a aVar = fVar.f256d;
        if (f == 1) {
            int size = (aVar == null || aVar.f263e == null) ? 0 : aVar.f263e.size();
            for (int i = 0; i < size; i++) {
                String a = m168a(aVar.f263e.get(i), C0714b.FINISH_SHARE);
                if (!TextUtils.isEmpty(a)) {
                    aVar.f262d.add(a);
                }
            }
            int size2 = (aVar == null || aVar.f264f == null) ? 0 : aVar.f264f.size();
            for (int i2 = 0; i2 < size2; i2++) {
                String a2 = m167a(aVar.f264f.get(i2), C0714b.FINISH_SHARE);
                if (!TextUtils.isEmpty(a2)) {
                    aVar.f262d.add(a2);
                }
            }
        } else {
            fVar.f256d = null;
        }
        if (!d) {
            fVar.f257m = null;
        }
    }

    /* renamed from: a */
    private String m168a(String str, C0714b bVar) throws Throwable {
        double ceil;
        if (!TextUtils.isEmpty(str) && new File(str).exists()) {
            String networkType = this.f213c.getNetworkType();
            if (!SchedulerSupport.NONE.equals(networkType) && !TextUtils.isEmpty(networkType)) {
                Bitmap.CompressFormat bmpFormat = BitmapHelper.getBmpFormat(str);
                float f = 200.0f;
                if (bVar == C0714b.BEFORE_SHARE) {
                    f = 600.0f;
                }
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(str, options);
                options.inJustDecodeBounds = false;
                int i = options.outWidth;
                int i2 = options.outHeight;
                if (i >= i2 && ((float) i2) > f) {
                    ceil = Math.ceil((double) (((float) options.outHeight) / f));
                } else if (i >= i2 || ((float) i) <= f) {
                    return m173d(str);
                } else {
                    ceil = Math.ceil((double) (((float) options.outWidth) / f));
                }
                int i3 = (int) ceil;
                if (i3 <= 0) {
                    i3 = 1;
                }
                BitmapFactory.Options options2 = new BitmapFactory.Options();
                options2.inSampleSize = i3;
                options2.inPurgeable = true;
                options2.inInputShareable = true;
                Bitmap decodeFile = BitmapFactory.decodeFile(str, options2);
                decodeFile.getHeight();
                decodeFile.getWidth();
                File createTempFile = File.createTempFile("bm_tmp2", "." + bmpFormat.name().toLowerCase());
                FileOutputStream fileOutputStream = new FileOutputStream(createTempFile);
                decodeFile.compress(bmpFormat, 80, fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
                return m173d(createTempFile.getAbsolutePath());
            }
        }
        return null;
    }

    /* renamed from: d */
    private String m173d(String str) throws Throwable {
        HashMap<String, Object> c = this.f212b.mo10634c(str);
        if (c == null || c.size() <= 0 || !c.containsKey("status") || ResHelper.parseInt(String.valueOf(c.get("status"))) != 200 || !c.containsKey("url")) {
            return null;
        }
        return (String) c.get("url");
    }

    /* renamed from: a */
    private String m167a(Bitmap bitmap, C0714b bVar) throws Throwable {
        File createTempFile = File.createTempFile("bm_tmp", ".png");
        FileOutputStream fileOutputStream = new FileOutputStream(createTempFile);
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
        fileOutputStream.flush();
        fileOutputStream.close();
        return m168a(createTempFile.getAbsolutePath(), bVar);
    }

    /* renamed from: d */
    public void mo10570d() {
        boolean z;
        try {
            String networkType = this.f213c.getNetworkType();
            if (SchedulerSupport.NONE.equals(networkType)) {
                return;
            }
            if (!TextUtils.isEmpty(networkType)) {
                if (this.f214d.mo10609k()) {
                    ArrayList<C0711c> e = this.f212b.mo10637e();
                    for (int i = 0; i < e.size(); i++) {
                        C0711c cVar = e.get(i);
                        if (cVar.f219b.size() == 1) {
                            z = m172a(cVar.f218a, false);
                        } else {
                            z = m172a(m174e(cVar.f218a), true);
                        }
                        if (z) {
                            this.f212b.mo10627a(cVar.f219b);
                        }
                    }
                }
            }
        } catch (Throwable th) {
            SSDKLog.m645b().mo29769d(th);
        }
    }

    /* renamed from: e */
    private String m174e(String str) throws Throwable {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(str.getBytes());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
        byte[] bArr = new byte[1024];
        while (true) {
            int read = byteArrayInputStream.read(bArr, 0, 1024);
            if (read != -1) {
                gZIPOutputStream.write(bArr, 0, read);
            } else {
                gZIPOutputStream.flush();
                gZIPOutputStream.close();
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.flush();
                byteArrayOutputStream.close();
                byteArrayInputStream.close();
                return Base64.encodeToString(byteArray, 2);
            }
        }
    }

    /* renamed from: a */
    private boolean m172a(String str, boolean z) throws Throwable {
        return this.f212b.mo10629a(str, z);
    }

    /* renamed from: a */
    public String mo10562a(String str, int i, boolean z, String str2) {
        String a;
        try {
            if (this.f214d.mo10609k()) {
                if (this.f214d.mo10597e()) {
                    String networkType = this.f213c.getNetworkType();
                    if (!SchedulerSupport.NONE.equals(networkType)) {
                        if (!TextUtils.isEmpty(networkType)) {
                            if (z && (a = m169a(str, "<a[^>]*?href[\\s]*=[\\s]*[\"']?([^'\">]+?)['\"]?>", i, str2)) != null && !a.equals(str)) {
                                return a;
                            }
                            String a2 = m169a(str, "(http://|https://){1}[\\w\\.\\-/:\\?&%=,;\\[\\]\\{\\}`~!@#\\$\\^\\*\\(\\)_\\+\\\\\\|]+", i, str2);
                            return (a2 == null || a2.equals(str)) ? str : a2;
                        }
                    }
                }
            }
        } catch (Throwable th) {
            SSDKLog.m645b().mo29769d(th);
            return str;
        }
    }

    /* renamed from: a */
    private String m169a(String str, String str2, int i, String str3) throws Throwable {
        HashMap<String, Object> a;
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        ArrayList arrayList = new ArrayList();
        Pattern compile = Pattern.compile(str2);
        Matcher matcher = compile.matcher(str);
        while (matcher.find()) {
            String group = matcher.group();
            if (group != null && group.length() > 0) {
                arrayList.add(group);
            }
        }
        if (arrayList.size() == 0 || (a = this.f212b.mo10624a(str, arrayList, i, str3)) == null || a.size() <= 0 || !a.containsKey(ShareConstants.WEB_DIALOG_PARAM_DATA)) {
            return str;
        }
        HashMap hashMap = new HashMap();
        Iterator it = ((ArrayList) a.get(ShareConstants.WEB_DIALOG_PARAM_DATA)).iterator();
        while (it.hasNext()) {
            HashMap hashMap2 = (HashMap) it.next();
            hashMap.put(String.valueOf(hashMap2.get(ShareConstants.FEED_SOURCE_PARAM)), String.valueOf(hashMap2.get("surl")));
        }
        Matcher matcher2 = compile.matcher(str);
        StringBuilder sb = new StringBuilder();
        int i2 = 0;
        while (matcher2.find()) {
            sb.append(str.substring(i2, matcher2.start()));
            sb.append((String) hashMap.get(matcher2.group()));
            i2 = matcher2.end();
        }
        sb.append(str.substring(i2, str.length()));
        String sb2 = sb.toString();
        SSDKLog.m645b().mo29775i("> SERVER_SHORT_LINK_URL content after replace link ===  %s", sb2);
        return sb2;
    }

    /* renamed from: b */
    public String mo10566b(String str) {
        if (!this.f214d.mo10609k()) {
            return null;
        }
        try {
            return m168a(str, C0714b.BEFORE_SHARE);
        } catch (Throwable th) {
            SSDKLog.m645b().mo29769d(th);
            return null;
        }
    }

    /* renamed from: a */
    public String mo10561a(Bitmap bitmap) {
        if (!this.f214d.mo10609k()) {
            return null;
        }
        try {
            return m167a(bitmap, C0714b.BEFORE_SHARE);
        } catch (Throwable th) {
            SSDKLog.m645b().mo29769d(th);
            return null;
        }
    }

    /* renamed from: e */
    public HashMap<String, Object> mo10571e() {
        try {
            return this.f212b.mo10638f();
        } catch (Throwable th) {
            SSDKLog.m645b().mo29769d(th);
            return new HashMap<>();
        }
    }

    /* renamed from: f */
    public HashMap<String, Object> mo10572f() {
        if (!this.f214d.mo10609k() && this.f214d.mo10611m()) {
            return new HashMap<>();
        }
        try {
            HashMap<String, Object> d = this.f212b.mo10635d();
            this.f214d.mo10592c(true);
            return d;
        } catch (Throwable th) {
            this.f214d.mo10592c(false);
            SSDKLog.m645b().mo29769d(th);
            return new HashMap<>();
        }
    }

    /* renamed from: a */
    public void mo10565a(HashMap<String, Object> hashMap) {
        try {
            this.f212b.mo10632b(hashMap);
        } catch (Throwable th) {
            SSDKLog.m645b().mo29769d(th);
        }
    }

    /* renamed from: c */
    public HashMap<String, Object> mo10568c(String str) {
        try {
            return this.f212b.mo10636d(str);
        } catch (Throwable th) {
            SSDKLog.m645b().mo29769d(th);
            return null;
        }
    }
}
