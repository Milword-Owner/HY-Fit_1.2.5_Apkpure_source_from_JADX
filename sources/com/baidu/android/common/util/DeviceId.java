package com.baidu.android.common.util;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Process;
import android.os.SystemClock;
import android.provider.Settings;
import android.system.ErrnoException;
import android.system.Os;
import android.text.TextUtils;
import android.util.Log;
import com.baidu.android.common.security.AESUtil;
import com.baidu.android.common.security.Base64;
import com.baidu.android.common.security.MD5Util;
import com.baidu.android.common.security.SHA1Util;
import com.hjq.permissions.Permission;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import javax.crypto.Cipher;
import kotlin.UByte;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class DeviceId {
    private static final String ACTION_GLAXY_CUID = "com.baidu.intent.action.GALAXY";
    private static final String AES_KEY;
    private static final boolean CONFIG_WRITE_V1_STORAGE = false;
    private static final boolean DEBUG = false;
    private static final String DEFAULT_TM_DEVICEID = "";
    private static final String EXT_DIR = "backups/.SystemConfig";
    private static final String EXT_FILE = ".cuid";
    private static final String EXT_FILE_V2 = ".cuid2";
    private static final String KEY_DEVICE_ID = "com.baidu.deviceid";
    private static final String KEY_DEVICE_ID_V2 = "com.baidu.deviceid.v2";
    private static final String KEY_FLAG = "bd_setting_i";
    private static final String META_KEY_GALAXY_SF = "galaxy_sf";
    private static final String META_KEY_GLAXY_DATA = "galaxy_data";
    private static final String OLD_EXT_DIR = "baidu";
    private static final String RSA_ALGORITHM = "RSA/ECB/PKCS1Padding";
    private static final int SDK_ANDROID_M = 23;
    private static final String SELF_CUID_FILE = "libcuid.so";
    private static final int STORAGE_SDCARD_V1 = 4;
    private static final int STORAGE_SDCARD_V2 = 8;
    private static final int STORAGE_SELF_FILE = 16;
    private static final int STORAGE_SYSTEM_SETTING_V1 = 1;
    private static final int STORAGE_SYSTEM_SETTING_V2 = 2;
    private static final int S_IRGRP = 32;
    private static final int S_IROTH = 4;
    private static final int S_IRUSR = 256;
    private static final int S_IRWXG = 56;
    private static final int S_IRWXO = 7;
    private static final int S_IRWXU = 448;
    private static final int S_IWGRP = 16;
    private static final int S_IWOTH = 2;
    private static final int S_IWUSR = 128;
    private static final int S_IXGRP = 8;
    private static final int S_IXOTH = 1;
    private static final int S_IXUSR = 64;
    private static final String TAG = "DeviceId";
    private static CUIDInfo sCachedCuidInfo = null;
    private static final String sDEncoded = "ZGV2aWNlaWQ=";
    private static boolean sDataCuidInfoShable = true;
    private static final String sIEncoded = "aW1laQ==";
    private static final String sVEncoded = "dmVy";
    /* access modifiers changed from: private */
    public final Context mContext;
    private PublicKey mPublicKey;
    private int mSaveMask = 0;

    /* access modifiers changed from: private */
    public String getDefaultFlag(String str) {
        return "0";
    }

    /* access modifiers changed from: private */
    public static void handleThrowable(Throwable th) {
    }

    static {
        String str = new String(Base64.decode(new byte[]{77, 122, 65, 121, 77, 84, 73, 120, 77, 68, 73, 61}));
        String str2 = new String(Base64.decode(new byte[]{90, 71, 108, 106, 100, 87, 82, 112, 89, 87, 73, 61}));
        AES_KEY = str + str2;
    }

    private DeviceId(Context context) {
        this.mContext = context.getApplicationContext();
        initPublicKey();
    }

    private static String byte2hex(byte[] bArr) {
        if (bArr != null) {
            String str = "";
            for (byte b : bArr) {
                String hexString = Integer.toHexString(b & UByte.MAX_VALUE);
                if (hexString.length() == 1) {
                    str = str + "0" + hexString;
                } else {
                    str = str + hexString;
                }
            }
            return str.toLowerCase();
        }
        throw new IllegalArgumentException("Argument b ( byte array ) is null! ");
    }

    public static void setCuidDataShable(Context context, boolean z) {
        File file = new File(context.getApplicationContext().getFilesDir(), SELF_CUID_FILE);
        Context applicationContext = context.getApplicationContext();
        if (file.exists()) {
            if (sCachedCuidInfo == null) {
                tryToModifyChmodForSelfFile(applicationContext, z);
            } else if (sDataCuidInfoShable != z) {
                tryToModifyChmodForSelfFile(applicationContext, z);
            }
        }
        sDataCuidInfoShable = z;
    }

    private String[] formatAndroidSigArray(Signature[] signatureArr) {
        String[] strArr = new String[signatureArr.length];
        for (int i = 0; i < strArr.length; i++) {
            strArr[i] = byte2hex(SHA1Util.sha1(signatureArr[i].toByteArray()));
        }
        return strArr;
    }

    private static byte[] decryptByPublicKey(byte[] bArr, PublicKey publicKey) throws Exception {
        Cipher instance = Cipher.getInstance(RSA_ALGORITHM);
        instance.init(2, publicKey);
        return instance.doFinal(bArr);
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0028 A[SYNTHETIC, Splitter:B:13:0x0028] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0034 A[SYNTHETIC, Splitter:B:21:0x0034] */
    /* JADX WARNING: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void initPublicKey() {
        /*
            r4 = this;
            r0 = 0
            java.io.ByteArrayInputStream r1 = new java.io.ByteArrayInputStream     // Catch:{ Exception -> 0x0031, all -> 0x0022 }
            byte[] r2 = com.baidu.android.common.util.CuidCertStore.getCertBytes()     // Catch:{ Exception -> 0x0031, all -> 0x0022 }
            r1.<init>(r2)     // Catch:{ Exception -> 0x0031, all -> 0x0022 }
            java.lang.String r0 = "X.509"
            java.security.cert.CertificateFactory r0 = java.security.cert.CertificateFactory.getInstance(r0)     // Catch:{ Exception -> 0x0020, all -> 0x001e }
            java.security.cert.Certificate r0 = r0.generateCertificate(r1)     // Catch:{ Exception -> 0x0020, all -> 0x001e }
            java.security.PublicKey r0 = r0.getPublicKey()     // Catch:{ Exception -> 0x0020, all -> 0x001e }
            r4.mPublicKey = r0     // Catch:{ Exception -> 0x0020, all -> 0x001e }
            r1.close()     // Catch:{ Exception -> 0x0038 }
            goto L_0x003c
        L_0x001e:
            r0 = move-exception
            goto L_0x0026
        L_0x0020:
            goto L_0x0032
        L_0x0022:
            r1 = move-exception
            r3 = r1
            r1 = r0
            r0 = r3
        L_0x0026:
            if (r1 == 0) goto L_0x0030
            r1.close()     // Catch:{ Exception -> 0x002c }
            goto L_0x0030
        L_0x002c:
            r1 = move-exception
            handleThrowable(r1)
        L_0x0030:
            throw r0
        L_0x0031:
            r1 = r0
        L_0x0032:
            if (r1 == 0) goto L_0x003c
            r1.close()     // Catch:{ Exception -> 0x0038 }
            goto L_0x003c
        L_0x0038:
            r0 = move-exception
            handleThrowable(r0)
        L_0x003c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.common.util.DeviceId.initPublicKey():void");
    }

    private List<CUIDBuddyInfo> collectBuddyInfos(Intent intent, boolean z) {
        ArrayList arrayList = new ArrayList();
        PackageManager packageManager = this.mContext.getPackageManager();
        List<ResolveInfo> queryBroadcastReceivers = packageManager.queryBroadcastReceivers(intent, 0);
        if (queryBroadcastReceivers != null) {
            for (ResolveInfo next : queryBroadcastReceivers) {
                if (!(next.activityInfo == null || next.activityInfo.applicationInfo == null)) {
                    try {
                        Bundle bundle = packageManager.getReceiverInfo(new ComponentName(next.activityInfo.packageName, next.activityInfo.name), 128).metaData;
                        if (bundle != null) {
                            String string = bundle.getString(META_KEY_GLAXY_DATA);
                            if (!TextUtils.isEmpty(string)) {
                                byte[] decode = Base64.decode(string.getBytes("utf-8"));
                                JSONObject jSONObject = new JSONObject(new String(decode));
                                CUIDBuddyInfo cUIDBuddyInfo = new CUIDBuddyInfo();
                                cUIDBuddyInfo.priority = jSONObject.getInt("priority");
                                cUIDBuddyInfo.appInfo = next.activityInfo.applicationInfo;
                                if (this.mContext.getPackageName().equals(next.activityInfo.applicationInfo.packageName)) {
                                    cUIDBuddyInfo.isSelf = true;
                                }
                                if (z) {
                                    String string2 = bundle.getString(META_KEY_GALAXY_SF);
                                    if (!TextUtils.isEmpty(string2)) {
                                        PackageInfo packageInfo = packageManager.getPackageInfo(next.activityInfo.applicationInfo.packageName, 64);
                                        JSONArray jSONArray = jSONObject.getJSONArray("sigs");
                                        String[] strArr = new String[jSONArray.length()];
                                        for (int i = 0; i < strArr.length; i++) {
                                            strArr[i] = jSONArray.getString(i);
                                        }
                                        if (isSigsEqual(strArr, formatAndroidSigArray(packageInfo.signatures))) {
                                            byte[] decryptByPublicKey = decryptByPublicKey(Base64.decode(string2.getBytes()), this.mPublicKey);
                                            if (decryptByPublicKey != null && Arrays.equals(decryptByPublicKey, SHA1Util.sha1(decode))) {
                                                cUIDBuddyInfo.sigMatched = true;
                                            }
                                        }
                                    }
                                }
                                arrayList.add(cUIDBuddyInfo);
                            }
                        }
                    } catch (Exception unused) {
                    }
                }
            }
        }
        Collections.sort(arrayList, new Comparator<CUIDBuddyInfo>() {
            public int compare(CUIDBuddyInfo cUIDBuddyInfo, CUIDBuddyInfo cUIDBuddyInfo2) {
                int i = cUIDBuddyInfo2.priority - cUIDBuddyInfo.priority;
                if (i == 0) {
                    if (cUIDBuddyInfo.isSelf && cUIDBuddyInfo2.isSelf) {
                        return 0;
                    }
                    if (cUIDBuddyInfo.isSelf) {
                        return -1;
                    }
                    if (cUIDBuddyInfo2.isSelf) {
                        return 1;
                    }
                }
                return i;
            }
        });
        return arrayList;
    }

    private boolean isSigsEqual(String[] strArr, String[] strArr2) {
        if (strArr == null || strArr2 == null || strArr.length != strArr2.length) {
            return false;
        }
        HashSet hashSet = new HashSet();
        for (String add : strArr) {
            hashSet.add(add);
        }
        HashSet hashSet2 = new HashSet();
        for (String add2 : strArr2) {
            hashSet2.add(add2);
        }
        return hashSet.equals(hashSet2);
    }

    static class CUIDBuddyInfo {
        public ApplicationInfo appInfo;
        public boolean isSelf;
        public int priority;
        public boolean sigMatched;

        private CUIDBuddyInfo() {
            this.priority = 0;
            this.sigMatched = false;
            this.isSelf = false;
        }
    }

    /* access modifiers changed from: private */
    @SuppressLint({"NewApi"})
    public boolean writeToCuidFile(String str) {
        int i = (!sDataCuidInfoShable || Build.VERSION.SDK_INT >= 24) ? 0 : 1;
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = this.mContext.openFileOutput(SELF_CUID_FILE, i);
            fileOutputStream.write(str.getBytes());
            fileOutputStream.flush();
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (Exception e) {
                    handleThrowable(e);
                }
            }
            if (Build.VERSION.SDK_INT >= 21) {
                if (i == 0 && sDataCuidInfoShable) {
                    return TargetApiSupport.doChmodSafely(new File(this.mContext.getFilesDir(), SELF_CUID_FILE).getAbsolutePath(), 436);
                }
                if (!sDataCuidInfoShable) {
                    return TargetApiSupport.doChmodSafely(new File(this.mContext.getFilesDir(), SELF_CUID_FILE).getAbsolutePath(), 432);
                }
            }
            return true;
        } catch (Exception e2) {
            handleThrowable(e2);
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (Exception e3) {
                    handleThrowable(e3);
                }
            }
            return false;
        } catch (Throwable th) {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (Exception e4) {
                    handleThrowable(e4);
                }
            }
            throw th;
        }
    }

    @SuppressLint({"NewApi"})
    private static boolean tryToModifyChmodForSelfFile(Context context, boolean z) {
        File file = new File(context.getApplicationContext().getFilesDir(), SELF_CUID_FILE);
        if (!file.exists()) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= 21) {
            return TargetApiSupport.doChmodSafely(file.getAbsolutePath(), z ? 436 : 432);
        } else if (z) {
            try {
                return file.setReadable(true, false);
            } catch (Exception e) {
                handleThrowable(e);
                return false;
            }
        } else {
            boolean readable = file.setReadable(false, false);
            boolean readable2 = file.setReadable(true, true);
            if (!readable || !readable2) {
                return false;
            }
            return true;
        }
    }

    static class TargetApiSupport {
        TargetApiSupport() {
        }

        static boolean doChmodSafely(String str, int i) {
            try {
                Os.chmod(str, i);
                return true;
            } catch (ErrnoException e) {
                DeviceId.handleThrowable(e);
                return false;
            } catch (Exception e2) {
                DeviceId.handleThrowable(e2);
                return false;
            }
        }
    }

    /* access modifiers changed from: private */
    public String getSystemSettingValue(String str) {
        try {
            return Settings.System.getString(this.mContext.getContentResolver(), str);
        } catch (Exception e) {
            handleThrowable(e);
            return null;
        }
    }

    /* access modifiers changed from: private */
    public boolean tryPutSystemSettingValue(String str, String str2) {
        try {
            return Settings.System.putString(this.mContext.getContentResolver(), str, str2);
        } catch (Exception e) {
            handleThrowable(e);
            return false;
        }
    }

    static class CUIDInfo {
        public static final String I_EMPTY = "0";
        public static final String I_FIXED = "O";
        public static final int PROTOCAL_MAX_LENGTH = 14;
        private static final int VERSION_DEF = 2;
        public String deviceId;
        public String flag;
        public int oldValueLength;
        public int version;

        public static boolean isIENormal(int i) {
            return i >= 14;
        }

        private CUIDInfo() {
            this.version = 2;
            this.oldValueLength = 0;
        }

        public boolean isIENull() {
            return isIENull(this.flag);
        }

        public boolean isIENormal() {
            return isIENormal(this.oldValueLength);
        }

        public static boolean isIENull(String str) {
            return TextUtils.isEmpty(str);
        }

        public static CUIDInfo createFromJson(String str) {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            try {
                JSONObject jSONObject = new JSONObject(str);
                Iterator<String> keys = jSONObject.keys();
                String str2 = "0";
                String str3 = str2;
                while (keys.hasNext()) {
                    String next = keys.next();
                    if (!DeviceId.getBase64DecodeStr(DeviceId.sDEncoded).equals(next)) {
                        if (!DeviceId.getBase64DecodeStr(DeviceId.sVEncoded).equals(next)) {
                            str3 = jSONObject.optString(next, str2);
                        }
                    }
                }
                String string = jSONObject.getString(DeviceId.getBase64DecodeStr(DeviceId.sDEncoded));
                int i = jSONObject.getInt(DeviceId.getBase64DecodeStr(DeviceId.sVEncoded));
                int length = TextUtils.isEmpty(str3) ? 0 : str3.length();
                if (!TextUtils.isEmpty(string)) {
                    CUIDInfo cUIDInfo = new CUIDInfo();
                    cUIDInfo.deviceId = string;
                    cUIDInfo.version = i;
                    cUIDInfo.oldValueLength = length;
                    if (cUIDInfo.oldValueLength < 14) {
                        if (!TextUtils.isEmpty(str3)) {
                            str2 = str3;
                        }
                        cUIDInfo.flag = str2;
                    }
                    return cUIDInfo;
                }
            } catch (JSONException e) {
                DeviceId.handleThrowable(e);
            }
            return null;
        }

        /* access modifiers changed from: private */
        public static CUIDInfo createCuidInfoFromV1Cache(String str, String str2) {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            CUIDInfo cUIDInfo = new CUIDInfo();
            cUIDInfo.deviceId = str;
            cUIDInfo.oldValueLength = TextUtils.isEmpty(str2) ? 0 : str2.length();
            if (cUIDInfo.oldValueLength < 14) {
                if (TextUtils.isEmpty(str2)) {
                    str2 = "0";
                }
                cUIDInfo.flag = str2;
            }
            return cUIDInfo;
        }

        public String toPersitString() {
            try {
                return new JSONObject().put(DeviceId.getBase64DecodeStr(DeviceId.sDEncoded), this.deviceId).put(DeviceId.getBase64DecodeStr(DeviceId.sIEncoded), this.flag).put(DeviceId.getBase64DecodeStr(DeviceId.sVEncoded), this.version).toString();
            } catch (JSONException e) {
                DeviceId.handleThrowable(e);
                return null;
            }
        }

        public String getFinalCUID() {
            String str = this.flag;
            if (TextUtils.isEmpty(str)) {
                str = "0";
            }
            return this.deviceId + "|" + str;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0021 A[SYNTHETIC, Splitter:B:18:0x0021] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x002b A[SYNTHETIC, Splitter:B:24:0x002b] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0036 A[SYNTHETIC, Splitter:B:29:0x0036] */
    /* JADX WARNING: Removed duplicated region for block: B:35:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:36:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:21:0x0026=Splitter:B:21:0x0026, B:15:0x001c=Splitter:B:15:0x001c} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void writeToFile(java.io.File r2, byte[] r3) {
        /*
            r0 = 0
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x0025, SecurityException -> 0x001b }
            r1.<init>(r2)     // Catch:{ IOException -> 0x0025, SecurityException -> 0x001b }
            r1.write(r3)     // Catch:{ IOException -> 0x0016, SecurityException -> 0x0013, all -> 0x0010 }
            r1.flush()     // Catch:{ IOException -> 0x0016, SecurityException -> 0x0013, all -> 0x0010 }
            r1.close()     // Catch:{ IOException -> 0x002f }
            goto L_0x0033
        L_0x0010:
            r2 = move-exception
            r0 = r1
            goto L_0x0034
        L_0x0013:
            r2 = move-exception
            r0 = r1
            goto L_0x001c
        L_0x0016:
            r2 = move-exception
            r0 = r1
            goto L_0x0026
        L_0x0019:
            r2 = move-exception
            goto L_0x0034
        L_0x001b:
            r2 = move-exception
        L_0x001c:
            handleThrowable(r2)     // Catch:{ all -> 0x0019 }
            if (r0 == 0) goto L_0x0033
            r0.close()     // Catch:{ IOException -> 0x002f }
            goto L_0x0033
        L_0x0025:
            r2 = move-exception
        L_0x0026:
            handleThrowable(r2)     // Catch:{ all -> 0x0019 }
            if (r0 == 0) goto L_0x0033
            r0.close()     // Catch:{ IOException -> 0x002f }
            goto L_0x0033
        L_0x002f:
            r2 = move-exception
            handleThrowable(r2)
        L_0x0033:
            return
        L_0x0034:
            if (r0 == 0) goto L_0x003e
            r0.close()     // Catch:{ IOException -> 0x003a }
            goto L_0x003e
        L_0x003a:
            r3 = move-exception
            handleThrowable(r3)
        L_0x003e:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.common.util.DeviceId.writeToFile(java.io.File, byte[]):void");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0033 A[SYNTHETIC, Splitter:B:23:0x0033] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x003f A[SYNTHETIC, Splitter:B:30:0x003f] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getFileContent(java.io.File r5) {
        /*
            r0 = 0
            java.io.FileReader r1 = new java.io.FileReader     // Catch:{ Exception -> 0x002c, all -> 0x0029 }
            r1.<init>(r5)     // Catch:{ Exception -> 0x002c, all -> 0x0029 }
            r5 = 8192(0x2000, float:1.14794E-41)
            char[] r5 = new char[r5]     // Catch:{ Exception -> 0x0027 }
            java.io.CharArrayWriter r2 = new java.io.CharArrayWriter     // Catch:{ Exception -> 0x0027 }
            r2.<init>()     // Catch:{ Exception -> 0x0027 }
        L_0x000f:
            int r3 = r1.read(r5)     // Catch:{ Exception -> 0x0027 }
            if (r3 <= 0) goto L_0x001a
            r4 = 0
            r2.write(r5, r4, r3)     // Catch:{ Exception -> 0x0027 }
            goto L_0x000f
        L_0x001a:
            java.lang.String r5 = r2.toString()     // Catch:{ Exception -> 0x0027 }
            r1.close()     // Catch:{ Exception -> 0x0022 }
            goto L_0x0026
        L_0x0022:
            r0 = move-exception
            handleThrowable(r0)
        L_0x0026:
            return r5
        L_0x0027:
            r5 = move-exception
            goto L_0x002e
        L_0x0029:
            r5 = move-exception
            r1 = r0
            goto L_0x003d
        L_0x002c:
            r5 = move-exception
            r1 = r0
        L_0x002e:
            handleThrowable(r5)     // Catch:{ all -> 0x003c }
            if (r1 == 0) goto L_0x003b
            r1.close()     // Catch:{ Exception -> 0x0037 }
            goto L_0x003b
        L_0x0037:
            r5 = move-exception
            handleThrowable(r5)
        L_0x003b:
            return r0
        L_0x003c:
            r5 = move-exception
        L_0x003d:
            if (r1 == 0) goto L_0x0047
            r1.close()     // Catch:{ Exception -> 0x0043 }
            goto L_0x0047
        L_0x0043:
            r0 = move-exception
            handleThrowable(r0)
        L_0x0047:
            goto L_0x0049
        L_0x0048:
            throw r5
        L_0x0049:
            goto L_0x0048
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.common.util.DeviceId.getFileContent(java.io.File):java.lang.String");
    }

    public static String getCUID(Context context) {
        return getOrCreateCUIDInfo(context).getFinalCUID();
    }

    private static CUIDInfo getOrCreateCUIDInfo(Context context) {
        if (sCachedCuidInfo == null) {
            synchronized (CUIDInfo.class) {
                if (sCachedCuidInfo == null) {
                    SystemClock.uptimeMillis();
                    sCachedCuidInfo = new DeviceId(context).getCUIDInfo();
                    SystemClock.uptimeMillis();
                }
            }
        }
        return sCachedCuidInfo;
    }

    public static String getDeviceID(Context context) {
        return getOrCreateCUIDInfo(context).deviceId;
    }

    private static String getAndroidId(Context context) {
        String string = Settings.Secure.getString(context.getContentResolver(), "android_id");
        return TextUtils.isEmpty(string) ? "" : string;
    }

    private CUIDInfo getCUIDInfo() {
        boolean z;
        String str;
        String str2;
        List<CUIDBuddyInfo> collectBuddyInfos = collectBuddyInfos(new Intent(ACTION_GLAXY_CUID).setPackage(this.mContext.getPackageName()), true);
        boolean z2 = false;
        if (collectBuddyInfos == null || collectBuddyInfos.size() == 0) {
            for (int i = 0; i < 3; i++) {
                Log.w(TAG, "galaxy lib host missing meta-data,make sure you know the right way to integrate galaxy");
            }
            z = false;
        } else {
            CUIDBuddyInfo cUIDBuddyInfo = collectBuddyInfos.get(0);
            z = cUIDBuddyInfo.sigMatched;
            if (!cUIDBuddyInfo.sigMatched) {
                for (int i2 = 0; i2 < 3; i2++) {
                    Log.w(TAG, "galaxy config err, In the release version of the signature should be matched");
                }
            }
        }
        File file = new File(this.mContext.getFilesDir(), SELF_CUID_FILE);
        CUIDInfo createFromJson = file.exists() ? CUIDInfo.createFromJson(decryptCUIDInfo(getFileContent(file))) : null;
        if (createFromJson == null) {
            this.mSaveMask |= 16;
            List<CUIDBuddyInfo> collectBuddyInfos2 = collectBuddyInfos(new Intent(ACTION_GLAXY_CUID), z);
            if (collectBuddyInfos2 != null) {
                String str3 = "files";
                File filesDir = this.mContext.getFilesDir();
                if (!str3.equals(filesDir.getName())) {
                    Log.e(TAG, "fetal error:: app files dir name is unexpectedly :: " + filesDir.getAbsolutePath());
                    str3 = filesDir.getName();
                }
                for (CUIDBuddyInfo next : collectBuddyInfos2) {
                    if (!next.isSelf) {
                        File file2 = new File(new File(next.appInfo.dataDir, str3), SELF_CUID_FILE);
                        if (file2.exists() && (createFromJson = CUIDInfo.createFromJson(decryptCUIDInfo(getFileContent(file2)))) != null) {
                            break;
                        }
                    }
                }
            }
        }
        if (createFromJson == null) {
            createFromJson = CUIDInfo.createFromJson(decryptCUIDInfo(getSystemSettingValue(KEY_DEVICE_ID_V2)));
        }
        boolean checkSelfPermission = checkSelfPermission(Permission.READ_EXTERNAL_STORAGE);
        if (createFromJson == null && checkSelfPermission) {
            this.mSaveMask |= 2;
            createFromJson = getCuidInfoFromExternalV2();
        }
        if (createFromJson == null) {
            this.mSaveMask |= 8;
            createFromJson = getCUidInfoFromSystemSettingV1();
        }
        if (createFromJson != null || !checkSelfPermission) {
            str = null;
        } else {
            this.mSaveMask |= 1;
            String defaultFlag = getDefaultFlag("");
            z2 = true;
            str = defaultFlag;
            createFromJson = getExternalV1DeviceId(defaultFlag);
        }
        if (createFromJson == null) {
            this.mSaveMask |= 4;
            if (!z2) {
                str = getDefaultFlag("");
            }
            createFromJson = new CUIDInfo();
            String androidId = getAndroidId(this.mContext);
            if (Build.VERSION.SDK_INT < 23) {
                str2 = str + androidId + UUID.randomUUID().toString();
            } else {
                str2 = "com.baidu" + androidId;
            }
            createFromJson.deviceId = MD5Util.toMd5(str2.getBytes(), true);
            createFromJson.flag = str;
        }
        fixCUIDInfoByIE(createFromJson);
        writeJobThread(createFromJson);
        return createFromJson;
    }

    private synchronized void writeJobThread(CUIDInfo cUIDInfo) {
        new Thread(getWriteRunnable(cUIDInfo)).start();
    }

    /* access modifiers changed from: private */
    public boolean fixCUIDInfoByIE(CUIDInfo cUIDInfo) {
        if (cUIDInfo.isIENormal()) {
            cUIDInfo.flag = CUIDInfo.I_FIXED;
            return true;
        } else if (!cUIDInfo.isIENull()) {
            return false;
        } else {
            cUIDInfo.flag = "0";
            return true;
        }
    }

    private Runnable getWriteRunnable(final CUIDInfo cUIDInfo) {
        return new Runnable() {
            public void run() {
                CUIDInfo access$1700;
                CUIDInfo cUIDInfo = new CUIDInfo();
                cUIDInfo.flag = cUIDInfo.flag;
                cUIDInfo.deviceId = cUIDInfo.deviceId;
                File file = new File(DeviceId.this.mContext.getFilesDir(), DeviceId.SELF_CUID_FILE);
                String access$500 = DeviceId.encryptCUIDInfo(cUIDInfo.toPersitString());
                if (!file.exists()) {
                    boolean unused = DeviceId.this.writeToCuidFile(access$500);
                } else {
                    CUIDInfo createFromJson = CUIDInfo.createFromJson(DeviceId.decryptCUIDInfo(DeviceId.getFileContent(file)));
                    if (createFromJson != null) {
                        if (DeviceId.this.fixCUIDInfoByIE(createFromJson)) {
                            boolean unused2 = DeviceId.this.writeToCuidFile(DeviceId.encryptCUIDInfo(createFromJson.toPersitString()));
                        }
                    } else if (createFromJson == null) {
                        boolean unused3 = DeviceId.this.writeToCuidFile(access$500);
                    }
                }
                boolean access$1000 = DeviceId.this.hasWriteSettingsPermission();
                if (access$1000) {
                    String access$1100 = DeviceId.this.getSystemSettingValue(DeviceId.KEY_DEVICE_ID_V2);
                    if (TextUtils.isEmpty(access$1100)) {
                        boolean unused4 = DeviceId.this.tryPutSystemSettingValue(DeviceId.KEY_DEVICE_ID_V2, access$500);
                    } else {
                        CUIDInfo createFromJson2 = CUIDInfo.createFromJson(DeviceId.decryptCUIDInfo(access$1100));
                        if (createFromJson2 != null) {
                            if (DeviceId.this.fixCUIDInfoByIE(createFromJson2)) {
                                boolean unused5 = DeviceId.this.tryPutSystemSettingValue(DeviceId.KEY_DEVICE_ID_V2, DeviceId.encryptCUIDInfo(createFromJson2.toPersitString()));
                            }
                        } else if (createFromJson2 == null) {
                            boolean unused6 = DeviceId.this.tryPutSystemSettingValue(DeviceId.KEY_DEVICE_ID_V2, access$500);
                        }
                    }
                }
                boolean access$1300 = DeviceId.this.checkSelfPermission(Permission.WRITE_EXTERNAL_STORAGE);
                if (access$1300) {
                    if (!new File(Environment.getExternalStorageDirectory(), "backups/.SystemConfig/.cuid2").exists()) {
                        DeviceId.setExternalV2DeviceId(access$500);
                    } else {
                        CUIDInfo access$1500 = DeviceId.this.getCuidInfoFromExternalV2();
                        if (access$1500 != null) {
                            if (DeviceId.this.fixCUIDInfoByIE(access$1500)) {
                                DeviceId.setExternalV2DeviceId(DeviceId.encryptCUIDInfo(access$1500.toPersitString()));
                            }
                        } else if (access$1500 == null) {
                            DeviceId.setExternalV2DeviceId(access$500);
                        }
                    }
                }
                if (access$1000) {
                    String access$11002 = DeviceId.this.getSystemSettingValue(DeviceId.KEY_FLAG);
                    if (CUIDInfo.isIENormal(TextUtils.isEmpty(access$11002) ? 0 : access$11002.length())) {
                        boolean unused7 = DeviceId.this.tryPutSystemSettingValue(DeviceId.KEY_FLAG, CUIDInfo.I_FIXED);
                    } else if (CUIDInfo.isIENull(access$11002)) {
                        boolean unused8 = DeviceId.this.tryPutSystemSettingValue(DeviceId.KEY_FLAG, "0");
                    }
                }
                if (access$1300 && new File(Environment.getExternalStorageDirectory(), "backups/.SystemConfig/.cuid").exists() && (access$1700 = DeviceId.this.getExternalV1DeviceId(DeviceId.this.getDefaultFlag(""))) != null && DeviceId.this.fixCUIDInfoByIE(access$1700)) {
                    DeviceId.setExternalDeviceId(access$1700.flag, access$1700.deviceId);
                }
            }
        };
    }

    /* access modifiers changed from: private */
    public boolean hasWriteSettingsPermission() {
        return checkSelfPermission(Permission.WRITE_SETTINGS);
    }

    /* access modifiers changed from: private */
    public boolean checkSelfPermission(String str) {
        return this.mContext.checkPermission(str, Process.myPid(), Process.myUid()) == 0;
    }

    private CUIDInfo getCUidInfoFromSystemSettingV1() {
        return CUIDInfo.createCuidInfoFromV1Cache(getSystemSettingValue(KEY_DEVICE_ID), getSystemSettingValue(KEY_FLAG));
    }

    /* access modifiers changed from: private */
    public CUIDInfo getCuidInfoFromExternalV2() {
        File file = new File(Environment.getExternalStorageDirectory(), "backups/.SystemConfig/.cuid2");
        if (!file.exists()) {
            return null;
        }
        String fileContent = getFileContent(file);
        if (TextUtils.isEmpty(fileContent)) {
            return null;
        }
        try {
            return CUIDInfo.createFromJson(new String(AESUtil.decrypt(AES_KEY, AES_KEY, Base64.decode(fileContent.getBytes()))));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* access modifiers changed from: private */
    public CUIDInfo getExternalV1DeviceId(String str) {
        String str2;
        String str3 = "";
        File file = new File(Environment.getExternalStorageDirectory(), "baidu/.cuid");
        if (!file.exists()) {
            file = new File(Environment.getExternalStorageDirectory(), "backups/.SystemConfig/.cuid");
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            StringBuilder sb = new StringBuilder();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                sb.append(readLine);
                sb.append("\r\n");
            }
            bufferedReader.close();
            String[] split = new String(AESUtil.decrypt(AES_KEY, AES_KEY, Base64.decode(sb.toString().getBytes()))).split("=");
            if (split != null && split.length == 2) {
                str2 = split[0];
                try {
                    str3 = split[1];
                } catch (FileNotFoundException | IOException | Exception unused) {
                }
                return CUIDInfo.createCuidInfoFromV1Cache(str3, str2);
            }
        } catch (FileNotFoundException | IOException | Exception unused2) {
        }
        str2 = str3;
        return CUIDInfo.createCuidInfoFromV1Cache(str3, str2);
    }

    /* access modifiers changed from: private */
    public static String encryptCUIDInfo(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return Base64.encode(AESUtil.encrypt(AES_KEY, AES_KEY, str.getBytes()), "utf-8");
        } catch (UnsupportedEncodingException e) {
            handleThrowable(e);
            return "";
        } catch (Exception e2) {
            handleThrowable(e2);
            return "";
        }
    }

    /* access modifiers changed from: private */
    public static String decryptCUIDInfo(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return new String(AESUtil.decrypt(AES_KEY, AES_KEY, Base64.decode(str.getBytes())));
        } catch (Exception e) {
            handleThrowable(e);
            return "";
        }
    }

    /* access modifiers changed from: private */
    public static void setExternalV2DeviceId(String str) {
        File file;
        File file2 = new File(Environment.getExternalStorageDirectory(), EXT_DIR);
        File file3 = new File(file2, EXT_FILE_V2);
        try {
            if (file2.exists() && !file2.isDirectory()) {
                Random random = new Random();
                File parentFile = file2.getParentFile();
                String name = file2.getName();
                do {
                    file = new File(parentFile, name + random.nextInt() + ".tmp");
                } while (file.exists());
                file2.renameTo(file);
                file.delete();
            }
            file2.mkdirs();
            FileWriter fileWriter = new FileWriter(file3, false);
            fileWriter.write(str);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException | Exception unused) {
        }
    }

    /* access modifiers changed from: private */
    public static void setExternalDeviceId(String str, String str2) {
        File file;
        if (!TextUtils.isEmpty(str)) {
            File file2 = new File(Environment.getExternalStorageDirectory(), EXT_DIR);
            File file3 = new File(file2, EXT_FILE);
            try {
                if (file2.exists() && !file2.isDirectory()) {
                    Random random = new Random();
                    File parentFile = file2.getParentFile();
                    String name = file2.getName();
                    do {
                        file = new File(parentFile, name + random.nextInt() + ".tmp");
                    } while (file.exists());
                    file2.renameTo(file);
                    file.delete();
                }
                file2.mkdirs();
                FileWriter fileWriter = new FileWriter(file3, false);
                fileWriter.write(Base64.encode(AESUtil.encrypt(AES_KEY, AES_KEY, (str + "=" + str2).getBytes()), "utf-8"));
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException | Exception unused) {
            }
        }
    }

    /* access modifiers changed from: private */
    public static String getBase64DecodeStr(String str) {
        return new String(Base64.decode(str.getBytes()));
    }
}
