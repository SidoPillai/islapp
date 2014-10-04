package com.example.islapp;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class LeagueTableFragment extends Fragment {

	public LeagueTableFragment() {
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.league_table_list_item, container, false);
		return rootView;
		
	}
	
}
