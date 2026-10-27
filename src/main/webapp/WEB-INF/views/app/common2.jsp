.<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="../common/mainhead.jspf" %>    



   
                  
 <input type="hidden" name="data_namerefer"  id="data_namerefer"  value="${edatas.data_namerefer }">       
 		<c:set var="steptype" value="clear" />   
 <c:set var="readonly" value="readonly" /> 
<c:if test="${docs == null ||  docs.current_step == 'Clear' || fn:contains(sub_processes.edit_list,'edit_list_data') && docs.current_step == 'Review'    }"> 
 <c:set var="readonly" value="" /> 
</c:if>



<c:forEach items="${eformfields }"   var="row" varStatus="count">



<c:set var="title" value="${row.field_label }" />   

<c:set var="code_app" value="" />  
	 <c:choose>
	 
	 
	 	<c:when test="${autoid.contains(row.field_id.toString()) &&  action_custom_clear.contains(row.field_id.toString()) }">
	 	     	<c:set var="code_app" value="${Code_app2}" /> 
	      <%@ include file="input/autoid_input.jspf" %>   
	    </c:when>
	
	
	 
		    <c:when test="${row.field_id == input_supportdata1  &&  action_custom_clear.contains(row.field_id.toString()) }"> 
		
	     	     <c:set var="supportdata" value="${work.workflow_maindb}" />  
	     	          	     <c:set var="input_db_n" value="1" /> 
	     	       	     <c:set var="input_db" value="${input_db1}" /> 
	      <%@ include file="input/search_input.jspf" %>     
	    </c:when>
	    
	       <c:when test="${row.field_id == input_supportdata2  &&  action_custom_clear.contains(row.field_id.toString())}">

	   	     <c:set var="supportdata" value="${work.workflow_maindb}" /> 
	   	          	     <c:set var="input_db_n" value="2" /> 
	   	         	     <c:set var="input_db" value="${input_db2}" /> 
	      <%@ include file="input/search_input.jspf" %>     
	    </c:when>
	    
	       	    <c:when test="${row.field_id == input_supportdata3  &&  action_custom_clear.contains(row.field_id.toString())}">
     	     <c:set var="input_db_n" value="3" /> 
	   	     <c:set var="supportdata" value="${work.workflow_maindb}" /> 
	   	         	     <c:set var="input_db" value="${input_db3}" /> 
	      <%@ include file="input/search_input.jspf" %>     
	    </c:when>
	    
	   	    <c:when test="${row.field_id == input_supportdata4  &&  action_custom_clear.contains(row.field_id.toString())}">
     	     <c:set var="input_db_n" value="4" /> 
	   	     <c:set var="supportdata" value="${work.workflow_subdb}" /> 
	   	         	     <c:set var="input_db" value="${input_db4}" /> 
	      <%@ include file="input/search_input.jspf" %>     
	    </c:when>
	    
	    
	  <c:when test="${row.field_id == input_supportdata5 &&  action_custom_clear.contains(row.field_id.toString()) }">
	      	     <c:set var="input_db_n" value="5" /> 
	   	     <c:set var="supportdata" value="${work.workflow_subdb}" /> 
	   	         	     <c:set var="input_db" value="${input_db5}" /> 
	      <%@ include file="input/search_input.jspf" %>     
	    </c:when>
	    
	    	  <c:when test="${row.field_id == input_supportdata6 &&  action_custom_clear.contains(row.field_id.toString()) }">
	    	       	     <c:set var="input_db_n" value="6" /> 
	   	     <c:set var="supportdata" value="${work.workflow_subdb2}" /> 
	   	         	     <c:set var="input_db" value="${input_db6}" /> 
	      <%@ include file="input/search_input.jspf" %>     
	    </c:when>
	    
	    
	    	  <c:when test="${row.field_id == input_supportdata7 &&  action_custom_clear.contains(row.field_id.toString()) }">
	    	       	     <c:set var="input_db_n" value="7" /> 
	   	     <c:set var="supportdata" value="${work.workflow_subdb2}" /> 
	   	         	     <c:set var="input_db" value="${input_db7}" /> 
	      <%@ include file="input/search_input.jspf" %>     
	      
	    </c:when>
	    
	    
	    
	    
	 		  <c:when test="${ action_custom_clear.contains(row.field_id.toString()) && row.keymap.type == steptype  }">  
	 	   <%@ include file="input/new_input.jspf" %>   
	 	   </c:when>
	 	
	 	  <c:when test="${ action_custom_clear.contains(row.field_id.toString()) }">  
	 	  <%@ include file="input/common_input.jspf" %> 
	 	   </c:when>
	 	 
	 		 	   
	 	 
	  <c:when test="${row.keymap.type != steptype }"></c:when>  	 
		 
		 	 <c:when test="${row.keymap != null && fn:contains(action_batch_clear,row.keymap.mainkey)  && steptype == 'clear' }"></c:when>  	
		 	 
		 
	

	 
	 	
	 	<c:when test="${ action_custom_clear.contains(row.field_id.toString()) && steptype == 'clear'  }">
	 	<c:set var="steptypeclear" value="${steptype }" /> 
	 	  <%@ include file="input/common_input.jspf" %>  
	 	</c:when>
	 	
	 	
	 	
	  
	    

	    

	    
	 <c:when test="${row.keymap != null}">
	      <%@ include file="input/new_input.jspf" %>     
	    </c:when> 

	
	</c:choose>

                
</c:forEach>

           
  
            

