package p005cn.sharesdk.framework;

import com.mob.MobSDK;
import com.mob.OperationCallback;
import com.mob.RHolder;
import com.mob.commons.SHARESDK;
import com.mob.commons.dialog.entity.InternalPolicyUi;
import com.mob.tools.log.NLog;
import com.mob.tools.utils.ResHelper;
import p005cn.sharesdk.framework.utils.SSDKLog;
import p005cn.sharesdk.onekeyshare.OnekeyShare;

/* renamed from: cn.sharesdk.framework.ProvicyCanContinue */
public class ProvicyCanContinue {

    /* renamed from: a */
    private static volatile ProvicyCanContinue f151a;

    /* renamed from: cn.sharesdk.framework.ProvicyCanContinue$OnBusinessListener */
    public interface OnBusinessListener {
        void onContinue();

        void onError(Throwable th);

        void onStop();
    }

    private ProvicyCanContinue() {
        m99b();
    }

    /* renamed from: b */
    private void m99b() {
        RHolder.getInstance().setActivityThemeId(ResHelper.getStyleRes(MobSDK.getContext(), "mobcommon_TranslucentTheme")).setDialogThemeId(ResHelper.getStyleRes(MobSDK.getContext(), "mobcommon_DialogStyle")).setDialogLayoutId(ResHelper.getLayoutRes(MobSDK.getContext(), "mob_authorize_dialog"));
        SSDKLog.m645b().mo29768d(OnekeyShare.SHARESDK_TAG, "ProvicyCanContinue initMobCommonView()");
    }

    /* renamed from: a */
    public static ProvicyCanContinue m98a() {
        synchronized (ProvicyCanContinue.class) {
            if (f151a == null) {
                synchronized (ProvicyCanContinue.class) {
                    if (f151a == null) {
                        f151a = new ProvicyCanContinue();
                    }
                }
            }
        }
        return f151a;
    }

    /* renamed from: a */
    public void mo10464a(final OnBusinessListener onBusinessListener) {
        MobSDK.canIContinueBusiness(new SHARESDK(), new InternalPolicyUi.Builder().setTitleText(MobSDK.getContext().getResources().getString(ResHelper.getStringRes(MobSDK.getContext(), "mobcommon_authorize_dialog_title"))).setContentText(MobSDK.getContext().getResources().getString(ResHelper.getStringRes(MobSDK.getContext(), "mobcommon_authorize_dialog_content"))).build(), new OperationCallback<Boolean>() {
            public void onComplete(Boolean bool) {
                NLog b = SSDKLog.m645b();
                b.mo29768d(OnekeyShare.SHARESDK_TAG, "canIContinueBusiness: onComplete(), " + bool);
                if (bool.booleanValue()) {
                    OnBusinessListener onBusinessListener = onBusinessListener;
                    if (onBusinessListener != null) {
                        onBusinessListener.onContinue();
                    }
                    SSDKLog.m645b().mo29768d(OnekeyShare.SHARESDK_TAG, "MobSDK.canIContinueBusiness if ");
                    return;
                }
                OnBusinessListener onBusinessListener2 = onBusinessListener;
                if (onBusinessListener2 != null) {
                    onBusinessListener2.onStop();
                }
                SSDKLog.m645b().mo29768d(OnekeyShare.SHARESDK_TAG, "MobSDK.canIContinueBusiness else ");
            }

            public void onFailure(Throwable th) {
                NLog b = SSDKLog.m645b();
                b.mo29768d(OnekeyShare.SHARESDK_TAG, "canIContinueBusiness: onFailure() " + th);
                OnBusinessListener onBusinessListener = onBusinessListener;
                if (onBusinessListener != null) {
                    onBusinessListener.onError(th);
                }
            }
        });
    }
}
