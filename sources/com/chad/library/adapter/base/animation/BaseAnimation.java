package com.chad.library.adapter.base.animation;

import android.animation.Animator;
import android.view.View;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u001b\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&¢\u0006\u0002\u0010\u0007¨\u0006\b"}, mo21895d2 = {"Lcom/chad/library/adapter/base/animation/BaseAnimation;", "", "animators", "", "Landroid/animation/Animator;", "view", "Landroid/view/View;", "(Landroid/view/View;)[Landroid/animation/Animator;", "com.github.CymChad.brvah"}, mo21896k = 1, mo21897mv = {1, 1, 16})
/* compiled from: BaseAnimation.kt */
public interface BaseAnimation {
    @NotNull
    Animator[] animators(@NotNull View view);
}
