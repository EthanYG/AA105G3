package com.customer_demand.model;

import java.util.*;
import java.sql.*;

public class Customer_demandService {
	private Customer_demandDAO_interface dao;

	public Customer_demandService() {
		dao = new Customer_demandDAO();
	}

	public Customer_demandVO addCustomer_demand(String mem_no, String cusde_detail, Timestamp cusde_date, Timestamp cusde_create_date, String cusde_title) {

		Customer_demandVO customer_demandVO = new Customer_demandVO();
	
		customer_demandVO.setMem_no(mem_no);
		customer_demandVO.setCusde_detail(cusde_detail);
		customer_demandVO.setCusde_date(cusde_date);
		customer_demandVO.setCusde_create_date(cusde_create_date);
		customer_demandVO.setCusde_title(cusde_title);
		dao.insert(customer_demandVO);

		return customer_demandVO;
	}

	public Customer_demandVO updateCustomer_demand(String cusde_no, String mem_no, String cusde_detail, Timestamp cusde_date, Timestamp cusde_create_date, String cusde_title) {

		Customer_demandVO customer_demandVO = new Customer_demandVO();

		customer_demandVO.setCusde_no(cusde_no);
		customer_demandVO.setMem_no(mem_no);
		customer_demandVO.setCusde_detail(cusde_detail);
		customer_demandVO.setCusde_date(cusde_date);
		customer_demandVO.setCusde_create_date(cusde_create_date);
		customer_demandVO.setCusde_title(cusde_title);
		dao.update(customer_demandVO);

		return customer_demandVO;
	}

	public void deleteCustomer_demand(String cusde_no) {
		dao.delete(cusde_no);
	}

	public Customer_demandVO getOneCustomer_demand(String cusde_no) {
		return dao.findByPrimaryKey(cusde_no);
	}
	
	public List<Customer_demandVO> getAllByMem_noCustomer_demand(String mem_no) {
		return dao.findByMem_no(mem_no);
	}
	
	public List<Customer_demandVO> getAll() {
		return dao.getAll();
	}

}
