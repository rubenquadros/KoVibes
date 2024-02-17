package com.ruben.spotify.api

internal interface AuthService {

    fun init(clientId: String, clientSecret: String)

    suspend fun generateToken() : AuthResult

    fun getAccessToken(): String
}