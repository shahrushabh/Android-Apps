package com.example.rusha_000.homework2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.Console;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img = (ImageView) findViewById(R.id.image_view);
        Button button = (Button) findViewById(R.id.button_a);
        button.setOnClickListener(this);
        button = (Button) findViewById(R.id.button_b);
        button.setOnClickListener(this);
        button = (Button) findViewById(R.id.button_c);
        button.setOnClickListener(this);
        button = (Button) findViewById(R.id.button_d);
        button.setOnClickListener(this);
        button = (Button) findViewById(R.id.button_e);
        button.setOnClickListener(this);
        button = (Button) findViewById(R.id.button_f);
        button.setOnClickListener(this);
        button = (Button) findViewById(R.id.button_g);
        button.setOnClickListener(this);
        button = (Button) findViewById(R.id.button_h);
        button.setOnClickListener(this);
        button = (Button) findViewById(R.id.button_i);
        button.setOnClickListener(this);
        button = (Button) findViewById(R.id.button_j);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        update_img(v);
    }

    private void update_img(View v){
        int button = v.getId();
        switch (button){
            case R.id.button_a:
                img.setImageResource(R.drawable.a);
                break;
            case R.id.button_b:
                img.setImageResource(R.drawable.b);
                break;
            case R.id.button_c:
                img.setImageResource(R.drawable.c);
                break;
            case R.id.button_d:
                img.setImageResource(R.drawable.d);
                break;
            case R.id.button_e:
                img.setImageResource(R.drawable.e);
                break;
            case R.id.button_f:
                img.setImageResource(R.drawable.f);
                break;
            case R.id.button_g:
                img.setImageResource(R.drawable.g);
                break;
            case R.id.button_h:
                img.setImageResource(R.drawable.h);
                break;
            case R.id.button_i:
                img.setImageResource(R.drawable.i);
                break;
            case R.id.button_j:
                img.setImageResource(R.drawable.j);
                break;
        }
    }
//
//    public void show_a(View v){
//        img.setImageResource(R.drawable.a);
//    }
//
//    public void show_b(View v){
//        img.setImageResource(R.drawable.b);
//    }
//
//    public void show_c(View v){
//        img.setImageResource(R.drawable.c);
//    }
//
//    public void show_d(View v){
//        img.setImageResource(R.drawable.d);
//    }
//
//    public void show_e(View v){
//        img.setImageResource(R.drawable.e);
//    }
//
//    public void show_f(View v){
//        img.setImageResource(R.drawable.f);
//    }
//
//    public void show_g(View v){
//        img.setImageResource(R.drawable.g);
//    }
//
//    public void show_h(View v){
//        img.setImageResource(R.drawable.h);
//    }
//
//    public void show_i(View v){
//        img.setImageResource(R.drawable.i);
//    }
//
//    public void show_j(View v){
//        img.setImageResource(R.drawable.j);
//    }
}
