package tjrus.ideasplatformtest.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tjrus.ideasplatformtest.data.repository.DatabaseRepository
import tjrus.ideasplatformtest.domain.useCase.DeleteItemUseCase
import tjrus.ideasplatformtest.domain.useCase.DeleteItemUseCaseImpl
import tjrus.ideasplatformtest.domain.useCase.GetAllItemsUseCase
import tjrus.ideasplatformtest.domain.useCase.GetAllItemsUseCaseImpl
import tjrus.ideasplatformtest.domain.useCase.SearchByNameUseCase
import tjrus.ideasplatformtest.domain.useCase.SearchByNameUseCaseImpl
import tjrus.ideasplatformtest.domain.useCase.UpdateItemUseCase
import tjrus.ideasplatformtest.domain.useCase.UpdateItemUseCaseImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideGetAllItemsUseCase(
        repository: DatabaseRepository
    ): GetAllItemsUseCase = GetAllItemsUseCaseImpl(repository)

    @Singleton
    @Provides
    fun provideSearchByNameUseCase(
        repository: DatabaseRepository
    ): SearchByNameUseCase = SearchByNameUseCaseImpl(repository)

    @Singleton
    @Provides
    fun provideUpdateItemUseCase(
        repository: DatabaseRepository
    ): UpdateItemUseCase = UpdateItemUseCaseImpl(repository)

    @Singleton
    @Provides
    fun provideDeleteItemUseCase(
        repository: DatabaseRepository
    ): DeleteItemUseCase = DeleteItemUseCaseImpl(repository)
}