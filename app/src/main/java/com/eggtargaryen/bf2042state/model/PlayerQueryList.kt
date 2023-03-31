package com.eggtargaryen.bf2042state.model

import androidx.annotation.Keep
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class PlayerQueryList(
    val namespace: String? = "",
    val query: String? = "",

    val results: List<PlayerQueryResult>? = listOf(),
)

@Keep
@JsonClass(generateAdapter = true)
data class PlayerQueryResult(
    val name: String? = "",
    val pid: Long? = 0L,
)