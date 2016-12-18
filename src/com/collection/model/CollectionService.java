package com.collection.model;

import java.util.List;

public class CollectionService {
	private CollectionDAO_interface dao;

	public CollectionService() {
		dao = new CollectionDAO();
	}

	public CollectionVO addCollection(String mem_no, String all_no, String class_no) {

		CollectionVO collectionVO = new CollectionVO();

		collectionVO.setMem_no(mem_no);
		collectionVO.setAll_no(all_no);
		collectionVO.setClass_no(class_no);
		dao.insert(collectionVO);

		return collectionVO;
	}

	public CollectionVO updateCollection(String coll_no, String mem_no, String all_no, String class_no) {

		CollectionVO collectionVO = new CollectionVO();

		collectionVO.setColl_no(coll_no);
		collectionVO.setMem_no(mem_no);
		collectionVO.setAll_no(all_no);
		collectionVO.setClass_no(class_no);
		dao.update(collectionVO);

		return collectionVO;
	}

	public void deleteCollection(String coll_no) {
		dao.delete(coll_no);
	}

	public CollectionVO getOneCollection(String coll_no) {
		return dao.findByPrimaryKey(coll_no);
	}
	
	public List<CollectionVO> getAllByMem_noCollection(String mem_no) {
		return dao.findByMem_no(mem_no);
	}
	
	public List<CollectionVO> getAll() {
		return dao.getAll();
	}
}
