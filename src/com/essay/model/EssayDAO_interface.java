package com.essay.model;

import java.util.List;

public interface EssayDAO_interface {
    public void insert(EssayVO essayVO);
    public void update(EssayVO essayVO);
    public void delete(String esa_no);
    public EssayVO findByPrimaryKey(String esa_no);
    public List<EssayVO> getAll();
}
