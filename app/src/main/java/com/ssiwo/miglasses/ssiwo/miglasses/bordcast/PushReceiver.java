package com.ssiwo.miglasses.ssiwo.miglasses.bordcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;


import com.ssiwo.miglasses.ssiwo.miglasses.activity.NewSplashActivity;
import com.ssiwo.miglasses.ssiwo.miglasses.activity.WebViewActivity;

import org.json.JSONException;
import org.json.JSONObject;

import cn.jpush.android.api.JPushInterface;

public class PushReceiver extends BroadcastReceiver {

	private static final String TAG = "PushReceiver";

	@Override
	public void onReceive(Context context, Intent intent) {
		Bundle bundle = intent.getExtras();
		Log.d(TAG, "onReceive - " + intent.getAction());

		if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {

		} else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent
				.getAction())) {
			System.out.println("收到了自定义消息。消息内容是："
					+ bundle.getString(JPushInterface.EXTRA_MESSAGE));
			// 自定义消息不会展示在通知栏，完全要开发者写代码去处理
		} else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent
				.getAction())) {
			System.out.println("收到了通知");
			// 在这里可以做些统计，或者做些其他工作
		} else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent
				.getAction())) {
			System.out.println("用户点击打开了通知");
			// 在这里可以自己写代码去定义用户点击后的行为
			String extra = bundle.getString(JPushInterface.EXTRA_EXTRA);
			System.out.println("附加信息:" + extra);
			Intent i = new Intent(context, NewSplashActivity.class);
			i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(i);

			/*Intent intent1 = new Intent(context,WebViewActivity.class);
			intent1.putExtra("url",extra);
			context.startActivity(intent1);*/

			try {
				JSONObject jo = new JSONObject(extra);
				String url = jo.getString("url");
				System.out.println("url:" + url);

				Intent i2 = new Intent(context,WebViewActivity.class);
				i2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				i2.putExtra("url",url);
				System.out.println("url=====" + url);
				context.startActivity(i2);
				// 跳浏览器加载网页
			} catch (JSONException e) {
				e.printStackTrace();
			}

		}
	}
}
