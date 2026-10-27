<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="../common/mainhead.jspf" %>    



    <!-- BEGIN: Vendor CSS-->
    <link rel="stylesheet" type="text/css" href="/resources/app-assets/vendors/css/vendors.min.css">
    <link rel="stylesheet" type="text/css" href="/resources/app-assets/vendors/css/forms/wizard/bs-stepper.min.css">
    <link rel="stylesheet" type="text/css" href="/resources/app-assets/vendors/css/forms/select/select2.min.css">
    <!-- END: Vendor CSS-->

   <link rel="stylesheet" type="text/css" href="/resources/app-assets/css/core/menu/menu-types/vertical-menu.css">
    <link rel="stylesheet" type="text/css" href="/resources/app-assets/css/plugins/forms/form-wizard.css">
    <link rel="stylesheet" type="text/css" href="/resources/app-assets/css/plugins/forms/form-validation.css">
    <link rel="stylesheet" type="text/css" href="/resources/app-assets/css/pages/modal-create-app.css">

<script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js'>  </script>  

<table class="table">
                    <tr>
                    <th>#</th>
                    <th>column</th>
                    <th>Meta columns</th>
					<th>RM</th>
					</tr>
					
					  <c:forEach items="${eformfields }" var="fields" varStatus="count">   
					<tr>
	                    	<td>${count.count }</<td>
	                        <td>${fields.field_label }
	                        
	                        <input type="hidden" name="field_id"  value="${fields.field_id }">
	                        </<td>
	                       	<td>

	       					 <select id="tax-1-input"  name="field_cal_type"  class="form-select select2" >
 	                         <option value="-" >-</option>
 	                        <c:forEach items="${columntype }" var="row" varStatus="count">
 	                        <option value="${row.name()}" <c:if test="${fields.field_cal_type == row.name()}">selected</c:if>>${row.name()}[${row.label} ]</option>
 	            			</c:forEach>
 	                        </select>	
	                       	</td>
	                       	
						    <td>
						 <select id="tax-1-input" class="form-select" name="field_cal_type2">
 	                              <option value="-">-</option>
 	                                <option value="verification" <c:if test="${fields.field_cal_type2 =='verification'}">selected</c:if>>Verification</option>
 	                                <option value="register" <c:if test="${fields.field_cal_type2 =='register'}">selected</c:if>>register</option>
 	                                </select>
						    </<td>
					   
				</tr>
					  </c:forEach>
					
					
					 </table>
*** <spring:message code="common.RM"  />					 
					 