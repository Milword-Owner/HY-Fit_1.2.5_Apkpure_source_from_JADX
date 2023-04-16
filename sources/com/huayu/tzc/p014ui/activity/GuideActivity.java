package com.huayu.tzc.p014ui.activity;

import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import androidx.core.app.NotificationCompat;
import com.hjq.toast.ToastUtils;
import com.huayu.tzc.C2128R;
import com.huayu.tzc.base.BaseActivity;
import com.huayu.tzc.contract.MainContract;
import com.huayu.tzc.presenter.NotPresenter;
import com.huayu.tzc.statusbar.StatusBarUtil;
import com.huayu.tzc.utils.AppManager;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\bH\u0014J\b\u0010\t\u001a\u00020\u0003H\u0016J\b\u0010\n\u001a\u00020\u000bH\u0014J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000¨\u0006\u0011"}, mo21895d2 = {"Lcom/huayu/tzc/ui/activity/GuideActivity;", "Lcom/huayu/tzc/base/BaseActivity;", "Lcom/huayu/tzc/contract/MainContract$View;", "Lcom/huayu/tzc/presenter/NotPresenter;", "()V", "exitTime", "", "getLayoutId", "", "getPresenter", "initView", "", "onKeyDown", "", "keyCode", "event", "Landroid/view/KeyEvent;", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
/* renamed from: com.huayu.tzc.ui.activity.GuideActivity */
/* compiled from: GuideActivity.kt */
public final class GuideActivity extends BaseActivity<MainContract.View, NotPresenter> {
    private HashMap _$_findViewCache;
    private long exitTime;

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
        return C2128R.C2133layout.activity_guide;
    }

    @NotNull
    public NotPresenter getPresenter() {
        return new NotPresenter();
    }

    /* access modifiers changed from: protected */
    public void initView() {
        StatusBarUtil.setTranslucentStatus(this);
        Button button = (Button) _$_findCachedViewById(C2128R.C2131id.guideLogin);
        button.setOnClickListener(new GuideActivity$initView$$inlined$singleClick$1(button, 800, this));
        Button button2 = (Button) _$_findCachedViewById(C2128R.C2131id.guideRegister);
        button2.setOnClickListener(new GuideActivity$initView$$inlined$singleClick$2(button2, 800, this));
    }

    public boolean onKeyDown(int i, @NotNull KeyEvent keyEvent) {
        Intrinsics.checkParameterIsNotNull(keyEvent, NotificationCompat.CATEGORY_EVENT);
        if (i != 4 || keyEvent.getAction() != 0) {
            return super.onKeyDown(i, keyEvent);
        }
        if (System.currentTimeMillis() - this.exitTime > ((long) 2000)) {
            ToastUtils.show((CharSequence) getString(C2128R.string.common_exit));
            this.exitTime = System.currentTimeMillis();
            return true;
        }
        AppManager.finishAllActivity();
        System.exit(0);
        throw new RuntimeException("System.exit returned normally, while it was supposed to halt JVM.");
    }
}
