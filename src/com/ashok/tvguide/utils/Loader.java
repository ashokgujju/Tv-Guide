package com.ashok.tvguide.utils;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;

public class Loader {
	
	private static ProgressDialog progressDialog;
	
	public static void show(Context context, String message) {
		progressDialog = new ProgressDialog(context, AlertDialog.THEME_HOLO_DARK);
		if (progressDialog.isShowing())
			progressDialog.cancel();

		progressDialog.setTitle("Please wait..");
		progressDialog.setCancelable(false);
		progressDialog.setMessage("" + message);
		progressDialog.show();
	}
	
	public static void close() {
		if (progressDialog.isShowing())
			progressDialog.cancel();
		progressDialog = null;
	}
}
