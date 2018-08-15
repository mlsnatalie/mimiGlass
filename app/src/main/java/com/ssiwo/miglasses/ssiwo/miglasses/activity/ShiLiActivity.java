package com.ssiwo.miglasses.ssiwo.miglasses.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.meikoz.core.ui.SweetAlertDialog;
import com.ssiwo.miglasses.R;

public class ShiLiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shi_li);
        SweetAlertDialog.Builder builder = new SweetAlertDialog.Builder(this);
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("温馨提示");
        builder.setMessage("请与平板保持1m距离进行检测");
        builder.setCancelable(false);
        builder.setPositiveButton("确定", new SweetAlertDialog.OnDialogClickListener() {
            @Override
            public void onClick(Dialog dialog, int which) {

            }
        });
        builder.show();
    }
}
