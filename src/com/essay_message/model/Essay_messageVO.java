package com.essay_message.model;

import java.sql.*;

public class Essay_messageVO implements java.io.Serializable{
	private String esamsg_no;
	private String mem_no;
	private String esa_no;
	private String esa_rel;
	private String esamsg_detail;
	private Timestamp esamsg_date;
	public String getEsamsg_no() {
		return esamsg_no;
	}
	public void setEsamsg_no(String esamsg_no) {
		this.esamsg_no = esamsg_no;
	}
	public String getMem_no() {
		return mem_no;
	}
	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}
	public String getEsa_no() {
		return esa_no;
	}
	public void setEsa_no(String esa_no) {
		this.esa_no = esa_no;
	}
	public String getEsa_rel() {
		return esa_rel;
	}
	public void setEsa_rel(String esa_rel) {
		this.esa_rel = esa_rel;
	}
	public String getEsamsg_detail() {
		return esamsg_detail;
	}
	public void setEsamsg_detail(String esamsg_detail) {
		this.esamsg_detail = esamsg_detail;
	}
	public Timestamp getEsamsg_date() {
		return esamsg_date;
	}
	public void setEsamsg_date(Timestamp esamsg_date) {
		this.esamsg_date = esamsg_date;
	}
}
