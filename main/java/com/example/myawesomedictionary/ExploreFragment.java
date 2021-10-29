package com.example.myawesomedictionary;

//Name: Timothy Natan
//NIM: 2301870804

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ExploreFragment extends Fragment {

    View view;
    EditText search_bar;
    ImageButton search_btn;
    RecyclerView rv_explore;
    ExploreAdapter adapter;
    ArrayList<WordD> wordDArrayList;
    ArrayList<String> wordArrayList;

    public ExploreFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_explore, container, false);

        rv_explore = view.findViewById(R.id.rv_explore);
        search_bar = view.findViewById(R.id.search_bar);
        search_btn = view.findViewById(R.id.search_btn);

        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String search = search_bar.getText().toString();
                search(search);
            }
        });

        return view;
    }

    void search(String search){

        wordDArrayList = new ArrayList<>();
        wordArrayList = new ArrayList<>();

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        String url = "https://myawesomedictionary.herokuapp.com/words?q=" + search;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url,
                response -> {
                    for(int i=0;i<response.length();i++){
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            String name = jsonObject.getString("word");
                            wordArrayList.add(name);
                            JSONArray jsonArray = jsonObject.getJSONArray("definitions");

                            for (int j=0;j<jsonArray.length();j++){
                                JSONObject definition = jsonArray.getJSONObject(j);
                                WordD wordD = new WordD(name, definition.getString("image_url"), definition.getString("type"), definition.getString("definition"));
                                wordDArrayList.add(wordD);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    adapter.notifyDataSetChanged();
                }, error -> {
            Log.e("errortag", error.toString());
        });

        requestQueue.add(jsonArrayRequest);
        adapter = new ExploreAdapter(getContext());
        adapter.setWordArrayList(wordArrayList);
        adapter.setWordDArrayList(wordDArrayList);

        rv_explore.setAdapter(adapter);
        rv_explore.setLayoutManager(new LinearLayoutManager(getContext()));
    }


}