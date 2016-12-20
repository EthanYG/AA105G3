package com.product.model;

import java.sql.Date;

public class ProductVO implements java.io.Serializable{
	private String prod_no;
	private String prod_name;
	private String prod_type;
	private Integer sales_volume;
	private Integer stor_capacity;
	private Integer unit_price;
	private String prod_description;
	private String prod_status;
	private String disc_status;
	private String sell_status;
	private byte[] prod_picture;
	private Date shelf_date;
	private Date remove_date;
	private Integer disc_price;
	private Date disc_start_date;
	private Date disc_end_date;
	
	public String getProd_no() {
		return prod_no;
	}
	public void setProd_no(String prod_no) {
		this.prod_no = prod_no;
	}
	public String getProd_name() {
		return prod_name;
	}
	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}
	public String getProd_type() {
		return prod_type;
	}
	public void setProd_type(String prod_type) {
		this.prod_type = prod_type;
	}
	public Integer getSales_volume() {
		return sales_volume;
	}
	public void setSales_volume(Integer sales_volume) {
		this.sales_volume = sales_volume;
	}
	public Integer getStor_capacity() {
		return stor_capacity;
	}
	public void setStor_capacity(Integer stor_capacity) {
		this.stor_capacity = stor_capacity;
	}
	public Integer getUnit_price() {
		return unit_price;
	}
	public void setUnit_price(Integer unit_price) {
		this.unit_price = unit_price;
	}
	public String getProd_description() {
		return prod_description;
	}
	public void setProd_description(String prod_description) {
		this.prod_description = prod_description;
	}
	public String getProd_status() {
		return prod_status;
	}
	public void setProd_status(String prod_status) {
		this.prod_status = prod_status;
	}
	public String getDisc_status() {
		return disc_status;
	}
	public void setDisc_status(String disc_status) {
		this.disc_status = disc_status;
	}
	public String getSell_status() {
		return sell_status;
	}
	public void setSell_status(String sell_status) {
		this.sell_status = sell_status;
	}
	public byte[] getProd_picture() {
		return prod_picture;
	}
	public void setProd_picture(byte[] prod_picture) {
		this.prod_picture = prod_picture;
	}
	public Date getShelf_date() {
		return shelf_date;
	}
	public void setShelf_date(Date shelf_date) {
		this.shelf_date = shelf_date;
	}
	public Date getRemove_date() {
		return remove_date;
	}
	public void setRemove_date(Date remove_date) {
		this.remove_date = remove_date;
	}
	public Integer getDisc_price() {
		return disc_price;
	}
	public void setDisc_price(Integer disc_price) {
		this.disc_price = disc_price;
	}
	public Date getDisc_start_date() {
		return disc_start_date;
	}
	public void setDisc_start_date(Date disc_start_date) {
		this.disc_start_date = disc_start_date;
	}
	public Date getDisc_end_date() {
		return disc_end_date;
	}
	public void setDisc_end_date(Date disc_end_date) {
		this.disc_end_date = disc_end_date;
	}

}
