package com.example.myawesomedictionary;

//Name: Timothy Natan
//NIM: 2301870804

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    TextView detail_word;
    Button detail_save;
    RecyclerView rv_detail;
    DetailAdapter adapter;
    ArrayList<WordD> wordDArrayList, new_wordD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        detail_word = findViewById(R.id.detail_word);
        detail_save = findViewById(R.id.detail_save);
        rv_detail = findViewById(R.id.rv_detail);

        wordDArrayList = new ArrayList<>();
        new_wordD = new ArrayList<>();

        Intent intent = getIntent();
        String word = intent.getStringExtra("word");
        wordDArrayList = (ArrayList<WordD>) intent.getSerializableExtra("wordD");

        for(int i=0;i<wordDArrayList.size();i++){
            if(wordDArrayList.get(i).getWord().equals(word)){
                new_wordD.add(wordDArrayList.get(i));
            }
        }

        detail_word.setText(word);

        adapter = new DetailAdapter(this);
        adapter.setWordDArrayList(new_wordD);

        rv_detail.setAdapter(adapter);
        rv_detail.setLayoutManager(new LinearLayoutManager(this));

        detail_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseHelper databaseHelper = new DatabaseHelper(DetailActivity.this);
                SQLiteDatabase db = databaseHelper.getReadableDatabase();
                Cursor cursor = db.rawQuery("SELECT * FROM Word WHERE word = '"+word+"'", null);
                cursor.moveToFirst();

                if(cursor.getCount() == 0){
                    WordFavoriteHelper wordFavoriteHelper = new WordFavoriteHelper(DetailActivity.this);
                    wordFavoriteHelper.insertWordFavorite(word);

                    FavoriteHelper helper = new FavoriteHelper(DetailActivity.this);

                    for(int i=0;i<new_wordD.size();i++){
                        helper.insertFavorite(word, new_wordD.get(i).getImageurl(), new_wordD.get(i).getType(), new_wordD.get(i).getDefinition());
                    }

                    Toast.makeText(DetailActivity.this, "Word Saved", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(DetailActivity.this, "Word Already Saved", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}