package com.ruben.spotify.api.playlist.models

import com.ruben.spotify.api.ExcludeFromCoverage
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@ExcludeFromCoverage
@Serializable
internal data class FollowersInfo(
    @SerialName("href")
    val href: String?,
    @SerialName("total")
    val total: Long
)

@ExcludeFromCoverage
@Serializable
internal data class RestrictionInfo(
    @SerialName("reason")
    val reason: String
)
