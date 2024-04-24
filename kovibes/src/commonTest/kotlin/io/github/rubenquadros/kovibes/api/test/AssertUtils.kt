package io.github.rubenquadros.kovibes.api.test

import io.github.rubenquadros.kovibes.api.ApiResponse
import io.github.rubenquadros.kovibes.api.response.ErrorBody
import io.github.rubenquadros.kovibes.api.response.SpotifyApiResponse

@Suppress("UNCHECKED_CAST")
internal fun <SUCCESS, ERROR> ApiResponse<SUCCESS, ERROR>.assertApiResponseSuccess(vararg successBlock: (result: SUCCESS) -> Boolean) {
    assert(failure == null)
    assert(result != null)
    successBlock.forEach { block ->
        assert(block(result as SUCCESS))
    }
}

internal fun <SUCCESS, ERROR> ApiResponse<SUCCESS, ERROR>.assertApiResponseFailure() {
    assert(result == null)
    assert(failure is ErrorBody)
    assert((failure as ErrorBody).error.status == 500)
}

internal fun <SUCCESS, ERROR> SpotifyApiResponse<SUCCESS, ERROR>.assertSpotifyApiSuccess(vararg successBlock: (result: SUCCESS) -> Boolean) {
    assert(this is SpotifyApiResponse.Success)
    successBlock.forEach { block ->
        assert(block((this as SpotifyApiResponse.Success).result))
    }
}

internal fun <SUCCESS, ERROR> SpotifyApiResponse<SUCCESS, ERROR>.assertSpotifyApiError() {
    assert(this is SpotifyApiResponse.Error)
    assert((this as SpotifyApiResponse.Error).body is ErrorBody)
    assert((this.body as ErrorBody).error.status == 400)
}