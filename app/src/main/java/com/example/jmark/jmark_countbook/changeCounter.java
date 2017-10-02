
/*
 *  Change Counter class
 *
 *  Version 1.0
 *
 *  September 30, 2017
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;

public class changeCounter extends AppCompatActivity {

    private static final String FILENAME = "file.sav";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_counter);

        /*get all the values from xml view as well as from arraylist from main*/
        final TextView current = (TextView) findViewById(R.id.currentV);
        TextView initial = (TextView) findViewById(R.id.initial);
        TextView comments = (TextView) findViewById(R.id.comment);
        TextView counterName = (TextView) findViewById(R.id.name);
        Button delete = (Button) findViewById(R.id.delete);
        Button increment = (Button) findViewById(R.id.increment);
        Button decrement = (Button) findViewById(R.id.decrement);
        Button reset = (Button) findViewById(R.id.reset);

        final Integer pos = getIntent().getExtras().getInt("pos");
        final String name = MainActivity.counters.get(pos).getName();
        final String currentV = MainActivity.counters.get(pos).getCurrent();
        final String initialV = MainActivity.counters.get(pos).getInitial();
        final String comment = MainActivity.counters.get(pos).getComment();
        counterName.setText(name);
        current.setText(currentV);
        initial.setText("Initial Value: "+ initialV);
        comments.setText("Comments: "+ comment);

        /*on button press get integer from arraylist add 1 and set the new value to arraylist*/
        increment.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);

                Integer incrementVal = Integer.parseInt(MainActivity.counters.get(pos).getCurrent());
                incrementVal=incrementVal + 1;
                String incrementCurr = String.valueOf(incrementVal);
                MainActivity.counters.set(pos, new NormalCounter(name,initialV,incrementCurr,comment));
                current.setText(incrementCurr);

                saveInFile();
            }

        });

        /*on button press get integer from arraylist subtract 1 and set the new value to arraylist*/
        decrement.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);

                Integer decrementVal = Integer.parseInt(MainActivity.counters.get(pos).getCurrent());
                decrementVal = decrementVal - 1;
                String decrementCurr = String.valueOf(decrementVal);
                MainActivity.counters.set(pos, new NormalCounter(name,initialV,decrementCurr,comment));
                current.setText(decrementCurr);

                saveInFile();
            }

        });

        /* resets current value to initial value by setting current in arraylist to same as initial in arraylist*/
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK);

                String currentV=initialV;
                MainActivity.counters.set(pos, new NormalCounter(name,initialV,currentV,comment));
                current.setText(currentV);

                saveInFile();
            }
        });

        /*removes data from arraylist at position passed from main activity*/
        delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                setResult(RESULT_OK);

                int keyLocation = pos;
                MainActivity.counters.remove(keyLocation);
                MainActivity.adapter.notifyDataSetChanged();
                saveInFile();

                finish();
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
