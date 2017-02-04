package com.sergey.myeditor.data.remote;

import com.sergey.myeditor.data.AppDataStore;
import com.sergey.myeditor.data.api.EditorAPI;
import com.sergey.myeditor.data.local.AppLocalDataStore;
import com.sergey.myeditor.data.model.Field;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * Created by sergey on 03.02.17.
 */

public class AppRemoteDataStore implements AppDataStore {

    private final Retrofit retrofit;
    private final AppLocalDataStore appLocalDataStore;

    @Inject
    public AppRemoteDataStore(AppLocalDataStore appLocalDataStore, Retrofit retrofit) {
        this.appLocalDataStore = appLocalDataStore;
        this.retrofit = retrofit;
    }


    @Override
    public Observable<List<Field>> getFields() {
        return retrofit.create(EditorAPI.class).getToDoList().doOnNext(appLocalDataStore::saveFieldsToDatabase);
    }


}
