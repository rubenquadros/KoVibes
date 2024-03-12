package com.ruben.spotify.api.recommendations

import com.ruben.spotify.api.ApiResponse
import com.ruben.spotify.api.KtorService
import com.ruben.spotify.api.getParsedHttpResponse
import com.ruben.spotify.api.recommendations.models.RecommendationsResponse
import com.ruben.spotify.api.request.GetRecommendationsRequest
import com.ruben.spotify.api.response.Error
import com.ruben.spotify.api.response.ErrorBody
import io.ktor.client.request.get
import io.ktor.http.path
import io.ktor.util.StringValues
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * @suppress
 * RecommendationsApiImpl is the implementation of [RecommendationsApi].
 *
 * @property ktorService
 * @property dispatcher
 */
internal class RecommendationsApiImpl(
    private val ktorService: KtorService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : RecommendationsApi {

    override suspend fun getRecommendations(
        getRecommendationsRequest: GetRecommendationsRequest
    ): ApiResponse<RecommendationsResponse, ErrorBody> {

        if (isValidRequest(getRecommendationsRequest).not()) {
            return ApiResponse(
                failure = ErrorBody(
                    error = Error(
                        status = 400,
                        message = "Invalid request - Please provide one of seedArtists, seedGenres or seedTracks."
                    )
                )
            )
        }

        val response = withContext(dispatcher) {
            ktorService.client.get {
                url {
                    path("/v1/recommendations")

                    parameters.appendAll(
                        getParameters(getRecommendationsRequest)
                    )
                }
            }
        }

        return response.getParsedHttpResponse()
    }

    private fun isValidRequest(getRecommendationsRequest: GetRecommendationsRequest): Boolean {
        getRecommendationsRequest.run {
            if (seedGenres.isEmpty() && seedArtists.isEmpty() && seedTracks.isEmpty()) {
                return false
            }

            if (seedTracks.isNotEmpty() && seedGenres.isEmpty() && seedArtists.isEmpty()) {
                return true
            }

            if (seedGenres.isNotEmpty() && seedTracks.isEmpty() && seedArtists.isEmpty()) {
                return true
            }

            if (seedArtists.isNotEmpty() && seedTracks.isEmpty() && seedGenres.isEmpty()) {
                return true
            }
        }

        return true
    }

    private fun getParameters(getRecommendationsRequest: GetRecommendationsRequest): StringValues {
        return getRecommendationsRequest.run {
            StringValues.build {
                if (seedGenres.isNotEmpty()) {
                    this["seed_genres"] = seedGenres.joinToString { it }
                }

                if (seedTracks.isNotEmpty()) {
                    this["seed_tracks"] = seedTracks.joinToString { it }
                }

                if (seedArtists.isNotEmpty()) {
                    this["seed_artists"] = seedArtists.joinToString { it }
                }

                market?.let { this["market"] = it }

                minAcousticness?.let { this["min_acousticness"] = it.toString() }
                maxAcousticness?.let { this["max_acousticness"] = it.toString() }
                targetAcousticness?.let { this["target_acousticness"] = it.toString() }

                minDanceability?.let { this["min_danceability"] = it.toString() }
                maxDanceability?.let { this["max_danceability"] = it.toString() }
                targetDanceability?.let { this["target_danceability"] = it.toString() }

                minDurationMs?.let { this["min_duration_ms"] = it.toString() }
                maxDurationMs?.let { this["max_duration_ms"] = it.toString() }
                targetDurationMs?.let { this["target_duration_ms"] = it.toString() }

                minEnergy?.let { this["min_energy"] = it.toString() }
                maxEnergy?.let { this["max_energy"] = it.toString() }
                targetEnergy?.let { this["target_energy"] = it.toString() }

                minInstrumentalness?.let { this["min_instrumentalness"] = it.toString() }
                maxInstrumentalness?.let { this["max_instrumentalness"] = it.toString() }
                targetInstrumentalness?.let { this["target_instrumentalness"] = it.toString() }

                minKey?.let { this["min_key"] = it.toString() }
                maxKey?.let { this["max_key"] = it.toString() }
                targetKey?.let { this["target_key"] = it.toString() }

                minLiveness?.let { this["max_liveness"] = it.toString() }
                maxLiveness?.let { this["max_liveness"] = it.toString() }
                targetLiveness?.let { this["target_liveness"] = it.toString() }

                minLoudness?.let { this["min_loudness"] = it.toString() }
                maxLoudness?.let { this["max_loudness"] = it.toString() }
                targetLoudness?.let { this["target_loudness"] = it.toString() }

                minMode?.let { this["min_mode"] = it.toString() }
                maxMode?.let { this["max_mode"] = it.toString() }
                targetMode?.let { this["target_mode"] = it.toString() }

                minPopularity?.let { this["min_popularity"] = it.toString() }
                maxPopularity?.let { this["max_popularity"] = it.toString() }
                targetPopularity?.let { this["target_popularity"] = it.toString() }

                minSpeechiness?.let { this["min_speechiness"] = it.toString() }
                maxSpeechiness?.let { this["max_speechiness"] = it.toString() }
                targetSpeechiness?.let { this["target_speechiness"] = it.toString() }

                minTempo?.let { this["min_tempo"] = it.toString() }
                maxTempo?.let { this["max_tempo"] = it.toString() }
                targetTempo?.let { this["target_tempo"] = it.toString() }

                minTimeSignature?.let { this["min_time_signature"] = it.toString() }
                maxTimeSignature?.let { this["max_time_signature"] = it.toString() }
                targetTimeSignature?.let { this["target_time_signature"] = it.toString() }

                minValence?.let { this["min_valence"] = it.toString() }
                maxValence?.let { this["max_valence"] = it.toString() }
                targetValence?.let { this["target_valence"] = it.toString() }
            }
        }
    }
}