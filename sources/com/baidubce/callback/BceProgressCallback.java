package com.baidubce.callback;

import com.baidubce.model.AbstractBceRequest;

public interface BceProgressCallback<T extends AbstractBceRequest> {
    void onProgress(T t, long j, long j2);
}
