package com.huayu.tzc.presenter;

import com.huayu.tzc.base.BaseBean;
import com.huayu.tzc.base.MainPresenter;
import com.huayu.tzc.contract.MainContract;
import com.huayu.tzc.http.Api;
import com.huayu.tzc.http.BaseResourceObserver;
import okhttp3.RequestBody;
import org.jetbrains.annotations.NotNull;
import p015io.reactivex.android.schedulers.AndroidSchedulers;
import p015io.reactivex.disposables.Disposable;
import p015io.reactivex.schedulers.Schedulers;

public class TargetPresenter extends MainPresenter<MainContract.TargetView> implements MainContract.TargetPresenter {
    public void updateMem(@NotNull RequestBody requestBody) {
        Api.Companion.getInstance().updateMem(requestBody).subscribeOn(Schedulers.m2943io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseResourceObserver<BaseBean<String>>() {
            public void onSubscribe(Disposable disposable) {
                TargetPresenter.this.addReqs(disposable);
            }

            public void onNext(BaseBean baseBean) {
                ((MainContract.TargetView) TargetPresenter.this.getMView()).updateMem(baseBean);
            }

            public void onError(Throwable th) {
                super.onError(th);
                ((MainContract.TargetView) TargetPresenter.this.getMView()).showError(th);
            }
        });
    }

    public void updateUserInfo(@NotNull RequestBody requestBody) {
        Api.Companion.getInstance().suppleMentUser(requestBody).subscribeOn(Schedulers.m2943io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseResourceObserver<BaseBean<String>>() {
            public void onSubscribe(Disposable disposable) {
                TargetPresenter.this.addReqs(disposable);
            }

            public void onNext(BaseBean<String> baseBean) {
                ((MainContract.TargetView) TargetPresenter.this.getMView()).updateUserInfo(baseBean);
            }

            public void onError(Throwable th) {
                super.onError(th);
                ((MainContract.TargetView) TargetPresenter.this.getMView()).showError(th);
            }
        });
    }
}
