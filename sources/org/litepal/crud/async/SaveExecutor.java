package org.litepal.crud.async;

import org.litepal.crud.callback.SaveCallback;

public class SaveExecutor extends AsyncExecutor {

    /* renamed from: cb */
    private SaveCallback f2795cb;

    public void listen(SaveCallback saveCallback) {
        this.f2795cb = saveCallback;
        execute();
    }

    public SaveCallback getListener() {
        return this.f2795cb;
    }
}
