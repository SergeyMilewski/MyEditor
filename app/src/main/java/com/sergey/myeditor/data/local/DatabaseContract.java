package com.sergey.myeditor.data.local;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.annotation.NonNull;

/**
 * Created by sergey on 03.02.17.
 */

public class DatabaseContract {

    public static final String CONTENT_AUTHORITY = "com.sergey.myeditor";
    private static final String CONTENT_SCHEME = "content://";
    public static final Uri BASE_CONTENT_URI = Uri.parse(CONTENT_SCHEME + CONTENT_AUTHORITY);

    public static final String PATH_FIELD = "field";

    public DatabaseContract() {
    }

    public static abstract class Edit implements BaseColumns {
        @NonNull
        public static final String CONTENT_URI_STRING = "content://" + CONTENT_AUTHORITY + "/" + PATH_FIELD;
        public static final Uri CONTENT_URI = Uri.parse(CONTENT_URI_STRING);

        public static final String CONTENT_USER_TYPE = "vnd.android.cursor.dir/" + CONTENT_AUTHORITY + "/" + PATH_FIELD;
        public static final String CONTENT_USER_ITEM_TYPE = "vnd.android.cursor.item/" + CONTENT_AUTHORITY + "/" + PATH_FIELD;

        public static final String TABLE_NAME = "fields";

        public static final String COLUMN_ID = "id";
        public static final String COLUMN_USER_ID = "user_id";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_FLAG = "flag";

        public static String getPostCreateQuery() {
            return "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " LONG NOT NULL PRIMARY KEY, " +
                    COLUMN_USER_ID + " LONG , " +
                    COLUMN_TITLE + " TEXT NOT NULL, " +
                    COLUMN_FLAG + " TEXT NOT NULL" + ");";
        }

        public static String getUserDeleteQuery() {
            return "DROP TABLE IF EXISTS " + TABLE_NAME;
        }


        public static Uri buildUserUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }
}