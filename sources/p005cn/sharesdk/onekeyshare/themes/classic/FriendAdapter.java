package p005cn.sharesdk.onekeyshare.themes.classic;

import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import com.baidu.mobstat.Config;
import com.facebook.appevents.internal.ViewHierarchyConstants;
import com.facebook.share.internal.ShareConstants;
import com.mob.tools.gui.PullToRequestListAdapter;
import com.mob.tools.gui.PullToRequestView;
import com.mob.tools.utils.UIHandler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import p005cn.sharesdk.facebook.Facebook;
import p005cn.sharesdk.framework.Platform;
import p005cn.sharesdk.framework.PlatformActionListener;

/* renamed from: cn.sharesdk.onekeyshare.themes.classic.FriendAdapter */
public class FriendAdapter extends PullToRequestListAdapter implements PlatformActionListener {
    /* access modifiers changed from: private */
    public FriendListPage activity;
    /* access modifiers changed from: private */
    public int curPage;
    /* access modifiers changed from: private */
    public ArrayList<Following> follows;
    private boolean hasNext;
    private PRTHeader llHeader;
    private HashMap<String, Boolean> map;
    private final int pageCount = 15;
    private Platform platform;
    private float ratio;

    /* renamed from: cn.sharesdk.onekeyshare.themes.classic.FriendAdapter$Following */
    public static class Following {
        public String atName;
        public boolean checked;
        public String description;
        public String icon;
        public String screenName;
        public String uid;
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public FriendAdapter(FriendListPage friendListPage, PullToRequestView pullToRequestView) {
        super(pullToRequestView);
        this.activity = friendListPage;
        this.curPage = -1;
        this.hasNext = true;
        this.map = new HashMap<>();
        this.follows = new ArrayList<>();
        getListView().setDivider(new ColorDrawable(-1381654));
    }

    public void setRatio(float f) {
        this.ratio = f;
        ListView listView = getListView();
        if (f < 1.0f) {
            f = 1.0f;
        }
        listView.setDividerHeight((int) f);
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        getListView().setOnItemClickListener(onItemClickListener);
    }

    public void setPlatform(Platform platform2) {
        this.platform = platform2;
        platform2.setPlatformActionListener(this);
    }

    private void next() {
        if (this.hasNext) {
            this.platform.listFriend(15, this.curPage + 1, (String) null);
        }
    }

    public void onComplete(Platform platform2, int i, HashMap<String, Object> hashMap) {
        final FollowersResult parseFollowers = parseFollowers(this.platform.getName(), hashMap, this.map);
        if (parseFollowers == null) {
            UIHandler.sendEmptyMessage(0, new Handler.Callback() {
                public boolean handleMessage(Message message) {
                    FriendAdapter.this.notifyDataSetChanged();
                    return false;
                }
            });
            return;
        }
        this.hasNext = parseFollowers.hasNextPage;
        if (parseFollowers.list != null && parseFollowers.list.size() > 0) {
            this.curPage++;
            Message message = new Message();
            message.what = 1;
            message.obj = parseFollowers.list;
            UIHandler.sendMessage(message, new Handler.Callback() {
                public boolean handleMessage(Message message) {
                    if (FriendAdapter.this.curPage <= 0) {
                        FriendAdapter.this.follows.clear();
                    }
                    FriendAdapter.this.follows.addAll(parseFollowers.list);
                    FriendAdapter.this.notifyDataSetChanged();
                    return false;
                }
            });
        }
    }

    private FollowersResult parseFollowers(String str, HashMap<String, Object> hashMap, HashMap<String, Boolean> hashMap2) {
        if (hashMap == null || hashMap.size() <= 0) {
            return null;
        }
        ArrayList<Following> arrayList = new ArrayList<>();
        boolean z = false;
        if ("SinaWeibo".equals(str)) {
            Iterator it = ((ArrayList) hashMap.get("users")).iterator();
            while (it.hasNext()) {
                HashMap hashMap3 = (HashMap) it.next();
                String valueOf = String.valueOf(hashMap3.get("id"));
                if (!hashMap2.containsKey(valueOf)) {
                    Following following = new Following();
                    following.uid = valueOf;
                    following.screenName = String.valueOf(hashMap3.get("name"));
                    following.description = String.valueOf(hashMap3.get("description"));
                    following.icon = String.valueOf(hashMap3.get("profile_image_url"));
                    following.atName = following.screenName;
                    hashMap2.put(following.uid, true);
                    arrayList.add(following);
                }
            }
            if (((Integer) hashMap.get("total_number")).intValue() > hashMap2.size()) {
                z = true;
            }
        } else if ("TencentWeibo".equals(str)) {
            if (((Integer) hashMap.get("hasnext")).intValue() == 0) {
                z = true;
            }
            Iterator it2 = ((ArrayList) hashMap.get(Config.LAUNCH_INFO)).iterator();
            while (it2.hasNext()) {
                HashMap hashMap4 = (HashMap) it2.next();
                String valueOf2 = String.valueOf(hashMap4.get("name"));
                if (!hashMap2.containsKey(valueOf2)) {
                    Following following2 = new Following();
                    following2.screenName = String.valueOf(hashMap4.get("nick"));
                    following2.uid = valueOf2;
                    following2.atName = valueOf2;
                    Iterator it3 = ((ArrayList) hashMap4.get("tweet")).iterator();
                    if (it3.hasNext()) {
                        following2.description = String.valueOf(((HashMap) it3.next()).get(ViewHierarchyConstants.TEXT_KEY));
                    }
                    following2.icon = String.valueOf(hashMap4.get("head")) + "/100";
                    hashMap2.put(following2.uid, true);
                    arrayList.add(following2);
                }
            }
        } else if (Facebook.NAME.equals(str)) {
            Iterator it4 = ((ArrayList) hashMap.get(ShareConstants.WEB_DIALOG_PARAM_DATA)).iterator();
            while (it4.hasNext()) {
                HashMap hashMap5 = (HashMap) it4.next();
                String valueOf3 = String.valueOf(hashMap5.get("id"));
                if (!hashMap2.containsKey(valueOf3)) {
                    Following following3 = new Following();
                    following3.uid = valueOf3;
                    following3.atName = "[" + valueOf3 + "]";
                    following3.screenName = String.valueOf(hashMap5.get("name"));
                    HashMap hashMap6 = (HashMap) hashMap5.get("picture");
                    if (hashMap6 != null) {
                        following3.icon = String.valueOf(((HashMap) hashMap6.get(ShareConstants.WEB_DIALOG_PARAM_DATA)).get("url"));
                    }
                    hashMap2.put(following3.uid, true);
                    arrayList.add(following3);
                }
            }
            z = ((HashMap) hashMap.get("paging")).containsKey("next");
        } else if ("Twitter".equals(str)) {
            Iterator it5 = ((ArrayList) hashMap.get("users")).iterator();
            while (it5.hasNext()) {
                HashMap hashMap7 = (HashMap) it5.next();
                String valueOf4 = String.valueOf(hashMap7.get("screen_name"));
                if (!hashMap2.containsKey(valueOf4)) {
                    Following following4 = new Following();
                    following4.uid = valueOf4;
                    following4.atName = valueOf4;
                    following4.screenName = String.valueOf(hashMap7.get("name"));
                    following4.description = String.valueOf(hashMap7.get("description"));
                    following4.icon = String.valueOf(hashMap7.get("profile_image_url"));
                    hashMap2.put(following4.uid, true);
                    arrayList.add(following4);
                }
            }
        }
        FollowersResult followersResult = new FollowersResult();
        followersResult.list = arrayList;
        followersResult.hasNextPage = z;
        return followersResult;
    }

    public void onError(Platform platform2, int i, Throwable th) {
        th.printStackTrace();
    }

    public void onCancel(Platform platform2, int i) {
        UIHandler.sendEmptyMessage(0, new Handler.Callback() {
            public boolean handleMessage(Message message) {
                FriendAdapter.this.activity.finish();
                return false;
            }
        });
    }

    public Following getItem(int i) {
        return this.follows.get(i);
    }

    public int getCount() {
        ArrayList<Following> arrayList = this.follows;
        if (arrayList == null) {
            return 0;
        }
        return arrayList.size();
    }

    public View getHeaderView() {
        if (this.llHeader == null) {
            this.llHeader = new PRTHeader(getContext());
        }
        return this.llHeader;
    }

    public void onPullDown(int i) {
        this.llHeader.onPullDown(i);
    }

    public void onRefresh() {
        this.llHeader.onRequest();
        this.curPage = -1;
        this.hasNext = true;
        this.map.clear();
        next();
    }

    public void onReversed() {
        this.llHeader.reverse();
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = new FriendListItem(viewGroup.getContext(), this.ratio);
        }
        ((FriendListItem) view).update(getItem(i), isFling());
        if (i == getCount() - 1) {
            next();
        }
        return view;
    }

    /* renamed from: cn.sharesdk.onekeyshare.themes.classic.FriendAdapter$FollowersResult */
    private static class FollowersResult {
        public boolean hasNextPage;
        public ArrayList<Following> list;

        private FollowersResult() {
            this.hasNextPage = false;
        }
    }

    public View getFooterView() {
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setMinimumHeight(10);
        return linearLayout;
    }
}
