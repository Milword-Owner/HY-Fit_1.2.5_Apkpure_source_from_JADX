package org.litepal.crud.async;

public abstract class AsyncExecutor {
    private Runnable pendingTask;

    public void submit(Runnable runnable) {
        this.pendingTask = runnable;
    }

    /* access modifiers changed from: package-private */
    public void execute() {
        Runnable runnable = this.pendingTask;
        if (runnable != null) {
            new Thread(runnable).start();
        }
    }
}
