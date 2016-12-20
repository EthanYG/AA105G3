package com.film.model;

import java.util.*;

public interface FilmDAO_interface {

	public void insert(FilmVO filmVO);

	public void update(FilmVO filmVO);

	public void delete(String film_no);

	public FilmVO findByPrimaryKey(String film_no);

	public List<FilmVO> getAll();

}
