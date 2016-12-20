package com.film.model;
import java.util.List;


public class FilmService {

	private FilmDAO_interface dao;

	public FilmService() {
		dao = new FilmJDBCDAO();
	}

	public FilmVO addFilm(String film_no, String mem_no,String film_title,String film_content, java.sql.Date film_date ,Integer film_like,Integer film_popular)
	{
	
		FilmVO FilmVO = new FilmVO();

		FilmVO.setFilm_no(film_no);
		FilmVO.setMem_no(mem_no);
		FilmVO.setFilm_title(film_title);
		FilmVO.setFilm_content(film_content);
		FilmVO.setFilm_date(film_date);
		FilmVO.setFilm_like(film_like);
		FilmVO.setFilm_popular(film_popular);
		dao.insert(FilmVO);

		return FilmVO;
	}

	public FilmVO updateFilm(String film_no,String mem_no,String film_title,String film_content,
				java.sql.Date film_date,Integer film_like,Integer film_popular) {

		FilmVO FilmVO = new FilmVO();
		
		
		
		FilmVO.setFilm_no(film_no);
		FilmVO.setMem_no(mem_no);
		FilmVO.setFilm_title(film_title);
		FilmVO.setFilm_content(film_content);
		FilmVO.setFilm_date(film_date);
//		FilmVO.setFilm_file(film_file);
		FilmVO.setFilm_like(film_like);
		FilmVO.setFilm_popular(film_popular);
		
		dao.update(FilmVO);

		return FilmVO;
	}

	public void deleteFilm(String film_no) {
		dao.delete(film_no);
	}

	public FilmVO getOneFilm(String film_no) {
		return dao.findByPrimaryKey(film_no);
	}

	public List<FilmVO> getAll() {
		return dao.getAll();
	}
}
