package com.subwaystopper;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List; 
import java.util.Vector;

import com.subwaystopper.data.Contract;


import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.CursorJoiner.Result;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class StopTrackerActvity extends FragmentActivity {

	DisplayAdapter mPagerAdapter;
	String stop_id;
	ArrayList<Long> times_list = new ArrayList<Long>();
	List<DisplayFragment> fragments = new Vector<DisplayFragment>();	 	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		Intent intent = getIntent();
		stop_id = intent.getStringExtra("active_stop");
		
		final ActionBar actionBar = getActionBar();		
		 actionBar.setDisplayHomeAsUpEnabled(true);
		 
		Log.d("sdasdf", stop_id);
		
		String where = "stop_id=?";
		String[] whereArgs = {stop_id};
		Cursor active_stop = getContentResolver().query(Contract.RealTimeStopTimes.CONTENT_URI,
															null,
															where,
															whereArgs,
															null);
		active_stop.moveToFirst();
		for(int i = 0; i < 25; i ++){
			long secs = active_stop.getLong(i + 2);
			if (secs <= 0){
				break;
				
			}
			if (i != 0 && i-1 <= times_list.size() && secs == times_list.get(i-1)){
				Log.d("cursor", "added time");
				continue;
			}
			times_list.add(secs);
		}
		
		String where1 = "stop_id=?";
		String[] whereArgs1 = {stop_id};
		String[] projection = {"stop_name", "stop_heading_name"};
		Cursor active_stop_info = getContentResolver().query(Contract.Stops.CONTENT_URI,
															projection,
															where1,
															whereArgs1,
															null);
		
		active_stop_info.moveToFirst();
		String stop_info_name = active_stop_info.getString(0);
		String stop_info_heading = active_stop_info.getString(1);
		Log.d("name", "" + stop_info_name);
		Log.d("name", "" + stop_info_heading);
		setContentView(R.layout.activity_stop_tracker);
		TextView view_name = (TextView) findViewById(R.id.name_view);
		TextView view_heading = (TextView) findViewById(R.id.heading_view);
		view_name.setText(stop_info_name);
		view_heading.setText(stop_info_heading);



		
		initializePaging();
		
		getContentResolver().registerContentObserver(Contract.RealTimeStopTimes.CONTENT_URI,
										false, new MyObserver(new Handler()));
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
	    Log.d("confic change", "callback");
		super.onConfigurationChanged(newConfig);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
	    case android.R.id.home:
	        finish();										
	        return true;
	    }
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.stop_tracker_actvity, menu);
		return true;
	}
	
	class MyObserver extends ContentObserver {      
		   public MyObserver(Handler handler) {
		      super(handler);         
		   }
		 
		   @Override
		   public void onChange(boolean selfChange) {
		      super.onChange(selfChange, null);
		   }     
		   
		 
		   @Override
		   public void onChange(boolean selfChange, Uri uri) {
			   	Log.d("chang", "FARTS");
			   	new refreshUI().execute(null, null, null);
			   	
		   }     

		}
	
	private class refreshUI extends AsyncTask<URL, Integer, Long> {

		@Override
		protected Long doInBackground(URL... params) {
			Log.d("old", "" + times_list);
			times_list.clear();
			String where = "stop_id=?";
			String[] whereArgs = {stop_id};
			Cursor active_stop = getContentResolver().query(Contract.RealTimeStopTimes.CONTENT_URI,
																null,
																where,
																whereArgs,
																null);
			
			active_stop.moveToFirst();
			for(int i = 0; i < 25; i ++){
				long secs = (active_stop.getLong(i + 2));
				if (secs <= 0){
					break;
				}
				if (i != 0 && secs == times_list.get(i-1)){
					continue;
				}
				times_list.add(secs);
			}	
			Log.d("new", "" + times_list);
			Log.d("system", "" + System.currentTimeMillis()/1000);


			return null;
		}
		
		@Override
		protected void onPostExecute (Long result){
			refreshFragments();
			
		}
		
		
	}
	
	public void refreshFragments(){
		int count = 0;
		 for (int i = 0; i < times_list.size(); i++){
			 if (times_list.get(i) < System.currentTimeMillis()/1000){
				 continue;
			 }
			 Log.d("test", "" + times_list.get(i));
			 count += 1;
			 if (fragments.size() < count){
				 fragments.add(DisplayFragment.newInstance(2));
			 }
			
			 fragments.get(count - 1).setTime((times_list.get(i) - (System.currentTimeMillis()/1000)) * 1000);
			 
	     }
		 if (fragments.size() > count){
			 for (int j = count; j < fragments.size(); j ++){
				 fragments.remove(j);
			 }
		 }
		 
		 
		 Log.d("refresh", "hmm");
		 if (fragments.size() > 0){
	    	 fragments.get(0).setFirst();
	     }
	     mPagerAdapter.notifyDataSetChanged();
	}


	
	public void initializePaging(){

		 this.mPagerAdapter  = new DisplayAdapter(super.getSupportFragmentManager(), fragments);	 
		 ViewPager pager = (ViewPager)super.findViewById(R.id.viewpager);
	     pager.setAdapter(this.mPagerAdapter);
	     pager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
	         @SuppressLint("NewApi")
			 @Override
	         public void onPageSelected(int position) {										
	             
	         }
	     });
	     
	   
	    //long time = times_list.get(1) - (System.currentTimeMillis()/1000);
	     
	     
	     int count = 0;
	     for (int i = 0; i < times_list.size(); i++){
	    	 if (times_list.get(i) < (System.currentTimeMillis()/1000)){
	    		 continue;
	    	 }
	    	 Log.d("hi", "hello");
	    	 if (i == 0){
	    		DisplayFragment df = DisplayFragment.newInstance(0);
	    		df.setFirst();
	    		fragments.add(df);
	    		 
	    	 }
	    	 else{
	    		 fragments.add(DisplayFragment.newInstance(1));
	    	 }
	    	 count += 1;
	    	 fragments.get(count-1).setTime((times_list.get(i) - (System.currentTimeMillis()/1000)) * 1000);
	     }
	     if (fragments.size() > 0){
	    	 fragments.get(0).setFirst();
	     }
	     mPagerAdapter.notifyDataSetChanged();

	     
	     
	}

}

