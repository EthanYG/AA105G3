package com.auth.model;

import java.util.List;


public class AuthService
{
	private AuthDAO_interface dao;
	
	public AuthService() {
		dao = new AuthDAO();
	}
	public AuthVO addAuth(String auth_name) {

		AuthVO authVO = new AuthVO();

		authVO.setAuth_name(auth_name);
		
		dao.insert(authVO);

		return authVO;
	}
	public AuthVO updateAuth(String auth_no, String auth_name) {

		AuthVO authVO = new AuthVO();
		
		authVO.setAuth_no(auth_no);
		authVO.setAuth_name(auth_name);
		
		dao.update(authVO);

		return authVO;
	}
	public void deleteAuth(String auth_no) {
		dao.delete(auth_no);
	}

	public AuthVO getOneAuth(String auth_no) {
		return dao.findByPrimaryKey(auth_no);
	}

	public List<AuthVO> getAll() {
		return dao.getAll();
	}
}
