package com.ashok.tvguide;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.ashok.tvguide.utils.Loader;
import com.ashok.tvguide.utils.MySingleton;

public class ShowsList extends Activity {

	private ExpandableListView list;
	private ArrayList<Show> shows = new ArrayList<>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shows_list);
		
		list = (ExpandableListView) findViewById(R.id.listOfShows);
		
		getActionBar().setTitle(getIntent().getExtras().getString("name"));	
		fetchShows(getIntent().getExtras().getString("key"));
	}

	private void fetchShows(String channel) {		
		Loader.show(this, "Fetching shows..");

		String url = "http://indian-television-guide.appspot.com/indian_television_guide?channel="
				+ channel + "&" + "date=" +  new SimpleDateFormat("ddMMyyyy").format(new Date());
		
		StringRequest strReq = new StringRequest(Request.Method.GET, url,
				new Response.Listener<String>() {

					@Override
					public void onResponse(String res) {
						parseResponse(res);
						displayShows();
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError arg0) {
						Loader.close();
						Toast.makeText(getApplicationContext(), arg0.getMessage(), Toast.LENGTH_LONG).show();
					}
				});

		MySingleton.getInstance(this).addToRequestQueue(strReq);
	}

	private void parseResponse(String res) {
		try {
			JSONObject response = new JSONObject(res);
			JSONArray showsJsArr = response.getJSONArray("listOfShows");
			for (int i = 0; i < showsJsArr.length(); i++) {
				JSONObject showJsObj = showsJsArr.getJSONObject(i);
				JSONObject showJsObjDetails = showJsObj.getJSONObject("showDetails");
				
				Show show = new Show();
				show.setTitle(showJsObj.getString("showTitle"));
				show.setTime(showJsObj.getString("showTime"));
				show.setThumb(showJsObj.getString("showThumb"));
				show.setLanguage(showJsObjDetails.getString("Language:"));
				show.setType(showJsObjDetails.getString("Show Type:"));
				show.setDescription(showJsObjDetails.getString("Show Description"));
				
				shows.add(show);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	private void displayShows() {
		ShowsListAdapter adapter = new ShowsListAdapter(this, shows);
		list.setAdapter(adapter);
		Loader.close();
	}
}