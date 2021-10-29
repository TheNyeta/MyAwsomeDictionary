package com.example.myawesomedictionary;

//Name: Timothy Natan
//NIM: 2301870804

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.ViewHolder>{

    Context ctx;
    ArrayList<WordD> wordDArrayList;

    public DetailAdapter(Context ctx) {
        this.ctx = ctx;
    }

    public void setWordDArrayList(ArrayList<WordD> wordDArrayList) {
        this.wordDArrayList = wordDArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.detail_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailAdapter.ViewHolder holder, int position) {
        holder.definition_type.setText(wordDArrayList.get(position).getType());
        holder.definition_definition.setText(wordDArrayList.get(position).getDefinition());
        if(!wordDArrayList.get(position).getImageurl().equals("null")){
            Picasso.get().load(wordDArrayList.get(position).getImageurl()).into(holder.definition_image);
        }

    }

    @Override
    public int getItemCount() {
        return wordDArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView definition_type, definition_definition;
        ImageView definition_image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            definition_type = itemView.findViewById(R.id.definition_type);
            definition_definition = itemView.findViewById(R.id.definition_definition);
            definition_image = itemView.findViewById(R.id.definition_image);

        }
    }
}
