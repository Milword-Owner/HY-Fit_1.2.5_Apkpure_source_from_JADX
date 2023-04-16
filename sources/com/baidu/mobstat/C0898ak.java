package com.baidu.mobstat;

import android.annotation.SuppressLint;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.KeyboardShortcutGroup;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SearchEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import java.util.List;

/* renamed from: com.baidu.mobstat.ak */
public class C0898ak implements Window.Callback {

    /* renamed from: a */
    private Window.Callback f965a;

    /* renamed from: b */
    private C0899a f966b;

    /* renamed from: com.baidu.mobstat.ak$a */
    public interface C0899a {
        /* renamed from: a */
        void mo11463a(KeyEvent keyEvent);

        /* renamed from: a */
        void mo11464a(MotionEvent motionEvent);
    }

    /* renamed from: a */
    public Window.Callback mo11466a() {
        return this.f965a;
    }

    public C0898ak(Window.Callback callback, C0899a aVar) {
        this.f965a = callback;
        this.f966b = aVar;
    }

    public boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
        return this.f965a.dispatchGenericMotionEvent(motionEvent);
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        C0899a aVar = this.f966b;
        if (aVar != null) {
            aVar.mo11463a(keyEvent);
        }
        return this.f965a.dispatchKeyEvent(keyEvent);
    }

    public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
        return this.f965a.dispatchKeyShortcutEvent(keyEvent);
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return this.f965a.dispatchPopulateAccessibilityEvent(accessibilityEvent);
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        C0899a aVar = this.f966b;
        if (aVar != null) {
            aVar.mo11464a(motionEvent);
        }
        return this.f965a.dispatchTouchEvent(motionEvent);
    }

    public boolean dispatchTrackballEvent(MotionEvent motionEvent) {
        return this.f965a.dispatchTrackballEvent(motionEvent);
    }

    public void onActionModeFinished(ActionMode actionMode) {
        this.f965a.onActionModeFinished(actionMode);
    }

    public void onActionModeStarted(ActionMode actionMode) {
        this.f965a.onActionModeStarted(actionMode);
    }

    public void onAttachedToWindow() {
        this.f965a.onAttachedToWindow();
    }

    public void onContentChanged() {
        this.f965a.onContentChanged();
    }

    public boolean onCreatePanelMenu(int i, Menu menu) {
        return this.f965a.onCreatePanelMenu(i, menu);
    }

    public View onCreatePanelView(int i) {
        return this.f965a.onCreatePanelView(i);
    }

    public void onDetachedFromWindow() {
        this.f965a.onDetachedFromWindow();
    }

    public boolean onMenuItemSelected(int i, MenuItem menuItem) {
        return this.f965a.onMenuItemSelected(i, menuItem);
    }

    public boolean onMenuOpened(int i, Menu menu) {
        return this.f965a.onMenuOpened(i, menu);
    }

    public void onPanelClosed(int i, Menu menu) {
        this.f965a.onPanelClosed(i, menu);
    }

    public boolean onPreparePanel(int i, View view, Menu menu) {
        return this.f965a.onPreparePanel(i, view, menu);
    }

    public boolean onSearchRequested() {
        return this.f965a.onSearchRequested();
    }

    @SuppressLint({"NewApi"})
    public boolean onSearchRequested(SearchEvent searchEvent) {
        return this.f965a.onSearchRequested(searchEvent);
    }

    public void onWindowAttributesChanged(WindowManager.LayoutParams layoutParams) {
        this.f965a.onWindowAttributesChanged(layoutParams);
    }

    public void onWindowFocusChanged(boolean z) {
        this.f965a.onWindowFocusChanged(z);
    }

    @SuppressLint({"NewApi"})
    public void onPointerCaptureChanged(boolean z) {
        this.f965a.onPointerCaptureChanged(z);
    }

    @SuppressLint({"NewApi"})
    public void onProvideKeyboardShortcuts(List<KeyboardShortcutGroup> list, Menu menu, int i) {
        this.f965a.onProvideKeyboardShortcuts(list, menu, i);
    }

    public ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
        return this.f965a.onWindowStartingActionMode(callback);
    }

    @SuppressLint({"NewApi"})
    public ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int i) {
        return this.f965a.onWindowStartingActionMode(callback, i);
    }
}
