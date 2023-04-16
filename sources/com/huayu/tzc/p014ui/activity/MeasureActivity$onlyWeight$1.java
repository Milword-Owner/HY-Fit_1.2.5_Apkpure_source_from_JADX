package com.huayu.tzc.p014ui.activity;

import com.clj.fastble.data.BleDevice;
import com.google.android.gms.fitness.FitnessStatusCodes;
import kotlin.Metadata;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, mo21895d2 = {"<anonymous>", "", "run"}, mo21896k = 3, mo21897mv = {1, 1, 16})
/* renamed from: com.huayu.tzc.ui.activity.MeasureActivity$onlyWeight$1 */
/* compiled from: MeasureActivity.kt */
final class MeasureActivity$onlyWeight$1 implements Runnable {
    final /* synthetic */ BleDevice $bleDevice;
    final /* synthetic */ byte[] $weight;
    final /* synthetic */ MeasureActivity this$0;

    MeasureActivity$onlyWeight$1(MeasureActivity measureActivity, BleDevice bleDevice, byte[] bArr) {
        this.this$0 = measureActivity;
        this.$bleDevice = bleDevice;
        this.$weight = bArr;
    }

    public final void run() {
        if (System.currentTimeMillis() - this.this$0.lastTimeStamp >= ((long) FitnessStatusCodes.NEEDS_OAUTH_PERMISSIONS)) {
            this.this$0.showOnlyWeight(this.$bleDevice, this.$weight);
        }
    }
}
