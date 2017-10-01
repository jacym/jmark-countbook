package com.example.jmark.jmark_countbook;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import static com.example.jmark.jmark_countbook.R.id.name;

public class editCounter extends AppCompatActivity {

    private static final String FILENAME="file.sav";

    private EditText initialNew;
    private EditText currentNew;
    private EditText nameNew;
    private EditText commentNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_counter);
        final EditText current = (EditText) findViewById(R.id.currentVal);
        EditText initial = (EditText) findViewById(R.id.initialVal);
        EditText comments = (EditText) findViewById(R.id.comment);
        EditText counterName = (EditText) findViewById(name);
        Button change= (Button) findViewById(R.id.submit);

        final Integer pos = getIntent().getExtras().getInt("pos");
        final String name = MainActivity.counters.get(pos).getName();
        String currentV = MainActivity.counters.get(pos).getCurrent();
        final String initialV = MainActivity.counters.get(pos).getInitial();
        final String comment = MainActivity.counters.get(pos).getComment();
        counterName.setText(name);
        current.setText(currentV);
        initial.setText(initialV);
        comments.setText(comment);

        initialNew = (EditText) findViewById(R.id.initialVal);
        currentNew = (EditText) findViewById(R.id.currentVal);
        commentNew = (EditText) findViewById(R.id.comment);
        nameNew = (EditText) findViewById(R.id.name);

        change.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);
                String name = nameNew.getText().toString();
                String init = initialNew.getText().toString();
                String curr = currentNew.getText().toString();
                String commentlines = commentNew.getText().toString();

                if (name.equals("") || init.equals("") || curr.equals("")){
                    Context context = getApplicationContext();
                    CharSequence text = "Name, Initial Value, or Curret Value cannot be empty";
                    int duration = Toast.LENGTH_SHORT;

                    Toast.makeText(context,text,duration).show();
                }
                else{
                    MainActivity.counters.set(pos, new NormalCounter(name,init,curr,commentlines));
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
