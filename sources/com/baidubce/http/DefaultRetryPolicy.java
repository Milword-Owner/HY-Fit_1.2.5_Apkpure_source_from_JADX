package com.baidubce.http;

import com.baidubce.BceClientException;
import com.baidubce.BceServiceException;
import com.baidubce.ErrorCode;
import com.baidubce.util.BLog;
import com.baidubce.util.CheckUtils;
import java.io.IOException;

public class DefaultRetryPolicy implements RetryPolicy {
    private static final int SCALE_FACTOR = 300;
    private long maxDelayInMillis;
    private int maxErrorRetry;

    public DefaultRetryPolicy() {
        this(3, 30000);
    }

    public DefaultRetryPolicy(int i, long j) {
        boolean z = true;
        CheckUtils.checkArgument(i >= 0, "maxErrorRetry should be a non-negative.");
        CheckUtils.checkArgument(j < 0 ? false : z, "maxDelayInMillis should be a non-negative.");
        this.maxErrorRetry = i;
        this.maxDelayInMillis = j;
    }

    public int getMaxErrorRetry() {
        return this.maxErrorRetry;
    }

    public long getMaxDelayInMillis() {
        return this.maxDelayInMillis;
    }

    public long getDelayBeforeNextRetryInMillis(BceClientException bceClientException, int i) {
        if (!shouldRetry(bceClientException, i)) {
            return -1;
        }
        if (i < 0) {
            return 0;
        }
        return (long) ((1 << (i + 1)) * SCALE_FACTOR);
    }

    /* access modifiers changed from: protected */
    public boolean shouldRetry(BceClientException bceClientException, int i) {
        if (bceClientException.getCause() instanceof IOException) {
            BLog.error("Retry for IOException.");
            return true;
        } else if (!(bceClientException instanceof BceServiceException)) {
            return false;
        } else {
            BceServiceException bceServiceException = (BceServiceException) bceClientException;
            if (bceServiceException.getStatusCode() == 500) {
                BLog.error("Retry for internal server error.");
                return true;
            } else if (bceServiceException.getStatusCode() == 502) {
                BLog.debug("Retry for bad gateway.");
                return true;
            } else if (bceServiceException.getStatusCode() == 503) {
                BLog.error("Retry for service unavailable.");
                return true;
            } else {
                String errorCode = bceServiceException.getErrorCode();
                if (ErrorCode.REQUEST_EXPIRED.equals(errorCode)) {
                    BLog.error("Retry for request expired.");
                    return true;
                } else if (!ErrorCode.REQUEST_TIME_TOO_SKEWED.equals(errorCode)) {
                    return false;
                } else {
                    BLog.error("Retry for request time too skewed");
                    return true;
                }
            }
        }
    }
}
