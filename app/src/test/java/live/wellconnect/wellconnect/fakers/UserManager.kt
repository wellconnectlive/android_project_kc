package live.wellconnect.wellconnect.fakers

import live.wellconnect.wellconnect.viewmodels.firebaseService.FireBaseService
import live.wellconnect.wellconnect.viewmodels.firebaseService.FireBaseServiceImpl

class UserManager(
    private val firebaseInject : FireBaseService
) {

    fun getRegister(email : String, password : String) {
        firebaseInject.register(email, password)
    }
}