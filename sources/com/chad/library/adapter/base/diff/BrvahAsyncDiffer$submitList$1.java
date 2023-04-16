package com.chad.library.adapter.base.diff;

import androidx.recyclerview.widget.DiffUtil;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002H\n¢\u0006\u0002\b\u0003"}, mo21895d2 = {"<anonymous>", "", "T", "run"}, mo21896k = 3, mo21897mv = {1, 1, 16})
/* compiled from: BrvahAsyncDiffer.kt */
final class BrvahAsyncDiffer$submitList$1 implements Runnable {
    final /* synthetic */ Runnable $commitCallback;
    final /* synthetic */ List $newList;
    final /* synthetic */ List $oldList;
    final /* synthetic */ int $runGeneration;
    final /* synthetic */ BrvahAsyncDiffer this$0;

    BrvahAsyncDiffer$submitList$1(BrvahAsyncDiffer brvahAsyncDiffer, List list, List list2, int i, Runnable runnable) {
        this.this$0 = brvahAsyncDiffer;
        this.$oldList = list;
        this.$newList = list2;
        this.$runGeneration = i;
        this.$commitCallback = runnable;
    }

    public final void run() {
        final DiffUtil.DiffResult calculateDiff = DiffUtil.calculateDiff(new BrvahAsyncDiffer$submitList$1$result$1(this));
        Intrinsics.checkExpressionValueIsNotNull(calculateDiff, "DiffUtil.calculateDiff(o…         }\n            })");
        this.this$0.mMainThreadExecutor.execute(new Runnable(this) {
            final /* synthetic */ BrvahAsyncDiffer$submitList$1 this$0;

            {
                this.this$0 = r1;
            }

            public final void run() {
                if (this.this$0.this$0.mMaxScheduledGeneration == this.this$0.$runGeneration) {
                    this.this$0.this$0.latchList(this.this$0.$newList, calculateDiff, this.this$0.$commitCallback);
                }
            }
        });
    }
}
