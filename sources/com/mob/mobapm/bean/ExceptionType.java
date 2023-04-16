package com.mob.mobapm.bean;

public enum ExceptionType {
    CRASH("CRASH"),
    STUCK("STUCK");
    
    public String name;

    private ExceptionType(String str) {
        this.name = str;
    }

    public static ExceptionType valuesOf(String str) {
        for (ExceptionType exceptionType : values()) {
            if (exceptionType.name.equals(str)) {
                return exceptionType;
            }
        }
        return CRASH;
    }
}
