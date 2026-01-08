package com.truyen.dexreader.data.repository

import com.truyen.dexreader.data.mapper.toDomain
import com.truyen.dexreader.data.mapper.toDto
import com.truyen.dexreader.data.network.firebase.firestore.FirebaseFirestoreSource
import com.truyen.dexreader.domain.model.ReadingHistory
import com.truyen.dexreader.domain.repository.HistoryRepository
import com.truyen.dexreader.utils.runSuspendCatching
import com.truyen.dexreader.utils.toResultFlow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class HistoryRepositoryImpl @Inject constructor(
  private val firebaseFirestoreSource: FirebaseFirestoreSource
) : HistoryRepository {
  override fun observeHistory(
    userId: String,
    limit: Int,
    mangaId: String?,
    lastReadingHistoryId: String?
  ): Flow<Result<List<ReadingHistory>>> =
    firebaseFirestoreSource
      .observeHistory(
        userId = userId,
        limit = limit.toLong(),
        mangaId = mangaId,
        lastReadingHistoryId = lastReadingHistoryId
      )
      .map { readingHistoryDtoList ->
        readingHistoryDtoList.map { it.toDomain() }
      }
      .flowOn(Dispatchers.IO)
      .distinctUntilChanged()
      .toResultFlow()

  override suspend fun addAndUpdateToHistory(
    userId: String,
    readingHistory: ReadingHistory
  ): Result<Unit>  =
    runSuspendCatching(Dispatchers.IO) {
      firebaseFirestoreSource.addAndUpdateToHistory(
        userId = userId,
        readingHistory = readingHistory.toDto()
      )
    }

  override suspend fun removeFromHistory(
    userId: String,
    readingHistoryId: String
  ): Result<Unit> =
    runSuspendCatching(Dispatchers.IO) {
      firebaseFirestoreSource.removeFromHistory(
        userId = userId,
        readingHistoryId = readingHistoryId
      )
    }
}