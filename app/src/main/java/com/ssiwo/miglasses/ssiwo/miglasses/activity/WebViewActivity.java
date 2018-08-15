package com.ssiwo.miglasses.ssiwo.miglasses.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ssiwo.miglasses.R;

/**
 * Created by mls on 2016/11/2.
 */
public class WebViewActivity  extends Activity{
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_activity);

        Intent intent = getIntent();//注意这个是getIntent()
        //获取数据
        String url = intent.getStringExtra("url");
        System.out.println("已经跳转到webview网页了");
        System.out.println("url+++++++++++++" + url);

        webView = (WebView) findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
    }
}
