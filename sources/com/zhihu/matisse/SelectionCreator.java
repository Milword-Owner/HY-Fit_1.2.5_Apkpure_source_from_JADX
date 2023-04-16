package com.zhihu.matisse;

import android.app.Activity;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;
import androidx.fragment.app.Fragment;
import com.zhihu.matisse.engine.ImageEngine;
import com.zhihu.matisse.filter.Filter;
import com.zhihu.matisse.internal.entity.CaptureStrategy;
import com.zhihu.matisse.internal.entity.SelectionSpec;
import com.zhihu.matisse.listener.OnCheckedListener;
import com.zhihu.matisse.listener.OnSelectedListener;
import com.zhihu.matisse.p035ui.MatisseActivity;
import java.util.ArrayList;
import java.util.Set;

public final class SelectionCreator {
    private final Matisse mMatisse;
    private final SelectionSpec mSelectionSpec = SelectionSpec.getCleanInstance();

    SelectionCreator(Matisse matisse, @NonNull Set<MimeType> set, boolean z) {
        this.mMatisse = matisse;
        SelectionSpec selectionSpec = this.mSelectionSpec;
        selectionSpec.mimeTypeSet = set;
        selectionSpec.mediaTypeExclusive = z;
        selectionSpec.orientation = -1;
    }

    public SelectionCreator showSingleMediaType(boolean z) {
        this.mSelectionSpec.showSingleMediaType = z;
        return this;
    }

    public SelectionCreator theme(@StyleRes int i) {
        this.mSelectionSpec.themeId = i;
        return this;
    }

    public SelectionCreator countable(boolean z) {
        this.mSelectionSpec.countable = z;
        return this;
    }

    public SelectionCreator maxSelectable(int i) {
        if (i < 1) {
            throw new IllegalArgumentException("maxSelectable must be greater than or equal to one");
        } else if (this.mSelectionSpec.maxImageSelectable > 0 || this.mSelectionSpec.maxVideoSelectable > 0) {
            throw new IllegalStateException("already set maxImageSelectable and maxVideoSelectable");
        } else {
            this.mSelectionSpec.maxSelectable = i;
            return this;
        }
    }

    public SelectionCreator maxSelectablePerMediaType(int i, int i2) {
        if (i < 1 || i2 < 1) {
            throw new IllegalArgumentException("max selectable must be greater than or equal to one");
        }
        SelectionSpec selectionSpec = this.mSelectionSpec;
        selectionSpec.maxSelectable = -1;
        selectionSpec.maxImageSelectable = i;
        selectionSpec.maxVideoSelectable = i2;
        return this;
    }

    public SelectionCreator addFilter(@NonNull Filter filter) {
        if (this.mSelectionSpec.filters == null) {
            this.mSelectionSpec.filters = new ArrayList();
        }
        if (filter != null) {
            this.mSelectionSpec.filters.add(filter);
            return this;
        }
        throw new IllegalArgumentException("filter cannot be null");
    }

    public SelectionCreator capture(boolean z) {
        this.mSelectionSpec.capture = z;
        return this;
    }

    public SelectionCreator originalEnable(boolean z) {
        this.mSelectionSpec.originalable = z;
        return this;
    }

    public SelectionCreator autoHideToolbarOnSingleTap(boolean z) {
        this.mSelectionSpec.autoHideToobar = z;
        return this;
    }

    public SelectionCreator maxOriginalSize(int i) {
        this.mSelectionSpec.originalMaxSize = i;
        return this;
    }

    public SelectionCreator captureStrategy(CaptureStrategy captureStrategy) {
        this.mSelectionSpec.captureStrategy = captureStrategy;
        return this;
    }

    public SelectionCreator restrictOrientation(int i) {
        this.mSelectionSpec.orientation = i;
        return this;
    }

    public SelectionCreator spanCount(int i) {
        if (i >= 1) {
            this.mSelectionSpec.spanCount = i;
            return this;
        }
        throw new IllegalArgumentException("spanCount cannot be less than 1");
    }

    public SelectionCreator gridExpectedSize(int i) {
        this.mSelectionSpec.gridExpectedSize = i;
        return this;
    }

    public SelectionCreator thumbnailScale(float f) {
        if (f <= 0.0f || f > 1.0f) {
            throw new IllegalArgumentException("Thumbnail scale must be between (0.0, 1.0]");
        }
        this.mSelectionSpec.thumbnailScale = f;
        return this;
    }

    public SelectionCreator imageEngine(ImageEngine imageEngine) {
        this.mSelectionSpec.imageEngine = imageEngine;
        return this;
    }

    @NonNull
    public SelectionCreator setOnSelectedListener(@Nullable OnSelectedListener onSelectedListener) {
        this.mSelectionSpec.onSelectedListener = onSelectedListener;
        return this;
    }

    public SelectionCreator setOnCheckedListener(@Nullable OnCheckedListener onCheckedListener) {
        this.mSelectionSpec.onCheckedListener = onCheckedListener;
        return this;
    }

    public void forResult(int i) {
        Activity activity = this.mMatisse.getActivity();
        if (activity != null) {
            Intent intent = new Intent(activity, MatisseActivity.class);
            Fragment fragment = this.mMatisse.getFragment();
            if (fragment != null) {
                fragment.startActivityForResult(intent, i);
            } else {
                activity.startActivityForResult(intent, i);
            }
        }
    }

    public SelectionCreator showPreview(boolean z) {
        this.mSelectionSpec.showPreview = z;
        return this;
    }
}
