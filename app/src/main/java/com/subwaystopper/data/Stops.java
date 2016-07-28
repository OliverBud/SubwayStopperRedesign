package com.subwaystopper.data;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import android.content.ContentValues;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;

public class Stops {
	
		public static final String TABLE_STOPS = "stops_table";
		  public static final String COLUMN_ID = "_id";
		  public static final String COLUMN_STOP_ID = "stop_id";
		  public static final String COLUMN_STOP_NAME = "stop_name";
		  public static final String COLUMN_STOP_HEADING = "stop_heading";
		  public static final String COLUMN_STOP_HEADING_NAME = "stop_heading_name";


		  private static final String DATABASE_CREATE = "create table " 
			      + TABLE_STOPS
			      + "(" 
			      + COLUMN_ID + " integer primary key autoincrement, " 
			      + COLUMN_STOP_ID + " text not null," 
			      + COLUMN_STOP_HEADING + " text not null," 
			      + COLUMN_STOP_HEADING_NAME + " text not null," 
			      + COLUMN_STOP_NAME + " text not null" + ");";
		  
		  public static void onCreate(SQLiteDatabase database) {
			    
			  database.execSQL(DATABASE_CREATE);
			  fill_table(database);
		  }
		  
		  private static void fill_table(SQLiteDatabase database){
			  ContentValues stops1 = new ContentValues();
			  stops1.put(COLUMN_STOP_ID, "x401N");
			  stops1.put(COLUMN_STOP_NAME, "Woodlawn");
			  stops1.put(COLUMN_STOP_HEADING, "N");
			  stops1.put(COLUMN_STOP_HEADING_NAME, "Borough Hall");
			  database.insert(TABLE_STOPS, null, stops1);
			  
			  ContentValues stops2 = new ContentValues();
			  stops2.put(COLUMN_STOP_ID, "x401S");
			  stops2.put(COLUMN_STOP_NAME, "Woodlawn");
			  stops2.put(COLUMN_STOP_HEADING, "S");
			  stops2.put(COLUMN_STOP_HEADING_NAME, "Woodlawn");
			  database.insert(TABLE_STOPS, null, stops2);

			  ContentValues stops3 = new ContentValues();
			  stops3.put(COLUMN_STOP_ID, "x402N");
			  stops3.put(COLUMN_STOP_NAME, "Mosholu Pkwy");
			  stops3.put(COLUMN_STOP_HEADING, "N");
			  stops3.put(COLUMN_STOP_HEADING_NAME, "Borough Hall");
			  database.insert(TABLE_STOPS, null, stops3);

			  ContentValues stops4 = new ContentValues();
			  stops4.put(COLUMN_STOP_ID, "x402S");
			  stops4.put(COLUMN_STOP_NAME, "Mosholu Pkwy");
			  stops4.put(COLUMN_STOP_HEADING, "S");
			  stops4.put(COLUMN_STOP_HEADING_NAME, "Woodlawn");
			  database.insert(TABLE_STOPS, null, stops4);

			  ContentValues stops5 = new ContentValues();
			  stops5.put(COLUMN_STOP_ID, "x405N");
			  stops5.put(COLUMN_STOP_NAME, "Bedford Park Blvd - Lehman College");
			  stops5.put(COLUMN_STOP_HEADING, "N");
			  stops5.put(COLUMN_STOP_HEADING_NAME, "Borough Hall");
			  database.insert(TABLE_STOPS, null, stops5);

			  ContentValues stops6 = new ContentValues();
			  stops6.put(COLUMN_STOP_ID, "x405S");
			  stops6.put(COLUMN_STOP_NAME, "Bedford Park Blvd - Lehman College");
			  stops6.put(COLUMN_STOP_HEADING, "S");
			  stops6.put(COLUMN_STOP_HEADING_NAME, "Woodlawn");
			  database.insert(TABLE_STOPS, null, stops6);

			  ContentValues stops7 = new ContentValues();
			  stops7.put(COLUMN_STOP_ID, "x406N");
			  stops7.put(COLUMN_STOP_NAME, "Kingsbridge Rd");
			  stops7.put(COLUMN_STOP_HEADING, "N");
			  stops7.put(COLUMN_STOP_HEADING_NAME, "Borough Hall");
			  database.insert(TABLE_STOPS, null, stops7);

			  ContentValues stops8 = new ContentValues();
			  stops8.put(COLUMN_STOP_ID, "x406S");
			  stops8.put(COLUMN_STOP_NAME, "Kingsbridge Rd");
			  stops8.put(COLUMN_STOP_HEADING, "S");
			  stops8.put(COLUMN_STOP_HEADING_NAME, "Woodlawn");
			  database.insert(TABLE_STOPS, null, stops8);

			  ContentValues stops9 = new ContentValues();
			  stops9.put(COLUMN_STOP_ID, "x407N");
			  stops9.put(COLUMN_STOP_NAME, "Fordham Rd");
			  stops9.put(COLUMN_STOP_HEADING, "N");
			  stops9.put(COLUMN_STOP_HEADING_NAME, "Borough Hall");
			  database.insert(TABLE_STOPS, null, stops9);

			  ContentValues stops10 = new ContentValues();
			  stops10.put(COLUMN_STOP_ID, "x407S");
			  stops10.put(COLUMN_STOP_NAME, "Fordham Rd");
			  stops10.put(COLUMN_STOP_HEADING, "S");
			  stops10.put(COLUMN_STOP_HEADING_NAME, "Woodlawn");
			  database.insert(TABLE_STOPS, null, stops10);

			  ContentValues stops11 = new ContentValues();
			  stops11.put(COLUMN_STOP_ID, "x408N");
			  stops11.put(COLUMN_STOP_NAME, "183 St");
			  stops11.put(COLUMN_STOP_HEADING, "N");
			  stops11.put(COLUMN_STOP_HEADING_NAME, "Borough Hall");
			  database.insert(TABLE_STOPS, null, stops11);

			  ContentValues stops12 = new ContentValues();
			  stops12.put(COLUMN_STOP_ID, "x408S");
			  stops12.put(COLUMN_STOP_NAME, "183 St");
			  stops12.put(COLUMN_STOP_HEADING, "S");
			  stops12.put(COLUMN_STOP_HEADING_NAME, "Woodlawn");
			  database.insert(TABLE_STOPS, null, stops12);

			  ContentValues stops13 = new ContentValues();
			  stops13.put(COLUMN_STOP_ID, "x409N");
			  stops13.put(COLUMN_STOP_NAME, "Burnside Av");
			  stops13.put(COLUMN_STOP_HEADING, "N");
			  stops13.put(COLUMN_STOP_HEADING_NAME, "Borough Hall");
			  database.insert(TABLE_STOPS, null, stops13);

			  ContentValues stops14 = new ContentValues();
			  stops14.put(COLUMN_STOP_ID, "x409S");
			  stops14.put(COLUMN_STOP_NAME, "Burnside Av");
			  stops14.put(COLUMN_STOP_HEADING, "S");
			  stops14.put(COLUMN_STOP_HEADING_NAME, "Woodlawn");
			  database.insert(TABLE_STOPS, null, stops14);

			  ContentValues stops15 = new ContentValues();
			  stops15.put(COLUMN_STOP_ID, "x410N");
			  stops15.put(COLUMN_STOP_NAME, "176 St");
			  stops15.put(COLUMN_STOP_HEADING, "N");
			  stops15.put(COLUMN_STOP_HEADING_NAME, "Borough Hall");
			  database.insert(TABLE_STOPS, null, stops15);

			  ContentValues stops16 = new ContentValues();
			  stops16.put(COLUMN_STOP_ID, "x410S");
			  stops16.put(COLUMN_STOP_NAME, "176 St");
			  stops16.put(COLUMN_STOP_HEADING, "S");
			  stops16.put(COLUMN_STOP_HEADING_NAME, "Woodlawn");
			  database.insert(TABLE_STOPS, null, stops16);

			  ContentValues stops17 = new ContentValues();
			  stops17.put(COLUMN_STOP_ID, "x411N");
			  stops17.put(COLUMN_STOP_NAME, "Mt Eden Av");
			  stops17.put(COLUMN_STOP_HEADING, "N");
			  stops17.put(COLUMN_STOP_HEADING_NAME, "Borough Hall");
			  database.insert(TABLE_STOPS, null, stops17);

			  ContentValues stops18 = new ContentValues();
			  stops18.put(COLUMN_STOP_ID, "x411S");
			  stops18.put(COLUMN_STOP_NAME, "Mt Eden Av");
			  stops18.put(COLUMN_STOP_HEADING, "S");
			  stops18.put(COLUMN_STOP_HEADING_NAME, "Woodlawn");
			  database.insert(TABLE_STOPS, null, stops18);

			  ContentValues stops19 = new ContentValues();
			  stops19.put(COLUMN_STOP_ID, "x412N");
			  stops19.put(COLUMN_STOP_NAME, "170 St");
			  stops19.put(COLUMN_STOP_HEADING, "N");
			  stops19.put(COLUMN_STOP_HEADING_NAME, "Borough Hall");
			  database.insert(TABLE_STOPS, null, stops19);

			  ContentValues stops20 = new ContentValues();
			  stops20.put(COLUMN_STOP_ID, "x412S");
			  stops20.put(COLUMN_STOP_NAME, "170 St");
			  stops20.put(COLUMN_STOP_HEADING, "S");
			  stops20.put(COLUMN_STOP_HEADING_NAME, "Woodlawn");
			  database.insert(TABLE_STOPS, null, stops20);

			  ContentValues stops21 = new ContentValues();
			  stops21.put(COLUMN_STOP_ID, "x413N");
			  stops21.put(COLUMN_STOP_NAME, "167 St");
			  stops21.put(COLUMN_STOP_HEADING, "N");
			  stops21.put(COLUMN_STOP_HEADING_NAME, "Borough Hall");
			  database.insert(TABLE_STOPS, null, stops21);

			  ContentValues stops22 = new ContentValues();
			  stops22.put(COLUMN_STOP_ID, "x413S");
			  stops22.put(COLUMN_STOP_NAME, "167 St");
			  stops22.put(COLUMN_STOP_HEADING, "S");
			  stops22.put(COLUMN_STOP_HEADING_NAME, "Woodlawn");
			  database.insert(TABLE_STOPS, null, stops22);

			  ContentValues stops23 = new ContentValues();
			  stops23.put(COLUMN_STOP_ID, "x414N");
			  stops23.put(COLUMN_STOP_NAME, "161 St - Yankee Stadium");
			  stops23.put(COLUMN_STOP_HEADING, "N");
			  stops23.put(COLUMN_STOP_HEADING_NAME, "Borough Hall");
			  database.insert(TABLE_STOPS, null, stops23);

			  ContentValues stops24 = new ContentValues();
			  stops24.put(COLUMN_STOP_ID, "x414S");
			  stops24.put(COLUMN_STOP_NAME, "161 St - Yankee Stadium");
			  stops24.put(COLUMN_STOP_HEADING, "S");
			  stops24.put(COLUMN_STOP_HEADING_NAME, "Woodlawn");
			  database.insert(TABLE_STOPS, null, stops24);

			  ContentValues stops25 = new ContentValues();
			  stops25.put(COLUMN_STOP_ID, "x415N");
			  stops25.put(COLUMN_STOP_NAME, "149 St - Grand Concourse");
			  stops25.put(COLUMN_STOP_HEADING, "N");
			  stops25.put(COLUMN_STOP_HEADING_NAME, "Borough Hall");
			  database.insert(TABLE_STOPS, null, stops25);

			  ContentValues stops26 = new ContentValues();
			  stops26.put(COLUMN_STOP_ID, "x415S");
			  stops26.put(COLUMN_STOP_NAME, "149 St - Grand Concourse");
			  stops26.put(COLUMN_STOP_HEADING, "S");
			  stops26.put(COLUMN_STOP_HEADING_NAME, "Woodlawn");
			  database.insert(TABLE_STOPS, null, stops26);

			  ContentValues stops27 = new ContentValues();
			  stops27.put(COLUMN_STOP_ID, "x416N");
			  stops27.put(COLUMN_STOP_NAME, "138 St - Grand Concourse");
			  stops27.put(COLUMN_STOP_HEADING, "N");
			  stops27.put(COLUMN_STOP_HEADING_NAME, "Borough Hall");
			  database.insert(TABLE_STOPS, null, stops27);

			  ContentValues stops28 = new ContentValues();
			  stops28.put(COLUMN_STOP_ID, "x416S");
			  stops28.put(COLUMN_STOP_NAME, "138 St - Grand Concourse");
			  stops28.put(COLUMN_STOP_HEADING, "S");
			  stops28.put(COLUMN_STOP_HEADING_NAME, "Woodlawn");
			  database.insert(TABLE_STOPS, null, stops28);

			  ContentValues stops29 = new ContentValues();
			  stops29.put(COLUMN_STOP_ID, "x418N");
			  stops29.put(COLUMN_STOP_NAME, "Fulton St");
			  stops29.put(COLUMN_STOP_HEADING, "N");
			  stops29.put(COLUMN_STOP_HEADING_NAME, "Borough Hall");
			  database.insert(TABLE_STOPS, null, stops29);

			  ContentValues stops30 = new ContentValues();
			  stops30.put(COLUMN_STOP_ID, "x418S");
			  stops30.put(COLUMN_STOP_NAME, "Fulton St");
			  stops30.put(COLUMN_STOP_HEADING, "S");
			  stops30.put(COLUMN_STOP_HEADING_NAME, "Woodlawn");
			  database.insert(TABLE_STOPS, null, stops30);

			  ContentValues stops31 = new ContentValues();
			  stops31.put(COLUMN_STOP_ID, "x419N");
			  stops31.put(COLUMN_STOP_NAME, "Wall St");
			  stops31.put(COLUMN_STOP_HEADING, "N");
			  stops31.put(COLUMN_STOP_HEADING_NAME, "Borough Hall");
			  database.insert(TABLE_STOPS, null, stops31);

			  ContentValues stops32 = new ContentValues();
			  stops32.put(COLUMN_STOP_ID, "x419S");
			  stops32.put(COLUMN_STOP_NAME, "Wall St");
			  stops32.put(COLUMN_STOP_HEADING, "S");
			  stops32.put(COLUMN_STOP_HEADING_NAME, "Woodlawn");
			  database.insert(TABLE_STOPS, null, stops32);

			  ContentValues stops33 = new ContentValues();
			  stops33.put(COLUMN_STOP_ID, "x420N");
			  stops33.put(COLUMN_STOP_NAME, "Bowling Green");
			  stops33.put(COLUMN_STOP_HEADING, "N");
			  stops33.put(COLUMN_STOP_HEADING_NAME, "Borough Hall");
			  database.insert(TABLE_STOPS, null, stops33);

			  ContentValues stops34 = new ContentValues();
			  stops34.put(COLUMN_STOP_ID, "x420S");
			  stops34.put(COLUMN_STOP_NAME, "Bowling Green");
			  stops34.put(COLUMN_STOP_HEADING, "S");
			  stops34.put(COLUMN_STOP_HEADING_NAME, "Woodlawn");
			  database.insert(TABLE_STOPS, null, stops34);

			  ContentValues stops35 = new ContentValues();
			  stops35.put(COLUMN_STOP_ID, "x423N");
			  stops35.put(COLUMN_STOP_NAME, "Borough Hall");
			  stops35.put(COLUMN_STOP_HEADING, "N");
			  stops35.put(COLUMN_STOP_HEADING_NAME, "Borough Hall");
			  database.insert(TABLE_STOPS, null, stops35);

			  ContentValues stops36 = new ContentValues();
			  stops36.put(COLUMN_STOP_ID, "x423S");
			  stops36.put(COLUMN_STOP_NAME, "Borough Hall");
			  stops36.put(COLUMN_STOP_HEADING, "S");
			  stops36.put(COLUMN_STOP_HEADING_NAME, "Woodlawn");
			  database.insert(TABLE_STOPS, null, stops36);


		  }
		  
		  
}
