package com.ruben.spotify.example.android

import android.app.Application
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module

class SpotifyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(AppModule().module)
        }
    }
}