package io.github.rubenquadros.kovibes.api.test.authstorage

import io.github.rubenquadros.kovibes.api.AuthStorage
import io.github.rubenquadros.kovibes.api.test.authToken
import io.github.rubenquadros.kovibes.api.test.clientId
import io.github.rubenquadros.kovibes.api.test.clientSecret
import io.github.rubenquadros.kovibes.api.test.encodedCred
import kotlin.test.Test
import kotlin.test.assertTrue

class AuthStorageTest {

    private val authStorage = AuthStorage()

    @Test
    fun `initially the auth token is not available`() {
        assertTrue { authStorage.getAccessToken().isEmpty() }
    }

    @Test
    fun `when auth storage is initialised then then encoded credentials is available`() {
        authStorage.init(clientId, clientSecret)

        assertTrue { authStorage.getEncodedCredentials() == encodedCred }
    }

    @Test
    fun `when token is updated then it is stored`() {
        assertTrue { authStorage.getAccessToken().isEmpty() }

        authStorage.updateAccessToken(authToken)

        assertTrue { authStorage.getAccessToken() == authToken }
    }
}