package com.example.myawesomedictionary;

//Name: Timothy Natan
//NIM: 2301870804

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder>{

    Context ctx;
    ArrayList<WordD> wordDArrayList;
    ArrayList<String> wordArrayList;

    public FavoriteAdapter(Context ctx) {
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
        View view = LayoutInflater.from(ctx).inflate(R.layout.favorite_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteAdapter.ViewHolder holder, int position) {
        holder.favorite_word.setText(wordArrayList.get(position));
        holder.favorite_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ctx, DetailActivity.class);
                intent.putExtra("word", wordArrayList.get(position));
                intent.putExtra("wordD", wordDArrayList);
                ctx.startActivity(intent);
            }
        });

        holder.delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                WordFavoriteHelper wordFavoriteHelper = new WordFavoriteHelper(ctx);
                wordFavoriteHelper.deleteWordFavorite(wordArrayList.get(position));

                FavoriteHelper helper = new FavoriteHelper(ctx);
                helper.deleteFavorite(wordArrayList.get(position));

                wordDArrayList.remove(position);
                wordArrayList.remove(position);

                notifyItemRemoved(position);
                notifyDataSetChanged();

            }
        });

    }

    @Override
    public int getItemCount() {
        return wordArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView favorite_word;
        Button delete_btn;
        CardView favorite_item;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            favorite_word = itemView.findViewById(R.id.favorite_word);
            delete_btn = itemView.findViewById(R.id.delete_btn);
            favorite_item = itemView.findViewById(R.id.favorite_item);

        }
    }
}
