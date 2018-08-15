package com.ssiwo.miglasses.ssiwo.miglasses.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Ssiwo on 2016/11/8.
 */

public class ListBean implements Serializable{

    /**
     * result : true
     * data : [{"cat_id":"1","cat_name":"[精选]男款眼镜","cate_icon":"/images/cat_icon/14779861761405.png","cat_type":"system"},{"cat_id":"2","cat_name":"[精选]功能眼镜","cate_icon":"/images/cat_icon/14779864032951.png","cat_type":"system"},{"cat_id":"4","cat_name":"[精选]女款眼镜","cate_icon":"/images/cat_icon/14779862935968.png","cat_type":"system"},{"cat_id":"5","cat_name":"[精选]儿童眼镜","cate_icon":"/images/cat_icon/14779864337807.png","cat_type":"system"},{"cat_id":"6","cat_name":"[精选]太阳镜","cate_icon":"/images/cat_icon/14779863715201.png","cat_type":"system"}]
     */

    private String result;
    /**
     * cat_id : 1
     * cat_name : [精选]男款眼镜
     * cate_icon : /images/cat_icon/14779861761405.png
     * cat_type : system
     */

    private List<DataBean> data;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        private String cat_id;
        private String cat_name;
        private String cate_icon;
        private String cat_type;

        public String getCat_id() {
            return cat_id;
        }

        public void setCat_id(String cat_id) {
            this.cat_id = cat_id;
        }

        public String getCat_name() {
            return cat_name;
        }

        public void setCat_name(String cat_name) {
            this.cat_name = cat_name;
        }

        public String getCate_icon() {
            return cate_icon;
        }

        public void setCate_icon(String cate_icon) {
            this.cate_icon = cate_icon;
        }

        public String getCat_type() {
            return cat_type;
        }

        public void setCat_type(String cat_type) {
            this.cat_type = cat_type;
        }
    }
}
