package com.subwaystopper;

import com.google.transit.realtime.GtfsRealtime.Alert; 
import com.google.transit.realtime.GtfsRealtime.FeedEntity;
import com.google.transit.realtime.GtfsRealtime.FeedHeader;
import com.google.transit.realtime.GtfsRealtime.FeedMessage;
import com.google.transit.realtime.GtfsRealtime.TripDescriptor;
import com.google.transit.realtime.GtfsRealtime.TripUpdate;
import com.google.transit.realtime.GtfsRealtime.TripUpdate.StopTimeEvent;
import com.google.transit.realtime.GtfsRealtime.TripUpdate.StopTimeUpdate;
import com.google.transit.realtime.GtfsRealtime.VehiclePosition;
import com.subwaystopper.data.*;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import au.com.bytecode.opencsv.CSVReader;

public class StartingActivity extends Activity {

	String[] stop_names = new String[36];
	String[] stop_headings = new String[36];
	String[] stop_ids = new String[36];
	dataItem[] list_data = new dataItem[36];
	list_adapter adapter;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d("start","test");
		setContentView(R.layout.activity_starting);
		
		
		Intent mIntent = new Intent(this, DataConnectionService.class);
		startService(mIntent);

		populate_lists();		
		adapter = new list_adapter(this, 0, list_data);
		
		ListView view_list = (ListView) findViewById(R.id.listView1);
		view_list.setAdapter(adapter);
		
		
		view_list.setOnItemClickListener(new OnItemClickListener(){
			
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				Intent stop_intent = new Intent(StartingActivity.this, StopTrackerActvity.class);
				stop_intent.putExtra("active_stop", stop_ids[position]);
				Log.d("stop_id", stop_ids[position]);
				startActivityForResult(stop_intent, 0);
				
			}
		});
		//getContentResolver().registerContentObserver(Contract.RealTimeStopTimes.CONTENT_URI,
			//	false, new MyObserver(new Handler()));
		
		new RefreshTask().execute(null,null,null);
		Log.d("activity", "serviceStarted");
	
	}
	
	 private class RefreshTask extends AsyncTask<URL, Integer, Long> {

		@Override
		protected Long doInBackground(URL... arg0) {
			 synchronized (this) {
			        try {
			           while (true){
			            wait(2000);
			            runOnUiThread(new Runnable(){

							@Override
							public void run() {
								refresh_lists();
							}
			            	
			            });
			           }


			        } catch (InterruptedException e) {
			        }			return null;
		}		
	
	 }
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
			   refresh_lists();
			   Log.d("hoopla", "" + System.currentTimeMillis()/1000);
		   }     

		}
	
	public void refresh_lists(){
		for (int i = 0; i < 36; i ++){
			String selection = "stop_id=?";
			String[] selectionArgs = {stop_ids[i]};
			Cursor minutes = getContentResolver().query(Contract.RealTimeStopTimes.CONTENT_URI,
					null, selection, selectionArgs, null);
			minutes.moveToFirst();
			long seconds;
			long mins = 0;
			int count = 2;
			while(count < 26 && minutes.getLong(count) < System.currentTimeMillis()/1000){	
				count ++;
			}
			seconds = minutes.getLong(count) - System.currentTimeMillis()/1000;

			mins = (long) Math.floor((seconds % 3600)  / 60);
			
			list_data[i]= new dataItem(stop_names[i], stop_headings[i], mins);
			minutes.close();
			adapter.notifyDataSetChanged();
		}
	}
	
	public void populate_lists(){
		
		String[] projection = {"stop_name","stop_heading_name", "stop_id"};
		
		Cursor stops_table = getContentResolver().query(Contract.Stops.CONTENT_URI,
															projection,
															null, 
															null, 
															null);
		
		stops_table.moveToFirst();
		for (int i = 0; i < 36; i ++){
			stop_names[i] = stops_table.getString(0);
			stop_headings[i] = stops_table.getString(1);
			stop_ids[i] = stops_table.getString(2);

			Log.d("stopName: ", stops_table.getString(0));
			Log.d("stopHeading: ", stops_table.getString(1));
			Log.d("stopId: ", stops_table.getString(2));
			String selection = "stop_id=?";
			String[] selectionArgs = {stop_ids[i]};
			Cursor minutes = getContentResolver().query(Contract.RealTimeStopTimes.CONTENT_URI,
														null, selection, selectionArgs, null);
			minutes.moveToFirst();
			long seconds;
			long mins = 0;
			int count = 2;
			while(count < 26 && minutes.getLong(count) < System.currentTimeMillis()/1000){	
				count ++;
			}
			seconds = minutes.getLong(count) - System.currentTimeMillis()/1000;
			mins = (long) Math.floor((seconds % 3600)  / 60);
			list_data[i]= new dataItem(stop_names[i], stop_headings[i], mins);
			minutes.close();
			stops_table.moveToNext();

		}
		
	}

	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.starting, menu);
		return true;
	}
	
	
	

}
