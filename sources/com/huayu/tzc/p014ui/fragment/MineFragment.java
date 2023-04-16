package com.huayu.tzc.p014ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.blankj.utilcode.util.AppUtils;
import com.bumptech.glide.Glide;
import com.facebook.share.internal.ShareConstants;
import com.hjq.toast.ToastUtils;
import com.huayu.tzc.C2128R;
import com.huayu.tzc.adapter.MemberAdapter;
import com.huayu.tzc.base.BaseBean;
import com.huayu.tzc.base.BaseFragment;
import com.huayu.tzc.bean.Member;
import com.huayu.tzc.bean.User;
import com.huayu.tzc.contract.MainContract;
import com.huayu.tzc.customview.MyLinearLayout;
import com.huayu.tzc.p014ui.activity.UserInfoActivity;
import com.huayu.tzc.p014ui.activity.WebActivity;
import com.huayu.tzc.p014ui.activity.setting.AboutActivity;
import com.huayu.tzc.p014ui.activity.setting.AccountActivity;
import com.huayu.tzc.p014ui.activity.setting.FitBitActivity;
import com.huayu.tzc.p014ui.activity.setting.GoogleFitActivity;
import com.huayu.tzc.p014ui.activity.setting.LanguageActivity;
import com.huayu.tzc.p014ui.activity.setting.RemindActivity;
import com.huayu.tzc.p014ui.activity.setting.UnitActivity;
import com.huayu.tzc.p014ui.activity.setting.VipActivity;
import com.huayu.tzc.presenter.UserPresenter;
import com.huayu.tzc.utils.EventBusUtils;
import com.huayu.tzc.utils.SingleClickKt;
import com.huayu.tzc.utils.Utils;
import com.tencent.mmkv.MMKV;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import p036de.hdodenhof.circleimageview.CircleImageView;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0003\n\u0002\b\u0004\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u00020\u00022\u00020\u0004B\u0005¢\u0006\u0002\u0010\u0005J\u0016\u0010\r\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0016J\u0016\u0010\u0012\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0016J\b\u0010\u0013\u001a\u00020\u0014H\u0014J\u001c\u0010\u0015\u001a\u00020\u000e2\u0012\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00170\u0010H\u0016J\b\u0010\u0018\u001a\u00020\u0003H\u0014J\b\u0010\u0019\u001a\u00020\u000eH\u0002J\b\u0010\u001a\u001a\u00020\u000eH\u0002J\b\u0010\u001b\u001a\u00020\u000eH\u0014J\u0012\u0010\u001c\u001a\u00020\u000e2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J\b\u0010\u001f\u001a\u00020\u000eH\u0016J\u0010\u0010 \u001a\u00020\u000e2\u0006\u0010!\u001a\u00020\fH\u0007J\u0016\u0010\"\u001a\u00020\u000e2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020$0#H\u0007J,\u0010%\u001a\u00020\u000e2\u0006\u0010&\u001a\u00020'2\b\u0010(\u001a\u0004\u0018\u00010\u00112\b\u0010)\u001a\u0004\u0018\u00010\u00112\u0006\u0010*\u001a\u00020\u0011H\u0002J\u0012\u0010+\u001a\u00020\u000e2\b\u0010,\u001a\u0004\u0018\u00010-H\u0016J\u0016\u0010.\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0016J\b\u0010/\u001a\u00020\u000eH\u0002J\u0016\u00100\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X.¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000¨\u00061"}, mo21895d2 = {"Lcom/huayu/tzc/ui/fragment/MineFragment;", "Lcom/huayu/tzc/base/BaseFragment;", "Lcom/huayu/tzc/contract/MainContract$UserView;", "Lcom/huayu/tzc/presenter/UserPresenter;", "Landroid/view/View$OnClickListener;", "()V", "memberAdapter", "Lcom/huayu/tzc/adapter/MemberAdapter;", "memberList", "", "Lcom/huayu/tzc/bean/Member;", "user", "Lcom/huayu/tzc/bean/User;", "addMem", "", "baseBean", "Lcom/huayu/tzc/base/BaseBean;", "", "deleteMem", "getLayoutId", "", "getMineList", "loginBean", "", "getPresenter", "initAdapter", "initClick", "initView", "onClick", "p0", "Landroid/view/View;", "onDestroy", "onMessageEventMain", "event", "onMessageEventMainList", "Lcom/huayu/tzc/utils/EventBusUtils$EventMessage;", "", "sendEmail", "context", "Landroid/content/Context;", "title", "content", "address", "showError", "e", "", "updateMem", "updateUser", "updateUserInfo", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
/* renamed from: com.huayu.tzc.ui.fragment.MineFragment */
/* compiled from: MineFragment.kt */
public final class MineFragment extends BaseFragment<MainContract.UserView, UserPresenter> implements MainContract.UserView, View.OnClickListener {
    private HashMap _$_findViewCache;
    private MemberAdapter memberAdapter;
    /* access modifiers changed from: private */
    public final List<Member> memberList = new ArrayList();
    private User user = new User();

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
        return C2128R.C2133layout.fragment_mine;
    }

    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public final void onMessageEventMain(@NotNull User user2) {
        Intrinsics.checkParameterIsNotNull(user2, NotificationCompat.CATEGORY_EVENT);
        this.user = user2;
        updateUser();
    }

    private final void updateUser() {
        if (this.user.getU_avatar().length() > 0) {
            Intrinsics.checkExpressionValueIsNotNull(Glide.with((Fragment) this).load(this.user.getU_avatar()).into((ImageView) (CircleImageView) _$_findCachedViewById(C2128R.C2131id.minePic)), "Glide.with(this).load(user.u_avatar).into(minePic)");
        } else {
            ((CircleImageView) _$_findCachedViewById(C2128R.C2131id.minePic)).setImageResource(Utils.getImgRes(this.user.getU_gender()));
        }
        if (this.user.getU_level() == 0) {
            Glide.with((Fragment) this).load(Integer.valueOf(C2128R.C2130drawable.ic_vip_f)).into((ImageView) _$_findCachedViewById(C2128R.C2131id.mineVipPic));
            TextView textView = (TextView) _$_findCachedViewById(C2128R.C2131id.mineVipLevel);
            Intrinsics.checkExpressionValueIsNotNull(textView, "mineVipLevel");
            textView.setText(getString(C2128R.string.pt_vip));
        } else {
            TextView textView2 = (TextView) _$_findCachedViewById(C2128R.C2131id.mineVipLevel);
            Intrinsics.checkExpressionValueIsNotNull(textView2, "mineVipLevel");
            textView2.setText(getString(C2128R.string.gj_vip));
            Intrinsics.checkExpressionValueIsNotNull(Glide.with((Fragment) this).load(Integer.valueOf(C2128R.C2130drawable.ic_vip_m)).into((ImageView) _$_findCachedViewById(C2128R.C2131id.mineVipPic)), "Glide.with(this).load(R.…c_vip_m).into(mineVipPic)");
        }
        TextView textView3 = (TextView) _$_findCachedViewById(C2128R.C2131id.mineName);
        Intrinsics.checkExpressionValueIsNotNull(textView3, "mineName");
        textView3.setText(this.user.getU_nickname());
        TextView textView4 = (TextView) _$_findCachedViewById(C2128R.C2131id.mineTarget);
        Intrinsics.checkExpressionValueIsNotNull(textView4, "mineTarget");
        textView4.setText(getString(C2128R.string.target_title) + this.user.getU_weight() + this.user.getWeightunit());
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public final void onMessageEventMainList(@NotNull EventBusUtils.EventMessage<Object> eventMessage) {
        Intrinsics.checkParameterIsNotNull(eventMessage, NotificationCompat.CATEGORY_EVENT);
        if (eventMessage.getCode() == 5) {
            String tag = getTAG();
            Log.e(tag, "onMessageEventMainList: " + eventMessage.getUserBeanList().size());
            this.memberList.clear();
            this.memberList.add(new Member("add", 1));
            List<Member> list = this.memberList;
            List<Member> userBeanList = eventMessage.getUserBeanList();
            Intrinsics.checkExpressionValueIsNotNull(userBeanList, "event.userBeanList");
            list.addAll(userBeanList);
            MemberAdapter memberAdapter2 = this.memberAdapter;
            if (memberAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("memberAdapter");
            }
            memberAdapter2.notifyDataSetChanged();
        }
    }

    public void onDestroy() {
        super.onDestroy();
        EventBusUtils.unregister(this);
    }

    /* access modifiers changed from: protected */
    public void initView() {
        initAdapter();
        Parcelable decodeParcelable = MMKV.defaultMMKV().decodeParcelable("user", User.class, new User());
        Intrinsics.checkExpressionValueIsNotNull(decodeParcelable, "MMKV.defaultMMKV().decod…,User::class.java,User())");
        this.user = (User) decodeParcelable;
        updateUser();
        EventBusUtils.register(this);
        initClick();
        if (this.memberList.size() <= 1) {
            UserPresenter userPresenter = (UserPresenter) getMPresenter();
            if (userPresenter != null) {
                userPresenter.getMemList();
            }
            progressShow();
        }
    }

    private final void initClick() {
        View.OnClickListener onClickListener = this;
        SingleClickKt.singleClick$default((View) (RelativeLayout) _$_findCachedViewById(C2128R.C2131id.mineLayout), onClickListener, 0, 2, (Object) null);
        View.OnClickListener onClickListener2 = onClickListener;
        SingleClickKt.singleClick$default((View) (MyLinearLayout) _$_findCachedViewById(C2128R.C2131id.mineFeedback), onClickListener2, 0, 2, (Object) null);
        SingleClickKt.singleClick$default((View) (MyLinearLayout) _$_findCachedViewById(C2128R.C2131id.mineManage), onClickListener2, 0, 2, (Object) null);
        SingleClickKt.singleClick$default((View) (MyLinearLayout) _$_findCachedViewById(C2128R.C2131id.mineUnit), onClickListener2, 0, 2, (Object) null);
        SingleClickKt.singleClick$default((View) (MyLinearLayout) _$_findCachedViewById(C2128R.C2131id.mineRemind), onClickListener2, 0, 2, (Object) null);
        SingleClickKt.singleClick$default((View) (MyLinearLayout) _$_findCachedViewById(C2128R.C2131id.mineLanguage), onClickListener2, 0, 2, (Object) null);
        SingleClickKt.singleClick$default((View) (MyLinearLayout) _$_findCachedViewById(C2128R.C2131id.mineYs), onClickListener2, 0, 2, (Object) null);
        SingleClickKt.singleClick$default((View) (MyLinearLayout) _$_findCachedViewById(C2128R.C2131id.mineAbout), onClickListener2, 0, 2, (Object) null);
        ((MyLinearLayout) _$_findCachedViewById(C2128R.C2131id.mineClear)).setOnClickListener(onClickListener);
        SingleClickKt.singleClick$default((View) (MyLinearLayout) _$_findCachedViewById(C2128R.C2131id.mineGoogle), onClickListener2, 0, 2, (Object) null);
        SingleClickKt.singleClick$default((View) (MyLinearLayout) _$_findCachedViewById(C2128R.C2131id.mineFit), onClickListener2, 0, 2, (Object) null);
        SingleClickKt.singleClick$default((View) (MyLinearLayout) _$_findCachedViewById(C2128R.C2131id.mineVip), onClickListener2, 0, 2, (Object) null);
    }

    private final void initAdapter() {
        this.memberAdapter = new MemberAdapter(this.memberList);
        LinearLayoutManager mineFragment$initAdapter$manager$1 = new MineFragment$initAdapter$manager$1(this, getContext());
        mineFragment$initAdapter$manager$1.setOrientation(0);
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(C2128R.C2131id.mineRecyclerview);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView, "mineRecyclerview");
        recyclerView.setLayoutManager(mineFragment$initAdapter$manager$1);
        RecyclerView recyclerView2 = (RecyclerView) _$_findCachedViewById(C2128R.C2131id.mineRecyclerview);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView2, "mineRecyclerview");
        MemberAdapter memberAdapter2 = this.memberAdapter;
        if (memberAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("memberAdapter");
        }
        recyclerView2.setAdapter(memberAdapter2);
        MemberAdapter memberAdapter3 = this.memberAdapter;
        if (memberAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("memberAdapter");
        }
        memberAdapter3.setOnItemClickListener(new MineFragment$initAdapter$1(this));
    }

    private final void sendEmail(Context context, String str, String str2, String str3) {
        Intent intent = new Intent("android.intent.action.SENDTO", Uri.parse("mailto:" + str3));
        intent.putExtra("android.intent.extra.EMAIL", str3);
        intent.putExtra("android.intent.extra.SUBJECT", str);
        intent.putExtra("android.intent.extra.TITLE", str2);
        context.startActivity(Intent.createChooser(intent, ""));
    }

    /* access modifiers changed from: protected */
    @NotNull
    public UserPresenter getPresenter() {
        return new UserPresenter();
    }

    public void getMineList(@NotNull BaseBean<List<Member>> baseBean) {
        Intrinsics.checkParameterIsNotNull(baseBean, "loginBean");
        progressDissmiss();
        if (baseBean.getMeta().getSuccess()) {
            this.memberList.clear();
            this.memberList.add(new Member("add", 1));
            List data = baseBean.getData();
            if (data != null) {
                this.memberList.addAll(data);
            }
            MemberAdapter memberAdapter2 = this.memberAdapter;
            if (memberAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("memberAdapter");
            }
            memberAdapter2.notifyDataSetChanged();
        }
    }

    public void addMem(@NotNull BaseBean<String> baseBean) {
        Intrinsics.checkParameterIsNotNull(baseBean, "baseBean");
        progressDissmiss();
    }

    public void deleteMem(@NotNull BaseBean<String> baseBean) {
        Intrinsics.checkParameterIsNotNull(baseBean, "baseBean");
        progressDissmiss();
    }

    public void updateMem(@NotNull BaseBean<String> baseBean) {
        Intrinsics.checkParameterIsNotNull(baseBean, "baseBean");
        progressDissmiss();
    }

    public void updateUserInfo(@NotNull BaseBean<String> baseBean) {
        Intrinsics.checkParameterIsNotNull(baseBean, "baseBean");
        progressDissmiss();
    }

    public void showError(@Nullable Throwable th) {
        progressDissmiss();
    }

    public void onClick(@Nullable View view) {
        Integer valueOf = view != null ? Integer.valueOf(view.getId()) : null;
        if (valueOf != null && valueOf.intValue() == C2128R.C2131id.mineLayout) {
            FragmentActivity activity = getActivity();
            if (activity == null) {
                Intrinsics.throwNpe();
            }
            activity.startActivityForResult(new Intent(getContext(), UserInfoActivity.class).putExtra(ShareConstants.WEB_DIALOG_PARAM_DATA, new Member(this.user)).putExtra("flag", 0), 801);
        } else if (valueOf != null && valueOf.intValue() == C2128R.C2131id.mineFeedback) {
            Context context = getContext();
            if (context == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(context, "context!!");
            sendEmail(context, "Android-" + AppUtils.getAppName() + AppUtils.getAppVersionName(), "", "app@tenswall.com");
        } else if (valueOf != null && valueOf.intValue() == C2128R.C2131id.mineManage) {
            startActivity(new Intent(getContext(), AccountActivity.class));
        } else if (valueOf != null && valueOf.intValue() == C2128R.C2131id.mineUnit) {
            startActivity(new Intent(getContext(), UnitActivity.class));
        } else if (valueOf != null && valueOf.intValue() == C2128R.C2131id.mineRemind) {
            startActivity(new Intent(getContext(), RemindActivity.class));
        } else if (valueOf != null && valueOf.intValue() == C2128R.C2131id.mineLanguage) {
            startActivity(new Intent(getContext(), LanguageActivity.class));
        } else if (valueOf != null && valueOf.intValue() == C2128R.C2131id.mineYs) {
            startActivity(new Intent(getContext(), WebActivity.class));
        } else if (valueOf != null && valueOf.intValue() == C2128R.C2131id.mineAbout) {
            startActivity(new Intent(getContext(), AboutActivity.class));
        } else if (valueOf != null && valueOf.intValue() == C2128R.C2131id.mineClear) {
            ToastUtils.show((CharSequence) "ok");
        } else if (valueOf != null && valueOf.intValue() == C2128R.C2131id.mineGoogle) {
            startActivity(new Intent(getContext(), GoogleFitActivity.class));
        } else if (valueOf != null && valueOf.intValue() == C2128R.C2131id.mineFit) {
            startActivity(new Intent(getContext(), FitBitActivity.class));
        } else if (valueOf != null && valueOf.intValue() == C2128R.C2131id.mineVip) {
            startActivity(new Intent(getContext(), VipActivity.class));
        }
    }
}
