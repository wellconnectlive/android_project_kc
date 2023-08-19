package live.wellconnect.wellconnect


import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.auth.api.identity.Identity
import kotlinx.coroutines.launch
import live.wellconnect.wellconnect.presentation.sign_in.GoogleAuthUiClient
import live.wellconnect.wellconnect.ui.theme.WellconnectTheme
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import live.wellconnect.wellconnect.presentation.SignInViewModel
import androidx.activity.viewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import live.wellconnect.wellconnect.presentation.SignInScreen
import live.wellconnect.wellconnect.presentation.profile.ProfileScreen
import live.wellconnect.wellconnect.presentation.profile.ProfileScreenViewModel
import live.wellconnect.wellconnect.presentation.register_example.Register
import live.wellconnect.wellconnect.presentation.register_example.RegisterScreen
import live.wellconnect.wellconnect.presentation.register_example.RegisterViewModel
import live.wellconnect.wellconnect.presentation.register_example.RegisterViewModel_ex


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val registerViewModelEx : RegisterViewModel_ex by viewModels()
    private val registerViewModel: RegisterViewModel by viewModels()
    private val profileViewModel: ProfileScreenViewModel  by viewModels()

    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WellconnectTheme {
                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        val navController = rememberNavController()
                        NavHost(navController = navController, startDestination = "sign_in") {

                            composable("sign_in") {
                                val viewModel = viewModel<SignInViewModel>()
                                val state by viewModel.state.collectAsStateWithLifecycle()

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
                                                viewModel.onSignInResult(signInResult)
                                            }
                                        }
                                    }
                                )


                                LaunchedEffect(key1 = state.isSignInSuccessful, key2 = state.registerState) {
                                    if(state.isSignInSuccessful) {
                                        Toast.makeText(
                                            applicationContext,
                                            "Sign in successful",
                                            Toast.LENGTH_LONG
                                        ).show()

                                        navController.navigate("profile")
                                        viewModel.resetState()
                                    }
                                    if(state.registerState) {
                                        Toast.makeText(
                                            applicationContext,
                                            "Register",
                                            Toast.LENGTH_LONG
                                        ).show()

                                        navController.navigate("register")
                                        viewModel.resetState()
                                    }
                                }

                                SignInScreen(
                                    state = state,
                                    onSignInClick = {
                                        lifecycleScope.launch {
                                            val signInIntentSender = googleAuthUiClient.signIn()
                                            launcher.launch(
                                                IntentSenderRequest.Builder(
                                                    signInIntentSender ?: return@launch
                                                ).build()
                                            )
                                        }
                                    },
                                    onRegisterClick = {
                                        Log.i("PUSH_REG", "PRESIONA DESDE REG")
                                        navController.navigate("register")
                                    },
                                    viewModel,
                                )
                            }


                            composable("profile") {
                                ProfileScreen(
                                    userData = googleAuthUiClient.getSignedInUser(),
                                    onSignOut = {
                                        lifecycleScope.launch {
                                            googleAuthUiClient.signOut()
                                            Toast.makeText(
                                                applicationContext,
                                                "Signed out",
                                                Toast.LENGTH_LONG
                                            ).show()

                                            navController.popBackStack()
                                        }
                                    },
                                    profileViewModel,
                                )
                            }

                            composable("register") {
                                RegisterScreen(registerViewModel)
                               // navController.popBackStack()
                            }
                        }
                    }

            }
        }
    }
}





