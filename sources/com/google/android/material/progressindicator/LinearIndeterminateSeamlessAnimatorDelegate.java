package com.google.android.material.progressindicator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.util.Property;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.math.MathUtils;

final class LinearIndeterminateSeamlessAnimatorDelegate extends IndeterminateAnimatorDelegate<AnimatorSet> {
    private static final int DURATION_PER_COLOR = 667;
    private static final Property<LinearIndeterminateSeamlessAnimatorDelegate, Float> LINE_CONNECT_POINT_1_FRACTION = new Property<LinearIndeterminateSeamlessAnimatorDelegate, Float>(Float.class, "lineConnectPoint1Fraction") {
        public Float get(LinearIndeterminateSeamlessAnimatorDelegate linearIndeterminateSeamlessAnimatorDelegate) {
            return Float.valueOf(linearIndeterminateSeamlessAnimatorDelegate.getLineConnectPoint1Fraction());
        }

        public void set(LinearIndeterminateSeamlessAnimatorDelegate linearIndeterminateSeamlessAnimatorDelegate, Float f) {
            linearIndeterminateSeamlessAnimatorDelegate.setLineConnectPoint1Fraction(f.floatValue());
        }
    };
    private static final Property<LinearIndeterminateSeamlessAnimatorDelegate, Float> LINE_CONNECT_POINT_2_FRACTION = new Property<LinearIndeterminateSeamlessAnimatorDelegate, Float>(Float.class, "lineConnectPoint2Fraction") {
        public Float get(LinearIndeterminateSeamlessAnimatorDelegate linearIndeterminateSeamlessAnimatorDelegate) {
            return Float.valueOf(linearIndeterminateSeamlessAnimatorDelegate.getLineConnectPoint2Fraction());
        }

        public void set(LinearIndeterminateSeamlessAnimatorDelegate linearIndeterminateSeamlessAnimatorDelegate, Float f) {
            linearIndeterminateSeamlessAnimatorDelegate.setLineConnectPoint2Fraction(f.floatValue());
        }
    };
    private static final int NEXT_COLOR_DELAY = 333;
    private AnimatorSet animatorSet;
    private final BaseProgressIndicatorSpec baseSpec;
    private float lineConnectPoint1Fraction;
    private float lineConnectPoint2Fraction;
    private int referenceSegmentColorIndex;

    public void registerAnimatorsCompleteCallback(@Nullable Animatable2Compat.AnimationCallback animationCallback) {
    }

    public void requestCancelAnimatorAfterCurrentCycle() {
    }

    public void resetPropertiesForNextCycle() {
    }

    public void unregisterAnimatorsCompleteCallback() {
    }

    public LinearIndeterminateSeamlessAnimatorDelegate(@NonNull LinearProgressIndicatorSpec linearProgressIndicatorSpec) {
        super(3);
        this.baseSpec = linearProgressIndicatorSpec;
    }

    public void startAnimator() {
        maybeInitializeAnimators();
        this.animatorSet.start();
    }

    private void maybeInitializeAnimators() {
        if (this.animatorSet == null) {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, LINE_CONNECT_POINT_1_FRACTION, new float[]{0.0f, 1.0f});
            ofFloat.setDuration(667);
            ofFloat.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
            ofFloat.setRepeatCount(-1);
            ofFloat.setRepeatMode(1);
            ofFloat.addListener(new AnimatorListenerAdapter() {
                public void onAnimationRepeat(Animator animator) {
                    super.onAnimationRepeat(animator);
                    if (LinearIndeterminateSeamlessAnimatorDelegate.this.getLineConnectPoint2Fraction() > 0.0f && LinearIndeterminateSeamlessAnimatorDelegate.this.getLineConnectPoint2Fraction() < 1.0f) {
                        LinearIndeterminateSeamlessAnimatorDelegate.this.shiftSegmentColors();
                    }
                }
            });
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this, LINE_CONNECT_POINT_2_FRACTION, new float[]{0.0f, 0.0f});
            ofFloat2.setDuration(333);
            ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(this, LINE_CONNECT_POINT_2_FRACTION, new float[]{0.0f, 1.0f});
            ofFloat3.setDuration(667);
            ofFloat3.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
            ofFloat3.setRepeatCount(-1);
            ofFloat3.setRepeatMode(1);
            ofFloat3.addListener(new AnimatorListenerAdapter() {
                public void onAnimationRepeat(Animator animator) {
                    super.onAnimationRepeat(animator);
                    if (LinearIndeterminateSeamlessAnimatorDelegate.this.getLineConnectPoint1Fraction() > 0.0f && LinearIndeterminateSeamlessAnimatorDelegate.this.getLineConnectPoint1Fraction() < 1.0f) {
                        LinearIndeterminateSeamlessAnimatorDelegate.this.shiftSegmentColors();
                    }
                }
            });
            AnimatorSet animatorSet2 = new AnimatorSet();
            animatorSet2.playSequentially(new Animator[]{ofFloat2, ofFloat3});
            this.animatorSet = new AnimatorSet();
            this.animatorSet.playTogether(new Animator[]{ofFloat, animatorSet2});
        }
    }

    public void resetPropertiesForNewStart() {
        setLineConnectPoint1Fraction(0.0f);
        setLineConnectPoint2Fraction(0.0f);
        resetSegmentColors();
    }

    public void cancelAnimatorImmediately() {
        AnimatorSet animatorSet2 = this.animatorSet;
        if (animatorSet2 != null) {
            animatorSet2.cancel();
        }
    }

    public void invalidateSpecValues() {
        resetSegmentColors();
    }

    /* access modifiers changed from: private */
    public void shiftSegmentColors() {
        this.referenceSegmentColorIndex = (this.referenceSegmentColorIndex + 1) % this.baseSpec.indicatorColors.length;
        updateSegmentColors();
    }

    private void resetSegmentColors() {
        this.referenceSegmentColorIndex = 0;
        updateSegmentColors();
    }

    private void updateSegmentColors() {
        int floorMod = MathUtils.floorMod(this.referenceSegmentColorIndex + 2, this.baseSpec.indicatorColors.length);
        int floorMod2 = MathUtils.floorMod(this.referenceSegmentColorIndex + 1, this.baseSpec.indicatorColors.length);
        this.segmentColors[0] = MaterialColors.compositeARGBWithAlpha(this.baseSpec.indicatorColors[floorMod], this.drawable.getAlpha());
        this.segmentColors[1] = MaterialColors.compositeARGBWithAlpha(this.baseSpec.indicatorColors[floorMod2], this.drawable.getAlpha());
        this.segmentColors[2] = MaterialColors.compositeARGBWithAlpha(this.baseSpec.indicatorColors[this.referenceSegmentColorIndex], this.drawable.getAlpha());
    }

    private void updateSegmentPositions() {
        this.segmentPositions[0] = 0.0f;
        float[] fArr = this.segmentPositions;
        float[] fArr2 = this.segmentPositions;
        float min = Math.min(getLineConnectPoint1Fraction(), getLineConnectPoint2Fraction());
        fArr2[2] = min;
        fArr[1] = min;
        float[] fArr3 = this.segmentPositions;
        float[] fArr4 = this.segmentPositions;
        float max = Math.max(getLineConnectPoint1Fraction(), getLineConnectPoint2Fraction());
        fArr4[4] = max;
        fArr3[3] = max;
        this.segmentPositions[5] = 1.0f;
    }

    /* access modifiers changed from: private */
    public float getLineConnectPoint1Fraction() {
        return this.lineConnectPoint1Fraction;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void setLineConnectPoint1Fraction(float f) {
        this.lineConnectPoint1Fraction = f;
        updateSegmentPositions();
        this.drawable.invalidateSelf();
    }

    /* access modifiers changed from: private */
    public float getLineConnectPoint2Fraction() {
        return this.lineConnectPoint2Fraction;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void setLineConnectPoint2Fraction(float f) {
        this.lineConnectPoint2Fraction = f;
        updateSegmentPositions();
        this.drawable.invalidateSelf();
    }
}
