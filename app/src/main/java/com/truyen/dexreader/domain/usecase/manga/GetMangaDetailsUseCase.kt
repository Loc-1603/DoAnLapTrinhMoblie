package com.truyen.dexreader.domain.usecase.manga

import com.truyen.dexreader.domain.model.Manga
import com.truyen.dexreader.domain.repository.MangaRepository
import jakarta.inject.Inject

class GetMangaDetailsUseCase @Inject constructor(
  private val mangaRepository: MangaRepository,
) {
  suspend operator fun invoke(mangaId: String): Result<Manga> =
    mangaRepository.getMangaDetails(mangaId)
}