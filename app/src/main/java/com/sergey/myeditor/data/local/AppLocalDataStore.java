package com.sergey.myeditor.data.local;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.pushtorefresh.storio.contentresolver.ContentResolverTypeMapping;
import com.pushtorefresh.storio.contentresolver.StorIOContentResolver;
import com.pushtorefresh.storio.contentresolver.impl.DefaultStorIOContentResolver;
import com.pushtorefresh.storio.contentresolver.queries.Query;
import com.sergey.myeditor.data.AppDataStore;
import com.sergey.myeditor.data.model.Field;
import com.sergey.myeditor.data.model.FieldStorIOContentResolverDeleteResolver;
import com.sergey.myeditor.data.model.FieldStorIOContentResolverGetResolver;
import com.sergey.myeditor.data.model.FieldStorIOContentResolverPutResolver;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by sergey on 03.02.17.
 */

public class AppLocalDataStore implements AppDataStore {

    private StorIOContentResolver storIOContentResolver;

    @Inject
    public AppLocalDataStore(@NonNull Context context) {
        storIOContentResolver = DefaultStorIOContentResolver.builder()
                .contentResolver(context.getContentResolver())
                .addTypeMapping(Field.class, ContentResolverTypeMapping.<Field>builder()
                        .putResolver(new FieldStorIOContentResolverPutResolver())
                        .getResolver(new FieldStorIOContentResolverGetResolver())
                        .deleteResolver(new FieldStorIOContentResolverDeleteResolver())
                        .build()
                ).build();
    }


    @Override
    public Observable<List<Field>> getFields() {
        return storIOContentResolver.get()
                .listOfObjects(Field.class)
                .withQuery(Query.builder()
                        .uri(DatabaseContract.Edit.CONTENT_URI)
                        .build())

                .prepare()
                .asRxObservable();
    }

    public void saveFieldsToDatabase(List<Field> fields) {
        storIOContentResolver.put().objects(fields).prepare().executeAsBlocking();
    }
}
