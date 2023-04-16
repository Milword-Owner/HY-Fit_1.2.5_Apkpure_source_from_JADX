package com.huayu.tzc.p014ui.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Parcelable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;
import com.huayu.tzc.C2128R;
import com.huayu.tzc.adapter.MsgAdapter;
import com.huayu.tzc.base.BaseBean;
import com.huayu.tzc.base.BaseFragment;
import com.huayu.tzc.base.BaseListBean;
import com.huayu.tzc.bean.MsgBean;
import com.huayu.tzc.bean.MsgHistory;
import com.huayu.tzc.bean.User;
import com.huayu.tzc.contract.MainContract;
import com.huayu.tzc.p021im.JWebSocketClientService;
import com.huayu.tzc.presenter.MsgPresenter;
import com.huayu.tzc.utils.EventBusUtils;
import com.huayu.tzc.utils.GlideEngine;
import com.huayu.tzc.utils.UploadImg;
import com.huayu.tzc.utils.Utils;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.tencent.mmkv.MMKV;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.internal.entity.CaptureStrategy;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.zibin.luban.Luban;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u00020\u0002:\u0001;B\u0005¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\b\u0010\u001b\u001a\u00020\u0018H\u0002J\b\u0010\u001c\u001a\u00020\rH\u0014J\u0016\u0010\u001d\u001a\u00020\u00182\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020 0\u001fH\u0016J\u0016\u0010!\u001a\u00020\u00182\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020 0\u001fH\u0016J\b\u0010\"\u001a\u00020\u0003H\u0014J\b\u0010#\u001a\u00020\u0018H\u0002J\b\u0010$\u001a\u00020\u0018H\u0014J\b\u0010%\u001a\u00020\u0018H\u0002J\"\u0010&\u001a\u00020\u00182\u0006\u0010'\u001a\u00020\r2\u0006\u0010(\u001a\u00020\r2\b\u0010)\u001a\u0004\u0018\u00010*H\u0016J\b\u0010+\u001a\u00020\u0018H\u0016J\u0010\u0010,\u001a\u00020\u00182\u0006\u0010-\u001a\u00020\u0016H\u0007J\b\u0010.\u001a\u00020\u0018H\u0002J\u0010\u0010/\u001a\u00020\u00182\u0006\u00100\u001a\u00020\u000bH\u0002J\u0016\u00101\u001a\u00020\u00182\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u000b02H\u0016J\u000e\u00101\u001a\u00020\u00182\u0006\u00103\u001a\u00020\u000bJ\u0012\u00104\u001a\u00020\u00182\b\u00105\u001a\u0004\u0018\u000106H\u0016J\b\u00107\u001a\u00020\u0018H\u0002J\u0010\u00108\u001a\u00020\u00182\u0006\u00109\u001a\u00020:H\u0002R\u0012\u0010\u0005\u001a\u00060\u0006R\u00020\u0007X.¢\u0006\u0002\n\u0000R\u0012\u0010\b\u001a\u00060\tR\u00020\u0000X.¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX.¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\rX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\rX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X.¢\u0006\u0002\n\u0000¨\u0006<"}, mo21895d2 = {"Lcom/huayu/tzc/ui/fragment/MsgFragment;", "Lcom/huayu/tzc/base/BaseFragment;", "Lcom/huayu/tzc/contract/MainContract$MsgView;", "Lcom/huayu/tzc/presenter/MsgPresenter;", "()V", "binder", "Lcom/huayu/tzc/im/JWebSocketClientService$JWebSocketClientBinder;", "Lcom/huayu/tzc/im/JWebSocketClientService;", "chatMessageReceiver", "Lcom/huayu/tzc/ui/fragment/MsgFragment$ChatMessageReceiver;", "img", "", "maxChatId", "", "msgAdapter", "Lcom/huayu/tzc/adapter/MsgAdapter;", "msgList", "", "Lcom/huayu/tzc/bean/MsgBean;", "pageNumber", "pageSize", "user", "Lcom/huayu/tzc/bean/User;", "compressImg", "", "uri", "Landroid/net/Uri;", "doRegisterReceiver", "getLayoutId", "getMsgHistory", "baseBean", "Lcom/huayu/tzc/base/BaseListBean;", "Lcom/huayu/tzc/bean/MsgHistory;", "getNewMsg", "getPresenter", "initAdapter", "initView", "loadMore", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onDestroy", "onMessageEventMain", "event", "requestImgPermission", "sendImg", "imgUrl", "sendMsg", "Lcom/huayu/tzc/base/BaseBean;", "content", "showError", "e", "", "showPicturePicker", "uploadImg", "file", "Ljava/io/File;", "ChatMessageReceiver", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
/* renamed from: com.huayu.tzc.ui.fragment.MsgFragment */
/* compiled from: MsgFragment.kt */
public final class MsgFragment extends BaseFragment<MainContract.MsgView, MsgPresenter> implements MainContract.MsgView {
    private HashMap _$_findViewCache;
    private JWebSocketClientService.JWebSocketClientBinder binder;
    private ChatMessageReceiver chatMessageReceiver;
    /* access modifiers changed from: private */
    public String img = "";
    /* access modifiers changed from: private */
    public int maxChatId;
    private MsgAdapter msgAdapter;
    /* access modifiers changed from: private */
    public List<MsgBean> msgList = new ArrayList();
    private int pageNumber = 1;
    private int pageSize = 10;
    /* access modifiers changed from: private */
    public User user;

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View view2 = getView();
        if (view2 == null) {
            return null;
        }
        View findViewById = view2.findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return C2128R.C2133layout.fragment_msg;
    }

    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public static final /* synthetic */ MsgPresenter access$getMPresenter$p(MsgFragment msgFragment) {
        return (MsgPresenter) msgFragment.getMPresenter();
    }

    public static final /* synthetic */ User access$getUser$p(MsgFragment msgFragment) {
        User user2 = msgFragment.user;
        if (user2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("user");
        }
        return user2;
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public final void onMessageEventMain(@NotNull User user2) {
        Intrinsics.checkParameterIsNotNull(user2, NotificationCompat.CATEGORY_EVENT);
        this.user = user2;
        MsgAdapter msgAdapter2 = this.msgAdapter;
        if (msgAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("msgAdapter");
        }
        User user3 = this.user;
        if (user3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("user");
        }
        msgAdapter2.setUserBean(user3);
        MsgAdapter msgAdapter3 = this.msgAdapter;
        if (msgAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("msgAdapter");
        }
        msgAdapter3.notifyDataSetChanged();
    }

    @Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, mo21895d2 = {"Lcom/huayu/tzc/ui/fragment/MsgFragment$ChatMessageReceiver;", "Landroid/content/BroadcastReceiver;", "(Lcom/huayu/tzc/ui/fragment/MsgFragment;)V", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
    /* renamed from: com.huayu.tzc.ui.fragment.MsgFragment$ChatMessageReceiver */
    /* compiled from: MsgFragment.kt */
    public final class ChatMessageReceiver extends BroadcastReceiver {
        public ChatMessageReceiver() {
        }

        public void onReceive(@NotNull Context context, @NotNull Intent intent) {
            MsgPresenter access$getMPresenter$p;
            Intrinsics.checkParameterIsNotNull(context, "context");
            Intrinsics.checkParameterIsNotNull(intent, "intent");
            if (MsgFragment.this.maxChatId != 0 && (access$getMPresenter$p = MsgFragment.access$getMPresenter$p(MsgFragment.this)) != null) {
                access$getMPresenter$p.getNewMsg(MsgFragment.this.maxChatId);
            }
        }
    }

    public void onDestroy() {
        super.onDestroy();
        Context context = getContext();
        if (context == null) {
            Intrinsics.throwNpe();
        }
        ChatMessageReceiver chatMessageReceiver2 = this.chatMessageReceiver;
        if (chatMessageReceiver2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("chatMessageReceiver");
        }
        context.unregisterReceiver(chatMessageReceiver2);
        EventBusUtils.unregister(this);
    }

    /* access modifiers changed from: protected */
    public void initView() {
        Parcelable decodeParcelable = MMKV.defaultMMKV().decodeParcelable("user", User.class, new User());
        Intrinsics.checkExpressionValueIsNotNull(decodeParcelable, "MMKV.defaultMMKV().decod…,User::class.java,User())");
        this.user = (User) decodeParcelable;
        initAdapter();
        doRegisterReceiver();
        MsgPresenter msgPresenter = (MsgPresenter) getMPresenter();
        if (msgPresenter != null) {
            msgPresenter.getMsgHistory(this.pageNumber, this.pageSize);
        }
        ((SmartRefreshLayout) _$_findCachedViewById(C2128R.C2131id.msg_smart)).setEnableLoadMore(false);
        ((SmartRefreshLayout) _$_findCachedViewById(C2128R.C2131id.msg_smart)).setOnRefreshListener(new MsgFragment$initView$1(this));
        EventBusUtils.register(this);
        ImageView imageView = (ImageView) _$_findCachedViewById(C2128R.C2131id.msg_pic);
        imageView.setOnClickListener(new MsgFragment$initView$$inlined$singleClick$1(imageView, 800, this));
        TextView textView = (TextView) _$_findCachedViewById(C2128R.C2131id.msg_send);
        textView.setOnClickListener(new MsgFragment$initView$$inlined$singleClick$2(textView, 800, this));
    }

    /* access modifiers changed from: private */
    public final void loadMore() {
        this.pageNumber++;
        MsgPresenter msgPresenter = (MsgPresenter) getMPresenter();
        if (msgPresenter != null) {
            msgPresenter.getMsgHistory(this.pageNumber, this.pageSize);
        }
    }

    private final void initAdapter() {
        List<MsgBean> list = this.msgList;
        User user2 = this.user;
        if (user2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("user");
        }
        this.msgAdapter = new MsgAdapter(list, user2);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setStackFromEnd(true);
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(C2128R.C2131id.msg_recyclerview);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView, "msg_recyclerview");
        recyclerView.setLayoutManager(linearLayoutManager);
        RecyclerView recyclerView2 = (RecyclerView) _$_findCachedViewById(C2128R.C2131id.msg_recyclerview);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView2, "msg_recyclerview");
        MsgAdapter msgAdapter2 = this.msgAdapter;
        if (msgAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("msgAdapter");
        }
        recyclerView2.setAdapter(msgAdapter2);
        MsgAdapter msgAdapter3 = this.msgAdapter;
        if (msgAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("msgAdapter");
        }
        msgAdapter3.setOnItemChildClickListener(new MsgFragment$initAdapter$1(this));
    }

    /* access modifiers changed from: private */
    public final void requestImgPermission() {
        XXPermissions.with((Fragment) this).permission(Permission.WRITE_EXTERNAL_STORAGE).permission(Permission.READ_EXTERNAL_STORAGE).permission(Permission.CAMERA).request(new MsgFragment$requestImgPermission$1(this));
    }

    /* access modifiers changed from: private */
    public final void showPicturePicker() {
        Matisse.from((Fragment) this).choose(MimeType.ofImage()).countable(false).restrictOrientation(-1).thumbnailScale(0.85f).capture(true).captureStrategy(new CaptureStrategy(true, "com.huayu.tzc.provider")).imageEngine(new GlideEngine()).showSingleMediaType(true).maxSelectable(1).theme(2131820787).forResult(600);
    }

    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1 && i == 600) {
            List<Uri> obtainResult = Matisse.obtainResult(intent);
            List<MsgBean> list = this.msgList;
            User user2 = this.user;
            if (user2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("user");
            }
            list.add(new MsgBean(user2.getU_id(), obtainResult.get(0).toString(), 1, 2));
            MsgAdapter msgAdapter2 = this.msgAdapter;
            if (msgAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("msgAdapter");
            }
            msgAdapter2.notifyDataSetChanged();
            RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(C2128R.C2131id.msg_recyclerview);
            MsgAdapter msgAdapter3 = this.msgAdapter;
            if (msgAdapter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("msgAdapter");
            }
            recyclerView.scrollToPosition(msgAdapter3.getItemCount() - 1);
            Uri uri = obtainResult.get(0);
            Intrinsics.checkExpressionValueIsNotNull(uri, "pathList[0]");
            compressImg(uri);
        }
    }

    private final void compressImg(Uri uri) {
        Luban.with(getContext()).load(Utils.getRealFilePath(getContext(), uri)).ignoreBy(2000).setCompressListener(new MsgFragment$compressImg$1(this)).launch();
    }

    /* access modifiers changed from: private */
    public final void uploadImg(File file) {
        Context context = getContext();
        String name = file.getName();
        User user2 = this.user;
        if (user2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("user");
        }
        String key = UploadImg.getKey(context, name, user2.getU_id());
        Intrinsics.checkExpressionValueIsNotNull(key, "UploadImg.getKey(context, file.name, user.u_id)");
        new Thread(new MsgFragment$uploadImg$1(this, key, file)).start();
    }

    public final void sendMsg(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "content");
        FormBody.Builder add = new FormBody.Builder().add("app_type", String.valueOf(2)).add("content", str).add("flag", String.valueOf(true)).add("msgtype", String.valueOf(1)).add("receive", "1-kf");
        StringBuilder sb = new StringBuilder();
        sb.append("0-");
        User user2 = this.user;
        if (user2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("user");
        }
        sb.append(user2.getEmail());
        FormBody build = add.add("sender", sb.toString()).build();
        Intrinsics.checkExpressionValueIsNotNull(build, "FormBody.Builder()\n     …l}\")\n            .build()");
        RequestBody requestBody = build;
        MsgPresenter msgPresenter = (MsgPresenter) getMPresenter();
        if (msgPresenter != null) {
            msgPresenter.sendMsg(requestBody);
        }
        List<MsgBean> list = this.msgList;
        User user3 = this.user;
        if (user3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("user");
        }
        list.add(new MsgBean(user3.getU_id(), str, 1, 1));
        MsgAdapter msgAdapter2 = this.msgAdapter;
        if (msgAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("msgAdapter");
        }
        msgAdapter2.notifyDataSetChanged();
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(C2128R.C2131id.msg_recyclerview);
        MsgAdapter msgAdapter3 = this.msgAdapter;
        if (msgAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("msgAdapter");
        }
        recyclerView.scrollToPosition(msgAdapter3.getItemCount() - 1);
        ((EditText) _$_findCachedViewById(C2128R.C2131id.msg_edit)).setText("");
    }

    /* access modifiers changed from: private */
    public final void sendImg(String str) {
        ((RecyclerView) _$_findCachedViewById(C2128R.C2131id.msg_recyclerview)).post(new MsgFragment$sendImg$1(this, str));
    }

    private final void doRegisterReceiver() {
        this.chatMessageReceiver = new ChatMessageReceiver();
        IntentFilter intentFilter = new IntentFilter("com.huayu.tzc.im");
        Context context = getContext();
        if (context == null) {
            Intrinsics.throwNpe();
        }
        ChatMessageReceiver chatMessageReceiver2 = this.chatMessageReceiver;
        if (chatMessageReceiver2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("chatMessageReceiver");
        }
        context.registerReceiver(chatMessageReceiver2, intentFilter);
    }

    /* access modifiers changed from: protected */
    @NotNull
    public MsgPresenter getPresenter() {
        return new MsgPresenter();
    }

    public void getMsgHistory(@NotNull BaseListBean<MsgHistory> baseListBean) {
        Intrinsics.checkParameterIsNotNull(baseListBean, "baseBean");
        progressDissmiss();
        if (!baseListBean.getRows().isEmpty()) {
            Collections.reverse(baseListBean.getRows());
            List arrayList = new ArrayList();
            for (MsgHistory next : baseListBean.getRows()) {
                arrayList.add(new MsgBean(next.getContent(), StringsKt.contains$default((CharSequence) next.getSender(), (CharSequence) "1-", false, 2, (Object) null) ^ true ? 1 : 0, next.getMsgtype(), next.getChatid()));
            }
            this.msgList.addAll(0, arrayList);
            List<MsgBean> list = this.msgList;
            this.maxChatId = list.get(CollectionsKt.getLastIndex(list)).getChatid();
            MsgAdapter msgAdapter2 = this.msgAdapter;
            if (msgAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("msgAdapter");
            }
            msgAdapter2.notifyDataSetChanged();
            ((RecyclerView) _$_findCachedViewById(C2128R.C2131id.msg_recyclerview)).smoothScrollToPosition(CollectionsKt.getLastIndex(arrayList));
        }
        if (baseListBean.getRows().size() < this.pageSize) {
            ((SmartRefreshLayout) _$_findCachedViewById(C2128R.C2131id.msg_smart)).setOnRefreshListener(MsgFragment$getMsgHistory$1.INSTANCE);
        }
    }

    public void getNewMsg(@NotNull BaseListBean<MsgHistory> baseListBean) {
        Intrinsics.checkParameterIsNotNull(baseListBean, "baseBean");
        progressDissmiss();
        if (!baseListBean.getRows().isEmpty()) {
            Collections.reverse(baseListBean.getRows());
            List arrayList = new ArrayList();
            for (MsgHistory next : baseListBean.getRows()) {
                arrayList.add(new MsgBean(next.getContent(), StringsKt.contains$default((CharSequence) next.getSender(), (CharSequence) "1-", false, 2, (Object) null) ^ true ? 1 : 0, next.getMsgtype(), next.getChatid()));
            }
            for (int size = this.msgList.size() - 1; size >= 0; size--) {
                if (this.msgList.get(size).getChatid() == -1) {
                    this.msgList.remove(size);
                }
            }
            this.msgList.addAll(arrayList);
            List<MsgBean> list = this.msgList;
            this.maxChatId = list.get(CollectionsKt.getLastIndex(list)).getChatid();
            MsgAdapter msgAdapter2 = this.msgAdapter;
            if (msgAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("msgAdapter");
            }
            msgAdapter2.notifyDataSetChanged();
            RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(C2128R.C2131id.msg_recyclerview);
            MsgAdapter msgAdapter3 = this.msgAdapter;
            if (msgAdapter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("msgAdapter");
            }
            recyclerView.smoothScrollToPosition(msgAdapter3.getItemCount() - 1);
        }
    }

    public void sendMsg(@NotNull BaseBean<String> baseBean) {
        MsgPresenter msgPresenter;
        Intrinsics.checkParameterIsNotNull(baseBean, "baseBean");
        progressDissmiss();
        if (baseBean.getMeta().getSuccess() && this.maxChatId != 0 && (msgPresenter = (MsgPresenter) getMPresenter()) != null) {
            msgPresenter.getNewMsg(this.maxChatId);
        }
    }

    public void showError(@Nullable Throwable th) {
        progressDissmiss();
    }
}
