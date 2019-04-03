package com.coder.dagger_java.di.component;

import com.coder.dagger_java.MainActivity;
import com.coder.dagger_java.di.PerActivity;
import com.coder.dagger_java.di.module.ActivityModule;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);
}
