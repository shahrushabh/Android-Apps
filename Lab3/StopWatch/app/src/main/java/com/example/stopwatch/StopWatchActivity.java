package com.example.stopwatch;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 *
 * Sample Stopwatch Android activity
 *
 */
public class StopWatchActivity extends Activity {

    /**
     * REFRESH_RATE defines how often we should update the timer to show how much time has elapsed.
     * refresh every 100 milliseconds
     */
    private final int REFRESH_RATE = 100;

    /**
     * A variable to keep track of the seconds
     */
    private Long startms;
    private Handler mHandler = new Handler();
    private TextView timerMsView;
    private TextView timerView;
    private ArrayList<Long> intervals = new ArrayList<>();

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stopwatch);
        timerMsView = (TextView) findViewById(R.id.timerMsView);
        timerView = (TextView) findViewById(R.id.timerView);
        startms = null;
        hideStopButton();
    }


    //
    /**
     * This method will start the current stopwatch clock
     *
     * @param view the current view
     */

    public void startClick(View view){
        if(startms == null){
            startms = System.currentTimeMillis();
        }
        mHandler.postDelayed(startTimer,REFRESH_RATE);
        showStopButton();
    }
    /**
     * This method will reset the current stopwatch clock
     *
     * @param view the current view
     */
    public void resetClick(View view){
        String tm = "00:00:00";
        timerView.setText(tm);
        tm = ".0";
        timerMsView.setText(tm);
        startms = null;
        intervals = new ArrayList<>();
    }

    /**
     * This method will stop the current stopwatch.
     *
     * @param view the current view
     */
    public void stopClick(View view){
        intervals.add(System.currentTimeMillis() - startms);
        startms = null;
        hideStopButton();
    }

    /**
     * This method will show the stop button when called by hiding the 
     * start and reset button and making the stop button visible.
     */
    private void showStopButton(){
        findViewById(R.id.btn_start).setVisibility(View.GONE);
        findViewById(R.id.btn_reset).setVisibility(View.GONE);
        findViewById(R.id.btn_stop).setVisibility(View.VISIBLE);
    }

    /**
     * This method will show the start and reset buttons by hiding the 
     * stop button and making the start and reset buttons visible.
     */
    private void hideStopButton(){
        findViewById(R.id.btn_start).setVisibility(View.VISIBLE);
        findViewById(R.id.btn_reset).setVisibility(View.VISIBLE);
        findViewById(R.id.btn_stop).setVisibility(View.GONE);
        mHandler.removeCallbacks(startTimer);
    }

    /**
     * Converts the elapsed given time and updates the display
     *
     * @param time the time to update the current display to
     */
    private void updateTimer (long time){
        //Convert the milliseconds,seconds,minutes,hours to String and format to ensure it has a leading zero when required
        long milis = time;
        long secs = (time / 1000) % 60;
        long mins = (time / (1000*60)) % 60;
        long hrs = (time / (1000*60*60)) % 24;

        // String formation
        String mili, sec, min, hr;
        if(milis < 10){
            mili = Long.toString(milis);
            mili = mili.substring(mili.length()-1,mili.length());
        }else if(milis < 100){
            mili = Long.toString(milis);
            mili = mili.substring(mili.length()-2,mili.length());
        }else if(milis < 1000){
            mili = Long.toString(milis);
            mili = mili.substring(mili.length()-1,mili.length());
        }else{
            mili = Long.toString(milis);
            mili = mili.substring(mili.length()-1,mili.length());
        }
        if(secs < 10){
            sec = "0" + Long.toString(secs);
        }else{
            sec = Long.toString(secs);
        }
        if(mins < 10){
            min = "0" + Long.toString(mins);
        }else{
            min = Long.toString(mins);
        }
        if(hrs < 10){
            hr = "0" + Long.toString(hrs);
        }else{
            hr = Long.toString(hrs);
        }

        //Setting the timer text to the elapsed time
        mili = "." + mili;
        String timeToSet = hr+":"+min+":"+sec;
        String msTimeToSet = mili;
        timerView.setText(timeToSet);
        timerMsView.setText(msTimeToSet);
        mHandler.postDelayed(startTimer,REFRESH_RATE);
    } // End function updateTimer.

    /**
     * Create a Runnable startTimer that makes timer runnable.
     */
    private Runnable startTimer = new Runnable() {
        public void run() {
            long time = 0;
            // Update time here.
            if(intervals.size() > 0){
                for (long interval: intervals) {
                    time += interval;
                }
                time = System.currentTimeMillis() + time - startms;
            }else{
                time = System.currentTimeMillis() - startms;
            }
            updateTimer(time);
        }
    };

}
    