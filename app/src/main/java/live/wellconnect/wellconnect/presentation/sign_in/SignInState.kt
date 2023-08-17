package live.wellconnect.wellconnect.presentation
data class SignInState(
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null,
    val registerState : Boolean = false,
)
