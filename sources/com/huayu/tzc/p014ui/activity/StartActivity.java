package com.huayu.tzc.p014ui.activity;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import com.huayu.tzc.C2128R;
import com.huayu.tzc.base.BaseActivity;
import com.huayu.tzc.contract.MainContract;
import com.huayu.tzc.presenter.NotPresenter;
import com.tencent.mmkv.MMKV;
import java.util.HashMap;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0014J\b\u0010\u0007\u001a\u00020\u0003H\u0016J\b\u0010\b\u001a\u00020\tH\u0014¨\u0006\n"}, mo21895d2 = {"Lcom/huayu/tzc/ui/activity/StartActivity;", "Lcom/huayu/tzc/base/BaseActivity;", "Lcom/huayu/tzc/contract/MainContract$View;", "Lcom/huayu/tzc/presenter/NotPresenter;", "()V", "getLayoutId", "", "getPresenter", "initView", "", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
/* renamed from: com.huayu.tzc.ui.activity.StartActivity */
/* compiled from: StartActivity.kt */
public final class StartActivity extends BaseActivity<MainContract.View, NotPresenter> {
    private HashMap _$_findViewCache;

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
        return C2128R.C2133layout.activity_start;
    }

    @NotNull
    public NotPresenter getPresenter() {
        return new NotPresenter();
    }

    /* access modifiers changed from: protected */
    public void initView() {
        String tag = getTAG();
        Log.e(tag, "Token: " + MMKV.defaultMMKV().decodeString("token", ""));
        new Handler().postDelayed(new StartActivity$initView$1(this), 1500);
    }
}
