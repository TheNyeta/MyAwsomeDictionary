package com.example.myawesomedictionary;

//Name: Timothy Natan
//NIM: 2301870804

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class FavoriteHelper {
    DatabaseHelper helper;

    public FavoriteHelper(Context context) {
        helper = new DatabaseHelper(context);
    }

    public void insertFavorite(String word, String imageurl, String type, String definition){
        SQLiteDatabase db = helper.getWritableDatabase();

        String newDefinition = definition.replace("'", "''");

        db.execSQL("INSERT INTO Favorite(word, imageurl, type, definition) VALUES(" +
                "'"+word+"', '"+imageurl+"', '"+type+"', '"+newDefinition+"')");
        db.close();
        helper.close();
    }

    public void deleteFavorite(String word){
        SQLiteDatabase db = helper.getWritableDatabase();

        db.execSQL("DELETE FROM Favorite WHERE word = '"+word+"'");
        db.close();
        helper.close();
    }

    public ArrayList<WordD> getAllData(){
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Favorite", null);
        cursor.moveToFirst();

        ArrayList<WordD> wordDArrayList = null;
        if(cursor.getCount() > 0){
            wordDArrayList = new ArrayList<>();
            while(!cursor.isAfterLast()){
                String word = cursor.getString(cursor.getColumnIndex("word"));
                String imageurl = cursor.getString(cursor.getColumnIndex("imageurl"));
                String type = cursor.getString(cursor.getColumnIndex("type"));
                String definition = cursor.getString(cursor.getColumnIndex("definition"));

                wordDArrayList.add(new WordD(word, imageurl, type, definition));
                cursor.moveToNext();
            }
        }
        cursor.close();
        db.close();
        helper.close();
        return wordDArrayList;
    }

}
