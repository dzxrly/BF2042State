package com.eggtargaryen.bf2042state.model

import androidx.annotation.Keep
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
@Keep
@JsonClass(generateAdapter = true)
data class CheatInfo(
    @Json(name = "name")
    val name: String? = null,
    @Json(name = "url")
    val url: String? = null,
    @Json(name = "status")
    val status: Int? = null,
    @Json(name = "hacker")
    val hacker: Boolean? = null,
    @Json(name = "originId")
    val originId: String? = null,
    @Json(name = "originPersonaId")
    val originPersonaId: String? = null,
    @Json(name = "originUserId")
    val originUserId: String? = null,
    @Json(name = "cheatMethods")
    val cheatMethods: Array<String>? = null
)