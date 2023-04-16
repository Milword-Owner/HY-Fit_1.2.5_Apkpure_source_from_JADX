package com.huayu.tzc.p014ui.activity.history;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hjq.toast.ToastUtils;
import com.huayu.tzc.C2128R;
import com.huayu.tzc.adapter.HistoryAdapter;
import com.huayu.tzc.base.BaseActivity;
import com.huayu.tzc.base.BaseListBean;
import com.huayu.tzc.bean.History;
import com.huayu.tzc.bean.Measure;
import com.huayu.tzc.bean.Member;
import com.huayu.tzc.contract.MainContract;
import com.huayu.tzc.customview.MyHeaderView;
import com.huayu.tzc.presenter.HistoryPresenter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u0013\u001a\u00020\u0014H\u0002J\b\u0010\u0015\u001a\u00020\u000fH\u0014J\u0016\u0010\u0016\u001a\u00020\u00142\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018H\u0016J\b\u0010\u001a\u001a\u00020\u0003H\u0016J\b\u0010\u001b\u001a\u00020\u0014H\u0002J\b\u0010\u001c\u001a\u00020\u0014H\u0002J\b\u0010\u001d\u001a\u00020\u0014H\u0014J\u0010\u0010\u001e\u001a\u00020\u00122\u0006\u0010\u001f\u001a\u00020 H\u0002J\b\u0010!\u001a\u00020\u0014H\u0002J\u0012\u0010\"\u001a\u00020\u00142\b\u0010#\u001a\u0004\u0018\u00010$H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X.¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX.¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\bX\u0004¢\u0006\u0002\n\u0000¨\u0006%"}, mo21895d2 = {"Lcom/huayu/tzc/ui/activity/history/HistoryActivity;", "Lcom/huayu/tzc/base/BaseActivity;", "Lcom/huayu/tzc/contract/MainContract$HistoryView;", "Lcom/huayu/tzc/presenter/HistoryPresenter;", "()V", "historyAdapter", "Lcom/huayu/tzc/adapter/HistoryAdapter;", "historyList", "", "Lcom/huayu/tzc/bean/History;", "isEdit", "", "member", "Lcom/huayu/tzc/bean/Member;", "pageNumber", "", "pageSize", "timeList", "", "deleteItem", "", "getLayoutId", "getMeasures", "measureBean", "Lcom/huayu/tzc/base/BaseListBean;", "Lcom/huayu/tzc/bean/Measure;", "getPresenter", "hideEdit", "initAdapter", "initView", "monthToString", "date", "Ljava/util/Date;", "showEdit", "showError", "e", "", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
/* renamed from: com.huayu.tzc.ui.activity.history.HistoryActivity */
/* compiled from: HistoryActivity.kt */
public final class HistoryActivity extends BaseActivity<MainContract.HistoryView, HistoryPresenter> implements MainContract.HistoryView {
    private HashMap _$_findViewCache;
    /* access modifiers changed from: private */
    public HistoryAdapter historyAdapter;
    /* access modifiers changed from: private */
    public List<History> historyList = new ArrayList();
    /* access modifiers changed from: private */
    public boolean isEdit;
    /* access modifiers changed from: private */
    public Member member;
    /* access modifiers changed from: private */
    public int pageNumber = 1;
    /* access modifiers changed from: private */
    public int pageSize = 20;
    private final List<String> timeList = new ArrayList();

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
        return C2128R.C2133layout.activity_history;
    }

    public static final /* synthetic */ HistoryAdapter access$getHistoryAdapter$p(HistoryActivity historyActivity) {
        HistoryAdapter historyAdapter2 = historyActivity.historyAdapter;
        if (historyAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("historyAdapter");
        }
        return historyAdapter2;
    }

    public static final /* synthetic */ HistoryPresenter access$getMPresenter$p(HistoryActivity historyActivity) {
        return (HistoryPresenter) historyActivity.getMPresenter();
    }

    public static final /* synthetic */ Member access$getMember$p(HistoryActivity historyActivity) {
        Member member2 = historyActivity.member;
        if (member2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("member");
        }
        return member2;
    }

    @NotNull
    public HistoryPresenter getPresenter() {
        return new HistoryPresenter();
    }

    /* access modifiers changed from: protected */
    public void initView() {
        Member member2 = (Member) getIntent().getParcelableExtra("member");
        if (member2 == null) {
            member2 = new Member();
        }
        this.member = member2;
        initAdapter();
        ((MyHeaderView) _$_findCachedViewById(C2128R.C2131id.historyHeader)).setTextClickListener(new HistoryActivity$initView$1(this));
        TextView textView = (TextView) _$_findCachedViewById(C2128R.C2131id.historyDel);
        textView.setOnClickListener(new HistoryActivity$initView$$inlined$singleClick$1(textView, 800, this));
        TextView textView2 = (TextView) _$_findCachedViewById(C2128R.C2131id.historyNum);
        Intrinsics.checkExpressionValueIsNotNull(textView2, "historyNum");
        String string = getString(C2128R.string.select_num);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.select_num)");
        textView2.setText(StringsKt.replace$default(string, "_num", "0", false, 4, (Object) null));
    }

    /* access modifiers changed from: private */
    public final void showEdit() {
        this.isEdit = false;
        HistoryAdapter historyAdapter2 = this.historyAdapter;
        if (historyAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("historyAdapter");
        }
        historyAdapter2.getLoadMoreModule().setEnableLoadMore(true);
        ((MyHeaderView) _$_findCachedViewById(C2128R.C2131id.historyHeader)).setMenuImg(C2128R.C2130drawable.ic_del);
        LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(C2128R.C2131id.historyDelLayout);
        Intrinsics.checkExpressionValueIsNotNull(linearLayout, "historyDelLayout");
        linearLayout.setVisibility(8);
        for (History show : this.historyList) {
            show.setShow(false);
        }
        HistoryAdapter historyAdapter3 = this.historyAdapter;
        if (historyAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("historyAdapter");
        }
        historyAdapter3.notifyDataSetChanged();
    }

    /* access modifiers changed from: private */
    public final void hideEdit() {
        this.isEdit = true;
        HistoryAdapter historyAdapter2 = this.historyAdapter;
        if (historyAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("historyAdapter");
        }
        int i = 0;
        historyAdapter2.getLoadMoreModule().setEnableLoadMore(false);
        ((MyHeaderView) _$_findCachedViewById(C2128R.C2131id.historyHeader)).setMenu(getString(C2128R.string.complete));
        ((MyHeaderView) _$_findCachedViewById(C2128R.C2131id.historyHeader)).setMenuTextColor("#333333");
        LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(C2128R.C2131id.historyDelLayout);
        Intrinsics.checkExpressionValueIsNotNull(linearLayout, "historyDelLayout");
        linearLayout.setVisibility(0);
        for (History next : this.historyList) {
            next.setShow(true);
            if (next.getSelect()) {
                i++;
            }
        }
        TextView textView = (TextView) _$_findCachedViewById(C2128R.C2131id.historyNum);
        Intrinsics.checkExpressionValueIsNotNull(textView, "historyNum");
        String string = getString(C2128R.string.select_num);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.select_num)");
        textView.setText(StringsKt.replace$default(string, "_num", String.valueOf(i), false, 4, (Object) null));
        HistoryAdapter historyAdapter3 = this.historyAdapter;
        if (historyAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("historyAdapter");
        }
        historyAdapter3.notifyDataSetChanged();
    }

    private final void initAdapter() {
        this.historyAdapter = new HistoryAdapter(this.historyList);
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(C2128R.C2131id.historyRecyclerview);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView, "historyRecyclerview");
        Context context = this;
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        RecyclerView recyclerView2 = (RecyclerView) _$_findCachedViewById(C2128R.C2131id.historyRecyclerview);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView2, "historyRecyclerview");
        HistoryAdapter historyAdapter2 = this.historyAdapter;
        if (historyAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("historyAdapter");
        }
        recyclerView2.setAdapter(historyAdapter2);
        View inflate = LayoutInflater.from(context).inflate(C2128R.C2133layout.view_listnull, (ViewGroup) null);
        Intrinsics.checkExpressionValueIsNotNull(inflate, "LayoutInflater.from(this…yout.view_listnull, null)");
        HistoryAdapter historyAdapter3 = this.historyAdapter;
        if (historyAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("historyAdapter");
        }
        historyAdapter3.setEmptyView(inflate);
        HistoryAdapter historyAdapter4 = this.historyAdapter;
        if (historyAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("historyAdapter");
        }
        historyAdapter4.getLoadMoreModule().setOnLoadMoreListener(new HistoryActivity$initAdapter$1(this));
        HistoryAdapter historyAdapter5 = this.historyAdapter;
        if (historyAdapter5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("historyAdapter");
        }
        historyAdapter5.setOnItemChildClickListener(new HistoryActivity$initAdapter$2(this));
        HistoryAdapter historyAdapter6 = this.historyAdapter;
        if (historyAdapter6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("historyAdapter");
        }
        historyAdapter6.setOnItemClickListener(new HistoryActivity$initAdapter$3(this));
        progressShow();
        HistoryPresenter historyPresenter = (HistoryPresenter) getMPresenter();
        if (historyPresenter != null) {
            int i = this.pageNumber;
            int i2 = this.pageSize;
            Member member2 = this.member;
            if (member2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("member");
            }
            historyPresenter.getMeasures(i, i2, member2.getMember_id());
        }
    }

    /* access modifiers changed from: private */
    public final void deleteItem() {
        int i;
        List<History> arrayList = new ArrayList<>();
        for (History next : this.historyList) {
            if (next.getSelect()) {
                arrayList.add(next);
            }
        }
        if (arrayList.size() < 1) {
            ToastUtils.show((CharSequence) getString(C2128R.string.del_tip));
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (History history : arrayList) {
            if (Intrinsics.areEqual((Object) history, (Object) (History) CollectionsKt.last(arrayList))) {
                sb.append(history.getMeasure().getRecordid());
            } else {
                sb.append(String.valueOf(history.getMeasure().getRecordid()) + ",");
            }
        }
        String tag = getTAG();
        Log.e(tag, "deleteItem: " + sb);
        HistoryPresenter historyPresenter = (HistoryPresenter) getMPresenter();
        if (historyPresenter != null) {
            historyPresenter.deleteMeasures(sb.toString());
        }
        this.historyList.removeAll(arrayList);
        Iterator it = arrayList.iterator();
        while (true) {
            i = 0;
            if (!it.hasNext()) {
                break;
            }
            String time = ((History) it.next()).getTime();
            List arrayList2 = new ArrayList();
            for (History next2 : this.historyList) {
                if (Intrinsics.areEqual((Object) next2.getTime(), (Object) time)) {
                    arrayList2.add(Integer.valueOf(this.historyList.indexOf(next2)));
                }
            }
            if (arrayList2.size() == 1) {
                this.timeList.remove(this.historyList.get(((Number) arrayList2.get(0)).intValue()).getTime());
                this.historyList.remove(((Number) arrayList2.get(0)).intValue());
            }
        }
        HistoryAdapter historyAdapter2 = this.historyAdapter;
        if (historyAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("historyAdapter");
        }
        historyAdapter2.notifyDataSetChanged();
        for (History select : this.historyList) {
            if (select.getSelect()) {
                i++;
            }
        }
        TextView textView = (TextView) _$_findCachedViewById(C2128R.C2131id.historyNum);
        Intrinsics.checkExpressionValueIsNotNull(textView, "historyNum");
        String string = getString(C2128R.string.select_num);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.select_num)");
        textView.setText(StringsKt.replace$default(string, "_num", String.valueOf(i), false, 4, (Object) null));
    }

    public void getMeasures(@NotNull BaseListBean<Measure> baseListBean) {
        Intrinsics.checkParameterIsNotNull(baseListBean, "measureBean");
        progressDissmiss();
        if (!baseListBean.getRows().isEmpty()) {
            for (Measure next : baseListBean.getRows()) {
                if (!this.timeList.contains(monthToString(next.getMeasureDate()))) {
                    this.timeList.add(monthToString(next.getMeasureDate()));
                    this.historyList.add(new History(1, monthToString(next.getMeasureDate())));
                    this.historyList.add(new History(2, next, monthToString(next.getMeasureDate())));
                } else {
                    this.historyList.add(new History(2, next, monthToString(next.getMeasureDate())));
                }
            }
        }
        if (baseListBean.getRows().size() == this.pageSize) {
            HistoryAdapter historyAdapter2 = this.historyAdapter;
            if (historyAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("historyAdapter");
            }
            historyAdapter2.getLoadMoreModule().loadMoreComplete();
        } else if (baseListBean.getRows().size() < this.pageSize) {
            HistoryAdapter historyAdapter3 = this.historyAdapter;
            if (historyAdapter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("historyAdapter");
            }
            historyAdapter3.getLoadMoreModule().loadMoreEnd(false);
        }
    }

    private final String monthToString(Date date) {
        String format = new SimpleDateFormat("yyyy-MM", Locale.ENGLISH).format(date);
        Intrinsics.checkExpressionValueIsNotNull(format, "formatter.format(date)");
        return format;
    }

    public void showError(@Nullable Throwable th) {
        progressDissmiss();
    }
}
