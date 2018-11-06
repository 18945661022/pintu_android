package com.example.sanwei_andorid;

import com.example.sanwei_andorid.MyApplication;
import com.example.sanwei_andorid.R;
import com.tencent.mm.sdk.openapi.BaseReq;
import com.tencent.mm.sdk.openapi.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
	
	@Override
	protected void onCreate( Bundle savedInstanceState){
		//TestLog.writeLog("wxentryActivity onCreate");
		super.onCreate(savedInstanceState);
		MyApplication.api.handleIntent(getIntent(), this);
	}
	
	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		//TestLog.writeLog("onNewIntent");
		//更新intent
		setIntent(intent);
		MyApplication.api.handleIntent(intent, this);
	}
	
	//接受微信调用
	@Override
	public void onReq(BaseReq arg0) {
		// TODO Auto-generated method stub
		finish();
	}
	
	//发信息到微信的相应
	@Override
	public void onResp(BaseResp arg0) {
		// TODO Auto-generated method stub
		this.finish();
		 int result = 0;
		switch (arg0.errCode) {
		case BaseResp.ErrCode.ERR_OK:
			result = R.string.errcode_success;
			break;
		case BaseResp.ErrCode.ERR_USER_CANCEL:
			result = R.string.errcode_cancel;
			break;
		case BaseResp.ErrCode.ERR_AUTH_DENIED:
			result = R.string.errcode_deny;
			break;
		default:
			result = R.string.errcode_unknown;
			break;
		}
		//TestLog.writeLog("result:"+result);
		Toast.makeText(this, result, Toast.LENGTH_LONG).show();
	}

}
