package com.adv.model;

import java.util.List;

public class AdvService {
	
	private AdvDAO_interface dao;

	public AdvService() {
		dao = new AdvJNDIDAO();
	}
	
	public AdvVO addAdv(String emp_no, String adv_name, String adv_image_name, byte[] adv_image, String adv_url){
		
		AdvVO advVO = new AdvVO();
		
		advVO.setEmp_no(emp_no);
		advVO.setAdv_name(adv_name);
		advVO.setAdv_image_name(adv_image_name);
		advVO.setAdv_image(adv_image);
		advVO.setAdv_url(adv_url);
		dao.insert(advVO);
		
		return advVO;
	}
	
	public AdvVO updateAdv(String adv_no, String emp_no, String adv_name, String adv_image_name, byte[] adv_image, String adv_url){
		
		AdvVO advVO = new AdvVO();
		
		advVO.setAdv_no(adv_no);
		advVO.setEmp_no(emp_no);
		advVO.setAdv_name(adv_name);
		advVO.setAdv_image_name(adv_image_name);
		advVO.setAdv_image(adv_image);
		advVO.setAdv_url(adv_url);
		dao.update(advVO);
		
		return advVO;
	}
	
	public void deleteAdv(String adv_no) {
		dao.delete(adv_no);
	}

	public AdvVO getOneAdv(String adv_no) {
		return dao.findByPrimaryKey(adv_no);
	}

	public List<AdvVO> getAll() {
		return dao.getAll();
	}

}
