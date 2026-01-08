package com.truyen.dexreader.domain.usecase.manga

import com.truyen.dexreader.domain.model.Manga
import com.truyen.dexreader.domain.repository.MangaRepository
import javax.inject.Inject

class GetTopRatedMangaListUseCase @Inject constructor(
  private val mangaRepository: MangaRepository
) {
  suspend operator fun invoke(): Result<List<Manga>> {
    return mangaRepository.getTopRatedMangaList()
  }
}