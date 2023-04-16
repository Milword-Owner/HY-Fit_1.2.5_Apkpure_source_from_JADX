package p005cn.sharesdk.framework.utils;

import android.content.Context;
import com.mob.tools.utils.ResHelper;

/* renamed from: cn.sharesdk.framework.utils.g */
/* compiled from: SizeHelper */
public class C0809g {

    /* renamed from: a */
    public static float f617a = 1.5f;

    /* renamed from: b */
    public static int f618b = 540;

    /* renamed from: c */
    private static Context f619c;

    /* renamed from: a */
    public static void m688a(Context context) {
        Context context2 = f619c;
        if (context2 == null || context2 != context.getApplicationContext()) {
            f619c = context;
        }
    }

    /* renamed from: a */
    public static int m687a(int i) {
        return ResHelper.designToDevice(f619c, f617a, i);
    }

    /* renamed from: b */
    public static int m689b(int i) {
        return ResHelper.designToDevice(f619c, f618b, i);
    }
}
