package com.ruben.spotify.api.test

import okio.FileSystem
import okio.Path.Companion.toPath

internal actual fun readResource(resourceName: String): String {
    return FileSystem.SYSTEM.read("src/commonTest/resources/$resourceName".toPath()) {
        readUtf8()
    }
}