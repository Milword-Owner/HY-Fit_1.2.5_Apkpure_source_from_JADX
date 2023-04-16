package com.google.android.gms.fitness.data;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.DeviceProperties;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public final class zzq {
    @Nullable
    private static String zzmw = null;
    private static int zzmx = -1;

    @SuppressLint({"HardwareIds"})
    public static String zza(Context context) {
        String str = zzmw;
        if (str != null) {
            return str;
        }
        String string = Settings.Secure.getString(context.getContentResolver(), "android_id");
        zzmw = string;
        return string;
    }

    public static int zzb(Context context) {
        if (zzmx == -1) {
            if (DeviceProperties.isWearable(context)) {
                zzmx = 3;
            } else {
                boolean z = false;
                if (DeviceProperties.isTv(context) || DeviceProperties.isAuto(context)) {
                    zzmx = 0;
                } else {
                    if (DeviceProperties.isTablet(context.getResources()) && !zzc(context)) {
                        zzmx = 2;
                    } else {
                        if (!TextUtils.isEmpty(Build.PRODUCT) && Build.PRODUCT.startsWith("glass_")) {
                            z = true;
                        }
                        if (z) {
                            zzmx = 6;
                        } else {
                            zzmx = 1;
                        }
                    }
                }
            }
        }
        return zzmx;
    }

    private static boolean zzc(Context context) {
        try {
            if (((TelephonyManager) Preconditions.checkNotNull((TelephonyManager) context.getSystemService("phone"))).getPhoneType() != 0) {
                return true;
            }
            return false;
        } catch (Resources.NotFoundException e) {
            Log.e("Fitness", "Unable to determine type of device, assuming phone.", e);
            return true;
        }
    }
}
