package com.facebook.internal;

import androidx.annotation.RestrictTo;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.InputDeviceCompat;
import androidx.core.view.ViewCompat;
import com.facebook.FacebookSdk;
import com.facebook.internal.FetchedAppGateKeepersManager;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public final class FeatureManager {

    public interface Callback {
        void onCompleted(boolean z);
    }

    public static void checkFeature(final Feature feature, final Callback callback) {
        FetchedAppGateKeepersManager.loadAppGateKeepersAsync(new FetchedAppGateKeepersManager.Callback() {
            public void onCompleted() {
                callback.onCompleted(FeatureManager.isEnabled(feature));
            }
        });
    }

    public static boolean isEnabled(Feature feature) {
        if (Feature.Unknown == feature) {
            return false;
        }
        if (Feature.Core == feature) {
            return true;
        }
        Feature parent = feature.getParent();
        if (parent == feature) {
            return getGKStatus(feature);
        }
        if (!isEnabled(parent) || !getGKStatus(feature)) {
            return false;
        }
        return true;
    }

    private static boolean getGKStatus(Feature feature) {
        return FetchedAppGateKeepersManager.getGateKeeperForKey("FBSDKFeature" + feature.toString(), FacebookSdk.getApplicationId(), defaultStatus(feature));
    }

    private static boolean defaultStatus(Feature feature) {
        switch (feature) {
            case RestrictiveDataFiltering:
            case Instrument:
            case CrashReport:
            case ErrorReport:
            case AAM:
            case PrivacyProtection:
            case SuggestedEvents:
            case PIIFiltering:
            case EventDeactivation:
                return false;
            default:
                return true;
        }
    }

    public enum Feature {
        Unknown(-1),
        Core(0),
        AppEvents(65536),
        CodelessEvents(65792),
        RestrictiveDataFiltering(66048),
        AAM(66304),
        PrivacyProtection(66560),
        SuggestedEvents(66561),
        PIIFiltering(66562),
        EventDeactivation(66816),
        Instrument(131072),
        CrashReport(131328),
        ErrorReport(131584),
        Login(16777216),
        Share(33554432),
        Places(50331648);
        
        private final int code;

        private Feature(int i) {
            this.code = i;
        }

        public String toString() {
            switch (this) {
                case RestrictiveDataFiltering:
                    return "RestrictiveDataFiltering";
                case Instrument:
                    return "Instrument";
                case CrashReport:
                    return "CrashReport";
                case ErrorReport:
                    return "ErrorReport";
                case AAM:
                    return "AAM";
                case PrivacyProtection:
                    return "PrivacyProtection";
                case SuggestedEvents:
                    return "SuggestedEvents";
                case PIIFiltering:
                    return "PIIFiltering";
                case EventDeactivation:
                    return "EventDeactivation";
                case Core:
                    return "CoreKit";
                case AppEvents:
                    return "AppEvents";
                case CodelessEvents:
                    return "CodelessEvents";
                case Login:
                    return "LoginKit";
                case Share:
                    return "ShareKit";
                case Places:
                    return "PlacesKit";
                default:
                    return "unknown";
            }
        }

        static Feature fromInt(int i) {
            for (Feature feature : values()) {
                if (feature.code == i) {
                    return feature;
                }
            }
            return Unknown;
        }

        public Feature getParent() {
            int i = this.code;
            if ((i & 255) > 0) {
                return fromInt(i & InputDeviceCompat.SOURCE_ANY);
            }
            if ((65280 & i) > 0) {
                return fromInt(i & SupportMenu.CATEGORY_MASK);
            }
            if ((16711680 & i) > 0) {
                return fromInt(i & ViewCompat.MEASURED_STATE_MASK);
            }
            return fromInt(0);
        }
    }
}
