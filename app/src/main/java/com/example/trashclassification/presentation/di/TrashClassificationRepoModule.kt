package com.example.trashclassification.presentation.di

import com.example.trashclassification.data.repository.TrashClassificationRepoImpl
import com.example.trashclassification.domain.repository.TrashClassificationRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class TrashClassificationRepoModule {
    @Binds
    abstract fun bindTrashClassificationRepo(trashClassificationRepoImpl: TrashClassificationRepoImpl)
        : TrashClassificationRepo
}