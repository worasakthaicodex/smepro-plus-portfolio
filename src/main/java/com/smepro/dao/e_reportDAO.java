package com.smepro.dao;

import java.util.List;

import com.smepro.models.central;
import com.smepro.models.e_report;
import com.smepro.models.edata;

public interface e_reportDAO {

	  void save(e_report e_reports);
	  void update(e_report e_reports);
	  e_report getby(int id,int condition);
}
