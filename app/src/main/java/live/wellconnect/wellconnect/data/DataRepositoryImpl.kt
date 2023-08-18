package live.wellconnect.wellconnect.data

import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import live.wellconnect.wellconnect.data.local.DataDAO
import live.wellconnect.wellconnect.data.mappers.toUserDAO
import live.wellconnect.wellconnect.domain.UserModel
import live.wellconnect.wellconnect.domain.UserRegister
import javax.inject.Inject

class DataRepositoryImpl @Inject constructor(
    private val localStorage : DataDAO
) : DataRepository {



    override suspend fun insertUser(user: UserModel) = localStorage.insertNew(user.toUserDAO())

    override fun loadUser(user : UserRegister) {

    }
}