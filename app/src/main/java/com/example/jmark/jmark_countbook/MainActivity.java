package com.example.jmark.jmark_countbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**Called when user taps the create floating button in lower right*/
    public void createCounter(View view){
        Intent intent = new Intent(this, createCounter.class);
        startActivity(intent);
    }
}
