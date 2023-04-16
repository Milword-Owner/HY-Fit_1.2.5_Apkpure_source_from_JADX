package top.zibin.luban;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

class Engine {
    private boolean focusAlpha;
    private int srcHeight;
    private InputStreamProvider srcImg;
    private int srcWidth;
    private File tagImg;

    Engine(InputStreamProvider inputStreamProvider, File file, boolean z) throws IOException {
        this.tagImg = file;
        this.srcImg = inputStreamProvider;
        this.focusAlpha = z;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inSampleSize = 1;
        BitmapFactory.decodeStream(inputStreamProvider.open(), (Rect) null, options);
        this.srcWidth = options.outWidth;
        this.srcHeight = options.outHeight;
    }

    private int computeSize() {
        int i = this.srcWidth;
        if (i % 2 == 1) {
            i++;
        }
        this.srcWidth = i;
        int i2 = this.srcHeight;
        if (i2 % 2 == 1) {
            i2++;
        }
        this.srcHeight = i2;
        int max = Math.max(this.srcWidth, this.srcHeight);
        float min = ((float) Math.min(this.srcWidth, this.srcHeight)) / ((float) max);
        if (min > 1.0f || ((double) min) <= 0.5625d) {
            double d = (double) min;
            if (d > 0.5625d || d <= 0.5d) {
                double d2 = (double) max;
                Double.isNaN(d);
                Double.isNaN(d2);
                return (int) Math.ceil(d2 / (1280.0d / d));
            }
            int i3 = max / 1280;
            if (i3 == 0) {
                return 1;
            }
            return i3;
        } else if (max < 1664) {
            return 1;
        } else {
            if (max < 4990) {
                return 2;
            }
            if (max > 4990 && max < 10240) {
                return 4;
            }
            int i4 = max / 1280;
            if (i4 == 0) {
                return 1;
            }
            return i4;
        }
    }

    private Bitmap rotatingImage(Bitmap bitmap, int i) {
        Matrix matrix = new Matrix();
        matrix.postRotate((float) i);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    /* access modifiers changed from: package-private */
    public File compress() throws IOException {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = computeSize();
        Bitmap decodeStream = BitmapFactory.decodeStream(this.srcImg.open(), (Rect) null, options);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (Checker.SINGLE.isJPG(this.srcImg.open())) {
            decodeStream = rotatingImage(decodeStream, Checker.SINGLE.getOrientation(this.srcImg.open()));
        }
        decodeStream.compress(this.focusAlpha ? Bitmap.CompressFormat.PNG : Bitmap.CompressFormat.JPEG, 60, byteArrayOutputStream);
        decodeStream.recycle();
        FileOutputStream fileOutputStream = new FileOutputStream(this.tagImg);
        fileOutputStream.write(byteArrayOutputStream.toByteArray());
        fileOutputStream.flush();
        fileOutputStream.close();
        byteArrayOutputStream.close();
        return this.tagImg;
    }
}
