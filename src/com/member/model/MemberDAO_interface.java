package com.member.model;

import java.util.*;

public interface MemberDAO_interface {
	public void insert(MemberVO memVO);
	public void update(MemberVO memVO);
	public void delete(String mem_no);
	public MemberVO findByPrimaryKey(String mem_no);
	public List<MemberVO> getAll();
}
