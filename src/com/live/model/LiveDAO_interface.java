package com.live.model;

import java.util.*;

public interface LiveDAO_interface {
	public void insert(LiveVO liveVO);

	public void update(LiveVO liveVO);

	public void delete(String mem_no);

	public LiveVO findByPrimaryKey(String mem_no);

	public List<LiveVO> getAll();

}
