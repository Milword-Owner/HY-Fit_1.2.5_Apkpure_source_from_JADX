package com.huayu.tzc.presenter;

import com.huayu.tzc.base.BaseBean;
import com.huayu.tzc.base.BaseListBean;
import com.huayu.tzc.base.MainPresenter;
import com.huayu.tzc.bean.Measure;
import com.huayu.tzc.contract.MainContract;
import com.huayu.tzc.http.Api;
import com.huayu.tzc.http.BaseResourceObserver;
import okhttp3.RequestBody;
import org.jetbrains.annotations.NotNull;
import p015io.reactivex.android.schedulers.AndroidSchedulers;
import p015io.reactivex.disposables.Disposable;
import p015io.reactivex.schedulers.Schedulers;

public class HomePresenter extends MainPresenter<MainContract.HomeView> implements MainContract.HomePresenter {
    public void reporting(@NotNull RequestBody requestBody) {
        Api.Companion.getInstance().reporting(requestBody).subscribeOn(Schedulers.m2943io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseResourceObserver<BaseBean<String>>() {
            public void onSubscribe(Disposable disposable) {
                HomePresenter.this.addReqs(disposable);
            }

            public void onNext(BaseBean baseBean) {
                ((MainContract.HomeView) HomePresenter.this.getMView()).reporting(baseBean);
            }

            public void onError(Throwable th) {
                super.onError(th);
                ((MainContract.HomeView) HomePresenter.this.getMView()).showError(th);
            }
        });
    }

    public void reportingData(RequestBody requestBody) {
        Api.Companion.getInstance().reportingData(requestBody).subscribeOn(Schedulers.m2943io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseResourceObserver<BaseBean>() {
            public void onSubscribe(Disposable disposable) {
                HomePresenter.this.addReqs(disposable);
            }

            public void onNext(BaseBean baseBean) {
                ((MainContract.HomeView) HomePresenter.this.getMView()).reporting(baseBean);
            }

            public void onError(Throwable th) {
                super.onError(th);
                ((MainContract.HomeView) HomePresenter.this.getMView()).showError(th);
            }
        });
    }

    public void fitFat(String str, RequestBody requestBody) {
        Api.Companion.getInstance().fitFat(str, requestBody).subscribeOn(Schedulers.m2943io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseResourceObserver<String>() {
            public void onNext(String str) {
            }

            public void onSubscribe(Disposable disposable) {
                HomePresenter.this.addReqs(disposable);
            }

            public void onError(Throwable th) {
                ((MainContract.HomeView) HomePresenter.this.getMView()).showError(th);
            }
        });
    }

    public void fitWeight(String str, RequestBody requestBody) {
        Api.Companion.getInstance().fitWeight(str, requestBody).subscribeOn(Schedulers.m2943io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseResourceObserver<String>() {
            public void onNext(String str) {
            }

            public void onSubscribe(Disposable disposable) {
                HomePresenter.this.addReqs(disposable);
            }

            public void onError(Throwable th) {
                ((MainContract.HomeView) HomePresenter.this.getMView()).showError(th);
            }
        });
    }

    public void getMeasures(int i, int i2, int i3) {
        Api.Companion.getInstance().getMeasures(i, i2, i3).subscribeOn(Schedulers.m2943io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseResourceObserver<BaseListBean<Measure>>() {
            public void onSubscribe(Disposable disposable) {
                HomePresenter.this.addReqs(disposable);
            }

            public void onNext(BaseListBean<Measure> baseListBean) {
                ((MainContract.HomeView) HomePresenter.this.getMView()).getMeasures(baseListBean);
            }

            public void onError(Throwable th) {
                super.onError(th);
                ((MainContract.HomeView) HomePresenter.this.getMView()).showError(th);
            }
        });
    }

    public void deleteMeasures(int i) {
        Api.Companion.getInstance().deleteMeasures(i).subscribeOn(Schedulers.m2943io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseResourceObserver<BaseBean>() {
            public void onSubscribe(Disposable disposable) {
                HomePresenter.this.addReqs(disposable);
            }

            public void onNext(BaseBean baseBean) {
                ((MainContract.HomeView) HomePresenter.this.getMView()).reporting(baseBean);
            }

            public void onError(Throwable th) {
                super.onError(th);
                ((MainContract.HomeView) HomePresenter.this.getMView()).showError(th);
            }
        });
    }
}
