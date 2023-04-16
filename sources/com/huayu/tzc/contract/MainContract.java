package com.huayu.tzc.contract;

import com.huayu.tzc.base.BaseBean;
import com.huayu.tzc.base.BaseContract;
import com.huayu.tzc.base.BaseListBean;
import com.huayu.tzc.bean.LoginBean;
import com.huayu.tzc.bean.Measure;
import com.huayu.tzc.bean.Member;
import com.huayu.tzc.bean.MsgHistory;
import com.huayu.tzc.bean.Trend;
import com.huayu.tzc.bean.Tweet;
import com.huayu.tzc.bean.User;
import com.huayu.tzc.bean.Version;
import com.huayu.tzc.bean.VipBean;
import java.util.List;
import kotlin.Metadata;
import okhttp3.RequestBody;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0019\bf\u0018\u00002\u00020\u0001:\u0018\u0002\u0003\u0004\u0005\u0006\u0007\b\t\n\u000b\f\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014\u0015\u0016\u0017\u0018\u0019¨\u0006\u001a"}, mo21895d2 = {"Lcom/huayu/tzc/contract/MainContract;", "Lcom/huayu/tzc/base/BaseContract;", "HistoryPresenter", "HistoryView", "HomePresenter", "HomeView", "LoginPresenter", "LoginView", "ModifyPresenter", "ModifyView", "MsgPresenter", "MsgView", "Presenter", "PrimaryPresenter", "PrimaryView", "TargetPresenter", "TargetView", "TrendPresenter", "TrendView", "TweetPresenter", "TweetView", "UserPresenter", "UserView", "View", "VipPresenter", "VipView", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
/* compiled from: MainContract.kt */
public interface MainContract extends BaseContract {

    @Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&J\u0016\u0010\u0007\u001a\u00020\u00032\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tH&¨\u0006\u000b"}, mo21895d2 = {"Lcom/huayu/tzc/contract/MainContract$HomeView;", "Lcom/huayu/tzc/base/BaseContract$BaseView;", "getMeasures", "", "measureBean", "Lcom/huayu/tzc/base/BaseListBean;", "Lcom/huayu/tzc/bean/Measure;", "reporting", "baseBean", "Lcom/huayu/tzc/base/BaseBean;", "", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
    /* compiled from: MainContract.kt */
    public interface HomeView extends BaseContract.BaseView {
        void getMeasures(@NotNull BaseListBean<Measure> baseListBean);

        void reporting(@NotNull BaseBean<String> baseBean);
    }

    @Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&J\u0016\u0010\u0007\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&J\u0016\u0010\b\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\n0\tH&¨\u0006\u000b"}, mo21895d2 = {"Lcom/huayu/tzc/contract/MainContract$MsgView;", "Lcom/huayu/tzc/base/BaseContract$BaseView;", "getMsgHistory", "", "baseBean", "Lcom/huayu/tzc/base/BaseListBean;", "Lcom/huayu/tzc/bean/MsgHistory;", "getNewMsg", "sendMsg", "Lcom/huayu/tzc/base/BaseBean;", "", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
    /* compiled from: MainContract.kt */
    public interface MsgView extends BaseContract.BaseView {
        void getMsgHistory(@NotNull BaseListBean<MsgHistory> baseListBean);

        void getNewMsg(@NotNull BaseListBean<MsgHistory> baseListBean);

        void sendMsg(@NotNull BaseBean<String> baseBean);
    }

    @Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u0005H&¨\u0006\b"}, mo21895d2 = {"Lcom/huayu/tzc/contract/MainContract$TrendView;", "Lcom/huayu/tzc/base/BaseContract$BaseView;", "getTrend", "", "baseBean", "Lcom/huayu/tzc/base/BaseBean;", "", "Lcom/huayu/tzc/bean/Trend;", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
    /* compiled from: MainContract.kt */
    public interface TrendView extends BaseContract.BaseView {
        void getTrend(@NotNull BaseBean<List<Trend>> baseBean);
    }

    @Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&J\u0016\u0010\u0007\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&J\u001c\u0010\b\u001a\u00020\u00032\u0012\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\u0005H&J\u0016\u0010\f\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&J\u0016\u0010\r\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&¨\u0006\u000e"}, mo21895d2 = {"Lcom/huayu/tzc/contract/MainContract$UserView;", "Lcom/huayu/tzc/base/BaseContract$BaseView;", "addMem", "", "baseBean", "Lcom/huayu/tzc/base/BaseBean;", "", "deleteMem", "getMineList", "loginBean", "", "Lcom/huayu/tzc/bean/Member;", "updateMem", "updateUserInfo", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
    /* compiled from: MainContract.kt */
    public interface UserView extends BaseContract.BaseView {
        void addMem(@NotNull BaseBean<String> baseBean);

        void deleteMem(@NotNull BaseBean<String> baseBean);

        void getMineList(@NotNull BaseBean<List<Member>> baseBean);

        void updateMem(@NotNull BaseBean<String> baseBean);

        void updateUserInfo(@NotNull BaseBean<String> baseBean);
    }

    @Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J \u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\bH&¨\u0006\n"}, mo21895d2 = {"Lcom/huayu/tzc/contract/MainContract$HistoryPresenter;", "", "deleteMeasures", "", "id", "", "getMeasures", "page", "", "pageSize", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
    /* compiled from: MainContract.kt */
    public interface HistoryPresenter {
        void deleteMeasures(@NotNull String str);

        void getMeasures(int i, int i2, int i3);
    }

    @Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&¨\u0006\u0007"}, mo21895d2 = {"Lcom/huayu/tzc/contract/MainContract$HistoryView;", "Lcom/huayu/tzc/base/BaseContract$BaseView;", "getMeasures", "", "measureBean", "Lcom/huayu/tzc/base/BaseListBean;", "Lcom/huayu/tzc/bean/Measure;", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
    /* compiled from: MainContract.kt */
    public interface HistoryView extends BaseContract.BaseView {
        void getMeasures(@NotNull BaseListBean<Measure> baseListBean);
    }

    @Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0018\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&J\u0018\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&J \u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u000e\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH&J\u0010\u0010\u000f\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH&¨\u0006\u0010"}, mo21895d2 = {"Lcom/huayu/tzc/contract/MainContract$HomePresenter;", "", "deleteMeasures", "", "id", "", "fitFat", "", "requestBody", "Lokhttp3/RequestBody;", "fitWeight", "getMeasures", "page", "pageSize", "reporting", "reportingData", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
    /* compiled from: MainContract.kt */
    public interface HomePresenter {
        void deleteMeasures(int i);

        void fitFat(@NotNull String str, @NotNull RequestBody requestBody);

        void fitWeight(@NotNull String str, @NotNull RequestBody requestBody);

        void getMeasures(int i, int i2, int i3);

        void reporting(@NotNull RequestBody requestBody);

        void reportingData(@NotNull RequestBody requestBody);
    }

    @Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0005H&J\u0018\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u0005H&J\u0018\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0005H&J\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0005H&J\u0018\u0010\u000e\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0005H&J\u0012\u0010\u000f\u001a\u00020\u00032\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H&J\u0010\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0013"}, mo21895d2 = {"Lcom/huayu/tzc/contract/MainContract$LoginPresenter;", "", "bindThird", "", "userId", "", "phone", "password", "checkCode", "mail", "code", "forget", "mobile", "getMailCode", "login", "register", "requestBody", "Lokhttp3/RequestBody;", "thirdLogin", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
    /* compiled from: MainContract.kt */
    public interface LoginPresenter {
        void bindThird(@NotNull String str, @NotNull String str2, @NotNull String str3);

        void checkCode(@NotNull String str, @NotNull String str2);

        void forget(@NotNull String str, @NotNull String str2);

        void getMailCode(@NotNull String str);

        void login(@NotNull String str, @NotNull String str2);

        void register(@Nullable RequestBody requestBody);

        void thirdLogin(@NotNull String str);
    }

    @Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&¨\u0006\u0007"}, mo21895d2 = {"Lcom/huayu/tzc/contract/MainContract$ModifyPresenter;", "", "modifyPass", "", "oldPass", "", "newPass", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
    /* compiled from: MainContract.kt */
    public interface ModifyPresenter {
        void modifyPass(@NotNull String str, @NotNull String str2);
    }

    @Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&¨\u0006\u0007"}, mo21895d2 = {"Lcom/huayu/tzc/contract/MainContract$ModifyView;", "Lcom/huayu/tzc/base/BaseContract$BaseView;", "modifyPass", "", "baseBean", "Lcom/huayu/tzc/base/BaseBean;", "", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
    /* compiled from: MainContract.kt */
    public interface ModifyView extends BaseContract.BaseView {
        void modifyPass(@NotNull BaseBean<String> baseBean);
    }

    @Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0005H&J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH&¨\u0006\f"}, mo21895d2 = {"Lcom/huayu/tzc/contract/MainContract$MsgPresenter;", "", "getMsgHistory", "", "page", "", "pageSize", "getNewMsg", "maxId", "sendMsg", "requestBody", "Lokhttp3/RequestBody;", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
    /* compiled from: MainContract.kt */
    public interface MsgPresenter {
        void getMsgHistory(int i, int i2);

        void getNewMsg(int i);

        void sendMsg(@NotNull RequestBody requestBody);
    }

    @Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\bf\u0018\u00002\u00020\u0001¨\u0006\u0002"}, mo21895d2 = {"Lcom/huayu/tzc/contract/MainContract$Presenter;", "", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
    /* compiled from: MainContract.kt */
    public interface Presenter {
    }

    @Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\b\u0010\u0005\u001a\u00020\u0003H&J\b\u0010\u0006\u001a\u00020\u0003H&J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH&J\u0018\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\b\u001a\u00020\tH&J\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH&¨\u0006\u000e"}, mo21895d2 = {"Lcom/huayu/tzc/contract/MainContract$PrimaryPresenter;", "", "getAppVersion", "", "getMemList", "getNotReadCount", "getUserInfo", "midUnit", "requestBody", "Lokhttp3/RequestBody;", "reportingData", "item", "Lcom/huayu/tzc/bean/Measure;", "updateUserInfo", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
    /* compiled from: MainContract.kt */
    public interface PrimaryPresenter {
        void getAppVersion();

        void getMemList();

        void getNotReadCount();

        void getUserInfo();

        void midUnit(@NotNull RequestBody requestBody);

        void reportingData(@NotNull Measure measure, @NotNull RequestBody requestBody);

        void updateUserInfo(@NotNull RequestBody requestBody);
    }

    @Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&J\u001c\u0010\u0007\u001a\u00020\u00032\u0012\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u0005H&J\u0016\u0010\u000b\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\f0\u0005H&J\u0016\u0010\r\u001a\u00020\u00032\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0005H&J\u001e\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u00112\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00120\u0005H&J\u0016\u0010\u0013\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00120\u0005H&¨\u0006\u0014"}, mo21895d2 = {"Lcom/huayu/tzc/contract/MainContract$PrimaryView;", "Lcom/huayu/tzc/base/BaseContract$BaseView;", "getAppVersion", "", "baseBean", "Lcom/huayu/tzc/base/BaseBean;", "Lcom/huayu/tzc/bean/Version;", "getMineList", "loginBean", "", "Lcom/huayu/tzc/bean/Member;", "getNotReadCount", "", "getUserInfo", "Lcom/huayu/tzc/bean/User;", "reporting", "item", "Lcom/huayu/tzc/bean/Measure;", "", "updateUserInfo", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
    /* compiled from: MainContract.kt */
    public interface PrimaryView extends BaseContract.BaseView {
        void getAppVersion(@NotNull BaseBean<Version> baseBean);

        void getMineList(@NotNull BaseBean<List<Member>> baseBean);

        void getNotReadCount(@NotNull BaseBean<Integer> baseBean);

        void getUserInfo(@NotNull BaseBean<User> baseBean);

        void reporting(@NotNull Measure measure, @NotNull BaseBean<String> baseBean);

        void updateUserInfo(@NotNull BaseBean<String> baseBean);
    }

    @Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0007"}, mo21895d2 = {"Lcom/huayu/tzc/contract/MainContract$TargetPresenter;", "", "updateMem", "", "requestBody", "Lokhttp3/RequestBody;", "updateUserInfo", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
    /* compiled from: MainContract.kt */
    public interface TargetPresenter {
        void updateMem(@NotNull RequestBody requestBody);

        void updateUserInfo(@NotNull RequestBody requestBody);
    }

    @Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&J\u0016\u0010\u0007\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&¨\u0006\b"}, mo21895d2 = {"Lcom/huayu/tzc/contract/MainContract$TargetView;", "Lcom/huayu/tzc/base/BaseContract$BaseView;", "updateMem", "", "baseBean", "Lcom/huayu/tzc/base/BaseBean;", "", "updateUserInfo", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
    /* compiled from: MainContract.kt */
    public interface TargetView extends BaseContract.BaseView {
        void updateMem(@NotNull BaseBean<String> baseBean);

        void updateUserInfo(@NotNull BaseBean<String> baseBean);
    }

    @Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J(\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007H&¨\u0006\n"}, mo21895d2 = {"Lcom/huayu/tzc/contract/MainContract$TrendPresenter;", "", "getTrend", "", "id", "", "startTime", "", "endTime", "type", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
    /* compiled from: MainContract.kt */
    public interface TrendPresenter {
        void getTrend(int i, @NotNull String str, @NotNull String str2, @NotNull String str3);
    }

    @Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0018\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0005H&J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u000b"}, mo21895d2 = {"Lcom/huayu/tzc/contract/MainContract$TweetPresenter;", "", "getStatus", "", "id", "", "getTweetList", "page", "rows", "giveUpCount", "giveUpStatus", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
    /* compiled from: MainContract.kt */
    public interface TweetPresenter {
        void getStatus(int i);

        void getTweetList(int i, int i2);

        void giveUpCount(int i);

        void giveUpStatus(int i);
    }

    @Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&J\u0016\u0010\u0007\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\t0\bH&J\u0016\u0010\n\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0005H&¨\u0006\f"}, mo21895d2 = {"Lcom/huayu/tzc/contract/MainContract$TweetView;", "Lcom/huayu/tzc/base/BaseContract$BaseView;", "getStatus", "", "baseBean", "Lcom/huayu/tzc/base/BaseBean;", "", "getTweetList", "Lcom/huayu/tzc/base/BaseListBean;", "Lcom/huayu/tzc/bean/Tweet;", "giveUpCount", "", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
    /* compiled from: MainContract.kt */
    public interface TweetView extends BaseContract.BaseView {
        void getStatus(@NotNull BaseBean<Boolean> baseBean);

        void getTweetList(@NotNull BaseListBean<Tweet> baseListBean);

        void giveUpCount(@NotNull BaseBean<Integer> baseBean);
    }

    @Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\u0003H&J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\f"}, mo21895d2 = {"Lcom/huayu/tzc/contract/MainContract$UserPresenter;", "", "addMem", "", "requestBody", "Lokhttp3/RequestBody;", "deleteMem", "id", "", "getMemList", "updateMem", "updateUserInfo", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
    /* compiled from: MainContract.kt */
    public interface UserPresenter {
        void addMem(@NotNull RequestBody requestBody);

        void deleteMem(@NotNull String str);

        void getMemList();

        void updateMem(@NotNull RequestBody requestBody);

        void updateUserInfo(@NotNull RequestBody requestBody);
    }

    @Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001¨\u0006\u0002"}, mo21895d2 = {"Lcom/huayu/tzc/contract/MainContract$View;", "Lcom/huayu/tzc/base/BaseContract$BaseView;", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
    /* compiled from: MainContract.kt */
    public interface View extends BaseContract.BaseView {
    }

    @Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&¨\u0006\b"}, mo21895d2 = {"Lcom/huayu/tzc/contract/MainContract$VipPresenter;", "", "getVipMsg", "", "modifyUpgrade", "purchase", "", "upgradeVip", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
    /* compiled from: MainContract.kt */
    public interface VipPresenter {
        void getVipMsg();

        void modifyUpgrade(@NotNull String str);

        void upgradeVip(@NotNull String str);
    }

    @Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&J\u0016\u0010\u0007\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\b0\u0005H&J\u0016\u0010\t\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\b0\u0005H&¨\u0006\n"}, mo21895d2 = {"Lcom/huayu/tzc/contract/MainContract$VipView;", "Lcom/huayu/tzc/base/BaseContract$BaseView;", "getVipMsg", "", "baseBean", "Lcom/huayu/tzc/base/BaseBean;", "Lcom/huayu/tzc/bean/VipBean;", "modifyUpgrade", "", "upgradeVip", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
    /* compiled from: MainContract.kt */
    public interface VipView extends BaseContract.BaseView {
        void getVipMsg(@NotNull BaseBean<VipBean> baseBean);

        void modifyUpgrade(@NotNull BaseBean<String> baseBean);

        void upgradeVip(@NotNull BaseBean<String> baseBean);
    }

    @Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H&J\u0016\u0010\t\u001a\u00020\u00032\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\n0\u0007H&J\u0016\u0010\u000b\u001a\u00020\u00032\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\n0\u0007H&¨\u0006\r"}, mo21895d2 = {"Lcom/huayu/tzc/contract/MainContract$LoginView;", "Lcom/huayu/tzc/base/BaseContract$BaseView;", "getData", "", "flag", "", "baseBean", "Lcom/huayu/tzc/base/BaseBean;", "", "login", "Lcom/huayu/tzc/bean/LoginBean;", "thirdLogin", "loginBean", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
    /* compiled from: MainContract.kt */
    public interface LoginView extends BaseContract.BaseView {
        void getData(int i, @NotNull BaseBean<String> baseBean);

        void login(@NotNull BaseBean<LoginBean> baseBean);

        void thirdLogin(@NotNull BaseBean<LoginBean> baseBean);

        @Metadata(mo21893bv = {1, 0, 3}, mo21896k = 3, mo21897mv = {1, 1, 16})
        /* compiled from: MainContract.kt */
        public static final class DefaultImpls {
            public static /* synthetic */ void getData$default(LoginView loginView, int i, BaseBean baseBean, int i2, Object obj) {
                if (obj == null) {
                    if ((i2 & 1) != 0) {
                        i = 1;
                    }
                    loginView.getData(i, baseBean);
                    return;
                }
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getData");
            }
        }
    }
}
