package com.huayu.tzc.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.huayu.tzc.C2128R;
import com.huayu.tzc.bean.History;
import com.huayu.tzc.customview.MyImageView;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class HistoryAdapter extends BaseMultiItemQuickAdapter<History, BaseViewHolder> implements LoadMoreModule {
    public HistoryAdapter(@Nullable List<History> list) {
        super(list);
        addItemType(1, C2128R.C2133layout.history_header);
        addItemType(2, C2128R.C2133layout.history_content);
        addChildClickViewIds(C2128R.C2131id.history_item_layout, C2128R.C2131id.historySelect);
    }

    /* access modifiers changed from: protected */
    public void convert(@NotNull BaseViewHolder baseViewHolder, History history) {
        int itemViewType = baseViewHolder.getItemViewType();
        if (itemViewType == 1) {
            baseViewHolder.setText((int) C2128R.C2131id.historyTime, (CharSequence) history.getTime());
        } else if (itemViewType == 2) {
            BaseViewHolder text = baseViewHolder.setText((int) C2128R.C2131id.history_info_month, (CharSequence) monthToString(history.getMeasure().getMeasureDate())).setText((int) C2128R.C2131id.history_info_hour, (CharSequence) hourToString(history.getMeasure().getMeasureDate()));
            BaseViewHolder text2 = text.setText((int) C2128R.C2131id.history_info_weight, (CharSequence) history.getMeasure().getWeight() + history.getMeasure().getWeightunit());
            BaseViewHolder text3 = text2.setText((int) C2128R.C2131id.history_info_tz, (CharSequence) history.getMeasure().getBodyfat_rate() + "%");
            text3.setText((int) C2128R.C2131id.history_info_bmi, (CharSequence) history.getMeasure().getBmi() + "");
            MyImageView myImageView = (MyImageView) baseViewHolder.getView(C2128R.C2131id.historySelect);
            myImageView.setChecked(history.getSelect());
            if (history.getShow()) {
                myImageView.setVisibility(0);
            } else {
                myImageView.setVisibility(8);
            }
        }
    }

    public String monthToString(Date date) {
        return new SimpleDateFormat("MM-dd", Locale.ENGLISH).format(date);
    }

    public String hourToString(Date date) {
        return new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH).format(date);
    }
}
