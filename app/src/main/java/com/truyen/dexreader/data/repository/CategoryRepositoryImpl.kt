package com.truyen.dexreader.data.repository

import com.truyen.dexreader.data.mapper.toDomain
import com.truyen.dexreader.data.network.mangadex_api.MangaDexApiService
import com.truyen.dexreader.di.UploadUrlQualifier
import com.truyen.dexreader.domain.model.Category
import com.truyen.dexreader.domain.model.Manga
import com.truyen.dexreader.domain.repository.CategoryRepository
import com.truyen.dexreader.utils.runSuspendCatching
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
  private val mangaDexApiService: MangaDexApiService,
  @UploadUrlQualifier private val uploadUrl: String
) : CategoryRepository {
  override suspend fun getCategoryList(): Result<List<Category>> =
    runSuspendCatching(Dispatchers.IO) {
      mangaDexApiService.getTagList().data.map { it.toDomain() }
    }

  override suspend fun getMangaListByCategory(
    categoryId: String,
    offset: Int,
    // sorting
    lastUpdated: String?, // latest update
    followedCount: String?, // trending
    createdAt: String?, // new release
    rating: String?, // top rated
    // filters
    status: List<String>, // ongoing, completed, hiatus, cancelled
    contentRating: List<String>, // safe, suggestive, erotica
  ): Result<List<Manga>> =
    runSuspendCatching(Dispatchers.IO) {
      mangaDexApiService.getMangaListByTag(
        tagId = categoryId,
        offset = offset,
        lastUpdated = lastUpdated,
        followedCount = followedCount,
        createdAt = createdAt,
        rating = rating,
        status = status,
        contentRating = contentRating
      ).data.map { it.toDomain(uploadUrl) }
    }
}