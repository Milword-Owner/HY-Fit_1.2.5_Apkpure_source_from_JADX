package com.previewlibrary.enitity;

import android.graphics.Rect;
import android.os.Parcelable;
import androidx.annotation.Nullable;

public interface IThumbViewInfo extends Parcelable {
    Rect getBounds();

    String getUrl();

    @Nullable
    String getVideoUrl();
}
