package com.huayu.tzc.base;

import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001:\u0002\u0002\u0003¨\u0006\u0004"}, mo21895d2 = {"Lcom/huayu/tzc/base/BaseContract;", "", "BasePresenter", "BaseView", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
/* compiled from: BaseContract.kt */
public interface BaseContract {

    @Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&¨\u0006\u0006"}, mo21895d2 = {"Lcom/huayu/tzc/base/BaseContract$BaseView;", "", "showError", "", "e", "", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
    /* compiled from: BaseContract.kt */
    public interface BaseView {
        void showError(@Nullable Throwable th);
    }

    @Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J\u0015\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00028\u0000H&¢\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\u0004H&J\b\u0010\b\u001a\u00020\u0004H&¨\u0006\t"}, mo21895d2 = {"Lcom/huayu/tzc/base/BaseContract$BasePresenter;", "T", "", "attachView", "", "view", "(Ljava/lang/Object;)V", "cancelAll", "detachView", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
    /* compiled from: BaseContract.kt */
    public interface BasePresenter<T> {
        void attachView(T t);

        void cancelAll();

        void detachView();
    }
}
