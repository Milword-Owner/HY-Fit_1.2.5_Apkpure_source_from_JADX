package com.huayu.tzc.p014ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import com.hjq.toast.ToastUtils;
import com.huayu.tzc.C2128R;
import com.huayu.tzc.base.BaseActivity;
import com.huayu.tzc.base.BaseBean;
import com.huayu.tzc.base.Constant;
import com.huayu.tzc.bean.Member;
import com.huayu.tzc.contract.MainContract;
import com.huayu.tzc.customview.BodyStateView;
import com.huayu.tzc.customview.ruler.RuleLayout;
import com.huayu.tzc.customview.ruler.UnitLayout;
import com.huayu.tzc.presenter.TargetPresenter;
import com.huayu.tzc.utils.BodyUtil;
import com.huayu.tzc.utils.UnitUtils;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u0012\u001a\u00020\u0013H\u0014J\b\u0010\u0014\u001a\u00020\u0003H\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0002J\b\u0010\u0017\u001a\u00020\u0016H\u0002J\b\u0010\u0018\u001a\u00020\u0016H\u0014J\b\u0010\u0019\u001a\u00020\u0016H\u0002J\u0012\u0010\u001a\u001a\u00020\u00162\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0016J\b\u0010\u001d\u001a\u00020\u0016H\u0002J\u0016\u0010\u001e\u001a\u00020\u00162\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00070 H\u0016J\u0016\u0010!\u001a\u00020\u00162\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00070 H\u0016R\u0016\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0004¢\u0006\u0004\n\u0002\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX.¢\u0006\u0002\n\u0000R\u0016\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X.¢\u0006\u0004\n\u0002\u0010\bR\u000e\u0010\u0010\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0007X.¢\u0006\u0002\n\u0000¨\u0006\""}, mo21895d2 = {"Lcom/huayu/tzc/ui/activity/TargetActivity;", "Lcom/huayu/tzc/base/BaseActivity;", "Lcom/huayu/tzc/contract/MainContract$TargetView;", "Lcom/huayu/tzc/presenter/TargetPresenter;", "()V", "colors", "", "", "[Ljava/lang/String;", "floats", "", "goal", "", "member", "Lcom/huayu/tzc/bean/Member;", "strings", "weight", "weightUnit", "getLayoutId", "", "getPresenter", "initData", "", "initGoglRuler", "initView", "initWeightRuler", "showError", "e", "", "updateData", "updateMem", "baseBean", "Lcom/huayu/tzc/base/BaseBean;", "updateUserInfo", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
/* renamed from: com.huayu.tzc.ui.activity.TargetActivity */
/* compiled from: TargetActivity.kt */
public final class TargetActivity extends BaseActivity<MainContract.TargetView, TargetPresenter> implements MainContract.TargetView {
    private HashMap _$_findViewCache;
    private final String[] colors = {"#56A3FA", "#0EDAAE", "#F66B89"};
    /* access modifiers changed from: private */
    public float[] floats = {43.1f, 64.7f};
    /* access modifiers changed from: private */
    public float goal;
    private Member member;
    /* access modifiers changed from: private */
    public String[] strings;
    /* access modifiers changed from: private */
    public float weight;
    private String weightUnit;

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
        return C2128R.C2133layout.activity_target;
    }

    public static final /* synthetic */ String[] access$getStrings$p(TargetActivity targetActivity) {
        String[] strArr = targetActivity.strings;
        if (strArr == null) {
            Intrinsics.throwUninitializedPropertyAccessException("strings");
        }
        return strArr;
    }

    @NotNull
    public TargetPresenter getPresenter() {
        return new TargetPresenter();
    }

    /* access modifiers changed from: protected */
    public void initView() {
        ((BodyStateView) _$_findCachedViewById(C2128R.C2131id.target_state)).setColors(this.colors);
        initData();
        initWeightRuler();
        initGoglRuler();
        Button button = (Button) _$_findCachedViewById(C2128R.C2131id.target_bt);
        button.setOnClickListener(new TargetActivity$initView$$inlined$singleClick$1(button, 800, this));
    }

    /* access modifiers changed from: private */
    public final void updateData() {
        String str;
        String str2 = this.weightUnit;
        if (str2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("weightUnit");
        }
        if (Intrinsics.areEqual((Object) str2, (Object) Constant.UNIT_WEIGHT[2])) {
            str = UnitUtils.st2StLb(this.weight);
        } else {
            str = String.valueOf(this.weight);
        }
        Member member2 = this.member;
        if (member2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("member");
        }
        if (member2.getMember_id() == 0) {
            FormBody.Builder builder = new FormBody.Builder();
            Member member3 = this.member;
            if (member3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("member");
            }
            FormBody build = builder.add("u_id", String.valueOf(member3.getUser_id())).add("u_weight", str).add("u_goal", String.valueOf(this.goal)).build();
            Intrinsics.checkExpressionValueIsNotNull(build, "FormBody.Builder()\n     …\n                .build()");
            RequestBody requestBody = build;
            TargetPresenter targetPresenter = (TargetPresenter) getMPresenter();
            if (targetPresenter != null) {
                targetPresenter.updateUserInfo(requestBody);
            }
        } else {
            FormBody.Builder builder2 = new FormBody.Builder();
            Member member4 = this.member;
            if (member4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("member");
            }
            FormBody build2 = builder2.add("member_id", String.valueOf(member4.getMember_id())).add("m_weight", str).add("m_goal", String.valueOf(this.goal)).build();
            Intrinsics.checkExpressionValueIsNotNull(build2, "FormBody.Builder()\n     …\n                .build()");
            RequestBody requestBody2 = build2;
            TargetPresenter targetPresenter2 = (TargetPresenter) getMPresenter();
            if (targetPresenter2 != null) {
                targetPresenter2.updateMem(requestBody2);
            }
        }
        progressShow();
    }

    private final void initGoglRuler() {
        ((UnitLayout) _$_findCachedViewById(C2128R.C2131id.target_Value2)).bindRuler((RuleLayout) _$_findCachedViewById(C2128R.C2131id.target_ruler2));
        ((UnitLayout) _$_findCachedViewById(C2128R.C2131id.target_Value2)).setOnValueChangeListener(new TargetActivity$initGoglRuler$1(this));
        ((UnitLayout) _$_findCachedViewById(C2128R.C2131id.target_Value2)).bindRuler((RuleLayout) _$_findCachedViewById(C2128R.C2131id.target_ruler2));
        RuleLayout ruleLayout = (RuleLayout) _$_findCachedViewById(C2128R.C2131id.target_ruler2);
        Intrinsics.checkExpressionValueIsNotNull(ruleLayout, "target_ruler2");
        ruleLayout.setCurrentScale(this.goal * ((float) 10));
        ((RuleLayout) _$_findCachedViewById(C2128R.C2131id.target_ruler2)).refreshRuler();
    }

    private final void initWeightRuler() {
        String str = this.weightUnit;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("weightUnit");
        }
        if (Intrinsics.areEqual((Object) str, (Object) Constant.UNIT_WEIGHT[2])) {
            ((UnitLayout) _$_findCachedViewById(C2128R.C2131id.target_Value)).setTextUnit("st");
        } else {
            UnitLayout unitLayout = (UnitLayout) _$_findCachedViewById(C2128R.C2131id.target_Value);
            String str2 = this.weightUnit;
            if (str2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("weightUnit");
            }
            unitLayout.setTextUnit(str2);
        }
        ((UnitLayout) _$_findCachedViewById(C2128R.C2131id.target_Value)).setOnValueChangeListener(new TargetActivity$initWeightRuler$1(this));
        ((UnitLayout) _$_findCachedViewById(C2128R.C2131id.target_Value)).bindRuler((RuleLayout) _$_findCachedViewById(C2128R.C2131id.target_ruler));
        if (this.weight < ((float) 0)) {
            this.weight = 55.0f;
            ((UnitLayout) _$_findCachedViewById(C2128R.C2131id.target_Value)).bindRuler((RuleLayout) _$_findCachedViewById(C2128R.C2131id.target_ruler));
            RuleLayout ruleLayout = (RuleLayout) _$_findCachedViewById(C2128R.C2131id.target_ruler);
            Intrinsics.checkExpressionValueIsNotNull(ruleLayout, "target_ruler");
            ruleLayout.setCurrentScale(550.0f);
        } else {
            ((UnitLayout) _$_findCachedViewById(C2128R.C2131id.target_Value)).bindRuler((RuleLayout) _$_findCachedViewById(C2128R.C2131id.target_ruler));
            RuleLayout ruleLayout2 = (RuleLayout) _$_findCachedViewById(C2128R.C2131id.target_ruler);
            Intrinsics.checkExpressionValueIsNotNull(ruleLayout2, "target_ruler");
            ruleLayout2.setCurrentScale(this.weight * ((float) 10));
        }
        ((RuleLayout) _$_findCachedViewById(C2128R.C2131id.target_ruler)).refreshRuler();
    }

    private final void initData() {
        float f;
        String[] stringArray = getResources().getStringArray(C2128R.array.have_red_status);
        Intrinsics.checkExpressionValueIsNotNull(stringArray, "resources.getStringArray(R.array.have_red_status)");
        this.strings = stringArray;
        String stringExtra = getIntent().getStringExtra("unit");
        if (stringExtra == null) {
            stringExtra = "kg";
        }
        this.weightUnit = stringExtra;
        Member member2 = (Member) getIntent().getParcelableExtra("member");
        if (member2 == null) {
            member2 = new Member();
        }
        this.member = member2;
        Member member3 = this.member;
        if (member3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("member");
        }
        this.weight = UnitUtils.getFloatWeight(member3.getM_weight());
        float[] fArr = this.floats;
        String str = this.weightUnit;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("weightUnit");
        }
        float[] units = BodyUtil.getUnits(fArr, str);
        Intrinsics.checkExpressionValueIsNotNull(units, "getUnits(floats, weightUnit)");
        this.floats = units;
        Member member4 = this.member;
        if (member4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("member");
        }
        if (member4.getM_goal() >= ((double) 5)) {
            Member member5 = this.member;
            if (member5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("member");
            }
            f = (float) member5.getM_goal();
        } else {
            f = 18.0f;
        }
        this.goal = f;
    }

    public void updateMem(@NotNull BaseBean<String> baseBean) {
        Intrinsics.checkParameterIsNotNull(baseBean, "baseBean");
        progressDissmiss();
        if (baseBean.getMeta().getSuccess()) {
            setResult(-1, new Intent().putExtra("isMainUser", false));
            finish();
        }
        ToastUtils.show((CharSequence) baseBean.getMeta().getMessage());
    }

    public void updateUserInfo(@NotNull BaseBean<String> baseBean) {
        Intrinsics.checkParameterIsNotNull(baseBean, "baseBean");
        progressDissmiss();
        if (baseBean.getMeta().getSuccess()) {
            setResult(-1, new Intent().putExtra("isMainUser", true));
            finish();
        }
        ToastUtils.show((CharSequence) baseBean.getMeta().getMessage());
    }

    public void showError(@Nullable Throwable th) {
        progressDissmiss();
    }
}
