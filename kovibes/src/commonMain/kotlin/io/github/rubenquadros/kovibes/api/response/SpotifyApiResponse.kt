package io.github.rubenquadros.kovibes.api.response

import kotlinx.serialization.Serializable

/**
 * All APIs return [SpotifyApiResponse].
 * If the request is success then you will be receiving [SpotifyApiResponse.Success].
 * If there is any error you will be receiving [SpotifyApiResponse.Error].
 *
 * @see [ErrorBody]
 */
sealed interface SpotifyApiResponse<out RESPONSE, out ERROR> {
    data class Success<RESPONSE>(val result: RESPONSE): SpotifyApiResponse<RESPONSE, Nothing>

    data class Error<ERROR>(val body: ERROR): SpotifyApiResponse<Nothing, ERROR>
}

@Serializable
data class ErrorBody(
    val error: Error
)

@Serializable
data class Error(
    val status: Int,
    val message: String
)