package io.github.rubenquadros.kovibes.api

import kotlin.io.encoding.Base64

/**
 * AuthStorage is responsible to store the [accessToken].
 */
internal class AuthStorage {

    private var clientId: String = ""
    private var clientSecret: String = ""

    private var accessToken: String = ""

    /**
     * Init the storage.
     *
     * @param clientId
     * @param clientSecret
     */
    fun init(clientId: String, clientSecret: String) {
        this.clientId = clientId
        this.clientSecret = clientSecret
    }

    /**
     * Return the access token.
     *
     * @return [String].
     */
    fun getAccessToken(): String {
        return accessToken
    }

    /**
     * Update access token with a new one after expiry.
     *
     * @param accessToken
     */
    fun updateAccessToken(accessToken: String) {
        this.accessToken = accessToken
    }

    /**
     * Encode the [clientId] and [clientSecret] and return the encoded string.
     *
     * @return [String].
     */
    @OptIn(kotlin.io.encoding.ExperimentalEncodingApi::class)
    fun getEncodedCredentials(): String {
        return Base64.encode(
            source = "$clientId:$clientSecret".toByteArray()
        )
    }

}