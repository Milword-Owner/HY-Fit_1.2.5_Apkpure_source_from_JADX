package com.huayu.tzc.p014ui.fragment;

import android.util.Log;
import com.baidubce.services.bos.callback.BosProgressCallback;
import com.baidubce.services.bos.model.PutObjectRequest;
import com.huayu.tzc.utils.UploadImg;
import java.io.File;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, mo21895d2 = {"<anonymous>", "", "run"}, mo21896k = 3, mo21897mv = {1, 1, 16})
/* renamed from: com.huayu.tzc.ui.fragment.MsgFragment$uploadImg$1 */
/* compiled from: MsgFragment.kt */
final class MsgFragment$uploadImg$1 implements Runnable {
    final /* synthetic */ File $file;
    final /* synthetic */ String $key;
    final /* synthetic */ MsgFragment this$0;

    MsgFragment$uploadImg$1(MsgFragment msgFragment, String str, File file) {
        this.this$0 = msgFragment;
        this.$key = str;
        this.$file = file;
    }

    public final void run() {
        UploadImg.uploadImg(this.this$0.getContext(), this.$key, this.$file, new BosProgressCallback<PutObjectRequest>(this) {
            final /* synthetic */ MsgFragment$uploadImg$1 this$0;

            {
                this.this$0 = r1;
            }

            public void onProgress(@Nullable PutObjectRequest putObjectRequest, long j, long j2) {
                super.onProgress(putObjectRequest, j, j2);
                this.this$0.this$0.progressDissmiss();
                String access$getTAG$p = this.this$0.this$0.getTAG();
                Log.e(access$getTAG$p, "onProgress: " + j + "   " + j2);
                if (j == j2) {
                    MsgFragment msgFragment = this.this$0.this$0;
                    msgFragment.img = "https://tenswall.hkg.bcebos.com/tenswall" + this.this$0.$key;
                    this.this$0.this$0.sendImg(this.this$0.this$0.img);
                    String access$getTAG$p2 = this.this$0.this$0.getTAG();
                    Log.e(access$getTAG$p2, "onProgress: imgUrl   " + this.this$0.this$0.img);
                }
            }
        });
    }
}
