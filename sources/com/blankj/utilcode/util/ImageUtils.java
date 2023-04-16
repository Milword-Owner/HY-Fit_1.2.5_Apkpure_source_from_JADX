package com.blankj.utilcode.util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.os.Build;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.view.View;
import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.FloatRange;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewCompat;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;

public final class ImageUtils {
    private ImageUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static byte[] bitmap2Bytes(Bitmap bitmap) {
        return bitmap2Bytes(bitmap, Bitmap.CompressFormat.PNG, 100);
    }

    public static byte[] bitmap2Bytes(Bitmap bitmap, Bitmap.CompressFormat compressFormat, int i) {
        if (bitmap == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(compressFormat, i, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static Bitmap bytes2Bitmap(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        return BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
    }

    public static Bitmap drawable2Bitmap(Drawable drawable) {
        Bitmap bitmap;
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }
        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, drawable.getOpacity() != -1 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), drawable.getOpacity() != -1 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        }
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    public static Drawable bitmap2Drawable(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        return new BitmapDrawable(Utils.getApp().getResources(), bitmap);
    }

    public static byte[] drawable2Bytes(Drawable drawable) {
        if (drawable == null) {
            return null;
        }
        return bitmap2Bytes(drawable2Bitmap(drawable));
    }

    public static byte[] drawable2Bytes(Drawable drawable, Bitmap.CompressFormat compressFormat, int i) {
        if (drawable == null) {
            return null;
        }
        return bitmap2Bytes(drawable2Bitmap(drawable), compressFormat, i);
    }

    public static Drawable bytes2Drawable(byte[] bArr) {
        return bitmap2Drawable(bytes2Bitmap(bArr));
    }

    public static Bitmap view2Bitmap(View view) {
        Bitmap bitmap;
        if (view == null) {
            return null;
        }
        boolean isDrawingCacheEnabled = view.isDrawingCacheEnabled();
        boolean willNotCacheDrawing = view.willNotCacheDrawing();
        view.setDrawingCacheEnabled(true);
        view.setWillNotCacheDrawing(false);
        Bitmap drawingCache = view.getDrawingCache();
        if (drawingCache == null || drawingCache.isRecycled()) {
            view.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
            view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
            view.buildDrawingCache();
            Bitmap drawingCache2 = view.getDrawingCache();
            if (drawingCache2 == null || drawingCache2.isRecycled()) {
                bitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Bitmap.Config.RGB_565);
                view.draw(new Canvas(bitmap));
            } else {
                bitmap = Bitmap.createBitmap(drawingCache2);
            }
        } else {
            bitmap = Bitmap.createBitmap(drawingCache);
        }
        view.setWillNotCacheDrawing(willNotCacheDrawing);
        view.setDrawingCacheEnabled(isDrawingCacheEnabled);
        return bitmap;
    }

    public static Bitmap getBitmap(File file) {
        if (file == null) {
            return null;
        }
        return BitmapFactory.decodeFile(file.getAbsolutePath());
    }

    public static Bitmap getBitmap(File file, int i, int i2) {
        if (file == null) {
            return null;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(file.getAbsolutePath(), options);
        options.inSampleSize = calculateInSampleSize(options, i, i2);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(file.getAbsolutePath(), options);
    }

    public static Bitmap getBitmap(String str) {
        if (UtilsBridge.isSpace(str)) {
            return null;
        }
        return BitmapFactory.decodeFile(str);
    }

    public static Bitmap getBitmap(String str, int i, int i2) {
        if (UtilsBridge.isSpace(str)) {
            return null;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        options.inSampleSize = calculateInSampleSize(options, i, i2);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(str, options);
    }

    public static Bitmap getBitmap(InputStream inputStream) {
        if (inputStream == null) {
            return null;
        }
        return BitmapFactory.decodeStream(inputStream);
    }

    public static Bitmap getBitmap(InputStream inputStream, int i, int i2) {
        if (inputStream == null) {
            return null;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(inputStream, (Rect) null, options);
        options.inSampleSize = calculateInSampleSize(options, i, i2);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeStream(inputStream, (Rect) null, options);
    }

    public static Bitmap getBitmap(byte[] bArr, int i) {
        if (bArr.length == 0) {
            return null;
        }
        return BitmapFactory.decodeByteArray(bArr, i, bArr.length);
    }

    public static Bitmap getBitmap(byte[] bArr, int i, int i2, int i3) {
        if (bArr.length == 0) {
            return null;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(bArr, i, bArr.length, options);
        options.inSampleSize = calculateInSampleSize(options, i2, i3);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeByteArray(bArr, i, bArr.length, options);
    }

    public static Bitmap getBitmap(@DrawableRes int i) {
        return BitmapFactory.decodeResource(Utils.getApp().getResources(), i);
    }

    public static Bitmap getBitmap(@DrawableRes int i, int i2, int i3) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        Resources resources = Utils.getApp().getResources();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(resources, i, options);
        options.inSampleSize = calculateInSampleSize(options, i2, i3);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(resources, i, options);
    }

    public static Bitmap getBitmap(FileDescriptor fileDescriptor) {
        if (fileDescriptor == null) {
            return null;
        }
        return BitmapFactory.decodeFileDescriptor(fileDescriptor);
    }

    public static Bitmap getBitmap(FileDescriptor fileDescriptor, int i, int i2) {
        if (fileDescriptor == null) {
            return null;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFileDescriptor(fileDescriptor, (Rect) null, options);
        options.inSampleSize = calculateInSampleSize(options, i, i2);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFileDescriptor(fileDescriptor, (Rect) null, options);
    }

    public static Bitmap drawColor(@NonNull Bitmap bitmap, @ColorInt int i) {
        if (bitmap != null) {
            return drawColor(bitmap, i, false);
        }
        throw new NullPointerException("Argument 'src' of type Bitmap (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static Bitmap drawColor(@NonNull Bitmap bitmap, @ColorInt int i, boolean z) {
        if (bitmap == null) {
            throw new NullPointerException("Argument 'src' of type Bitmap (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (isEmptyBitmap(bitmap)) {
            return null;
        } else {
            if (!z) {
                bitmap = bitmap.copy(bitmap.getConfig(), true);
            }
            new Canvas(bitmap).drawColor(i, PorterDuff.Mode.DARKEN);
            return bitmap;
        }
    }

    public static Bitmap scale(Bitmap bitmap, int i, int i2) {
        return scale(bitmap, i, i2, false);
    }

    public static Bitmap scale(Bitmap bitmap, int i, int i2, boolean z) {
        if (isEmptyBitmap(bitmap)) {
            return null;
        }
        Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap, i, i2, true);
        if (z && !bitmap.isRecycled() && createScaledBitmap != bitmap) {
            bitmap.recycle();
        }
        return createScaledBitmap;
    }

    public static Bitmap scale(Bitmap bitmap, float f, float f2) {
        return scale(bitmap, f, f2, false);
    }

    public static Bitmap scale(Bitmap bitmap, float f, float f2, boolean z) {
        if (isEmptyBitmap(bitmap)) {
            return null;
        }
        Matrix matrix = new Matrix();
        matrix.setScale(f, f2);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        if (z && !bitmap.isRecycled() && createBitmap != bitmap) {
            bitmap.recycle();
        }
        return createBitmap;
    }

    public static Bitmap clip(Bitmap bitmap, int i, int i2, int i3, int i4) {
        return clip(bitmap, i, i2, i3, i4, false);
    }

    public static Bitmap clip(Bitmap bitmap, int i, int i2, int i3, int i4, boolean z) {
        if (isEmptyBitmap(bitmap)) {
            return null;
        }
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, i, i2, i3, i4);
        if (z && !bitmap.isRecycled() && createBitmap != bitmap) {
            bitmap.recycle();
        }
        return createBitmap;
    }

    public static Bitmap skew(Bitmap bitmap, float f, float f2) {
        return skew(bitmap, f, f2, 0.0f, 0.0f, false);
    }

    public static Bitmap skew(Bitmap bitmap, float f, float f2, boolean z) {
        return skew(bitmap, f, f2, 0.0f, 0.0f, z);
    }

    public static Bitmap skew(Bitmap bitmap, float f, float f2, float f3, float f4) {
        return skew(bitmap, f, f2, f3, f4, false);
    }

    public static Bitmap skew(Bitmap bitmap, float f, float f2, float f3, float f4, boolean z) {
        if (isEmptyBitmap(bitmap)) {
            return null;
        }
        Matrix matrix = new Matrix();
        matrix.setSkew(f, f2, f3, f4);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        if (z && !bitmap.isRecycled() && createBitmap != bitmap) {
            bitmap.recycle();
        }
        return createBitmap;
    }

    public static Bitmap rotate(Bitmap bitmap, int i, float f, float f2) {
        return rotate(bitmap, i, f, f2, false);
    }

    public static Bitmap rotate(Bitmap bitmap, int i, float f, float f2, boolean z) {
        if (isEmptyBitmap(bitmap)) {
            return null;
        }
        if (i == 0) {
            return bitmap;
        }
        Matrix matrix = new Matrix();
        matrix.setRotate((float) i, f, f2);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        if (z && !bitmap.isRecycled() && createBitmap != bitmap) {
            bitmap.recycle();
        }
        return createBitmap;
    }

    public static int getRotateDegree(String str) {
        try {
            int attributeInt = new ExifInterface(str).getAttributeInt(androidx.exifinterface.media.ExifInterface.TAG_ORIENTATION, 1);
            if (attributeInt == 3) {
                return 180;
            }
            if (attributeInt != 6) {
                return attributeInt != 8 ? 0 : 270;
            }
            return 90;
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static Bitmap toRound(Bitmap bitmap) {
        return toRound(bitmap, 0, 0, false);
    }

    public static Bitmap toRound(Bitmap bitmap, boolean z) {
        return toRound(bitmap, 0, 0, z);
    }

    public static Bitmap toRound(Bitmap bitmap, @IntRange(from = 0) int i, @ColorInt int i2) {
        return toRound(bitmap, i, i2, false);
    }

    public static Bitmap toRound(Bitmap bitmap, @IntRange(from = 0) int i, @ColorInt int i2, boolean z) {
        Bitmap bitmap2 = bitmap;
        int i3 = i;
        if (isEmptyBitmap(bitmap)) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int min = Math.min(width, height);
        Paint paint = new Paint(1);
        Bitmap createBitmap = Bitmap.createBitmap(width, height, bitmap.getConfig());
        float f = (float) min;
        float f2 = f / 2.0f;
        float f3 = (float) width;
        float f4 = (float) height;
        RectF rectF = new RectF(0.0f, 0.0f, f3, f4);
        rectF.inset(((float) (width - min)) / 2.0f, ((float) (height - min)) / 2.0f);
        Matrix matrix = new Matrix();
        matrix.setTranslate(rectF.left, rectF.top);
        if (width != height) {
            matrix.preScale(f / f3, f / f4);
        }
        BitmapShader bitmapShader = new BitmapShader(bitmap2, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        bitmapShader.setLocalMatrix(matrix);
        paint.setShader(bitmapShader);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawRoundRect(rectF, f2, f2, paint);
        if (i3 > 0) {
            paint.setShader((Shader) null);
            paint.setColor(i2);
            paint.setStyle(Paint.Style.STROKE);
            float f5 = (float) i3;
            paint.setStrokeWidth(f5);
            canvas.drawCircle(f3 / 2.0f, f4 / 2.0f, f2 - (f5 / 2.0f), paint);
        }
        if (z && !bitmap.isRecycled() && createBitmap != bitmap2) {
            bitmap.recycle();
        }
        return createBitmap;
    }

    public static Bitmap toRoundCorner(Bitmap bitmap, float f) {
        return toRoundCorner(bitmap, f, 0.0f, 0, false);
    }

    public static Bitmap toRoundCorner(Bitmap bitmap, float f, boolean z) {
        return toRoundCorner(bitmap, f, 0.0f, 0, z);
    }

    public static Bitmap toRoundCorner(Bitmap bitmap, float f, @FloatRange(from = 0.0d) float f2, @ColorInt int i) {
        return toRoundCorner(bitmap, f, f2, i, false);
    }

    public static Bitmap toRoundCorner(Bitmap bitmap, float[] fArr, @FloatRange(from = 0.0d) float f, @ColorInt int i) {
        return toRoundCorner(bitmap, fArr, f, i, false);
    }

    public static Bitmap toRoundCorner(Bitmap bitmap, float f, @FloatRange(from = 0.0d) float f2, @ColorInt int i, boolean z) {
        return toRoundCorner(bitmap, new float[]{f, f, f, f, f, f, f, f}, f2, i, z);
    }

    public static Bitmap toRoundCorner(Bitmap bitmap, float[] fArr, @FloatRange(from = 0.0d) float f, @ColorInt int i, boolean z) {
        if (isEmptyBitmap(bitmap)) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Paint paint = new Paint(1);
        Bitmap createBitmap = Bitmap.createBitmap(width, height, bitmap.getConfig());
        paint.setShader(new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        Canvas canvas = new Canvas(createBitmap);
        RectF rectF = new RectF(0.0f, 0.0f, (float) width, (float) height);
        float f2 = f / 2.0f;
        rectF.inset(f2, f2);
        Path path = new Path();
        path.addRoundRect(rectF, fArr, Path.Direction.CW);
        canvas.drawPath(path, paint);
        if (f > 0.0f) {
            paint.setShader((Shader) null);
            paint.setColor(i);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(f);
            paint.setStrokeCap(Paint.Cap.ROUND);
            canvas.drawPath(path, paint);
        }
        if (z && !bitmap.isRecycled() && createBitmap != bitmap) {
            bitmap.recycle();
        }
        return createBitmap;
    }

    public static Bitmap addCornerBorder(Bitmap bitmap, @FloatRange(from = 1.0d) float f, @ColorInt int i, @FloatRange(from = 0.0d) float f2) {
        return addBorder(bitmap, f, i, false, f2, false);
    }

    public static Bitmap addCornerBorder(Bitmap bitmap, @FloatRange(from = 1.0d) float f, @ColorInt int i, float[] fArr) {
        return addBorder(bitmap, f, i, false, fArr, false);
    }

    public static Bitmap addCornerBorder(Bitmap bitmap, @FloatRange(from = 1.0d) float f, @ColorInt int i, float[] fArr, boolean z) {
        return addBorder(bitmap, f, i, false, fArr, z);
    }

    public static Bitmap addCornerBorder(Bitmap bitmap, @FloatRange(from = 1.0d) float f, @ColorInt int i, @FloatRange(from = 0.0d) float f2, boolean z) {
        return addBorder(bitmap, f, i, false, f2, z);
    }

    public static Bitmap addCircleBorder(Bitmap bitmap, @FloatRange(from = 1.0d) float f, @ColorInt int i) {
        return addBorder(bitmap, f, i, true, 0.0f, false);
    }

    public static Bitmap addCircleBorder(Bitmap bitmap, @FloatRange(from = 1.0d) float f, @ColorInt int i, boolean z) {
        return addBorder(bitmap, f, i, true, 0.0f, z);
    }

    private static Bitmap addBorder(Bitmap bitmap, @FloatRange(from = 1.0d) float f, @ColorInt int i, boolean z, float f2, boolean z2) {
        return addBorder(bitmap, f, i, z, new float[]{f2, f2, f2, f2, f2, f2, f2, f2}, z2);
    }

    private static Bitmap addBorder(Bitmap bitmap, @FloatRange(from = 1.0d) float f, @ColorInt int i, boolean z, float[] fArr, boolean z2) {
        if (isEmptyBitmap(bitmap)) {
            return null;
        }
        if (!z2) {
            bitmap = bitmap.copy(bitmap.getConfig(), true);
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint(1);
        paint.setColor(i);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(f);
        if (z) {
            canvas.drawCircle(((float) width) / 2.0f, ((float) height) / 2.0f, (((float) Math.min(width, height)) / 2.0f) - (f / 2.0f), paint);
        } else {
            RectF rectF = new RectF(0.0f, 0.0f, (float) width, (float) height);
            float f2 = f / 2.0f;
            rectF.inset(f2, f2);
            Path path = new Path();
            path.addRoundRect(rectF, fArr, Path.Direction.CW);
            canvas.drawPath(path, paint);
        }
        return bitmap;
    }

    public static Bitmap addReflection(Bitmap bitmap, int i) {
        return addReflection(bitmap, i, false);
    }

    public static Bitmap addReflection(Bitmap bitmap, int i, boolean z) {
        Bitmap bitmap2 = bitmap;
        if (isEmptyBitmap(bitmap)) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.preScale(1.0f, -1.0f);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, height - i, width, i, matrix, false);
        Bitmap createBitmap2 = Bitmap.createBitmap(width, height + i, bitmap.getConfig());
        Canvas canvas = new Canvas(createBitmap2);
        canvas.drawBitmap(bitmap2, 0.0f, 0.0f, (Paint) null);
        float f = (float) (height + 0);
        canvas.drawBitmap(createBitmap, 0.0f, f, (Paint) null);
        Paint paint = new Paint(1);
        paint.setShader(new LinearGradient(0.0f, (float) height, 0.0f, (float) (createBitmap2.getHeight() + 0), 1895825407, ViewCompat.MEASURED_SIZE_MASK, Shader.TileMode.MIRROR));
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawRect(0.0f, f, (float) width, (float) createBitmap2.getHeight(), paint);
        if (!createBitmap.isRecycled()) {
            createBitmap.recycle();
        }
        if (z && !bitmap.isRecycled() && createBitmap2 != bitmap2) {
            bitmap.recycle();
        }
        return createBitmap2;
    }

    public static Bitmap addTextWatermark(Bitmap bitmap, String str, int i, @ColorInt int i2, float f, float f2) {
        return addTextWatermark(bitmap, str, (float) i, i2, f, f2, false);
    }

    public static Bitmap addTextWatermark(Bitmap bitmap, String str, float f, @ColorInt int i, float f2, float f3, boolean z) {
        if (isEmptyBitmap(bitmap) || str == null) {
            return null;
        }
        Bitmap copy = bitmap.copy(bitmap.getConfig(), true);
        Paint paint = new Paint(1);
        Canvas canvas = new Canvas(copy);
        paint.setColor(i);
        paint.setTextSize(f);
        paint.getTextBounds(str, 0, str.length(), new Rect());
        canvas.drawText(str, f2, f3 + f, paint);
        if (z && !bitmap.isRecycled() && copy != bitmap) {
            bitmap.recycle();
        }
        return copy;
    }

    public static Bitmap addImageWatermark(Bitmap bitmap, Bitmap bitmap2, int i, int i2, int i3) {
        return addImageWatermark(bitmap, bitmap2, i, i2, i3, false);
    }

    public static Bitmap addImageWatermark(Bitmap bitmap, Bitmap bitmap2, int i, int i2, int i3, boolean z) {
        if (isEmptyBitmap(bitmap)) {
            return null;
        }
        Bitmap copy = bitmap.copy(bitmap.getConfig(), true);
        if (!isEmptyBitmap(bitmap2)) {
            Paint paint = new Paint(1);
            Canvas canvas = new Canvas(copy);
            paint.setAlpha(i3);
            canvas.drawBitmap(bitmap2, (float) i, (float) i2, paint);
        }
        if (z && !bitmap.isRecycled() && copy != bitmap) {
            bitmap.recycle();
        }
        return copy;
    }

    public static Bitmap toAlpha(Bitmap bitmap) {
        return toAlpha(bitmap, false);
    }

    public static Bitmap toAlpha(Bitmap bitmap, Boolean bool) {
        if (isEmptyBitmap(bitmap)) {
            return null;
        }
        Bitmap extractAlpha = bitmap.extractAlpha();
        if (bool.booleanValue() && !bitmap.isRecycled() && extractAlpha != bitmap) {
            bitmap.recycle();
        }
        return extractAlpha;
    }

    public static Bitmap toGray(Bitmap bitmap) {
        return toGray(bitmap, false);
    }

    public static Bitmap toGray(Bitmap bitmap, boolean z) {
        if (isEmptyBitmap(bitmap)) {
            return null;
        }
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0.0f);
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        if (z && !bitmap.isRecycled() && createBitmap != bitmap) {
            bitmap.recycle();
        }
        return createBitmap;
    }

    public static Bitmap fastBlur(Bitmap bitmap, @FloatRange(from = 0.0d, fromInclusive = false, mo652to = 1.0d) float f, @FloatRange(from = 0.0d, fromInclusive = false, mo652to = 25.0d) float f2) {
        return fastBlur(bitmap, f, f2, false, false);
    }

    public static Bitmap fastBlur(Bitmap bitmap, @FloatRange(from = 0.0d, fromInclusive = false, mo652to = 1.0d) float f, @FloatRange(from = 0.0d, fromInclusive = false, mo652to = 25.0d) float f2, boolean z) {
        return fastBlur(bitmap, f, f2, z, false);
    }

    public static Bitmap fastBlur(Bitmap bitmap, @FloatRange(from = 0.0d, fromInclusive = false, mo652to = 1.0d) float f, @FloatRange(from = 0.0d, fromInclusive = false, mo652to = 25.0d) float f2, boolean z, boolean z2) {
        Bitmap bitmap2;
        if (isEmptyBitmap(bitmap)) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.setScale(f, f);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        Paint paint = new Paint(3);
        Canvas canvas = new Canvas();
        paint.setColorFilter(new PorterDuffColorFilter(0, PorterDuff.Mode.SRC_ATOP));
        canvas.scale(f, f);
        canvas.drawBitmap(createBitmap, 0.0f, 0.0f, paint);
        if (Build.VERSION.SDK_INT >= 17) {
            bitmap2 = renderScriptBlur(createBitmap, f2, z);
        } else {
            bitmap2 = stackBlur(createBitmap, (int) f2, z);
        }
        if (f == 1.0f || z2) {
            if (z && !bitmap.isRecycled() && bitmap2 != bitmap) {
                bitmap.recycle();
            }
            return bitmap2;
        }
        Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap2, width, height, true);
        if (!bitmap2.isRecycled()) {
            bitmap2.recycle();
        }
        if (z && !bitmap.isRecycled() && createScaledBitmap != bitmap) {
            bitmap.recycle();
        }
        return createScaledBitmap;
    }

    @RequiresApi(17)
    public static Bitmap renderScriptBlur(Bitmap bitmap, @FloatRange(from = 0.0d, fromInclusive = false, mo652to = 25.0d) float f) {
        return renderScriptBlur(bitmap, f, false);
    }

    @RequiresApi(17)
    public static Bitmap renderScriptBlur(Bitmap bitmap, @FloatRange(from = 0.0d, fromInclusive = false, mo652to = 25.0d) float f, boolean z) {
        if (!z) {
            bitmap = bitmap.copy(bitmap.getConfig(), true);
        }
        RenderScript renderScript = null;
        try {
            renderScript = RenderScript.create(Utils.getApp());
            renderScript.setMessageHandler(new RenderScript.RSMessageHandler());
            Allocation createFromBitmap = Allocation.createFromBitmap(renderScript, bitmap, Allocation.MipmapControl.MIPMAP_NONE, 1);
            Allocation createTyped = Allocation.createTyped(renderScript, createFromBitmap.getType());
            ScriptIntrinsicBlur create = ScriptIntrinsicBlur.create(renderScript, Element.U8_4(renderScript));
            create.setInput(createFromBitmap);
            create.setRadius(f);
            create.forEach(createTyped);
            createTyped.copyTo(bitmap);
            return bitmap;
        } finally {
            if (renderScript != null) {
                renderScript.destroy();
            }
        }
    }

    public static Bitmap stackBlur(Bitmap bitmap, int i) {
        return stackBlur(bitmap, i, false);
    }

    public static Bitmap stackBlur(Bitmap bitmap, int i, boolean z) {
        int i2;
        int[] iArr;
        Bitmap copy = z ? bitmap : bitmap.copy(bitmap.getConfig(), true);
        int i3 = i;
        int i4 = i3 < 1 ? 1 : i3;
        int width = copy.getWidth();
        int height = copy.getHeight();
        int i5 = width * height;
        int[] iArr2 = new int[i5];
        copy.getPixels(iArr2, 0, width, 0, 0, width, height);
        int i6 = width - 1;
        int i7 = height - 1;
        int i8 = i4 + i4 + 1;
        int[] iArr3 = new int[i5];
        int[] iArr4 = new int[i5];
        int[] iArr5 = new int[i5];
        int[] iArr6 = new int[Math.max(width, height)];
        int i9 = (i8 + 1) >> 1;
        int i10 = i9 * i9;
        int i11 = i10 * 256;
        int[] iArr7 = new int[i11];
        for (int i12 = 0; i12 < i11; i12++) {
            iArr7[i12] = i12 / i10;
        }
        int[][] iArr8 = (int[][]) Array.newInstance(int.class, new int[]{i8, 3});
        int i13 = i4 + 1;
        int i14 = 0;
        int i15 = 0;
        int i16 = 0;
        while (i14 < height) {
            Bitmap bitmap2 = copy;
            int i17 = -i4;
            int i18 = 0;
            int i19 = 0;
            int i20 = 0;
            int i21 = 0;
            int i22 = 0;
            int i23 = 0;
            int i24 = 0;
            int i25 = 0;
            int i26 = 0;
            while (i17 <= i4) {
                int i27 = i7;
                int i28 = height;
                int i29 = iArr2[i15 + Math.min(i6, Math.max(i17, 0))];
                int[] iArr9 = iArr8[i17 + i4];
                iArr9[0] = (i29 & 16711680) >> 16;
                iArr9[1] = (i29 & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
                iArr9[2] = i29 & 255;
                int abs = i13 - Math.abs(i17);
                i18 += iArr9[0] * abs;
                i19 += iArr9[1] * abs;
                i20 += iArr9[2] * abs;
                if (i17 > 0) {
                    i24 += iArr9[0];
                    i25 += iArr9[1];
                    i26 += iArr9[2];
                } else {
                    i21 += iArr9[0];
                    i22 += iArr9[1];
                    i23 += iArr9[2];
                }
                i17++;
                height = i28;
                i7 = i27;
            }
            int i30 = i7;
            int i31 = height;
            int i32 = i4;
            int i33 = 0;
            while (i33 < width) {
                iArr3[i15] = iArr7[i18];
                iArr4[i15] = iArr7[i19];
                iArr5[i15] = iArr7[i20];
                int i34 = i18 - i21;
                int i35 = i19 - i22;
                int i36 = i20 - i23;
                int[] iArr10 = iArr8[((i32 - i4) + i8) % i8];
                int i37 = i21 - iArr10[0];
                int i38 = i22 - iArr10[1];
                int i39 = i23 - iArr10[2];
                if (i14 == 0) {
                    iArr = iArr7;
                    iArr6[i33] = Math.min(i33 + i4 + 1, i6);
                } else {
                    iArr = iArr7;
                }
                int i40 = iArr2[i16 + iArr6[i33]];
                iArr10[0] = (i40 & 16711680) >> 16;
                iArr10[1] = (i40 & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
                iArr10[2] = i40 & 255;
                int i41 = i24 + iArr10[0];
                int i42 = i25 + iArr10[1];
                int i43 = i26 + iArr10[2];
                i18 = i34 + i41;
                i19 = i35 + i42;
                i20 = i36 + i43;
                i32 = (i32 + 1) % i8;
                int[] iArr11 = iArr8[i32 % i8];
                i21 = i37 + iArr11[0];
                i22 = i38 + iArr11[1];
                i23 = i39 + iArr11[2];
                i24 = i41 - iArr11[0];
                i25 = i42 - iArr11[1];
                i26 = i43 - iArr11[2];
                i15++;
                i33++;
                iArr7 = iArr;
            }
            int[] iArr12 = iArr7;
            i16 += width;
            i14++;
            height = i31;
            copy = bitmap2;
            i7 = i30;
        }
        int i44 = i7;
        Bitmap bitmap3 = copy;
        int i45 = height;
        int[] iArr13 = iArr7;
        int i46 = 0;
        while (i46 < width) {
            int i47 = -i4;
            int i48 = i47 * width;
            int i49 = 0;
            int i50 = 0;
            int i51 = 0;
            int i52 = 0;
            int i53 = 0;
            int i54 = 0;
            int i55 = 0;
            int i56 = 0;
            int i57 = 0;
            while (i47 <= i4) {
                int[] iArr14 = iArr6;
                int max = Math.max(0, i48) + i46;
                int[] iArr15 = iArr8[i47 + i4];
                iArr15[0] = iArr3[max];
                iArr15[1] = iArr4[max];
                iArr15[2] = iArr5[max];
                int abs2 = i13 - Math.abs(i47);
                i49 += iArr3[max] * abs2;
                i50 += iArr4[max] * abs2;
                i51 += iArr5[max] * abs2;
                if (i47 > 0) {
                    i55 += iArr15[0];
                    i56 += iArr15[1];
                    i57 += iArr15[2];
                } else {
                    i52 += iArr15[0];
                    i53 += iArr15[1];
                    i54 += iArr15[2];
                }
                int i58 = i44;
                if (i47 < i58) {
                    i48 += width;
                }
                i47++;
                i44 = i58;
                iArr6 = iArr14;
            }
            int[] iArr16 = iArr6;
            int i59 = i44;
            int i60 = i45;
            int i61 = i56;
            int i62 = i57;
            int i63 = 0;
            int i64 = i4;
            int i65 = i55;
            int i66 = i54;
            int i67 = i53;
            int i68 = i52;
            int i69 = i51;
            int i70 = i50;
            int i71 = i49;
            int i72 = i46;
            while (i63 < i60) {
                iArr2[i72] = (iArr2[i72] & ViewCompat.MEASURED_STATE_MASK) | (iArr13[i71] << 16) | (iArr13[i70] << 8) | iArr13[i69];
                int i73 = i71 - i68;
                int i74 = i70 - i67;
                int i75 = i69 - i66;
                int[] iArr17 = iArr8[((i64 - i4) + i8) % i8];
                int i76 = i68 - iArr17[0];
                int i77 = i67 - iArr17[1];
                int i78 = i66 - iArr17[2];
                if (i46 == 0) {
                    i2 = i4;
                    iArr16[i63] = Math.min(i63 + i13, i59) * width;
                } else {
                    i2 = i4;
                }
                int i79 = iArr16[i63] + i46;
                iArr17[0] = iArr3[i79];
                iArr17[1] = iArr4[i79];
                iArr17[2] = iArr5[i79];
                int i80 = i65 + iArr17[0];
                int i81 = i61 + iArr17[1];
                int i82 = i62 + iArr17[2];
                i71 = i73 + i80;
                i70 = i74 + i81;
                i69 = i75 + i82;
                i64 = (i64 + 1) % i8;
                int[] iArr18 = iArr8[i64];
                i68 = i76 + iArr18[0];
                i67 = i77 + iArr18[1];
                i66 = i78 + iArr18[2];
                i65 = i80 - iArr18[0];
                i61 = i81 - iArr18[1];
                i62 = i82 - iArr18[2];
                i72 += width;
                i63++;
                i4 = i2;
            }
            int i83 = i4;
            i46++;
            i44 = i59;
            i45 = i60;
            iArr6 = iArr16;
        }
        bitmap3.setPixels(iArr2, 0, width, 0, 0, width, i45);
        return bitmap3;
    }

    public static boolean save(Bitmap bitmap, String str, Bitmap.CompressFormat compressFormat) {
        return save(bitmap, str, compressFormat, 100, false);
    }

    public static boolean save(Bitmap bitmap, File file, Bitmap.CompressFormat compressFormat) {
        return save(bitmap, file, compressFormat, 100, false);
    }

    public static boolean save(Bitmap bitmap, String str, Bitmap.CompressFormat compressFormat, boolean z) {
        return save(bitmap, str, compressFormat, 100, z);
    }

    public static boolean save(Bitmap bitmap, File file, Bitmap.CompressFormat compressFormat, boolean z) {
        return save(bitmap, file, compressFormat, 100, z);
    }

    public static boolean save(Bitmap bitmap, String str, Bitmap.CompressFormat compressFormat, int i) {
        return save(bitmap, UtilsBridge.getFileByPath(str), compressFormat, i, false);
    }

    public static boolean save(Bitmap bitmap, File file, Bitmap.CompressFormat compressFormat, int i) {
        return save(bitmap, file, compressFormat, i, false);
    }

    public static boolean save(Bitmap bitmap, String str, Bitmap.CompressFormat compressFormat, int i, boolean z) {
        return save(bitmap, UtilsBridge.getFileByPath(str), compressFormat, i, z);
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x006c A[SYNTHETIC, Splitter:B:34:0x006c] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0072 A[SYNTHETIC, Splitter:B:38:0x0072] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean save(android.graphics.Bitmap r4, java.io.File r5, android.graphics.Bitmap.CompressFormat r6, int r7, boolean r8) {
        /*
            boolean r0 = isEmptyBitmap(r4)
            java.lang.String r1 = "ImageUtils"
            r2 = 0
            if (r0 == 0) goto L_0x000f
            java.lang.String r4 = "bitmap is empty."
            android.util.Log.e(r1, r4)
            return r2
        L_0x000f:
            boolean r0 = r4.isRecycled()
            if (r0 == 0) goto L_0x001b
            java.lang.String r4 = "bitmap is recycled."
            android.util.Log.e(r1, r4)
            return r2
        L_0x001b:
            boolean r0 = com.blankj.utilcode.util.UtilsBridge.createFileByDeleteOldFile(r5)
            if (r0 != 0) goto L_0x003b
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r6 = "create or delete file <"
            r4.append(r6)
            r4.append(r5)
            java.lang.String r5 = "> failed."
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            android.util.Log.e(r1, r4)
            return r2
        L_0x003b:
            r0 = 0
            java.io.BufferedOutputStream r1 = new java.io.BufferedOutputStream     // Catch:{ IOException -> 0x0066 }
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x0066 }
            r3.<init>(r5)     // Catch:{ IOException -> 0x0066 }
            r1.<init>(r3)     // Catch:{ IOException -> 0x0066 }
            boolean r2 = r4.compress(r6, r7, r1)     // Catch:{ IOException -> 0x0061, all -> 0x005e }
            if (r8 == 0) goto L_0x0055
            boolean r5 = r4.isRecycled()     // Catch:{ IOException -> 0x0061, all -> 0x005e }
            if (r5 != 0) goto L_0x0055
            r4.recycle()     // Catch:{ IOException -> 0x0061, all -> 0x005e }
        L_0x0055:
            r1.close()     // Catch:{ IOException -> 0x0059 }
            goto L_0x006f
        L_0x0059:
            r4 = move-exception
            r4.printStackTrace()
            goto L_0x006f
        L_0x005e:
            r4 = move-exception
            r0 = r1
            goto L_0x0070
        L_0x0061:
            r4 = move-exception
            r0 = r1
            goto L_0x0067
        L_0x0064:
            r4 = move-exception
            goto L_0x0070
        L_0x0066:
            r4 = move-exception
        L_0x0067:
            r4.printStackTrace()     // Catch:{ all -> 0x0064 }
            if (r0 == 0) goto L_0x006f
            r0.close()     // Catch:{ IOException -> 0x0059 }
        L_0x006f:
            return r2
        L_0x0070:
            if (r0 == 0) goto L_0x007a
            r0.close()     // Catch:{ IOException -> 0x0076 }
            goto L_0x007a
        L_0x0076:
            r5 = move-exception
            r5.printStackTrace()
        L_0x007a:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.blankj.utilcode.util.ImageUtils.save(android.graphics.Bitmap, java.io.File, android.graphics.Bitmap$CompressFormat, int, boolean):boolean");
    }

    @Nullable
    public static File save2Album(Bitmap bitmap, Bitmap.CompressFormat compressFormat) {
        return save2Album(bitmap, compressFormat, 100, false);
    }

    @Nullable
    public static File save2Album(Bitmap bitmap, Bitmap.CompressFormat compressFormat, boolean z) {
        return save2Album(bitmap, compressFormat, 100, z);
    }

    @Nullable
    public static File save2Album(Bitmap bitmap, Bitmap.CompressFormat compressFormat, int i) {
        return save2Album(bitmap, compressFormat, i, false);
    }

    /* JADX WARNING: Removed duplicated region for block: B:40:0x0123 A[SYNTHETIC, Splitter:B:40:0x0123] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x012f A[SYNTHETIC, Splitter:B:47:0x012f] */
    @androidx.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.io.File save2Album(android.graphics.Bitmap r7, android.graphics.Bitmap.CompressFormat r8, int r9, boolean r10) {
        /*
            android.graphics.Bitmap$CompressFormat r0 = android.graphics.Bitmap.CompressFormat.JPEG
            boolean r0 = r0.equals(r8)
            if (r0 == 0) goto L_0x000b
            java.lang.String r0 = "JPG"
            goto L_0x000f
        L_0x000b:
            java.lang.String r0 = r8.name()
        L_0x000f:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            long r2 = java.lang.System.currentTimeMillis()
            r1.append(r2)
            java.lang.String r2 = "_"
            r1.append(r2)
            r1.append(r9)
            java.lang.String r2 = "."
            r1.append(r2)
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            int r1 = android.os.Build.VERSION.SDK_INT
            r2 = 29
            java.lang.String r3 = "/"
            r4 = 0
            if (r1 >= r2) goto L_0x007c
            java.lang.String r1 = "android.permission.WRITE_EXTERNAL_STORAGE"
            java.lang.String[] r1 = new java.lang.String[]{r1}
            boolean r1 = com.blankj.utilcode.util.UtilsBridge.isGranted(r1)
            if (r1 != 0) goto L_0x004c
            java.lang.String r7 = "ImageUtils"
            java.lang.String r8 = "save to album need storage permission"
            android.util.Log.e(r7, r8)
            return r4
        L_0x004c:
            java.lang.String r1 = android.os.Environment.DIRECTORY_DCIM
            java.io.File r1 = android.os.Environment.getExternalStoragePublicDirectory(r1)
            java.io.File r2 = new java.io.File
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            android.app.Application r6 = com.blankj.utilcode.util.Utils.getApp()
            java.lang.String r6 = r6.getPackageName()
            r5.append(r6)
            r5.append(r3)
            r5.append(r0)
            java.lang.String r0 = r5.toString()
            r2.<init>(r1, r0)
            boolean r7 = save((android.graphics.Bitmap) r7, (java.io.File) r2, (android.graphics.Bitmap.CompressFormat) r8, (int) r9, (boolean) r10)
            if (r7 != 0) goto L_0x0078
            return r4
        L_0x0078:
            com.blankj.utilcode.util.UtilsBridge.notifySystemToScan(r2)
            return r2
        L_0x007c:
            android.content.ContentValues r10 = new android.content.ContentValues
            r10.<init>()
            java.lang.String r1 = "_display_name"
            r10.put(r1, r0)
            java.lang.String r0 = "mime_type"
            java.lang.String r1 = "image/*"
            r10.put(r0, r1)
            java.lang.String r0 = android.os.Environment.getExternalStorageState()
            java.lang.String r1 = "mounted"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x009c
            android.net.Uri r0 = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            goto L_0x009e
        L_0x009c:
            android.net.Uri r0 = android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI
        L_0x009e:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = android.os.Environment.DIRECTORY_DCIM
            r1.append(r2)
            r1.append(r3)
            android.app.Application r2 = com.blankj.utilcode.util.Utils.getApp()
            java.lang.String r2 = r2.getPackageName()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.lang.String r2 = "relative_path"
            r10.put(r2, r1)
            r1 = 1
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            java.lang.String r2 = "is_pending"
            r10.put(r2, r1)
            android.app.Application r1 = com.blankj.utilcode.util.Utils.getApp()
            android.content.ContentResolver r1 = r1.getContentResolver()
            android.net.Uri r0 = r1.insert(r0, r10)
            if (r0 != 0) goto L_0x00d8
            return r4
        L_0x00d8:
            android.app.Application r1 = com.blankj.utilcode.util.Utils.getApp()     // Catch:{ Exception -> 0x0111, all -> 0x010e }
            android.content.ContentResolver r1 = r1.getContentResolver()     // Catch:{ Exception -> 0x0111, all -> 0x010e }
            java.io.OutputStream r1 = r1.openOutputStream(r0)     // Catch:{ Exception -> 0x0111, all -> 0x010e }
            r7.compress(r8, r9, r1)     // Catch:{ Exception -> 0x010c }
            r10.clear()     // Catch:{ Exception -> 0x010c }
            r7 = 0
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)     // Catch:{ Exception -> 0x010c }
            r10.put(r2, r7)     // Catch:{ Exception -> 0x010c }
            android.app.Application r7 = com.blankj.utilcode.util.Utils.getApp()     // Catch:{ Exception -> 0x010c }
            android.content.ContentResolver r7 = r7.getContentResolver()     // Catch:{ Exception -> 0x010c }
            r7.update(r0, r10, r4, r4)     // Catch:{ Exception -> 0x010c }
            java.io.File r7 = com.blankj.utilcode.util.UtilsBridge.uri2File(r0)     // Catch:{ Exception -> 0x010c }
            if (r1 == 0) goto L_0x010b
            r1.close()     // Catch:{ IOException -> 0x0107 }
            goto L_0x010b
        L_0x0107:
            r8 = move-exception
            r8.printStackTrace()
        L_0x010b:
            return r7
        L_0x010c:
            r7 = move-exception
            goto L_0x0113
        L_0x010e:
            r7 = move-exception
            r1 = r4
            goto L_0x012d
        L_0x0111:
            r7 = move-exception
            r1 = r4
        L_0x0113:
            android.app.Application r8 = com.blankj.utilcode.util.Utils.getApp()     // Catch:{ all -> 0x012c }
            android.content.ContentResolver r8 = r8.getContentResolver()     // Catch:{ all -> 0x012c }
            r8.delete(r0, r4, r4)     // Catch:{ all -> 0x012c }
            r7.printStackTrace()     // Catch:{ all -> 0x012c }
            if (r1 == 0) goto L_0x012b
            r1.close()     // Catch:{ IOException -> 0x0127 }
            goto L_0x012b
        L_0x0127:
            r7 = move-exception
            r7.printStackTrace()
        L_0x012b:
            return r4
        L_0x012c:
            r7 = move-exception
        L_0x012d:
            if (r1 == 0) goto L_0x0137
            r1.close()     // Catch:{ IOException -> 0x0133 }
            goto L_0x0137
        L_0x0133:
            r8 = move-exception
            r8.printStackTrace()
        L_0x0137:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.blankj.utilcode.util.ImageUtils.save2Album(android.graphics.Bitmap, android.graphics.Bitmap$CompressFormat, int, boolean):java.io.File");
    }

    public static boolean isImage(File file) {
        if (file == null || !file.exists()) {
            return false;
        }
        return isImage(file.getPath());
    }

    public static boolean isImage(String str) {
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(str, options);
            if (options.outWidth <= 0 || options.outHeight <= 0) {
                return false;
            }
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static ImageType getImageType(String str) {
        return getImageType(UtilsBridge.getFileByPath(str));
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0027 A[SYNTHETIC, Splitter:B:22:0x0027] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0034 A[SYNTHETIC, Splitter:B:30:0x0034] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.blankj.utilcode.util.ImageUtils.ImageType getImageType(java.io.File r2) {
        /*
            r0 = 0
            if (r2 != 0) goto L_0x0004
            return r0
        L_0x0004:
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0020, all -> 0x001e }
            r1.<init>(r2)     // Catch:{ IOException -> 0x0020, all -> 0x001e }
            com.blankj.utilcode.util.ImageUtils$ImageType r2 = getImageType((java.io.InputStream) r1)     // Catch:{ IOException -> 0x001c }
            if (r2 == 0) goto L_0x0018
            r1.close()     // Catch:{ IOException -> 0x0013 }
            goto L_0x0017
        L_0x0013:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0017:
            return r2
        L_0x0018:
            r1.close()     // Catch:{ IOException -> 0x002b }
            goto L_0x002f
        L_0x001c:
            r2 = move-exception
            goto L_0x0022
        L_0x001e:
            r2 = move-exception
            goto L_0x0032
        L_0x0020:
            r2 = move-exception
            r1 = r0
        L_0x0022:
            r2.printStackTrace()     // Catch:{ all -> 0x0030 }
            if (r1 == 0) goto L_0x002f
            r1.close()     // Catch:{ IOException -> 0x002b }
            goto L_0x002f
        L_0x002b:
            r2 = move-exception
            r2.printStackTrace()
        L_0x002f:
            return r0
        L_0x0030:
            r2 = move-exception
            r0 = r1
        L_0x0032:
            if (r0 == 0) goto L_0x003c
            r0.close()     // Catch:{ IOException -> 0x0038 }
            goto L_0x003c
        L_0x0038:
            r0 = move-exception
            r0.printStackTrace()
        L_0x003c:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.blankj.utilcode.util.ImageUtils.getImageType(java.io.File):com.blankj.utilcode.util.ImageUtils$ImageType");
    }

    private static ImageType getImageType(InputStream inputStream) {
        if (inputStream == null) {
            return null;
        }
        try {
            byte[] bArr = new byte[12];
            if (inputStream.read(bArr) != -1) {
                return getImageType(bArr);
            }
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static ImageType getImageType(byte[] bArr) {
        String upperCase = UtilsBridge.bytes2HexString(bArr).toUpperCase();
        if (upperCase.contains("FFD8FF")) {
            return ImageType.TYPE_JPG;
        }
        if (upperCase.contains("89504E47")) {
            return ImageType.TYPE_PNG;
        }
        if (upperCase.contains("47494638")) {
            return ImageType.TYPE_GIF;
        }
        if (upperCase.contains("49492A00") || upperCase.contains("4D4D002A")) {
            return ImageType.TYPE_TIFF;
        }
        if (upperCase.contains("424D")) {
            return ImageType.TYPE_BMP;
        }
        if (upperCase.startsWith("52494646") && upperCase.endsWith("57454250")) {
            return ImageType.TYPE_WEBP;
        }
        if (upperCase.contains("00000100") || upperCase.contains("00000200")) {
            return ImageType.TYPE_ICO;
        }
        return ImageType.TYPE_UNKNOWN;
    }

    private static boolean isJPEG(byte[] bArr) {
        return bArr.length >= 2 && bArr[0] == -1 && bArr[1] == -40;
    }

    private static boolean isGIF(byte[] bArr) {
        return bArr.length >= 6 && bArr[0] == 71 && bArr[1] == 73 && bArr[2] == 70 && bArr[3] == 56 && (bArr[4] == 55 || bArr[4] == 57) && bArr[5] == 97;
    }

    private static boolean isPNG(byte[] bArr) {
        return bArr.length >= 8 && bArr[0] == -119 && bArr[1] == 80 && bArr[2] == 78 && bArr[3] == 71 && bArr[4] == 13 && bArr[5] == 10 && bArr[6] == 26 && bArr[7] == 10;
    }

    private static boolean isBMP(byte[] bArr) {
        return bArr.length >= 2 && bArr[0] == 66 && bArr[1] == 77;
    }

    private static boolean isEmptyBitmap(Bitmap bitmap) {
        return bitmap == null || bitmap.getWidth() == 0 || bitmap.getHeight() == 0;
    }

    public static Bitmap compressByScale(Bitmap bitmap, int i, int i2) {
        return scale(bitmap, i, i2, false);
    }

    public static Bitmap compressByScale(Bitmap bitmap, int i, int i2, boolean z) {
        return scale(bitmap, i, i2, z);
    }

    public static Bitmap compressByScale(Bitmap bitmap, float f, float f2) {
        return scale(bitmap, f, f2, false);
    }

    public static Bitmap compressByScale(Bitmap bitmap, float f, float f2, boolean z) {
        return scale(bitmap, f, f2, z);
    }

    public static byte[] compressByQuality(Bitmap bitmap, @IntRange(from = 0, mo670to = 100) int i) {
        return compressByQuality(bitmap, i, false);
    }

    public static byte[] compressByQuality(Bitmap bitmap, @IntRange(from = 0, mo670to = 100) int i, boolean z) {
        if (isEmptyBitmap(bitmap)) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, i, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        if (z && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
        return byteArray;
    }

    public static byte[] compressByQuality(Bitmap bitmap, long j) {
        return compressByQuality(bitmap, j, false);
    }

    public static byte[] compressByQuality(Bitmap bitmap, long j, boolean z) {
        byte[] bArr;
        int i = 0;
        if (isEmptyBitmap(bitmap) || j <= 0) {
            return new byte[0];
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i2 = 100;
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        if (((long) byteArrayOutputStream.size()) <= j) {
            bArr = byteArrayOutputStream.toByteArray();
        } else {
            byteArrayOutputStream.reset();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 0, byteArrayOutputStream);
            if (((long) byteArrayOutputStream.size()) >= j) {
                bArr = byteArrayOutputStream.toByteArray();
            } else {
                int i3 = 0;
                while (i < i2) {
                    i3 = (i + i2) / 2;
                    byteArrayOutputStream.reset();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, i3, byteArrayOutputStream);
                    long size = (long) byteArrayOutputStream.size();
                    if (size == j) {
                        break;
                    } else if (size > j) {
                        i2 = i3 - 1;
                    } else {
                        i = i3 + 1;
                    }
                }
                if (i2 == i3 - 1) {
                    byteArrayOutputStream.reset();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, i, byteArrayOutputStream);
                }
                bArr = byteArrayOutputStream.toByteArray();
            }
        }
        if (z && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
        return bArr;
    }

    public static Bitmap compressBySampleSize(Bitmap bitmap, int i) {
        return compressBySampleSize(bitmap, i, false);
    }

    public static Bitmap compressBySampleSize(Bitmap bitmap, int i, boolean z) {
        if (isEmptyBitmap(bitmap)) {
            return null;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = i;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        if (z && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length, options);
    }

    public static Bitmap compressBySampleSize(Bitmap bitmap, int i, int i2) {
        return compressBySampleSize(bitmap, i, i2, false);
    }

    public static Bitmap compressBySampleSize(Bitmap bitmap, int i, int i2, boolean z) {
        if (isEmptyBitmap(bitmap)) {
            return null;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length, options);
        options.inSampleSize = calculateInSampleSize(options, i, i2);
        options.inJustDecodeBounds = false;
        if (z && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length, options);
    }

    public static int[] getSize(String str) {
        return getSize(UtilsBridge.getFileByPath(str));
    }

    public static int[] getSize(File file) {
        if (file == null) {
            return new int[]{0, 0};
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(file.getAbsolutePath(), options);
        return new int[]{options.outWidth, options.outHeight};
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int i, int i2) {
        int i3 = options.outHeight;
        int i4 = options.outWidth;
        int i5 = 1;
        while (true) {
            if (i3 <= i2 && i4 <= i) {
                return i5;
            }
            i3 >>= 1;
            i4 >>= 1;
            i5 <<= 1;
        }
    }

    public enum ImageType {
        TYPE_JPG("jpg"),
        TYPE_PNG("png"),
        TYPE_GIF("gif"),
        TYPE_TIFF("tiff"),
        TYPE_BMP("bmp"),
        TYPE_WEBP("webp"),
        TYPE_ICO("ico"),
        TYPE_UNKNOWN("unknown");
        
        String value;

        private ImageType(String str) {
            this.value = str;
        }

        public String getValue() {
            return this.value;
        }
    }
}
