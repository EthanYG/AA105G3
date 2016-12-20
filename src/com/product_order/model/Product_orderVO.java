package com.product_order.model;

import java.sql.Date;

public class Product_orderVO implements java.io.Serializable{
	private String prod_ord_no;
	private String mem_no;
	private Date prod_ord_time;
	private String cred_card_no;
	private Date valid_date;
	private String valid_no;
	private String cred_card_type;
	private Integer total_money;
	private String ship_name;
	private String post_code;
	private String mem_adrs;
	private String cell_phone;
	private String tel_phone;
	
	public String getProd_ord_no() {
		return prod_ord_no;
	}
	public void setProd_ord_no(String prod_ord_no) {
		this.prod_ord_no = prod_ord_no;
	}
	public String getMem_no() {
		return mem_no;
	}
	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}
	public Date getProd_ord_time() {
		return prod_ord_time;
	}
	public void setProd_ord_time(Date prod_ord_time) {
		this.prod_ord_time = prod_ord_time;
	}
	public String getCred_card_no() {
		return cred_card_no;
	}
	public void setCred_card_no(String cred_card_no) {
		this.cred_card_no = cred_card_no;
	}
	public Date getValid_date() {
		return valid_date;
	}
	public void setValid_date(Date valid_date) {
		this.valid_date = valid_date;
	}
	public String getValid_no() {
		return valid_no;
	}
	public void setValid_no(String valid_no) {
		this.valid_no = valid_no;
	}
	public String getCred_card_type() {
		return cred_card_type;
	}
	public void setCred_card_type(String cred_card_type) {
		this.cred_card_type = cred_card_type;
	}
	public Integer getTotal_money() {
		return total_money;
	}
	public void setTotal_money(Integer total_money) {
		this.total_money = total_money;
	}
	public String getShip_name() {
		return ship_name;
	}
	public void setShip_name(String ship_name) {
		this.ship_name = ship_name;
	}
	public String getPost_code() {
		return post_code;
	}
	public void setPost_code(String post_code) {
		this.post_code = post_code;
	}
	public String getMem_adrs() {
		return mem_adrs;
	}
	public void setMem_adrs(String mem_adrs) {
		this.mem_adrs = mem_adrs;
	}
	public String getCell_phone() {
		return cell_phone;
	}
	public void setCell_phone(String cell_phone) {
		this.cell_phone = cell_phone;
	}
	public String getTel_phone() {
		return tel_phone;
	}
	public void setTel_phone(String tel_phone) {
		this.tel_phone = tel_phone;
	}

}
