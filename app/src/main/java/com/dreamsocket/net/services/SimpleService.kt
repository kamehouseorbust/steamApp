package com.dreamsocket.net.services

import com.dreamsocket.net.okhttp.OkHTTPCallback
import com.dreamsocket.net.okhttp.OkHTTPResponseTranslator
import com.dreamsocket.rx.RxScheduler
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response



class SimpleService(protected var m_client: OkHttpClient) {

    fun execute(p_url: String): Observable<Response> {
        return Observable.create<Response> { emitter ->
            try {
                val call = this.m_client.newCall(Request.Builder().url(p_url).build())

                emitter.setCancellable { call.cancel() }

                call.enqueue(OkHTTPCallback(emitter, OkHTTPResponseTranslator()))
            }
            catch (p_error: Throwable) {
                emitter.onError(p_error)
            }
        }.compose(RxScheduler.apply())
    }
}

