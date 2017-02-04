package com.sergey.myeditor.ui.presenter;

import com.sergey.myeditor.BasePresenter;
import com.sergey.myeditor.UI;
import com.sergey.myeditor.data.local.AppLocalDataStore;
import com.sergey.myeditor.data.model.Field;
import com.sergey.myeditor.data.remote.AppRemoteDataStore;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by smileuski on 02.02.17.
 */
@Singleton
public class MainViewFragmentPresenter extends BasePresenter<MainViewFragmentPresenter.MainViewFragmentUI> {

    private List<Field> fields = new ArrayList<>();
    private Set<Field> changes;

    private final AppRemoteDataStore appRemoteDataStore;
    private final AppLocalDataStore appLocalDataStore;
    private boolean isInitialized;


    @Inject
    public MainViewFragmentPresenter(AppRemoteDataStore appRemoteDataStore, AppLocalDataStore appLocalDataStore) {
        this.appRemoteDataStore = appRemoteDataStore;
        this.appLocalDataStore = appLocalDataStore;
    }

    public void fillData() {
        if (isInitialized) {
            getDataFromDB();
            return;
        }
        appRemoteDataStore.getFields()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(val -> {
                    isInitialized = true;
                    fillData();
                });

    }

    private void getDataFromDB() {
        appLocalDataStore.getFields()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(val -> {
                    fields.addAll(val);
                    if (ui != null) {
                        ui.fillUI(fields);
                    }
                });
    }

    public interface MainViewFragmentUI extends UI {

        void fillUI(List<Field> fields);
    }
}
