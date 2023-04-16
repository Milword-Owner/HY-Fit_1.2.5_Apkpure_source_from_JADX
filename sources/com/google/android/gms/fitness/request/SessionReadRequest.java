package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import androidx.annotation.RecentlyNonNull;
import androidx.annotation.RecentlyNullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.internal.fitness.zzcg;
import com.google.android.gms.internal.fitness.zzch;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@SafeParcelable.Class(creator = "SessionReadRequestCreator")
@SafeParcelable.Reserved({11, 1000})
/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public class SessionReadRequest extends AbstractSafeParcelable {
    @RecentlyNonNull
    public static final Parcelable.Creator<SessionReadRequest> CREATOR = new zzax();
    @SafeParcelable.Field(getter = "getStartTimeMillis", mo19514id = 3)
    private final long zzkr;
    @SafeParcelable.Field(getter = "getEndTimeMillis", mo19514id = 4)
    private final long zzks;
    @SafeParcelable.Field(getter = "getDataTypes", mo19514id = 5)
    private final List<DataType> zzlf;
    @SafeParcelable.Field(getter = "getDataSources", mo19514id = 6)
    private final List<DataSource> zzqq;
    @SafeParcelable.Field(getter = "areServerQueriesEnabled", mo19514id = 8)
    private final boolean zzrc;
    @SafeParcelable.Field(getter = "getSessionName", mo19514id = 1)
    private final String zzsd;
    @SafeParcelable.Field(getter = "getSessionId", mo19514id = 2)
    private final String zzse;
    @SafeParcelable.Field(getter = "includeSessionsFromAllApps", mo19514id = 7)
    private final boolean zzsf;
    @SafeParcelable.Field(getter = "getExcludedPackages", mo19514id = 9)
    private final List<String> zzsg;
    @SafeParcelable.Field(getter = "getCallbackBinder", mo19514id = 10, type = "android.os.IBinder")
    @Nullable
    private final zzch zzsh;
    @SafeParcelable.Field(defaultValue = "true", getter = "areActivitySessionsIncluded", mo19514id = 12)
    private final boolean zzsi;
    @SafeParcelable.Field(defaultValue = "false", getter = "areSleepSessionsIncluded", mo19514id = 13)
    private final boolean zzsj;

    @SafeParcelable.Constructor
    SessionReadRequest(@SafeParcelable.Param(mo19517id = 1) String str, @SafeParcelable.Param(mo19517id = 2) String str2, @SafeParcelable.Param(mo19517id = 3) long j, @SafeParcelable.Param(mo19517id = 4) long j2, @SafeParcelable.Param(mo19517id = 5) List<DataType> list, @SafeParcelable.Param(mo19517id = 6) List<DataSource> list2, @SafeParcelable.Param(mo19517id = 7) boolean z, @SafeParcelable.Param(mo19517id = 8) boolean z2, @SafeParcelable.Param(mo19517id = 9) List<String> list3, @SafeParcelable.Param(mo19517id = 10) @Nullable IBinder iBinder, @SafeParcelable.Param(mo19517id = 12) boolean z3, @SafeParcelable.Param(mo19517id = 13) boolean z4) {
        zzch zzch;
        this.zzsd = str;
        this.zzse = str2;
        this.zzkr = j;
        this.zzks = j2;
        this.zzlf = list;
        this.zzqq = list2;
        this.zzsf = z;
        this.zzrc = z2;
        this.zzsg = list3;
        if (iBinder == null) {
            zzch = null;
        } else {
            zzch = zzcg.zzh(iBinder);
        }
        this.zzsh = zzch;
        this.zzsi = z3;
        this.zzsj = z4;
    }

    /* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
    public static class Builder {
        /* access modifiers changed from: private */
        public long zzkr = 0;
        /* access modifiers changed from: private */
        public long zzks = 0;
        /* access modifiers changed from: private */
        public final List<DataType> zzlf = new ArrayList();
        /* access modifiers changed from: private */
        public final List<DataSource> zzqq = new ArrayList();
        /* access modifiers changed from: private */
        public boolean zzrc = false;
        /* access modifiers changed from: private */
        public String zzsd;
        /* access modifiers changed from: private */
        public String zzse;
        /* access modifiers changed from: private */
        public final List<String> zzsg = new ArrayList();
        /* access modifiers changed from: private */
        public boolean zzsi = false;
        /* access modifiers changed from: private */
        public boolean zzsj = false;
        /* access modifiers changed from: private */
        public boolean zzsk = false;
        private boolean zzsl = false;

        @RecentlyNonNull
        public Builder setTimeInterval(long j, long j2, @RecentlyNonNull TimeUnit timeUnit) {
            this.zzkr = timeUnit.toMillis(j);
            this.zzks = timeUnit.toMillis(j2);
            return this;
        }

        @RecentlyNonNull
        public Builder setSessionName(@RecentlyNonNull String str) {
            this.zzsd = str;
            return this;
        }

        @RecentlyNonNull
        public Builder setSessionId(@RecentlyNonNull String str) {
            this.zzse = str;
            return this;
        }

        @RecentlyNonNull
        public Builder read(@RecentlyNonNull DataSource dataSource) {
            Preconditions.checkNotNull(dataSource, "Attempting to add a null data source");
            if (!this.zzqq.contains(dataSource)) {
                this.zzqq.add(dataSource);
            }
            return this;
        }

        @RecentlyNonNull
        public Builder read(@RecentlyNonNull DataType dataType) {
            Preconditions.checkNotNull(dataType, "Attempting to use a null data type");
            if (!this.zzlf.contains(dataType)) {
                this.zzlf.add(dataType);
            }
            return this;
        }

        @RecentlyNonNull
        public Builder readSessionsFromAllApps() {
            this.zzsk = true;
            return this;
        }

        @RecentlyNonNull
        public Builder excludePackage(@RecentlyNonNull String str) {
            Preconditions.checkNotNull(str, "Attempting to use a null package name");
            if (!this.zzsg.contains(str)) {
                this.zzsg.add(str);
            }
            return this;
        }

        @RecentlyNonNull
        public Builder enableServerQueries() {
            this.zzrc = true;
            return this;
        }

        @RecentlyNonNull
        public Builder includeActivitySessions() {
            this.zzsi = true;
            this.zzsl = true;
            return this;
        }

        @RecentlyNonNull
        public Builder includeSleepSessions() {
            this.zzsj = true;
            this.zzsl = true;
            return this;
        }

        @RecentlyNonNull
        public SessionReadRequest build() {
            Preconditions.checkArgument(this.zzkr > 0, "Invalid start time: %s", Long.valueOf(this.zzkr));
            long j = this.zzks;
            Preconditions.checkArgument(j > 0 && j > this.zzkr, "Invalid end time: %s", Long.valueOf(this.zzks));
            if (!this.zzsl) {
                this.zzsi = true;
            }
            return new SessionReadRequest(this);
        }
    }

    private SessionReadRequest(Builder builder) {
        this(builder.zzsd, builder.zzse, builder.zzkr, builder.zzks, builder.zzlf, builder.zzqq, builder.zzsk, builder.zzrc, builder.zzsg, (IBinder) null, builder.zzsi, builder.zzsj);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public SessionReadRequest(com.google.android.gms.fitness.request.SessionReadRequest r16, com.google.android.gms.internal.fitness.zzch r17) {
        /*
            r15 = this;
            r0 = r16
            java.lang.String r1 = r0.zzsd
            java.lang.String r2 = r0.zzse
            long r3 = r0.zzkr
            long r5 = r0.zzks
            java.util.List<com.google.android.gms.fitness.data.DataType> r7 = r0.zzlf
            java.util.List<com.google.android.gms.fitness.data.DataSource> r8 = r0.zzqq
            boolean r9 = r0.zzsf
            boolean r10 = r0.zzrc
            java.util.List<java.lang.String> r11 = r0.zzsg
            android.os.IBinder r12 = r17.asBinder()
            boolean r13 = r0.zzsi
            boolean r14 = r0.zzsj
            r0 = r15
            r0.<init>(r1, r2, r3, r5, r7, r8, r9, r10, r11, r12, r13, r14)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.fitness.request.SessionReadRequest.<init>(com.google.android.gms.fitness.request.SessionReadRequest, com.google.android.gms.internal.fitness.zzch):void");
    }

    public long getStartTime(@RecentlyNonNull TimeUnit timeUnit) {
        return timeUnit.convert(this.zzkr, TimeUnit.MILLISECONDS);
    }

    public long getEndTime(@RecentlyNonNull TimeUnit timeUnit) {
        return timeUnit.convert(this.zzks, TimeUnit.MILLISECONDS);
    }

    @RecentlyNullable
    public String getSessionName() {
        return this.zzsd;
    }

    @RecentlyNullable
    public String getSessionId() {
        return this.zzse;
    }

    @RecentlyNonNull
    public List<DataType> getDataTypes() {
        return this.zzlf;
    }

    @RecentlyNonNull
    public List<DataSource> getDataSources() {
        return this.zzqq;
    }

    public boolean includeSessionsFromAllApps() {
        return this.zzsf;
    }

    @RecentlyNonNull
    public List<String> getExcludedPackages() {
        return this.zzsg;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SessionReadRequest)) {
            return false;
        }
        SessionReadRequest sessionReadRequest = (SessionReadRequest) obj;
        return Objects.equal(this.zzsd, sessionReadRequest.zzsd) && this.zzse.equals(sessionReadRequest.zzse) && this.zzkr == sessionReadRequest.zzkr && this.zzks == sessionReadRequest.zzks && Objects.equal(this.zzlf, sessionReadRequest.zzlf) && Objects.equal(this.zzqq, sessionReadRequest.zzqq) && this.zzsf == sessionReadRequest.zzsf && this.zzsg.equals(sessionReadRequest.zzsg) && this.zzrc == sessionReadRequest.zzrc && this.zzsi == sessionReadRequest.zzsi && this.zzsj == sessionReadRequest.zzsj;
    }

    public int hashCode() {
        return Objects.hashCode(this.zzsd, this.zzse, Long.valueOf(this.zzkr), Long.valueOf(this.zzks));
    }

    @RecentlyNonNull
    public String toString() {
        return Objects.toStringHelper(this).add("sessionName", this.zzsd).add("sessionId", this.zzse).add("startTimeMillis", Long.valueOf(this.zzkr)).add("endTimeMillis", Long.valueOf(this.zzks)).add("dataTypes", this.zzlf).add("dataSources", this.zzqq).add("sessionsFromAllApps", Boolean.valueOf(this.zzsf)).add("excludedPackages", this.zzsg).add("useServer", Boolean.valueOf(this.zzrc)).add("activitySessionsIncluded", Boolean.valueOf(this.zzsi)).add("sleepSessionsIncluded", Boolean.valueOf(this.zzsj)).toString();
    }

    public void writeToParcel(@RecentlyNonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getSessionName(), false);
        SafeParcelWriter.writeString(parcel, 2, getSessionId(), false);
        SafeParcelWriter.writeLong(parcel, 3, this.zzkr);
        SafeParcelWriter.writeLong(parcel, 4, this.zzks);
        SafeParcelWriter.writeTypedList(parcel, 5, getDataTypes(), false);
        SafeParcelWriter.writeTypedList(parcel, 6, getDataSources(), false);
        SafeParcelWriter.writeBoolean(parcel, 7, includeSessionsFromAllApps());
        SafeParcelWriter.writeBoolean(parcel, 8, this.zzrc);
        SafeParcelWriter.writeStringList(parcel, 9, getExcludedPackages(), false);
        zzch zzch = this.zzsh;
        SafeParcelWriter.writeIBinder(parcel, 10, zzch == null ? null : zzch.asBinder(), false);
        SafeParcelWriter.writeBoolean(parcel, 12, this.zzsi);
        SafeParcelWriter.writeBoolean(parcel, 13, this.zzsj);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
