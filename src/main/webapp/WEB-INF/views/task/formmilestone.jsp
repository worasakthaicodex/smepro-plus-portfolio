<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

           
           
           
 <div class="mb-1">
       
                 
                  <input  type="hidden" class="form-control"  name="id"  value="${milestone.id}">
                                                    <label class="form-label" for="first-name-column"><spring:message code="milestone.detail" /></label>
                                               <input  type="text" class="form-control"  name="detail"  value="${milestone.detail}">
                                           </div>      
                                   <div class="mb-1">
                                                    <label class="form-label" for="first-name-column"><spring:message code="milestone.date_due" /></label>
                                                           <input  type="date" class="form-control"  name="date_due"  value="${milestone.date_due}">
                                           </div>       
                     				
                     				   <div class="mb-1">
                                                    <label class="form-label" for="first-name-column"><spring:message code="milestone.date_alert" /></label>
                                                           <input  type="date" class="form-control"  name="date_alert"  value="${milestone.date_alert}">
                                           </div>   
                     				
                     				   <div class="mb-1">
                                                    <label class="form-label" for="first-name-column"><spring:message code="milestone.billing_amount" /></label>
                                                           <input  type="number" class="form-control"  name="billing_amount"  value="${milestone.billing_amount}">
                                           </div>   
                     				
                     				 
               	  				    	 <div class="mb-1">
                                                    <label class="form-label" for="first-name-column"><spring:message code="milestone.user_work_name" /></label>
								                                             <select class="select2 form-select" name="user_work_name"  > 

								 
								    <c:forEach var="nameItem" items="${user_all}">
								            <option value="${nameItem.user_name}" <c:if test="${milestone.user_work_name == nameItem.user_name}"> selected </c:if>>${nameItem.user_name}</option>
								    </c:forEach>
								</select>
                                           </div>   
                                    <br>       
                                           <hr>
                                         <br>         
                                              <div class="mb-2 row">
                                               <div class="col-4">
                                                    <label class="form-label" for="first-name-column"><spring:message code="milestone.processing_date" /></label>
                                                           <input  type="date" class="form-control"  name="processing_date"  value="${milestone.processing_date}">
                                                               </div>  
                                           <div class="col-4 ">
                                           
                                                    <label class="form-label" for="first-name-column"><spring:message code="milestone.collected_payment" /></label>
                                                           <input  type="number" class="form-control"  name="collected_payment"  value="${milestone.collected_payment}">
                                                               </div>  
                                        
                                              <div class="col-4 ">
                                           
                                                    <label class="form-label" for="first-name-column"><spring:message code="tables.status" /></label>
                                                          <select class="form-select" name="status">
                                                          <option value="0" <c:if test="${milestone.status == 0}"> selected </c:if> ><spring:message code="status.INCOMPLETE" /></option>
                                                            <option value="1" <c:if test="${milestone.status == 1}"> selected </c:if>><spring:message code="status.COMPLETED" /></option>
                                                          </select>
                                                               </div>  
                                           </div>    
                                           
           
           
                