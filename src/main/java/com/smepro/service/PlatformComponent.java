package com.smepro.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.smepro.dao.edataDAO;
import com.smepro.models.LoadApp;

@Component
public class PlatformComponent {

	
	
	private edataDAO EdataDAO;
	
	  @Autowired
		public void setMyRepository(
				edataDAO EdataDAO
				) {
		 
		  this.EdataDAO = EdataDAO;
	  }
	  
	  
	  
	protected void BuilderSubTabledetails(LoadApp LoadApp,List<?> SubDataDTO) {
	
        if(SubDataDTO == null) {
        	EdataDAO.setSubTabledetails(null,LoadApp.getEdata().getData_id());
        	return;
        }
		
		try {
		  StringBuilder BuilderRow = new StringBuilder();
		  List<String> title = new ArrayList<String>(Arrays.asList("bom_name","bom_base_quantity","bom_base_unit"));
		  for (Object item : SubDataDTO) {
				 StringBuilder BuilderColumn = new StringBuilder();
				 
					 for(String customLable :title) {
						 	Field field = item.getClass().getDeclaredField(customLable);
							field.setAccessible(true);
							Object value = field.get(item);
							String label = String.valueOf(value);
							BuilderColumn.append(label).append(" ");	
										
					 }		
					 BuilderRow.append(BuilderColumn.toString()).append("\n");
				  }
				EdataDAO.setSubTabledetails(BuilderRow.toString(),LoadApp.getEdata().getData_id());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
	
	}
	
	
	 
}
