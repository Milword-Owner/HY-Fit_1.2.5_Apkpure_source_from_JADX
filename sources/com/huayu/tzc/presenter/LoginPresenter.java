package com.huayu.tzc.presenter;

import com.huayu.tzc.base.BaseBean;
import com.huayu.tzc.base.MainPresenter;
import com.huayu.tzc.bean.LoginBean;
import com.huayu.tzc.contract.MainContract;
import com.huayu.tzc.http.Api;
import com.huayu.tzc.http.BaseResourceObserver;
import okhttp3.RequestBody;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import p015io.reactivex.android.schedulers.AndroidSchedulers;
import p015io.reactivex.disposables.Disposable;
import p015io.reactivex.schedulers.Schedulers;

public class LoginPresenter extends MainPresenter<MainContract.LoginView> implements MainContract.LoginPresenter {
    public void login(@NotNull String str, @NotNull String str2) {
        Api.Companion.getInstance().login(str, str2).subscribeOn(Schedulers.m2943io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseResourceObserver<BaseBean<LoginBean>>() {
            public void onSubscribe(Disposable disposable) {
                LoginPresenter.this.addReqs(disposable);
            }

            public void onNext(BaseBean<LoginBean> baseBean) {
                ((MainContract.LoginView) LoginPresenter.this.getMView()).login(baseBean);
            }

            public void onError(Throwable th) {
                super.onError(th);
                ((MainContract.LoginView) LoginPresenter.this.getMView()).showError(th);
            }
        });
    }

    public void register(@Nullable RequestBody requestBody) {
        Api.Companion.getInstance().register(requestBody).subscribeOn(Schedulers.m2943io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseResourceObserver<BaseBean<String>>() {
            public void onSubscribe(Disposable disposable) {
                LoginPresenter.this.addReqs(disposable);
            }

            public void onNext(BaseBean<String> baseBean) {
                ((MainContract.LoginView) LoginPresenter.this.getMView()).getData(1, baseBean);
            }

            public void onError(Throwable th) {
                super.onError(th);
                ((MainContract.LoginView) LoginPresenter.this.getMView()).showError(th);
            }
        });
    }

    public void forget(@NotNull String str, @NotNull String str2) {
        Api.Companion.getInstance().forget(str, str2).subscribeOn(Schedulers.m2943io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseResourceObserver<BaseBean<String>>() {
            public void onSubscribe(Disposable disposable) {
                LoginPresenter.this.addReqs(disposable);
            }

            public void onNext(BaseBean<String> baseBean) {
                ((MainContract.LoginView) LoginPresenter.this.getMView()).getData(1, baseBean);
            }

            public void onError(Throwable th) {
                super.onError(th);
                ((MainContract.LoginView) LoginPresenter.this.getMView()).showError(th);
            }
        });
    }

    public void getMailCode(@NotNull String str) {
        Api.Companion.getInstance().getCode(str).subscribeOn(Schedulers.m2943io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseResourceObserver<BaseBean<String>>() {
            public void onSubscribe(Disposable disposable) {
                LoginPresenter.this.addReqs(disposable);
            }

            public void onNext(BaseBean<String> baseBean) {
                ((MainContract.LoginView) LoginPresenter.this.getMView()).getData(2, baseBean);
            }

            public void onError(Throwable th) {
                super.onError(th);
                ((MainContract.LoginView) LoginPresenter.this.getMView()).showError(th);
            }
        });
    }

    public void checkCode(@NotNull String str, @NotNull String str2) {
        Api.Companion.getInstance().checkCode(str, str2).subscribeOn(Schedulers.m2943io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseResourceObserver<BaseBean<String>>() {
            public void onSubscribe(Disposable disposable) {
                LoginPresenter.this.addReqs(disposable);
            }

            public void onNext(BaseBean<String> baseBean) {
                ((MainContract.LoginView) LoginPresenter.this.getMView()).getData(3, baseBean);
            }

            public void onError(Throwable th) {
                super.onError(th);
                ((MainContract.LoginView) LoginPresenter.this.getMView()).showError(th);
            }
        });
    }

    public void thirdLogin(String str) {
        Api.Companion.getInstance().thirdLogin(str).subscribeOn(Schedulers.m2943io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseResourceObserver<BaseBean<LoginBean>>() {
            public void onSubscribe(Disposable disposable) {
                LoginPresenter.this.addReqs(disposable);
            }

            public void onNext(BaseBean<LoginBean> baseBean) {
                ((MainContract.LoginView) LoginPresenter.this.getMView()).thirdLogin(baseBean);
            }

            public void onError(Throwable th) {
                super.onError(th);
                ((MainContract.LoginView) LoginPresenter.this.getMView()).showError(th);
            }
        });
    }

    public void bindThird(String str, String str2, String str3) {
        Api.Companion.getInstance().bindThird(str, str2, str3).subscribeOn(Schedulers.m2943io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseResourceObserver<BaseBean>() {
            public void onSubscribe(Disposable disposable) {
                LoginPresenter.this.addReqs(disposable);
            }

            public void onNext(BaseBean baseBean) {
                ((MainContract.LoginView) LoginPresenter.this.getMView()).thirdLogin(baseBean);
            }

            public void onError(Throwable th) {
                super.onError(th);
                ((MainContract.LoginView) LoginPresenter.this.getMView()).showError(th);
            }
        });
    }
}
