<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="../common/mainhead.jspf" %>    




 <input type="hidden" name="data_namerefer"  id="data_namerefer"  value="${edatas.data_namerefer }">       
 <c:set var="steptype" value="create,reference_continue,reference_modify" />     
 <c:set var="readonly" value="readonly" /> 
 <c:set var="action_batch" value="${action_batch_insert},${action_batch_modify},${action_batch_continue}" />		
 

 
<c:if test="${docs == null &&  reference == null 
||  docs.current_step == 'Create' &&  reference == null 
|| fn:contains(sub_processes.edit_list,'edit_list_data') && docs.current_step == 'Review'   &&  reference == null    }"> 
 <c:set var="readonly" value="" /> 
</c:if>	   






  
     <div class="row">
                  
 
      
                  
<c:forEach items="${eformfields }"   var="row" varStatus="count">
<c:set var="code_app" value="" />  
<c:set var="title" value="${row.field_label }" />   
	

	 <c:choose>

	   <c:when test="${row.field_type.equals('Title') }">


<div class="card-header pb-1">
                                <h4 class="card-title"  title="KET->${row.keymap.change_format }">${title }</h4>
                          
                            </div>
	   
	   </c:when>  	
	 
	    <c:when test="${row.keymap != null && actionBatchList.contains(row.keymap.mainkey)  }"></c:when>  	
	 	<c:when test="${ action_custom_clear.contains(row.field_id.toString())   }"></c:when>
	   
	   
	    <c:when test="${row.field_id == input_supportdata1 }">
	    	   <div class="col-sm-${row.input_size }" title="1_${input_db1}">
	     	     <c:set var="supportdata" value="${work.workflow_maindb}" /> 
	     	     	     <c:set var="input_db" value="${input_db1}" /> 
	     	     	     	     <c:set var="input_db_n" value="1" /> 
	      <%@ include file="input/search_input.jspf" %>     
	       </div>
	    </c:when>
	    
	    
	    <c:when test="${row.field_id == input_supportdata2 }">
			 	    
			 	     <c:set var="supportdata" value="${work.workflow_maindb}" /> 
	   	     		<c:set var="input_db" value="${input_db2}" /> 
	   	     	     <c:set var="input_db_n" value="2" /> 
			 <div class="col-sm-${row.input_size }" title="2_${input_db2}"> 

	      <%@ include file="input/search_input.jspf" %>    
	         </div> 
	    </c:when>
	    	   	    <c:when test="${row.field_id == input_supportdata3 }">

			 <div class="col-sm-${row.input_size }" title="3_${input_db3}">
			 	     <c:set var="supportdata" value="${work.workflow_maindb}" /> 
	   	     <c:set var="input_db" value="${input_db3}" /> 
	   	     	     <c:set var="input_db_n" value="3" /> 
	      <%@ include file="input/search_input.jspf" %>    
	         </div> 
	    </c:when>
	    
	   	    <c:when test="${row.field_id == input_supportdata4 }">

			 <div class="col-sm-${row.input_size }" >
				     <c:set var="supportdata" value="${work.workflow_subdb}" /> 
	   	     <c:set var="input_db" value="${input_db4}" /> 
	   	     	     <c:set var="input_db_n" value="4" /> 
	      <%@ include file="input/search_input.jspf" %>    
	         </div> 
	    </c:when>
	       	    <c:when test="${row.field_id == input_supportdata5 }">
	 <div class="col-sm-${row.input_size }" >
	   	     <c:set var="input_db" value="${input_db5}" /> 
	   	     <c:set var="input_db_n" value="5" /> 
	   	     				     <c:set var="supportdata" value="${work.workflow_subdb}" /> 
	      <%@ include file="input/search_input.jspf" %>     
	        </div> 
	    </c:when>
	    
	            	    <c:when test="${row.field_id == input_supportdata6 }">
	 <div class="col-sm-${row.input_size }" >
	   	     <c:set var="input_db" value="${input_db6}" /> 
	   	     <c:set var="input_db_n" value="6" /> 
	   	     				     <c:set var="supportdata" value="${work.workflow_subdb2}" /> 
	      <%@ include file="input/search_input.jspf" %>     
	        </div> 
	    </c:when>
	    
	           	    <c:when test="${row.field_id == input_supportdata7 }">
	 <div class="col-sm-${row.input_size }" >
	   	     <c:set var="input_db" value="${input_db7}" /> 
	   	          <c:set var="input_db_n" value="7" /> 
	   	     				     <c:set var="supportdata" value="${work.workflow_subdb2}" /> 
	      <%@ include file="input/search_input.jspf" %>     
	        </div> 
	    </c:when>
	     
	
	     
	     
	     
 	<c:when test="${autoid.contains(row.field_id.toString()) }">
 		 <div class="col-sm-${row.input_size }" >
     	<c:set var="code_app" value="${Code_app}" /> 
	      <%@ include file="input/autoid_input.jspf" %>  
	             </div>    
	    </c:when>
	    
	 <c:when test="${row.keymap != null}">
	 	 <div class="col-sm-${row.input_size }" >
	 	 
	      <%@ include file="input/new_input.jspf" %>     
	         </div> 
	    </c:when> 

	    <c:otherwise>
	    	 <div class="col-sm-${row.input_size }" >
	      <%@ include file="input/common_input.jspf" %>  
	             </div> 
	    </c:otherwise>
	</c:choose>
        

       
                
</c:forEach>

   
   </div>

         

