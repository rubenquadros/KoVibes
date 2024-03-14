package io.github.rubenquadros.kovibes.api.test

import java.io.InputStreamReader

internal actual fun readResource(resourceName: String): String {
    return ClassLoader.getSystemResourceAsStream(resourceName).use { stream ->
        assert(stream != null) {
            "Error reading the file."
        }
        InputStreamReader(stream!!).use { reader ->
            reader.readText()
        }
    }
}