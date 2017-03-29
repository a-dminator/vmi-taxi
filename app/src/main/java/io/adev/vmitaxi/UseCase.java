package io.adev.vmitaxi;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;

public abstract class UseCase<T, C> {

    private final Scheduler workScheduler;
    public UseCase(Scheduler workScheduler) {
        this.workScheduler = workScheduler;
    }

    private DisposableObserver<T> observer;

    public void execute(DisposableObserver<T> observer, C critera) {
        this.observer = buildObservable(critera)
                .subscribeOn(workScheduler)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(observer);
    }

    public void dispose() {
        if (!observer.isDisposed()) {
            observer.dispose();
        }
    }

    protected abstract Observable<T> buildObservable(C criteria);

}
