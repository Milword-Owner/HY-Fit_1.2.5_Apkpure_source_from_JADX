package com.huayu.tzc.p014ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import com.baidu.mobstat.Config;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bumptech.glide.Glide;
import com.facebook.share.internal.ShareConstants;
import com.hjq.toast.ToastUtils;
import com.huayu.tzc.C2128R;
import com.huayu.tzc.MainActivity;
import com.huayu.tzc.base.BaseActivity;
import com.huayu.tzc.base.BaseBean;
import com.huayu.tzc.base.Constant;
import com.huayu.tzc.bean.Member;
import com.huayu.tzc.bean.User;
import com.huayu.tzc.contract.MainContract;
import com.huayu.tzc.customview.MyHeaderView;
import com.huayu.tzc.presenter.UserPresenter;
import com.huayu.tzc.utils.GlideEngine;
import com.huayu.tzc.utils.SingleClickKt;
import com.huayu.tzc.utils.UploadImg;
import com.huayu.tzc.utils.Utils;
import com.tencent.mmkv.MMKV;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.internal.entity.CaptureStrategy;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import p036de.hdodenhof.circleimageview.CircleImageView;
import top.zibin.luban.Luban;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 :2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u00020\u00022\u00020\u0004:\u0001:B\u0005¢\u0006\u0002\u0010\u0005J\u0016\u0010\u000f\u001a\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\t0\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u0010H\u0002J\u0010\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u0016\u0010\u0017\u001a\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\t0\u0012H\u0016J\b\u0010\u0018\u001a\u00020\u0007H\u0014J\u001c\u0010\u0019\u001a\u00020\u00102\u0012\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u001b0\u0012H\u0016J\b\u0010\u001c\u001a\u00020\u0003H\u0016J\u0012\u0010\u001d\u001a\u0004\u0018\u00010\t2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J\b\u0010 \u001a\u00020\u0010H\u0002J\b\u0010!\u001a\u00020\u0010H\u0002J\b\u0010\"\u001a\u00020\u0010H\u0002J\b\u0010#\u001a\u00020\u0010H\u0014J\"\u0010$\u001a\u00020\u00102\u0006\u0010%\u001a\u00020\u00072\u0006\u0010&\u001a\u00020\u00072\b\u0010'\u001a\u0004\u0018\u00010(H\u0014J\u0012\u0010)\u001a\u00020\u00102\b\u0010*\u001a\u0004\u0018\u00010+H\u0016J\u0010\u0010,\u001a\u00020\u00102\u0006\u0010%\u001a\u00020\u0007H\u0016J\u0010\u0010-\u001a\u00020\u00102\u0006\u0010.\u001a\u00020/H\u0002J\u0012\u00100\u001a\u00020\u00102\b\u00101\u001a\u0004\u0018\u000102H\u0016J\u0010\u00103\u001a\u00020\u00102\u0006\u0010.\u001a\u00020/H\u0002J\u0010\u00104\u001a\u00020\u00102\u0006\u0010.\u001a\u00020/H\u0002J\u0016\u00105\u001a\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\t0\u0012H\u0016J\u0016\u00106\u001a\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\t0\u0012H\u0016J\u0010\u00107\u001a\u00020\u00102\u0006\u00108\u001a\u000209H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX.¢\u0006\u0002\n\u0000¨\u0006;"}, mo21895d2 = {"Lcom/huayu/tzc/ui/activity/UserInfoActivity;", "Lcom/huayu/tzc/base/BaseActivity;", "Lcom/huayu/tzc/contract/MainContract$UserView;", "Lcom/huayu/tzc/presenter/UserPresenter;", "Landroid/view/View$OnClickListener;", "()V", "flag", "", "img", "", "member", "Lcom/huayu/tzc/bean/Member;", "sex", "user", "Lcom/huayu/tzc/bean/User;", "addMem", "", "baseBean", "Lcom/huayu/tzc/base/BaseBean;", "checkEdit", "compressImg", "uri", "Landroid/net/Uri;", "deleteMem", "getLayoutId", "getMineList", "loginBean", "", "getPresenter", "getTime", "date", "Ljava/util/Date;", "initClick", "initData", "initUnit", "initView", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onClick", "p0", "Landroid/view/View;", "permissionSuccess", "showDatePickerView", "textView", "Landroid/widget/TextView;", "showError", "e", "", "showHeight2PickerView", "showHeightPickerView", "updateMem", "updateUserInfo", "uploadImg", "file", "Ljava/io/File;", "Companion", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
/* renamed from: com.huayu.tzc.ui.activity.UserInfoActivity */
/* compiled from: UserInfoActivity.kt */
public final class UserInfoActivity extends BaseActivity<MainContract.UserView, UserPresenter> implements MainContract.UserView, View.OnClickListener {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final int FLAG_ADD = 2;
    public static final int FLAG_MAIN = 3;
    public static final int FLAG_MEM = 1;
    public static final int FLAG_USER = 0;
    private HashMap _$_findViewCache;
    private int flag;
    /* access modifiers changed from: private */
    public String img = "";
    /* access modifiers changed from: private */
    public Member member = new Member();
    private int sex;
    private User user;

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
        return C2128R.C2133layout.activity_user_info;
    }

    public static final /* synthetic */ UserPresenter access$getMPresenter$p(UserInfoActivity userInfoActivity) {
        return (UserPresenter) userInfoActivity.getMPresenter();
    }

    @Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\b"}, mo21895d2 = {"Lcom/huayu/tzc/ui/activity/UserInfoActivity$Companion;", "", "()V", "FLAG_ADD", "", "FLAG_MAIN", "FLAG_MEM", "FLAG_USER", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
    /* renamed from: com.huayu.tzc.ui.activity.UserInfoActivity$Companion */
    /* compiled from: UserInfoActivity.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @NotNull
    public UserPresenter getPresenter() {
        return new UserPresenter();
    }

    /* access modifiers changed from: protected */
    public void initView() {
        Parcelable decodeParcelable = MMKV.defaultMMKV().decodeParcelable("user", User.class, new User());
        Intrinsics.checkExpressionValueIsNotNull(decodeParcelable, "MMKV.defaultMMKV().decod…User::class.java, User())");
        this.user = (User) decodeParcelable;
        LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(C2128R.C2131id.userMan);
        Intrinsics.checkExpressionValueIsNotNull(linearLayout, "userMan");
        Context context = this;
        linearLayout.setBackground(ContextCompat.getDrawable(context, C2128R.C2130drawable.radius_5));
        ((LinearLayout) _$_findCachedViewById(C2128R.C2131id.userWoman)).setBackgroundColor(ContextCompat.getColor(context, C2128R.C2129color.colorWhite));
        ToastUtils.show((CharSequence) getString(C2128R.string.user_tip));
        TextView textView = (TextView) _$_findCachedViewById(C2128R.C2131id.userHeightUnit);
        Intrinsics.checkExpressionValueIsNotNull(textView, "userHeightUnit");
        User user2 = this.user;
        if (user2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("user");
        }
        textView.setText(user2.getHeightunit());
        this.flag = getIntent().getIntExtra("flag", 0);
        int i = this.flag;
        if (i == 0) {
            Parcelable parcelableExtra = getIntent().getParcelableExtra(ShareConstants.WEB_DIALOG_PARAM_DATA);
            if (parcelableExtra != null) {
                this.member = (Member) parcelableExtra;
                initData();
            } else {
                throw new TypeCastException("null cannot be cast to non-null type com.huayu.tzc.bean.Member");
            }
        } else if (i == 1) {
            Parcelable parcelableExtra2 = getIntent().getParcelableExtra(ShareConstants.WEB_DIALOG_PARAM_DATA);
            if (parcelableExtra2 != null) {
                this.member = (Member) parcelableExtra2;
                ((MyHeaderView) _$_findCachedViewById(C2128R.C2131id.userHeader)).setTitle("");
                ((MyHeaderView) _$_findCachedViewById(C2128R.C2131id.userHeader)).setMenu(getString(C2128R.string.member_delete));
                ((MyHeaderView) _$_findCachedViewById(C2128R.C2131id.userHeader)).setTextClickListener(new UserInfoActivity$initView$1(this));
                initData();
            } else {
                throw new TypeCastException("null cannot be cast to non-null type com.huayu.tzc.bean.Member");
            }
        } else if (i == 2) {
            ((MyHeaderView) _$_findCachedViewById(C2128R.C2131id.userHeader)).setTitle(getString(C2128R.string.adduser));
        } else if (i == 3) {
            Parcelable parcelableExtra3 = getIntent().getParcelableExtra("user");
            if (parcelableExtra3 != null) {
                this.user = (User) parcelableExtra3;
                TextView textView2 = (TextView) _$_findCachedViewById(C2128R.C2131id.userHeightUnit);
                Intrinsics.checkExpressionValueIsNotNull(textView2, "userHeightUnit");
                User user3 = this.user;
                if (user3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("user");
                }
                textView2.setText(user3.getHeightunit());
            } else {
                throw new TypeCastException("null cannot be cast to non-null type com.huayu.tzc.bean.User");
            }
        }
        initUnit();
        initClick();
    }

    private final void initClick() {
        View.OnClickListener onClickListener = this;
        SingleClickKt.singleClick$default((View) (CircleImageView) _$_findCachedViewById(C2128R.C2131id.userImg), onClickListener, 0, 2, (Object) null);
        View.OnClickListener onClickListener2 = onClickListener;
        SingleClickKt.singleClick$default((View) (LinearLayout) _$_findCachedViewById(C2128R.C2131id.userMan), onClickListener2, 0, 2, (Object) null);
        SingleClickKt.singleClick$default((View) (LinearLayout) _$_findCachedViewById(C2128R.C2131id.userWoman), onClickListener2, 0, 2, (Object) null);
        SingleClickKt.singleClick$default((View) (TextView) _$_findCachedViewById(C2128R.C2131id.userDate), onClickListener2, 0, 2, (Object) null);
        SingleClickKt.singleClick$default((View) (TextView) _$_findCachedViewById(C2128R.C2131id.userHeight), onClickListener2, 0, 2, (Object) null);
        SingleClickKt.singleClick$default((View) (Button) _$_findCachedViewById(C2128R.C2131id.userOk), onClickListener2, 0, 2, (Object) null);
    }

    private final void initUnit() {
        User user2 = this.user;
        if (user2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("user");
        }
        boolean z = true;
        if (Intrinsics.areEqual((Object) user2.getWeightunit(), (Object) Constant.UNIT_WEIGHT[2])) {
            LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(C2128R.C2131id.userStLayout);
            Intrinsics.checkExpressionValueIsNotNull(linearLayout, "userStLayout");
            linearLayout.setVisibility(0);
            RelativeLayout relativeLayout = (RelativeLayout) _$_findCachedViewById(C2128R.C2131id.userWeightLayout);
            Intrinsics.checkExpressionValueIsNotNull(relativeLayout, "userWeightLayout");
            relativeLayout.setVisibility(8);
            if (this.member.getM_weight().length() > 0) {
                ((EditText) _$_findCachedViewById(C2128R.C2131id.userWeightSt)).setText((CharSequence) StringsKt.split$default((CharSequence) this.member.getM_weight(), new String[]{Config.TRACE_TODAY_VISIT_SPLIT}, false, 0, 6, (Object) null).get(0));
                ((EditText) _$_findCachedViewById(C2128R.C2131id.userWeightLb)).setText((CharSequence) StringsKt.split$default((CharSequence) this.member.getM_weight(), new String[]{Config.TRACE_TODAY_VISIT_SPLIT}, false, 0, 6, (Object) null).get(1));
            }
        } else {
            if (this.member.getM_weight().length() <= 0) {
                z = false;
            }
            if (z) {
                ((EditText) _$_findCachedViewById(C2128R.C2131id.userWeight)).setText(this.member.getM_weight());
            }
        }
        TextView textView = (TextView) _$_findCachedViewById(C2128R.C2131id.userWeightUnit);
        Intrinsics.checkExpressionValueIsNotNull(textView, "userWeightUnit");
        User user3 = this.user;
        if (user3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("user");
        }
        textView.setText(user3.getWeightunit());
    }

    private final void initData() {
        if (this.member.getM_nickname().length() > 0) {
            ((EditText) _$_findCachedViewById(C2128R.C2131id.userName)).setText(this.member.getM_nickname());
        }
        TextView textView = (TextView) _$_findCachedViewById(C2128R.C2131id.userHeight);
        Intrinsics.checkExpressionValueIsNotNull(textView, "userHeight");
        textView.setText(this.member.getM_height());
        if (this.member.getM_birthday().length() > 0) {
            TextView textView2 = (TextView) _$_findCachedViewById(C2128R.C2131id.userDate);
            Intrinsics.checkExpressionValueIsNotNull(textView2, "userDate");
            textView2.setText(this.member.getM_birthday());
        }
        this.img = this.member.getM_avatar();
        if (this.member.getM_avatar().length() > 0) {
            Glide.with((FragmentActivity) this).load(this.member.getM_avatar()).into((ImageView) (CircleImageView) _$_findCachedViewById(C2128R.C2131id.userImg));
        }
        if (this.member.getM_gender() == 1) {
            this.sex = 1;
            LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(C2128R.C2131id.userWoman);
            Intrinsics.checkExpressionValueIsNotNull(linearLayout, "userWoman");
            Context context = this;
            linearLayout.setBackground(ContextCompat.getDrawable(context, C2128R.C2130drawable.radius_5));
            ((LinearLayout) _$_findCachedViewById(C2128R.C2131id.userMan)).setBackgroundColor(ContextCompat.getColor(context, C2128R.C2129color.colorWhite));
            return;
        }
        this.sex = 0;
        LinearLayout linearLayout2 = (LinearLayout) _$_findCachedViewById(C2128R.C2131id.userMan);
        Intrinsics.checkExpressionValueIsNotNull(linearLayout2, "userMan");
        Context context2 = this;
        linearLayout2.setBackground(ContextCompat.getDrawable(context2, C2128R.C2130drawable.radius_5));
        ((LinearLayout) _$_findCachedViewById(C2128R.C2131id.userWoman)).setBackgroundColor(ContextCompat.getColor(context2, C2128R.C2129color.colorWhite));
    }

    public void getMineList(@NotNull BaseBean<List<Member>> baseBean) {
        Intrinsics.checkParameterIsNotNull(baseBean, "loginBean");
        progressDissmiss();
    }

    public void addMem(@NotNull BaseBean<String> baseBean) {
        Intrinsics.checkParameterIsNotNull(baseBean, "baseBean");
        progressDissmiss();
        if (baseBean.getMeta().getSuccess()) {
            setResult(-1);
            finish();
        }
        ToastUtils.show((CharSequence) baseBean.getMeta().getMessage());
    }

    public void deleteMem(@NotNull BaseBean<String> baseBean) {
        Intrinsics.checkParameterIsNotNull(baseBean, "baseBean");
        progressDissmiss();
        if (baseBean.getMeta().getSuccess()) {
            setResult(-1);
            finish();
        }
    }

    public void updateMem(@NotNull BaseBean<String> baseBean) {
        Intrinsics.checkParameterIsNotNull(baseBean, "baseBean");
        progressDissmiss();
        if (baseBean.getMeta().getSuccess()) {
            setResult(-1);
            finish();
        }
    }

    public void updateUserInfo(@NotNull BaseBean<String> baseBean) {
        Intrinsics.checkParameterIsNotNull(baseBean, "baseBean");
        progressDissmiss();
        if (!baseBean.getMeta().getSuccess()) {
            return;
        }
        if (this.flag == 3) {
            startActivity(new Intent(this, MainActivity.class).setFlags(268468224));
            finish();
            return;
        }
        setResult(-1);
        finish();
    }

    public void showError(@Nullable Throwable th) {
        progressDissmiss();
    }

    public void onClick(@Nullable View view) {
        Integer valueOf = view != null ? Integer.valueOf(view.getId()) : null;
        if (valueOf != null && valueOf.intValue() == C2128R.C2131id.userImg) {
            requestPermission(602);
        } else if (valueOf != null && valueOf.intValue() == C2128R.C2131id.userMan) {
            this.sex = 0;
            LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(C2128R.C2131id.userMan);
            Intrinsics.checkExpressionValueIsNotNull(linearLayout, "userMan");
            Context context = this;
            linearLayout.setBackground(ContextCompat.getDrawable(context, C2128R.C2130drawable.radius_5));
            ((LinearLayout) _$_findCachedViewById(C2128R.C2131id.userWoman)).setBackgroundColor(ContextCompat.getColor(context, C2128R.C2129color.colorWhite));
        } else if (valueOf != null && valueOf.intValue() == C2128R.C2131id.userWoman) {
            this.sex = 1;
            LinearLayout linearLayout2 = (LinearLayout) _$_findCachedViewById(C2128R.C2131id.userWoman);
            Intrinsics.checkExpressionValueIsNotNull(linearLayout2, "userWoman");
            Context context2 = this;
            linearLayout2.setBackground(ContextCompat.getDrawable(context2, C2128R.C2130drawable.radius_5));
            ((LinearLayout) _$_findCachedViewById(C2128R.C2131id.userMan)).setBackgroundColor(ContextCompat.getColor(context2, C2128R.C2129color.colorWhite));
        } else if (valueOf != null && valueOf.intValue() == C2128R.C2131id.userDate) {
            TextView textView = (TextView) _$_findCachedViewById(C2128R.C2131id.userDate);
            Intrinsics.checkExpressionValueIsNotNull(textView, "userDate");
            showDatePickerView(textView);
        } else if (valueOf != null && valueOf.intValue() == C2128R.C2131id.userHeight) {
            User user2 = this.user;
            if (user2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("user");
            }
            if (Intrinsics.areEqual((Object) user2.getHeightunit(), (Object) "cm")) {
                TextView textView2 = (TextView) _$_findCachedViewById(C2128R.C2131id.userHeight);
                Intrinsics.checkExpressionValueIsNotNull(textView2, "userHeight");
                showHeightPickerView(textView2);
                return;
            }
            TextView textView3 = (TextView) _$_findCachedViewById(C2128R.C2131id.userHeight);
            Intrinsics.checkExpressionValueIsNotNull(textView3, "userHeight");
            showHeight2PickerView(textView3);
        } else if (valueOf != null && valueOf.intValue() == C2128R.C2131id.userOk) {
            checkEdit();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:46:0x015e, code lost:
        if (r2 != 3) goto L_0x0332;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void checkEdit() {
        /*
            r12 = this;
            int r0 = com.huayu.tzc.C2128R.C2131id.userName
            android.view.View r0 = r12._$_findCachedViewById(r0)
            android.widget.EditText r0 = (android.widget.EditText) r0
            java.lang.String r1 = "userName"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            android.text.Editable r0 = r0.getText()
            java.lang.String r0 = r0.toString()
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            int r0 = r0.length()
            r2 = 0
            r3 = 1
            if (r0 != 0) goto L_0x0021
            r0 = 1
            goto L_0x0022
        L_0x0021:
            r0 = 0
        L_0x0022:
            r4 = 2131755328(0x7f100140, float:1.9141532E38)
            if (r0 != 0) goto L_0x0336
            int r0 = com.huayu.tzc.C2128R.C2131id.userDate
            android.view.View r0 = r12._$_findCachedViewById(r0)
            android.widget.TextView r0 = (android.widget.TextView) r0
            java.lang.String r5 = "userDate"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r5)
            java.lang.CharSequence r0 = r0.getText()
            java.lang.String r0 = r0.toString()
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            int r0 = r0.length()
            if (r0 != 0) goto L_0x0046
            r0 = 1
            goto L_0x0047
        L_0x0046:
            r0 = 0
        L_0x0047:
            if (r0 != 0) goto L_0x0336
            int r0 = com.huayu.tzc.C2128R.C2131id.userHeight
            android.view.View r0 = r12._$_findCachedViewById(r0)
            android.widget.TextView r0 = (android.widget.TextView) r0
            java.lang.String r6 = "userHeight"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r6)
            java.lang.CharSequence r0 = r0.getText()
            java.lang.String r0 = r0.toString()
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            int r0 = r0.length()
            if (r0 != 0) goto L_0x0068
            r0 = 1
            goto L_0x0069
        L_0x0068:
            r0 = 0
        L_0x0069:
            if (r0 == 0) goto L_0x006d
            goto L_0x0336
        L_0x006d:
            com.huayu.tzc.bean.User r0 = r12.user
            java.lang.String r7 = "user"
            if (r0 != 0) goto L_0x0076
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r7)
        L_0x0076:
            java.lang.String r0 = r0.getWeightunit()
            java.lang.String[] r8 = com.huayu.tzc.base.Constant.UNIT_WEIGHT
            r9 = 2
            r8 = r8[r9]
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r0, (java.lang.Object) r8)
            if (r0 == 0) goto L_0x010f
            int r0 = com.huayu.tzc.C2128R.C2131id.userWeightSt
            android.view.View r0 = r12._$_findCachedViewById(r0)
            android.widget.EditText r0 = (android.widget.EditText) r0
            java.lang.String r8 = "userWeightSt"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r8)
            android.text.Editable r0 = r0.getText()
            java.lang.String r10 = "userWeightSt.text"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r10)
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            int r0 = r0.length()
            if (r0 != 0) goto L_0x00a5
            r0 = 1
            goto L_0x00a6
        L_0x00a5:
            r0 = 0
        L_0x00a6:
            if (r0 != 0) goto L_0x0105
            int r0 = com.huayu.tzc.C2128R.C2131id.userWeightLb
            android.view.View r0 = r12._$_findCachedViewById(r0)
            android.widget.EditText r0 = (android.widget.EditText) r0
            java.lang.String r10 = "userWeightLb"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r10)
            android.text.Editable r0 = r0.getText()
            java.lang.String r11 = "userWeightLb.text"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r11)
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            int r0 = r0.length()
            if (r0 != 0) goto L_0x00c7
            r2 = 1
        L_0x00c7:
            if (r2 == 0) goto L_0x00ca
            goto L_0x0105
        L_0x00ca:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            int r2 = com.huayu.tzc.C2128R.C2131id.userWeightSt
            android.view.View r2 = r12._$_findCachedViewById(r2)
            android.widget.EditText r2 = (android.widget.EditText) r2
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r8)
            android.text.Editable r2 = r2.getText()
            java.lang.String r2 = r2.toString()
            r0.append(r2)
            java.lang.String r2 = ":"
            r0.append(r2)
            int r2 = com.huayu.tzc.C2128R.C2131id.userWeightLb
            android.view.View r2 = r12._$_findCachedViewById(r2)
            android.widget.EditText r2 = (android.widget.EditText) r2
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r10)
            android.text.Editable r2 = r2.getText()
            java.lang.String r2 = r2.toString()
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            goto L_0x014d
        L_0x0105:
            java.lang.String r0 = r12.getString(r4)
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            com.hjq.toast.ToastUtils.show((java.lang.CharSequence) r0)
            return
        L_0x010f:
            int r0 = com.huayu.tzc.C2128R.C2131id.userWeight
            android.view.View r0 = r12._$_findCachedViewById(r0)
            android.widget.EditText r0 = (android.widget.EditText) r0
            java.lang.String r8 = "userWeight"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r8)
            android.text.Editable r0 = r0.getText()
            java.lang.String r10 = "userWeight.text"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r10)
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            int r0 = r0.length()
            if (r0 != 0) goto L_0x012e
            r2 = 1
        L_0x012e:
            if (r2 == 0) goto L_0x013a
            java.lang.String r0 = r12.getString(r4)
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            com.hjq.toast.ToastUtils.show((java.lang.CharSequence) r0)
            return
        L_0x013a:
            int r0 = com.huayu.tzc.C2128R.C2131id.userWeight
            android.view.View r0 = r12._$_findCachedViewById(r0)
            android.widget.EditText r0 = (android.widget.EditText) r0
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r8)
            android.text.Editable r0 = r0.getText()
            java.lang.String r0 = r0.toString()
        L_0x014d:
            int r2 = r12.flag
            if (r2 == 0) goto L_0x0266
            java.lang.String r4 = "m_weight"
            java.lang.String r8 = "m_height"
            java.lang.String r10 = "m_gender"
            java.lang.String r11 = "m_nickname"
            if (r2 == r3) goto L_0x01dc
            if (r2 == r9) goto L_0x0162
            r3 = 3
            if (r2 == r3) goto L_0x0266
            goto L_0x0332
        L_0x0162:
            okhttp3.FormBody$Builder r2 = new okhttp3.FormBody$Builder
            r2.<init>()
            int r3 = com.huayu.tzc.C2128R.C2131id.userName
            android.view.View r3 = r12._$_findCachedViewById(r3)
            android.widget.EditText r3 = (android.widget.EditText) r3
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, r1)
            android.text.Editable r1 = r3.getText()
            java.lang.String r1 = r1.toString()
            okhttp3.FormBody$Builder r1 = r2.add(r11, r1)
            int r2 = r12.sex
            java.lang.String r2 = java.lang.String.valueOf(r2)
            okhttp3.FormBody$Builder r1 = r1.add(r10, r2)
            int r2 = com.huayu.tzc.C2128R.C2131id.userHeight
            android.view.View r2 = r12._$_findCachedViewById(r2)
            android.widget.TextView r2 = (android.widget.TextView) r2
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r6)
            java.lang.CharSequence r2 = r2.getText()
            java.lang.String r2 = r2.toString()
            okhttp3.FormBody$Builder r1 = r1.add(r8, r2)
            okhttp3.FormBody$Builder r0 = r1.add(r4, r0)
            java.lang.String r1 = r12.img
            java.lang.String r2 = "m_avatar"
            okhttp3.FormBody$Builder r0 = r0.add(r2, r1)
            int r1 = com.huayu.tzc.C2128R.C2131id.userDate
            android.view.View r1 = r12._$_findCachedViewById(r1)
            android.widget.TextView r1 = (android.widget.TextView) r1
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r5)
            java.lang.CharSequence r1 = r1.getText()
            java.lang.String r1 = r1.toString()
            java.lang.String r2 = "m_birthday"
            okhttp3.FormBody$Builder r0 = r0.add(r2, r1)
            okhttp3.FormBody r0 = r0.build()
            java.lang.String r1 = "FormBody.Builder()\n     …                 .build()"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            okhttp3.RequestBody r0 = (okhttp3.RequestBody) r0
            com.huayu.tzc.base.BaseContract$BasePresenter r1 = r12.getMPresenter()
            com.huayu.tzc.presenter.UserPresenter r1 = (com.huayu.tzc.presenter.UserPresenter) r1
            if (r1 == 0) goto L_0x0332
            r1.addMem(r0)
            goto L_0x0332
        L_0x01dc:
            okhttp3.FormBody$Builder r2 = new okhttp3.FormBody$Builder
            r2.<init>()
            com.huayu.tzc.bean.Member r3 = r12.member
            int r3 = r3.getMember_id()
            java.lang.String r3 = java.lang.String.valueOf(r3)
            java.lang.String r7 = "member_id"
            okhttp3.FormBody$Builder r2 = r2.add(r7, r3)
            int r3 = com.huayu.tzc.C2128R.C2131id.userName
            android.view.View r3 = r12._$_findCachedViewById(r3)
            android.widget.EditText r3 = (android.widget.EditText) r3
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, r1)
            android.text.Editable r1 = r3.getText()
            java.lang.String r1 = r1.toString()
            okhttp3.FormBody$Builder r1 = r2.add(r11, r1)
            int r2 = r12.sex
            java.lang.String r2 = java.lang.String.valueOf(r2)
            okhttp3.FormBody$Builder r1 = r1.add(r10, r2)
            int r2 = com.huayu.tzc.C2128R.C2131id.userHeight
            android.view.View r2 = r12._$_findCachedViewById(r2)
            android.widget.TextView r2 = (android.widget.TextView) r2
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r6)
            java.lang.CharSequence r2 = r2.getText()
            java.lang.String r2 = r2.toString()
            okhttp3.FormBody$Builder r1 = r1.add(r8, r2)
            okhttp3.FormBody$Builder r0 = r1.add(r4, r0)
            java.lang.String r1 = r12.img
            java.lang.String r2 = "m_avatar"
            okhttp3.FormBody$Builder r0 = r0.add(r2, r1)
            int r1 = com.huayu.tzc.C2128R.C2131id.userDate
            android.view.View r1 = r12._$_findCachedViewById(r1)
            android.widget.TextView r1 = (android.widget.TextView) r1
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r5)
            java.lang.CharSequence r1 = r1.getText()
            java.lang.String r1 = r1.toString()
            java.lang.String r2 = "m_birthday"
            okhttp3.FormBody$Builder r0 = r0.add(r2, r1)
            okhttp3.FormBody r0 = r0.build()
            java.lang.String r1 = "FormBody.Builder()\n     …                 .build()"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            okhttp3.RequestBody r0 = (okhttp3.RequestBody) r0
            com.huayu.tzc.base.BaseContract$BasePresenter r1 = r12.getMPresenter()
            com.huayu.tzc.presenter.UserPresenter r1 = (com.huayu.tzc.presenter.UserPresenter) r1
            if (r1 == 0) goto L_0x0332
            r1.updateMem(r0)
            goto L_0x0332
        L_0x0266:
            okhttp3.FormBody$Builder r2 = new okhttp3.FormBody$Builder
            r2.<init>()
            com.huayu.tzc.bean.User r3 = r12.user
            if (r3 != 0) goto L_0x0272
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r7)
        L_0x0272:
            int r3 = r3.getU_id()
            java.lang.String r3 = java.lang.String.valueOf(r3)
            java.lang.String r4 = "u_id"
            okhttp3.FormBody$Builder r2 = r2.add(r4, r3)
            int r3 = com.huayu.tzc.C2128R.C2131id.userName
            android.view.View r3 = r12._$_findCachedViewById(r3)
            android.widget.EditText r3 = (android.widget.EditText) r3
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, r1)
            android.text.Editable r1 = r3.getText()
            java.lang.String r1 = r1.toString()
            java.lang.String r3 = "u_nickname"
            okhttp3.FormBody$Builder r1 = r2.add(r3, r1)
            int r2 = r12.sex
            java.lang.String r2 = java.lang.String.valueOf(r2)
            java.lang.String r3 = "u_gender"
            okhttp3.FormBody$Builder r1 = r1.add(r3, r2)
            int r2 = com.huayu.tzc.C2128R.C2131id.userHeight
            android.view.View r2 = r12._$_findCachedViewById(r2)
            android.widget.TextView r2 = (android.widget.TextView) r2
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r6)
            java.lang.CharSequence r2 = r2.getText()
            java.lang.String r2 = r2.toString()
            java.lang.String r3 = "u_height"
            okhttp3.FormBody$Builder r1 = r1.add(r3, r2)
            java.lang.String r2 = "u_weight"
            okhttp3.FormBody$Builder r0 = r1.add(r2, r0)
            java.lang.String r1 = r12.img
            java.lang.String r2 = "u_avatar"
            okhttp3.FormBody$Builder r0 = r0.add(r2, r1)
            int r1 = com.huayu.tzc.C2128R.C2131id.userDate
            android.view.View r1 = r12._$_findCachedViewById(r1)
            android.widget.TextView r1 = (android.widget.TextView) r1
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r5)
            java.lang.CharSequence r1 = r1.getText()
            java.lang.String r1 = r1.toString()
            java.lang.String r2 = "u_birthday"
            okhttp3.FormBody$Builder r0 = r0.add(r2, r1)
            com.huayu.tzc.bean.User r1 = r12.user
            if (r1 != 0) goto L_0x02ec
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r7)
        L_0x02ec:
            java.lang.String r1 = r1.getWeightunit()
            java.lang.String r2 = "weightunit"
            okhttp3.FormBody$Builder r0 = r0.add(r2, r1)
            com.huayu.tzc.bean.User r1 = r12.user
            if (r1 != 0) goto L_0x02fd
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r7)
        L_0x02fd:
            java.lang.String r1 = r1.getHeightunit()
            java.lang.String r2 = "heightunit"
            okhttp3.FormBody$Builder r0 = r0.add(r2, r1)
            okhttp3.FormBody r0 = r0.build()
            java.lang.String r1 = r12.getTAG()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "checkEdit: "
            r2.append(r3)
            int r3 = r12.flag
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            android.util.Log.e(r1, r2)
            com.huayu.tzc.base.BaseContract$BasePresenter r1 = r12.getMPresenter()
            com.huayu.tzc.presenter.UserPresenter r1 = (com.huayu.tzc.presenter.UserPresenter) r1
            if (r1 == 0) goto L_0x0332
            okhttp3.RequestBody r0 = (okhttp3.RequestBody) r0
            r1.updateUserInfo(r0)
        L_0x0332:
            r12.progressShow()
            return
        L_0x0336:
            java.lang.String r0 = r12.getString(r4)
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            com.hjq.toast.ToastUtils.show((java.lang.CharSequence) r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huayu.tzc.p014ui.activity.UserInfoActivity.checkEdit():void");
    }

    private final void showHeightPickerView(TextView textView) {
        List arrayList = new ArrayList();
        for (int i = 10; i <= 299; i++) {
            arrayList.add(String.valueOf(i) + "");
        }
        OptionsPickerView build = new OptionsPickerBuilder(this, new UserInfoActivity$showHeightPickerView$optionsPickerView$1(textView, arrayList)).setSelectOptions(160).setOutSideCancelable(true).setCancelText(getString(C2128R.string.common_cancel)).setSubmitText(getString(C2128R.string.common_confirm)).build();
        Intrinsics.checkExpressionValueIsNotNull(build, "OptionsPickerBuilder(thi…         .build<String>()");
        build.setPicker(arrayList);
        build.show();
    }

    private final void showHeight2PickerView(TextView textView) {
        List arrayList = new ArrayList();
        List arrayList2 = new ArrayList();
        for (int i = 0; i <= 12; i++) {
            arrayList.add(String.valueOf(i) + "");
            arrayList2.add(String.valueOf(i) + "");
        }
        OptionsPickerView build = new OptionsPickerBuilder(this, new UserInfoActivity$showHeight2PickerView$optionsPickerView$1(textView, arrayList, arrayList2)).setSelectOptions(0).setOutSideCancelable(true).setCancelText(getString(C2128R.string.common_cancel)).setSubmitText(getString(C2128R.string.common_confirm)).build();
        Intrinsics.checkExpressionValueIsNotNull(build, "OptionsPickerBuilder(thi…         .build<String>()");
        build.setNPicker(arrayList, arrayList2, (List) null);
        build.show();
    }

    private final void showDatePickerView(TextView textView) {
        Calendar instance = Calendar.getInstance();
        Calendar.getInstance();
        instance.set(1920, 1, 1);
        new TimePickerBuilder(this, new UserInfoActivity$showDatePickerView$pickerView$1(this, textView)).setOutSideCancelable(true).setCancelText(getString(C2128R.string.common_cancel)).setSubmitText(getString(C2128R.string.common_confirm)).setLabel(getString(C2128R.string.common_year), getString(C2128R.string.common_month), getString(C2128R.string.common_day), getString(C2128R.string.hour), getString(C2128R.string.min), getString(C2128R.string.f1666ss)).build().show();
    }

    /* access modifiers changed from: private */
    public final String getTime(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(date);
    }

    public void permissionSuccess(int i) {
        super.permissionSuccess(i);
        Matisse.from((Activity) this).choose(MimeType.ofImage()).countable(false).restrictOrientation(-1).thumbnailScale(0.85f).capture(true).captureStrategy(new CaptureStrategy(true, "com.huayu.tzc.provider")).imageEngine(new GlideEngine()).showSingleMediaType(true).maxSelectable(1).theme(2131820787).forResult(i);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1 && i == 602) {
            List<Uri> obtainResult = Matisse.obtainResult(intent);
            progressShow();
            Uri uri = obtainResult.get(0);
            Intrinsics.checkExpressionValueIsNotNull(uri, "pathList[0]");
            compressImg(uri);
        }
    }

    private final void compressImg(Uri uri) {
        Context context = this;
        String realFilePath = Utils.getRealFilePath(context, uri);
        Intrinsics.checkExpressionValueIsNotNull(realFilePath, "Utils.getRealFilePath(this, uri)");
        Luban.with(context).load(realFilePath).ignoreBy(200).setCompressListener(new UserInfoActivity$compressImg$1(this)).launch();
    }

    /* access modifiers changed from: private */
    public final void uploadImg(File file) {
        Context context = this;
        String name = file.getName();
        User user2 = this.user;
        if (user2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("user");
        }
        String key = UploadImg.getKey(context, name, user2.getU_id());
        Intrinsics.checkExpressionValueIsNotNull(key, "UploadImg.getKey(this, file.name, user.u_id)");
        new Thread(new UserInfoActivity$uploadImg$1(this, key, file)).start();
    }
}
