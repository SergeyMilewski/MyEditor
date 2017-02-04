package com.sergey.myeditor.data;

import com.sergey.myeditor.data.model.Field;

import java.util.List;

import rx.Observable;

/**
 * Created by sergey on 03.02.17.
 */

public interface AppDataStore {
    Observable<List<Field>> getFields();
}
