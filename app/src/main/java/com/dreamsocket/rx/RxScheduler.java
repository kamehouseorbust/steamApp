package com.dreamsocket.rx;


import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RxScheduler {

    protected static final ObservableTransformer<Object, Object> k_SCHEDULE_TRANSFORMER = observable ->
            observable
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());



    @SuppressWarnings("unchecked")
    public static <T> ObservableTransformer<T, T> apply() {
        return (ObservableTransformer<T, T>) k_SCHEDULE_TRANSFORMER;
    }
}
