package com.huayu.tzc.adapter;

import android.text.TextUtils;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.huayu.tzc.C2128R;
import com.huayu.tzc.bean.MsgBean;
import com.huayu.tzc.bean.User;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import p036de.hdodenhof.circleimageview.CircleImageView;

public class MsgAdapter extends BaseQuickAdapter<MsgBean, BaseViewHolder> {
    private User userBean;

    public MsgAdapter(@Nullable List<MsgBean> list, User user) {
        super(C2128R.C2133layout.recyclerview_msg, list);
        this.userBean = user;
        addChildClickViewIds(C2128R.C2131id.msg_kf_img, C2128R.C2131id.msg_img);
    }

    public void setUserBean(User user) {
        this.userBean = user;
    }

    /* access modifiers changed from: protected */
    public void convert(@NotNull BaseViewHolder baseViewHolder, MsgBean msgBean) {
        int type = msgBean.getType();
        if (type == 0) {
            baseViewHolder.setVisible(C2128R.C2131id.msg_left, true).setGone(C2128R.C2131id.msg_date, true).setGone(C2128R.C2131id.msg_right, true);
            if (msgBean.getMsgtype() == 2) {
                baseViewHolder.setGone(C2128R.C2131id.msg_service, true).setVisible(C2128R.C2131id.msg_kf_img, true);
                ((RequestBuilder) Glide.with(getContext()).load(msgBean.getMsg()).placeholder((int) C2128R.C2130drawable.ic_placeholder)).into((ImageView) baseViewHolder.getView(C2128R.C2131id.msg_kf_img));
                return;
            }
            baseViewHolder.setGone(C2128R.C2131id.msg_kf_img, true).setVisible(C2128R.C2131id.msg_service, true);
            baseViewHolder.setText((int) C2128R.C2131id.msg_service, (CharSequence) msgBean.getMsg());
        } else if (type != 1) {
            baseViewHolder.setGone(C2128R.C2131id.msg_left, true).setVisible(C2128R.C2131id.msg_date, true).setGone(C2128R.C2131id.msg_right, true).setText((int) C2128R.C2131id.msg_date, (CharSequence) msgBean.getMsg());
        } else {
            baseViewHolder.setGone(C2128R.C2131id.msg_left, true).setGone(C2128R.C2131id.msg_date, true).setVisible(C2128R.C2131id.msg_right, true);
            if (msgBean.getMsgtype() == 2) {
                baseViewHolder.setGone(C2128R.C2131id.msg_user, true).setVisible(C2128R.C2131id.msg_img, true);
                ((RequestBuilder) Glide.with(getContext()).load(msgBean.getMsg()).placeholder((int) C2128R.C2130drawable.ic_placeholder)).into((ImageView) baseViewHolder.getView(C2128R.C2131id.msg_img));
            } else {
                baseViewHolder.setVisible(C2128R.C2131id.msg_user, true).setGone(C2128R.C2131id.msg_img, true).setText((int) C2128R.C2131id.msg_user, (CharSequence) msgBean.getMsg());
            }
            CircleImageView circleImageView = (CircleImageView) baseViewHolder.getView(C2128R.C2131id.msg_header);
            User user = this.userBean;
            if (user == null) {
                return;
            }
            if (!TextUtils.isEmpty(user.getU_avatar())) {
                Glide.with(getContext()).load(this.userBean.getU_avatar()).into((ImageView) circleImageView);
            } else if (TextUtils.isEmpty(this.userBean.getU_avatar()) && this.userBean.getU_gender() == 1) {
                circleImageView.setImageResource(C2128R.C2130drawable.ic_woman_b);
            } else if (TextUtils.isEmpty(this.userBean.getU_avatar()) && this.userBean.getU_gender() == 0) {
                circleImageView.setImageResource(C2128R.C2130drawable.ic_man_b);
            }
        }
    }
}
