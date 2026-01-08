package com.truyen.dexreader.di

import com.truyen.dexreader.data.repository.CacheRepositoryImpl
import com.truyen.dexreader.data.repository.CategoryRepositoryImpl
import com.truyen.dexreader.data.repository.ChapterRepositoryImpl
import com.truyen.dexreader.data.repository.FavoritesRepositoryImpl
import com.truyen.dexreader.data.repository.HistoryRepositoryImpl
import com.truyen.dexreader.data.repository.MangaRepositoryImpl
import com.truyen.dexreader.data.repository.SettingsRepositoryImpl
import com.truyen.dexreader.data.repository.UserRepositoryImpl
import com.truyen.dexreader.domain.repository.CacheRepository
import com.truyen.dexreader.domain.repository.CategoryRepository
import com.truyen.dexreader.domain.repository.ChapterRepository
import com.truyen.dexreader.domain.repository.FavoritesRepository
import com.truyen.dexreader.domain.repository.HistoryRepository
import com.truyen.dexreader.domain.repository.MangaRepository
import com.truyen.dexreader.domain.repository.SettingsRepository
import com.truyen.dexreader.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
  @Binds
  fun provideMangaRepository(
    mangaRepositoryImpl: MangaRepositoryImpl
  ): MangaRepository

  @Binds
  fun provideChapterRepository(
    chapterRepositoryImpl: ChapterRepositoryImpl
  ): ChapterRepository

  @Binds
  fun provideCacheRepository(
    cacheRepositoryImpl: CacheRepositoryImpl
  ): CacheRepository

  @Binds
  fun provideCategoryRepository(
    categoryRepositoryImpl: CategoryRepositoryImpl
  ): CategoryRepository

  @Binds
  fun provideUserRepository(
    userRepositoryImpl: UserRepositoryImpl
  ): UserRepository

  @Binds
  fun provideFavoritesRepository(
    favoritesRepositoryImpl: FavoritesRepositoryImpl
  ): FavoritesRepository

  @Binds
  fun provideHistoryRepository(
    historyRepositoryImpl: HistoryRepositoryImpl
  ): HistoryRepository

  @Binds
  fun provideSettingsRepository(
    settingsRepositoryImpl: SettingsRepositoryImpl
  ): SettingsRepository
}