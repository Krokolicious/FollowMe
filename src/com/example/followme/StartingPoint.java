package com.example.followme;


import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Button;
import android.view.View;
import android.content.Intent;

public class StartingPoint extends Activity implements View.OnClickListener {

	
	Button bStart;
	
	//variable for the start button
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_starting_point);
		bStart = (Button) findViewById(R.id.bStart);
		bStart.setOnClickListener(this); //Sets an OnClickListener in this class
		//when the button is pressed, it calls the public void onClick(View v)
		
		
		//(Button) forces bStart to be a button
		// findViewById finds the button "bStart" that was created in the xml file
		//R.id is the reference to the id
		//
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.starting_point, menu);
		return true;
	}

	
	private void bStartClick()
	{
		startActivity(new Intent("com.example.followme.Class2"));
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId())
		{
		   case R.id.bStart:  
		       bStartClick();
		       break;
		}
	}
		
}
