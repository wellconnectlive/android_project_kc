package live.wellconnect.wellconnect.presentation

data class SignInResult(
    val data: UserData?,
    val errorMessage: String?
)

data class UserData(
    val userId: String,
    //val username: String?,
    val email: String?,
    val name: String?,
    //val profilePictureUrl: String?
)


