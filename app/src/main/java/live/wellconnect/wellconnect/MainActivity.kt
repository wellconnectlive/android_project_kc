package live.wellconnect.wellconnect


import android.annotation.SuppressLint
import android.app.Application
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
import com.jmoreno.mispruebasparawell.RegisterSecond
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
<<<<<<< Updated upstream
import live.wellconnect.wellconnect.data.local.SharedPreferenceServiceImpl
=======
import live.wellconnect.wellconnect.presentation.QR.QRScreen
>>>>>>> Stashed changes
import live.wellconnect.wellconnect.presentation.SignInScreen
import live.wellconnect.wellconnect.presentation.home.HomeScreen
import live.wellconnect.wellconnect.presentation.location.MyLocationScreen
import live.wellconnect.wellconnect.presentation.profile.ProfileScreen
import live.wellconnect.wellconnect.presentation.profile.ProfileScreenViewModel
import live.wellconnect.wellconnect.presentation.register.Register
import live.wellconnect.wellconnect.presentation.register.RegisterScreen
import live.wellconnect.wellconnect.presentation.register.RegisterViewModel
import live.wellconnect.wellconnect.presentation.register.register_ex.RegisterFirst
import live.wellconnect.wellconnect.presentation.register.register_ex.RegisterViewModelContinue
import live.wellconnect.wellconnect.presentation.sign_in.OnBoardingCircle
import live.wellconnect.wellconnect.presentation.sign_in.OnBoardingFirst
import live.wellconnect.wellconnect.presentation.sign_in.OnBoardingSecond
import live.wellconnect.wellconnect.presentation.sign_in.WelcomeFirst
import live.wellconnect.wellconnect.presentation.sign_in.WelcomeSecond
import live.wellconnect.wellconnect.presentation.sign_in.WelcomeThird


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val registerViewModelEx : RegisterViewModelContinue by viewModels()
    private val registerViewModel: RegisterViewModel by viewModels()
    private val profileViewModel: ProfileScreenViewModel  by viewModels()

    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }

    @SuppressLint("ComposableDestinationInComposeScope")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        SharedPreferenceServiceImpl.init(applicationContext)
        setContent {
            WellconnectTheme {
                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        val navController = rememberNavController()
<<<<<<< Updated upstream
                       // NavHost(navController = navController, startDestination = "sign_in") {
                        NavHost(navController = navController, startDestination = "onboarding_one") {


                            composable("onboarding_one"){
                                WelcomeFirst(
                                    welcomeSecond = { navController.navigate("onboarding_two") }
                                )
                            }

                            composable("onboarding_two"){
                                WelcomeSecond {
                                    navController.navigate("onboarding_three")
                                }
                            }

                            composable("onboarding_three"){
                                WelcomeThird{
                                    navController.navigate("sign_in")
                                }
                            }
=======
                        val userData = googleAuthUiClient.getSignedInUser()
                        NavHost(navController = navController, startDestination = "sign_in") {
>>>>>>> Stashed changes

                            composable("sign_in") {
                                val viewModel = viewModel<SignInViewModel>()
                                val state by viewModel.state.collectAsStateWithLifecycle()

                                LaunchedEffect(key1 = Unit) {
                                    if(googleAuthUiClient.getSignedInUser() != null) {
                                        //navController.navigate("profile")
                                        navController.navigate("home")
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
                                        navController.navigate("home")
                                        //navController.navigate("profile")
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
                                    onRegContinue = {
                                        navController.navigate("register_first")
                                    },
                                )
                            }

                            composable("home") {
                                HomeScreen(
                                    userData = googleAuthUiClient.getSignedInUser(),
                                    onQRShow = {
                                        navController.navigate("qr")
                                    },
                                    onMapClick = {
                                        navController.navigate("mylocation")
                                    }
                                )
                            }

                            composable("register") {
                                RegisterScreen(registerViewModel)
                               // navController.popBackStack()
                            }

                            composable("register_continue") {
                                Register(userData = googleAuthUiClient.getSignedInUser(), registerViewModelEx)
                                //navController.popBackStack()  // todo, chequear porque sobreescirbe en empty la pantalla
                            }
                            composable("register_first") {
                                RegisterFirst(userData = googleAuthUiClient.getSignedInUser(),registerViewModelEx, onRegContinue = {
                                    navController.navigate("register_second")
                                })
                                // navController.popBackStack()
                            }
                            composable("register_second") {
                                RegisterSecond(navController = navController, userData = googleAuthUiClient.getSignedInUser(),registerViewModelEx, onRegContinue = {
                                    navController.navigate("")
                                }, navigateBack = {})
                                // navController.popBackStack()
                            }


                            composable("qr") {
                                if (userData != null) {
                                    QRScreen(
                                        uiID = userData.userId,
                                    )
                                }
                            }

                            composable("mylocation") {
                                if (userData != null) {
                                    MyLocationScreen(
                                        uiID = userData.userId,
                                    )
                                }
                            }
                        }
                    }

            }
        }
    }
}





