package live.wellconnect.wellconnect.utils

import java.util.regex.Pattern

object Validator {

    fun validateName(name : String) = !name.isNullOrEmpty() && name.length >= 2

    fun validateEmail(email : String) = !email.isNullOrEmpty() && isValidEmail(email)

    private fun isValidEmail(email : String) : Boolean {
        val EMAIL_IS_OK = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
        )

        return EMAIL_IS_OK.matcher(email).matches()
    }

    fun validatePassword(password : String) = !password.isNullOrEmpty() && password.length >= 6

    fun validateTermsAndPolicys(status : Boolean) = status

}

