 <%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

           
           <c:if test="${problem.id  > 0}">
           <input type="hidden" name="id" value="${problem.id}">
           </c:if>
           
 
 			      <div class="cols-4">
                                                <div class="mb-1">
                                                    <label for="todoTitleAdd" class="form-label"><spring:message code="actionplan.problems" /></label>
                                                   <select class="form-select mb-2" name="title" >
                                                     	<option value="0"><spring:message code="actionplan.unassessed" /></option>
                                                 
                                                     
                                                     <c:forEach var="row" items="${risks }">
                                                           	<option value="${row.id }"   <c:if test="${ problem.title == row.id}">selected</c:if>>
                                                           	${row.description } [<spring:message code="${row.category }" />]
                                                           	</option>
                                                      </c:forEach>             
                                                          </select>   
                                                </div>
                                                
                                           
                                            </div>
                                                  <div class="cols-4">
                                                <div class="mb-1">
                                                    <label for="todoTitleAdd" class="form-label"><spring:message code="actionplan.description" /></label>
                                                    <textarea type="text" name="description" id="todoTitleAdd" class="new-todo-item-title form-control" >${problem.description }</textarea>
                                                </div>
                                                
                                           
                                            </div>
                                                  <div class="cols-4">
                                                <div class="mb-1">
                                                    <label for="todoTitleAdd" class="form-label"><spring:message code="actionplan.solving" /></label>
                                                    <textarea type="text" name="mitigation" id="todoTitleAdd" class="new-todo-item-title form-control" >${problem.mitigation }</textarea>
                                                </div>
                                                
                                           
                                            </div>
                                                   <div class="action-tags">
                                                <div class="mb-1">
                                                    <label for="todoTitleAdd" class="form-label"><spring:message code="actionplan.fixedyet" /></label>
                                                  <select class="form-select mb-2" name="status" >
                                                  
                                                     	 <option value="1" <c:if test="${actionplan.actionplan_result == 1}">selected</c:if>><spring:message code="actionplan.inprogress" /></option>
                                                     	 <option value="2" <c:if test="${actionplan.actionplan_result == 2}">selected</c:if>><spring:message code="actionplan.closed" /></option>
                                                     </select>
                                                </div>
                                                
                                           
                                            </div>
                                               	<c:if test="${problem.owner_id == Member_id}">  
                                            <div class="my-1">
                                             <c:if test="${problem == null}">
                                                <button type="button" onclick='postdatas("/addproblems-filemanager?${_csrf.parameterName}=${_csrf.token}","problems_form","problems_list")' class="btn btn-primary add-todo-item me-1 waves-effect waves-float waves-light">
                                                <spring:message code="btn.save" /></button>
                                            </c:if>
                                            
                                                <c:if test="${problem.id  > 0}">
                                               <button type="button" onclick='postdatas("/addproblems-filemanager?${_csrf.parameterName}=${_csrf.token}","problems_form","problems_list")' class="btn btn-info add-todo-item me-1 waves-effect waves-float waves-light">
                                                 <spring:message code="btn.edit" /></button> 
                                                 
                                                  <button type="button" onclick="getdatas('/addproblems-filemanager/${problem.task_id}','subproblems_form',0);" class="btn btn-light add-todo-item me-1 waves-effect waves-float waves-light">
                                                 <spring:message code="btn.cancel" /></button> 
                                                 
                                                 
                                                             <button type="button" onclick="deletedatas('/deleteproblems-filemanager?${_csrf.parameterName}=${_csrf.token}','problems_form','problems_list');" 
                                                             class="btn btn-danger add-todo-item me-1 waves-effect waves-float waves-light" style="float:right">
                                                 <spring:message code="btn.delete" /></button>
                                                </c:if>
                                             
                                            </div>
                                              </c:if>