package com.truyen.dexreader.utils

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import com.truyen.dexreader.domain.model.LanguageType
import java.util.Locale

object LocaleManager {

    private const val PREFS_NAME = "language_prefs"
    private const val KEY_LANGUAGE = "selected_language"

    /**
     * Áp dụng ngôn ngữ cho Context
     */
    fun setLocale(context: Context, languageType: LanguageType): Context {
        val locale = when (languageType) {
            LanguageType.ENGLISH -> Locale.ENGLISH
            LanguageType.VIETNAMESE -> Locale("vi")
        }

        Locale.setDefault(locale)

        val config = Configuration(context.resources.configuration)
        config.setLocale(locale)

        return context.createConfigurationContext(config)
    }

    /**
     * Lưu ngôn ngữ vào SharedPreferences (để load nhanh khi app khởi động)
     */
    fun saveLanguageToPrefs(context: Context, languageType: LanguageType) {
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .edit()
            .putString(KEY_LANGUAGE, languageType.name)
            .apply()
    }

    /**
     * Đọc ngôn ngữ từ SharedPreferences
     */
    fun getLanguageFromPrefs(context: Context): LanguageType {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val languageName = prefs.getString(KEY_LANGUAGE, LanguageType.ENGLISH.name)
        return try {
            LanguageType.valueOf(languageName ?: LanguageType.ENGLISH.name)
        } catch (e: IllegalArgumentException) {
            LanguageType.ENGLISH
        }
    }

    /**
     * Lấy Locale hiện tại từ LanguageType
     */
    fun getLocale(languageType: LanguageType): Locale {
        return when (languageType) {
            LanguageType.ENGLISH -> Locale.ENGLISH
            LanguageType.VIETNAMESE -> Locale("vi")
        }
    }
}