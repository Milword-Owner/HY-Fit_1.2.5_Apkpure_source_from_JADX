package com.scwang.smart.refresh.layout.constant;

public class DimensionStatus {
    public static final DimensionStatus CodeExact = new DimensionStatus(9, true);
    public static final DimensionStatus CodeExactUnNotify = new DimensionStatus(8, false);
    public static final DimensionStatus DeadLock = new DimensionStatus(10, true);
    public static final DimensionStatus DeadLockUnNotify = new DimensionStatus(10, false);
    public static final DimensionStatus Default = new DimensionStatus(1, true);
    public static final DimensionStatus DefaultUnNotify = new DimensionStatus(0, false);
    public static final DimensionStatus XmlExact = new DimensionStatus(5, true);
    public static final DimensionStatus XmlExactUnNotify = new DimensionStatus(4, false);
    public static final DimensionStatus XmlLayout = new DimensionStatus(7, true);
    public static final DimensionStatus XmlLayoutUnNotify = new DimensionStatus(6, false);
    public static final DimensionStatus XmlWrap = new DimensionStatus(3, true);
    public static final DimensionStatus XmlWrapUnNotify = new DimensionStatus(2, false);
    public static final DimensionStatus[] values = {DefaultUnNotify, Default, XmlWrapUnNotify, XmlWrap, XmlExactUnNotify, XmlExact, XmlLayoutUnNotify, XmlLayout, CodeExactUnNotify, CodeExact, DeadLockUnNotify, DeadLock};
    public final boolean notified;
    public final int ordinal;

    private DimensionStatus(int i, boolean z) {
        this.ordinal = i;
        this.notified = z;
    }

    public DimensionStatus unNotify() {
        if (!this.notified) {
            return this;
        }
        DimensionStatus dimensionStatus = values[this.ordinal - 1];
        if (!dimensionStatus.notified) {
            return dimensionStatus;
        }
        return DefaultUnNotify;
    }

    public DimensionStatus notified() {
        return !this.notified ? values[this.ordinal + 1] : this;
    }

    public boolean canReplaceWith(DimensionStatus dimensionStatus) {
        return this.ordinal < dimensionStatus.ordinal || ((!this.notified || CodeExact == this) && this.ordinal == dimensionStatus.ordinal);
    }
}
