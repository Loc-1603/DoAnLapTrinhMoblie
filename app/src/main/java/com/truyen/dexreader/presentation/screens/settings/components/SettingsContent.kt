package com.truyen.dexreader.presentation.screens.settings.components



import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.truyen.dexreader.R
import com.truyen.dexreader.domain.model.LanguageType  // THÊM import
import com.truyen.dexreader.domain.model.ThemeType
import com.truyen.dexreader.presentation.screens.common.dialog.NotificationDialog
import com.truyen.dexreader.presentation.screens.common.states.LoadingScreen
import com.truyen.dexreader.presentation.screens.settings.SettingsUiState

@Composable
fun SettingsContent(
  uiState: SettingsUiState,
  onChangeThemeType: (ThemeType) -> Unit,
  onSetThemeType: () -> Unit,
  onChangeLanguageType: (LanguageType) -> Unit,  // THÊM parameter này
  onSetLanguageType: () -> Unit,  // THÊM parameter này
  onRetry: () -> Unit,
  modifier: Modifier = Modifier
) {
  var isShowConfirmChangeThemeDialog by rememberSaveable { mutableStateOf(false) }
  var isShowChangeThemeErrorDialog by rememberSaveable { mutableStateOf(true) }
  var isShowChangeThemeSuccessDialog by rememberSaveable { mutableStateOf(true) }

  // THÊM 3 biến này
  var isShowConfirmChangeLanguageDialog by rememberSaveable { mutableStateOf(false) }
  var isShowChangeLanguageErrorDialog by rememberSaveable { mutableStateOf(true) }
  var isShowChangeLanguageSuccessDialog by rememberSaveable { mutableStateOf(true) }

  Column(
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier =
      if (uiState.isLoading) modifier.blur(8.dp)
      else modifier
  ) {
    ThemeSelectorList(
      selectedThemeType = uiState.themeType,
      onSelectedTheme = {
        isShowConfirmChangeThemeDialog = true
        onChangeThemeType(it)
      },
      modifier = Modifier
    )
    Spacer(modifier = Modifier.height(24.dp))
    // THÊM phần này (Language Options)
    LanguageSelectorList(
      selectedLanguageType = uiState.languageType,
      onSelectedLanguage = {
        isShowConfirmChangeLanguageDialog = true
        onChangeLanguageType(it)
      },
      modifier = Modifier
    )
  }

  when {
    uiState.isLoading -> LoadingScreen(modifier = modifier)

    uiState.isChangeThemeError -> {
      if (isShowChangeThemeErrorDialog) {
        NotificationDialog(
          title = stringResource(R.string.change_theme_failed),
          onDismissClick = { isShowChangeThemeErrorDialog = false },
          onConfirmClick = {
            isShowChangeThemeErrorDialog = false
            onRetry()
          },
        )
      }
    }

    uiState.isChangeThemeSuccess -> {
      if (isShowChangeThemeSuccessDialog) {
        NotificationDialog(
          icon = Icons.Default.Done,
          title = stringResource(R.string.theme_change_successful),
          isEnableDismiss = false,
          confirm = stringResource(R.string.ok),
          onConfirmClick = { isShowChangeThemeSuccessDialog = false },
        )
      }
    }
    // THÊM phần này (xử lý lỗi và thành công cho Language)
    uiState.isChangeLanguageError -> {
      if (isShowChangeLanguageErrorDialog) {
        NotificationDialog(
          title = stringResource(R.string.change_language_failed),
          onDismissClick = { isShowChangeLanguageErrorDialog = false },
          onConfirmClick = {
            isShowChangeLanguageErrorDialog = false
            onRetry()
          },
        )
      }
    }

    uiState.isChangeLanguageSuccess -> {
      if (isShowChangeLanguageSuccessDialog) {
        NotificationDialog(
          icon = Icons.Default.Done,
          title = stringResource(R.string.language_change_successful),
          isEnableDismiss = false,
          confirm = stringResource(R.string.ok),
          onConfirmClick = { isShowChangeLanguageSuccessDialog = false },
        )
      }
    }
  }

  if (isShowConfirmChangeThemeDialog) {
    NotificationDialog(
      title = stringResource(R.string.are_you_sure_you_want_to_change_the_theme),
      onDismissClick = { isShowConfirmChangeThemeDialog = false },
      confirm = stringResource(R.string.change),
      onConfirmClick = {
        isShowConfirmChangeThemeDialog = false
        onSetThemeType()
      },
    )
  }
  // THÊM phần này (dialog xác nhận đổi ngôn ngữ)
  if (isShowConfirmChangeLanguageDialog) {
    NotificationDialog(
      title = stringResource(R.string.are_you_sure_you_want_to_change_the_language),
      onDismissClick = { isShowConfirmChangeLanguageDialog = false },
      confirm = stringResource(R.string.change),
      onConfirmClick = {
        isShowConfirmChangeLanguageDialog = false
        onSetLanguageType()
      },
    )
  }
}



@Preview(showBackground = true, name = "Settings Content Preview", showSystemUi = true)
@Composable
fun SettingsContentPreview() {
  // Giả lập một UI State để Preview có cái để hiển thị
  val mockUiState = SettingsUiState(
    isLoading = false,
    themeType = ThemeType.DARK,
    languageType = LanguageType.VIETNAMESE, // Hoặc giá trị mặc định của bạn
    isChangeThemeSuccess = false,
    isChangeLanguageSuccess = false
  )

  SettingsContent(
    uiState = mockUiState,
    onChangeThemeType = {},
    onSetThemeType = {},
    onChangeLanguageType = {},
    onSetLanguageType = {},
    onRetry = {}
  )
}