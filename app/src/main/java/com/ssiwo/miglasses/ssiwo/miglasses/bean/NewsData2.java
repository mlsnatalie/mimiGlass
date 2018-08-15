package com.ssiwo.miglasses.ssiwo.miglasses.bean;

import java.util.List;

public class NewsData2 {
	
	public List<Data> data;
	public String message;
	public String status;
	
	@Override
	public String toString() {
		return "NewsData2 [data=" + data + ", message=" + message + ", status="
				+ status + "]";
	}
	
	public class Status{
		
	}
		
	public class Data{
			
			public String a_m_url;
			public String goods_id;
			public String goods_img;
			public String goods_name;
			public String m_img_url;
			public String market_price;
			public String name;
			public String shop_price;
			public String url;
			
			@Override
			public String toString() {
				return "Data [a_m_url=" + a_m_url + ", goods_id=" + goods_id
						+ ", goods_img=" + goods_img + ", goods_name=" + goods_name
						+ ", m_img_url=" + m_img_url + ", market_price="
						+ market_price + ", name=" + name + ", shop_price="
						+ shop_price + ", url=" + url + "]";
			}
			public String getA_m_url() {
				return a_m_url;
			}
			public void setA_m_url(String a_m_url) {
				this.a_m_url = a_m_url;
			}
			public String getGoods_id() {
				return goods_id;
			}
			public void setGoods_id(String goods_id) {
				this.goods_id = goods_id;
			}
			public String getGoods_img() {
				return goods_img;
			}
			public void setGoods_img(String goods_img) {
				this.goods_img = goods_img;
			}
			public String getGoods_name() {
				return goods_name;
			}
			public void setGoods_name(String goods_name) {
				this.goods_name = goods_name;
			}
			public String getM_img_url() {
				return m_img_url;
			}
			public void setM_img_url(String m_img_url) {
				this.m_img_url = m_img_url;
			}
			public String getMarket_price() {
				return market_price;
			}
			public void setMarket_price(String market_price) {
				this.market_price = market_price;
			}
			public String getName() {
				return name;
			}
			public void setName(String name) {
				this.name = name;
			}
			public String getShop_price() {
				return shop_price;
			}
			public void setShop_price(String shop_price) {
				this.shop_price = shop_price;
			}
			public String getUrl() {
				return url;
			}
			public void setUrl(String url) {
				this.url = url;
			}
			
		}
		
	
}
