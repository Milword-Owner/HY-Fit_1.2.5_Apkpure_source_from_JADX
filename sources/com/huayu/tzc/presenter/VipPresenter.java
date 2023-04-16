package com.huayu.tzc.presenter;

import com.huayu.tzc.base.BaseBean;
import com.huayu.tzc.base.MainPresenter;
import com.huayu.tzc.bean.VipBean;
import com.huayu.tzc.contract.MainContract;
import com.huayu.tzc.http.Api;
import com.huayu.tzc.http.BaseResourceObserver;
import org.jetbrains.annotations.NotNull;
import p015io.reactivex.android.schedulers.AndroidSchedulers;
import p015io.reactivex.disposables.Disposable;
import p015io.reactivex.schedulers.Schedulers;

public class VipPresenter extends MainPresenter<MainContract.VipView> implements MainContract.VipPresenter {
    public void getVipMsg() {
        Api.Companion.getInstance().getVipMsg().subscribeOn(Schedulers.m2943io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseResourceObserver<BaseBean<VipBean>>() {
            public void onSubscribe(Disposable disposable) {
                VipPresenter.this.addReqs(disposable);
            }

            public void onNext(BaseBean<VipBean> baseBean) {
                ((MainContract.VipView) VipPresenter.this.getMView()).getVipMsg(baseBean);
            }

            public void onError(Throwable th) {
                super.onError(th);
                ((MainContract.VipView) VipPresenter.this.getMView()).showError(th);
            }
        });
    }

    public void upgradeVip(@NotNull String str) {
        Api.Companion.getInstance().upgradeVip(str).subscribeOn(Schedulers.m2943io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseResourceObserver<BaseBean<String>>() {
            public void onSubscribe(Disposable disposable) {
                VipPresenter.this.addReqs(disposable);
            }

            public void onNext(BaseBean baseBean) {
                ((MainContract.VipView) VipPresenter.this.getMView()).upgradeVip(baseBean);
            }

            public void onError(Throwable th) {
                super.onError(th);
                ((MainContract.VipView) VipPresenter.this.getMView()).showError(th);
            }
        });
    }

    public void modifyUpgrade(@NotNull String str) {
        Api.Companion.getInstance().modifyUpgrade(str).subscribeOn(Schedulers.m2943io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseResourceObserver<BaseBean<String>>() {
            public void onSubscribe(Disposable disposable) {
                VipPresenter.this.addReqs(disposable);
            }

            public void onNext(BaseBean baseBean) {
                ((MainContract.VipView) VipPresenter.this.getMView()).modifyUpgrade(baseBean);
            }

            public void onError(Throwable th) {
                super.onError(th);
                ((MainContract.VipView) VipPresenter.this.getMView()).showError(th);
            }
        });
    }
}
