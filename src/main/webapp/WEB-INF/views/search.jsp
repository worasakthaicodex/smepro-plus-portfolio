<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>



   <c:forEach items="${file }" var="row" varStatus="count">
        <div class="list-item-body " style="width:100%">
                                       <span class="fw-bolder">${row.file_name_original }</span>
                                       
                                       
                                           <c:set var="path" value="${row.file_path}" />
					                      <c:if test="${row.file_shotcut ne null}">   
					                       <c:set var="path" value="${row.file_shotcut}" />
					                      </c:if>     
                                       
                                       
                                       
                                        <c:if test="${groups == row.group_id }">    
                                       <a href="/subfileinfo-filemanager?id=${row.file_id }&&Folders=${path}" style="float:right;magin-top:5px;"  class="btn btn-light waves-effect waves-float waves-light"> View</a>
                                          </c:if>  
                                          
                                          
                                       <br><small class="notification-text">
                                      <c:if test="${row.file_no == '' }">   
                                       ID: ${row.file_no } REV ${row.file_rev } ,
                                            </c:if>  
                                       
                                       
                                    
                                        Size : ${row.file_size2 } ,  Type :  ${row.file_type } , Location : ${row.file_path_full }</small>
                                    
                             
		            </div>
                               <br>  
        </c:forEach>         