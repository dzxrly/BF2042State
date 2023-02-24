package com.eggtargaryen.bf2042state.api

object BF2042StateApi {
    fun getPlayerState(
        playerName: String,
        platform: String,
        raw: String = "false",
        formatValue: String = "true",
        skipBattleLog: String = "false",
        onSuccess: () -> Unit,
        onFail: () -> Unit
    ) {
        http {
            urls =
                "/bf2042/stats/?raw=$raw&format_values=$formatValue&name=$playerName&platform=$platform&skip_battlelog=$skipBattleLog"
            method = "GET"
            timeout = BF2042StateBaseApi.timeout.toLong()
            onSuccess {
                onSuccess()
            }
            onFail {
                onFail()
            }
        }
    }
}
