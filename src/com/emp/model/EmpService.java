package com.emp.model;

import java.util.List;


public class EmpService
{
	private EmpDAO_interface dao;
		
	public EmpService() {
		dao = new EmpDAO();
	}
	public EmpVO addEmp(String emp_name, String emp_account,String emp_password,String emp_id,String emp_email,String emp_address
			,String emp_phone, java.sql.Date emp_hiredate,String emp_job) {

		EmpVO empVO = new EmpVO();

		empVO.setEmp_name(emp_name);
		empVO.setEmp_account(emp_account);
		empVO.setEmp_password(emp_password);
		empVO.setEmp_id(emp_id);
		empVO.setEmp_email(emp_email);
		empVO.setEmp_address(emp_address);
		empVO.setEmp_phone(emp_phone);
		empVO.setEmp_hiredate(emp_hiredate);
		empVO.setEmp_job(emp_job);
		dao.insert(empVO);

		return empVO;
	}
//
	public EmpVO updateEmp(String emp_no, String emp_name, String emp_account,String emp_password,String emp_id,String emp_email,
			String emp_address,String emp_phone, java.sql.Date emp_hiredate,String emp_job,String emp_status) {

		EmpVO empVO = new EmpVO();

		empVO.setEmp_no(emp_no);
		empVO.setEmp_name(emp_name);
		empVO.setEmp_account(emp_account);
		empVO.setEmp_password(emp_password);
		empVO.setEmp_id(emp_id);
		empVO.setEmp_email(emp_email);
		empVO.setEmp_address(emp_address);
		empVO.setEmp_phone(emp_phone);
		empVO.setEmp_hiredate(emp_hiredate);
		empVO.setEmp_job(emp_job);
		empVO.setEmp_status(emp_status);
		dao.update(empVO);

		return empVO;
	}

	public void deleteEmp(String emp_no) {
		dao.delete(emp_no);
	}

	public EmpVO getOneEmp(String emp_no) {
		return dao.findByPrimaryKey(emp_no);
	}

	public List<EmpVO> getAll() {
		return dao.getAll();
	}
}
