package com.baidubce.callback;

import com.baidubce.BceClientException;
import com.baidubce.BceServiceException;

public interface BceCompleteCallback<T> {
    void onFailure(BceClientException bceClientException, BceServiceException bceServiceException);

    void onSuccess(T t);
}
