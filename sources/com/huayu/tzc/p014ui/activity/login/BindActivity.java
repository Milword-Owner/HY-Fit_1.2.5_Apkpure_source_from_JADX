package com.huayu.tzc.p014ui.activity.login;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.baidu.mobstat.Config;
import com.huayu.tzc.C2128R;
import com.huayu.tzc.base.BaseActivity;
import com.huayu.tzc.contract.MainContract;
import com.huayu.tzc.presenter.NotPresenter;
import com.huayu.tzc.utils.SingleClickKt;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u00020\u0004B\u0005¢\u0006\u0002\u0010\u0005J\b\u0010\b\u001a\u00020\tH\u0014J\b\u0010\n\u001a\u00020\u0003H\u0016J\b\u0010\u000b\u001a\u00020\fH\u0014J\u0012\u0010\r\u001a\u00020\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000¨\u0006\u0010"}, mo21895d2 = {"Lcom/huayu/tzc/ui/activity/login/BindActivity;", "Lcom/huayu/tzc/base/BaseActivity;", "Lcom/huayu/tzc/contract/MainContract$View;", "Lcom/huayu/tzc/presenter/NotPresenter;", "Landroid/view/View$OnClickListener;", "()V", "uid", "", "getLayoutId", "", "getPresenter", "initView", "", "onClick", "p0", "Landroid/view/View;", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
/* renamed from: com.huayu.tzc.ui.activity.login.BindActivity */
/* compiled from: BindActivity.kt */
public final class BindActivity extends BaseActivity<MainContract.View, NotPresenter> implements View.OnClickListener {
    private HashMap _$_findViewCache;
    private String uid = "";

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
        return C2128R.C2133layout.activity_bind;
    }

    @NotNull
    public NotPresenter getPresenter() {
        return new NotPresenter();
    }

    /* access modifiers changed from: protected */
    public void initView() {
        String stringExtra = getIntent().getStringExtra(Config.CUSTOM_USER_ID);
        if (stringExtra != null) {
            if (stringExtra == null) {
                stringExtra = "";
            }
            this.uid = stringExtra;
            View.OnClickListener onClickListener = this;
            SingleClickKt.singleClick$default((View) (TextView) _$_findCachedViewById(C2128R.C2131id.bind_no), onClickListener, 0, 2, (Object) null);
            View.OnClickListener onClickListener2 = onClickListener;
            SingleClickKt.singleClick$default((View) (ImageView) _$_findCachedViewById(C2128R.C2131id.bind_back), onClickListener2, 0, 2, (Object) null);
            SingleClickKt.singleClick$default((View) (TextView) _$_findCachedViewById(C2128R.C2131id.bind_yes), onClickListener2, 0, 2, (Object) null);
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
    }

    public void onClick(@Nullable View view) {
        Integer valueOf = view != null ? Integer.valueOf(view.getId()) : null;
        if (valueOf != null && valueOf.intValue() == C2128R.C2131id.bind_back) {
            finish();
        } else if (valueOf != null && valueOf.intValue() == C2128R.C2131id.bind_no) {
            startActivity(new Intent(this, RegisteredActivity.class).putExtra("bind", true).putExtra(Config.CUSTOM_USER_ID, this.uid));
        } else if (valueOf != null && valueOf.intValue() == C2128R.C2131id.bind_yes) {
            startActivity(new Intent(this, BindLoginActivity.class).putExtra("bind", true).putExtra(Config.CUSTOM_USER_ID, this.uid));
        }
    }
}
