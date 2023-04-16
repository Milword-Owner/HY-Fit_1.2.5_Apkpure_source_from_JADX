package p005cn.sharesdk.framework.loopshare.watermark;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import java.io.FileOutputStream;
import p005cn.sharesdk.framework.utils.SSDKLog;
import p005cn.sharesdk.onekeyshare.OnekeyShare;

/* renamed from: cn.sharesdk.framework.loopshare.watermark.a */
/* compiled from: BitmapFactory */
public class C0763a {

    /* renamed from: a */
    private static int[] f364a = new int[3];

    /* renamed from: b */
    private static int f365b = 15;

    /* renamed from: c */
    private static int f366c = 270;

    /* renamed from: d */
    private static float[] f367d = {60.0f, 30.0f, 10.0f};

    /* renamed from: e */
    private static int f368e = -1;

    /* renamed from: f */
    private static int f369f = 0;

    /* renamed from: g */
    private static int f370g = 100;

    /* renamed from: a */
    public static boolean m479a(String str, String[] strArr) {
        try {
            Paint paint = new Paint(1);
            paint.setColor(f368e);
            f366c = 0;
            f365b = 0;
            for (int i = 0; i < strArr.length; i++) {
                paint.setTextSize(f367d[i]);
                Rect rect = new Rect();
                paint.getTextBounds(strArr[i], 0, strArr[i].length(), rect);
                if (f366c < rect.width()) {
                    f366c = rect.width();
                }
                f364a[i] = rect.height();
                f365b += rect.height() * 2;
            }
            Bitmap createBitmap = Bitmap.createBitmap(f366c, f365b, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            canvas.drawColor(f369f);
            int i2 = 0;
            for (int i3 = 0; i3 < strArr.length; i3++) {
                paint.setTextSize(f367d[i3]);
                i2 += f364a[i3];
                if (i3 > 0) {
                    i2 += f364a[i3 - 1];
                }
                canvas.drawText(strArr[i3], 0.0f, (float) i2, paint);
            }
            SSDKLog.m645b().mo29768d(OnekeyShare.SHARESDK_TAG, "path:" + str);
            createBitmap.compress(Bitmap.CompressFormat.JPEG, f370g, new FileOutputStream(str));
            C0773g.f403a = f366c;
            C0773g.f404b = f365b;
            return true;
        } catch (Throwable th) {
            SSDKLog.m645b().mo29768d(OnekeyShare.SHARESDK_TAG, "e:" + th.toString());
            SSDKLog.m645b().mo29768d(OnekeyShare.SHARESDK_TAG, "writeImage catch: " + th);
            return false;
        }
    }
}
