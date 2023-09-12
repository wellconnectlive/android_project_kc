package live.wellconnect.wellconnect.presentation.profile

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
import live.wellconnect.wellconnect.domain.UserModelExample
import javax.inject.Inject

@HiltViewModel
class ProfileScreenViewModel @Inject constructor(
    private val repository: DataRepository
) : ViewModel(){

    //private val _dataUser : MutableLiveData<String> = MutableLiveData<String>()
    private val _dataUser : MutableLiveData<UserModelDTO> = MutableLiveData<UserModelDTO>()
    //private val _dataUser : MutableLiveData<UserModelExample> = MutableLiveData<UserModelExample>()
    //val dataUser : LiveData<UserModelExample> get() = _dataUser
    val dataUser : LiveData<UserModelDTO> get() = _dataUser
    val datoURL = "https://wellconnect.live/qr/?data="

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