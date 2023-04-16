package com.huayu.tzc.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.zhihu.matisse.engine.ImageEngine;

public class GlideEngine implements ImageEngine {
    public boolean supportAnimatedGif() {
        return true;
    }

    public void loadThumbnail(Context context, int i, Drawable drawable, ImageView imageView, Uri uri) {
        Glide.with(context).asBitmap().load(uri).apply((BaseRequestOptions<?>) (RequestOptions) ((RequestOptions) ((RequestOptions) new RequestOptions().centerCrop()).placeholder(drawable)).override(i, i)).into(imageView);
    }

    public void loadGifThumbnail(Context context, int i, Drawable drawable, ImageView imageView, Uri uri) {
        Glide.with(context).asGif().load(uri).apply((BaseRequestOptions<?>) (RequestOptions) ((RequestOptions) ((RequestOptions) new RequestOptions().centerCrop()).placeholder(drawable)).override(i, i)).into(imageView);
    }

    public void loadImage(Context context, int i, int i2, ImageView imageView, Uri uri) {
        Glide.with(context).load(uri).apply((BaseRequestOptions<?>) (RequestOptions) ((RequestOptions) ((RequestOptions) new RequestOptions().centerCrop()).override(i, i2)).priority(Priority.HIGH)).into(imageView);
    }

    public void loadGifImage(Context context, int i, int i2, ImageView imageView, Uri uri) {
        Glide.with(context).asGif().load(uri).apply((BaseRequestOptions<?>) (RequestOptions) ((RequestOptions) new RequestOptions().centerCrop()).override(i, i2)).into(imageView);
    }
}
