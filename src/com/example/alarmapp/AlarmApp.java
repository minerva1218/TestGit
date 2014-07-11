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

		// �x�a���s��ť
		set.setOnClickListener(this);
		cancel.setOnClickListener(this);
		btn_back.setOnClickListener(this);
		// �]�m24�p�ɨ�
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
			/* ���o�]�w���}�l�ɶ��A��β@��]��0 */

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
				 //��]�w�ɶ��p��{�b�ɶ� �A�t�δN�N"��"+1=�]�w���O���Ѫ��ɶ�
				 
				 //+1 d
			 }
//			

				/* ���w�x���]�w�ɶ���ɭn����CallAlarm.class */

				Intent intent = new Intent(AlarmApp.this, CallAlarm.class);
				PendingIntent sender = PendingIntent.getBroadcast(
						AlarmApp.this, 0, intent, 0);
				/*
				 * AlarmManager.RTC_WAKEUP�]�w�A�Ȧb�t�Υ�v�ɦP�˷|����
				 * �Hset()�]�w��PendingIntent�u�|����@��
				 */
				/* setRepeating()�i���x�����а��� */
				alarmMgr = (AlarmManager) getSystemService(ALARM_SERVICE);
				alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP,
						c.getTimeInMillis(), Rtimes, sender);
				alarmIntent = PendingIntent.getBroadcast(AlarmApp.this, 0,
						intent, 0);
				// /* ��s��ܪ��]�w�x���ɶ� */
				value = (("�]�w�x���ɶ���" + setHour + "��" + setMinute + "��"
						+ "�}�l�A���ж��j��" + Rtimes / 1000 + "��" + "�q����Ĺ���Ƭ�" + Gtimes + "��"));
				//
				
				/* �HToast���ܳ]�w�w���� */

				Toast.makeText(AlarmApp.this, value, Toast.LENGTH_SHORT).show();
				/*
				 * protected String format(Integer currentHour) { // TODO
				 * Auto-generated method stub return null; }
				 */

				//�Ǧ^�Ȩ�HP
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

			// ��X��e�����ܪ��x���ɶ��A������e��ܪ�����
			/* ��AlarmManager������ */
			if (alarmMgr != null) {
				alarmMgr.cancel(alarmIntent);
				alarmMgr = null;
//				AlamrReceiver.mp.pause();
//				AlamrReceiver.mp.stop();
				//mTitleTv.setText("�A�����F" + AlarmApp.setHour + "��"
					//	+ AlarmApp.setMinute + "��" + "���x�a!!!");
				Toast.makeText(AlarmApp.this, "�x���ɶ��Ѱ�", Toast.LENGTH_SHORT)
				.show();					
			} else {
				Toast.makeText(AlarmApp.this, "�L�x���]�w", Toast.LENGTH_SHORT)
						.show();
				
			}
			//�Ǧ^�Ȩ�HP
			requestCode = 1;
			i = new Intent();
			b = new Bundle();
			b.putString("NoneClock", "�]�w�x��");
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
		// �x�sUI���A��bundle��
		//�N�ثe�����A�s�_�ӡA�H��activity�Q�����ɡA�i�H�^�_��ӳ]�w����B�ɡB���A�٦���ܪ����e
		outState.putString("str", value);
		outState.putInt("inthour", setHour);
		outState.putInt("intminute", setMinute);
		outState.putInt("intday",day );
		}
	
	
	//�٭�H�U���ѩһ������e
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onRestoreInstanceState(savedInstanceState);  
		//�N�ثe�����A�s�_�ӡA�H��activity�Q�����ɡA�i�H�^�_��ӳ]�w����B�ɡB���A�٦���ܪ����e
		
		mTitleTv.setText(savedInstanceState.getShort("str"));
		int hourOfDay = savedInstanceState.getInt("inthour");
		int minute =savedInstanceState.getInt("intminute");
		int day =savedInstanceState.getInt("intday");
		
		
		Log.i("error", "��ƨS�פJ");
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
