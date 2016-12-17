package com.emp_auth.model;

import java.util.List;
import java.util.Set;

public interface Emp_authDAO_interface
{
	public void insert(Emp_authVO emp_authVO);
    public void update(Emp_authVO emp_authVO);
    public void delete(String emp_no);
    public void deleteOneAuth(String emp_no,String auth_no);
    public Set<Emp_authVO> findByPrimaryKey(String emp_no);
    public List<String> getAuthsbyPrimaryKey(String emp_no);
    public List<Emp_authVO> getAll();
}
