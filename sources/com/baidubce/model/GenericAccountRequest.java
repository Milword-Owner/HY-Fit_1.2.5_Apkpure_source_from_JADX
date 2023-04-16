package com.baidubce.model;

import com.baidubce.auth.BceCredentials;

public class GenericAccountRequest extends AbstractBceRequest {
    public GenericAccountRequest withRequestCredentials(BceCredentials bceCredentials) {
        setRequestCredentials(bceCredentials);
        return this;
    }
}
