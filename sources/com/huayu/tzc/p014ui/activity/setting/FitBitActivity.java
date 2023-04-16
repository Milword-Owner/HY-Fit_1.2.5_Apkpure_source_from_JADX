package com.huayu.tzc.p014ui.activity.setting;

import android.content.Intent;
import android.os.Parcelable;
import android.view.View;
import android.widget.Switch;
import com.huayu.tzc.C2128R;
import com.huayu.tzc.base.BaseActivity;
import com.huayu.tzc.bean.FitBit;
import com.huayu.tzc.contract.MainContract;
import com.huayu.tzc.presenter.NotPresenter;
import com.tencent.mmkv.MMKV;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\bH\u0014J\b\u0010\t\u001a\u00020\u0003H\u0016J\b\u0010\n\u001a\u00020\u000bH\u0014J\"\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0014R\u000e\u0010\u0005\u001a\u00020\u0006X.¢\u0006\u0002\n\u0000¨\u0006\u0011"}, mo21895d2 = {"Lcom/huayu/tzc/ui/activity/setting/FitBitActivity;", "Lcom/huayu/tzc/base/BaseActivity;", "Lcom/huayu/tzc/contract/MainContract$View;", "Lcom/huayu/tzc/presenter/NotPresenter;", "()V", "fitBit", "Lcom/huayu/tzc/bean/FitBit;", "getLayoutId", "", "getPresenter", "initView", "", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
/* renamed from: com.huayu.tzc.ui.activity.setting.FitBitActivity */
/* compiled from: FitBitActivity.kt */
public final class FitBitActivity extends BaseActivity<MainContract.View, NotPresenter> {
    private HashMap _$_findViewCache;
    /* access modifiers changed from: private */
    public FitBit fitBit;

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return C2128R.C2133layout.activity_fit_bit;
    }

    public static final /* synthetic */ FitBit access$getFitBit$p(FitBitActivity fitBitActivity) {
        FitBit fitBit2 = fitBitActivity.fitBit;
        if (fitBit2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fitBit");
        }
        return fitBit2;
    }

    @NotNull
    public NotPresenter getPresenter() {
        return new NotPresenter();
    }

    /* access modifiers changed from: protected */
    public void initView() {
        Parcelable decodeParcelable = MMKV.defaultMMKV().decodeParcelable("fitbit", FitBit.class, new FitBit());
        Intrinsics.checkExpressionValueIsNotNull(decodeParcelable, "MMKV.defaultMMKV().decod…Bit::class.java,FitBit())");
        this.fitBit = (FitBit) decodeParcelable;
        Switch switchR = (Switch) _$_findCachedViewById(C2128R.C2131id.fitSwitch);
        Intrinsics.checkExpressionValueIsNotNull(switchR, "fitSwitch");
        FitBit fitBit2 = this.fitBit;
        if (fitBit2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fitBit");
        }
        switchR.setChecked(fitBit2.getConnectFitBit());
        ((Switch) _$_findCachedViewById(C2128R.C2131id.fitSwitch)).setOnCheckedChangeListener(new FitBitActivity$initView$1(this));
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 818 && i2 == -1) {
            Switch switchR = (Switch) _$_findCachedViewById(C2128R.C2131id.fitSwitch);
            Intrinsics.checkExpressionValueIsNotNull(switchR, "fitSwitch");
            switchR.setChecked(true);
            Parcelable decodeParcelable = MMKV.defaultMMKV().decodeParcelable("fitbit", FitBit.class, new FitBit());
            Intrinsics.checkExpressionValueIsNotNull(decodeParcelable, "MMKV.defaultMMKV().decod…Bit::class.java,FitBit())");
            this.fitBit = (FitBit) decodeParcelable;
        }
    }
}
