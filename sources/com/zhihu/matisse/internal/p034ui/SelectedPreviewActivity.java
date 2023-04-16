package com.zhihu.matisse.internal.p034ui;

import android.os.Bundle;
import androidx.annotation.Nullable;
import com.zhihu.matisse.internal.entity.Item;
import com.zhihu.matisse.internal.entity.SelectionSpec;
import com.zhihu.matisse.internal.model.SelectedItemCollection;
import java.util.ArrayList;

/* renamed from: com.zhihu.matisse.internal.ui.SelectedPreviewActivity */
public class SelectedPreviewActivity extends BasePreviewActivity {
    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        if (!SelectionSpec.getInstance().hasInited) {
            setResult(0);
            finish();
            return;
        }
        ArrayList parcelableArrayList = getIntent().getBundleExtra(BasePreviewActivity.EXTRA_DEFAULT_BUNDLE).getParcelableArrayList(SelectedItemCollection.STATE_SELECTION);
        this.mAdapter.addAll(parcelableArrayList);
        this.mAdapter.notifyDataSetChanged();
        if (this.mSpec.countable) {
            this.mCheckView.setCheckedNum(1);
        } else {
            this.mCheckView.setChecked(true);
        }
        this.mPreviousPos = 0;
        updateSize((Item) parcelableArrayList.get(0));
    }
}
