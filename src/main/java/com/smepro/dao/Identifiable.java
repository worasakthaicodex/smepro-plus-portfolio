package com.smepro.dao;

import java.util.List;

public interface Identifiable {
	
	
		int getId();
		int getProcessId();
		
		
		
		
		List<Object> getJoin1();
		void setJoin1(List<Object> list);

		List<Object> getJoin2();
		void setJoin2(List<Object> list);

		
}
