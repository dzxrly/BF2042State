package com.eggtargaryen.bf2042state.model

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class GiteeRelease(
    val assets: List<Asset>,
    val author: Author,
    val body: String,

    @Json(name = "created_at")
    val createdAt: String,

    val id: Long,
    val name: String,
    val prerelease: Boolean,

    @Json(name = "tag_name")
    val tagName: String,

    @Json(name = "target_commitish")
    val targetCommitish: String
)

@Keep
@JsonClass(generateAdapter = true)
data class Asset(
    @Json(name = "browser_download_url")
    val browserDownloadURL: String,

    val name: String? = null
)

@Keep
@JsonClass(generateAdapter = true)
data class Author(
    @Json(name = "avatar_url")
    val avatarURL: String,

    @Json(name = "events_url")
    val eventsURL: String,

    @Json(name = "followers_url")
    val followersURL: String,

    @Json(name = "following_url")
    val followingURL: String,

    @Json(name = "gists_url")
    val gistsURL: String,

    @Json(name = "html_url")
    val htmlURL: String,

    val id: Long,
    val login: String,
    val name: String,

    @Json(name = "organizations_url")
    val organizationsURL: String,

    @Json(name = "received_events_url")
    val receivedEventsURL: String,

    val remark: String,

    @Json(name = "repos_url")
    val reposURL: String,

    @Json(name = "starred_url")
    val starredURL: String,

    @Json(name = "subscriptions_url")
    val subscriptionsURL: String,

    val type: String,
    val url: String
)