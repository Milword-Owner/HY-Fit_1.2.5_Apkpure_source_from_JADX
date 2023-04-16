package com.huayu.tzc.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.p000v4.media.session.PlaybackStateCompat;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import androidx.collection.LruCache;
import androidx.recyclerview.widget.RecyclerView;

public class SystemUtil {
    public static int dpToPx(Context context, float f) {
        return (int) TypedValue.applyDimension(1, f, context.getResources().getDisplayMetrics());
    }

    public static float dpToPx2(Context context, float f) {
        return TypedValue.applyDimension(1, f, context.getResources().getDisplayMetrics());
    }

    public static int spToPx(Context context, float f) {
        return (int) TypedValue.applyDimension(2, f, context.getResources().getDisplayMetrics());
    }

    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public static Bitmap snapShotWithoutStatusBar(Activity activity) {
        View decorView = activity.getWindow().getDecorView();
        decorView.setDrawingCacheEnabled(true);
        decorView.buildDrawingCache();
        Bitmap drawingCache = decorView.getDrawingCache();
        Rect rect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        int i = rect.top;
        int[] screen = getScreen(activity);
        Bitmap createBitmap = Bitmap.createBitmap(drawingCache, 0, i, screen[0], screen[1] - i);
        decorView.destroyDrawingCache();
        return createBitmap;
    }

    public static int[] getScreen(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return new int[]{displayMetrics.widthPixels, displayMetrics.heightPixels};
    }

    public static Bitmap snapRecyclerView(Activity activity, RecyclerView recyclerView) {
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter == null) {
            return null;
        }
        int itemCount = adapter.getItemCount();
        Paint paint = new Paint();
        LruCache lruCache = new LruCache(((int) (Runtime.getRuntime().maxMemory() / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID)) / 8);
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < itemCount; i3++) {
            RecyclerView.ViewHolder createViewHolder = adapter.createViewHolder(recyclerView, adapter.getItemViewType(i3));
            adapter.onBindViewHolder(createViewHolder, i3);
            createViewHolder.itemView.measure(View.MeasureSpec.makeMeasureSpec(recyclerView.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(0, 0));
            createViewHolder.itemView.layout(0, 0, createViewHolder.itemView.getMeasuredWidth(), createViewHolder.itemView.getMeasuredHeight());
            createViewHolder.itemView.setDrawingCacheEnabled(true);
            createViewHolder.itemView.buildDrawingCache();
            Bitmap drawingCache = createViewHolder.itemView.getDrawingCache(true);
            if (drawingCache != null) {
                lruCache.put(String.valueOf(i3), drawingCache);
            }
            i += createViewHolder.itemView.getMeasuredHeight();
            if (i3 == 0) {
                i2 = i;
            }
        }
        Bitmap createBitmap = Bitmap.createBitmap(recyclerView.getMeasuredWidth(), i, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Drawable background = recyclerView.getBackground();
        if (background instanceof ColorDrawable) {
            canvas.drawColor(((ColorDrawable) background).getColor());
        }
        View decorView = activity.getWindow().getDecorView();
        decorView.setDrawingCacheEnabled(true);
        decorView.buildDrawingCache();
        Bitmap drawingCache2 = decorView.getDrawingCache();
        Rect rect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        int i4 = rect.top;
        lruCache.put("0", Bitmap.createBitmap(drawingCache2, 0, i4, getScreen(activity)[0], i2 - i4));
        int i5 = 0;
        for (int i6 = 0; i6 < itemCount; i6++) {
            Bitmap bitmap = (Bitmap) lruCache.get(String.valueOf(i6));
            if (bitmap != null) {
                canvas.drawBitmap(bitmap, 0.0f, (float) i5, paint);
                i5 += bitmap.getHeight();
                bitmap.recycle();
            }
        }
        decorView.destroyDrawingCache();
        return createBitmap;
    }
}
