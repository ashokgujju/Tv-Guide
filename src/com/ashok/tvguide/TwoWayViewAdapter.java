package com.ashok.tvguide;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class TwoWayViewAdapter extends BaseAdapter {
	
	private Context context;
	private LayoutInflater inflater;
	private ArrayList<Channel> channels;

	public TwoWayViewAdapter(Context context, ArrayList<Channel> channels) {
		this.context = context;
		this.channels = channels;
	}

	@Override
	public int getCount() {
		return channels.size();
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
			convertView = inflater.inflate(R.layout.twview_item, null);
		}
		
		Channel channel = channels.get(position);
		
		ImageView mLogo = (ImageView) convertView.findViewById(R.id.logo);
		mLogo.setImageResource(context.getResources().getIdentifier(channel.getLogo(), "drawable", context.getPackageName()));
		
		return convertView;
	}
}