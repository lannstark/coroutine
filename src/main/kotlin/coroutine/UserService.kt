package coroutine

import kotlinx.coroutines.delay

class UserServiceV2 {

  private val userProfileRepository = UserProfileRepositoryV2()
  private val userImageRepository = UserImageRepositoryV2()

  suspend fun findUser(userId: Long): UserDtoV2 {
    println("유저를 가져오겠습니다")
    val profile = userProfileRepository.findProfile(userId)
    println("이미지를 가져오겠습니다")
    val image = userImageRepository.findImage(profile)
    return UserDtoV2(profile, image)
  }

}

data class UserDtoV2(
  val profile: ProfileV2,
  val image: ImageV2,
)


class UserProfileRepositoryV2 {
  suspend fun findProfile(userId: Long): ProfileV2 {
    delay(100L)
    return ProfileV2()
  }
}

class ProfileV2

class UserImageRepositoryV2 {
  suspend fun findImage(profile: ProfileV2): ImageV2 {
    delay(100L)
    return ImageV2()
  }
}

class ImageV2