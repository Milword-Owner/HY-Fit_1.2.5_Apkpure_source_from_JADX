package org.litepal.crud.async;

import org.litepal.crud.callback.AverageCallback;

public class AverageExecutor extends AsyncExecutor {

    /* renamed from: cb */
    private AverageCallback f2791cb;

    public void listen(AverageCallback averageCallback) {
        this.f2791cb = averageCallback;
        execute();
    }

    public AverageCallback getListener() {
        return this.f2791cb;
    }
}
