package com.mob.mobapm.bean;

public enum CarrierType {
    YD1("46000", "移动"),
    YD2("46002", "移动"),
    YD3("46004", "移动"),
    YD4("46007", "移动"),
    LT1("46001", "联通"),
    LT2("46006", "联通"),
    LT3("46009", "联通"),
    DX1("46003", "电信"),
    DX2("46005", "电信"),
    DX3("460011", "电信"),
    UNKNOWN("0", "未知");
    

    /* renamed from: a */
    String f2237a;
    public String name;

    private CarrierType(String str, String str2) {
        this.f2237a = str;
        this.name = str2;
    }

    public static CarrierType valuesOf(String str) {
        for (CarrierType carrierType : values()) {
            if (carrierType.f2237a.equals(str)) {
                return carrierType;
            }
        }
        return UNKNOWN;
    }
}
