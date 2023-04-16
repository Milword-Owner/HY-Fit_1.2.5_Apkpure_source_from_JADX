package com.huayu.tzc.customview;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.LayoutRes;
import com.huayu.tzc.C2128R;
import com.huayu.tzc.utils.NoDoubleClickListener;

public class BaseAlterDialog {
    private Dialog dialog;
    private boolean isCanCancelTouchOutside = false;
    private boolean isCancelable = true;
    private boolean isShowCancelBtn = true;
    private boolean isShowOkBtn = true;
    private boolean isShowTitle = false;
    private TextView mCancelBtn;
    private final String mContent;
    private final Context mContext;
    private TextView mInfoTv;
    private TextView mOkBtn;
    private String mTitle;
    private TextView mTitleTv;
    @LayoutRes
    private int resource = C2128R.C2133layout.dialog_custom;

    public BaseAlterDialog(Context context, String str) {
        this.mContext = context;
        this.mContent = str;
        initDialog();
    }

    public BaseAlterDialog(Context context, String str, @LayoutRes int i) {
        this.mContext = context;
        this.mContent = str;
        this.resource = i;
        initDialog();
    }

    public BaseAlterDialog(Context context, @LayoutRes int i) {
        this.mContext = context;
        this.mContent = "";
        this.resource = i;
        initDialog2();
    }

    private void initDialog2() {
        this.dialog = new Dialog(this.mContext, C2128R.C2134style.MyDialog);
        View inflate = LayoutInflater.from(this.mContext).inflate(this.resource, (ViewGroup) null);
        this.dialog.setContentView(inflate);
        ViewGroup.LayoutParams layoutParams = inflate.getLayoutParams();
        layoutParams.width = this.mContext.getResources().getDisplayMetrics().widthPixels;
        inflate.setLayoutParams(layoutParams);
        this.dialog.getWindow().setGravity(17);
        this.dialog.getWindow().setWindowAnimations(C2128R.C2134style.MenuDialog_Animation);
        this.mCancelBtn = (TextView) inflate.findViewById(C2128R.C2131id.dialog_cancel);
        this.mOkBtn = (TextView) inflate.findViewById(C2128R.C2131id.dialog_ok);
    }

    private void initDialog() {
        this.dialog = new Dialog(this.mContext, C2128R.C2134style.MyDialog);
        View inflate = LayoutInflater.from(this.mContext).inflate(this.resource, (ViewGroup) null);
        this.dialog.setContentView(inflate);
        ViewGroup.LayoutParams layoutParams = inflate.getLayoutParams();
        layoutParams.width = this.mContext.getResources().getDisplayMetrics().widthPixels;
        inflate.setLayoutParams(layoutParams);
        this.dialog.getWindow().setGravity(17);
        this.dialog.getWindow().setWindowAnimations(C2128R.C2134style.MenuDialog_Animation);
        this.mTitleTv = (TextView) inflate.findViewById(C2128R.C2131id.dialog_title);
        this.mInfoTv = (TextView) inflate.findViewById(C2128R.C2131id.dialog_content);
        this.mCancelBtn = (TextView) inflate.findViewById(C2128R.C2131id.dialog_cancel);
        this.mOkBtn = (TextView) inflate.findViewById(C2128R.C2131id.dialog_ok);
        this.mInfoTv.setText(this.mContent.replace("\\n", "\n"));
        this.mInfoTv.setMovementMethod(ScrollingMovementMethod.getInstance());
    }

    public void setCancelable(boolean z) {
        this.isCancelable = z;
    }

    public void setCanCancelTouchOutside(boolean z) {
        this.isCanCancelTouchOutside = z;
    }

    public void isShowOkBtn(boolean z) {
        this.isShowOkBtn = z;
    }

    public void isShowCancelBtn(boolean z) {
        this.isShowCancelBtn = z;
    }

    public void setTitle(String str) {
        this.isShowTitle = true;
        this.mTitle = str;
    }

    public void setmOkBtn(String str, NoDoubleClickListener noDoubleClickListener) {
        this.mOkBtn.setText(str);
        this.mOkBtn.setOnClickListener(noDoubleClickListener);
    }

    public void setmCancelBtn(String str, NoDoubleClickListener noDoubleClickListener) {
        this.mCancelBtn.setText(str);
        this.mCancelBtn.setOnClickListener(noDoubleClickListener);
    }

    public void setmOkBtn(NoDoubleClickListener noDoubleClickListener) {
        this.mOkBtn.setOnClickListener(noDoubleClickListener);
    }

    public void setmCancelBtn(NoDoubleClickListener noDoubleClickListener) {
        this.mCancelBtn.setOnClickListener(noDoubleClickListener);
    }

    public void show() {
        Dialog dialog2 = this.dialog;
        if (dialog2 != null) {
            dialog2.setCancelable(this.isCancelable);
            this.dialog.setCanceledOnTouchOutside(this.isCanCancelTouchOutside);
            if (this.isShowTitle) {
                this.mTitleTv.setText(this.mTitle);
                this.mTitleTv.setVisibility(0);
            }
            if (!this.isShowOkBtn) {
                this.mOkBtn.setVisibility(8);
                this.mCancelBtn.setBackgroundResource(C2128R.C2130drawable.custom_dialog_only_ok);
            } else if (!this.isShowCancelBtn) {
                this.mCancelBtn.setVisibility(8);
                this.mOkBtn.setBackgroundResource(C2128R.C2130drawable.custom_dialog_only_ok);
            }
            this.dialog.show();
            return;
        }
        Toast.makeText(this.mContext, "Dialog creation failed", 0).show();
    }

    public void setOnKeyDownListener(DialogInterface.OnKeyListener onKeyListener) {
        this.dialog.setOnKeyListener(onKeyListener);
    }

    public boolean getIsCanable() {
        return this.isCancelable;
    }

    public void dismiss() {
        Dialog dialog2 = this.dialog;
        if (dialog2 != null && dialog2.isShowing()) {
            this.dialog.dismiss();
        }
    }
}
