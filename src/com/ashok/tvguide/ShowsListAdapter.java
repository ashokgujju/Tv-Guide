package com.ashok.tvguide;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.ashok.tvguide.utils.MySingleton;

public class ShowsListAdapter extends BaseExpandableListAdapter {

	private Context context;
	private ArrayList<Show> shows;
	
	public ShowsListAdapter(Context context, ArrayList<Show> shows) {
		this.context = context;
		this.shows = shows;
	}

	@Override
	public int getGroupCount() {
		return shows.size();
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return 1;
	}

	@Override
	public Object getGroup(int groupPosition) {
		return shows.get(groupPosition);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return shows.get(groupPosition);
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return groupPosition;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.shows_list_item_group, null);
		}
		TextView mTitle = (TextView) convertView.findViewById(R.id.title);
		TextView mTime = (TextView) convertView.findViewById(R.id.time);
		TextView mType = (TextView) convertView.findViewById(R.id.type);
		NetworkImageView mThumb = (NetworkImageView) convertView.findViewById(R.id.thumb);
		mThumb.setDefaultImageResId(R.drawable.show);
		mThumb.setImageUrl(shows.get(groupPosition).getThumb(), MySingleton.getInstance(context).getImageLoader());
		
		mTitle.setText(shows.get(groupPosition).getTitle());
		mTime.setText(shows.get(groupPosition).getTime());
		mType.setText(shows.get(groupPosition).getType());
		
		return convertView;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.shows_list_item_description, null);
		}
		TextView mDescription = (TextView) convertView.findViewById(R.id.description);
		mDescription.setText(shows.get(groupPosition).getDescription());
		
		return convertView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

}
