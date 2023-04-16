package com.huayu.tzc.presenter;

import com.huayu.tzc.base.BaseBean;
import com.huayu.tzc.base.MainPresenter;
import com.huayu.tzc.bean.Member;
import com.huayu.tzc.contract.MainContract;
import com.huayu.tzc.http.Api;
import com.huayu.tzc.http.BaseResourceObserver;
import java.util.List;
import okhttp3.RequestBody;
import org.jetbrains.annotations.NotNull;
import p015io.reactivex.android.schedulers.AndroidSchedulers;
import p015io.reactivex.disposables.Disposable;
import p015io.reactivex.schedulers.Schedulers;

public class UserPresenter extends MainPresenter<MainContract.UserView> implements MainContract.UserPresenter {
    public void updateUserInfo(@NotNull RequestBody requestBody) {
        Api.Companion.getInstance().suppleMentUser(requestBody).subscribeOn(Schedulers.m2943io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseResourceObserver<BaseBean>() {
            public void onSubscribe(Disposable disposable) {
                UserPresenter.this.addReqs(disposable);
            }

            public void onNext(BaseBean baseBean) {
                ((MainContract.UserView) UserPresenter.this.getMView()).updateUserInfo(baseBean);
            }

            public void onError(Throwable th) {
                super.onError(th);
                ((MainContract.UserView) UserPresenter.this.getMView()).showError(th);
            }
        });
    }

    public void getMemList() {
        Api.Companion.getInstance().getMemList().subscribeOn(Schedulers.m2943io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseResourceObserver<BaseBean<List<Member>>>() {
            public void onSubscribe(Disposable disposable) {
                UserPresenter.this.addReqs(disposable);
            }

            public void onNext(BaseBean<List<Member>> baseBean) {
                ((MainContract.UserView) UserPresenter.this.getMView()).getMineList(baseBean);
            }

            public void onError(Throwable th) {
                super.onError(th);
                ((MainContract.UserView) UserPresenter.this.getMView()).showError(th);
            }
        });
    }

    public void addMem(@NotNull RequestBody requestBody) {
        Api.Companion.getInstance().addMem(requestBody).subscribeOn(Schedulers.m2943io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseResourceObserver<BaseBean>() {
            public void onSubscribe(Disposable disposable) {
                UserPresenter.this.addReqs(disposable);
            }

            public void onNext(BaseBean baseBean) {
                ((MainContract.UserView) UserPresenter.this.getMView()).addMem(baseBean);
            }

            public void onError(Throwable th) {
                super.onError(th);
                ((MainContract.UserView) UserPresenter.this.getMView()).showError(th);
            }
        });
    }

    public void deleteMem(@NotNull String str) {
        Api.Companion.getInstance().deleteMem(str).subscribeOn(Schedulers.m2943io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseResourceObserver<BaseBean>() {
            public void onSubscribe(Disposable disposable) {
                UserPresenter.this.addReqs(disposable);
            }

            public void onNext(BaseBean baseBean) {
                ((MainContract.UserView) UserPresenter.this.getMView()).deleteMem(baseBean);
            }

            public void onError(Throwable th) {
                super.onError(th);
                ((MainContract.UserView) UserPresenter.this.getMView()).showError(th);
            }
        });
    }

    public void updateMem(@NotNull RequestBody requestBody) {
        Api.Companion.getInstance().updateMem(requestBody).subscribeOn(Schedulers.m2943io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseResourceObserver<BaseBean>() {
            public void onSubscribe(Disposable disposable) {
                UserPresenter.this.addReqs(disposable);
            }

            public void onNext(BaseBean baseBean) {
                ((MainContract.UserView) UserPresenter.this.getMView()).updateMem(baseBean);
            }

            public void onError(Throwable th) {
                super.onError(th);
                ((MainContract.UserView) UserPresenter.this.getMView()).showError(th);
            }
        });
    }
}
