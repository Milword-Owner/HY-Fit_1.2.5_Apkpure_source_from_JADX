package com.google.android.gms.auth.api.credentials;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.NonNull;
import com.google.android.gms.auth.api.Auth;

/* compiled from: com.google.android.gms:play-services-auth@@19.0.0 */
public class Credentials {
    public static CredentialsClient getClient(@NonNull Activity activity) {
        return new CredentialsClient(activity, (Auth.AuthCredentialsOptions) CredentialsOptions.DEFAULT);
    }

    public static CredentialsClient getClient(@NonNull Activity activity, @NonNull CredentialsOptions credentialsOptions) {
        return new CredentialsClient(activity, (Auth.AuthCredentialsOptions) credentialsOptions);
    }

    public static CredentialsClient getClient(@NonNull Context context) {
        return new CredentialsClient(context, (Auth.AuthCredentialsOptions) CredentialsOptions.DEFAULT);
    }

    public static CredentialsClient getClient(@NonNull Context context, @NonNull CredentialsOptions credentialsOptions) {
        return new CredentialsClient(context, (Auth.AuthCredentialsOptions) credentialsOptions);
    }
}
