package com.huayu.tzc.p014ui.activity;

import android.view.View;
import com.huayu.tzc.C2128R;
import com.huayu.tzc.base.BaseActivity;
import com.huayu.tzc.contract.MainContract;
import com.huayu.tzc.customview.MyHeaderView;
import com.huayu.tzc.customview.calendar.CalendarList;
import com.huayu.tzc.presenter.NotPresenter;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u000b\u001a\u00020\fH\u0014J\b\u0010\r\u001a\u00020\u0003H\u0016J\b\u0010\u000e\u001a\u00020\u000fH\u0014R\u001a\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u0010"}, mo21895d2 = {"Lcom/huayu/tzc/ui/activity/ChoiceDateActivity;", "Lcom/huayu/tzc/base/BaseActivity;", "Lcom/huayu/tzc/contract/MainContract$View;", "Lcom/huayu/tzc/presenter/NotPresenter;", "()V", "simpleDateFormat", "Ljava/text/SimpleDateFormat;", "getSimpleDateFormat", "()Ljava/text/SimpleDateFormat;", "setSimpleDateFormat", "(Ljava/text/SimpleDateFormat;)V", "getLayoutId", "", "getPresenter", "initView", "", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
/* renamed from: com.huayu.tzc.ui.activity.ChoiceDateActivity */
/* compiled from: ChoiceDateActivity.kt */
public final class ChoiceDateActivity extends BaseActivity<MainContract.View, NotPresenter> {
    private HashMap _$_findViewCache;
    @NotNull
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

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
        return C2128R.C2133layout.activity_choice_date;
    }

    @NotNull
    public final SimpleDateFormat getSimpleDateFormat() {
        return this.simpleDateFormat;
    }

    public final void setSimpleDateFormat(@NotNull SimpleDateFormat simpleDateFormat2) {
        Intrinsics.checkParameterIsNotNull(simpleDateFormat2, "<set-?>");
        this.simpleDateFormat = simpleDateFormat2;
    }

    @NotNull
    public NotPresenter getPresenter() {
        return new NotPresenter();
    }

    /* access modifiers changed from: protected */
    public void initView() {
        ((MyHeaderView) _$_findCachedViewById(C2128R.C2131id.choiceHeader)).setMenuTextColor("#000000");
        ((MyHeaderView) _$_findCachedViewById(C2128R.C2131id.choiceHeader)).setTextClickListener(new ChoiceDateActivity$initView$1(this));
        ((CalendarList) _$_findCachedViewById(C2128R.C2131id.calendarList)).setOnDateSelected(new ChoiceDateActivity$initView$2(this));
    }
}
