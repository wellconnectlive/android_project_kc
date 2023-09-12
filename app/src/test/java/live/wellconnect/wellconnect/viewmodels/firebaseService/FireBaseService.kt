package live.wellconnect.wellconnect.viewmodels.firebaseService

import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.ktx.Firebase

interface FireBaseService {
    //fun register(email : String, password : String) : DocumentSnapshot?
    fun register(email : String, password : String) : Boolean
}