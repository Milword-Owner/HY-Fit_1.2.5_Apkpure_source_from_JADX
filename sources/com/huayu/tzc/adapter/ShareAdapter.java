package com.huayu.tzc.adapter;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.huayu.tzc.C2128R;
import com.huayu.tzc.bean.Share;
import com.huayu.tzc.utils.Utils;
import java.util.List;
import p036de.hdodenhof.circleimageview.CircleImageView;

public class ShareAdapter extends BaseMultiItemQuickAdapter<Share, BaseViewHolder> {
    public ShareAdapter(List<Share> list) {
        super(list);
        addItemType(1, C2128R.C2133layout.share_header);
        addItemType(2, C2128R.C2133layout.share_item);
        addItemType(3, C2128R.C2133layout.share_footer);
    }

    /* access modifiers changed from: protected */
    public void convert(BaseViewHolder baseViewHolder, Share share) {
        int itemViewType = baseViewHolder.getItemViewType();
        if (itemViewType == 1) {
            CircleImageView circleImageView = (CircleImageView) baseViewHolder.getView(C2128R.C2131id.share_header);
            if (!TextUtils.isEmpty(share.getMember().getM_avatar())) {
                Glide.with(getContext()).load(share.getMember().getM_avatar()).into((ImageView) circleImageView);
            } else {
                circleImageView.setImageResource(Utils.getImgRes(share.getMember().getM_gender()));
            }
            baseViewHolder.setText((int) C2128R.C2131id.share_date, (CharSequence) share.getTime());
        } else if (itemViewType == 2) {
            baseViewHolder.setText((int) C2128R.C2131id.share_title, (CharSequence) share.getHistoryInfo().getName()).setImageResource(C2128R.C2131id.share_img, share.getHistoryInfo().getImg());
            if (share.getHistoryInfo().getNum() >= 0.0f) {
                GradientDrawable gradientDrawable = (GradientDrawable) baseViewHolder.getView(C2128R.C2131id.share_state).getBackground();
                if (!TextUtils.isEmpty(share.getHistoryInfo().getColor())) {
                    gradientDrawable.setColor(Color.parseColor(share.getHistoryInfo().getColor()));
                }
                baseViewHolder.setText((int) C2128R.C2131id.share_content, (CharSequence) share.getHistoryInfo().getContent()).setText((int) C2128R.C2131id.share_state, (CharSequence) share.getHistoryInfo().getState()).setVisible(C2128R.C2131id.share_state, true);
            } else {
                baseViewHolder.setText((int) C2128R.C2131id.share_content, (CharSequence) "--");
            }
            if (TextUtils.isEmpty(share.getHistoryInfo().getState()) || share.getHistoryInfo().getNum() <= 0.0f) {
                baseViewHolder.setGone(C2128R.C2131id.share_state, true);
            }
        }
    }
}
