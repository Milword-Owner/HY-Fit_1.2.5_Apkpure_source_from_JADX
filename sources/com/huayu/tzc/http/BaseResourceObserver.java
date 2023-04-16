package com.huayu.tzc.http;

import android.content.Intent;
import android.util.Log;
import com.google.gson.JsonParseException;
import com.hjq.toast.ToastUtils;
import com.huayu.tzc.p014ui.activity.GuideActivity;
import com.huayu.tzc.utils.AppManager;
import com.tencent.mmkv.MMKV;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.text.ParseException;
import org.json.JSONException;
import p015io.reactivex.Observer;
import retrofit2.HttpException;

public abstract class BaseResourceObserver<T> implements Observer<T> {
    private static final int BAD_GATEWAY = 502;
    private static final int FORBIDDEN = 403;
    private static final int GATEWAY_TIMEOUT = 504;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int UNAUTHORIZED = 401;
    private long lastClickTime = 0;

    public void onComplete() {
    }

    public void onError(Throwable th) {
        ApiException requestHandle = requestHandle(th);
        ToastUtils.show((CharSequence) requestHandle.getMessage());
        Log.e("TAG", "requestHandle: " + requestHandle.getCode() + requestHandle.getMessage());
        StringBuilder sb = new StringBuilder();
        sb.append("requestHandle: ");
        sb.append(th.getMessage());
        Log.e("TAG", sb.toString());
    }

    private ApiException requestHandle(Throwable th) {
        if (th instanceof HttpException) {
            ApiException apiException = new ApiException(th, 1003);
            ((HttpException) th).code();
            apiException.setMessage("Network Error");
            return apiException;
        } else if (th instanceof ServerException) {
            ServerException serverException = (ServerException) th;
            ApiException apiException2 = new ApiException(serverException, serverException.getCode());
            apiException2.setMessage(serverException.getMessage());
            return apiException2;
        } else if ((th instanceof JsonParseException) || (th instanceof JSONException) || (th instanceof ParseException)) {
            ApiException apiException3 = new ApiException(th, 1001);
            apiException3.setMessage("Parsing error");
            return apiException3;
        } else if (th instanceof ConnectException) {
            ApiException apiException4 = new ApiException(th, 1002);
            apiException4.setMessage("Connection failed");
            return apiException4;
        } else if (th instanceof ApiException) {
            ApiException apiException5 = new ApiException(th, ((ApiException) th).getCode());
            if (apiException5.getCode() == UNAUTHORIZED || apiException5.getCode() == 403) {
                long currentTimeMillis = System.currentTimeMillis();
                apiException5.setMessage("The account has been offline. Please login again");
                if (currentTimeMillis - this.lastClickTime > 1000) {
                    this.lastClickTime = currentTimeMillis;
                    showLoginDialog();
                }
            } else {
                apiException5.setMessage("An error occurred");
            }
            return apiException5;
        } else if (th instanceof UnknownHostException) {
            ApiException apiException6 = new ApiException(th, 1002);
            apiException6.setMessage("No network");
            return apiException6;
        } else {
            ApiException apiException7 = new ApiException(th, 1000);
            apiException7.setMessage("Unknown Mistake");
            return apiException7;
        }
    }

    private void showLoginDialog() {
        AppManager.currentActivity().finish();
        MMKV.defaultMMKV().removeValueForKey("token");
        AppManager.currentActivity().startActivity(new Intent(AppManager.currentActivity(), GuideActivity.class).setFlags(268468224));
    }

    class ERROR {
        public static final int HTTP_ERROR = 1003;
        public static final int NETWORD_ERROR = 1002;
        public static final int PARSE_ERROR = 1001;
        public static final int UNKNOWN = 1000;

        ERROR() {
        }
    }
}
