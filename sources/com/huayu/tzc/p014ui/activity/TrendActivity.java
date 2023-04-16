package com.huayu.tzc.p014ui.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.google.android.material.tabs.TabLayout;
import com.huayu.tzc.C2128R;
import com.huayu.tzc.adapter.MyFragmentPagerAdapter;
import com.huayu.tzc.base.BaseActivity;
import com.huayu.tzc.bean.Member;
import com.huayu.tzc.contract.MainContract;
import com.huayu.tzc.customview.MyHeaderView;
import com.huayu.tzc.customview.MyViewPager;
import com.huayu.tzc.p014ui.fragment.TrendFragment;
import com.huayu.tzc.presenter.NotPresenter;
import com.huayu.tzc.utils.EventBusUtils;
import com.huayu.tzc.utils.SingleClickKt;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 -2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u00020\u00042\u00020\u0005:\u0001-B\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0014\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u0018\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\b\u0010\u001b\u001a\u00020\u001aH\u0014J\b\u0010\u001c\u001a\u00020\u0003H\u0016J\b\u0010\u001d\u001a\u00020\u001eH\u0002J\b\u0010\u001f\u001a\u00020\u001eH\u0014J\"\u0010 \u001a\u00020\u001e2\u0006\u0010!\u001a\u00020\u001a2\u0006\u0010\"\u001a\u00020\u001a2\b\u0010#\u001a\u0004\u0018\u00010$H\u0014J\u0012\u0010%\u001a\u00020\u001e2\b\u0010&\u001a\u0004\u0018\u00010'H\u0016J\u0010\u0010(\u001a\u00020\u001e2\u0006\u0010)\u001a\u00020*H\u0016J\u0010\u0010+\u001a\u00020\u001e2\u0006\u0010)\u001a\u00020*H\u0016J\u0010\u0010,\u001a\u00020\u001e2\u0006\u0010)\u001a\u00020*H\u0016R\u000e\u0010\u0007\u001a\u00020\bX.¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX.¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\nX\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0011X.¢\u0006\u0004\n\u0002\u0010\u0012R\u000e\u0010\u0013\u001a\u00020\u000fX\u000e¢\u0006\u0002\n\u0000¨\u0006."}, mo21895d2 = {"Lcom/huayu/tzc/ui/activity/TrendActivity;", "Lcom/huayu/tzc/base/BaseActivity;", "Lcom/huayu/tzc/contract/MainContract$View;", "Lcom/huayu/tzc/presenter/NotPresenter;", "Lcom/google/android/material/tabs/TabLayout$OnTabSelectedListener;", "Landroid/view/View$OnClickListener;", "()V", "fragmentAdapter", "Lcom/huayu/tzc/adapter/MyFragmentPagerAdapter;", "fragments", "Ljava/util/ArrayList;", "Landroidx/fragment/app/Fragment;", "member", "Lcom/huayu/tzc/bean/Member;", "titleList", "", "titles", "", "[Ljava/lang/String;", "weightUnit", "dateToString", "date", "Ljava/util/Date;", "getDateBefore", "d", "day", "", "getLayoutId", "getPresenter", "initTab", "", "initView", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onClick", "p0", "Landroid/view/View;", "onTabReselected", "tab", "Lcom/google/android/material/tabs/TabLayout$Tab;", "onTabSelected", "onTabUnselected", "Companion", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
/* renamed from: com.huayu.tzc.ui.activity.TrendActivity */
/* compiled from: TrendActivity.kt */
public final class TrendActivity extends BaseActivity<MainContract.View, NotPresenter> implements TabLayout.OnTabSelectedListener, View.OnClickListener {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    @NotNull
    public static String endTime = "";
    /* access modifiers changed from: private */
    public static int position;
    /* access modifiers changed from: private */
    @NotNull
    public static String startTime = "";
    private HashMap _$_findViewCache;
    private MyFragmentPagerAdapter fragmentAdapter;
    private final ArrayList<Fragment> fragments = new ArrayList<>();
    private Member member;
    private final ArrayList<String> titleList = new ArrayList<>();
    private String[] titles;
    private String weightUnit = "";

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
        return C2128R.C2133layout.activity_trend;
    }

    public void onTabReselected(@NotNull TabLayout.Tab tab) {
        Intrinsics.checkParameterIsNotNull(tab, "tab");
    }

    @Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\b\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0006\"\u0004\b\u0011\u0010\b¨\u0006\u0012"}, mo21895d2 = {"Lcom/huayu/tzc/ui/activity/TrendActivity$Companion;", "", "()V", "endTime", "", "getEndTime", "()Ljava/lang/String;", "setEndTime", "(Ljava/lang/String;)V", "position", "", "getPosition", "()I", "setPosition", "(I)V", "startTime", "getStartTime", "setStartTime", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
    /* renamed from: com.huayu.tzc.ui.activity.TrendActivity$Companion */
    /* compiled from: TrendActivity.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final String getStartTime() {
            return TrendActivity.startTime;
        }

        public final void setStartTime(@NotNull String str) {
            Intrinsics.checkParameterIsNotNull(str, "<set-?>");
            TrendActivity.startTime = str;
        }

        @NotNull
        public final String getEndTime() {
            return TrendActivity.endTime;
        }

        public final void setEndTime(@NotNull String str) {
            Intrinsics.checkParameterIsNotNull(str, "<set-?>");
            TrendActivity.endTime = str;
        }

        public final int getPosition() {
            return TrendActivity.position;
        }

        public final void setPosition(int i) {
            TrendActivity.position = i;
        }
    }

    @NotNull
    public NotPresenter getPresenter() {
        return new NotPresenter();
    }

    /* access modifiers changed from: protected */
    public void initView() {
        Member member2 = (Member) getIntent().getParcelableExtra("member");
        if (member2 == null) {
            member2 = new Member();
        }
        this.member = member2;
        String stringExtra = getIntent().getStringExtra("unit");
        if (stringExtra == null) {
            stringExtra = "kg";
        }
        this.weightUnit = stringExtra;
        String[] stringArray = getResources().getStringArray(C2128R.array.trend_name);
        Intrinsics.checkExpressionValueIsNotNull(stringArray, "resources.getStringArray(R.array.trend_name)");
        this.titles = stringArray;
        initTab();
        Context context = this;
        ((TextView) _$_findCachedViewById(C2128R.C2131id.trend_week)).setTextColor(ContextCompat.getColor(context, C2128R.C2129color.colorBlack));
        ((TextView) _$_findCachedViewById(C2128R.C2131id.trend_month)).setTextColor(ContextCompat.getColor(context, C2128R.C2129color.colorBlue));
        ((TextView) _$_findCachedViewById(C2128R.C2131id.trend_year)).setTextColor(ContextCompat.getColor(context, C2128R.C2129color.colorBlack));
        startTime = dateToString(getDateBefore(new Date(System.currentTimeMillis()), 30));
        endTime = dateToString(new Date(System.currentTimeMillis()));
        EventBusUtils.postSticky(new EventBusUtils.EventMessage(1, startTime, endTime));
        View.OnClickListener onClickListener = this;
        SingleClickKt.singleClick$default((View) (TextView) _$_findCachedViewById(C2128R.C2131id.trend_week), onClickListener, 0, 2, (Object) null);
        View.OnClickListener onClickListener2 = onClickListener;
        SingleClickKt.singleClick$default((View) (TextView) _$_findCachedViewById(C2128R.C2131id.trend_month), onClickListener2, 0, 2, (Object) null);
        SingleClickKt.singleClick$default((View) (TextView) _$_findCachedViewById(C2128R.C2131id.trend_year), onClickListener2, 0, 2, (Object) null);
        SingleClickKt.singleClick$default((View) (ImageView) _$_findCachedViewById(C2128R.C2131id.trend_date), onClickListener2, 0, 2, (Object) null);
    }

    private final void initTab() {
        String[] strArr = this.titles;
        if (strArr == null) {
            Intrinsics.throwUninitializedPropertyAccessException("titles");
        }
        int length = strArr.length;
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            ArrayList<String> arrayList = this.titleList;
            String[] strArr2 = this.titles;
            if (strArr2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("titles");
            }
            arrayList.add(strArr2[i2]);
            TabLayout tabLayout = (TabLayout) _$_findCachedViewById(C2128R.C2131id.trend_tab);
            TabLayout.Tab newTab = ((TabLayout) _$_findCachedViewById(C2128R.C2131id.trend_tab)).newTab();
            String[] strArr3 = this.titles;
            if (strArr3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("titles");
            }
            tabLayout.addTab(newTab.setText((CharSequence) strArr3[i2]));
            ArrayList<Fragment> arrayList2 = this.fragments;
            Member member2 = this.member;
            if (member2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("member");
            }
            arrayList2.add(new TrendFragment(i2, member2.getMember_id(), this.weightUnit));
        }
        this.fragmentAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), this.titleList, this.fragments);
        MyViewPager myViewPager = (MyViewPager) _$_findCachedViewById(C2128R.C2131id.trend_viewpager);
        Intrinsics.checkExpressionValueIsNotNull(myViewPager, "trend_viewpager");
        MyFragmentPagerAdapter myFragmentPagerAdapter = this.fragmentAdapter;
        if (myFragmentPagerAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fragmentAdapter");
        }
        myViewPager.setAdapter(myFragmentPagerAdapter);
        MyViewPager myViewPager2 = (MyViewPager) _$_findCachedViewById(C2128R.C2131id.trend_viewpager);
        Intrinsics.checkExpressionValueIsNotNull(myViewPager2, "trend_viewpager");
        myViewPager2.setOffscreenPageLimit(1);
        ((TabLayout) _$_findCachedViewById(C2128R.C2131id.trend_tab)).setupWithViewPager((MyViewPager) _$_findCachedViewById(C2128R.C2131id.trend_viewpager));
        TabLayout.Tab tabAt = ((TabLayout) _$_findCachedViewById(C2128R.C2131id.trend_tab)).getTabAt(0);
        if (tabAt != null) {
            tabAt.select();
        }
        ((TabLayout) _$_findCachedViewById(C2128R.C2131id.trend_tab)).addOnTabSelectedListener((TabLayout.OnTabSelectedListener) this);
        String[] strArr4 = this.titles;
        if (strArr4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("titles");
        }
        int length2 = strArr4.length;
        while (i < length2) {
            TabLayout.Tab tabAt2 = ((TabLayout) _$_findCachedViewById(C2128R.C2131id.trend_tab)).getTabAt(i);
            if (tabAt2 == null) {
                Intrinsics.throwNpe();
            }
            tabAt2.setCustomView((int) C2128R.C2133layout.tab_item);
            View customView = tabAt2.getCustomView();
            View findViewById = customView != null ? customView.findViewById(C2128R.C2131id.tab_text) : null;
            if (findViewById != null) {
                TextView textView = (TextView) findViewById;
                if (i == 0) {
                    View customView2 = tabAt2.getCustomView();
                    if (customView2 == null) {
                        Intrinsics.throwNpe();
                    }
                    View findViewById2 = customView2.findViewById(C2128R.C2131id.tab_text);
                    Intrinsics.checkExpressionValueIsNotNull(findViewById2, "tab.customView!!.findViewById<View>(R.id.tab_text)");
                    findViewById2.setSelected(true);
                    Context context = this;
                    textView.setTextColor(ContextCompat.getColor(context, C2128R.C2129color.colorWhite));
                    textView.setBackground(ContextCompat.getDrawable(context, C2128R.C2130drawable.radius_10_blue));
                    ((MyHeaderView) _$_findCachedViewById(C2128R.C2131id.trend_header)).setTitle(getString(C2128R.string.trend_weight));
                } else {
                    String[] strArr5 = this.titles;
                    if (strArr5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("titles");
                    }
                    textView.setText(strArr5[i]);
                }
                i++;
            } else {
                throw new TypeCastException("null cannot be cast to non-null type android.widget.TextView");
            }
        }
    }

    public void onTabUnselected(@NotNull TabLayout.Tab tab) {
        Intrinsics.checkParameterIsNotNull(tab, "tab");
        View customView = tab.getCustomView();
        View findViewById = customView != null ? customView.findViewById(C2128R.C2131id.tab_text) : null;
        if (findViewById != null) {
            TextView textView = (TextView) findViewById;
            textView.setTextColor(ContextCompat.getColor(this, C2128R.C2129color.colorBlack));
            textView.setBackgroundColor(getResources().getColor(C2128R.C2129color.colorWhite));
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.widget.TextView");
    }

    public void onTabSelected(@NotNull TabLayout.Tab tab) {
        Intrinsics.checkParameterIsNotNull(tab, "tab");
        if (tab.getCustomView() != null) {
            View customView = tab.getCustomView();
            View findViewById = customView != null ? customView.findViewById(C2128R.C2131id.tab_text) : null;
            if (findViewById != null) {
                TextView textView = (TextView) findViewById;
                Context context = this;
                textView.setTextColor(ContextCompat.getColor(context, C2128R.C2129color.colorWhite));
                textView.setBackground(ContextCompat.getDrawable(context, C2128R.C2130drawable.radius_10_blue));
                ((MyHeaderView) _$_findCachedViewById(C2128R.C2131id.trend_header)).setTitle(textView.getText().toString());
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type android.widget.TextView");
        }
    }

    private final String dateToString(Date date) {
        String format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(date);
        Intrinsics.checkExpressionValueIsNotNull(format, "formatter.format(date)");
        return format;
    }

    private final Date getDateBefore(Date date, int i) {
        Calendar instance = Calendar.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(instance, "now");
        instance.setTime(date);
        instance.set(5, instance.get(5) - i);
        Date time = instance.getTime();
        Intrinsics.checkExpressionValueIsNotNull(time, "now.time");
        return time;
    }

    public void onClick(@Nullable View view) {
        Integer valueOf = view != null ? Integer.valueOf(view.getId()) : null;
        if (valueOf != null && valueOf.intValue() == C2128R.C2131id.trend_week) {
            Context context = this;
            ((TextView) _$_findCachedViewById(C2128R.C2131id.trend_week)).setTextColor(ContextCompat.getColor(context, C2128R.C2129color.colorBlue));
            ((TextView) _$_findCachedViewById(C2128R.C2131id.trend_month)).setTextColor(ContextCompat.getColor(context, C2128R.C2129color.colorBlack));
            ((TextView) _$_findCachedViewById(C2128R.C2131id.trend_year)).setTextColor(ContextCompat.getColor(context, C2128R.C2129color.colorBlack));
            startTime = dateToString(getDateBefore(new Date(System.currentTimeMillis()), 6));
            endTime = dateToString(new Date(System.currentTimeMillis()));
            position = 0;
            EventBusUtils.postSticky(new EventBusUtils.EventMessage(0, startTime, endTime));
        } else if (valueOf != null && valueOf.intValue() == C2128R.C2131id.trend_month) {
            Context context2 = this;
            ((TextView) _$_findCachedViewById(C2128R.C2131id.trend_month)).setTextColor(ContextCompat.getColor(context2, C2128R.C2129color.colorBlue));
            ((TextView) _$_findCachedViewById(C2128R.C2131id.trend_week)).setTextColor(ContextCompat.getColor(context2, C2128R.C2129color.colorBlack));
            ((TextView) _$_findCachedViewById(C2128R.C2131id.trend_year)).setTextColor(ContextCompat.getColor(context2, C2128R.C2129color.colorBlack));
            startTime = dateToString(getDateBefore(new Date(System.currentTimeMillis()), 30));
            endTime = dateToString(new Date(System.currentTimeMillis()));
            position = 1;
            EventBusUtils.postSticky(new EventBusUtils.EventMessage(1, startTime, endTime));
        } else if (valueOf != null && valueOf.intValue() == C2128R.C2131id.trend_year) {
            Context context3 = this;
            ((TextView) _$_findCachedViewById(C2128R.C2131id.trend_year)).setTextColor(ContextCompat.getColor(context3, C2128R.C2129color.colorBlue));
            ((TextView) _$_findCachedViewById(C2128R.C2131id.trend_month)).setTextColor(ContextCompat.getColor(context3, C2128R.C2129color.colorBlack));
            ((TextView) _$_findCachedViewById(C2128R.C2131id.trend_week)).setTextColor(ContextCompat.getColor(context3, C2128R.C2129color.colorBlack));
            Calendar instance = Calendar.getInstance();
            Calendar instance2 = Calendar.getInstance();
            instance2.clear();
            instance2.set(1, instance.get(1));
            Intrinsics.checkExpressionValueIsNotNull(instance2, "calendar");
            Date time = instance2.getTime();
            Intrinsics.checkExpressionValueIsNotNull(time, "calendar.time");
            startTime = dateToString(time);
            endTime = dateToString(new Date(System.currentTimeMillis()));
            position = 2;
            EventBusUtils.postSticky(new EventBusUtils.EventMessage(2, startTime, endTime));
        } else if (valueOf != null && valueOf.intValue() == C2128R.C2131id.trend_date) {
            startActivityForResult(new Intent(this, ChoiceDateActivity.class), 811);
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        String str;
        String str2;
        super.onActivityResult(i, i2, intent);
        if (i == 811 && i2 == -1) {
            if (intent == null || (str = intent.getStringExtra("startTime")) == null) {
                str = dateToString(getDateBefore(new Date(System.currentTimeMillis()), 6));
            }
            startTime = str;
            if (intent == null || (str2 = intent.getStringExtra("endTime")) == null) {
                str2 = dateToString(new Date(System.currentTimeMillis()));
            }
            endTime = str2;
            Context context = this;
            ((TextView) _$_findCachedViewById(C2128R.C2131id.trend_year)).setTextColor(ContextCompat.getColor(context, C2128R.C2129color.colorBlack));
            ((TextView) _$_findCachedViewById(C2128R.C2131id.trend_month)).setTextColor(ContextCompat.getColor(context, C2128R.C2129color.colorBlack));
            ((TextView) _$_findCachedViewById(C2128R.C2131id.trend_week)).setTextColor(ContextCompat.getColor(context, C2128R.C2129color.colorBlack));
            position = 3;
            EventBusUtils.postSticky(new EventBusUtils.EventMessage(3, startTime, endTime));
        }
    }
}
