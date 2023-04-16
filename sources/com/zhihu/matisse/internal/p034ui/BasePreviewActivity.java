package com.zhihu.matisse.internal.p034ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import androidx.viewpager.widget.ViewPager;
import com.zhihu.matisse.C2570R;
import com.zhihu.matisse.internal.entity.IncapableCause;
import com.zhihu.matisse.internal.entity.Item;
import com.zhihu.matisse.internal.entity.SelectionSpec;
import com.zhihu.matisse.internal.model.SelectedItemCollection;
import com.zhihu.matisse.internal.p034ui.adapter.PreviewPagerAdapter;
import com.zhihu.matisse.internal.p034ui.widget.CheckRadioView;
import com.zhihu.matisse.internal.p034ui.widget.CheckView;
import com.zhihu.matisse.internal.p034ui.widget.IncapableDialog;
import com.zhihu.matisse.internal.utils.PhotoMetadataUtils;
import com.zhihu.matisse.internal.utils.Platform;
import com.zhihu.matisse.listener.OnFragmentInteractionListener;

/* renamed from: com.zhihu.matisse.internal.ui.BasePreviewActivity */
public abstract class BasePreviewActivity extends AppCompatActivity implements View.OnClickListener, ViewPager.OnPageChangeListener, OnFragmentInteractionListener {
    public static final String CHECK_STATE = "checkState";
    public static final String EXTRA_DEFAULT_BUNDLE = "extra_default_bundle";
    public static final String EXTRA_RESULT_APPLY = "extra_result_apply";
    public static final String EXTRA_RESULT_BUNDLE = "extra_result_bundle";
    public static final String EXTRA_RESULT_ORIGINAL_ENABLE = "extra_result_original_enable";
    protected PreviewPagerAdapter mAdapter;
    private FrameLayout mBottomToolbar;
    protected TextView mButtonApply;
    protected TextView mButtonBack;
    protected CheckView mCheckView;
    private boolean mIsToolbarHide = false;
    /* access modifiers changed from: private */
    public CheckRadioView mOriginal;
    protected boolean mOriginalEnable;
    private LinearLayout mOriginalLayout;
    protected ViewPager mPager;
    protected int mPreviousPos = -1;
    protected final SelectedItemCollection mSelectedCollection = new SelectedItemCollection(this);
    protected TextView mSize;
    protected SelectionSpec mSpec;
    private FrameLayout mTopToolbar;

    public void onPageScrollStateChanged(int i) {
    }

    public void onPageScrolled(int i, float f, int i2) {
    }

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        setTheme(SelectionSpec.getInstance().themeId);
        super.onCreate(bundle);
        if (!SelectionSpec.getInstance().hasInited) {
            setResult(0);
            finish();
            return;
        }
        setContentView(C2570R.C2575layout.activity_media_preview);
        if (Platform.hasKitKat()) {
            getWindow().addFlags(67108864);
        }
        this.mSpec = SelectionSpec.getInstance();
        if (this.mSpec.needOrientationRestriction()) {
            setRequestedOrientation(this.mSpec.orientation);
        }
        if (bundle == null) {
            this.mSelectedCollection.onCreate(getIntent().getBundleExtra(EXTRA_DEFAULT_BUNDLE));
            this.mOriginalEnable = getIntent().getBooleanExtra("extra_result_original_enable", false);
        } else {
            this.mSelectedCollection.onCreate(bundle);
            this.mOriginalEnable = bundle.getBoolean("checkState");
        }
        this.mButtonBack = (TextView) findViewById(C2570R.C2573id.button_back);
        this.mButtonApply = (TextView) findViewById(C2570R.C2573id.button_apply);
        this.mSize = (TextView) findViewById(C2570R.C2573id.size);
        this.mButtonBack.setOnClickListener(this);
        this.mButtonApply.setOnClickListener(this);
        this.mPager = (ViewPager) findViewById(C2570R.C2573id.pager);
        this.mPager.addOnPageChangeListener(this);
        this.mAdapter = new PreviewPagerAdapter(getSupportFragmentManager(), (PreviewPagerAdapter.OnPrimaryItemSetListener) null);
        this.mPager.setAdapter(this.mAdapter);
        this.mCheckView = (CheckView) findViewById(C2570R.C2573id.check_view);
        this.mCheckView.setCountable(this.mSpec.countable);
        this.mBottomToolbar = (FrameLayout) findViewById(C2570R.C2573id.bottom_toolbar);
        this.mTopToolbar = (FrameLayout) findViewById(C2570R.C2573id.top_toolbar);
        this.mCheckView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Item mediaItem = BasePreviewActivity.this.mAdapter.getMediaItem(BasePreviewActivity.this.mPager.getCurrentItem());
                if (BasePreviewActivity.this.mSelectedCollection.isSelected(mediaItem)) {
                    BasePreviewActivity.this.mSelectedCollection.remove(mediaItem);
                    if (BasePreviewActivity.this.mSpec.countable) {
                        BasePreviewActivity.this.mCheckView.setCheckedNum(Integer.MIN_VALUE);
                    } else {
                        BasePreviewActivity.this.mCheckView.setChecked(false);
                    }
                } else if (BasePreviewActivity.this.assertAddSelection(mediaItem)) {
                    BasePreviewActivity.this.mSelectedCollection.add(mediaItem);
                    if (BasePreviewActivity.this.mSpec.countable) {
                        BasePreviewActivity.this.mCheckView.setCheckedNum(BasePreviewActivity.this.mSelectedCollection.checkedNumOf(mediaItem));
                    } else {
                        BasePreviewActivity.this.mCheckView.setChecked(true);
                    }
                }
                BasePreviewActivity.this.updateApplyButton();
                if (BasePreviewActivity.this.mSpec.onSelectedListener != null) {
                    BasePreviewActivity.this.mSpec.onSelectedListener.onSelected(BasePreviewActivity.this.mSelectedCollection.asListOfUri(), BasePreviewActivity.this.mSelectedCollection.asListOfString());
                }
            }
        });
        this.mOriginalLayout = (LinearLayout) findViewById(C2570R.C2573id.originalLayout);
        this.mOriginal = (CheckRadioView) findViewById(C2570R.C2573id.original);
        this.mOriginalLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int access$200 = BasePreviewActivity.this.countOverMaxSize();
                if (access$200 > 0) {
                    IncapableDialog.newInstance("", BasePreviewActivity.this.getString(C2570R.string.error_over_original_count, new Object[]{Integer.valueOf(access$200), Integer.valueOf(BasePreviewActivity.this.mSpec.originalMaxSize)})).show(BasePreviewActivity.this.getSupportFragmentManager(), IncapableDialog.class.getName());
                    return;
                }
                BasePreviewActivity basePreviewActivity = BasePreviewActivity.this;
                basePreviewActivity.mOriginalEnable = true ^ basePreviewActivity.mOriginalEnable;
                BasePreviewActivity.this.mOriginal.setChecked(BasePreviewActivity.this.mOriginalEnable);
                if (!BasePreviewActivity.this.mOriginalEnable) {
                    BasePreviewActivity.this.mOriginal.setColor(-1);
                }
                if (BasePreviewActivity.this.mSpec.onCheckedListener != null) {
                    BasePreviewActivity.this.mSpec.onCheckedListener.onCheck(BasePreviewActivity.this.mOriginalEnable);
                }
            }
        });
        updateApplyButton();
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        this.mSelectedCollection.onSaveInstanceState(bundle);
        bundle.putBoolean("checkState", this.mOriginalEnable);
        super.onSaveInstanceState(bundle);
    }

    public void onBackPressed() {
        sendBackResult(false);
        super.onBackPressed();
    }

    public void onClick(View view) {
        if (view.getId() == C2570R.C2573id.button_back) {
            onBackPressed();
        } else if (view.getId() == C2570R.C2573id.button_apply) {
            sendBackResult(true);
            finish();
        }
    }

    public void onClick() {
        if (this.mSpec.autoHideToobar) {
            if (this.mIsToolbarHide) {
                this.mTopToolbar.animate().setInterpolator(new FastOutSlowInInterpolator()).translationYBy((float) this.mTopToolbar.getMeasuredHeight()).start();
                this.mBottomToolbar.animate().translationYBy((float) (-this.mBottomToolbar.getMeasuredHeight())).setInterpolator(new FastOutSlowInInterpolator()).start();
            } else {
                this.mTopToolbar.animate().setInterpolator(new FastOutSlowInInterpolator()).translationYBy((float) (-this.mTopToolbar.getMeasuredHeight())).start();
                this.mBottomToolbar.animate().setInterpolator(new FastOutSlowInInterpolator()).translationYBy((float) this.mBottomToolbar.getMeasuredHeight()).start();
            }
            this.mIsToolbarHide = !this.mIsToolbarHide;
        }
    }

    public void onPageSelected(int i) {
        PreviewPagerAdapter previewPagerAdapter = (PreviewPagerAdapter) this.mPager.getAdapter();
        int i2 = this.mPreviousPos;
        if (!(i2 == -1 || i2 == i)) {
            ((PreviewItemFragment) previewPagerAdapter.instantiateItem(this.mPager, i2)).resetView();
            Item mediaItem = previewPagerAdapter.getMediaItem(i);
            if (this.mSpec.countable) {
                int checkedNumOf = this.mSelectedCollection.checkedNumOf(mediaItem);
                this.mCheckView.setCheckedNum(checkedNumOf);
                if (checkedNumOf > 0) {
                    this.mCheckView.setEnabled(true);
                } else {
                    this.mCheckView.setEnabled(true ^ this.mSelectedCollection.maxSelectableReached());
                }
            } else {
                boolean isSelected = this.mSelectedCollection.isSelected(mediaItem);
                this.mCheckView.setChecked(isSelected);
                if (isSelected) {
                    this.mCheckView.setEnabled(true);
                } else {
                    this.mCheckView.setEnabled(true ^ this.mSelectedCollection.maxSelectableReached());
                }
            }
            updateSize(mediaItem);
        }
        this.mPreviousPos = i;
    }

    /* access modifiers changed from: private */
    public void updateApplyButton() {
        int count = this.mSelectedCollection.count();
        if (count == 0) {
            this.mButtonApply.setText(C2570R.string.button_apply_default);
            this.mButtonApply.setEnabled(false);
        } else if (count != 1 || !this.mSpec.singleSelectionModeEnabled()) {
            this.mButtonApply.setEnabled(true);
            this.mButtonApply.setText(getString(C2570R.string.button_apply, new Object[]{Integer.valueOf(count)}));
        } else {
            this.mButtonApply.setText(C2570R.string.button_apply_default);
            this.mButtonApply.setEnabled(true);
        }
        if (this.mSpec.originalable) {
            this.mOriginalLayout.setVisibility(0);
            updateOriginalState();
            return;
        }
        this.mOriginalLayout.setVisibility(8);
    }

    private void updateOriginalState() {
        this.mOriginal.setChecked(this.mOriginalEnable);
        if (!this.mOriginalEnable) {
            this.mOriginal.setColor(-1);
        }
        if (countOverMaxSize() > 0 && this.mOriginalEnable) {
            IncapableDialog.newInstance("", getString(C2570R.string.error_over_original_size, new Object[]{Integer.valueOf(this.mSpec.originalMaxSize)})).show(getSupportFragmentManager(), IncapableDialog.class.getName());
            this.mOriginal.setChecked(false);
            this.mOriginal.setColor(-1);
            this.mOriginalEnable = false;
        }
    }

    /* access modifiers changed from: private */
    public int countOverMaxSize() {
        int count = this.mSelectedCollection.count();
        int i = 0;
        for (int i2 = 0; i2 < count; i2++) {
            Item item = this.mSelectedCollection.asList().get(i2);
            if (item.isImage() && PhotoMetadataUtils.getSizeInMB(item.size) > ((float) this.mSpec.originalMaxSize)) {
                i++;
            }
        }
        return i;
    }

    /* access modifiers changed from: protected */
    public void updateSize(Item item) {
        if (item.isGif()) {
            this.mSize.setVisibility(0);
            TextView textView = this.mSize;
            textView.setText(PhotoMetadataUtils.getSizeInMB(item.size) + "M");
        } else {
            this.mSize.setVisibility(8);
        }
        if (item.isVideo()) {
            this.mOriginalLayout.setVisibility(8);
        } else if (this.mSpec.originalable) {
            this.mOriginalLayout.setVisibility(0);
        }
    }

    /* access modifiers changed from: protected */
    public void sendBackResult(boolean z) {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_RESULT_BUNDLE, this.mSelectedCollection.getDataWithBundle());
        intent.putExtra(EXTRA_RESULT_APPLY, z);
        intent.putExtra("extra_result_original_enable", this.mOriginalEnable);
        setResult(-1, intent);
    }

    /* access modifiers changed from: private */
    public boolean assertAddSelection(Item item) {
        IncapableCause isAcceptable = this.mSelectedCollection.isAcceptable(item);
        IncapableCause.handleCause(this, isAcceptable);
        return isAcceptable == null;
    }
}
