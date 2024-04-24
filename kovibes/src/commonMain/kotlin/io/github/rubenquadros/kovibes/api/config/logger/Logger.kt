package io.github.rubenquadros.kovibes.api.config.logger

/**
 * Log level decides the logging level of the entire API.
 */
enum class LogLevel {
    /**
     * Everything is logged - [HEADERS], [BODY] and [INFO]
     */
    ALL,

    /**
     * Only headers are logged.
     */
    HEADERS,

    /**
     * Only body is logged.
     */
    BODY,

    /**
     * Only info logs are provided - request type and response status.
     */
    INFO,

    /**
     * No logs are provided.
     * This is the default log level.
     */
    NONE
}

/**
 * @suppress
 * Map [LogLevel] to [io.ktor.client.plugins.logging.LogLevel]
 */
internal fun LogLevel.toKtorLogLevel(): io.ktor.client.plugins.logging.LogLevel {
    return when(this) {
        LogLevel.ALL -> io.ktor.client.plugins.logging.LogLevel.ALL
        LogLevel.HEADERS -> io.ktor.client.plugins.logging.LogLevel.HEADERS
        LogLevel.BODY -> io.ktor.client.plugins.logging.LogLevel.BODY
        LogLevel.INFO -> io.ktor.client.plugins.logging.LogLevel.INFO
        LogLevel.NONE -> io.ktor.client.plugins.logging.LogLevel.NONE
    }
}