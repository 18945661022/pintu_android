package com.example.sanwei_andorid;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.unity3d.player.UnityPlayer;
import com.unity3d.player.UnityPlayerActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;



public class VuforiaActivity extends UnityPlayerActivity{
	Context mContext = null;
	boolean firstStarted;
	private String photoPath;
	private Handler handler;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		 
	     
	     super.onCreate(savedInstanceState);
	    
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        Log.i("Unity", "oncreate");
        mContext = this;
        firstStarted = true;
        photoPath = getSDPath()+"//DCIM//"+getResources().getString(R.string.photo_folder);
        File dir = new File(photoPath);                        
        if(!dir.exists()){                                
               dir.mkdir();                    
         }       
        
        handler = new Handler(){
			@Override
			public void handleMessage(Message msg){
				super.handleMessage(msg);
				switch(msg.what){
				case 1:
					setBrightness(255);
					break;
				case 2:
					break;
				}
			}
        };
	}
	

	@Override
	public void onConfigurationChanged(Configuration newConfig){
		//if(newConfig.orientation==1)
			//super.onConfigurationChanged(newConfig);
		Log.i("Unity","onConfigurationChanged");
	}
	
	@Override
	public void onStart(){
		Log.i("Unity", "onstart");
		super.onStart();
	}
	
	@Override
	public void onResume(){
		super.onResume();
		Log.i("Unity", "onResume");
	}
	@Override
	public void onPause(){
		super.onPause();
		Log.i("Unity", "onpause");
	}
	
	public void objectTest(final Object obj){
		Log.i("Unity", "objectTestAndroid");
	/*	Camera camera = (Camera)obj;
		Parameters parameters = camera.getParameters();
		int exposureCompensation = parameters.getExposureCompensation();*/
		//Log.i("Unity","turturtut:"+exposureCompensation);
	}
	
	public void unityInitialize(final String str){
		//cameraShow();
		
		new Thread(new Runnable(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try{
					Log.i("tag","new thread");
					Message msg = new Message();
					msg.what = 1;
					
					handler.sendMessage(msg);
					
				}catch(Exception e){
					e.printStackTrace();
				}finally{
					
				}
			}
			
		}).start();
	}
	
	public void setBrightness(int brightValue){
		Window window = getWindow();
		WindowManager.LayoutParams lp = window.getAttributes();
		lp.screenBrightness = (brightValue<=0?-1.0f:(float)brightValue/255);
		window.setAttributes(lp);
		
	}
	
	public void sendMessageToUnity3D(String msg)
    {
		UnityPlayer.UnitySendMessage("ARCamera","getMessageFromAndroid",msg);
    }
	
	public void startCamera(String msg)
	{
		UnityPlayer.UnitySendMessage("ARCamera","OpenCameraDevice",msg);
	}
	
	
	public void getMessageFromUnity3d(final int orderId,final String msg){
	
		switch(orderId){
			case 1:
				VuforiaActivity.this.finish();
				break;
			case 2:
				break;
			default:
				break;
		}
	}
	
	public  String getSDPath(){ 
	       File sdDir = null; 
	       boolean sdCardExist = Environment.getExternalStorageState()   
	                           .equals(Environment.MEDIA_MOUNTED);   
	       if   (sdCardExist)   
	       {                               
	         sdDir = Environment.getExternalStorageDirectory();
	       }   
	       return sdDir.toString(); 
	       
	} 	
	
	public String getPath(final String path){
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd_HHmmss");
		String t = format.format(new Date());
		String returnPath = photoPath+"//"+t+".jpg";
		Log.i("Unity", returnPath);
		return returnPath;
	}
	
	public void notifyPhotos(final String path){
		sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,Uri.parse("file://"+path)));
	}
	
	public void getUnityTargetShare(final String path){
		//TestLog.writeLog("getUnityTargetShare:"+path);
		Intent intent = new Intent(VuforiaActivity.this,WXShareActivity.class);
		intent.putExtra("path", path);
		VuforiaActivity.this.startActivity(intent);
	}
	
	@Override
	protected void onNewIntent(Intent intent){
		super.onNewIntent(intent);
		setIntent(intent);
		
	}
	
}
