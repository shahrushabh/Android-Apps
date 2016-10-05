package com.example.rusha_000.intentproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            TextView tv = (TextView) findViewById(R.id.textView);
            tv.setText(extras.getString("message"));
        }
    }
}
