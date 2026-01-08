package com.truyen.dexreader.presentation.screens.settings

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.truyen.dexreader.domain.model.ThemeType
import com.truyen.dexreader.domain.model.LanguageType
import com.truyen.dexreader.domain.usecase.settings.ObserveThemeTypeUseCase
import com.truyen.dexreader.domain.usecase.settings.SetThemeTypeUseCase
import com.truyen.dexreader.domain.usecase.settings.SetLanguageUseCase
import com.truyen.dexreader.domain.usecase.settings.GetLanguageUseCase
import com.truyen.dexreader.utils.LocaleManager
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
  @ApplicationContext private val context: Context,
  private val observeThemeTypeUseCase: ObserveThemeTypeUseCase,
  private val setThemeTypeUseCase: SetThemeTypeUseCase,
  private val setLanguageUseCase: SetLanguageUseCase,
  private val getLanguageUseCase: GetLanguageUseCase,
) : ViewModel() {
  private val _uiState = MutableStateFlow<SettingsUiState>(SettingsUiState())
  val uiState: StateFlow<SettingsUiState> = _uiState.asStateFlow()

  init {
    observeThemeType()
    loadLanguage()
  }

  private fun observeThemeType() {
    viewModelScope.launch {
      _uiState.update { it.copy(isLoading = true) }

      observeThemeTypeUseCase().collect { result ->
        result
          .onSuccess { type ->
            _uiState.update {
              it.copy(
                isLoading = false,
                themeType = type,
              )
            }
          }
          .onFailure {
            _uiState.update {
              it.copy(
                isLoading = false,
                themeType = ThemeType.SYSTEM,
              )
            }
            Log.e(TAG, "observeIsDynamicTheme have error: ${it.stackTraceToString()}")
          }
      }
    }
  }

  private fun loadLanguage() {
    viewModelScope.launch {
      try {
        val language = getLanguageUseCase()
        val languageType = when (language) {
          "vi" -> LanguageType.VIETNAMESE
          else -> LanguageType.ENGLISH
        }
        _uiState.update {
          it.copy(languageType = languageType)
        }
      } catch (e: Exception) {
        Log.e(TAG, "loadLanguage have error: ${e.stackTraceToString()}")
      }
    }
  }

  fun setThemeType() {
    val currentUiState = _uiState.value
    if (currentUiState.isLoading) return

    viewModelScope.launch {
      _uiState.update {
        it.copy(
          isLoading = true,
          isChangeThemeSuccess = false,
          isChangeThemeError = false
        )
      }

      setThemeTypeUseCase(currentUiState.themeType)
        .onSuccess {
          _uiState.update {
            it.copy(
              isLoading = false,
              isChangeThemeSuccess = true,
              isChangeThemeError = false
            )
          }
        }
        .onFailure {
          _uiState.update {
            it.copy(
              isLoading = false,
              isChangeThemeSuccess = false,
              isChangeThemeError = true
            )
          }
          Log.e(TAG, "setThemeType have error: ${it.stackTraceToString()}")
        }
    }
  }

  fun changeThemeType(type: ThemeType) {
    if (_uiState.value.themeType == type) return
    _uiState.update {
      it.copy(
        isLoading = false,
        themeType = type,
        isChangeThemeSuccess = false,
        isChangeThemeError = false
      )
    }
  }

  // ✅ ĐÃ SỬA: Thêm lưu vào SharedPreferences
  fun setLanguage() {
    val currentUiState = _uiState.value
    if (currentUiState.isLoading) return

    viewModelScope.launch {
      _uiState.update {
        it.copy(
          isLoading = true,
          isChangeLanguageSuccess = false,
          isChangeLanguageError = false
        )
      }

      try {
        val languageCode = when (currentUiState.languageType) {
          LanguageType.ENGLISH -> "en"
          LanguageType.VIETNAMESE -> "vi"
        }

        // Lưu vào DataStore
        setLanguageUseCase(languageCode)

        // ✅ THÊM: Lưu vào SharedPreferences để load nhanh khi app khởi động
        LocaleManager.saveLanguageToPrefs(context, currentUiState.languageType)

        _uiState.update {
          it.copy(
            isLoading = false,
            isChangeLanguageSuccess = true,
            isChangeLanguageError = false
          )
        }
      } catch (e: Exception) {
        _uiState.update {
          it.copy(
            isLoading = false,
            isChangeLanguageSuccess = false,
            isChangeLanguageError = true
          )
        }
        Log.e(TAG, "setLanguage have error: ${e.stackTraceToString()}")
      }
    }
  }

  fun changeLanguageType(type: LanguageType) {
    if (_uiState.value.languageType == type) return
    _uiState.update {
      it.copy(
        isLoading = false,
        languageType = type,
        isChangeLanguageSuccess = false,
        isChangeLanguageError = false
      )
    }
  }

  // ✅ THÊM: Reset trạng thái sau khi hiển thị dialog
  fun resetLanguageChangeState() {
    _uiState.update {
      it.copy(
        isChangeLanguageSuccess = false,
        isChangeLanguageError = false
      )
    }
  }

  fun retry() {
    if (_uiState.value.isChangeThemeError) setThemeType()
    if (_uiState.value.isChangeLanguageError) setLanguage()
  }

  companion object {
    private const val TAG = "SettingsViewModel"
  }
}