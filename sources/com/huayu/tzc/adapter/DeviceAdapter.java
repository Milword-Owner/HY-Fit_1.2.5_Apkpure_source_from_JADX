package com.huayu.tzc.adapter;

import android.graphics.Color;
import androidx.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.huayu.tzc.C2128R;
import com.huayu.tzc.bean.Measure;
import java.util.List;

public class DeviceAdapter extends BaseQuickAdapter<Measure, BaseViewHolder> {
    public DeviceAdapter(@Nullable List<Measure> list) {
        super(C2128R.C2133layout.recyclerview_device, list);
    }

    /* access modifiers changed from: protected */
    public void convert(BaseViewHolder baseViewHolder, Measure measure) {
        baseViewHolder.setText((int) C2128R.C2131id.device_mac, (CharSequence) getContext().getString(C2128R.string.mac) + measure.getDevmac());
        if (measure.isConnect()) {
            baseViewHolder.setText((int) C2128R.C2131id.device_connect, (CharSequence) getContext().getString(C2128R.string.connect_true)).setTextColor(C2128R.C2131id.device_connect, Color.parseColor("#3A84FF"));
            return;
        }
        baseViewHolder.setText((int) C2128R.C2131id.device_connect, (CharSequence) getContext().getString(C2128R.string.connect_false));
        baseViewHolder.setTextColor(C2128R.C2131id.device_connect, Color.parseColor("#ff999999"));
    }
}
