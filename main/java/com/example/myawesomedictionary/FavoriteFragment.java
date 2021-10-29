package com.example.myawesomedictionary;

//Name: Timothy Natan
//NIM: 2301870804

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class FavoriteFragment extends Fragment {

    View view;
    RecyclerView rv_favorite;
    FavoriteAdapter adapter;
    ArrayList<WordD> wordDArrayList;
    ArrayList<String> wordArrayList;

    public FavoriteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_favorite, container, false);

        rv_favorite = view.findViewById(R.id.rv_favorite);

        DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Word", null);
        cursor.moveToFirst();

        if(cursor.getCount() != 0){
            WordFavoriteHelper wordFavoriteHelper = new WordFavoriteHelper(getContext());
            wordArrayList = wordFavoriteHelper.getAllData();

            FavoriteHelper helper = new FavoriteHelper(getContext());
            wordDArrayList = helper.getAllData();

            adapter = new FavoriteAdapter(getContext());
            adapter.setWordArrayList(wordArrayList);
            adapter.setWordDArrayList(wordDArrayList);

            rv_favorite.setAdapter(adapter);
            rv_favorite.setLayoutManager(new LinearLayoutManager(getContext()));
        }

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Word", null);
        cursor.moveToFirst();

        if(cursor.getCount() != 0){
            WordFavoriteHelper wordFavoriteHelper = new WordFavoriteHelper(getContext());
            wordArrayList = wordFavoriteHelper.getAllData();

            FavoriteHelper helper = new FavoriteHelper(getContext());
            wordDArrayList = helper.getAllData();

            adapter = new FavoriteAdapter(getContext());
            adapter.setWordArrayList(wordArrayList);
            adapter.setWordDArrayList(wordDArrayList);

            rv_favorite.setAdapter(adapter);
            rv_favorite.setLayoutManager(new LinearLayoutManager(getContext()));
        }

    }
}