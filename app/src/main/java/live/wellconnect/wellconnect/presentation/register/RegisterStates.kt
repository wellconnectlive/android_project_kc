package live.wellconnect.wellconnect.presentation.register

sealed class RegisterStates{

    data class NameTaking(val name : String) : RegisterStates()
    data class EmailTaking(val email : String) : RegisterStates()
    data class PasswordTaking(val password : String) : RegisterStates()
    data class RepasswordTaking(val repassword : String) : RegisterStates()
    data class TermsAndPolicyTaking(val isAccepted : Boolean) : RegisterStates()
    object ButtonClicked : RegisterStates()
}

data class RegisterUIStates(
    var name : String = "",
    var email : String = "",
    var password : String = "",
    var repassword : String = "",
    var termsAndPolicy : Boolean = false,

    // error states

    var nameError : Boolean = false,
    var emailError : Boolean = false,
    var passwordError : Boolean = false,
    var repasswordError : Boolean = false,
    var termsAndPolicyError : Boolean = false,
)