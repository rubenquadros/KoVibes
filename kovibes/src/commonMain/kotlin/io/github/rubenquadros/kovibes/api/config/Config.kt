package io.github.rubenquadros.kovibes.api.config

import io.github.rubenquadros.kovibes.api.config.logger.LogLevel

/**
 * This represents all the configurations of the [io.github.rubenquadros.kovibes.api.KoVibesApi].
 *
 * If no configurations are provided explicitly then the default values are taken.
 */
data class Config(
    val logLevel: LogLevel = LogLevel.NONE
)