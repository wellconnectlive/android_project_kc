package live.wellconnect.wellconnect.data


import android.net.Uri
import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.CompletableDeferred
import live.wellconnect.wellconnect.domain.UserDataModel
import live.wellconnect.wellconnect.domain.UserModel
import live.wellconnect.wellconnect.domain.UserModelDTO
import live.wellconnect.wellconnect.domain.UserRegister
import java.io.File
import javax.inject.Inject

class DataRepositoryImpl @Inject constructor(db: FirebaseFirestore) : DataRepository {

    private val db = FirebaseFirestore.getInstance()

    // todo, prueba subida de imagenes en el perfil, se ha de mover
    private val storage = Firebase.storage("gs://welconnect.appspot.com/")
    // reference
    private val referenceStorage = storage.reference
    override suspend fun insertUser(user: UserModel, userUID : String) {
        val user = hashMapOf(
            "name" to user.name,
            //"lastNameFather" to user.lastNameFather,
           // "lastNameMother" to user.lastNameMother,
            "gender" to user.gender,
            //"dni" to user.dni,
            "email" to user.address,
            //"poblation" to user.poblation,
            "country" to user.country,
            "age" to user.age,
            "phoneNumber" to user.phoneNumber,
            "religion" to user.religion,
            "bloodType" to user.bloodType,
            "implants" to user.implants,
            //"postalCode" to user.postalCode,
        )

        db.collection("users").document(userUID).set(user)
            .addOnFailureListener{ error -> Log.i("ERROR_INSERT_USER", "Error en insert de user", error)}
            .addOnSuccessListener { Log.i("SUCESSFULL_INSERT", "User Update") }

    }


    override fun loadUser(user: UserRegister, currentUser: String) {
        db.collection("users").document(currentUser).set(
                hashMapOf(
                    "email" to user.email,
                    "password" to user.password,
                    "name" to user.name,
                )
            )
    }

    private fun getImage(photo : String) : Task<Uri> {
        val file = Uri.fromFile(File("$photo"))
        val imagesRef = referenceStorage.child("images/${file.lastPathSegment}")
        val uploadTask = imagesRef.putFile(file)

        // observers de la imagen subida
        uploadTask.addOnFailureListener {
            Log.i("ERROR_IMAGE", it.toString())
        }.addOnSuccessListener {
            Log.i("OK_IMAGE", it.toString())
        }

        return referenceStorage.downloadUrl
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
    override suspend fun insertUserContinue(user: UserModel, userContinue: UserDataModel, userUID : String) {
        val user = hashMapOf(
            "name" to user.name,
            "gender" to user.gender,
            "email" to user.address,
            "country" to user.country,
            "age" to user.age,
            "phoneNumber" to user.phoneNumber,
            "religion" to user.religion,
            "bloodType" to user.bloodType,
            "implants" to user.implants,
            "type" to userContinue.userType,
            "allowTracking" to userContinue.allowTracking,
            "contacts" to userContinue.contacts,
            "diseases" to userContinue.diseases,
            "medAllergy" to userContinue.medAllergy,
            "foodAllergy" to userContinue.medAllergy,
            "otherAllergy" to userContinue.otherAllergy,
        )

        db.collection("users").document(userUID).set(user)
            .addOnFailureListener{ error -> Log.i("ERROR_INSERT_USER", "Error en insert de user", error)}
            .addOnSuccessListener { Log.i("SUCESSFULL_INSERT", "User Update") }

    }


}
