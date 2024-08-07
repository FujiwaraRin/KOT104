package com.fpoly.huyndph30375_assignment_kot104.font

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import com.hq.quanhqph33420_assignment.R

class GoogleFont {
    companion object {
        private val provider = GoogleFont.Provider(
            providerAuthority = "com.google.android.gms.fonts",
            providerPackage = "com.google.android.gms",
            certificates = R.array.com_google_android_gms_fonts_certs
        )

        private fun changeFontFamily(name: String): FontFamily {
            val font = GoogleFont(name)
            return FontFamily(Font(googleFont = font, fontProvider = provider))
        }

        val GelasioFont = changeFontFamily("Gelasio")
        val NunitoSansFont = changeFontFamily("Nunito Sans")
        val MerriweatherFont = changeFontFamily("Merriweather")
    }
}