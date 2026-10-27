<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

    <c:if test="${row.main != 1}"> 

				     <a href="javascript:void(0)" onclick="edit('${row.dataset_data_id }','${row.dataset_data_title }','${row.dataset_data_subtitle1 }','${row.dataset_data_subtitle2 }','${row.dataset_data_subtitle3 }','${row.dataset_data_subtitle4 }')"
				      class="btn btn-info" data-bs-toggle="modal" data-bs-target="#addNewCard2"><spring:message code="btn.edit" text="แก้ไข" /></a>
				                                                    
				                                                     <c:if test="${datasets.dataset_status == 0 and datasets.dataset_idadd ==  Member_id or Member_id == 1}"> 
				                                           
				                                                  </c:if>
                                   </c:if>                      
                               