package com.facebook.share.model;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.facebook.share.model.ShareMedia;
import java.util.ArrayList;
import java.util.List;

public final class SharePhoto extends ShareMedia {
    public static final Parcelable.Creator<SharePhoto> CREATOR = new Parcelable.Creator<SharePhoto>() {
        public SharePhoto createFromParcel(Parcel parcel) {
            return new SharePhoto(parcel);
        }

        public SharePhoto[] newArray(int i) {
            return new SharePhoto[i];
        }
    };
    private final Bitmap bitmap;
    private final String caption;
    private final Uri imageUrl;
    private final boolean userGenerated;

    public int describeContents() {
        return 0;
    }

    private SharePhoto(Builder builder) {
        super((ShareMedia.Builder) builder);
        this.bitmap = builder.bitmap;
        this.imageUrl = builder.imageUrl;
        this.userGenerated = builder.userGenerated;
        this.caption = builder.caption;
    }

    SharePhoto(Parcel parcel) {
        super(parcel);
        this.bitmap = (Bitmap) parcel.readParcelable(Bitmap.class.getClassLoader());
        this.imageUrl = (Uri) parcel.readParcelable(Uri.class.getClassLoader());
        this.userGenerated = parcel.readByte() != 0;
        this.caption = parcel.readString();
    }

    @Nullable
    public Bitmap getBitmap() {
        return this.bitmap;
    }

    @Nullable
    public Uri getImageUrl() {
        return this.imageUrl;
    }

    public boolean getUserGenerated() {
        return this.userGenerated;
    }

    public String getCaption() {
        return this.caption;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(this.bitmap, 0);
        parcel.writeParcelable(this.imageUrl, 0);
        parcel.writeByte(this.userGenerated ? (byte) 1 : 0);
        parcel.writeString(this.caption);
    }

    public ShareMedia.Type getMediaType() {
        return ShareMedia.Type.PHOTO;
    }

    public static final class Builder extends ShareMedia.Builder<SharePhoto, Builder> {
        /* access modifiers changed from: private */
        public Bitmap bitmap;
        /* access modifiers changed from: private */
        public String caption;
        /* access modifiers changed from: private */
        public Uri imageUrl;
        /* access modifiers changed from: private */
        public boolean userGenerated;

        public Builder setBitmap(@Nullable Bitmap bitmap2) {
            this.bitmap = bitmap2;
            return this;
        }

        public Builder setImageUrl(@Nullable Uri uri) {
            this.imageUrl = uri;
            return this;
        }

        public Builder setUserGenerated(boolean z) {
            this.userGenerated = z;
            return this;
        }

        public Builder setCaption(@Nullable String str) {
            this.caption = str;
            return this;
        }

        /* access modifiers changed from: package-private */
        public Uri getImageUrl() {
            return this.imageUrl;
        }

        /* access modifiers changed from: package-private */
        public Bitmap getBitmap() {
            return this.bitmap;
        }

        public SharePhoto build() {
            return new SharePhoto(this);
        }

        public Builder readFrom(SharePhoto sharePhoto) {
            if (sharePhoto == null) {
                return this;
            }
            return ((Builder) super.readFrom(sharePhoto)).setBitmap(sharePhoto.getBitmap()).setImageUrl(sharePhoto.getImageUrl()).setUserGenerated(sharePhoto.getUserGenerated()).setCaption(sharePhoto.getCaption());
        }

        /* access modifiers changed from: package-private */
        public Builder readFrom(Parcel parcel) {
            return readFrom((SharePhoto) parcel.readParcelable(SharePhoto.class.getClassLoader()));
        }

        static void writePhotoListTo(Parcel parcel, int i, List<SharePhoto> list) {
            ShareMedia[] shareMediaArr = new ShareMedia[list.size()];
            for (int i2 = 0; i2 < list.size(); i2++) {
                shareMediaArr[i2] = list.get(i2);
            }
            parcel.writeParcelableArray(shareMediaArr, i);
        }

        static List<SharePhoto> readPhotoListFrom(Parcel parcel) {
            List<ShareMedia> readListFrom = readListFrom(parcel);
            ArrayList arrayList = new ArrayList();
            for (ShareMedia next : readListFrom) {
                if (next instanceof SharePhoto) {
                    arrayList.add((SharePhoto) next);
                }
            }
            return arrayList;
        }
    }
}
