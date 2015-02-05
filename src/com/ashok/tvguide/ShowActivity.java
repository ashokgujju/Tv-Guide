package com.ashok.tvguide;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ShowActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show);

		Show show = (Show) getIntent().getSerializableExtra("show");

		TextView mTitle = (TextView) findViewById(R.id.title);
		TextView mDescription = (TextView) findViewById(R.id.description);
		TextView mTime = (TextView) findViewById(R.id.time);

		mTitle.setText(show.getTitle());
		mDescription.setText(show.getDescription());
		mTime.setText(show.getTime());
	}
}