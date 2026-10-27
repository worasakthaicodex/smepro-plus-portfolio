<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<form method="POST"   action="/savecalculationlinks-workflow">
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
                                           <input type="text" min="0" name="rollup_name" class="form-control" value="${data.rollup_name }">
                                                        </div>
                                                           
                                                            </div>
                                                            
                     
                         		
                                      
                                       <div class="mb-1 row">
                                                 <div class="col-sm-3">Condition  </div>
                                                       
                                                         <div class="col-sm-3">
                                                         <select class="form-control" name="condition_column1">
                                                           <option value="0">N/A</option>
                                                  <c:forEach items="${eformfields }" var="rows" >
                                                         <option value="${rows.field_id }" <c:if test="${data.condition_column1 ==  rows.field_id }">selected</c:if> >${rows.field_label }</option>
                                                       		</c:forEach>
                                                  </select>
                                               
                                                         </div>
                                                         
                                                                   <div class="col-sm-3">
                                                   <select class="form-control" name="condition_operation1">
                                                      <option  <c:if test="${data.condition_column1 ==  'N/A' }">selected</c:if> value="N/A">N/A</option>
                                                            <option <c:if test="${data.condition_column1 ==  '=' }">selected</c:if> value="=">=</option>
                                                         <option <c:if test="${data.condition_column1 ==  '!=' }">selected</c:if> value="!=">!=</option>
                                                          <option <c:if test="${data.condition_column1 ==  '>=' }">selected</c:if> value=">=">>=</option>
                                                           <option <c:if test="${data.condition_column1 ==  '<=' }">selected</c:if> value="<="><=</option>
                                                            <option <c:if test="${data.condition_column1 ==  'Like' }">selected</c:if> value="Like">Like</option>
                                                         <option <c:if test="${data.condition_column1 ==  'NULL' }">selected</c:if> value="NULL">NULL</option>
                                                          <option <c:if test="${data.condition_column1 ==  'NOT NULL' }">selected</c:if>  value="NOT NULL">NOT NULL</option>
                                                          <option <c:if test="${data.condition_column1 ==  'IN' }">selected</c:if> value="IN">IN</option>
                                                             <option <c:if test="${data.condition_column1 ==  'NOT IN' }">selected</c:if> value="NOT IN">NOT IN</option>
                                                         </select>
                                                        </div>
                                                                <div class="col-sm-3"> 
                                                 <input type="text"  name="condition_as_to1" class="form-control" value="${data.condition_as_to1 }">
                                                         
                                                         </div>
                                                         
                                      </div>
                                      
                                            
                                               <div class="mb-1 row">
                                                 <div class="col-sm-3">
                                                  <select class="form-control" name="condition2">
                                                                  <option value="0">N/A</option>
                                                      <option  <c:if test="${data.condition2 ==  'AND' }">selected</c:if> value="AND">AND</option>
                                                            <option <c:if test="${data.condition2 ==  'OR' }">selected</c:if> value="OR">OR</option>
                                                       
                                                         </select>
                                                 
                                                 
                                                  </div>
                                                       
                                                         <div class="col-sm-3">
                                                         <select class="form-control" name="condition_column2">
                                                           <option value="0">N/A</option>
                                                  <c:forEach items="${eformfields }" var="rows" >
                                                         <option value="${rows.field_id }" <c:if test="${data.condition_column2 ==  rows.field_id }">selected</c:if> >${rows.field_label }</option>
                                                       		</c:forEach>
                                                  </select>
                                                          
                                                     
                                                         </div>
                                                         
                                                                   <div class="col-sm-3">
                                                   <select class="form-control" name="condition_operation2">
                                                      <option  <c:if test="${data.condition_operation2 ==  'N/A' }">selected</c:if>  value="N/A">N/A</option>
                                                            <option <c:if test="${data.condition_operation2 ==  '=' }">selected</c:if> value="=">=</option>
                                                         <option <c:if test="${data.condition_operation2 ==  '!=' }">selected</c:if> value="!=">!=</option>
                                                          <option <c:if test="${data.condition_operation2 ==  '>=' }">selected</c:if> value=">=">>=</option>
                                                           <option <c:if test="${data.condition_operation2 ==  '<=' }">selected</c:if> value="<="><=</option>
                                                            <option <c:if test="${data.condition_operation2 ==  'Like' }">selected</c:if> value="Like">Like</option>
                                                         <option <c:if test="${data.condition_operation2 ==  'NULL' }">selected</c:if> value="NULL">NULL</option>
                                                          <option <c:if test="${data.condition_operation2 ==  'NOT NULL' }">selected</c:if>  value="NOT NULL">NOT NULL</option>
                                                          <option <c:if test="${data.condition_operation2 ==  'IN' }">selected</c:if> value="IN">IN</option>
                                                             <option <c:if test="${data.condition_operation2 ==  'NOT IN' }">selected</c:if> value="NOT IN">NOT IN</option>
                                                         </select>
                                                        </div>
                                                                <div class="col-sm-3"> 
                                                 <input type="text"  name="condition_as_to2" class="form-control" value="${data.condition_as_to2 }">
                                                         
                                                         </div>
                                                         
                                      </div>
                                      
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                               <div class="mb-1 row">
                                                 <div class="col-sm-3">
                                                  <select class="form-control" name="operation3">
                                                                  <option value="0">N/A</option>
                                                      <option  <c:if test="${data.condition3 ==  'AND' }">selected</c:if> value="AND">AND</option>
                                                            <option <c:if test="${data.condition3 ==  'OR' }">selected</c:if> value="OR">OR</option>
                                                       
                                                         </select>
                                                 
                                                 
                                                  </div>
                                                       
                                                         <div class="col-sm-3">
                                                         <select class="form-control" name="condition_column3">
                                                           <option value="0">N/A</option>
                                                  <c:forEach items="${eformfields }" var="rows" >
                                                         <option value="${rows.field_id }" <c:if test="${data.condition_column3 ==  rows.field_id }">selected</c:if> >${rows.field_label }</option>
                                                       		</c:forEach>
                                                  </select>
                                                 
                                                         </div>
                                                         
                                                                   <div class="col-sm-3">
                                                   <select class="form-control" name="condition_operation3">
                                                      <option  <c:if test="${data.condition_operation3 ==  'N/A' }">selected</c:if>  value="N/A">N/A</option>
                                                            <option <c:if test="${data.condition_operation3 ==  '=' }">selected</c:if> value="=">=</option>
                                                         <option <c:if test="${data.condition_operation3 ==  '!=' }">selected</c:if> value="!=">!=</option>
                                                          <option <c:if test="${data.condition_operation3 ==  '>=' }">selected</c:if> value=">=">>=</option>
                                                           <option <c:if test="${data.condition_operation3 ==  '<=' }">selected</c:if> value="<="><=</option>
                                                            <option <c:if test="${data.condition_operation3 ==  'Like' }">selected</c:if> value="Like">Like</option>
                                                         <option <c:if test="${data.condition_operation3 ==  'NULL' }">selected</c:if> value="NULL">NULL</option>
                                                          <option <c:if test="${data.condition_operation3 ==  'NOT NULL' }">selected</c:if>  value="NOT NULL">NOT NULL</option>
                                                          <option <c:if test="${data.condition_operation3 ==  'IN' }">selected</c:if> value="IN">IN</option>
                                                             <option <c:if test="${data.condition_operation3 ==  'NOT IN' }">selected</c:if> value="NOT IN">NOT IN</option>
                                                         </select>
                                                        </div>
                                                                <div class="col-sm-3"> 
                                                 <input type="text"  name="condition_as_to3" class="form-control" value="${data.condition_as_to3 }">
                                                         
                                                         </div>
                                                         
                                      </div>
                                      
                                      
                                      
                                      
                                      
                                               <div class="mb-1 row">
                                                 <div class="col-sm-3">
                                                  <select class="form-control" name="operation4">
                                                                  <option value="0">N/A</option>
                                                      <option  <c:if test="${data.condition4 ==  'AND' }">selected</c:if> value="AND">AND</option>
                                                            <option <c:if test="${data.condition4 ==  'OR' }">selected</c:if> value="OR">OR</option>
                                                       
                                                         </select>
                                                 
                                                 
                                                  </div>
                                                       
                                                         <div class="col-sm-3">
                                                         <select class="form-control" name="condition_column4">
                                                           <option value="0">N/A</option>
                                                  <c:forEach items="${eformfields }" var="rows" >
                                                         <option value="${rows.field_id }" <c:if test="${data.condition_column4 ==  rows.field_id }">selected</c:if> >${rows.field_label }</option>
                                                       		</c:forEach>
                                                  </select>
                                                 
                                                         </div>
                                                         
                                                                   <div class="col-sm-3">
                                                   <select class="form-control" name="condition_operation4">
                                                      <option  <c:if test="${data.condition_operation4 ==  'N/A' }">selected</c:if>  value="N/A">N/A</option>
                                                            <option <c:if test="${data.condition_operation4 ==  '=' }">selected</c:if> value="=">=</option>
                                                         <option <c:if test="${data.condition_operation4 ==  '!=' }">selected</c:if> value="!=">!=</option>
                                                          <option <c:if test="${data.condition_operation4 ==  '>=' }">selected</c:if> value=">=">>=</option>
                                                           <option <c:if test="${data.condition_operation4 ==  '<=' }">selected</c:if> value="<="><=</option>
                                                            <option <c:if test="${data.condition_operation4 ==  'Like' }">selected</c:if> value="Like">Like</option>
                                                         <option <c:if test="${data.condition_operation4 ==  'NULL' }">selected</c:if> value="NULL">NULL</option>
                                                          <option <c:if test="${data.condition_operation4 ==  'NOT NULL' }">selected</c:if>  value="NOT NULL">NOT NULL</option>
                                                          <option <c:if test="${data.condition_operation4 ==  'IN' }">selected</c:if> value="IN">IN</option>
                                                             <option <c:if test="${data.condition_operation4 ==  'NOT IN' }">selected</c:if> value="NOT IN">NOT IN</option>
                                                         </select>
                                                        </div>
                                                                <div class="col-sm-3"> 
                                                 <input type="text"  name="condition_as_to4" class="form-control" value="${data.condition_as_to4 }">
                                                         
                                                         </div>
                                                         
                                      </div>
                                      
                                            
                                            
                                            
                                            
                                            
                                            
                                                
                                                                <hr>     
                                                            	 <div class="mb-1 row">
                                               
                                                       
                                                         <div class="col-sm-4">Column</div>
                                                         
                                                                   <div class="col-sm-4">
                                           Operation
                                                        </div>
                                                       
                                                         
                                                                      <div class="col-sm-4">AS</div>
                                                           
                                                            </div>
                                    	
                                                         	
                                                         	
                                                         	 <div class="mb-1 row">
                                               
                                                       
                                                         <div class="col-sm-4">
                                                         <select class="form-control" name="column1">
                                                            <option>N/A</option>
                                                            
                                                   
                                                            <c:forEach items="${eformfields }" var="rows" >
                                                         <option value="${rows.field_id }" <c:if test="${data.column1 ==  rows.field_id.toString() }">selected</c:if>>${rows.field_label }</option>
                                                       		</c:forEach>
                                                        
                                                          
                                                         </select>
                                                         </div>
                                                         
                                                                   <div class="col-sm-4">
                                                   <select class="form-control" name="operation1">
                                                      <option  <c:if test="${data.operation1 ==  'N/A' }">selected</c:if> value="N/A">N/A</option>
                                                         <option <c:if test="${data.operation1 ==  'first_row' }">selected</c:if> value="first_row">First row</option>
                                                         <option <c:if test="${data.operation1 ==  'end_row' }">selected</c:if> value="end_row">End row</option>
                                                          <option <c:if test="${data.operation1 ==  'average' }">selected</c:if> value="average">Average</option>
                                                           <option <c:if test="${data.operation1 ==  'sum' }">selected</c:if> value="sum">Sum</option>
                                                            <option <c:if test="${data.operation1 ==  'count' }">selected</c:if>  value="count">Count</option>
                                                                <option <c:if test="${data.operation1 ==  'max' }">selected</c:if> value="max">max</option>
                                                            <option <c:if test="${data.operation1 ==  'min' }">selected</c:if> value="min">min</option>
                                                         </select>
                                                        </div>
                                                                
                                                         
                                                                   <div class="col-sm-4">
                                            <select class="form-control" name="as_to1">
                                               <option>N/A</option>
                   									           <c:forEach items="${Feedseformfields }" var="rows" >
                                                         <option value="${rows.field_id }" <c:if test="${data.as_to1 ==  rows.field_id.toString() }">selected</c:if> >${rows.field_label }</option>
                                                       		</c:forEach> 
                                                         
                                                         </select>
                                                        </div>
                                                           
                                                           
                                                                 </div>
                                                           
                                                           
                                                           
                                                           
                                                           
                                                           
                                                           
                                                           
                                                            	
                                                         	 <div class="mb-1 row">
                                               
                                                       
                                                         <div class="col-sm-4">
                                                         <select class="form-control" name="column2">
                                                            <option>N/A</option>
                                                            
                                                   
                                                            <c:forEach items="${eformfields }" var="rows" >
                                                         <option value="${rows.field_id }" <c:if test="${data.column2 ==  rows.field_id.toString() }">selected</c:if>>${rows.field_label }</option>
                                                       		</c:forEach>
                                                         </select>     
                                                
                                                         </div>
                                                         
                                                                   <div class="col-sm-4">
                                                   <select class="form-control" name="operation2">
                                                      <option  <c:if test="${data.operation2 ==  'N/A' }">selected</c:if> value="N/A">N/A</option>
                                                         <option <c:if test="${data.operation2 ==  'first_row' }">selected</c:if> value="first_row">First row</option>
                                                         <option <c:if test="${data.operation2 ==  'end_row' }">selected</c:if> value="end_row">End row</option>
                                                          <option <c:if test="${data.operation2 ==  'average' }">selected</c:if> value="average">Average</option>
                                                           <option <c:if test="${data.operation2 ==  'sum' }">selected</c:if> value="sum">Sum</option>
                                                            <option <c:if test="${data.operation2 ==  'count' }">selected</c:if>  value="count">Count</option>
                                                                <option <c:if test="${data.operation2 ==  'max' }">selected</c:if> value="max">max</option>
                                                            <option <c:if test="${data.operation2 ==  'min' }">selected</c:if> value="min">min</option>
                                                         </select>
                                                        </div>
                                                                
                                                         
                                                                   <div class="col-sm-4">
                                            <select class="form-control" name="as_to2">
                                               <option>N/A</option>
                   									           <c:forEach items="${Feedseformfields }" var="rows" >
                                                         <option value="${rows.field_id }" <c:if test="${data.as_to2 ==  rows.field_id.toString() }">selected</c:if> >${rows.field_label }</option>
                                                       		</c:forEach> 
                                                         
                                                         </select>
                                                        </div>
                                                           
                                                           
                                                           
                                                           
                                                           
                                                            </div>   
                                                            
                                                            
                                                            	 <div class="mb-1 row">
                                               
                                                       
                                                         <div class="col-sm-4">
                                                         <select class="form-control" name="column3">
                                                            <option>N/A</option>
                                                            
                                                   
                                                            <c:forEach items="${eformfields }" var="rows" >
                                                         <option value="${rows.field_id }" <c:if test="${data.column2 ==  rows.field_id.toString() }">selected</c:if>>${rows.field_label }</option>
                                                       		</c:forEach>
                                                         </select>     
                                                      
                                                         </div>
                                                         
                                                                   <div class="col-sm-4">
                                                   <select class="form-control" name="operation3">
                                                      <option  <c:if test="${data.operation3 ==  'N/A' }">selected</c:if> value="N/A">N/A</option>
                                                         <option <c:if test="${data.operation3 ==  'first_row' }">selected</c:if> value="first_row">First row</option>
                                                         <option <c:if test="${data.operation3 ==  'end_row' }">selected</c:if> value="end_row">End row</option>
                                                          <option <c:if test="${data.operation3 ==  'average' }">selected</c:if> value="average">Average</option>
                                                           <option <c:if test="${data.operation3 ==  'sum' }">selected</c:if> value="sum">Sum</option>
                                                            <option <c:if test="${data.operation3 ==  'count' }">selected</c:if>  value="count">Count</option>
                                                                <option <c:if test="${data.operation3 ==  'max' }">selected</c:if> value="max">max</option>
                                                            <option <c:if test="${data.operation3 ==  'min' }">selected</c:if> value="min">min</option>
                                                         </select>
                                                        </div>
                                                                
                                                         
                                                                   <div class="col-sm-4">
                                            <select class="form-control" name="as_to3">
                                               <option>N/A</option>
                   									           <c:forEach items="${Feedseformfields }" var="rows" >
                                                         <option value="${rows.field_id }" <c:if test="${data.as_to3 ==  rows.field_id.toString() }">selected</c:if> >${rows.field_label }</option>
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
                                            