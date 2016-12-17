package com.emp_auth.model;

import java.util.List;
import java.util.Set;

import com.auth.model.AuthVO;


public class Emp_authService
{
	private Emp_authDAO_interface dao;

	public Emp_authService() {
		dao = new Emp_authDAO();
	}
	public Emp_authVO addEmp_Auth(String emp_no,String auth_no) {

		Emp_authVO emp_authVO = new Emp_authVO();
		
		emp_authVO.setEmp_no(emp_no);;
		emp_authVO.setAuth_no(auth_no);
		dao.insert(emp_authVO);

		return emp_authVO;
	}
	
	public List<Emp_authVO> getAll() {
		return dao.getAll();
	}

	public Set<Emp_authVO> getAuthsByEmp_no(String emp_no) {
		return dao.findByPrimaryKey(emp_no);
	}
	public List<String> getAuthsStringByEmp_no(String emp_no) {
		return dao.getAuthsbyPrimaryKey(emp_no);
	}

	public void deleteEmp_auth(String emp_no) {
		dao.delete(emp_no);
	}
}

