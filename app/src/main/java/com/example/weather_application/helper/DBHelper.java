package com.example.weather_application.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "favorites.db";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create table
        String createTable = "CREATE TABLE favorite_city (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "cityName TEXT UNIQUE)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Upgrade logic if needed
        db.execSQL("DROP TABLE IF EXISTS favorite_city");
        onCreate(db);
    }

    public long addFavoriteCity(String cityName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("cityName", cityName);
        long id = db.insert("favorite_city", null, values);
        db.close();
        return id;
    }

    public Cursor getAllFavoriteCities() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query("favorite_city", null, null, null, null, null, null);
    }

    public int removeFavoriteCity(String cityName) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsDeleted = db.delete("favorite_city", "cityName = ?", new String[]{cityName});
        db.close();
        return rowsDeleted;
    }
}