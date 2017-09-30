package com.example.jmark.jmark_countbook;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;


public class createCounter extends AppCompatActivity {

    private static final String FILENAME = "file.sav";
    private ArrayList<counter> counters = new ArrayList<counter>();
    private ArrayAdapter<counter> adapter;

    private EditText counterName;
    private EditText current;
    private EditText initial;
    private EditText comments;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_counter);

        current = (EditText) findViewById(R.id.currentVal);
        initial = (EditText) findViewById(R.id.initialVal);
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

                counters.add(new NormalCounter(name,init,curr,commentlines));
                adapter.notifyDataSetChanged();
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
            gson.toJson(counters,writer);
            writer.flush();

            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }
}
