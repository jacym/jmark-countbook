
/*
 *  Create counter class
 *
 *  Version 1.0
 *
 *  September 27, 2017
 *
 *  Copyright © 2017 Jacy Mark, CMPUT301, University of Alberta - All Rights Reserved.
 *  You may use, distribute, or modify this code under terms and conditions of the Code of Student Behavior at University of Alberta.
 *  You can find a copy of the license in this project. Otherwise please contact contact@abc.ca
 */

package com.example.jmark.jmark_countbook;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;


public class createCounter extends AppCompatActivity {

    private static final String FILENAME = "file.sav";

    private EditText counterName;
    private EditText current;
    private EditText initial;
    private EditText comments;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_counter);

        initial = (EditText) findViewById(R.id.initialVal);
        current = initial;
        comments = (EditText) findViewById(R.id.comment);
        counterName = (EditText) findViewById(R.id.name);
        Button submitButton = (Button) findViewById(R.id.submit);

        submitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);
                String name = counterName.getText().toString();
                String init = initial.getText().toString();
                String curr = current.getText().toString();
                String commentlines = comments.getText().toString();

                if (name.equals("")||init.equals("")){
                    Context context = getApplicationContext();
                    CharSequence text = "Name and initial value required!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast.makeText(context,text,duration).show();
                }
                else{
                    MainActivity.counters.add(new NormalCounter(name,init,curr,commentlines));
                    saveInFile();


                    finish();
                }
            }

        });

    }
    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    Context.MODE_PRIVATE);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(MainActivity.counters,writer);
            writer.flush();

            fos.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }
}
