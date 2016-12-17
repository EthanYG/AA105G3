package com.emp.model;

import java.util.*;

public interface EmpDAO_interface {
    public void insert(EmpVO empVO);
    public void update(EmpVO empVO);
    public void delete(String emp_no);
    public EmpVO findByPrimaryKey(String emp_no);
    public List<EmpVO> getAll();
}
