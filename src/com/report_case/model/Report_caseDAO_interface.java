package com.report_case.model;

import java.util.*;

public interface Report_caseDAO_interface {

	public void insert(Report_caseVO report_caseVO);

	public void update(Report_caseVO report_caseVO);

	public void delete(String rep_no);

	public Report_caseVO findByPrimaryKey(String rep_no);

	public List<Report_caseVO> getAll();

}
