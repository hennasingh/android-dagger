package com.coder.dagger_kotlin.di.component

import com.coder.dagger_kotlin.MainActivity
import com.coder.dagger_kotlin.di.PerActivity
import com.coder.dagger_kotlin.di.module.ActivityModule
import dagger.Component

@PerActivity
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(mainActivity: MainActivity)
}