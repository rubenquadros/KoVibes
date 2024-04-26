package io.github.rubenquadros.kovibes.api.test

import io.github.rubenquadros.kovibes.api.ApiResponse
import io.github.rubenquadros.kovibes.api.response.Error
import io.github.rubenquadros.kovibes.api.response.ErrorBody
import kotlinx.serialization.json.Json

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

val json: Json by lazy { Json { ignoreUnknownKeys = true } }