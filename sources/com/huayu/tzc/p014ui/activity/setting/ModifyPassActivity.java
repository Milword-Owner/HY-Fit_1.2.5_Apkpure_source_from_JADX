package com.huayu.tzc.p014ui.activity.setting;

import android.content.Intent;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.hjq.toast.ToastUtils;
import com.huayu.tzc.C2128R;
import com.huayu.tzc.base.BaseActivity;
import com.huayu.tzc.base.BaseBean;
import com.huayu.tzc.contract.MainContract;
import com.huayu.tzc.customview.MyImageView;
import com.huayu.tzc.p014ui.activity.login.LoginActivity;
import com.huayu.tzc.presenter.ModifyPresenter;
import com.huayu.tzc.utils.SingleClickKt;
import com.tencent.mmkv.MMKV;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u00020\u00022\u00020\u0004B\u0005¢\u0006\u0002\u0010\u0005J\b\u0010\b\u001a\u00020\tH\u0002J\b\u0010\n\u001a\u00020\u000bH\u0014J\b\u0010\f\u001a\u00020\u0003H\u0016J\b\u0010\r\u001a\u00020\tH\u0002J\b\u0010\u000e\u001a\u00020\tH\u0014J\u0016\u0010\u000f\u001a\u00020\t2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00070\u0011H\u0016J\u0012\u0010\u0012\u001a\u00020\t2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016J\u0012\u0010\u0015\u001a\u00020\t2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000¨\u0006\u0018"}, mo21895d2 = {"Lcom/huayu/tzc/ui/activity/setting/ModifyPassActivity;", "Lcom/huayu/tzc/base/BaseActivity;", "Lcom/huayu/tzc/contract/MainContract$ModifyView;", "Lcom/huayu/tzc/presenter/ModifyPresenter;", "Landroid/view/View$OnClickListener;", "()V", "email", "", "checkPass", "", "getLayoutId", "", "getPresenter", "initClick", "initView", "modifyPass", "baseBean", "Lcom/huayu/tzc/base/BaseBean;", "onClick", "p0", "Landroid/view/View;", "showError", "e", "", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
/* renamed from: com.huayu.tzc.ui.activity.setting.ModifyPassActivity */
/* compiled from: ModifyPassActivity.kt */
public final class ModifyPassActivity extends BaseActivity<MainContract.ModifyView, ModifyPresenter> implements MainContract.ModifyView, View.OnClickListener {
    private HashMap _$_findViewCache;
    private String email = "";

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
        return C2128R.C2133layout.activity_modify_pass;
    }

    @NotNull
    public ModifyPresenter getPresenter() {
        return new ModifyPresenter();
    }

    /* access modifiers changed from: protected */
    public void initView() {
        String decodeString = MMKV.defaultMMKV().decodeString("mail");
        Intrinsics.checkExpressionValueIsNotNull(decodeString, "MMKV.defaultMMKV().decodeString(\"mail\")");
        this.email = decodeString;
        TextView textView = (TextView) _$_findCachedViewById(C2128R.C2131id.modifyMail);
        Intrinsics.checkExpressionValueIsNotNull(textView, "modifyMail");
        textView.setText(this.email);
        initClick();
    }

    private final void initClick() {
        View.OnClickListener onClickListener = this;
        ((MyImageView) _$_findCachedViewById(C2128R.C2131id.modifyImgOld)).setOnClickListener(onClickListener);
        ((MyImageView) _$_findCachedViewById(C2128R.C2131id.modifyImg)).setOnClickListener(onClickListener);
        ((MyImageView) _$_findCachedViewById(C2128R.C2131id.modifyImg2)).setOnClickListener(onClickListener);
        SingleClickKt.singleClick$default((View) (Button) _$_findCachedViewById(C2128R.C2131id.modifyBt), onClickListener, 0, 2, (Object) null);
    }

    public void modifyPass(@NotNull BaseBean<String> baseBean) {
        Intrinsics.checkParameterIsNotNull(baseBean, "baseBean");
        progressDissmiss();
        if (baseBean.getMeta().getSuccess()) {
            MMKV.defaultMMKV().removeValueForKey("token");
            MMKV.defaultMMKV().removeValueForKey("user");
            MMKV.defaultMMKV().removeValueForKey("password");
            startActivity(new Intent(this, LoginActivity.class).setFlags(268468224));
            finish();
        }
        ToastUtils.show((CharSequence) baseBean.getMeta().getMessage());
    }

    public void showError(@Nullable Throwable th) {
        progressDissmiss();
    }

    public void onClick(@Nullable View view) {
        Integer valueOf = view != null ? Integer.valueOf(view.getId()) : null;
        if (valueOf != null && valueOf.intValue() == C2128R.C2131id.modifyImgOld) {
            MyImageView myImageView = (MyImageView) _$_findCachedViewById(C2128R.C2131id.modifyImgOld);
            Intrinsics.checkExpressionValueIsNotNull(myImageView, "modifyImgOld");
            EditText editText = (EditText) _$_findCachedViewById(C2128R.C2131id.modifyPassOld);
            Intrinsics.checkExpressionValueIsNotNull(editText, "modifyPassOld");
            eyeEditText(myImageView, editText);
        } else if (valueOf != null && valueOf.intValue() == C2128R.C2131id.modifyImg) {
            MyImageView myImageView2 = (MyImageView) _$_findCachedViewById(C2128R.C2131id.modifyImg);
            Intrinsics.checkExpressionValueIsNotNull(myImageView2, "modifyImg");
            EditText editText2 = (EditText) _$_findCachedViewById(C2128R.C2131id.modifyPassNew);
            Intrinsics.checkExpressionValueIsNotNull(editText2, "modifyPassNew");
            eyeEditText(myImageView2, editText2);
        } else if (valueOf != null && valueOf.intValue() == C2128R.C2131id.modifyImg2) {
            MyImageView myImageView3 = (MyImageView) _$_findCachedViewById(C2128R.C2131id.modifyImg2);
            Intrinsics.checkExpressionValueIsNotNull(myImageView3, "modifyImg2");
            EditText editText3 = (EditText) _$_findCachedViewById(C2128R.C2131id.modifyPassConfirm);
            Intrinsics.checkExpressionValueIsNotNull(editText3, "modifyPassConfirm");
            eyeEditText(myImageView3, editText3);
        } else if (valueOf != null && valueOf.intValue() == C2128R.C2131id.modifyBt) {
            checkPass();
        }
    }

    private final void checkPass() {
        EditText editText = (EditText) _$_findCachedViewById(C2128R.C2131id.modifyPassConfirm);
        Intrinsics.checkExpressionValueIsNotNull(editText, "modifyPassConfirm");
        Editable text = editText.getText();
        Intrinsics.checkExpressionValueIsNotNull(text, "modifyPassConfirm.text");
        boolean z = false;
        if (!(text.length() == 0)) {
            EditText editText2 = (EditText) _$_findCachedViewById(C2128R.C2131id.modifyPassNew);
            Intrinsics.checkExpressionValueIsNotNull(editText2, "modifyPassNew");
            Editable text2 = editText2.getText();
            Intrinsics.checkExpressionValueIsNotNull(text2, "modifyPassNew.text");
            if (!(text2.length() == 0)) {
                EditText editText3 = (EditText) _$_findCachedViewById(C2128R.C2131id.modifyPassOld);
                Intrinsics.checkExpressionValueIsNotNull(editText3, "modifyPassOld");
                Editable text3 = editText3.getText();
                Intrinsics.checkExpressionValueIsNotNull(text3, "modifyPassOld.text");
                if (text3.length() == 0) {
                    z = true;
                }
                if (!z) {
                    EditText editText4 = (EditText) _$_findCachedViewById(C2128R.C2131id.modifyPassNew);
                    Intrinsics.checkExpressionValueIsNotNull(editText4, "modifyPassNew");
                    String obj = editText4.getText().toString();
                    EditText editText5 = (EditText) _$_findCachedViewById(C2128R.C2131id.modifyPassConfirm);
                    Intrinsics.checkExpressionValueIsNotNull(editText5, "modifyPassConfirm");
                    if (!Intrinsics.areEqual((Object) obj, (Object) editText5.getText().toString())) {
                        ToastUtils.show((CharSequence) getString(C2128R.string.register_password_again_error));
                        return;
                    }
                    EditText editText6 = (EditText) _$_findCachedViewById(C2128R.C2131id.modifyPassNew);
                    Intrinsics.checkExpressionValueIsNotNull(editText6, "modifyPassNew");
                    if (editText6.getText().length() >= 6) {
                        EditText editText7 = (EditText) _$_findCachedViewById(C2128R.C2131id.modifyPassConfirm);
                        Intrinsics.checkExpressionValueIsNotNull(editText7, "modifyPassConfirm");
                        if (editText7.getText().length() >= 6) {
                            progressShow();
                            ModifyPresenter modifyPresenter = (ModifyPresenter) getMPresenter();
                            if (modifyPresenter != null) {
                                EditText editText8 = (EditText) _$_findCachedViewById(C2128R.C2131id.modifyPassOld);
                                Intrinsics.checkExpressionValueIsNotNull(editText8, "modifyPassOld");
                                String obj2 = editText8.getText().toString();
                                EditText editText9 = (EditText) _$_findCachedViewById(C2128R.C2131id.modifyPassConfirm);
                                Intrinsics.checkExpressionValueIsNotNull(editText9, "modifyPassConfirm");
                                modifyPresenter.modifyPass(obj2, editText9.getText().toString());
                                return;
                            }
                            return;
                        }
                    }
                    ToastUtils.show((CharSequence) getString(C2128R.string.pass_six));
                    return;
                }
            }
        }
        ToastUtils.show((CharSequence) getString(C2128R.string.register_password_empty));
    }
}
