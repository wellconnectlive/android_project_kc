package live.wellconnect.wellconnect.viewmodels.firebaseService

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import live.wellconnect.wellconnect.domain.UserRegister

class FireBaseServiceImpl : FireBaseService {

    private val db = FirebaseFirestore.getInstance()
    private val auth : FirebaseAuth = Firebase.auth
    private val _user : MutableLiveData<UserRegister> = MutableLiveData<UserRegister>()
    val user : MutableLiveData<UserRegister> get() = _user

    override fun register(email: String, password: String): Boolean {
            auth.createUserWithEmailAndPassword(
                email,
                password,
            ).addOnCompleteListener{ task ->
                if (task.isSuccessful) {
                    auth.currentUser?.let { db.collection("users").document(it.uid) }
                } else {
                    Log.i("ERROR", "No se ha conseguido autenticar al usuario, intentelo nuevamente")
                    // todo debe de ir un alert de error, chequear con las states
                }
            }.addOnFailureListener {
                Log.i("ERROR_REGISTER", "No se ha conseguido registrar al usuario, intentelo nuevamente")
            }

        return true
    }

    /*
    override fun register(email: String, password: String) {
        auth.createUserWithEmailAndPassword(
            email,
            password,
        ).addOnCompleteListener{ task ->
            if (task.isSuccessful) {
                Log.i("REGISTER", "Usuario autenticado con éxito")
                /*auth.currentUser?.sendEmailVerification()
                auth.currentUser?.let { repository.loadUser(userRegister, it.uid) }

                isRegisterShow = true*/
            } else {
                Log.i("ERROR", "No se ha conseguido autenticar al usuario, intentelo nuevamente")
                // todo debe de ir un alert de error, chequear con las states
            }
        }.addOnFailureListener {
            Log.i("ERROR_REGISTER", "No se ha conseguido registrar al usuario, intentelo nuevamente")
        }*/
}