package com.ssiwo.miglasses.ssiwo.miglasses.bean;

public class NewsData4 {
	
	public ShopInfo shopInfo;  
    public String error;  
    public String message; 
    
    @Override
	public String toString() {
		return "NewsData4 [shopInfo=" + shopInfo + "]";
	}
    
    
    public class ShopInfo{
    	public String ru_id;

		public String getRu_id() {
			return ru_id;
		}

		public void setRu_id(String ru_id) {
			this.ru_id = ru_id;
		}

		@Override
		public String toString() {
			return "ShopInfo [ru_id=" + ru_id + "]";
		}
    	
		

        
    }

}
