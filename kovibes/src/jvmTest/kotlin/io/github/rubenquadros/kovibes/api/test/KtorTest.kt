package io.github.rubenquadros.kovibes.api.test

import io.github.rubenquadros.kovibes.api.getKtorEngine
import io.github.rubenquadros.kovibes.api.getKtorLogger
import io.ktor.client.engine.java.JavaHttpEngine
import io.ktor.client.plugins.logging.Logger
import io.ktor.util.reflect.instanceOf
import kotlin.test.Test
import kotlin.test.assertTrue

class KtorTest {

    @Test
    fun `ktor engine is provided`() {
        val engine = getKtorEngine()

        assertTrue { engine is JavaHttpEngine }
    }

    @Test
    fun `logger is provided`() {
        val logger = getKtorLogger()

        // We should have checked for SimpleLogger but it is a private class
        assertTrue { logger.instanceOf(Logger::class) }
    }
}