package com.baidu.mobstat;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.ListFragment;
import android.os.Build;
import android.text.TextUtils;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.webkit.WebView;
import android.webkit.WebViewFragment;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.VideoView;
import android.widget.ViewFlipper;
import android.widget.ViewSwitcher;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;
import java.util.HashMap;
import java.util.Locale;

/* renamed from: com.baidu.mobstat.ax */
public class C0948ax {

    /* renamed from: b */
    private static final C0948ax f1179b = new C0948ax();

    /* renamed from: a */
    private HashMap<String, String> f1180a = new HashMap<>();

    /* renamed from: a */
    public static C0948ax m1145a() {
        return f1179b;
    }

    private C0948ax() {
        m1148b();
    }

    /* renamed from: a */
    private void m1147a(Throwable th) {
        if (C0963bg.m1227c().mo11630b()) {
            C0963bg.m1227c().mo11627b(th.toString());
        }
    }

    /* renamed from: b */
    private void m1148b() {
        if (Build.VERSION.SDK_INT >= 14 && this.f1180a.size() == 0) {
            try {
                m1146a(AutoCompleteTextView.class.getSimpleName(), "A0");
            } catch (Throwable th) {
                m1147a(th);
            }
            try {
                m1146a(ActionBar.class.getSimpleName(), "A1");
            } catch (Throwable th2) {
                m1147a(th2);
            }
            try {
                m1146a(AlertDialog.class.getSimpleName(), "A2");
            } catch (Throwable th3) {
                m1147a(th3);
            }
            try {
                m1146a(Button.class.getSimpleName(), "B0");
            } catch (Throwable th4) {
                m1147a(th4);
            }
            try {
                m1146a(CheckBox.class.getSimpleName(), "C0");
            } catch (Throwable th5) {
                m1147a(th5);
            }
            try {
                m1146a(CheckedTextView.class.getSimpleName(), "C1");
            } catch (Throwable th6) {
                m1147a(th6);
            }
            try {
                m1146a(Class.forName("com.android.internal.policy.DecorView").getSimpleName(), "D0");
            } catch (Throwable th7) {
                m1147a(th7);
            }
            try {
                m1146a(DrawerLayout.class.getSimpleName(), "D1");
            } catch (Throwable th8) {
                m1147a(th8);
            }
            try {
                m1146a(EditText.class.getSimpleName(), "E0");
            } catch (Throwable th9) {
                m1147a(th9);
            }
            try {
                m1146a(ExpandableListView.class.getSimpleName(), "E1");
            } catch (Throwable th10) {
                m1147a(th10);
            }
            try {
                m1146a(FrameLayout.class.getSimpleName(), "F0");
            } catch (Throwable th11) {
                m1147a(th11);
            }
            try {
                m1146a(Fragment.class.getSimpleName(), "F1");
            } catch (Throwable th12) {
                m1147a(th12);
            }
            try {
                m1146a(Gallery.class.getSimpleName(), "G0");
            } catch (Throwable th13) {
                m1147a(th13);
            }
            try {
                m1146a(GridView.class.getSimpleName(), "G1");
            } catch (Throwable th14) {
                m1147a(th14);
            }
            try {
                m1146a(HorizontalScrollView.class.getSimpleName(), "H0");
            } catch (Throwable th15) {
                m1147a(th15);
            }
            try {
                m1146a(ImageButton.class.getSimpleName(), "I0");
            } catch (Throwable th16) {
                m1147a(th16);
            }
            try {
                m1146a(ImageSwitcher.class.getSimpleName(), "I1");
            } catch (Throwable th17) {
                m1147a(th17);
            }
            try {
                m1146a(ImageView.class.getSimpleName(), "I2");
            } catch (Throwable th18) {
                m1147a(th18);
            }
            try {
                m1146a(LinearLayout.class.getSimpleName(), "L0");
            } catch (Throwable th19) {
                m1147a(th19);
            }
            try {
                m1146a(ListView.class.getSimpleName(), "L1");
            } catch (Throwable th20) {
                m1147a(th20);
            }
            try {
                m1146a(ListFragment.class.getSimpleName(), "L2");
            } catch (Throwable th21) {
                m1147a(th21);
            }
            try {
                m1146a(MultiAutoCompleteTextView.class.getSimpleName(), "M0");
            } catch (Throwable th22) {
                m1147a(th22);
            }
            try {
                m1146a(NestedScrollView.class.getSimpleName(), "N0");
            } catch (Throwable th23) {
                m1147a(th23);
            }
            try {
                m1146a(ProgressBar.class.getSimpleName(), "P0");
            } catch (Throwable th24) {
                m1147a(th24);
            }
            try {
                m1146a(RadioButton.class.getSimpleName(), "R0");
            } catch (Throwable th25) {
                m1147a(th25);
            }
            try {
                m1146a(RadioGroup.class.getSimpleName(), "R1");
            } catch (Throwable th26) {
                m1147a(th26);
            }
            try {
                m1146a(RatingBar.class.getSimpleName(), "R2");
            } catch (Throwable th27) {
                m1147a(th27);
            }
            try {
                m1146a(RelativeLayout.class.getSimpleName(), "R3");
            } catch (Throwable th28) {
                m1147a(th28);
            }
            try {
                m1146a(RecyclerView.class.getSimpleName(), "R4");
            } catch (Throwable th29) {
                m1147a(th29);
            }
            try {
                m1146a(ScrollView.class.getSimpleName(), "S0");
            } catch (Throwable th30) {
                m1147a(th30);
            }
            try {
                m1146a(SearchView.class.getSimpleName(), "S1");
            } catch (Throwable th31) {
                m1147a(th31);
            }
            try {
                m1146a(SeekBar.class.getSimpleName(), "S2");
            } catch (Throwable th32) {
                m1147a(th32);
            }
            try {
                m1146a(Spinner.class.getSimpleName(), "S3");
            } catch (Throwable th33) {
                m1147a(th33);
            }
            try {
                m1146a(Switch.class.getSimpleName(), "S4");
            } catch (Throwable th34) {
                m1147a(th34);
            }
            try {
                m1146a(SurfaceView.class.getSimpleName(), "S5");
            } catch (Throwable th35) {
                m1147a(th35);
            }
            try {
                m1146a(SwipeRefreshLayout.class.getSimpleName(), "S6");
            } catch (Throwable th36) {
                m1147a(th36);
            }
            try {
                m1146a(TabHost.class.getSimpleName(), "T0");
            } catch (Throwable th37) {
                m1147a(th37);
            }
            try {
                m1146a(TableLayout.class.getSimpleName(), "T1");
            } catch (Throwable th38) {
                m1147a(th38);
            }
            try {
                m1146a(TableRow.class.getSimpleName(), "T2");
            } catch (Throwable th39) {
                m1147a(th39);
            }
            try {
                m1146a(TabWidget.class.getSimpleName(), "T3");
            } catch (Throwable th40) {
                m1147a(th40);
            }
            try {
                m1146a(TextSwitcher.class.getSimpleName(), "T4");
            } catch (Throwable th41) {
                m1147a(th41);
            }
            try {
                m1146a(TextView.class.getSimpleName(), "T5");
            } catch (Throwable th42) {
                m1147a(th42);
            }
            try {
                m1146a(Toast.class.getSimpleName(), "T6");
            } catch (Throwable th43) {
                m1147a(th43);
            }
            try {
                m1146a(ToggleButton.class.getSimpleName(), "T7");
            } catch (Throwable th44) {
                m1147a(th44);
            }
            try {
                m1146a(TextureView.class.getSimpleName(), "T8");
            } catch (Throwable th45) {
                m1147a(th45);
            }
            try {
                m1146a(Toolbar.class.getSimpleName(), "T9");
            } catch (Throwable th46) {
                m1147a(th46);
            }
            try {
                m1146a(View.class.getSimpleName(), "V0");
            } catch (Throwable th47) {
                m1147a(th47);
            }
            try {
                m1146a(ViewGroup.class.getSimpleName(), "V1");
            } catch (Throwable th48) {
                m1147a(th48);
            }
            try {
                m1146a(ViewStub.class.getSimpleName(), "V2");
            } catch (Throwable th49) {
                m1147a(th49);
            }
            try {
                m1146a(VideoView.class.getSimpleName(), "V3");
            } catch (Throwable th50) {
                m1147a(th50);
            }
            try {
                m1146a(ViewSwitcher.class.getSimpleName(), "V4");
            } catch (Throwable th51) {
                m1147a(th51);
            }
            try {
                m1146a(ViewFlipper.class.getSimpleName(), "V5");
            } catch (Throwable th52) {
                m1147a(th52);
            }
            try {
                m1146a(ViewPager.class.getSimpleName(), "V6");
            } catch (Throwable th53) {
                m1147a(th53);
            }
            try {
                m1146a(WebView.class.getSimpleName(), "W0");
            } catch (Throwable th54) {
                m1147a(th54);
            }
            try {
                m1146a(WebViewFragment.class.getSimpleName(), "W1");
            } catch (Throwable th55) {
                m1147a(th55);
            }
        }
    }

    /* renamed from: a */
    private void m1146a(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !this.f1180a.containsKey(str)) {
            this.f1180a.put(str, str2.toUpperCase(Locale.ENGLISH));
        }
    }

    /* renamed from: a */
    public String mo11614a(String str) {
        return this.f1180a.get(str);
    }
}
