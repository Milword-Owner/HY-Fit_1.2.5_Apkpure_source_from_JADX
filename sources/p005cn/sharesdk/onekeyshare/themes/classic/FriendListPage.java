package p005cn.sharesdk.onekeyshare.themes.classic;

import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.mob.tools.gui.PullToRequestView;
import com.mob.tools.utils.ResHelper;
import java.util.ArrayList;
import java.util.HashMap;
import p005cn.sharesdk.framework.Platform;
import p005cn.sharesdk.onekeyshare.OnekeySharePage;
import p005cn.sharesdk.onekeyshare.OnekeyShareThemeImpl;
import p005cn.sharesdk.onekeyshare.themes.classic.FriendAdapter;

/* renamed from: cn.sharesdk.onekeyshare.themes.classic.FriendListPage */
public abstract class FriendListPage extends OnekeySharePage implements View.OnClickListener, AdapterView.OnItemClickListener {
    private static final int DESIGN_LEFT_PADDING = 40;
    private FriendAdapter adapter;
    private int checkNum = 0;
    private int lastPosition = -1;
    private LinearLayout llPage;
    private Platform platform;
    private RelativeLayout rlTitle;
    private TextView tvCancel;
    private TextView tvConfirm;

    /* access modifiers changed from: protected */
    public abstract int getDesignTitleHeight();

    /* access modifiers changed from: protected */
    public abstract float getRatio();

    public FriendListPage(OnekeyShareThemeImpl onekeyShareThemeImpl) {
        super(onekeyShareThemeImpl);
    }

    public void setPlatform(Platform platform2) {
        this.platform = platform2;
    }

    public void onCreate() {
        this.activity.getWindow().setBackgroundDrawable(new ColorDrawable(-789517));
        this.llPage = new LinearLayout(this.activity);
        this.llPage.setOrientation(1);
        this.activity.setContentView(this.llPage);
        this.rlTitle = new RelativeLayout(this.activity);
        float ratio = getRatio();
        this.llPage.addView(this.rlTitle, new LinearLayout.LayoutParams(-1, (int) (((float) getDesignTitleHeight()) * ratio)));
        initTitle(this.rlTitle, ratio);
        View view = new View(this.activity);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, (int) (ratio < 1.0f ? 1.0f : ratio));
        view.setBackgroundColor(-2434599);
        this.llPage.addView(view, layoutParams);
        FrameLayout frameLayout = new FrameLayout(getContext());
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams2.weight = 1.0f;
        frameLayout.setLayoutParams(layoutParams2);
        this.llPage.addView(frameLayout);
        PullToRequestView pullToRequestView = new PullToRequestView(getContext());
        pullToRequestView.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        frameLayout.addView(pullToRequestView);
        this.adapter = new FriendAdapter(this, pullToRequestView);
        this.adapter.setPlatform(this.platform);
        this.adapter.setRatio(ratio);
        this.adapter.setOnItemClickListener(this);
        pullToRequestView.setAdapter(this.adapter);
        pullToRequestView.performPullingDown(true);
    }

    private void initTitle(RelativeLayout relativeLayout, float f) {
        this.tvCancel = new TextView(this.activity);
        this.tvCancel.setTextColor(-12895429);
        this.tvCancel.setTextSize(2, 18.0f);
        this.tvCancel.setGravity(17);
        int stringRes = ResHelper.getStringRes(this.activity, "ssdk_oks_cancel");
        if (stringRes > 0) {
            this.tvCancel.setText(stringRes);
        }
        int i = (int) (f * 40.0f);
        this.tvCancel.setPadding(i, 0, i, 0);
        relativeLayout.addView(this.tvCancel, new RelativeLayout.LayoutParams(-2, -1));
        this.tvCancel.setOnClickListener(this);
        TextView textView = new TextView(this.activity);
        textView.setTextColor(-12895429);
        textView.setTextSize(2, 22.0f);
        textView.setGravity(17);
        int stringRes2 = ResHelper.getStringRes(this.activity, "ssdk_oks_contacts");
        if (stringRes2 > 0) {
            textView.setText(stringRes2);
        }
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -1);
        layoutParams.addRule(13);
        relativeLayout.addView(textView, layoutParams);
        this.tvConfirm = new TextView(this.activity);
        this.tvConfirm.setTextColor(-37615);
        this.tvConfirm.setTextSize(2, 18.0f);
        this.tvConfirm.setGravity(17);
        int stringRes3 = ResHelper.getStringRes(this.activity, "ssdk_oks_confirm");
        if (stringRes3 > 0) {
            this.tvConfirm.setText(stringRes3);
        }
        this.tvConfirm.setPadding(i, 0, i, 0);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -1);
        layoutParams2.addRule(11);
        relativeLayout.addView(this.tvConfirm, layoutParams2);
        this.tvConfirm.setOnClickListener(this);
    }

    public void onClick(View view) {
        if (view.equals(this.tvCancel)) {
            finish();
            return;
        }
        ArrayList arrayList = new ArrayList();
        int count = this.adapter.getCount();
        for (int i = 0; i < count; i++) {
            if (this.adapter.getItem(i).checked) {
                arrayList.add(this.adapter.getItem(i).atName);
            }
        }
        HashMap hashMap = new HashMap();
        hashMap.put("selected", arrayList);
        hashMap.put("platform", this.platform);
        setResult(hashMap);
        finish();
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        if ("FacebookMessenger".equals(this.platform.getName())) {
            int i2 = this.lastPosition;
            if (i2 >= 0) {
                this.adapter.getItem(i2).checked = false;
            }
            this.lastPosition = i;
        }
        FriendAdapter.Following item = this.adapter.getItem(i);
        item.checked = !item.checked;
        if (item.checked) {
            this.checkNum++;
        } else {
            this.checkNum--;
        }
        updateConfirmView();
        this.adapter.notifyDataSetChanged();
    }

    private void updateConfirmView() {
        int stringRes = ResHelper.getStringRes(this.activity, "ssdk_oks_confirm");
        String string = stringRes > 0 ? getContext().getResources().getString(stringRes) : "Confirm";
        int i = this.checkNum;
        if (i == 0) {
            this.tvConfirm.setText(string);
        } else if (i > 0) {
            TextView textView = this.tvConfirm;
            textView.setText(string + "(" + this.checkNum + ")");
        }
    }
}
