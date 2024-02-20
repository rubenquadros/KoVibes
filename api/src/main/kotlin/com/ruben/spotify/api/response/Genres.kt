package com.ruben.spotify.api.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Genres(
    @SerialName("genres")
    val genres: List<String>
)
