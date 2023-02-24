package com.eggtargaryen.bf2042state.api

import okhttp3.*
import okio.IOException
import java.util.concurrent.TimeUnit


object BF2042StateBaseApi {
    val devMode = true
    val baseApi = "https://api.gametools.network"
    val baseDevApi = "http://127.0.0.1:4523/m1/2353382-0-default"
    val timeout = 3000
}

class RequestBuilder : Callback {
    var urls: String = ""
    var method: String = "GET"
    var body: RequestBody? = null
    var timeout: Long = 3000
    var success: ((Response) -> Unit)? = null
    var faild: ((Throwable) -> Unit)? = null
    private fun urlBuilder(extendUrl: String): String {
        return if (BF2042StateBaseApi.devMode) {
            BF2042StateBaseApi.baseDevApi + extendUrl
        } else {
            BF2042StateBaseApi.baseApi + extendUrl
        }
    }

    fun onSuccess(onSuccess: (Response) -> Unit) {
        success = onSuccess
    }

    fun onFail(onError: (Throwable) -> Unit) {
        faild = onError
    }

    override fun onFailure(call: Call, e: IOException) {
        faild?.invoke(e)
    }

    override fun onResponse(call: Call, response: Response) {
        success?.invoke(response)
    }

    fun request() = OkHttpClient.Builder().run {
        connectTimeout(timeout, TimeUnit.MILLISECONDS)
        readTimeout(timeout, TimeUnit.MILLISECONDS)
        retryOnConnectionFailure(true)
        build().newCall(
            when (method) {
                "post", "Post", "POST" -> Request.Builder().url(urlBuilder(urls)).post(body!!)
                    .build()
                else -> Request.Builder().url(urlBuilder(urls)).build()
            }
        )
    }.enqueue(this)
}

fun http(init: RequestBuilder.() -> Unit) {
    val wrap = RequestBuilder()
    wrap.init()
    wrap.request()
}
