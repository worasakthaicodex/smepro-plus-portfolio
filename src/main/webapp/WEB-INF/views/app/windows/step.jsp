<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html lang="en"  ">

    <div id="body" class="mainbody2"   >
      <div id="data_iadmin">
       




  <div class="container"  >
   
   
  </div>
  
  <div class="container2"  >
  
<div id="body" class="mainbody"  style="max-height:200px;overflow-y:auto" >
  
  
  
<table border="1" style="width:100%; border-collapse: collapse;">
    <thead>
      <tr style="background-color: #f2f2f2; position: sticky; top: 0;">

      <th ลำดับ</th>
            <th >DATE</th>
        <th >TYPE</th>
           <th >TYPE</th>
  <th>SEND</th>
  <th>TO</th>
  <th>Comment</th>
  </tr>
  </thead>
  <tbody>
 
                                        <c:forEach items="${log }" var="logs"  varStatus="count">
				                                     
				                                     
				                                      <tr >
				                                       <td style="padding:5px;font-size:13px">${count.count }</td >
				                                     <td style="padding:5px;font-size:13px">${logs.flow_date}</td >
				                                      <td style="padding:5px;font-size:13px">${logs.flow_step}</td >   
				                                       <td style="padding:5px;font-size:13px">${logs.flow_type}</td >                            
														
														       <td style="padding:5px;font-size:13px">${logs.send}</td >       
														       <td style="padding:5px;font-size:13px">${logs.to}</td >       
														             <td style="padding:5px;font-size:13px">${logs.flow_comment}</td >    
				                                        </tr>
                                        </c:forEach>

</tbody>
</table>
      
  
  
  </div>
  
  
  
  <div class="footer">
  
  </div>
  
  
  </div>
   














    






