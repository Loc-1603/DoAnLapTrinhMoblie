package com.truyen.dexreader.domain.usecase.settings

import com.truyen.dexreader.domain.repository.SettingsRepository
import javax.inject.Inject

class SetLanguageUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository
) {
    suspend operator fun invoke(language: String) = settingsRepository.setLanguage(language)
}
