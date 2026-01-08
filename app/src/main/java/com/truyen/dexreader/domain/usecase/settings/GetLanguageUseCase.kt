package com.truyen.dexreader.domain.usecase.settings

import com.truyen.dexreader.domain.repository.SettingsRepository
import javax.inject.Inject

class GetLanguageUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository
) {
    suspend operator fun invoke(): String = settingsRepository.getLanguage()
}