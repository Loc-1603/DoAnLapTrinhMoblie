package com.truyen.dexreader.domain.repository

import com.truyen.dexreader.domain.model.ThemeType
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
  fun observeThemeType(): Flow<Result<ThemeType>>
  suspend fun setThemeType(value: ThemeType): Result<Unit>
  suspend fun setLanguage(language: String)
  suspend fun getLanguage(): String
}