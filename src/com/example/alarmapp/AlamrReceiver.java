package com.example.alarmapp;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
		// TODO Auto-generated method stub

		/* ��ڸ��X�x�aDialog��Activity */
		public class AlamrReceiver extends Activity
		{
			static MediaPlayer mp;
		  @Override
		  protected void onCreate(Bundle savedInstanceState) 
		  {
			  mp=MediaPlayer.create(this, R.raw.ifornot);
			  
		    super.onCreate(savedInstanceState);
		    /* ���X���x�aĵ��  */
		    //
		    Log.i("XXXXXXXXXXXXXXXXX", "onCreate(...)!");
		    //
		    new AlertDialog.Builder(AlamrReceiver.this)
		        .setTitle("�x���T�F!!")
		        .setMessage("���ְ_�ɧa!!!")
		        .setPositiveButton("�Q�����L��!? ���N���q���a!!",new DialogInterface.OnClickListener()
		        {
		          public void onClick(DialogInterface dialog, int whichButton)
		          {
		            /* ����Activity */
//		        	  AlamrReceiver.this.finish();
//		        	  mp.pause();
		        	  Intent it= new Intent(AlamrReceiver.this,GameControl.class);
		        	  startActivityForResult(it, 1001);
		          }
		        })
		        .show();
		  }
		
		  public void onClick(View v) {
				// TODO Auto-generated method stub
				finish(); //����
				
			}
/*			@Override
			public void onPause() {
				// TODO Auto-generated method stub
				super.onPause();
				mp.pause();  //���ּȰ�
			}*/
			@Override
			public void onResume() {
				// TODO Auto-generated method stub
				super.onResume();  //���ֶ}�l����
				mp.start();
			}
/*			@Override
			public void onStop() {
				// TODO Auto-generated method stub
				super.onStop();
				mp.stop();   ///���ְ���
				mp.release();
			}*/

			@Override
			public void onRestart() {
				// TODO Auto-generated method stub
				super.onRestart();
				//mp = MediaPlayer.create(this, R.raw.parttwo);
			}

			@Override
			protected void onActivityResult(int requestCode, int resultCode,
					Intent data) {
				// TODO Auto-generated method stub
				super.onActivityResult(requestCode, resultCode, data);
				if(requestCode==1001){
					if(resultCode==100){
						this.finish();
					}
				}
			}
			
	}
