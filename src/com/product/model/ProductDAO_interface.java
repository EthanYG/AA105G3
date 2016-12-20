package com.product.model;

import java.util.*;

public interface ProductDAO_interface {
	public void insert(ProductVO prodVO);
	public void update(ProductVO prodVO);
	public void delete(String prod_no);
	public ProductVO findByPrimaryKey(String prod_no);
	public List<ProductVO> getAll();

}