package com.huayu.tzc.adapter;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.huayu.tzc.C2128R;
import com.huayu.tzc.bean.HistoryInfo;
import java.util.List;
import org.jetbrains.annotations.Nullable;

public class HistoryInfoAdapter extends BaseQuickAdapter<HistoryInfo, BaseViewHolder> {
    public HistoryInfoAdapter(@Nullable List<HistoryInfo> list) {
        super(C2128R.C2133layout.recyclerview_history_info, list);
    }

    /* access modifiers changed from: protected */
    public void convert(BaseViewHolder baseViewHolder, HistoryInfo historyInfo) {
        baseViewHolder.setText((int) C2128R.C2131id.history_info_title, (CharSequence) historyInfo.getName()).setImageResource(C2128R.C2131id.history_info_img, historyInfo.getImg());
        if (historyInfo.getNum() >= 0.0f) {
            GradientDrawable gradientDrawable = (GradientDrawable) baseViewHolder.getView(C2128R.C2131id.history_info_state).getBackground();
            if (!TextUtils.isEmpty(historyInfo.getColor())) {
                gradientDrawable.setColor(Color.parseColor(historyInfo.getColor()));
            }
            baseViewHolder.setText((int) C2128R.C2131id.history_info_content, (CharSequence) historyInfo.getContent()).setText((int) C2128R.C2131id.history_info_state, (CharSequence) historyInfo.getState()).setVisible(C2128R.C2131id.history_info_state, true);
        } else {
            baseViewHolder.setText((int) C2128R.C2131id.history_info_content, (CharSequence) "--");
        }
        if (TextUtils.isEmpty(historyInfo.getState()) || historyInfo.getNum() <= 0.0f) {
            baseViewHolder.setGone(C2128R.C2131id.history_info_state, true);
        }
    }
}
