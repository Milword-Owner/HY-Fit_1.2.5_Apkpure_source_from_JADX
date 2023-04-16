package com.huayu.tzc.p014ui.activity.history;

import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.facebook.share.internal.ShareConstants;
import com.huayu.tzc.C2128R;
import com.huayu.tzc.adapter.HistoryInfoAdapter;
import com.huayu.tzc.base.BaseActivity;
import com.huayu.tzc.base.Constant;
import com.huayu.tzc.bean.HistoryInfo;
import com.huayu.tzc.bean.Measure;
import com.huayu.tzc.bean.Member;
import com.huayu.tzc.contract.MainContract;
import com.huayu.tzc.customview.BodyStateView;
import com.huayu.tzc.presenter.NotPresenter;
import com.huayu.tzc.utils.BodyLevelUtil;
import com.huayu.tzc.utils.BodyUtil;
import com.huayu.tzc.utils.RangeUtil;
import com.huayu.tzc.utils.UnitUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J \u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0013\u001a\u00020\bH\u0002J\b\u0010\u001e\u001a\u00020\u0006H\u0014J\b\u0010\u001f\u001a\u00020\u0003H\u0016J\b\u0010 \u001a\u00020\u001aH\u0002J\b\u0010!\u001a\u00020\u001aH\u0002J\b\u0010\"\u001a\u00020\u001aH\u0014R\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX.¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016X\u000e¢\u0006\u0004\n\u0002\u0010\u0018¨\u0006#"}, mo21895d2 = {"Lcom/huayu/tzc/ui/activity/history/HistoryInfoActivity;", "Lcom/huayu/tzc/base/BaseActivity;", "Lcom/huayu/tzc/contract/MainContract$View;", "Lcom/huayu/tzc/presenter/NotPresenter;", "()V", "age", "", "height", "", "historyInfoAdapter", "Lcom/huayu/tzc/adapter/HistoryInfoAdapter;", "historyInfoList", "", "Lcom/huayu/tzc/bean/HistoryInfo;", "level", "measure", "Lcom/huayu/tzc/bean/Measure;", "member", "Lcom/huayu/tzc/bean/Member;", "num", "sex", "titles", "", "", "[Ljava/lang/String;", "drawView", "", "position", "bodyStateView", "Lcom/huayu/tzc/customview/BodyStateView;", "getLayoutId", "getPresenter", "initAdapter", "initData", "initView", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
/* renamed from: com.huayu.tzc.ui.activity.history.HistoryInfoActivity */
/* compiled from: HistoryInfoActivity.kt */
public final class HistoryInfoActivity extends BaseActivity<MainContract.View, NotPresenter> {
    private HashMap _$_findViewCache;
    private int age;
    private float height;
    private HistoryInfoAdapter historyInfoAdapter;
    /* access modifiers changed from: private */
    public List<HistoryInfo> historyInfoList = new ArrayList();
    private int level;
    private Measure measure;
    private Member member;
    private int num;
    private int sex;
    private String[] titles = new String[0];

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
        return C2128R.C2133layout.activity_history_info;
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
        Measure measure2 = (Measure) getIntent().getParcelableExtra(ShareConstants.WEB_DIALOG_PARAM_DATA);
        if (measure2 == null) {
            measure2 = new Measure();
        }
        this.measure = measure2;
        initAdapter();
        initData();
    }

    private final void initAdapter() {
        this.historyInfoAdapter = new HistoryInfoAdapter(this.historyInfoList);
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(C2128R.C2131id.historyInfoRecyclerview);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView, "historyInfoRecyclerview");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView recyclerView2 = (RecyclerView) _$_findCachedViewById(C2128R.C2131id.historyInfoRecyclerview);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView2, "historyInfoRecyclerview");
        HistoryInfoAdapter historyInfoAdapter2 = this.historyInfoAdapter;
        if (historyInfoAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("historyInfoAdapter");
        }
        recyclerView2.setAdapter(historyInfoAdapter2);
        HistoryInfoAdapter historyInfoAdapter3 = this.historyInfoAdapter;
        if (historyInfoAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("historyInfoAdapter");
        }
        historyInfoAdapter3.setOnItemClickListener(new HistoryInfoActivity$initAdapter$1(this));
    }

    private final void initData() {
        Member member2 = this.member;
        if (member2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("member");
        }
        this.age = member2.getUserAge();
        Member member3 = this.member;
        if (member3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("member");
        }
        this.sex = member3.getM_gender();
        String[] stringArray = getResources().getStringArray(C2128R.array.details_body);
        Intrinsics.checkExpressionValueIsNotNull(stringArray, "resources.getStringArray(R.array.details_body)");
        this.titles = stringArray;
        Measure measure2 = this.measure;
        if (measure2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("measure");
        }
        this.height = (float) UnitUtils.getHeight(measure2.getHeight());
        int length = this.titles.length;
        for (int i = 0; i < length; i++) {
            HistoryInfo historyInfo = new HistoryInfo(Constant.IMGS[i], this.titles[i]);
            switch (i) {
                case 0:
                    Measure measure3 = this.measure;
                    if (measure3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("measure");
                    }
                    float floatWeight = UnitUtils.getFloatWeight(measure3.getWeight());
                    Measure measure4 = this.measure;
                    if (measure4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("measure");
                    }
                    this.level = BodyLevelUtil.getWeightLevel(UnitUtils.getKgWeight(floatWeight, measure4.getWeightunit()), this.sex, this.height);
                    StringBuilder sb = new StringBuilder();
                    Measure measure5 = this.measure;
                    if (measure5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("measure");
                    }
                    sb.append(measure5.getWeight());
                    Measure measure6 = this.measure;
                    if (measure6 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("measure");
                    }
                    sb.append(measure6.getWeightunit());
                    String sb2 = sb.toString();
                    Measure measure7 = this.measure;
                    if (measure7 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("measure");
                    }
                    float floatWeight2 = UnitUtils.getFloatWeight(measure7.getWeight());
                    int i2 = this.level;
                    String[] stringArray2 = getResources().getStringArray(C2128R.array.weight_status);
                    int i3 = this.level;
                    String str = stringArray2[i3];
                    String weightColor = BodyUtil.getWeightColor(i3);
                    String[] strArr = Constant.COLORS4;
                    Intrinsics.checkExpressionValueIsNotNull(strArr, "COLORS4");
                    historyInfo.setContent(sb2, floatWeight2, i2, str, weightColor, strArr);
                    break;
                case 1:
                    Measure measure8 = this.measure;
                    if (measure8 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("measure");
                    }
                    this.level = BodyLevelUtil.getBmiLevel((float) measure8.getBmi());
                    Measure measure9 = this.measure;
                    if (measure9 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("measure");
                    }
                    String valueOf = String.valueOf(measure9.getBmi());
                    Measure measure10 = this.measure;
                    if (measure10 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("measure");
                    }
                    float bmi = (float) measure10.getBmi();
                    int i4 = this.level;
                    String str2 = getResources().getStringArray(C2128R.array.have_red_status)[this.level];
                    float[] fArr = Constant.BMI_FALSE;
                    Measure measure11 = this.measure;
                    if (measure11 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("measure");
                    }
                    historyInfo.setContent(valueOf, bmi, i4, str2, BodyUtil.getColorBmi(fArr, (float) measure11.getBmi()));
                    break;
                case 2:
                    Measure measure12 = this.measure;
                    if (measure12 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("measure");
                    }
                    this.level = BodyLevelUtil.getTLevel((float) measure12.getBodyfat_rate(), RangeUtil.getFatRangFalse(this.sex, this.age));
                    StringBuilder sb3 = new StringBuilder();
                    Measure measure13 = this.measure;
                    if (measure13 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("measure");
                    }
                    sb3.append(String.valueOf(measure13.getBodyfat_rate()));
                    sb3.append("%");
                    String sb4 = sb3.toString();
                    Measure measure14 = this.measure;
                    if (measure14 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("measure");
                    }
                    float bodyfat_rate = (float) measure14.getBodyfat_rate();
                    int i5 = this.level;
                    String str3 = getResources().getStringArray(C2128R.array.fat_status)[this.level];
                    float[] fatRangFalse = RangeUtil.getFatRangFalse(this.sex, this.age);
                    Measure measure15 = this.measure;
                    if (measure15 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("measure");
                    }
                    historyInfo.setContent(sb4, bodyfat_rate, i5, str3, BodyUtil.getColor4(fatRangFalse, (float) measure15.getBodyfat_rate()));
                    break;
                case 3:
                    String[] stringArray3 = getResources().getStringArray(C2128R.array.shape);
                    Intrinsics.checkExpressionValueIsNotNull(stringArray3, "resources.getStringArray(R.array.shape)");
                    int i6 = -1;
                    Measure measure16 = this.measure;
                    if (measure16 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("measure");
                    }
                    if (measure16.getMuscle_mass().length() > 0) {
                        Measure measure17 = this.measure;
                        if (measure17 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("measure");
                        }
                        Measure measure18 = this.measure;
                        if (measure18 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("measure");
                        }
                        float bodyfat_rate2 = (float) measure18.getBodyfat_rate();
                        Measure measure19 = this.measure;
                        if (measure19 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("measure");
                        }
                        float floatWeight3 = UnitUtils.getFloatWeight(measure19.getMuscle_mass());
                        float[] fatRangFalse2 = RangeUtil.getFatRangFalse(this.sex, this.age);
                        Intrinsics.checkExpressionValueIsNotNull(fatRangFalse2, "RangeUtil.getFatRangFalse(sex, age)");
                        float[] jRRange = RangeUtil.getJRRange(this.sex, this.height);
                        Measure measure20 = this.measure;
                        if (measure20 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("measure");
                        }
                        float[] units = BodyUtil.getUnits(jRRange, measure20.getWeightunit());
                        Intrinsics.checkExpressionValueIsNotNull(units, "BodyUtil.getUnits(RangeU…ight),measure.weightunit)");
                        i6 = measure17.getTx(bodyfat_rate2, floatWeight3, fatRangFalse2, units);
                    }
                    if (i6 >= 0) {
                        historyInfo.setContent(stringArray3[i6], (float) i6, i6, "");
                        break;
                    } else {
                        historyInfo.setContent("", (float) i6, i6, "");
                        break;
                    }
                case 4:
                    Measure measure21 = this.measure;
                    if (measure21 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("measure");
                    }
                    this.level = BodyLevelUtil.getQzLevel(UnitUtils.getFloatWeight(measure21.getFfm()));
                    StringBuilder sb5 = new StringBuilder();
                    Measure measure22 = this.measure;
                    if (measure22 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("measure");
                    }
                    sb5.append(measure22.getFfm());
                    Measure measure23 = this.measure;
                    if (measure23 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("measure");
                    }
                    sb5.append(measure23.getWeightunit());
                    String sb6 = sb5.toString();
                    Measure measure24 = this.measure;
                    if (measure24 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("measure");
                    }
                    historyInfo.setContent(sb6, UnitUtils.getFloatWeight(measure24.getFfm()), this.level, "");
                    break;
                case 5:
                    Measure measure25 = this.measure;
                    if (measure25 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("measure");
                    }
                    this.level = BodyLevelUtil.getPxLevel((float) measure25.getSubcutaneousfat_rate(), RangeUtil.getPxRang(this.sex));
                    StringBuilder sb7 = new StringBuilder();
                    Measure measure26 = this.measure;
                    if (measure26 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("measure");
                    }
                    sb7.append(String.valueOf(measure26.getSubcutaneousfat_rate()));
                    sb7.append("%");
                    String sb8 = sb7.toString();
                    Measure measure27 = this.measure;
                    if (measure27 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("measure");
                    }
                    float subcutaneousfat_rate = (float) measure27.getSubcutaneousfat_rate();
                    int i7 = this.level;
                    String str4 = getResources().getStringArray(C2128R.array.have_red_status)[this.level];
                    float[] pxRang = RangeUtil.getPxRang(this.sex);
                    Measure measure28 = this.measure;
                    if (measure28 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("measure");
                    }
                    historyInfo.setContent(sb8, subcutaneousfat_rate, i7, str4, BodyUtil.getColor3(pxRang, (float) measure28.getSubcutaneousfat_rate()));
                    break;
                case 6:
                    Measure measure29 = this.measure;
                    if (measure29 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("measure");
                    }
                    this.level = BodyLevelUtil.getNzLevel(measure29.getVisceral_fat());
                    Measure measure30 = this.measure;
                    if (measure30 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("measure");
                    }
                    String valueOf2 = String.valueOf(measure30.getVisceral_fat());
                    Measure measure31 = this.measure;
                    if (measure31 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("measure");
                    }
                    float visceral_fat = measure31.getVisceral_fat();
                    int i8 = this.level;
                    String str5 = getResources().getStringArray(C2128R.array.visceral_fat)[this.level];
                    float[] fArr2 = Constant.NZZZ;
                    Measure measure32 = this.measure;
                    if (measure32 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("measure");
                    }
                    historyInfo.setContent(valueOf2, visceral_fat, i8, str5, BodyUtil.getColor4(fArr2, measure32.getVisceral_fat()));
                    break;
                case 7:
                    Measure measure33 = this.measure;
                    if (measure33 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("measure");
                    }
                    this.level = BodyLevelUtil.getTsLevel((float) measure33.getBodywater_rate(), RangeUtil.getTsRang(this.sex));
                    this.num = this.level;
                    int i9 = this.num;
                    if (i9 == 0) {
                        this.num = 2;
                    } else if (i9 == 2) {
                        this.num = 0;
                    }
                    StringBuilder sb9 = new StringBuilder();
                    Measure measure34 = this.measure;
                    if (measure34 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("measure");
                    }
                    sb9.append(String.valueOf(measure34.getBodywater_rate()));
                    sb9.append("%");
                    String sb10 = sb9.toString();
                    Measure measure35 = this.measure;
                    if (measure35 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("measure");
                    }
                    float bodywater_rate = (float) measure35.getBodywater_rate();
                    int i10 = this.level;
                    String str6 = getResources().getStringArray(C2128R.array.no_red_status)[this.num];
                    float[] tsRang = RangeUtil.getTsRang(this.sex);
                    Measure measure36 = this.measure;
                    if (measure36 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("measure");
                    }
                    historyInfo.setContent(sb10, bodywater_rate, i10, str6, BodyUtil.getColorTs(tsRang, (float) measure36.getBodywater_rate()));
                    break;
                case 8:
                    Measure measure37 = this.measure;
                    if (measure37 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("measure");
                    }
                    this.level = BodyLevelUtil.getGgjLevel((float) measure37.getSkeletalfat_percentage());
                    this.num = this.level;
                    int i11 = this.num;
                    if (i11 == 0) {
                        this.num = 2;
                    } else if (i11 == 2) {
                        this.num = 0;
                    }
                    StringBuilder sb11 = new StringBuilder();
                    Measure measure38 = this.measure;
                    if (measure38 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("measure");
                    }
                    sb11.append(String.valueOf(measure38.getSkeletalfat_percentage()));
                    sb11.append("%");
                    String sb12 = sb11.toString();
                    Measure measure39 = this.measure;
                    if (measure39 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("measure");
                    }
                    float skeletalfat_percentage = (float) measure39.getSkeletalfat_percentage();
                    int i12 = this.level;
                    String str7 = getResources().getStringArray(C2128R.array.no_red_status)[this.num];
                    float[] fArr3 = Constant.GGJ;
                    Measure measure40 = this.measure;
                    if (measure40 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("measure");
                    }
                    historyInfo.setContent(sb12, skeletalfat_percentage, i12, str7, BodyUtil.getColorTs(fArr3, (float) measure40.getSkeletalfat_percentage()));
                    break;
                case 9:
                    Measure measure41 = this.measure;
                    if (measure41 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("measure");
                    }
                    float floatWeight4 = UnitUtils.getFloatWeight(measure41.getMuscle_mass());
                    Measure measure42 = this.measure;
                    if (measure42 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("measure");
                    }
                    this.level = BodyLevelUtil.getJrLevel(UnitUtils.getKgWeight(floatWeight4, measure42.getWeightunit()), RangeUtil.getJRRange(this.sex, this.height));
                    this.num = this.level;
                    int i13 = this.num;
                    if (i13 == 0) {
                        this.num = 2;
                    } else if (i13 == 2) {
                        this.num = 0;
                    }
                    StringBuilder sb13 = new StringBuilder();
                    Measure measure43 = this.measure;
                    if (measure43 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("measure");
                    }
                    sb13.append(measure43.getMuscle_mass());
                    Measure measure44 = this.measure;
                    if (measure44 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("measure");
                    }
                    sb13.append(measure44.getWeightunit());
                    String sb14 = sb13.toString();
                    Measure measure45 = this.measure;
                    if (measure45 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("measure");
                    }
                    float floatWeight5 = UnitUtils.getFloatWeight(measure45.getMuscle_mass());
                    int i14 = this.level;
                    String str8 = getResources().getStringArray(C2128R.array.no_red_status)[this.num];
                    float[] jRRange2 = RangeUtil.getJRRange(this.sex, this.height);
                    Measure measure46 = this.measure;
                    if (measure46 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("measure");
                    }
                    float[] units2 = BodyUtil.getUnits(jRRange2, measure46.getWeightunit());
                    Measure measure47 = this.measure;
                    if (measure47 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("measure");
                    }
                    historyInfo.setContent(sb14, floatWeight5, i14, str8, BodyUtil.getColorTs(units2, UnitUtils.getFloatWeight(measure47.getMuscle_mass())));
                    break;
                case 10:
                    Measure measure48 = this.measure;
                    if (measure48 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("measure");
                    }
                    float floatWeight6 = UnitUtils.getFloatWeight(measure48.getBone_mass());
                    Measure measure49 = this.measure;
                    if (measure49 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("measure");
                    }
                    this.level = BodyLevelUtil.getGLevel(UnitUtils.getKgWeight(floatWeight6, measure49.getWeightunit()));
                    this.num = this.level;
                    int i15 = this.num;
                    if (i15 == 0) {
                        this.num = 2;
                    } else if (i15 == 2) {
                        this.num = 0;
                    }
                    StringBuilder sb15 = new StringBuilder();
                    Measure measure50 = this.measure;
                    if (measure50 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("measure");
                    }
                    sb15.append(measure50.getBone_mass());
                    Measure measure51 = this.measure;
                    if (measure51 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("measure");
                    }
                    sb15.append(measure51.getWeightunit());
                    String sb16 = sb15.toString();
                    Measure measure52 = this.measure;
                    if (measure52 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("measure");
                    }
                    float floatWeight7 = UnitUtils.getFloatWeight(measure52.getBone_mass());
                    int i16 = this.level;
                    String str9 = getResources().getStringArray(C2128R.array.no_red_status)[this.num];
                    float[] fArr4 = Constant.f1685GL;
                    Measure measure53 = this.measure;
                    if (measure53 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("measure");
                    }
                    float[] units3 = BodyUtil.getUnits(fArr4, measure53.getWeightunit());
                    Measure measure54 = this.measure;
                    if (measure54 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("measure");
                    }
                    historyInfo.setContent(sb16, floatWeight7, i16, str9, BodyUtil.getColorTs(units3, UnitUtils.getFloatWeight(measure54.getBone_mass())));
                    break;
                case 11:
                    Measure measure55 = this.measure;
                    if (measure55 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("measure");
                    }
                    this.level = BodyLevelUtil.getDbzLevel((float) measure55.getProtein_rate());
                    this.num = this.level;
                    StringBuilder sb17 = new StringBuilder();
                    Measure measure56 = this.measure;
                    if (measure56 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("measure");
                    }
                    sb17.append(String.valueOf(measure56.getProtein_rate()));
                    sb17.append("%");
                    String sb18 = sb17.toString();
                    Measure measure57 = this.measure;
                    if (measure57 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("measure");
                    }
                    float protein_rate = (float) measure57.getProtein_rate();
                    int i17 = this.num;
                    String str10 = getResources().getStringArray(C2128R.array.no_red_status)[this.level];
                    float[] fArr5 = Constant.DBZ;
                    Measure measure58 = this.measure;
                    if (measure58 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("measure");
                    }
                    historyInfo.setContent(sb18, protein_rate, i17, str10, BodyUtil.getColorTs(fArr5, (float) measure58.getProtein_rate()));
                    break;
                case 12:
                    Measure measure59 = this.measure;
                    if (measure59 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("measure");
                    }
                    this.level = BodyLevelUtil.getJcLevel((float) measure59.getBasalmetabolic_rate());
                    StringBuilder sb19 = new StringBuilder();
                    Measure measure60 = this.measure;
                    if (measure60 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("measure");
                    }
                    sb19.append(String.valueOf(measure60.getBasalmetabolic_rate()));
                    sb19.append("kcal");
                    String sb20 = sb19.toString();
                    Measure measure61 = this.measure;
                    if (measure61 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("measure");
                    }
                    float basalmetabolic_rate = (float) measure61.getBasalmetabolic_rate();
                    int i18 = this.level;
                    String str11 = getResources().getStringArray(C2128R.array.base_metabolic)[this.level];
                    float[] fArr6 = Constant.JCDX;
                    Measure measure62 = this.measure;
                    if (measure62 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("measure");
                    }
                    historyInfo.setContent(sb20, basalmetabolic_rate, i18, str11, BodyUtil.getColor2(fArr6, (float) measure62.getBasalmetabolic_rate()));
                    break;
                case 13:
                    Measure measure63 = this.measure;
                    if (measure63 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("measure");
                    }
                    String valueOf3 = String.valueOf(measure63.getBodyage());
                    Measure measure64 = this.measure;
                    if (measure64 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("measure");
                    }
                    float bodyage = (float) measure64.getBodyage();
                    Measure measure65 = this.measure;
                    if (measure65 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("measure");
                    }
                    historyInfo.setContent(valueOf3, bodyage, measure65.getBodyage(), "");
                    break;
                case 14:
                    Measure measure66 = this.measure;
                    if (measure66 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("measure");
                    }
                    this.level = BodyLevelUtil.getScoreLevel((float) measure66.getBody_score());
                    Measure measure67 = this.measure;
                    if (measure67 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("measure");
                    }
                    String valueOf4 = String.valueOf(measure67.getBody_score());
                    Measure measure68 = this.measure;
                    if (measure68 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("measure");
                    }
                    float body_score = (float) measure68.getBody_score();
                    int i19 = this.level;
                    Measure measure69 = this.measure;
                    if (measure69 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("measure");
                    }
                    historyInfo.setContent(valueOf4, body_score, i19, "", BodyUtil.getColor1((float) measure69.getBody_score()));
                    break;
            }
            this.historyInfoList.add(historyInfo);
        }
        HistoryInfoAdapter historyInfoAdapter2 = this.historyInfoAdapter;
        if (historyInfoAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("historyInfoAdapter");
        }
        historyInfoAdapter2.notifyDataSetChanged();
    }

    /* access modifiers changed from: private */
    public final void drawView(int i, BodyStateView bodyStateView, float f) {
        bodyStateView.setVisibility(0);
        if (i == 0) {
            String[] stringArray = getResources().getStringArray(C2128R.array.weight_status);
            float[] weightArray = RangeUtil.getWeightArray(this.sex, this.height);
            Measure measure2 = this.measure;
            if (measure2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("measure");
            }
            bodyStateView.canvasContent(stringArray, BodyUtil.getUnits(weightArray, measure2.getWeightunit()), this.historyInfoList.get(0).getNum(), this.historyInfoList.get(0).getColors());
        } else if (i == 1) {
            bodyStateView.canvasContent(getResources().getStringArray(C2128R.array.have_red_status), Constant.BMI_FALSE, f, Constant.COLORS3);
        } else if (i != 2) {
            switch (i) {
                case 5:
                    bodyStateView.canvasContent(getResources().getStringArray(C2128R.array.have_red_status), RangeUtil.getPxRang(this.sex), f, Constant.COLORS3);
                    return;
                case 6:
                    bodyStateView.canvasContent(getResources().getStringArray(C2128R.array.visceral_fat), Constant.NZZZ, f);
                    return;
                case 7:
                    bodyStateView.canvasContent(getResources().getStringArray(C2128R.array.no_red_status), RangeUtil.getTsRang(this.sex), f, Constant.COLORS2);
                    return;
                case 8:
                    bodyStateView.canvasContent(getResources().getStringArray(C2128R.array.no_red_status), Constant.GGJ, f, Constant.COLORS2);
                    return;
                case 9:
                    String[] stringArray2 = getResources().getStringArray(C2128R.array.no_red_status);
                    float[] jRRange = RangeUtil.getJRRange(this.sex, this.height);
                    Measure measure3 = this.measure;
                    if (measure3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("measure");
                    }
                    bodyStateView.canvasContent(stringArray2, BodyUtil.getUnits(jRRange, measure3.getWeightunit()), f, Constant.COLORS2);
                    return;
                case 10:
                    String[] stringArray3 = getResources().getStringArray(C2128R.array.no_red_status);
                    float[] fArr = Constant.f1685GL;
                    Measure measure4 = this.measure;
                    if (measure4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("measure");
                    }
                    bodyStateView.canvasContent(stringArray3, BodyUtil.getUnits(fArr, measure4.getWeightunit()), f, Constant.COLORS2);
                    return;
                case 11:
                    bodyStateView.canvasContent(getResources().getStringArray(C2128R.array.have_red_status), Constant.DBZ, f, Constant.COLORS2);
                    return;
                case 12:
                    bodyStateView.canvasContent(getResources().getStringArray(C2128R.array.base_metabolic), Constant.JCDX, f);
                    return;
                default:
                    bodyStateView.setVisibility(8);
                    return;
            }
        } else {
            bodyStateView.canvasContent(getResources().getStringArray(C2128R.array.fat_status), RangeUtil.getFatRangFalse(this.sex, this.age), f, Constant.COLORSFat);
        }
    }
}
