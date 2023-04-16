package com.huayu.tzc.base;

import com.huayu.tzc.base.BaseContract;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import p015io.reactivex.disposables.Disposable;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\b\u0016\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0006H\u0004J\u0015\u0010\u0016\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u000fJ\b\u0010\u0018\u001a\u00020\u0014H\u0016J\b\u0010\u0019\u001a\u00020\u0014H\u0016R \u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001e\u0010\u000b\u001a\u0004\u0018\u00018\u0000X\u000e¢\u0006\u0010\n\u0002\u0010\u0010\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0011\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0012X\u000e¢\u0006\u0002\n\u0000¨\u0006\u001a"}, mo21895d2 = {"Lcom/huayu/tzc/base/MainPresenter;", "T", "Lcom/huayu/tzc/base/BaseContract$BasePresenter;", "()V", "listReqs", "", "Lio/reactivex/disposables/Disposable;", "getListReqs", "()Ljava/util/List;", "setListReqs", "(Ljava/util/List;)V", "mView", "getMView", "()Ljava/lang/Object;", "setMView", "(Ljava/lang/Object;)V", "Ljava/lang/Object;", "weakView", "Ljava/lang/ref/WeakReference;", "addReqs", "", "disposable", "attachView", "view", "cancelAll", "detachView", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
/* compiled from: MainPresenter.kt */
public class MainPresenter<T> implements BaseContract.BasePresenter<T> {
    @NotNull
    private List<Disposable> listReqs = new ArrayList();
    @Nullable
    private T mView;
    private WeakReference<T> weakView;

    /* access modifiers changed from: protected */
    @Nullable
    public final T getMView() {
        return this.mView;
    }

    /* access modifiers changed from: protected */
    public final void setMView(@Nullable T t) {
        this.mView = t;
    }

    /* access modifiers changed from: protected */
    @NotNull
    public final List<Disposable> getListReqs() {
        return this.listReqs;
    }

    /* access modifiers changed from: protected */
    public final void setListReqs(@NotNull List<Disposable> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.listReqs = list;
    }

    public void attachView(T t) {
        this.weakView = new WeakReference<>(t);
        WeakReference<T> weakReference = this.weakView;
        if (weakReference == null) {
            Intrinsics.throwNpe();
        }
        this.mView = weakReference.get();
    }

    public void detachView() {
        if (this.mView != null) {
            this.mView = null;
            WeakReference<T> weakReference = this.weakView;
            if (weakReference == null) {
                Intrinsics.throwNpe();
            }
            weakReference.clear();
            this.weakView = null;
        }
    }

    public void cancelAll() {
        for (Disposable dispose : this.listReqs) {
            dispose.dispose();
        }
    }

    /* access modifiers changed from: protected */
    public final void addReqs(@NotNull Disposable disposable) {
        Intrinsics.checkParameterIsNotNull(disposable, "disposable");
        this.listReqs.add(disposable);
    }
}
