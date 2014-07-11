package com.example.alarmapp;

/* import¬ÛÃöclass */
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/* ©I¥s¾xÄÁAlertªºReceiver */
public class CallAlarm extends BroadcastReceiver
{
  @Override
  public void onReceive(Context context, Intent intent)
  {
    //Toast.makeText(context,intent.getAction(),Toast.LENGTH_LONG).show();
    
    /* create Intent¡A©I¥sAlarmReceiver.class */
    Intent i = new Intent(context, AlamrReceiver.class);
        
    Bundle bundleRet = new Bundle();
    bundleRet.putString("STR_CALLER", "");
    i.putExtras(bundleRet);
    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    Log.i("XXXXXXXXXXXXXXXXX", "Before startActivity!");
    context.startActivity(i);      
    Log.i("XXXXXXXXXXXXXXXXX", "After startActivity!");
  }
}