package p005cn.sharesdk.onekeyshare.themes.classic;

import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import com.mob.tools.gui.ScaledImageView;
import p005cn.sharesdk.onekeyshare.OnekeySharePage;
import p005cn.sharesdk.onekeyshare.OnekeyShareThemeImpl;

/* renamed from: cn.sharesdk.onekeyshare.themes.classic.PicViewerPage */
public class PicViewerPage extends OnekeySharePage implements ViewTreeObserver.OnGlobalLayoutListener {
    /* access modifiers changed from: private */
    public Bitmap pic;
    /* access modifiers changed from: private */
    public ScaledImageView sivViewer;

    public PicViewerPage(OnekeyShareThemeImpl onekeyShareThemeImpl) {
        super(onekeyShareThemeImpl);
    }

    public void setImageBitmap(Bitmap bitmap) {
        this.pic = bitmap;
    }

    public void onCreate() {
        this.activity.getWindow().setBackgroundDrawable(new ColorDrawable(1275068416));
        this.sivViewer = new ScaledImageView(this.activity);
        this.sivViewer.setScaleType(ImageView.ScaleType.MATRIX);
        this.activity.setContentView(this.sivViewer);
        if (this.pic != null) {
            this.sivViewer.getViewTreeObserver().addOnGlobalLayoutListener(this);
        }
    }

    public void onGlobalLayout() {
        this.sivViewer.getViewTreeObserver().removeGlobalOnLayoutListener(this);
        this.sivViewer.post(new Runnable() {
            public void run() {
                PicViewerPage.this.sivViewer.setBitmap(PicViewerPage.this.pic);
            }
        });
    }
}
