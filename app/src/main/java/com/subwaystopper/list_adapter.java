package com.subwaystopper;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class list_adapter extends ArrayAdapter <dataItem>{

	Context context;
	int layoutResourseId;
	
	dataItem[] data;
	int data_size;
	
	public list_adapter(Context context, int layoutResourseID, dataItem[] data){
		super(context, layoutResourseID, data);
		this.data = data;
		this.context = context;
		this.layoutResourseId = layoutResourseId;
		data_size = data.length;
			
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		View row = convertView;
		
		holder holder = null;
		
		

		if(row == null){
			LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(R.layout.row_layout,null);
            
            holder = new holder();
            holder.name_view = (TextView)row.findViewById(R.id.name);
            holder.heading_view = (TextView)row.findViewById(R.id.heading);
            holder.min_view = (TextView)row.findViewById(R.id.min);
            
            row.setTag(holder);
			
        }
	
		else{
			holder = (holder)row.getTag();
		}
    
		dataItem stop = data[position];
		holder.name_view.setText(stop.stop_name);
		holder.heading_view.setText(stop.stop_heading);
		String text;
		if (stop.min < 0){
			text = "NA";
			//text = String.format("%02d min", stop.min);

		}
		else{
			text = String.format("%02d min", stop.min);
		}
		holder.min_view.setText(text);

		return row;
	}
	
	static class holder{
		TextView name_view;
		TextView heading_view;
		TextView min_view;
	}

}
