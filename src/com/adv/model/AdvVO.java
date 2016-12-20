package com.adv.model;

import java.sql.Date;

public class AdvVO implements java.io.Serializable{
	private String adv_no;
	private String emp_no;
	private String adv_name;
	private String adv_image_name;
	private byte[] adv_image;
	private String adv_url;
	
	public String getAdv_no() {
		return adv_no;
	}
	public void setAdv_no(String adv_no) {
		this.adv_no = adv_no;
	}
	public String getEmp_no() {
		return emp_no;
	}
	public void setEmp_no(String emp_no) {
		this.emp_no = emp_no;
	}
	public String getAdv_name() {
		return adv_name;
	}
	public void setAdv_name(String adv_name) {
		this.adv_name = adv_name;
	}
	public String getAdv_image_name() {
		return adv_image_name;
	}
	public void setAdv_image_name(String adv_image_name) {
		this.adv_image_name = adv_image_name;
	}
	public byte[] getAdv_image() {
		return adv_image;
	}
	public void setAdv_image(byte[] adv_image) {
		this.adv_image = adv_image;
	}
	public String getAdv_url() {
		return adv_url;
	}
	public void setAdv_url(String adv_url) {
		this.adv_url = adv_url;
	}

}
