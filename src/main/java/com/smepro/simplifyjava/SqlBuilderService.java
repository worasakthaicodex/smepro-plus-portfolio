package com.smepro.simplifyjava;

import org.springframework.stereotype.Service;



@Service
public class SqlBuilderService {
    
	private StringBuilder sql;
	private StringBuilder sql2;
	private String Table;
	private String table;
	private int  processId;

	public SqlBuilderService() {
		this.sql = new StringBuilder();
		this.sql2 = new StringBuilder();

	}


	public SqlBuilderService BuilderString() {
		this.Table =  "";
		this.table =  "";
		this.processId =  0;
		this.sql.setLength(0);
		this.sql2.setLength(0);
		return this;
	}


	public SqlBuilderService SELECT( ) {
	    	

		
		this.sql.append(" SELECT ");
	    return this;
  }

	public SqlBuilderService SELECT( String select) {
		this.sql.append(" SELECT ");	
		this.sql.append("  ").append(select).append("  ");
		return this;
	}
	public SqlBuilderService ProcessId( int processId) {
		this.processId = processId;
		return this;
	}

	

  public SqlBuilderService  FROM( String Table) {
	this.Table =  Table;
	this.table =  Table.toLowerCase();
	sql.append(" FROM ");
	sql.append(Table);
	return this;
}


	public SqlBuilderService FullSearch( SqlBuilderDTO SqlBuilderDTO) {
	
		String date_start = SqlBuilderDTO.getDate_start();
		String date_end = SqlBuilderDTO.getDate_end();
        String keyword1 = SqlBuilderDTO.getKeyword1();
		String keyword2 = SqlBuilderDTO.getKeyword2();
		String keyword3 = SqlBuilderDTO.getKeyword3();
		String search_type1 = SqlBuilderDTO.getSearch_type1();
		String search_type2 = SqlBuilderDTO.getSearch_type2();
		String search_type3 = SqlBuilderDTO.getSearch_type3();
		String search_by1 = SqlBuilderDTO.getSearch_by1();
		String search_by2 = SqlBuilderDTO.getSearch_by2();
		String search_by3 = SqlBuilderDTO.getSearch_by3();
		String type1 = SqlBuilderDTO.getType1();
		String type2 = SqlBuilderDTO.getType2();
		String type3 = SqlBuilderDTO.getType3();
		
		
		
		sql.append(" WHERE ").append("processId").append(" = ").append(this.processId);

		if(type1 == null) { return this;}
		if(type1.equals("NONE")) { return this;}
		
		 if(!date_start.isBlank()){
		   System.out.println("search_type1 "+date_start);
		   sql.append(" AND ").append(this.table).append("_insert_date").append(" >= ").append("'").append(date_start).append("'");
		   sql2.append(" AND ").append(this.table).append("_insert_date").append(" >= ").append("'").append(date_start).append("'");
		 }
 
		 if(!date_end.isBlank()){
 
		   System.out.println("search_type2 "+date_end);
		   sql.append(" AND ").append(this.table).append("_insert_date").append(" <= ").append("'").append(date_end).append("'");
		   sql2.append(" AND ").append(this.table).append("_insert_date").append(" <= ").append("'").append(date_end).append("'");
		 }
 
		 if(keyword1 != null){
 
		  if(search_type1.equals("NULL") || search_type1.equals("NOT NULL") ){
 
			  sql.append(" and ").append(search_by1).append(" IS ").append(search_type1).append(" ");
 
 
 
		   }else if(search_type1.equals("Like")){
			 sql.append(" and ")
			 .append(" ( ")
			 .append(search_by1).append("  ").append(search_type1).append(" '%").append(keyword1).append("%' ")
			 .append(" OR ")
			 .append(search_by1).append(" ").append(search_type1).append(" '%").append(keyword1).append("' ")
			 .append(" OR ")
			 .append(search_by1).append(" ").append(search_type1).append(" '").append(keyword1).append("%' ")
			 .append(" ) ");
		   }else{
			  sql.append(" AND ").append(search_by1).append("  ").append(search_type1).append("  '").append(keyword1).append("' ");
 
		   }
 
		 }
	   
		 if(keyword2 != null){
 
			 if(type2.equals("AND") ){
	 
			   if(search_type2.equals("NULL") || search_type2.equals("NOT NULL") ){
 
					sql.append(" and ").append(search_by2).append(" IS ").append(search_type2).append(" ");
	   
				 }else if(search_type2.equals("Like")){
				 sql.append(" and ")
				 .append(" ( ")
				 .append(search_by2).append("  ").append(search_type2).append(" '%").append(keyword2).append("%' ")
				 .append(" OR ")
				 .append(search_by2).append(" ").append(search_type2).append(" '%").append(keyword2).append("' ")
				 .append(" OR ")
				 .append(search_by2).append(" ").append(search_type2).append(" '").append(keyword2).append("'% ")
				 .append(" ) ");
			   }else{
				  sql.append(" AND ").append(search_by2).append("  ").append(search_type2).append("  '").append(keyword2).append("' ");
	 
			   }
			 }
			 if(type2.equals("OR") ){
			  
			   if(search_type2.equals("NULL") || search_type2.equals("NOT NULL") ){
 
				 sql.append(" OR ").append(search_by2).append(" IS ").append(search_type2).append("  ").append(sql2.toString());
 
			  }else if(search_type2.equals("Like")){
				  sql.append(" OR ")
				  .append(" ( ")
				  .append(search_by2).append("  ").append(search_type2).append(" '%").append(keyword2).append("%' ")
				  .append(" OR ")
				  .append(search_by2).append(" ").append(search_type2).append(" '%").append(keyword2).append("' ")
				  .append(" OR ")
				  .append(search_by2).append(" ").append(search_type2).append(" '").append(keyword2).append("%' ")
				  .append(" ) ").append("  ").append(sql2.toString());
				 }else{
				 sql.append(" AND ").append(search_by2).append("  ").append(search_type2).append("  '").append(keyword2).append("' ").append("  ").append(sql2.toString());
			  }
 
 
			 }
			
		 }
 
		 if(keyword3 != null){
 
		   if(type3.equals("AND") ){
   
			 if(search_type3.equals("NULL") || search_type3.equals("NOT NULL") ){

				sql.append(" and ").append(search_by3).append(" IS ").append(search_type3).append(" ");

			}else if(search_type3.equals("Like")){
			   sql.append(" and ")
			   .append(" ( ")
			   .append(search_by3).append("  ").append(search_type3).append(" '%").append(keyword3).append("%' ")
			   .append(" OR ")
			   .append(search_by3).append(" ").append(search_type3).append(" '%").append(keyword3).append("' ")
			   .append(" OR ")
			   .append(search_by3).append(" ").append(search_type3).append(" '").append(keyword3).append("%' ")
			   .append(" ) ");
			 }else{
				sql.append(" AND ").append(search_by3).append("  ").append(search_type3).append("  '").append(keyword3).append("' ");
   
			 }
		   }
		   if(type3.equals("OR") ){
			
			 if(search_type3.equals("NULL") || search_type3.equals("NOT NULL") ){
 
			   sql.append(" OR ").append(search_by3).append(" IS ").append(search_type3).append("  ").append(sql2.toString());
 
			}else if(search_type3.equals("Like")){
				sql.append(" OR ")
				.append(" ( ")
				.append(search_by3).append("  ").append(search_type3).append(" '%").append(keyword3).append("%' ")
				.append(" OR ")
				.append(search_by3).append(" ").append(search_type3).append(" '%").append(keyword3).append("' ")
				.append(" OR ")
				.append(search_by3).append(" ").append(search_type3).append(" '").append(keyword3).append("%' ")
				.append(" ) ").append("  ").append(sql2.toString());
			   }else{
			   sql.append(" AND ").append(search_by3).append("  ").append(search_type3).append("  '").append(keyword3).append("' ").append("  ").append(sql2.toString());
			}
 
 
		   }
		 
	   }
 
 
	   return this;



	}

	public String build() {
		return this.sql.toString();
	}


}
