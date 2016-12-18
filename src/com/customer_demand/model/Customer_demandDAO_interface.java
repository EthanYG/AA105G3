package com.customer_demand.model;

import java.util.List;

import com.collection.model.CollectionVO;

public interface Customer_demandDAO_interface {
    public void insert(Customer_demandVO customer_demandVO);
    public void update(Customer_demandVO customer_demandVO);
    public void delete(String cusde_no);
    public Customer_demandVO findByPrimaryKey(String cusde_no);
    public List<Customer_demandVO> getAll();
    
    public List<Customer_demandVO> findByMem_no(String mem_no);

}
