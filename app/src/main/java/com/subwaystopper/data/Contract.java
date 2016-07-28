package com.subwaystopper.data;

import android.net.Uri;

public class Contract {
	static final String AUTHORITY = "com.subwaystopper.provider";
	public static final Uri CONTENT_URI =  Uri.parse("content://" + AUTHORITY);
	
	public static final class Trips{
		public static final Uri CONTENT_URI =  Uri.withAppendedPath(Contract.CONTENT_URI,   "trips_table");
		
		private Trips() {}

	}

	public static final class StopTimes{
		 public static final Uri CONTENT_URI =  Uri.withAppendedPath(Contract.CONTENT_URI,   "stop_times_table");

		 private StopTimes() {}
		 
	}
	
	public static final class Stops{
		 public static final Uri CONTENT_URI =  Uri.withAppendedPath(Contract.CONTENT_URI,   "stops_table");

		 private Stops() {}
		 
	}
	
	public static final class RealTimeStopTimes{
		 public static final Uri CONTENT_URI =  Uri.withAppendedPath(Contract.CONTENT_URI,   "real_time_stops_table");

		 private RealTimeStopTimes() {}
		 
	}

}