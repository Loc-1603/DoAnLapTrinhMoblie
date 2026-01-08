package com.truyen.dexreader.domain.usecase.chapter

import com.truyen.dexreader.domain.model.Chapter
import com.truyen.dexreader.domain.repository.ChapterRepository
import javax.inject.Inject

class GetChapterDetailsUseCase @Inject constructor(
  private val chapterRepository: ChapterRepository,
) {
  suspend operator fun invoke(chapterId: String): Result<Chapter> =
    chapterRepository.getChapterDetails(chapterId)
}