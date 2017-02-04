package com.sergey.myeditor.data.model;

import com.pushtorefresh.storio.contentresolver.annotations.StorIOContentResolverColumn;
import com.pushtorefresh.storio.contentresolver.annotations.StorIOContentResolverType;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteColumn;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteType;
import com.sergey.myeditor.data.local.DatabaseContract;

@StorIOSQLiteType(table = DatabaseContract.Edit.TABLE_NAME)
@StorIOContentResolverType(uri = DatabaseContract.Edit.CONTENT_URI_STRING)
public class Field {

    @StorIOSQLiteColumn(name = DatabaseContract.Edit.COLUMN_USER_ID)
    @StorIOContentResolverColumn(name = DatabaseContract.Edit.COLUMN_USER_ID)
    int userId;
    @StorIOSQLiteColumn(name = DatabaseContract.Edit.COLUMN_ID, key = true)
    @StorIOContentResolverColumn(name = DatabaseContract.Edit.COLUMN_ID, key = true)
    int id;
    @StorIOSQLiteColumn(name = DatabaseContract.Edit.COLUMN_TITLE)
    @StorIOContentResolverColumn(name = DatabaseContract.Edit.COLUMN_TITLE)
    String title;
    @StorIOSQLiteColumn(name = DatabaseContract.Edit.COLUMN_FLAG)
    @StorIOContentResolverColumn(name = DatabaseContract.Edit.COLUMN_FLAG)
    Boolean completed;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Field withUserId(int userId) {
        this.userId = userId;
        return this;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Field withCompleted(boolean completed) {
        this.completed = completed;
        return this;
    }

}
