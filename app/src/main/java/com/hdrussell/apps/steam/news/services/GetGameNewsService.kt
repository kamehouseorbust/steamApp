package com.hdrussell.apps.steam.news.services

import com.dreamsocket.net.okhttp.IOkHTTPResponseTranslator
import com.dreamsocket.net.okhttp.OkHTTPCallback
import com.dreamsocket.rx.RxScheduler
import com.hdrussell.apps.steam.news.data.GameNewsItems
import com.hdrussell.apps.steam.news.data.decoders.GameNewsItemsDecoder
import io.reactivex.Observable
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import org.json.JSONTokener

class GetGameNewsService(protected var m_client: OkHttpClient) {

    var m_url:String = "https://api.steampowered.com/ISteamNews/GetNewsForApp/v2/"

    fun execute(p_params:GetGameNewsParams): Observable<GameNewsItems> {
        return Observable.create<GameNewsItems> {emitter ->
            try {
                var url = HttpUrl.parse(this.m_url)!!.newBuilder()
                        .addQueryParameter("appid", p_params.appid.toString())
                        .addQueryParameter("count", p_params.count.toString())
                        .addQueryParameter("maxlength", p_params.maxlength.toString())
                        .addQueryParameter("format", p_params.format)
                        .build()


                val request = Request.Builder()
                        .url(url)
                        .build()

                val call = this.m_client.newCall(request)

                emitter.setCancellable { call.cancel() }

                call.enqueue(OkHTTPCallback(emitter, Decoder()))
            } catch(p_error:Throwable) {
                emitter.onError(p_error)
            }
        }.compose(RxScheduler.apply())
    }


    class Decoder : IOkHTTPResponseTranslator<GameNewsItems> {
        @Throws(Throwable::class)
        override fun decode(p_input: Response): GameNewsItems {
            println(p_input.code())
            return GameNewsItemsDecoder().decode(JSONTokener(p_input.body()!!.string()).nextValue() as JSONObject)
        }
    }
}