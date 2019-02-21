package com.dreamsocket.net.okhttp;

import io.reactivex.ObservableEmitter;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import java.io.IOException;


public class OkHTTPCallback<T> implements Callback{

    protected IOkHTTPResponseTranslator<T> m_responseDecoder;
    protected ObservableEmitter<? super T> m_emitter;


    public OkHTTPCallback(ObservableEmitter<? super T> p_emitter, IOkHTTPResponseTranslator<T> p_decoder) {
        this.m_emitter = p_emitter;
        this.m_responseDecoder = p_decoder;
    }

    protected void dispatchError(Throwable p_exception){
        ObservableEmitter<? super T> emitter = this.m_emitter;

        this.m_emitter = null;

        if(emitter != null && !emitter.isDisposed()) {
            emitter.onError(p_exception);
        }
    }


    public void onFailure(Call p_call, IOException p_exception){
        this.dispatchError(p_exception);
    }


    public void onResponse(Call p_call, Response p_response) throws IOException{
        if(this.m_emitter != null && !this.m_emitter.isDisposed()) {
            try {
                this.m_emitter.onNext(this.m_responseDecoder.decode(p_response));
                this.m_emitter.onComplete();
            }
            catch (Throwable e) {
                this.dispatchError(e);
            }
        }
        this.m_emitter = null;
    }
}
