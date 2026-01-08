package com.truyen.dexreader.domain.usecase.user

import com.truyen.dexreader.domain.model.User
import com.truyen.dexreader.domain.repository.UserRepository
import javax.inject.Inject

class AddAndUpdateUserProfileUseCase @Inject constructor(
  private val userRepository: UserRepository
) {
  suspend operator fun invoke(user: User): Result<Unit> =
    userRepository.addAndUpdateUserProfile(user)
}