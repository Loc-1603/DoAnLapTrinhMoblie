package com.truyen.dexreader.data.mapper

import com.truyen.dexreader.data.network.firebase.dto.FavoriteMangaDto
import com.truyen.dexreader.domain.model.FavoriteManga
import com.truyen.dexreader.domain.model.Manga
import com.truyen.dexreader.utils.toTimeAgo

fun FavoriteMangaDto.toDomain() =
  FavoriteManga(
    id = id,
    title = title,
    coverUrl = coverUrl,
    author = author,
    status = status,
    addedAt = createdAt?.time?.toTimeAgo() ?: "Unknown time"
  )

fun FavoriteManga.toDto() =
  FavoriteMangaDto(
    id = id,
    title = title,
    coverUrl = coverUrl,
    author = author,
    status = status,
  )

fun FavoriteManga.toManga() =
  Manga(
    id = id,
    title = title,
    coverUrl = coverUrl,
    description = "",
    author = author,
    artist = "",
    categories = emptyList(),
    status = status,
    year = "",
    availableTranslatedLanguages = emptyList(),
    lastChapter = "",
    lastUpdated = ""
  )