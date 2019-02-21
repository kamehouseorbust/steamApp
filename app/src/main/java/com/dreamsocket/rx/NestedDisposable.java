package com.dreamsocket.rx;


import io.reactivex.disposables.Disposable;

public class NestedDisposable {

    public Disposable disposable;


    public NestedDisposable dispose(){
        Disposable d = this.disposable;

        this.disposable = null;

        if(d != null && !d.isDisposed()){
            d.dispose();
        }
        return this;
    }
}
