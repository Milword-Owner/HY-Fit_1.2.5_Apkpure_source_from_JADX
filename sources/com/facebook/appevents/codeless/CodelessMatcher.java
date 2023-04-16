package com.facebook.appevents.codeless;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.annotation.Nullable;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.codeless.CodelessLoggingEventListener;
import com.facebook.appevents.codeless.RCTCodelessLoggingEventListener;
import com.facebook.appevents.codeless.internal.Constants;
import com.facebook.appevents.codeless.internal.EventBinding;
import com.facebook.appevents.codeless.internal.ParameterComponent;
import com.facebook.appevents.codeless.internal.PathComponent;
import com.facebook.appevents.codeless.internal.ViewHierarchy;
import com.facebook.internal.FetchedAppSettings;
import com.facebook.internal.FetchedAppSettingsManager;
import com.facebook.internal.InternalSettings;
import com.facebook.internal.Utility;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.WeakHashMap;

class CodelessMatcher {
    private static final String CURRENT_CLASS_NAME = ".";
    private static final String PARENT_CLASS_NAME = "..";
    /* access modifiers changed from: private */
    public static final String TAG = "com.facebook.appevents.codeless.CodelessMatcher";
    private static CodelessMatcher codelessMatcher;
    private Set<Activity> activitiesSet = Collections.newSetFromMap(new WeakHashMap());
    private HashMap<Integer, HashSet<String>> activityToListenerMap = new HashMap<>();
    private HashSet<String> listenerSet = new HashSet<>();
    private final Handler uiThreadHandler = new Handler(Looper.getMainLooper());
    private Set<ViewMatcher> viewMatchers = new HashSet();

    private CodelessMatcher() {
    }

    public static synchronized CodelessMatcher getInstance() {
        CodelessMatcher codelessMatcher2;
        synchronized (CodelessMatcher.class) {
            if (codelessMatcher == null) {
                codelessMatcher = new CodelessMatcher();
            }
            codelessMatcher2 = codelessMatcher;
        }
        return codelessMatcher2;
    }

    public void add(Activity activity) {
        if (!InternalSettings.isUnityApp()) {
            if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
                this.activitiesSet.add(activity);
                this.listenerSet.clear();
                if (this.activityToListenerMap.containsKey(Integer.valueOf(activity.hashCode()))) {
                    this.listenerSet = this.activityToListenerMap.get(Integer.valueOf(activity.hashCode()));
                }
                startTracking();
                return;
            }
            throw new FacebookException("Can't add activity to CodelessMatcher on non-UI thread");
        }
    }

    public void remove(Activity activity) {
        if (!InternalSettings.isUnityApp()) {
            if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
                this.activitiesSet.remove(activity);
                this.viewMatchers.clear();
                this.activityToListenerMap.put(Integer.valueOf(activity.hashCode()), (HashSet) this.listenerSet.clone());
                this.listenerSet.clear();
                return;
            }
            throw new FacebookException("Can't remove activity from CodelessMatcher on non-UI thread");
        }
    }

    public void destroy(Activity activity) {
        this.activityToListenerMap.remove(Integer.valueOf(activity.hashCode()));
    }

    public static Bundle getParameters(EventBinding eventBinding, View view, View view2) {
        List<ParameterComponent> viewParameters;
        List<MatchedView> list;
        Bundle bundle = new Bundle();
        if (!(eventBinding == null || (viewParameters = eventBinding.getViewParameters()) == null)) {
            for (ParameterComponent next : viewParameters) {
                if (next.value == null || next.value.length() <= 0) {
                    if (next.path.size() > 0) {
                        if (next.pathType.equals(Constants.PATH_TYPE_RELATIVE)) {
                            list = ViewMatcher.findViewByPath(eventBinding, view2, next.path, 0, -1, view2.getClass().getSimpleName());
                        } else {
                            list = ViewMatcher.findViewByPath(eventBinding, view, next.path, 0, -1, view.getClass().getSimpleName());
                        }
                        Iterator<MatchedView> it = list.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            MatchedView next2 = it.next();
                            if (next2.getView() != null) {
                                String textOfView = ViewHierarchy.getTextOfView(next2.getView());
                                if (textOfView.length() > 0) {
                                    bundle.putString(next.name, textOfView);
                                    break;
                                }
                            }
                        }
                    }
                } else {
                    bundle.putString(next.name, next.value);
                }
            }
        }
        return bundle;
    }

    private void startTracking() {
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            matchViews();
        } else {
            this.uiThreadHandler.post(new Runnable() {
                public void run() {
                    CodelessMatcher.this.matchViews();
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void matchViews() {
        for (Activity next : this.activitiesSet) {
            if (next != null) {
                this.viewMatchers.add(new ViewMatcher(next.getWindow().getDecorView().getRootView(), this.uiThreadHandler, this.listenerSet, next.getClass().getSimpleName()));
            }
        }
    }

    public static class MatchedView {
        private WeakReference<View> view;
        private String viewMapKey;

        public MatchedView(View view2, String str) {
            this.view = new WeakReference<>(view2);
            this.viewMapKey = str;
        }

        @Nullable
        public View getView() {
            WeakReference<View> weakReference = this.view;
            if (weakReference == null) {
                return null;
            }
            return (View) weakReference.get();
        }

        public String getViewMapKey() {
            return this.viewMapKey;
        }
    }

    protected static class ViewMatcher implements ViewTreeObserver.OnGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener, Runnable {
        private final String activityName;
        @Nullable
        private List<EventBinding> eventBindings;
        private final Handler handler;
        private HashSet<String> listenerSet;
        private WeakReference<View> rootView;

        public ViewMatcher(View view, Handler handler2, HashSet<String> hashSet, String str) {
            this.rootView = new WeakReference<>(view);
            this.handler = handler2;
            this.listenerSet = hashSet;
            this.activityName = str;
            this.handler.postDelayed(this, 200);
        }

        public void run() {
            View view;
            FetchedAppSettings appSettingsWithoutQuery = FetchedAppSettingsManager.getAppSettingsWithoutQuery(FacebookSdk.getApplicationId());
            if (appSettingsWithoutQuery != null && appSettingsWithoutQuery.getCodelessEventsEnabled()) {
                this.eventBindings = EventBinding.parseArray(appSettingsWithoutQuery.getEventBindings());
                if (this.eventBindings != null && (view = (View) this.rootView.get()) != null) {
                    ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
                    if (viewTreeObserver.isAlive()) {
                        viewTreeObserver.addOnGlobalLayoutListener(this);
                        viewTreeObserver.addOnScrollChangedListener(this);
                    }
                    startMatch();
                }
            }
        }

        public void onGlobalLayout() {
            startMatch();
        }

        public void onScrollChanged() {
            startMatch();
        }

        private void startMatch() {
            if (this.eventBindings != null && this.rootView.get() != null) {
                for (int i = 0; i < this.eventBindings.size(); i++) {
                    findView(this.eventBindings.get(i), (View) this.rootView.get());
                }
            }
        }

        public void findView(EventBinding eventBinding, View view) {
            if (eventBinding != null && view != null) {
                if (TextUtils.isEmpty(eventBinding.getActivityName()) || eventBinding.getActivityName().equals(this.activityName)) {
                    List<PathComponent> viewPath = eventBinding.getViewPath();
                    if (viewPath.size() <= 25) {
                        for (MatchedView attachListener : findViewByPath(eventBinding, view, viewPath, 0, -1, this.activityName)) {
                            attachListener(attachListener, view, eventBinding);
                        }
                    }
                }
            }
        }

        public static List<MatchedView> findViewByPath(EventBinding eventBinding, View view, List<PathComponent> list, int i, int i2, String str) {
            String str2 = str + CodelessMatcher.CURRENT_CLASS_NAME + String.valueOf(i2);
            ArrayList arrayList = new ArrayList();
            if (view == null) {
                return arrayList;
            }
            if (i >= list.size()) {
                arrayList.add(new MatchedView(view, str2));
            } else {
                PathComponent pathComponent = list.get(i);
                if (pathComponent.className.equals(CodelessMatcher.PARENT_CLASS_NAME)) {
                    ViewParent parent = view.getParent();
                    if (parent instanceof ViewGroup) {
                        List<View> findVisibleChildren = findVisibleChildren((ViewGroup) parent);
                        int size = findVisibleChildren.size();
                        for (int i3 = 0; i3 < size; i3++) {
                            arrayList.addAll(findViewByPath(eventBinding, findVisibleChildren.get(i3), list, i + 1, i3, str2));
                        }
                    }
                    return arrayList;
                } else if (pathComponent.className.equals(CodelessMatcher.CURRENT_CLASS_NAME)) {
                    arrayList.add(new MatchedView(view, str2));
                    return arrayList;
                } else if (!isTheSameView(view, pathComponent, i2)) {
                    return arrayList;
                } else {
                    if (i == list.size() - 1) {
                        arrayList.add(new MatchedView(view, str2));
                    }
                }
            }
            if (view instanceof ViewGroup) {
                List<View> findVisibleChildren2 = findVisibleChildren((ViewGroup) view);
                int size2 = findVisibleChildren2.size();
                for (int i4 = 0; i4 < size2; i4++) {
                    arrayList.addAll(findViewByPath(eventBinding, findVisibleChildren2.get(i4), list, i + 1, i4, str2));
                }
            }
            return arrayList;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0041, code lost:
            if (r5.getClass().getSimpleName().equals(r7[r7.length - 1]) == false) goto L_0x0043;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private static boolean isTheSameView(android.view.View r5, com.facebook.appevents.codeless.internal.PathComponent r6, int r7) {
            /*
                int r0 = r6.index
                r1 = 0
                r2 = -1
                if (r0 == r2) goto L_0x000b
                int r0 = r6.index
                if (r7 == r0) goto L_0x000b
                return r1
            L_0x000b:
                java.lang.Class r7 = r5.getClass()
                java.lang.String r7 = r7.getCanonicalName()
                java.lang.String r0 = r6.className
                boolean r7 = r7.equals(r0)
                r0 = 1
                if (r7 != 0) goto L_0x0044
                java.lang.String r7 = r6.className
                java.lang.String r2 = ".*android\\..*"
                boolean r7 = r7.matches(r2)
                if (r7 == 0) goto L_0x0043
                java.lang.String r7 = r6.className
                java.lang.String r2 = "\\."
                java.lang.String[] r7 = r7.split(r2)
                int r2 = r7.length
                if (r2 <= 0) goto L_0x0043
                int r2 = r7.length
                int r2 = r2 - r0
                r7 = r7[r2]
                java.lang.Class r2 = r5.getClass()
                java.lang.String r2 = r2.getSimpleName()
                boolean r7 = r2.equals(r7)
                if (r7 != 0) goto L_0x0044
            L_0x0043:
                return r1
            L_0x0044:
                int r7 = r6.matchBitmask
                com.facebook.appevents.codeless.internal.PathComponent$MatchBitmaskType r2 = com.facebook.appevents.codeless.internal.PathComponent.MatchBitmaskType.ID
                int r2 = r2.getValue()
                r7 = r7 & r2
                if (r7 <= 0) goto L_0x0058
                int r7 = r6.f1529id
                int r2 = r5.getId()
                if (r7 == r2) goto L_0x0058
                return r1
            L_0x0058:
                int r7 = r6.matchBitmask
                com.facebook.appevents.codeless.internal.PathComponent$MatchBitmaskType r2 = com.facebook.appevents.codeless.internal.PathComponent.MatchBitmaskType.TEXT
                int r2 = r2.getValue()
                r7 = r7 & r2
                java.lang.String r2 = ""
                if (r7 <= 0) goto L_0x0080
                java.lang.String r7 = r6.text
                java.lang.String r3 = com.facebook.appevents.codeless.internal.ViewHierarchy.getTextOfView(r5)
                java.lang.String r4 = com.facebook.internal.Utility.sha256hash((java.lang.String) r3)
                java.lang.String r4 = com.facebook.internal.Utility.coerceValueIfNullOrEmpty(r4, r2)
                boolean r3 = r7.equals(r3)
                if (r3 != 0) goto L_0x0080
                boolean r7 = r7.equals(r4)
                if (r7 != 0) goto L_0x0080
                return r1
            L_0x0080:
                int r7 = r6.matchBitmask
                com.facebook.appevents.codeless.internal.PathComponent$MatchBitmaskType r3 = com.facebook.appevents.codeless.internal.PathComponent.MatchBitmaskType.DESCRIPTION
                int r3 = r3.getValue()
                r7 = r7 & r3
                if (r7 <= 0) goto L_0x00b2
                java.lang.String r7 = r6.description
                java.lang.CharSequence r3 = r5.getContentDescription()
                if (r3 != 0) goto L_0x0095
                r3 = r2
                goto L_0x009d
            L_0x0095:
                java.lang.CharSequence r3 = r5.getContentDescription()
                java.lang.String r3 = java.lang.String.valueOf(r3)
            L_0x009d:
                java.lang.String r4 = com.facebook.internal.Utility.sha256hash((java.lang.String) r3)
                java.lang.String r4 = com.facebook.internal.Utility.coerceValueIfNullOrEmpty(r4, r2)
                boolean r3 = r7.equals(r3)
                if (r3 != 0) goto L_0x00b2
                boolean r7 = r7.equals(r4)
                if (r7 != 0) goto L_0x00b2
                return r1
            L_0x00b2:
                int r7 = r6.matchBitmask
                com.facebook.appevents.codeless.internal.PathComponent$MatchBitmaskType r3 = com.facebook.appevents.codeless.internal.PathComponent.MatchBitmaskType.HINT
                int r3 = r3.getValue()
                r7 = r7 & r3
                if (r7 <= 0) goto L_0x00d8
                java.lang.String r7 = r6.hint
                java.lang.String r3 = com.facebook.appevents.codeless.internal.ViewHierarchy.getHintOfView(r5)
                java.lang.String r4 = com.facebook.internal.Utility.sha256hash((java.lang.String) r3)
                java.lang.String r4 = com.facebook.internal.Utility.coerceValueIfNullOrEmpty(r4, r2)
                boolean r3 = r7.equals(r3)
                if (r3 != 0) goto L_0x00d8
                boolean r7 = r7.equals(r4)
                if (r7 != 0) goto L_0x00d8
                return r1
            L_0x00d8:
                int r7 = r6.matchBitmask
                com.facebook.appevents.codeless.internal.PathComponent$MatchBitmaskType r3 = com.facebook.appevents.codeless.internal.PathComponent.MatchBitmaskType.TAG
                int r3 = r3.getValue()
                r7 = r7 & r3
                if (r7 <= 0) goto L_0x010a
                java.lang.String r6 = r6.tag
                java.lang.Object r7 = r5.getTag()
                if (r7 != 0) goto L_0x00ed
                r5 = r2
                goto L_0x00f5
            L_0x00ed:
                java.lang.Object r5 = r5.getTag()
                java.lang.String r5 = java.lang.String.valueOf(r5)
            L_0x00f5:
                java.lang.String r7 = com.facebook.internal.Utility.sha256hash((java.lang.String) r5)
                java.lang.String r7 = com.facebook.internal.Utility.coerceValueIfNullOrEmpty(r7, r2)
                boolean r5 = r6.equals(r5)
                if (r5 != 0) goto L_0x010a
                boolean r5 = r6.equals(r7)
                if (r5 != 0) goto L_0x010a
                return r1
            L_0x010a:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.appevents.codeless.CodelessMatcher.ViewMatcher.isTheSameView(android.view.View, com.facebook.appevents.codeless.internal.PathComponent, int):boolean");
        }

        private static List<View> findVisibleChildren(ViewGroup viewGroup) {
            ArrayList arrayList = new ArrayList();
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                if (childAt.getVisibility() == 0) {
                    arrayList.add(childAt);
                }
            }
            return arrayList;
        }

        private void attachListener(MatchedView matchedView, View view, EventBinding eventBinding) {
            if (eventBinding != null) {
                try {
                    View view2 = matchedView.getView();
                    if (view2 != null) {
                        View findRCTRootView = ViewHierarchy.findRCTRootView(view2);
                        if (findRCTRootView != null && ViewHierarchy.isRCTButton(view2, findRCTRootView)) {
                            attachRCTListener(matchedView, view, eventBinding);
                        } else if (!view2.getClass().getName().startsWith("com.facebook.react")) {
                            if (!(view2 instanceof AdapterView)) {
                                attachOnClickListener(matchedView, view, eventBinding);
                            } else if (view2 instanceof ListView) {
                                attachOnItemClickListener(matchedView, view, eventBinding);
                            }
                        }
                    }
                } catch (Exception e) {
                    Utility.logd(CodelessMatcher.TAG, e);
                }
            }
        }

        private void attachOnClickListener(MatchedView matchedView, View view, EventBinding eventBinding) {
            View view2 = matchedView.getView();
            if (view2 != null) {
                String viewMapKey = matchedView.getViewMapKey();
                View.OnClickListener existingOnClickListener = ViewHierarchy.getExistingOnClickListener(view2);
                boolean z = (existingOnClickListener instanceof CodelessLoggingEventListener.AutoLoggingOnClickListener) && ((CodelessLoggingEventListener.AutoLoggingOnClickListener) existingOnClickListener).getSupportCodelessLogging();
                if (!this.listenerSet.contains(viewMapKey) && !z) {
                    view2.setOnClickListener(CodelessLoggingEventListener.getOnClickListener(eventBinding, view, view2));
                    this.listenerSet.add(viewMapKey);
                }
            }
        }

        private void attachOnItemClickListener(MatchedView matchedView, View view, EventBinding eventBinding) {
            AdapterView adapterView = (AdapterView) matchedView.getView();
            if (adapterView != null) {
                String viewMapKey = matchedView.getViewMapKey();
                AdapterView.OnItemClickListener onItemClickListener = adapterView.getOnItemClickListener();
                boolean z = (onItemClickListener instanceof CodelessLoggingEventListener.AutoLoggingOnItemClickListener) && ((CodelessLoggingEventListener.AutoLoggingOnItemClickListener) onItemClickListener).getSupportCodelessLogging();
                if (!this.listenerSet.contains(viewMapKey) && !z) {
                    adapterView.setOnItemClickListener(CodelessLoggingEventListener.getOnItemClickListener(eventBinding, view, adapterView));
                    this.listenerSet.add(viewMapKey);
                }
            }
        }

        private void attachRCTListener(MatchedView matchedView, View view, EventBinding eventBinding) {
            View view2 = matchedView.getView();
            if (view2 != null) {
                String viewMapKey = matchedView.getViewMapKey();
                View.OnTouchListener existingOnTouchListener = ViewHierarchy.getExistingOnTouchListener(view2);
                boolean z = (existingOnTouchListener instanceof RCTCodelessLoggingEventListener.AutoLoggingOnTouchListener) && ((RCTCodelessLoggingEventListener.AutoLoggingOnTouchListener) existingOnTouchListener).getSupportCodelessLogging();
                if (!this.listenerSet.contains(viewMapKey) && !z) {
                    view2.setOnTouchListener(RCTCodelessLoggingEventListener.getOnTouchListener(eventBinding, view, view2));
                    this.listenerSet.add(viewMapKey);
                }
            }
        }
    }
}
