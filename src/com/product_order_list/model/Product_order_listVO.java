package com.product_order_list.model;

import java.sql.Date;

public class Product_order_listVO {
	private String prod_ord_no;
	private String prod_no;
	private Integer unit_price;
	private Integer prod_quantity;
	private String deli_status;
	private Date deli_time;
	
	public String getProd_ord_no() {
		return prod_ord_no;
	}
	public void setProd_ord_no(String prod_ord_no) {
		this.prod_ord_no = prod_ord_no;
	}
	public String getProd_no() {
		return prod_no;
	}
	public void setProd_no(String prod_no) {
		this.prod_no = prod_no;
	}
	public Integer getUnit_price() {
		return unit_price;
	}
	public void setUnit_price(Integer unit_price) {
		this.unit_price = unit_price;
	}
	public Integer getProd_quantity() {
		return prod_quantity;
	}
	public void setProd_quantity(Integer prod_quantity) {
		this.prod_quantity = prod_quantity;
	}
	public String getDeli_status() {
		return deli_status;
	}
	public void setDeli_status(String deli_status) {
		this.deli_status = deli_status;
	}
	public Date getDeli_time() {
		return deli_time;
	}
	public void setDeli_time(Date deli_time) {
		this.deli_time = deli_time;
	}

}
