package com.huayu.tzc.presenter;

import com.huayu.tzc.base.BaseBean;
import com.huayu.tzc.base.MainPresenter;
import com.huayu.tzc.bean.Measure;
import com.huayu.tzc.bean.Member;
import com.huayu.tzc.bean.User;
import com.huayu.tzc.bean.Version;
import com.huayu.tzc.contract.MainContract;
import com.huayu.tzc.http.Api;
import com.huayu.tzc.http.BaseResourceObserver;
import java.util.List;
import okhttp3.RequestBody;
import org.jetbrains.annotations.NotNull;
import p015io.reactivex.android.schedulers.AndroidSchedulers;
import p015io.reactivex.disposables.Disposable;
import p015io.reactivex.schedulers.Schedulers;

public class PrimaryPresenter extends MainPresenter<MainContract.PrimaryView> implements MainContract.PrimaryPresenter {
    public void getAppVersion() {
        Api.Companion.getInstance().getAppVersion().subscribeOn(Schedulers.m2943io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseResourceObserver<BaseBean<Version>>() {
            public void onSubscribe(Disposable disposable) {
                PrimaryPresenter.this.addReqs(disposable);
            }

            public void onNext(BaseBean<Version> baseBean) {
                ((MainContract.PrimaryView) PrimaryPresenter.this.getMView()).getAppVersion(baseBean);
            }

            public void onError(Throwable th) {
                super.onError(th);
                ((MainContract.PrimaryView) PrimaryPresenter.this.getMView()).showError(th);
            }
        });
    }

    public void getMemList() {
        Api.Companion.getInstance().getMemList().subscribeOn(Schedulers.m2943io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseResourceObserver<BaseBean<List<Member>>>() {
            public void onSubscribe(Disposable disposable) {
                PrimaryPresenter.this.addReqs(disposable);
            }

            public void onNext(BaseBean<List<Member>> baseBean) {
                ((MainContract.PrimaryView) PrimaryPresenter.this.getMView()).getMineList(baseBean);
            }

            public void onError(Throwable th) {
                super.onError(th);
                ((MainContract.PrimaryView) PrimaryPresenter.this.getMView()).showError(th);
            }
        });
    }

    public void getUserInfo() {
        Api.Companion.getInstance().getUserInfo().subscribeOn(Schedulers.m2943io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseResourceObserver<BaseBean<User>>() {
            public void onSubscribe(Disposable disposable) {
                PrimaryPresenter.this.addReqs(disposable);
            }

            public void onNext(BaseBean<User> baseBean) {
                ((MainContract.PrimaryView) PrimaryPresenter.this.getMView()).getUserInfo(baseBean);
            }

            public void onError(Throwable th) {
                super.onError(th);
                ((MainContract.PrimaryView) PrimaryPresenter.this.getMView()).showError(th);
            }
        });
    }

    public void updateUserInfo(@NotNull RequestBody requestBody) {
        Api.Companion.getInstance().suppleMentUser(requestBody).subscribeOn(Schedulers.m2943io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseResourceObserver<BaseBean>() {
            public void onSubscribe(Disposable disposable) {
                PrimaryPresenter.this.addReqs(disposable);
            }

            public void onNext(BaseBean baseBean) {
                ((MainContract.PrimaryView) PrimaryPresenter.this.getMView()).updateUserInfo(baseBean);
            }

            public void onError(Throwable th) {
                super.onError(th);
                ((MainContract.PrimaryView) PrimaryPresenter.this.getMView()).showError(th);
            }
        });
    }

    public void midUnit(@NotNull RequestBody requestBody) {
        Api.Companion.getInstance().midUnit(requestBody).subscribeOn(Schedulers.m2943io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseResourceObserver<BaseBean<String>>() {
            public void onSubscribe(Disposable disposable) {
                PrimaryPresenter.this.addReqs(disposable);
            }

            public void onNext(BaseBean<String> baseBean) {
                ((MainContract.PrimaryView) PrimaryPresenter.this.getMView()).updateUserInfo(baseBean);
            }

            public void onError(Throwable th) {
                super.onError(th);
                ((MainContract.PrimaryView) PrimaryPresenter.this.getMView()).showError(th);
            }
        });
    }

    public void getNotReadCount() {
        Api.Companion.getInstance().getNotReadCount().subscribeOn(Schedulers.m2943io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseResourceObserver<BaseBean<Integer>>() {
            public void onSubscribe(Disposable disposable) {
                PrimaryPresenter.this.addReqs(disposable);
            }

            public void onNext(BaseBean<Integer> baseBean) {
                ((MainContract.PrimaryView) PrimaryPresenter.this.getMView()).getNotReadCount(baseBean);
            }

            public void onError(Throwable th) {
                super.onError(th);
                ((MainContract.PrimaryView) PrimaryPresenter.this.getMView()).showError(th);
            }
        });
    }

    public void reportingData(@NotNull final Measure measure, @NotNull RequestBody requestBody) {
        Api.Companion.getInstance().reportingData(requestBody).subscribeOn(Schedulers.m2943io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseResourceObserver<BaseBean>() {
            public void onSubscribe(Disposable disposable) {
                PrimaryPresenter.this.addReqs(disposable);
            }

            public void onNext(BaseBean baseBean) {
                ((MainContract.PrimaryView) PrimaryPresenter.this.getMView()).reporting(measure, baseBean);
            }

            public void onError(Throwable th) {
                super.onError(th);
                ((MainContract.PrimaryView) PrimaryPresenter.this.getMView()).showError(th);
            }
        });
    }
}
