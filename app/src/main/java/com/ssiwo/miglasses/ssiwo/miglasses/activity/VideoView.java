package com.ssiwo.miglasses.ssiwo.miglasses.activity;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;

import com.ssiwo.miglasses.R;
import com.ssiwo.miglasses.ssiwo.miglasses.view.MyVideoView;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by mls on 2016/11/9.
 */

public class VideoView extends Activity {

    private MyVideoView vv_videoview;
    private MediaController mController;
//    private ImageView btn_play;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.videoview_activity);

//        btn_play = (ImageView) findViewById(R.id.btn_play);
        vv_videoview = (MyVideoView) findViewById(R.id.vv_videoview);
        mController = new MediaController(this);

        vv_videoview.setVideoURI(Uri.parse("android.resource://"+this.getPackageName()+"/"+ R.raw.logo));
        vv_videoview.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Intent intent = new Intent(VideoView.this, NewSplashActivity.class);
                startActivity(intent);
                finish();
            }
        });
        vv_videoview.start();

//        btn_play.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(VideoView.this, NewSplashActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        });
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
}
