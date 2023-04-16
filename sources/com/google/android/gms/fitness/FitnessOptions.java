package com.google.android.gms.fitness;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.annotation.RecentlyNonNull;
import com.google.android.gms.auth.api.signin.GoogleSignInOptionsExtension;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.fitness.data.DataType;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public final class FitnessOptions implements GoogleSignInOptionsExtension {
    public static final int ACCESS_READ = 0;
    public static final int ACCESS_WRITE = 1;
    private final Set<Scope> zzkl;

    private FitnessOptions(Builder builder) {
        this.zzkl = builder.zzkl;
    }

    public final int getExtensionType() {
        return 3;
    }

    /* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
    public static final class Builder {
        /* access modifiers changed from: private */
        public final Set<Scope> zzkl;

        private Builder() {
            this.zzkl = new HashSet();
        }

        @RecentlyNonNull
        public final Builder addDataType(@RecentlyNonNull DataType dataType) {
            return addDataType(dataType, 0);
        }

        @RecentlyNonNull
        public final Builder addDataType(@RecentlyNonNull DataType dataType, int i) {
            Preconditions.checkArgument(i == 0 || i == 1, "valid access types are FitnessOptions.ACCESS_READ or FitnessOptions.ACCESS_WRITE");
            String zzk = dataType.zzk();
            String zzl = dataType.zzl();
            if (i == 0 && zzk != null) {
                this.zzkl.add(new Scope(zzk));
            } else if (i == 1 && zzl != null) {
                this.zzkl.add(new Scope(zzl));
            }
            return this;
        }

        @RecentlyNonNull
        public final Builder accessActivitySessions(int i) {
            if (i == 0) {
                this.zzkl.add(new Scope(Scopes.FITNESS_ACTIVITY_READ));
            } else if (i == 1) {
                this.zzkl.add(new Scope(Scopes.FITNESS_ACTIVITY_READ_WRITE));
            } else {
                throw new IllegalArgumentException("valid access types are FitnessOptions.ACCESS_READ or FitnessOptions.ACCESS_WRITE");
            }
            return this;
        }

        @RecentlyNonNull
        public final Builder accessSleepSessions(int i) {
            Preconditions.checkArgument(i == 0 || i == 1, "valid access types are FitnessOptions.ACCESS_READ or FitnessOptions.ACCESS_WRITE");
            if (i == 0) {
                this.zzkl.add(new Scope("https://www.googleapis.com/auth/fitness.sleep.read"));
            } else if (i == 1) {
                this.zzkl.add(new Scope("https://www.googleapis.com/auth/fitness.sleep.write"));
            }
            return this;
        }

        @RecentlyNonNull
        public final FitnessOptions build() {
            return new FitnessOptions(this);
        }
    }

    @RecentlyNonNull
    public final Bundle toBundle() {
        return new Bundle();
    }

    @RecentlyNonNull
    public final List<Scope> getImpliedScopes() {
        return new ArrayList(this.zzkl);
    }

    @RecentlyNonNull
    public static Builder builder() {
        return new Builder();
    }

    public final boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FitnessOptions)) {
            return false;
        }
        return this.zzkl.equals(((FitnessOptions) obj).zzkl);
    }

    public final int hashCode() {
        return Objects.hashCode(this.zzkl);
    }
}
