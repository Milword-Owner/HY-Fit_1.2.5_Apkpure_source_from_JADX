package com.bigkoo.pickerview.adapter;

import com.contrarywind.adapter.WheelAdapter;

public class NumericWheelAdapter implements WheelAdapter {
    private int maxValue;
    private int minValue;

    public NumericWheelAdapter(int i, int i2) {
        this.minValue = i;
        this.maxValue = i2;
    }

    public Object getItem(int i) {
        if (i < 0 || i >= getItemsCount()) {
            return 0;
        }
        return Integer.valueOf(this.minValue + i);
    }

    public int getItemsCount() {
        return (this.maxValue - this.minValue) + 1;
    }

    public int indexOf(Object obj) {
        try {
            return ((Integer) obj).intValue() - this.minValue;
        } catch (Exception unused) {
            return -1;
        }
    }
}
