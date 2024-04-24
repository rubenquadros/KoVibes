package io.github.rubenquadros.kovibes.api

import io.github.rubenquadros.kovibes.api.config.Config
import io.github.rubenquadros.kovibes.api.config.logger.LogLevel

/**
 * ConfigProvider is responsible for providing the different [Config].
 */
internal class ConfigProvider {

    private var config: Config? = null

    /**
     * Init the config provider.
     *
     * @param config
     */
    fun init(config: Config) {
        this.config = config
    }

    /**
     * Return the log level for the API.
     *
     * @return [LogLevel]
     */
    fun getLogLevel(): LogLevel {
        if (config == null) error("Method init has not been called.")
        return config!!.logLevel
    }
}