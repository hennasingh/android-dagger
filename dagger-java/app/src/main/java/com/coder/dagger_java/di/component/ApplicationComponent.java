package com.coder.dagger_java.di.component;

import android.app.Application;
import android.content.Context;

import com.coder.dagger_java.DaggerApplication;
import com.coder.dagger_java.data.DataManager;
import com.coder.dagger_java.data.DbHelper;
import com.coder.dagger_java.data.SharedPrefsHelper;
import com.coder.dagger_java.di.ApplicationContext;
import com.coder.dagger_java.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * After rebuilding, Dagger 2 generates the class DaggerApplicationComponent, that will generate
 * all the dependencies mentioned below(try ctrl+click on DaggerApplicationComponent) and then
 * these can be injected wherever @inject annotation is.
 * Dagger 2 uses builder pattern to provide us the dependencies
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(DaggerApplication daggerApplication);

    @ApplicationContext
    Context getContext();

    Application getApplication();

    DataManager getDataManager();

    SharedPrefsHelper getPreferenceHelper();

    DbHelper getDbHelper();
}
