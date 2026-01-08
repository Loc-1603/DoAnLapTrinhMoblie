package com.truyen.dexreader.domain.usecase.user

import com.truyen.dexreader.domain.model.User
import com.truyen.dexreader.domain.repository.UserRepository
import javax.inject.Inject

class RegisterUserUseCase @Inject constructor(
  private val userRepository: UserRepository
) {
  suspend operator fun invoke(email: String, password: String): Result<User> =
    userRepository.registerUser(email, password)
}