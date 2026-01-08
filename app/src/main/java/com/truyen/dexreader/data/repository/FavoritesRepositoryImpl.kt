package com.truyen.dexreader.data.repository

import com.truyen.dexreader.data.mapper.toDomain
import com.truyen.dexreader.data.mapper.toDto
import com.truyen.dexreader.data.network.firebase.firestore.FirebaseFirestoreSource
import com.truyen.dexreader.domain.model.FavoriteManga
import com.truyen.dexreader.domain.repository.FavoritesRepository
import com.truyen.dexreader.utils.runSuspendCatching
import com.truyen.dexreader.utils.toResultFlow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoritesRepositoryImpl @Inject constructor(
  private val firebaseFirestoreSource: FirebaseFirestoreSource,
) : FavoritesRepository {
  override fun observeFavorites(
    userId: String,
    limit: Int,
    lastFavoriteMangaId: String?
  ): Flow<Result<List<FavoriteManga>>> =
    firebaseFirestoreSource
      .observeFavorites(
        userId = userId,
        limit = limit.toLong(),
        lastFavoriteMangaId = lastFavoriteMangaId
      )
      .map { favoriteMangaDtoList ->
        favoriteMangaDtoList.map { it.toDomain() }
      }
      .flowOn(Dispatchers.IO)
      .distinctUntilChanged()
      .toResultFlow()

  override suspend fun addToFavorites(
    userId: String,
    manga: FavoriteManga
  ): Result<Unit> =
    runSuspendCatching(Dispatchers.IO) {
      firebaseFirestoreSource.addToFavorites(userId, manga.toDto())
    }

  override suspend fun removeFromFavorites(
    userId: String,
    mangaId: String
  ): Result<Unit> =
    runSuspendCatching(Dispatchers.IO) {
      firebaseFirestoreSource.removeFromFavorites(userId, mangaId)
    }

  override fun observeIsFavorite(
    userId: String,
    mangaId: String
  ): Flow<Result<Boolean>> =
    firebaseFirestoreSource
      .observeIsFavorite(userId, mangaId)
      .flowOn(Dispatchers.IO)
      .distinctUntilChanged()
      .toResultFlow()
}