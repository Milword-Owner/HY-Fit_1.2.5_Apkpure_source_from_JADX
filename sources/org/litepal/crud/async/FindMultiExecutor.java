package org.litepal.crud.async;

import org.litepal.crud.callback.FindMultiCallback;

public class FindMultiExecutor<T> extends AsyncExecutor {

    /* renamed from: cb */
    private FindMultiCallback<T> f2794cb;

    public void listen(FindMultiCallback<T> findMultiCallback) {
        this.f2794cb = findMultiCallback;
        execute();
    }

    public FindMultiCallback<T> getListener() {
        return this.f2794cb;
    }
}
