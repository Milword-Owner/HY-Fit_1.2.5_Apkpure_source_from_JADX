package com.huayu.tzc.p014ui.activity.setting;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.icu.text.SimpleDateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Switch;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.view.TimePickerView;
import com.google.gson.Gson;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;
import com.huayu.tzc.C2128R;
import com.huayu.tzc.adapter.RemindAdapter;
import com.huayu.tzc.base.BaseActivity;
import com.huayu.tzc.bean.Remind;
import com.huayu.tzc.contract.MainContract;
import com.huayu.tzc.customview.MyHeaderView;
import com.huayu.tzc.presenter.NotPresenter;
import com.tencent.mmkv.MMKV;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\tH\u0003J\u0012\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0003J\b\u0010\u001b\u001a\u00020\tH\u0014J\u0012\u0010\u001c\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0003J\b\u0010\u001d\u001a\u00020\u0003H\u0016J\u0010\u0010\u001e\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0003J\b\u0010\u001f\u001a\u00020\u0013H\u0002J\b\u0010 \u001a\u00020\u0013H\u0002J\b\u0010!\u001a\u00020\u0013H\u0014J\u0010\u0010\"\u001a\u00020\u00132\u0006\u0010#\u001a\u00020\tH\u0002J\u0010\u0010$\u001a\u00020\u001a2\u0006\u0010\u0019\u001a\u00020\u0018H\u0003J\b\u0010%\u001a\u00020\u0013H\u0002J\u0010\u0010%\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\tH\u0002R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX.¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX.¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u000e¢\u0006\u0002\n\u0000¨\u0006&"}, mo21895d2 = {"Lcom/huayu/tzc/ui/activity/setting/RemindActivity;", "Lcom/huayu/tzc/base/BaseActivity;", "Lcom/huayu/tzc/contract/MainContract$View;", "Lcom/huayu/tzc/presenter/NotPresenter;", "()V", "calendar", "Ljava/util/Calendar;", "kotlin.jvm.PlatformType", "hour", "", "min", "pickerView", "Lcom/bigkoo/pickerview/view/TimePickerView;", "remindAdapter", "Lcom/huayu/tzc/adapter/RemindAdapter;", "remindList", "", "Lcom/huayu/tzc/bean/Remind;", "clickSwitch", "", "view", "Landroid/view/View;", "position", "getHourTime", "", "date", "Ljava/util/Date;", "getLayoutId", "getMTime", "getPresenter", "getTime", "initAdapter", "initData", "initView", "requestCalendarPermission", "requestCode", "setTime", "showDatePickerView", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
/* renamed from: com.huayu.tzc.ui.activity.setting.RemindActivity */
/* compiled from: RemindActivity.kt */
public final class RemindActivity extends BaseActivity<MainContract.View, NotPresenter> {
    private HashMap _$_findViewCache;
    /* access modifiers changed from: private */
    public final Calendar calendar = Calendar.getInstance();
    private int hour;
    private int min;
    /* access modifiers changed from: private */
    public TimePickerView pickerView;
    /* access modifiers changed from: private */
    public RemindAdapter remindAdapter;
    /* access modifiers changed from: private */
    public List<Remind> remindList = new ArrayList();

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
        return C2128R.C2133layout.activity_remind;
    }

    public static final /* synthetic */ TimePickerView access$getPickerView$p(RemindActivity remindActivity) {
        TimePickerView timePickerView = remindActivity.pickerView;
        if (timePickerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("pickerView");
        }
        return timePickerView;
    }

    public static final /* synthetic */ RemindAdapter access$getRemindAdapter$p(RemindActivity remindActivity) {
        RemindAdapter remindAdapter2 = remindActivity.remindAdapter;
        if (remindAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("remindAdapter");
        }
        return remindAdapter2;
    }

    @NotNull
    public NotPresenter getPresenter() {
        return new NotPresenter();
    }

    /* access modifiers changed from: protected */
    public void initView() {
        ((MyHeaderView) _$_findCachedViewById(C2128R.C2131id.remindHeader)).setTextClickListener(new RemindActivity$initView$1(this));
        initAdapter();
    }

    private final void initAdapter() {
        this.remindAdapter = new RemindAdapter(this.remindList);
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(C2128R.C2131id.remindRecyclerview);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView, "remindRecyclerview");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView recyclerView2 = (RecyclerView) _$_findCachedViewById(C2128R.C2131id.remindRecyclerview);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView2, "remindRecyclerview");
        RemindAdapter remindAdapter2 = this.remindAdapter;
        if (remindAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("remindAdapter");
        }
        recyclerView2.setAdapter(remindAdapter2);
        RemindAdapter remindAdapter3 = this.remindAdapter;
        if (remindAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("remindAdapter");
        }
        remindAdapter3.setOnItemClickListener(new RemindActivity$initAdapter$1(this));
        RemindAdapter remindAdapter4 = this.remindAdapter;
        if (remindAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("remindAdapter");
        }
        remindAdapter4.setOnItemChildClickListener(new RemindActivity$initAdapter$2(this));
        initData();
    }

    /* access modifiers changed from: private */
    @SuppressLint({"UseSwitchCompatOrMaterialCode"})
    public final void clickSwitch(View view, int i) {
        Switch switchR = (Switch) view.findViewById(C2128R.C2131id.remind_switch);
        Intrinsics.checkExpressionValueIsNotNull(switchR, "mSwitch");
        this.remindList.get(i).setChecked(switchR.isChecked());
        RemindAdapter remindAdapter2 = this.remindAdapter;
        if (remindAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("remindAdapter");
        }
        remindAdapter2.notifyDataSetChanged();
        MMKV.defaultMMKV().encode("remind", new Gson().toJson((Object) this.remindList));
        Date time = setTime(this.remindList.get(i).getDate());
        String hourTime = getHourTime(time);
        this.hour = hourTime != null ? Integer.parseInt(hourTime) : 0;
        String mTime = getMTime(time);
        this.min = mTime != null ? Integer.parseInt(mTime) : 0;
        String tag = getTAG();
        Log.e(tag, "onItemChildClick: " + this.hour + "  " + this.min);
        Calendar calendar2 = this.calendar;
        Intrinsics.checkExpressionValueIsNotNull(calendar2, "calendar");
        calendar2.setTimeInMillis(System.currentTimeMillis());
        this.calendar.set(11, this.hour);
        this.calendar.set(12, this.min);
        this.calendar.set(13, 0);
        this.calendar.set(14, 0);
        setTitle(this.remindList.get(i).getDate());
        if (switchR.isChecked()) {
            requestCalendarPermission(616);
        } else {
            requestCalendarPermission(617);
        }
    }

    private final void initData() {
        String decodeString = MMKV.defaultMMKV().decodeString("remind", "");
        Intrinsics.checkExpressionValueIsNotNull(decodeString, "json");
        if (decodeString.length() > 0) {
            Object fromJson = new Gson().fromJson(decodeString, new RemindActivity$initData$remindBeanList$1().getType());
            Intrinsics.checkExpressionValueIsNotNull(fromJson, "Gson().fromJson<List<Rem…ist<Remind?>?>() {}.type)");
            this.remindList.addAll((List) fromJson);
            RemindAdapter remindAdapter2 = this.remindAdapter;
            if (remindAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("remindAdapter");
            }
            remindAdapter2.notifyDataSetChanged();
            return;
        }
        this.remindList.add(new Remind("08:00", false));
        this.remindList.add(new Remind("12:00", false));
        this.remindList.add(new Remind("17:00", false));
    }

    /* access modifiers changed from: private */
    public final void requestCalendarPermission(int i) {
        XXPermissions.with((FragmentActivity) this).permission(Permission.WRITE_CALENDAR).permission(Permission.READ_CALENDAR).request(new RemindActivity$requestCalendarPermission$1(this, i));
    }

    /* access modifiers changed from: private */
    public final void showDatePickerView(int i) {
        TimePickerView build = new TimePickerBuilder(this, new RemindActivity$showDatePickerView$1(this, i)).setOutSideCancelable(true).setCancelText(getString(C2128R.string.common_cancel)).setSubmitText(getString(C2128R.string.f1665ok)).setType(new boolean[]{false, false, false, true, true, false}).setLabel(getString(C2128R.string.common_year), getString(C2128R.string.common_month), getString(C2128R.string.common_day), getString(C2128R.string.hour), getString(C2128R.string.min), getString(C2128R.string.f1666ss)).addOnCancelClickListener(new RemindActivity$showDatePickerView$2(this, i)).build();
        Intrinsics.checkExpressionValueIsNotNull(build, "TimePickerBuilder(this,\n…   }\n            .build()");
        this.pickerView = build;
        TimePickerView timePickerView = this.pickerView;
        if (timePickerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("pickerView");
        }
        timePickerView.show();
    }

    /* access modifiers changed from: private */
    public final void showDatePickerView() {
        TimePickerView build = new TimePickerBuilder(this, new RemindActivity$showDatePickerView$3(this)).setOutSideCancelable(true).setCancelText(getString(C2128R.string.common_cancel)).setSubmitText(getString(C2128R.string.f1665ok)).setType(new boolean[]{false, false, false, true, true, false}).setLabel(getString(C2128R.string.common_year), getString(C2128R.string.common_month), getString(C2128R.string.common_day), getString(C2128R.string.hour), getString(C2128R.string.min), getString(C2128R.string.f1666ss)).build();
        Intrinsics.checkExpressionValueIsNotNull(build, "TimePickerBuilder(this,\n…ss)\n            ).build()");
        this.pickerView = build;
        TimePickerView timePickerView = this.pickerView;
        if (timePickerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("pickerView");
        }
        timePickerView.show();
    }

    /* access modifiers changed from: private */
    @TargetApi(24)
    public final String getTime(Date date) {
        String format = new SimpleDateFormat("HH:mm", Locale.ENGLISH).format(date);
        Intrinsics.checkExpressionValueIsNotNull(format, "format.format(date)");
        return format;
    }

    @TargetApi(24)
    private final Date setTime(String str) {
        try {
            Date parse = new SimpleDateFormat("HH:mm", Locale.ENGLISH).parse(str);
            Intrinsics.checkExpressionValueIsNotNull(parse, "format.parse(date)");
            return parse;
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }

    @TargetApi(24)
    private final String getHourTime(Date date) {
        return new SimpleDateFormat("HH", Locale.ENGLISH).format(date);
    }

    @TargetApi(24)
    private final String getMTime(Date date) {
        return new SimpleDateFormat("mm", Locale.ENGLISH).format(date);
    }
}
