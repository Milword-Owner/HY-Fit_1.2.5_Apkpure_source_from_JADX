package com.google.android.gms.fitness.data;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import androidx.annotation.RecentlyNonNull;
import androidx.annotation.RecentlyNullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import com.google.android.gms.internal.fitness.zzkn;
import com.google.android.gms.internal.fitness.zzko;
import java.util.concurrent.TimeUnit;

@SafeParcelable.Class(creator = "SessionCreator")
@SafeParcelable.Reserved({1000})
/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public class Session extends AbstractSafeParcelable {
    @RecentlyNonNull
    public static final Parcelable.Creator<Session> CREATOR = new zzaf();
    @RecentlyNonNull
    public static final String EXTRA_SESSION = "vnd.google.fitness.session";
    @RecentlyNonNull
    public static final String MIME_TYPE_PREFIX = "vnd.google.fitness.session/";
    @SafeParcelable.Field(getter = "getDescription", mo19514id = 5)
    private final String description;
    @SafeParcelable.Field(getter = "getName", mo19514id = 3)
    @Nullable
    private final String name;
    @SafeParcelable.Field(getter = "getStartTimeMillis", mo19514id = 1)
    private final long zzkr;
    @SafeParcelable.Field(getter = "getEndTimeMillis", mo19514id = 2)
    private final long zzks;
    @SafeParcelable.Field(getter = "getActivityType", mo19514id = 7)
    private final int zzlg;
    @SafeParcelable.Field(getter = "getApplication", mo19514id = 8)
    private final zza zzlw;
    @SafeParcelable.Field(getter = "getIdentifier", mo19514id = 4)
    private final String zzod;
    @SafeParcelable.Field(getter = "getActiveTimeMillis", mo19514id = 9)
    @Nullable
    private final Long zzoe;

    @SafeParcelable.Constructor
    public Session(@SafeParcelable.Param(mo19517id = 1) long j, @SafeParcelable.Param(mo19517id = 2) long j2, @SafeParcelable.Param(mo19517id = 3) @Nullable String str, @SafeParcelable.Param(mo19517id = 4) String str2, @SafeParcelable.Param(mo19517id = 5) String str3, @SafeParcelable.Param(mo19517id = 7) int i, @SafeParcelable.Param(mo19517id = 8) zza zza, @SafeParcelable.Param(mo19517id = 9) @Nullable Long l) {
        this.zzkr = j;
        this.zzks = j2;
        this.name = str;
        this.zzod = str2;
        this.description = str3;
        this.zzlg = i;
        this.zzlw = zza;
        this.zzoe = l;
    }

    /* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
    public static class Builder {
        /* access modifiers changed from: private */
        public String description = "";
        /* access modifiers changed from: private */
        @Nullable
        public String name = null;
        /* access modifiers changed from: private */
        public long zzkr = 0;
        /* access modifiers changed from: private */
        public long zzks = 0;
        /* access modifiers changed from: private */
        public int zzlg = 4;
        /* access modifiers changed from: private */
        public String zzod;
        /* access modifiers changed from: private */
        @Nullable
        public Long zzoe;

        @RecentlyNonNull
        public Builder setStartTime(long j, @RecentlyNonNull TimeUnit timeUnit) {
            Preconditions.checkState(j > 0, "Start time should be positive.");
            this.zzkr = timeUnit.toMillis(j);
            return this;
        }

        @RecentlyNonNull
        public Builder setEndTime(long j, @RecentlyNonNull TimeUnit timeUnit) {
            Preconditions.checkState(j >= 0, "End time should be positive.");
            this.zzks = timeUnit.toMillis(j);
            return this;
        }

        @RecentlyNonNull
        public Builder setName(@RecentlyNonNull String str) {
            Preconditions.checkArgument(str.length() <= 100, "Session name cannot exceed %d characters", 100);
            this.name = str;
            return this;
        }

        @RecentlyNonNull
        public Builder setIdentifier(@RecentlyNonNull String str) {
            Preconditions.checkArgument(str != null && TextUtils.getTrimmedLength(str) > 0);
            this.zzod = str;
            return this;
        }

        @RecentlyNonNull
        public Builder setDescription(@RecentlyNonNull String str) {
            Preconditions.checkArgument(str.length() <= 1000, "Session description cannot exceed %d characters", 1000);
            this.description = str;
            return this;
        }

        @RecentlyNonNull
        public Builder setActivity(@RecentlyNonNull String str) {
            int zzo = zzko.zzo(str);
            zzkn zza = zzkn.zza(zzo, zzkn.UNKNOWN);
            Preconditions.checkArgument(!(zza.zzdz() && !zza.equals(zzkn.SLEEP)), "Unsupported session activity type %s.", Integer.valueOf(zzo));
            this.zzlg = zzo;
            return this;
        }

        @RecentlyNonNull
        public Builder setActiveTime(long j, @RecentlyNonNull TimeUnit timeUnit) {
            this.zzoe = Long.valueOf(timeUnit.toMillis(j));
            return this;
        }

        @RecentlyNonNull
        public Session build() {
            boolean z = true;
            Preconditions.checkState(this.zzkr > 0, "Start time should be specified.");
            long j = this.zzks;
            if (j != 0 && j <= this.zzkr) {
                z = false;
            }
            Preconditions.checkState(z, "End time should be later than start time.");
            if (this.zzod == null) {
                String str = this.name;
                if (str == null) {
                    str = "";
                }
                long j2 = this.zzkr;
                StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 20);
                sb.append(str);
                sb.append(j2);
                this.zzod = sb.toString();
            }
            return new Session(this);
        }
    }

    private Session(Builder builder) {
        this(builder.zzkr, builder.zzks, builder.name, builder.zzod, builder.description, builder.zzlg, (zza) null, builder.zzoe);
    }

    @RecentlyNullable
    public static Session extract(@RecentlyNonNull Intent intent) {
        if (intent == null) {
            return null;
        }
        return (Session) SafeParcelableSerializer.deserializeFromIntentExtra(intent, EXTRA_SESSION, CREATOR);
    }

    @RecentlyNonNull
    public static String getMimeType(@RecentlyNonNull String str) {
        String valueOf = String.valueOf(str);
        return valueOf.length() != 0 ? MIME_TYPE_PREFIX.concat(valueOf) : new String(MIME_TYPE_PREFIX);
    }

    public long getStartTime(@RecentlyNonNull TimeUnit timeUnit) {
        return timeUnit.convert(this.zzkr, TimeUnit.MILLISECONDS);
    }

    public long getEndTime(@RecentlyNonNull TimeUnit timeUnit) {
        return timeUnit.convert(this.zzks, TimeUnit.MILLISECONDS);
    }

    public long getActiveTime(@RecentlyNonNull TimeUnit timeUnit) {
        Long l = this.zzoe;
        if (l != null) {
            return timeUnit.convert(l.longValue(), TimeUnit.MILLISECONDS);
        }
        throw new IllegalStateException("Active time is not set");
    }

    public boolean hasActiveTime() {
        return this.zzoe != null;
    }

    public boolean isOngoing() {
        return this.zzks == 0;
    }

    @RecentlyNullable
    public String getName() {
        return this.name;
    }

    @RecentlyNonNull
    public String getIdentifier() {
        return this.zzod;
    }

    @RecentlyNonNull
    public String getDescription() {
        return this.description;
    }

    @RecentlyNonNull
    public String getActivity() {
        return zzko.getName(this.zzlg);
    }

    @RecentlyNullable
    public String getAppPackageName() {
        zza zza = this.zzlw;
        if (zza == null) {
            return null;
        }
        return zza.getPackageName();
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Session)) {
            return false;
        }
        Session session = (Session) obj;
        return this.zzkr == session.zzkr && this.zzks == session.zzks && Objects.equal(this.name, session.name) && Objects.equal(this.zzod, session.zzod) && Objects.equal(this.description, session.description) && Objects.equal(this.zzlw, session.zzlw) && this.zzlg == session.zzlg;
    }

    public int hashCode() {
        return Objects.hashCode(Long.valueOf(this.zzkr), Long.valueOf(this.zzks), this.zzod);
    }

    @RecentlyNonNull
    public String toString() {
        return Objects.toStringHelper(this).add("startTime", Long.valueOf(this.zzkr)).add("endTime", Long.valueOf(this.zzks)).add("name", this.name).add("identifier", this.zzod).add("description", this.description).add("activity", Integer.valueOf(this.zzlg)).add("application", this.zzlw).toString();
    }

    public void writeToParcel(@RecentlyNonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 1, this.zzkr);
        SafeParcelWriter.writeLong(parcel, 2, this.zzks);
        SafeParcelWriter.writeString(parcel, 3, getName(), false);
        SafeParcelWriter.writeString(parcel, 4, getIdentifier(), false);
        SafeParcelWriter.writeString(parcel, 5, getDescription(), false);
        SafeParcelWriter.writeInt(parcel, 7, this.zzlg);
        SafeParcelWriter.writeParcelable(parcel, 8, this.zzlw, i, false);
        SafeParcelWriter.writeLongObject(parcel, 9, this.zzoe, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
