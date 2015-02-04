package com.ashok.tvguide;

import java.io.IOException;
import java.io.InputStream;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.toolbox.JsonArrayRequest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
//      int i = R.drawable.etv;
//      int j = Integer.parseInt("R.drawable.etv");
     
//     int j = getResources().getIdentifier("etv", "drawable", getPackageName());
//     Toast.makeText(this, i+"asdf"+j, Toast.LENGTH_LONG).show();
//     
     try {
		JSONArray jsonArray = new JSONArray(loadJSONFromAsset());
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject object = jsonArray.getJSONObject(i);
			String categoryName = object.getString("category");
			JSONArray data = object.getJSONArray("data");
			for (int j = 0; j < data.length(); j++) {
				JSONObject show = data.getJSONObject(j);
				String showName = show.getString("name");
				Toast.makeText(getApplicationContext(), categoryName, Toast.LENGTH_LONG).show();
				Toast.makeText(getApplicationContext(), showName, Toast.LENGTH_LONG).show();
				break;
			}
			break;
		}
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }
    
    public String loadJSONFromAsset() {
    	String json = null;
    	try {
    		InputStream is = getAssets().open("data.json");
    		int size = is.available();
    		byte[] buffer = new byte [size];
    		is.read(buffer);
    		is.close();
    		json = new String(buffer, "UTF-8");
    	} catch (IOException ex) {
    		ex.printStackTrace();
    	}
		return json;
    }
}