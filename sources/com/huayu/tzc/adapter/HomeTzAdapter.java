package com.huayu.tzc.adapter;

import android.graphics.Color;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.huayu.tzc.C2128R;
import com.huayu.tzc.bean.TzContent;
import java.util.List;

public class HomeTzAdapter extends BaseMultiItemQuickAdapter<TzContent, BaseViewHolder> {
    public HomeTzAdapter(List<TzContent> list) {
        super(list);
        addItemType(1, C2128R.C2133layout.recyclerview_home_tz);
        addItemType(2, C2128R.C2133layout.recyclerview_home_content);
        addItemType(3, C2128R.C2133layout.recyclerview_null);
    }

    /* access modifiers changed from: protected */
    public void convert(BaseViewHolder baseViewHolder, TzContent tzContent) {
        int itemViewType = baseViewHolder.getItemViewType();
        if (itemViewType == 1) {
            BaseViewHolder text = baseViewHolder.setText((int) C2128R.C2131id.home_title, (CharSequence) tzContent.getTitle());
            text.setText((int) C2128R.C2131id.home_num, (CharSequence) tzContent.getContent() + tzContent.getUnit()).setTextColor(C2128R.C2131id.home_num, Color.parseColor(tzContent.getColor()));
            if (tzContent.getValue() < 0.0f && tzContent.getValue() != -2.0f) {
                baseViewHolder.setText((int) C2128R.C2131id.home_num, (CharSequence) "--");
            }
        } else if (itemViewType == 2) {
            baseViewHolder.setGone(C2128R.C2131id.home_text, true).setGone(C2128R.C2131id.home_view, true);
        } else if (itemViewType == 3) {
            baseViewHolder.setVisible(C2128R.C2131id.homeNull, true);
        }
    }
}
