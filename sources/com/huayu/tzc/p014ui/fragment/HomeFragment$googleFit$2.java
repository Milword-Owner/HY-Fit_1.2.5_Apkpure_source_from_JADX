package com.huayu.tzc.p014ui.fragment;

import android.util.Log;
import com.google.android.gms.tasks.OnFailureListener;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004H\n¢\u0006\u0002\b\u0005"}, mo21895d2 = {"<anonymous>", "", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "onFailure"}, mo21896k = 3, mo21897mv = {1, 1, 16})
/* renamed from: com.huayu.tzc.ui.fragment.HomeFragment$googleFit$2 */
/* compiled from: HomeFragment.kt */
final class HomeFragment$googleFit$2 implements OnFailureListener {
    final /* synthetic */ HomeFragment this$0;

    HomeFragment$googleFit$2(HomeFragment homeFragment) {
        this.this$0 = homeFragment;
    }

    public final void onFailure(@NotNull Exception exc) {
        Intrinsics.checkParameterIsNotNull(exc, "e");
        Log.d(this.this$0.getTAG(), "OnFailure()", exc);
    }
}
