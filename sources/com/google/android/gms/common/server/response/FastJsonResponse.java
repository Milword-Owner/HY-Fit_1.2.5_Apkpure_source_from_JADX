package com.google.android.gms.common.server.response;

import android.os.Parcel;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.server.converter.zab;
import com.google.android.gms.common.util.Base64Utils;
import com.google.android.gms.common.util.JsonUtils;
import com.google.android.gms.common.util.MapUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@ShowFirstParty
@KeepForSdk
/* compiled from: com.google.android.gms:play-services-base@@17.1.0 */
public abstract class FastJsonResponse {

    @ShowFirstParty
    /* compiled from: com.google.android.gms:play-services-base@@17.1.0 */
    public interface FieldConverter<I, O> {
        O convert(I i);

        I convertBack(O o);

        int zach();

        int zaci();
    }

    @KeepForSdk
    public abstract Map<String, Field<?, ?>> getFieldMappings();

    /* access modifiers changed from: protected */
    @KeepForSdk
    public abstract Object getValueObject(String str);

    /* access modifiers changed from: protected */
    @KeepForSdk
    public abstract boolean isPrimitiveFieldSet(String str);

    /* access modifiers changed from: protected */
    @KeepForSdk
    public boolean isFieldSet(Field field) {
        if (field.zaqh != 11) {
            return isPrimitiveFieldSet(field.zaqj);
        }
        if (field.zaqi) {
            String str = field.zaqj;
            throw new UnsupportedOperationException("Concrete type arrays not supported");
        }
        String str2 = field.zaqj;
        throw new UnsupportedOperationException("Concrete types not supported");
    }

    @ShowFirstParty
    @SafeParcelable.Class(creator = "FieldCreator")
    @VisibleForTesting
    @KeepForSdk
    /* compiled from: com.google.android.gms:play-services-base@@17.1.0 */
    public static class Field<I, O> extends AbstractSafeParcelable {
        public static final zai CREATOR = new zai();
        @SafeParcelable.VersionField(getter = "getVersionCode", mo19520id = 1)
        private final int zali;
        @SafeParcelable.Field(getter = "getTypeIn", mo19514id = 2)
        protected final int zaqf;
        @SafeParcelable.Field(getter = "isTypeInArray", mo19514id = 3)
        protected final boolean zaqg;
        @SafeParcelable.Field(getter = "getTypeOut", mo19514id = 4)
        protected final int zaqh;
        @SafeParcelable.Field(getter = "isTypeOutArray", mo19514id = 5)
        protected final boolean zaqi;
        @SafeParcelable.Field(getter = "getOutputFieldName", mo19514id = 6)
        protected final String zaqj;
        @SafeParcelable.Field(getter = "getSafeParcelableFieldId", mo19514id = 7)
        protected final int zaqk;
        protected final Class<? extends FastJsonResponse> zaql;
        @SafeParcelable.Field(getter = "getConcreteTypeName", mo19514id = 8)
        private final String zaqm;
        private zaj zaqn;
        /* access modifiers changed from: private */
        @SafeParcelable.Field(getter = "getWrappedConverter", mo19514id = 9, type = "com.google.android.gms.common.server.converter.ConverterWrapper")
        public FieldConverter<I, O> zaqo;

        @SafeParcelable.Constructor
        Field(@SafeParcelable.Param(mo19517id = 1) int i, @SafeParcelable.Param(mo19517id = 2) int i2, @SafeParcelable.Param(mo19517id = 3) boolean z, @SafeParcelable.Param(mo19517id = 4) int i3, @SafeParcelable.Param(mo19517id = 5) boolean z2, @SafeParcelable.Param(mo19517id = 6) String str, @SafeParcelable.Param(mo19517id = 7) int i4, @SafeParcelable.Param(mo19517id = 8) String str2, @SafeParcelable.Param(mo19517id = 9) zab zab) {
            this.zali = i;
            this.zaqf = i2;
            this.zaqg = z;
            this.zaqh = i3;
            this.zaqi = z2;
            this.zaqj = str;
            this.zaqk = i4;
            if (str2 == null) {
                this.zaql = null;
                this.zaqm = null;
            } else {
                this.zaql = SafeParcelResponse.class;
                this.zaqm = str2;
            }
            if (zab == null) {
                this.zaqo = null;
            } else {
                this.zaqo = zab.zacg();
            }
        }

        private Field(int i, boolean z, int i2, boolean z2, String str, int i3, Class<? extends FastJsonResponse> cls, FieldConverter<I, O> fieldConverter) {
            this.zali = 1;
            this.zaqf = i;
            this.zaqg = z;
            this.zaqh = i2;
            this.zaqi = z2;
            this.zaqj = str;
            this.zaqk = i3;
            this.zaql = cls;
            if (cls == null) {
                this.zaqm = null;
            } else {
                this.zaqm = cls.getCanonicalName();
            }
            this.zaqo = fieldConverter;
        }

        public final Field<I, O> zacj() {
            return new Field(this.zali, this.zaqf, this.zaqg, this.zaqh, this.zaqi, this.zaqj, this.zaqk, this.zaqm, zacm());
        }

        @KeepForSdk
        public int getSafeParcelableFieldId() {
            return this.zaqk;
        }

        private final String zack() {
            String str = this.zaqm;
            if (str == null) {
                return null;
            }
            return str;
        }

        public final boolean zacl() {
            return this.zaqo != null;
        }

        public final void zaa(zaj zaj) {
            this.zaqn = zaj;
        }

        private final zab zacm() {
            FieldConverter<I, O> fieldConverter = this.zaqo;
            if (fieldConverter == null) {
                return null;
            }
            return zab.zaa(fieldConverter);
        }

        public final FastJsonResponse zacn() throws InstantiationException, IllegalAccessException {
            Class<? extends FastJsonResponse> cls = this.zaql;
            if (cls != SafeParcelResponse.class) {
                return (FastJsonResponse) cls.newInstance();
            }
            Preconditions.checkNotNull(this.zaqn, "The field mapping dictionary must be set if the concrete type is a SafeParcelResponse object.");
            return new SafeParcelResponse(this.zaqn, this.zaqm);
        }

        public final Map<String, Field<?, ?>> zaco() {
            Preconditions.checkNotNull(this.zaqm);
            Preconditions.checkNotNull(this.zaqn);
            return this.zaqn.zai(this.zaqm);
        }

        public final O convert(I i) {
            return this.zaqo.convert(i);
        }

        public final I convertBack(O o) {
            return this.zaqo.convertBack(o);
        }

        @KeepForSdk
        @VisibleForTesting
        public static Field<Integer, Integer> forInteger(String str, int i) {
            return new Field(0, false, 0, false, str, i, (Class<? extends FastJsonResponse>) null, (FieldConverter) null);
        }

        @KeepForSdk
        public static Field<Long, Long> forLong(String str, int i) {
            return new Field(2, false, 2, false, str, i, (Class<? extends FastJsonResponse>) null, (FieldConverter) null);
        }

        @KeepForSdk
        public static Field<Float, Float> forFloat(String str, int i) {
            return new Field(3, false, 3, false, str, i, (Class<? extends FastJsonResponse>) null, (FieldConverter) null);
        }

        @KeepForSdk
        public static Field<Double, Double> forDouble(String str, int i) {
            return new Field(4, false, 4, false, str, i, (Class<? extends FastJsonResponse>) null, (FieldConverter) null);
        }

        @KeepForSdk
        public static Field<Boolean, Boolean> forBoolean(String str, int i) {
            return new Field(6, false, 6, false, str, i, (Class<? extends FastJsonResponse>) null, (FieldConverter) null);
        }

        @KeepForSdk
        public static Field<String, String> forString(String str, int i) {
            return new Field(7, false, 7, false, str, i, (Class<? extends FastJsonResponse>) null, (FieldConverter) null);
        }

        @KeepForSdk
        public static Field<ArrayList<String>, ArrayList<String>> forStrings(String str, int i) {
            return new Field(7, true, 7, true, str, i, (Class<? extends FastJsonResponse>) null, (FieldConverter) null);
        }

        @KeepForSdk
        @VisibleForTesting
        public static Field<byte[], byte[]> forBase64(String str, int i) {
            return new Field(8, false, 8, false, str, i, (Class<? extends FastJsonResponse>) null, (FieldConverter) null);
        }

        @KeepForSdk
        public static Field<HashMap<String, String>, HashMap<String, String>> forStringMap(String str, int i) {
            return new Field(10, false, 10, false, str, i, (Class<? extends FastJsonResponse>) null, (FieldConverter) null);
        }

        @KeepForSdk
        public static <T extends FastJsonResponse> Field<T, T> forConcreteType(String str, int i, Class<T> cls) {
            return new Field(11, false, 11, false, str, i, cls, (FieldConverter) null);
        }

        @KeepForSdk
        public static <T extends FastJsonResponse> Field<ArrayList<T>, ArrayList<T>> forConcreteTypeArray(String str, int i, Class<T> cls) {
            return new Field(11, true, 11, true, str, i, cls, (FieldConverter) null);
        }

        @KeepForSdk
        public static Field withConverter(String str, int i, FieldConverter<?, ?> fieldConverter, boolean z) {
            return new Field(fieldConverter.zach(), z, fieldConverter.zaci(), false, str, i, (Class<? extends FastJsonResponse>) null, fieldConverter);
        }

        public void writeToParcel(Parcel parcel, int i) {
            int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
            SafeParcelWriter.writeInt(parcel, 1, this.zali);
            SafeParcelWriter.writeInt(parcel, 2, this.zaqf);
            SafeParcelWriter.writeBoolean(parcel, 3, this.zaqg);
            SafeParcelWriter.writeInt(parcel, 4, this.zaqh);
            SafeParcelWriter.writeBoolean(parcel, 5, this.zaqi);
            SafeParcelWriter.writeString(parcel, 6, this.zaqj, false);
            SafeParcelWriter.writeInt(parcel, 7, getSafeParcelableFieldId());
            SafeParcelWriter.writeString(parcel, 8, zack(), false);
            SafeParcelWriter.writeParcelable(parcel, 9, zacm(), i, false);
            SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
        }

        public String toString() {
            Objects.ToStringHelper add = Objects.toStringHelper(this).add("versionCode", Integer.valueOf(this.zali)).add("typeIn", Integer.valueOf(this.zaqf)).add("typeInArray", Boolean.valueOf(this.zaqg)).add("typeOut", Integer.valueOf(this.zaqh)).add("typeOutArray", Boolean.valueOf(this.zaqi)).add("outputFieldName", this.zaqj).add("safeParcelFieldId", Integer.valueOf(this.zaqk)).add("concreteTypeName", zack());
            Class<? extends FastJsonResponse> cls = this.zaql;
            if (cls != null) {
                add.add("concreteType.class", cls.getCanonicalName());
            }
            FieldConverter<I, O> fieldConverter = this.zaqo;
            if (fieldConverter != null) {
                add.add("converterName", fieldConverter.getClass().getCanonicalName());
            }
            return add.toString();
        }
    }

    private final <I, O> void zaa(Field<I, O> field, I i) {
        String str = field.zaqj;
        O convert = field.convert(i);
        switch (field.zaqh) {
            case 0:
                if (zaa(str, convert)) {
                    setIntegerInternal(field, str, ((Integer) convert).intValue());
                    return;
                }
                return;
            case 1:
                zaa((Field<?, ?>) field, str, (BigInteger) convert);
                return;
            case 2:
                if (zaa(str, convert)) {
                    setLongInternal(field, str, ((Long) convert).longValue());
                    return;
                }
                return;
            case 4:
                if (zaa(str, convert)) {
                    zaa((Field<?, ?>) field, str, ((Double) convert).doubleValue());
                    return;
                }
                return;
            case 5:
                zaa((Field<?, ?>) field, str, (BigDecimal) convert);
                return;
            case 6:
                if (zaa(str, convert)) {
                    setBooleanInternal(field, str, ((Boolean) convert).booleanValue());
                    return;
                }
                return;
            case 7:
                setStringInternal(field, str, (String) convert);
                return;
            case 8:
            case 9:
                if (zaa(str, convert)) {
                    setDecodedBytesInternal(field, str, (byte[]) convert);
                    return;
                }
                return;
            default:
                int i2 = field.zaqh;
                StringBuilder sb = new StringBuilder(44);
                sb.append("Unsupported type for conversion: ");
                sb.append(i2);
                throw new IllegalStateException(sb.toString());
        }
    }

    protected static <O, I> I zab(Field<I, O> field, Object obj) {
        return field.zaqo != null ? field.convertBack(obj) : obj;
    }

    public final <O> void zaa(Field<Integer, O> field, int i) {
        if (field.zaqo != null) {
            zaa(field, Integer.valueOf(i));
        } else {
            setIntegerInternal(field, field.zaqj, i);
        }
    }

    public final <O> void zaa(Field<ArrayList<Integer>, O> field, ArrayList<Integer> arrayList) {
        if (field.zaqo != null) {
            zaa(field, arrayList);
        } else {
            zaa((Field<?, ?>) field, field.zaqj, arrayList);
        }
    }

    public final <O> void zaa(Field<BigInteger, O> field, BigInteger bigInteger) {
        if (field.zaqo != null) {
            zaa(field, bigInteger);
        } else {
            zaa((Field<?, ?>) field, field.zaqj, bigInteger);
        }
    }

    public final <O> void zab(Field<ArrayList<BigInteger>, O> field, ArrayList<BigInteger> arrayList) {
        if (field.zaqo != null) {
            zaa(field, arrayList);
        } else {
            zab(field, field.zaqj, arrayList);
        }
    }

    public final <O> void zaa(Field<Long, O> field, long j) {
        if (field.zaqo != null) {
            zaa(field, Long.valueOf(j));
        } else {
            setLongInternal(field, field.zaqj, j);
        }
    }

    public final <O> void zac(Field<ArrayList<Long>, O> field, ArrayList<Long> arrayList) {
        if (field.zaqo != null) {
            zaa(field, arrayList);
        } else {
            zac(field, field.zaqj, arrayList);
        }
    }

    public final <O> void zaa(Field<Float, O> field, float f) {
        if (field.zaqo != null) {
            zaa(field, Float.valueOf(f));
        } else {
            zaa((Field<?, ?>) field, field.zaqj, f);
        }
    }

    public final <O> void zad(Field<ArrayList<Float>, O> field, ArrayList<Float> arrayList) {
        if (field.zaqo != null) {
            zaa(field, arrayList);
        } else {
            zad(field, field.zaqj, arrayList);
        }
    }

    public final <O> void zaa(Field<Double, O> field, double d) {
        if (field.zaqo != null) {
            zaa(field, Double.valueOf(d));
        } else {
            zaa((Field<?, ?>) field, field.zaqj, d);
        }
    }

    public final <O> void zae(Field<ArrayList<Double>, O> field, ArrayList<Double> arrayList) {
        if (field.zaqo != null) {
            zaa(field, arrayList);
        } else {
            zae(field, field.zaqj, arrayList);
        }
    }

    public final <O> void zaa(Field<BigDecimal, O> field, BigDecimal bigDecimal) {
        if (field.zaqo != null) {
            zaa(field, bigDecimal);
        } else {
            zaa((Field<?, ?>) field, field.zaqj, bigDecimal);
        }
    }

    public final <O> void zaf(Field<ArrayList<BigDecimal>, O> field, ArrayList<BigDecimal> arrayList) {
        if (field.zaqo != null) {
            zaa(field, arrayList);
        } else {
            zaf(field, field.zaqj, arrayList);
        }
    }

    public final <O> void zaa(Field<Boolean, O> field, boolean z) {
        if (field.zaqo != null) {
            zaa(field, Boolean.valueOf(z));
        } else {
            setBooleanInternal(field, field.zaqj, z);
        }
    }

    public final <O> void zag(Field<ArrayList<Boolean>, O> field, ArrayList<Boolean> arrayList) {
        if (field.zaqo != null) {
            zaa(field, arrayList);
        } else {
            zag(field, field.zaqj, arrayList);
        }
    }

    public final <O> void zaa(Field<String, O> field, String str) {
        if (field.zaqo != null) {
            zaa(field, str);
        } else {
            setStringInternal(field, field.zaqj, str);
        }
    }

    public final <O> void zah(Field<ArrayList<String>, O> field, ArrayList<String> arrayList) {
        if (field.zaqo != null) {
            zaa(field, arrayList);
        } else {
            setStringsInternal(field, field.zaqj, arrayList);
        }
    }

    public final <O> void zaa(Field<byte[], O> field, byte[] bArr) {
        if (field.zaqo != null) {
            zaa(field, bArr);
        } else {
            setDecodedBytesInternal(field, field.zaqj, bArr);
        }
    }

    public final <O> void zaa(Field<Map<String, String>, O> field, Map<String, String> map) {
        if (field.zaqo != null) {
            zaa(field, map);
        } else {
            setStringMapInternal(field, field.zaqj, map);
        }
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public void setIntegerInternal(Field<?, ?> field, String str, int i) {
        throw new UnsupportedOperationException("Integer not supported");
    }

    /* access modifiers changed from: protected */
    public void zaa(Field<?, ?> field, String str, ArrayList<Integer> arrayList) {
        throw new UnsupportedOperationException("Integer list not supported");
    }

    /* access modifiers changed from: protected */
    public void zaa(Field<?, ?> field, String str, BigInteger bigInteger) {
        throw new UnsupportedOperationException("BigInteger not supported");
    }

    /* access modifiers changed from: protected */
    public void zab(Field<?, ?> field, String str, ArrayList<BigInteger> arrayList) {
        throw new UnsupportedOperationException("BigInteger list not supported");
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public void setLongInternal(Field<?, ?> field, String str, long j) {
        throw new UnsupportedOperationException("Long not supported");
    }

    /* access modifiers changed from: protected */
    public void zac(Field<?, ?> field, String str, ArrayList<Long> arrayList) {
        throw new UnsupportedOperationException("Long list not supported");
    }

    /* access modifiers changed from: protected */
    public void zaa(Field<?, ?> field, String str, float f) {
        throw new UnsupportedOperationException("Float not supported");
    }

    /* access modifiers changed from: protected */
    public void zad(Field<?, ?> field, String str, ArrayList<Float> arrayList) {
        throw new UnsupportedOperationException("Float list not supported");
    }

    /* access modifiers changed from: protected */
    public void zaa(Field<?, ?> field, String str, double d) {
        throw new UnsupportedOperationException("Double not supported");
    }

    /* access modifiers changed from: protected */
    public void zae(Field<?, ?> field, String str, ArrayList<Double> arrayList) {
        throw new UnsupportedOperationException("Double list not supported");
    }

    /* access modifiers changed from: protected */
    public void zaa(Field<?, ?> field, String str, BigDecimal bigDecimal) {
        throw new UnsupportedOperationException("BigDecimal not supported");
    }

    /* access modifiers changed from: protected */
    public void zaf(Field<?, ?> field, String str, ArrayList<BigDecimal> arrayList) {
        throw new UnsupportedOperationException("BigDecimal list not supported");
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public void setBooleanInternal(Field<?, ?> field, String str, boolean z) {
        throw new UnsupportedOperationException("Boolean not supported");
    }

    /* access modifiers changed from: protected */
    public void zag(Field<?, ?> field, String str, ArrayList<Boolean> arrayList) {
        throw new UnsupportedOperationException("Boolean list not supported");
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public void setStringInternal(Field<?, ?> field, String str, String str2) {
        throw new UnsupportedOperationException("String not supported");
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public void setStringsInternal(Field<?, ?> field, String str, ArrayList<String> arrayList) {
        throw new UnsupportedOperationException("String list not supported");
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public void setDecodedBytesInternal(Field<?, ?> field, String str, byte[] bArr) {
        throw new UnsupportedOperationException("byte[] not supported");
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public void setStringMapInternal(Field<?, ?> field, String str, Map<String, String> map) {
        throw new UnsupportedOperationException("String map not supported");
    }

    private static <O> boolean zaa(String str, O o) {
        if (o != null) {
            return true;
        }
        if (!Log.isLoggable("FastJsonResponse", 6)) {
            return false;
        }
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 58);
        sb.append("Output field (");
        sb.append(str);
        sb.append(") has a null value, but expected a primitive");
        Log.e("FastJsonResponse", sb.toString());
        return false;
    }

    @KeepForSdk
    public <T extends FastJsonResponse> void addConcreteTypeInternal(Field<?, ?> field, String str, T t) {
        throw new UnsupportedOperationException("Concrete type not supported");
    }

    @KeepForSdk
    public <T extends FastJsonResponse> void addConcreteTypeArrayInternal(Field<?, ?> field, String str, ArrayList<T> arrayList) {
        throw new UnsupportedOperationException("Concrete type array not supported");
    }

    @KeepForSdk
    public String toString() {
        Map<String, Field<?, ?>> fieldMappings = getFieldMappings();
        StringBuilder sb = new StringBuilder(100);
        for (String next : fieldMappings.keySet()) {
            Field field = fieldMappings.get(next);
            if (isFieldSet(field)) {
                Object zab = zab(field, getFieldValue(field));
                if (sb.length() == 0) {
                    sb.append("{");
                } else {
                    sb.append(",");
                }
                sb.append("\"");
                sb.append(next);
                sb.append("\":");
                if (zab != null) {
                    switch (field.zaqh) {
                        case 8:
                            sb.append("\"");
                            sb.append(Base64Utils.encode((byte[]) zab));
                            sb.append("\"");
                            break;
                        case 9:
                            sb.append("\"");
                            sb.append(Base64Utils.encodeUrlSafe((byte[]) zab));
                            sb.append("\"");
                            break;
                        case 10:
                            MapUtils.writeStringMapToJson(sb, (HashMap) zab);
                            break;
                        default:
                            if (!field.zaqg) {
                                zaa(sb, field, zab);
                                break;
                            } else {
                                ArrayList arrayList = (ArrayList) zab;
                                sb.append("[");
                                int size = arrayList.size();
                                for (int i = 0; i < size; i++) {
                                    if (i > 0) {
                                        sb.append(",");
                                    }
                                    Object obj = arrayList.get(i);
                                    if (obj != null) {
                                        zaa(sb, field, obj);
                                    }
                                }
                                sb.append("]");
                                break;
                            }
                    }
                } else {
                    sb.append("null");
                }
            }
        }
        if (sb.length() > 0) {
            sb.append("}");
        } else {
            sb.append("{}");
        }
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public Object getFieldValue(Field field) {
        String str = field.zaqj;
        if (field.zaql == null) {
            return getValueObject(field.zaqj);
        }
        Preconditions.checkState(getValueObject(field.zaqj) == null, "Concrete field shouldn't be value object: %s", field.zaqj);
        boolean z = field.zaqi;
        try {
            char upperCase = Character.toUpperCase(str.charAt(0));
            String substring = str.substring(1);
            StringBuilder sb = new StringBuilder(String.valueOf(substring).length() + 4);
            sb.append("get");
            sb.append(upperCase);
            sb.append(substring);
            return getClass().getMethod(sb.toString(), new Class[0]).invoke(this, new Object[0]);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void zaa(StringBuilder sb, Field field, Object obj) {
        if (field.zaqf == 11) {
            sb.append(((FastJsonResponse) field.zaql.cast(obj)).toString());
        } else if (field.zaqf == 7) {
            sb.append("\"");
            sb.append(JsonUtils.escapeString((String) obj));
            sb.append("\"");
        } else {
            sb.append(obj);
        }
    }
}
