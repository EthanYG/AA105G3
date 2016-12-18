package com.essay.model;

import java.sql.*;

public class EssayVO implements java.io.Serializable{
	private String esa_no;
	private String mem_no;
	private String esa_detail;
	private String esa_title;
	private Timestamp esa_date;
	private Integer esa_pop;
	
	public String getEsa_no() {
		return esa_no;
	}
	public void setEsa_no(String esa_no) {
		this.esa_no = esa_no;
	}
	public String getMem_no() {
		return mem_no;
	}
	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}
	public String getEsa_detail() {
		return esa_detail;
	}
	public void setEsa_detail(String esa_detail) {
		this.esa_detail = esa_detail;
	}
	public String getEsa_title() {
		return esa_title;
	}
	public void setEsa_title(String esa_title) {
		this.esa_title = esa_title;
	}
	public Timestamp getEsa_date() {
		return esa_date;
	}
	public void setEsa_date(Timestamp esa_date) {
		this.esa_date = esa_date;
	}
	public Integer getEsa_pop() {
		return esa_pop;
	}
	public void setEsa_pop(Integer esa_pop) {
		this.esa_pop = esa_pop;
	}
}
