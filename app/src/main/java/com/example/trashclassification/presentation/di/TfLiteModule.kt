package com.example.trashclassification.presentation.di

import android.content.Context
import com.example.trashclassification.ml.ModelUnquant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object TfLiteModule {
    @Provides
    fun providesModelUnquant(@ApplicationContext context: Context) = ModelUnquant.newInstance(context)
}