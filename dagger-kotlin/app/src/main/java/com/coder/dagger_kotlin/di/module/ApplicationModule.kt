package com.coder.dagger_kotlin.di.module

import android.app.Application
import android.content.Context
import com.coder.dagger_kotlin.di.ApplicationContext
import com.coder.dagger_kotlin.di.DatabaseInfo
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule constructor(var application: Application) {

    @Provides
    @ApplicationContext
    fun provideContext(): Context = application

    @Provides
    fun provideApplication() = application

    @Provides
    @DatabaseInfo
    fun provideDatabaseName() = "demo-dagger.db"

    @Provides
    @DatabaseInfo
    fun provideDatabaseVersion() = 2

    @Provides
    fun provideSharedPrefs() = application.getSharedPreferences("demo-prefs", Context.MODE_PRIVATE)

}