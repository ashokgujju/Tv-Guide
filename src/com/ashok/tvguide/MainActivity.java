package com.ashok.tvguide;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.ashok.tvguide.utils.Loader;
import com.ashok.tvguide.utils.MySingleton;

public class MainActivity extends Activity {

	private HashMap<String, ArrayList<Channel>> categoriesHashMap = new HashMap<>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		fetchChannelsData();
	}

	private void fetchChannelsData() {
		Loader.show(this, "Fetching channels");
		String url = "https://raw.githubusercontent.com/ashokgujju/tv-guide-resources/master/data.json";
		StringRequest strReq = new StringRequest(Request.Method.GET, url,
				new Response.Listener<String>() {

					@Override
					public void onResponse(String res) {
						parseResponse(res);
						showChannels();
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError arg0) {
						Loader.close();
						Toast.makeText(getApplicationContext(),
								arg0.getMessage(), Toast.LENGTH_LONG).show();
					}
				});

		MySingleton.getInstance(this).addToRequestQueue(strReq);
	}

	private void parseResponse(String response) {
		try {
			JSONArray jsonArray = new JSONArray(response);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject categoryJSONObject = jsonArray.getJSONObject(i);
				String categoryName = categoryJSONObject.getString("category");
				JSONArray channelsJSONArray = categoryJSONObject
						.getJSONArray("channels");

				ArrayList<Channel> channels = new ArrayList<>();

				for (int j = 0; j < channelsJSONArray.length(); j++) {
					JSONObject channelJSONObject = channelsJSONArray
							.getJSONObject(j);

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
			Loader.close();
		}
	}

	private void showChannels() {
		ListView list = (ListView) findViewById(R.id.list);
		CategoriesListAdapter adapter = new CategoriesListAdapter(this,
				categoriesHashMap);
		list.setAdapter(adapter);
		Loader.close();
	}
}