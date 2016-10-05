package com.example.rusha_000.intentproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void send_message(View view){
        Intent second = new Intent(this,SecondActivity.class);
        second.putExtra("message",((TextView) findViewById(R.id.textInput)).getText().toString());
        startActivity(second);
    }

}
