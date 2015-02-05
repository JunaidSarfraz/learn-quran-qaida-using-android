package com.project.learnquran;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.Button;

public class Lessonnames extends Fragment implements OnClickListener {
	
	int [] btns = {R.id.btn_lesson1,R.id.btn_lesson2,R.id.btn_lesson3,R.id.btn_lesson4,R.id.btn_lesson5,
			R.id.btn_lesson6,R.id.btn_lesson7,R.id.btn_lesson8,R.id.btn_lesson9, R.id.btn_lesson10,
			R.id.btn_lesson11,R.id.btn_lesson12,R.id.btn_lesson13,R.id.btn_lesson14,R.id.btn_lesson15,R.id.btn_lesson16};
	
	Button [] buttons = new Button[btns.length];
	int default_btn_color = R.color.green;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.lessonname,container,false);
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		for(int i = 0;i < btns.length;i++){
			buttons[i] = (Button) getView().findViewById(btns[i]);
			buttons[i].setOnClickListener(this);
		}
	}
	@SuppressLint("ResourceAsColor")
	public void highLightButton(int index,int prev){
		//Wite here the code to highlight the selected fragment button. and index of selected Fragment button is sent as parameter
		if(prev == -10) //This is exceptional case when first button is to be highlighted
			prev = 0;
		if(index < 0 || index >= btns.length)
			return;
		if((prev < 0 || prev >= btns.length) && (prev != -10))
			return;
		//Here to restore the default color of the previously highlighted button
		Button btn = (Button) getView().findViewById(btns[prev]);
		btn.setBackgroundColor(getResources().getColor(R.color.green));
				
		//Here to highlight the button assosiated with current lesson
		btn = (Button) getView().findViewById(btns[index]);
		btn.setBackgroundColor(getResources().getColor(R.color.red));
	}
	@Override
	public void onClick(View v) {
		for(int i = 0; i < btns.length;i++){
			if(v.getId() == btns[i]){
				((Lessons)(getActivity())).changeContentNotification(i);
			}
		}
	}
}
