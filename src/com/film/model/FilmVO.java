package com.film.model;

import java.sql.Date;

public class FilmVO  implements java.io.Serializable {
	private String film_no;
	private String mem_no;
	private String film_title;
	private String film_content;
	private Date film_date;
	private byte[] film_file;
	private Integer film_like;
	private Integer film_popular;

	public String getFilm_no() {
		return film_no;
	}

	public void setFilm_no(String film_no) {
		this.film_no = film_no;
	}

	public String getMem_no() {
		return mem_no;
	}

	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}

	public String getFilm_title() {
		return film_title;
	}

	public void setFilm_title(String film_title) {
		this.film_title = film_title;
	}

	public String getFilm_content() {
		return film_content;
	}

	public void setFilm_content(String film_content) {
		this.film_content = film_content;
	}

	public Date getFilm_date() {
		return film_date;
	}

	public void setFilm_date(Date film_date) {
		this.film_date = film_date;
	}

	public byte[] getFilm_file() {
		return film_file;
	}

	public void setFilm_file(byte[] film_file) {
		this.film_file = film_file;
	}

	public Integer getFilm_like() {
		return film_like;
	}

	public void setFilm_like(Integer film_like) {
		this.film_like = film_like;
	}

	public Integer getFilm_popular() {
		return film_popular;
	}

	public void setFilm_popular(Integer film_popular) {
		this.film_popular = film_popular;
	}

}
