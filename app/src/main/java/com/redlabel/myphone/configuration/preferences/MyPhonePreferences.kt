package com.redlabel.myphone.configuration.preferences

import android.content.SharedPreferences
import androidx.core.content.edit
import com.redlabel.myphone.ui.theme.MyPhoneTheme
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Named

private const val KEY_THEME = "pref_theme"

class MyPhonePreferences @Inject constructor(@Named("theme") private val sharedPreferences: SharedPreferences) {
    private val defaultThemeValue = MyPhoneTheme.SYSTEM.name

    private val preferenceKeyChangedFlow = MutableSharedFlow<MyPhoneTheme>(extraBufferCapacity = 1)

    fun registerOnChangedListener() {
        sharedPreferences.registerOnSharedPreferenceChangeListener { _, _ ->
            preferenceKeyChangedFlow.tryEmit(theme)
        }
    }

    var theme: MyPhoneTheme
        get() = sharedPreferences.getString(KEY_THEME, defaultThemeValue)?.let { MyPhoneTheme.valueOf(it) } ?: MyPhoneTheme.SYSTEM
        set(value) = sharedPreferences.edit {
            putString(KEY_THEME, value.name)
        }

    fun observeTheme(): Flow<MyPhoneTheme> = preferenceKeyChangedFlow.onStart { emit(theme) }.distinctUntilChanged()
}
