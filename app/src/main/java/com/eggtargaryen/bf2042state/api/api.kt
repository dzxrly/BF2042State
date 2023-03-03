package com.eggtargaryen.bf2042state.api

import okhttp3.Call

fun getPlayerState(
    playerName: String,
    platform: String,
    raw: String = "false",
    formatValue: String = "true",
    skipBattleLog: String = "false",
): Call {
    val baseApi = RequestBuilder()
    baseApi.urls = "/bf2042/stats/?raw=$raw&format_values=$formatValue&name=$playerName&platform=$platform&skip_battlelog=$skipBattleLog"
    baseApi.method = "GET"
    baseApi.timeout = BF2042StateBaseApi.timeout.toLong()
    return baseApi.request()
}
