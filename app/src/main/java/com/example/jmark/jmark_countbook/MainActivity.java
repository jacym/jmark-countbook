
/*
 *  Main Activity for countbook
 *
 *  Version 1.0
 *
 *  September 25, 2017
 *
 *  Copyright © 2017 Jacy Mark, CMPUT301, University of Alberta - All Rights Reserved.
 *  You may use, distribute, or modify this code under terms and conditions of the Code of Student Behavior at University of Alberta.
 *  You can find a copy of the license in this project. Otherwise please contact contact@abc.ca
 */

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
import android.widget.TextView;

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
    public static ArrayAdapter<counter> adapter;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        countersList = (ListView) findViewById(R.id.countersList);

        /*initiate new activity for editing on tap and hold of the counter*/
        countersList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Integer pos = position;
                Intent intent = new Intent(MainActivity.this, editCounter.class);
                intent.putExtra("pos",pos);
                startActivity(intent);
                return false;
            }
        });

        /*initiate new activity for changing current and viewing details on tap*/
        countersList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Integer pos = position;
                Intent intent = new Intent(MainActivity.this, changeCounter.class);
                intent.putExtra("pos",pos);
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
        TextView counterNumberText = (TextView) findViewById(R.id.counterNumber);

        Integer numberOfCounters = countersList.getAdapter().getCount();

        counterNumberText.setText("Counters: " + numberOfCounters.toString());
    }

    /*loads from file (for saving*/
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
