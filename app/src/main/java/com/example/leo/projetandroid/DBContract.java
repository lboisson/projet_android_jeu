package com.example.leo.projetandroid;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public final class DBContract {

    private DBContract() {}

    /* BaseColumns inherit a primery key field called _ID that is needed sometimes */

    public static class table_ROOM /*implements BaseColumns*/ {
        public static final String TABLE_NAME = "t_ROOM";
        public static final String COLUMN_ID_ROOM = "ID_ROOM";
        public static final String COLUMN_LONG = "Long";
        public static final String COLUMN_LAT = "Lat";
        public static final String COLUMN_FLOOR = "Floor";
    }

    public static class table_FOURN {
        public static final String TABLE_NAME = "t_FOURN";
        public static final String COLUMN_ID_FOURN = "ID_FOURN";
        public static final String COLUMN_LONG = "Long";
        public static final String COLUMN_LAT = "Lat";
        public static final String COLUMN_ID_ROOM = "ID_ROOM";
        public static final String COLUMN_NAME = "Name";
        public static final String COLUMN_SPRITE_NAME = "Sprite_Name";
        public static final String COLUMN_ORIENT = "Orient";
    }

    public static class table_ITEM {
        public static final String TABLE_NAME = "t_ITEM";
        public static final String COLUMN_ID_ITEM = "ID_ITEM";
        public static final String COLUMN_ID_FOURN = "ID_FOURN";
        public static final String COLUMN_IN_INVENTORY = "In_Inventory";
        public static final String COLUMN_Equipped = "Equipped";
        public static final String COLUMN_NAME = "Name";
    }

    public static class table_CHARA {
        public static final String TABLE_NAME = "t_CHARA";
        public static final String COLUMN_ID_CHARACTER = "ID_CHARACTER";
        public static final String COLUMN_NAME = "Name";
        public static final String COLUMN_SPRITE_NAME = "Sprite_Name";
        public static final String COLUMN_ID_ROOM = "ID_ROOM";
        public static final String COLUMN_LONG = "Long";
        public static final String COLUMN_LAT = "Lat";
        public static final String COLUMN_STATS = "Stats";
    }

    private static final String SQL_CREATE_ENTRIES_ROOM =
            "CREATE TABLE " +
                    table_ROOM.TABLE_NAME + " (" +
                    table_ROOM.COLUMN_ID_ROOM + " INTEGER PRIMARY KEY," +
                    table_ROOM.COLUMN_LONG + " INTEGER," +
                    table_ROOM.COLUMN_LAT + " INTEGER," +
                    table_ROOM.COLUMN_FLOOR + " INTEGER)";

    private static final String SQL_CREATE_ENTRIES_FOURN =
            "CREATE TABLE " +
                    table_FOURN.TABLE_NAME + " (" +
                    table_FOURN.COLUMN_ID_FOURN + " INTEGER PRIMARY KEY," +
                    table_FOURN.COLUMN_LONG + " INTEGER," +
                    table_FOURN.COLUMN_LAT + " INTEGER," +
                    table_FOURN.COLUMN_ID_ROOM + " INTEGER," +
                    table_FOURN.COLUMN_NAME + " TEXT," +
                    table_FOURN.COLUMN_SPRITE_NAME + " TEXT," +
                    table_FOURN.COLUMN_ORIENT + " INTEGER)";

    private static final String SQL_CREATE_ENTRIES_ITEM =
            "CREATE TABLE " +
                    table_ITEM.TABLE_NAME + " (" +
                    table_ITEM.COLUMN_ID_ITEM + " INTEGER PRIMARY KEY," +
                    table_ITEM.COLUMN_ID_FOURN + " INTEGER," +
                    table_ITEM.COLUMN_IN_INVENTORY + " INTEGER," + /* Integer act as a boolean here */
                    table_ITEM.COLUMN_Equipped + " INTEGER," + /* Integer act as a boolean here */
                    table_ITEM.COLUMN_NAME + " TEXT)";

    private static final String SAL_CREATE_ENTRIES_CHARA =
            "CREATE TABLE " +
                    table_CHARA.TABLE_NAME + " (" +
                    table_CHARA.COLUMN_ID_CHARACTER + " INTEGER PRIMARY KEY," +
                    table_CHARA.COLUMN_NAME + " TEXT," +
                    table_CHARA.COLUMN_SPRITE_NAME + " TEXT," +
                    table_CHARA.COLUMN_ID_ROOM + " INTEGER," +
                    table_CHARA.COLUMN_LONG + " INTEGER," +
                    table_CHARA.COLUMN_LAT + " INTEGER," +
                    table_CHARA.COLUMN_STATS + " TEXT)";

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
            db.execSQL(SQL_CREATE_ENTRIES_FOURN);
            db.execSQL(SQL_CREATE_ENTRIES_ITEM);
            db.execSQL(SAL_CREATE_ENTRIES_CHARA);
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