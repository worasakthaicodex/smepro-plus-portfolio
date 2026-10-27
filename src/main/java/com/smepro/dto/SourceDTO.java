package com.smepro.dto;

import java.util.List;

import com.smepro.models.commitment;
import com.smepro.models.dataset_data;
import com.smepro.models.registerkey;

public class SourceDTO {

   private int id;
   private String table;
   private List<dataset_data> app_dataset_data;

   
   public SourceDTO(
		   int id,
		   String table, 
		   List<dataset_data> app_dataset_data)
   	{ 
	   this.id = id;
	   this.app_dataset_data = app_dataset_data;
	   this.table=  table;
   	}


		public int getId() {
			return id;
		}
		
		
		public void setId(int id) {
			this.id = id;
		}
		
		
		public String getTable() {
			return table;
		}
		
		
		public void setTable(String table) {
			this.table = table;
		}
		
		
		public List<dataset_data> getApp_dataset_data() {
			return app_dataset_data;
		}
		
		
		public void setApp_dataset_data(List<dataset_data> app_dataset_data) {
			this.app_dataset_data = app_dataset_data;
		}
		
		

	
		
		   
	   
   
}
