package com.hdrussell.apps.steam.profile.services

import com.dreamsocket.net.okhttp.IOkHTTPResponseTranslator
import com.dreamsocket.net.okhttp.OkHTTPCallback
import com.dreamsocket.rx.RxScheduler
import com.hdrussell.apps.steam.profile.data.PlayerSummaries
import com.hdrussell.apps.steam.profile.data.decoders.PlayerSummariesDecoder
import io.reactivex.Observable
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import org.json.JSONTokener

class GetPlayerSummaryService(protected var m_client: OkHttpClient) {

    var m_url:String = "http://api.steampowered.com/ISteamUser/GetPlayerSummaries/v0002/"

    fun execute(p_params: GetPlayerSummaryParams): Observable<PlayerSummaries> {
        return Observable.create<PlayerSummaries> { emitter ->
            try {
                var url = HttpUrl.parse(this.m_url)!!.newBuilder()
                        .addQueryParameter("key", p_params.key)
                        .addQueryParameter("steamids", p_params.steamid.toString())
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


    class Decoder : IOkHTTPResponseTranslator<PlayerSummaries> {
        @Throws(Throwable::class)
        override fun decode(p_input: Response): PlayerSummaries {
            println(p_input.code())
            return PlayerSummariesDecoder().decode(JSONTokener(p_input.body()!!.string()).nextValue() as JSONObject)
        }
    }
}