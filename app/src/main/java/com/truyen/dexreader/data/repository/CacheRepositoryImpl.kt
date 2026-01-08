package com.truyen.dexreader.data.repository

import com.truyen.dexreader.data.local.database.dao.ChapterCacheDao
import com.truyen.dexreader.data.mapper.toDomain
import com.truyen.dexreader.data.mapper.toEntity
import com.truyen.dexreader.domain.model.ChapterPages
import com.truyen.dexreader.domain.repository.CacheRepository
import com.truyen.dexreader.utils.runSuspendCatching
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class CacheRepositoryImpl @Inject constructor(
  private val chapterCacheDao: ChapterCacheDao
) : CacheRepository {
  override suspend fun addChapterCache(
    mangaId: String, chapterPages: ChapterPages
  ): Result<Unit> = runSuspendCatching(Dispatchers.IO) {
    chapterCacheDao.addChapterCache(chapterCacheEntity = chapterPages.toEntity(mangaId))
  }

  override suspend fun getChapterCache(chapterId: String): Result<ChapterPages> =
    runSuspendCatching(Dispatchers.IO) {
      chapterCacheDao.getChapterCache(chapterId)?.toDomain()
        ?: throw IllegalArgumentException("Chapter cache not found.")
    }

  override suspend fun deleteChapterCache(chapterId: String): Result<Unit> =
    runSuspendCatching(Dispatchers.IO) {
      chapterCacheDao.deleteChapterCache(chapterId)
    }

  override suspend fun clearExpiredCache(expiryTimestamp: Long): Result<Unit> =
    runSuspendCatching(Dispatchers.IO) {
      chapterCacheDao.clearExpiredCache(expiryTimestamp)
    }

}