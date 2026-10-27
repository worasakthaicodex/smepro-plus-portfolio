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

      <th> ลำดับ</th>
            <th >วันที่</th>
        <th >เอกสารที่อ้างถึง</th>
           <th >ดำเนินการ</th>
  </tr>
  </thead>
  <tbody>
    
 
                                        <c:forEach items="${audit }" var="row"  varStatus="count">
				                                     
				                                     
				                                      <tr >
				                                       <td style="padding:5px;font-size:13px">${count.count }</td >
				                                      <td style="padding:5px;font-size:13px">${row.created_at}</td >   
				                                       <td style="padding:5px;font-size:13px">${row.edata_id}</td >                            
														
														       <td style="padding:5px;font-size:13px">
														
														     
														      <span style="margin-left:5px;cursor: pointer;" class="btn-hover-effect" >
        <a 
       onclick="openwindow('draggable-window6','ข้อมูลเอกสาร','9500');getData('/viewRelationsDoc-filemanager/${row.edata_id}','mainbody6');"   
         data-original-title="Img" >
            <img  src="/app/system/icon/preview.ico" />
         </a>
         
      </span>
														     
														       </td >       
														    
				                                        </tr>
                                        </c:forEach>

</tbody>
</table>
      
  
  
  </div>
  
  
  
  <div class="footer">
  
  </div>
  
  
  </div>
   














    






