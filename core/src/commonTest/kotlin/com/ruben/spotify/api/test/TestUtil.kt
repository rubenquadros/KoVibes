package com.ruben.spotify.api.test

import com.ruben.spotify.api.ApiResponse
import com.ruben.spotify.api.response.Error
import com.ruben.spotify.api.response.ErrorBody
import com.ruben.spotify.api.response.SpotifyApiResponse

fun getExpectedResponse(responsePath: String): String {
    return readResource(responsePath)
}

internal fun <API_RESPONSE> getApiResponse(
    isSuccess: Boolean,
    getResponse: () -> API_RESPONSE
): ApiResponse<API_RESPONSE, ErrorBody> {
    return if (isSuccess) {
        ApiResponse(
            failure = null,
            result = getResponse()
        )
    } else {
        ApiResponse(
            result = null,
            failure = ErrorBody(
                error = Error(
                    status = 400,
                    message = "Bad request"
                )
            )
        )
    }
}

internal fun <SUCCESS, ERROR> SpotifyApiResponse<SUCCESS, ERROR>.getSuccessSpotifyApiResponse(): SUCCESS {
    return (this as SpotifyApiResponse.Success).result
}