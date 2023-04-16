package com.zhihu.matisse.internal.p034ui.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.zhihu.matisse.C2570R;
import com.zhihu.matisse.internal.entity.Album;
import com.zhihu.matisse.internal.entity.SelectionSpec;

/* renamed from: com.zhihu.matisse.internal.ui.adapter.AlbumsAdapter */
public class AlbumsAdapter extends CursorAdapter {
    private final Drawable mPlaceholder;

    public AlbumsAdapter(Context context, Cursor cursor, boolean z) {
        super(context, cursor, z);
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(new int[]{C2570R.attr.album_thumbnail_placeholder});
        this.mPlaceholder = obtainStyledAttributes.getDrawable(0);
        obtainStyledAttributes.recycle();
    }

    public AlbumsAdapter(Context context, Cursor cursor, int i) {
        super(context, cursor, i);
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(new int[]{C2570R.attr.album_thumbnail_placeholder});
        this.mPlaceholder = obtainStyledAttributes.getDrawable(0);
        obtainStyledAttributes.recycle();
    }

    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(C2570R.C2575layout.album_list_item, viewGroup, false);
    }

    public void bindView(View view, Context context, Cursor cursor) {
        Album valueOf = Album.valueOf(cursor);
        ((TextView) view.findViewById(C2570R.C2573id.album_name)).setText(valueOf.getDisplayName(context));
        ((TextView) view.findViewById(C2570R.C2573id.album_media_count)).setText(String.valueOf(valueOf.getCount()));
        SelectionSpec.getInstance().imageEngine.loadThumbnail(context, context.getResources().getDimensionPixelSize(C2570R.dimen.media_grid_size), this.mPlaceholder, (ImageView) view.findViewById(C2570R.C2573id.album_cover), valueOf.getCoverUri());
    }
}
