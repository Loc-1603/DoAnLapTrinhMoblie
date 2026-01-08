package com.truyen.dexreader.domain.usecase.settings

import com.truyen.dexreader.domain.model.ThemeType
import com.truyen.dexreader.domain.repository.SettingsRepository
import javax.inject.Inject

class SetThemeTypeUseCase @Inject constructor(
  private val settingRepository: SettingsRepository
) {
  suspend operator fun invoke(value: ThemeType): Result<Unit> =
    settingRepository.setThemeType(value)
}