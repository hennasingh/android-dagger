package com.coder.dagger_kotlin

import android.app.Application
import com.coder.dagger_kotlin.di.component.ApplicationComponent
import com.coder.dagger_kotlin.di.component.DaggerApplicationComponent
import com.coder.dagger_kotlin.di.module.ApplicationModule

class DaggerApplication : Application() {

    companion object {
        lateinit var applicationComponent: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()

        applicationComponent.inject(this)
    }
}