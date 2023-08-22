package live.wellconnect.wellconnect.data

import android.util.Log
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.tasks.await
import live.wellconnect.wellconnect.domain.UserModel
import live.wellconnect.wellconnect.domain.UserModelDTO
import live.wellconnect.wellconnect.domain.UserRegister
import javax.inject.Inject

class DataRepositoryImpl @Inject constructor(

) : DataRepository {

    private val db = FirebaseFirestore.getInstance()
    override suspend fun insertUser(user: UserModel) {
        TODO("Not yet implemented")
    }


    override fun loadUser(user : UserRegister) {
        db.collection("users").document(user.email).set(
            hashMapOf(
                "email" to user.email,
                "password" to user.password,
                "name" to user.name,
            )
        )
    }

    override suspend fun checkUser(uID: String) : UserModelDTO {

        var deferreduser = CompletableDeferred<UserModelDTO>()

        db.collection("users").document(uID).get().addOnSuccessListener {
            val userOnScope = it.toObject<UserModelDTO>()
            if(userOnScope != null) {
                deferreduser.complete(userOnScope)
            } else {
                deferreduser.completeExceptionally(Exception("Not User with that ID"))
            }
        }.addOnFailureListener {
            deferreduser.completeExceptionally(it)
        }

        return deferreduser.await()
    }

}