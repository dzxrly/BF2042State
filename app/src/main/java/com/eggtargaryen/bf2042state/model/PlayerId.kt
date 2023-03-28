package com.eggtargaryen.bf2042state.model

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class PlayerId(
    val results: List<Result>
)

@Keep
@JsonClass(generateAdapter = true)
data class Result(
    val name: String? = null,

    @Json(name = "nucleusId")
    val nucleusID: Long? = null,

    @Json(name = "personaId")
    val personaID: Long? = null,

    val platform: String? = null,

    @Json(name = "platformId")
    val platformID: Long? = null
)