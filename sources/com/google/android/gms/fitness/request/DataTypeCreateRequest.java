package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import androidx.annotation.RecentlyNonNull;
import com.facebook.GraphRequest;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.Field;
import com.google.android.gms.internal.fitness.zzbi;
import com.google.android.gms.internal.fitness.zzbl;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SafeParcelable.Class(creator = "DataTypeCreateRequestCreator")
@SafeParcelable.Reserved({4, 1000})
@Deprecated
/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public class DataTypeCreateRequest extends AbstractSafeParcelable {
    @RecentlyNonNull
    public static final Parcelable.Creator<DataTypeCreateRequest> CREATOR = new zzs();
    @SafeParcelable.Field(getter = "getName", mo19514id = 1)
    private final String name;
    @SafeParcelable.Field(getter = "getFields", mo19514id = 2)
    private final List<Field> zzlz;
    @SafeParcelable.Field(getter = "getCallbackBinder", mo19514id = 3, type = "android.os.IBinder")
    @Nullable
    private final zzbi zzrj;

    @SafeParcelable.Constructor
    DataTypeCreateRequest(@SafeParcelable.Param(mo19517id = 1) String str, @SafeParcelable.Param(mo19517id = 2) List<Field> list, @SafeParcelable.Param(mo19517id = 3) @Nullable IBinder iBinder) {
        zzbi zzbi;
        this.name = str;
        this.zzlz = Collections.unmodifiableList(list);
        if (iBinder == null) {
            zzbi = null;
        } else {
            zzbi = zzbl.zze(iBinder);
        }
        this.zzrj = zzbi;
    }

    /* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
    public static class Builder {
        /* access modifiers changed from: private */
        public String name;
        /* access modifiers changed from: private */
        public final List<Field> zzlz = new ArrayList();

        @RecentlyNonNull
        public Builder setName(@RecentlyNonNull String str) {
            this.name = str;
            return this;
        }

        @RecentlyNonNull
        public Builder addField(@RecentlyNonNull Field field) {
            if (!this.zzlz.contains(field)) {
                this.zzlz.add(field);
            }
            return this;
        }

        @RecentlyNonNull
        public Builder addField(@RecentlyNonNull String str, int i) {
            Preconditions.checkArgument(str != null && !str.isEmpty(), "Invalid name specified");
            return addField(new Field(str, i));
        }

        @RecentlyNonNull
        public DataTypeCreateRequest build() {
            Preconditions.checkState(this.name != null, "Must set the name");
            Preconditions.checkState(!this.zzlz.isEmpty(), "Must specify the data fields");
            return new DataTypeCreateRequest(this);
        }
    }

    private DataTypeCreateRequest(Builder builder) {
        this(builder.name, (List<Field>) builder.zzlz, (zzbi) null);
    }

    public DataTypeCreateRequest(DataTypeCreateRequest dataTypeCreateRequest, zzbi zzbi) {
        this(dataTypeCreateRequest.name, dataTypeCreateRequest.zzlz, zzbi);
    }

    private DataTypeCreateRequest(String str, List<Field> list, @Nullable zzbi zzbi) {
        this.name = str;
        this.zzlz = Collections.unmodifiableList(list);
        this.zzrj = zzbi;
    }

    @RecentlyNonNull
    public String getName() {
        return this.name;
    }

    @RecentlyNonNull
    public List<Field> getFields() {
        return this.zzlz;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DataTypeCreateRequest)) {
            return false;
        }
        DataTypeCreateRequest dataTypeCreateRequest = (DataTypeCreateRequest) obj;
        return Objects.equal(this.name, dataTypeCreateRequest.name) && Objects.equal(this.zzlz, dataTypeCreateRequest.zzlz);
    }

    public int hashCode() {
        return Objects.hashCode(this.name, this.zzlz);
    }

    @RecentlyNonNull
    public String toString() {
        return Objects.toStringHelper(this).add("name", this.name).add(GraphRequest.FIELDS_PARAM, this.zzlz).toString();
    }

    public void writeToParcel(@RecentlyNonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getName(), false);
        SafeParcelWriter.writeTypedList(parcel, 2, getFields(), false);
        zzbi zzbi = this.zzrj;
        SafeParcelWriter.writeIBinder(parcel, 3, zzbi == null ? null : zzbi.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
