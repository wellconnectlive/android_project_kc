package live.wellconnect.wellconnect.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SignInViewModel: ViewModel() {

    private val _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()

    // declarar con hilt para que sea singleton
    private val auth : FirebaseAuth = Firebase.auth

    //??? no dispara eventos ni toma los valores, o dónde lo hace?
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
                // todo : reorganizar ésto, se logra el Login pero es una asquerosa manera de forzarlo.
                onSignInResult(SignInResult((it.result.user?.let { it1 -> UserData(it1.uid, it1.email, "") }), ""))
            } else {
                Log.i("LOGIN_REJECT", "Usuario no iniciado con éxito $email")
            }
        }
    }
}