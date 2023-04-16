package com.huayu.tzc.p014ui.activity.setting;

import android.os.Parcelable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.hjq.toast.ToastUtils;
import com.huayu.tzc.C2128R;
import com.huayu.tzc.base.BaseActivity;
import com.huayu.tzc.base.BaseBean;
import com.huayu.tzc.bean.User;
import com.huayu.tzc.bean.VipBean;
import com.huayu.tzc.contract.MainContract;
import com.huayu.tzc.presenter.VipPresenter;
import com.huayu.tzc.utils.SingleClickKt;
import com.huayu.tzc.utils.Utils;
import com.tencent.mmkv.MMKV;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import p036de.hdodenhof.circleimageview.CircleImageView;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u00020\u00022\u00020\u0004B\u0005¢\u0006\u0002\u0010\u0005J\b\u0010\f\u001a\u00020\u0007H\u0014J\b\u0010\r\u001a\u00020\u0003H\u0016J\u0016\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u000fH\u0014J\u0016\u0010\u0013\u001a\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00140\u0011H\u0016J\u0012\u0010\u0015\u001a\u00020\u000f2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\u0012\u0010\u0018\u001a\u00020\u000f2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\b\u0010\u001b\u001a\u00020\u000fH\u0002J\u0016\u0010\u001c\u001a\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00140\u0011H\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX.¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u000e¢\u0006\u0002\n\u0000¨\u0006\u001d"}, mo21895d2 = {"Lcom/huayu/tzc/ui/activity/setting/VipActivity;", "Lcom/huayu/tzc/base/BaseActivity;", "Lcom/huayu/tzc/contract/MainContract$VipView;", "Lcom/huayu/tzc/presenter/VipPresenter;", "Landroid/view/View$OnClickListener;", "()V", "flag", "", "user", "Lcom/huayu/tzc/bean/User;", "vipBean", "Lcom/huayu/tzc/bean/VipBean;", "getLayoutId", "getPresenter", "getVipMsg", "", "baseBean", "Lcom/huayu/tzc/base/BaseBean;", "initView", "modifyUpgrade", "", "onClick", "p0", "Landroid/view/View;", "showError", "e", "", "updateView", "upgradeVip", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
/* renamed from: com.huayu.tzc.ui.activity.setting.VipActivity */
/* compiled from: VipActivity.kt */
public final class VipActivity extends BaseActivity<MainContract.VipView, VipPresenter> implements MainContract.VipView, View.OnClickListener {
    private HashMap _$_findViewCache;
    private int flag = -1;
    private User user;
    private VipBean vipBean = new VipBean();

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
        return C2128R.C2133layout.activity_vip;
    }

    @NotNull
    public VipPresenter getPresenter() {
        return new VipPresenter();
    }

    /* access modifiers changed from: protected */
    public void initView() {
        Parcelable decodeParcelable = MMKV.defaultMMKV().decodeParcelable("user", User.class, new User());
        Intrinsics.checkExpressionValueIsNotNull(decodeParcelable, "MMKV.defaultMMKV().decod…User::class.java, User())");
        this.user = (User) decodeParcelable;
        User user2 = this.user;
        if (user2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("user");
        }
        if (user2.getU_avatar().length() > 0) {
            RequestManager with = Glide.with((FragmentActivity) this);
            User user3 = this.user;
            if (user3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("user");
            }
            Intrinsics.checkExpressionValueIsNotNull(with.load(user3.getU_avatar()).into((ImageView) (CircleImageView) _$_findCachedViewById(C2128R.C2131id.vipImg)), "Glide.with(this).load(user.u_avatar).into(vipImg)");
        } else {
            CircleImageView circleImageView = (CircleImageView) _$_findCachedViewById(C2128R.C2131id.vipImg);
            User user4 = this.user;
            if (user4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("user");
            }
            circleImageView.setImageResource(Utils.getImgRes(user4.getU_gender()));
        }
        User user5 = this.user;
        if (user5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("user");
        }
        if (user5.getU_level() == 0) {
            Glide.with((FragmentActivity) this).load(Integer.valueOf(C2128R.C2130drawable.ic_vip_f)).into((ImageView) _$_findCachedViewById(C2128R.C2131id.vipPic));
            TextView textView = (TextView) _$_findCachedViewById(C2128R.C2131id.vipLevel);
            Intrinsics.checkExpressionValueIsNotNull(textView, "vipLevel");
            textView.setText(getString(C2128R.string.pt_vip));
        } else {
            TextView textView2 = (TextView) _$_findCachedViewById(C2128R.C2131id.vipLevel);
            Intrinsics.checkExpressionValueIsNotNull(textView2, "vipLevel");
            textView2.setText(getString(C2128R.string.gj_vip));
            Intrinsics.checkExpressionValueIsNotNull(Glide.with((FragmentActivity) this).load(Integer.valueOf(C2128R.C2130drawable.ic_vip_m)).into((ImageView) _$_findCachedViewById(C2128R.C2131id.vipPic)), "Glide.with(this).load(R.…le.ic_vip_m).into(vipPic)");
        }
        TextView textView3 = (TextView) _$_findCachedViewById(C2128R.C2131id.vipName);
        Intrinsics.checkExpressionValueIsNotNull(textView3, "vipName");
        User user6 = this.user;
        if (user6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("user");
        }
        textView3.setText(user6.getU_nickname());
        VipPresenter vipPresenter = (VipPresenter) getMPresenter();
        if (vipPresenter != null) {
            vipPresenter.getVipMsg();
        }
        progressShow();
        View.OnClickListener onClickListener = this;
        SingleClickKt.singleClick$default((View) (Button) _$_findCachedViewById(C2128R.C2131id.vipBt), onClickListener, 0, 2, (Object) null);
        SingleClickKt.singleClick$default((View) (TextView) _$_findCachedViewById(C2128R.C2131id.vipUpdate), onClickListener, 0, 2, (Object) null);
    }

    private final void updateView() {
        int i = this.flag;
        if (i == -1) {
            return;
        }
        if (i == 0) {
            ((EditText) _$_findCachedViewById(C2128R.C2131id.vipNumber)).setText(this.vipBean.getPurchase());
            TextView textView = (TextView) _$_findCachedViewById(C2128R.C2131id.vipUpdate);
            Intrinsics.checkExpressionValueIsNotNull(textView, "vipUpdate");
            textView.setVisibility(0);
            Button button = (Button) _$_findCachedViewById(C2128R.C2131id.vipBt);
            Intrinsics.checkExpressionValueIsNotNull(button, "vipBt");
            button.setVisibility(8);
            ((TextView) _$_findCachedViewById(C2128R.C2131id.vipTip)).setText(C2128R.string.vip_wait);
        } else if (i == 1) {
            LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(C2128R.C2131id.vipNumLayout);
            Intrinsics.checkExpressionValueIsNotNull(linearLayout, "vipNumLayout");
            linearLayout.setVisibility(8);
            TextView textView2 = (TextView) _$_findCachedViewById(C2128R.C2131id.vipTip);
            Intrinsics.checkExpressionValueIsNotNull(textView2, "vipTip");
            textView2.setVisibility(8);
            TextView textView3 = (TextView) _$_findCachedViewById(C2128R.C2131id.vipSuccess);
            Intrinsics.checkExpressionValueIsNotNull(textView3, "vipSuccess");
            textView3.setVisibility(0);
            TextView textView4 = (TextView) _$_findCachedViewById(C2128R.C2131id.vipLevel);
            Intrinsics.checkExpressionValueIsNotNull(textView4, "vipLevel");
            textView4.setText(getString(C2128R.string.gj_vip));
            Glide.with((FragmentActivity) this).load(Integer.valueOf(C2128R.C2130drawable.ic_vip_m)).into((ImageView) _$_findCachedViewById(C2128R.C2131id.vipPic));
            String string = getString(C2128R.string.vip_success);
            Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.vip_success)");
            String replace$default = StringsKt.replace$default(StringsKt.replace$default(string, "order_no", this.vipBean.getPurchase(), false, 4, (Object) null), "vip_date", this.vipBean.getValiditydate(), false, 4, (Object) null);
            TextView textView5 = (TextView) _$_findCachedViewById(C2128R.C2131id.vipSuccess);
            Intrinsics.checkExpressionValueIsNotNull(textView5, "vipSuccess");
            textView5.setText(replace$default);
        }
    }

    public void getVipMsg(@NotNull BaseBean<VipBean> baseBean) {
        Intrinsics.checkParameterIsNotNull(baseBean, "baseBean");
        progressDissmiss();
        if (baseBean.getData() == null) {
            this.flag = -1;
        } else {
            VipBean data = baseBean.getData();
            if (data == null) {
                Intrinsics.throwNpe();
            }
            this.vipBean = data;
            VipBean data2 = baseBean.getData();
            if (data2 == null) {
                Intrinsics.throwNpe();
            }
            this.flag = Integer.parseInt(data2.getAuditstatus());
        }
        updateView();
    }

    public void upgradeVip(@NotNull BaseBean<String> baseBean) {
        Intrinsics.checkParameterIsNotNull(baseBean, "baseBean");
        progressDissmiss();
        if (baseBean.getMeta().getSuccess()) {
            VipPresenter vipPresenter = (VipPresenter) getMPresenter();
            if (vipPresenter != null) {
                vipPresenter.getVipMsg();
            }
            progressShow();
        }
        ToastUtils.show((CharSequence) baseBean.getMeta().getMessage());
    }

    public void modifyUpgrade(@NotNull BaseBean<String> baseBean) {
        Intrinsics.checkParameterIsNotNull(baseBean, "baseBean");
        progressDissmiss();
        ToastUtils.show((CharSequence) baseBean.getMeta().getMessage());
    }

    public void showError(@Nullable Throwable th) {
        progressDissmiss();
    }

    public void onClick(@Nullable View view) {
        Integer valueOf = view != null ? Integer.valueOf(view.getId()) : null;
        if (valueOf != null && valueOf.intValue() == C2128R.C2131id.vipBt) {
            EditText editText = (EditText) _$_findCachedViewById(C2128R.C2131id.vipNumber);
            Intrinsics.checkExpressionValueIsNotNull(editText, "vipNumber");
            if (TextUtils.isEmpty(editText.getText().toString())) {
                ToastUtils.show((int) C2128R.string.nonull);
                return;
            }
            progressShow();
            VipPresenter vipPresenter = (VipPresenter) getMPresenter();
            if (vipPresenter != null) {
                EditText editText2 = (EditText) _$_findCachedViewById(C2128R.C2131id.vipNumber);
                Intrinsics.checkExpressionValueIsNotNull(editText2, "vipNumber");
                vipPresenter.upgradeVip(editText2.getText().toString());
            }
        } else if (valueOf != null && valueOf.intValue() == C2128R.C2131id.vipUpdate) {
            EditText editText3 = (EditText) _$_findCachedViewById(C2128R.C2131id.vipNumber);
            Intrinsics.checkExpressionValueIsNotNull(editText3, "vipNumber");
            if (TextUtils.isEmpty(editText3.getText().toString())) {
                ToastUtils.show((int) C2128R.string.nonull);
                return;
            }
            progressShow();
            VipPresenter vipPresenter2 = (VipPresenter) getMPresenter();
            if (vipPresenter2 != null) {
                EditText editText4 = (EditText) _$_findCachedViewById(C2128R.C2131id.vipNumber);
                Intrinsics.checkExpressionValueIsNotNull(editText4, "vipNumber");
                vipPresenter2.modifyUpgrade(editText4.getText().toString());
            }
        }
    }
}
