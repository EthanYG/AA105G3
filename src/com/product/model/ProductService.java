package com.product.model;

import java.util.List;

public class ProductService {
	
	private ProductDAO_interface dao;

	public ProductService() {
		dao = new ProductJNDIDAO();
	}
	
	public ProductVO addProduct(String prod_name, String prod_type, Integer sales_volume, Integer stor_capacity, 
			Integer unit_price, String prod_description, String prod_status, String disc_status, String sell_status, 
			byte[] prod_picture, java.sql.Date shelf_date, java.sql.Date remove_date, Integer disc_price,
			java.sql.Date disc_start_date, java.sql.Date disc_end_date){
		
		ProductVO productVO = new ProductVO();
		
		productVO.setProd_name(prod_name);
		productVO.setProd_type(prod_type);
		productVO.setSales_volume(sales_volume);
		productVO.setStor_capacity(stor_capacity);
		productVO.setUnit_price(unit_price);
		productVO.setProd_description(prod_description);
		productVO.setProd_status(prod_status);
		productVO.setDisc_status(disc_status);
		productVO.setSell_status(sell_status);
		productVO.setProd_picture(prod_picture);
		productVO.setShelf_date(shelf_date);
		productVO.setRemove_date(remove_date);
		productVO.setDisc_price(disc_price);
		productVO.setDisc_start_date(disc_start_date);
		productVO.setDisc_end_date(disc_end_date);
		dao.insert(productVO);
		
		return productVO;
	}
	
	public ProductVO updateProduct(String prod_no, String prod_name, String prod_type, Integer sales_volume, 
			Integer stor_capacity, Integer unit_price, String prod_description, String prod_status, String disc_status, 
			String sell_status, byte[] prod_picture, java.sql.Date shelf_date, java.sql.Date remove_date, Integer disc_price, 
			java.sql.Date disc_start_date, java.sql.Date disc_end_date){
		
		ProductVO productVO = new ProductVO();
		
		productVO.setProd_no(prod_no);
		productVO.setProd_name(prod_name);
		productVO.setProd_type(prod_type);
		productVO.setSales_volume(sales_volume);
		productVO.setStor_capacity(stor_capacity);
		productVO.setUnit_price(unit_price);
		productVO.setProd_description(prod_description);
		productVO.setProd_status(prod_status);
		productVO.setDisc_status(disc_status);
		productVO.setSell_status(sell_status);
		productVO.setProd_picture(prod_picture);
		productVO.setShelf_date(shelf_date);
		productVO.setRemove_date(remove_date);
		productVO.setDisc_price(disc_price);
		productVO.setDisc_start_date(disc_start_date);
		productVO.setDisc_end_date(disc_end_date);
		dao.update(productVO);
		
		return productVO;
	}
	
	public ProductVO getOneProduct(String prod_no) {
		return dao.findByPrimaryKey(prod_no);
	}

	public List<ProductVO> getAll() {
		return dao.getAll();
	}

}
