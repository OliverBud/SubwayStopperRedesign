package com.subwaystopper.data;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class RealTimeStopTimes {
	
	public static final String TABLE_RTSTOPS = "real_time_stops_table";
	  public static final String COLUMN_ID = "_id";
	  public static final String COLUMN_STOP_ID = "stop_id";
	  public static final String COLUMN_NEXT_ARRIVAL_0 = "arrival_0";
	  public static final String COLUMN_NEXT_ARRIVAL_1 = "arrival_1";
	  public static final String COLUMN_NEXT_ARRIVAL_2 = "arrival_2";
	  public static final String COLUMN_NEXT_ARRIVAL_3 = "arrival_3";
	  public static final String COLUMN_NEXT_ARRIVAL_4 = "arrival_4";
	  public static final String COLUMN_NEXT_ARRIVAL_5 = "arrival_5";
	  public static final String COLUMN_NEXT_ARRIVAL_6 = "arrival_6";
	  public static final String COLUMN_NEXT_ARRIVAL_7 = "arrival_7";
	  public static final String COLUMN_NEXT_ARRIVAL_8 = "arrival_8";
	  public static final String COLUMN_NEXT_ARRIVAL_9 = "arrival_9";
	  public static final String COLUMN_NEXT_ARRIVAL_10 = "arrival_10";
	  public static final String COLUMN_NEXT_ARRIVAL_11 = "arrival_11";
	  public static final String COLUMN_NEXT_ARRIVAL_12 = "arrival_12";
	  public static final String COLUMN_NEXT_ARRIVAL_13 = "arrival_13";
	  public static final String COLUMN_NEXT_ARRIVAL_14 = "arrival_14";
	  public static final String COLUMN_NEXT_ARRIVAL_15 = "arrival_15";
	  public static final String COLUMN_NEXT_ARRIVAL_16 = "arrival_16";
	  public static final String COLUMN_NEXT_ARRIVAL_17 = "arrival_17";
	  public static final String COLUMN_NEXT_ARRIVAL_18 = "arrival_18";
	  public static final String COLUMN_NEXT_ARRIVAL_19 = "arrival_19";
	  public static final String COLUMN_NEXT_ARRIVAL_20 = "arrival_20";
	  public static final String COLUMN_NEXT_ARRIVAL_21 = "arrival_21";
	  public static final String COLUMN_NEXT_ARRIVAL_22 = "arrival_22";
	  public static final String COLUMN_NEXT_ARRIVAL_23 = "arrival_23";
	  public static final String COLUMN_NEXT_ARRIVAL_24 = "arrival_24";


	  
	  private static final String DATABASE_CREATE = "create table " 
		      + TABLE_RTSTOPS
		      + "(" 
		      + COLUMN_ID + " integer primary key autoincrement, "		      
		      + COLUMN_STOP_ID + " text not null,"
		      + COLUMN_NEXT_ARRIVAL_0 + " real, "
		      + COLUMN_NEXT_ARRIVAL_1 + " real, "
		      + COLUMN_NEXT_ARRIVAL_2 + " real, "
		      + COLUMN_NEXT_ARRIVAL_3 + " real, "
		      + COLUMN_NEXT_ARRIVAL_4 + " real, "
		      + COLUMN_NEXT_ARRIVAL_5 + " real, "
		      + COLUMN_NEXT_ARRIVAL_6 + " real, "
		      + COLUMN_NEXT_ARRIVAL_7 + " real, "
		      + COLUMN_NEXT_ARRIVAL_8 + " real, "
		      + COLUMN_NEXT_ARRIVAL_9 + " real, "
		      + COLUMN_NEXT_ARRIVAL_10 + " real, "
		      + COLUMN_NEXT_ARRIVAL_11 + " real, "
		      + COLUMN_NEXT_ARRIVAL_12 + " real, "
		      + COLUMN_NEXT_ARRIVAL_13 + " real, "
		      + COLUMN_NEXT_ARRIVAL_14 + " real, "
		      + COLUMN_NEXT_ARRIVAL_15 + " real, "
		      + COLUMN_NEXT_ARRIVAL_16 + " real, "
		      + COLUMN_NEXT_ARRIVAL_17 + " real, "
		      + COLUMN_NEXT_ARRIVAL_18 + " real, "
		      + COLUMN_NEXT_ARRIVAL_19 + " real, "
		      + COLUMN_NEXT_ARRIVAL_20 + " real, "
		      + COLUMN_NEXT_ARRIVAL_21 + " real, "
		      + COLUMN_NEXT_ARRIVAL_22 + " real, "
		      + COLUMN_NEXT_ARRIVAL_23 + " real, "
		      + COLUMN_NEXT_ARRIVAL_24 + " real "+ ");";
	  
	  private static final String DATABASE_POPULATE = " insert into "
			  + TABLE_RTSTOPS 
			  + "("
			  + COLUMN_STOP_ID
			  + ")"
			  + " values "
			  + "('x401N'), "
			  + "('x401S'), "
			  + "('x402N'), "
			  + "('x402S'), "
			  + "('x405N'), "
			  + "('x405S'), "
			  + "('x406N'), "
			  + "('x406S'), "
			  + "('x407N'), "
			  + "('x407S'), "
			  + "('x408N'), "
			  + "('x408S'), "
			  + "('x409N'), "
			  + "('x409S'), "
			  + "('x410N'), "
			  + "('x410S'), "
			  + "('x411N'), "
			  + "('x411S'), "
			  + "('x412N'), "
			  + "('x412S'), "
			  + "('x413N'), "
			  + "('x413S'), "
			  + "('x414N'), "
			  + "('x414S'), "
			  + "('x415N'), "
			  + "('x415S'), "
			  + "('x416N'), "
			  + "('x416S'), "
			  + "('x418N'), "
			  + "('x418S'), "
			  + "('x419N'), "
			  + "('x419S'), "
			  + "('x420N'), "
			  + "('x420S'), "
			  + "('x423N'), "
			  + "('x423S') "
			  + ";";

			  
	  
	  public static void onCreate(SQLiteDatabase database) {
		  Log.d("data", "creating");  
		  database.execSQL(DATABASE_CREATE);
		  database.execSQL(DATABASE_POPULATE);
		  }

}
