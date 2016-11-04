package edu.iastate.qmurphy.compasslab.models;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import edu.iastate.qmurphy.compasslab.interfaces.SensorUpdateCallback;

/**
 * Created by Quinn on 10/23/2016.
 */

public class TiltCalculator implements SensorEventListener {
    private SensorManager mSensorManager;
    private Sensor mAcc;
    private SensorUpdateCallback mCallback;

    public TiltCalculator(Context context, SensorUpdateCallback callback) {
        mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        mAcc = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mCallback = callback;
    }

    public void start() {
        mSensorManager.registerListener(this,mAcc,SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void stop() {
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float orientation = 0.0f;
        double pitch = Math.atan(event.values[1]/Math.sqrt(Math.pow(event.values[0],2) + Math.pow(event.values[2],2)));
        double roll = Math.atan((-1*event.values[0])/event.values[2]);
        orientation = (float) ((Math.atan2(pitch,roll) * 180 / Math.PI) + 90.0f);
        mCallback.update(orientation);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
