package com.facebook.appevents.eventdeactivation;

import androidx.annotation.RestrictTo;
import com.facebook.appevents.AppEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public final class EventDeactivationManager {
    private static final Set<String> deprecatedEvents = new HashSet();
    private static final List<DeprecatedParamFilter> deprecatedParamFilters = new ArrayList();
    private static boolean enabled = false;

    public static void enable() {
        enabled = true;
        initialize();
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static synchronized void initialize() {
        /*
            java.lang.Class<com.facebook.appevents.eventdeactivation.EventDeactivationManager> r0 = com.facebook.appevents.eventdeactivation.EventDeactivationManager.class
            monitor-enter(r0)
            java.lang.String r1 = com.facebook.FacebookSdk.getApplicationId()     // Catch:{ Exception -> 0x0069, all -> 0x0066 }
            r2 = 0
            com.facebook.internal.FetchedAppSettings r1 = com.facebook.internal.FetchedAppSettingsManager.queryAppSettings(r1, r2)     // Catch:{ Exception -> 0x0069, all -> 0x0066 }
            if (r1 != 0) goto L_0x0010
            monitor-exit(r0)
            return
        L_0x0010:
            java.lang.String r1 = r1.getRestrictiveDataSetting()     // Catch:{ Exception -> 0x0069, all -> 0x0066 }
            boolean r2 = r1.isEmpty()     // Catch:{ Exception -> 0x0069, all -> 0x0066 }
            if (r2 != 0) goto L_0x0069
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch:{ Exception -> 0x0069, all -> 0x0066 }
            r2.<init>(r1)     // Catch:{ Exception -> 0x0069, all -> 0x0066 }
            java.util.List<com.facebook.appevents.eventdeactivation.EventDeactivationManager$DeprecatedParamFilter> r1 = deprecatedParamFilters     // Catch:{ Exception -> 0x0069, all -> 0x0066 }
            r1.clear()     // Catch:{ Exception -> 0x0069, all -> 0x0066 }
            java.util.Iterator r1 = r2.keys()     // Catch:{ Exception -> 0x0069, all -> 0x0066 }
        L_0x0028:
            boolean r3 = r1.hasNext()     // Catch:{ Exception -> 0x0069, all -> 0x0066 }
            if (r3 == 0) goto L_0x0069
            java.lang.Object r3 = r1.next()     // Catch:{ Exception -> 0x0069, all -> 0x0066 }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ Exception -> 0x0069, all -> 0x0066 }
            org.json.JSONObject r4 = r2.getJSONObject(r3)     // Catch:{ Exception -> 0x0069, all -> 0x0066 }
            if (r4 == 0) goto L_0x0028
            java.lang.String r5 = "is_deprecated_event"
            boolean r5 = r4.optBoolean(r5)     // Catch:{ Exception -> 0x0069, all -> 0x0066 }
            if (r5 == 0) goto L_0x0048
            java.util.Set<java.lang.String> r4 = deprecatedEvents     // Catch:{ Exception -> 0x0069, all -> 0x0066 }
            r4.add(r3)     // Catch:{ Exception -> 0x0069, all -> 0x0066 }
            goto L_0x0028
        L_0x0048:
            java.lang.String r5 = "deprecated_param"
            org.json.JSONArray r4 = r4.optJSONArray(r5)     // Catch:{ Exception -> 0x0069, all -> 0x0066 }
            com.facebook.appevents.eventdeactivation.EventDeactivationManager$DeprecatedParamFilter r5 = new com.facebook.appevents.eventdeactivation.EventDeactivationManager$DeprecatedParamFilter     // Catch:{ Exception -> 0x0069, all -> 0x0066 }
            java.util.ArrayList r6 = new java.util.ArrayList     // Catch:{ Exception -> 0x0069, all -> 0x0066 }
            r6.<init>()     // Catch:{ Exception -> 0x0069, all -> 0x0066 }
            r5.<init>(r3, r6)     // Catch:{ Exception -> 0x0069, all -> 0x0066 }
            if (r4 == 0) goto L_0x0060
            java.util.List r3 = com.facebook.internal.Utility.convertJSONArrayToList(r4)     // Catch:{ Exception -> 0x0069, all -> 0x0066 }
            r5.deprecateParams = r3     // Catch:{ Exception -> 0x0069, all -> 0x0066 }
        L_0x0060:
            java.util.List<com.facebook.appevents.eventdeactivation.EventDeactivationManager$DeprecatedParamFilter> r3 = deprecatedParamFilters     // Catch:{ Exception -> 0x0069, all -> 0x0066 }
            r3.add(r5)     // Catch:{ Exception -> 0x0069, all -> 0x0066 }
            goto L_0x0028
        L_0x0066:
            r1 = move-exception
            monitor-exit(r0)
            throw r1
        L_0x0069:
            monitor-exit(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.appevents.eventdeactivation.EventDeactivationManager.initialize():void");
    }

    public static void processEvents(List<AppEvent> list) {
        if (enabled) {
            Iterator<AppEvent> it = list.iterator();
            while (it.hasNext()) {
                if (deprecatedEvents.contains(it.next().getName())) {
                    it.remove();
                }
            }
        }
    }

    public static void processDeprecatedParameters(Map<String, String> map, String str) {
        if (enabled) {
            ArrayList<String> arrayList = new ArrayList<>(map.keySet());
            for (DeprecatedParamFilter deprecatedParamFilter : new ArrayList(deprecatedParamFilters)) {
                if (deprecatedParamFilter.eventName.equals(str)) {
                    for (String str2 : arrayList) {
                        if (deprecatedParamFilter.deprecateParams.contains(str2)) {
                            map.remove(str2);
                        }
                    }
                }
            }
        }
    }

    static class DeprecatedParamFilter {
        List<String> deprecateParams;
        String eventName;

        DeprecatedParamFilter(String str, List<String> list) {
            this.eventName = str;
            this.deprecateParams = list;
        }
    }
}
