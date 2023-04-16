package com.baidu.mobstat;

import android.content.Context;
import android.text.TextUtils;
import com.facebook.internal.ServerProtocol;

public class PrefOperate {
    public static void loadMetaDataConfig(Context context) {
        SendStrategyEnum sendStrategyEnum = SendStrategyEnum.APP_START;
        try {
            String a = C0991bw.m1434a(context, Config.EXCEPTION_LOG_META_NAME);
            if (!TextUtils.isEmpty(a) && ServerProtocol.DIALOG_RETURN_SCOPES_TRUE.equals(a)) {
                ExceptionAnalysis.getInstance().openExceptionAnalysis(context, false);
            }
        } catch (Exception unused) {
        }
        try {
            String a2 = C0991bw.m1434a(context, Config.SEND_STRATEGY_META_NAME);
            if (!TextUtils.isEmpty(a2)) {
                if (a2.equals(SendStrategyEnum.APP_START.name())) {
                    sendStrategyEnum = SendStrategyEnum.APP_START;
                    C0982bp.m1357a().mo11674a(context, sendStrategyEnum.ordinal());
                } else if (a2.equals(SendStrategyEnum.ONCE_A_DAY.name())) {
                    sendStrategyEnum = SendStrategyEnum.ONCE_A_DAY;
                    C0982bp.m1357a().mo11674a(context, sendStrategyEnum.ordinal());
                    C0982bp.m1357a().mo11679b(context, 24);
                } else if (a2.equals(SendStrategyEnum.SET_TIME_INTERVAL.name())) {
                    sendStrategyEnum = SendStrategyEnum.SET_TIME_INTERVAL;
                    C0982bp.m1357a().mo11674a(context, sendStrategyEnum.ordinal());
                }
            }
        } catch (Exception unused2) {
        }
        try {
            String a3 = C0991bw.m1434a(context, Config.TIME_INTERVAL_META_NAME);
            if (!TextUtils.isEmpty(a3)) {
                int parseInt = Integer.parseInt(a3);
                if (sendStrategyEnum.ordinal() == SendStrategyEnum.SET_TIME_INTERVAL.ordinal() && parseInt > 0 && parseInt <= 24) {
                    C0982bp.m1357a().mo11679b(context, parseInt);
                }
            }
        } catch (Exception unused3) {
        }
        try {
            String a4 = C0991bw.m1434a(context, Config.ONLY_WIFI_META_NAME);
            if (TextUtils.isEmpty(a4)) {
                return;
            }
            if (ServerProtocol.DIALOG_RETURN_SCOPES_TRUE.equals(a4)) {
                C0982bp.m1357a().mo11677a(context, true);
            } else if ("false".equals(a4)) {
                C0982bp.m1357a().mo11677a(context, false);
            }
        } catch (Exception unused4) {
        }
    }

    public static void setAppKey(String str) {
        CooperService.instance().getHeadObject().f833e = str;
    }

    public static String getAppKey(Context context) {
        return CooperService.instance().getAppKey(context);
    }

    public static void setAppChannel(String str) {
        if (str == null || str.equals("")) {
            C0955bb.m1194c().mo11631c("[WARNING] The channel you have set is empty");
        }
        CooperService.instance().getHeadObject().f840l = str;
    }

    public static void setAppChannel(Context context, String str, boolean z) {
        if (str == null || str.equals("")) {
            C0955bb.m1194c().mo11631c("[WARNING] The channel you have set is empty");
        }
        CooperService.instance().getHeadObject().f840l = str;
        if (z && str != null && !str.equals("")) {
            C0982bp.m1357a().mo11686d(context, str);
            C0982bp.m1357a().mo11682b(context, true);
        }
        if (!z) {
            C0982bp.m1357a().mo11686d(context, "");
            C0982bp.m1357a().mo11682b(context, false);
        }
    }
}
