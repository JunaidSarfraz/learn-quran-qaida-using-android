package com.project.learnquran;

import android.app.Fragment;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Toast;

public class Contents extends Fragment {
	
	//Advance requirement is this file is populated using file
	private int [] xmls = {R.layout.contents,
			R.layout.l2,R.layout.l2p2,R.layout.l2p3,
			R.layout.l3,R.layout.l3p2,R.layout.l3p3,
			R.layout.l4,R.layout.l4p2,R.layout.l4p3,
			R.layout.l5,R.layout.l6,R.layout.l7,
			R.layout.l8,R.layout.l9,R.layout.l10 };
	
	int curr = 0;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		curr = 0;
		View v =  inflater.inflate(xmls[curr], container, false);
		
		final GestureDetector gesture = new GestureDetector(getActivity(),
	            new GestureDetector.SimpleOnGestureListener() {

	                @Override
	                public boolean onDown(MotionEvent e) {
	                    return true;
	                }

	                @Override
	                public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,float velocityY) {
	                	
	                    final int SWIPE_MIN_DISTANCE = 120;
	                    final int SWIPE_MAX_OFF_PATH = 250;
	                    final int SWIPE_THRESHOLD_VELOCITY = 200;
	                    try {
	                        if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
	                            return false;
	                        if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {	          
	                        	prev();
	                        	//Toast.makeText(getActivity(), "Right to Left", Toast.LENGTH_SHORT).show();
	                        } 
	                        else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
	                        	next();
	                        	//Toast.makeText(getActivity(), "Left to Right", Toast.LENGTH_SHORT).show();
	                        }
	                    } catch (Exception e) {
	                        // nothing
	                    }
	                    return super.onFling(e1, e2, velocityX, velocityY);
	                }
	            });

		        v.setOnTouchListener(new View.OnTouchListener() {
		            @Override
		            public boolean onTouch(View v, MotionEvent event) {
		            	//Toast.makeText(getActivity(), "hello", Toast.LENGTH_SHORT).show();
		            	return gesture.onTouchEvent(event);
		            }
		        });
		        
		return v;
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		// This Function call tells the lesson name Fragment to highlight the lesson1 button
		// curr = 0 here.
		((Lessons)getActivity()).lessonNameHighlightNotification(curr,-10);
	}
	public void next(){
		curr = curr + 1;
		if(curr >= xmls.length){
			curr = curr - 1;
			return;
		}
		((Lessons)getActivity()).lessonNameHighlightNotification(curr,(curr-1));
    	((ViewGroup)getView()).removeAllViews();
    	LayoutInflater.from(getActivity()).inflate(xmls[curr], (ViewGroup) getView());
	}
	public void prev(){
		curr = curr - 1;
		if(curr < 0){
			curr = 0;
			return;
		}
		((Lessons)getActivity()).lessonNameHighlightNotification(curr,(curr+1));
    	((ViewGroup)getView()).removeAllViews();
    	LayoutInflater.from(getActivity()).inflate(xmls[curr], (ViewGroup) getView());
	}
	public void loadScreen(int index){
		int prev = curr;
		curr = index;
		((Lessons)getActivity()).lessonNameHighlightNotification(curr,prev);
		((ViewGroup)getView()).removeAllViews();
		LayoutInflater.from(getActivity()).inflate(xmls[index], (ViewGroup) getView());
	}
}