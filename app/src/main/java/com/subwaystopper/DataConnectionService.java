package com.subwaystopper;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.transit.realtime.GtfsRealtime.Alert;
import com.google.transit.realtime.GtfsRealtime.FeedEntity;
import com.google.transit.realtime.GtfsRealtime.FeedHeader;
import com.google.transit.realtime.GtfsRealtime.FeedMessage;
import com.google.transit.realtime.GtfsRealtime.TripDescriptor;
import com.google.transit.realtime.GtfsRealtime.TripUpdate;
import com.google.transit.realtime.GtfsRealtime.VehiclePosition;
import com.google.transit.realtime.GtfsRealtime.TripUpdate.StopTimeEvent;
import com.google.transit.realtime.GtfsRealtime.TripUpdate.StopTimeUpdate;
import com.subwaystopper.data.Contract;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class DataConnectionService extends IntentService{
	
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

	public DataConnectionService() {
		super("intent Service Name");
		mHandler = new Handler();
		// TODO Auto-generated constructor stub
	}

	Handler mHandler;
	boolean go = true;
	URL url = null; 
	public enum LStop {
	    L01N, L01S, L02N, L02S, L03N, L03S, L05N, L05S ,L06N, L06S, L08N, L08S,
	    L10N, L10S, L11N, L11S, L12N, L12S, L13N, L13S ,L14N, L14S, L15N, L15S, 
	    L16N, L16S, L17N, L17S, L19N, L19S, L20N, L20S ,L21N, L21S, L22N, L22S, 
	    L24N, L24S, L25N, L25S, L26N, L26S, L27N, L27S ,L28N, L28S, L29N, L29S,
	}

	public enum Stop {
		x401N, x401S, x402N, x402S, x405N, x405S, x406N, x406S, x407N, x407S,
		x408N, x408S, x409N, x409S, x410N, x410S, x411N, x411S, x412N, x412S,
		x413N, x413S, x414N, x414S, x415N, x415S, x416N, x416S, x418N, x418S,
		x419N, x419S, x420N, x420S, x423N, x423S
	}
	
	
	//ArrayList<ArrayList<Long>> stop_times = new ArrayList<ArrayList<Long>>(48);
	long[][] stop_time_arrays = new long[48][25];
	int[] index_keeper = new int[48];
	
	
	
	 public boolean isNetworkOnline() {
		    boolean status=false;
		    try{
		        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		        NetworkInfo netInfo = cm.getNetworkInfo(0);
		        if (netInfo != null && netInfo.getState()==NetworkInfo.State.CONNECTED) {
		            status= true;
		        }else {
		            netInfo = cm.getNetworkInfo(1);
		            if(netInfo!=null && netInfo.getState()==NetworkInfo.State.CONNECTED)
		                status= true;
		        }
		    }catch(Exception e){
		        e.printStackTrace();  
		        return false;
		    }
		    return status;

		    }  

	@Override
	protected void onHandleIntent(Intent intent) {
		try {
			url = new URL("http://datamine.mta.info/mta_esi.php?key=21b9e5ee83a281f9c2b891cf24ed8ed8");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

			//Log.d("service", "serviceStarted");
			HttpURLConnection urlConnection = null;
				try {
					while(go){
						if (isNetworkOnline()){
							urlConnection = (HttpURLConnection) url.openConnection();
							Log.d("service", "making http connection");
							
							InputStream in = new BufferedInputStream(urlConnection.getInputStream());
							readStream(in);
							urlConnection.disconnect();
						}
						else{
							mHandler.post(new DisplayToast(this, "No network connection! stop times may be stale")); 						}
						
				    synchronized (this) {
				        try {
				            wait(5000);
				            Log.d("waiting", "hey there");
				            wait(5000);
				            Log.d("waiting", "2");
				            wait(5000);
				            Log.d("waiting", "3");
				            wait(5000);
				            Log.d("waiting", "h4");
				            wait(5000);
				            Log.d("waiting", "h5e");
				            wait(5000);
				            Log.d("waiting", "6re");

				        } catch (InterruptedException e) {
				        }
				    }
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				} 

				
			
		
	}
	List<FeedEntity> fel;
	FeedMessage fm;
	
	private class DisplayToast implements Runnable{
		  private final Context mContext;
		  String mText;

		  public DisplayToast(Context mContext, String text){
		    this.mContext = mContext;
		    mText = text;
		  }

		  public void run(){
		     Toast.makeText(mContext, mText, Toast.LENGTH_LONG).show();
		  }
		}
	
	public void readStream(InputStream in){
		try {
			for(int ugh = 0; ugh < 48; ugh ++){
				index_keeper[ugh] = 0;
			}

			//Log.d("service", "reading stream");

			fm = FeedMessage.parseFrom(in);
			
			int entity_count = fm.getEntityCount();
//			Log.d("reading feed message", "entity_count" + Integer.toString(entity_count));
			fel= fm.getEntityList();
			//stop_times.clear();
			
			for (int fill = 0; fill < 48; fill ++){
				//stop_times.get(fill).clear();
			}
			Stop stop;
		
			
			for (int i = 0; i < entity_count; i ++){
				FeedEntity entity = fel.get(i);
				if (entity.hasAlert()){
					Alert alert = entity.getAlert();
//					Log.d("entities", "alert");
					
					
				}
				if (entity.hasTripUpdate()){
//					Log.d("entities", "update");
					

					
					TripUpdate update = entity.getTripUpdate();
					TripDescriptor trip_descriptor = update.getTrip();
					String trip = trip_descriptor.getTripId();
					String route = trip_descriptor.getRouteId();
//					Log.d("trip", trip);
//					Log.d("route", route);
					int stop_time_count = update.getStopTimeUpdateCount();
//					Log.d("stop_time_count", "" + stop_time_count);
					List<StopTimeUpdate> stop_time_update_list = update.getStopTimeUpdateList();
					for (int b = 0; b < stop_time_count; b ++){
						try {
							StopTimeUpdate stu = stop_time_update_list.get(b);
							String stop_id = stu.getStopId();
//							Log.d("stop_id", "" + stop_id);
							stop = Stop.valueOf("x" + stop_id);
							int stop_index = stop.ordinal();

							StopTimeEvent arrival_time = stu.getArrival();
//							Log.d("arrival_time", "" + arrival_time);

							boolean placed = false;
							long time = arrival_time.getTime();
							int delay = arrival_time.getDelay();
							time = time + (long) delay;

							Log.d("stop_test", "" + stop_index);
							Log.d("index_keeper[stop_index", "" + index_keeper[stop_index]);

							Log.d("service", stop_id + " " + Long.toString(time));

							stop_time_arrays[stop_index][index_keeper[stop_index]] = time;
							index_keeper[stop_index]++;

//						if (stop_times.get(stop_index).isEmpty()){
//							stop_times.get(stop_index).add(time);
//							placed = true;
//						}
//						if (!placed){
//							for (int j = 0; j < stop_times.get(stop_index).size(); j ++){
//								if (stop_times.get(stop_index).get(j) > time){
//									stop_times.get(stop_index).add(j, time);
//									placed = true;
//								}
//							}
//						}
//						if (!placed){
//							stop_times.get(stop_index).add(time);
//						}


							long sec_in_day = time % 86400;
							long hours = (long) Math.floor(sec_in_day / 3600);
							long minutes = (long) Math.floor(sec_in_day / 60);
							long seconds = (long) Math.floor(sec_in_day % 60);


						}
						catch(Exception e){
//							Log.e("error trace", e.toString());
							continue;
						}
					}
				}
				if (entity.hasVehicle()){
					//Log.d("entities", "position");

					VehiclePosition vehicle = entity.getVehicle();
					
				}
			}
			
			sort_times();
			//long[][] stop_time_arrays = new long[48][25];
			for (int y = 0; y < 36; y ++){
//				for (int x = 0; x < 25; x ++){
//					if (stop_times.get(y).size() > x){
//						
//						stop_time_arrays[y][x] = stop_times.get(y).get(x);
//						//Log.d("fill", "" + stop_time_arrays[y][x]);
//						
//					}
//					else{
//						//Log.d("test fil arrya", y + " " + x);
//						stop_time_arrays[y][x] = -1;
//					}
//					
//				}
				
				Log.d("cont"," updating database: " + y);
				ContentValues updateValues = new ContentValues();
				//updateValues.put(COLUMN_STOP_ID, "'" + Stop.values()[y] + "'");
				Log.d("stop_time_arrays[y][0]", "" + stop_time_arrays[y][0]);
				Log.d("stop_time_arrays[y][1]", "" + stop_time_arrays[y][1]);
				Log.d("stop_time_arrays[y][2]", "" + stop_time_arrays[y][2]);
				Log.d("stop_time_arrays[y][3]", "" + stop_time_arrays[y][3]);
				Log.d("stop_time_arrays[y][4]", "" + stop_time_arrays[y][4]);
				Log.d("stop_time_arrays[y][5]", "" + stop_time_arrays[y][5]);
				Log.d("stop_time_arrays[y][6]", "" + stop_time_arrays[y][6]);
				Log.d("stop_time_arrays[y][7]", "" + stop_time_arrays[y][7]);
				Log.d("stop_time_arrays[y][8]", "" + stop_time_arrays[y][8]);
				Log.d("stop_time_arrays[y][9]", "" + stop_time_arrays[y][9]);
				Log.d("stop_time_arrays[y][10]", "" + stop_time_arrays[y][10]);
				Log.d("stop_time_arrays[y][11]", "" + stop_time_arrays[y][11]);
				Log.d("stop_time_arrays[y][12]", "" + stop_time_arrays[y][12]);
				Log.d("stop_time_arrays[y][13]", "" + stop_time_arrays[y][13]);
				Log.d("stop_time_arrays[y][14]", "" + stop_time_arrays[y][14]);
				Log.d("stop_time_arrays[y][15]", "" + stop_time_arrays[y][15]);
				Log.d("stop_time_arrays[y][16]", "" + stop_time_arrays[y][16]);
				Log.d("stop_time_arrays[y][17]", "" + stop_time_arrays[y][17]);
				Log.d("stop_time_arrays[y][18]", "" + stop_time_arrays[y][18]);
				Log.d("stop_time_arrays[y][19]", "" + stop_time_arrays[y][19]);
				Log.d("stop_time_arrays[y][20]", "" + stop_time_arrays[y][20]);
				Log.d("stop_time_arrays[y][21]", "" + stop_time_arrays[y][21]);
				Log.d("stop_time_arrays[y][22]", "" + stop_time_arrays[y][22]);
				Log.d("stop_time_arrays[y][23]", "" + stop_time_arrays[y][23]);
				Log.d("stop_time_arrays[y][24]", "" + stop_time_arrays[y][24]);


				updateValues.put(COLUMN_NEXT_ARRIVAL_0,  stop_time_arrays[y][0] );
				updateValues.put(COLUMN_NEXT_ARRIVAL_1,  stop_time_arrays[y][1] );
				updateValues.put(COLUMN_NEXT_ARRIVAL_2,  stop_time_arrays[y][2] );
				updateValues.put(COLUMN_NEXT_ARRIVAL_3,  stop_time_arrays[y][3] );
				updateValues.put(COLUMN_NEXT_ARRIVAL_4,  stop_time_arrays[y][4] );
				updateValues.put(COLUMN_NEXT_ARRIVAL_5,  stop_time_arrays[y][5] );
				updateValues.put(COLUMN_NEXT_ARRIVAL_6,  stop_time_arrays[y][6] );
				updateValues.put(COLUMN_NEXT_ARRIVAL_7,  stop_time_arrays[y][7] );
				updateValues.put(COLUMN_NEXT_ARRIVAL_8,  stop_time_arrays[y][8] );
				updateValues.put(COLUMN_NEXT_ARRIVAL_9,  stop_time_arrays[y][9] );
				updateValues.put(COLUMN_NEXT_ARRIVAL_10, stop_time_arrays[y][10] );
				updateValues.put(COLUMN_NEXT_ARRIVAL_11, stop_time_arrays[y][11] );
				updateValues.put(COLUMN_NEXT_ARRIVAL_12, stop_time_arrays[y][12] );
				updateValues.put(COLUMN_NEXT_ARRIVAL_13, stop_time_arrays[y][13] );
				updateValues.put(COLUMN_NEXT_ARRIVAL_14, stop_time_arrays[y][14] );
				updateValues.put(COLUMN_NEXT_ARRIVAL_15, stop_time_arrays[y][15] );
				updateValues.put(COLUMN_NEXT_ARRIVAL_16, stop_time_arrays[y][16] );
				updateValues.put(COLUMN_NEXT_ARRIVAL_17, stop_time_arrays[y][17] );
				updateValues.put(COLUMN_NEXT_ARRIVAL_18, stop_time_arrays[y][18] );
				updateValues.put(COLUMN_NEXT_ARRIVAL_19, stop_time_arrays[y][19] );
				updateValues.put(COLUMN_NEXT_ARRIVAL_20, stop_time_arrays[y][20] );
				updateValues.put(COLUMN_NEXT_ARRIVAL_21, stop_time_arrays[y][21] );
				updateValues.put(COLUMN_NEXT_ARRIVAL_22, stop_time_arrays[y][22] );
				updateValues.put(COLUMN_NEXT_ARRIVAL_23, stop_time_arrays[y][23] );
				updateValues.put(COLUMN_NEXT_ARRIVAL_24, stop_time_arrays[y][24] );
				
				String where = COLUMN_STOP_ID + "=?";
				String[] whereArgs = {Stop.values()[y].toString()};
				getContentResolver().update(Contract.RealTimeStopTimes.CONTENT_URI, updateValues, where, whereArgs);

								
				
			}
			displayDB();
			
		} catch (IOException e) {
			Log.d("reading message", "error");
			e.printStackTrace();
		}
		
		getContentResolver().notifyChange(Contract.RealTimeStopTimes.CONTENT_URI, null);
		
	}
	
	
	public void displayDB(){
		Cursor recordSet = getContentResolver().query(Contract.RealTimeStopTimes.CONTENT_URI,
				null,
				null,
				null,
				null);
		int numRows = recordSet.getCount();
		recordSet.moveToFirst();
		String stop_id;
		
		
		long stop_0;
		long stop_1;
		long stop_2;
		long stop_3;
		long stop_4;
		long stop_5;
		long stop_6;
		long stop_7;
		long stop_8;
		long stop_9;
		long stop_10;
		long stop_11;
		long stop_12;
		long stop_13;
		long stop_14;
		long stop_15;
		long stop_16;
		long stop_17;
		long stop_18;
		long stop_19;
		long stop_20;
		long stop_21;
		long stop_22;
		long stop_23;
		long stop_24;

		
		String Text;
		Text = Integer.toString(numRows) + "\n";
		for (int i = 0; i < numRows; i ++){
			stop_id = recordSet.getString(recordSet.getColumnIndexOrThrow("stop_id"));
			stop_0 = recordSet.getLong(recordSet.getColumnIndexOrThrow("arrival_0"));
			stop_1 = recordSet.getLong(recordSet.getColumnIndexOrThrow("arrival_1"));
			stop_2 = recordSet.getLong(recordSet.getColumnIndexOrThrow("arrival_2"));
			stop_3 = recordSet.getLong(recordSet.getColumnIndexOrThrow("arrival_3"));
			stop_4 = recordSet.getLong(recordSet.getColumnIndexOrThrow("arrival_4"));
			stop_5 = recordSet.getLong(recordSet.getColumnIndexOrThrow("arrival_5"));
			stop_6 = recordSet.getLong(recordSet.getColumnIndexOrThrow("arrival_6"));
			stop_7 = recordSet.getLong(recordSet.getColumnIndexOrThrow("arrival_7"));
			stop_8 = recordSet.getLong(recordSet.getColumnIndexOrThrow("arrival_8"));
			stop_9 = recordSet.getLong(recordSet.getColumnIndexOrThrow("arrival_9"));
			stop_10 = recordSet.getLong(recordSet.getColumnIndexOrThrow("arrival_10"));
			stop_11 = recordSet.getLong(recordSet.getColumnIndexOrThrow("arrival_11"));
			stop_12 = recordSet.getLong(recordSet.getColumnIndexOrThrow("arrival_12"));
			stop_13 = recordSet.getLong(recordSet.getColumnIndexOrThrow("arrival_13"));
			stop_14 = recordSet.getLong(recordSet.getColumnIndexOrThrow("arrival_14"));
			stop_15 = recordSet.getLong(recordSet.getColumnIndexOrThrow("arrival_15"));
			stop_16 = recordSet.getLong(recordSet.getColumnIndexOrThrow("arrival_16"));
			stop_17 = recordSet.getLong(recordSet.getColumnIndexOrThrow("arrival_17"));
			stop_18 = recordSet.getLong(recordSet.getColumnIndexOrThrow("arrival_18"));
			stop_19 = recordSet.getLong(recordSet.getColumnIndexOrThrow("arrival_19"));
			stop_20 = recordSet.getLong(recordSet.getColumnIndexOrThrow("arrival_20"));
			stop_21 = recordSet.getLong(recordSet.getColumnIndexOrThrow("arrival_21"));
			stop_22 = recordSet.getLong(recordSet.getColumnIndexOrThrow("arrival_22"));
			stop_23 = recordSet.getLong(recordSet.getColumnIndexOrThrow("arrival_23"));
			stop_24 = recordSet.getLong(recordSet.getColumnIndexOrThrow("arrival_24"));
			
			
			Text = Text + stop_id + " " 
					+ Long.toString(stop_0) + " " 
					+ Long.toString(stop_1) + " "
					+ Long.toString(stop_2) + " "
					+ Long.toString(stop_3) + " "
					+ Long.toString(stop_4) + " "
					+ Long.toString(stop_5) + " "
					+ Long.toString(stop_6) + " "
					+ Long.toString(stop_7) + " "
					+ Long.toString(stop_8) + " "
					+ Long.toString(stop_9) + " "
					+ Long.toString(stop_10) + " "
					+ Long.toString(stop_11) + " "
					+ Long.toString(stop_12) + " "
					+ Long.toString(stop_13) + " "
					+ Long.toString(stop_14) + " "
					+ Long.toString(stop_15) + " "
					+ Long.toString(stop_16) + " "
					+ Long.toString(stop_17) + " "
					+ Long.toString(stop_18) + " "
					+ Long.toString(stop_19) + " "
					+ Long.toString(stop_20) + " "
					+ Long.toString(stop_21) + " "
					+ Long.toString(stop_22) + " "
					+ Long.toString(stop_23) + " "
					+ Long.toString(stop_24) + "\n";	
			recordSet.moveToNext();
			
		}
		//TextView display_text = (TextView) findViewById(R.id.texty);
		//display_text.setText(Text);
		try {
		      if( recordSet != null && !recordSet.isClosed() )
		        recordSet.close();
		    } catch(Exception ex) {}
		Log.d("et", Text);
	}
	
	public void sort_times(){
		for (int i = 0; i < 48; i ++){
			
			long end_check = 0;
			int index = 0; 
			for (int count = 0; count < 25; count ++){
	
				end_check = stop_time_arrays[i][count];
				if (end_check <= 0){
					index = count -1;
					break;
				}
			
			}
			stop_time_arrays[i] = mergeSort(stop_time_arrays[i], 0, index);
		}
	}
	
	public static long[] mergeSort(long array[], int start, int end) {
        int middle;     // Middle of array
        int left;       // First element in the left array
        int right;      // First element in the right array
        long temp;       // Temporary storage

        if (start < end) {
            // Split the array in half and sort each half
            middle = (start + end) / 2;
            mergeSort(array, start, middle);
            mergeSort(array, middle + 1, end);

            // Merge the sorted arrays into one
            left = start;
            right = middle + 1;

            // While there are numbers in the array to be sorted
            while (left <= middle && right <= end) {
                // If the current number in the left array
                // is larger than the current number in the right
                // array the numbers need to be moved around
                if (array[left] > array[right]) {
                    // Remember the first number in the right array
                    temp = array[right];

                    // Move the left array right one position to
                    // make room for the smaller number
                    for (int i=right-1; i>=left; i--) {
                        array[i+1] = array[i];
                    }

                    // Put the smaller number where it belongs
                    array[left] = temp;

                    // The right array and the middle need to be
                    // shifted right
                    right++;
                    middle++;
                }

                // No matter what the left array moves right
                left++;
            }

            // For debugging purposes, print out the result of this
            // mergeSort call
            return array;
        }
		return array;
	}

}
