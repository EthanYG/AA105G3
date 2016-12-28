package com.message.model;

import java.sql.Date;

public class MessageVO {
	private String msg_no;
	private String mem_no;
	private String msg_mem_no;
	private String msg_rel;
	private String msg_detail;
	private Date msg_date;
	
	public String getMsg_no()
	{
		return msg_no;
	}
	public void setMsg_no(String msg_no)
	{
		this.msg_no = msg_no;
	}
	public String getMem_no()
	{
		return mem_no;
	}
	public void setMem_no(String mem_no)
	{
		this.mem_no = mem_no;
	}
	public String getMsg_mem_no()
	{
		return msg_mem_no;
	}
	public void setMsg_mem_no(String msg_mem_no)
	{
		this.msg_mem_no = msg_mem_no;
	}
	public String getMsg_rel()
	{
		return msg_rel;
	}
	public void setMsg_rel(String msg_rel)
	{
		this.msg_rel = msg_rel;
	}
	public String getMsg_detail()
	{
		return msg_detail;
	}
	public void setMsg_detail(String msg_detail)
	{
		this.msg_detail = msg_detail;
	}
	public Date getMsg_date()
	{
		return msg_date;
	}
	public void setMsg_date(Date msg_date)
	{
		this.msg_date = msg_date;
	}

	

}
