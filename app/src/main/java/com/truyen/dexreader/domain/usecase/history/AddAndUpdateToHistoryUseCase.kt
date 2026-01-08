package com.truyen.dexreader.domain.usecase.history

import com.truyen.dexreader.domain.model.ReadingHistory
import com.truyen.dexreader.domain.repository.HistoryRepository
import javax.inject.Inject

class AddAndUpdateToHistoryUseCase @Inject constructor(
  private val historyRepository: HistoryRepository
) {
  suspend operator fun invoke(
    userId: String,
    readingHistory: ReadingHistory
  ): Result<Unit> =
    historyRepository.addAndUpdateToHistory(
      userId = userId,
      readingHistory = readingHistory
    )
}