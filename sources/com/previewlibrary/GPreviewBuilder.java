package com.previewlibrary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.previewlibrary.enitity.IThumbViewInfo;
import com.previewlibrary.loader.VideoClickListener;
import com.previewlibrary.view.BasePhotoFragment;
import java.util.ArrayList;
import java.util.List;

public final class GPreviewBuilder {
    private Class className;
    private Intent intent = new Intent();
    private Activity mContext;
    private VideoClickListener videoClickListener;

    public enum IndicatorType {
        Dot,
        Number
    }

    private GPreviewBuilder(@NonNull Activity activity) {
        this.mContext = activity;
    }

    public static GPreviewBuilder from(@NonNull Activity activity) {
        return new GPreviewBuilder(activity);
    }

    public static GPreviewBuilder from(@NonNull Fragment fragment) {
        return new GPreviewBuilder(fragment.getActivity());
    }

    /* renamed from: to */
    public GPreviewBuilder mo30402to(@NonNull Class cls) {
        this.className = cls;
        this.intent.setClass(this.mContext, cls);
        return this;
    }

    /* renamed from: to */
    public GPreviewBuilder mo30403to(@NonNull Class cls, @NonNull Bundle bundle) {
        this.className = cls;
        this.intent.setClass(this.mContext, cls);
        this.intent.putExtras(bundle);
        return this;
    }

    public <T extends IThumbViewInfo> GPreviewBuilder setData(@NonNull List<T> list) {
        this.intent.putParcelableArrayListExtra("imagePaths", new ArrayList(list));
        return this;
    }

    public <E extends IThumbViewInfo> GPreviewBuilder setSingleData(@NonNull E e) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(e);
        this.intent.putParcelableArrayListExtra("imagePaths", arrayList);
        return this;
    }

    public GPreviewBuilder setUserFragment(@NonNull Class<? extends BasePhotoFragment> cls) {
        this.intent.putExtra("className", cls);
        return this;
    }

    public GPreviewBuilder setCurrentIndex(int i) {
        this.intent.putExtra("position", i);
        return this;
    }

    public GPreviewBuilder setType(@NonNull IndicatorType indicatorType) {
        this.intent.putExtra("type", indicatorType);
        return this;
    }

    public GPreviewBuilder setDrag(boolean z) {
        this.intent.putExtra("isDrag", z);
        return this;
    }

    public GPreviewBuilder setDrag(boolean z, float f) {
        this.intent.putExtra("isDrag", z);
        this.intent.putExtra("sensitivity", f);
        return this;
    }

    public GPreviewBuilder setSingleShowType(boolean z) {
        this.intent.putExtra("isShow", z);
        return this;
    }

    public GPreviewBuilder setSingleFling(boolean z) {
        this.intent.putExtra("isSingleFling", z);
        return this;
    }

    public GPreviewBuilder setDuration(int i) {
        this.intent.putExtra("duration", i);
        return this;
    }

    public GPreviewBuilder setFullscreen(boolean z) {
        this.intent.putExtra("isFullscreen", z);
        return this;
    }

    public GPreviewBuilder setIsScale(boolean z) {
        this.intent.putExtra("isScale", z);
        return this;
    }

    public GPreviewBuilder setOnVideoPlayerListener(VideoClickListener videoClickListener2) {
        this.videoClickListener = videoClickListener2;
        return this;
    }

    public void start() {
        Class cls = this.className;
        if (cls == null) {
            this.intent.setClass(this.mContext, GPreviewActivity.class);
        } else {
            this.intent.setClass(this.mContext, cls);
        }
        BasePhotoFragment.listener = this.videoClickListener;
        this.mContext.startActivity(this.intent);
        this.mContext.overridePendingTransition(0, 0);
        this.intent = null;
        this.mContext = null;
    }
}
