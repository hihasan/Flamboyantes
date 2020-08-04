package com.flamboyantes.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.flamboyantes.model.CartModel;
import com.flamboyantes.model.FavoriteModel;
import com.flamboyantes.model.ProfileModel;
import com.flamboyantes.model.customers.Customer;

import java.util.ArrayList;
import java.util.List;

public class SqliteDatabaseHelper extends SQLiteOpenHelper {
    // Logcat tag
    private static final String LOG = "Flamboyantes";

    // Database Version
    private static final int DATABASE_VERSION = 3;
    // Database Name
    private static final String DATABASE_NAME = "flamboyantes_db";

    // Table Names
    private static final String FAVORITE = "favorite";
    private static final String CUSTOMERS = "customer";
    private static final String CART ="cart";

    //Customer Table Coloumn Name
    private static final String CUSTOMERS_SL_NO = "sl_no";
    private static final String CUSTOMERS_ID ="id";
    private static final String CUSTOMERS_FIRST_NAME = "first_name";
    private static final String CUSTOMERS_LAST_NAME ="last_name";
    private static final String CUSTOMERS_EMAIL ="email";

    //cart item Coloum Name
    private static final String CART_ID = "id";
    private static final String CART_ITEM_NAME = "name";
    private static final String CART_ITEM_IMAGE = "image";
    private static final String CART_ITEM_PRICE = "price";

    //Favorite Coloum Name
    private static final String FAVORITE_sl_no ="sl_no";
    private static final String FAVORITE_ID = "id";
    private static final String FAVORITE_IMAGE ="img";
    private static final String FAVORITE_NAME = "name";
    private static final String FAVORITE_PRICE = "price";
    private static final String FAVORITE_DATE = "date";

    private static final String CREATE_FAVORITE = "create table " + FAVORITE +" (sl_no INTEGER PRIMARY KEY AUTOINCREMENT, id TEXT, name TEXT, img TEXT, price TEXT, UNIQUE(id) ON CONFLICT REPLACE)";

    private static final String CREATE_CUSTOMERS = "create table " + CUSTOMERS +" (sl_no INTEGER PRIMARY KEY AUTOINCREMENT, id TEXT, first_name TEXT, last_name TEXT, email TEXT, UNIQUE(id) ON CONFLICT REPLACE)";

    private static final String CREATE_CART = "create table " + CART +" (sl_no INTEGER PRIMARY KEY AUTOINCREMENT, id TEXT, name TEXT, image TEXT, price TEXT, UNIQUE(id) ON CONFLICT REPLACE)";


    public SqliteDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_FAVORITE);
        db.execSQL(CREATE_CUSTOMERS);
        db.execSQL(CREATE_CART);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL(" DROP TABLE IF EXISTS " + FAVORITE);
        db.execSQL(" DROP TABLE IF EXISTS " + CUSTOMERS);
        db.execSQL(" DROP TABLE IF EXISTS " + CART);

        // create new tables
        onCreate(db);
    }

    public boolean customer(String id, String first_name, String last_name, String email){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(CUSTOMERS_ID, id);
        contentValues.put(CUSTOMERS_FIRST_NAME, first_name);
        contentValues.put(CUSTOMERS_LAST_NAME, last_name);
        contentValues.put(CUSTOMERS_EMAIL, email);

        long result = db.insert(CUSTOMERS, null, contentValues);

        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean favorite_insert(String id, String name, String img, String price){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
//        contentValues.put(FAVORITE_sl_no, sl_no);
        contentValues.put(FAVORITE_ID, id);
        contentValues.put(FAVORITE_NAME, name);
        contentValues.put(FAVORITE_IMAGE, img);
        contentValues.put(FAVORITE_PRICE, price);

        long result = db.insert(FAVORITE, null, contentValues);

        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean cart_item (String id, String name, String image, String price){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(CART_ID, id);
        contentValues.put(CART_ITEM_NAME, name);
        contentValues.put(CART_ITEM_IMAGE, image);
        contentValues.put(CART_ITEM_PRICE, price);

        long result = db.insert (CART, null, contentValues);

        if (result == -1)
            return false;
        else
            return true;
    }

    //get the all Profile
    public List<ProfileModel> getProfile() {
        // Data model list in which we have to return the data
        List<ProfileModel> data = new ArrayList<ProfileModel>();
        // Accessing database for reading data
        SQLiteDatabase db = getWritableDatabase();
        // Select query for selecting whole table data
        String select_query = "Select * from " + CUSTOMERS;
        // Cursor for traversing whole data into database
        Cursor cursor = db.rawQuery(select_query, null);
        try {
            // check if cursor move to first
            if (cursor.moveToFirst()) {
                // looping through all data and adding to arraylist
                do {
                    ProfileModel data_model = new ProfileModel(cursor.getString(2), cursor.getString(3), cursor.getString(4));
                    data.add(data_model);
                } while (cursor.moveToNext());
            }
        } finally {
            // After using cursor we have to close it
            cursor.close();

        }

        // Closing database
        db.close();

        // returning list
        return data;
    }

    //get the all Profile
    public List<ProfileModel> getProfileId() {
        // Data model list in which we have to return the data
        List<ProfileModel> data = new ArrayList<ProfileModel>();
        // Accessing database for reading data
        SQLiteDatabase db = getWritableDatabase();
        // Select query for selecting whole table data
        String select_query = "Select * from " + CUSTOMERS;
        // Cursor for traversing whole data into database
        Cursor cursor = db.rawQuery(select_query, null);
        try {
            // check if cursor move to first
            if (cursor.moveToFirst()) {
                // looping through all data and adding to arraylist
                do {
                    ProfileModel data_model = new ProfileModel(cursor.getString(1));
                    data.add(data_model);
                } while (cursor.moveToNext());
            }
        } finally {
            // After using cursor we have to close it
            cursor.close();

        }

        // Closing database
        db.close();

        // returning list
        return data;
    }

    //get the all cart item
    public List<CartModel> getCartItem() {
        // Data model list in which we have to return the data
        List<CartModel> data = new ArrayList<CartModel>();
        // Accessing database for reading data
        SQLiteDatabase db = getWritableDatabase();
        // Select query for selecting whole table data
        String select_query = "Select * from " + CART;
        // Cursor for traversing whole data into database
        Cursor cursor = db.rawQuery(select_query, null);
        try {
            // check if cursor move to first
            if (cursor.moveToFirst()) {
                // looping through all data and adding to arraylist
                do {
                    CartModel data_model = new CartModel(cursor.getString(3), cursor.getString(2), cursor.getString(4));
                    data.add(data_model);
                } while (cursor.moveToNext());
            }
        } finally {
            // After using cursor we have to close it
            cursor.close();

        }

        // Closing database
        db.close();

        // returning list
        return data;
    }

    //get the all favorite item
    public List<FavoriteModel> getFavorite() {
        // Data model list in which we have to return the data
        List<FavoriteModel> data = new ArrayList<FavoriteModel>();
        // Accessing database for reading data
        SQLiteDatabase db = getWritableDatabase();
        // Select query for selecting whole table data
        String select_query = "Select * from " + FAVORITE;
        // Cursor for traversing whole data into database
        Cursor cursor = db.rawQuery(select_query, null);
        try {
            // check if cursor move to first
            if (cursor.moveToFirst()) {
                // looping through all data and adding to arraylist
                do {
                    FavoriteModel data_model = new FavoriteModel(cursor.getString(3), cursor.getString(2), cursor.getString(4));
                    data.add(data_model);
                } while (cursor.moveToNext());
            }
        } finally {
            // After using cursor we have to close it
            cursor.close();

        }

        // Closing database
        db.close();

        // returning list
        return data;
    }
}
