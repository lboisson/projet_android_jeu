package com.example.leo.projetandroid;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.preference.PreferenceManager;
import android.provider.BaseColumns;

public final class DBContractTest {

    private DBContractTest() {}

    /* BaseColumns inherit a primary key field called _ID that is needed sometimes */

    public static class table_ROOM implements BaseColumns {
        public static final String TABLE_NAME = "t_ROOM";
        public static final String COLUMN_LONG = "Long";
        public static final String COLUMN_LAT = "Lat";
        public static final String COLUMN_FLOOR = "Floor";
        public static final String COLUMN_FLOOR_SPRITE_NUMBER = "Floor_Sprite_Number";
        public static final String COLUMN_WALL_WEST_SPRITE_NAME = "Wall_West_Sprite_Name";
        public static final String COLUMN_WALL_EAST_SPRITE_NAME = "Wall_East_Sprite_Name";
        public static final String COLUMN_WALL_SOUTH_SPRITE_NAME = "Wall_South_Sprite_Name";
        public static final String COLUMN_WALL_NORTH_SPRITE_NAME = "Wall_North_Sprite_Name";
    }

    private static final String SQL_CREATE_ENTRIES_ROOM =
            "CREATE TABLE " +
                    table_ROOM.TABLE_NAME + " (" +
                    table_ROOM._ID + " INTEGER PRIMARY KEY," +
                    table_ROOM.COLUMN_LONG + " INTEGER," +
                    table_ROOM.COLUMN_LAT + " INTEGER," +
                    table_ROOM.COLUMN_FLOOR + " INTEGER," +
                    table_ROOM.COLUMN_FLOOR_SPRITE_NUMBER + " TEXT," +
                    table_ROOM.COLUMN_WALL_WEST_SPRITE_NAME + " TEXT," +
                    table_ROOM.COLUMN_WALL_EAST_SPRITE_NAME + " TEXT," +
                    table_ROOM.COLUMN_WALL_SOUTH_SPRITE_NAME + " TEXT," +
                    table_ROOM.COLUMN_WALL_NORTH_SPRITE_NAME + " TEXT)";

    /*private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;*/

    public static class DBHelper extends SQLiteOpenHelper {

        // If you change the database schema, you must increment the database version.
        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "FeedReader.db";
        private boolean exist;

        public DBHelper (Context context, boolean e) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            exist = e;
        }

        public void onCreate(SQLiteDatabase db) {
            if ( !exist ) {
                db.execSQL(SQL_CREATE_ENTRIES_ROOM);
            }
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