package com.google.android.material.progressindicator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.Property;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat;
import androidx.vectordrawable.graphics.drawable.AnimationUtilsCompat;
import com.google.android.material.C1722R;
import com.google.android.material.color.MaterialColors;
import java.util.Arrays;

final class LinearIndeterminateSpacingAnimatorDelegate extends IndeterminateAnimatorDelegate<AnimatorSet> {
    private static final Property<LinearIndeterminateSpacingAnimatorDelegate, Float> LINE_1_HEAD_FRACTION = new Property<LinearIndeterminateSpacingAnimatorDelegate, Float>(Float.class, "line1HeadFraction") {
        public Float get(LinearIndeterminateSpacingAnimatorDelegate linearIndeterminateSpacingAnimatorDelegate) {
            return Float.valueOf(linearIndeterminateSpacingAnimatorDelegate.getLine1HeadFraction());
        }

        public void set(LinearIndeterminateSpacingAnimatorDelegate linearIndeterminateSpacingAnimatorDelegate, Float f) {
            linearIndeterminateSpacingAnimatorDelegate.setLine1HeadFraction(f.floatValue());
        }
    };
    private static final Property<LinearIndeterminateSpacingAnimatorDelegate, Float> LINE_1_TAIL_FRACTION = new Property<LinearIndeterminateSpacingAnimatorDelegate, Float>(Float.class, "line1TailFraction") {
        public Float get(LinearIndeterminateSpacingAnimatorDelegate linearIndeterminateSpacingAnimatorDelegate) {
            return Float.valueOf(linearIndeterminateSpacingAnimatorDelegate.getLine1TailFraction());
        }

        public void set(LinearIndeterminateSpacingAnimatorDelegate linearIndeterminateSpacingAnimatorDelegate, Float f) {
            linearIndeterminateSpacingAnimatorDelegate.setLine1TailFraction(f.floatValue());
        }
    };
    private static final Property<LinearIndeterminateSpacingAnimatorDelegate, Float> LINE_2_HEAD_FRACTION = new Property<LinearIndeterminateSpacingAnimatorDelegate, Float>(Float.class, "line2HeadFraction") {
        public Float get(LinearIndeterminateSpacingAnimatorDelegate linearIndeterminateSpacingAnimatorDelegate) {
            return Float.valueOf(linearIndeterminateSpacingAnimatorDelegate.getLine2HeadFraction());
        }

        public void set(LinearIndeterminateSpacingAnimatorDelegate linearIndeterminateSpacingAnimatorDelegate, Float f) {
            linearIndeterminateSpacingAnimatorDelegate.setLine2HeadFraction(f.floatValue());
        }
    };
    private static final Property<LinearIndeterminateSpacingAnimatorDelegate, Float> LINE_2_TAIL_FRACTION = new Property<LinearIndeterminateSpacingAnimatorDelegate, Float>(Float.class, "line2TailFraction") {
        public Float get(LinearIndeterminateSpacingAnimatorDelegate linearIndeterminateSpacingAnimatorDelegate) {
            return Float.valueOf(linearIndeterminateSpacingAnimatorDelegate.getLine2TailFraction());
        }

        public void set(LinearIndeterminateSpacingAnimatorDelegate linearIndeterminateSpacingAnimatorDelegate, Float f) {
            linearIndeterminateSpacingAnimatorDelegate.setLine2TailFraction(f.floatValue());
        }
    };
    private static final int MAIN_LINE_1_HEAD_DURATION = 750;
    private static final int MAIN_LINE_1_TAIL_DELAY = 333;
    private static final int MAIN_LINE_1_TAIL_DURATION = 850;
    private static final int MAIN_LINE_2_HEAD_DELAY = 1000;
    private static final int MAIN_LINE_2_HEAD_DURATION = 567;
    private static final int MAIN_LINE_2_TAIL_DELAY = 1267;
    private static final int MAIN_LINE_2_TAIL_DURATION = 533;
    Animatable2Compat.AnimationCallback animatorCompleteCallback = null;
    boolean animatorCompleteEndRequested = false;
    private AnimatorSet animatorSet;
    private final BaseProgressIndicatorSpec baseSpec;
    private final Context context;
    private int displayedSegmentColorIndex;
    private float line1HeadFraction;
    private float line1TailFraction;
    private float line2HeadFraction;
    private float line2TailFraction;

    public LinearIndeterminateSpacingAnimatorDelegate(@NonNull Context context2, @NonNull LinearProgressIndicatorSpec linearProgressIndicatorSpec) {
        super(2);
        this.context = context2;
        this.baseSpec = linearProgressIndicatorSpec;
    }

    public void startAnimator() {
        maybeInitializeAnimators();
        this.animatorSet.start();
    }

    private void maybeInitializeAnimators() {
        if (this.animatorSet == null) {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, LINE_1_HEAD_FRACTION, new float[]{0.0f, 1.0f});
            ofFloat.setDuration(750);
            ofFloat.setInterpolator(AnimationUtilsCompat.loadInterpolator(this.context, C1722R.animator.linear_indeterminate_line1_head_interpolator));
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this, LINE_1_TAIL_FRACTION, new float[]{0.0f, 1.0f});
            ofFloat2.setStartDelay(333);
            ofFloat2.setDuration(850);
            ofFloat2.setInterpolator(AnimationUtilsCompat.loadInterpolator(this.context, C1722R.animator.linear_indeterminate_line1_tail_interpolator));
            ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(this, LINE_2_HEAD_FRACTION, new float[]{0.0f, 1.0f});
            ofFloat3.setStartDelay(1000);
            ofFloat3.setDuration(567);
            ofFloat3.setInterpolator(AnimationUtilsCompat.loadInterpolator(this.context, C1722R.animator.linear_indeterminate_line2_head_interpolator));
            ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(this, LINE_2_TAIL_FRACTION, new float[]{0.0f, 1.0f});
            ofFloat4.setStartDelay(1267);
            ofFloat4.setDuration(533);
            ofFloat4.setInterpolator(AnimationUtilsCompat.loadInterpolator(this.context, C1722R.animator.linear_indeterminate_line2_tail_interpolator));
            this.animatorSet = new AnimatorSet();
            this.animatorSet.playTogether(new Animator[]{ofFloat, ofFloat2, ofFloat3, ofFloat4});
            this.animatorSet.addListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animator) {
                    super.onAnimationEnd(animator);
                    if (LinearIndeterminateSpacingAnimatorDelegate.this.animatorCompleteEndRequested) {
                        LinearIndeterminateSpacingAnimatorDelegate linearIndeterminateSpacingAnimatorDelegate = LinearIndeterminateSpacingAnimatorDelegate.this;
                        linearIndeterminateSpacingAnimatorDelegate.animatorCompleteEndRequested = false;
                        linearIndeterminateSpacingAnimatorDelegate.animatorCompleteCallback.onAnimationEnd(LinearIndeterminateSpacingAnimatorDelegate.this.drawable);
                        LinearIndeterminateSpacingAnimatorDelegate.this.resetPropertiesForNewStart();
                    } else if (LinearIndeterminateSpacingAnimatorDelegate.this.drawable.isVisible()) {
                        LinearIndeterminateSpacingAnimatorDelegate.this.resetPropertiesForNextCycle();
                        LinearIndeterminateSpacingAnimatorDelegate.this.startAnimator();
                    } else {
                        LinearIndeterminateSpacingAnimatorDelegate.this.resetPropertiesForNewStart();
                    }
                }
            });
        }
    }

    public void resetPropertiesForNewStart() {
        resetPropertiesForNextCycle();
        resetSegmentColors();
    }

    public void resetPropertiesForNextCycle() {
        setLine1HeadFraction(0.0f);
        setLine1TailFraction(0.0f);
        setLine2HeadFraction(0.0f);
        setLine2TailFraction(0.0f);
        rotateSegmentColors();
    }

    public void cancelAnimatorImmediately() {
        AnimatorSet animatorSet2 = this.animatorSet;
        if (animatorSet2 != null) {
            animatorSet2.cancel();
        }
    }

    public void requestCancelAnimatorAfterCurrentCycle() {
        if (!this.animatorCompleteEndRequested) {
            if (!this.drawable.isVisible()) {
                cancelAnimatorImmediately();
            } else {
                this.animatorCompleteEndRequested = true;
            }
        }
    }

    public void invalidateSpecValues() {
        resetSegmentColors();
    }

    public void registerAnimatorsCompleteCallback(@NonNull Animatable2Compat.AnimationCallback animationCallback) {
        this.animatorCompleteCallback = animationCallback;
    }

    public void unregisterAnimatorsCompleteCallback() {
        this.animatorCompleteCallback = null;
    }

    private void rotateSegmentColors() {
        this.displayedSegmentColorIndex = (this.displayedSegmentColorIndex + 1) % this.baseSpec.indicatorColors.length;
        Arrays.fill(this.segmentColors, MaterialColors.compositeARGBWithAlpha(this.baseSpec.indicatorColors[this.displayedSegmentColorIndex], this.drawable.getAlpha()));
    }

    private void resetSegmentColors() {
        this.displayedSegmentColorIndex = 0;
        Arrays.fill(this.segmentColors, MaterialColors.compositeARGBWithAlpha(this.baseSpec.indicatorColors[this.displayedSegmentColorIndex], this.drawable.getAlpha()));
    }

    /* access modifiers changed from: private */
    public float getLine1HeadFraction() {
        return this.line1HeadFraction;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void setLine1HeadFraction(float f) {
        this.line1HeadFraction = f;
        this.segmentPositions[3] = f;
        this.drawable.invalidateSelf();
    }

    /* access modifiers changed from: private */
    public float getLine1TailFraction() {
        return this.line1TailFraction;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void setLine1TailFraction(float f) {
        this.line1TailFraction = f;
        this.segmentPositions[2] = f;
        this.drawable.invalidateSelf();
    }

    /* access modifiers changed from: private */
    public float getLine2HeadFraction() {
        return this.line2HeadFraction;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void setLine2HeadFraction(float f) {
        this.line2HeadFraction = f;
        this.segmentPositions[1] = f;
        this.drawable.invalidateSelf();
    }

    /* access modifiers changed from: private */
    public float getLine2TailFraction() {
        return this.line2TailFraction;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void setLine2TailFraction(float f) {
        this.line2TailFraction = f;
        this.segmentPositions[0] = f;
        this.drawable.invalidateSelf();
    }
}
