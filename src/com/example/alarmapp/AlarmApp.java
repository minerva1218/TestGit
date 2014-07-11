package com.example.alarmapp;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;
import android.widget.Toast;

public class AlarmApp extends Activity implements OnClickListener {

	private int requestCode;
	Intent i;
	Bundle b;
	
	private static TextView mTitleTv;
	private TimePicker mTimePicker;
	private Button set, cancel, btn_back;
	private static int setHour;
	private static int setMinute;
	static int Gtimes;
	Calendar c = Calendar.getInstance();
	 int day =c.get(Calendar.DAY_OF_MONTH);

	// -------------------
	private static AlarmManager alarmMgr;
	private static PendingIntent alarmIntent;
	//private boolean dissure = false;
	String value;

	// ------------------
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alarm_app);
		// init();

		// private void init(){

		mTimePicker = (TimePicker) findViewById(R.id.timePicker1);
		set = (Button) findViewById(R.id.set);
		cancel = (Button) findViewById(R.id.cancel);
		//mTitleTv = (TextView) findViewById(R.id.showTime);
		btn_back = (Button)findViewById(R.id.btn_back);

		c.setTimeInMillis(System.currentTimeMillis());

		// 鬧鈴按鈕監聽
		set.setOnClickListener(this);
		cancel.setOnClickListener(this);
		btn_back.setOnClickListener(this);
		// 設置24小時制
		mTimePicker.setIs24HourView(false);
		mTimePicker.setOnTimeChangedListener(new OnTimeChangedListener() {

			@Override
			public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {

				AlarmApp.setHour = hourOfDay;
				AlarmApp.setMinute = minute;
				// int setHour=c.get(Calendar.HOUR_OF_DAY);
				// int setMinute=c.get(Calendar.MINUTE);
			}
		});

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.set:
			EditText ed = (EditText) findViewById(R.id.mEdit);
			EditText gt = (EditText) findViewById(R.id.editText1);
			int Rtimes = Integer.parseInt(ed.getText().toString()) * 1000;
			/* 取得設定的開始時間，秒及毫秒設為0 */

			Gtimes = Integer.parseInt(gt.getText().toString());
			c.setTimeInMillis(System.currentTimeMillis());
			c.set(Calendar.DAY_OF_MONTH,day);
			c.set(Calendar.HOUR_OF_DAY, mTimePicker.getCurrentHour());
			c.set(Calendar.MINUTE, mTimePicker.getCurrentMinute());
			c.set(Calendar.SECOND, 0);
			c.set(Calendar.MILLISECOND, 0);
			//
			 if (c.getTimeInMillis()<System.currentTimeMillis()) {
				 c.add(Calendar.DAY_OF_MONTH, 1); 
				 //當設定時間小於現在時間 ，系統就將"日"+1=設定的是明天的時間
				 
				 //+1 d
			 }
//			

				/* 指定鬧鐘設定時間到時要執行CallAlarm.class */

				Intent intent = new Intent(AlarmApp.this, CallAlarm.class);
				PendingIntent sender = PendingIntent.getBroadcast(
						AlarmApp.this, 0, intent, 0);
				/*
				 * AlarmManager.RTC_WAKEUP設定服務在系統休眠時同樣會執行
				 * 以set()設定的PendingIntent只會執行一次
				 */
				/* setRepeating()可讓鬧鐘重覆執行 */
				alarmMgr = (AlarmManager) getSystemService(ALARM_SERVICE);
				alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP,
						c.getTimeInMillis(), Rtimes, sender);
				alarmIntent = PendingIntent.getBroadcast(AlarmApp.this, 0,
						intent, 0);
				// /* 更新顯示的設定鬧鐘時間 */
				value = (("設定鬧鐘時間為" + setHour + "時" + setMinute + "分"
						+ "開始，重覆間隔為" + Rtimes / 1000 + "秒" + "猜拳須贏次數為" + Gtimes + "次"));
				//
				
				/* 以Toast提示設定已完成 */

				Toast.makeText(AlarmApp.this, value, Toast.LENGTH_SHORT).show();
				/*
				 * protected String format(Integer currentHour) { // TODO
				 * Auto-generated method stub return null; }
				 */

				//傳回值到HP
				requestCode = 0;
				i = new Intent();
				b = new Bundle();
				b.putInt("setHour", setHour);
				b.putInt("setMinute", setMinute);
				i.putExtras(b);
				setResult(requestCode, i);
				//
			break;

		case R.id.cancel: {

			// 找出當前控件選擇的鬧鐘時間，關閉當前選擇的時鐘
			/* 由AlarmManager中移除 */
			if (alarmMgr != null) {
				alarmMgr.cancel(alarmIntent);
				alarmMgr = null;
//				AlamrReceiver.mp.pause();
//				AlamrReceiver.mp.stop();
				//mTitleTv.setText("你取消了" + AlarmApp.setHour + "時"
					//	+ AlarmApp.setMinute + "分" + "的鬧鈴!!!");
				Toast.makeText(AlarmApp.this, "鬧鐘時間解除", Toast.LENGTH_SHORT)
				.show();					
			} else {
				Toast.makeText(AlarmApp.this, "無鬧鐘設定", Toast.LENGTH_SHORT)
						.show();
				
			}
			//傳回值到HP
			requestCode = 1;
			i = new Intent();
			b = new Bundle();
			b.putString("NoneClock", "設定鬧鐘");
			i.putExtras(b);			
			setResult(requestCode, i);
			//
			break;	
		}
		case R.id.btn_back:
			finish();
			break;
		}
	}
	

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		// 儲存UI狀態到bundle中
		//將目前的狀態存起來，以防activity被殺死時，可以回復原來設定的日、時、分，還有顯示的內容
		outState.putString("str", value);
		outState.putInt("inthour", setHour);
		outState.putInt("intminute", setMinute);
		outState.putInt("intday",day );
		}
	
	
	//還原以下註解所說的內容
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onRestoreInstanceState(savedInstanceState);  
		//將目前的狀態存起來，以防activity被殺死時，可以回復原來設定的日、時、分，還有顯示的內容
		
		mTitleTv.setText(savedInstanceState.getShort("str"));
		int hourOfDay = savedInstanceState.getInt("inthour");
		int minute =savedInstanceState.getInt("intminute");
		int day =savedInstanceState.getInt("intday");
		
		
		Log.i("error", "資料沒匯入");
	}
	
	static public void stopAlarm() {
		if (alarmMgr != null) {
			alarmMgr.cancel(alarmIntent);
			alarmMgr = null;
		}
	}

	// private String format(Integer currentHour) {
	// // TODO Auto-generated method stub
	// return null;
	// }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.alarm_app, menu);
		return true;
	}
}
