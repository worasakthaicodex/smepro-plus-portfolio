<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<select class="form-select"  name="${listname }">
				                                              <option value="${dataset.dataset_1 }" <c:if test="${dataset.dataset_1 == listdata}">selected</c:if>>${dataset.dataset_1 }</option>
				                                         <c:if test="${dataset.dataset_1 != ''}">
				                                                        <option value="${dataset.dataset_1 }" <c:if test="${dataset.dataset_1 == listdata}">selected</c:if>>${dataset.dataset_1 }</option>
				                                          </c:if>
				                                           <c:if test="${dataset.dataset_2 != ''}">
				                                                        <option value="${dataset.dataset_2 }" <c:if test="${dataset.dataset_1 == listdata}">selected</c:if>>${dataset.dataset_2 }</option>
				                                          </c:if>
				                                             <c:if test="${dataset.dataset_3 != ''}">
				                                                        <option value="${dataset.dataset_3 }" <c:if test="${dataset.dataset_1 == listdata}">selected</c:if>>${dataset.dataset_3 }</option>
				                                          </c:if>
				                                             <c:if test="${dataset.dataset_4 != ''}">
				                                                        <option value="${dataset.dataset_4 }" <c:if test="${dataset.dataset_1 == listdata}">selected</c:if>>${dataset.dataset_4 }</option>
				                                          </c:if>
				                                             <c:if test="${dataset.dataset_5 != ''}">
				                                                        <option value="${dataset.dataset_5 }" <c:if test="${dataset.dataset_1 == listdata}">selected</c:if>>${dataset.dataset_5 }</option>
				                                          </c:if>
</select>