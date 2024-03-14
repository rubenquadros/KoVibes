package io.github.rubenquadros.kovibes.api

import io.github.rubenquadros.kovibes.api.response.Error
import io.github.rubenquadros.kovibes.api.response.ErrorBody
import io.github.rubenquadros.kovibes.api.response.SpotifyApiResponse
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode

/**
 * Internal class which maps the Spotify API responses.
 *
 * @param API_RESPONSE
 * @param ERROR_RESPONSE
 * @property result
 * @property failure
 *
 * When the Spotify API returns success then it is mapped to `result`.
 *
 * When the Spotify API returns error then it is mapped to `failure`.
 *
 * Either `result` or `failure` will be present at a time and never both.
 *
 * Both `result` and `failure` will never be null.
 */
internal data class ApiResponse<API_RESPONSE, ERROR_RESPONSE>(
    val result: API_RESPONSE? = null,
    val failure: ERROR_RESPONSE? = null
)

/**
 * @suppress
 * Map [ApiResponse] to [SpotifyApiResponse].
 *
 * @param API_RESPONSE
 * @param RESPONSE
 * @param ERROR
 * @param mapperBlock
 * @receiver
 * @return [SpotifyApiResponse.Success] when success and [SpotifyApiResponse.Error] when error.
 */
internal fun <API_RESPONSE, RESPONSE, ERROR> ApiResponse<API_RESPONSE, ERROR>.getParsedApiResponse(
    mapperBlock: (API_RESPONSE) -> RESPONSE
): SpotifyApiResponse<RESPONSE, ERROR> {
    return if (result != null) {
        SpotifyApiResponse.Success(mapperBlock(result))
    } else {
        SpotifyApiResponse.Error(failure!!)
    }
}

/**
 * @suppress
 * Map [HttpResponse] to [ApiResponse].
 *
 * @param HTTP_RESPONSE
 * @param ERROR_RESPONSE
 * @return [ApiResponse.result] when success and [ApiResponse.failure] when error.
 */
internal suspend inline fun <reified HTTP_RESPONSE, reified ERROR_RESPONSE> HttpResponse.getParsedHttpResponse(): ApiResponse<HTTP_RESPONSE, ERROR_RESPONSE> {
    return runCatching<ApiResponse<HTTP_RESPONSE, ERROR_RESPONSE>> {
        if (status == HttpStatusCode.OK) {
            ApiResponse(result = body<HTTP_RESPONSE>())
        } else {
            ApiResponse(failure = body<ERROR_RESPONSE>())
        }
    }.getOrElse {
        it.printStackTrace()
        ApiResponse(failure = ErrorBody(error = Error(status = 500, message = "Internal server error")) as ERROR_RESPONSE)
    }
}