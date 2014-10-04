package com.example.islapp;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AllFixturesFragment extends Fragment {

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.all_matches_layout, container, false);
//		int i = getArguments().getInt(ARG_ITEM_POS);
//		((TextView) rootView.findViewById(R.id.team_1_score_match1)).setText("1");
//							.setImageResource(imageId);

		return rootView;
	}
	
}
