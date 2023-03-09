package com.eggtargaryen.bf2042state.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * CheatInfo
 * @param name
 * @param url
 * @param status
 * @param hacker
 * @param originId
 * @param originPersonaId
 * @param originUserId
 * @param cheatMethods
 */
@JsonClass(generateAdapter = true)
data class CheatInfo(
    @Json(name = "name")
    val name: kotlin.String? = null,
    @Json(name = "url")
    val url: kotlin.String? = null,
    @Json(name = "status")
    val status: kotlin.Int? = null,
    @Json(name = "hacker")
    val hacker: kotlin.Boolean? = null,
    @Json(name = "originId")
    val originId: kotlin.String? = null,
    @Json(name = "originPersonaId")
    val originPersonaId: kotlin.String? = null,
    @Json(name = "originUserId")
    val originUserId: kotlin.String? = null,
    @Json(name = "cheatMethods")
    val cheatMethods: kotlin.Array<kotlin.String>? = null
)