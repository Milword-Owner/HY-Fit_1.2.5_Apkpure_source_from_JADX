package com.bigkoo.pickerview.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import com.bigkoo.pickerview.C1097R;
import com.bigkoo.pickerview.configure.PickerOptions;
import com.bigkoo.pickerview.listener.OnDismissListener;
import com.bigkoo.pickerview.utils.PickerViewAnimateUtil;

public class BasePickerView {
    protected int animGravity = 80;
    protected View clickView;
    protected ViewGroup contentContainer;
    private Context context;
    private ViewGroup dialogView;
    /* access modifiers changed from: private */
    public boolean dismissing;
    private Animation inAnim;
    private boolean isAnim = true;
    /* access modifiers changed from: private */
    public boolean isShowing;
    private Dialog mDialog;
    protected PickerOptions mPickerOptions;
    private final View.OnTouchListener onCancelableTouchListener = new View.OnTouchListener() {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() != 0) {
                return false;
            }
            BasePickerView.this.dismiss();
            return false;
        }
    };
    /* access modifiers changed from: private */
    public OnDismissListener onDismissListener;
    private View.OnKeyListener onKeyBackListener = new View.OnKeyListener() {
        public boolean onKey(View view, int i, KeyEvent keyEvent) {
            if (i != 4 || keyEvent.getAction() != 0 || !BasePickerView.this.isShowing()) {
                return false;
            }
            BasePickerView.this.dismiss();
            return true;
        }
    };
    private Animation outAnim;
    /* access modifiers changed from: private */
    public ViewGroup rootView;

    /* access modifiers changed from: protected */
    public void initEvents() {
    }

    public boolean isDialog() {
        return false;
    }

    public BasePickerView(Context context2) {
        this.context = context2;
    }

    /* access modifiers changed from: protected */
    public void initViews() {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2, 80);
        LayoutInflater from = LayoutInflater.from(this.context);
        if (isDialog()) {
            this.dialogView = (ViewGroup) from.inflate(C1097R.C1101layout.layout_basepickerview, (ViewGroup) null, false);
            this.dialogView.setBackgroundColor(0);
            this.contentContainer = (ViewGroup) this.dialogView.findViewById(C1097R.C1100id.content_container);
            layoutParams.leftMargin = 30;
            layoutParams.rightMargin = 30;
            this.contentContainer.setLayoutParams(layoutParams);
            createDialog();
            this.dialogView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    BasePickerView.this.dismiss();
                }
            });
        } else {
            if (this.mPickerOptions.decorView == null) {
                this.mPickerOptions.decorView = (ViewGroup) ((Activity) this.context).getWindow().getDecorView();
            }
            this.rootView = (ViewGroup) from.inflate(C1097R.C1101layout.layout_basepickerview, this.mPickerOptions.decorView, false);
            this.rootView.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
            if (this.mPickerOptions.outSideColor != -1) {
                this.rootView.setBackgroundColor(this.mPickerOptions.outSideColor);
            }
            this.contentContainer = (ViewGroup) this.rootView.findViewById(C1097R.C1100id.content_container);
            this.contentContainer.setLayoutParams(layoutParams);
        }
        setKeyBackCancelable(true);
    }

    /* access modifiers changed from: protected */
    public void initAnim() {
        this.inAnim = getInAnimation();
        this.outAnim = getOutAnimation();
    }

    public void show(View view, boolean z) {
        this.clickView = view;
        this.isAnim = z;
        show();
    }

    public void show(boolean z) {
        show((View) null, z);
    }

    public void show(View view) {
        this.clickView = view;
        show();
    }

    public void show() {
        if (isDialog()) {
            showDialog();
        } else if (!isShowing()) {
            this.isShowing = true;
            onAttached(this.rootView);
            this.rootView.requestFocus();
        }
    }

    private void onAttached(View view) {
        this.mPickerOptions.decorView.addView(view);
        if (this.isAnim) {
            this.contentContainer.startAnimation(this.inAnim);
        }
    }

    public boolean isShowing() {
        if (isDialog()) {
            return false;
        }
        if (this.rootView.getParent() != null || this.isShowing) {
            return true;
        }
        return false;
    }

    public void dismiss() {
        if (isDialog()) {
            dismissDialog();
        } else if (!this.dismissing) {
            if (this.isAnim) {
                this.outAnim.setAnimationListener(new Animation.AnimationListener() {
                    public void onAnimationRepeat(Animation animation) {
                    }

                    public void onAnimationStart(Animation animation) {
                    }

                    public void onAnimationEnd(Animation animation) {
                        BasePickerView.this.dismissImmediately();
                    }
                });
                this.contentContainer.startAnimation(this.outAnim);
            } else {
                dismissImmediately();
            }
            this.dismissing = true;
        }
    }

    public void dismissImmediately() {
        this.mPickerOptions.decorView.post(new Runnable() {
            public void run() {
                BasePickerView.this.mPickerOptions.decorView.removeView(BasePickerView.this.rootView);
                boolean unused = BasePickerView.this.isShowing = false;
                boolean unused2 = BasePickerView.this.dismissing = false;
                if (BasePickerView.this.onDismissListener != null) {
                    BasePickerView.this.onDismissListener.onDismiss(BasePickerView.this);
                }
            }
        });
    }

    private Animation getInAnimation() {
        return AnimationUtils.loadAnimation(this.context, PickerViewAnimateUtil.getAnimationResource(this.animGravity, true));
    }

    private Animation getOutAnimation() {
        return AnimationUtils.loadAnimation(this.context, PickerViewAnimateUtil.getAnimationResource(this.animGravity, false));
    }

    public BasePickerView setOnDismissListener(OnDismissListener onDismissListener2) {
        this.onDismissListener = onDismissListener2;
        return this;
    }

    public void setKeyBackCancelable(boolean z) {
        ViewGroup viewGroup;
        if (isDialog()) {
            viewGroup = this.dialogView;
        } else {
            viewGroup = this.rootView;
        }
        viewGroup.setFocusable(z);
        viewGroup.setFocusableInTouchMode(z);
        if (z) {
            viewGroup.setOnKeyListener(this.onKeyBackListener);
        } else {
            viewGroup.setOnKeyListener((View.OnKeyListener) null);
        }
    }

    /* access modifiers changed from: protected */
    public BasePickerView setOutSideCancelable(boolean z) {
        ViewGroup viewGroup = this.rootView;
        if (viewGroup != null) {
            View findViewById = viewGroup.findViewById(C1097R.C1100id.outmost_container);
            if (z) {
                findViewById.setOnTouchListener(this.onCancelableTouchListener);
            } else {
                findViewById.setOnTouchListener((View.OnTouchListener) null);
            }
        }
        return this;
    }

    public void setDialogOutSideCancelable() {
        Dialog dialog = this.mDialog;
        if (dialog != null) {
            dialog.setCancelable(this.mPickerOptions.cancelable);
        }
    }

    public View findViewById(int i) {
        return this.contentContainer.findViewById(i);
    }

    public void createDialog() {
        if (this.dialogView != null) {
            this.mDialog = new Dialog(this.context, C1097R.C1102style.custom_dialog2);
            this.mDialog.setCancelable(this.mPickerOptions.cancelable);
            this.mDialog.setContentView(this.dialogView);
            Window window = this.mDialog.getWindow();
            if (window != null) {
                window.setWindowAnimations(C1097R.C1102style.picker_view_scale_anim);
                window.setGravity(17);
            }
            this.mDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                public void onDismiss(DialogInterface dialogInterface) {
                    if (BasePickerView.this.onDismissListener != null) {
                        BasePickerView.this.onDismissListener.onDismiss(BasePickerView.this);
                    }
                }
            });
        }
    }

    private void showDialog() {
        Dialog dialog = this.mDialog;
        if (dialog != null) {
            dialog.show();
        }
    }

    private void dismissDialog() {
        Dialog dialog = this.mDialog;
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    public ViewGroup getDialogContainerLayout() {
        return this.contentContainer;
    }

    public Dialog getDialog() {
        return this.mDialog;
    }
}
