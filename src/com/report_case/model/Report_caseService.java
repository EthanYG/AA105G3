package com.report_case.model;

import java.util.List;

public class Report_caseService {

	private Report_caseDAO_interface dao;

	public Report_caseService() {
		dao = new Report_caseJNDIDAO();
	}

	public Report_caseVO addReport_case( String rep_mem_no,String rep_tar_no ,String rep_type,String rep_reason, java.sql.Date rep_date ,String rep_chk_con)
	{
		
	
		
		Report_caseVO Report_caseVO = new Report_caseVO();



		Report_caseVO.setRep_mem_no(rep_mem_no);
		Report_caseVO.setRep_tar_no(rep_tar_no);
		Report_caseVO.setRep_type(rep_type);
		Report_caseVO.setRep_date(rep_date);		//Timestamp may be different with Date
		Report_caseVO.setRep_reason(rep_reason);
		Report_caseVO.setRep_chk_con(rep_chk_con);
		dao.insert(Report_caseVO);

		return Report_caseVO;
	}

	public Report_caseVO updateReport_case(String rep_mem_no,String rep_tar_no,String rep_type,
				java.sql.Date rep_date,String rep_reason,String rep_chk_con) {

		Report_caseVO Report_caseVO = new Report_caseVO();
		

		
//		Report_caseVO.setRep_no(rep_no);
		Report_caseVO.setRep_mem_no(rep_mem_no);
		Report_caseVO.setRep_tar_no(rep_tar_no);
		Report_caseVO.setRep_type(rep_type);
		Report_caseVO.setRep_date(rep_date);
		Report_caseVO.setRep_reason(rep_reason);
		Report_caseVO.setRep_chk_con(rep_chk_con);
		
		dao.update(Report_caseVO);

		return Report_caseVO;
	}

	public void deleteReport_case(String rep_no) {
		dao.delete(rep_no);
	}

	public Report_caseVO getOneReport_case(String rep_no) {
		return dao.findByPrimaryKey(rep_no);
	}

	public List<Report_caseVO> getAll() {
		return dao.getAll();
	}
}
