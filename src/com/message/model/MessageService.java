package com.message.model;

import java.util.List;

import com.report_case.model.Report_caseVO;
public class MessageService {

	private MessageDAO_interface dao;

	public MessageService() {
		dao = new MessageJDBCDAO();
	}

	public MessageVO addMessage( String mem_no,String msg_mem_no ,String msg_rel,String msg_detail, java.sql.Date msg_date)
	{
		
	
		
		MessageVO MessageVO = new MessageVO();


		MessageVO.setMem_no(mem_no);
		MessageVO.setMsg_mem_no(msg_mem_no);
		MessageVO.setMsg_rel(msg_rel);
		MessageVO.setMsg_detail(msg_detail);
		MessageVO.setMsg_date(msg_date);		
		
		dao.insert(MessageVO);

		return MessageVO;
	}

	public MessageVO updateMessage(String msg_detail,String msg_no) {

		MessageVO MessageVO = new MessageVO();
		

		
		MessageVO.setMsg_detail(msg_detail);
		MessageVO.setMsg_no(msg_no);

		
		dao.update(MessageVO);

		return MessageVO;
	}

	public void deleteMessage(String msg_no) {
		dao.delete(msg_no);
	}

	public MessageVO getOneMessage(String msg_no) {
		return dao.findByPrimaryKey(msg_no);
	}

	public List<MessageVO> getAll() {
		return dao.getAll();
	}
}

