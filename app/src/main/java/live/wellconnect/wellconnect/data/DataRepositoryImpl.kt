package live.wellconnect.wellconnect.data

import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import live.wellconnect.wellconnect.data.local.DataDAO
import live.wellconnect.wellconnect.data.mappers.toUserDAO
import live.wellconnect.wellconnect.domain.UserModel
import live.wellconnect.wellconnect.domain.UserRegister
import javax.inject.Inject

class DataRepositoryImpl @Inject constructor(
    private val localStorage : DataDAO
) : DataRepository {

    private val db = FirebaseFirestore.getInstance()

    override suspend fun insertUser(user: UserModel) = localStorage.insertNew(user.toUserDAO())

    override fun loadUser(user : UserRegister) {
        db.collection("users").document(user.email).set(
            hashMapOf(
                "email" to user.email,
                "password" to user.password,
                "name" to user.name,
            )
        )
    }

    override suspend fun checkUser(uID: String) : String {

        var name : String = "hero"
        db.collection("users").document(uID).get().addOnSuccessListener {
            println(it.data)
            println(it.get("name"))
            name = it.get("name").toString()
        }

        return name
    }

}