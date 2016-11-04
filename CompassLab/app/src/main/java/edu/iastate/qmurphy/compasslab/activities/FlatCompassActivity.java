package edu.iastate.qmurphy.compasslab.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import edu.iastate.qmurphy.compasslab.interfaces.SensorUpdateCallback;
import edu.iastate.qmurphy.compasslab.R;
import edu.iastate.qmurphy.compasslab.models.FlatCompass;

public class FlatCompassActivity extends AppCompatActivity implements SensorUpdateCallback, View.OnClickListener {
    private FlatCompass mCompass;
    private ImageView mArrow;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all);
        mCompass = new FlatCompass(getApplicationContext(),this);

        mArrow = (ImageView) findViewById(R.id.image);
        mArrow.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.compass));

        Button btn1 = (Button) findViewById(R.id.btn1);
        btn1.setText("Tilt");
        btn1.setOnClickListener(this);

        Button btn2 = (Button) findViewById(R.id.btn2);
        btn2.setText("Better Compass");
        btn2.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mCompass.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mCompass.stop();
    }

    @Override
    public void update(float orientation) {
        mArrow.setRotation(orientation);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn1) {
            startActivity(new Intent(this, TiltActivity.class));
        }
        else if (v.getId() == R.id.btn2) {
            startActivity(new Intent(this, BetterCompassActivity.class));
        }
    }
}
