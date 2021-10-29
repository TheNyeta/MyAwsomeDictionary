package com.example.myawesomedictionary;

//Name: Timothy Natan
//NIM: 2301870804

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ExploreAdapter extends RecyclerView.Adapter<ExploreAdapter.ViewHolder>{

    Context ctx;
    ArrayList<WordD> wordDArrayList;
    ArrayList<String> wordArrayList;

    public ExploreAdapter(Context ctx) {
        this.ctx = ctx;
    }

    public void setWordDArrayList(ArrayList<WordD> wordDArrayList) {
        this.wordDArrayList = wordDArrayList;
    }

    public void setWordArrayList(ArrayList<String> wordArrayList) {
        this.wordArrayList = wordArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.explore_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExploreAdapter.ViewHolder holder, int position) {
        holder.tv_word.setText(wordArrayList.get(position));
        holder.explore_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ctx, DetailActivity.class);
                intent.putExtra("word", wordArrayList.get(position));
                intent.putExtra("wordD", wordDArrayList);
                ctx.startActivity(intent);
            }
        });

        holder.save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String word = wordArrayList.get(position);

                DatabaseHelper databaseHelper = new DatabaseHelper(ctx);
                SQLiteDatabase db = databaseHelper.getReadableDatabase();
                Cursor cursor = db.rawQuery("SELECT * FROM Word WHERE word = '"+word+"'", null);
                cursor.moveToFirst();

                if(cursor.getCount() == 0){
                    WordFavoriteHelper wordFavoriteHelper = new WordFavoriteHelper(ctx);
                    wordFavoriteHelper.insertWordFavorite(wordArrayList.get(position));

                    FavoriteHelper helper = new FavoriteHelper(ctx);

                    for(int i=0;i<wordDArrayList.size();i++){
                        if(wordDArrayList.get(i).getWord().equals(wordArrayList.get(position))){
                            helper.insertFavorite(wordDArrayList.get(i).getWord(), wordDArrayList.get(i).getImageurl(), wordDArrayList.get(i).getType(), wordDArrayList.get(i).getDefinition());
                        }
                    }

                    Toast.makeText(ctx, "Word Saved", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(ctx, "Word Already Saved", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return wordArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tv_word;
        Button save_btn;
        CardView explore_item;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_word = itemView.findViewById(R.id.tv_word);
            save_btn = itemView.findViewById(R.id.save_btn);
            explore_item = itemView.findViewById(R.id.explore_item);

            save_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

        }
    }
}
