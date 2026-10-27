      <%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

   
     
   
                        
                    <div class="wpf-datagrid-container">
                        <table>
                            <thead>
                                <tr >
                                    <th>#</th>
                                      <th>ID</th>
                                   <c:forEach items="${heads }" var="row" varStatus="count"> 
                     					   <th><spring:message code="${row.title}"  text="-" /></th>
                   				  </c:forEach> 
                              
                             
                                
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${exampleData }" var="rows" varStatus="counts"> 
	                    <tr ondblclick="getData('/profileapp-filemanager/${rows.id}/${process.id}?from=${from+0 }', 'microsofts${process.id}')" style="cursor:copy;">
	                        <td  style="border-left: 0px solid #bbb;">${counts.count }</td>
	                          <td  style="border-left: 0px solid #bbb;">${rows['register_code']}${rows['register_daterun']}-${rows['register_id']}</td>
	                  <c:forEach items="${heads }" var="row" varStatus="count"> 
                      <td style="border-left: 0px solid #bbb;">${rows[row.mainkey]}</td>
                     </c:forEach> 
	           
	           
	                     
	                    </tr>
                      </c:forEach>   
                        
                            </tbody>
                        </table>
                    </div>