package com.subwaystopper.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

public class mContentProvider  extends ContentProvider{

	private DatabaseHelper helper;
	private static final int TRIPS_TABLE = 10;
	private static final int TRIPS_TABLE_ID = 20;

	private static final int STOPS_TABLE = 15;
	private static final int STOPS_TABLE_ID = 25;
	
	private static final int STOP_TIMES_TABLE = 35;
	private static final int STOP_TIMES_TABLE_ID = 40;
	
	private static final int REAL_TIME_TABLE = 45;
	private static final int REAL_TIME_TABLE_ID = 55;
	
	private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);
	  static {
	    sURIMatcher.addURI(Contract.AUTHORITY, Stops.TABLE_STOPS, STOPS_TABLE);
	    sURIMatcher.addURI(Contract.AUTHORITY, Stops.TABLE_STOPS + "/#", STOPS_TABLE_ID);
	    
	    sURIMatcher.addURI(Contract.AUTHORITY, Trips.TABLE_TRIPS, TRIPS_TABLE);
	    sURIMatcher.addURI(Contract.AUTHORITY, Trips.TABLE_TRIPS + "/#", TRIPS_TABLE_ID);
	    
	    sURIMatcher.addURI(Contract.AUTHORITY, StopTimes.TABLE_STOP_TIMES, STOP_TIMES_TABLE);
	    sURIMatcher.addURI(Contract.AUTHORITY, StopTimes.TABLE_STOP_TIMES + "/#", STOP_TIMES_TABLE_ID);
	    
	    sURIMatcher.addURI(Contract.AUTHORITY, RealTimeStopTimes.TABLE_RTSTOPS, REAL_TIME_TABLE);
	    sURIMatcher.addURI(Contract.AUTHORITY, RealTimeStopTimes.TABLE_RTSTOPS + "/#", REAL_TIME_TABLE_ID);
	  }
	
	@Override
	public int delete(Uri arg0, String arg1, String[] arg2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getType(Uri uri) {
		int uriType = sURIMatcher.match(uri);
		SQLiteDatabase db = helper.getWritableDatabase();
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		int uriType = sURIMatcher.match(uri);
		SQLiteDatabase db = helper.getWritableDatabase();
		long id = 0;
		Uri PATH;
		switch(uriType){
			case STOPS_TABLE:
				id = db.insert(Stops.TABLE_STOPS, null, values);
				PATH = Contract.Stops.CONTENT_URI;
				break;
			case TRIPS_TABLE:
				id = db.insert(Trips.TABLE_TRIPS, null, values);
				PATH = Contract.Trips.CONTENT_URI;
				break;
			case STOP_TIMES_TABLE:
				id = db.insert(StopTimes.TABLE_STOP_TIMES, null, values);
				PATH = Contract.StopTimes.CONTENT_URI;
				break;
			default:
			      throw new IllegalArgumentException("Unknown URI: " + uri);
		}
		getContext().getContentResolver().notifyChange(uri, null);
		
		return ContentUris.withAppendedId(PATH, id);
	}

	@Override
	public boolean onCreate() {
		helper = new DatabaseHelper(getContext());
		Log.d("cp", "content Provider created");
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String where,
		      String[] whereArgs, String sortOrder) {
		int uriType = sURIMatcher.match(uri);
		SQLiteDatabase db = helper.getWritableDatabase();
		
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
		
		
		switch(uriType){
		case STOPS_TABLE:
			qb.setTables(Stops.TABLE_STOPS);
			break;
		case STOPS_TABLE_ID:
			qb.setTables(Stops.TABLE_STOPS);
			String id = uri.getLastPathSegment();
			qb.appendWhere(Stops.COLUMN_ID + "=" + id);
			break;
		case REAL_TIME_TABLE:
			qb.setTables(RealTimeStopTimes.TABLE_RTSTOPS);
			break;
		case REAL_TIME_TABLE_ID:
			qb.setTables(RealTimeStopTimes.TABLE_RTSTOPS);
			String idq = uri.getLastPathSegment();
			qb.appendWhere(RealTimeStopTimes.COLUMN_ID + "=" + idq);
			break;
		case TRIPS_TABLE:
			qb.setTables(Trips.TABLE_TRIPS);
			break;
		case TRIPS_TABLE_ID:
			qb.setTables(Trips.TABLE_TRIPS);
			String id2 = uri.getLastPathSegment();
			qb.appendWhere(Trips.COLUMN_ID + "=" + id2);
			break;
		case STOP_TIMES_TABLE:
			qb.setTables(StopTimes.TABLE_STOP_TIMES);
			break;
		case STOP_TIMES_TABLE_ID:
			qb.setTables(StopTimes.TABLE_STOP_TIMES);
			String id1 = uri.getLastPathSegment();
			qb.appendWhere(StopTimes.COLUMN_ID + "=" + id1);
			break;
		default:
		      throw new IllegalArgumentException("Unknown URI: " + uri);
		}
		
		Cursor cursor = qb.query(db, projection, where,
		        whereArgs, null, null, sortOrder);
		cursor.setNotificationUri(getContext().getContentResolver(), uri);
		
		return cursor;
	}

	@Override
	public int update(Uri uri, ContentValues values, String where, String[] whereArgs) {
		int uriType = sURIMatcher.match(uri);
		SQLiteDatabase db = helper.getWritableDatabase();
		int rowsUpdated = 0;
		switch(uriType){
			case REAL_TIME_TABLE:
				db.update(RealTimeStopTimes.TABLE_RTSTOPS, values, where, whereArgs);
				break;
			case REAL_TIME_TABLE_ID:
				String id = uri.getLastPathSegment();
				if (TextUtils.isEmpty(where)){
					rowsUpdated = db.update(RealTimeStopTimes.TABLE_RTSTOPS,
							values, 
							RealTimeStopTimes.COLUMN_ID + "=" + id, 
							null);
					
				} else {
					rowsUpdated = db.update(RealTimeStopTimes.TABLE_RTSTOPS, 
							values, 
							RealTimeStopTimes.COLUMN_ID + "=" + id + "and" + where, 
							whereArgs);
					
				}
				break;
		
		}
		//getContext().getContentResolver().notifyChange(uri, null);
		return 0;
	}

}
