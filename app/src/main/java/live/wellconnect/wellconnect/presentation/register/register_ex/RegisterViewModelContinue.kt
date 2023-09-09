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
import live.wellconnect.wellconnect.domain.UserModelDTO
import javax.inject.Inject

@HiltViewModel
class RegisterViewModelContinue @Inject constructor(
    private val repository: DataRepository
) : ViewModel() {

    private val _user : MutableLiveData<UserModel> = MutableLiveData<UserModel>()
    private val _dataUser : MutableLiveData<UserModelDTO> = MutableLiveData<UserModelDTO>()
    val user : LiveData<UserModel> get() = _user
    val dataUser : LiveData<UserModelDTO> get() = _dataUser

    // rebuilt to firebase
    fun addUser(userModel: UserModel, email : String) = viewModelScope.launch(Dispatchers.IO) {
        Log.i("PUSHED2", userModel.toString())
        repository.insertUser(userModel, email)
    }
    fun getUserValues(uID : String) = viewModelScope.launch(Dispatchers.IO) {
        try{
            val userRegister = repository.checkUser(uID)
            _dataUser.postValue(userRegister)
            Log.i("USERMODEL_VIEWMODEL", userRegister.toString())
        } catch (_: Exception) {
            Log.i("USERMODEL_ERROR", _dataUser.value.toString())
        }
    }

}