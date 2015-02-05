package com.ashok.tvguide;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListOfShowsAdapter extends BaseAdapter {

	private Context context;
	private ArrayList<Show> shows;
	private LayoutInflater inflater;
	
	public ListOfShowsAdapter(Context context, ArrayList<Show> shows) {
		this.context = context;
		this.shows = shows;
	}

	@Override
	public int getCount() {
		return shows.size();
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
	public View getView(final int position, View convertView, ViewGroup parent) {
		if(inflater == null) {
			inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}
		if(convertView == null) {
			convertView = inflater.inflate(R.layout.shows_list_item, null);
		}
		
		TextView mTitle = (TextView) convertView.findViewById(R.id.title);
		TextView mLanguage = (TextView) convertView.findViewById(R.id.language);
		mTitle.setText(shows.get(position).getTitle());
		mLanguage.setText(shows.get(position).getLanguage());
		
		convertView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(context, ShowActivity.class);
				i.putExtra("show", shows.get(position));
				context.startActivity(i);
			}
		});
		
		return convertView;
	}

}
