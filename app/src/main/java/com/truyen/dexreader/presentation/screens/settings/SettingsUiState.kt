package com.truyen.dexreader.presentation.screens.settings

import com.truyen.dexreader.domain.model.ThemeType
import com.truyen.dexreader.domain.model.LanguageType  // THÊM dòng này
data class SettingsUiState(
  val isLoading: Boolean = false,
  val themeType: ThemeType = ThemeType.SYSTEM,
  val languageType: LanguageType = LanguageType.ENGLISH, // THÊM dòng này
  val isChangeThemeSuccess: Boolean = false,
  val isChangeThemeError: Boolean = false,
  val isChangeLanguageError: Boolean = false, // THÊM dòng này
  val isChangeLanguageSuccess: Boolean = false // THÊM dòng này
)
