package p040uk.p041co.senab2.photoview2.gestures;

import android.annotation.TargetApi;
import android.content.Context;
import android.view.MotionEvent;
import p040uk.p041co.senab2.photoview2.Compat;

@TargetApi(5)
/* renamed from: uk.co.senab2.photoview2.gestures.EclairGestureDetector */
public class EclairGestureDetector extends CupcakeGestureDetector {
    private static final int INVALID_POINTER_ID = -1;
    private int mActivePointerId = -1;
    private int mActivePointerIndex = 0;

    public EclairGestureDetector(Context context) {
        super(context);
    }

    /* access modifiers changed from: package-private */
    public float getActiveX(MotionEvent motionEvent) {
        try {
            return motionEvent.getX(this.mActivePointerIndex);
        } catch (Exception unused) {
            return motionEvent.getX();
        }
    }

    /* access modifiers changed from: package-private */
    public float getActiveY(MotionEvent motionEvent) {
        try {
            return motionEvent.getY(this.mActivePointerIndex);
        } catch (Exception unused) {
            return motionEvent.getY();
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & 255;
        if (action == 0) {
            this.mActivePointerId = motionEvent.getPointerId(0);
        } else if (action == 1 || action == 3) {
            this.mActivePointerId = -1;
        } else if (action == 6) {
            int pointerIndex = Compat.getPointerIndex(motionEvent.getAction());
            if (motionEvent.getPointerId(pointerIndex) == this.mActivePointerId) {
                int i = pointerIndex == 0 ? 1 : 0;
                this.mActivePointerId = motionEvent.getPointerId(i);
                this.mLastTouchX = motionEvent.getX(i);
                this.mLastTouchY = motionEvent.getY(i);
            }
        }
        int i2 = this.mActivePointerId;
        if (i2 == -1) {
            i2 = 0;
        }
        this.mActivePointerIndex = motionEvent.findPointerIndex(i2);
        try {
            return super.onTouchEvent(motionEvent);
        } catch (IllegalArgumentException unused) {
            return true;
        }
    }
}
