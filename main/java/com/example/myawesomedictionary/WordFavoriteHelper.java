package com.example.myawesomedictionary;

//Name: Timothy Natan
//NIM: 2301870804

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class WordFavoriteHelper {
    DatabaseHelper helper;

    public WordFavoriteHelper(Context context) {
        helper = new DatabaseHelper(context);
    }

    public void insertWordFavorite(String word){
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("INSERT INTO Word VALUES('"+word+"')");
        db.close();
        helper.close();
    }

    public void deleteWordFavorite(String word){
        SQLiteDatabase db = helper.getWritableDatabase();

        db.execSQL("DELETE FROM Word WHERE word = '"+word+"'");
        db.close();
        helper.close();
    }

    public ArrayList<String> getAllData(){
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Word ORDER BY word", null);
        cursor.moveToFirst();

        ArrayList<String> wordArrayList = null;
        if(cursor.getCount() > 0){
            wordArrayList = new ArrayList<>();
            while(!cursor.isAfterLast()){
                String word = cursor.getString(cursor.getColumnIndex("word"));

                wordArrayList.add(word);
                cursor.moveToNext();
            }
        }
        cursor.close();
        db.close();
        helper.close();
        return wordArrayList;
    }

}
