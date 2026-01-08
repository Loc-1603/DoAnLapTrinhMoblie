package com.truyen.dexreader.domain.usecase.user

import com.truyen.dexreader.domain.model.User
import com.truyen.dexreader.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveCurrentUserUseCase @Inject constructor(
  private val userRepository: UserRepository
) {
  operator fun invoke(): Flow<Result<User?>> = userRepository.observeCurrentUser()
}