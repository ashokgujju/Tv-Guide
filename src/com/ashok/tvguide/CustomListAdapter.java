package com.ashok.tvguide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.lucasr.twowayview.TwoWayView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CustomListAdapter extends BaseAdapter {

	private Context context;
	private LayoutInflater inflater;
	private HashMap<String, ArrayList<Channel>> dataHashMap;
	private ArrayList<String> categories = new ArrayList<>();
	
	public CustomListAdapter(Context context, HashMap<String, ArrayList<Channel>> dataHashMap) {
		this.context = context;
		this.dataHashMap = dataHashMap;
		
		Iterator<String> keysIterator = dataHashMap.keySet().iterator(); 
		while (keysIterator.hasNext())
			categories.add(keysIterator.next());
	}

	@Override
	public int getCount() {
		return dataHashMap.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(inflater == null) {
			inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}
		if(convertView == null) {
			convertView = inflater.inflate(R.layout.list_item, null);
		}
			
		TextView mCategory = (TextView) convertView.findViewById(R.id.category);
		mCategory.setText(categories.get(position));
		
		TwoWayView mTwView = (TwoWayView) convertView.findViewById(R.id.twowayview);
		TwoWayViewAdapter adapter = new TwoWayViewAdapter(context, dataHashMap.get(categories.get(position)));
		mTwView.setAdapter(adapter);
		return convertView;
	}
}
