package com.coder.dagger_kotlin.di.component

import android.app.Application
import android.content.Context
import com.coder.dagger_kotlin.DaggerApplication
import com.coder.dagger_kotlin.data.DataManager
import com.coder.dagger_kotlin.data.DbHelper
import com.coder.dagger_kotlin.data.SharedPrefsHelper
import com.coder.dagger_kotlin.di.ApplicationContext
import com.coder.dagger_kotlin.di.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(daggerApplication: DaggerApplication)

    @ApplicationContext
    fun getContext(): Context

    fun getApplication(): Application

    fun getDataManager(): DataManager

    fun getDbHelper(): DbHelper

    fun getSharedPrefs(): SharedPrefsHelper


}