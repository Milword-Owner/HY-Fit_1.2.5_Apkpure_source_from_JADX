package com.baidu.mobstat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.google.android.gms.fitness.FitnessStatusCodes;

/* renamed from: com.baidu.mobstat.ah */
public class C0885ah {
    /* renamed from: a */
    public static boolean m847a(View view) {
        Object tag = view.getTag(FitnessStatusCodes.SUCCESS_NO_DATA_SOURCES);
        return tag != null && (tag instanceof String) && ((String) tag).equals("baidu_mtj_edit_txtview");
    }

    /* renamed from: a */
    public static void m844a(Activity activity, boolean z) {
        ViewGroup viewGroup;
        View a;
        try {
            viewGroup = (ViewGroup) C0968bi.m1241a(activity).findViewById(16908290);
        } catch (Exception unused) {
            viewGroup = null;
        }
        if (viewGroup != null && (a = m841a(viewGroup)) != null) {
            a.setVisibility(z ? 0 : 4);
        }
    }

    /* renamed from: a */
    public static void m842a(Activity activity) {
        ViewGroup viewGroup;
        View a;
        try {
            viewGroup = (ViewGroup) C0968bi.m1241a(activity).findViewById(16908290);
        } catch (Exception unused) {
            viewGroup = null;
        }
        if (viewGroup != null && (a = m841a(viewGroup)) != null) {
            viewGroup.removeView(a);
        }
    }

    @SuppressLint({"NewApi"})
    /* renamed from: b */
    public static void m848b(final Activity activity) {
        final ViewGroup viewGroup;
        try {
            viewGroup = (ViewGroup) C0968bi.m1241a(activity).findViewById(16908290);
        } catch (Exception unused) {
            viewGroup = null;
        }
        if (viewGroup != null && viewGroup != null && m841a(viewGroup) == null) {
            final C0882ae aeVar = new C0882ae(activity);
            aeVar.setBackgroundColor(-16745729);
            aeVar.setGravity(17);
            aeVar.setText("连接中");
            aeVar.setTag(FitnessStatusCodes.SUCCESS_NO_DATA_SOURCES, "baidu_mtj_edit_txtview");
            viewGroup.post(new Runnable() {
                public void run() {
                    int width = viewGroup.getWidth();
                    int height = viewGroup.getHeight();
                    int c = C0884ag.m840c(activity, 55.0f);
                    FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(c, c);
                    layoutParams.leftMargin = (width - c) / 6;
                    layoutParams.topMargin = ((height - c) * 5) / 6;
                    aeVar.setLayoutParams(layoutParams);
                }
            });
            viewGroup.addView(aeVar);
            m843a(activity, (TextView) aeVar);
        }
    }

    /* renamed from: a */
    private static void m843a(final Activity activity, TextView textView) {
        final View view = (View) textView.getParent();
        textView.setOnTouchListener(new View.OnTouchListener() {

            /* renamed from: a */
            int f948a = 0;

            /* renamed from: b */
            int f949b = 0;

            /* renamed from: c */
            int f950c = 0;

            /* renamed from: d */
            int f951d = 0;

            public boolean onTouch(View view, MotionEvent motionEvent) {
                int rawX = (int) motionEvent.getRawX();
                int rawY = (int) motionEvent.getRawY();
                int action = motionEvent.getAction();
                if (action == 0) {
                    this.f948a = rawX;
                    this.f949b = rawY;
                    this.f950c = rawX - view.getLeft();
                    this.f951d = rawY - view.getTop();
                } else if (action != 1) {
                    if (action == 2) {
                        int i = rawX - this.f950c;
                        int i2 = rawY - this.f951d;
                        Rect rect = new Rect();
                        view.getLocalVisibleRect(rect);
                        if (rect.contains(new Rect(i, i2, view.getWidth() + i, view.getHeight() + i2))) {
                            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
                            marginLayoutParams.leftMargin = i;
                            marginLayoutParams.topMargin = i2;
                            view.setLayoutParams(marginLayoutParams);
                        }
                    }
                } else if (C0885ah.m850b((float) this.f948a, (float) ((int) motionEvent.getRawX()), (float) this.f949b, (float) ((int) motionEvent.getRawY()))) {
                    C0885ah.m849b((Context) activity);
                }
                return true;
            }
        });
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public static boolean m850b(float f, float f2, float f3, float f4) {
        return Math.abs(f - f2) <= 5.0f && Math.abs(f3 - f4) <= 5.0f;
    }

    /* renamed from: a */
    private static View m841a(ViewGroup viewGroup) {
        Object tag;
        if (viewGroup == null) {
            return null;
        }
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt != null && (tag = childAt.getTag(FitnessStatusCodes.SUCCESS_NO_DATA_SOURCES)) != null && (tag instanceof String) && ((String) tag).equals("baidu_mtj_edit_txtview")) {
                return childAt;
            }
        }
        return null;
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public static void m849b(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("是否确认退出连接?");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                C0900al.m880a().mo11502c();
                C0955bb.m1194c().mo11624a("autotrace: connect close, app close");
                C0900al.m880a().mo11492a(4);
                C0900al.m880a().mo11504d();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.create().show();
    }
}
