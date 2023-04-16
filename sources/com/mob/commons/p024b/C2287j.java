package com.mob.commons.p024b;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.Signature;
import android.os.IBinder;
import android.text.TextUtils;
import com.mob.commons.C2312k;
import com.mob.commons.p024b.C2277f;
import java.security.MessageDigest;
import kotlin.UByte;

/* renamed from: com.mob.commons.b.j */
/* compiled from: Oppo */
public class C2287j extends C2277f {

    /* renamed from: c */
    private String f2097c;

    public C2287j(Context context) {
        super(context);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0027, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0030, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0035, code lost:
        return false;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* renamed from: h */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean mo29056h() {
        /*
            r7 = this;
            monitor-enter(r7)
            r0 = 0
            android.content.Context r1 = r7.f2069a     // Catch:{ Throwable -> 0x0034, all -> 0x0031 }
            android.content.pm.PackageManager r1 = r1.getPackageManager()     // Catch:{ Throwable -> 0x0034, all -> 0x0031 }
            r2 = 87
            java.lang.String r2 = com.mob.commons.C2312k.m2575a(r2)     // Catch:{ Throwable -> 0x0034, all -> 0x0031 }
            android.content.pm.PackageInfo r1 = r1.getPackageInfo(r2, r0)     // Catch:{ Throwable -> 0x0034, all -> 0x0031 }
            int r2 = android.os.Build.VERSION.SDK_INT     // Catch:{ Throwable -> 0x0034, all -> 0x0031 }
            r3 = 28
            r4 = 1
            if (r2 < r3) goto L_0x0028
            if (r1 == 0) goto L_0x0026
            long r1 = r1.getLongVersionCode()     // Catch:{ Throwable -> 0x0034, all -> 0x0031 }
            r5 = 1
            int r3 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1))
            if (r3 < 0) goto L_0x0026
            r0 = 1
        L_0x0026:
            monitor-exit(r7)
            return r0
        L_0x0028:
            if (r1 == 0) goto L_0x002f
            int r1 = r1.versionCode     // Catch:{ Throwable -> 0x0034, all -> 0x0031 }
            if (r1 < r4) goto L_0x002f
            r0 = 1
        L_0x002f:
            monitor-exit(r7)
            return r0
        L_0x0031:
            r0 = move-exception
            monitor-exit(r7)
            throw r0
        L_0x0034:
            monitor-exit(r7)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.p024b.C2287j.mo29056h():boolean");
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Intent mo29040a() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(C2312k.m2575a(87), C2312k.m2575a(88)));
        intent.setAction(C2312k.m2575a(89));
        return intent;
    }

    /* renamed from: a */
    public C2277f.C2281c mo29041a(IBinder iBinder) {
        C2277f.C2281c cVar = new C2277f.C2281c();
        cVar.f2083c = m2409a(iBinder, C2312k.m2575a(90));
        cVar.f2082b = m2409a(iBinder, C2312k.m2575a(91));
        cVar.f2085e = m2409a(iBinder, C2312k.m2575a(92));
        return cVar;
    }

    /* renamed from: a */
    private final String m2409a(IBinder iBinder, String str) {
        if (TextUtils.isEmpty(this.f2070b)) {
            this.f2070b = this.f2069a.getPackageName();
        }
        if (TextUtils.isEmpty(this.f2097c)) {
            try {
                Signature[] signatureArr = this.f2069a.getPackageManager().getPackageInfo(this.f2070b, 64).signatures;
                if (signatureArr != null && signatureArr.length > 0) {
                    byte[] byteArray = signatureArr[0].toByteArray();
                    MessageDigest instance = MessageDigest.getInstance("SHA1");
                    if (instance != null) {
                        byte[] digest = instance.digest(byteArray);
                        StringBuilder sb = new StringBuilder();
                        for (byte b : digest) {
                            sb.append(Integer.toHexString((b & UByte.MAX_VALUE) | UByte.MIN_VALUE).substring(1, 3));
                        }
                        this.f2097c = sb.toString();
                    }
                }
            } catch (Throwable unused) {
            }
        }
        return mo29047a(str, iBinder, C2312k.m2575a(93), 1, this.f2070b, this.f2097c, str);
    }
}
