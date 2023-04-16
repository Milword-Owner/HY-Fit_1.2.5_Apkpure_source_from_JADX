package p005cn.sharesdk.framework;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import com.mob.tools.FakeActivity;
import com.mob.tools.utils.ResHelper;
import p005cn.sharesdk.framework.p007b.p008a.C0713e;

/* renamed from: cn.sharesdk.framework.AgreementDialog */
public class AgreementDialog extends FakeActivity {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public Dialog f142a;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public OnDialogDismiss f143b;

    /* renamed from: cn.sharesdk.framework.AgreementDialog$OnDialogDismiss */
    interface OnDialogDismiss {
        void consent();

        void refuse();
    }

    public void setActivity(final Activity activity) {
        super.setActivity(activity);
        try {
            this.f142a = new Dialog(activity, ResHelper.getStyleRes(activity, "mobcommon_DialogStyle"));
            View inflate = LayoutInflater.from(activity).inflate(ResHelper.getLayoutRes(activity, "sharesdk_agreement_dialog"), (ViewGroup) null);
            this.f142a.setCanceledOnTouchOutside(false);
            this.f142a.setContentView(inflate);
            Window window = this.f142a.getWindow();
            WindowManager.LayoutParams attributes = window.getAttributes();
            window.setGravity(17);
            window.setAttributes(attributes);
            this.f142a.setCancelable(true);
            inflate.findViewById(ResHelper.getIdRes(activity, "sharesdk_agreement_dialog_reject_tv")).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    AgreementDialog.this.m91a();
                }
            });
            inflate.findViewById(ResHelper.getIdRes(activity, "sharesdk_agreement_dialog_accept_tv")).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (AgreementDialog.this.f142a != null && AgreementDialog.this.f142a.isShowing()) {
                        AgreementDialog.this.f142a.dismiss();
                        if (AgreementDialog.this.f143b != null) {
                            AgreementDialog.this.f143b.consent();
                        }
                        C0713e.m196a().mo10586a(true);
                    }
                    activity.finish();
                }
            });
            this.f142a.setOnKeyListener(new DialogInterface.OnKeyListener() {
                public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                    if (i != 4) {
                        return false;
                    }
                    AgreementDialog.this.m91a();
                    return false;
                }
            });
            this.f142a.show();
        } catch (Throwable unused) {
        }
    }

    /* renamed from: a */
    public void mo10242a(OnDialogDismiss onDialogDismiss) {
        this.f143b = onDialogDismiss;
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m91a() {
        Dialog dialog = this.f142a;
        if (dialog != null && dialog.isShowing()) {
            this.f142a.dismiss();
            OnDialogDismiss onDialogDismiss = this.f143b;
            if (onDialogDismiss != null) {
                onDialogDismiss.refuse();
            }
        }
        this.activity.finish();
    }
}
