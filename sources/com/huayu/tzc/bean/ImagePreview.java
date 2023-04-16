package com.huayu.tzc.bean;

import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import com.previewlibrary.enitity.IThumbViewInfo;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.android.parcel.Parcelize;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u00012\u00020\u0002B\u0007\b\u0016¢\u0006\u0002\u0010\u0003B\u000f\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B+\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\u0005\u0012\b\b\u0002\u0010\n\u001a\u00020\u0005¢\u0006\u0002\u0010\u000bJ\t\u0010\u0013\u001a\u00020\u0005HÂ\u0003J\t\u0010\u0014\u001a\u00020\bHÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÂ\u0003J1\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u0005HÆ\u0001J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dHÖ\u0003J\b\u0010\u001e\u001a\u00020\bH\u0016J\b\u0010\u001f\u001a\u00020\u0005H\u0016J\b\u0010 \u001a\u00020\u0005H\u0016J\t\u0010!\u001a\u00020\u0019HÖ\u0001J\t\u0010\"\u001a\u00020\u0005HÖ\u0001J\u0019\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020\u0019HÖ\u0001R\u001a\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0006R\u000e\u0010\n\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000¨\u0006("}, mo21895d2 = {"Lcom/huayu/tzc/bean/ImagePreview;", "Landroid/os/Parcelable;", "Lcom/previewlibrary/enitity/IThumbViewInfo;", "()V", "url", "", "(Ljava/lang/String;)V", "mBounds", "Landroid/graphics/Rect;", "user", "videoUrl", "(Ljava/lang/String;Landroid/graphics/Rect;Ljava/lang/String;Ljava/lang/String;)V", "getMBounds", "()Landroid/graphics/Rect;", "setMBounds", "(Landroid/graphics/Rect;)V", "getUser", "()Ljava/lang/String;", "setUser", "component1", "component2", "component3", "component4", "copy", "describeContents", "", "equals", "", "other", "", "getBounds", "getUrl", "getVideoUrl", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
@Parcelize
/* compiled from: ImagePreview.kt */
public final class ImagePreview implements Parcelable, IThumbViewInfo {
    public static final Parcelable.Creator CREATOR = new Creator();
    @NotNull
    private Rect mBounds;
    private String url;
    @NotNull
    private String user;
    private String videoUrl;

    @Metadata(mo21893bv = {1, 0, 3}, mo21896k = 3, mo21897mv = {1, 1, 16})
    public static class Creator implements Parcelable.Creator {
        @NotNull
        public final Object createFromParcel(@NotNull Parcel parcel) {
            Intrinsics.checkParameterIsNotNull(parcel, "in");
            return new ImagePreview(parcel.readString(), (Rect) Rect.CREATOR.createFromParcel(parcel), parcel.readString(), parcel.readString());
        }

        @NotNull
        public final Object[] newArray(int i) {
            return new ImagePreview[i];
        }
    }

    private final String component1() {
        return this.url;
    }

    private final String component4() {
        return this.videoUrl;
    }

    public static /* synthetic */ ImagePreview copy$default(ImagePreview imagePreview, String str, Rect rect, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = imagePreview.url;
        }
        if ((i & 2) != 0) {
            rect = imagePreview.mBounds;
        }
        if ((i & 4) != 0) {
            str2 = imagePreview.user;
        }
        if ((i & 8) != 0) {
            str3 = imagePreview.videoUrl;
        }
        return imagePreview.copy(str, rect, str2, str3);
    }

    @NotNull
    public final Rect component2() {
        return this.mBounds;
    }

    @NotNull
    public final String component3() {
        return this.user;
    }

    @NotNull
    public final ImagePreview copy(@NotNull String str, @NotNull Rect rect, @NotNull String str2, @NotNull String str3) {
        Intrinsics.checkParameterIsNotNull(str, "url");
        Intrinsics.checkParameterIsNotNull(rect, "mBounds");
        Intrinsics.checkParameterIsNotNull(str2, "user");
        Intrinsics.checkParameterIsNotNull(str3, "videoUrl");
        return new ImagePreview(str, rect, str2, str3);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ImagePreview)) {
            return false;
        }
        ImagePreview imagePreview = (ImagePreview) obj;
        return Intrinsics.areEqual((Object) this.url, (Object) imagePreview.url) && Intrinsics.areEqual((Object) this.mBounds, (Object) imagePreview.mBounds) && Intrinsics.areEqual((Object) this.user, (Object) imagePreview.user) && Intrinsics.areEqual((Object) this.videoUrl, (Object) imagePreview.videoUrl);
    }

    public int hashCode() {
        String str = this.url;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        Rect rect = this.mBounds;
        int hashCode2 = (hashCode + (rect != null ? rect.hashCode() : 0)) * 31;
        String str2 = this.user;
        int hashCode3 = (hashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.videoUrl;
        if (str3 != null) {
            i = str3.hashCode();
        }
        return hashCode3 + i;
    }

    @NotNull
    public String toString() {
        return "ImagePreview(url=" + this.url + ", mBounds=" + this.mBounds + ", user=" + this.user + ", videoUrl=" + this.videoUrl + ")";
    }

    public void writeToParcel(@NotNull Parcel parcel, int i) {
        Intrinsics.checkParameterIsNotNull(parcel, "parcel");
        parcel.writeString(this.url);
        this.mBounds.writeToParcel(parcel, 0);
        parcel.writeString(this.user);
        parcel.writeString(this.videoUrl);
    }

    public ImagePreview(@NotNull String str, @NotNull Rect rect, @NotNull String str2, @NotNull String str3) {
        Intrinsics.checkParameterIsNotNull(str, "url");
        Intrinsics.checkParameterIsNotNull(rect, "mBounds");
        Intrinsics.checkParameterIsNotNull(str2, "user");
        Intrinsics.checkParameterIsNotNull(str3, "videoUrl");
        this.url = str;
        this.mBounds = rect;
        this.user = str2;
        this.videoUrl = str3;
    }

    @NotNull
    public final Rect getMBounds() {
        return this.mBounds;
    }

    public final void setMBounds(@NotNull Rect rect) {
        Intrinsics.checkParameterIsNotNull(rect, "<set-?>");
        this.mBounds = rect;
    }

    @NotNull
    public final String getUser() {
        return this.user;
    }

    public final void setUser(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.user = str;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ImagePreview(String str, Rect rect, String str2, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : str, rect, (i & 4) != 0 ? "用户字段" : str2, (i & 8) != 0 ? "" : str3);
    }

    public ImagePreview() {
        this("", new Rect(), "", "");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public ImagePreview(@NotNull String str) {
        this();
        Intrinsics.checkParameterIsNotNull(str, "url");
        this.url = str;
    }

    @NotNull
    public String getUrl() {
        return this.url;
    }

    @NotNull
    public String getVideoUrl() {
        return this.videoUrl;
    }

    @NotNull
    public Rect getBounds() {
        return this.mBounds;
    }
}
