package live.wellconnect.wellconnect.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

//sealed class Screens(val route : String) {
sealed class Screens {
    //object LoginScreen : Screens(LOGIN_BASE_ROUTE)
    //object LoginScreen : Screens()
    //object ProfileScreen : Screens(PROFILE_BASE_ROUTE)
    //object ProfileScreen : Screens()
    //object RegisterScreen : Screens(REGISTER_BASE_ROUTE)
    //object RegisterScreen : Screens()
    //object TermsAndConditionsScreen : Screens(TERMS_AND_CONDITIONS_ROUTE)
    object TermsAndConditionsScreen : Screens()

    /*companion object {
        /*private const val LOGIN_BASE_ROUTE = "LoginScreen"
        private const val PROFILE_BASE_ROUTE = "ProfileScreen"
        private const val REGISTER_BASE_ROUTE = "RegisterScreen"
        private const val TERMS_AND_CONDITIONS_ROUTE = "TermsAndConditionsScreen"*/

        fun navigateTo(detination : Screens){

        }
    }*/

}

object AppRouter {
    var currentScreen : MutableState<Screens> = mutableStateOf(Screens.TermsAndConditionsScreen)

    fun navigateTo(destination : Screens) {
        currentScreen.value = destination
    }
}