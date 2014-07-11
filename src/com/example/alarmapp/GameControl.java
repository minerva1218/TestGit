package com.example.alarmapp;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class GameControl extends Activity implements OnClickListener {
	private ImageView img01, img02, img03, img04;
	private TextView tvresult;
	int point[] = new int[3];
	int cpoint = 0;
	int count = 0;// �qĹ����
	int Acount=0;
	Context context = GameControl.this;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		AlamrReceiver ar=new AlamrReceiver();
		
		
		img01 = (ImageView) findViewById(R.id.imageView2);
		img02 = (ImageView) findViewById(R.id.imageView3);
		img03 = (ImageView) findViewById(R.id.imageView4);
		img04 = (ImageView) findViewById(R.id.imageView5);
		tvresult = (TextView) findViewById(R.id.textView2);
		tvresult.setText("Ĺ�G+1�A��G-1�A����+0");
		img01.setOnClickListener(this);
		img02.setOnClickListener(this);
		img03.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		ImageView img=new ImageView(context);
		img.setImageResource(R.drawable.boy00);
		computerGame();
		Acount=AlarmApp.Gtimes;
		if (count==Acount-1||Acount==0) {
			new AlertDialog.Builder(GameControl.this)
	        .setTitle("���I�����x��")
	        .setMessage("�e�@�i�ӭ��ӷ���y��^_^")
	        .setView(img)
	        .setPositiveButton("�����x��",new DialogInterface.OnClickListener()
	        {
	          public void onClick(DialogInterface dialog, int whichButton)
	          {
	        	  AlarmApp.stopAlarm();
	        	  //AlamrReceiver.mp.pause();
	        	  AlamrReceiver.mp.stop();
	        	  setResult(100);
	        	  finish();
	          }
	        })
	        .show();
			
//			
			
		}
		switch (v.getId()) {
		case R.id.imageView1:

			switch (cpoint) {
			case 1:
				count += 0;
				tvresult.setText("�ثe�w�gĹ�F�G" + count + "��");
				break;
			case 2:
				//count -= 1;
				tvresult.setText("�ثe�w�gĹ�F�G" + count + "��");
				break;
			case 3:
				count += 1;
				tvresult.setText("�ثe�w�gĹ�F�G" + count + "��");
				break;
			}

			break;
		case R.id.imageView2:
			switch (cpoint) {
			case 1:
				count += 1;
				tvresult.setText("�ثe�w�gĹ�F�G" + count + "��");
				break;
			case 2:
				count += 0;
				tvresult.setText("�ثe�w�gĹ�F�G" + count + "��");
				break;
			case 3:
				//count -= 1;
				tvresult.setText("�ثe�w�gĹ�F�G" + count + "��");
				break;

			}
			break;
		case R.id.imageView3:
			switch (cpoint) {
			case 1:
				//count -= 1;
				tvresult.setText("�ثe�w�gĹ�F�G" + count + "��");
				break;
			case 2:
				count += 1;
				tvresult.setText("�ثe�w�gĹ�F�G" + count + "��");
				break;
			case 3:
				count += 0;
				tvresult.setText("�ثe�w�gĹ�F�G" + count + "��");
				break;

			}
			break;
		}

	}

	private void computerGame() {
		// TODO Auto-generated method stub
		cpoint = (int) (Math.random() * 3 + 1);
		switch (cpoint) {
		case 1:
			img04.setImageResource(R.drawable.game01);
			break;
		case 2:
			img04.setImageResource(R.drawable.game02);
			break;
		case 3:
			img04.setImageResource(R.drawable.game03);
			break;
		}
	}
}
