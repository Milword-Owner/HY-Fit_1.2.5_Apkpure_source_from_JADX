package org.litepal.crud.async;

import org.litepal.crud.callback.FindCallback;

public class FindExecutor<T> extends AsyncExecutor {

    /* renamed from: cb */
    private FindCallback<T> f2793cb;

    public void listen(FindCallback<T> findCallback) {
        this.f2793cb = findCallback;
        execute();
    }

    public FindCallback<T> getListener() {
        return this.f2793cb;
    }
}
