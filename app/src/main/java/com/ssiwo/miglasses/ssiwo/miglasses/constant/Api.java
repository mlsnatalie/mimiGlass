package com.ssiwo.miglasses.ssiwo.miglasses.constant;

public interface Api {


    int suppid = 41;
    //String url = "http://wx.faceme.me/api/store/index.php?action=category&supplier_id=12";
    //String url = "http://192.168.30.140/mJson.json";
    //String url1 = "http://192.168.30.142:8080/TestJson.json";
    //String url2 = "http://192.168.30.142:8080/goods.json";
    //String url1 = "http://192.168.30.142:8080/mJson.json";


    //		String url1 = "http://m.faceme.me/api/store/index.php?action=goods&suppId="+suppid+"&cat_id=173&cate_type=store";
//		String url2 = "http://m.faceme.me/api/store/index.php?action=goods&suppId="+suppid+"&cat_id=174&cate_type=store";
//		String url3 = "http://m.faceme.me/api/store/index.php?action=goods&suppId="+suppid+"&cat_id=175&cate_type=store";
//		String url4 = "http://m.faceme.me/api/store/index.php?action=goods&suppId="+suppid+"&cat_id=1&cate_type=system";
//		String url5 = "http://m.faceme.me/api/store/index.php?action=goods&suppId="+suppid+"&cat_id=2&cate_type=system";
//		String url6 = "http://m.faceme.me/api/store/index.php?action=goods&suppId="+suppid+"&cat_id=3&cate_type=system";
//		String url7 = "http://m.faceme.me/api/store/index.php?action=goods&suppId="+suppid+"&cat_id=4&cate_type=system";
//		String url8 = "http://m.faceme.me/api/store/index.php?action=goods&suppId="+suppid+"&cat_id=5&cate_type=system";

    String BASE_URL = "http://www.tryonmall.com";
//    String BASE_URL = "http://192.168.30.26:8083";
    //版本号url
    String VISION_URL = "http://api.faceme.me/api/glasses/version/index?appId=1001";
    //测试版本号url
    String VISION_test_URL = "http://api.faceme.me/api/glasses/version/index?appId=test";
    //登陆url
    String LOGIN_URL = BASE_URL + "/api/store/author.php?act=mLogin";
    //分类url
    String CATEGORY_URL = BASE_URL + "/api/store/index.php?act=category&supplier_id=";
    //分类详情url
    String CATEGORY_DETAIL = BASE_URL+ "/api/store/index.php?act=goods&cat_id=%s&suppId=%s&cate_type=%s";

    //二维码
    String ER_WEIMA = "http://www.tryonmall.com/mobile/index.php?r=store/index/shop_info&id=";

    //生成二维码
    String PAZER = "http://qr.topscan.com/api.php?bg=f3f3f3&fg=ff0000&gc=222222&el=l&w=200&m=10&text=";
   // String url9 = "http://m.faceme.me/api/store/index.php?action=category&supplier_id=" + suppid;
    //String url9 = "http://192.168.30.140/newsData.json";
    //String url9 = "http://192.168.30.142:8080/newsData.json";

}
