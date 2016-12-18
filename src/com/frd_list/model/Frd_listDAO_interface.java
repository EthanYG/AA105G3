package com.frd_list.model;

import java.util.List;

public interface Frd_listDAO_interface {
    public void insert(Frd_listVO frd_listVO);
    public void update(Frd_listVO frd_listVO);
    public void delete(String mem_no);
    public void deleteOne(Frd_listVO frd_listVO);
    public Frd_listVO findByPrimaryKey(String mem_no);
    public Frd_listVO findByTwoPrimaryKey(String mem_no, String friend_no);
    public List<Frd_listVO> getAll();
    public List<Frd_listVO> findByMem_no(String mem_no);
}
