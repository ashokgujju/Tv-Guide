package com.ashok.tvguide;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListOfShows extends Activity {

	private ListView list;
	private ArrayList<Show> shows = new ArrayList<>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shows_list);
		
		list = (ListView) findViewById(R.id.listOfShows);	
		getJsonResponse(getIntent().getExtras().getString("key"), new SimpleDateFormat("ddMMyyyy").format(new Date()));
	}

	private void getJsonResponse(String key, String date) {

		String url = "http://indian-television-guide.appspot.com/indian_television_guide?channel="
				+ key + "&" + "date=" + date;

		StringRequest strReq = new StringRequest(Request.Method.GET, url,
				new Response.Listener<String>() {

					@Override
					public void onResponse(String res) {
						parseResponse(res);
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError arg0) {
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
			showTheList();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	private void showTheList() {
		ListOfShowsAdapter adapter = new ListOfShowsAdapter(this, shows);
		list.setAdapter(adapter);
	}
}
