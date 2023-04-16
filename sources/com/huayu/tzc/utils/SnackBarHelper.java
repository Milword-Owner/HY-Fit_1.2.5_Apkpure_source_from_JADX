package com.huayu.tzc.utils;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;
import com.google.android.gms.fitness.FitnessStatusCodes;
import com.google.android.material.snackbar.Snackbar;
import com.huayu.tzc.C2128R;

public class SnackBarHelper {
    public static void showLong(View view, String str) {
        showLongWithColor(view, str, BaseApplication.getInstance().getResources().getColor(C2128R.C2129color.colorBlack30));
    }

    public static void showLong(Activity activity, String str) {
        showLongWithColor(activity.getWindow().getDecorView().findViewById(16908290), str, BaseApplication.getInstance().getResources().getColor(C2128R.C2129color.colorBlack30));
    }

    public static void showLongWithColor(View view, String str, int i) {
        final Snackbar action = Snackbar.make(view, (CharSequence) str, -2).setAction((CharSequence) "", (View.OnClickListener) new View.OnClickListener() {
            public void onClick(View view) {
            }
        });
        View view2 = action.getView();
        ((TextView) view2.findViewById(C2128R.C2131id.snackbar_text)).setMaxLines(5);
        view2.setBackgroundColor(i);
        action.setDuration(FitnessStatusCodes.NEEDS_OAUTH_PERMISSIONS);
        view2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                action.dismiss();
            }
        });
        action.show();
    }
}
