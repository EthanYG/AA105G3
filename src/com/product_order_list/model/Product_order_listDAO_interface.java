package com.product_order_list.model;

import java.util.*;

public interface Product_order_listDAO_interface {
	public void insert(Product_order_listVO prod_ord_listVO);
	public void update(Product_order_listVO prod_ord_listVO);
	public void delete(String prod_ord_list_no);
	public Product_order_listVO findByPrimaryKey(String prod_ord_no, String prod_no);
	public Product_order_listVO findByPrimaryKey(String prod_ord_no);
	public List<Product_order_listVO> findByPK(String prod_ord_no);
	public List<Product_order_listVO> getAll();

}
