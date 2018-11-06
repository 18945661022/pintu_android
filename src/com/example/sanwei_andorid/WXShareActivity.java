package com.example.sanwei_andorid;

import com.example.sanwei_andorid.R;
import com.tencent.mm.sdk.openapi.SendMessageToWX;
import com.tencent.mm.sdk.openapi.WXImageObject;
import com.tencent.mm.sdk.openapi.WXMediaMessage;
import com.tencent.mm.sdk.platformtools.Log;
import com.tencent.mm.sdk.platformtools.Util;

//import com.tencent.mm.sdk.openapi.BaseReq;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class WXShareActivity extends Activity{
	
	private PublishSelectPicPopupWindow menuWindow;
	private String path;
	private Bitmap bmp;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.preview_activity);
		Intent intent = getIntent();
		path = intent.getStringExtra("path");
		bmp=Tools.getBitmap(path);
		ImageView iv_preview = (ImageView) this.findViewById(R.id.iv_preview);
		iv_preview.setImageBitmap(bmp);
		EditText edt_memo = (EditText) this.findViewById(R.id.edt_memo);
	    Button shareButton = (Button)findViewById(R.id.btn_share);
	    shareButton.setOnClickListener(new OnClickListener() {
	    		
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// 实例化SelectPicPopupWindow
					menuWindow = new PublishSelectPicPopupWindow(WXShareActivity.this,itemsOnClick);
					// 显示窗口
					menuWindow.showAtLocation(WXShareActivity.this.findViewById(R.id.btn_share),
							Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
			}
		});
	    ImageView back = (ImageView)this.findViewById(R.id.back_layout);
	    back.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				WXShareActivity.this.finish();
			}
	    	
	    });
	}
	
	
	
	private OnClickListener itemsOnClick = new OnClickListener(){

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			int flag = 0;
			menuWindow.dismiss();
			switch (v.getId()) {
			case R.id.btn_hand:
				flag = 0;
				break;
			case R.id.btn_library:
				flag = 1;
				break;
			}
			//微信发送处理
			Log.i("tag", "flag:"+flag);
			if(checkWXAPI()){
				//TestLog.writeLog("weixinfenxiang:"+flag);
				shareImg(flag);
				WXShareActivity.this.finish();
			}
		}
	};
	
	private boolean checkWXAPI(){
		if(!MyApplication.api.isWXAppInstalled()){
			//TestLog.writeLog("isnotinstalled");
			Toast.makeText(WXShareActivity.this,"请安装微信",Toast.LENGTH_SHORT).show();
			return false;
		}
		if(!MyApplication.api.isWXAppSupportAPI()){
			//TestLog.writeLog("supportapierror");
			Toast.makeText(WXShareActivity.this,"微信版本不支持",Toast.LENGTH_SHORT).show();
			return false;
		}
		//TestLog.writeLog("checkok");
		return true;
	}
	
	private void shareImg(int flag){
		Log.i("tag", "imgObj");
		WXImageObject imgObj = new WXImageObject(bmp);
		WXMediaMessage msg = new WXMediaMessage();
		msg.mediaObject = imgObj;
		Log.i("tag", "thumbBmp");
		Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp,150,150,true);
		bmp.recycle();
		msg.thumbData = Util.bmpToByteArray(thumbBmp, true);
		Log.i("tag", "SendMessageToWX");
		SendMessageToWX.Req req = new SendMessageToWX.Req();
		req.transaction = buildTransaction("img");
		req.message = msg;
		Log.i("tag", "switch(flag)");
		switch(flag){
		case 0:
			req.scene = SendMessageToWX.Req.WXSceneTimeline;
			break;
		case 1:
			req.scene = SendMessageToWX.Req.WXSceneSession;
			break;
		}
		MyApplication.api.sendReq(req);
		return;
	}
	
	private String buildTransaction(final String type){
	    return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
	}

}
