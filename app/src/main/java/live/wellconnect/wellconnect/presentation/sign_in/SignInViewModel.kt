package live.wellconnect.wellconnect.presentation

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import live.wellconnect.wellconnect.utils.Validator
import javax.inject.Inject

class SignInViewModel @Inject constructor() : ViewModel()
{

    private val _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()

    // declarar con hilt para que sea singleton
    private val auth : FirebaseAuth = Firebase.auth

    var signUIStates = mutableStateOf(SignInUIStateOk())
    var isValidOk = mutableStateOf(false)
    var isSignIn = mutableStateOf(false)

    fun onEvent(event : SignInStateOk) {
        when (event) {
            is SignInStateOk.EmailTaking -> {
                signUIStates.value = signUIStates.value.copy(
                    email = event.email
                )
            }
            is SignInStateOk.PasswordTaking -> {
                signUIStates.value = signUIStates.value.copy(
                    password = event.password
                )
            }
            is SignInStateOk.ButtonClicked -> {
                login(signUIStates.value.email, signUIStates.value.password)
            }

            else -> {}
        }
        isAllFieldsValidates()
    }

    private fun isAllFieldsValidates() {
        val emailResult = Validator.validateEmail(email = signUIStates.value.email)
        val passwordResult = Validator.validatePasswordOnLogin(password = signUIStates.value.password)

        signUIStates.value = signUIStates.value.copy(
            emailError = emailResult,
            passwordError = passwordResult,
        )

        isValidOk.value = emailResult && passwordResult
    }
    fun onSignInResult(result: SignInResult) {
        _state.update { it.copy(
            isSignInSuccessful = result.data != null,
            signInError = result.errorMessage
        ) }
    }

    fun resetState() {
        _state.update { SignInState() }
    }

    fun registerState() {
        _state.update {
            it.copy(
                registerState = true
            )
        }
    }

    fun login(email : String, password : String){
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener{
            if(it.isSuccessful) {
                Log.i("LOGIN", "Usuario iniciado con éxito $email")
                onSignInResult(SignInResult((it.result.user?.let { it1 -> UserData(it1.uid, it1.email, "") }), ""))
            } else {
                Log.i("LOGIN_REJECT", "Usuario no iniciado con éxito $email")
            }
        }
    }
}