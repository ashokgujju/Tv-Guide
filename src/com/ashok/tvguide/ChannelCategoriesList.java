package com.ashok.tvguide;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class ChannelCategoriesList extends Activity {

	private HashMap<String, ArrayList<Channel>> categoriesHashMap = new HashMap<>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_channel_categories);
		
		try {
			JSONArray jsonArray = new JSONArray(loadJSONFromAsset());
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject categoryJSONObject = jsonArray.getJSONObject(i);
				String categoryName = categoryJSONObject.getString("category");
				JSONArray channelsJSONArray = categoryJSONObject.getJSONArray("channels");
				
				ArrayList<Channel> channels = new ArrayList<>();
				
				for (int j = 0; j < channelsJSONArray.length(); j++) {
					JSONObject channelJSONObject = channelsJSONArray.getJSONObject(j);
					
					Channel channel = new Channel();
					channel.setName(channelJSONObject.getString("name"));
					channel.setKey(channelJSONObject.getString("key"));
					channel.setLogo(channelJSONObject.getString("logo"));
					
					channels.add(channel);
				}
				categoriesHashMap.put(categoryName, channels);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		ListView list = (ListView) findViewById(R.id.list);
		CategoriesListAdapter adapter = new CategoriesListAdapter(this, categoriesHashMap);
		list.setAdapter(adapter);
	}

	public String loadJSONFromAsset() {
		String json = null;
		try {
			InputStream is = getAssets().open("data.json");
			int size = is.available();
			byte[] buffer = new byte[size];
			is.read(buffer);
			is.close();
			json = new String(buffer, "UTF-8");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return json;
	}
}