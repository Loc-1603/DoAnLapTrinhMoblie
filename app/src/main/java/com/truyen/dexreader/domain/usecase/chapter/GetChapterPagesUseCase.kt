package com.truyen.dexreader.domain.usecase.chapter

import com.truyen.dexreader.domain.model.ChapterPages
import com.truyen.dexreader.domain.repository.ChapterRepository
import jakarta.inject.Inject

class GetChapterPagesUseCase @Inject constructor(
  private val chapterRepository: ChapterRepository,
) {
  suspend operator fun invoke(chapterId: String): Result<ChapterPages> =
    chapterRepository.getChapterPages(chapterId)
}