package com.huayu.tzc.adapter;

import android.widget.Switch;
import androidx.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.huayu.tzc.C2128R;
import com.huayu.tzc.bean.Remind;
import java.util.List;

public class RemindAdapter extends BaseQuickAdapter<Remind, BaseViewHolder> {
    public RemindAdapter(@Nullable List<Remind> list) {
        super(C2128R.C2133layout.recyclerview_remind, list);
        addChildClickViewIds(C2128R.C2131id.remind_switch);
    }

    /* access modifiers changed from: protected */
    public void convert(BaseViewHolder baseViewHolder, Remind remind) {
        baseViewHolder.setText((int) C2128R.C2131id.remind_date, (CharSequence) remind.getDate());
        ((Switch) baseViewHolder.getView(C2128R.C2131id.remind_switch)).setChecked(remind.isChecked());
        if (remind.isChecked()) {
            baseViewHolder.setText((int) C2128R.C2131id.remind_open, (CharSequence) getContext().getString(C2128R.string.open_true));
        } else {
            baseViewHolder.setText((int) C2128R.C2131id.remind_open, (CharSequence) getContext().getString(C2128R.string.open_false));
        }
    }
}
