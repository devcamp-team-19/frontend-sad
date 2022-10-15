package com.ex.scandanggerarea.di

import android.content.Context
import com.ex.scandanggerarea.data.local.SharedPref
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    @Provides
    fun providePref(@ApplicationContext appContext: Context): SharedPref = SharedPref(appContext)
}