import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import live.wellconnect.wellconnect.navigation.AppRouter
import live.wellconnect.wellconnect.navigation.Screens
import live.wellconnect.wellconnect.presentation.register.TermsAndConditionsScreen

/*@Composable
fun NavigationApp() {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Color.Black
    ) {
        Crossfade(targetState = AppRouter.currentScreen) { currentState ->
            when(currentState.value){
                is Screens.TermsAndConditionsScreen -> {
                    TermsAndConditionsScreen({})
                }

                else -> {}
            }
        }
    }
}

/*package live.wellconnect.wellconnect.navigation

import android.app.Activity.RESULT_OK
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.auth.api.identity.Identity
import live.wellconnect.wellconnect.presentation.SignInViewModel
import live.wellconnect.wellconnect.presentation.sign_in.GoogleAuthUiClient

@Composable
fun NavigationGraph(
    loginViewModel : SignInViewModel,
    googleAuthUiClient : GoogleAuthUiClient,
) {

    val navController = rememberNavController()

    /**
     * - Se debe de indicar las pantallas a enlazar con el orden lógico que deseemos
     */
    NavHost(
        navController = navController,
        startDestination = Screens.LoginScreen.route
    ){

        composable(Screens.LoginScreen.route){
            val state = loginViewModel.state
            LaunchedEffect(key1 = Unit) {
                if(googleAuthUiClient.getSignedInUser() != null) {
                    navController.navigate("profile")
                }
            }

            val launcher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.StartIntentSenderForResult(),
                onResult = { result ->
                    if(result.resultCode == RESULT_OK) {
                        lifecycleScope.launch {
                            val signInResult = googleAuthUiClient.signInWithIntent(
                                intent = result.data ?: return@launch
                            )
                            loginViewModel.onSignInResult(signInResult)
                        }
                    }
                }
            )

            LaunchedEffect(key1 = state.isSignInSuccessful) {
                if(state.isSignInSuccessful) {
                    Toast.makeText(
                        applicationContext,
                        "Sign in successful",
                        Toast.LENGTH_LONG
                    ).show()

                    navController.navigate("profile")
                    loginViewModel.resetState()
                }
            }
        }
    }
}*/