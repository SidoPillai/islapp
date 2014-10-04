package com.example.islapp;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class FeedbackFragment extends Fragment {

	String name;
	String email;
	String feedback;
	Button customDialog_Update;

	static FeedbackFragment newInstance() {
		return new FeedbackFragment(); 
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.feedback, container, false);
		customDialog_Update = (Button) rootView.findViewById(R.id.ButtonSendFeedback);
		customDialog_Update.setOnClickListener(customDialog_UpdateOnClickListener);
		return rootView;
	}

	private Button.OnClickListener customDialog_UpdateOnClickListener
	= new Button.OnClickListener(){

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			final EditText nameField = (EditText) getView().findViewById(R.id.EditTextName);
			name = nameField.getText().toString();

			final EditText emailField = (EditText) getView().findViewById(R.id.EditTextEmail);
			email = emailField.getText().toString();

			final EditText feedbackField = (EditText) getView().findViewById(R.id.EditTextFeedbackBody);
			feedback = feedbackField.getText().toString();

			Intent mail = new Intent(Intent.ACTION_SEND);
			mail.putExtra(Intent.EXTRA_EMAIL, new String[]{"heroisl2014@gmail.com"});
			mail.putExtra(Intent.EXTRA_SUBJECT, "Name : " + name + " Email :" + email);
			mail.putExtra(Intent.EXTRA_TEXT, feedback);
			mail.setType("message/rfc822");
			startActivity(Intent.createChooser(mail, "Thanks for the feedback:"));

		}
	};

}
