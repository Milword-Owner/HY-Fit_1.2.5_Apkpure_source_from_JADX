package com.google.firebase;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.StatusExceptionMapper;

@KeepForSdk
/* compiled from: com.google.android.gms:play-services-basement@@17.1.1 */
public class FirebaseExceptionMapper implements StatusExceptionMapper {
    public Exception getException(Status status) {
        if (status.getStatusCode() == 8) {
            return new FirebaseException(status.zzg());
        }
        return new FirebaseApiNotAvailableException(status.zzg());
    }
}
