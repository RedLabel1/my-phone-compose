package com.redlabel.myphone.configuration.initializers

import android.app.Application
import androidx.core.provider.FontRequest
import androidx.emoji2.text.EmojiCompat
import androidx.emoji2.text.FontRequestEmojiCompatConfig
import com.redlabel.myphone.R
import javax.inject.Inject

class EmojiInitializer @Inject constructor() : AppInitializer {

    override fun init(application: Application) {
        val fontRequest = FontRequest(
            "com.google.android.gms.fonts",
            "com.google.android.gms",
            "Noto Color Emoji Compat",
            R.array.com_google_android_gms_fonts_certs
        )

        val config = FontRequestEmojiCompatConfig(application, fontRequest)
            .setReplaceAll(true)

        EmojiCompat.init(config)
    }
}
