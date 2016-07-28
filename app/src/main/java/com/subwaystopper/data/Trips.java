package com.subwaystopper.data;

import android.database.sqlite.SQLiteDatabase;

public class Trips {
	public static final String TABLE_TRIPS = "trips_table";
	  public static final String COLUMN_ID = "_id";
	  public static final String COLUMN_ROUTE_ID = "route_id";
	  public static final String COLUMN_TRIP_ID = "trip_id";
	  public static final String COLUMN_DIRECTION = "direction";
	  
	  private static final String DATABASE_CREATE = "create table " 
		      + TABLE_TRIPS
		      + "(" 
		      + COLUMN_ID + " integer primary key autoincrement, " 
		      + COLUMN_ROUTE_ID + " text not null," 
		      + COLUMN_TRIP_ID + " text not null,"
		      + COLUMN_DIRECTION + " text not null" + ");";
	  
	  public static void onCreate(SQLiteDatabase database) {
		    database.execSQL(DATABASE_CREATE);
		  }

}
