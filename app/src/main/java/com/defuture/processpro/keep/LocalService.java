package com.defuture.processpro.keep;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

public class LocalService extends Service {
	
	private MyBinder myBinder;
	
	private MyConn conn;

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return myBinder;
	}
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		
		myBinder = new MyBinder();
		
		if(null == conn)
			conn = new MyConn();
	}
	
	@Override
	@Deprecated
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		this.bindService(new Intent(this, RemoteService.class), conn, Context.BIND_IMPORTANT);
	}
	
	class MyBinder extends ProcessService.Stub{

		@Override
		public String getServiceName() throws RemoteException {
			// TODO Auto-generated method stub
			return "LocalService";
		}
		
	}
	
	class MyConn implements ServiceConnection{

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			Log.d("TAG", "本地服务连接成功");
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			Toast.makeText(LocalService.this, "本地服务被杀死", Toast.LENGTH_SHORT).show();
			LocalService.this.startService(new Intent(LocalService.this, RemoteService.class));
			LocalService.this.bindService(new Intent(LocalService.this, RemoteService.class), conn, Context.BIND_IMPORTANT);
		}
		
	}

}
