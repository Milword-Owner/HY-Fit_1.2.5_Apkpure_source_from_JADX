package kotlin.time;

import java.util.concurrent.TimeUnit;
import kotlin.Metadata;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"kotlin/time/DurationUnitKt__DurationUnitJvmKt", "kotlin/time/DurationUnitKt__DurationUnitKt"}, mo21896k = 4, mo21897mv = {1, 1, 16}, mo21899xi = 1)
public final class DurationUnitKt extends DurationUnitKt__DurationUnitKt {

    @Metadata(mo21893bv = {1, 0, 3}, mo21896k = 3, mo21897mv = {1, 1, 16})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[TimeUnit.values().length];

        static {
            $EnumSwitchMapping$0[TimeUnit.NANOSECONDS.ordinal()] = 1;
            $EnumSwitchMapping$0[TimeUnit.MICROSECONDS.ordinal()] = 2;
            $EnumSwitchMapping$0[TimeUnit.MILLISECONDS.ordinal()] = 3;
            $EnumSwitchMapping$0[TimeUnit.SECONDS.ordinal()] = 4;
            $EnumSwitchMapping$0[TimeUnit.MINUTES.ordinal()] = 5;
            $EnumSwitchMapping$0[TimeUnit.HOURS.ordinal()] = 6;
            $EnumSwitchMapping$0[TimeUnit.DAYS.ordinal()] = 7;
        }
    }

    private DurationUnitKt() {
    }
}
