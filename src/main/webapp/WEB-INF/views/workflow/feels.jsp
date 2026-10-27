<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<form method="POST"   action="/savesetfeels-workflow">
    <div class="card-body"> 
    
          <input type="hidden"  name="workflow_id" value="${workflow_id }">
     <input type="hidden"  name="id" value="${process.id}">
        <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    		 <div class="mb-1 row">
                                               
                                                       
                                                         <div class="col-sm-4">Outgoing Column</div>
                                                               <div class="col-sm-4">Column</div>
                                                <div class="col-sm-4">Feels Type</div>
                                                
                                                  </div>
                                                  
                                        
                                                 <div class="mb-1 row">
                                                
                                                         
                                               <div class="col-sm-4">
                                                <select class="form-control" name="outgoing_column1">
                                                 <option value="0">-</option>
                                                       <c:forEach items="${Feedseformfields }" var="rows" >
                                                         <option value="${rows.field_id }" <c:if test="${process.outgoing_column1 ==  rows.field_id }">selected</c:if> >${rows.field_label }</option>
                                                       		</c:forEach>
                                                         </select>
                                                        </div>
                                   
                                                                              <div class="col-sm-4">
                                                 <select class="form-control" name="for_column1">
                                                 <option value="0">-</option>
                                                       <c:forEach items="${eformfields }" var="rows" >
                                                         <option value="${rows.field_id }" <c:if test="${process.for_column1 ==  rows.field_id }">selected</c:if>>${rows.field_label }</option>
                                                       		</c:forEach>
                                                         </select>
                                                        </div>
                                                     <div class="col-sm-4">
                                                 	<select class="form-control" name="feed_type1">
                                                 	   <option value="-" <c:if test="${process.feed_type1 ==  '-' }">selected</c:if>>-</option>
                                                      <option value="input" <c:if test="${process.feed_type1 ==  'input' }">selected</c:if>>Input</option>
                                                      <option value="select" <c:if test="${process.feed_type1 ==  'select' }">selected</c:if>>Select</option>
                                                          
                                                     </select>
                                                       </div>
                                                            </div>
                                   			
                                             
                                             
                                                   <div class="mb-1 row">
                                                
                                                         
                                               <div class="col-sm-4">
                                                <select class="form-control" name="outgoing_column2">
                                                 <option value="0">-</option>
                                                       <c:forEach items="${Feedseformfields }" var="rows" >
                                                         <option value="${row.field_id }" <c:if test="${process.outgoing_column2 ==  rows.field_id }">selected</c:if> >${rows.field_label }</option>
                                                       		</c:forEach>
                                                         </select>
                                                        </div>
                                   
                                                                              <div class="col-sm-4">
                                                 <select class="form-control" name="for_column2">
                                                 <option value="0">-</option>
                                                       <c:forEach items="${eformfields }" var="rows" >
                                                         <option value="${rows.field_id }" <c:if test="${process.for_column2 ==  rows.field_id }">selected</c:if>>${rows.field_label }</option>
                                                       		</c:forEach>
                                                         </select>
                                                        </div>
                                                     <div class="col-sm-4">
                                                 	<select class="form-control" name="feed_type2">
                                                 	   <option value="-" <c:if test="${process.feed_type2 ==  '-' }">selected</c:if>>-</option>
                                                      <option value="input" <c:if test="${process.feed_type2 ==  'input' }">selected</c:if>>Input</option>
                                                      <option value="select" <c:if test="${process.feed_type2 ==  'select' }">selected</c:if>>Select</option>
                                                          
                                                     </select>
                                                       </div>
                                                            </div>
                                             
                                                        
                                                           <div class="mb-1 row">
                                                
                                                         
                                               <div class="col-sm-4">
                                                <select class="form-control" name="outgoing_column3">
                                                 <option value="0">-</option>
                                                       <c:forEach items="${Feedseformfields }" var="rows" >
                                                         <option value="${rows.field_id }" <c:if test="${process.outgoing_column3 ==  rows.field_id }">selected</c:if> >${rows.field_label }</option>
                                                       		</c:forEach>
                                                         </select>
                                                        </div>
                                   
                                                                              <div class="col-sm-4">
                                                 <select class="form-control" name="for_column3">
                                                 <option value="0">-</option>
                                                       <c:forEach items="${eformfields }" var="rows" >
                                                         <option value="${rows.field_id }" <c:if test="${process.for_column3 ==  rows.field_id }">selected</c:if>>${rows.field_label }</option>
                                                       		</c:forEach>
                                                         </select>
                                                        </div>
                                                     <div class="col-sm-4">
                                                 	<select class="form-control" name="feed_type3">
                                                 	   <option value="-" <c:if test="${process.feed_type3 ==  '-' }">selected</c:if>>-</option>
                                                      <option value="input" <c:if test="${process.feed_type3 ==  'input' }">selected</c:if>>Input</option>
                                                      <option value="select" <c:if test="${process.feed_type3 ==  'select' }">selected</c:if>>Select</option>
                                                          
                                                     </select>
                                                       </div>
                                                            </div>
                                                        
                                                         	
 </div>      
                     <div class="modal-footer" >
                                                              <a  href="/docworkflowapp-workflow"  target="_blank" class="btn btn-light waves-effect waves-float waves-light" >Doc</a>
                                                                <input type="Submit"  value="Save"  class="btn btn-primary waves-effect waves-float waves-light">
                                                            </div>                               
    </form>                                                                  
                                            