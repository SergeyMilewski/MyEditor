package com.sergey.myeditor;

import android.app.Application;

import com.sergey.myeditor.dagger.component.AppComponent;
import com.sergey.myeditor.dagger.component.DaggerAppComponent;
import com.sergey.myeditor.dagger.module.AppModule;

import com.sergey.myeditor.dagger.module.DataModule;

/**
 * Created by smileuski on 02.02.17.
 */

public class MyApplication extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .dataModule(new DataModule("http://jsonplaceholder.typicode.com/"))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
