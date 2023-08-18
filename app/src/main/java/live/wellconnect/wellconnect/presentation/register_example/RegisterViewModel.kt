package live.wellconnect.wellconnect.presentation.register_example

import android.util.Log
import androidx.compose.material3.AlertDialog
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.oAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import live.wellconnect.wellconnect.data.DataRepository
import live.wellconnect.wellconnect.domain.UserRegister
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repository : DataRepository
) : ViewModel() {

    // move to repo

    private val auth : FirebaseAuth = Firebase.auth
    // just proof to mov own repository
    private val db = FirebaseFirestore.getInstance()

    private val _user : MutableLiveData <UserRegister> = MutableLiveData<UserRegister>()
    val user : MutableLiveData<UserRegister> get() = _user

    fun registerUser(userRegister: UserRegister) = viewModelScope.launch(Dispatchers.IO) {
        auth.createUserWithEmailAndPassword(
            userRegister.email,
            userRegister.password
        ).addOnCompleteListener{ task ->
            if (task.isSuccessful) {
                Log.i("REGISTER", "No se ha conseguido autenticar al usuario, intentelo nuevamente")
                db.collection("users").document(userRegister.email).set(
                    hashMapOf(
                        "email" to userRegister.email,
                        "password" to userRegister.password,
                        "name" to userRegister.name,
                    )
                )
                //repository.loadUser(userRegister)
            } else {
                Log.i("ERROR", "No se ha conseguido autenticar al usuario, intentelo nuevamente")
                // todo debe de ir un alert de error, chequear con las states
                //getMessageError()
            }
        }

    }

}