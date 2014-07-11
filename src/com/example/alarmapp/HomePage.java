package com.example.alarmapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class HomePage extends Activity implements OnClickListener {
	TextView editclock;
	private int requestCode;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_homepage);
		
		editclock = (TextView)findViewById(R.id.editclock);
		editclock.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent i;
		switch (v.getId()) {
		case R.id.editclock:
			i = new Intent(HomePage.this, AlarmApp.class);
			//requestCode = 0;
			//i.putExtra("setHour", requestCode);
			startActivityForResult(i, requestCode);
			break;
		default:
			break;
		}
	}
	
	//
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		int timeH, timeM;
		Bundle b =data.getExtras();
		timeH = b.getInt("setHour");
		String timeTextH = Integer.toString(timeH);
		timeM = b.getInt("setMinute");
		String timeTextM = Integer.toString(timeM);
		//
		String noneClock = data.getStringExtra("NoneClock");
		switch (requestCode) {
		case 0:
			editclock.setText("設定鬧鐘時間為" + timeTextH + "時" + timeTextM + "分");
			break;
		case 1:
			editclock.setText(noneClock);
			break;
		default:
			break;
		}
	}
}
