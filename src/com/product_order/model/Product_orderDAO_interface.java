package com.product_order.model;

import java.util.*;

import com.product_order_list.model.Product_order_listVO;

public interface Product_orderDAO_interface {
	public void insert(Product_orderVO prodVO);
	public void update(Product_orderVO prodVO);
	public void delete(String prod_ord_no);
	public Product_orderVO findByPrimaryKey(String prod_ord_no);
	public List<Product_orderVO> getAll();

}
