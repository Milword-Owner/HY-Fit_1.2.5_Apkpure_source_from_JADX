package com.google.android.material.progressindicator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.util.Property;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.animation.ArgbEvaluatorCompat;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.math.MathUtils;

final class CircularIndeterminateAnimatorDelegate extends IndeterminateAnimatorDelegate<AnimatorSet> {
    private static final int COLOR_FADING_DELAY = 1000;
    private static final int COLOR_FADING_DURATION = 333;
    private static final Property<CircularIndeterminateAnimatorDelegate, Integer> DISPLAYED_INDICATOR_COLOR = new Property<CircularIndeterminateAnimatorDelegate, Integer>(Integer.class, "displayedIndicatorColor") {
        public Integer get(CircularIndeterminateAnimatorDelegate circularIndeterminateAnimatorDelegate) {
            return Integer.valueOf(circularIndeterminateAnimatorDelegate.getDisplayedIndicatorColor());
        }

        public void set(CircularIndeterminateAnimatorDelegate circularIndeterminateAnimatorDelegate, Integer num) {
            circularIndeterminateAnimatorDelegate.setDisplayedIndicatorColor(num.intValue());
        }
    };
    private static final int DURATION_PER_COLOR_IN_MS = 1333;
    private static final float INDICATOR_DELTA_DEGREES = 250.0f;
    private static final Property<CircularIndeterminateAnimatorDelegate, Float> INDICATOR_HEAD_CHANGE_FRACTION = new Property<CircularIndeterminateAnimatorDelegate, Float>(Float.class, "indicatorHeadChangeFraction") {
        public Float get(CircularIndeterminateAnimatorDelegate circularIndeterminateAnimatorDelegate) {
            return Float.valueOf(circularIndeterminateAnimatorDelegate.getIndicatorHeadChangeFraction());
        }

        public void set(CircularIndeterminateAnimatorDelegate circularIndeterminateAnimatorDelegate, Float f) {
            circularIndeterminateAnimatorDelegate.setIndicatorHeadChangeFraction(f.floatValue());
        }
    };
    private static final Property<CircularIndeterminateAnimatorDelegate, Float> INDICATOR_IN_CYCLE_OFFSET = new Property<CircularIndeterminateAnimatorDelegate, Float>(Float.class, "indicatorInCycleOffset") {
        public Float get(CircularIndeterminateAnimatorDelegate circularIndeterminateAnimatorDelegate) {
            return Float.valueOf(circularIndeterminateAnimatorDelegate.getIndicatorInCycleOffset());
        }

        public void set(CircularIndeterminateAnimatorDelegate circularIndeterminateAnimatorDelegate, Float f) {
            circularIndeterminateAnimatorDelegate.setIndicatorInCycleOffset(f.floatValue());
        }
    };
    private static final float INDICATOR_MAX_DEGREES = 270.0f;
    private static final float INDICATOR_MIN_DEGREES = 20.0f;
    private static final float INDICATOR_OFFSET_PER_COLOR_DEGREES = 360.0f;
    private static final Property<CircularIndeterminateAnimatorDelegate, Float> INDICATOR_TAIL_CHANGE_FRACTION = new Property<CircularIndeterminateAnimatorDelegate, Float>(Float.class, "indicatorTailChangeFraction") {
        public Float get(CircularIndeterminateAnimatorDelegate circularIndeterminateAnimatorDelegate) {
            return Float.valueOf(circularIndeterminateAnimatorDelegate.getIndicatorTailChangeFraction());
        }

        public void set(CircularIndeterminateAnimatorDelegate circularIndeterminateAnimatorDelegate, Float f) {
            circularIndeterminateAnimatorDelegate.setIndicatorTailChangeFraction(f.floatValue());
        }
    };
    Animatable2Compat.AnimationCallback animatorCompleteCallback = null;
    boolean animatorCompleteEndRequested = false;
    private AnimatorSet animatorSet;
    private final BaseProgressIndicatorSpec baseSpec;
    private ObjectAnimator colorFadingAnimator;
    private int displayedIndicatorColor;
    /* access modifiers changed from: private */
    public ObjectAnimator indicatorCollapsingAnimator;
    private int indicatorColorIndex;
    private float indicatorHeadChangeFraction;
    private float indicatorInCycleOffset;
    private float indicatorStartOffset;
    private float indicatorTailChangeFraction;

    public CircularIndeterminateAnimatorDelegate(@NonNull CircularProgressIndicatorSpec circularProgressIndicatorSpec) {
        super(1);
        this.baseSpec = circularProgressIndicatorSpec;
    }

    /* access modifiers changed from: protected */
    public void registerDrawable(@NonNull IndeterminateDrawable indeterminateDrawable) {
        super.registerDrawable(indeterminateDrawable);
        this.colorFadingAnimator = ObjectAnimator.ofObject(this, DISPLAYED_INDICATOR_COLOR, new ArgbEvaluatorCompat(), new Integer[]{Integer.valueOf(MaterialColors.compositeARGBWithAlpha(this.baseSpec.indicatorColors[this.indicatorColorIndex], indeterminateDrawable.getAlpha())), Integer.valueOf(MaterialColors.compositeARGBWithAlpha(this.baseSpec.indicatorColors[getNextIndicatorColorIndex()], indeterminateDrawable.getAlpha()))});
        this.colorFadingAnimator.setDuration(333);
        this.colorFadingAnimator.setStartDelay(1000);
        this.colorFadingAnimator.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
        AnimatorSet animatorSet2 = this.animatorSet;
        if (animatorSet2 != null) {
            animatorSet2.playTogether(new Animator[]{this.colorFadingAnimator});
        }
    }

    /* access modifiers changed from: package-private */
    public void startAnimator() {
        maybeInitializeAnimators();
        this.animatorSet.start();
    }

    private void maybeInitializeAnimators() {
        if (this.animatorSet == null) {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, INDICATOR_IN_CYCLE_OFFSET, new float[]{0.0f, INDICATOR_OFFSET_PER_COLOR_DEGREES});
            ofFloat.setDuration(1333);
            ofFloat.setInterpolator((TimeInterpolator) null);
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this, INDICATOR_HEAD_CHANGE_FRACTION, new float[]{0.0f, 1.0f});
            ofFloat2.setDuration(666);
            ofFloat2.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
            ofFloat2.addListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animator) {
                    super.onAnimationEnd(animator);
                    if (CircularIndeterminateAnimatorDelegate.this.animatorCompleteEndRequested) {
                        CircularIndeterminateAnimatorDelegate.this.indicatorCollapsingAnimator.setFloatValues(new float[]{0.0f, 1.08f});
                    }
                }
            });
            this.indicatorCollapsingAnimator = ObjectAnimator.ofFloat(this, INDICATOR_TAIL_CHANGE_FRACTION, new float[]{0.0f, 1.0f});
            this.indicatorCollapsingAnimator.setDuration(666);
            this.indicatorCollapsingAnimator.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
            this.animatorSet = new AnimatorSet();
            this.animatorSet.playSequentially(new Animator[]{ofFloat2, this.indicatorCollapsingAnimator});
            this.animatorSet.playTogether(new Animator[]{ofFloat});
            ObjectAnimator objectAnimator = this.colorFadingAnimator;
            if (objectAnimator != null) {
                this.animatorSet.playTogether(new Animator[]{objectAnimator});
            }
            this.animatorSet.addListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animator) {
                    super.onAnimationEnd(animator);
                    if (CircularIndeterminateAnimatorDelegate.this.animatorCompleteEndRequested && CircularIndeterminateAnimatorDelegate.this.segmentPositions[0] == CircularIndeterminateAnimatorDelegate.this.segmentPositions[1]) {
                        CircularIndeterminateAnimatorDelegate.this.animatorCompleteCallback.onAnimationEnd(CircularIndeterminateAnimatorDelegate.this.drawable);
                        CircularIndeterminateAnimatorDelegate.this.animatorCompleteEndRequested = false;
                    } else if (CircularIndeterminateAnimatorDelegate.this.drawable.isVisible()) {
                        CircularIndeterminateAnimatorDelegate.this.resetPropertiesForNextCycle();
                        CircularIndeterminateAnimatorDelegate.this.startAnimator();
                    }
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public void cancelAnimatorImmediately() {
        AnimatorSet animatorSet2 = this.animatorSet;
        if (animatorSet2 != null) {
            animatorSet2.cancel();
        }
    }

    /* access modifiers changed from: package-private */
    public void requestCancelAnimatorAfterCurrentCycle() {
        if (!this.animatorCompleteEndRequested) {
            if (this.drawable.isVisible()) {
                this.animatorCompleteEndRequested = true;
            } else {
                cancelAnimatorImmediately();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void resetPropertiesForNewStart() {
        setIndicatorHeadChangeFraction(0.0f);
        setIndicatorTailChangeFraction(0.0f);
        setIndicatorStartOffset(0.0f);
        ObjectAnimator objectAnimator = this.indicatorCollapsingAnimator;
        if (objectAnimator != null) {
            objectAnimator.setFloatValues(new float[]{0.0f, 1.0f});
        }
        resetSegmentColors();
    }

    /* access modifiers changed from: package-private */
    public void resetPropertiesForNextCycle() {
        setIndicatorHeadChangeFraction(0.0f);
        setIndicatorTailChangeFraction(0.0f);
        setIndicatorStartOffset(MathUtils.floorMod(getIndicatorStartOffset() + INDICATOR_OFFSET_PER_COLOR_DEGREES + INDICATOR_DELTA_DEGREES, 360));
        shiftSegmentColors();
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

    private int getNextIndicatorColorIndex() {
        return (this.indicatorColorIndex + 1) % this.baseSpec.indicatorColors.length;
    }

    private void updateSegmentPositions() {
        this.segmentPositions[0] = (((getIndicatorStartOffset() + getIndicatorInCycleOffset()) - INDICATOR_MIN_DEGREES) + (getIndicatorTailChangeFraction() * INDICATOR_DELTA_DEGREES)) / INDICATOR_OFFSET_PER_COLOR_DEGREES;
        this.segmentPositions[1] = ((getIndicatorStartOffset() + getIndicatorInCycleOffset()) + (getIndicatorHeadChangeFraction() * INDICATOR_DELTA_DEGREES)) / INDICATOR_OFFSET_PER_COLOR_DEGREES;
    }

    private void shiftSegmentColors() {
        this.indicatorColorIndex = getNextIndicatorColorIndex();
        int compositeARGBWithAlpha = MaterialColors.compositeARGBWithAlpha(this.baseSpec.indicatorColors[this.indicatorColorIndex], this.drawable.getAlpha());
        int compositeARGBWithAlpha2 = MaterialColors.compositeARGBWithAlpha(this.baseSpec.indicatorColors[getNextIndicatorColorIndex()], this.drawable.getAlpha());
        this.colorFadingAnimator.setIntValues(new int[]{compositeARGBWithAlpha, compositeARGBWithAlpha2});
        setDisplayedIndicatorColor(compositeARGBWithAlpha);
    }

    private void resetSegmentColors() {
        this.indicatorColorIndex = 0;
        int compositeARGBWithAlpha = MaterialColors.compositeARGBWithAlpha(this.baseSpec.indicatorColors[this.indicatorColorIndex], this.drawable.getAlpha());
        int compositeARGBWithAlpha2 = MaterialColors.compositeARGBWithAlpha(this.baseSpec.indicatorColors[getNextIndicatorColorIndex()], this.drawable.getAlpha());
        this.colorFadingAnimator.setIntValues(new int[]{compositeARGBWithAlpha, compositeARGBWithAlpha2});
        setDisplayedIndicatorColor(compositeARGBWithAlpha);
    }

    /* access modifiers changed from: private */
    public int getDisplayedIndicatorColor() {
        return this.displayedIndicatorColor;
    }

    /* access modifiers changed from: private */
    public void setDisplayedIndicatorColor(int i) {
        this.displayedIndicatorColor = i;
        this.segmentColors[0] = i;
        this.drawable.invalidateSelf();
    }

    private float getIndicatorStartOffset() {
        return this.indicatorStartOffset;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void setIndicatorStartOffset(float f) {
        this.indicatorStartOffset = f;
        updateSegmentPositions();
        this.drawable.invalidateSelf();
    }

    /* access modifiers changed from: private */
    public float getIndicatorInCycleOffset() {
        return this.indicatorInCycleOffset;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void setIndicatorInCycleOffset(float f) {
        this.indicatorInCycleOffset = f;
        updateSegmentPositions();
        this.drawable.invalidateSelf();
    }

    /* access modifiers changed from: private */
    public float getIndicatorHeadChangeFraction() {
        return this.indicatorHeadChangeFraction;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void setIndicatorHeadChangeFraction(float f) {
        this.indicatorHeadChangeFraction = f;
        updateSegmentPositions();
        this.drawable.invalidateSelf();
    }

    /* access modifiers changed from: private */
    public float getIndicatorTailChangeFraction() {
        return this.indicatorTailChangeFraction;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void setIndicatorTailChangeFraction(float f) {
        this.indicatorTailChangeFraction = f;
        updateSegmentPositions();
        this.drawable.invalidateSelf();
    }
}
