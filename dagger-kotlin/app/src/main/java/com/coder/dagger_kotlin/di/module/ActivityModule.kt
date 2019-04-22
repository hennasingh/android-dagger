package com.coder.dagger_kotlin.di.module

import android.app.Activity
import android.content.Context
import com.coder.dagger_kotlin.di.ActivityContext
import dagger.Module
import dagger.Provides

@Module
class ActivityModule constructor(var activity: Activity) {

    @Provides
    @ActivityContext
    fun provideContext(): Context = activity

    @Provides
    fun provideActivity() = activity
}