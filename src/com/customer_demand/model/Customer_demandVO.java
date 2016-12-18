package com.customer_demand.model;

import java.sql.*;

public class Customer_demandVO implements java.io.Serializable {
	private String cusde_no;
	private String mem_no;
	private String cusde_detail;
	private Timestamp cusde_date;
	private Timestamp cusde_create_date;
	private String cusde_title;
	
	public String getCusde_no() {
		return cusde_no;
	}
	public void setCusde_no(String cusde_no) {
		this.cusde_no = cusde_no;
	}
	public String getMem_no() {
		return mem_no;
	}
	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}
	public String getCusde_detail() {
		return cusde_detail;
	}
	public void setCusde_detail(String cusde_detail) {
		this.cusde_detail = cusde_detail;
	}
	public Timestamp getCusde_date() {
		return cusde_date;
	}
	public void setCusde_date(Timestamp cusde_date) {
		this.cusde_date = cusde_date;
	}
	public Timestamp getCusde_create_date() {
		return cusde_create_date;
	}
	public void setCusde_create_date(Timestamp cusde_create_date) {
		this.cusde_create_date = cusde_create_date;
	}
	public String getCusde_title() {
		return cusde_title;
	}
	public void setCusde_title(String cusde_title) {
		this.cusde_title = cusde_title;
	}

}
