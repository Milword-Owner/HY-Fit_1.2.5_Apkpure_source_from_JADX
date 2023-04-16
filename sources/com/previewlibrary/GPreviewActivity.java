package com.previewlibrary;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.previewlibrary.GPreviewBuilder;
import com.previewlibrary.enitity.IThumbViewInfo;
import com.previewlibrary.view.BasePhotoFragment;
import com.previewlibrary.wight.BezierBannerView;
import com.previewlibrary.wight.PhotoViewPager;
import com.previewlibrary.wight.SmoothImageView;
import java.util.ArrayList;
import java.util.List;

public class GPreviewActivity extends FragmentActivity {
    private static final String TAG = "com.previewlibrary.GPreviewActivity";
    private BezierBannerView bezierBannerView;
    protected int currentIndex;
    /* access modifiers changed from: private */
    public List<BasePhotoFragment> fragments = new ArrayList();
    /* access modifiers changed from: private */
    public List<IThumbViewInfo> imgUrls;
    private boolean isShow = true;
    protected boolean isTransformOut = false;
    /* access modifiers changed from: private */
    public TextView ltAddDot;
    private GPreviewBuilder.IndicatorType type;
    /* access modifiers changed from: private */
    public PhotoViewPager viewPager;

    public int setContentLayout() {
        return 0;
    }

    /* access modifiers changed from: protected */
    @CallSuper
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        initData();
        if (setContentLayout() == 0) {
            setContentView(C2517R.C2521layout.activity_image_preview_photo);
        } else {
            setContentView(setContentLayout());
        }
        initView();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        ZoomMediaLoader.getInstance().getLoader().clearMemory(this);
        PhotoViewPager photoViewPager = this.viewPager;
        if (photoViewPager != null) {
            photoViewPager.setAdapter((PagerAdapter) null);
            this.viewPager.clearOnPageChangeListeners();
            this.viewPager.removeAllViews();
            this.viewPager = null;
        }
        List<BasePhotoFragment> list = this.fragments;
        if (list != null) {
            list.clear();
            this.fragments = null;
        }
        List<IThumbViewInfo> list2 = this.imgUrls;
        if (list2 != null) {
            list2.clear();
            this.imgUrls = null;
        }
        super.onDestroy();
    }

    private void initData() {
        this.imgUrls = getIntent().getParcelableArrayListExtra("imagePaths");
        this.currentIndex = getIntent().getIntExtra("position", -1);
        this.type = (GPreviewBuilder.IndicatorType) getIntent().getSerializableExtra("type");
        this.isShow = getIntent().getBooleanExtra("isShow", true);
        int intExtra = getIntent().getIntExtra("duration", 300);
        boolean booleanExtra = getIntent().getBooleanExtra("isFullscreen", false);
        boolean booleanExtra2 = getIntent().getBooleanExtra("isScale", false);
        SmoothImageView.setFullscreen(booleanExtra);
        SmoothImageView.setIsScale(booleanExtra2);
        if (booleanExtra) {
            setTheme(16973841);
        }
        try {
            SmoothImageView.setDuration(intExtra);
            iniFragment(this.imgUrls, this.currentIndex, (Class) getIntent().getSerializableExtra("className"));
        } catch (Exception unused) {
            iniFragment(this.imgUrls, this.currentIndex, BasePhotoFragment.class);
        }
    }

    /* access modifiers changed from: protected */
    public void iniFragment(List<IThumbViewInfo> list, int i, Class<? extends BasePhotoFragment> cls) {
        if (list != null) {
            int size = list.size();
            int i2 = 0;
            while (i2 < size) {
                this.fragments.add(BasePhotoFragment.getInstance(cls, list.get(i2), i == i2, getIntent().getBooleanExtra("isSingleFling", false), getIntent().getBooleanExtra("isDrag", false), getIntent().getFloatExtra("sensitivity", 0.5f)));
                i2++;
            }
            return;
        }
        finish();
    }

    @SuppressLint({"StringFormatMatches"})
    private void initView() {
        this.viewPager = (PhotoViewPager) findViewById(C2517R.C2520id.viewPager);
        this.viewPager.setAdapter(new PhotoPagerAdapter(getSupportFragmentManager()));
        this.viewPager.setCurrentItem(this.currentIndex);
        this.viewPager.setOffscreenPageLimit(3);
        this.bezierBannerView = (BezierBannerView) findViewById(C2517R.C2520id.bezierBannerView);
        this.ltAddDot = (TextView) findViewById(C2517R.C2520id.ltAddDot);
        if (this.type == GPreviewBuilder.IndicatorType.Dot) {
            this.bezierBannerView.setVisibility(0);
            this.bezierBannerView.attachToViewpager(this.viewPager);
        } else {
            this.ltAddDot.setVisibility(0);
            this.ltAddDot.setText(getString(C2517R.string.string_count, new Object[]{Integer.valueOf(this.currentIndex + 1), Integer.valueOf(this.imgUrls.size())}));
            this.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                public void onPageScrollStateChanged(int i) {
                }

                public void onPageScrolled(int i, float f, int i2) {
                }

                public void onPageSelected(int i) {
                    if (GPreviewActivity.this.ltAddDot != null) {
                        GPreviewActivity.this.ltAddDot.setText(GPreviewActivity.this.getString(C2517R.string.string_count, new Object[]{Integer.valueOf(i + 1), Integer.valueOf(GPreviewActivity.this.imgUrls.size())}));
                    }
                    GPreviewActivity gPreviewActivity = GPreviewActivity.this;
                    gPreviewActivity.currentIndex = i;
                    gPreviewActivity.viewPager.setCurrentItem(GPreviewActivity.this.currentIndex, true);
                }
            });
        }
        if (this.fragments.size() == 1 && !this.isShow) {
            this.bezierBannerView.setVisibility(8);
            this.ltAddDot.setVisibility(8);
        }
        this.viewPager.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                GPreviewActivity.this.viewPager.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                ((BasePhotoFragment) GPreviewActivity.this.fragments.get(GPreviewActivity.this.currentIndex)).transformIn();
            }
        });
    }

    public void transformOut() {
        if (!this.isTransformOut) {
            getViewPager().setEnabled(false);
            this.isTransformOut = true;
            int currentItem = this.viewPager.getCurrentItem();
            if (currentItem < this.imgUrls.size()) {
                BasePhotoFragment basePhotoFragment = this.fragments.get(currentItem);
                TextView textView = this.ltAddDot;
                if (textView != null) {
                    textView.setVisibility(8);
                } else {
                    this.bezierBannerView.setVisibility(8);
                }
                basePhotoFragment.changeBg(0);
                basePhotoFragment.transformOut(new SmoothImageView.onTransformListener() {
                    public void onTransformCompleted(SmoothImageView.Status status) {
                        GPreviewActivity.this.getViewPager().setEnabled(true);
                        GPreviewActivity.this.exit();
                    }
                });
                return;
            }
            exit();
        }
    }

    public void finish() {
        BasePhotoFragment.listener = null;
        super.finish();
    }

    public List<BasePhotoFragment> getFragments() {
        return this.fragments;
    }

    /* access modifiers changed from: private */
    public void exit() {
        finish();
        overridePendingTransition(0, 0);
    }

    public PhotoViewPager getViewPager() {
        return this.viewPager;
    }

    public void onBackPressed() {
        this.isTransformOut = false;
        transformOut();
    }

    private class PhotoPagerAdapter extends FragmentStatePagerAdapter {
        PhotoPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        public Fragment getItem(int i) {
            return (Fragment) GPreviewActivity.this.fragments.get(i);
        }

        public int getCount() {
            if (GPreviewActivity.this.fragments == null) {
                return 0;
            }
            return GPreviewActivity.this.fragments.size();
        }
    }
}
