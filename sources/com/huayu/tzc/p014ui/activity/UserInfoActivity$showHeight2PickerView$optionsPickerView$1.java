package com.huayu.tzc.p014ui.activity;

import android.view.View;
import android.widget.TextView;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import java.util.List;
import kotlin.Metadata;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00032\u000e\u0010\u0006\u001a\n \b*\u0004\u0018\u00010\u00070\u0007H\n¢\u0006\u0002\b\t"}, mo21895d2 = {"<anonymous>", "", "options1", "", "options2", "<anonymous parameter 2>", "<anonymous parameter 3>", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onOptionsSelect"}, mo21896k = 3, mo21897mv = {1, 1, 16})
/* renamed from: com.huayu.tzc.ui.activity.UserInfoActivity$showHeight2PickerView$optionsPickerView$1 */
/* compiled from: UserInfoActivity.kt */
final class UserInfoActivity$showHeight2PickerView$optionsPickerView$1 implements OnOptionsSelectListener {
    final /* synthetic */ List $dataList;
    final /* synthetic */ List $dataList2;
    final /* synthetic */ TextView $textView;

    UserInfoActivity$showHeight2PickerView$optionsPickerView$1(TextView textView, List list, List list2) {
        this.$textView = textView;
        this.$dataList = list;
        this.$dataList2 = list2;
    }

    public final void onOptionsSelect(int i, int i2, int i3, View view) {
        TextView textView = this.$textView;
        textView.setText(((String) this.$dataList.get(i)) + "'" + ((String) this.$dataList2.get(i2)) + "\"");
    }
}
