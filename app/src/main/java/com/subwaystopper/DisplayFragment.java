package com.subwaystopper;


import android.os.Bundle;  
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.ListView;
import android.widget.TextView;

public class DisplayFragment extends Fragment{
	

	
	long time_data;
	CountDownTimer cdt;
	//final View v = this.getView();
	String text;
	boolean first = false;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState){
		if (container == null){
			return null;
		}
		View v = (RelativeLayout)inflater.inflate(R.layout.fragment_display, container, false);
		final TextView tv = (TextView) v.findViewById(R.id.tart);
		tv.setText(Long.toString(time_data));
		if (first){
			TextView first_view = (TextView) v.findViewById(R.id.first);
			first_view.setText("The next L train will arrive in:");
		}
		else{
			TextView first_view = (TextView) v.findViewById(R.id.first);
			first_view.setText("The following L train will arrive in:");
		}
		  
		CountDownTimer official = new CountDownTimer(time_data * 1000, 1000) {

			
		     public void onTick(long millisUntilFinished) {
		    	 
		    	 
		    	 long seconds = millisUntilFinished / 1000;
		         long sec_in_day = seconds % 86400;
				 long hours = (long) Math.floor(sec_in_day / 3600);
				 long minutes = (long) Math.floor((sec_in_day % 3600)  / 60);
				 long real_seconds = (long) Math.floor(sec_in_day % 60);
				 
				 
		         //TextView tv = (TextView) v.findViewById(R.id.tart);
		         
				 tv.setText(text);
				 
//		         tv.setText(Long.toString(hours) + ":" + 
//		        		 Long.toString(minutes) + ":" + 
//		        		 Long.toString(real_seconds));
		     }

		     
		     public void onFinish() {
		     
		         tv.setText("At the Station!");
		     }
		  }.start();

		return v;
	}

	
	public static final DisplayFragment newInstance(int data){
		DisplayFragment f = new DisplayFragment();
		
		return f;

	}
	
	public void setFirst(){
		first = true;
	}
	
	public void setTime(long time){
		time_data = time;
		if (cdt != null){
			cdt.cancel();
		}
		
		
			cdt = new CountDownTimer(time_data, 1000) {

				
			     public void onTick(long millisUntilFinished) {
			    	 
			    	 //Log.d("dsfgkljnasfglkj","sdfgkjg");
			    	 long seconds = millisUntilFinished / 1000;
			         long sec_in_day = seconds % 86400;
					 long hours = (long) Math.floor(sec_in_day / 3600);
					 long minutes = (long) Math.floor((sec_in_day % 3600)  / 60);
					 long real_seconds = (long) Math.floor(sec_in_day % 60);
					 
			         
			         if (hours < 1){
			        	 text = String.format("%02dm %02ds", minutes, real_seconds);
			        	 //text =  Long.toString(minutes) + ":" + 
				        //		 Long.toString(real_seconds);
			        	 return; 
			         }
			         if (hours < 1 && minutes < 1){
			        	 text = String.format("%02ds", real_seconds);
			        	 //text = Long.toString(real_seconds);
				        	return; 
				         }
			         //text = Long.toString(hours) + ":" + 
			        	//	 Long.toString(minutes) + ":" + 
			        		// Long.toString(real_seconds);
			         text = String.format("%02dh %02dm %02ds", hours, minutes, real_seconds);
			         
			     }

			     
			     public void onFinish() {
			    	
			         
			         text = "At the Station!";
			     }
			  }.start();
			
		
		
		
	}
}
