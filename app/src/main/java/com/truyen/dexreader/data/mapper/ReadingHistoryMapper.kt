package com.truyen.dexreader.data.mapper

import com.truyen.dexreader.data.network.firebase.dto.ReadingHistoryDto
import com.truyen.dexreader.domain.model.ReadingHistory
import com.truyen.dexreader.utils.toTimeAgo

fun ReadingHistoryDto.toDomain() =
  ReadingHistory(
    id = id,
    mangaId = mangaId,
    mangaTitle = mangaTitle,
    mangaCoverUrl = mangaCoverUrl,
    chapterId = chapterId,
    chapterTitle = chapterTitle,
    chapterNumber = chapterNumber,
    chapterVolume = chapterVolume,
    lastReadPage = lastReadPage,
    totalChapterPages = totalChapterPages,
    lastReadAt = createdAt?.time.toTimeAgo()
  )

fun ReadingHistory.toDto() =
  ReadingHistoryDto(
    id = id,
    mangaId = mangaId,
    mangaTitle = mangaTitle,
    mangaCoverUrl = mangaCoverUrl,
    chapterId = chapterId,
    chapterTitle = chapterTitle,
    chapterNumber = chapterNumber,
    chapterVolume = chapterVolume,
    lastReadPage = lastReadPage,
    totalChapterPages = totalChapterPages,
  )