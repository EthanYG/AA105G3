package com.product_order_list.model;

import java.util.List;

public class Product_order_listService {
	
	private Product_order_listDAO_interface dao;

	public Product_order_listService() {
		dao = new Product_order_listJNDIDAO();
	}
	
	public Product_order_listVO addProduct_order_list(String prod_ord_no, String prod_no, Integer unit_price, Integer prod_quantity, String deli_status, java.sql.Date deli_time){
		
		Product_order_listVO product_order_listVO = new Product_order_listVO();
		
		product_order_listVO.setProd_ord_no(prod_ord_no);
		product_order_listVO.setProd_no(prod_no);
		product_order_listVO.setUnit_price(unit_price);
		product_order_listVO.setProd_quantity(prod_quantity);
		product_order_listVO.setDeli_status(deli_status);
		product_order_listVO.setDeli_time(deli_time);
		dao.insert(product_order_listVO);
		
		return product_order_listVO;
	}
	
	public Product_order_listVO updateProduct_order_list(String prod_ord_no, String prod_no, Integer unit_price, Integer prod_quantity, String deli_status, java.sql.Date deli_time){
		
		Product_order_listVO product_order_listVO = new Product_order_listVO();
		
		product_order_listVO.setProd_ord_no(prod_ord_no);
		product_order_listVO.setProd_no(prod_no);
		product_order_listVO.setUnit_price(unit_price);
		product_order_listVO.setProd_quantity(prod_quantity);
		product_order_listVO.setDeli_status(deli_status);
		product_order_listVO.setDeli_time(deli_time);
		dao.update(product_order_listVO);
		
		return product_order_listVO;
	}
	
	public void deleteProduct_order_list(String prod_ord_no) {
		dao.delete(prod_ord_no);
	}

	public Product_order_listVO getOneProduct_order_list(String prod_ord_no, String prod_no) {
		return dao.findByPrimaryKey(prod_ord_no, prod_no);
	}
	
	public Product_order_listVO getOneProduct_order_list_By_One_PK(String prod_ord_no) {
		return dao.findByPrimaryKey(prod_ord_no);
	}
	
	public List<Product_order_listVO> getProduct_order_list_By_One_PK(String prod_ord_no) {
		return dao.findByPK(prod_ord_no);
	}

	public List<Product_order_listVO> getAll() {
		return dao.getAll();
	}
	
}
