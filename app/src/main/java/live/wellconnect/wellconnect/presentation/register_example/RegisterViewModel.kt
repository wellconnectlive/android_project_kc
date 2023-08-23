package live.wellconnect.wellconnect.presentation.register_example

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import live.wellconnect.wellconnect.data.DataRepository
import live.wellconnect.wellconnect.domain.UserRegister
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repository: DataRepository
) : ViewModel() {

    private val auth : FirebaseAuth = Firebase.auth

    private val _user : MutableLiveData <UserRegister> = MutableLiveData<UserRegister>()
    val user : MutableLiveData<UserRegister> get() = _user

    // todo, chequear porqué no puede ser una password menor a 6 , caso contrario da error
    fun registerUser(userRegister: UserRegister) = viewModelScope.launch(Dispatchers.IO) {
        auth.createUserWithEmailAndPassword(
            userRegister.email,
            userRegister.password
        ).addOnCompleteListener{ task ->
            if (task.isSuccessful) {
                Log.i("REGISTER", "Usuario autenticado con éxito")
                auth.currentUser?.sendEmailVerification()
                auth.currentUser?.let { repository.loadUser(userRegister, it.uid) }
            } else {
                Log.i("ERROR", "No se ha conseguido autenticar al usuario, intentelo nuevamente")
                // todo debe de ir un alert de error, chequear con las states
            }
        }.addOnFailureListener {
            Log.i("ERROR_REGISTER", "No se ha conseguido registrar al usuario, intentelo nuevamente")
        }
    }

}