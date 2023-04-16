package com.huayu.tzc.customview;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.LayoutRes;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;
import com.huayu.tzc.C2128R;
import com.huayu.tzc.adapter.ViewPagerAdapter;
import java.util.ArrayList;
import java.util.List;

public class StepDialog {
    private Dialog dialog;
    private ImageView mCancel;
    /* access modifiers changed from: private */
    public final Context mContext;
    private WrapContentViewPager mViewPager;
    @LayoutRes
    private int resource = C2128R.C2133layout.dialog_step;
    /* access modifiers changed from: private */
    public View step1;
    /* access modifiers changed from: private */
    public View step2;
    /* access modifiers changed from: private */
    public View step3;
    /* access modifiers changed from: private */
    public View step4;
    private List<View> viewList = new ArrayList();

    public StepDialog(Context context) {
        this.mContext = context;
        initDialog();
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
        this.mCancel = (ImageView) inflate.findViewById(C2128R.C2131id.stepCancel);
        this.mViewPager = (WrapContentViewPager) inflate.findViewById(C2128R.C2131id.stepViewPager);
        this.step1 = inflate.findViewById(C2128R.C2131id.step1);
        this.step2 = inflate.findViewById(C2128R.C2131id.step2);
        this.step3 = inflate.findViewById(C2128R.C2131id.step3);
        this.step4 = inflate.findViewById(C2128R.C2131id.step4);
        this.mCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                StepDialog.this.dismiss();
            }
        });
        View inflate2 = View.inflate(this.mContext, C2128R.C2133layout.layout_step, (ViewGroup) null);
        ((TextView) inflate2.findViewById(C2128R.C2131id.stepTitle)).setText(C2128R.string.step1);
        ((TextView) inflate2.findViewById(C2128R.C2131id.stepContent)).setText(C2128R.string.step1_content);
        ((ImageView) inflate2.findViewById(C2128R.C2131id.stepImg)).setImageResource(C2128R.C2130drawable.ic_step1);
        this.viewList.add(inflate2);
        View inflate3 = View.inflate(this.mContext, C2128R.C2133layout.layout_step, (ViewGroup) null);
        ((TextView) inflate3.findViewById(C2128R.C2131id.stepTitle)).setText(C2128R.string.step2);
        ((TextView) inflate3.findViewById(C2128R.C2131id.stepContent)).setText(C2128R.string.step2_content);
        ((ImageView) inflate3.findViewById(C2128R.C2131id.stepImg)).setImageResource(C2128R.C2130drawable.ic_step2);
        this.viewList.add(inflate3);
        View inflate4 = View.inflate(this.mContext, C2128R.C2133layout.layout_step, (ViewGroup) null);
        ((TextView) inflate4.findViewById(C2128R.C2131id.stepTitle)).setText(C2128R.string.step3);
        ((TextView) inflate4.findViewById(C2128R.C2131id.stepContent)).setText(C2128R.string.step3_content);
        ((ImageView) inflate4.findViewById(C2128R.C2131id.stepImg)).setImageResource(C2128R.C2130drawable.ic_step3);
        this.viewList.add(inflate4);
        View inflate5 = View.inflate(this.mContext, C2128R.C2133layout.layout_step, (ViewGroup) null);
        ((TextView) inflate5.findViewById(C2128R.C2131id.stepTitle)).setText(C2128R.string.step4);
        ((TextView) inflate5.findViewById(C2128R.C2131id.stepContent)).setText(C2128R.string.step4_content);
        ((ImageView) inflate5.findViewById(C2128R.C2131id.stepImg)).setImageResource(C2128R.C2130drawable.ic_step4);
        this.viewList.add(inflate5);
        this.mViewPager.setOffscreenPageLimit(4);
        this.mViewPager.setAdapter(new ViewPagerAdapter(this.viewList));
        this.mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int i) {
            }

            public void onPageScrolled(int i, float f, int i2) {
            }

            public void onPageSelected(int i) {
                if (i == 0) {
                    StepDialog.this.step1.setBackground(ContextCompat.getDrawable(StepDialog.this.mContext, C2128R.C2130drawable.oval_5));
                    StepDialog.this.step2.setBackground(ContextCompat.getDrawable(StepDialog.this.mContext, C2128R.C2130drawable.oval_5_f));
                    StepDialog.this.step3.setBackground(ContextCompat.getDrawable(StepDialog.this.mContext, C2128R.C2130drawable.oval_5_f));
                    StepDialog.this.step4.setBackground(ContextCompat.getDrawable(StepDialog.this.mContext, C2128R.C2130drawable.oval_5_f));
                } else if (i == 1) {
                    StepDialog.this.step1.setBackground(ContextCompat.getDrawable(StepDialog.this.mContext, C2128R.C2130drawable.oval_5_f));
                    StepDialog.this.step2.setBackground(ContextCompat.getDrawable(StepDialog.this.mContext, C2128R.C2130drawable.oval_5));
                    StepDialog.this.step3.setBackground(ContextCompat.getDrawable(StepDialog.this.mContext, C2128R.C2130drawable.oval_5_f));
                    StepDialog.this.step4.setBackground(ContextCompat.getDrawable(StepDialog.this.mContext, C2128R.C2130drawable.oval_5_f));
                } else if (i == 2) {
                    StepDialog.this.step1.setBackground(ContextCompat.getDrawable(StepDialog.this.mContext, C2128R.C2130drawable.oval_5_f));
                    StepDialog.this.step2.setBackground(ContextCompat.getDrawable(StepDialog.this.mContext, C2128R.C2130drawable.oval_5_f));
                    StepDialog.this.step3.setBackground(ContextCompat.getDrawable(StepDialog.this.mContext, C2128R.C2130drawable.oval_5));
                    StepDialog.this.step4.setBackground(ContextCompat.getDrawable(StepDialog.this.mContext, C2128R.C2130drawable.oval_5_f));
                } else if (i == 3) {
                    StepDialog.this.step1.setBackground(ContextCompat.getDrawable(StepDialog.this.mContext, C2128R.C2130drawable.oval_5_f));
                    StepDialog.this.step2.setBackground(ContextCompat.getDrawable(StepDialog.this.mContext, C2128R.C2130drawable.oval_5_f));
                    StepDialog.this.step3.setBackground(ContextCompat.getDrawable(StepDialog.this.mContext, C2128R.C2130drawable.oval_5_f));
                    StepDialog.this.step4.setBackground(ContextCompat.getDrawable(StepDialog.this.mContext, C2128R.C2130drawable.oval_5));
                }
            }
        });
    }

    public void show() {
        Dialog dialog2 = this.dialog;
        if (dialog2 != null) {
            dialog2.show();
        } else {
            Toast.makeText(this.mContext, "Dialog creation failed", 0).show();
        }
    }

    public void setOnKeyDownListener(DialogInterface.OnKeyListener onKeyListener) {
        this.dialog.setOnKeyListener(onKeyListener);
    }

    public void dismiss() {
        Dialog dialog2 = this.dialog;
        if (dialog2 != null && dialog2.isShowing()) {
            this.dialog.dismiss();
        }
    }
}
