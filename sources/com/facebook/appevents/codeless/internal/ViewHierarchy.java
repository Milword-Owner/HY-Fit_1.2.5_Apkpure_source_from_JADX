package com.facebook.appevents.codeless.internal;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.core.view.NestedScrollingChild;
import com.facebook.appevents.internal.ViewHierarchyConstants;
import com.facebook.internal.Utility;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ViewHierarchy {
    private static final String CLASS_RCTROOTVIEW = "com.facebook.react.ReactRootView";
    private static final String CLASS_RCTTEXTVIEW = "com.facebook.react.views.view.ReactTextView";
    private static final String CLASS_RCTVIEWGROUP = "com.facebook.react.views.view.ReactViewGroup";
    private static final String CLASS_TOUCHTARGETHELPER = "com.facebook.react.uimanager.TouchTargetHelper";
    private static final int ICON_MAX_EDGE_LENGTH = 44;
    private static final String METHOD_FIND_TOUCHTARGET_VIEW = "findTouchTargetView";
    private static WeakReference<View> RCTRootViewReference = new WeakReference<>((Object) null);
    private static final String TAG = "com.facebook.appevents.codeless.internal.ViewHierarchy";
    @Nullable
    private static Method methodFindTouchTargetView = null;

    @Nullable
    @RestrictTo({RestrictTo.Scope.GROUP_ID})
    public static ViewGroup getParentOfView(View view) {
        if (view == null) {
            return null;
        }
        ViewParent parent = view.getParent();
        if (parent instanceof ViewGroup) {
            return (ViewGroup) parent;
        }
        return null;
    }

    @RestrictTo({RestrictTo.Scope.GROUP_ID})
    public static List<View> getChildrenOfView(View view) {
        ArrayList arrayList = new ArrayList();
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                arrayList.add(viewGroup.getChildAt(i));
            }
        }
        return arrayList;
    }

    @RestrictTo({RestrictTo.Scope.GROUP_ID})
    public static void updateBasicInfoOfView(View view, JSONObject jSONObject) {
        try {
            String textOfView = getTextOfView(view);
            String hintOfView = getHintOfView(view);
            Object tag = view.getTag();
            CharSequence contentDescription = view.getContentDescription();
            jSONObject.put(ViewHierarchyConstants.CLASS_NAME_KEY, view.getClass().getCanonicalName());
            jSONObject.put(ViewHierarchyConstants.CLASS_TYPE_BITMASK_KEY, getClassTypeBitmask(view));
            jSONObject.put("id", view.getId());
            if (!SensitiveUserDataUtils.isSensitiveUserData(view)) {
                jSONObject.put(ViewHierarchyConstants.TEXT_KEY, Utility.coerceValueIfNullOrEmpty(Utility.sha256hash(textOfView), ""));
            } else {
                jSONObject.put(ViewHierarchyConstants.TEXT_KEY, "");
                jSONObject.put(ViewHierarchyConstants.IS_USER_INPUT_KEY, true);
            }
            jSONObject.put(ViewHierarchyConstants.HINT_KEY, Utility.coerceValueIfNullOrEmpty(Utility.sha256hash(hintOfView), ""));
            if (tag != null) {
                jSONObject.put(ViewHierarchyConstants.TAG_KEY, Utility.coerceValueIfNullOrEmpty(Utility.sha256hash(tag.toString()), ""));
            }
            if (contentDescription != null) {
                jSONObject.put("description", Utility.coerceValueIfNullOrEmpty(Utility.sha256hash(contentDescription.toString()), ""));
            }
            jSONObject.put(ViewHierarchyConstants.DIMENSION_KEY, getDimensionOfView(view));
        } catch (JSONException e) {
            Utility.logd(TAG, (Exception) e);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0009, code lost:
        r1 = (android.widget.TextView) r6;
     */
    @androidx.annotation.RestrictTo({androidx.annotation.RestrictTo.Scope.GROUP_ID})
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void updateAppearanceOfView(android.view.View r6, org.json.JSONObject r7, float r8) {
        /*
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{ JSONException -> 0x007b }
            r0.<init>()     // Catch:{ JSONException -> 0x007b }
            boolean r1 = r6 instanceof android.widget.TextView     // Catch:{ JSONException -> 0x007b }
            if (r1 == 0) goto L_0x0033
            r1 = r6
            android.widget.TextView r1 = (android.widget.TextView) r1     // Catch:{ JSONException -> 0x007b }
            android.graphics.Typeface r2 = r1.getTypeface()     // Catch:{ JSONException -> 0x007b }
            if (r2 == 0) goto L_0x0033
            java.lang.String r3 = "font_size"
            float r1 = r1.getTextSize()     // Catch:{ JSONException -> 0x007b }
            double r4 = (double) r1     // Catch:{ JSONException -> 0x007b }
            r0.put(r3, r4)     // Catch:{ JSONException -> 0x007b }
            java.lang.String r1 = "is_bold"
            boolean r3 = r2.isBold()     // Catch:{ JSONException -> 0x007b }
            r0.put(r1, r3)     // Catch:{ JSONException -> 0x007b }
            java.lang.String r1 = "is_italic"
            boolean r2 = r2.isItalic()     // Catch:{ JSONException -> 0x007b }
            r0.put(r1, r2)     // Catch:{ JSONException -> 0x007b }
            java.lang.String r1 = "text_style"
            r7.put(r1, r0)     // Catch:{ JSONException -> 0x007b }
        L_0x0033:
            boolean r0 = r6 instanceof android.widget.ImageView     // Catch:{ JSONException -> 0x007b }
            if (r0 == 0) goto L_0x0081
            r0 = r6
            android.widget.ImageView r0 = (android.widget.ImageView) r0     // Catch:{ JSONException -> 0x007b }
            android.graphics.drawable.Drawable r0 = r0.getDrawable()     // Catch:{ JSONException -> 0x007b }
            boolean r1 = r0 instanceof android.graphics.drawable.BitmapDrawable     // Catch:{ JSONException -> 0x007b }
            if (r1 == 0) goto L_0x0081
            int r1 = r6.getHeight()     // Catch:{ JSONException -> 0x007b }
            float r1 = (float) r1     // Catch:{ JSONException -> 0x007b }
            float r1 = r1 / r8
            r2 = 1110441984(0x42300000, float:44.0)
            int r1 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
            if (r1 > 0) goto L_0x0081
            int r6 = r6.getWidth()     // Catch:{ JSONException -> 0x007b }
            float r6 = (float) r6     // Catch:{ JSONException -> 0x007b }
            float r6 = r6 / r8
            int r6 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r6 > 0) goto L_0x0081
            android.graphics.drawable.BitmapDrawable r0 = (android.graphics.drawable.BitmapDrawable) r0     // Catch:{ JSONException -> 0x007b }
            android.graphics.Bitmap r6 = r0.getBitmap()     // Catch:{ JSONException -> 0x007b }
            if (r6 == 0) goto L_0x0081
            java.io.ByteArrayOutputStream r8 = new java.io.ByteArrayOutputStream     // Catch:{ JSONException -> 0x007b }
            r8.<init>()     // Catch:{ JSONException -> 0x007b }
            android.graphics.Bitmap$CompressFormat r0 = android.graphics.Bitmap.CompressFormat.PNG     // Catch:{ JSONException -> 0x007b }
            r1 = 100
            r6.compress(r0, r1, r8)     // Catch:{ JSONException -> 0x007b }
            byte[] r6 = r8.toByteArray()     // Catch:{ JSONException -> 0x007b }
            r8 = 0
            java.lang.String r6 = android.util.Base64.encodeToString(r6, r8)     // Catch:{ JSONException -> 0x007b }
            java.lang.String r8 = "icon_image"
            r7.put(r8, r6)     // Catch:{ JSONException -> 0x007b }
            goto L_0x0081
        L_0x007b:
            r6 = move-exception
            java.lang.String r7 = TAG
            com.facebook.internal.Utility.logd((java.lang.String) r7, (java.lang.Exception) r6)
        L_0x0081:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.appevents.codeless.internal.ViewHierarchy.updateAppearanceOfView(android.view.View, org.json.JSONObject, float):void");
    }

    @RestrictTo({RestrictTo.Scope.GROUP_ID})
    public static JSONObject getDictionaryOfView(View view) {
        if (view.getClass().getName().equals(CLASS_RCTROOTVIEW)) {
            RCTRootViewReference = new WeakReference<>(view);
        }
        JSONObject jSONObject = new JSONObject();
        try {
            updateBasicInfoOfView(view, jSONObject);
            JSONArray jSONArray = new JSONArray();
            List<View> childrenOfView = getChildrenOfView(view);
            for (int i = 0; i < childrenOfView.size(); i++) {
                jSONArray.put(getDictionaryOfView(childrenOfView.get(i)));
            }
            jSONObject.put(ViewHierarchyConstants.CHILDREN_VIEW_KEY, jSONArray);
        } catch (JSONException e) {
            Log.e(TAG, "Failed to create JSONObject for view.", e);
        }
        return jSONObject;
    }

    @RestrictTo({RestrictTo.Scope.GROUP_ID})
    public static int getClassTypeBitmask(View view) {
        int i = view instanceof ImageView ? 2 : 0;
        if (view.isClickable()) {
            i |= 32;
        }
        if (isAdapterViewItem(view)) {
            i |= 512;
        }
        if (view instanceof TextView) {
            int i2 = i | 1024 | 1;
            if (view instanceof Button) {
                i2 |= 4;
                if (view instanceof Switch) {
                    i2 |= 8192;
                } else if (view instanceof CheckBox) {
                    i2 |= 32768;
                }
            }
            if (view instanceof EditText) {
                return i2 | 2048;
            }
            return i2;
        } else if ((view instanceof Spinner) || (view instanceof DatePicker)) {
            return i | 4096;
        } else {
            if (view instanceof RatingBar) {
                return i | 65536;
            }
            if (view instanceof RadioGroup) {
                return i | 16384;
            }
            return (!(view instanceof ViewGroup) || !isRCTButton(view, (View) RCTRootViewReference.get())) ? i : i | 64;
        }
    }

    private static boolean isAdapterViewItem(View view) {
        ViewParent parent = view.getParent();
        return (parent instanceof AdapterView) || (parent instanceof NestedScrollingChild);
    }

    /* JADX WARNING: Removed duplicated region for block: B:36:0x00cd A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00d0  */
    @androidx.annotation.RestrictTo({androidx.annotation.RestrictTo.Scope.GROUP_ID})
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getTextOfView(android.view.View r6) {
        /*
            boolean r0 = r6 instanceof android.widget.TextView
            r1 = 0
            if (r0 == 0) goto L_0x0020
            r0 = r6
            android.widget.TextView r0 = (android.widget.TextView) r0
            java.lang.CharSequence r1 = r0.getText()
            boolean r0 = r6 instanceof android.widget.Switch
            if (r0 == 0) goto L_0x00cb
            android.widget.Switch r6 = (android.widget.Switch) r6
            boolean r6 = r6.isChecked()
            if (r6 == 0) goto L_0x001b
            java.lang.String r6 = "1"
            goto L_0x001d
        L_0x001b:
            java.lang.String r6 = "0"
        L_0x001d:
            r1 = r6
            goto L_0x00cb
        L_0x0020:
            boolean r0 = r6 instanceof android.widget.Spinner
            if (r0 == 0) goto L_0x0038
            android.widget.Spinner r6 = (android.widget.Spinner) r6
            int r0 = r6.getCount()
            if (r0 <= 0) goto L_0x00cb
            java.lang.Object r6 = r6.getSelectedItem()
            if (r6 == 0) goto L_0x00cb
            java.lang.String r1 = r6.toString()
            goto L_0x00cb
        L_0x0038:
            boolean r0 = r6 instanceof android.widget.DatePicker
            r2 = 2
            r3 = 1
            r4 = 0
            if (r0 == 0) goto L_0x0069
            android.widget.DatePicker r6 = (android.widget.DatePicker) r6
            int r0 = r6.getYear()
            int r1 = r6.getMonth()
            int r6 = r6.getDayOfMonth()
            r5 = 3
            java.lang.Object[] r5 = new java.lang.Object[r5]
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r5[r4] = r0
            java.lang.Integer r0 = java.lang.Integer.valueOf(r1)
            r5[r3] = r0
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            r5[r2] = r6
            java.lang.String r6 = "%04d-%02d-%02d"
            java.lang.String r1 = java.lang.String.format(r6, r5)
            goto L_0x00cb
        L_0x0069:
            boolean r0 = r6 instanceof android.widget.TimePicker
            if (r0 == 0) goto L_0x0094
            android.widget.TimePicker r6 = (android.widget.TimePicker) r6
            java.lang.Integer r0 = r6.getCurrentHour()
            int r0 = r0.intValue()
            java.lang.Integer r6 = r6.getCurrentMinute()
            int r6 = r6.intValue()
            java.lang.Object[] r1 = new java.lang.Object[r2]
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r1[r4] = r0
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            r1[r3] = r6
            java.lang.String r6 = "%02d:%02d"
            java.lang.String r1 = java.lang.String.format(r6, r1)
            goto L_0x00cb
        L_0x0094:
            boolean r0 = r6 instanceof android.widget.RadioGroup
            if (r0 == 0) goto L_0x00bd
            android.widget.RadioGroup r6 = (android.widget.RadioGroup) r6
            int r0 = r6.getCheckedRadioButtonId()
            int r2 = r6.getChildCount()
        L_0x00a2:
            if (r4 >= r2) goto L_0x00cb
            android.view.View r3 = r6.getChildAt(r4)
            int r5 = r3.getId()
            if (r5 != r0) goto L_0x00ba
            boolean r5 = r3 instanceof android.widget.RadioButton
            if (r5 == 0) goto L_0x00ba
            android.widget.RadioButton r3 = (android.widget.RadioButton) r3
            java.lang.CharSequence r6 = r3.getText()
            goto L_0x001d
        L_0x00ba:
            int r4 = r4 + 1
            goto L_0x00a2
        L_0x00bd:
            boolean r0 = r6 instanceof android.widget.RatingBar
            if (r0 == 0) goto L_0x00cb
            android.widget.RatingBar r6 = (android.widget.RatingBar) r6
            float r6 = r6.getRating()
            java.lang.String r1 = java.lang.String.valueOf(r6)
        L_0x00cb:
            if (r1 != 0) goto L_0x00d0
            java.lang.String r6 = ""
            goto L_0x00d4
        L_0x00d0:
            java.lang.String r6 = r1.toString()
        L_0x00d4:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.appevents.codeless.internal.ViewHierarchy.getTextOfView(android.view.View):java.lang.String");
    }

    @RestrictTo({RestrictTo.Scope.GROUP_ID})
    public static String getHintOfView(View view) {
        CharSequence charSequence;
        if (view instanceof EditText) {
            charSequence = ((EditText) view).getHint();
        } else {
            charSequence = view instanceof TextView ? ((TextView) view).getHint() : null;
        }
        if (charSequence == null) {
            return "";
        }
        return charSequence.toString();
    }

    private static JSONObject getDimensionOfView(View view) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(ViewHierarchyConstants.DIMENSION_TOP_KEY, view.getTop());
            jSONObject.put(ViewHierarchyConstants.DIMENSION_LEFT_KEY, view.getLeft());
            jSONObject.put(ViewHierarchyConstants.DIMENSION_WIDTH_KEY, view.getWidth());
            jSONObject.put(ViewHierarchyConstants.DIMENSION_HEIGHT_KEY, view.getHeight());
            jSONObject.put(ViewHierarchyConstants.DIMENSION_SCROLL_X_KEY, view.getScrollX());
            jSONObject.put(ViewHierarchyConstants.DIMENSION_SCROLL_Y_KEY, view.getScrollY());
            jSONObject.put(ViewHierarchyConstants.DIMENSION_VISIBILITY_KEY, view.getVisibility());
        } catch (JSONException e) {
            Log.e(TAG, "Failed to create JSONObject for dimension.", e);
        }
        return jSONObject;
    }

    @Nullable
    @RestrictTo({RestrictTo.Scope.GROUP_ID})
    public static View.OnClickListener getExistingOnClickListener(View view) {
        try {
            Field declaredField = Class.forName("android.view.View").getDeclaredField("mListenerInfo");
            if (declaredField != null) {
                declaredField.setAccessible(true);
            }
            Object obj = declaredField.get(view);
            if (obj == null) {
                return null;
            }
            Field declaredField2 = Class.forName("android.view.View$ListenerInfo").getDeclaredField("mOnClickListener");
            if (declaredField2 == null) {
                return null;
            }
            declaredField2.setAccessible(true);
            return (View.OnClickListener) declaredField2.get(obj);
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException unused) {
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x001e A[ADDED_TO_REGION] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void setOnClickListener(android.view.View r4, android.view.View.OnClickListener r5) {
        /*
            r0 = 0
            java.lang.String r1 = "android.view.View"
            java.lang.Class r1 = java.lang.Class.forName(r1)     // Catch:{ ClassNotFoundException | NoSuchFieldException -> 0x001a }
            java.lang.String r2 = "mListenerInfo"
            java.lang.reflect.Field r1 = r1.getDeclaredField(r2)     // Catch:{ ClassNotFoundException | NoSuchFieldException -> 0x001a }
            java.lang.String r2 = "android.view.View$ListenerInfo"
            java.lang.Class r2 = java.lang.Class.forName(r2)     // Catch:{ ClassNotFoundException | NoSuchFieldException -> 0x001b }
            java.lang.String r3 = "mOnClickListener"
            java.lang.reflect.Field r2 = r2.getDeclaredField(r3)     // Catch:{ ClassNotFoundException | NoSuchFieldException -> 0x001b }
            goto L_0x001c
        L_0x001a:
            r1 = r0
        L_0x001b:
            r2 = r0
        L_0x001c:
            if (r1 == 0) goto L_0x0039
            if (r2 != 0) goto L_0x0021
            goto L_0x0039
        L_0x0021:
            r3 = 1
            r1.setAccessible(r3)     // Catch:{ Exception -> 0x003c }
            r2.setAccessible(r3)     // Catch:{ Exception -> 0x003c }
            r1.setAccessible(r3)     // Catch:{ IllegalAccessException -> 0x002f }
            java.lang.Object r0 = r1.get(r4)     // Catch:{ IllegalAccessException -> 0x002f }
        L_0x002f:
            if (r0 != 0) goto L_0x0035
            r4.setOnClickListener(r5)     // Catch:{ Exception -> 0x003c }
            return
        L_0x0035:
            r2.set(r0, r5)     // Catch:{ Exception -> 0x003c }
            goto L_0x003c
        L_0x0039:
            r4.setOnClickListener(r5)     // Catch:{ Exception -> 0x003c }
        L_0x003c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.appevents.codeless.internal.ViewHierarchy.setOnClickListener(android.view.View, android.view.View$OnClickListener):void");
    }

    @Nullable
    @RestrictTo({RestrictTo.Scope.GROUP_ID})
    public static View.OnTouchListener getExistingOnTouchListener(View view) {
        try {
            Field declaredField = Class.forName("android.view.View").getDeclaredField("mListenerInfo");
            if (declaredField != null) {
                declaredField.setAccessible(true);
            }
            Object obj = declaredField.get(view);
            if (obj == null) {
                return null;
            }
            Field declaredField2 = Class.forName("android.view.View$ListenerInfo").getDeclaredField("mOnTouchListener");
            if (declaredField2 == null) {
                return null;
            }
            declaredField2.setAccessible(true);
            return (View.OnTouchListener) declaredField2.get(obj);
        } catch (NoSuchFieldException e) {
            Utility.logd(TAG, (Exception) e);
            return null;
        } catch (ClassNotFoundException e2) {
            Utility.logd(TAG, (Exception) e2);
            return null;
        } catch (IllegalAccessException e3) {
            Utility.logd(TAG, (Exception) e3);
            return null;
        }
    }

    @Nullable
    @RestrictTo({RestrictTo.Scope.GROUP_ID})
    public static View getTouchReactView(float[] fArr, @Nullable View view) {
        initTouchTargetHelperMethods();
        Method method = methodFindTouchTargetView;
        if (!(method == null || view == null)) {
            try {
                View view2 = (View) method.invoke((Object) null, new Object[]{fArr, view});
                if (view2 != null && view2.getId() > 0) {
                    View view3 = (View) view2.getParent();
                    if (view3 != null) {
                        return view3;
                    }
                    return null;
                }
            } catch (IllegalAccessException e) {
                Utility.logd(TAG, (Exception) e);
            } catch (InvocationTargetException e2) {
                Utility.logd(TAG, (Exception) e2);
            }
        }
        return null;
    }

    @RestrictTo({RestrictTo.Scope.GROUP_ID})
    public static boolean isRCTButton(View view, @Nullable View view2) {
        View touchReactView;
        if (!view.getClass().getName().equals(CLASS_RCTVIEWGROUP) || (touchReactView = getTouchReactView(getViewLocationOnScreen(view), view2)) == null || touchReactView.getId() != view.getId()) {
            return false;
        }
        return true;
    }

    @RestrictTo({RestrictTo.Scope.GROUP_ID})
    public static boolean isRCTRootView(View view) {
        return view.getClass().getName().equals(CLASS_RCTROOTVIEW);
    }

    @RestrictTo({RestrictTo.Scope.GROUP_ID})
    public static boolean isRCTTextView(View view) {
        return view.getClass().getName().equals(CLASS_RCTTEXTVIEW);
    }

    @RestrictTo({RestrictTo.Scope.GROUP_ID})
    public static boolean isRCTViewGroup(View view) {
        return view.getClass().getName().equals(CLASS_RCTVIEWGROUP);
    }

    @Nullable
    @RestrictTo({RestrictTo.Scope.GROUP_ID})
    public static View findRCTRootView(View view) {
        while (view != null) {
            if (isRCTRootView(view)) {
                return view;
            }
            ViewParent parent = view.getParent();
            if (!(parent instanceof View)) {
                return null;
            }
            view = (View) parent;
        }
        return null;
    }

    private static float[] getViewLocationOnScreen(View view) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        return new float[]{(float) iArr[0], (float) iArr[1]};
    }

    private static void initTouchTargetHelperMethods() {
        if (methodFindTouchTargetView == null) {
            try {
                methodFindTouchTargetView = Class.forName(CLASS_TOUCHTARGETHELPER).getDeclaredMethod(METHOD_FIND_TOUCHTARGET_VIEW, new Class[]{float[].class, ViewGroup.class});
                methodFindTouchTargetView.setAccessible(true);
            } catch (ClassNotFoundException e) {
                Utility.logd(TAG, (Exception) e);
            } catch (NoSuchMethodException e2) {
                Utility.logd(TAG, (Exception) e2);
            }
        }
    }
}
