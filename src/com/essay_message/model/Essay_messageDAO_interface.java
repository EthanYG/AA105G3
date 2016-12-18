package com.essay_message.model;

import java.util.*;

public interface Essay_messageDAO_interface {
    public void insert(Essay_messageVO essay_messageVO);
    public void update(Essay_messageVO essay_messageVO);
    public void delete(String esamsg_no);
    public Essay_messageVO findByPrimaryKey(String esamsg_no);
    public List<Essay_messageVO> getAll();
}
