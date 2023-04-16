package com.previewlibrary.view;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.CallSuper;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import com.previewlibrary.C2517R;
import com.previewlibrary.GPVideoPlayerActivity;
import com.previewlibrary.GPreviewActivity;
import com.previewlibrary.ZoomMediaLoader;
import com.previewlibrary.enitity.IThumbViewInfo;
import com.previewlibrary.loader.MySimpleTarget;
import com.previewlibrary.loader.VideoClickListener;
import com.previewlibrary.wight.SmoothImageView;
import p040uk.p041co.senab2.photoview2.PhotoViewAttacher;

public class BasePhotoFragment extends Fragment {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String KEY_DRAG = "isDrag";
    private static final String KEY_PATH = "key_item";
    private static final String KEY_SEN = "sensitivity";
    private static final String KEY_SING_FILING = "isSingleFling";
    private static final String KEY_TRANS_PHOTO = "is_trans_photo";
    public static VideoClickListener listener;
    /* access modifiers changed from: private */
    public IThumbViewInfo beanViewInfo;
    protected View btnVideo;
    protected SmoothImageView imageView;
    private boolean isTransPhoto = false;
    protected View loading;
    protected MySimpleTarget mySimpleTarget;
    protected View rootView;

    public static BasePhotoFragment getInstance(Class<? extends BasePhotoFragment> cls, IThumbViewInfo iThumbViewInfo, boolean z, boolean z2, boolean z3, float f) {
        BasePhotoFragment basePhotoFragment;
        try {
            basePhotoFragment = (BasePhotoFragment) cls.newInstance();
        } catch (Exception unused) {
            basePhotoFragment = new BasePhotoFragment();
        }
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_PATH, iThumbViewInfo);
        bundle.putBoolean(KEY_TRANS_PHOTO, z);
        bundle.putBoolean(KEY_SING_FILING, z2);
        bundle.putBoolean(KEY_DRAG, z3);
        bundle.putFloat(KEY_SEN, f);
        basePhotoFragment.setArguments(bundle);
        return basePhotoFragment;
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        return layoutInflater.inflate(C2517R.C2521layout.fragment_image_photo_layout, viewGroup, false);
    }

    @CallSuper
    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        initView(view);
        initData();
    }

    @CallSuper
    public void onStop() {
        ZoomMediaLoader.getInstance().getLoader().onStop(this);
        super.onStop();
    }

    @CallSuper
    public void onDestroyView() {
        super.onDestroyView();
        release();
    }

    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
    }

    public void onDestroy() {
        super.onDestroy();
        ZoomMediaLoader.getInstance().getLoader().clearMemory(getActivity());
        if (getActivity() != null && getActivity().isFinishing()) {
            listener = null;
        }
    }

    public void release() {
        this.isTransPhoto = false;
    }

    private void initView(View view) {
        this.loading = view.findViewById(C2517R.C2520id.loading);
        this.imageView = (SmoothImageView) view.findViewById(C2517R.C2520id.photoView);
        this.btnVideo = view.findViewById(C2517R.C2520id.btnVideo);
        this.rootView = view.findViewById(C2517R.C2520id.rootView);
        this.rootView.setDrawingCacheEnabled(false);
        this.imageView.setDrawingCacheEnabled(false);
        this.btnVideo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String videoUrl = BasePhotoFragment.this.beanViewInfo.getVideoUrl();
                if (videoUrl != null && !videoUrl.isEmpty()) {
                    if (BasePhotoFragment.listener != null) {
                        BasePhotoFragment.listener.onPlayerVideo(videoUrl);
                    } else {
                        GPVideoPlayerActivity.startActivity(BasePhotoFragment.this.getContext(), videoUrl);
                    }
                }
            }
        });
        this.mySimpleTarget = new MySimpleTarget() {
            public void onResourceReady() {
                BasePhotoFragment.this.loading.setVisibility(8);
                String videoUrl = BasePhotoFragment.this.beanViewInfo.getVideoUrl();
                if (videoUrl == null || videoUrl.isEmpty()) {
                    BasePhotoFragment.this.btnVideo.setVisibility(8);
                    return;
                }
                BasePhotoFragment.this.btnVideo.setVisibility(0);
                ViewCompat.animate(BasePhotoFragment.this.btnVideo).alpha(1.0f).setDuration(1000).start();
            }

            public void onLoadFailed(Drawable drawable) {
                BasePhotoFragment.this.loading.setVisibility(8);
                BasePhotoFragment.this.btnVideo.setVisibility(8);
                if (drawable != null) {
                    BasePhotoFragment.this.imageView.setImageDrawable(drawable);
                }
            }
        };
    }

    private void initData() {
        boolean z;
        Bundle arguments = getArguments();
        if (arguments != null) {
            z = arguments.getBoolean(KEY_SING_FILING);
            this.beanViewInfo = (IThumbViewInfo) arguments.getParcelable(KEY_PATH);
            this.imageView.setDrag(arguments.getBoolean(KEY_DRAG), arguments.getFloat(KEY_SEN));
            this.imageView.setThumbRect(this.beanViewInfo.getBounds());
            this.rootView.setTag(this.beanViewInfo.getUrl());
            this.isTransPhoto = arguments.getBoolean(KEY_TRANS_PHOTO, false);
            if (this.beanViewInfo.getUrl().toLowerCase().contains(".gif")) {
                this.imageView.setZoomable(false);
                ZoomMediaLoader.getInstance().getLoader().displayGifImage(this, this.beanViewInfo.getUrl(), this.imageView, this.mySimpleTarget);
            } else {
                ZoomMediaLoader.getInstance().getLoader().displayImage(this, this.beanViewInfo.getUrl(), this.imageView, this.mySimpleTarget);
            }
        } else {
            z = true;
        }
        if (!this.isTransPhoto) {
            this.rootView.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        } else {
            this.imageView.setMinimumScale(0.7f);
        }
        if (z) {
            this.imageView.setOnViewTapListener(new PhotoViewAttacher.OnViewTapListener() {
                public void onViewTap(View view, float f, float f2) {
                }
            });
            this.imageView.setOnViewTapListener(new PhotoViewAttacher.OnViewTapListener() {
                public void onViewTap(View view, float f, float f2) {
                    if (BasePhotoFragment.this.imageView.checkMinScale()) {
                        ((GPreviewActivity) BasePhotoFragment.this.getActivity()).transformOut();
                    }
                }
            });
        } else {
            this.imageView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
                public void onOutsidePhotoTap() {
                }

                public void onPhotoTap(View view, float f, float f2) {
                    if (BasePhotoFragment.this.imageView.checkMinScale()) {
                        ((GPreviewActivity) BasePhotoFragment.this.getActivity()).transformOut();
                    }
                }
            });
        }
        this.imageView.setAlphaChangeListener(new SmoothImageView.OnAlphaChangeListener() {
            public void onAlphaChange(int i) {
                if (i == 255) {
                    String videoUrl = BasePhotoFragment.this.beanViewInfo.getVideoUrl();
                    if (videoUrl == null || videoUrl.isEmpty()) {
                        BasePhotoFragment.this.btnVideo.setVisibility(8);
                    } else {
                        BasePhotoFragment.this.btnVideo.setVisibility(0);
                    }
                } else {
                    BasePhotoFragment.this.btnVideo.setVisibility(8);
                }
                BasePhotoFragment.this.rootView.setBackgroundColor(BasePhotoFragment.getColorWithAlpha(((float) i) / 255.0f, ViewCompat.MEASURED_STATE_MASK));
            }
        });
        this.imageView.setTransformOutListener(new SmoothImageView.OnTransformOutListener() {
            public void onTransformOut() {
                ((GPreviewActivity) BasePhotoFragment.this.getActivity()).transformOut();
            }
        });
    }

    public static int getColorWithAlpha(float f, int i) {
        return (Math.min(255, Math.max(0, (int) (f * 255.0f))) << 24) + (i & ViewCompat.MEASURED_SIZE_MASK);
    }

    public void transformIn() {
        this.imageView.transformIn(new SmoothImageView.onTransformListener() {
            public void onTransformCompleted(SmoothImageView.Status status) {
                BasePhotoFragment.this.rootView.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
            }
        });
    }

    public void transformOut(SmoothImageView.onTransformListener ontransformlistener) {
        this.imageView.transformOut(ontransformlistener);
    }

    public void changeBg(int i) {
        ViewCompat.animate(this.btnVideo).alpha(0.0f).setDuration((long) SmoothImageView.getDuration()).start();
        this.rootView.setBackgroundColor(i);
    }

    public IThumbViewInfo getBeanViewInfo() {
        return this.beanViewInfo;
    }
}
