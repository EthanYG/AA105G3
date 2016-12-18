package com.frd_list.model;

import java.util.List;

import com.frd_list.model.Frd_listDAO;
import com.frd_list.model.Frd_listDAO_interface;
import com.frd_list.model.Frd_listVO;

public class Frd_listService {
	private Frd_listDAO_interface dao;

	public Frd_listService() {
		dao = new Frd_listDAO();
	}

	public Frd_listVO addFrd_list(String mem_no, String friend_no, String friend_chk) {

		Frd_listVO frd_listVO = new Frd_listVO();

		frd_listVO.setMem_no(mem_no);
		frd_listVO.setFriend_no(friend_no);
		frd_listVO.setFriend_chk(friend_chk);
		dao.insert(frd_listVO);

		return frd_listVO;
	}

	public Frd_listVO updateFrd_list(String mem_no, String friend_no, String friend_chk) {

		Frd_listVO frd_listVO = new Frd_listVO();

		frd_listVO.setMem_no(mem_no);
		frd_listVO.setFriend_no(friend_no);
		frd_listVO.setFriend_chk(friend_chk);
		dao.update(frd_listVO);

		return frd_listVO;
	}

	public void deleteFrd_list(String mem_no) {
		dao.delete(mem_no);
	}
	
	public void deleteOneFrd_list(Frd_listVO frd_listVO){
		dao.deleteOne(frd_listVO);
	}
	
	//Frd_listVO frd_listVO5 = dao.findByTwoPrimaryKey("M00000001","M00000003");
	public Frd_listVO findByTwoPrimaryKey(String mem_no, String friend_no){
		return dao.findByTwoPrimaryKey(mem_no, friend_no);
	}
	
	public Frd_listVO getOneFrd_list(String mem_no) {
		return dao.findByPrimaryKey(mem_no);
	}
	
	public List<Frd_listVO> getAllByMem_noFrd_list(String mem_no) {
		return dao.findByMem_no(mem_no);
	}
	
	public List<Frd_listVO> getAll() {
		return dao.getAll();
	}
}
