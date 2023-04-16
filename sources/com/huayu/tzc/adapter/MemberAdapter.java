package com.huayu.tzc.adapter;

import android.text.TextUtils;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.huayu.tzc.C2128R;
import com.huayu.tzc.bean.Member;
import java.util.List;
import p036de.hdodenhof.circleimageview.CircleImageView;

public class MemberAdapter extends BaseQuickAdapter<Member, BaseViewHolder> {
    public MemberAdapter(@Nullable List<Member> list) {
        super(C2128R.C2133layout.recyclerview_add, list);
    }

    /* access modifiers changed from: protected */
    public void convert(BaseViewHolder baseViewHolder, Member member) {
        baseViewHolder.setText((int) C2128R.C2131id.add_text, (CharSequence) member.getM_nickname());
        if (member.getFlag() == 1) {
            Glide.with(getContext()).load(Integer.valueOf(C2128R.C2130drawable.ic_add)).into((ImageView) (CircleImageView) baseViewHolder.getView(C2128R.C2131id.add_img));
        } else if (!TextUtils.isEmpty(member.getM_avatar()) || member.getFlag() != 0) {
            if (!TextUtils.isEmpty(member.getM_avatar()) && member.getFlag() == 0) {
                Glide.with(getContext()).load(member.getM_avatar()).into((ImageView) (CircleImageView) baseViewHolder.getView(C2128R.C2131id.add_img));
            }
        } else if (member.getM_gender() == 1) {
            baseViewHolder.setImageResource(C2128R.C2131id.add_img, C2128R.C2130drawable.ic_woman_b);
        } else if (member.getM_gender() == 0) {
            baseViewHolder.setImageResource(C2128R.C2131id.add_img, C2128R.C2130drawable.ic_man_b);
        }
    }
}
