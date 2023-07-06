package com.eggtargaryen.bf2042state.api

import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import okio.IOException


object BF2042StateBaseApi {
    val devMode = false
    val baseApi = "https://api.gametools.network"
    val feslApi = "https://fesl.gametools.network"
    val giteeApi = "https://gitee.com/api/v5"
    val baseDevApi = "http://127.0.0.1:4523/m1/2353382-0-default"
    val timeout = 30000  // 30s
}

class RequestBuilder : Callback {
    var urls: String = ""
    var method: String = "GET"
    var body: RequestBody? = null
    var timeout: Long = 30000
    var success: ((Response) -> Unit)? = null
    var faild: ((Throwable) -> Unit)? = null
    var baseApi: String = BF2042StateBaseApi.baseApi

    private fun urlBuilder(extendUrl: String): String {
        return baseApi + extendUrl
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

    val client = OkHttpClient()

    fun request(): Call {
        return client.newCall(
            when (method) {
                "post", "Post", "POST" -> Request.Builder().url(urlBuilder(urls)).post(body!!)
                    .build()

                else -> Request.Builder().url(urlBuilder(urls)).build()
            }
        )
    }
}
