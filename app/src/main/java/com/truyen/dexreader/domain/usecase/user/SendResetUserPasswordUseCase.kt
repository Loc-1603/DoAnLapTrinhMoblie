package com.truyen.dexreader.domain.usecase.user

import com.truyen.dexreader.domain.repository.UserRepository
import javax.inject.Inject

class SendResetUserPasswordUseCase @Inject constructor(
  private val userRepository: UserRepository
) {
  suspend operator fun invoke(email: String): Result<Unit> =
    userRepository.sendResetUserPassword(email)
}