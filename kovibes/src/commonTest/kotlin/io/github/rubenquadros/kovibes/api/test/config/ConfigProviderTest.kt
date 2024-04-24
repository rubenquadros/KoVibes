package io.github.rubenquadros.kovibes.api.test.config

import io.github.rubenquadros.kovibes.api.ConfigProvider
import io.github.rubenquadros.kovibes.api.config.Config
import io.github.rubenquadros.kovibes.api.config.logger.LogLevel
import kotlin.test.Test
import kotlin.test.assertFails
import kotlin.test.assertTrue

class ConfigProviderTest {

    private val configProvider = ConfigProvider()

    @Test
    fun `retrieving the config before initializing the provider throws error`() {
        val error = assertFails { configProvider.getLogLevel() }

        assertTrue { error is IllegalStateException }
        assertTrue { error.message == "Method init has not been called." }
    }

    @Test
    fun `we are able to retrieve the configurations`() {
        LogLevel.entries.forEach {
            configProvider.init(config = Config(it))
            val logLevel = configProvider.getLogLevel()

            assertTrue { logLevel == it }
        }
    }
}