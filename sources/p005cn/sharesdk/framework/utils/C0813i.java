package p005cn.sharesdk.framework.utils;

import android.graphics.Bitmap;
import android.text.TextUtils;
import java.util.Hashtable;
import p005cn.sharesdk.framework.utils.QRCodeUtil.C0776a;
import p005cn.sharesdk.framework.utils.QRCodeUtil.C0785c;
import p005cn.sharesdk.framework.utils.QRCodeUtil.C0787e;
import p005cn.sharesdk.framework.utils.QRCodeUtil.C0794l;
import p005cn.sharesdk.framework.utils.QRCodeUtil.WriterException;

/* renamed from: cn.sharesdk.framework.utils.i */
/* compiled from: UrlToQRCode */
public class C0813i {

    /* renamed from: a */
    private static volatile C0813i f624a;

    /* renamed from: a */
    public static C0813i m698a() {
        synchronized (C0813i.class) {
            if (f624a == null) {
                synchronized (C0813i.class) {
                    if (f624a == null) {
                        f624a = new C0813i();
                    }
                }
            }
        }
        return f624a;
    }

    /* renamed from: a */
    public static Bitmap m697a(String str, int i, int i2, String str2, String str3, String str4, int i3, int i4) {
        C0785c cVar;
        int i5 = i;
        int i6 = i2;
        if (!TextUtils.isEmpty(str) && i5 >= 0 && i6 >= 0) {
            try {
                Hashtable hashtable = new Hashtable();
                if (!TextUtils.isEmpty(str2)) {
                    String str5 = str2;
                    hashtable.put(C0787e.CHARACTER_SET, str2);
                }
                if (!TextUtils.isEmpty(str3)) {
                    String str6 = str3;
                    hashtable.put(C0787e.ERROR_CORRECTION, str3);
                }
                if (!TextUtils.isEmpty(str4)) {
                    C0787e eVar = C0787e.AZTEC_LAYERS;
                    String str7 = str4;
                    hashtable.put(C0787e.MARGIN, str4);
                }
                try {
                    cVar = new C0794l().encode(str, C0776a.QR_CODE, i, i2, hashtable);
                } catch (WriterException e) {
                    e.printStackTrace();
                    cVar = null;
                }
                int[] iArr = new int[(i5 * i6)];
                for (int i7 = 0; i7 < i6; i7++) {
                    for (int i8 = 0; i8 < i5; i8++) {
                        if (cVar.mo10851a(i8, i7)) {
                            iArr[(i7 * i5) + i8] = i3;
                        } else {
                            iArr[(i7 * i5) + i8] = i4;
                        }
                    }
                }
                Bitmap createBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
                createBitmap.setPixels(iArr, 0, i, 0, 0, i, i2);
                return createBitmap;
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }
}
