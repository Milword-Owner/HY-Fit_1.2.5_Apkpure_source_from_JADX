package p005cn.sharesdk.onekeyshare.themes.classic.port;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.mob.tools.gui.AsyncImageView;
import com.mob.tools.utils.ResHelper;
import p005cn.sharesdk.onekeyshare.OnekeyShareThemeImpl;
import p005cn.sharesdk.onekeyshare.themes.classic.EditPage;
import p005cn.sharesdk.onekeyshare.themes.classic.XView;

/* renamed from: cn.sharesdk.onekeyshare.themes.classic.port.EditPagePort */
public class EditPagePort extends EditPage {
    private static final int DESIGN_BOTTOM_HEIGHT = 75;
    private static final int DESIGN_LEFT_PADDING = 40;
    private static final int DESIGN_REMOVE_THUMB_HEIGHT = 70;
    private static final int DESIGN_SCREEN_HEIGHT = 1280;
    private static final int DESIGN_THUMB_HEIGHT = 300;
    private static final int DESIGN_TITLE_HEIGHT = 96;

    public EditPagePort(OnekeyShareThemeImpl onekeyShareThemeImpl) {
        super(onekeyShareThemeImpl);
    }

    public void onCreate() {
        super.onCreate();
        float screenHeight = ((float) ResHelper.getScreenHeight(this.activity)) / 1280.0f;
        this.maxBodyHeight = 0;
        this.llPage = new LinearLayout(this.activity);
        this.llPage.setOrientation(1);
        this.activity.setContentView(this.llPage);
        this.rlTitle = new RelativeLayout(this.activity);
        this.rlTitle.setBackgroundColor(-1644052);
        this.llPage.addView(this.rlTitle, new LinearLayout.LayoutParams(-1, (int) (96.0f * screenHeight)));
        initTitle(this.rlTitle, screenHeight);
        RelativeLayout relativeLayout = new RelativeLayout(this.activity);
        relativeLayout.setBackgroundColor(-1);
        this.llPage.addView(relativeLayout, new LinearLayout.LayoutParams(-1, -2));
        initBody(relativeLayout, screenHeight);
        LinearLayout linearLayout = new LinearLayout(this.activity);
        linearLayout.setOrientation(1);
        relativeLayout.addView(linearLayout, new RelativeLayout.LayoutParams(-1, -2));
        initShadow(linearLayout, screenHeight);
        this.llBottom = new LinearLayout(this.activity);
        this.llBottom.setOrientation(1);
        this.llPage.addView(this.llBottom, new LinearLayout.LayoutParams(-1, -2));
        initBottom(this.llBottom, screenHeight);
    }

    private void initTitle(RelativeLayout relativeLayout, float f) {
        this.tvCancel = new TextView(this.activity);
        this.tvCancel.setTextColor(-12895429);
        this.tvCancel.setTextSize(2, 18.0f);
        this.tvCancel.setGravity(17);
        int stringRes = ResHelper.getStringRes(this.activity, "ssdk_oks_cancel");
        if (stringRes > 0) {
            this.tvCancel.setText(stringRes);
        }
        int i = (int) (f * 40.0f);
        this.tvCancel.setPadding(i, 0, i, 0);
        relativeLayout.addView(this.tvCancel, new RelativeLayout.LayoutParams(-2, -1));
        this.tvCancel.setOnClickListener(this);
        TextView textView = new TextView(this.activity);
        textView.setTextColor(-12895429);
        textView.setTextSize(2, 22.0f);
        textView.setGravity(17);
        int stringRes2 = ResHelper.getStringRes(this.activity, "ssdk_oks_multi_share");
        if (stringRes2 > 0) {
            textView.setText(stringRes2);
        }
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -1);
        layoutParams.addRule(13);
        relativeLayout.addView(textView, layoutParams);
        this.tvShare = new TextView(this.activity);
        this.tvShare.setTextColor(-37615);
        this.tvShare.setTextSize(2, 18.0f);
        this.tvShare.setGravity(17);
        int stringRes3 = ResHelper.getStringRes(this.activity, "ssdk_oks_share");
        if (stringRes3 > 0) {
            this.tvShare.setText(stringRes3);
        }
        this.tvShare.setPadding(i, 0, i, 0);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -1);
        layoutParams2.addRule(11);
        relativeLayout.addView(this.tvShare, layoutParams2);
        this.tvShare.setOnClickListener(this);
    }

    private void initBody(RelativeLayout relativeLayout, float f) {
        this.svContent = new ScrollView(this.activity);
        relativeLayout.addView(this.svContent, new RelativeLayout.LayoutParams(-1, -2));
        LinearLayout linearLayout = new LinearLayout(this.activity);
        linearLayout.setOrientation(1);
        this.svContent.addView(linearLayout, new FrameLayout.LayoutParams(-1, -2));
        this.etContent = new EditText(this.activity);
        int i = (int) (40.0f * f);
        this.etContent.setPadding(i, i, i, i);
        this.etContent.setBackgroundDrawable((Drawable) null);
        this.etContent.setTextColor(-12895429);
        this.etContent.setTextSize(2, 21.0f);
        this.etContent.setText(this.f627sp.getText());
        linearLayout.addView(this.etContent, new LinearLayout.LayoutParams(-1, -2));
        this.etContent.addTextChangedListener(this);
        this.rlThumb = new RelativeLayout(this.activity);
        this.rlThumb.setBackgroundColor(-13553359);
        int i2 = (int) (300.0f * f);
        int i3 = (int) (70.0f * f);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(i2, i2);
        layoutParams.topMargin = i;
        layoutParams.bottomMargin = i;
        layoutParams.rightMargin = i;
        layoutParams.leftMargin = i;
        linearLayout.addView(this.rlThumb, layoutParams);
        this.aivThumb = new AsyncImageView(this.activity) {
            public void onImageGot(String str, Bitmap bitmap) {
                Bitmap unused = EditPagePort.this.thumb = bitmap;
                super.onImageGot(str, bitmap);
            }
        };
        this.aivThumb.setScaleToCropCenter(true);
        this.rlThumb.addView(this.aivThumb, new RelativeLayout.LayoutParams(i2, i2));
        this.aivThumb.setOnClickListener(this);
        initThumb(this.aivThumb);
        this.xvRemove = new XView(this.activity);
        this.xvRemove.setRatio(f);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(i3, i3);
        layoutParams2.addRule(10);
        layoutParams2.addRule(11);
        this.rlThumb.addView(this.xvRemove, layoutParams2);
        this.xvRemove.setOnClickListener(this);
    }

    private void initBottom(LinearLayout linearLayout, float f) {
        LinearLayout linearLayout2 = new LinearLayout(this.activity);
        linearLayout2.setPadding(0, 0, 0, 5);
        linearLayout2.setBackgroundColor(-1);
        linearLayout.addView(linearLayout2, new LinearLayout.LayoutParams(-1, (int) (75.0f * f)));
        this.tvAt = new TextView(this.activity);
        this.tvAt.setTextColor(-12895429);
        this.tvAt.setTextSize(2, 22.0f);
        this.tvAt.setGravity(80);
        this.tvAt.setText("@");
        int i = (int) (40.0f * f);
        this.tvAt.setPadding(i, 0, i, 0);
        linearLayout2.addView(this.tvAt, new LinearLayout.LayoutParams(-2, -1));
        this.tvAt.setOnClickListener(this);
        if (isShowAtUserLayout(this.platform.getName())) {
            this.tvAt.setVisibility(0);
        } else {
            this.tvAt.setVisibility(4);
        }
        this.tvTextCouter = new TextView(this.activity);
        this.tvTextCouter.setTextColor(-12895429);
        this.tvTextCouter.setTextSize(2, 21.0f);
        this.tvTextCouter.setGravity(85);
        onTextChanged(this.etContent.getText(), 0, 0, 0);
        this.tvTextCouter.setPadding(i, 0, i, 0);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -1);
        layoutParams.weight = 1.0f;
        linearLayout2.addView(this.tvTextCouter, layoutParams);
        View view = new View(this.activity);
        view.setBackgroundColor(-3355444);
        linearLayout.addView(view, new LinearLayout.LayoutParams(-1, f > 1.0f ? (int) f : 1));
    }

    private void initShadow(LinearLayout linearLayout, float f) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, f > 1.0f ? (int) f : 1);
        View view = new View(this.activity);
        view.setBackgroundColor(687865856);
        linearLayout.addView(view, layoutParams);
        View view2 = new View(this.activity);
        view2.setBackgroundColor(335544320);
        linearLayout.addView(view2, layoutParams);
        View view3 = new View(this.activity);
        view3.setBackgroundColor(117440512);
        linearLayout.addView(view3, layoutParams);
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0035  */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x003b A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0060  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0066  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void initThumb(com.mob.tools.gui.AsyncImageView r7) {
        /*
            r6 = this;
            cn.sharesdk.framework.Platform$ShareParams r0 = r6.f627sp
            java.lang.String r0 = r0.getImageUrl()
            cn.sharesdk.framework.Platform$ShareParams r1 = r6.f627sp
            java.lang.String r1 = r1.getImagePath()
            cn.sharesdk.framework.Platform$ShareParams r2 = r6.f627sp
            java.lang.String[] r2 = r2.getImageArray()
            android.widget.RelativeLayout r3 = r6.rlThumb
            r4 = 0
            r3.setVisibility(r4)
            boolean r3 = android.text.TextUtils.isEmpty(r1)
            if (r3 != 0) goto L_0x0032
            java.io.File r3 = new java.io.File
            r3.<init>(r1)
            boolean r3 = r3.exists()
            if (r3 == 0) goto L_0x0032
            android.graphics.Bitmap r3 = com.mob.tools.utils.BitmapHelper.getBitmap(r1)     // Catch:{ Throwable -> 0x002e }
            goto L_0x0033
        L_0x002e:
            r3 = move-exception
            r3.printStackTrace()
        L_0x0032:
            r3 = 0
        L_0x0033:
            if (r3 == 0) goto L_0x003b
            r6.thumb = r3
            r7.setBitmap(r3)
            goto L_0x005e
        L_0x003b:
            if (r2 == 0) goto L_0x005e
            int r5 = r2.length
            if (r5 <= 0) goto L_0x005e
            r5 = r2[r4]
            boolean r5 = android.text.TextUtils.isEmpty(r5)
            if (r5 != 0) goto L_0x005e
            java.io.File r5 = new java.io.File
            r2 = r2[r4]
            r5.<init>(r2)
            boolean r2 = r5.exists()
            if (r2 == 0) goto L_0x005e
            android.graphics.Bitmap r3 = com.mob.tools.utils.BitmapHelper.getBitmap(r1)     // Catch:{ Throwable -> 0x005a }
            goto L_0x005e
        L_0x005a:
            r1 = move-exception
            r1.printStackTrace()
        L_0x005e:
            if (r3 == 0) goto L_0x0066
            r6.thumb = r3
            r7.setBitmap(r3)
            goto L_0x0079
        L_0x0066:
            if (r3 != 0) goto L_0x0072
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 != 0) goto L_0x0072
            r7.execute((java.lang.String) r0, (int) r4)
            goto L_0x0079
        L_0x0072:
            android.widget.RelativeLayout r7 = r6.rlThumb
            r0 = 8
            r7.setVisibility(r0)
        L_0x0079:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p005cn.sharesdk.onekeyshare.themes.classic.port.EditPagePort.initThumb(com.mob.tools.gui.AsyncImageView):void");
    }
}
