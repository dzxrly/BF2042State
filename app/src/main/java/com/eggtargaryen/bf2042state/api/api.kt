package com.eggtargaryen.bf2042state.api

import okhttp3.Call
import okhttp3.RequestBody.Companion.toRequestBody

fun getPlayerState(
    playerName: String,
    platform: String,
    raw: String = "false",
    formatValue: String = "true",
    skipBattleLog: String = "false",
): Call {
    val baseApi = RequestBuilder()
    baseApi.urls =
        "/bf2042/stats/?raw=$raw&format_values=$formatValue&name=$playerName&platform=$platform&skip_battlelog=$skipBattleLog"
    baseApi.method = "GET"
    baseApi.timeout = BF2042StateBaseApi.timeout.toLong()
    return baseApi.request()
}


// BFBan 状态查询
// status=0: 未处理
// status=1: 石锤
// status=2: 待自证
// status=3: MOSS自证
// status=4: 无效举报
// status=5: 讨论中
// status=6: 需要更多管理投票
// status=7:
// status=8: 刷枪
fun postPlayerBFBanStatus(
    playerName: String
): Call {
    val baseApi = RequestBuilder()
    baseApi.urls = "/bfban/checkban"
    baseApi.method = "POST"
    baseApi.body = "[{\"name\": \"$playerName\"}]".toRequestBody()
    baseApi.timeout = BF2042StateBaseApi.timeout.toLong()
    return baseApi.request()
}
