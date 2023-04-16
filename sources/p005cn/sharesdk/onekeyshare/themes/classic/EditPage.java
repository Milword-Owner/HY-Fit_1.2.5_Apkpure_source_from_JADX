package p005cn.sharesdk.onekeyshare.themes.classic;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import com.mob.MobSDK;
import com.mob.tools.gui.AsyncImageView;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.ReflectHelper;
import com.mob.tools.utils.ResHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import p005cn.sharesdk.facebook.Facebook;
import p005cn.sharesdk.framework.Platform;
import p005cn.sharesdk.framework.ShareSDK;
import p005cn.sharesdk.onekeyshare.OnekeySharePage;
import p005cn.sharesdk.onekeyshare.OnekeyShareThemeImpl;
import p005cn.sharesdk.onekeyshare.themes.classic.land.FriendListPageLand;
import p005cn.sharesdk.onekeyshare.themes.classic.port.FriendListPagePort;

/* renamed from: cn.sharesdk.onekeyshare.themes.classic.EditPage */
public class EditPage extends OnekeySharePage implements View.OnClickListener, TextWatcher, Runnable {
    protected AsyncImageView aivThumb;
    protected EditText etContent;
    private OnekeyShareThemeImpl impl;
    protected LinearLayout llBottom;
    protected LinearLayout llPage;
    protected int maxBodyHeight;
    protected Platform platform;
    protected RelativeLayout rlThumb;
    protected RelativeLayout rlTitle;

    /* renamed from: sp */
    protected Platform.ShareParams f627sp;
    protected ScrollView svContent;
    /* access modifiers changed from: protected */
    public Bitmap thumb;
    protected TextView tvAt;
    protected TextView tvCancel;
    protected TextView tvShare;
    protected TextView tvTextCouter;
    protected XView xvRemove;

    public void afterTextChanged(Editable editable) {
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public EditPage(OnekeyShareThemeImpl onekeyShareThemeImpl) {
        super(onekeyShareThemeImpl);
        this.impl = onekeyShareThemeImpl;
    }

    public void setPlatform(Platform platform2) {
        this.platform = platform2;
    }

    public void setShareParams(Platform.ShareParams shareParams) {
        this.f627sp = shareParams;
    }

    /* access modifiers changed from: protected */
    public int onSetTheme(int i, boolean z) {
        if (isDialogMode()) {
            this.activity.requestWindowFeature(1);
            if (Build.VERSION.SDK_INT < 11) {
                return 16973835;
            }
            try {
                ReflectHelper.invokeInstanceMethod(this.activity, "setFinishOnTouchOutside", false);
                return 16973835;
            } catch (Throwable unused) {
                return 16973835;
            }
        } else {
            this.activity.getWindow().setSoftInputMode(37);
            return super.onSetTheme(i, z);
        }
    }

    public void onCreate() {
        this.activity.getWindow().setBackgroundDrawable(new ColorDrawable(-789517));
    }

    private void cancelAndFinish() {
        ShareSDK.logDemoEvent(5, this.platform);
        finish();
    }

    private void shareAndFinish() {
        int stringRes = ResHelper.getStringRes(this.activity, "ssdk_oks_sharing");
        if (stringRes > 0) {
            Toast.makeText(this.activity, stringRes, 0).show();
        }
        if (isDisableSSO()) {
            this.platform.SSOSetting(true);
        }
        this.platform.setPlatformActionListener(getCallback());
        this.platform.share(this.f627sp);
        this.impl.callback = null;
        finish();
    }

    private void showThumb(Bitmap bitmap) {
        PicViewerPage picViewerPage = new PicViewerPage(this.impl);
        picViewerPage.setImageBitmap(bitmap);
        picViewerPage.show(this.activity, (Intent) null);
    }

    private void removeThumb() {
        this.f627sp.setImageArray((String[]) null);
        this.f627sp.setImageData((Bitmap) null);
        this.f627sp.setImagePath((String) null);
        this.f627sp.setImageUrl((String) null);
    }

    private void showFriendList() {
        FriendListPage friendListPage;
        if (this.activity.getResources().getConfiguration().orientation == 1) {
            friendListPage = new FriendListPagePort(this.impl);
        } else {
            friendListPage = new FriendListPageLand(this.impl);
        }
        friendListPage.setPlatform(this.platform);
        friendListPage.showForResult(MobSDK.getContext(), (Intent) null, this);
    }

    public void onResult(HashMap<String, Object> hashMap) {
        String joinSelectedUser = getJoinSelectedUser(hashMap);
        if (!TextUtils.isEmpty(joinSelectedUser)) {
            this.etContent.append(joinSelectedUser);
        }
    }

    private String getJoinSelectedUser(HashMap<String, Object> hashMap) {
        if (hashMap == null || !hashMap.containsKey("selected")) {
            return null;
        }
        ArrayList arrayList = (ArrayList) hashMap.get("selected");
        if ("FacebookMessenger".equals(((Platform) hashMap.get("platform")).getName())) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            sb.append('@');
            sb.append((String) it.next());
            sb.append(' ');
        }
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public boolean isShowAtUserLayout(String str) {
        return "SinaWeibo".equals(str) || "TencentWeibo".equals(str) || Facebook.NAME.equals(str) || "Twitter".equals(str);
    }

    public void onClick(View view) {
        if (view.equals(this.tvCancel)) {
            cancelAndFinish();
        } else if (view.equals(this.tvShare)) {
            this.f627sp.setText(this.etContent.getText().toString().trim());
            shareAndFinish();
        } else if (view.equals(this.aivThumb)) {
            showThumb(this.thumb);
        } else if (view.equals(this.xvRemove)) {
            this.maxBodyHeight = 0;
            this.rlThumb.setVisibility(8);
            this.llPage.measure(0, 0);
            onTextChanged(this.etContent.getText(), 0, 0, 0);
            removeThumb();
        } else if (view.equals(this.tvAt)) {
            showFriendList();
        }
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        this.tvTextCouter.setText(String.valueOf(charSequence.length()));
        if (this.maxBodyHeight == 0) {
            this.maxBodyHeight = (this.llPage.getHeight() - this.rlTitle.getHeight()) - this.llBottom.getHeight();
        }
        if (this.maxBodyHeight > 0) {
            this.svContent.post(this);
        }
    }

    public void run() {
        int i;
        int height = this.svContent.getChildAt(0).getHeight();
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) ResHelper.forceCast(this.svContent.getLayoutParams());
        if (height > this.maxBodyHeight && layoutParams.height != (i = this.maxBodyHeight)) {
            layoutParams.height = i;
            this.svContent.setLayoutParams(layoutParams);
        } else if (height < this.maxBodyHeight && layoutParams.height == this.maxBodyHeight) {
            layoutParams.height = -2;
            this.svContent.setLayoutParams(layoutParams);
        }
    }

    public void onPause() {
        DeviceHelper.getInstance(this.activity).hideSoftInput(getContentView());
        super.onPause();
    }
}
