package com.huayu.tzc.adapter;

import androidx.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.huayu.tzc.C2128R;
import com.huayu.tzc.bean.LanguageBean;
import com.huayu.tzc.customview.MyImageView;
import java.util.List;

public class LanguageAdapter extends BaseQuickAdapter<LanguageBean, BaseViewHolder> {
    public LanguageAdapter(@Nullable List<LanguageBean> list) {
        super(C2128R.C2133layout.recyclerview_language, list);
    }

    /* access modifiers changed from: protected */
    public void convert(BaseViewHolder baseViewHolder, LanguageBean languageBean) {
        baseViewHolder.setText((int) C2128R.C2131id.language_text, (CharSequence) languageBean.getText());
        ((MyImageView) baseViewHolder.getView(C2128R.C2131id.language_img)).setChecked(languageBean.isChecked());
    }
}
