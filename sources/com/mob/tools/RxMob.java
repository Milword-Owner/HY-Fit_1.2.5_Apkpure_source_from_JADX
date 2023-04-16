package com.mob.tools;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.mob.tools.utils.UIHandler;
import java.util.Iterator;

public class RxMob {

    public interface OnSubscribe<T> {
        void call(Subscriber<T> subscriber);
    }

    public enum Thread {
        IMMEDIATE,
        UI_THREAD,
        NEW_THREAD
    }

    public static <T> Subscribable<T> create(OnSubscribe<T> onSubscribe) {
        Subscribable<T> subscribable = new Subscribable<>();
        OnSubscribe unused = subscribable.onSubscribe = onSubscribe;
        return subscribable;
    }

    public static <T> Subscribable<T> just(final T... tArr) {
        return create(new QuickSubscribe<T>() {
            /* access modifiers changed from: protected */
            public void doNext(Subscriber<T> subscriber) throws Throwable {
                for (Object onNext : tArr) {
                    subscriber.onNext(onNext);
                }
            }
        });
    }

    public static <T> Subscribable<T> from(final Iterator<T> it) {
        return create(new QuickSubscribe<T>() {
            /* access modifiers changed from: protected */
            public void doNext(Subscriber<T> subscriber) throws Throwable {
                while (it.hasNext()) {
                    subscriber.onNext(it.next());
                }
            }
        });
    }

    public static final class Subscribable<T> {
        /* access modifiers changed from: private */
        public Thread observeThread;
        /* access modifiers changed from: private */
        public OnSubscribe<T> onSubscribe;
        private Thread subscribeThread;

        private Subscribable() {
        }

        public Subscribable<T> subscribeOn(Thread thread) {
            this.subscribeThread = thread;
            return this;
        }

        public Subscribable<T> observeOn(Thread thread) {
            this.observeThread = thread;
            return this;
        }

        public void subscribe(final Subscriber<T> subscriber) {
            if (this.onSubscribe == null) {
                return;
            }
            if (this.subscribeThread == Thread.UI_THREAD) {
                UIHandler.sendEmptyMessage(0, new Handler.Callback() {
                    public boolean handleMessage(Message message) {
                        Subscribable.this.onSubscribe.call(new SubscriberWarpper(Subscribable.this, subscriber));
                        return false;
                    }
                });
            } else if (this.subscribeThread == Thread.NEW_THREAD) {
                new Thread() {
                    public void run() {
                        Subscribable.this.onSubscribe.call(new SubscriberWarpper(Subscribable.this, subscriber));
                    }
                }.start();
            } else {
                this.onSubscribe.call(new SubscriberWarpper(this, subscriber));
            }
        }

        public void subscribeOnNewThreadAndObserveOnUIThread(Subscriber<T> subscriber) {
            subscribeOn(Thread.NEW_THREAD);
            observeOn(Thread.UI_THREAD);
            subscribe(subscriber);
        }
    }

    public static abstract class QuickSubscribe<T> implements OnSubscribe<T> {
        /* access modifiers changed from: protected */
        public abstract void doNext(Subscriber<T> subscriber) throws Throwable;

        public final void call(Subscriber<T> subscriber) {
            subscriber.onStart();
            try {
                doNext(subscriber);
                subscriber.onCompleted();
            } catch (Throwable th) {
                subscriber.onError(th);
            }
        }
    }

    public static class Subscriber<T> {
        private SubscriberWarpper<T> warpper;

        public void onCompleted() {
        }

        public void onError(Throwable th) {
        }

        public void onNext(T t) {
        }

        public void onStart() {
        }

        /* access modifiers changed from: private */
        public void setWarpper(SubscriberWarpper<T> subscriberWarpper) {
            this.warpper = subscriberWarpper;
        }

        public final void unsubscribe() {
            SubscriberWarpper<T> subscriberWarpper = this.warpper;
            if (subscriberWarpper != null) {
                subscriberWarpper.removeSubscriber();
                this.warpper = null;
            }
        }
    }

    private static final class SubscriberWarpper<T> extends Subscriber<T> {
        private Subscribable<T> subscribable;
        /* access modifiers changed from: private */
        public Subscriber<T> subscriber;

        public SubscriberWarpper(Subscribable<T> subscribable2, Subscriber<T> subscriber2) {
            this.subscribable = subscribable2;
            this.subscriber = subscriber2;
            subscriber2.setWarpper(this);
        }

        public void removeSubscriber() {
            this.subscriber = null;
        }

        public void onStart() {
            if (this.subscriber == null) {
                return;
            }
            if (this.subscribable.observeThread == Thread.UI_THREAD) {
                if (Thread.currentThread().getId() == Looper.getMainLooper().getThread().getId()) {
                    this.subscriber.onStart();
                    return;
                }
                Message obtain = Message.obtain();
                obtain.what = 0;
                obtain.obj = this.subscriber;
                UIHandler.sendMessage(obtain, new Handler.Callback() {
                    public boolean handleMessage(Message message) {
                        if (message == null) {
                            return false;
                        }
                        try {
                            Subscriber subscriber = (Subscriber) message.obj;
                            if (subscriber != null) {
                                subscriber.onStart();
                                return false;
                            }
                            MobLog.getInstance().crash(new Throwable("[UIThread/onStart] Network request interrupted as Subscriber is null"));
                            return false;
                        } catch (Throwable th) {
                            MobLog.getInstance().crash(th);
                            return false;
                        }
                    }
                });
            } else if (this.subscribable.observeThread == Thread.NEW_THREAD) {
                new Thread() {
                    public void run() {
                        if (SubscriberWarpper.this.subscriber != null) {
                            SubscriberWarpper.this.subscriber.onStart();
                        } else {
                            MobLog.getInstance().crash(new Throwable("[NewThread/onStart] Network request interrupted as Subscriber is null"));
                        }
                    }
                }.start();
            } else {
                this.subscriber.onStart();
            }
        }

        public void onNext(final T t) {
            if (this.subscriber == null) {
                return;
            }
            if (this.subscribable.observeThread == Thread.UI_THREAD) {
                if (Thread.currentThread().getId() == Looper.getMainLooper().getThread().getId()) {
                    this.subscriber.onNext(t);
                    return;
                }
                Message obtain = Message.obtain();
                obtain.what = 0;
                obtain.obj = this.subscriber;
                UIHandler.sendMessage(obtain, new Handler.Callback() {
                    public boolean handleMessage(Message message) {
                        if (message == null) {
                            return false;
                        }
                        try {
                            Subscriber subscriber = (Subscriber) message.obj;
                            if (subscriber != null) {
                                subscriber.onNext(t);
                                return false;
                            }
                            MobLog.getInstance().crash(new Throwable("[UIThread/onNext] Network request interrupted as Subscriber is null"));
                            return false;
                        } catch (Throwable th) {
                            MobLog.getInstance().crash(th);
                            return false;
                        }
                    }
                });
            } else if (this.subscribable.observeThread == Thread.NEW_THREAD) {
                new Thread() {
                    public void run() {
                        if (SubscriberWarpper.this.subscriber != null) {
                            SubscriberWarpper.this.subscriber.onNext(t);
                        } else {
                            MobLog.getInstance().crash(new Throwable("[NewThread/onNext] Network request interrupted as Subscriber is null"));
                        }
                    }
                }.start();
            } else {
                this.subscriber.onNext(t);
            }
        }

        public void onCompleted() {
            if (this.subscriber == null) {
                return;
            }
            if (this.subscribable.observeThread == Thread.UI_THREAD) {
                if (Thread.currentThread().getId() == Looper.getMainLooper().getThread().getId()) {
                    this.subscriber.onCompleted();
                    removeSubscriber();
                    return;
                }
                Message obtain = Message.obtain();
                obtain.what = 0;
                obtain.obj = this.subscriber;
                UIHandler.sendMessage(obtain, new Handler.Callback() {
                    public boolean handleMessage(Message message) {
                        if (message == null) {
                            return false;
                        }
                        try {
                            Subscriber subscriber = (Subscriber) message.obj;
                            if (subscriber != null) {
                                subscriber.onCompleted();
                                SubscriberWarpper.this.removeSubscriber();
                                return false;
                            }
                            MobLog.getInstance().crash(new Throwable("[UIThread/onComplete] Network request interrupted as Subscriber is null"));
                            return false;
                        } catch (Throwable th) {
                            MobLog.getInstance().crash(th);
                            return false;
                        }
                    }
                });
            } else if (this.subscribable.observeThread == Thread.NEW_THREAD) {
                new Thread() {
                    public void run() {
                        if (SubscriberWarpper.this.subscriber != null) {
                            SubscriberWarpper.this.subscriber.onCompleted();
                            SubscriberWarpper.this.removeSubscriber();
                            return;
                        }
                        MobLog.getInstance().crash(new Throwable("[NewThread/onComplete] Network request interrupted as Subscriber is null"));
                    }
                }.start();
            } else {
                this.subscriber.onCompleted();
                removeSubscriber();
            }
        }

        public void onError(final Throwable th) {
            if (this.subscriber == null) {
                return;
            }
            if (this.subscribable.observeThread == Thread.UI_THREAD) {
                if (Thread.currentThread().getId() == Looper.getMainLooper().getThread().getId()) {
                    this.subscriber.onError(th);
                    removeSubscriber();
                    return;
                }
                Message obtain = Message.obtain();
                obtain.what = 0;
                obtain.obj = this.subscriber;
                UIHandler.sendMessage(obtain, new Handler.Callback() {
                    public boolean handleMessage(Message message) {
                        if (message == null) {
                            return false;
                        }
                        try {
                            Subscriber subscriber = (Subscriber) message.obj;
                            if (subscriber != null) {
                                subscriber.onError(th);
                                SubscriberWarpper.this.removeSubscriber();
                                return false;
                            }
                            MobLog.getInstance().crash(new Throwable("[UIThread/onError] Network request interrupted as Subscriber is null"));
                            return false;
                        } catch (Throwable th) {
                            MobLog.getInstance().crash(th);
                            return false;
                        }
                    }
                });
            } else if (this.subscribable.observeThread == Thread.NEW_THREAD) {
                new Thread() {
                    public void run() {
                        if (SubscriberWarpper.this.subscriber != null) {
                            SubscriberWarpper.this.subscriber.onError(th);
                            SubscriberWarpper.this.removeSubscriber();
                            return;
                        }
                        MobLog.getInstance().crash(new Throwable("[NewThread/onError] Network request interrupted as Subscriber is null"));
                    }
                }.start();
            } else {
                this.subscriber.onError(th);
                removeSubscriber();
            }
        }
    }
}
