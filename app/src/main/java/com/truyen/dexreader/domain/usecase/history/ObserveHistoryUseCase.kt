package com.truyen.dexreader.domain.usecase.history

import com.truyen.dexreader.domain.model.ReadingHistory
import com.truyen.dexreader.domain.repository.HistoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveHistoryUseCase @Inject constructor(
  private val historyRepository: HistoryRepository
) {
  operator fun invoke(
    userId: String,
    limit: Int = 10,
    mangaId: String? = null,
    lastReadingHistoryId: String? = null
  ): Flow<Result<List<ReadingHistory>>> =
    historyRepository.observeHistory(
      userId = userId,
      limit = limit,
      mangaId = mangaId,
      lastReadingHistoryId = lastReadingHistoryId
    )
}