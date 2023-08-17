package live.wellconnect.wellconnect.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import live.wellconnect.wellconnect.data.DataRepository
import live.wellconnect.wellconnect.data.DataRepositoryImpl

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindRepository(repo : DataRepositoryImpl) : DataRepository
}