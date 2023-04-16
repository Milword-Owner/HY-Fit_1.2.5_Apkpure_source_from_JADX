package com.huayu.tzc.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.previewlibrary.loader.IZoomMediaLoader;
import com.previewlibrary.loader.MySimpleTarget;

public class TestImageLoader implements IZoomMediaLoader {
    public void displayImage(@NonNull Fragment fragment, @NonNull String str, final ImageView imageView, @NonNull final MySimpleTarget mySimpleTarget) {
        Glide.with(fragment).asBitmap().load(str).apply((BaseRequestOptions<?>) new RequestOptions().fitCenter()).into(new SimpleTarget<Bitmap>() {
            public void onResourceReady(@NonNull Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {
                mySimpleTarget.onResourceReady();
                imageView.setImageBitmap(bitmap);
            }
        });
    }

    public void displayGifImage(@NonNull Fragment fragment, @NonNull String str, ImageView imageView, @NonNull final MySimpleTarget mySimpleTarget) {
        Glide.with(fragment).asGif().load(str).apply((BaseRequestOptions<?>) ((RequestOptions) new RequestOptions().diskCacheStrategy(DiskCacheStrategy.RESOURCE)).dontAnimate()).listener(new RequestListener<GifDrawable>() {
            public boolean onLoadFailed(@Nullable GlideException glideException, Object obj, Target<GifDrawable> target, boolean z) {
                mySimpleTarget.onResourceReady();
                return false;
            }

            public boolean onResourceReady(GifDrawable gifDrawable, Object obj, Target<GifDrawable> target, DataSource dataSource, boolean z) {
                mySimpleTarget.onLoadFailed((Drawable) null);
                return false;
            }
        }).into(imageView);
    }

    public void onStop(@NonNull Fragment fragment) {
        Glide.with(fragment).onStop();
    }

    public void clearMemory(@NonNull Context context) {
        Glide.get(context).clearMemory();
    }
}
