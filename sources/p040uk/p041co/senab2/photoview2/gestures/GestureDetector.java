package p040uk.p041co.senab2.photoview2.gestures;

import android.view.MotionEvent;

/* renamed from: uk.co.senab2.photoview2.gestures.GestureDetector */
public interface GestureDetector {
    boolean isDragging();

    boolean isScaling();

    boolean onTouchEvent(MotionEvent motionEvent);

    void setOnGestureListener(OnGestureListener onGestureListener);
}
