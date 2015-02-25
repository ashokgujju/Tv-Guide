package com.ashok.tvguide;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.android.volley.toolbox.NetworkImageView;
import com.ashok.tvguide.utils.MySingleton;

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
			convertView = inflater.inflate(R.layout.channel_list_item, null);
		}
		
		final Channel channel = channels.get(position);
		
		final NetworkImageView mLogo = (NetworkImageView) convertView.findViewById(R.id.logo);
		mLogo.setDefaultImageResId(R.drawable.ic_launcher);
		mLogo.setImageUrl(channel.getLogo(), MySingleton.getInstance(context).getImageLoader());
		
		mLogo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(context, ShowsList.class);;
				i.putExtra("name", channel.getName());
				i.putExtra("key", channel.getKey());
				context.startActivity(i);
			}
		});
		return convertView;
	}
}