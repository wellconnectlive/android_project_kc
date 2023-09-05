package live.wellconnect.wellconnect.di

import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import live.wellconnect.wellconnect.data.DataRepositoryImpl
import live.wellconnect.wellconnect.presentation.SignInViewModel

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun providesFirebase() : DataRepositoryImpl = DataRepositoryImpl(
        db = FirebaseFirestore.getInstance()
    )

    /*@Provides
    fun providesAuth() : SignInViewModel = SignInViewModel(
        auth = Firebase.auth
    )*/
}