<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<br><br><br><br><br>
                ข้อมูลที่ถูกเชื่อมเข้ามา
              <table class="tabledata" style="height:auto">
                                    <thead>
                                        <tr>
                           
                 
                              <c:forEach items="${eformfields }" var="row" varStatus="count">     
                              
                            
                              
                              
                                        <th class="form-label fixhead">${row.field_label }</th>
                               </c:forEach> 
                               </tr>
                                 </thead>
                 </table>