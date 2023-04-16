package com.mob.commons.filesys;

import android.text.TextUtils;
import com.baidu.mobstat.Config;
import com.baidubce.BceConfig;
import com.facebook.appevents.internal.ViewHierarchyConstants;
import com.google.android.gms.fitness.FitnessStatusCodes;
import com.mob.MobSDK;
import com.mob.commons.C2262b;
import com.mob.commons.C2310j;
import com.mob.commons.ForbThrowable;
import com.mob.commons.MobProduct;
import com.mob.commons.authorize.DeviceAuthorizer;
import com.mob.tools.network.KVPair;
import com.mob.tools.network.NetworkHelper;
import com.mob.tools.proguard.PublicMemberKeeper;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.Hashon;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class FileUploader implements PublicMemberKeeper {

    /* renamed from: a */
    private static String f2132a = C2310j.m2574c("http://up.sdk.mob.com");

    public static void setUploadServer(String str) {
        if (str.endsWith(BceConfig.BOS_DELIMITER)) {
            str = str.substring(0, str.length() - 1);
        }
        f2132a = C2310j.m2574c(str);
    }

    public static UploadedImage uploadImage(MobProduct mobProduct, String str) throws Throwable {
        return uploadImage(mobProduct, str, false, new int[0]);
    }

    /* renamed from: a */
    private static String m2481a() {
        return f2132a + "/image";
    }

    public static UploadedImage uploadImage(MobProduct mobProduct, String str, boolean z, int... iArr) throws Throwable {
        String str2 = null;
        if (iArr != null && iArr.length > 0) {
            String str3 = "";
            for (int i : iArr) {
                str3 = str3 + "," + i;
            }
            if (str3.length() > 0) {
                str2 = str3.substring(1);
            }
        }
        return new UploadedImage(m2486a(mobProduct, str, z, str2, m2481a()));
    }

    public static UploadedAvatar uploadAvatar(MobProduct mobProduct, String str) throws Throwable {
        return uploadAvatar(mobProduct, str, false, new int[0]);
    }

    /* renamed from: b */
    private static String m2487b() {
        return f2132a + "/avatar";
    }

    public static UploadedAvatar uploadAvatar(MobProduct mobProduct, String str, boolean z, int... iArr) throws Throwable {
        String str2 = null;
        if (iArr != null && iArr.length > 0) {
            String str3 = "";
            for (int i : iArr) {
                str3 = str3 + "," + i;
            }
            if (str3.length() > 0) {
                str2 = str3.substring(1);
            }
        }
        return new UploadedAvatar(m2486a(mobProduct, str, z, str2, m2487b()));
    }

    /* renamed from: c */
    private static String m2488c() {
        return f2132a + "/video";
    }

    public static UploadedVideo uploadVideo(MobProduct mobProduct, String str, boolean z) throws Throwable {
        HashMap hashMap = new HashMap();
        hashMap.put("sm", Integer.valueOf(z ? 2 : 1));
        return new UploadedVideo(m2485a(mobProduct, str, (HashMap<String, Object>) hashMap, m2488c(), 209715200));
    }

    /* renamed from: d */
    private static String m2489d() {
        return f2132a + "/audio";
    }

    public static UploadedAudio uploadAudio(MobProduct mobProduct, String str, boolean z) throws Throwable {
        HashMap hashMap = new HashMap();
        hashMap.put("sm", Integer.valueOf(z ? 2 : 1));
        return new UploadedAudio(m2485a(mobProduct, str, (HashMap<String, Object>) hashMap, m2489d(), 209715200));
    }

    /* renamed from: e */
    private static String m2490e() {
        return f2132a + "/file";
    }

    public static UploadedFile uploadFile(MobProduct mobProduct, String str, boolean z) throws Throwable {
        HashMap hashMap = new HashMap();
        hashMap.put("sm", Integer.valueOf(z ? 2 : 1));
        return new UploadedFile(m2485a(mobProduct, str, (HashMap<String, Object>) hashMap, m2490e(), (long) Config.RAVEN_LOG_LIMIT));
    }

    /* renamed from: a */
    private static Throwable m2483a(long j, long j2) {
        return new Throwable("{\"status\": ,\"error\":\"max size: " + j2 + ", file size: " + j + "\"}");
    }

    /* renamed from: a */
    private static HashMap<String, Object> m2486a(MobProduct mobProduct, String str, boolean z, String str2, String str3) throws Throwable {
        HashMap hashMap = new HashMap();
        hashMap.put("sm", Integer.valueOf(z ? 2 : 1));
        if (!TextUtils.isEmpty(str2)) {
            hashMap.put("zoomScaleWidths", str2);
        }
        return m2485a(mobProduct, str, (HashMap<String, Object>) hashMap, str3, (long) Config.FULL_TRACE_LOG_LIMIT);
    }

    /* renamed from: a */
    private static HashMap<String, Object> m2485a(MobProduct mobProduct, String str, HashMap<String, Object> hashMap, String str2, long j) throws Throwable {
        if (!C2262b.m2276aa()) {
            File file = new File(str);
            if (file.length() <= j) {
                NetworkHelper networkHelper = new NetworkHelper();
                ArrayList<KVPair<String>> a = m2484a(mobProduct, m2482a(mobProduct));
                ArrayList arrayList = new ArrayList();
                for (String next : hashMap.keySet()) {
                    arrayList.add(new KVPair(next, String.valueOf(hashMap.get(next))));
                }
                KVPair kVPair = new KVPair("file", str);
                NetworkHelper.NetworkTimeOut networkTimeOut = new NetworkHelper.NetworkTimeOut();
                networkTimeOut.readTimout = 30000;
                networkTimeOut.connectionTimeout = FitnessStatusCodes.NEEDS_OAUTH_PERMISSIONS;
                String httpPost = networkHelper.httpPost(str2, (ArrayList<KVPair<String>>) arrayList, (KVPair<String>) kVPair, a, networkTimeOut);
                HashMap fromJson = new Hashon().fromJson(httpPost);
                if (fromJson != null && "200".equals(String.valueOf(fromJson.get("status")))) {
                    return (HashMap) fromJson.get("res");
                }
                throw new Throwable(httpPost);
            }
            throw m2483a(file.length(), j);
        }
        throw new ForbThrowable();
    }

    /* renamed from: f */
    private static String m2491f() {
        return f2132a + "/getToken";
    }

    /* renamed from: a */
    private static String m2482a(MobProduct mobProduct) throws Throwable {
        NetworkHelper networkHelper = new NetworkHelper();
        ArrayList<KVPair<String>> a = m2484a(mobProduct, "1234567890abcdeffedcba0987654321");
        NetworkHelper.NetworkTimeOut networkTimeOut = new NetworkHelper.NetworkTimeOut();
        networkTimeOut.readTimout = 30000;
        networkTimeOut.connectionTimeout = FitnessStatusCodes.NEEDS_OAUTH_PERMISSIONS;
        String httpPost = networkHelper.httpPost(m2491f(), (ArrayList<KVPair<String>>) null, (KVPair<String>) null, a, networkTimeOut);
        HashMap fromJson = new Hashon().fromJson(httpPost);
        if (fromJson == null || !"200".equals(String.valueOf(fromJson.get("status")))) {
            throw new Throwable(httpPost);
        }
        try {
            return (String) ((HashMap) fromJson.get("res")).get("token");
        } catch (Throwable th) {
            throw new Throwable(httpPost, th);
        }
    }

    /* renamed from: a */
    private static ArrayList<KVPair<String>> m2484a(MobProduct mobProduct, String str) {
        ArrayList<KVPair<String>> arrayList = new ArrayList<>();
        arrayList.add(new KVPair("sign", Data.MD5(str + MobSDK.getAppSecret())));
        arrayList.add(new KVPair("key", MobSDK.getAppkey()));
        arrayList.add(new KVPair("token", str));
        arrayList.add(new KVPair("product", mobProduct.getProductTag()));
        arrayList.add(new KVPair("cliid", DeviceAuthorizer.authorize(mobProduct)));
        return arrayList;
    }

    public static class UploadedFile implements PublicMemberKeeper {

        /* renamed from: id */
        public final String f2133id;
        public final String url;

        public UploadedFile(HashMap<String, Object> hashMap) {
            if (hashMap.containsKey("src")) {
                this.url = (String) hashMap.get("src");
            } else {
                this.url = null;
            }
            if (hashMap.containsKey("id")) {
                this.f2133id = (String) hashMap.get("id");
            } else {
                this.f2133id = null;
            }
        }
    }

    public static class UploadedImage extends UploadedFile {
        public final HashMap<String, String> zoomList;

        public UploadedImage(HashMap<String, Object> hashMap) {
            super(hashMap);
            HashMap<String, String> hashMap2;
            try {
                hashMap2 = (HashMap) hashMap.get("list");
            } catch (Throwable unused) {
                hashMap2 = null;
            }
            this.zoomList = hashMap2;
        }
    }

    public static class UploadedVideo extends UploadedFile {
        public final int durationUSec;
        public final int height;
        public final int width;

        public UploadedVideo(HashMap<String, Object> hashMap) {
            super(hashMap);
            int i;
            int i2;
            int i3 = -1;
            try {
                i2 = Integer.parseInt(String.valueOf(hashMap.get("time")));
                try {
                    i = Integer.parseInt(String.valueOf(hashMap.get(ViewHierarchyConstants.DIMENSION_HEIGHT_KEY)));
                    try {
                        i3 = Integer.parseInt(String.valueOf(hashMap.get(ViewHierarchyConstants.DIMENSION_WIDTH_KEY)));
                    } catch (Throwable unused) {
                    }
                } catch (Throwable unused2) {
                    i = -1;
                    this.durationUSec = i2;
                    this.width = i3;
                    this.height = i;
                }
            } catch (Throwable unused3) {
                i2 = -1;
                i = -1;
                this.durationUSec = i2;
                this.width = i3;
                this.height = i;
            }
            this.durationUSec = i2;
            this.width = i3;
            this.height = i;
        }
    }

    public static class UploadedAudio extends UploadedFile {
        public final int durationUSec;

        public UploadedAudio(HashMap<String, Object> hashMap) {
            super(hashMap);
            int i;
            try {
                i = Integer.parseInt(String.valueOf(hashMap.get("time")));
            } catch (Throwable unused) {
                i = -1;
            }
            this.durationUSec = i;
        }
    }

    public static class UploadedAvatar extends UploadedImage {
        public UploadedAvatar(HashMap<String, Object> hashMap) {
            super(hashMap);
        }
    }
}
