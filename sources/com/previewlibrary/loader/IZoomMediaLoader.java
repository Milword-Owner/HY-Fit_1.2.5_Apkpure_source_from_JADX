package com.previewlibrary.loader;

import android.content.Context;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public interface IZoomMediaLoader {
    void clearMemory(@NonNull Context context);

    void displayGifImage(@NonNull Fragment fragment, @NonNull String str, ImageView imageView, @NonNull MySimpleTarget mySimpleTarget);

    void displayImage(@NonNull Fragment fragment, @NonNull String str, ImageView imageView, @NonNull MySimpleTarget mySimpleTarget);

    void onStop(@NonNull Fragment fragment);
}
