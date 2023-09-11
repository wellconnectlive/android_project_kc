package live.wellconnect.wellconnect.presentation.register

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import live.wellconnect.wellconnect.data.DataRepository
import live.wellconnect.wellconnect.data.local.SharedPreferenceServiceImpl
import live.wellconnect.wellconnect.domain.UserRegister
import live.wellconnect.wellconnect.utils.Validator
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repository: DataRepository
) : ViewModel() {

    private val TAGGER = RegisterViewModel::class.simpleName

    private var USER_SAVED = ""

    private lateinit var shared : SharedPreferenceServiceImpl

    private val auth : FirebaseAuth = Firebase.auth
    var isRegisterShow by mutableStateOf(false)
        private set

    var isTermsShow by mutableStateOf(false)
    var isPolicyShow by mutableStateOf(false)

    private val _user : MutableLiveData <UserRegister> = MutableLiveData<UserRegister>()
    val user : MutableLiveData<UserRegister> get() = _user

    var registerUIStates = mutableStateOf(RegisterUIStates())

    var isValidOK = mutableStateOf(false)

    var isSignIn = mutableStateOf(false)

    fun onEvent(event : RegisterStates) {
        when (event) {
            is RegisterStates.NameTaking -> {
                registerUIStates.value = registerUIStates.value.copy(
                    name = event.name
                )
                printState()
            }
            is RegisterStates.EmailTaking -> {
                registerUIStates.value = registerUIStates.value.copy(
                    email =  event.email
                )
                printState()
            }
            is RegisterStates.PasswordTaking -> {
                registerUIStates.value = registerUIStates.value.copy(
                    password = event.password
                )
                printState()
            }
            is RegisterStates.RepasswordTaking -> {
                registerUIStates.value = registerUIStates.value.copy(
                    repassword = event.repassword
                )
                printState()
            }
            is RegisterStates.TermsAndPolicyTaking -> {
                registerUIStates.value = registerUIStates.value.copy(
                    termsAndPolicy = event.isAccepted
                )
                printState()
            }
            is RegisterStates.ButtonClicked -> {
                val user = UserRegister(registerUIStates.value.name, registerUIStates.value.email, registerUIStates.value.password )
                registerUser(user)
            }
        }

        isAllFieldsValidates()
    }

    private fun isAllFieldsValidates() {
        val nameResult = Validator.validateName(name = registerUIStates.value.name)
        val emailResult = Validator.validateEmail(email = registerUIStates.value.email)
        val passwordResult = Validator.validatePassword(password = registerUIStates.value.password, registerUIStates.value.repassword)
        val termsAndPolicyResult = Validator.validateTermsAndPolicys(status = registerUIStates.value.termsAndPolicy)

        registerUIStates.value = registerUIStates.value.copy(
            nameError = nameResult,
            emailError = emailResult,
            passwordError = passwordResult,
            repasswordError = passwordResult,
            termsAndPolicyError = termsAndPolicyResult,
        )

        isValidOK.value = nameResult && emailResult && passwordResult  && termsAndPolicyResult
    }

    private fun registerUser(userRegister: UserRegister) = viewModelScope.launch(Dispatchers.IO) {
        auth.createUserWithEmailAndPassword(
            userRegister.email,
            userRegister.password,
        ).addOnCompleteListener{ task ->
            if (task.isSuccessful) {
                Log.i("REGISTER", "Usuario autenticado con éxito")
                auth.currentUser?.sendEmailVerification()
                auth.currentUser?.let { repository.loadUser(userRegister, it.uid) }

               // shared!!.putPrefString(userRegister.email, true)
               // Log.i("shared", shared!!.getPrefString(userRegister.email, true).toString())

                isRegisterShow = true
            } else {
                Log.i("ERROR", "No se ha conseguido autenticar al usuario, intentelo nuevamente")
                // todo debe de ir un alert de error, chequear con las states
            }
        }.addOnFailureListener {
            Log.i("ERROR_REGISTER", "No se ha conseguido registrar al usuario, intentelo nuevamente")
        }
    }

    fun signOut() {
        isRegisterShow = false
        auth.signOut()
    }

    private fun printState() {
        Log.d(TAGGER, registerUIStates.value.toString())
    }

}