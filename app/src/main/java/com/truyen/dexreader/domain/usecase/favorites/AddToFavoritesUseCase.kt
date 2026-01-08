package com.truyen.dexreader.domain.usecase.favorites

import com.truyen.dexreader.domain.model.FavoriteManga
import com.truyen.dexreader.domain.repository.FavoritesRepository
import javax.inject.Inject

class AddToFavoritesUseCase @Inject constructor(
  private val favoriteRepository: FavoritesRepository
) {
  suspend operator fun invoke(userId: String, manga: FavoriteManga): Result<Unit> =
    favoriteRepository.addToFavorites(userId, manga)
}