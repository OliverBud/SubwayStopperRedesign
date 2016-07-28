package com.subwaystopper;

public class dataItem {

	public String stop_name;
	public String stop_heading;
	public long min;
	
	public dataItem(){
		super();
	}
	
	public dataItem(String stop_name, String stop_heading, long min){
		super();
		this.stop_name = stop_name;
		this.stop_heading = stop_heading;
		this.min = min;
	}
	
	public void resetMin(long new_min){
		this.min = new_min;
	}
}
