package com.huayu.tzc.presenter;

import com.huayu.tzc.base.BaseBean;
import com.huayu.tzc.base.MainPresenter;
import com.huayu.tzc.bean.Trend;
import com.huayu.tzc.contract.MainContract;
import com.huayu.tzc.http.Api;
import com.huayu.tzc.http.BaseResourceObserver;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import p015io.reactivex.android.schedulers.AndroidSchedulers;
import p015io.reactivex.disposables.Disposable;
import p015io.reactivex.schedulers.Schedulers;

public class TrendPresenter extends MainPresenter<MainContract.TrendView> implements MainContract.TrendPresenter {
    public void getTrend(int i, @NotNull String str, @NotNull String str2, @NotNull String str3) {
        Api.Companion.getInstance().getTrend(i, str, str2, str3).subscribeOn(Schedulers.m2943io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseResourceObserver<BaseBean<List<Trend>>>() {
            public void onSubscribe(Disposable disposable) {
                TrendPresenter.this.addReqs(disposable);
            }

            public void onNext(BaseBean<List<Trend>> baseBean) {
                ((MainContract.TrendView) TrendPresenter.this.getMView()).getTrend(baseBean);
            }

            public void onError(Throwable th) {
                super.onError(th);
                ((MainContract.TrendView) TrendPresenter.this.getMView()).showError(th);
            }
        });
    }
}
