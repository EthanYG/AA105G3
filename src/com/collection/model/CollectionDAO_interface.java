package com.collection.model;

import java.util.List;

public interface CollectionDAO_interface {
    public void insert(CollectionVO collectionVO);
    public void update(CollectionVO collectionVO);
    public void delete(String coll_no);
    public CollectionVO findByPrimaryKey(String coll_no);
    public List<CollectionVO> getAll();
    
    public List<CollectionVO> findByMem_no(String mem_no);
}
