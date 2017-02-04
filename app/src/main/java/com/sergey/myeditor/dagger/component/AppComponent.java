package com.sergey.myeditor.dagger.component;

import com.sergey.myeditor.dagger.module.AppModule;
import com.sergey.myeditor.dagger.module.DataModule;
import com.sergey.myeditor.data.remote.AppRemoteDataStore;
import com.sergey.myeditor.ui.MainViewFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by smileuski on 02.02.17.
 */
@Singleton
@Component(modules = {AppModule.class, DataModule.class})
public interface AppComponent {
    void inject(MainViewFragment mainViewFragment);

    void inject(AppRemoteDataStore appRemoteDataStore);
}
