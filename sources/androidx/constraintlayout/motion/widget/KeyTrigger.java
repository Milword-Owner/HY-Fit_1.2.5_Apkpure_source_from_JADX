package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.View;
import androidx.constraintlayout.widget.C0207R;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;

public class KeyTrigger extends Key {
    public static final int KEY_TYPE = 5;
    static final String NAME = "KeyTrigger";
    private static final String TAG = "KeyTrigger";
    RectF mCollisionRect = new RectF();
    /* access modifiers changed from: private */
    public String mCross = null;
    private int mCurveFit = -1;
    private Method mFireCross;
    private boolean mFireCrossReset = true;
    private float mFireLastPos;
    private Method mFireNegativeCross;
    private boolean mFireNegativeReset = true;
    private Method mFirePositiveCross;
    private boolean mFirePositiveReset = true;
    /* access modifiers changed from: private */
    public float mFireThreshold = Float.NaN;
    /* access modifiers changed from: private */
    public String mNegativeCross = null;
    /* access modifiers changed from: private */
    public String mPositiveCross = null;
    /* access modifiers changed from: private */
    public boolean mPostLayout = false;
    RectF mTargetRect = new RectF();
    /* access modifiers changed from: private */
    public int mTriggerCollisionId = UNSET;
    private View mTriggerCollisionView = null;
    /* access modifiers changed from: private */
    public int mTriggerID = UNSET;
    /* access modifiers changed from: private */
    public int mTriggerReceiver = UNSET;
    float mTriggerSlack = 0.1f;

    public void addValues(HashMap<String, SplineSet> hashMap) {
    }

    public void getAttributeNames(HashSet<String> hashSet) {
    }

    public void setValue(String str, Object obj) {
    }

    public KeyTrigger() {
        this.mType = 5;
        this.mCustomConstraints = new HashMap();
    }

    public void load(Context context, AttributeSet attributeSet) {
        Loader.read(this, context.obtainStyledAttributes(attributeSet, C0207R.styleable.KeyTrigger), context);
    }

    /* access modifiers changed from: package-private */
    public int getCurveFit() {
        return this.mCurveFit;
    }

    private void setUpRect(RectF rectF, View view, boolean z) {
        rectF.top = (float) view.getTop();
        rectF.bottom = (float) view.getBottom();
        rectF.left = (float) view.getLeft();
        rectF.right = (float) view.getRight();
        if (z) {
            view.getMatrix().mapRect(rectF);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:37:0x008e  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00a3  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00b8  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00cc  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void conditionallyFire(float r11, android.view.View r12) {
        /*
            r10 = this;
            int r0 = r10.mTriggerCollisionId
            int r1 = UNSET
            r2 = 1
            r3 = 0
            if (r0 == r1) goto L_0x0063
            android.view.View r0 = r10.mTriggerCollisionView
            if (r0 != 0) goto L_0x001a
            android.view.ViewParent r0 = r12.getParent()
            android.view.ViewGroup r0 = (android.view.ViewGroup) r0
            int r1 = r10.mTriggerCollisionId
            android.view.View r0 = r0.findViewById(r1)
            r10.mTriggerCollisionView = r0
        L_0x001a:
            android.graphics.RectF r0 = r10.mCollisionRect
            android.view.View r1 = r10.mTriggerCollisionView
            boolean r4 = r10.mPostLayout
            r10.setUpRect(r0, r1, r4)
            android.graphics.RectF r0 = r10.mTargetRect
            boolean r1 = r10.mPostLayout
            r10.setUpRect(r0, r12, r1)
            android.graphics.RectF r0 = r10.mCollisionRect
            android.graphics.RectF r1 = r10.mTargetRect
            boolean r0 = r0.intersect(r1)
            if (r0 == 0) goto L_0x004b
            boolean r0 = r10.mFireCrossReset
            if (r0 == 0) goto L_0x003c
            r10.mFireCrossReset = r3
            r0 = 1
            goto L_0x003d
        L_0x003c:
            r0 = 0
        L_0x003d:
            boolean r1 = r10.mFirePositiveReset
            if (r1 == 0) goto L_0x0045
            r10.mFirePositiveReset = r3
            r1 = 1
            goto L_0x0046
        L_0x0045:
            r1 = 0
        L_0x0046:
            r10.mFireNegativeReset = r2
            r2 = r1
            r1 = 0
            goto L_0x0060
        L_0x004b:
            boolean r0 = r10.mFireCrossReset
            if (r0 != 0) goto L_0x0053
            r10.mFireCrossReset = r2
            r0 = 1
            goto L_0x0054
        L_0x0053:
            r0 = 0
        L_0x0054:
            boolean r1 = r10.mFireNegativeReset
            if (r1 == 0) goto L_0x005c
            r10.mFireNegativeReset = r3
            r1 = 1
            goto L_0x005d
        L_0x005c:
            r1 = 0
        L_0x005d:
            r10.mFirePositiveReset = r2
            r2 = 0
        L_0x0060:
            r4 = r1
            goto L_0x00dd
        L_0x0063:
            boolean r0 = r10.mFireCrossReset
            r1 = 0
            if (r0 == 0) goto L_0x0079
            float r0 = r10.mFireThreshold
            float r4 = r11 - r0
            float r5 = r10.mFireLastPos
            float r5 = r5 - r0
            float r4 = r4 * r5
            int r0 = (r4 > r1 ? 1 : (r4 == r1 ? 0 : -1))
            if (r0 >= 0) goto L_0x0089
            r10.mFireCrossReset = r3
            r0 = 1
            goto L_0x008a
        L_0x0079:
            float r0 = r10.mFireThreshold
            float r0 = r11 - r0
            float r0 = java.lang.Math.abs(r0)
            float r4 = r10.mTriggerSlack
            int r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r0 <= 0) goto L_0x0089
            r10.mFireCrossReset = r2
        L_0x0089:
            r0 = 0
        L_0x008a:
            boolean r4 = r10.mFireNegativeReset
            if (r4 == 0) goto L_0x00a3
            float r4 = r10.mFireThreshold
            float r5 = r11 - r4
            float r6 = r10.mFireLastPos
            float r6 = r6 - r4
            float r6 = r6 * r5
            int r4 = (r6 > r1 ? 1 : (r6 == r1 ? 0 : -1))
            if (r4 >= 0) goto L_0x00b3
            int r4 = (r5 > r1 ? 1 : (r5 == r1 ? 0 : -1))
            if (r4 >= 0) goto L_0x00b3
            r10.mFireNegativeReset = r3
            r4 = 1
            goto L_0x00b4
        L_0x00a3:
            float r4 = r10.mFireThreshold
            float r4 = r11 - r4
            float r4 = java.lang.Math.abs(r4)
            float r5 = r10.mTriggerSlack
            int r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r4 <= 0) goto L_0x00b3
            r10.mFireNegativeReset = r2
        L_0x00b3:
            r4 = 0
        L_0x00b4:
            boolean r5 = r10.mFirePositiveReset
            if (r5 == 0) goto L_0x00cc
            float r5 = r10.mFireThreshold
            float r6 = r11 - r5
            float r7 = r10.mFireLastPos
            float r7 = r7 - r5
            float r7 = r7 * r6
            int r5 = (r7 > r1 ? 1 : (r7 == r1 ? 0 : -1))
            if (r5 >= 0) goto L_0x00dc
            int r1 = (r6 > r1 ? 1 : (r6 == r1 ? 0 : -1))
            if (r1 <= 0) goto L_0x00dc
            r10.mFirePositiveReset = r3
            goto L_0x00dd
        L_0x00cc:
            float r1 = r10.mFireThreshold
            float r1 = r11 - r1
            float r1 = java.lang.Math.abs(r1)
            float r5 = r10.mTriggerSlack
            int r1 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1))
            if (r1 <= 0) goto L_0x00dc
            r10.mFirePositiveReset = r2
        L_0x00dc:
            r2 = 0
        L_0x00dd:
            r10.mFireLastPos = r11
            if (r4 != 0) goto L_0x00e5
            if (r0 != 0) goto L_0x00e5
            if (r2 == 0) goto L_0x00f0
        L_0x00e5:
            android.view.ViewParent r1 = r12.getParent()
            androidx.constraintlayout.motion.widget.MotionLayout r1 = (androidx.constraintlayout.motion.widget.MotionLayout) r1
            int r5 = r10.mTriggerID
            r1.fireTrigger(r5, r2, r11)
        L_0x00f0:
            int r11 = r10.mTriggerReceiver
            int r1 = UNSET
            if (r11 != r1) goto L_0x00f7
            goto L_0x0103
        L_0x00f7:
            android.view.ViewParent r11 = r12.getParent()
            androidx.constraintlayout.motion.widget.MotionLayout r11 = (androidx.constraintlayout.motion.widget.MotionLayout) r11
            int r12 = r10.mTriggerReceiver
            android.view.View r12 = r11.findViewById(r12)
        L_0x0103:
            java.lang.String r11 = "Could not find method \""
            java.lang.String r1 = "Exception in call \""
            java.lang.String r5 = " "
            java.lang.String r6 = "\"on class "
            java.lang.String r7 = "KeyTrigger"
            if (r4 == 0) goto L_0x0186
            java.lang.String r4 = r10.mNegativeCross
            if (r4 == 0) goto L_0x0186
            java.lang.reflect.Method r4 = r10.mFireNegativeCross
            if (r4 != 0) goto L_0x0152
            java.lang.Class r4 = r12.getClass()     // Catch:{ NoSuchMethodException -> 0x0126 }
            java.lang.String r8 = r10.mNegativeCross     // Catch:{ NoSuchMethodException -> 0x0126 }
            java.lang.Class[] r9 = new java.lang.Class[r3]     // Catch:{ NoSuchMethodException -> 0x0126 }
            java.lang.reflect.Method r4 = r4.getMethod(r8, r9)     // Catch:{ NoSuchMethodException -> 0x0126 }
            r10.mFireNegativeCross = r4     // Catch:{ NoSuchMethodException -> 0x0126 }
            goto L_0x0152
        L_0x0126:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r11)
            java.lang.String r8 = r10.mNegativeCross
            r4.append(r8)
            r4.append(r6)
            java.lang.Class r8 = r12.getClass()
            java.lang.String r8 = r8.getSimpleName()
            r4.append(r8)
            r4.append(r5)
            java.lang.String r8 = androidx.constraintlayout.motion.widget.Debug.getName(r12)
            r4.append(r8)
            java.lang.String r4 = r4.toString()
            android.util.Log.e(r7, r4)
        L_0x0152:
            java.lang.reflect.Method r4 = r10.mFireNegativeCross     // Catch:{ Exception -> 0x015a }
            java.lang.Object[] r8 = new java.lang.Object[r3]     // Catch:{ Exception -> 0x015a }
            r4.invoke(r12, r8)     // Catch:{ Exception -> 0x015a }
            goto L_0x0186
        L_0x015a:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r1)
            java.lang.String r8 = r10.mNegativeCross
            r4.append(r8)
            r4.append(r6)
            java.lang.Class r8 = r12.getClass()
            java.lang.String r8 = r8.getSimpleName()
            r4.append(r8)
            r4.append(r5)
            java.lang.String r8 = androidx.constraintlayout.motion.widget.Debug.getName(r12)
            r4.append(r8)
            java.lang.String r4 = r4.toString()
            android.util.Log.e(r7, r4)
        L_0x0186:
            if (r2 == 0) goto L_0x01ff
            java.lang.String r2 = r10.mPositiveCross
            if (r2 == 0) goto L_0x01ff
            java.lang.reflect.Method r2 = r10.mFirePositiveCross
            if (r2 != 0) goto L_0x01cb
            java.lang.Class r2 = r12.getClass()     // Catch:{ NoSuchMethodException -> 0x019f }
            java.lang.String r4 = r10.mPositiveCross     // Catch:{ NoSuchMethodException -> 0x019f }
            java.lang.Class[] r8 = new java.lang.Class[r3]     // Catch:{ NoSuchMethodException -> 0x019f }
            java.lang.reflect.Method r2 = r2.getMethod(r4, r8)     // Catch:{ NoSuchMethodException -> 0x019f }
            r10.mFirePositiveCross = r2     // Catch:{ NoSuchMethodException -> 0x019f }
            goto L_0x01cb
        L_0x019f:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r11)
            java.lang.String r4 = r10.mPositiveCross
            r2.append(r4)
            r2.append(r6)
            java.lang.Class r4 = r12.getClass()
            java.lang.String r4 = r4.getSimpleName()
            r2.append(r4)
            r2.append(r5)
            java.lang.String r4 = androidx.constraintlayout.motion.widget.Debug.getName(r12)
            r2.append(r4)
            java.lang.String r2 = r2.toString()
            android.util.Log.e(r7, r2)
        L_0x01cb:
            java.lang.reflect.Method r2 = r10.mFirePositiveCross     // Catch:{ Exception -> 0x01d3 }
            java.lang.Object[] r4 = new java.lang.Object[r3]     // Catch:{ Exception -> 0x01d3 }
            r2.invoke(r12, r4)     // Catch:{ Exception -> 0x01d3 }
            goto L_0x01ff
        L_0x01d3:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r1)
            java.lang.String r4 = r10.mPositiveCross
            r2.append(r4)
            r2.append(r6)
            java.lang.Class r4 = r12.getClass()
            java.lang.String r4 = r4.getSimpleName()
            r2.append(r4)
            r2.append(r5)
            java.lang.String r4 = androidx.constraintlayout.motion.widget.Debug.getName(r12)
            r2.append(r4)
            java.lang.String r2 = r2.toString()
            android.util.Log.e(r7, r2)
        L_0x01ff:
            if (r0 == 0) goto L_0x0278
            java.lang.String r0 = r10.mCross
            if (r0 == 0) goto L_0x0278
            java.lang.reflect.Method r0 = r10.mFireCross
            if (r0 != 0) goto L_0x0244
            java.lang.Class r0 = r12.getClass()     // Catch:{ NoSuchMethodException -> 0x0218 }
            java.lang.String r2 = r10.mCross     // Catch:{ NoSuchMethodException -> 0x0218 }
            java.lang.Class[] r4 = new java.lang.Class[r3]     // Catch:{ NoSuchMethodException -> 0x0218 }
            java.lang.reflect.Method r0 = r0.getMethod(r2, r4)     // Catch:{ NoSuchMethodException -> 0x0218 }
            r10.mFireCross = r0     // Catch:{ NoSuchMethodException -> 0x0218 }
            goto L_0x0244
        L_0x0218:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r11)
            java.lang.String r11 = r10.mCross
            r0.append(r11)
            r0.append(r6)
            java.lang.Class r11 = r12.getClass()
            java.lang.String r11 = r11.getSimpleName()
            r0.append(r11)
            r0.append(r5)
            java.lang.String r11 = androidx.constraintlayout.motion.widget.Debug.getName(r12)
            r0.append(r11)
            java.lang.String r11 = r0.toString()
            android.util.Log.e(r7, r11)
        L_0x0244:
            java.lang.reflect.Method r11 = r10.mFireCross     // Catch:{ Exception -> 0x024c }
            java.lang.Object[] r0 = new java.lang.Object[r3]     // Catch:{ Exception -> 0x024c }
            r11.invoke(r12, r0)     // Catch:{ Exception -> 0x024c }
            goto L_0x0278
        L_0x024c:
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            r11.append(r1)
            java.lang.String r0 = r10.mCross
            r11.append(r0)
            r11.append(r6)
            java.lang.Class r0 = r12.getClass()
            java.lang.String r0 = r0.getSimpleName()
            r11.append(r0)
            r11.append(r5)
            java.lang.String r12 = androidx.constraintlayout.motion.widget.Debug.getName(r12)
            r11.append(r12)
            java.lang.String r11 = r11.toString()
            android.util.Log.e(r7, r11)
        L_0x0278:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.KeyTrigger.conditionallyFire(float, android.view.View):void");
    }

    private static class Loader {
        private static final int COLLISION = 9;
        private static final int CROSS = 4;
        private static final int FRAME_POS = 8;
        private static final int NEGATIVE_CROSS = 1;
        private static final int POSITIVE_CROSS = 2;
        private static final int POST_LAYOUT = 10;
        private static final int TARGET_ID = 7;
        private static final int TRIGGER_ID = 6;
        private static final int TRIGGER_RECEIVER = 11;
        private static final int TRIGGER_SLACK = 5;
        private static SparseIntArray mAttrMap = new SparseIntArray();

        private Loader() {
        }

        static {
            mAttrMap.append(C0207R.styleable.KeyTrigger_framePosition, 8);
            mAttrMap.append(C0207R.styleable.KeyTrigger_onCross, 4);
            mAttrMap.append(C0207R.styleable.KeyTrigger_onNegativeCross, 1);
            mAttrMap.append(C0207R.styleable.KeyTrigger_onPositiveCross, 2);
            mAttrMap.append(C0207R.styleable.KeyTrigger_motionTarget, 7);
            mAttrMap.append(C0207R.styleable.KeyTrigger_triggerId, 6);
            mAttrMap.append(C0207R.styleable.KeyTrigger_triggerSlack, 5);
            mAttrMap.append(C0207R.styleable.KeyTrigger_motion_triggerOnCollision, 9);
            mAttrMap.append(C0207R.styleable.KeyTrigger_motion_postLayoutCollision, 10);
            mAttrMap.append(C0207R.styleable.KeyTrigger_triggerReceiver, 11);
        }

        public static void read(KeyTrigger keyTrigger, TypedArray typedArray, Context context) {
            int indexCount = typedArray.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArray.getIndex(i);
                switch (mAttrMap.get(index)) {
                    case 1:
                        String unused = keyTrigger.mNegativeCross = typedArray.getString(index);
                        continue;
                    case 2:
                        String unused2 = keyTrigger.mPositiveCross = typedArray.getString(index);
                        continue;
                    case 4:
                        String unused3 = keyTrigger.mCross = typedArray.getString(index);
                        continue;
                    case 5:
                        keyTrigger.mTriggerSlack = typedArray.getFloat(index, keyTrigger.mTriggerSlack);
                        continue;
                    case 6:
                        int unused4 = keyTrigger.mTriggerID = typedArray.getResourceId(index, keyTrigger.mTriggerID);
                        continue;
                    case 7:
                        if (!MotionLayout.IS_IN_EDIT_MODE) {
                            if (typedArray.peekValue(index).type != 3) {
                                keyTrigger.mTargetId = typedArray.getResourceId(index, keyTrigger.mTargetId);
                                break;
                            } else {
                                keyTrigger.mTargetString = typedArray.getString(index);
                                break;
                            }
                        } else {
                            keyTrigger.mTargetId = typedArray.getResourceId(index, keyTrigger.mTargetId);
                            if (keyTrigger.mTargetId == -1) {
                                keyTrigger.mTargetString = typedArray.getString(index);
                                break;
                            } else {
                                continue;
                            }
                        }
                    case 8:
                        keyTrigger.mFramePosition = typedArray.getInteger(index, keyTrigger.mFramePosition);
                        float unused5 = keyTrigger.mFireThreshold = (((float) keyTrigger.mFramePosition) + 0.5f) / 100.0f;
                        continue;
                    case 9:
                        int unused6 = keyTrigger.mTriggerCollisionId = typedArray.getResourceId(index, keyTrigger.mTriggerCollisionId);
                        continue;
                    case 10:
                        boolean unused7 = keyTrigger.mPostLayout = typedArray.getBoolean(index, keyTrigger.mPostLayout);
                        continue;
                    case 11:
                        int unused8 = keyTrigger.mTriggerReceiver = typedArray.getResourceId(index, keyTrigger.mTriggerReceiver);
                        break;
                }
                Log.e("KeyTrigger", "unused attribute 0x" + Integer.toHexString(index) + "   " + mAttrMap.get(index));
            }
        }
    }
}
