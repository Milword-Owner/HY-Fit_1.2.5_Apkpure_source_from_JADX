package com.facebook.appevents;

import androidx.annotation.RestrictTo;
import com.facebook.FacebookSdk;
import com.facebook.appevents.aam.MetadataIndexer;
import com.facebook.appevents.eventdeactivation.EventDeactivationManager;
import com.facebook.appevents.p011ml.ModelManager;
import com.facebook.appevents.restrictivedatafilter.RestrictiveDataManager;
import com.facebook.internal.FeatureManager;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class AppEventsManager {
    public static void start() {
        if (FacebookSdk.getAutoLogAppEventsEnabled()) {
            FeatureManager.checkFeature(FeatureManager.Feature.AAM, new FeatureManager.Callback() {
                public void onCompleted(boolean z) {
                    if (z) {
                        MetadataIndexer.enable();
                    }
                }
            });
            FeatureManager.checkFeature(FeatureManager.Feature.RestrictiveDataFiltering, new FeatureManager.Callback() {
                public void onCompleted(boolean z) {
                    if (z) {
                        RestrictiveDataManager.enable();
                    }
                }
            });
            FeatureManager.checkFeature(FeatureManager.Feature.PrivacyProtection, new FeatureManager.Callback() {
                public void onCompleted(boolean z) {
                    if (z) {
                        ModelManager.enable();
                    }
                }
            });
            FeatureManager.checkFeature(FeatureManager.Feature.EventDeactivation, new FeatureManager.Callback() {
                public void onCompleted(boolean z) {
                    if (z) {
                        EventDeactivationManager.enable();
                    }
                }
            });
        }
    }
}
