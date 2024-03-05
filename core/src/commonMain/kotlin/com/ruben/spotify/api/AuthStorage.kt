package com.ruben.spotify.api

import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

internal class AuthStorage {

    private var clientId: String = ""
    private var clientSecret: String = ""

    private var accessToken: String = ""

    fun init(clientId: String, clientSecret: String) {
        this.clientId = clientId
        this.clientSecret = clientSecret
    }

    fun getAccessToken(): String {
        return accessToken
    }

    fun updateAccessToken(accessToken: String) {
        this.accessToken = accessToken
    }

    @OptIn(ExperimentalEncodingApi::class)
    fun getEncodedCredentials(): String {
        return Base64.encode(
            source = "$clientId:$clientSecret".toByteArray()
        )
    }

}