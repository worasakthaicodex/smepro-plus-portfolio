      <%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

   
       <div class="cl_window">
    <div class="top-section">
                <div class="main-form">
     <div class="form-row">
                            
                        <label style="width:30%"><spring:message code="profile.Grouping_Categorization" text="-​" /> :</label>

                    <select name="status" style="padding:0px"
					        onchange="getData('/GroupingCategorization-filemanager/${process.id}/' + encodeURIComponent(this.value), 'wpf-content-indexapp');"> 
					    <option value="-">-</option>
					    <c:forEach items="${Register_status}" var="row">
					        <option value="${row.dataset_data_title}" 
					            <c:if test="${Grouping == row.dataset_data_title}">selected</c:if>> 
					            ${row.dataset_data_title}
					        </option>
					    </c:forEach>
					    
					</select>
                    </div>
               </div>
                     </div>
           </div>            
                                   <br>
                                     <spring:message code="table.DataGridSample"  text="-" />
                                     : ${exampleData.size() } <spring:message code="table.for"  text="-" /> ${countData } <spring:message code="table.rows"  text="-" />
                                   
                                   <br> 
                    <div class="wpf-datagrid-container">
                        <table>
                            <thead>
                                <tr >
                                    <th>#</th>
                                      <th><spring:message code="${profile.id}"  text="-" /></th>
                                   <c:forEach items="${heads }" var="row" varStatus="count"> 
                     					   <th><spring:message code="${row.title}"  text="-" /></th>
                   				  </c:forEach> 
                              
                             
                                
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${exampleData }" var="rows" varStatus="counts"> 
	                    <tr ondblclick="getData('/${url}/${rows.id}/${process.id}?from=${from+0 }', 'microsofts${process.id}')" style="cursor:copy;">
	                        <td  style="border-left: 0px solid #bbb;">${counts.count }</td>
	                        
	                        <c:if test="${registerType == 'Profile' }">
	                         <td  style="border-left: 0px solid #bbb;">${rows['register_code']}${rows['register_daterun']}-${rows['register_id']}</td>
	                          </c:if>
	                               <c:if test="${registerType == 'Product' }">
	                         <td  style="border-left: 0px solid #bbb;">${rows['product_code']}${rows['product_daterun']}-${rows['product_id']}</td>
	                          </c:if> 
	                          
	                  <c:forEach items="${heads }" var="row" varStatus="count"> 
                      <td style="border-left: 0px solid #bbb;">${rows[row.mainkey]}</td>
                     </c:forEach> 
	           
	           
	                     
	                    </tr>
                      </c:forEach>   
                        
                            </tbody>
                        </table>
                    </div>