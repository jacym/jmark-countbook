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
