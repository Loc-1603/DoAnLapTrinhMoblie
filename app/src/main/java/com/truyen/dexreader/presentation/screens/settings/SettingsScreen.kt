package com.truyen.dexreader.presentation.screens.settings

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.truyen.dexreader.R
import com.truyen.dexreader.domain.model.User
import com.truyen.dexreader.presentation.MainActivity
import com.truyen.dexreader.presentation.navigation.NavDestination
import com.truyen.dexreader.presentation.screens.common.base.BaseScreen
import com.truyen.dexreader.presentation.screens.settings.components.SettingsContent
import kotlinx.coroutines.delay

@Composable
fun SettingsScreen(
  isUserLoggedIn: Boolean,
  currentUser: User?,
  onSignInClick: () -> Unit,
  onMenuItemClick: (String) -> Unit,
  viewModel: SettingsViewModel = hiltViewModel(),
  modifier: Modifier = Modifier
) {
  val uiState by viewModel.uiState.collectAsStateWithLifecycle()
  val route = NavDestination.SettingsDestination.route
  val context = LocalContext.current

  // ✅ THÊM: Xử lý khi thay đổi ngôn ngữ thành công
  LaunchedEffect(uiState.isChangeLanguageSuccess) {
    if (uiState.isChangeLanguageSuccess) {
      // Delay một chút để user thấy animation/feedback
      delay(500)
      // Restart Activity để áp dụng ngôn ngữ mới
      MainActivity.restart(context)
    }
  }

  BaseScreen(
    isUserLoggedIn = isUserLoggedIn,
    currentUser = currentUser,
    onSignInClick = onSignInClick,
    title = stringResource(R.string.settings_menu_item),
    route = route,
    onMenuItemClick = onMenuItemClick,
    isSearchEnabled = false,
    content = {
      SettingsContent(
        uiState = uiState,
        onChangeThemeType = viewModel::changeThemeType,
        onSetThemeType = viewModel::setThemeType,
        onChangeLanguageType = viewModel::changeLanguageType,
        onSetLanguageType = viewModel::setLanguage,
        onRetry = viewModel::retry,
        modifier = Modifier.fillMaxSize()
      )
    },
    modifier = modifier
  )
}
