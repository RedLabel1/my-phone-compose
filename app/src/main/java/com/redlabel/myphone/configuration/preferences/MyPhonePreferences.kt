package com.redlabel.myphone.configuration.preferences

import android.content.SharedPreferences
import androidx.core.content.edit
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Named

private const val KEY_THEME = "pref_theme"
private const val KEY_DESTINATION = "pref_destination"

class MyPhonePreferences @Inject constructor(@Named("theme") private val sharedPreferences: SharedPreferences) {

    private val defaultThemeValue = MyPhoneTheme.SYSTEM.name
    private val defaultStartDestinationValue = StartDestination.CONTACTS.name
    private val preferenceKeyChangedFlow = MutableSharedFlow<String>(extraBufferCapacity = 1)

    private val listener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
        preferenceKeyChangedFlow.tryEmit(key)
    }

    fun registerOnChangedListener() {
        sharedPreferences.registerOnSharedPreferenceChangeListener(listener)
    }

    var theme: MyPhoneTheme
        get() = sharedPreferences.getString(KEY_THEME, defaultThemeValue)?.let { MyPhoneTheme.valueOf(it) }
            ?: MyPhoneTheme.SYSTEM
        set(value) = sharedPreferences.edit {
            putString(KEY_THEME, value.name)
        }

    var startDestination: StartDestination
        get() = sharedPreferences.getString(KEY_DESTINATION, defaultStartDestinationValue)?.let { StartDestination.valueOf(it) }
            ?: StartDestination.CONTACTS
        set(value) = sharedPreferences.edit {
            putString(KEY_DESTINATION, value.name)
        }

    fun observeTheme(): Flow<MyPhoneTheme> = preferenceKeyChangedFlow
        .onStart { emit(KEY_THEME) }
        .filter { it == KEY_THEME }
        .map { theme }
        .distinctUntilChanged()
}

enum class MyPhoneTheme {
    LIGHT,
    DARK,
    SYSTEM,
    DYNAMIC
}

enum class StartDestination {
    FAVORITES,
    RECENTS,
    CONTACTS,
    KEYPAD,
    VOICEMAIL
}
