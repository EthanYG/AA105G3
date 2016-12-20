package com.adv.model;

import java.util.*;

public interface AdvDAO_interface {
	public void insert(AdvVO advVO);
	public void update(AdvVO advVO);
	public void delete(String adv_no);
	public AdvVO findByPrimaryKey(String adv_no);
	public List<AdvVO> getAll();

}
