package com.bumptech.glide.request;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import androidx.annotation.CheckResult;
import androidx.annotation.DrawableRes;
import androidx.annotation.FloatRange;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.Option;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy;

public class RequestOptions extends BaseRequestOptions<RequestOptions> {
    @Nullable
    private static RequestOptions centerCropOptions;
    @Nullable
    private static RequestOptions centerInsideOptions;
    @Nullable
    private static RequestOptions circleCropOptions;
    @Nullable
    private static RequestOptions fitCenterOptions;
    @Nullable
    private static RequestOptions noAnimationOptions;
    @Nullable
    private static RequestOptions noTransformOptions;
    @Nullable
    private static RequestOptions skipMemoryCacheFalseOptions;
    @Nullable
    private static RequestOptions skipMemoryCacheTrueOptions;

    @CheckResult
    @NonNull
    public static RequestOptions sizeMultiplierOf(@FloatRange(from = 0.0d, mo652to = 1.0d) float f) {
        return (RequestOptions) new RequestOptions().sizeMultiplier(f);
    }

    @CheckResult
    @NonNull
    public static RequestOptions diskCacheStrategyOf(@NonNull DiskCacheStrategy diskCacheStrategy) {
        return (RequestOptions) new RequestOptions().diskCacheStrategy(diskCacheStrategy);
    }

    @CheckResult
    @NonNull
    public static RequestOptions priorityOf(@NonNull Priority priority) {
        return (RequestOptions) new RequestOptions().priority(priority);
    }

    @CheckResult
    @NonNull
    public static RequestOptions placeholderOf(@Nullable Drawable drawable) {
        return (RequestOptions) new RequestOptions().placeholder(drawable);
    }

    @CheckResult
    @NonNull
    public static RequestOptions placeholderOf(@DrawableRes int i) {
        return (RequestOptions) new RequestOptions().placeholder(i);
    }

    @CheckResult
    @NonNull
    public static RequestOptions errorOf(@Nullable Drawable drawable) {
        return (RequestOptions) new RequestOptions().error(drawable);
    }

    @CheckResult
    @NonNull
    public static RequestOptions errorOf(@DrawableRes int i) {
        return (RequestOptions) new RequestOptions().error(i);
    }

    @CheckResult
    @NonNull
    public static RequestOptions skipMemoryCacheOf(boolean z) {
        if (z) {
            if (skipMemoryCacheTrueOptions == null) {
                skipMemoryCacheTrueOptions = (RequestOptions) ((RequestOptions) new RequestOptions().skipMemoryCache(true)).autoClone();
            }
            return skipMemoryCacheTrueOptions;
        }
        if (skipMemoryCacheFalseOptions == null) {
            skipMemoryCacheFalseOptions = (RequestOptions) ((RequestOptions) new RequestOptions().skipMemoryCache(false)).autoClone();
        }
        return skipMemoryCacheFalseOptions;
    }

    @CheckResult
    @NonNull
    public static RequestOptions overrideOf(int i, int i2) {
        return (RequestOptions) new RequestOptions().override(i, i2);
    }

    @CheckResult
    @NonNull
    public static RequestOptions overrideOf(int i) {
        return overrideOf(i, i);
    }

    @CheckResult
    @NonNull
    public static RequestOptions signatureOf(@NonNull Key key) {
        return (RequestOptions) new RequestOptions().signature(key);
    }

    @CheckResult
    @NonNull
    public static RequestOptions fitCenterTransform() {
        if (fitCenterOptions == null) {
            fitCenterOptions = (RequestOptions) ((RequestOptions) new RequestOptions().fitCenter()).autoClone();
        }
        return fitCenterOptions;
    }

    @CheckResult
    @NonNull
    public static RequestOptions centerInsideTransform() {
        if (centerInsideOptions == null) {
            centerInsideOptions = (RequestOptions) ((RequestOptions) new RequestOptions().centerInside()).autoClone();
        }
        return centerInsideOptions;
    }

    @CheckResult
    @NonNull
    public static RequestOptions centerCropTransform() {
        if (centerCropOptions == null) {
            centerCropOptions = (RequestOptions) ((RequestOptions) new RequestOptions().centerCrop()).autoClone();
        }
        return centerCropOptions;
    }

    @CheckResult
    @NonNull
    public static RequestOptions circleCropTransform() {
        if (circleCropOptions == null) {
            circleCropOptions = (RequestOptions) ((RequestOptions) new RequestOptions().circleCrop()).autoClone();
        }
        return circleCropOptions;
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [com.bumptech.glide.load.Transformation, com.bumptech.glide.load.Transformation<android.graphics.Bitmap>] */
    /* JADX WARNING: Unknown variable types count: 1 */
    @androidx.annotation.CheckResult
    @androidx.annotation.NonNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.bumptech.glide.request.RequestOptions bitmapTransform(@androidx.annotation.NonNull com.bumptech.glide.load.Transformation<android.graphics.Bitmap> r1) {
        /*
            com.bumptech.glide.request.RequestOptions r0 = new com.bumptech.glide.request.RequestOptions
            r0.<init>()
            com.bumptech.glide.request.BaseRequestOptions r1 = r0.transform((com.bumptech.glide.load.Transformation<android.graphics.Bitmap>) r1)
            com.bumptech.glide.request.RequestOptions r1 = (com.bumptech.glide.request.RequestOptions) r1
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.request.RequestOptions.bitmapTransform(com.bumptech.glide.load.Transformation):com.bumptech.glide.request.RequestOptions");
    }

    @CheckResult
    @NonNull
    public static RequestOptions noTransformation() {
        if (noTransformOptions == null) {
            noTransformOptions = (RequestOptions) ((RequestOptions) new RequestOptions().dontTransform()).autoClone();
        }
        return noTransformOptions;
    }

    @CheckResult
    @NonNull
    public static <T> RequestOptions option(@NonNull Option<T> option, @NonNull T t) {
        return (RequestOptions) new RequestOptions().set(option, t);
    }

    @CheckResult
    @NonNull
    public static RequestOptions decodeTypeOf(@NonNull Class<?> cls) {
        return (RequestOptions) new RequestOptions().decode(cls);
    }

    @CheckResult
    @NonNull
    public static RequestOptions formatOf(@NonNull DecodeFormat decodeFormat) {
        return (RequestOptions) new RequestOptions().format(decodeFormat);
    }

    @CheckResult
    @NonNull
    public static RequestOptions frameOf(@IntRange(from = 0) long j) {
        return (RequestOptions) new RequestOptions().frame(j);
    }

    @CheckResult
    @NonNull
    public static RequestOptions downsampleOf(@NonNull DownsampleStrategy downsampleStrategy) {
        return (RequestOptions) new RequestOptions().downsample(downsampleStrategy);
    }

    @CheckResult
    @NonNull
    public static RequestOptions timeoutOf(@IntRange(from = 0) int i) {
        return (RequestOptions) new RequestOptions().timeout(i);
    }

    @CheckResult
    @NonNull
    public static RequestOptions encodeQualityOf(@IntRange(from = 0, mo670to = 100) int i) {
        return (RequestOptions) new RequestOptions().encodeQuality(i);
    }

    @CheckResult
    @NonNull
    public static RequestOptions encodeFormatOf(@NonNull Bitmap.CompressFormat compressFormat) {
        return (RequestOptions) new RequestOptions().encodeFormat(compressFormat);
    }

    @CheckResult
    @NonNull
    public static RequestOptions noAnimation() {
        if (noAnimationOptions == null) {
            noAnimationOptions = (RequestOptions) ((RequestOptions) new RequestOptions().dontAnimate()).autoClone();
        }
        return noAnimationOptions;
    }
}
