package live.wellconnect.wellconnect.presentation.register.register_ex

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import live.wellconnect.wellconnect.data.DataRepository
import live.wellconnect.wellconnect.domain.UserModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModelContinue @Inject constructor(
    private val repository: DataRepository
) : ViewModel() {

    private val _user : MutableLiveData<UserModel> = MutableLiveData<UserModel>()
    val user : LiveData<UserModel> get() = _user

    // rebuilt to firebase
    fun addUser(userModel: UserModel, id: String) = viewModelScope.launch(Dispatchers.IO) {
        Log.i("PUSHED2", userModel.toString())
        repository.insertUser(userModel, id)
    }
}