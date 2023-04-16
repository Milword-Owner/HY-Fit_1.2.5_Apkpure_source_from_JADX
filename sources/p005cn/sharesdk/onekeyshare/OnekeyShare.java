package p005cn.sharesdk.onekeyshare;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import androidx.core.app.NotificationCompat;
import com.facebook.appevents.internal.ViewHierarchyConstants;
import com.facebook.share.internal.ShareConstants;
import com.mob.MobApplication;
import com.mob.MobSDK;
import com.mob.tools.utils.BitmapHelper;
import com.mob.tools.utils.ResHelper;
import java.util.ArrayList;
import java.util.HashMap;
import p005cn.sharesdk.framework.Platform;
import p005cn.sharesdk.framework.PlatformActionListener;
import p005cn.sharesdk.framework.ShareSDK;

/* renamed from: cn.sharesdk.onekeyshare.OnekeyShare */
public class OnekeyShare {
    public static final String SHARESDK_TAG = "ShareSDK";
    private HashMap<String, Object> params = new HashMap<>();

    public OnekeyShare() {
        this.params.put("customers", new ArrayList());
        this.params.put("hiddenPlatforms", new HashMap());
    }

    public void setActivity(Activity activity) {
        this.params.put("activity", activity);
    }

    public void setAddress(String str) {
        this.params.put("address", str);
    }

    public void setTitle(String str) {
        this.params.put("title", str);
    }

    public void setTitleUrl(String str) {
        this.params.put("titleUrl", str);
    }

    public void setText(String str) {
        this.params.put(ViewHierarchyConstants.TEXT_KEY, str);
    }

    public String getText() {
        if (this.params.containsKey(ViewHierarchyConstants.TEXT_KEY)) {
            return String.valueOf(this.params.get(ViewHierarchyConstants.TEXT_KEY));
        }
        return null;
    }

    public void setImagePath(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.params.put("imagePath", str);
        }
    }

    public void setImageUrl(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.params.put("imageUrl", str);
        }
    }

    public void setImageData(Bitmap bitmap) {
        if (bitmap != null) {
            this.params.put("imageData", bitmap);
        }
    }

    public void setUrl(String str) {
        this.params.put("url", str);
    }

    public void setFilePath(String str) {
        this.params.put("filePath", str);
    }

    public void setComment(String str) {
        this.params.put("comment", str);
    }

    public void setSite(String str) {
        this.params.put("site", str);
    }

    public void setSiteUrl(String str) {
        this.params.put("siteUrl", str);
    }

    public void setVenueName(String str) {
        this.params.put("venueName", str);
    }

    public void setVenueDescription(String str) {
        this.params.put("venueDescription", str);
    }

    public void setLatitude(float f) {
        this.params.put("latitude", Float.valueOf(f));
    }

    public void setLongitude(float f) {
        this.params.put("longitude", Float.valueOf(f));
    }

    public void setSilent(boolean z) {
        this.params.put(NotificationCompat.GROUP_KEY_SILENT, Boolean.valueOf(z));
    }

    public void setDisappearShareToast(boolean z) {
        this.params.put("disappearsharetoast", Boolean.valueOf(z));
    }

    public void setDialogMode(boolean z) {
        this.params.put("dialogMode", Boolean.valueOf(z));
    }

    public void setPlatform(String str) {
        this.params.put("platform", str);
    }

    public void setInstallUrl(String str) {
        this.params.put("installurl", str);
    }

    public void setExecuteUrl(String str) {
        this.params.put("executeurl", str);
    }

    public void setLinkedinDescription(String str) {
        this.params.put("linkedinDescription", str);
    }

    public void setMusicUrl(String str) {
        this.params.put("musicUrl", str);
    }

    public void setQuote(String str) {
        this.params.put(ShareConstants.QUOTE, str);
    }

    public void setHashtag(String str) {
        this.params.put(ShareConstants.HASHTAG, str);
    }

    public void setQQMiniProgramAppid(String str) {
        this.params.put("mini_program_appid", str);
    }

    public void setQQMiniProgramPath(String str) {
        this.params.put("mini_program_path", str);
    }

    public void setQQMiniProgramType(String str) {
        this.params.put("mini_program_type", str);
    }

    public void setCallback(PlatformActionListener platformActionListener) {
        this.params.put("callback", platformActionListener);
    }

    public PlatformActionListener getCallback() {
        return (PlatformActionListener) ResHelper.forceCast(this.params.get("callback"));
    }

    public void setShareContentCustomizeCallback(ShareContentCustomizeCallback shareContentCustomizeCallback) {
        this.params.put("customizeCallback", shareContentCustomizeCallback);
    }

    public ShareContentCustomizeCallback getShareContentCustomizeCallback() {
        return (ShareContentCustomizeCallback) ResHelper.forceCast(this.params.get("customizeCallback"));
    }

    public void setCustomerLogo(Bitmap bitmap, String str, View.OnClickListener onClickListener) {
        CustomerLogo customerLogo = new CustomerLogo();
        customerLogo.logo = bitmap;
        customerLogo.label = str;
        customerLogo.listener = onClickListener;
        ((ArrayList) ResHelper.forceCast(this.params.get("customers"))).add(customerLogo);
    }

    public void disableSSOWhenAuthorize() {
        this.params.put("disableSSO", true);
    }

    public void setVideoUrl(String str) {
        this.params.put("url", str);
        this.params.put("shareType", 6);
    }

    public void addHiddenPlatform(String str) {
        ((HashMap) ResHelper.forceCast(this.params.get("hiddenPlatforms"))).put(str, str);
    }

    public void setViewToShare(View view) {
        try {
            this.params.put("viewToShare", BitmapHelper.captureView(view, view.getWidth(), view.getHeight()));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void setImageArray(String[] strArr) {
        this.params.put("imageArray", strArr);
    }

    public void setShareToTencentWeiboWhenPerformingQQOrQZoneSharing() {
        this.params.put("isShareTencentWeibo", true);
    }

    public void setTheme(OnekeyShareTheme onekeyShareTheme) {
        this.params.put("theme", Integer.valueOf(onekeyShareTheme.getValue()));
    }

    public void show(Context context) {
        int i;
        HashMap hashMap = new HashMap();
        hashMap.putAll(this.params);
        if (!(context instanceof MobApplication)) {
            MobSDK.init(context.getApplicationContext());
        }
        ShareSDK.logDemoEvent(1, (Platform) null);
        boolean z = false;
        try {
            i = ResHelper.parseInt(String.valueOf(hashMap.remove("theme")));
        } catch (Throwable unused) {
            i = 0;
        }
        OnekeyShareThemeImpl impl = OnekeyShareTheme.fromValue(i).getImpl();
        impl.setShareParamsMap(hashMap);
        impl.setDialogMode(hashMap.containsKey("dialogMode") ? ((Boolean) hashMap.remove("dialogMode")).booleanValue() : false);
        if (hashMap.containsKey(NotificationCompat.GROUP_KEY_SILENT)) {
            z = ((Boolean) hashMap.remove(NotificationCompat.GROUP_KEY_SILENT)).booleanValue();
        }
        impl.setSilent(z);
        impl.setCustomerLogos((ArrayList) hashMap.remove("customers"));
        impl.setHiddenPlatforms((HashMap) hashMap.remove("hiddenPlatforms"));
        impl.setPlatformActionListener((PlatformActionListener) hashMap.remove("callback"));
        impl.setShareContentCustomizeCallback((ShareContentCustomizeCallback) hashMap.remove("customizeCallback"));
        if (hashMap.containsKey("disableSSO") && ((Boolean) hashMap.remove("disableSSO")).booleanValue()) {
            impl.disableSSO();
        }
        impl.show(context.getApplicationContext());
    }
}
