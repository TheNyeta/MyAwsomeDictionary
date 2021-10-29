package com.example.myawesomedictionary;

//Name: Timothy Natan
//NIM: 2301870804

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) {
        super(context, "MyAwesomeDictionary", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE Word(" +
                "word TEXT PRIMARY KEY)" +
                "");

        db.execSQL("CREATE TABLE Favorite(" +
                "word TEXT NOT NULL," +
                "imageurl TEXT," +
                "type TEXT NOT NULL," +
                "definition TEXT NOT NULL" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS Word");
        db.execSQL("DROP TABLE IF EXISTS Favorite");
    }
}
