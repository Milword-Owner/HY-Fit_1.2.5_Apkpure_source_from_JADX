package com.baidubce.auth;

import java.util.Date;
import java.util.Set;

public class SignOptions {
    public static final SignOptions DEFAULT = new SignOptions();
    public static final int DEFAULT_EXPIRATION_IN_SECONDS = 1800;
    private int expirationInSeconds = DEFAULT_EXPIRATION_IN_SECONDS;
    private Set<String> headersToSign = null;
    private Date timestamp = null;

    public Set<String> getHeadersToSign() {
        return this.headersToSign;
    }

    public void setHeadersToSign(Set<String> set) {
        this.headersToSign = set;
    }

    public Date getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Date date) {
        this.timestamp = date;
    }

    public int getExpirationInSeconds() {
        return this.expirationInSeconds;
    }

    public void setExpirationInSeconds(int i) {
        this.expirationInSeconds = i;
    }

    public String toString() {
        return "SignOptions [\n  headersToSign=" + this.headersToSign + ",\n  timestamp=" + this.timestamp + ",\n  expirationInSeconds=" + this.expirationInSeconds + "]";
    }
}
