package tjrus.ideasplatformtest.domain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tjrus.ideasplatformtest.data.repository.DatabaseRepository
import tjrus.ideasplatformtest.data.repository.DatabaseRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindDatabaseRepository(databaseRepositoryImpl: DatabaseRepositoryImpl): DatabaseRepository
}