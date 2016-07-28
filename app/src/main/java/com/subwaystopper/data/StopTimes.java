package com.subwaystopper.data;

import android.database.sqlite.SQLiteDatabase;

public class StopTimes {
		public static final String TABLE_STOP_TIMES = "stop_times_table";
		  public static final String COLUMN_ID = "_id";
		  public static final String COLUMN_TRIP_ID = "trip_id";
		  public static final String COLUMN_ARRIVAL_TIME = "arrival_time";
		  public static final String COLUMN_STOP_ID = "stop_id";
		  
		  private static final String DATABASE_CREATE = "create table " 
			      + TABLE_STOP_TIMES
			      + "(" 
			      + COLUMN_ID + " integer primary key autoincrement, " 
			      + COLUMN_TRIP_ID + " text not null," 
			      + COLUMN_ARRIVAL_TIME + " text not null,"
			      + COLUMN_STOP_ID + " text not null" + ");";
		  
		  public static void onCreate(SQLiteDatabase database) {
			    database.execSQL(DATABASE_CREATE);
			  }

}
