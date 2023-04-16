package com.previewlibrary.wight;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import androidx.core.view.ViewCompat;
import com.previewlibrary.C2517R;
import com.previewlibrary.view.ImageUtils;
import p040uk.p041co.senab2.photoview2.PhotoView;

public class SmoothImageView extends PhotoView {
    private static boolean ISFUll = false;
    private static boolean ISSCALE = false;
    private static final int MIN_TRANS_DEST = 5;
    private static int TRANSFORM_DURATION = 400;
    private float MAX_TRANS_SCALE = 0.5f;
    private int alpha = 0;
    /* access modifiers changed from: private */
    public OnAlphaChangeListener alphaChangeListener;
    /* access modifiers changed from: private */
    public Transform animTransform;
    ValueAnimator animator;
    private int bitmapHeight;
    private int bitmapWidth;
    private int downX;
    private int downY;
    private Transform endTransform;
    private boolean isDownPhoto = false;
    private boolean isDrag;
    private boolean isMoved = false;
    private Paint mPaint;
    /* access modifiers changed from: private */
    public Status mStatus = Status.STATE_NORMAL;
    private Transform markTransform;
    private Matrix matrix;
    /* access modifiers changed from: private */
    public onTransformListener onTransformListener;
    private Transform startTransform;
    private Rect thumbRect;
    private OnTransformOutListener transformOutListener;
    private boolean transformStart;

    public interface OnAlphaChangeListener {
        void onAlphaChange(int i);
    }

    public interface OnTransformOutListener {
        void onTransformOut();
    }

    public enum Status {
        STATE_NORMAL,
        STATE_IN,
        STATE_OUT,
        STATE_MOVE
    }

    public interface onTransformListener {
        void onTransformCompleted(Status status);
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.bitmapWidth = 0;
        this.bitmapHeight = 0;
        this.thumbRect = null;
        ISFUll = false;
        ValueAnimator valueAnimator = this.animator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
            this.animator.clone();
            this.animator = null;
        }
    }

    private void initSmoothImageView() {
        this.mPaint = new Paint();
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setColor(ViewCompat.MEASURED_STATE_MASK);
        this.matrix = new Matrix();
        setScaleType(ImageView.ScaleType.FIT_CENTER);
    }

    public boolean checkMinScale() {
        if (getScale() == 1.0f) {
            return true;
        }
        setScale(1.0f, true);
        return false;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        if (getDrawable() != null) {
            if (this.mStatus == Status.STATE_OUT || this.mStatus == Status.STATE_IN) {
                if (this.startTransform == null || this.endTransform == null || this.animTransform == null) {
                    initTransform();
                }
                Transform transform = this.animTransform;
                if (transform == null) {
                    super.onDraw(canvas);
                    return;
                }
                this.mPaint.setAlpha(transform.alpha);
                canvas.drawPaint(this.mPaint);
                int saveCount = canvas.getSaveCount();
                this.matrix.setScale(this.animTransform.scale, this.animTransform.scale);
                this.matrix.postTranslate((-((((float) this.bitmapWidth) * this.animTransform.scale) - this.animTransform.width)) / 2.0f, (-((((float) this.bitmapHeight) * this.animTransform.scale) - this.animTransform.height)) / 2.0f);
                canvas.translate(this.animTransform.left, this.animTransform.f2839top);
                canvas.clipRect(0.0f, 0.0f, this.animTransform.width, this.animTransform.height);
                canvas.concat(this.matrix);
                getDrawable().draw(canvas);
                canvas.restoreToCount(saveCount);
                if (this.transformStart) {
                    startTransform();
                }
            } else if (this.mStatus == Status.STATE_MOVE) {
                this.mPaint.setAlpha(0);
                canvas.drawPaint(this.mPaint);
                super.onDraw(canvas);
            } else {
                this.mPaint.setAlpha(255);
                canvas.drawPaint(this.mPaint);
                super.onDraw(canvas);
            }
        }
    }

    private void actionDown(MotionEvent motionEvent) {
        this.downX = (int) motionEvent.getX();
        this.downY = (int) motionEvent.getY();
        if (this.markTransform == null) {
            initTransform();
        }
        this.isDownPhoto = false;
        Transform transform = this.markTransform;
        if (transform != null) {
            int i = (int) transform.f2839top;
            int i2 = (int) (this.markTransform.height + this.markTransform.f2839top);
            int i3 = this.downY;
            if (i3 >= i && i2 >= i3) {
                this.isDownPhoto = true;
            }
        }
        this.isMoved = false;
    }

    private boolean actionMove(MotionEvent motionEvent) {
        if (!this.isDownPhoto && motionEvent.getPointerCount() == 1) {
            return super.dispatchTouchEvent(motionEvent);
        }
        int x = ((int) motionEvent.getX()) - this.downX;
        int y = ((int) motionEvent.getY()) - this.downY;
        if (!this.isMoved && (Math.abs(x) > Math.abs(y) || Math.abs(y) < 5)) {
            return super.dispatchTouchEvent(motionEvent);
        }
        if (this.isDrag) {
            return super.dispatchTouchEvent(motionEvent);
        }
        if (motionEvent.getPointerCount() != 1) {
            return super.dispatchTouchEvent(motionEvent);
        }
        this.mStatus = Status.STATE_MOVE;
        offsetLeftAndRight(x);
        offsetTopAndBottom(y);
        float moveScale = moveScale();
        float f = 1.0f - (0.1f * moveScale);
        setScaleY(f);
        setScaleX(f);
        this.isMoved = true;
        this.alpha = (int) ((1.0f - (moveScale * 0.5f)) * 255.0f);
        invalidate();
        if (this.alpha < 0) {
            this.alpha = 0;
        }
        OnAlphaChangeListener onAlphaChangeListener = this.alphaChangeListener;
        if (onAlphaChangeListener != null) {
            onAlphaChangeListener.onAlphaChange(this.alpha);
        }
        return true;
    }

    private boolean actionCancel() {
        if (moveScale() <= this.MAX_TRANS_SCALE) {
            moveToOldPosition();
        } else {
            changeTransform();
            setTag(C2517R.C2520id.item_image_key, true);
            OnTransformOutListener onTransformOutListener = this.transformOutListener;
            if (onTransformOutListener != null) {
                onTransformOutListener.onTransformOut();
            }
        }
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x004d, code lost:
        if (r0 != 3) goto L_0x0061;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x001b, code lost:
        if (r0 != 3) goto L_0x002f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean dispatchTouchEvent(android.view.MotionEvent r7) {
        /*
            r6 = this;
            int r0 = r7.getAction()
            boolean r1 = ISSCALE
            r2 = 2
            r3 = 3
            r4 = 1
            if (r1 == 0) goto L_0x0047
            float r1 = r6.getScale()
            r5 = 1065353216(0x3f800000, float:1.0)
            int r1 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1))
            if (r1 != 0) goto L_0x0034
            if (r0 == 0) goto L_0x002c
            if (r0 == r4) goto L_0x0023
            if (r0 == r2) goto L_0x001e
            if (r0 == r3) goto L_0x0023
            goto L_0x002f
        L_0x001e:
            boolean r7 = r6.actionMove(r7)
            return r7
        L_0x0023:
            boolean r0 = r6.isMoved
            if (r0 == 0) goto L_0x002f
            boolean r7 = r6.actionCancel()
            return r7
        L_0x002c:
            r6.actionDown(r7)
        L_0x002f:
            boolean r7 = super.dispatchTouchEvent(r7)
            return r7
        L_0x0034:
            if (r0 == r4) goto L_0x0039
            if (r0 == r3) goto L_0x0039
            goto L_0x0042
        L_0x0039:
            boolean r0 = r6.isMoved
            if (r0 == 0) goto L_0x0042
            boolean r7 = r6.actionCancel()
            return r7
        L_0x0042:
            boolean r7 = super.dispatchTouchEvent(r7)
            return r7
        L_0x0047:
            if (r0 == 0) goto L_0x005e
            if (r0 == r4) goto L_0x0055
            if (r0 == r2) goto L_0x0050
            if (r0 == r3) goto L_0x0055
            goto L_0x0061
        L_0x0050:
            boolean r7 = r6.actionMove(r7)
            return r7
        L_0x0055:
            boolean r0 = r6.isMoved
            if (r0 == 0) goto L_0x0061
            boolean r7 = r6.actionCancel()
            return r7
        L_0x005e:
            r6.actionDown(r7)
        L_0x0061:
            boolean r7 = super.dispatchTouchEvent(r7)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.previewlibrary.wight.SmoothImageView.dispatchTouchEvent(android.view.MotionEvent):boolean");
    }

    private void moveToOldPosition() {
        ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{getTop(), 0});
        ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            int startValue = 0;

            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                int i = this.startValue;
                if (i != 0) {
                    SmoothImageView.this.offsetTopAndBottom(intValue - i);
                }
                this.startValue = intValue;
            }
        });
        ValueAnimator ofInt2 = ValueAnimator.ofInt(new int[]{getLeft(), 0});
        ofInt2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            int startValue = 0;

            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                int i = this.startValue;
                if (i != 0) {
                    SmoothImageView.this.offsetLeftAndRight(intValue - i);
                }
                this.startValue = intValue;
            }
        });
        ValueAnimator ofInt3 = ValueAnimator.ofInt(new int[]{this.alpha, 255});
        ofInt3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                if (SmoothImageView.this.alphaChangeListener != null) {
                    SmoothImageView.this.alphaChangeListener.onAlphaChange(((Integer) valueAnimator.getAnimatedValue()).intValue());
                }
            }
        });
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{getScaleX(), 1.0f});
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                SmoothImageView.this.setScaleX(floatValue);
                SmoothImageView.this.setScaleY(floatValue);
            }
        });
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration((long) TRANSFORM_DURATION);
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        animatorSet.playTogether(new Animator[]{ofInt, ofInt2, ofFloat, ofInt3});
        animatorSet.start();
    }

    private float moveScale() {
        if (this.markTransform == null) {
            initTransform();
        }
        return Math.abs(((float) getTop()) / this.markTransform.height);
    }

    public void setTransformOutListener(OnTransformOutListener onTransformOutListener) {
        this.transformOutListener = onTransformOutListener;
    }

    public void setAlphaChangeListener(OnAlphaChangeListener onAlphaChangeListener) {
        this.alphaChangeListener = onAlphaChangeListener;
    }

    private void changeTransform() {
        Transform transform = this.markTransform;
        if (transform != null) {
            Transform clone = transform.clone();
            clone.f2839top = this.markTransform.f2839top + ((float) getTop());
            clone.left = this.markTransform.left + ((float) getLeft());
            clone.alpha = this.alpha;
            clone.scale = this.markTransform.scale - ((1.0f - getScaleX()) * this.markTransform.scale);
            this.animTransform = clone.clone();
            this.endTransform = clone.clone();
        }
    }

    private void startTransform() {
        this.transformStart = false;
        if (this.animTransform != null) {
            this.animator = new ValueAnimator();
            this.animator.setDuration((long) TRANSFORM_DURATION);
            this.animator.setInterpolator(new AccelerateDecelerateInterpolator());
            if (this.mStatus == Status.STATE_IN) {
                PropertyValuesHolder ofFloat = PropertyValuesHolder.ofFloat("animScale", new float[]{this.startTransform.scale, this.endTransform.scale});
                PropertyValuesHolder ofInt = PropertyValuesHolder.ofInt("animAlpha", new int[]{this.startTransform.alpha, this.endTransform.alpha});
                PropertyValuesHolder ofFloat2 = PropertyValuesHolder.ofFloat("animLeft", new float[]{this.startTransform.left, this.endTransform.left});
                PropertyValuesHolder ofFloat3 = PropertyValuesHolder.ofFloat("animTop", new float[]{this.startTransform.f2839top, this.endTransform.f2839top});
                PropertyValuesHolder ofFloat4 = PropertyValuesHolder.ofFloat("animWidth", new float[]{this.startTransform.width, this.endTransform.width});
                PropertyValuesHolder ofFloat5 = PropertyValuesHolder.ofFloat("animHeight", new float[]{this.startTransform.height, this.endTransform.height});
                this.animator.setValues(new PropertyValuesHolder[]{ofFloat, ofInt, ofFloat2, ofFloat3, ofFloat4, ofFloat5});
            } else if (this.mStatus == Status.STATE_OUT) {
                PropertyValuesHolder ofFloat6 = PropertyValuesHolder.ofFloat("animScale", new float[]{this.endTransform.scale, this.startTransform.scale});
                PropertyValuesHolder ofInt2 = PropertyValuesHolder.ofInt("animAlpha", new int[]{this.endTransform.alpha, this.startTransform.alpha});
                PropertyValuesHolder ofFloat7 = PropertyValuesHolder.ofFloat("animLeft", new float[]{this.endTransform.left, this.startTransform.left});
                PropertyValuesHolder ofFloat8 = PropertyValuesHolder.ofFloat("animTop", new float[]{this.endTransform.f2839top, this.startTransform.f2839top});
                PropertyValuesHolder ofFloat9 = PropertyValuesHolder.ofFloat("animWidth", new float[]{this.endTransform.width, this.startTransform.width});
                PropertyValuesHolder ofFloat10 = PropertyValuesHolder.ofFloat("animHeight", new float[]{this.endTransform.height, this.startTransform.height});
                this.animator.setValues(new PropertyValuesHolder[]{ofFloat6, ofInt2, ofFloat7, ofFloat8, ofFloat9, ofFloat10});
            }
            this.animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    SmoothImageView.this.animTransform.alpha = ((Integer) valueAnimator.getAnimatedValue("animAlpha")).intValue();
                    SmoothImageView.this.animTransform.scale = ((Float) valueAnimator.getAnimatedValue("animScale")).floatValue();
                    SmoothImageView.this.animTransform.left = ((Float) valueAnimator.getAnimatedValue("animLeft")).floatValue();
                    SmoothImageView.this.animTransform.f2839top = ((Float) valueAnimator.getAnimatedValue("animTop")).floatValue();
                    SmoothImageView.this.animTransform.width = ((Float) valueAnimator.getAnimatedValue("animWidth")).floatValue();
                    SmoothImageView.this.animTransform.height = ((Float) valueAnimator.getAnimatedValue("animHeight")).floatValue();
                    SmoothImageView.this.invalidate();
                }
            });
            this.animator.addListener(new AnimatorListenerAdapter() {
                public void onAnimationStart(Animator animator) {
                    if (SmoothImageView.this.getTag(C2517R.C2520id.item_image_key) != null) {
                        SmoothImageView.this.setTag(C2517R.C2520id.item_image_key, (Object) null);
                        SmoothImageView.this.setOnLongClickListener((View.OnLongClickListener) null);
                    }
                }

                public void onAnimationEnd(Animator animator) {
                    if (SmoothImageView.this.onTransformListener != null) {
                        SmoothImageView.this.onTransformListener.onTransformCompleted(SmoothImageView.this.mStatus);
                    }
                    if (SmoothImageView.this.mStatus == Status.STATE_IN) {
                        Status unused = SmoothImageView.this.mStatus = Status.STATE_NORMAL;
                    }
                }
            });
            this.animator.start();
        }
    }

    public void transformIn(onTransformListener ontransformlistener) {
        setOnTransformListener(ontransformlistener);
        this.transformStart = true;
        this.mStatus = Status.STATE_IN;
        invalidate();
    }

    public void transformOut(onTransformListener ontransformlistener) {
        if (getTop() != 0) {
            offsetTopAndBottom(-getTop());
        }
        if (getLeft() != 0) {
            offsetLeftAndRight(-getLeft());
        }
        if (getScaleX() != 1.0f) {
            setScaleX(1.0f);
            setScaleY(1.0f);
        }
        setOnTransformListener(ontransformlistener);
        this.transformStart = true;
        this.mStatus = Status.STATE_OUT;
        invalidate();
    }

    public void setThumbRect(Rect rect) {
        this.thumbRect = rect;
    }

    private void initTransform() {
        if (getDrawable() != null) {
            if ((this.startTransform == null || this.endTransform == null || this.animTransform == null) && getWidth() != 0 && getHeight() != 0) {
                if (getDrawable() instanceof BitmapDrawable) {
                    Bitmap bitmap = ((BitmapDrawable) getDrawable()).getBitmap();
                    this.bitmapWidth = bitmap.getWidth();
                    this.bitmapHeight = bitmap.getHeight();
                } else if (getDrawable() instanceof ColorDrawable) {
                    ColorDrawable colorDrawable = (ColorDrawable) getDrawable();
                    this.bitmapWidth = colorDrawable.getIntrinsicWidth();
                    this.bitmapHeight = colorDrawable.getIntrinsicHeight();
                } else {
                    Bitmap createBitmap = Bitmap.createBitmap(getDrawable().getIntrinsicWidth(), getDrawable().getIntrinsicHeight(), Bitmap.Config.RGB_565);
                    this.bitmapWidth = createBitmap.getWidth();
                    this.bitmapHeight = createBitmap.getHeight();
                }
                this.startTransform = new Transform();
                this.startTransform.alpha = 0;
                if (this.thumbRect == null) {
                    this.thumbRect = new Rect();
                }
                this.startTransform.left = (float) this.thumbRect.left;
                if (ISFUll) {
                    this.startTransform.f2839top = (float) this.thumbRect.top;
                } else {
                    this.startTransform.f2839top = (float) (this.thumbRect.top - ImageUtils.getStatusBarHeight(getContext().getApplicationContext()));
                }
                this.startTransform.width = (float) this.thumbRect.width();
                this.startTransform.height = (float) this.thumbRect.height();
                float width = ((float) this.thumbRect.width()) / ((float) this.bitmapWidth);
                float height = ((float) this.thumbRect.height()) / ((float) this.bitmapHeight);
                Transform transform = this.startTransform;
                if (width <= height) {
                    width = height;
                }
                transform.scale = width;
                float width2 = ((float) getWidth()) / ((float) this.bitmapWidth);
                float height2 = ((float) getHeight()) / ((float) this.bitmapHeight);
                this.endTransform = new Transform();
                Transform transform2 = this.endTransform;
                if (width2 >= height2) {
                    width2 = height2;
                }
                transform2.scale = width2;
                Transform transform3 = this.endTransform;
                transform3.alpha = 255;
                int i = (int) (transform3.scale * ((float) this.bitmapWidth));
                int i2 = (int) (this.endTransform.scale * ((float) this.bitmapHeight));
                this.endTransform.left = (float) ((getWidth() - i) / 2);
                this.endTransform.f2839top = (float) ((getHeight() - i2) / 2);
                Transform transform4 = this.endTransform;
                transform4.width = (float) i;
                transform4.height = (float) i2;
                if (this.mStatus == Status.STATE_IN) {
                    this.animTransform = this.startTransform.clone();
                } else if (this.mStatus == Status.STATE_OUT) {
                    this.animTransform = this.endTransform.clone();
                }
                this.markTransform = this.endTransform;
            }
        }
    }

    public void setOnTransformListener(onTransformListener ontransformlistener) {
        this.onTransformListener = ontransformlistener;
    }

    private class Transform implements Cloneable {
        int alpha;
        float height;
        float left;
        float scale;

        /* renamed from: top  reason: collision with root package name */
        float f2839top;
        float width;

        private Transform() {
        }

        public Transform clone() {
            try {
                return (Transform) super.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    public SmoothImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initSmoothImageView();
    }

    public SmoothImageView(Context context) {
        super(context);
        initSmoothImageView();
    }

    public void setDrag(boolean z, float f) {
        this.isDrag = z;
        this.MAX_TRANS_SCALE = f;
    }

    public static void setDuration(int i) {
        TRANSFORM_DURATION = i;
    }

    public static int getDuration() {
        return TRANSFORM_DURATION;
    }

    public static void setFullscreen(boolean z) {
        ISFUll = z;
    }

    public static void setIsScale(boolean z) {
        ISSCALE = z;
    }
}
