package io.github.rubenquadros.kovibes.api.test

import io.github.rubenquadros.kovibes.api.getKtorEngine
import io.github.rubenquadros.kovibes.api.getKtorLogger
import io.ktor.client.engine.android.AndroidClientEngine
import io.ktor.client.plugins.logging.MessageLengthLimitingLogger
import kotlin.test.Test
import kotlin.test.assertTrue

class KtorTest {
    @Test
    fun `ktor engine is provided`() {
        val engine = getKtorEngine()

        assertTrue { engine is AndroidClientEngine }
    }

    @Test
    fun `logger is provided`() {
        val logger = getKtorLogger()

        assertTrue { logger is MessageLengthLimitingLogger }
    }
}