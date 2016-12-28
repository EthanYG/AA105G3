package com.live.model;

public class LiveVO  implements java.io.Serializable{
	private String mem_no;
	private String live_name;
	private String live_intro;
	private byte[] live_pic;
	private Integer live_counts;
	private Integer live_follow;

	private String live_status;

	

	public String getMem_no() {
		return mem_no;
	}

	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}

	public String getLive_name() {
		return live_name;
	}

	public void setLive_name(String live_name) {
		this.live_name = live_name;
	}

	public String getLive_intro() {
		return live_intro;
	}

	public void setLive_intro(String live_intro) {
		this.live_intro = live_intro;
	}

	public byte[] getLive_pic() {
		return live_pic;
	}

	public void setLive_pic(byte[] live_pic) {
		this.live_pic = live_pic;
	}

	public Integer getLive_counts() {
		return live_counts;
	}

	public void setLive_counts(Integer live_counts) {
		this.live_counts = live_counts;
	}

	public Integer getLive_follow() {
		return live_follow;
	}

	public void setLive_follow(Integer live_follow) {
		this.live_follow = live_follow;
	}


	public String getLive_status() {
		return live_status;
	}

	public void setLive_status(String live_status) {
		this.live_status = live_status;
	}

}
