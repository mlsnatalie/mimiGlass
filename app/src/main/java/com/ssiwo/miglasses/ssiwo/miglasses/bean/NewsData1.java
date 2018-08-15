package com.ssiwo.miglasses.ssiwo.miglasses.bean;

import java.util.List;

public class NewsData1 {
	
	public List<Data> data;
	public int retcode;
	
	@Override
	public String toString() {
		return "NewsData1 [data=" + data + "]";
	}

	public class Data{
		public String cat_id;
		public String cat_name;
		public String keywords;
		public String desc;
		public String parent_id;
		public String sort_order;
		public String template_file;
		public String measure_unit;
		public String show_in_nav;
		public String style;
		public String is_show;
		public String grade;
		public String filter_attr;
		public String supplier_id;
		public String is_show_cat_pic;
		public String cat_pic;
		public String cat_pic_url;
		public String cat_goods_limit;
		
		
		@Override
		public String toString() {
			return "Data [cat_id=" + cat_id + ", cat_name=" + cat_name
					+ ", keywords=" + keywords + ", desc=" + desc
					+ ", parent_id=" + parent_id + ", sort_order=" + sort_order
					+ ", template_file=" + template_file + ", measure_unit="
					+ measure_unit + ", show_in_nav=" + show_in_nav
					+ ", style=" + style + ", is_show=" + is_show + ", grade="
					+ grade + ", filter_attr=" + filter_attr + ", supplier_id="
					+ supplier_id + ", is_show_cat_pic=" + is_show_cat_pic
					+ ", cat_pic=" + cat_pic + ", cat_pic_url=" + cat_pic_url
					+ ", cat_goods_limit=" + cat_goods_limit + "]";
		}
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
		public String getKeywords() {
			return keywords;
		}
		public void setKeywords(String keywords) {
			this.keywords = keywords;
		}
		public String getDesc() {
			return desc;
		}
		public void setDesc(String desc) {
			this.desc = desc;
		}
		public String getParent_id() {
			return parent_id;
		}
		public void setParent_id(String parent_id) {
			this.parent_id = parent_id;
		}
		public String getSort_order() {
			return sort_order;
		}
		public void setSort_order(String sort_order) {
			this.sort_order = sort_order;
		}
		public String getTemplate_file() {
			return template_file;
		}
		public void setTemplate_file(String template_file) {
			this.template_file = template_file;
		}
		public String getMeasure_unit() {
			return measure_unit;
		}
		public void setMeasure_unit(String measure_unit) {
			this.measure_unit = measure_unit;
		}
		public String getShow_in_nav() {
			return show_in_nav;
		}
		public void setShow_in_nav(String show_in_nav) {
			this.show_in_nav = show_in_nav;
		}
		public String getStyle() {
			return style;
		}
		public void setStyle(String style) {
			this.style = style;
		}
		public String getIs_show() {
			return is_show;
		}
		public void setIs_show(String is_show) {
			this.is_show = is_show;
		}
		public String getGrade() {
			return grade;
		}
		public void setGrade(String grade) {
			this.grade = grade;
		}
		public String getFilter_attr() {
			return filter_attr;
		}
		public void setFilter_attr(String filter_attr) {
			this.filter_attr = filter_attr;
		}
		public String getSupplier_id() {
			return supplier_id;
		}
		public void setSupplier_id(String supplier_id) {
			this.supplier_id = supplier_id;
		}
		public String getIs_show_cat_pic() {
			return is_show_cat_pic;
		}
		public void setIs_show_cat_pic(String is_show_cat_pic) {
			this.is_show_cat_pic = is_show_cat_pic;
		}
		public String getCat_pic() {
			return cat_pic;
		}
		public void setCat_pic(String cat_pic) {
			this.cat_pic = cat_pic;
		}
		public String getCat_pic_url() {
			return cat_pic_url;
		}
		public void setCat_pic_url(String cat_pic_url) {
			this.cat_pic_url = cat_pic_url;
		}
		public String getCat_goods_limit() {
			return cat_goods_limit;
		}
		public void setCat_goods_limit(String cat_goods_limit) {
			this.cat_goods_limit = cat_goods_limit;
		}
		
		
		
	}
}

