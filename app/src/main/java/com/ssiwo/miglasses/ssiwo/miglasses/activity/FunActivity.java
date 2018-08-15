package com.ssiwo.miglasses.ssiwo.miglasses.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.google.gson.Gson;
import com.lidroid.xutils.http.client.util.URLEncodedUtils;
import com.meikoz.core.ui.SweetAlertDialog;
import com.ssiwo.miglasses.R;
import com.ssiwo.miglasses.ssiwo.miglasses.adapter.CategroyAdapter;
import com.ssiwo.miglasses.ssiwo.miglasses.bean.DetailBean;
import com.ssiwo.miglasses.ssiwo.miglasses.bean.ListBean;
import com.ssiwo.miglasses.ssiwo.miglasses.constant.Api;
import com.ssiwo.miglasses.ssiwo.miglasses.download.FileDownloader;
import com.ssiwo.miglasses.ssiwo.miglasses.view.GifView;
import com.unity3d.player.UnityPlayer;
import com.xzimg.rigidfacetracking.RigidFaceTrackingPlugin;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by Ssiwo on 2016/11/8.
 */

public class FunActivity extends RigidFaceTrackingPlugin implements CategroyAdapter.OnMyItemClickListener {


    @Bind(R.id.ivTop)
    ImageView ivTop;
    @Bind(R.id.ivCenter)
    RelativeLayout ivCenter;
    @Bind(R.id.lv)
    RecyclerView lv;
    @Bind(R.id.UnityView)
    LinearLayout UnityView;
    @Bind(R.id.ivBottom)
    RelativeLayout ivBottom;
    @Bind(R.id.back)
    Button back;
    @Bind(R.id.ll_main)
    LinearLayout llMain;
    //    @Bind(R.id.vv)
//    MyVideoView vv;
    @Bind(R.id.gv_main)
    GifView gvMain;
    @Bind(R.id.srl)
    SwipeRefreshLayout srl;
    //    @Bind(R.id.back_main)
//    Button backMain;
    @Bind(R.id.fb)
    FloatingActionsMenu fb;
    @Bind(R.id.shop_title)
    TextView shopTitle;
    @Bind(R.id.iv_erweima)
    ImageView ivErweima;
//    @Bind(R.id.ll_vedio)
//    LinearLayout llVedio;


    private List<ListBean.DataBean> showItems = new ArrayList<>();
    private CategroyAdapter myAdapter;
    private OkHttpClient okHttpClient;
    private JSONObject realJson;
    private List<DetailBean.DataBean> myList = new ArrayList<>();
    private JSONObject jsonObject;
    private List<String> bundleUrl = new ArrayList<>();
    private List<String> bundleName = new ArrayList<>();
    private List<String> pictureUrl = new ArrayList<>();
    private List<String> pictureName = new ArrayList<>();
    private List<String> carId = new ArrayList<>();
    private ListBean listdata;
    private ArrayList<String> mShowItems = new ArrayList<>();
    private String ruId;
    private Gson gson;
    private boolean isFrist = true;
    private boolean isRefresg = false;
    private SweetAlertDialog.Builder builder;
    private View view;
    private String car_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mimiactivity);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initData() {
        if (isRefresg) {
            showItems.clear();
            mShowItems.clear();
        }
        okHttpClient = new OkHttpClient();
        String url = Api.CATEGORY_URL + ruId;
        Request request = new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {

                    final String json = response.body().string();
                    parzeData(json);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            if (showItems != null) {

                                myAdapter = new CategroyAdapter(showItems, FunActivity.this);
                                lv.setAdapter(myAdapter);
                                myAdapter.setOnMyItemClickListener(FunActivity.this);
                                download();
                                srl.setRefreshing(false);
                            }
                        }
                    });
                }

            }
        });
//        ruId = getIntent().getStringExtra("ruId");
//        listdata = (ListBean) getIntent().getSerializableExtra("listdata");
//        showitems = getIntent().getStringArrayListExtra("showitems");
//        if (listdata != null && listdata.getData().size() > 0) {
//            myAdapter = new CategroyAdapter(listdata.getData(), FunActivity.this);
//            lv.setAdapter(myAdapter);
//            myAdapter.setOnMyItemClickListener(FunActivity.this);
//        }
    }

    private void parzeData(String json) {
        gson = new Gson();
        ListBean listBean = gson.fromJson(json, ListBean.class);
        showItems.addAll(listBean.getData());
        for (int i = 0; i < showItems.size(); i++) {

            downData(showItems.get(i).getCat_id(), showItems.get(i).getCat_type());
            SystemClock.sleep(50);
        }

    }

    private void initView() {
        gvMain.setMovieResource(R.drawable.loadview);
//        vv.setVideoURI(Uri.parse("android.resource://" + this.getPackageName() + "/" + R.raw.logo));
//
//        vv.start();
//        view = mUnityPlayer.getView();
//        UnityView.addView(view);
//        UnityPlayer.UnitySendMessage("GetMessage", "ShowMessage", mShowItems.toString());

        back.setVisibility(back.GONE);
        FloatingActionButton quit = new FloatingActionButton(getBaseContext());
        quit.setSize(FloatingActionButton.SIZE_MINI);
        quit.setTitle("退出");
        quit.setImageResource(R.drawable.quit);
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
        FloatingActionButton actionC = new FloatingActionButton(getBaseContext());
        actionC.setSize(FloatingActionButton.SIZE_MINI);
        actionC.setTitle("测视力");
        actionC.setImageResource(R.drawable.eye);
        actionC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFrist) {
                    Toast.makeText(FunActivity.this, "测试视力前请先试戴一次", Toast.LENGTH_LONG).show();
                    fb.collapse();
                    return;
                }
                startActivity(new Intent(FunActivity.this, ShiLiActivity.class));
                fb.collapse();


            }
        });
        fb.addButton(quit);
        fb.addButton(actionC);
        lv.setLayoutManager(new GridLayoutManager(this, 3));

//        gvMain.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                llMain.setVisibility(View.GONE);
//                gvMain.setVisibility(View.GONE);
//                llVedio.setVisibility(View.GONE);
//                vv.setVisibility(View.GONE);
//                lv.setVisibility(lv.VISIBLE);
//                ivBottom.setVisibility(ivBottom.VISIBLE);
//                ivCenter.setVisibility(ivCenter.VISIBLE);
//                ivTop.setVisibility(ivTop.VISIBLE);
//                back.setVisibility(back.GONE);
//                UnityPlayer.UnitySendMessage("GetMessage", "ClearItem", "clear");
//            }
//        }, 50);
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                isRefresg = true;
                initData();
            }
        });
        ruId = getIntent().getStringExtra("ruId");
        String shopName = getIntent().getStringExtra("name");
        if (!TextUtils.isEmpty(shopName)) {

            shopTitle.setText(shopName);
        }
//        String erweima = getIntent().getStringExtra("erweima");

            ivErweima.setVisibility(View.VISIBLE);
            String encode = URLEncoder.encode(Api.ER_WEIMA + ruId);
            String string = Api.PAZER + encode;
            Glide.with(FunActivity.this).load(string).into(ivErweima);

    }

    @Override
    public void onMyItemClick(final int position) {
//        Request request = new Request.Builder().url("http://www.tryonmall.com/api/store/index.php?act=goods&cat_id=" + carId + "&suppId=8").build();
//        okHttpClient.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                JSONArray jsonArray = new JSONArray();
//                if (response.isSuccessful()) {
//                    realJson = new JSONObject();
//
//                    String json = response.body().string();
//                    Gson gson = new Gson();
//                    DetailBean detailBean = gson.fromJson(json, DetailBean.class);
//                    myList.clear();
//                    myList.addAll(detailBean.getData());
//                    if (detailBean != null && myList.size() > 0) {
//                        for (int i = 0; i < myList.size(); i++) {
//                            jsonObject = new JSONObject();
//                            String a_m_url = myList.get(i).getA_m_url();
//                            String goods_name = myList.get(i).getGoods_name();
//                            String m_img_url = myList.get(i).getM_img_url();
//                            String shop_price = myList.get(i).getShop_price();
//                            String goods_id = myList.get(i).getGoods_id();
//                            String[] m_url = a_m_url.split("/");
//                            String mm_url = m_url[3].substring(0, m_url[3].indexOf("."));
//                            String[] img = m_img_url.split("/");
//                            String img_url = img[2].substring(0, img[2].indexOf("."));
//                            String[] price = shop_price.split(">");
//                            bundleUrl.add(a_m_url);
//                            pictureUrl.add(m_img_url);
//                            bundleName.add(m_url[3]);
//                            pictureName.add(img[2]);
//                            try {
//                                jsonObject.put("assetbundleName", mm_url);
//                                jsonObject.put("glassesIConName", img_url);
//
//                                jsonObject.put("glassesName", goods_name);
//                                jsonObject.put("price", price[2]);
//                                jsonObject.put("goodId", goods_id);
//                                jsonArray.put(jsonObject);
//
//
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                        try {
//                            download();
//                            realJson.put("glassesInfo", jsonArray);
//                            realJson.put("version", 2);
//                            Thread.sleep(2000);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mShowItems.get(position).length() < 50) {
                    Toast.makeText(FunActivity.this, "暂无商品", Toast.LENGTH_SHORT).show();
                    return;
                }
                String replace = mShowItems.get(position).replace("镜架", "");
                UnityPlayer.UnitySendMessage("GetMessage", "ShowMessage", replace);


                llMain.setVisibility(View.VISIBLE);
                gvMain.setVisibility(View.VISIBLE);
                lv.setVisibility(lv.GONE);
                fb.setVisibility(View.GONE);
//                backMain.setVisibility(View.GONE);
                ivBottom.setVisibility(ivBottom.GONE);
                ivCenter.setVisibility(ivCenter.GONE);
                ivTop.setVisibility(ivTop.GONE);

                srl.setVisibility(View.GONE);
//                UnityView.setVisibility(View.VISIBLE);
                if (isFrist) {
                    view = mUnityPlayer.getView();
                    UnityView.addView(view);
                    gvMain.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            llMain.setVisibility(View.GONE);
                            gvMain.setVisibility(View.GONE);
                            back.setVisibility(back.VISIBLE);
                        }
                    }, 8000);
                    isFrist = false;
                } else {
                    gvMain.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            llMain.setVisibility(View.GONE);
                            gvMain.setVisibility(View.GONE);
                            back.setVisibility(back.VISIBLE);

                        }
                    }, 2000);

                }
            }
        });
//
//                    } else {
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                Toast.makeText(FunActivity.this, "暂无商品", Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                    }
//
//                }
//            }
//        });
    }


    private void download() {
        new Thread() {
            @Override
            public void run() {
                synchronized ("aaa") {
                    for (int i = 0; i < bundleUrl.size(); i++) {
                        String mBundleUrl = Api.BASE_URL + bundleUrl.get(i);
                        String mBundleName = bundleName.get(i);

                        int gg = new FileDownloader().downloadFile("android/data/com.ssiwo.miglasses/files/", mBundleName, mBundleUrl);
//                        System.out.println(gg + "");
                    }
                }
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                synchronized ("bbb") {
                    for (int i = 0; i < bundleUrl.size(); i++) {

                        String mPictureUrl = Api.BASE_URL + "/" + pictureUrl.get(i);
                        String mPictureName = pictureName.get(i);
                        int ll = new FileDownloader().downloadFile("android/data/com.ssiwo.miglasses/files/", mPictureName, mPictureUrl);
//                        System.out.println(ll + "");
                    }
                }
            }
        }.start();

    }


    private void downData(String cat_id, String cate_type) {

        String format = String.format(Api.CATEGORY_DETAIL, cat_id, ruId, cate_type);
        Request request = new Request.Builder().url(format).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (response.isSuccessful()) {
                    String mData = response.body().string();
                    DetailBean detailBean = gson.fromJson(mData, DetailBean.class);
                    myList.clear();
                    myList.addAll(detailBean.getData());
                    realJson = new JSONObject();
                    JSONArray jsonArray = new JSONArray();
                    for (int i = 0; i < myList.size(); i++) {
                        jsonObject = new JSONObject();
                        String a_m_url = myList.get(i).getA_m_url();
                        String goods_name = myList.get(i).getGoods_name();
                        String m_img_url = myList.get(i).getM_img_url();
                        String shop_price = myList.get(i).getShop_price();
                        String goods_id = myList.get(i).getGoods_id();
                        if (a_m_url == null) {
                            continue;
                        }
                        String[] m_url = a_m_url.split("/");
                        String mm_url = m_url[m_url.length - 1].substring(0, m_url[m_url.length - 1].indexOf("."));
                        String[] img = m_img_url.split("/");
                        String img_url = img[img.length - 1].substring(0, img[img.length - 1].indexOf("."));
                        String[] price = shop_price.split(">");
                        bundleUrl.add(a_m_url);
                        pictureUrl.add(m_img_url);
                        bundleName.add(m_url[m_url.length - 1]);
                        pictureName.add(img[img.length - 1]);

                        try {
                            jsonObject.put("assetbundleName", mm_url);
                            jsonObject.put("glassesIConName", img_url);

                            jsonObject.put("glassesName", goods_name);
                            jsonObject.put("price", price[2]);
                            jsonObject.put("goodId", goods_id);
                            jsonArray.put(jsonObject);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        realJson.put("glassesInfo", jsonArray);
                        realJson.put("version", 2);
                        mShowItems.add(realJson.toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }

        });

    }

    //返回按键
//    public void click1(View v) {
//        backMain.setVisibility(View.VISIBLE);
//        srl.setVisibility(View.VISIBLE);
//        lv.setVisibility(lv.VISIBLE);
//        ivBottom.setVisibility(ivBottom.VISIBLE);
//        ivCenter.setVisibility(ivCenter.VISIBLE);
//        ivTop.setVisibility(ivTop.VISIBLE);
//        back.setVisibility(back.GONE);
//        //handler.sendEmptyMessage(1);
//        UnityPlayer.UnitySendMessage("GetMessage", "ClearItem", "clear");//向uity中发送信息
//    }


    @OnClick({R.id.back, R.id.fb})
    public void onClick(View view) {
        switch (view.getId()) {
//            case R.id.back_main:
//                showDialog();
//                break;
            case R.id.back:
//                backMain.setVisibility(View.VISIBLE);
                srl.setVisibility(View.VISIBLE);
                lv.setVisibility(lv.VISIBLE);
                ivBottom.setVisibility(ivBottom.VISIBLE);
                ivCenter.setVisibility(ivCenter.VISIBLE);
                ivTop.setVisibility(ivTop.VISIBLE);
                back.setVisibility(back.GONE);
                fb.setVisibility(View.VISIBLE);
                //handler.sendEmptyMessage(1);
                UnityPlayer.UnitySendMessage("GetMessage", "ClearItem", "clear");//向uity中发送信息
                break;
            case R.id.fb:
//                fb.setVisibility(fb.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
                break;
        }

    }
    public void showBackPress(String info) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                back.setVisibility(View.VISIBLE);
            }
        });
    }

    public void hideBackPress(String info) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                back.setVisibility(View.INVISIBLE);
            }
        });
    }
    private void showDialog() {
        builder = new SweetAlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("您确定要退出吗");
        builder.setPositiveButton("确定", new SweetAlertDialog.OnDialogClickListener() {
            @Override
            public void onClick(Dialog dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton("取消", new SweetAlertDialog.OnDialogClickListener() {
            @Override
            public void onClick(Dialog dialog, int which) {
                fb.collapse();
            }
        });
        builder.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        llMain.setVisibility(View.GONE);
        gvMain.setVisibility(View.GONE);
    }
}

