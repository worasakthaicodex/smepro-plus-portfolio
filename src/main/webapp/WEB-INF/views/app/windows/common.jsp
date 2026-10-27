<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>





 <input type="hidden" name="data_namerefer"  id="data_namerefer"  value="${edatas.data_namerefer }">       
 <c:set var="steptype" value="create,reference_continue,reference_modify" />     
 <c:set var="readonly" value="" /> 
 <c:set var="action_batch" value="${action_batch_insert},${action_batch_modify},${action_batch_continue}" />		
 




 
<c:if test="${docs == null &&  reference == null 
||  docs.current_step == 'Create' &&  reference == null 
|| fn:contains(sub_processes.edit_list,'edit_list_data') && docs.current_step == 'Review'    }"> 
 <c:set var="readonly" value="" /> 
</c:if>	   





<c:forEach items="${Tab.group_label}"   var="rows" varStatus="count">
<c:set var="fields" value="${rows.eformfields }" />  	
          
          
<div class="group-box">	
 <div class="group-label">${rows.group_label_name }</div>	
<c:forEach items="${fields }"   var="row" varStatus="count">
<c:set var="code_app" value="" />  
<c:set var="title" value="${row.field_label }" />   
 
	 <c:choose>
	    <c:when test="${row.field_id == input_supportdata1 }">
	    	 
	     	     <c:set var="supportdata" value="${work.workflow_maindb}" /> 
	     	     	     <c:set var="input_db" value="${input_db1}" /> 
	     	     	     	     <c:set var="input_db_n" value="1" /> 
	      <%@ include file="input/search_input.jspf" %>     
	
	    </c:when>
	    
	    
	    <c:when test="${row.field_id == input_supportdata2 }">
			 	    
			 	     <c:set var="supportdata" value="${work.workflow_maindb}" /> 
	   	     		<c:set var="input_db" value="${input_db2}" /> 
	   	     	     <c:set var="input_db_n" value="2" /> 
			
	      <%@ include file="input/search_input.jspf" %>    
	   
	    </c:when>
	    	   	    <c:when test="${row.field_id == input_supportdata3 }">

	
			 	     <c:set var="supportdata" value="${work.workflow_maindb}" /> 
	   	     <c:set var="input_db" value="${input_db3}" /> 
	   	     	     <c:set var="input_db_n" value="3" /> 
	      <%@ include file="input/search_input.jspf" %>    
	
	    </c:when>
	    
	   	    <c:when test="${row.field_id == input_supportdata4 }">

			
				     <c:set var="supportdata" value="${work.workflow_subdb}" /> 
	   	     <c:set var="input_db" value="${input_db4}" /> 
	   	     	     <c:set var="input_db_n" value="4" /> 
	      <%@ include file="input/search_input.jspf" %>    
	  
	    </c:when>
	       	    <c:when test="${row.field_id == input_supportdata5 }">

	   	     <c:set var="input_db" value="${input_db5}" /> 
	   	     <c:set var="input_db_n" value="5" /> 
	   	     				     <c:set var="supportdata" value="${work.workflow_subdb}" /> 
	      <%@ include file="input/search_input.jspf" %>     
	
	    </c:when>
	    
	            	    <c:when test="${row.field_id == input_supportdata6 }">
	
	   	     <c:set var="input_db" value="${input_db6}" /> 
	   	     <c:set var="input_db_n" value="6" /> 
	   	     				     <c:set var="supportdata" value="${work.workflow_subdb2}" /> 
	      <%@ include file="input/search_input.jspf" %>     
	     
	    </c:when>
	    
	           	    <c:when test="${row.field_id == input_supportdata7 }">
	
	   	     <c:set var="input_db" value="${input_db7}" /> 
	   	          <c:set var="input_db_n" value="7" /> 
	   	     				     <c:set var="supportdata" value="${work.workflow_subdb2}" /> 
	      <%@ include file="input/search_input.jspf" %>     
	
	    </c:when>
	     
	
	     
	     
	     
 	<c:when test="${autoid.contains(row.field_id.toString()) }">
 
     	<c:set var="code_app" value="${Code_app}" /> 
	      <%@ include file="input/autoid_input.jspf" %>  
	         
	    </c:when>
	    
	 <c:when test="${row.keymap != null  }">
	 
	      <%@ include file="input/new_input.jspf" %>     
	       
	    </c:when> 

	    <c:otherwise>
	    	
	      <%@ include file="input/common_input.jspf" %>  
	           
	    </c:otherwise>
	</c:choose>
        


                
</c:forEach>
            </div>
 
  </c:forEach>
