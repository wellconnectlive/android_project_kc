package live.wellconnect.wellconnect.presentation

import live.wellconnect.wellconnect.presentation.register.RegisterStates

data class SignInState(
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null,
    val registerState : Boolean = false,
)

sealed class SignInStateOk{
    data class EmailTaking(val email : String) : SignInStateOk()
    data class PasswordTaking(val password : String) : SignInStateOk()
    object ButtonClicked : SignInStateOk()
}

data class SignInUIStateOk(
    var email : String = "",
    var password : String = "",

    // error states
    var emailError : Boolean = false,
    var passwordError : Boolean = false,
)