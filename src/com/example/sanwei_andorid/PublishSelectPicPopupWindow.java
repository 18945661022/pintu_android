package com.example.sanwei_andorid;




import com.example.sanwei_andorid.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

public class PublishSelectPicPopupWindow extends PopupWindow {


	private LinearLayout btnHand;
	private LinearLayout btnLibrary;

	private View mMenuView;

	public PublishSelectPicPopupWindow(Activity context,OnClickListener itemsOnClick) {
		super(context);
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mMenuView = inflater.inflate(R.layout.publish_dialog, null);
		btnHand = (LinearLayout) mMenuView.findViewById(R.id.btn_hand);
		btnLibrary = (LinearLayout) mMenuView.findViewById(R.id.btn_library);
	//	btnCancel = (Button) mMenuView.findViewById(R.id.btn_cancel);
//		btnCancel.setOnClickListener(new OnClickListener() {
//
//			public void onClick(View v) {
//				dismiss();
//			}
//		});
		//璁剧疆鎸夐挳鐩戝�?
		btnHand.setOnClickListener(itemsOnClick);
		btnLibrary.setOnClickListener(itemsOnClick);
		//btnTwocode.setOnClickListener(itemsOnClick);
		//btnCancel.setOnClickListener(itemsOnClick);
		//璁剧疆SelectPicPopupWindow鐨刅iew
		this.setContentView(mMenuView);
		//璁剧疆SelectPicPopupWindow寮瑰嚭绐椾綋鐨勫�?
		this.setWidth(LayoutParams.MATCH_PARENT);
		//璁剧疆SelectPicPopupWindow寮瑰嚭绐椾綋鐨勯�?
		this.setHeight(LayoutParams.WRAP_CONTENT);
		//璁剧疆SelectPicPopupWindow寮瑰嚭绐椾綋鍙偣鍑�?		
		this.setFocusable(true);
		//璁剧疆SelectPicPopupWindow寮瑰嚭绐椾綋鍔ㄧ敾鏁堟灉
		this.setAnimationStyle(R.style.AnimBottom);
		//瀹炰緥鍖栦竴涓狢olorDrawable棰滆壊涓哄崐閫忔�?
		ColorDrawable dw = new ColorDrawable(0xb0000000);
		//璁剧疆SelectPicPopupWindow寮瑰嚭绐椾綋鐨勮儗鏅�?		this.setBackgroundDrawable(dw);
		//mMenuView娣诲姞OnTouchListener鐩戝惉鍒ゆ柇鑾峰彇瑙�?��浣嶇疆濡傛灉鍦ㄩ�鎷╂澶栭潰鍒欓攢姣佸脊鍑烘
		mMenuView.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				
				int height = mMenuView.findViewById(R.id.pop_layout).getTop();
				int y=(int) event.getY();
				if(event.getAction()==MotionEvent.ACTION_UP){
					if(y<height){
						dismiss();
					}
				}				
				return true;
			}
		});

	}

}
