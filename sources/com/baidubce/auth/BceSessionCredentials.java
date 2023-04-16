package com.baidubce.auth;

public interface BceSessionCredentials extends BceCredentials {
    String getSessionToken();
}
