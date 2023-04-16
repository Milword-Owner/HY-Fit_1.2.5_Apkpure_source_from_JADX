package com.huayu.tzc.p014ui.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.huayu.tzc.C2128R;
import com.huayu.tzc.bean.Measure;
import com.huayu.tzc.presenter.HomePresenter;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import org.jetbrains.annotations.NotNull;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\n¢\u0006\u0002\b\b"}, mo21895d2 = {"<anonymous>", "", "adapter", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "<anonymous parameter 1>", "Landroid/view/View;", "position", "", "onItemClick"}, mo21896k = 3, mo21897mv = {1, 1, 16})
/* renamed from: com.huayu.tzc.ui.activity.DeviceActivity$initAdapter$1 */
/* compiled from: DeviceActivity.kt */
final class DeviceActivity$initAdapter$1 implements OnItemClickListener {
    final /* synthetic */ DeviceActivity this$0;

    DeviceActivity$initAdapter$1(DeviceActivity deviceActivity) {
        this.this$0 = deviceActivity;
    }

    public final void onItemClick(@NotNull BaseQuickAdapter<?, ?> baseQuickAdapter, @NotNull View view, int i) {
        Intrinsics.checkParameterIsNotNull(baseQuickAdapter, "adapter");
        Intrinsics.checkParameterIsNotNull(view, "<anonymous parameter 1>");
        if (!StringsKt.equals(this.this$0.mac, ((Measure) this.this$0.deviceList.get(i)).getDevmac(), true)) {
            DeviceActivity deviceActivity = this.this$0;
            deviceActivity.mac = ((Measure) deviceActivity.deviceList.get(i)).getDevmac();
            TextView textView = (TextView) this.this$0._$_findCachedViewById(C2128R.C2131id.device_mac);
            Intrinsics.checkExpressionValueIsNotNull(textView, "device_mac");
            textView.setText(this.this$0.getString(C2128R.string.mac) + ((Measure) this.this$0.deviceList.get(i)).getDevmac());
            ImageView imageView = (ImageView) this.this$0._$_findCachedViewById(C2128R.C2131id.device_ok);
            Intrinsics.checkExpressionValueIsNotNull(imageView, "device_ok");
            imageView.setVisibility(0);
            int size = this.this$0.deviceList.size();
            for (int i2 = 0; i2 < size; i2++) {
                ((Measure) this.this$0.deviceList.get(i2)).setConnect(false);
            }
            ((Measure) this.this$0.deviceList.get(i)).setConnect(true);
            baseQuickAdapter.notifyDataSetChanged();
            this.this$0.progressShow();
            FormBody build = new FormBody.Builder().add("mac", ((Measure) this.this$0.deviceList.get(i)).getMacData()).add("device_type", String.valueOf(1)).add("device_name", "智能体脂秤").add("blu_name", "Chipsea-BLE").build();
            Intrinsics.checkExpressionValueIsNotNull(build, "FormBody.Builder()\n     …                 .build()");
            RequestBody requestBody = build;
            HomePresenter access$getMPresenter$p = DeviceActivity.access$getMPresenter$p(this.this$0);
            if (access$getMPresenter$p != null) {
                access$getMPresenter$p.reporting(requestBody);
            }
        }
    }
}
