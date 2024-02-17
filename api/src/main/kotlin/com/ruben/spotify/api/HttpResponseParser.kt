package com.ruben.spotify.api

import com.ruben.spotify.api.response.Error
import com.ruben.spotify.api.response.ErrorBody
import com.ruben.spotify.api.response.SpotifyApiResponse
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode

internal data class ApiResponse<API_RESPONSE, ERROR_RESPONSE>(
    val result: API_RESPONSE? = null,
    val error: ERROR_RESPONSE? = null
)

internal fun <API_RESPONSE, RESPONSE, ERROR>ApiResponse<API_RESPONSE, ERROR>.getParsedApiResponse(
    mapperBlock: (API_RESPONSE) -> RESPONSE
): SpotifyApiResponse<RESPONSE, ERROR> {
    return if (result != null) {
        SpotifyApiResponse.Success(mapperBlock(result))
    } else {
        SpotifyApiResponse.Error(error!!)
    }
}

internal suspend inline fun <reified HTTP_RESPONSE, reified ERROR_RESPONSE> HttpResponse.getParsedHttpResponse(): ApiResponse<HTTP_RESPONSE, ERROR_RESPONSE> {
    return runCatching<ApiResponse<HTTP_RESPONSE, ERROR_RESPONSE>> {
        if (status == HttpStatusCode.OK) {
            ApiResponse(result = body<HTTP_RESPONSE>())
        } else {
            ApiResponse(error = body<ERROR_RESPONSE>())
        }
    }.getOrElse {
        it.printStackTrace()
        ApiResponse(error = ErrorBody(error = Error(status = 500, message = "Internal server error")) as ERROR_RESPONSE)
    }
}