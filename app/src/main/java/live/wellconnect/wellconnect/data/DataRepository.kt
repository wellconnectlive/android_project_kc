package live.wellconnect.wellconnect.data

import live.wellconnect.wellconnect.domain.UserModel
import live.wellconnect.wellconnect.domain.UserRegister

interface DataRepository {

    suspend fun insertUser(user : UserModel)
    fun loadUser(user : UserRegister)
}