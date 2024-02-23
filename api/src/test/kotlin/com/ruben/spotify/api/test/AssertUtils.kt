package com.ruben.spotify.api.test

import com.ruben.spotify.api.ApiResponse
import com.ruben.spotify.api.response.ErrorBody
import com.ruben.spotify.api.response.SpotifyApiResponse

internal fun <SUCCESS, ERROR> ApiResponse<SUCCESS, ERROR>.assertApiResponseSuccess(vararg successBlock: () -> Boolean) {
    assert(failure == null)
    successBlock.forEach { block ->
        assert(block())
    }
}

internal fun <SUCCESS, ERROR> ApiResponse<SUCCESS, ERROR>.assertApiResponseFailure() {
    assert(result == null)
    assert(failure is ErrorBody)
    assert((failure as ErrorBody).error.status == 500)
}

internal fun <SUCCESS, ERROR> SpotifyApiResponse<SUCCESS, ERROR>.assertSpotifyApiSuccess(vararg successBlock: () -> Boolean) {
    assert(this is SpotifyApiResponse.Success)
    successBlock.forEach { block ->
        assert(block())
    }
}

internal fun <SUCCESS, ERROR>SpotifyApiResponse<SUCCESS, ERROR>.assertSpotifyApiError() {
    assert(this is SpotifyApiResponse.Error)
    assert((this as SpotifyApiResponse.Error).body is ErrorBody)
    assert((this.body as ErrorBody).error.status == 400)
}