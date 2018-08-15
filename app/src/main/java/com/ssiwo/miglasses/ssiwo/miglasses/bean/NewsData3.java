package com.ssiwo.miglasses.ssiwo.miglasses.bean;

import java.util.List;

public class NewsData3 {

	
public List<Data> data;
	
	@Override
	public String toString() {
		return "NewsData1 [data=" + data + "]";
	}

	public class Data{
		public String cat_name;
		public String cate_icon;
		public String cat_id;
		
		
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


		public String getCat_id() {
			return cat_id;
		}


		public void setCat_id(String cat_id) {
			this.cat_id = cat_id;
		}


		@Override
		public String toString() {
			return "Data [cat_name=" + cat_name + ", cate_icon=" + cate_icon
					+ ", cat_id=" + cat_id + "]";
		}
		
		
		
		
		
	}
	
}
