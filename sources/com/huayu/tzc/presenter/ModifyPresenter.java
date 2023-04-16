package com.huayu.tzc.presenter;

import com.huayu.tzc.base.BaseBean;
import com.huayu.tzc.base.MainPresenter;
import com.huayu.tzc.contract.MainContract;
import com.huayu.tzc.http.Api;
import com.huayu.tzc.http.BaseResourceObserver;
import org.jetbrains.annotations.Nullable;
import p015io.reactivex.android.schedulers.AndroidSchedulers;
import p015io.reactivex.disposables.Disposable;
import p015io.reactivex.schedulers.Schedulers;

public class ModifyPresenter extends MainPresenter<MainContract.ModifyView> implements MainContract.ModifyPresenter {
    public void modifyPass(@Nullable String str, @Nullable String str2) {
        Api.Companion.getInstance().modifyPass(str, str2).subscribeOn(Schedulers.m2943io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseResourceObserver<BaseBean<String>>() {
            public void onSubscribe(Disposable disposable) {
                ModifyPresenter.this.addReqs(disposable);
            }

            public void onNext(BaseBean<String> baseBean) {
                ((MainContract.ModifyView) ModifyPresenter.this.getMView()).modifyPass(baseBean);
            }

            public void onError(Throwable th) {
                super.onError(th);
                ((MainContract.ModifyView) ModifyPresenter.this.getMView()).showError(th);
            }
        });
    }
}
