package p015io.reactivex.internal.schedulers;

import p015io.reactivex.Scheduler;
import p015io.reactivex.annotations.Experimental;
import p015io.reactivex.annotations.NonNull;

@Experimental
/* renamed from: io.reactivex.internal.schedulers.SchedulerMultiWorkerSupport */
public interface SchedulerMultiWorkerSupport {

    /* renamed from: io.reactivex.internal.schedulers.SchedulerMultiWorkerSupport$WorkerCallback */
    public interface WorkerCallback {
        void onWorker(int i, @NonNull Scheduler.Worker worker);
    }

    void createWorkers(int i, @NonNull WorkerCallback workerCallback);
}
