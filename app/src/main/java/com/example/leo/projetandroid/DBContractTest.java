package com.example.leo.projetandroid;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public final class DBContractTest {

    private DBContractTest() {}

    /* BaseColumns inherit a primery key field called _ID that is needed sometimes */

    public static class table_ROOM /*implements BaseColumns*/ {
        public static final String TABLE_NAME = "t_ROOM";
        public static final String COLUMN_ID_ROOM = "ID_ROOM";
        public static final String COLUMN_LONG = "Long";
        public static final String COLUMN_LAT = "Lat";
        public static final String COLUMN_FLOOR = "Floor";
    }

    private static final String SQL_CREATE_ENTRIES_ROOM =
            "CREATE TABLE " +
                    table_ROOM.TABLE_NAME + " (" +
                    table_ROOM.COLUMN_ID_ROOM + " INTEGER PRIMARY KEY," +
                    table_ROOM.COLUMN_LONG + " INTEGER," +
                    table_ROOM.COLUMN_LAT + " INTEGER," +
                    table_ROOM.COLUMN_FLOOR + " INTEGER)";

    /*private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;*/

    public class DBHelper extends SQLiteOpenHelper {

        // If you change the database schema, you must increment the database version.
        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "FeedReader.db";

        public DBHelper (Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_ENTRIES_ROOM);
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // This database is only a cache for online data, so its upgrade policy is
            // to simply to discard the data and start over
            /*db.execSQL(SQL_DELETE_ENTRIES);
            onCreate(db);*/
        }

        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onUpgrade(db, oldVersion, newVersion);
        }
    }

}