package com.ssiwo.miglasses.ssiwo.miglasses.activity;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.ssiwo.miglasses.R;

import com.ssiwo.miglasses.ssiwo.miglasses.bean.InfoBean;
import com.ssiwo.miglasses.ssiwo.miglasses.bean.NewsData4;
import com.ssiwo.miglasses.ssiwo.miglasses.constant.Api;
import com.ssiwo.miglasses.ssiwo.miglasses.utils.StreamUtils;
import com.ssiwo.miglasses.ssiwo.miglasses.service.UpdateService;
import com.ssiwo.miglasses.ssiwo.miglasses.utils.Utils;


import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import cn.jpush.android.api.JPushInterface;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NewSplashActivity extends Activity{
	protected static final int CODE_UPDATE_DIALOG = 0;
	protected static final int CODE_URL_ERROR = 1;
	protected static final int CODE_NET_ERROR = 2;
	protected static final int CODE_JSON_ERROR = 3;
	protected static final int CODE_ENTER_HOME = 4;
	
	private ProgressBar pb;
	public static int loading_process;
	public static long count;
	private TextView tv;
	
	//服务器返回的信息
	private String mVersionName;
	private String mVersionCode;

	private String mDownloadUrl;
	private TextView tvVersion;
	
	private String userIdSplashActivity;
	private String SerialNumber;
	private NewsData4.ShopInfo shopInfoList;
	private InfoBean.ShopInfoBean shopInfoList1;
	private String ruId;
	
	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case CODE_UPDATE_DIALOG:
				showUpdateDailog();
				break;
			case CODE_URL_ERROR:
				Toast.makeText(NewSplashActivity.this, "url错误", Toast.LENGTH_SHORT)
						.show();
				//enterHome();
				break;
			case CODE_NET_ERROR:
				Toast.makeText(NewSplashActivity.this, "版本号检测异常", Toast.LENGTH_SHORT)
						.show();
				enterHome();
				break;
			case CODE_JSON_ERROR:
				Toast.makeText(NewSplashActivity.this, "数据解析错误",
						Toast.LENGTH_SHORT).show();
				//enterHome();
				break;
			case CODE_ENTER_HOME:
				enterHome();
				break;
				case 5:
					Intent intent=new Intent(NewSplashActivity.this,FunActivity.class);
					intent.putExtra("ruId", ruId);
					intent.putExtra("name", shopTitle);
					intent.putExtra("erweima", weichat_thumb);
//					System.out.println("跳转到下一个页面ruId=============" + ruId);
					startActivity(intent);
					finish();
					//System.out.println("登录成功");
					//Toast.makeText(Login.this, "登录成功", Toast.LENGTH_SHORT).show();
					break;
				case 6:
					Toast.makeText(NewSplashActivity.this, "序列号错误", Toast.LENGTH_SHORT).show();
					break;
				case 7:
					Toast.makeText(NewSplashActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
					break;
			default:
				break;
			}
			super.handleMessage(msg);
		};
	};
	private String shopTitle;
	private String weichat_thumb;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
		SerialNumber = android.os.Build.SERIAL;
//		System.out.println("SerialNumber+++++++" + SerialNumber);
//		System.out.println("wifi MAC地址:+++++++" + getMacAddress());

        /*Intent intent = getIntent();
        userIdSplashActivity = intent.getStringExtra("ruId");
        System.out.println("userIdSplashActivity+++++++++" + userIdSplashActivity);*/
        tvVersion = (TextView) findViewById(R.id.tv_version);
        tvVersion.setText("版本:" + getVersionName());
        //tvProgress = (TextView) findViewById(R.id.tv_progress);
        //pb = (ProgressBar) findViewById(R.id.pb);
        checkVersion();
    }

//    public void click(View v){
//		post();
//    	Intent intent = new Intent(SplashActivity.this,MimiMainActivity.class);
//    	intent.putExtra("ruId", ruId);
//    	startActivity(intent);
//    }

    //获取版本名称
    private String getVersionName() {
		PackageManager packageManager = getPackageManager();
		try {
			PackageInfo packageInfo = packageManager.getPackageInfo(
					getPackageName(), 0);

			int versionCode = packageInfo.versionCode;
			String versionName = packageInfo.versionName;

//			System.out.println("versionName=" + versionName + ";versionCode="
//					+ versionCode);

			return versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return "";
	}

	 //获取本地app的版本号
	private int getVersionCode() {
		PackageManager packageManager = getPackageManager();
		try {
			PackageInfo packageInfo = packageManager.getPackageInfo(
					getPackageName(), 0);

			int versionCode = packageInfo.versionCode;
//			System.out.println("versionCode++++++++" + versionCode);
			return versionCode;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return -1;
	}

	//从服务器获取版本信息
	private void checkVersion(){
		final long startTime = System.currentTimeMillis();
		new Thread(){

			@Override
			public void run() {
				Message msg = Message.obtain();
				HttpURLConnection conn = null;
				try {
					URL url = new URL(Api.VISION_URL);
					conn = (HttpURLConnection) url.openConnection();
					conn.setRequestMethod("GET");
					conn.setConnectTimeout(5000);
					conn.setReadTimeout(5000);
					conn.connect();

					int responseCode = conn.getResponseCode();
					if (responseCode == 200) {
						InputStream inputStream = conn.getInputStream();
						String result = StreamUtils.readFromStream(inputStream);
//						System.out.println("网络返回:" + result);
						// 解析json
						JSONObject jo = new JSONObject(result);
						Log.i("json", jo.toString());
						//mVersionName = jo.getString("versionName");
						mVersionCode = jo.getString("version");

//						System.out.println( "mVersionCode="
//								+ mVersionCode);
						//mDesc = jo.getString("description");
						mDownloadUrl = jo.getString("appUrl");
						Log.i("mDownloadUrl", mDownloadUrl);
						 //System.out.println("版本描述:" + mDesc);
//						System.out.println(mDownloadUrl + "999999999999999999");

						if (Double.parseDouble(mVersionCode) > getVersionCode()) {
							// 服务器的VersionCode大于本地的VersionCode
//							System.out.println("大于大于大于大于。。。。。。");
							msg.what = CODE_UPDATE_DIALOG;
						} else {
							msg.what = CODE_ENTER_HOME;
						}
					}
				} catch (MalformedURLException e) {
					msg.what = CODE_URL_ERROR;
					e.printStackTrace();
				} catch (IOException e) {
					msg.what = CODE_NET_ERROR;
					e.printStackTrace();
				} catch (JSONException e) {
					msg.what = CODE_JSON_ERROR;
					e.printStackTrace();
				} finally {
					long endTime = System.currentTimeMillis();
					long timeUsed = endTime - startTime;
					if (timeUsed < 2000) {
						try {
							Thread.sleep(2000 - timeUsed);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					mHandler.sendMessage(msg);
					if (conn != null) {
						conn.disconnect();
					}
				}
			}
		}.start();
	}
	//升级对话框
	public void showUpdateDailog(){
		Builder builder=new Builder(this);
		builder.setTitle("发现新版本");
		builder.setCancelable(false);
		builder.setIcon(R.drawable.icon);
		builder.setPositiveButton("稍后再说", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				enterHome();

			}
		});
		builder.setNegativeButton("立即更新", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				//启动服务
				Toast.makeText(NewSplashActivity.this, "正在后台为您下载", Toast.LENGTH_LONG).show();
				Intent intent=new Intent(NewSplashActivity.this,UpdateService.class);
				intent.putExtra("appName", "眯眯眼镜");
				intent.putExtra("url",mDownloadUrl );
				startService(intent);
				enterHome();
			}
		});
		builder.show();
	}
	public void Beginning(){
		//download();
		LinearLayout ll = (LinearLayout) LayoutInflater.from(NewSplashActivity.this).inflate(
				R.layout.layout_loadapk, null);
		pb = (ProgressBar) ll.findViewById(R.id.down_pb);
		tv = (TextView) ll.findViewById(R.id.tv);
		Builder builder = new Builder(NewSplashActivity.this);
		builder.setCancelable(false);
		builder.setView(ll);builder.setTitle("版本更新进度提示");
		builder.setNegativeButton("后台下载",
				new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent intent=new Intent(NewSplashActivity.this, UpdateService.class);
						startService(intent);
						enterHome();
						dialog.dismiss();
					}
				});
		builder.show();
		new Thread() {
			public void run() {
				download();
			}
		}.start();
	}
	
	//下载apk文件
	public void download(){
		
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
			
			//pb.setVisibility(View.VISIBLE);
			//tvProgress.setVisibility(View.VISIBLE);
			String target = Environment.getExternalStorageDirectory()
					+ "/gl.apk";
			HttpUtils utils = new HttpUtils();
			utils.download(mDownloadUrl, target, new RequestCallBack<File>() {
				
				@Override
				public void onLoading(long total, long current,
						boolean isUploading) {
					super.onLoading(total, current, isUploading);
					loading_process =(int)current;
					pb.setMax((int)total);
					pb.setProgress(loading_process);
					//System.out.println("下载进度:" + current + "/" + total);
					count = current * 100 / total;
					tv.setText("下载进度:" + count + "%");
				}
				
				public void onSuccess(ResponseInfo<File> arg0) {
					//System.out.println("下载成功");
					// 跳转到系统下载页面
					/*Intent intent = new Intent(Intent.ACTION_VIEW);
					intent.addCategory(Intent.CATEGORY_DEFAULT);
					intent.setDataAndType(Uri.fromFile(arg0.result),
							"application/vnd.android.package-archive");
					// startActivity(intent);
					startActivityForResult(intent, 0);*/
					
					Intent intent = new Intent(Intent.ACTION_VIEW);  
					intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  
					intent.setDataAndType(Uri.fromFile(arg0.result),"application/vnd.android.package-archive");  
					//startActivity(intent);  
					startActivityForResult(intent, 0);
					android.os.Process.killProcess(android.os.Process.myPid());
				}
				@Override
				public void onFailure(HttpException arg0, String arg1) {
					Toast.makeText(NewSplashActivity.this, "下载失败!",
							Toast.LENGTH_SHORT).show();
				}
			});
			
		} else {
			Toast.makeText(NewSplashActivity.this, "没有sd卡", Toast.LENGTH_SHORT).show();
		}
	}
	
	public boolean isConnect(Context context) {
		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity != null) {
			NetworkInfo info = connectivity.getActiveNetworkInfo();
			if (info != null && info.isConnected()) {
				if (info.getState() == NetworkInfo.State.CONNECTED) {
					return true;
				}
			}
		}
		return false;
	}
	
//	@Override
//	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//		enterHome();
//		super.onActivityResult(requestCode, resultCode, data);
//	}
//
	
	public void enterHome(){
		post();
		/*Intent intent = new Intent(SplashActivity.this, MimiMainActivity.class);
		//intent.putExtra("ruId", ruId);
		startActivity(intent);
		finish();*/
	}

	public void post(){
//		System.out.println("显示序列号+++++++++++++++++++++++");
//		Thread t = new Thread(){
//
//			@Override
//			public void run() {
//				//1.创建客户端对象
//				HttpClient hc = new DefaultHttpClient();
//				//2.创建post请求对象
//				HttpPost hp = new HttpPost(Api.LOGIN_URL);
////				System.out.println("提交数据SerialNumber++++++++" + SerialNumber);
//
//				//封装form表单提交的数据
//				//L2504491N1K2
//				SerialNumber = "L2504491N1K2";
//				BasicNameValuePair bnvp = new BasicNameValuePair("SerialNumber", SerialNumber);
//				List<BasicNameValuePair> parameters = new ArrayList<BasicNameValuePair>();
//				//把BasicNameValuePair放入集合中
//				parameters.add(bnvp);
//
//				try {
//					//要提交的数据都已经在集合中了，把集合传给实体对象
//					UrlEncodedFormEntity entity = new UrlEncodedFormEntity(parameters, "utf-8");
//					//设置post请求对象的实体，其实就是把要提交的数据封装至post请求的输出流中
//					hp.setEntity(entity);
//					//3.使用客户端发送post请求
//					HttpResponse hr = hc.execute(hp);
//					int statusCode = hr.getStatusLine().getStatusCode();
//					if(statusCode == 200){
//						InputStream is = hr.getEntity().getContent();
//						String mJson = Utils.getTextFromStream(is);
////						System.out.println("mjson===================" + mJson);
//
//						JSONObject jo = new JSONObject(mJson);
//						//System.out.println("text0000000000" + mJson);
//
//						int mError = jo.getInt("error");
//						//int mUserId = jo.getInt("user_id");
//						//System.out.println( "mError="+ mError);
//
//						if (mError == 0) {
//							//System.out.println("登录失败");
//							Message msg = mHandler.obtainMessage();
//							msg.what = 6;
//							mHandler.sendMessage(msg);
//						}
//
//						Gson gson = new Gson();
//						InfoBean bean = gson.fromJson(mJson,InfoBean.class);
////						shopInfoList1 = bean.shopInfo;
//						shopInfoList1 = bean.getShopInfo();
//						//userInfoList.addAll(userInfoList1);
//						ruId = shopInfoList1.getRu_id();
//						shopTitle = shopInfoList1.getShop_name();
//						weichat_thumb = shopInfoList1.getWeichat_thumb();
//						//System.out.println("shopInfoList1+++++++++++++" + shopInfoList1);
//						System.out.println("ruId+++++++++++++" + ruId);
//
//						if(mError > 0){
//							Message msg = mHandler.obtainMessage();
//							msg.what = 5;
//							mHandler.sendMessage(msg);
//						}
//
//					}
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		};
//		t.start();
		OkHttpClient okHttpClient = new OkHttpClient();
		SerialNumber = "L2504491N1K2";
		RequestBody requestBody = new FormBody.Builder().add("SerialNumber", SerialNumber).build();
		Request request = new Request.Builder().url(Api.LOGIN_URL).post(requestBody).addHeader("SerialNumber", SerialNumber).build();
		okHttpClient.newCall(request).enqueue(new Callback() {
			@Override
			public void onFailure(Call call, IOException e) {

				Message msg = mHandler.obtainMessage();
				msg.what = 7;
				mHandler.sendMessage(msg);
			}

			@Override
			public void onResponse(Call call, Response response) throws IOException {
				if (response.isSuccessful()) {
					String mJson = response.body().string();
					Gson gson = new Gson();
					InfoBean bean = gson.fromJson(mJson,InfoBean.class);
//						shopInfoList1 = bean.shopInfo;
					int error = bean.getError();
					if (error != 1) {
						Message msg = mHandler.obtainMessage();
						msg.what = 6;
						mHandler.sendMessage(msg);
						return;
					}
					shopInfoList1 = bean.getShopInfo();
					//userInfoList.addAll(userInfoList1);
					ruId = shopInfoList1.getRu_id();
					shopTitle = shopInfoList1.getShop_name();
					weichat_thumb = shopInfoList1.getWeichat_thumb();

					Message msg = mHandler.obtainMessage();
					msg.what = 5;
					mHandler.sendMessage(msg);
				}
			}
		});
	}

	public String getMacAddress()
	{
		String mac = "";
		// 获取wifi管理器
		WifiManager wifiMng = (WifiManager) getSystemService(Context.WIFI_SERVICE);
		WifiInfo wifiInfor = wifiMng.getConnectionInfo();
		mac = wifiInfor.getMacAddress();
		Log.i("TAG", "--- DVB Mac Address : " + mac);
		return mac;
	}
	//JPush统计代码
	@Override
	protected void onResume() {
		super.onResume();
		JPushInterface.onResume(this);
	}

	@Override
	protected void onPause() {
		super.onPause();
		JPushInterface.onPause(this);
	}
	//back键实现home键效果
	@Override
	public void onBackPressed() {
		//实现Home键效果
		//super.onBackPressed();这句话一定要注掉,不然又去调用默认的back处理方式了
		Intent i= new Intent(Intent.ACTION_MAIN);
		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		i.addCategory(Intent.CATEGORY_HOME);
		startActivity(i);

	}
}



