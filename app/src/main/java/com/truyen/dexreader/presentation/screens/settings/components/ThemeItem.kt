package com.truyen.dexreader.presentation.screens.settings.components

import androidx.compose.ui.graphics.vector.ImageVector
import com.truyen.dexreader.domain.model.ThemeType

data class ThemeItem(
  val type: ThemeType,
  val name: String,
  val icon: ImageVector
)
