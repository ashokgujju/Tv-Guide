package com.ashok.tvguide;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.toolbox.JsonArrayRequest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	HashMap<String, ArrayList<Channel>> dataHashMap = new HashMap<>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		try {
			JSONArray jsonArray = new JSONArray(loadJSONFromAsset());
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject object = jsonArray.getJSONObject(i);
				String categoryName = object.getString("category");
				JSONArray channelsArray = object.getJSONArray("channels");
				
				ArrayList<Channel> channels = new ArrayList<>();
				for (int j = 0; j < channelsArray.length(); j++) {
					JSONObject channelObject = channelsArray.getJSONObject(j);
					
					Channel channel = new Channel();
					channel.setName(channelObject.getString("name"));
					channel.setKey(channelObject.getString("key"));
					channel.setLogo(channelObject.getString("logo"));
					channels.add(channel);
				}
				dataHashMap.put(categoryName, channels);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		ListView list = (ListView) findViewById(R.id.list);
		ListOfCategoriesAdapter adapter = new ListOfCategoriesAdapter(this, dataHashMap);
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