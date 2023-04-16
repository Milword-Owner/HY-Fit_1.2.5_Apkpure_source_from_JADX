package com.huayu.tzc.p014ui.activity.setting;

import android.content.Intent;
import android.os.Parcelable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.core.app.NotificationCompat;
import com.hjq.toast.ToastUtils;
import com.huayu.tzc.C2128R;
import com.huayu.tzc.MainActivity;
import com.huayu.tzc.base.BaseActivity;
import com.huayu.tzc.base.BaseBean;
import com.huayu.tzc.base.Constant;
import com.huayu.tzc.bean.Measure;
import com.huayu.tzc.bean.Member;
import com.huayu.tzc.bean.User;
import com.huayu.tzc.bean.Version;
import com.huayu.tzc.contract.MainContract;
import com.huayu.tzc.customview.MyHeaderView;
import com.huayu.tzc.customview.MyImageView;
import com.huayu.tzc.p014ui.activity.UserInfoActivity;
import com.huayu.tzc.presenter.PrimaryPresenter;
import com.huayu.tzc.utils.AppManager;
import com.huayu.tzc.utils.SingleClickKt;
import com.tencent.mmkv.MMKV;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u00020\u00022\u00020\u0004B\u0005¢\u0006\u0002\u0010\u0005J\u0016\u0010\r\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0016J\b\u0010\u0012\u001a\u00020\u0013H\u0014J\u001c\u0010\u0014\u001a\u00020\u000e2\u0012\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u00160\u0010H\u0016J\u0016\u0010\u0018\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00130\u0010H\u0016J\b\u0010\u0019\u001a\u00020\u0003H\u0016J\u0016\u0010\u001a\u001a\u00020\u000e2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0010H\u0016J\b\u0010\u001b\u001a\u00020\u000eH\u0002J\b\u0010\u001c\u001a\u00020\u000eH\u0014J\b\u0010\u001d\u001a\u00020\u000eH\u0002J\u0012\u0010\u001e\u001a\u00020\u000e2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016J\u0018\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u00132\u0006\u0010$\u001a\u00020%H\u0016J\u001e\u0010&\u001a\u00020\u000e2\u0006\u0010'\u001a\u00020(2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\t0\u0010H\u0016J\u0012\u0010)\u001a\u00020\u000e2\b\u0010*\u001a\u0004\u0018\u00010+H\u0016J\u0016\u0010,\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\t0\u0010H\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX.¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000¨\u0006-"}, mo21895d2 = {"Lcom/huayu/tzc/ui/activity/setting/UnitActivity;", "Lcom/huayu/tzc/base/BaseActivity;", "Lcom/huayu/tzc/contract/MainContract$PrimaryView;", "Lcom/huayu/tzc/presenter/PrimaryPresenter;", "Landroid/view/View$OnClickListener;", "()V", "exitTime", "", "heightUnit", "", "user", "Lcom/huayu/tzc/bean/User;", "weightUnit", "getAppVersion", "", "baseBean", "Lcom/huayu/tzc/base/BaseBean;", "Lcom/huayu/tzc/bean/Version;", "getLayoutId", "", "getMineList", "loginBean", "", "Lcom/huayu/tzc/bean/Member;", "getNotReadCount", "getPresenter", "getUserInfo", "initClick", "initView", "modifyUnit", "onClick", "p0", "Landroid/view/View;", "onKeyDown", "", "keyCode", "event", "Landroid/view/KeyEvent;", "reporting", "item", "Lcom/huayu/tzc/bean/Measure;", "showError", "e", "", "updateUserInfo", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
/* renamed from: com.huayu.tzc.ui.activity.setting.UnitActivity */
/* compiled from: UnitActivity.kt */
public final class UnitActivity extends BaseActivity<MainContract.PrimaryView, PrimaryPresenter> implements MainContract.PrimaryView, View.OnClickListener {
    private HashMap _$_findViewCache;
    private long exitTime;
    private String heightUnit = "cm";
    private User user;
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
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return C2128R.C2133layout.activity_unit;
    }

    public void getNotReadCount(@NotNull BaseBean<Integer> baseBean) {
        Intrinsics.checkParameterIsNotNull(baseBean, "baseBean");
    }

    public void reporting(@NotNull Measure measure, @NotNull BaseBean<String> baseBean) {
        Intrinsics.checkParameterIsNotNull(measure, "item");
        Intrinsics.checkParameterIsNotNull(baseBean, "baseBean");
    }

    @NotNull
    public PrimaryPresenter getPresenter() {
        return new PrimaryPresenter();
    }

    public boolean onKeyDown(int i, @NotNull KeyEvent keyEvent) {
        Intrinsics.checkParameterIsNotNull(keyEvent, NotificationCompat.CATEGORY_EVENT);
        if (i != 4 || keyEvent.getAction() != 0 || !getIntent().getBooleanExtra("fromLogin", false)) {
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

    /* access modifiers changed from: protected */
    public void initView() {
        User user2;
        if (getIntent().getBooleanExtra("fromLogin", false)) {
            ((MyHeaderView) _$_findCachedViewById(C2128R.C2131id.unitHeader)).setBackShow(false);
            Parcelable parcelableExtra = getIntent().getParcelableExtra("user");
            if (parcelableExtra != null) {
                user2 = (User) parcelableExtra;
            } else {
                throw new TypeCastException("null cannot be cast to non-null type com.huayu.tzc.bean.User");
            }
        } else {
            Parcelable decodeParcelable = MMKV.defaultMMKV().decodeParcelable("user", User.class, new User());
            Intrinsics.checkExpressionValueIsNotNull(decodeParcelable, "MMKV.defaultMMKV().decod…User::class.java, User())");
            user2 = (User) decodeParcelable;
        }
        this.user = user2;
        User user3 = this.user;
        if (user3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("user");
        }
        if (user3.getHeightunit().length() == 0) {
            User user4 = this.user;
            if (user4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("user");
            }
            user4.setHeightunit("cm");
        }
        User user5 = this.user;
        if (user5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("user");
        }
        if (user5.getWeightunit().length() == 0) {
            User user6 = this.user;
            if (user6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("user");
            }
            user6.setWeightunit("kg");
        }
        User user7 = this.user;
        if (user7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("user");
        }
        this.heightUnit = user7.getHeightunit();
        User user8 = this.user;
        if (user8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("user");
        }
        this.weightUnit = user8.getWeightunit();
        if (Intrinsics.areEqual((Object) this.heightUnit, (Object) Constant.UNIT_HEIGHT[1])) {
            String str = Constant.UNIT_HEIGHT[1];
            Intrinsics.checkExpressionValueIsNotNull(str, "UNIT_HEIGHT[1]");
            this.heightUnit = str;
            MyImageView myImageView = (MyImageView) _$_findCachedViewById(C2128R.C2131id.unitFt);
            Intrinsics.checkExpressionValueIsNotNull(myImageView, "unitFt");
            myImageView.setChecked(true);
            MyImageView myImageView2 = (MyImageView) _$_findCachedViewById(C2128R.C2131id.unitCm);
            Intrinsics.checkExpressionValueIsNotNull(myImageView2, "unitCm");
            myImageView2.setChecked(false);
        } else {
            String str2 = Constant.UNIT_HEIGHT[0];
            Intrinsics.checkExpressionValueIsNotNull(str2, "UNIT_HEIGHT[0]");
            this.heightUnit = str2;
            MyImageView myImageView3 = (MyImageView) _$_findCachedViewById(C2128R.C2131id.unitCm);
            Intrinsics.checkExpressionValueIsNotNull(myImageView3, "unitCm");
            myImageView3.setChecked(true);
            MyImageView myImageView4 = (MyImageView) _$_findCachedViewById(C2128R.C2131id.unitFt);
            Intrinsics.checkExpressionValueIsNotNull(myImageView4, "unitFt");
            myImageView4.setChecked(false);
        }
        String str3 = this.weightUnit;
        if (Intrinsics.areEqual((Object) str3, (Object) Constant.UNIT_WEIGHT[2])) {
            String str4 = Constant.UNIT_WEIGHT[2];
            Intrinsics.checkExpressionValueIsNotNull(str4, "UNIT_WEIGHT[2]");
            this.weightUnit = str4;
            MyImageView myImageView5 = (MyImageView) _$_findCachedViewById(C2128R.C2131id.unitKg);
            Intrinsics.checkExpressionValueIsNotNull(myImageView5, "unitKg");
            myImageView5.setChecked(false);
            MyImageView myImageView6 = (MyImageView) _$_findCachedViewById(C2128R.C2131id.unitLb);
            Intrinsics.checkExpressionValueIsNotNull(myImageView6, "unitLb");
            myImageView6.setChecked(false);
            MyImageView myImageView7 = (MyImageView) _$_findCachedViewById(C2128R.C2131id.unitSt);
            Intrinsics.checkExpressionValueIsNotNull(myImageView7, "unitSt");
            myImageView7.setChecked(true);
        } else if (Intrinsics.areEqual((Object) str3, (Object) Constant.UNIT_WEIGHT[1])) {
            String str5 = Constant.UNIT_WEIGHT[1];
            Intrinsics.checkExpressionValueIsNotNull(str5, "UNIT_WEIGHT[1]");
            this.weightUnit = str5;
            MyImageView myImageView8 = (MyImageView) _$_findCachedViewById(C2128R.C2131id.unitKg);
            Intrinsics.checkExpressionValueIsNotNull(myImageView8, "unitKg");
            myImageView8.setChecked(false);
            MyImageView myImageView9 = (MyImageView) _$_findCachedViewById(C2128R.C2131id.unitLb);
            Intrinsics.checkExpressionValueIsNotNull(myImageView9, "unitLb");
            myImageView9.setChecked(true);
            MyImageView myImageView10 = (MyImageView) _$_findCachedViewById(C2128R.C2131id.unitSt);
            Intrinsics.checkExpressionValueIsNotNull(myImageView10, "unitSt");
            myImageView10.setChecked(false);
        } else {
            String str6 = Constant.UNIT_WEIGHT[0];
            Intrinsics.checkExpressionValueIsNotNull(str6, "UNIT_WEIGHT[0]");
            this.weightUnit = str6;
            MyImageView myImageView11 = (MyImageView) _$_findCachedViewById(C2128R.C2131id.unitKg);
            Intrinsics.checkExpressionValueIsNotNull(myImageView11, "unitKg");
            myImageView11.setChecked(true);
            MyImageView myImageView12 = (MyImageView) _$_findCachedViewById(C2128R.C2131id.unitLb);
            Intrinsics.checkExpressionValueIsNotNull(myImageView12, "unitLb");
            myImageView12.setChecked(false);
            MyImageView myImageView13 = (MyImageView) _$_findCachedViewById(C2128R.C2131id.unitSt);
            Intrinsics.checkExpressionValueIsNotNull(myImageView13, "unitSt");
            myImageView13.setChecked(false);
        }
        initClick();
    }

    private final void initClick() {
        View.OnClickListener onClickListener = this;
        ((LinearLayout) _$_findCachedViewById(C2128R.C2131id.unitCmLayout)).setOnClickListener(onClickListener);
        ((LinearLayout) _$_findCachedViewById(C2128R.C2131id.unitFtLayout)).setOnClickListener(onClickListener);
        ((LinearLayout) _$_findCachedViewById(C2128R.C2131id.unitKgLayout)).setOnClickListener(onClickListener);
        ((LinearLayout) _$_findCachedViewById(C2128R.C2131id.unitLbLayout)).setOnClickListener(onClickListener);
        ((LinearLayout) _$_findCachedViewById(C2128R.C2131id.unitStLayout)).setOnClickListener(onClickListener);
        SingleClickKt.singleClick$default((View) (Button) _$_findCachedViewById(C2128R.C2131id.unitBt), onClickListener, 0, 2, (Object) null);
    }

    public void getAppVersion(@NotNull BaseBean<Version> baseBean) {
        Intrinsics.checkParameterIsNotNull(baseBean, "baseBean");
        progressDissmiss();
    }

    public void updateUserInfo(@NotNull BaseBean<String> baseBean) {
        Intrinsics.checkParameterIsNotNull(baseBean, "baseBean");
        progressDissmiss();
        if (baseBean.getMeta().getSuccess()) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(268468224);
            startActivity(intent);
            overridePendingTransition(C2128R.anim.activity_alpha_in, C2128R.anim.activity_alpha_out);
            finish();
        }
        ToastUtils.show((CharSequence) baseBean.getMeta().getMessage());
    }

    public void getUserInfo(@NotNull BaseBean<User> baseBean) {
        Intrinsics.checkParameterIsNotNull(baseBean, "loginBean");
        progressDissmiss();
    }

    public void getMineList(@NotNull BaseBean<List<Member>> baseBean) {
        Intrinsics.checkParameterIsNotNull(baseBean, "loginBean");
        progressDissmiss();
    }

    public void showError(@Nullable Throwable th) {
        progressDissmiss();
    }

    public void onClick(@Nullable View view) {
        Integer valueOf = view != null ? Integer.valueOf(view.getId()) : null;
        if (valueOf != null && valueOf.intValue() == C2128R.C2131id.unitCmLayout) {
            String str = Constant.UNIT_HEIGHT[0];
            Intrinsics.checkExpressionValueIsNotNull(str, "UNIT_HEIGHT[0]");
            this.heightUnit = str;
            MyImageView myImageView = (MyImageView) _$_findCachedViewById(C2128R.C2131id.unitCm);
            Intrinsics.checkExpressionValueIsNotNull(myImageView, "unitCm");
            myImageView.setChecked(true);
            MyImageView myImageView2 = (MyImageView) _$_findCachedViewById(C2128R.C2131id.unitFt);
            Intrinsics.checkExpressionValueIsNotNull(myImageView2, "unitFt");
            myImageView2.setChecked(false);
        } else if (valueOf != null && valueOf.intValue() == C2128R.C2131id.unitFtLayout) {
            String str2 = Constant.UNIT_HEIGHT[1];
            Intrinsics.checkExpressionValueIsNotNull(str2, "UNIT_HEIGHT[1]");
            this.heightUnit = str2;
            MyImageView myImageView3 = (MyImageView) _$_findCachedViewById(C2128R.C2131id.unitCm);
            Intrinsics.checkExpressionValueIsNotNull(myImageView3, "unitCm");
            myImageView3.setChecked(false);
            MyImageView myImageView4 = (MyImageView) _$_findCachedViewById(C2128R.C2131id.unitFt);
            Intrinsics.checkExpressionValueIsNotNull(myImageView4, "unitFt");
            myImageView4.setChecked(true);
        } else if (valueOf != null && valueOf.intValue() == C2128R.C2131id.unitKgLayout) {
            String str3 = Constant.UNIT_WEIGHT[0];
            Intrinsics.checkExpressionValueIsNotNull(str3, "UNIT_WEIGHT[0]");
            this.weightUnit = str3;
            MyImageView myImageView5 = (MyImageView) _$_findCachedViewById(C2128R.C2131id.unitKg);
            Intrinsics.checkExpressionValueIsNotNull(myImageView5, "unitKg");
            myImageView5.setChecked(true);
            MyImageView myImageView6 = (MyImageView) _$_findCachedViewById(C2128R.C2131id.unitLb);
            Intrinsics.checkExpressionValueIsNotNull(myImageView6, "unitLb");
            myImageView6.setChecked(false);
            MyImageView myImageView7 = (MyImageView) _$_findCachedViewById(C2128R.C2131id.unitSt);
            Intrinsics.checkExpressionValueIsNotNull(myImageView7, "unitSt");
            myImageView7.setChecked(false);
        } else if (valueOf != null && valueOf.intValue() == C2128R.C2131id.unitLbLayout) {
            String str4 = Constant.UNIT_WEIGHT[1];
            Intrinsics.checkExpressionValueIsNotNull(str4, "UNIT_WEIGHT[1]");
            this.weightUnit = str4;
            MyImageView myImageView8 = (MyImageView) _$_findCachedViewById(C2128R.C2131id.unitKg);
            Intrinsics.checkExpressionValueIsNotNull(myImageView8, "unitKg");
            myImageView8.setChecked(false);
            MyImageView myImageView9 = (MyImageView) _$_findCachedViewById(C2128R.C2131id.unitLb);
            Intrinsics.checkExpressionValueIsNotNull(myImageView9, "unitLb");
            myImageView9.setChecked(true);
            MyImageView myImageView10 = (MyImageView) _$_findCachedViewById(C2128R.C2131id.unitSt);
            Intrinsics.checkExpressionValueIsNotNull(myImageView10, "unitSt");
            myImageView10.setChecked(false);
        } else if (valueOf != null && valueOf.intValue() == C2128R.C2131id.unitStLayout) {
            String str5 = Constant.UNIT_WEIGHT[2];
            Intrinsics.checkExpressionValueIsNotNull(str5, "UNIT_WEIGHT[2]");
            this.weightUnit = str5;
            MyImageView myImageView11 = (MyImageView) _$_findCachedViewById(C2128R.C2131id.unitKg);
            Intrinsics.checkExpressionValueIsNotNull(myImageView11, "unitKg");
            myImageView11.setChecked(false);
            MyImageView myImageView12 = (MyImageView) _$_findCachedViewById(C2128R.C2131id.unitLb);
            Intrinsics.checkExpressionValueIsNotNull(myImageView12, "unitLb");
            myImageView12.setChecked(false);
            MyImageView myImageView13 = (MyImageView) _$_findCachedViewById(C2128R.C2131id.unitSt);
            Intrinsics.checkExpressionValueIsNotNull(myImageView13, "unitSt");
            myImageView13.setChecked(true);
        } else if (valueOf != null && valueOf.intValue() == C2128R.C2131id.unitBt) {
            User user2 = this.user;
            if (user2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("user");
            }
            user2.setHeightunit(this.heightUnit);
            User user3 = this.user;
            if (user3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("user");
            }
            user3.setWeightunit(this.weightUnit);
            User user4 = this.user;
            if (user4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("user");
            }
            if (user4.getU_status() == 0) {
                Intent intent = new Intent(this, UserInfoActivity.class);
                User user5 = this.user;
                if (user5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("user");
                }
                startActivity(intent.putExtra("user", user5).putExtra("flag", 3).putExtra("fromLogin", true));
                return;
            }
            modifyUnit();
        }
    }

    private final void modifyUnit() {
        FormBody build = new FormBody.Builder().add("weightunit", this.weightUnit).add("heightunit", this.heightUnit).build();
        Intrinsics.checkExpressionValueIsNotNull(build, "FormBody.Builder()\n     …nit)\n            .build()");
        RequestBody requestBody = build;
        PrimaryPresenter primaryPresenter = (PrimaryPresenter) getMPresenter();
        if (primaryPresenter != null) {
            primaryPresenter.midUnit(requestBody);
        }
        progressShow();
    }
}
