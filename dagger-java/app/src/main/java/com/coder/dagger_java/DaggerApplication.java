package com.coder.dagger_java;

import android.app.Application;
import android.content.Context;

import com.coder.dagger_java.data.DataManager;
import com.coder.dagger_java.di.component.ApplicationComponent;
import com.coder.dagger_java.di.component.DaggerApplicationComponent;
import com.coder.dagger_java.di.module.ApplicationModule;

import javax.inject.Inject;

public class DaggerApplication extends Application {

    protected ApplicationComponent applicationComponent;

    @Inject
    DataManager dataManager;

    public static DaggerApplication get(Context context) {
        return (DaggerApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        applicationComponent.inject(this);
    }

    public ApplicationComponent getComponent() {
        return applicationComponent;
    }

}
