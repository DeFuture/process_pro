package com.defuture.processpro.keep;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		String action = intent.getAction();
		if("android.intent.action.USER_PRESENT".equals(action))
		{
			Toast.makeText(context, "action:" + action, Toast.LENGTH_SHORT).show();
			Log.i("MyBroadcastReceiver", "action:" + action);
		}
	}

}
