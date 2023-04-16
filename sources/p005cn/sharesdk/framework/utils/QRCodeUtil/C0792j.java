package p005cn.sharesdk.framework.utils.QRCodeUtil;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import androidx.core.view.ViewCompat;
import com.mob.MobSDK;
import com.mob.tools.utils.BitmapHelper;
import java.io.FileInputStream;
import java.util.HashMap;

/* renamed from: cn.sharesdk.framework.utils.QRCodeUtil.j */
/* compiled from: QRCode */
public class C0792j {

    /* renamed from: A */
    private int f533A = 80;

    /* renamed from: B */
    private Point f534B = new Point();

    /* renamed from: C */
    private float f535C;

    /* renamed from: D */
    private int f536D = 400;

    /* renamed from: E */
    private int f537E = 400;

    /* renamed from: F */
    private int f538F = ViewCompat.MEASURED_STATE_MASK;

    /* renamed from: G */
    private Bitmap f539G;

    /* renamed from: H */
    private boolean f540H;

    /* renamed from: I */
    private boolean f541I = true;

    /* renamed from: J */
    private float[] f542J;

    /* renamed from: K */
    private float[] f543K;

    /* renamed from: L */
    private float[] f544L;

    /* renamed from: M */
    private C0793k f545M = new C0793k();

    /* renamed from: a */
    protected Bitmap f546a;

    /* renamed from: b */
    protected Point f547b = new Point();

    /* renamed from: c */
    protected Point f548c = new Point(400, 400);

    /* renamed from: d */
    protected String f549d = null;

    /* renamed from: e */
    protected int f550e = 1;

    /* renamed from: f */
    protected String f551f = null;

    /* renamed from: g */
    protected boolean f552g = true;

    /* renamed from: h */
    protected int f553h = 0;

    /* renamed from: i */
    protected Bitmap f554i = null;

    /* renamed from: j */
    protected Drawable f555j = null;

    /* renamed from: k */
    protected String f556k = null;

    /* renamed from: l */
    protected String f557l = null;

    /* renamed from: m */
    protected int f558m = -1;

    /* renamed from: n */
    protected int f559n = 6;

    /* renamed from: o */
    protected boolean f560o = true;

    /* renamed from: p */
    protected int f561p = 0;

    /* renamed from: q */
    protected Bitmap f562q = null;

    /* renamed from: r */
    protected Drawable f563r = null;

    /* renamed from: s */
    protected String f564s = null;

    /* renamed from: t */
    protected String f565t = null;

    /* renamed from: u */
    protected int f566u = 6;

    /* renamed from: v */
    protected boolean f567v = true;

    /* renamed from: w */
    private int f568w = 400;

    /* renamed from: x */
    private int f569x = 400;

    /* renamed from: y */
    private int f570y = 400;

    /* renamed from: z */
    private int f571z = 0;

    public C0792j() {
        m611e();
    }

    /* renamed from: a */
    public void mo10879a(int i, int i2) {
        if (!this.f540H) {
            this.f568w = i;
            this.f569x = i2;
            this.f541I = true;
        }
    }

    /* renamed from: a */
    public void mo10880a(int i, int i2, int i3, int i4) {
        if (!this.f540H) {
            Point point = this.f547b;
            point.x = i;
            point.y = i2;
            Point point2 = this.f548c;
            point2.x = i3;
            point2.y = i4;
            m611e();
            this.f541I = true;
        }
    }

    /* renamed from: a */
    public void mo10883a(String str) {
        String str2;
        if (this.f540H) {
            return;
        }
        if (this.f550e != 1 || (str2 = this.f549d) == null || !str2.equals(str)) {
            this.f549d = str;
            this.f550e = 1;
            this.f552g = true;
            this.f541I = true;
        }
    }

    /* renamed from: b */
    public void mo10889b(String str) {
        String str2;
        if (this.f540H) {
            return;
        }
        if (this.f550e != 2 || (str2 = this.f551f) == null || !str2.equals(str)) {
            this.f551f = str;
            this.f550e = 2;
            this.f552g = true;
            this.f541I = true;
        }
    }

    /* renamed from: a */
    public void mo10878a(int i) {
        if (i > 0 && !this.f540H) {
            if (this.f553h != i || this.f559n != 1) {
                this.f553h = i;
                this.f560o = true;
                this.f559n = 1;
                this.f541I = true;
            }
        }
    }

    /* renamed from: a */
    public void mo10881a(Bitmap bitmap, boolean z) {
        if (bitmap != null && !this.f540H) {
            if (!z && this.f559n < 2) {
                return;
            }
            if (this.f554i != bitmap || this.f559n != 2) {
                this.f554i = bitmap;
                this.f559n = 2;
                this.f560o = true;
                this.f541I = true;
            }
        }
    }

    /* renamed from: a */
    public void mo10882a(Drawable drawable, boolean z) {
        if (drawable != null && !this.f540H) {
            if (!z && this.f559n < 3) {
                return;
            }
            if (this.f555j != drawable || this.f559n < 3) {
                this.f555j = drawable;
                this.f559n = 3;
                this.f560o = true;
                this.f541I = true;
            }
        }
    }

    /* renamed from: a */
    public void mo10884a(String str, boolean z) {
        if (str != null && str.length() != 0 && !this.f540H) {
            if (z || this.f559n >= 4) {
                String str2 = this.f556k;
                if (str2 == null || !str2.equals(str) || this.f559n != 4) {
                    this.f556k = str;
                    this.f559n = 4;
                    this.f560o = true;
                    this.f541I = true;
                }
            }
        }
    }

    /* renamed from: b */
    public void mo10890b(String str, boolean z) {
        if (str != null && str.length() != 0 && !this.f540H) {
            if (z || this.f559n >= 5) {
                String str2 = this.f557l;
                if (str2 == null || !str2.equals(str) || this.f559n != 5) {
                    this.f557l = str;
                    this.f559n = 5;
                    this.f560o = true;
                    this.f541I = true;
                }
            }
        }
    }

    /* renamed from: b */
    public void mo10886b(int i) {
        if (i > 0 && !this.f540H) {
            if (this.f561p != i || this.f566u != 1) {
                this.f561p = i;
                this.f566u = 1;
                this.f567v = true;
                this.f541I = true;
            }
        }
    }

    /* renamed from: b */
    public void mo10887b(Bitmap bitmap, boolean z) {
        if (bitmap != null && !this.f540H) {
            if (!z && this.f566u < 2) {
                return;
            }
            if (this.f562q != bitmap || this.f566u != 2) {
                this.f562q = bitmap;
                this.f566u = 2;
                this.f567v = true;
                this.f541I = true;
            }
        }
    }

    /* renamed from: b */
    public void mo10888b(Drawable drawable, boolean z) {
        if (drawable != null && !this.f540H) {
            if (!z && this.f566u < 3) {
                return;
            }
            if (this.f563r != drawable || this.f566u != 3) {
                this.f563r = drawable;
                this.f566u = 3;
                this.f567v = true;
                this.f541I = true;
            }
        }
    }

    /* renamed from: c */
    public void mo10892c(String str, boolean z) {
        if (str != null && str.length() != 0 && !this.f540H) {
            if (z || this.f566u >= 4) {
                String str2 = this.f564s;
                if (str2 == null || !str2.equals(str) || this.f566u != 4) {
                    this.f564s = str;
                    this.f566u = 4;
                    this.f567v = true;
                    this.f541I = true;
                }
            }
        }
    }

    /* renamed from: d */
    public void mo10893d(String str, boolean z) {
        if (str != null && str.length() != 0 && !this.f540H) {
            if (z || this.f566u >= 5) {
                String str2 = this.f565t;
                if (str2 == null || !str2.equals(str) || this.f566u != 5) {
                    this.f565t = str;
                    this.f566u = 5;
                    this.f567v = true;
                    this.f541I = true;
                }
            }
        }
    }

    /* renamed from: c */
    public void mo10891c(int i) {
        if ((i >>> 24) == 0) {
            i |= ViewCompat.MEASURED_STATE_MASK;
        }
        if (!this.f540H && this.f538F != i) {
            this.f538F = i;
            this.f541I = true;
        }
    }

    /* renamed from: a */
    public Bitmap mo10877a() {
        return this.f546a;
    }

    /* renamed from: b */
    public synchronized Bitmap mo10885b() throws Throwable {
        if (this.f541I) {
            this.f540H = true;
            m608c();
            if (this.f551f != null) {
                m610d();
                if (this.f554i != null) {
                    this.f568w = this.f554i.getWidth() < this.f536D ? this.f536D : this.f554i.getWidth();
                    this.f569x = this.f554i.getHeight() < this.f537E ? this.f537E : this.f554i.getHeight();
                }
                this.f546a = Bitmap.createBitmap(this.f568w, this.f569x, this.f554i == null ? Bitmap.Config.ARGB_8888 : this.f554i.getConfig());
                Canvas canvas = new Canvas(this.f546a);
                m604a(canvas);
                m606b(canvas);
                this.f540H = false;
                this.f541I = false;
            } else {
                throw new Error("content can not be null,please set url or uri before generate qrcode!");
            }
        }
        return this.f546a;
    }

    /* renamed from: a */
    private void m604a(Canvas canvas) {
        Bitmap bitmap = this.f554i;
        if (bitmap != null) {
            int i = this.f569x;
            this.f542J = new float[]{((float) bitmap.getWidth()) / ((float) this.f568w), ((float) this.f554i.getHeight()) / ((float) i)};
            int[] iArr = new int[(this.f568w * i)];
            int i2 = 0;
            while (true) {
                int i3 = this.f569x;
                if (i2 < i3) {
                    int i4 = 0;
                    while (true) {
                        int i5 = this.f568w;
                        if (i4 >= i5) {
                            break;
                        }
                        iArr[(i5 * i2) + i4] = m605b(i4, i2);
                        i4++;
                    }
                    i2++;
                } else {
                    Bitmap bitmap2 = this.f546a;
                    int i6 = this.f568w;
                    bitmap2.setPixels(iArr, 0, i6, 0, 0, i6, i3);
                    return;
                }
            }
        } else {
            canvas.drawColor(this.f558m);
        }
    }

    /* renamed from: b */
    private int m605b(int i, int i2) {
        float[] fArr = this.f542J;
        if (fArr == null) {
            return this.f558m;
        }
        return this.f554i.getPixel((int) (((float) i) * fArr[0]), (int) (((float) i2) * fArr[1]));
    }

    /* renamed from: b */
    private void m606b(Canvas canvas) throws Throwable {
        Bitmap bitmap = this.f539G;
        if (bitmap != null) {
            this.f543K = new float[]{((float) bitmap.getWidth()) / ((float) this.f570y), ((float) this.f539G.getHeight()) / ((float) this.f570y)};
        }
        C0794l lVar = new C0794l();
        HashMap hashMap = new HashMap();
        hashMap.put(C0787e.CHARACTER_SET, "utf-8");
        hashMap.put(C0787e.MARGIN, Integer.valueOf(this.f571z));
        hashMap.put(C0787e.ERROR_CORRECTION, C0788f.H);
        String str = this.f551f;
        C0776a aVar = C0776a.QR_CODE;
        int i = this.f570y;
        C0785c encode = lVar.encode(str, aVar, i, i, hashMap);
        int i2 = this.f570y;
        int[] iArr = new int[(i2 * i2)];
        for (int i3 = 0; i3 < this.f570y; i3++) {
            for (int i4 = 0; i4 < this.f570y; i4++) {
                if (encode.mo10851a(i4, i3)) {
                    iArr[(this.f570y * i3) + i4] = m607c(i4, i3);
                } else {
                    iArr[(this.f570y * i3) + i4] = 0;
                }
            }
        }
        Bitmap bitmap2 = this.f562q;
        if (bitmap2 != null) {
            int i5 = this.f533A;
            this.f544L = new float[]{((float) bitmap2.getWidth()) / ((float) this.f533A), ((float) this.f562q.getHeight()) / ((float) i5)};
            int i6 = this.f570y;
            int i7 = (i6 - i5) / 2;
            int i8 = (i6 + i5) / 2;
            for (int i9 = i7; i9 < i8; i9++) {
                for (int i10 = i7; i10 < i8; i10++) {
                    iArr[(this.f570y * i9) + i10] = m609d(i10 - i7, i9 - i7);
                }
            }
        }
        int i11 = this.f570y;
        Bitmap createBitmap = Bitmap.createBitmap(iArr, i11, i11, Bitmap.Config.ARGB_8888);
        canvas.translate((float) this.f534B.x, (float) this.f534B.y);
        canvas.rotate(this.f535C);
        int i12 = this.f570y;
        canvas.translate((float) ((-i12) / 2), (float) ((-i12) / 2));
        canvas.drawBitmap(createBitmap, 0.0f, 0.0f, (Paint) null);
    }

    /* renamed from: c */
    private int m607c(int i, int i2) {
        float[] fArr = this.f543K;
        if (fArr == null) {
            return this.f538F;
        }
        return this.f539G.getPixel((int) (((float) i) * fArr[0]), (int) (((float) i2) * fArr[1]));
    }

    /* renamed from: d */
    private int m609d(int i, int i2) {
        float[] fArr = this.f544L;
        int i3 = (int) (((float) i) * fArr[0]);
        int i4 = (int) (((float) i2) * fArr[1]);
        if (i3 >= this.f562q.getWidth()) {
            i3 = this.f562q.getWidth() - 1;
        }
        if (i4 >= this.f562q.getHeight()) {
            i4 = this.f562q.getHeight() - 1;
        }
        return this.f562q.getPixel(i3, i4);
    }

    /* renamed from: c */
    private void m608c() throws Throwable {
        if (this.f552g && this.f550e == 1) {
            this.f551f = this.f549d;
        }
    }

    /* renamed from: d */
    private void m610d() throws Throwable {
        int i;
        int i2;
        if (this.f560o && (i2 = this.f559n) != 6) {
            if (i2 == 1) {
                this.f554i = BitmapFactory.decodeResource(MobSDK.getContext().getResources(), this.f553h);
            } else if (i2 == 3) {
                this.f554i = m603a(this.f555j);
            } else if (i2 == 4) {
                this.f554i = BitmapFactory.decodeStream(new FileInputStream(this.f556k));
            } else if (i2 == 5) {
                this.f554i = BitmapFactory.decodeStream(new FileInputStream(BitmapHelper.downloadBitmap(MobSDK.getContext(), this.f557l)));
            }
            this.f560o = false;
        }
        if (this.f567v && (i = this.f566u) != 6) {
            if (i == 1) {
                this.f562q = BitmapFactory.decodeResource(MobSDK.getContext().getResources(), this.f561p);
            } else if (i == 3) {
                this.f562q = m603a(this.f563r);
            } else if (i == 4) {
                this.f562q = BitmapFactory.decodeStream(new FileInputStream(this.f564s));
            } else if (i == 5) {
                this.f562q = BitmapFactory.decodeStream(new FileInputStream(BitmapHelper.downloadBitmap(MobSDK.getContext(), this.f565t)));
            }
            this.f567v = false;
        }
    }

    /* renamed from: a */
    private Bitmap m603a(Drawable drawable) {
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        Bitmap createBitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, drawable.getOpacity() != -1 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
        drawable.draw(canvas);
        return createBitmap;
    }

    /* renamed from: e */
    private void m611e() {
        int i = this.f548c.x - this.f547b.x;
        int i2 = this.f548c.y - this.f547b.y;
        this.f570y = (int) (Math.sqrt((double) (((i * i) + (i2 * i2)) * 2)) / 2.0d);
        this.f534B.x = (this.f548c.x + this.f547b.x) / 2;
        this.f534B.y = (this.f548c.y + this.f547b.y) / 2;
        double d = (double) this.f570y;
        Double.isNaN(d);
        this.f533A = (int) (d * 0.2d);
        this.f535C = (float) (Math.toDegrees(Math.atan2((double) i2, (double) i)) - 45.0d);
    }
}
