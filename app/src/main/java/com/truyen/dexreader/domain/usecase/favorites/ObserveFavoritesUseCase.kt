package com.truyen.dexreader.domain.usecase.favorites

import com.truyen.dexreader.domain.model.FavoriteManga
import com.truyen.dexreader.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveFavoritesUseCase @Inject constructor(
  private val favoriteRepository: FavoritesRepository
) {
  operator fun invoke(
    userId: String,
    limit: Int = 20,
    lastFavoriteMangaId: String? = null
  ): Flow<Result<List<FavoriteManga>>> =
    favoriteRepository.observeFavorites(
      userId = userId,
      limit = limit,
      lastFavoriteMangaId = lastFavoriteMangaId
    )
}