package kotlin.concurrent;

import java.util.TimerTask;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, mo21895d2 = {"kotlin/concurrent/TimersKt$timerTask$1", "Ljava/util/TimerTask;", "run", "", "kotlin-stdlib"}, mo21896k = 1, mo21897mv = {1, 1, 16})
/* compiled from: Timer.kt */
public final class TimersKt$timerTask$1 extends TimerTask {
    final /* synthetic */ Function1 $action;

    public TimersKt$timerTask$1(Function1 function1) {
        this.$action = function1;
    }

    public void run() {
        this.$action.invoke(this);
    }
}
