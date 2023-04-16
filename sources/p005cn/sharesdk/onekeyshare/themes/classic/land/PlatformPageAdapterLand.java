package p005cn.sharesdk.onekeyshare.themes.classic.land;

import android.content.Context;
import com.mob.tools.utils.ResHelper;
import java.lang.reflect.Array;
import java.util.ArrayList;
import p005cn.sharesdk.onekeyshare.themes.classic.PlatformPage;
import p005cn.sharesdk.onekeyshare.themes.classic.PlatformPageAdapter;

/* renamed from: cn.sharesdk.onekeyshare.themes.classic.land.PlatformPageAdapterLand */
public class PlatformPageAdapterLand extends PlatformPageAdapter {
    private static final int DESIGN_CELL_WIDTH_L = 160;
    private static final int DESIGN_LOGO_HEIGHT = 76;
    private static final int DESIGN_PADDING_TOP = 20;
    private static final int DESIGN_SCREEN_WIDTH_L = 1280;
    private static final int DESIGN_SEP_LINE_WIDTH = 1;

    public PlatformPageAdapterLand(PlatformPage platformPage, ArrayList<Object> arrayList) {
        super(platformPage, arrayList);
    }

    /* access modifiers changed from: protected */
    public void calculateSize(Context context, ArrayList<Object> arrayList) {
        int screenWidth = ResHelper.getScreenWidth(context);
        float f = ((float) screenWidth) / 1280.0f;
        this.lineSize = screenWidth / ((int) (160.0f * f));
        this.sepLineWidth = (int) (1.0f * f);
        this.sepLineWidth = this.sepLineWidth < 1 ? 1 : this.sepLineWidth;
        this.logoHeight = (int) (76.0f * f);
        this.paddingTop = (int) (20.0f * f);
        this.bottomHeight = (int) (f * 52.0f);
        this.cellHeight = (screenWidth - (this.sepLineWidth * 3)) / (this.lineSize - 1);
        this.panelHeight = this.cellHeight + this.sepLineWidth;
    }

    /* access modifiers changed from: protected */
    public void collectCells(ArrayList<Object> arrayList) {
        int size = arrayList.size();
        if (size < this.lineSize) {
            int i = size / this.lineSize;
            if (size % this.lineSize != 0) {
                i++;
            }
            this.cells = (Object[][]) Array.newInstance(Object.class, new int[]{1, i * this.lineSize});
        } else {
            int i2 = size / this.lineSize;
            if (size % this.lineSize != 0) {
                i2++;
            }
            this.cells = (Object[][]) Array.newInstance(Object.class, new int[]{i2, this.lineSize});
        }
        for (int i3 = 0; i3 < size; i3++) {
            int i4 = i3 / this.lineSize;
            this.cells[i4][i3 - (this.lineSize * i4)] = arrayList.get(i3);
        }
    }
}
