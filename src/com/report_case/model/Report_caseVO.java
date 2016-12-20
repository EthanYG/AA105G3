package com.report_case.model;

//import java.sql.Timestamp;
import java.sql.Date;




public class Report_caseVO {

	private String rep_no;
	private String rep_mem_no;
	private String rep_tar_no;
	private String rep_type;
	private String rep_reason;
	private Date rep_date;
	private String rep_chk_con;
	
	
	public String getRep_no() {
		return rep_no;
	}
	public void setRep_no(String rep_no) {
		this.rep_no = rep_no;
	}
	public String getRep_mem_no() {
		return rep_mem_no;
	}
	public void setRep_mem_no(String rep_mem_no) {
		this.rep_mem_no = rep_mem_no;
	}
	public String getRep_tar_no() {
		return rep_tar_no;
	}
	public void setRep_tar_no(String rep_tar_no) {
		this.rep_tar_no = rep_tar_no;
	}
	public String getRep_type() {
		return rep_type;
	}
	public void setRep_type(String rep_type) {
		this.rep_type = rep_type;
	}
	public String getRep_reason() {
		return rep_reason;
	}
	public void setRep_reason(String rep_reason) {
		this.rep_reason = rep_reason;
	}
	public Date getRep_date() {
		return rep_date;
	}
	public void setRep_date(Date rep_date) {
		this.rep_date = rep_date;
	}
	public String getRep_chk_con() {
		return rep_chk_con;
	}
	public void setRep_chk_con(String rep_chk_con) {
		this.rep_chk_con = rep_chk_con;
	}

	
	
	

}
