package com.example.jmark.jmark_countbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView countersList;
    private static final String FILENAME = "file.sav";
    public static ArrayList<counter> counters = new ArrayList<counter>();
    public ArrayAdapter<counter> adapter;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        countersList = (ListView) findViewById(R.id.countersList);

        countersList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                String counterName = counters.get(position).getName();
                String counterCurrent = counters.get(position).getCurrent();
                String counterInitial = counters.get(position).getInitial();
                String counterComment = counters.get(position).getComment();
                Intent intent = new Intent(MainActivity.this, changeCounter.class);
                intent.putExtra("Name", counterName);
                intent.putExtra("Current", counterCurrent);
                intent.putExtra("Initial", counterInitial);
                intent.putExtra("Comment", counterComment);
                startActivity(intent);

            }
        });
    }

    /**Called when user taps the create floating button in lower right*/
    public void createCounter(View view){
        Intent intent = new Intent(this, createCounter.class);
        startActivity(intent);
    }


    @Override
    protected void onStart(){
        super.onStart();
        loadFromFile();
        adapter = new ArrayAdapter<counter>(this, R.layout.list_item, counters);
        countersList.setAdapter(adapter);
    }

    private void loadFromFile(){
        try{
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<NormalCounter>>() {}.getType();
            counters = gson.fromJson(in, listType);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
