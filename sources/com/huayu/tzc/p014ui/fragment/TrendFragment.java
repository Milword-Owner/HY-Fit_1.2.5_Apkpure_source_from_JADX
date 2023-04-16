package com.huayu.tzc.p014ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import com.baidu.mobstat.Config;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.utils.Utils;
import com.huayu.tzc.C2128R;
import com.huayu.tzc.base.BaseBean;
import com.huayu.tzc.base.BaseFragment;
import com.huayu.tzc.base.Constant;
import com.huayu.tzc.bean.Member;
import com.huayu.tzc.bean.Trend;
import com.huayu.tzc.contract.MainContract;
import com.huayu.tzc.customview.BodyStateView;
import com.huayu.tzc.customview.LineChartMarkView;
import com.huayu.tzc.p014ui.activity.TrendActivity;
import com.huayu.tzc.presenter.TrendPresenter;
import com.huayu.tzc.utils.BodyUtil;
import com.huayu.tzc.utils.DateUtil;
import com.huayu.tzc.utils.EventBusUtils;
import com.huayu.tzc.utils.RangeUtil;
import com.huayu.tzc.utils.UnitUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u00020\u0002B\u001d\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0004\u001a\u00020\u0005H\u0002J \u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0002J\b\u0010!\u001a\u00020\u0005H\u0014J\b\u0010\"\u001a\u00020\u0003H\u0014J\u001c\u0010#\u001a\u00020\u001b2\u0012\u0010$\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140&0%H\u0016J\u0010\u0010'\u001a\u00020\u001b2\u0006\u0010(\u001a\u00020)H\u0002J\b\u0010*\u001a\u00020\u001bH\u0002J\"\u0010+\u001a\u00020\u001b2\u0006\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020\u00052\b\u0010/\u001a\u0004\u0018\u000100H\u0002J\b\u00101\u001a\u00020\u001bH\u0014J\b\u00102\u001a\u00020\u001bH\u0016J\u0014\u00103\u001a\u00020\u001b2\n\u00104\u001a\u0006\u0012\u0002\b\u000305H\u0007J\u001a\u00106\u001a\u00020\u001b2\b\u00107\u001a\u0004\u0018\u0001082\u0006\u0010(\u001a\u00020)H\u0002J\u0012\u00109\u001a\u00020\u001b2\b\u0010:\u001a\u0004\u0018\u00010;H\u0016J\u001e\u0010<\u001a\u00020\u001b2\f\u0010=\u001a\b\u0012\u0004\u0012\u00020\u00140&2\u0006\u0010(\u001a\u00020)H\u0002R\u000e\u0010\n\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX.¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013X\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\b0\u0016X\u0004¢\u0006\u0004\n\u0002\u0010\u0017R\u0016\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\b0\u0016X\u0004¢\u0006\u0004\n\u0002\u0010\u0017R\u000e\u0010\u0006\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\b0\u0016X\u0004¢\u0006\u0004\n\u0002\u0010\u0017R\u000e\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000¨\u0006>"}, mo21895d2 = {"Lcom/huayu/tzc/ui/fragment/TrendFragment;", "Lcom/huayu/tzc/base/BaseFragment;", "Lcom/huayu/tzc/contract/MainContract$TrendView;", "Lcom/huayu/tzc/presenter/TrendPresenter;", "index", "", "userId", "weightUnit", "", "(IILjava/lang/String;)V", "age", "endTime", "height", "member", "Lcom/huayu/tzc/bean/Member;", "position", "sex", "startTime", "trendList", "", "Lcom/huayu/tzc/bean/Trend;", "types", "", "[Ljava/lang/String;", "unit", "weeks", "addView", "", "drawView", "bodyStateView", "Lcom/huayu/tzc/customview/BodyStateView;", "num", "", "getLayoutId", "getPresenter", "getTrend", "baseBean", "Lcom/huayu/tzc/base/BaseBean;", "", "initChart", "lineChart", "Lcom/github/mikephil/charting/charts/LineChart;", "initData", "initLineDataSet", "lineDataSet", "Lcom/github/mikephil/charting/data/LineDataSet;", "color", "mode", "Lcom/github/mikephil/charting/data/LineDataSet$Mode;", "initView", "onDestroy", "onMessageEventMain", "event", "Lcom/huayu/tzc/utils/EventBusUtils$EventMessage;", "setChartFillDrawable", "drawable", "Landroid/graphics/drawable/Drawable;", "showError", "e", "", "showLineChart", "dataList", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
/* renamed from: com.huayu.tzc.ui.fragment.TrendFragment */
/* compiled from: TrendFragment.kt */
public final class TrendFragment extends BaseFragment<MainContract.TrendView, TrendPresenter> implements MainContract.TrendView {
    private HashMap _$_findViewCache;
    private int age;
    private String endTime;
    private int height;
    private int index;
    private Member member;
    private int position;
    private int sex;
    private String startTime;
    private List<Trend> trendList;
    private final String[] types;
    private final String[] unit;
    private int userId;
    private final String[] weeks;
    private String weightUnit = "kg";

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
        View view2 = getView();
        if (view2 == null) {
            return null;
        }
        View findViewById = view2.findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return C2128R.C2133layout.fragment_trend;
    }

    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public TrendFragment(int i, int i2, @NotNull String str) {
        String str2 = str;
        Intrinsics.checkParameterIsNotNull(str2, "weightUnit");
        this.index = i;
        this.userId = i2;
        this.weightUnit = str2;
        this.types = new String[]{"weight", "bmi", "bodyfat_rate", "subcutaneousfat_rate", "visceral_fat", "bodywater_rate", "skeletalfat_percentage", "muscle_mass", "bone_mass", "protein_rate", "basalmetabolic_rate", "body_score"};
        this.weeks = new String[]{"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        this.unit = new String[]{"", "", "%", "%", "", "%", "%", "", "", "%", "kcal", ""};
        this.trendList = new ArrayList();
        this.startTime = "";
        this.endTime = "";
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public final void onMessageEventMain(@NotNull EventBusUtils.EventMessage<?> eventMessage) {
        Intrinsics.checkParameterIsNotNull(eventMessage, NotificationCompat.CATEGORY_EVENT);
        int code = eventMessage.getCode();
        if (code >= 0 && 3 >= code) {
            int i = this.position;
            if (i == 3 || i != eventMessage.getCode()) {
                String startTime2 = eventMessage.getStartTime();
                Intrinsics.checkExpressionValueIsNotNull(startTime2, "event.startTime");
                this.startTime = startTime2;
                String endTime2 = eventMessage.getEndTime();
                Intrinsics.checkExpressionValueIsNotNull(endTime2, "event.endTime");
                this.endTime = endTime2;
                this.position = eventMessage.getCode();
                progressShow();
                TrendPresenter trendPresenter = (TrendPresenter) getMPresenter();
                if (trendPresenter != null) {
                    Member member2 = this.member;
                    if (member2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("member");
                    }
                    trendPresenter.getTrend(member2.getMember_id(), this.startTime, this.endTime, this.types[this.index]);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void initView() {
        initData();
        progressShow();
        TrendPresenter trendPresenter = (TrendPresenter) getMPresenter();
        if (trendPresenter != null) {
            Member member2 = this.member;
            if (member2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("member");
            }
            trendPresenter.getTrend(member2.getMember_id(), TrendActivity.Companion.getStartTime(), TrendActivity.Companion.getEndTime(), this.types[this.index]);
        }
        LineChart lineChart = (LineChart) _$_findCachedViewById(C2128R.C2131id.trend_linechart);
        Intrinsics.checkExpressionValueIsNotNull(lineChart, "trend_linechart");
        initChart(lineChart);
        addView(this.index);
        EventBusUtils.register(this);
        LineChartMarkView lineChartMarkView = new LineChartMarkView(getContext(), this.trendList);
        lineChartMarkView.setChartView((LineChart) _$_findCachedViewById(C2128R.C2131id.trend_linechart));
        LineChart lineChart2 = (LineChart) _$_findCachedViewById(C2128R.C2131id.trend_linechart);
        Intrinsics.checkExpressionValueIsNotNull(lineChart2, "trend_linechart");
        lineChart2.setMarker(lineChartMarkView);
    }

    private final void initData() {
        Member member2;
        Intent intent;
        int i = this.index;
        if (i == 0 || i == 7 || i == 8) {
            String[] strArr = this.unit;
            int i2 = this.index;
            strArr[i2] = this.weightUnit;
            if (Intrinsics.areEqual((Object) strArr[i2], (Object) "st:lb")) {
                this.unit[this.index] = "st";
            }
        }
        FragmentActivity activity = getActivity();
        if (activity == null || (intent = activity.getIntent()) == null || (member2 = (Member) intent.getParcelableExtra("member")) == null) {
            member2 = new Member();
        }
        this.member = member2;
        Member member3 = this.member;
        if (member3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("member");
        }
        this.sex = member3.getM_gender();
        Member member4 = this.member;
        if (member4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("member");
        }
        this.age = member4.getUserAge();
        Member member5 = this.member;
        if (member5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("member");
        }
        this.height = member5.getHeight();
        this.position = TrendActivity.Companion.getPosition();
    }

    private final void addView(int i) {
        TextView textView = (TextView) _$_findCachedViewById(C2128R.C2131id.bmi_text);
        Intrinsics.checkExpressionValueIsNotNull(textView, "bmi_text");
        textView.setVisibility(0);
        TextView textView2 = (TextView) _$_findCachedViewById(C2128R.C2131id.bmi_text);
        Intrinsics.checkExpressionValueIsNotNull(textView2, "bmi_text");
        textView2.setText(getResources().getStringArray(C2128R.array.trend_description)[i]);
        if (i == 11) {
            BodyStateView bodyStateView = (BodyStateView) _$_findCachedViewById(C2128R.C2131id.view_state);
            Intrinsics.checkExpressionValueIsNotNull(bodyStateView, "view_state");
            bodyStateView.setVisibility(8);
            return;
        }
        BodyStateView bodyStateView2 = (BodyStateView) _$_findCachedViewById(C2128R.C2131id.view_state);
        Intrinsics.checkExpressionValueIsNotNull(bodyStateView2, "view_state");
        drawView(i, bodyStateView2, -1.0f);
        BodyStateView bodyStateView3 = (BodyStateView) _$_findCachedViewById(C2128R.C2131id.view_state);
        Intrinsics.checkExpressionValueIsNotNull(bodyStateView3, "view_state");
        bodyStateView3.setVisibility(0);
    }

    /* access modifiers changed from: protected */
    @NotNull
    public TrendPresenter getPresenter() {
        return new TrendPresenter();
    }

    public void getTrend(@NotNull BaseBean<List<Trend>> baseBean) {
        double d;
        String[] strArr;
        Intrinsics.checkParameterIsNotNull(baseBean, "baseBean");
        progressDissmiss();
        this.trendList.clear();
        if (baseBean.getMeta().getSuccess() && baseBean.getData() != null) {
            List<Trend> data = baseBean.getData();
            if (data == null) {
                Intrinsics.throwNpe();
            }
            if (!data.isEmpty()) {
                List<Trend> list = this.trendList;
                List<Trend> data2 = baseBean.getData();
                if (data2 == null) {
                    Intrinsics.throwNpe();
                }
                list.addAll(data2);
            }
        }
        int size = this.trendList.size();
        for (int i = 0; i < size; i++) {
            if (StringsKt.contains$default((CharSequence) this.trendList.get(i).getContent(), (CharSequence) Config.TRACE_TODAY_VISIT_SPLIT, false, 2, (Object) null)) {
                this.trendList.get(i).setContent(String.valueOf(UnitUtils.stlb2St(this.trendList.get(i).getContent())));
            }
        }
        String[] strArr2 = new String[this.trendList.size()];
        int size2 = this.trendList.size();
        double d2 = Utils.DOUBLE_EPSILON;
        if (size2 > 0) {
            d2 = Double.parseDouble(this.trendList.get(0).getContent());
            d = Double.parseDouble(this.trendList.get(0).getContent());
        } else {
            d = 0.0d;
        }
        try {
            int size3 = this.trendList.size();
            for (int i2 = 0; i2 < size3; i2++) {
                if (d2 < Double.parseDouble(this.trendList.get(i2).getContent())) {
                    d2 = Double.parseDouble(this.trendList.get(i2).getContent());
                }
                if (d > Double.parseDouble(this.trendList.get(i2).getContent())) {
                    d = Double.parseDouble(this.trendList.get(i2).getContent());
                }
                if (this.position == 0) {
                    strArr2[i2] = this.weeks[DateUtil.todayWeek(new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(this.trendList.get(i2).getMeasuredate())) - 1];
                } else {
                    strArr2[i2] = this.trendList.get(i2).getMeasuredate();
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        View mView = getMView();
        if (mView == null) {
            Intrinsics.throwNpe();
        }
        TextView textView = (TextView) mView.findViewById(C2128R.C2131id.trend_total_day);
        View mView2 = getMView();
        if (mView2 == null) {
            Intrinsics.throwNpe();
        }
        TextView textView2 = (TextView) mView2.findViewById(C2128R.C2131id.trend_total_num);
        View mView3 = getMView();
        if (mView3 == null) {
            Intrinsics.throwNpe();
        }
        TextView textView3 = (TextView) mView3.findViewById(C2128R.C2131id.trend_total_max);
        View mView4 = getMView();
        if (mView4 == null) {
            Intrinsics.throwNpe();
        }
        TextView textView4 = (TextView) mView4.findViewById(C2128R.C2131id.trend_total_min);
        View mView5 = getMView();
        if (mView5 == null) {
            Intrinsics.throwNpe();
        }
        LineChart lineChart = (LineChart) mView5.findViewById(C2128R.C2131id.trend_linechart);
        if (this.trendList.size() > 0) {
            Intrinsics.checkExpressionValueIsNotNull(textView, "totalDay");
            StringBuilder sb = new StringBuilder();
            strArr = strArr2;
            sb.append(String.valueOf(DateUtil.getDays(this.startTime, this.endTime) + 1));
            sb.append("");
            textView.setText(sb.toString());
            Intrinsics.checkExpressionValueIsNotNull(textView2, "totalNum");
            textView2.setText(String.valueOf(this.trendList.size()) + "");
            Intrinsics.checkExpressionValueIsNotNull(textView3, "totalMax");
            textView3.setText("" + d2 + this.unit[this.index]);
            Intrinsics.checkExpressionValueIsNotNull(textView4, "totalMin");
            textView4.setText("" + d + this.unit[this.index]);
        } else {
            strArr = strArr2;
            Intrinsics.checkExpressionValueIsNotNull(textView, "totalDay");
            textView.setText("-");
            Intrinsics.checkExpressionValueIsNotNull(textView2, "totalNum");
            textView2.setText("-");
            Intrinsics.checkExpressionValueIsNotNull(textView3, "totalMax");
            textView3.setText("-");
            Intrinsics.checkExpressionValueIsNotNull(textView4, "totalMin");
            textView4.setText("-");
        }
        Intrinsics.checkExpressionValueIsNotNull(lineChart, "lineChart");
        XAxis xAxis = lineChart.getXAxis();
        Intrinsics.checkExpressionValueIsNotNull(xAxis, "lineChart.xAxis");
        xAxis.setValueFormatter(new IndexAxisValueFormatter(strArr));
        showLineChart(this.trendList, lineChart);
    }

    public void showError(@Nullable Throwable th) {
        progressDissmiss();
    }

    private final void initChart(LineChart lineChart) {
        lineChart.setDrawGridBackground(false);
        lineChart.setDrawBorders(false);
        lineChart.setDragEnabled(true);
        lineChart.setTouchEnabled(true);
        lineChart.setScaleXEnabled(true);
        lineChart.setScaleYEnabled(false);
        lineChart.setViewPortOffsets(150.0f, 50.0f, 100.0f, 80.0f);
        lineChart.setVisibleXRange(5.0f, 5.0f);
        XAxis xAxis = lineChart.getXAxis();
        YAxis axisLeft = lineChart.getAxisLeft();
        YAxis axisRight = lineChart.getAxisRight();
        Intrinsics.checkExpressionValueIsNotNull(xAxis, "xAxis");
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1.0f);
        xAxis.setAxisLineColor(Color.parseColor("#E5E5E5"));
        Legend legend = lineChart.getLegend();
        Intrinsics.checkExpressionValueIsNotNull(legend, "lineChart.legend");
        legend.setForm(Legend.LegendForm.NONE);
        xAxis.setDrawGridLines(false);
        axisRight.setDrawGridLines(false);
        axisLeft.setDrawGridLines(true);
        Intrinsics.checkExpressionValueIsNotNull(axisLeft, "leftYAxis");
        axisLeft.setXOffset(15.0f);
        Intrinsics.checkExpressionValueIsNotNull(axisRight, "rightYaxis");
        axisRight.setEnabled(false);
        axisLeft.setEnabled(true);
        axisLeft.setAxisLineColor(Color.parseColor("#ffffff"));
        Description description = new Description();
        description.setEnabled(false);
        lineChart.setDescription(description);
        xAxis.setValueFormatter(new IndexAxisValueFormatter());
        axisLeft.setSpaceTop(20.0f);
        axisLeft.setLabelCount(3, false);
        xAxis.setLabelCount(7, false);
        axisLeft.setSpaceTop(180.0f);
        axisRight.setSpaceTop(180.0f);
        axisLeft.setSpaceBottom(10.0f);
        axisRight.setSpaceBottom(10.0f);
        xAxis.setGranularity(1.0f);
        axisLeft.enableGridDashedLine(10.0f, 10.0f, 0.0f);
        if (this.trendList.size() >= 7) {
            lineChart.setScaleMinima(((float) (this.trendList.size() / 5)) + ((float) 2), 1.0f);
        } else {
            lineChart.setScaleMinima(0.5f, 1.0f);
        }
    }

    private final void setChartFillDrawable(Drawable drawable, LineChart lineChart) {
        if (lineChart.getData() != null) {
            LineData lineData = (LineData) lineChart.getData();
            Intrinsics.checkExpressionValueIsNotNull(lineData, "lineChart.data");
            if (lineData.getDataSetCount() > 0) {
                IDataSet dataSetByIndex = ((LineData) lineChart.getData()).getDataSetByIndex(0);
                if (dataSetByIndex != null) {
                    LineDataSet lineDataSet = (LineDataSet) dataSetByIndex;
                    lineDataSet.setDrawFilled(true);
                    lineDataSet.setFillDrawable(drawable);
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type com.github.mikephil.charting.data.LineDataSet");
            }
        }
    }

    private final void initLineDataSet(LineDataSet lineDataSet, int i, LineDataSet.Mode mode) {
        lineDataSet.setColor(i);
        Context context = getContext();
        if (context == null) {
            Intrinsics.throwNpe();
        }
        lineDataSet.setCircleColor(ContextCompat.getColor(context, C2128R.C2129color.blue30));
        lineDataSet.setLineWidth(2.0f);
        lineDataSet.setCircleRadius(8.0f);
        lineDataSet.setDrawCircleHole(true);
        lineDataSet.setValueTextSize(10.0f);
        lineDataSet.setDrawFilled(true);
        lineDataSet.setFormLineWidth(1.0f);
        lineDataSet.setFormSize(15.0f);
        if (mode == null) {
            lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        } else {
            lineDataSet.setMode(mode);
        }
        lineDataSet.setHighLightColor(Color.parseColor("#3A84FF"));
        lineDataSet.setHighlightEnabled(true);
        lineDataSet.setHighlightLineWidth(1.0f);
        lineDataSet.enableDashedHighlightLine(10.0f, 5.0f, 0.0f);
        lineDataSet.setDrawHorizontalHighlightIndicator(false);
        lineDataSet.setDrawVerticalHighlightIndicator(true);
        lineDataSet.setDrawCircleHole(true);
        lineDataSet.setCircleHoleColor(i);
        lineDataSet.setCircleHoleRadius(4.0f);
        lineDataSet.setDrawValues(true);
        lineDataSet.setValueFormatter(new TrendFragment$initLineDataSet$1());
    }

    private final void showLineChart(List<Trend> list, LineChart lineChart) {
        List arrayList = new ArrayList();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            arrayList.add(new Entry((float) i, Float.parseFloat(list.get(i).getContent())));
        }
        if (arrayList.size() == 0) {
            lineChart.clear();
            return;
        }
        LineDataSet lineDataSet = new LineDataSet(arrayList, "");
        initLineDataSet(lineDataSet, Color.parseColor("#3A84FF"), LineDataSet.Mode.CUBIC_BEZIER);
        if (this.trendList.size() >= 7) {
            lineChart.setScaleMinima(((float) (this.trendList.size() / 5)) + ((float) 2), 1.0f);
        } else {
            lineChart.setScaleMinima(0.5f, 1.0f);
        }
        if (this.trendList.size() >= 7) {
            lineChart.moveViewToX(((float) this.trendList.size()) - ((float) 1));
        } else {
            lineChart.moveViewToX(0.0f);
        }
        lineChart.setData(new LineData(lineDataSet));
        Context context = getContext();
        setChartFillDrawable(context != null ? ContextCompat.getDrawable(context, C2128R.C2130drawable.fade_blue) : null, lineChart);
        lineChart.postInvalidate();
    }

    private final void drawView(int i, BodyStateView bodyStateView, float f) {
        String str = this.weightUnit;
        switch (i) {
            case 0:
                String[] stringArray = getResources().getStringArray(C2128R.array.weight_status);
                Member member2 = this.member;
                if (member2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("member");
                }
                int m_gender = member2.getM_gender();
                Member member3 = this.member;
                if (member3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("member");
                }
                bodyStateView.canvasContent(stringArray, BodyUtil.getUnits(RangeUtil.getWeightArray(m_gender, (float) member3.getHeight()), this.weightUnit), -1.0f, Constant.COLORS4);
                return;
            case 1:
                bodyStateView.canvasContent(getResources().getStringArray(C2128R.array.have_red_status), Constant.BMI_FALSE, f, Constant.COLORS3);
                return;
            case 2:
                bodyStateView.canvasContent(getResources().getStringArray(C2128R.array.fat_status), RangeUtil.getFatRangFalse(this.sex, this.age), f, Constant.COLORSFat);
                return;
            case 3:
                bodyStateView.canvasContent(getResources().getStringArray(C2128R.array.have_red_status), RangeUtil.getPxRang(this.sex), f, Constant.COLORS3);
                return;
            case 4:
                bodyStateView.canvasContent(getResources().getStringArray(C2128R.array.visceral_fat), Constant.NZZZ, f);
                return;
            case 5:
                bodyStateView.canvasContent(getResources().getStringArray(C2128R.array.no_red_status), RangeUtil.getTsRang(this.sex), f, Constant.COLORS2);
                return;
            case 6:
                bodyStateView.canvasContent(getResources().getStringArray(C2128R.array.no_red_status), Constant.GGJ, f, Constant.COLORS2);
                return;
            case 7:
                bodyStateView.canvasContent(getResources().getStringArray(C2128R.array.no_red_status), BodyUtil.getUnits(RangeUtil.getJRRange(this.sex, (float) this.height), str), f, Constant.COLORS2);
                return;
            case 8:
                bodyStateView.canvasContent(getResources().getStringArray(C2128R.array.no_red_status), BodyUtil.getUnits(Constant.f1685GL, str), f, Constant.COLORS2);
                return;
            case 9:
                bodyStateView.canvasContent(getResources().getStringArray(C2128R.array.have_red_status), Constant.DBZ, f, Constant.COLORS3);
                return;
            case 10:
                bodyStateView.canvasContent(getResources().getStringArray(C2128R.array.base_metabolic), Constant.JCDX, f);
                return;
            default:
                return;
        }
    }

    public void onDestroy() {
        super.onDestroy();
        EventBusUtils.unregister(this);
    }
}
