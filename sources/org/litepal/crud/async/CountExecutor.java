package org.litepal.crud.async;

import org.litepal.crud.callback.CountCallback;

public class CountExecutor extends AsyncExecutor {

    /* renamed from: cb */
    private CountCallback f2792cb;

    public void listen(CountCallback countCallback) {
        this.f2792cb = countCallback;
        execute();
    }

    public CountCallback getListener() {
        return this.f2792cb;
    }
}
