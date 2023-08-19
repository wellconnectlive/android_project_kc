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

    override fun checkUser(uID: String): String {

        /*val userId = "el_id_del_usuario"
        val userReference = firebaseDatabase.getReference("users").child(userId)

        userReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val userData = snapshot.getValue(UserData::class.java)

                if (userData != null) {
                    val userName = userData.name
                    // Aquí puedes usar el valor del nombre (userName)
                } else {
                    // Manejar el caso en que userData es nulo
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Manejar errores de lectura
            }
        })*/
        var name = ""
        /*db.collection("users").document(uID).get().addOnSuccessListener {

        }*/
        //val ref = db.collection("users").document(uID).get()
        val ref = db.collection("users").get()

        ref.addOnSuccessListener {
            it.forEach { i -> println(i.data) }
        }
        return name
    }
}