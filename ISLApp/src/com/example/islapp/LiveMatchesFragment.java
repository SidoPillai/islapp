package com.example.islapp;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class LiveMatchesFragment extends Fragment {

	private String curURL = "http://www.islapp.com";
	public void init(String url) {
		curURL = url;
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	@SuppressLint("SetJavaScriptEnabled")
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.webview_live_score, container, false);

		if (curURL != null) {
			WebView webview = (WebView) rootView.findViewById(R.id.webView2);
			webview.getSettings().setJavaScriptEnabled(true);
			webview.setWebViewClient(new webClient());
			webview.loadUrl(curURL);
		}
		return rootView;
	}

	@SuppressLint("SetJavaScriptEnabled")
	public void updateUrl(String url) {
		curURL = url;
		WebView webview = (WebView) getView().findViewById(R.id.webView2);
		webview.getSettings().setJavaScriptEnabled(true);
		webview.loadUrl(url);
	}

	private class webClient extends WebViewClient {

		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			return false;
		}
	}
}
