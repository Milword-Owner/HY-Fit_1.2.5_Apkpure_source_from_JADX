package com.google.android.gms.fitness.data;

import androidx.annotation.Nullable;
import com.facebook.appevents.internal.ViewHierarchyConstants;
import com.github.mikephil.charting.utils.Utils;
import com.google.android.gms.common.internal.ShowFirstParty;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@ShowFirstParty
/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public final class zzah {
    private static final double zzoi;
    private static final double zzoj;
    private static final double zzok;
    private static final double zzol;
    public static final Set<String> zzom = Collections.unmodifiableSet(new HashSet(Arrays.asList(new String[]{"altitude", "duration", "food_item", "meal_type", "repetitions", "resistance", "resistance_type"})));
    private static final zzah zzop = new zzah();
    private final Map<String, Map<String, zzaj>> zzon;
    private final Map<String, zzaj> zzoo;

    private zzah() {
        HashMap hashMap = new HashMap();
        hashMap.put("latitude", new zzaj(-90.0d, 90.0d));
        hashMap.put("longitude", new zzaj(-180.0d, 180.0d));
        hashMap.put("accuracy", new zzaj(Utils.DOUBLE_EPSILON, 10000.0d));
        hashMap.put("bpm", new zzaj(Utils.DOUBLE_EPSILON, 1000.0d));
        hashMap.put("altitude", new zzaj(-100000.0d, 100000.0d));
        hashMap.put("percentage", new zzaj(Utils.DOUBLE_EPSILON, 100.0d));
        hashMap.put("confidence", new zzaj(Utils.DOUBLE_EPSILON, 100.0d));
        hashMap.put("duration", new zzaj(Utils.DOUBLE_EPSILON, 9.223372036854776E18d));
        hashMap.put(ViewHierarchyConstants.DIMENSION_HEIGHT_KEY, new zzaj(Utils.DOUBLE_EPSILON, 3.0d));
        hashMap.put("weight", new zzaj(Utils.DOUBLE_EPSILON, 1000.0d));
        hashMap.put("speed", new zzaj(Utils.DOUBLE_EPSILON, 11000.0d));
        this.zzoo = Collections.unmodifiableMap(hashMap);
        HashMap hashMap2 = new HashMap();
        hashMap2.put("com.google.step_count.delta", zza("steps", new zzaj(Utils.DOUBLE_EPSILON, zzoi)));
        hashMap2.put("com.google.calories.consumed", zza(Field.NUTRIENT_CALORIES, new zzaj(Utils.DOUBLE_EPSILON, zzoj)));
        hashMap2.put("com.google.calories.expended", zza(Field.NUTRIENT_CALORIES, new zzaj(Utils.DOUBLE_EPSILON, zzok)));
        hashMap2.put("com.google.distance.delta", zza("distance", new zzaj(Utils.DOUBLE_EPSILON, zzol)));
        this.zzon = Collections.unmodifiableMap(hashMap2);
    }

    private static <K, V> Map<K, V> zza(K k, V v) {
        HashMap hashMap = new HashMap();
        hashMap.put(k, v);
        return hashMap;
    }

    @Nullable
    public final zzaj zzi(String str) {
        return this.zzoo.get(str);
    }

    @Nullable
    public final zzaj zza(String str, String str2) {
        Map map = this.zzon.get(str);
        if (map != null) {
            return (zzaj) map.get(str2);
        }
        return null;
    }

    public static zzah zzt() {
        return zzop;
    }

    static {
        double nanos = (double) TimeUnit.SECONDS.toNanos(1);
        Double.isNaN(nanos);
        zzoi = 10.0d / nanos;
        double nanos2 = (double) TimeUnit.SECONDS.toNanos(1);
        Double.isNaN(nanos2);
        zzoj = 1000.0d / nanos2;
        double nanos3 = (double) TimeUnit.HOURS.toNanos(1);
        Double.isNaN(nanos3);
        zzok = 2000.0d / nanos3;
        double nanos4 = (double) TimeUnit.SECONDS.toNanos(1);
        Double.isNaN(nanos4);
        zzol = 100.0d / nanos4;
    }
}
