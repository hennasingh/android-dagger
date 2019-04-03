package com.coder.dagger_java.di.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.coder.dagger_java.di.ApplicationContext;
import com.coder.dagger_java.di.DatabaseInfo;

import dagger.Module;
import dagger.Provides;

/**
 * The class annotated with @Module provides dependencies to other classes. @Provides annotation marks the
 * methods inside the modules, which provides the dependencies
 */
@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application app) {
        mApplication = app;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return "demo-dagger.db";
    }

    @Provides
    @DatabaseInfo
    Integer provideDatabaseVersion() {
        return 2;
    }

    @Provides
    SharedPreferences provideSharedPrefs() {
        return mApplication.getSharedPreferences("demo-prefs", Context.MODE_PRIVATE);
    }
}
