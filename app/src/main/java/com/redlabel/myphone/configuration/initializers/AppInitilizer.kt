package com.redlabel.myphone.configuration.initializers

import android.app.Application
import javax.inject.Inject

interface AppInitializer {
    fun init(application: Application)
}

class AppInitializers @Inject constructor(
    private val initializers: Set<@JvmSuppressWildcards AppInitializer>
) {
    fun init(application: Application) {
        initializers.forEach {
            it.init(application)
        }
    }
}
