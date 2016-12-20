package com.product_order.model;

import java.util.List;
import java.util.Set;

import com.product_order_list.model.Product_order_listVO;



public class Product_orderService {
	
	private Product_orderDAO_interface dao;

	public Product_orderService() {
		dao = new Product_orderJNDIDAO();
	}

	public Product_orderVO addProduct_order(String mem_no, java.sql.Date prod_ord_time, String cred_card_no,
			java.sql.Date valid_date, String valid_no, String cred_card_type, Integer total_money, String ship_name,
			String post_code, String mem_adrs, String cell_phone, String tel_phone) {

		Product_orderVO product_orderVO = new Product_orderVO();

		product_orderVO.setMem_no(mem_no);
		product_orderVO.setProd_ord_time(prod_ord_time);
		product_orderVO.setCred_card_no(cred_card_no);
		product_orderVO.setValid_date(valid_date);
		product_orderVO.setValid_no(valid_no);
		product_orderVO.setCred_card_type(cred_card_type);
		product_orderVO.setTotal_money(total_money);
		product_orderVO.setShip_name(ship_name);
		product_orderVO.setPost_code(post_code);
		product_orderVO.setMem_adrs(mem_adrs);
		product_orderVO.setCell_phone(cell_phone);
		product_orderVO.setTel_phone(tel_phone);
		dao.insert(product_orderVO);

		return product_orderVO;
	}

	public Product_orderVO updateProduct_order(String prod_ord_no ,String mem_no, java.sql.Date prod_ord_time, 
			String cred_card_no, java.sql.Date valid_date, String valid_no, String cred_card_type, Integer total_money, 
			String ship_name, String post_code, String mem_adrs, String cell_phone, String tel_phone) {

		Product_orderVO product_orderVO = new Product_orderVO();
		
		product_orderVO.setProd_ord_no(prod_ord_no);
		product_orderVO.setMem_no(mem_no);
		product_orderVO.setProd_ord_time(prod_ord_time);
		product_orderVO.setCred_card_no(cred_card_no);
		product_orderVO.setValid_date(valid_date);
		product_orderVO.setValid_no(valid_no);
		product_orderVO.setCred_card_type(cred_card_type);
		product_orderVO.setTotal_money(total_money);
		product_orderVO.setShip_name(ship_name);
		product_orderVO.setPost_code(post_code);
		product_orderVO.setMem_adrs(mem_adrs);
		product_orderVO.setCell_phone(cell_phone);
		product_orderVO.setTel_phone(tel_phone);
		dao.update(product_orderVO);

		return product_orderVO;
	}

	public Product_orderVO getOneProduct_order(String prod_ord_no) {
		return dao.findByPrimaryKey(prod_ord_no);
	}

	public List<Product_orderVO> getAll() {
		return dao.getAll();
	}

}
