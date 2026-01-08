package com.truyen.dexreader.data.mapper

import com.truyen.dexreader.data.network.mangadex_api.dto.TagDto
import com.truyen.dexreader.domain.model.Category

fun TagDto.toDomain(): Category =
  Category(
    id = id,
    title = attributes.name?.get("en") ?: "Unknown",
    group = attributes.group ?: "Unknown"
  )