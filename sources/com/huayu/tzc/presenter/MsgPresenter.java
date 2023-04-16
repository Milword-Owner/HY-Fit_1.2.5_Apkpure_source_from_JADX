package com.huayu.tzc.presenter;

import com.huayu.tzc.base.BaseBean;
import com.huayu.tzc.base.BaseListBean;
import com.huayu.tzc.base.MainPresenter;
import com.huayu.tzc.bean.MsgHistory;
import com.huayu.tzc.contract.MainContract;
import com.huayu.tzc.http.Api;
import com.huayu.tzc.http.BaseResourceObserver;
import okhttp3.RequestBody;
import org.jetbrains.annotations.NotNull;
import p015io.reactivex.android.schedulers.AndroidSchedulers;
import p015io.reactivex.disposables.Disposable;
import p015io.reactivex.schedulers.Schedulers;

public class MsgPresenter extends MainPresenter<MainContract.MsgView> implements MainContract.MsgPresenter {
    public void getMsgHistory(int i, int i2) {
        Api.Companion.getInstance().getMsgHistory(i, i2).subscribeOn(Schedulers.m2943io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseResourceObserver<BaseListBean<MsgHistory>>() {
            public void onSubscribe(Disposable disposable) {
                MsgPresenter.this.addReqs(disposable);
            }

            public void onNext(BaseListBean<MsgHistory> baseListBean) {
                ((MainContract.MsgView) MsgPresenter.this.getMView()).getMsgHistory(baseListBean);
            }

            public void onError(Throwable th) {
                super.onError(th);
                ((MainContract.MsgView) MsgPresenter.this.getMView()).showError(th);
            }
        });
    }

    public void sendMsg(@NotNull RequestBody requestBody) {
        Api.Companion.getInstance().sendMsg(requestBody).subscribeOn(Schedulers.m2943io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseResourceObserver<BaseBean<String>>() {
            public void onSubscribe(Disposable disposable) {
                MsgPresenter.this.addReqs(disposable);
            }

            public void onNext(BaseBean<String> baseBean) {
                ((MainContract.MsgView) MsgPresenter.this.getMView()).sendMsg(baseBean);
            }

            public void onError(Throwable th) {
                super.onError(th);
                ((MainContract.MsgView) MsgPresenter.this.getMView()).showError(th);
            }
        });
    }

    public void getNewMsg(int i) {
        Api.Companion.getInstance().getNewMsg(i).subscribeOn(Schedulers.m2943io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseResourceObserver<BaseListBean<MsgHistory>>() {
            public void onSubscribe(Disposable disposable) {
                MsgPresenter.this.addReqs(disposable);
            }

            public void onNext(BaseListBean<MsgHistory> baseListBean) {
                ((MainContract.MsgView) MsgPresenter.this.getMView()).getNewMsg(baseListBean);
            }

            public void onError(Throwable th) {
                super.onError(th);
                ((MainContract.MsgView) MsgPresenter.this.getMView()).showError(th);
            }
        });
    }
}
