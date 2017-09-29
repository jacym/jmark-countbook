package com.example.jmark.jmark_countbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class createCounter extends AppCompatActivity {

    private static final String FILENAME = "file.sav";
    public ArrayList<counter> counters= new ArrayList<counter>();
    private ArrayAdapter<counter> adaptar;

    private EditText counterName;
    private EditText current;
    private EditText initial;
    private EditText comments;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_counter);

        current = (EditText) findViewById(R.id.currentVal);
        initial = (EditText) findViewById(R.id.initialVal);
        comments = (EditText) findViewById(R.id.comment);
        counterName = (EditText) findViewById(R.id.name);
        Button submitButton = (Button) findViewById(R.id.submit);

        submitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                setResult(RESULT_OK);
                String name = counterName.getText().toString();
                String init = initial.getText().toString();
                String curr = current.getText().toString();
                String commentlines = comments.getText().toString();



                finish();
            }

        });
    }
}
