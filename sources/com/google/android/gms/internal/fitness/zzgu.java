package com.google.android.gms.internal.fitness;

import java.lang.reflect.Type;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public enum zzgu {
    DOUBLE(0, zzgw.SCALAR, zzhm.DOUBLE),
    FLOAT(1, zzgw.SCALAR, zzhm.FLOAT),
    INT64(2, zzgw.SCALAR, zzhm.LONG),
    UINT64(3, zzgw.SCALAR, zzhm.LONG),
    INT32(4, zzgw.SCALAR, zzhm.INT),
    FIXED64(5, zzgw.SCALAR, zzhm.LONG),
    FIXED32(6, zzgw.SCALAR, zzhm.INT),
    BOOL(7, zzgw.SCALAR, zzhm.BOOLEAN),
    STRING(8, zzgw.SCALAR, zzhm.STRING),
    MESSAGE(9, zzgw.SCALAR, zzhm.MESSAGE),
    BYTES(10, zzgw.SCALAR, zzhm.BYTE_STRING),
    UINT32(11, zzgw.SCALAR, zzhm.INT),
    ENUM(12, zzgw.SCALAR, zzhm.ENUM),
    SFIXED32(13, zzgw.SCALAR, zzhm.INT),
    SFIXED64(14, zzgw.SCALAR, zzhm.LONG),
    SINT32(15, zzgw.SCALAR, zzhm.INT),
    SINT64(16, zzgw.SCALAR, zzhm.LONG),
    GROUP(17, zzgw.SCALAR, zzhm.MESSAGE),
    DOUBLE_LIST(18, zzgw.VECTOR, zzhm.DOUBLE),
    FLOAT_LIST(19, zzgw.VECTOR, zzhm.FLOAT),
    INT64_LIST(20, zzgw.VECTOR, zzhm.LONG),
    UINT64_LIST(21, zzgw.VECTOR, zzhm.LONG),
    INT32_LIST(22, zzgw.VECTOR, zzhm.INT),
    FIXED64_LIST(23, zzgw.VECTOR, zzhm.LONG),
    FIXED32_LIST(24, zzgw.VECTOR, zzhm.INT),
    BOOL_LIST(25, zzgw.VECTOR, zzhm.BOOLEAN),
    STRING_LIST(26, zzgw.VECTOR, zzhm.STRING),
    MESSAGE_LIST(27, zzgw.VECTOR, zzhm.MESSAGE),
    BYTES_LIST(28, zzgw.VECTOR, zzhm.BYTE_STRING),
    UINT32_LIST(29, zzgw.VECTOR, zzhm.INT),
    ENUM_LIST(30, zzgw.VECTOR, zzhm.ENUM),
    SFIXED32_LIST(31, zzgw.VECTOR, zzhm.INT),
    SFIXED64_LIST(32, zzgw.VECTOR, zzhm.LONG),
    SINT32_LIST(33, zzgw.VECTOR, zzhm.INT),
    SINT64_LIST(34, zzgw.VECTOR, zzhm.LONG),
    DOUBLE_LIST_PACKED(35, zzgw.PACKED_VECTOR, zzhm.DOUBLE),
    FLOAT_LIST_PACKED(36, zzgw.PACKED_VECTOR, zzhm.FLOAT),
    INT64_LIST_PACKED(37, zzgw.PACKED_VECTOR, zzhm.LONG),
    UINT64_LIST_PACKED(38, zzgw.PACKED_VECTOR, zzhm.LONG),
    INT32_LIST_PACKED(39, zzgw.PACKED_VECTOR, zzhm.INT),
    FIXED64_LIST_PACKED(40, zzgw.PACKED_VECTOR, zzhm.LONG),
    FIXED32_LIST_PACKED(41, zzgw.PACKED_VECTOR, zzhm.INT),
    BOOL_LIST_PACKED(42, zzgw.PACKED_VECTOR, zzhm.BOOLEAN),
    UINT32_LIST_PACKED(43, zzgw.PACKED_VECTOR, zzhm.INT),
    ENUM_LIST_PACKED(44, zzgw.PACKED_VECTOR, zzhm.ENUM),
    SFIXED32_LIST_PACKED(45, zzgw.PACKED_VECTOR, zzhm.INT),
    SFIXED64_LIST_PACKED(46, zzgw.PACKED_VECTOR, zzhm.LONG),
    SINT32_LIST_PACKED(47, zzgw.PACKED_VECTOR, zzhm.INT),
    SINT64_LIST_PACKED(48, zzgw.PACKED_VECTOR, zzhm.LONG),
    GROUP_LIST(49, zzgw.VECTOR, zzhm.MESSAGE),
    MAP(50, zzgw.MAP, zzhm.VOID);
    
    private static final zzgu[] zzxi = null;
    private static final Type[] zzxj = null;

    /* renamed from: id */
    private final int f1595id;
    private final zzhm zzxe;
    private final zzgw zzxf;
    private final Class<?> zzxg;
    private final boolean zzxh;

    private zzgu(int i, zzgw zzgw, zzhm zzhm) {
        int i2;
        this.f1595id = i;
        this.zzxf = zzgw;
        this.zzxe = zzhm;
        int i3 = zzgx.zzxr[zzgw.ordinal()];
        if (i3 == 1) {
            this.zzxg = zzhm.zzcf();
        } else if (i3 != 2) {
            this.zzxg = null;
        } else {
            this.zzxg = zzhm.zzcf();
        }
        boolean z = false;
        if (!(zzgw != zzgw.SCALAR || (i2 = zzgx.zzxs[zzhm.ordinal()]) == 1 || i2 == 2 || i2 == 3)) {
            z = true;
        }
        this.zzxh = z;
    }

    /* renamed from: id */
    public final int mo23211id() {
        return this.f1595id;
    }

    static {
        int i;
        zzxj = new Type[0];
        zzgu[] values = values();
        zzxi = new zzgu[values.length];
        for (zzgu zzgu : values) {
            zzxi[zzgu.f1595id] = zzgu;
        }
    }
}
