package edu.iastate.gestures;

import android.app.Activity;
import android.content.Intent;
import android.provider.Settings;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This is a base class for handling swipes in the application.
 */
public class CustomGestureListener extends Activity implements OnGestureListener{
	/*
	 * These variables store activity specific values.
	 */
	private GestureDetector gesture = null;
	private Class<?> leftActivity = null;
	private Class<?> rightActivity = null;
	private float initial_x;
	private float initial_y;
	private float final_x;
	private float final_y;

    @Override
   public boolean onTouchEvent(MotionEvent me)
   {
    	if (gesture != null)
    		return gesture.onTouchEvent(me);
    	else
    		return false;
   }

	@Override
	public boolean onDown(MotionEvent e) {
//		initial_x = e.getX();
//		initial_y = e.getX();
//		return true;
		return false;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
		String swipe = "";
		initial_x = e1.getX();
		initial_y = e1.getX();
		final_x = e2.getX();
		final_y = e2.getX();

		if((final_x - initial_x) > 0){
			swipe = "Swiped Left!";
			showtoast(swipe);
			Intent activity = new Intent(this, leftActivity);
			startActivity(activity);
		}else{
			swipe = "Swiped Right!";
			showtoast(swipe);
			Intent activity = new Intent(this, rightActivity);
			startActivity(activity);
		}
//		System.out.println("E1 X: " + Float.toString(e1.getX()));
//		System.out.println("E1 Y: " + Float.toString(e1.getY()));
//		System.out.println("E2 X: " + Float.toString(e2.getX()));
//		System.out.println("E2 Y: " + Float.toString(e2.getY()));
//		System.out.println("X velocity: " + Float.toString(velocityX));
//		System.out.println("Y velocity: " + Float.toString(velocityY));
		return true;
	}

	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * Sets the gesture detector for the activity
	 * @param gesture the gesture detector specific to the activity
	 */
	public void setGestureDetector(GestureDetector gesture){
		this.gesture = gesture;
	}
	
	/**
	 * Sets the left and right activity classes which are swiped to
	 * @param leftActivity	The class for the left Activity
	 * @param rightActivity The class for the right Activity
	 */
	public void setLeftRight(Class<?> leftActivity, Class<?> rightActivity){
		this.leftActivity = leftActivity;
		this.rightActivity = rightActivity;
	}

	/**
	 * Private method to generate and show toast.
	 * @param layout String to put in the toast.
     */
	private void showtoast(String layout){
		Toast toast = Toast.makeText(getApplicationContext(),layout, Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.RELATIVE_LAYOUT_DIRECTION, 0, 0);
		toast.show();
	}

}
