<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<form method="POST"   action="/savesetcalculation-workflow">
    <div class="card-body"> 
    
     <input type="hidden"  name="workflow_id" value="${workflow_id }">
     <input type="hidden"  name="id_refer" value="${id_refer }">
          
          
        <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
     <c:if test="${data.id > 0 }">
     <input type="hidden"  name="id" value="${data.id }">
     </c:if>
     
    		 <div class="mb-1 row">
                                               
                                                       
                                                         <div class="col-sm-3">Calculation name</div>
                                                         
                                          
                                                         
                                                                   <div class="col-sm-9">
                                           <input type="text"  name="row_name" class="form-control" value="${data.row_name }">
                                                        </div>
                                                           
                                                            </div>
                                   					 <div class="mb-1 row">
                                               
                                                       
                                                         <div class="col-sm-3">Number Variable1</div>
                                                         
                                                                   <div class="col-sm-3">
                                           <input type="number"  min="-99999" name="number_variable1" class="form-control" value="${data.number_variable1 }">
                                                        </div>
                                                                <div class="col-sm-3">Number Variable2</div>
                                                         
                                                                   <div class="col-sm-3">
                                           <input type="number" min="-99999" name="number_variable2" class="form-control" value="${data.number_variable2 }">
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
                                            