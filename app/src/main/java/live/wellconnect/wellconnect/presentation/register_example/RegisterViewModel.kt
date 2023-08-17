package live.wellconnect.wellconnect.presentation.register_example

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
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
    private val repository : DataRepository
) : ViewModel() {

    private val auth : FirebaseAuth = Firebase.auth

    private val _user : MutableLiveData <UserRegister> = MutableLiveData<UserRegister>()
    val user : MutableLiveData<UserRegister> get() = _user

    fun registerUser(userRegister: UserRegister) = viewModelScope.launch(Dispatchers.IO) {

        auth.createUserWithEmailAndPassword(
            userRegister.email,
            userRegister.password
        ).addOnCompleteListener{ task ->
            if (task.isSuccessful) {

            }
        }

        repository.loadUser(userRegister)
    }
}