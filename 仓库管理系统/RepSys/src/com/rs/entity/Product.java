package com.rs.entity;


import com.rs.utils.StringUtil;

public class Product {

	private String name;                //货品名
	private String productId;           //货品号
	private Integer count;              //库存
	private String inTime;                //进货日期
	private Double price;               //货品单价
	private String manufacturer;        //生产厂家
	private String supplier;            //供应商

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getInTime() {
		return inTime;
	}
	public void setInTime(String inTime) {
		this.inTime = inTime;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public Product(String name, String productId, Integer count, String inTime, Double price, String manufacturer,
			String supplier) {
		super();
		this.name = name;
		this.productId = productId;
		this.count = count;
		this.inTime = inTime;
		this.price = price;
		this.manufacturer = manufacturer;
		this.supplier = supplier;
	}
	public Product() {
		super();
	}
	@Override
	public String toString() {
		return "[name=" + name + ",productId=" + productId + ",count=" + count + ",inTime=" + inTime
				+ ",price=" + price + ",manufacturer=" + manufacturer + ",supplier=" + supplier + "]";
	}

	public String myToString() {
		return name + "<_>" + productId + "<_>" + count + "<_>" + inTime
				+ "<_>" + price + "<_>" + manufacturer + "<_>" + supplier;
	}	
	
	
	
	
	
	
	
}
