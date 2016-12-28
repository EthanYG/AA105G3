package com.message.model;

import java.util.*;

public interface MessageDAO_interface {

	public void insert(MessageVO messageVO);

	public void update(MessageVO messageVO);

	public void delete(String msg_no);

	public MessageVO findByPrimaryKey(String msg_no);

	public List<MessageVO> getAll();

}
