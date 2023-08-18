package live.wellconnect.wellconnect.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import live.wellconnect.wellconnect.data.local.DataDAO
import live.wellconnect.wellconnect.data.local.LocalDataSource
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    fun getDataBase(@ApplicationContext context: Context) : LocalDataSource {
        return Room.databaseBuilder(
            context,
            LocalDataSource::class.java, "user-db"
        ).build()
    }

    @Provides
    fun getDao(db : LocalDataSource) : DataDAO = db.getDataQueryFromDAO()

    // providers de firebase
    @Provides
    fun getCollectionFireBase() = Firebase.firestore.collection("users")
}