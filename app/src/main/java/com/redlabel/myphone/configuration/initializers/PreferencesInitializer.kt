package com.redlabel.myphone.configuration.initializers

import android.app.Application
import com.redlabel.myphone.configuration.preferences.MyPhonePreferences
import javax.inject.Inject

class PreferencesInitializer @Inject constructor(
    private val preferences: MyPhonePreferences
) : AppInitializer {

    override fun init(application: Application) {
        preferences.registerOnChangedListener()
    }
}
