package com.huayu.tzc.http;

import com.huayu.tzc.base.BaseBean;
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
import p015io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0010\bf\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0007H'J\u001e\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010\t\u001a\u00020\u0005H'J<\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010\u000b\u001a\u00020\f2\b\b\u0001\u0010\r\u001a\u00020\u00052\b\b\u0001\u0010\u000e\u001a\u00020\u00052\b\b\u0001\u0010\u000f\u001a\u00020\u0005H'J,\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\n\b\u0001\u0010\u0011\u001a\u0004\u0018\u00010\u00052\n\b\u0001\u0010\u0012\u001a\u0004\u0018\u00010\u0005H'J\u001e\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010\u0014\u001a\u00020\fH'J \u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\n\b\u0001\u0010\u0016\u001a\u0004\u0018\u00010\u0005H'J\"\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00050\u00032\b\b\u0001\u0010\u0018\u001a\u00020\u00052\b\b\u0001\u0010\u0006\u001a\u00020\u0007H'J\"\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00050\u00032\b\b\u0001\u0010\u0018\u001a\u00020\u00052\b\b\u0001\u0010\u0006\u001a\u00020\u0007H'J,\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\n\b\u0001\u0010\u000e\u001a\u0004\u0018\u00010\u00052\n\b\u0001\u0010\u000f\u001a\u0004\u0018\u00010\u0005H'J\u0014\u0010\u001b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001c0\u00040\u0003H'J \u0010\u001d\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\n\b\u0001\u0010\u0011\u001a\u0004\u0018\u00010\u0005H'J2\u0010\u001e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020 0\u001f0\u00032\b\b\u0001\u0010!\u001a\u00020\f2\b\b\u0001\u0010\"\u001a\u00020\f2\b\b\u0001\u0010#\u001a\u00020\fH'J\u001a\u0010$\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020&0%0\u00040\u0003H'J(\u0010'\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020(0\u001f0\u00032\b\b\u0001\u0010!\u001a\u00020\f2\b\b\u0001\u0010\"\u001a\u00020\fH'J2\u0010)\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020(0\u001f0\u00032\b\b\u0001\u0010!\u001a\u00020\f2\b\b\u0001\u0010\"\u001a\u00020\f2\b\b\u0001\u0010*\u001a\u00020\fH'J\u0014\u0010+\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u00040\u0003H'J\u001e\u0010,\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020-0\u00040\u00032\b\b\u0001\u0010.\u001a\u00020\fH'JH\u0010/\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002000%0\u00040\u00032\b\b\u0001\u0010#\u001a\u00020\f2\n\b\u0001\u00101\u001a\u0004\u0018\u00010\u00052\n\b\u0001\u00102\u001a\u0004\u0018\u00010\u00052\n\b\u0001\u0010\u000b\u001a\u0004\u0018\u00010\u0005H'J(\u00103\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002040\u001f0\u00032\b\b\u0001\u0010!\u001a\u00020\f2\b\b\u0001\u00105\u001a\u00020\fH'J\u0014\u00106\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002070\u00040\u0003H'J\u0014\u00108\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002090\u00040\u0003H'J\u001e\u0010:\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u00040\u00032\b\b\u0001\u0010;\u001a\u00020\fH'J\u001e\u0010<\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010.\u001a\u00020\fH'J,\u0010=\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020>0\u00040\u00032\n\b\u0001\u0010\u000e\u001a\u0004\u0018\u00010\u00052\n\b\u0001\u0010\u000f\u001a\u0004\u0018\u00010\u0005H'J\u001e\u0010?\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u0007H'J(\u0010@\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010A\u001a\u00020\u00052\b\b\u0001\u0010B\u001a\u00020\u0005H'J\u001e\u0010C\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010D\u001a\u00020\u0005H'J \u0010E\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0007H'J\u001e\u0010F\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u0007H'J\u001e\u0010G\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u0007H'J\u001e\u0010H\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u0007H'J \u0010I\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0007H'J(\u0010J\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020>0\u00040\u00032\b\b\u0001\u0010\u000b\u001a\u00020\f2\b\b\u0001\u0010\u0018\u001a\u00020\u0005H'J \u0010K\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0007H'J(\u0010L\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010D\u001a\u00020\u00052\b\b\u0001\u0010M\u001a\u00020\fH'¨\u0006N"}, mo21895d2 = {"Lcom/huayu/tzc/http/ApiService;", "", "addMem", "Lio/reactivex/Observable;", "Lcom/huayu/tzc/base/BaseBean;", "", "requestBody", "Lokhttp3/RequestBody;", "batchDelete", "recordids", "bindThird", "type", "", "uid", "mobile", "password", "checkCode", "email", "vcode", "deleteMeasures", "recordid", "deleteMem", "member_id", "fitFat", "userId", "fitWeight", "forget", "getAppVersion", "Lcom/huayu/tzc/bean/Version;", "getCode", "getMeasures", "Lcom/huayu/tzc/base/BaseListBean;", "Lcom/huayu/tzc/bean/Measure;", "page", "pagesize", "id", "getMemList", "", "Lcom/huayu/tzc/bean/Member;", "getMsgHistory", "Lcom/huayu/tzc/bean/MsgHistory;", "getNewMsg", "max_chatid", "getNotReadCount", "getStatus", "", "pushmessageid", "getTrend", "Lcom/huayu/tzc/bean/Trend;", "startTime", "endTime", "getTweetList", "Lcom/huayu/tzc/bean/Tweet;", "rows", "getUserInfo", "Lcom/huayu/tzc/bean/User;", "getVipMsg", "Lcom/huayu/tzc/bean/VipBean;", "giveUpCount", "pushmessgeid", "giveUpStatus", "login", "Lcom/huayu/tzc/bean/LoginBean;", "midUnit", "modifyPass", "oldpwd", "newpwd", "modifyUpgrade", "purchase", "register", "reporting", "reportingData", "sendMessage", "suppleMentUser", "thirdLogin", "updateMem", "upgradeVip", "apptype", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
/* compiled from: ApiService.kt */
public interface ApiService {
    @NotNull
    @POST("/api/member/addmem")
    Observable<BaseBean<String>> addMem(@Body @Nullable RequestBody requestBody);

    @NotNull
    @GET("/api/measure/batchdelete")
    Observable<BaseBean<String>> batchDelete(@NotNull @Query("recordids") String str);

    @NotNull
    @POST("/user/bindthird")
    Observable<BaseBean<String>> bindThird(@Query("logintype") int i, @NotNull @Query("uid") String str, @NotNull @Query("email") String str2, @NotNull @Query("u_pwd") String str3);

    @NotNull
    @GET("/user/checkvcode/{email}/{vcode}")
    @Headers({"Cache-Control:public,no-cache"})
    Observable<BaseBean<String>> checkCode(@Nullable @Path("email") String str, @Nullable @Path("vcode") String str2);

    @NotNull
    @GET("/api/measure/delete/{recordid}")
    Observable<BaseBean<String>> deleteMeasures(@Path("recordid") int i);

    @NotNull
    @POST("/api/member/delmem/{member_id}")
    Observable<BaseBean<String>> deleteMem(@Nullable @Path("member_id") String str);

    @NotNull
    @POST("https://api.fitbit.com/1/user/{user-id}/body/log/fat.json")
    Observable<String> fitFat(@NotNull @Path("user-id") String str, @NotNull @Body RequestBody requestBody);

    @NotNull
    @POST("https://api.fitbit.com/1/user/{user-id}/body/log/weight.json")
    Observable<String> fitWeight(@NotNull @Path("user-id") String str, @NotNull @Body RequestBody requestBody);

    @NotNull
    @POST("/user/modifypwd/")
    Observable<BaseBean<String>> forget(@Nullable @Query("email") String str, @Nullable @Query("u_pwd") String str2);

    @NotNull
    @GET("/appversion/1/1")
    @Headers({"Cache-Control:public,no-cache"})
    Observable<BaseBean<Version>> getAppVersion();

    @NotNull
    @GET("/user/sendvcode/{email}")
    @Headers({"Cache-Control:public,no-cache"})
    Observable<BaseBean<String>> getCode(@Nullable @Path("email") String str);

    @NotNull
    @GET("/api/measure/getmeasures")
    @Headers({"Cache-Control:public,no-cache"})
    Observable<BaseListBean<Measure>> getMeasures(@Query("page") int i, @Query("pagesize") int i2, @Query("member_id") int i3);

    @NotNull
    @GET("/api/member/memlist")
    @Headers({"Cache-Control:public,no-cache"})
    Observable<BaseBean<List<Member>>> getMemList();

    @NotNull
    @GET("/api/chat/gethischats")
    @Headers({"Cache-Control:public,no-cache"})
    Observable<BaseListBean<MsgHistory>> getMsgHistory(@Query("page") int i, @Query("pagesize") int i2);

    @NotNull
    @GET("/api/chat/gethischats")
    @Headers({"Cache-Control:public,no-cache"})
    Observable<BaseListBean<MsgHistory>> getNewMsg(@Query("page") int i, @Query("pagesize") int i2, @Query("max_chatid") int i3);

    @NotNull
    @GET("/api/chat/getnotreadcount")
    Observable<BaseBean<Integer>> getNotReadCount();

    @NotNull
    @GET("/api/pushmessage/getgiveupstatus/{pushmessageid}")
    @Headers({"Cache-Control:public,no-cache"})
    Observable<BaseBean<Boolean>> getStatus(@Path("pushmessageid") int i);

    @NotNull
    @GET("/api/measure/gettred")
    @Headers({"Cache-Control:public,no-cache"})
    Observable<BaseBean<List<Trend>>> getTrend(@Query("member_id") int i, @Nullable @Query("begindate") String str, @Nullable @Query("enddate") String str2, @Nullable @Query("paramtype") String str3);

    @NotNull
    @GET("/api/pushmessage/getlist")
    Observable<BaseListBean<Tweet>> getTweetList(@Query("page") int i, @Query("rows") int i2);

    @NotNull
    @GET("/api/member/getuserinfo")
    @Headers({"Cache-Control:public,no-cache"})
    Observable<BaseBean<User>> getUserInfo();

    @NotNull
    @GET("/api/userUpgrad/get")
    @Headers({"Cache-Control:public,no-cache"})
    Observable<BaseBean<VipBean>> getVipMsg();

    @NotNull
    @GET("/api/pushmessage/giveupcount")
    @Headers({"Cache-Control:public,no-cache"})
    Observable<BaseBean<Integer>> giveUpCount(@Query("pushmessgeid") int i);

    @NotNull
    @GET("/api/pushmessage/giveup/{pushmessageid}")
    Observable<BaseBean<String>> giveUpStatus(@Path("pushmessageid") int i);

    @NotNull
    @POST("/user/login")
    Observable<BaseBean<LoginBean>> login(@Nullable @Query("email") String str, @Nullable @Query("u_pwd") String str2);

    @NotNull
    @POST("/api/member/midunit")
    Observable<BaseBean<String>> midUnit(@NotNull @Body RequestBody requestBody);

    @NotNull
    @POST("/api/member/midpwd")
    Observable<BaseBean<String>> modifyPass(@NotNull @Query("oldpwd") String str, @NotNull @Query("newpwd") String str2);

    @NotNull
    @GET("/api/userUpgrad/update/{purchase}")
    @Headers({"Cache-Control:public,no-cache"})
    Observable<BaseBean<String>> modifyUpgrade(@NotNull @Path("purchase") String str);

    @NotNull
    @POST("/user/register")
    Observable<BaseBean<String>> register(@Body @Nullable RequestBody requestBody);

    @NotNull
    @POST("/api/device/reporting")
    Observable<BaseBean<String>> reporting(@NotNull @Body RequestBody requestBody);

    @NotNull
    @POST("/api/measure/reporting")
    Observable<BaseBean<String>> reportingData(@NotNull @Body RequestBody requestBody);

    @NotNull
    @POST("/api/chat/sendMessage")
    Observable<BaseBean<String>> sendMessage(@NotNull @Body RequestBody requestBody);

    @NotNull
    @POST("/api/member/supplementuser")
    Observable<BaseBean<String>> suppleMentUser(@Body @Nullable RequestBody requestBody);

    @NotNull
    @POST("/user/thirdlogin")
    Observable<BaseBean<LoginBean>> thirdLogin(@Query("logintype") int i, @NotNull @Query("uid") String str);

    @NotNull
    @POST("/api/member/midmem")
    Observable<BaseBean<String>> updateMem(@Body @Nullable RequestBody requestBody);

    @NotNull
    @GET("/api/userUpgrad/add/{purchase}/{app_type}")
    @Headers({"Cache-Control:public,no-cache"})
    Observable<BaseBean<String>> upgradeVip(@NotNull @Path("purchase") String str, @Path("app_type") int i);
}
