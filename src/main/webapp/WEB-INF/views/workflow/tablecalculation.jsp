<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

 <link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">

 <link href="<c:url value="/resources/app-assets/vendors/css/forms/select/select2.min.css" />" rel="stylesheet">
 
	  <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
	      <script src="<c:url value="/resources/app-assets/vendors/js/forms/select/select2.full.min.js" />"></script>
  <script src="<c:url value="/resources/app-assets/js/scripts/forms/form-select2.js" />"></script>


<form method="POST"   action="/savetablecalculation-workflow">
    <div class="card-body"> 
    
    <input type="hidden"  name="workflow_id" value="${workflow_id }">
     <input type="hidden"  name="id_refer" value="${id_refer }">
          
          
        <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
     <c:if test="${data.id > 0 }">
     <input type="hidden"  name="id" value="${data.id }">
     </c:if>
     
    
    		 <div class="mb-1 row">
                                               
                                                       
                                                         <div class="col-sm-3">Footer calculation Name</div>
                                                         
                                          
                                                         
                                                                   <div class="col-sm-9">
                                           <input type="text" min="0" name="footer_name" class="form-control" value="${data.footer_name }">
                                                        </div>
                                                           
                                                            </div>
                                                            
                     
                         		 <div class="mb-1 row">
                                               
                                                       
                                                         <div class="col-sm-3">Footer Title 1</div>
                                                         
                                          
                                                         
                                                                   <div class="col-sm-9">
                                         <select class="form-control select2" class="form-control" name="footer_title1"  multiple="multiple"> 
                                                       
                                                   
                                                        <option  <c:if test='${ fn:contains(data.footer_title1,"t-1")}'>selected</c:if> value="t-1">Text Variable1</option>      
                                                       <option <c:if test='${ fn:contains(data.footer_title1,"t-2")}'>selected</c:if> value="t-2">Text Variable2</option>    
                                                              <option <c:if test='${ fn:contains(data.footer_title1,"t-3")}'>selected</c:if> value="t-3">Text Variable3</option>
                                                      <option <c:if test='${ fn:contains(data.footer_title1,"n-1")}'>selected</c:if>  value="n-1">Number Variable1</option>
                                                          <option <c:if test='${ fn:contains(data.footer_title1,"n-2")}'>selected</c:if> value="n-2">Number Variable2</option>
                                                  		  <option <c:if test='${ fn:contains(data.footer_title1,"n-3")}'>selected</c:if> value="n-3">Number Variable3</option>
                                                  			 <option <c:if test='${ fn:contains(data.footer_title1,"n-4")}'>selected</c:if> value="n-4">Number Variable4</option>
                                                        <option <c:if test='${ fn:contains(data.footer_title1,"n-5")}'>selected</c:if> value="n-5">Number Variable5</option>     
                                                         <option <c:if test='${ fn:contains(data.footer_title1,"t-4")}'>selected</c:if> value="t-4">Text Variable4</option>    
                                                         <option <c:if test='${ fn:contains(data.footer_title1,"t-5")}'>selected</c:if> value="t-5">Text Variable5</option>    
                                                         <option <c:if test='${ fn:contains(data.footer_title1,"t-6")}'>selected</c:if> value="t-6">Text Variable6</option>
                                                         </select>
                                                        </div>
                                                           
                                                            </div>
                                                            
                                     	 <div class="mb-1 row">
                                               
                                                       
                                                         <div class="col-sm-3">Footer Title 2</div>
                                                         
                                          
                                                         
                                                                   <div class="col-sm-9">
                                          <select class="form-control select2" class="form-control" name="footer_title2"  multiple="multiple"> 
                                                       
                                                        <c:set var="listcheck" value="ADD-DRIVE" />
  
                                                        <option  <c:if test='${ fn:contains(data.footer_title2,"t-1")}'>selected</c:if> value="t-1">Text Variable1</option>      
                                                       <option <c:if test='${ fn:contains(data.footer_title2,"t-2")}'>selected</c:if> value="t-2">Text Variable2</option>    
                                                              <option <c:if test='${ fn:contains(data.footer_title2,"t-3")}'>selected</c:if> value="t-3">Text Variable3</option>
                                                      <option <c:if test='${ fn:contains(data.footer_title2,"n-1")}'>selected</c:if>  value="n-1">Number Variable1</option>
                                                          <option <c:if test='${ fn:contains(data.footer_title2,"n-2")}'>selected</c:if> value="n-2">Number Variable2</option>
                                                  		  <option <c:if test='${ fn:contains(data.footer_title2,"n-3")}'>selected</c:if> value="n-3">Number Variable3</option>
                                                  			 <option <c:if test='${ fn:contains(data.footer_title2,"n-4")}'>selected</c:if> value="n-4">Number Variable4</option>
                                                        <option <c:if test='${ fn:contains(data.footer_title2,"n-5")}'>selected</c:if> value="n-5">Number Variable5</option>     
                                                         <option <c:if test='${ fn:contains(data.footer_title2,"t-4")}'>selected</c:if> value="t-4">Text Variable4</option>    
                                                         <option <c:if test='${ fn:contains(data.footer_title2,"t-5")}'>selected</c:if> value="t-5">Text Variable5</option>    
                                                         <option <c:if test='${ fn:contains(data.footer_title2,"t-6")}'>selected</c:if> value="t-6">Text Variable6</option>
                                                         </select>
                                                        </div>
                                                           
                                                            </div>
                                                            
                                                            	 <div class="mb-1 row">
                                               
                                                       
                                                         <div class="col-sm-3">Footer Title 3</div>
                                                         
                                          
                                                         
                                                                   <div class="col-sm-9">
                                                <select class="form-control select2" class="form-control" name="footer_title3"  multiple="multiple"> 
                                                       
                                                        <c:set var="listcheck" value="ADD-DRIVE" />
  
                                                        <option  <c:if test='${ fn:contains(data.footer_title3,"t-1")}'>selected</c:if> value="t-1">Text Variable1</option>      
                                                       <option <c:if test='${ fn:contains(data.footer_title3,"t-2")}'>selected</c:if> value="t-2">Text Variable2</option>    
                                                              <option <c:if test='${ fn:contains(data.footer_title3,"t-3")}'>selected</c:if> value="t-3">Text Variable3</option>
                                                      <option <c:if test='${ fn:contains(data.footer_title3,"n-1")}'>selected</c:if>  value="n-1">Number Variable1</option>
                                                          <option <c:if test='${ fn:contains(data.footer_title3,"n-2")}'>selected</c:if> value="n-2">Number Variable2</option>
                                                  		  <option <c:if test='${ fn:contains(data.footer_title3,"n-3")}'>selected</c:if> value="n-3">Number Variable3</option>
                                                  			 <option <c:if test='${ fn:contains(data.footer_title3,"n-4")}'>selected</c:if> value="n-4">Number Variable4</option>
                                                        <option <c:if test='${ fn:contains(data.footer_title3,"n-5")}'>selected</c:if> value="n-5">Number Variable5</option>     
                                                         <option <c:if test='${ fn:contains(data.footer_title3,"t-4")}'>selected</c:if> value="t-4">Text Variable4</option>    
                                                         <option <c:if test='${ fn:contains(data.footer_title3,"t-5")}'>selected</c:if> value="t-5">Text Variable5</option>    
                                                         <option <c:if test='${ fn:contains(data.footer_title3,"t-6")}'>selected</c:if> value="t-6">Text Variable6</option>
                                                         </select>
                                                        </div>
                                                           
                                                            </div>
                                                                                   
                                                        <hr>    
                                   					 <div class="mb-1 row">
                                               
                                                       
                                                         <div class="col-sm-3">Number Variable1</div>
                                                         
                                                                   <div class="col-sm-3">
                                           <input type="number"   min="-99999999" name="number_variable1" class="form-control" value="${data.number_variable1 }">
                                                        </div>
                                                                <div class="col-sm-3">Number Variable2</div>
                                                         
                                                                   <div class="col-sm-3">
                                           <input type="number" min="-99999999" name="number_variable2" class="form-control" value="${data.number_variable2 }">
                                                        </div>
                                                           
                                                            </div>
                                                            
                                                       			 <div class="mb-1 row">
                                               
                                                       
                                                         <div class="col-sm-3">Text Variable1</div>
                                                         
                                                                   <div class="col-sm-3">
                                           <input type="text"  name="text_variable1" class="form-control" value="${data.text_variable1 }">
                                                        </div>
                                                                <div class="col-sm-3">Text Variable2</div>
                                                         
                                                                   <div class="col-sm-3">
                                           <input type="text"  name="text_variable2" class="form-control" value="${data.text_variable2 }">
                                                        </div>
                                                           
                                                            </div>
                                                                 
                                                                    			 <div class="mb-1 row">
                                               
                                                       
                                                         <div class="col-sm-3">Text Variable3</div>
                                                         
                                                                   <div class="col-sm-3">
                                               <input type="text"  name="text_variable3" class="form-control" value="${data.text_variable3 }">
                                                        </div>
                                                                <div class="col-sm-3">Text Variable4</div>
                                                         
                                                                   <div class="col-sm-3">
                                               <input type="text"  name="text_variable4" class="form-control" value="${data.text_variable4 }">
                                                        </div>
                                                           
                                                            </div>
                                                            
                                                                          			 <div class="mb-1 row">
                                               
                                                       
                                                         <div class="col-sm-3">Text Variable5</div>
                                                         
                                                                   <div class="col-sm-3">
                                               <input type="text"  name="text_variable5" class="form-control" value="${data.text_variable5 }">
                                                        </div>
                                                                <div class="col-sm-3">Text Variable6</div>
                                                         
                                                                   <div class="col-sm-3">
                                               <input type="text"  name="text_variable6" class="form-control" value="${data.text_variable6 }">
                                                        </div>
                                                           
                                                            </div>
                                                            
                                                                <hr>     
                                                        	 <div class="mb-1 row">        
                                                         <div class="col-sm-3">Column</div>
                                                         
                                                                   <div class="col-sm-3">
                                           Operation
                                                        </div>
                                                                <div class="col-sm-3">Column</div>
                                                         
                                                                      <div class="col-sm-3">AS</div>
                                                           
                                                            </div>
                                    	
                                                         	
                                                         	
                                                         	 <div class="mb-1 row">
                                               
                                                       
                                                         <div class="col-sm-3">
                                                         <select class="form-control" name="start_column1">
                                                            <option <c:if test="${data.start_column1 == 'N/A' }">selected</c:if> value="N/A">N/A</option>
                                                      <option value="-1"  <c:if test="${data.start_column1 == '-1' }">selected</c:if>>Number Variable1</option>
                                                          <option value="-2" <c:if test="${data.start_column1 == '-2' }">selected</c:if>>Number Variable2</option>
                                                  		  <option value="-3" <c:if test="${data.start_column1 == '-3' }">selected</c:if>>Number Variable3</option>
                                                  			 <option value="-4" <c:if test="${data.start_column1 == '-4' }">selected</c:if>>Number Variable4</option>
                                                        <option value="-5" <c:if test="${data.start_column1 == '-5' }">selected</c:if>>Number Variable5</option>     
                                              
                                               <c:forEach items="${eformfields }" var="fields" >     
                                                        <option value="${fields.field_id}" <c:if test="${data.start_column1 == fields.field_id.toString()  }">selected</c:if>>${fields.field_label}</option>         
                                                </c:forEach>        
                                                               
                                                        
                                                         </select>
                                                         </div>
                                                         
                                                                   <div class="col-sm-3" >
                                                   <select class="form-control" name="operation1">
                                                      <option <c:if test="${data.operation1 == 'N/A' }">selected</c:if>  value="N/A" >N/A</option>
                                                      <option <c:if test="${data.operation1 == 'firstrow' }">selected</c:if> value="firstrow">First row</option>
                                                         <option <c:if test="${data.operation1 == 'endrow' }">selected</c:if> value="endrow">End row</option>
                                                          <option <c:if test="${data.operation1 == 'average' }">selected</c:if> value="average">Average</option>
                                                           <option <c:if test="${data.operation1 == 'sum' }">selected</c:if> value="sum">Sum</option>
                                                            <option <c:if test="${data.operation1 == 'count' }">selected</c:if> value="count">Count</option>
                                                         <option <c:if test="${data.operation1 == '+' }">selected</c:if> value="+">+</option>
                                                          <option <c:if test="${data.operation1 == '-' }">selected</c:if> value="-">-</option>
                                                          <option  <c:if test="${data.operation1 == '*' }">selected</c:if> value="*">*</option>
                                                          <option  <c:if test="${data.operation1 == '/' }">selected</c:if> value="/">/</option>
                                                          <option  <c:if test="${data.operation1 == '+%' }">selected</c:if> value="+%">+%</option>
                                                          <option  <c:if test="${data.operation1 == '-%' }">selected</c:if> value="-%">-%</option>
                                                         </select>
                                                        </div>
                                                                <div class="col-sm-3"> 
                                                                <select class="form-control" name="column1">
                                                                             <option <c:if test="${data.column1 == 'N/A' }">selected</c:if> value="N/A">N/A</option>
                                                      <option value="-1"  <c:if test="${data.column1 == '-1' }">selected</c:if>>Number Variable1</option>
                                                          <option value="-2" <c:if test="${data.column1 == '-2' }">selected</c:if>>Number Variable2</option>
                                                  		  <option value="-3" <c:if test="${data.column1 == '-3' }">selected</c:if>>Number Variable3</option>
                                                  		<option value="-4" <c:if test="${data.column1 == '-4' }">selected</c:if>>Number Variable4</option>
                                                        <option value="-5" <c:if test="${data.column1 == '-5' }">selected</c:if>>Number Variable5</option>     
                                              
                                               <c:forEach items="${eformfields }" var="fields" >     
                                                        <option value="${fields.field_id}" <c:if test="${data.column1 == fields.field_id.toString()  }">selected</c:if>>${fields.field_label}</option>         
                                                </c:forEach>        
                                                              
                                                         </select>
                                                         </div>
                                                         
                                                                   <div class="col-sm-3">
                                            <select class="form-control" name="as_to1">
                                     <option <c:if test="${data.as_to1 == 'N/A' }">selected</c:if> value="N/A">N/A</option>
                                                      <option value="-1"  <c:if test="${data.as_to1 == '-1' }">selected</c:if>>Number Variable1</option>
                                                          <option value="-2" <c:if test="${data.as_to1 == '-2' }">selected</c:if>>Number Variable2</option>
                                                  		  <option value="-3" <c:if test="${data.as_to1 == '-3' }">selected</c:if>>Number Variable3</option>
                                                  		<option value="-4" <c:if test="${data.as_to1 == '-4' }">selected</c:if>>Number Variable4</option>
                                                        <option value="-5" <c:if test="${data.as_to1 == '-5' }">selected</c:if>>Number Variable5</option>     
                                              
                                               <c:forEach items="${eformfields }" var="fields" >     
                                                        <option value="${fields.field_id}" <c:if test="${data.as_to1 == fields.field_id.toString()  }">selected</c:if>>${fields.field_label}</option>         
                                                </c:forEach>           
                                                         
                                                         </select>
                                                        </div>
                                                           
                                                            </div>    
                                                            
                                                            
                                                            
                                                            
                                                      
                                                                   	
                                                         	 
                                                         	
                                                         
                                                         	 
                                                         
                                                         	
                                                         	 <div class="mb-1 row">
                                               
                                                       
                                                         <div class="col-sm-3">
                                                         <select class="form-control" name="start_column2">
                                                            <option <c:if test="${data.start_column2 == 'N/A' }">selected</c:if> value="N/A">N/A</option>
                                                      <option value="-1"  <c:if test="${data.start_column2 == '-1' }">selected</c:if>>Number Variable1</option>
                                                          <option value="-2" <c:if test="${data.start_column2 == '-2' }">selected</c:if>>Number Variable2</option>
                                                  		  <option value="-3" <c:if test="${data.start_column2 == '-3' }">selected</c:if>>Number Variable3</option>
                                                  			 <option value="-4" <c:if test="${data.start_column2 == '-4' }">selected</c:if>>Number Variable4</option>
                                                        <option value="-5" <c:if test="${data.start_column2 == '-5' }">selected</c:if>>Number Variable5</option>     
                                              
                                               <c:forEach items="${eformfields }" var="fields" >     
                                                        <option value="${fields.field_id}" <c:if test="${data.start_column2 == fields.field_id.toString()  }">selected</c:if>>${fields.field_label}</option>         
                                                </c:forEach>        
                                                               
                                                        
                                                         </select>
                                                         </div>
                                                         
                                                                   <div class="col-sm-3" >
                                                   <select class="form-control" name="operation2">
                                                      <option <c:if test="${data.operation2 == 'N/A' }" >selected</c:if> value="N/A">N/A</option>
                                                      
                                                            <option <c:if test="${data.operation2 == 'firstrow' }">selected</c:if> value="firstrow">First row</option>
                                                         <option <c:if test="${data.operation2 == 'endrow' }">selected</c:if> value="endrow">End row</option>
                                                          <option <c:if test="${data.operation2 == 'average' }">selected</c:if> value="average">Average</option>
                                                           <option <c:if test="${data.operation2 == 'sum' }">selected</c:if> value="sum">Sum</option>
                                                            <option <c:if test="${data.operation2 == 'count' }">selected</c:if> value="count">Count</option>
                                                      
                                                         <option <c:if test="${data.operation2 == '+' }">selected</c:if> value="+">+</option>
                                                          <option <c:if test="${data.operation2 == '-' }">selected</c:if> value="-">-</option>
                                                          <option  <c:if test="${data.operation2 == '*' }">selected</c:if> value="*">*</option>
                                                          <option  <c:if test="${data.operation2 == '/' }">selected</c:if> value="/">/</option>
                                                          <option  <c:if test="${data.operation2 == '+%' }">selected</c:if> value="+%">+%</option>
                                                          <option  <c:if test="${data.operation2 == '-%' }">selected</c:if> value="-%">-%</option>
                                                         </select>
                                                        </div>
                                                                <div class="col-sm-3"> 
                                                                <select class="form-control" name="column2">
                                                                             <option <c:if test="${data.column2 == 'N/A' }">selected</c:if> value="N/A">N/A</option>
                                                      <option value="-1"  <c:if test="${data.column2 == '-1' }">selected</c:if>>Number Variable1</option>
                                                          <option value="-2" <c:if test="${data.column2 == '-2' }">selected</c:if>>Number Variable2</option>
                                                  		  <option value="-3" <c:if test="${data.column2 == '-3' }">selected</c:if>>Number Variable3</option>
                                                  		<option value="-4" <c:if test="${data.column2 == '-4' }">selected</c:if>>Number Variable4</option>
                                                        <option value="-5" <c:if test="${data.column2 == '-5' }">selected</c:if>>Number Variable5</option>     
                                              
                                               <c:forEach items="${eformfields }" var="fields" >     
                                                        <option value="${fields.field_id}" <c:if test="${data.column2 == fields.field_id.toString()  }">selected</c:if>>${fields.field_label}</option>         
                                                </c:forEach>        
                                                              
                                                         </select>
                                                         </div>
                                                         
                                                                   <div class="col-sm-3">
                                            <select class="form-control" name="as_to2">
                                     <option <c:if test="${data.as_to2 == 'N/A' }">selected</c:if> value="N/A">N/A</option>
                                                      <option value="-1"  <c:if test="${data.as_to2 == '-1' }">selected</c:if>>Number Variable1</option>
                                                          <option value="-2" <c:if test="${data.as_to2 == '-2' }">selected</c:if>>Number Variable2</option>
                                                  		  <option value="-3" <c:if test="${data.as_to2 == '-3' }">selected</c:if>>Number Variable3</option>
                                                  		<option value="-4" <c:if test="${data.as_to2 == '-4' }">selected</c:if>>Number Variable4</option>
                                                        <option value="-5" <c:if test="${data.as_to2 == '-5' }">selected</c:if>>Number Variable5</option>     
                                              
                                               <c:forEach items="${eformfields }" var="fields" >     
                                                        <option value="${fields.field_id}" <c:if test="${data.as_to2 == fields.field_id.toString()  }">selected</c:if>>${fields.field_label}</option>         
                                                </c:forEach>           
                                                         
                                                         </select>
                                                        </div>
                                                           
                                                            </div>  	 
                                                         	 
                                                         	 
                                                         	 	
                                                         	 <div class="mb-1 row">
                                               
                                                       
                                                         <div class="col-sm-3">
                                                         <select class="form-control" name="start_column3">
                                                            <option <c:if test="${data.start_column3 == 'N/A' }">selected</c:if> value="N/A">N/A</option>
                                                      <option value="-1"  <c:if test="${data.start_column3 == '-1' }">selected</c:if>>Number Variable1</option>
                                                          <option value="-2" <c:if test="${data.start_column3 == '-2' }">selected</c:if>>Number Variable2</option>
                                                  		  <option value="-3" <c:if test="${data.start_column3 == '-3' }">selected</c:if>>Number Variable3</option>
                                                  			 <option value="-4" <c:if test="${data.start_column3 == '-4' }">selected</c:if>>Number Variable4</option>
                                                        <option value="-5" <c:if test="${data.start_column3 == '-5' }">selected</c:if>>Number Variable5</option>     
                                              
                                               <c:forEach items="${eformfields }" var="fields" >     
                                                        <option value="${fields.field_id}" <c:if test="${data.start_column3 == fields.field_id.toString()  }">selected</c:if>>${fields.field_label}</option>         
                                                </c:forEach>        
                                                               
                                                        
                                                         </select>
                                                         </div>
                                                         
                                                                   <div class="col-sm-3" >
                                                   <select class="form-control" name="operation3">
                                                      <option <c:if test="${data.operation3 == 'N/A' }" >selected</c:if> value="N/A">N/A</option>
                                                      
                                                                 <option <c:if test="${data.operation3 == 'firstrow' }">selected</c:if> value="firstrow">First row</option>
                                                         <option <c:if test="${data.operation3 == 'endrow' }">selected</c:if> value="endrow">End row</option>
                                                          <option <c:if test="${data.operation3 == 'average' }">selected</c:if> value="average">Average</option>
                                                           <option <c:if test="${data.operation3 == 'sum' }">selected</c:if> value="sum">Sum</option>
                                                            <option <c:if test="${data.operation3 == 'count' }">selected</c:if> value="count">Count</option>
                                                      
                                                      
                                                         <option <c:if test="${data.operation3 == '+' }">selected</c:if> value="+">+</option>
                                                          <option <c:if test="${data.operation3 == '-' }">selected</c:if> value="-">-</option>
                                                          <option  <c:if test="${data.operation3 == '*' }">selected</c:if> value="*">*</option>
                                                          <option  <c:if test="${data.operation3 == '/' }">selected</c:if> value="/">/</option>
                                                          <option  <c:if test="${data.operation3 == '+%' }">selected</c:if> value="+%">+%</option>
                                                          <option  <c:if test="${data.operation3 == '-%' }">selected</c:if> value="-%">-%</option>
                                                         </select>
                                                        </div>
                                                                <div class="col-sm-3"> 
                                                                <select class="form-control" name="column3">
                                                                             <option <c:if test="${data.column3 == 'N/A' }">selected</c:if> value="N/A">N/A</option>
                                                      <option value="-1"  <c:if test="${data.column3 == '-1' }">selected</c:if>>Number Variable1</option>
                                                          <option value="-2" <c:if test="${data.column3 == '-2' }">selected</c:if>>Number Variable2</option>
                                                  		  <option value="-3" <c:if test="${data.column3 == '-3' }">selected</c:if>>Number Variable3</option>
                                                  		<option value="-4" <c:if test="${data.column3 == '-4' }">selected</c:if>>Number Variable4</option>
                                                        <option value="-5" <c:if test="${data.column3 == '-5' }">selected</c:if>>Number Variable5</option>     
                                              
                                               <c:forEach items="${eformfields }" var="fields" >     
                                                        <option value="${fields.field_id}" <c:if test="${data.column3 == fields.field_id.toString()  }">selected</c:if>>${fields.field_label}</option>         
                                                </c:forEach>        
                                                              
                                                         </select>
                                                         </div>
                                                         
                                                                   <div class="col-sm-3">
                                            <select class="form-control" name="as_to3">
                                   					  <option <c:if test="${data.as_to3 == 'N/A' }">selected</c:if> value="N/A">N/A</option>
                                                      <option value="-1"  <c:if test="${data.as_to3 == '-1' }">selected</c:if>>Number Variable1</option>
                                                          <option value="-2" <c:if test="${data.as_to3 == '-2' }">selected</c:if>>Number Variable2</option>
                                                  		  <option value="-3" <c:if test="${data.as_to3 == '-3' }">selected</c:if>>Number Variable3</option>
                                                  		<option value="-4" <c:if test="${data.as_to3 == '-4' }">selected</c:if>>Number Variable4</option>
                                                        <option value="-5" <c:if test="${data.as_to3 == '-5' }">selected</c:if>>Number Variable5</option>     
                                              
                                               <c:forEach items="${eformfields }" var="fields" >     
                                                        <option value="${fields.field_id}" <c:if test="${data.as_to3 == fields.field_id.toString()  }">selected</c:if>>${fields.field_label}</option>         
                                                </c:forEach>           
                                                         
                                                         </select>
                                                        </div>
                                                           
                                                            </div>       
                                                            
                                                            
 </div>      
                     <div class="modal-footer" >
                                                              <a  href="/docworkflowapp-workflow"  target="_blank" class="btn btn-light waves-effect waves-float waves-light" >Doc</a>
                                                                <input type="Submit"  value="Save"  class="btn btn-primary waves-effect waves-float waves-light">
                                                            </div>                               
                                                               
   </form>                                