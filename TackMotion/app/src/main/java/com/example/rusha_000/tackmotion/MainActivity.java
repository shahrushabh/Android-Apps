package com.example.rusha_000.tackmotion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView v;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        v = (TextView) findViewById(R.id.track_view);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e){
        String position = "";
        position = "X: " + Float.toString(e.getX()) + "\n" + "Y: " + Float.toString(e.getY());
        v.setText(position);
        return true;
    }
}
