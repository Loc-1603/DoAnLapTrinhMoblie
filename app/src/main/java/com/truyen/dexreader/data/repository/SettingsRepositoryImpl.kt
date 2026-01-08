package com.truyen.dexreader.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey  // THÊM dòng này
import com.truyen.dexreader.di.ThemeTypeKeyQualifier
import com.truyen.dexreader.domain.model.ThemeType
import com.truyen.dexreader.domain.repository.SettingsRepository
import com.truyen.dexreader.utils.runSuspendCatching
import com.truyen.dexreader.utils.toResultFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first  // THÊM dòng này
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(
  private val themePrefsManager: DataStore<Preferences>,
  @ThemeTypeKeyQualifier
  private val themeTypeKey: String
) : SettingsRepository {
  private val themeTypePrefsKey = intPreferencesKey(themeTypeKey)
  private val languagePrefsKey = stringPreferencesKey("language")  // THÊM dòng này

  override fun observeThemeType(): Flow<Result<ThemeType>> =
    themePrefsManager.data.map { prefs ->
      ThemeType.entries[prefs[themeTypePrefsKey] ?: 0]
    }.toResultFlow()

  override suspend fun setThemeType(value: ThemeType): Result<Unit> =
    runSuspendCatching {
      themePrefsManager.edit { prefs ->
        prefs[themeTypePrefsKey] = value.ordinal
      }
    }

  // THÊM 2 hàm này vào cuối class
  override suspend fun setLanguage(language: String) {
    themePrefsManager.edit { prefs ->
      prefs[languagePrefsKey] = language
    }
  }

  override suspend fun getLanguage(): String {
    return themePrefsManager.data.map { prefs ->
      prefs[languagePrefsKey] ?: "en"  // "en" là ngôn ngữ mặc định
    }.first()
  }
}