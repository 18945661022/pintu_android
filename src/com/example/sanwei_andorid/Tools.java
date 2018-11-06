package com.example.sanwei_andorid;

import java.io.File;
import java.io.FileInputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;

public class Tools{
	
	
	//鍥剧墖涓婁紶鍦板�?
	public static final String UPLOAD_URL = "http://android.vividworld.cn/MobileUploadFile";
	
	//鍥剧墖鏄剧ず鎷兼帴鍓嶇紑
	public static final String SHOW_URL = "http://android.vividworld.cn/extImage";	
	
	//鍒嗕韩璺宠浆WAP椤佃繛鎺�?	public static final String WAP_URL = "http://android.vividworld.cn/sdsj.jsp?img=";		

	public static boolean fileIsExists(String path){
        try{                        
           File f=new File(path);                        
           if(!f.exists()){                                
               return false;                        
            }                                        
        }catch (Exception e) {                        
        // TODO: handle exception                        
            return false;                
        }                
        return true;        
    }	
	
	public static void createDir(String path){
		try{                        
	           File dir = new File(path);                        
	           if(!dir.exists()){                                
	                  dir.mkdir();                    
	            }                                        
	        }catch (Exception e) {                        
	        // TODO: handle exception                        
	                       
	        }                
	}
	
	
	/*** 鑾峰彇鏂囦欢澶у皬 ***/
	public static long getFileSizes(String path) throws Exception { 
	     long s = 0; 
		 try {
				File f=new File(path);
				 if (f.exists()) { 
				 FileInputStream fis = null; 
				 fis = new FileInputStream(f); 
				 s = fis.available(); 
				 fis.close();
				 } else { 
				 f.createNewFile(); 
				 }
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	    return s; 
	} 
	
	//鑾峰彇鎵嬫満SD鍗¤矾寰�
	public static String getSDPath(){ 
	       File sdDir = null; 
	       boolean sdCardExist = Environment.getExternalStorageState()   
	                           .equals(Environment.MEDIA_MOUNTED);   //鍒ゆ柇sd鍗℃槸鍚�?��鍦�
	       if   (sdCardExist)   
	       {                               
	         sdDir = Environment.getExternalStorageDirectory();//鑾峰彇璺熺洰褰�
	       }   
	       return sdDir.toString(); 
	       
	} 	
	
	//鍒ゆ柇褰撳墠鎵嬫�?��戠粶绫诲�?
	public static int getConnectedType(Context context) {  
	    if (context != null) {  
	        ConnectivityManager mConnectivityManager = (ConnectivityManager) context  
	                .getSystemService(Context.CONNECTIVITY_SERVICE);  
	        NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();  
	        if (mNetworkInfo != null && mNetworkInfo.isAvailable()) {  
	            return mNetworkInfo.getType();  
	        }  
	    }  
	    return -1;  
	}
	
	//鍒ゆ柇鏄惁鏈夌綉缁滆繛鎺�
	public static boolean isNetworkConnected(Context context) {  
	    if (context != null) {  
	        ConnectivityManager mConnectivityManager = (ConnectivityManager) context  
	                .getSystemService(Context.CONNECTIVITY_SERVICE);  
	        NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();  
	        if (mNetworkInfo != null) {  
	            return mNetworkInfo.isAvailable();  
	        }  
	    }  
	    return false;  
	}
	
	//鍒ゆ柇WIFI鏄惁鍙敤
	public static boolean isWifiConnected(Context context) {  
	    if (context != null) {  
	        ConnectivityManager mConnectivityManager = (ConnectivityManager) context  
	                .getSystemService(Context.CONNECTIVITY_SERVICE);  
	        NetworkInfo mWiFiNetworkInfo = mConnectivityManager  
	                .getNetworkInfo(ConnectivityManager.TYPE_WIFI);  
	        if (mWiFiNetworkInfo != null) {  
	            return mWiFiNetworkInfo.isAvailable();  
	        }  
	    }  
	    return false;  
	}
	
	//鍒ゆ柇鎵嬫満缃戠粶鏄惁鍙�?
	public static boolean isMobileConnected(Context context) {  
	    if (context != null) {  
	        ConnectivityManager mConnectivityManager = (ConnectivityManager) context  
	                .getSystemService(Context.CONNECTIVITY_SERVICE);  
	        NetworkInfo mMobileNetworkInfo = mConnectivityManager  
	                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);  
	        if (mMobileNetworkInfo != null) {  
	            return mMobileNetworkInfo.isAvailable();  
	        }  
	    }  
	    return false;  
	}
	//鑾峰彇鑷畾璺緞鍥剧墖璧勬�?
	public static Bitmap getBitmap(String path)
	{
		File mFile=new File(path);
        //鑻ヨ鏂囦欢瀛樺�?
        if (mFile.exists()) {
            Bitmap bitmap=BitmapFactory.decodeFile(path);
            return bitmap;
        }
        return null;
	}	
	
}