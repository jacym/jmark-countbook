package com.example.jmark.jmark_countbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.HashMap;

public class changeCounter extends AppCompatActivity {

    private TextView counterName;
    private TextView current;
    private TextView initial;
    private TextView comments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_counter);

        TextView current = (TextView) findViewById(R.id.currentV);
        TextView initial = (TextView) findViewById(R.id.initial);
        TextView comments = (TextView) findViewById(R.id.comment);
        TextView counterName = (TextView) findViewById(R.id.name);
        Button delete = (Button) findViewById(R.id.delete);
        Button increment = (Button) findViewById(R.id.increment);
        Button decrement = (Button) findViewById(R.id.decrement);
        Button edit = (Button) findViewById(R.id.edit);

        String name = getIntent().getExtras().getString("Name");
        String currentV = getIntent().getExtras().getString("Current");
        String initialV = getIntent().getExtras().getString("Initial");
        String comment = getIntent().getExtras().getString("Comment");
        counterName.setText(name);
        current.setText(currentV);
        initial.setText(initialV);
        comments.setText(comment);

    }

}
