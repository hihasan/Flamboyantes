package com.flamboyantes.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SqliteDatabaseHelper extends SQLiteOpenHelper {
    // Logcat tag
    private static final String LOG = "Flamboyantes";

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "flamboyantes_db";

    // Table Names
    private static final String FAVORITE = "favorite";

    //Favorite Coloum Name
    private static final String FAVORITE_sl_no ="sl_no";
    private static final int FAVORITE_ID = 0;
    private static final String FAVORITE_IMAGE ="img";
    private static final String FAVORITE_NAME = "name";
    private static final String FAVORITE_PRICE = "price";
    private static final String FAVORITE_DATE = "date";

    private static final String CREATE_FAVORITE = "create table " + FAVORITE +" (sl_no INTEGER PRIMARY KEY AUTOINCREMENT, id INTEGER, name TEXT, img TEXT, price TEXT, UNIQUE(id) ON CONFLICT REPLACE)";


    public SqliteDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_FAVORITE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL(" DROP TABLE IF EXISTS " + FAVORITE);

        // create new tables
        onCreate(db);
    }

    public boolean favorite_insert(int id, String img, String name, String price){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
//        contentValues.put(FAVORITE_sl_no, sl_no);
        contentValues.put(String.valueOf(FAVORITE_ID), id);
        contentValues.put(FAVORITE_IMAGE, img);
        contentValues.put(FAVORITE_NAME, name);
        contentValues.put(FAVORITE_PRICE, price);

        long result = db.insert(FAVORITE, null, contentValues);

        if (result == -1)
            return false;
        else
            return true;
    }
}
