package com.bigkoo.pickerview.adapter;

import com.contrarywind.adapter.WheelAdapter;
import java.util.List;

public class ArrayWheelAdapter<T> implements WheelAdapter {
    private List<T> items;

    public ArrayWheelAdapter(List<T> list) {
        this.items = list;
    }

    public Object getItem(int i) {
        return (i < 0 || i >= this.items.size()) ? "" : this.items.get(i);
    }

    public int getItemsCount() {
        return this.items.size();
    }

    public int indexOf(Object obj) {
        return this.items.indexOf(obj);
    }
}
