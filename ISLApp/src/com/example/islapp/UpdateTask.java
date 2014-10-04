package com.example.islapp;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

public class UpdateTask extends AsyncTask<Void, Void, Void>{

	private Context mCon;

	public UpdateTask(Context con)
	{
		mCon = con;
	}
	@Override
	protected Void doInBackground(Void... params) {
		// TODO Auto-generated method stub

		try {
			// Set a time to simulate a long update process.
			Thread.sleep(4000);

			return null;

		} catch (Exception e) {
			return null;
		}

	}

	@Override
	protected void onPostExecute(Void nope) {
		// Give some feedback on the UI.
		Toast.makeText(mCon, "Refresh Complete", 
				Toast.LENGTH_LONG).show();

		// Change the menu back
		((IslappActivity) mCon).resetUpdating();
	}

}
