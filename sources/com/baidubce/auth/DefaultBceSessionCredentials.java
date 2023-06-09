package com.baidubce.auth;

import com.baidubce.util.CheckUtils;

public class DefaultBceSessionCredentials extends DefaultBceCredentials implements BceSessionCredentials {
    private final String sessionToken;

    public DefaultBceSessionCredentials(String str, String str2, String str3) {
        super(str, str2);
        CheckUtils.isNotNull(str3, "token should not be null.");
        CheckUtils.checkArgument(!str3.isEmpty(), "token should not be empty.");
        this.sessionToken = str3;
    }

    public String getSessionToken() {
        return this.sessionToken;
    }
}
