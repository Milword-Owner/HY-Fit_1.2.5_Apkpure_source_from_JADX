package com.huayu.tzc.presenter;

import com.huayu.tzc.base.BaseBean;
import com.huayu.tzc.base.BaseListBean;
import com.huayu.tzc.base.MainPresenter;
import com.huayu.tzc.bean.Tweet;
import com.huayu.tzc.contract.MainContract;
import com.huayu.tzc.http.Api;
import com.huayu.tzc.http.BaseResourceObserver;
import p015io.reactivex.android.schedulers.AndroidSchedulers;
import p015io.reactivex.disposables.Disposable;
import p015io.reactivex.schedulers.Schedulers;

public class TweetPresenter extends MainPresenter<MainContract.TweetView> implements MainContract.TweetPresenter {
    public void getTweetList(int i, int i2) {
        Api.Companion.getInstance().getTweetList(i, i2).subscribeOn(Schedulers.m2943io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseResourceObserver<BaseListBean<Tweet>>() {
            public void onSubscribe(Disposable disposable) {
                TweetPresenter.this.addReqs(disposable);
            }

            public void onNext(BaseListBean<Tweet> baseListBean) {
                ((MainContract.TweetView) TweetPresenter.this.getMView()).getTweetList(baseListBean);
            }

            public void onError(Throwable th) {
                super.onError(th);
                ((MainContract.TweetView) TweetPresenter.this.getMView()).showError(th);
            }
        });
    }

    public void getStatus(int i) {
        Api.Companion.getInstance().getStatus(i).subscribeOn(Schedulers.m2943io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseResourceObserver<BaseBean<Boolean>>() {
            public void onSubscribe(Disposable disposable) {
                TweetPresenter.this.addReqs(disposable);
            }

            public void onNext(BaseBean<Boolean> baseBean) {
                ((MainContract.TweetView) TweetPresenter.this.getMView()).getStatus(baseBean);
            }

            public void onError(Throwable th) {
                super.onError(th);
                ((MainContract.TweetView) TweetPresenter.this.getMView()).showError(th);
            }
        });
    }

    public void giveUpStatus(int i) {
        Api.Companion.getInstance().giveUpStatus(i).subscribeOn(Schedulers.m2943io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseResourceObserver<BaseBean>() {
            public void onNext(BaseBean baseBean) {
            }

            public void onSubscribe(Disposable disposable) {
                TweetPresenter.this.addReqs(disposable);
            }

            public void onError(Throwable th) {
                super.onError(th);
                ((MainContract.TweetView) TweetPresenter.this.getMView()).showError(th);
            }
        });
    }

    public void giveUpCount(int i) {
        Api.Companion.getInstance().giveUpCount(i).subscribeOn(Schedulers.m2943io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseResourceObserver<BaseBean<Integer>>() {
            public void onSubscribe(Disposable disposable) {
                TweetPresenter.this.addReqs(disposable);
            }

            public void onNext(BaseBean<Integer> baseBean) {
                ((MainContract.TweetView) TweetPresenter.this.getMView()).giveUpCount(baseBean);
            }

            public void onError(Throwable th) {
                super.onError(th);
                ((MainContract.TweetView) TweetPresenter.this.getMView()).showError(th);
            }
        });
    }
}
