package live.wellconnect.wellconnect.data

import live.wellconnect.wellconnect.domain.UserModel
import live.wellconnect.wellconnect.domain.UserModelDTO
import live.wellconnect.wellconnect.domain.UserRegister

interface DataRepository {


    suspend fun insertUser(user : UserModel, userUID : String)
    fun loadUser(user: UserRegister, currentUser: String)
    suspend fun checkUser(uID : String) : UserModelDTO
}