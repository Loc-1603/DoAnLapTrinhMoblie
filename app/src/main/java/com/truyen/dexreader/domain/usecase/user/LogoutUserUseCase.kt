package com.truyen.dexreader.domain.usecase.user

import com.truyen.dexreader.domain.repository.UserRepository
import javax.inject.Inject

class LogoutUserUseCase @Inject constructor(
  private val userRepository: UserRepository
) {
  suspend operator fun invoke(): Result<Unit> = userRepository.logoutUser()
}