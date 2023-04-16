package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.ApiExceptionUtil;

@KeepForSdk
/* compiled from: com.google.android.gms:play-services-base@@17.1.0 */
public class ApiExceptionMapper implements StatusExceptionMapper {
    public Exception getException(Status status) {
        return ApiExceptionUtil.fromStatus(status);
    }
}
