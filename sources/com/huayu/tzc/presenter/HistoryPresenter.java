package com.huayu.tzc.presenter;

import com.huayu.tzc.base.BaseBean;
import com.huayu.tzc.base.BaseListBean;
import com.huayu.tzc.base.MainPresenter;
import com.huayu.tzc.bean.Measure;
import com.huayu.tzc.contract.MainContract;
import com.huayu.tzc.http.Api;
import com.huayu.tzc.http.BaseResourceObserver;
import org.jetbrains.annotations.NotNull;
import p015io.reactivex.android.schedulers.AndroidSchedulers;
import p015io.reactivex.disposables.Disposable;
import p015io.reactivex.schedulers.Schedulers;

public class HistoryPresenter extends MainPresenter<MainContract.HistoryView> implements MainContract.HistoryPresenter {
    public void deleteMeasures(@NotNull String str) {
        Api.Companion.getInstance().batchDelete(str).subscribeOn(Schedulers.m2943io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseResourceObserver<BaseBean>() {
            public void onNext(BaseBean baseBean) {
            }

            public void onSubscribe(Disposable disposable) {
                HistoryPresenter.this.addReqs(disposable);
            }

            public void onError(Throwable th) {
                super.onError(th);
                ((MainContract.HistoryView) HistoryPresenter.this.getMView()).showError(th);
            }
        });
    }

    public void getMeasures(int i, int i2, int i3) {
        Api.Companion.getInstance().getMeasures(i, i2, i3).subscribeOn(Schedulers.m2943io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseResourceObserver<BaseListBean<Measure>>() {
            public void onSubscribe(Disposable disposable) {
                HistoryPresenter.this.addReqs(disposable);
            }

            public void onNext(BaseListBean<Measure> baseListBean) {
                ((MainContract.HistoryView) HistoryPresenter.this.getMView()).getMeasures(baseListBean);
            }

            public void onError(Throwable th) {
                super.onError(th);
                ((MainContract.HistoryView) HistoryPresenter.this.getMView()).showError(th);
            }
        });
    }
}
