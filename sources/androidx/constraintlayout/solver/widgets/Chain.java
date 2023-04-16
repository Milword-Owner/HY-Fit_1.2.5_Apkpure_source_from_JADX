package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.LinearSystem;
import java.util.ArrayList;

public class Chain {
    private static final boolean DEBUG = false;
    public static final boolean USE_CHAIN_OPTIMIZATION = false;

    public static void applyChainConstraints(ConstraintWidgetContainer constraintWidgetContainer, LinearSystem linearSystem, ArrayList<ConstraintWidget> arrayList, int i) {
        int i2;
        ChainHead[] chainHeadArr;
        int i3;
        if (i == 0) {
            i3 = constraintWidgetContainer.mHorizontalChainsSize;
            chainHeadArr = constraintWidgetContainer.mHorizontalChainsArray;
            i2 = 0;
        } else {
            i3 = constraintWidgetContainer.mVerticalChainsSize;
            chainHeadArr = constraintWidgetContainer.mVerticalChainsArray;
            i2 = 2;
        }
        for (int i4 = 0; i4 < i3; i4++) {
            ChainHead chainHead = chainHeadArr[i4];
            chainHead.define();
            if (arrayList == null || (arrayList != null && arrayList.contains(chainHead.mFirst))) {
                applyChainConstraints(constraintWidgetContainer, linearSystem, i, i2, chainHead);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0035, code lost:
        if (r2.mHorizontalChainStyle == 2) goto L_0x004a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0048, code lost:
        if (r2.mVerticalChainStyle == 2) goto L_0x004a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x004c, code lost:
        r5 = false;
     */
    /* JADX WARNING: Removed duplicated region for block: B:104:0x01a9  */
    /* JADX WARNING: Removed duplicated region for block: B:113:0x01d7  */
    /* JADX WARNING: Removed duplicated region for block: B:114:0x01db  */
    /* JADX WARNING: Removed duplicated region for block: B:117:0x01e5  */
    /* JADX WARNING: Removed duplicated region for block: B:211:0x03c5  */
    /* JADX WARNING: Removed duplicated region for block: B:220:0x03da  */
    /* JADX WARNING: Removed duplicated region for block: B:221:0x03dd  */
    /* JADX WARNING: Removed duplicated region for block: B:224:0x03e3  */
    /* JADX WARNING: Removed duplicated region for block: B:272:0x04b9  */
    /* JADX WARNING: Removed duplicated region for block: B:277:0x04ee  */
    /* JADX WARNING: Removed duplicated region for block: B:287:0x0515  */
    /* JADX WARNING: Removed duplicated region for block: B:288:0x051a  */
    /* JADX WARNING: Removed duplicated region for block: B:291:0x0520  */
    /* JADX WARNING: Removed duplicated region for block: B:292:0x0525  */
    /* JADX WARNING: Removed duplicated region for block: B:294:0x0529  */
    /* JADX WARNING: Removed duplicated region for block: B:300:0x053b  */
    /* JADX WARNING: Removed duplicated region for block: B:315:0x03c6 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void applyChainConstraints(androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer r37, androidx.constraintlayout.solver.LinearSystem r38, int r39, int r40, androidx.constraintlayout.solver.widgets.ChainHead r41) {
        /*
            r0 = r37
            r9 = r38
            r1 = r41
            androidx.constraintlayout.solver.widgets.ConstraintWidget r10 = r1.mFirst
            androidx.constraintlayout.solver.widgets.ConstraintWidget r11 = r1.mLast
            androidx.constraintlayout.solver.widgets.ConstraintWidget r12 = r1.mFirstVisibleWidget
            androidx.constraintlayout.solver.widgets.ConstraintWidget r13 = r1.mLastVisibleWidget
            androidx.constraintlayout.solver.widgets.ConstraintWidget r2 = r1.mHead
            float r3 = r1.mTotalWeight
            androidx.constraintlayout.solver.widgets.ConstraintWidget r4 = r1.mFirstMatchConstraintWidget
            androidx.constraintlayout.solver.widgets.ConstraintWidget r4 = r1.mLastMatchConstraintWidget
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r4 = r0.mListDimensionBehaviors
            r4 = r4[r39]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r5 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            r7 = 1
            if (r4 != r5) goto L_0x0021
            r4 = 1
            goto L_0x0022
        L_0x0021:
            r4 = 0
        L_0x0022:
            r5 = 2
            if (r39 != 0) goto L_0x0038
            int r8 = r2.mHorizontalChainStyle
            if (r8 != 0) goto L_0x002b
            r8 = 1
            goto L_0x002c
        L_0x002b:
            r8 = 0
        L_0x002c:
            int r14 = r2.mHorizontalChainStyle
            if (r14 != r7) goto L_0x0032
            r14 = 1
            goto L_0x0033
        L_0x0032:
            r14 = 0
        L_0x0033:
            int r15 = r2.mHorizontalChainStyle
            if (r15 != r5) goto L_0x004c
            goto L_0x004a
        L_0x0038:
            int r8 = r2.mVerticalChainStyle
            if (r8 != 0) goto L_0x003e
            r8 = 1
            goto L_0x003f
        L_0x003e:
            r8 = 0
        L_0x003f:
            int r14 = r2.mVerticalChainStyle
            if (r14 != r7) goto L_0x0045
            r14 = 1
            goto L_0x0046
        L_0x0045:
            r14 = 0
        L_0x0046:
            int r15 = r2.mVerticalChainStyle
            if (r15 != r5) goto L_0x004c
        L_0x004a:
            r5 = 1
            goto L_0x004d
        L_0x004c:
            r5 = 0
        L_0x004d:
            r15 = r8
            r8 = r10
            r16 = r14
            r14 = r5
            r5 = 0
        L_0x0053:
            r21 = 0
            if (r5 != 0) goto L_0x0139
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r6 = r8.mListAnchors
            r6 = r6[r40]
            if (r14 == 0) goto L_0x0060
            r19 = 1
            goto L_0x0062
        L_0x0060:
            r19 = 4
        L_0x0062:
            int r23 = r6.getMargin()
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r7 = r8.mListDimensionBehaviors
            r7 = r7[r39]
            r25 = r3
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r3 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r7 != r3) goto L_0x0078
            int[] r3 = r8.mResolvedMatchConstraintDefault
            r3 = r3[r39]
            if (r3 != 0) goto L_0x0078
            r3 = 1
            goto L_0x0079
        L_0x0078:
            r3 = 0
        L_0x0079:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r7 = r6.mTarget
            if (r7 == 0) goto L_0x0087
            if (r8 == r10) goto L_0x0087
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r7 = r6.mTarget
            int r7 = r7.getMargin()
            int r23 = r23 + r7
        L_0x0087:
            r7 = r23
            if (r14 == 0) goto L_0x0094
            if (r8 == r10) goto L_0x0094
            if (r8 == r12) goto L_0x0094
            r23 = r5
            r19 = 8
            goto L_0x0096
        L_0x0094:
            r23 = r5
        L_0x0096:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r5 = r6.mTarget
            if (r5 == 0) goto L_0x00cc
            if (r8 != r12) goto L_0x00ab
            androidx.constraintlayout.solver.SolverVariable r5 = r6.mSolverVariable
            r26 = r15
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r15 = r6.mTarget
            androidx.constraintlayout.solver.SolverVariable r15 = r15.mSolverVariable
            r27 = r2
            r2 = 6
            r9.addGreaterThan(r5, r15, r7, r2)
            goto L_0x00ba
        L_0x00ab:
            r27 = r2
            r26 = r15
            androidx.constraintlayout.solver.SolverVariable r2 = r6.mSolverVariable
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r5 = r6.mTarget
            androidx.constraintlayout.solver.SolverVariable r5 = r5.mSolverVariable
            r15 = 8
            r9.addGreaterThan(r2, r5, r7, r15)
        L_0x00ba:
            if (r3 == 0) goto L_0x00c0
            if (r14 != 0) goto L_0x00c0
            r2 = 5
            goto L_0x00c2
        L_0x00c0:
            r2 = r19
        L_0x00c2:
            androidx.constraintlayout.solver.SolverVariable r3 = r6.mSolverVariable
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r5 = r6.mTarget
            androidx.constraintlayout.solver.SolverVariable r5 = r5.mSolverVariable
            r9.addEquality(r3, r5, r7, r2)
            goto L_0x00d0
        L_0x00cc:
            r27 = r2
            r26 = r15
        L_0x00d0:
            if (r4 == 0) goto L_0x0108
            int r2 = r8.getVisibility()
            r3 = 8
            if (r2 == r3) goto L_0x00f6
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r2 = r8.mListDimensionBehaviors
            r2 = r2[r39]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r3 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r2 != r3) goto L_0x00f6
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r2 = r8.mListAnchors
            int r3 = r40 + 1
            r2 = r2[r3]
            androidx.constraintlayout.solver.SolverVariable r2 = r2.mSolverVariable
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r3 = r8.mListAnchors
            r3 = r3[r40]
            androidx.constraintlayout.solver.SolverVariable r3 = r3.mSolverVariable
            r5 = 5
            r6 = 0
            r9.addGreaterThan(r2, r3, r6, r5)
            goto L_0x00f7
        L_0x00f6:
            r6 = 0
        L_0x00f7:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r2 = r8.mListAnchors
            r2 = r2[r40]
            androidx.constraintlayout.solver.SolverVariable r2 = r2.mSolverVariable
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r3 = r0.mListAnchors
            r3 = r3[r40]
            androidx.constraintlayout.solver.SolverVariable r3 = r3.mSolverVariable
            r5 = 8
            r9.addGreaterThan(r2, r3, r6, r5)
        L_0x0108:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r2 = r8.mListAnchors
            int r3 = r40 + 1
            r2 = r2[r3]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r2 = r2.mTarget
            if (r2 == 0) goto L_0x0129
            androidx.constraintlayout.solver.widgets.ConstraintWidget r2 = r2.mOwner
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r3 = r2.mListAnchors
            r3 = r3[r40]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            if (r3 == 0) goto L_0x0129
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r3 = r2.mListAnchors
            r3 = r3[r40]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            androidx.constraintlayout.solver.widgets.ConstraintWidget r3 = r3.mOwner
            if (r3 == r8) goto L_0x0127
            goto L_0x0129
        L_0x0127:
            r21 = r2
        L_0x0129:
            if (r21 == 0) goto L_0x0130
            r8 = r21
            r5 = r23
            goto L_0x0131
        L_0x0130:
            r5 = 1
        L_0x0131:
            r3 = r25
            r15 = r26
            r2 = r27
            goto L_0x0053
        L_0x0139:
            r27 = r2
            r25 = r3
            r26 = r15
            if (r13 == 0) goto L_0x01a6
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r2 = r11.mListAnchors
            int r3 = r40 + 1
            r2 = r2[r3]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r2 = r2.mTarget
            if (r2 == 0) goto L_0x01a6
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r2 = r13.mListAnchors
            r2 = r2[r3]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r5 = r13.mListDimensionBehaviors
            r5 = r5[r39]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r6 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r5 != r6) goto L_0x015f
            int[] r5 = r13.mResolvedMatchConstraintDefault
            r5 = r5[r39]
            if (r5 != 0) goto L_0x015f
            r5 = 1
            goto L_0x0160
        L_0x015f:
            r5 = 0
        L_0x0160:
            if (r5 == 0) goto L_0x017a
            if (r14 != 0) goto L_0x017a
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r5 = r2.mTarget
            androidx.constraintlayout.solver.widgets.ConstraintWidget r5 = r5.mOwner
            if (r5 != r0) goto L_0x017a
            androidx.constraintlayout.solver.SolverVariable r5 = r2.mSolverVariable
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r6 = r2.mTarget
            androidx.constraintlayout.solver.SolverVariable r6 = r6.mSolverVariable
            int r7 = r2.getMargin()
            int r7 = -r7
            r8 = 5
            r9.addEquality(r5, r6, r7, r8)
            goto L_0x0192
        L_0x017a:
            r8 = 5
            if (r14 == 0) goto L_0x0192
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r5 = r2.mTarget
            androidx.constraintlayout.solver.widgets.ConstraintWidget r5 = r5.mOwner
            if (r5 != r0) goto L_0x0192
            androidx.constraintlayout.solver.SolverVariable r5 = r2.mSolverVariable
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r6 = r2.mTarget
            androidx.constraintlayout.solver.SolverVariable r6 = r6.mSolverVariable
            int r7 = r2.getMargin()
            int r7 = -r7
            r15 = 4
            r9.addEquality(r5, r6, r7, r15)
        L_0x0192:
            androidx.constraintlayout.solver.SolverVariable r5 = r2.mSolverVariable
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r6 = r11.mListAnchors
            r3 = r6[r3]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            androidx.constraintlayout.solver.SolverVariable r3 = r3.mSolverVariable
            int r2 = r2.getMargin()
            int r2 = -r2
            r6 = 6
            r9.addLowerThan(r5, r3, r2, r6)
            goto L_0x01a7
        L_0x01a6:
            r8 = 5
        L_0x01a7:
            if (r4 == 0) goto L_0x01c4
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r0 = r0.mListAnchors
            int r2 = r40 + 1
            r0 = r0[r2]
            androidx.constraintlayout.solver.SolverVariable r0 = r0.mSolverVariable
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r3 = r11.mListAnchors
            r3 = r3[r2]
            androidx.constraintlayout.solver.SolverVariable r3 = r3.mSolverVariable
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r4 = r11.mListAnchors
            r2 = r4[r2]
            int r2 = r2.getMargin()
            r4 = 8
            r9.addGreaterThan(r0, r3, r2, r4)
        L_0x01c4:
            java.util.ArrayList<androidx.constraintlayout.solver.widgets.ConstraintWidget> r0 = r1.mWeightedMatchConstraintsWidgets
            if (r0 == 0) goto L_0x027b
            int r2 = r0.size()
            r3 = 1
            if (r2 <= r3) goto L_0x027b
            boolean r4 = r1.mHasUndefinedWeights
            if (r4 == 0) goto L_0x01db
            boolean r4 = r1.mHasComplexMatchWeights
            if (r4 != 0) goto L_0x01db
            int r4 = r1.mWidgetsMatchCount
            float r4 = (float) r4
            goto L_0x01dd
        L_0x01db:
            r4 = r25
        L_0x01dd:
            r5 = 0
            r7 = r21
            r6 = 0
            r29 = 0
        L_0x01e3:
            if (r6 >= r2) goto L_0x027b
            java.lang.Object r15 = r0.get(r6)
            androidx.constraintlayout.solver.widgets.ConstraintWidget r15 = (androidx.constraintlayout.solver.widgets.ConstraintWidget) r15
            float[] r3 = r15.mWeight
            r3 = r3[r39]
            int r20 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r20 >= 0) goto L_0x0211
            boolean r3 = r1.mHasComplexMatchWeights
            if (r3 == 0) goto L_0x020c
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r3 = r15.mListAnchors
            int r20 = r40 + 1
            r3 = r3[r20]
            androidx.constraintlayout.solver.SolverVariable r3 = r3.mSolverVariable
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r15 = r15.mListAnchors
            r15 = r15[r40]
            androidx.constraintlayout.solver.SolverVariable r15 = r15.mSolverVariable
            r5 = 0
            r8 = 4
            r9.addEquality(r3, r15, r5, r8)
            r8 = 0
            goto L_0x022a
        L_0x020c:
            r8 = 4
            r3 = 1065353216(0x3f800000, float:1.0)
            r5 = 0
            goto L_0x0212
        L_0x0211:
            r8 = 4
        L_0x0212:
            int r19 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r19 != 0) goto L_0x022f
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r3 = r15.mListAnchors
            int r19 = r40 + 1
            r3 = r3[r19]
            androidx.constraintlayout.solver.SolverVariable r3 = r3.mSolverVariable
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r15 = r15.mListAnchors
            r15 = r15[r40]
            androidx.constraintlayout.solver.SolverVariable r15 = r15.mSolverVariable
            r5 = 8
            r8 = 0
            r9.addEquality(r3, r15, r8, r5)
        L_0x022a:
            r24 = r0
            r18 = r2
            goto L_0x0270
        L_0x022f:
            r8 = 0
            if (r7 == 0) goto L_0x0269
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r5 = r7.mListAnchors
            r5 = r5[r40]
            androidx.constraintlayout.solver.SolverVariable r5 = r5.mSolverVariable
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r7 = r7.mListAnchors
            int r18 = r40 + 1
            r7 = r7[r18]
            androidx.constraintlayout.solver.SolverVariable r7 = r7.mSolverVariable
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r8 = r15.mListAnchors
            r8 = r8[r40]
            androidx.constraintlayout.solver.SolverVariable r8 = r8.mSolverVariable
            r24 = r0
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r0 = r15.mListAnchors
            r0 = r0[r18]
            androidx.constraintlayout.solver.SolverVariable r0 = r0.mSolverVariable
            r18 = r2
            androidx.constraintlayout.solver.ArrayRow r2 = r38.createRow()
            r28 = r2
            r30 = r4
            r31 = r3
            r32 = r5
            r33 = r7
            r34 = r8
            r35 = r0
            r28.createRowEqualMatchDimensions(r29, r30, r31, r32, r33, r34, r35)
            r9.addConstraint(r2)
            goto L_0x026d
        L_0x0269:
            r24 = r0
            r18 = r2
        L_0x026d:
            r29 = r3
            r7 = r15
        L_0x0270:
            int r6 = r6 + 1
            r2 = r18
            r0 = r24
            r3 = 1
            r5 = 0
            r8 = 5
            goto L_0x01e3
        L_0x027b:
            if (r12 == 0) goto L_0x02d4
            if (r12 == r13) goto L_0x0281
            if (r14 == 0) goto L_0x02d4
        L_0x0281:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r0 = r10.mListAnchors
            r0 = r0[r40]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r1 = r11.mListAnchors
            int r2 = r40 + 1
            r1 = r1[r2]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r0.mTarget
            if (r3 == 0) goto L_0x0295
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r0.mTarget
            androidx.constraintlayout.solver.SolverVariable r0 = r0.mSolverVariable
            r3 = r0
            goto L_0x0297
        L_0x0295:
            r3 = r21
        L_0x0297:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r1.mTarget
            if (r0 == 0) goto L_0x02a1
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r1.mTarget
            androidx.constraintlayout.solver.SolverVariable r0 = r0.mSolverVariable
            r5 = r0
            goto L_0x02a3
        L_0x02a1:
            r5 = r21
        L_0x02a3:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r0 = r12.mListAnchors
            r0 = r0[r40]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r1 = r13.mListAnchors
            r1 = r1[r2]
            if (r3 == 0) goto L_0x04ff
            if (r5 == 0) goto L_0x04ff
            if (r39 != 0) goto L_0x02b6
            r2 = r27
            float r2 = r2.mHorizontalBiasPercent
            goto L_0x02ba
        L_0x02b6:
            r2 = r27
            float r2 = r2.mVerticalBiasPercent
        L_0x02ba:
            r4 = r2
            int r6 = r0.getMargin()
            int r7 = r1.getMargin()
            androidx.constraintlayout.solver.SolverVariable r2 = r0.mSolverVariable
            androidx.constraintlayout.solver.SolverVariable r8 = r1.mSolverVariable
            r10 = 7
            r0 = r38
            r1 = r2
            r2 = r3
            r3 = r6
            r6 = r8
            r8 = r10
            r0.addCentering(r1, r2, r3, r4, r5, r6, r7, r8)
            goto L_0x04ff
        L_0x02d4:
            if (r26 == 0) goto L_0x03ca
            if (r12 == 0) goto L_0x03ca
            int r0 = r1.mWidgetsMatchCount
            if (r0 <= 0) goto L_0x02e5
            int r0 = r1.mWidgetsCount
            int r1 = r1.mWidgetsMatchCount
            if (r0 != r1) goto L_0x02e5
            r17 = 1
            goto L_0x02e7
        L_0x02e5:
            r17 = 0
        L_0x02e7:
            r14 = r12
            r15 = r14
        L_0x02e9:
            if (r14 == 0) goto L_0x04ff
            androidx.constraintlayout.solver.widgets.ConstraintWidget[] r0 = r14.mNextChainWidget
            r0 = r0[r39]
            r8 = r0
        L_0x02f0:
            if (r8 == 0) goto L_0x02ff
            int r0 = r8.getVisibility()
            r6 = 8
            if (r0 != r6) goto L_0x0301
            androidx.constraintlayout.solver.widgets.ConstraintWidget[] r0 = r8.mNextChainWidget
            r8 = r0[r39]
            goto L_0x02f0
        L_0x02ff:
            r6 = 8
        L_0x0301:
            if (r8 != 0) goto L_0x030a
            if (r14 != r13) goto L_0x0306
            goto L_0x030a
        L_0x0306:
            r18 = r8
            goto L_0x03bd
        L_0x030a:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r0 = r14.mListAnchors
            r0 = r0[r40]
            androidx.constraintlayout.solver.SolverVariable r1 = r0.mSolverVariable
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r2 = r0.mTarget
            if (r2 == 0) goto L_0x0319
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r2 = r0.mTarget
            androidx.constraintlayout.solver.SolverVariable r2 = r2.mSolverVariable
            goto L_0x031b
        L_0x0319:
            r2 = r21
        L_0x031b:
            if (r15 == r14) goto L_0x0326
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r2 = r15.mListAnchors
            int r3 = r40 + 1
            r2 = r2[r3]
            androidx.constraintlayout.solver.SolverVariable r2 = r2.mSolverVariable
            goto L_0x033d
        L_0x0326:
            if (r14 != r12) goto L_0x033d
            if (r15 != r14) goto L_0x033d
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r2 = r10.mListAnchors
            r2 = r2[r40]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r2 = r2.mTarget
            if (r2 == 0) goto L_0x033b
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r2 = r10.mListAnchors
            r2 = r2[r40]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r2 = r2.mTarget
            androidx.constraintlayout.solver.SolverVariable r2 = r2.mSolverVariable
            goto L_0x033d
        L_0x033b:
            r2 = r21
        L_0x033d:
            int r0 = r0.getMargin()
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r3 = r14.mListAnchors
            int r4 = r40 + 1
            r3 = r3[r4]
            int r3 = r3.getMargin()
            if (r8 == 0) goto L_0x035f
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r5 = r8.mListAnchors
            r5 = r5[r40]
            androidx.constraintlayout.solver.SolverVariable r7 = r5.mSolverVariable
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r6 = r14.mListAnchors
            r6 = r6[r4]
            androidx.constraintlayout.solver.SolverVariable r6 = r6.mSolverVariable
            r36 = r7
            r7 = r6
            r6 = r36
            goto L_0x0372
        L_0x035f:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r5 = r11.mListAnchors
            r5 = r5[r4]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r5 = r5.mTarget
            if (r5 == 0) goto L_0x036a
            androidx.constraintlayout.solver.SolverVariable r6 = r5.mSolverVariable
            goto L_0x036c
        L_0x036a:
            r6 = r21
        L_0x036c:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r7 = r14.mListAnchors
            r7 = r7[r4]
            androidx.constraintlayout.solver.SolverVariable r7 = r7.mSolverVariable
        L_0x0372:
            if (r5 == 0) goto L_0x0379
            int r5 = r5.getMargin()
            int r3 = r3 + r5
        L_0x0379:
            if (r15 == 0) goto L_0x0384
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r5 = r15.mListAnchors
            r5 = r5[r4]
            int r5 = r5.getMargin()
            int r0 = r0 + r5
        L_0x0384:
            if (r1 == 0) goto L_0x0306
            if (r2 == 0) goto L_0x0306
            if (r6 == 0) goto L_0x0306
            if (r7 == 0) goto L_0x0306
            if (r14 != r12) goto L_0x0396
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r0 = r12.mListAnchors
            r0 = r0[r40]
            int r0 = r0.getMargin()
        L_0x0396:
            r5 = r0
            if (r14 != r13) goto L_0x03a4
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r0 = r13.mListAnchors
            r0 = r0[r4]
            int r0 = r0.getMargin()
            r18 = r0
            goto L_0x03a6
        L_0x03a4:
            r18 = r3
        L_0x03a6:
            if (r17 == 0) goto L_0x03ab
            r19 = 8
            goto L_0x03ad
        L_0x03ab:
            r19 = 5
        L_0x03ad:
            r4 = 1056964608(0x3f000000, float:0.5)
            r0 = r38
            r3 = r5
            r5 = r6
            r6 = r7
            r7 = r18
            r18 = r8
            r8 = r19
            r0.addCentering(r1, r2, r3, r4, r5, r6, r7, r8)
        L_0x03bd:
            int r0 = r14.getVisibility()
            r8 = 8
            if (r0 == r8) goto L_0x03c6
            r15 = r14
        L_0x03c6:
            r14 = r18
            goto L_0x02e9
        L_0x03ca:
            r8 = 8
            if (r16 == 0) goto L_0x04ff
            if (r12 == 0) goto L_0x04ff
            int r0 = r1.mWidgetsMatchCount
            if (r0 <= 0) goto L_0x03dd
            int r0 = r1.mWidgetsCount
            int r1 = r1.mWidgetsMatchCount
            if (r0 != r1) goto L_0x03dd
            r17 = 1
            goto L_0x03df
        L_0x03dd:
            r17 = 0
        L_0x03df:
            r14 = r12
            r15 = r14
        L_0x03e1:
            if (r14 == 0) goto L_0x04a1
            androidx.constraintlayout.solver.widgets.ConstraintWidget[] r0 = r14.mNextChainWidget
            r0 = r0[r39]
        L_0x03e7:
            if (r0 == 0) goto L_0x03f4
            int r1 = r0.getVisibility()
            if (r1 != r8) goto L_0x03f4
            androidx.constraintlayout.solver.widgets.ConstraintWidget[] r0 = r0.mNextChainWidget
            r0 = r0[r39]
            goto L_0x03e7
        L_0x03f4:
            if (r14 == r12) goto L_0x048c
            if (r14 == r13) goto L_0x048c
            if (r0 == 0) goto L_0x048c
            if (r0 != r13) goto L_0x03ff
            r7 = r21
            goto L_0x0400
        L_0x03ff:
            r7 = r0
        L_0x0400:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r0 = r14.mListAnchors
            r0 = r0[r40]
            androidx.constraintlayout.solver.SolverVariable r1 = r0.mSolverVariable
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r2 = r0.mTarget
            if (r2 == 0) goto L_0x040e
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r2 = r0.mTarget
            androidx.constraintlayout.solver.SolverVariable r2 = r2.mSolverVariable
        L_0x040e:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r2 = r15.mListAnchors
            int r3 = r40 + 1
            r2 = r2[r3]
            androidx.constraintlayout.solver.SolverVariable r2 = r2.mSolverVariable
            int r0 = r0.getMargin()
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r4 = r14.mListAnchors
            r4 = r4[r3]
            int r4 = r4.getMargin()
            if (r7 == 0) goto L_0x0436
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r5 = r7.mListAnchors
            r5 = r5[r40]
            androidx.constraintlayout.solver.SolverVariable r6 = r5.mSolverVariable
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r8 = r5.mTarget
            if (r8 == 0) goto L_0x0433
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r8 = r5.mTarget
            androidx.constraintlayout.solver.SolverVariable r8 = r8.mSolverVariable
            goto L_0x0447
        L_0x0433:
            r8 = r21
            goto L_0x0447
        L_0x0436:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r5 = r13.mListAnchors
            r5 = r5[r40]
            if (r5 == 0) goto L_0x043f
            androidx.constraintlayout.solver.SolverVariable r6 = r5.mSolverVariable
            goto L_0x0441
        L_0x043f:
            r6 = r21
        L_0x0441:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r8 = r14.mListAnchors
            r8 = r8[r3]
            androidx.constraintlayout.solver.SolverVariable r8 = r8.mSolverVariable
        L_0x0447:
            if (r5 == 0) goto L_0x044e
            int r5 = r5.getMargin()
            int r4 = r4 + r5
        L_0x044e:
            r18 = r4
            if (r15 == 0) goto L_0x045b
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r4 = r15.mListAnchors
            r3 = r4[r3]
            int r3 = r3.getMargin()
            int r0 = r0 + r3
        L_0x045b:
            r3 = r0
            if (r17 == 0) goto L_0x0461
            r20 = 8
            goto L_0x0463
        L_0x0461:
            r20 = 4
        L_0x0463:
            if (r1 == 0) goto L_0x0481
            if (r2 == 0) goto L_0x0481
            if (r6 == 0) goto L_0x0481
            if (r8 == 0) goto L_0x0481
            r4 = 1056964608(0x3f000000, float:0.5)
            r0 = r38
            r5 = r6
            r19 = 4
            r6 = r8
            r22 = r7
            r7 = r18
            r18 = r15
            r15 = 8
            r8 = r20
            r0.addCentering(r1, r2, r3, r4, r5, r6, r7, r8)
            goto L_0x0489
        L_0x0481:
            r22 = r7
            r18 = r15
            r15 = 8
            r19 = 4
        L_0x0489:
            r0 = r22
            goto L_0x0492
        L_0x048c:
            r18 = r15
            r15 = 8
            r19 = 4
        L_0x0492:
            int r1 = r14.getVisibility()
            if (r1 == r15) goto L_0x0499
            goto L_0x049b
        L_0x0499:
            r14 = r18
        L_0x049b:
            r15 = r14
            r8 = 8
            r14 = r0
            goto L_0x03e1
        L_0x04a1:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r0 = r12.mListAnchors
            r0 = r0[r40]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r1 = r10.mListAnchors
            r1 = r1[r40]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r1 = r1.mTarget
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r2 = r13.mListAnchors
            int r3 = r40 + 1
            r10 = r2[r3]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r2 = r11.mListAnchors
            r2 = r2[r3]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r14 = r2.mTarget
            if (r1 == 0) goto L_0x04ee
            if (r12 == r13) goto L_0x04c8
            androidx.constraintlayout.solver.SolverVariable r2 = r0.mSolverVariable
            androidx.constraintlayout.solver.SolverVariable r1 = r1.mSolverVariable
            int r0 = r0.getMargin()
            r15 = 5
            r9.addEquality(r2, r1, r0, r15)
            goto L_0x04ef
        L_0x04c8:
            r15 = 5
            if (r14 == 0) goto L_0x04ef
            androidx.constraintlayout.solver.SolverVariable r2 = r0.mSolverVariable
            androidx.constraintlayout.solver.SolverVariable r3 = r1.mSolverVariable
            int r4 = r0.getMargin()
            r5 = 1056964608(0x3f000000, float:0.5)
            androidx.constraintlayout.solver.SolverVariable r6 = r10.mSolverVariable
            androidx.constraintlayout.solver.SolverVariable r7 = r14.mSolverVariable
            int r8 = r10.getMargin()
            r17 = 5
            r0 = r38
            r1 = r2
            r2 = r3
            r3 = r4
            r4 = r5
            r5 = r6
            r6 = r7
            r7 = r8
            r8 = r17
            r0.addCentering(r1, r2, r3, r4, r5, r6, r7, r8)
            goto L_0x04ef
        L_0x04ee:
            r15 = 5
        L_0x04ef:
            if (r14 == 0) goto L_0x04ff
            if (r12 == r13) goto L_0x04ff
            androidx.constraintlayout.solver.SolverVariable r0 = r10.mSolverVariable
            androidx.constraintlayout.solver.SolverVariable r1 = r14.mSolverVariable
            int r2 = r10.getMargin()
            int r2 = -r2
            r9.addEquality(r0, r1, r2, r15)
        L_0x04ff:
            if (r26 != 0) goto L_0x0503
            if (r16 == 0) goto L_0x0568
        L_0x0503:
            if (r12 == 0) goto L_0x0568
            if (r12 == r13) goto L_0x0568
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r0 = r12.mListAnchors
            r0 = r0[r40]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r1 = r13.mListAnchors
            int r2 = r40 + 1
            r1 = r1[r2]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r0.mTarget
            if (r3 == 0) goto L_0x051a
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r0.mTarget
            androidx.constraintlayout.solver.SolverVariable r3 = r3.mSolverVariable
            goto L_0x051c
        L_0x051a:
            r3 = r21
        L_0x051c:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r4 = r1.mTarget
            if (r4 == 0) goto L_0x0525
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r4 = r1.mTarget
            androidx.constraintlayout.solver.SolverVariable r4 = r4.mSolverVariable
            goto L_0x0527
        L_0x0525:
            r4 = r21
        L_0x0527:
            if (r11 == r13) goto L_0x0538
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r4 = r11.mListAnchors
            r4 = r4[r2]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r5 = r4.mTarget
            if (r5 == 0) goto L_0x0536
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r4 = r4.mTarget
            androidx.constraintlayout.solver.SolverVariable r4 = r4.mSolverVariable
            goto L_0x0538
        L_0x0536:
            r4 = r21
        L_0x0538:
            r5 = r4
            if (r12 != r13) goto L_0x0543
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r0 = r12.mListAnchors
            r0 = r0[r40]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r1 = r12.mListAnchors
            r1 = r1[r2]
        L_0x0543:
            if (r3 == 0) goto L_0x0568
            if (r5 == 0) goto L_0x0568
            r4 = 1056964608(0x3f000000, float:0.5)
            int r6 = r0.getMargin()
            if (r13 != 0) goto L_0x0550
            goto L_0x0551
        L_0x0550:
            r11 = r13
        L_0x0551:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r7 = r11.mListAnchors
            r2 = r7[r2]
            int r7 = r2.getMargin()
            androidx.constraintlayout.solver.SolverVariable r2 = r0.mSolverVariable
            androidx.constraintlayout.solver.SolverVariable r8 = r1.mSolverVariable
            r10 = 5
            r0 = r38
            r1 = r2
            r2 = r3
            r3 = r6
            r6 = r8
            r8 = r10
            r0.addCentering(r1, r2, r3, r4, r5, r6, r7, r8)
        L_0x0568:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.widgets.Chain.applyChainConstraints(androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer, androidx.constraintlayout.solver.LinearSystem, int, int, androidx.constraintlayout.solver.widgets.ChainHead):void");
    }
}
