package com.ashok.tvguide;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class ChannelsTwViewAdapter extends BaseAdapter {
	
	private Context context;
	private LayoutInflater inflater;
	private ArrayList<Channel> channels;

	public ChannelsTwViewAdapter(Context context, ArrayList<Channel> channels) {
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
		
		final Channel channel = channels.get(position);
		
		ImageView mLogo = (ImageView) convertView.findViewById(R.id.logo);
		mLogo.setImageResource(context.getResources().getIdentifier(channel.getLogo(), "drawable", context.getPackageName()));
		mLogo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(context, ListOfShows.class);
				i.putExtra("logo", channel.getLogo());
				i.putExtra("name", channel.getName());
				i.putExtra("key", channel.getKey());
				context.startActivity(i);
			}
		});
		return convertView;
	}
}