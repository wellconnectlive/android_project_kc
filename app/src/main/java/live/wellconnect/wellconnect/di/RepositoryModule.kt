package live.wellconnect.wellconnect.di

import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import live.wellconnect.wellconnect.data.DataRepository
import live.wellconnect.wellconnect.data.DataRepositoryImpl
import live.wellconnect.wellconnect.presentation.SignInViewModel

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {
    @Binds
    abstract fun bindRepository(repo : DataRepositoryImpl) : DataRepository

}