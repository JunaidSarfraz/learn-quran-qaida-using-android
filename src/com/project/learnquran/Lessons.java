package com.project.learnquran;

import java.io.IOException;
import java.net.URI;

import android.R.string;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Lessons extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lessons);
	}
	public void lessonNameHighlightNotification(int index,int prev){
		FragmentManager fm = getFragmentManager(); //if added by xml
		Lessonnames fragment = (Lessonnames) fm.findFragmentById(R.id.fragment_lessons_names);
		fragment.highLightButton(index,prev);
	}
	public void changeContentNotification(int index){
		FragmentManager fm = getFragmentManager(); //if added by xml
		Contents fragment = (Contents) fm.findFragmentById(R.id.fragment_contents);
		fragment.loadScreen(index);
	}
    public static MediaPlayer mp = null;
	public void play(View v) {
		Toast.makeText(getApplicationContext(), v.getContentDescription() +"" , Toast.LENGTH_SHORT).show();
		((Button)findViewById(v.getId())).setBackgroundColor(BIND_IMPORTANT);
		AudioManager audioManager = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
		// For example to set the volume of played media to maximum.
		audioManager.setStreamVolume (AudioManager.STREAM_MUSIC, audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC),0);
		if(mp == null){
			mp = new MediaPlayer();
			AssetFileDescriptor afd;
			try {
				String name = v.getContentDescription().toString();
				name+=".mp3";
				afd=getAssets().openFd(name);
				mp.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
				mp.prepare();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mp.start();
		}
		else{
			if(mp.isPlaying())
			{
				mp.stop();
				mp.reset();
				AssetFileDescriptor afd;
	            try {
	            	String name = v.getContentDescription().toString();
					name+=".mp3";
					afd=getAssets().openFd(name);
					mp.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
					mp.prepare();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            mp.start();
			}
			else
			{
				mp.reset();
				AssetFileDescriptor afd;
	            try {
	            	String name = v.getContentDescription().toString();
					name+=".mp3";
					afd=getAssets().openFd(name);
					mp.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
					mp.prepare();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            mp.start();
			}
		}
	}
}