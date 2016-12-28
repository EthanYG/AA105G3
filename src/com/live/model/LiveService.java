package com.live.model;

import java.util.List;
import com.report_case.model.Report_caseVO;

public class LiveService {


	private LiveDAO_interface dao;

	public LiveService() {
		dao = new LiveJDBCDAO();
	}

	public LiveVO addLive( String mem_no,String live_name ,String live_intro,String live_status)
	{
		
	
		
		LiveVO LiveVO = new LiveVO();


		LiveVO.setMem_no(mem_no);
		LiveVO.setLive_name(live_name);
		LiveVO.setLive_intro(live_intro);
		LiveVO.setLive_status(live_status);
		
		dao.insert(LiveVO);

		return LiveVO;
	}

	public LiveVO updateLive(String live_name,String live_intro,Integer live_counts,Integer live_follow,String live_status,String mem_no) {

		LiveVO LiveVO = new LiveVO();
		


		LiveVO.setLive_name(live_name);
		LiveVO.setLive_intro(live_intro);
		LiveVO.setLive_counts(live_counts);
		LiveVO.setLive_follow(live_follow);		
		LiveVO.setLive_status(live_status);
		LiveVO.setMem_no(mem_no);		
		
		dao.update(LiveVO);

		return LiveVO;
	}

	public void deleteLive(String msg_no) {
		dao.delete(msg_no);
	}

	public LiveVO getOneLive(String msg_no) {
		return dao.findByPrimaryKey(msg_no);
	}

	public List<LiveVO> getAll() {
		return dao.getAll();
	}
}
