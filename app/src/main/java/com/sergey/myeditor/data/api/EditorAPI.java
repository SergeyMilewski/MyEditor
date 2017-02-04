package com.sergey.myeditor.data.api;

import com.sergey.myeditor.data.model.Field;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by sergey on 02.02.17.
 */

public interface EditorAPI {

    @GET("/todos")
    Observable<List<Field>> getToDoList();
}
