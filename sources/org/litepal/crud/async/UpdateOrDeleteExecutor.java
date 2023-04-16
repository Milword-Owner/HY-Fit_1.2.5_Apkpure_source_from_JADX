package org.litepal.crud.async;

import org.litepal.crud.callback.UpdateOrDeleteCallback;

public class UpdateOrDeleteExecutor extends AsyncExecutor {

    /* renamed from: cb */
    private UpdateOrDeleteCallback f2796cb;

    public void listen(UpdateOrDeleteCallback updateOrDeleteCallback) {
        this.f2796cb = updateOrDeleteCallback;
        execute();
    }

    public UpdateOrDeleteCallback getListener() {
        return this.f2796cb;
    }
}
