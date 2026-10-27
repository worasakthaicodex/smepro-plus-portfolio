package com.smepro.service;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;



@Service
public class sqlservice {


	 

	    private StringBuilder queryBuilder;
	    private StringBuilder keepwhere;
	    private StringBuilder keepselect;
	    private String column;
	    private String table;
	    private List<String> faketablesize;
	    private String faketable;
	    private String AND;
	    private String UNIONALL;
	    private String GROUP;

	    
	    public sqlservice() {
	        this.queryBuilder = new StringBuilder();
	        this.keepwhere = new StringBuilder();
	        this.keepselect = new StringBuilder();
	    }

	    public sqlservice SqlBuilderString() {
	    	
	        this.queryBuilder.setLength(0);
	        this.keepwhere.setLength(0);
	        this.keepselect.setLength(0);
	        this.column =  "";
	        this.table =  "";
	        this.faketable =  "";
	        this.GROUP =  "";
	        this.AND =  "";
	        this.faketablesize =  null;
	        this.UNIONALL =  "";
	        
	        return this;
	    }

	    
	    public sqlservice SELECT( ) {
	    	
	    	  this.queryBuilder.append(" SELECT ");
	      
	        return this;
	    }
	    
	  
	    public sqlservice column( String column) {
	        this.column =  column;
	        return this;
	    }
	    
	    public sqlservice table( String table) {
	        this.table =  table;
	        return this;
	    }
	    public sqlservice faketable( List<String> size) {
	        this.faketablesize =  size;
	        
	     	if(size != null) { 
	     		this.faketable ="F"+size.get(0)+".";
			}
	        
	        return this;
	    }
	    
	    
	    public sqlservice WHERE() {
	    	this.queryBuilder.append(" WHERE ");
	        return this;
	    }
	    
	    public sqlservice GROUP(String GROUP) {
	    	 this.GROUP = GROUP;
	        return this;
	    }
	    
	    
	    public sqlservice UNIONALL() {
	    	this.UNIONALL = " UNION ALL ";
	        return this;
	    }
	    
	    
	    public sqlservice AND() {
	    	this.AND =" AND ";
	        return this;
	    }
	    
	    public sqlservice FROM() {

	    	if(!table.isBlank()) {
	  			 this.queryBuilder.append(" FROM ").append(table).append(" ");
	  		}
	  		if(this.faketablesize != null) {
	  			 this.queryBuilder.append(faketable.replace("."," "));
	  		}
	  		
	  		this.keepselect.append( this.queryBuilder);
	  		
	        return this;
	    }
	    
	    
	    public sqlservice String(String value) {
	    	this.queryBuilder.append(value);
	        return this;
	    }
	    
	    
	    public sqlservice ConditionBuilder(String keyword, String[] filter,String operation) {
	    
	    	int start = 0;
	    	
			for (int i = 0; i < filter.length; i++) {
			if(filter[i] != null ) {
				start++;
				    if (start > 1) {
				    	this.queryBuilder.append(" ").append(operation).append(" ");
				    	this.keepwhere.append(" ").append(operation).append(" ");
				    }
				    this.queryBuilder.append(column+keyword).append(" = '").append(filter[i]).append("'");
				    this.keepwhere.append(column+keyword).append(" = '").append(filter[i]).append("'");
				}
			}
	        return this;
	    }
	    
	    
	    
	    
	    public sqlservice ConditionBuilder(String[] keyword, String[] value,String operation) {
	    	

	    	 
	    	if(keyword.length > 0) { 
	    		this.queryBuilder.append(" ").append(this.AND).append(" ");
	    		this.keepwhere.append(" ").append(this.AND).append(" ");
	    		int start =0;
						for (int i = 0; i < value.length; i++) {
							
							if( StringUtils.isNotBlank(value[i]) &&  StringUtils.isNotBlank(keyword[i])) {
											
								  start++;
									    if (start > 1) {
									    	this.queryBuilder.append(" ").append(operation).append(" ");
									    	this.keepwhere.append(" ").append(operation).append(" ");
									    }
									    this.queryBuilder.append(faketable+column+keyword[i]).append(" = '").append(value[i]).append("'");
										this.keepwhere.append(faketable+column+keyword[i]).append(" = '").append(value[i]).append("'");
							}
						    
						}
	    	}
	    	 
	    	 this.AND = "";
			
				
	        return this;
	    }
	    
	    
	    
	    
	    public sqlservice ConditionObjectBuilder(String[] keyword,String operation) {
	    	
	    	int start = 0;
	    	
	    	if(keyword.length > 0) { 
	    		this.queryBuilder.append(" ").append(this.AND).append(" ");
				 this.keepwhere.append(" ").append(this.AND).append(" ");
	    		
						for (int i = 0; i < keyword.length; i++) {
							
							if(  StringUtils.isNotBlank(keyword[i])) {
								start++;			
									    if (start > 1) {
									    	this.queryBuilder.append(" ").append(operation).append(" ");
											 this.keepwhere.append(" ").append(operation).append(" ");
									    }
									
									    this.queryBuilder.append(faketable+keyword[i]).append(" = ").append(" ? ");
										this.keepwhere.append(faketable+keyword[i]).append(" = ").append(" ? ");
							}
						    
						}
						 
	    	}	
	    	this.AND = "";
	        return this;
	    }
	    
	    
		    public DateRangeConditionStep DateBetweenConditionBuilder(String dateStart, String dateEnd, String key) {
		
		    	
		
		    		 return new DateRangeConditionStep(this,dateStart,dateEnd,column);
		   
	           
	        }

	    
	    
	    public sqlservice SelectObjectBuilder(String[] filter) {
	    	

			
	    	for (int i = 0; i < filter.length; i++) {
	    		if(filter[i] != null ) {
  
	    			if(i > 0) {
	    	
	    				 this.queryBuilder.append(",");	
	    				 
	    			}
	    				this.queryBuilder.append(faketable+filter[i]).append(" AS ").append(filter[i]);
	    				
	    		}
			    
			}

		        return this;
		    }
	    
	    
		    public sqlservice SelectStringBuilder(String[] filter) {
			    	
		
					
			    	for (int i = 0; i < filter.length; i++) {
			    		if(filter[i] != null ) {
		  
			    			if(i > 0) {
			    	
			    				 this.queryBuilder.append(",");	
			    				 
			    			}
			    				this.queryBuilder.append(faketable+filter[i]);
			    				
			    		}
					    
					}
		
				        return this;
				    }
			    
    
    
	    	public sqlservice SelectBuilder(String[] filter) {
	 
	    	
	    	for (int i = 0; i < filter.length; i++) {
	    		if(filter[i] != null ) {
	    			
	    			if(i > 0) {
	    				 this.queryBuilder.append(",");	
	    				
	    			}
	    				this.queryBuilder.append(faketable+column+filter[i]).append(" AS ").append(column+filter[i]);
	    			
	    		}
			    
			}
	
		        return this;
 
		    }
	    
	    
	    
	    	
	    	
	  	    
	    
	    
		 public sqlservice SelectConditionMaxBuilder(String[] filter, String[] filterAs) {
			    	

			 
		    	for (int i = 0; i < filter.length; i++) {
		    		
		    		if(filter[i] != null && filterAs[i]!= null) {
				    		this.queryBuilder.append("MAX(");
						    this.queryBuilder.append(column+filter[i]).append(")").append(" AS ").append(column+filterAs[i]);
						    
						    if(filter.length > 1 ) {
						    	  this.queryBuilder.append(",");
						    }
		    		}
				}
			        return this;
		}
			    
		  
		 public sqlservice Join(List<String> Join) {
		    	
			 String old = "0";
			 for (int e = 0; e < this.faketablesize.size(); e++) {
				
				 if(e > 0) {
					     
					   //  String keepselect = this.keepselect.toString().replace("F"+old,"F"+this.faketablesize.get(e)+",");
					    // String keepselect =" F"+old+"*,"+"F"+this.faketablesize.get(e)+"* ";
					     
					     this.queryBuilder.append(this.UNIONALL).append("SELECT").append(" ");
					     this.queryBuilder.append("F").append(this.faketablesize.get(e)).append(".* ");
					     this.queryBuilder.append(" FROM ").append(this.table).append(" F").append(this.faketablesize.get(e)).append(" ");
					     this.queryBuilder.append(" JOIN ").append(Join.get(e-1)); 	
						 this.queryBuilder.append(" AND  ").append(this.keepwhere); 	
						 this.queryBuilder.append(" GROUP BY  ").append("F").append(this.faketablesize.get(e)).append(".").append(this.GROUP).append(" "); 	
						 
						 old = this.faketablesize.get(e);

				 }else {
					 old = this.faketablesize.get(e);
				 }
				 
			 }
			
			
			 
			        return this;
		}
			 
		 
		 
			
		    public String build() {
		        return this.queryBuilder.toString();
		    }
		
	
	    
	    
	    
	    
		    public static class DateRangeConditionStep {
		        private sqlservice sqlBuilder;
		        private StringBuilder queryBuilder;
		        private StringBuilder keepwhere;
		        private List<String> faketablesize;
		        private String startDate;
		        private String endDate;
		        private String column;
		        private boolean Condition;

		        public DateRangeConditionStep(sqlservice sqlBuilder, String startDate, String endDate, String column) {
		            this.sqlBuilder = sqlBuilder;
		            this.queryBuilder = sqlBuilder.queryBuilder;
		            this.keepwhere = sqlBuilder.keepwhere;
		            this.faketablesize= sqlBuilder.faketablesize;
		            
		            this.startDate = startDate;
		            
		            if(endDate == null) {
		            	this.endDate = startDate;
		            }else {
		            	this.endDate = endDate;
		            }
		            
		            this.Condition=true;
		            this.column = column;
		        }

		        public DateRangeConditionStep withConditionIf(String key, String operator, String value) {
		        	
		             System.out.println("key "+key);
		        	
		          
					        	if(!evaluateCondition(key,operator,value)  && this.startDate != null  ) {
					        		
					        		  queryBuilder.append(" AND ").append(  this.column+key).append(" BETWEEN '")
				                        .append(this.startDate).append("' AND '").append(this.endDate).append("' ");
					        		 
					        		  keepwhere.append(" AND ").append(  this.column+key).append(" BETWEEN '")
					        		  .append(this.startDate).append("' AND '").append(this.endDate).append("' ");
					        		  
					        	}else {
					        		Condition =false;
					        	}
		          
		          
		            return this;
		        }

		        
		        public sqlservice defaultCondition(String elseValue) {
		        	
		        	if(Condition == false && this.startDate != null) {
		        		  queryBuilder.append(" AND ").append("F").append(this.faketablesize.get(0)).append(".").append(elseValue).append(" BETWEEN '")
	                        .append(this.startDate).append("' AND '").append(this.endDate).append("' ");
		        		  
		        		  keepwhere.append(" AND ").append("F").append(this.faketablesize.get(0)).append(".").append(elseValue).append(" BETWEEN '")
	                        .append(this.startDate).append("' AND '").append(this.endDate).append("' ");
		        	}

		            return sqlBuilder;
		        }
		        
		        
		        
		        public static boolean evaluateCondition(String key, String operator, String value) {
		        	
		       	 //System.out.println("value .... .... ... "+value);	
				// System.out.println("key .... .... ... "+key);
				// System.out.println("operator .... .... ... "+operator);
				
		        	if (key == null ) {
		        		return true;
		        	}
		        	
		        	 if (value == null ) {
		                 switch (operator) {
		                     case "!=":
		                         return key != null;
		                     case "=":
		                         return key == null;
		                     default:
		                         throw new IllegalArgumentException("Invalid operator: " + operator);
		                 }
		             } else {
		                 switch (operator) {
		                     case "!=":
		                         return !key.equals(value);
		                     case "=":
		                         return key.equals(value);
		                     case ">":
		                         return Integer.parseInt(key) > Integer.parseInt(value);
		                     case "<":
		                         return Integer.parseInt(key) < Integer.parseInt(value);
		                     case ">=":
		                         return Integer.parseInt(key) >= Integer.parseInt(value);
		                     case "<=":
		                         return Integer.parseInt(key) <= Integer.parseInt(value);
		                     default:
		                         throw new IllegalArgumentException("Invalid operator: " + operator);
		                 }
		             }
		        }
		        
		        
		        
		        
		        
		        
		      
		    }
	    
	   
}

