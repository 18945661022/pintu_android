package com.example.sanwei_andorid;

import java.util.HashSet;
import java.util.Set;

import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import android.app.Activity;
import android.app.Application;


public class MyApplication extends Application {
	
	public static IWXAPI api;

	private static final String TAG = MyApplication.class.getSimpleName();

	private static MyApplication instance;
	
	
	public static String RT_UPLOAD_URL;
	public static String RT_SHOW_URL;
	public static String RT_WAP_URL;	

	public static MyApplication getInstance() {
		return instance;
	}

	public static Set<Activity> setActivities;


	public void onCreate() {
		instance = this;
		setActivities = new HashSet<Activity>();
		wxInit();
	}
	
	private void wxInit(){
		api = WXAPIFactory.createWXAPI(this, Constant.weixin_appid,true);
		api.registerApp(Constant.weixin_appid);
		//TestLog.writeLog("wxInit ok");
	}

    

}
