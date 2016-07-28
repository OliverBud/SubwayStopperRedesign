package com.subwaystopper.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class DatabaseHelper extends SQLiteOpenHelper implements BaseColumns{

	final static int DB_VERSION = 1;
    final static String DB_NAME = "SubwayStopper_database";
    Context context;
     
    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        // Store the context for later use
        this.context = context;
        
    }

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		Trips.onCreate(arg0);		
		Stops.onCreate(arg0);		
		StopTimes.onCreate(arg0);	
		RealTimeStopTimes.onCreate(arg0);

	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}
}
