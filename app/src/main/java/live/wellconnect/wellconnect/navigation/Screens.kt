package live.wellconnect.wellconnect.navigation

sealed class Screens(val route : String) {
    object LoginScreen : Screens(LOGIN_BASE_ROUTE)
    object ProfileScreen : Screens(PROFILE_BASE_ROUTE)
    object RegisterScreen : Screens(REGISTER_BASE_ROUTE)

    companion object {
        private const val LOGIN_BASE_ROUTE = "LoginScreen"
        private const val PROFILE_BASE_ROUTE = "ProfileScreen"
        private const val REGISTER_BASE_ROUTE = "RegisterScreen"
    }
}