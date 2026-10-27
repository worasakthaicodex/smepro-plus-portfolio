<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html lang="en"  ">

    <div id="body" class="mainbody2"   >
      <div id="data_iadmin">
       




  <div class="container"  >
   
   
          
  
       <div class=" toolbar"   style="border-top: 1px solid #808080;">
<div class="btn-group" style="color:black">

 
  <button class="fa-button" title="โหลดใหม่"  onclick="getData('/app-filemanager/${process.id}', 'microsofts${process.id}')"  style="color:black" data-original-title="List"> 
    <i class="material-icons text-default">
    restart_alt
  </i></button>
  
  
 <div style="display:none">
  <button class="fa-button " title="นำออก" 
  th:onclick="'shows(\'draggable-iadmin\',\'นำออก\'); getData(\'' + @{/export/department }+ '\', \'mainbody-iadmin\')'"
   style="color:black;"   data-original-title="Img">
    <i class="material-icons text-default">
      download
    </i>
   </button>
   <button class="fa-button "  title="ค้นหา"  data-original-title="Img"
   th:onclick="'shows(\'draggable-iadmin\',\'ค้นหาเเบบละเอียด\'); getData(\'' + @{/department/search }+ '\', \'mainbody-iadmin\')'"
   >
    <i class="material-icons text-default">

      manage_search
    </i>
   </button>
   <button class="fa-button " title="จัดเรียง"   data-original-title="Img"
   th:onclick="'shows(\'draggable-iadmin\',\'จัดเรียง\'); getData(\'' + @{/department/sort }+ '\', \'mainbody-iadmin${process.id}\')'"
   >
    <i class="material-icons text-default">

      swap_vert
    </i>
   </button>
  </div>
 


 

    
     
    


   <span style="float:right;padding: 0;margin-top:-4px" >

      </span>




    </div>



    
</div>

     
  </div>
  
  <div class="container2"  >
  
  <div id="body" class="mainbody"   >
  
  
  
<c:if test="${not empty diffList}">
    <div class="alert alert-warning">
        <h4>พบข้อมูลเปลี่ยนแปลง:</h4>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>รายการ (Field)</th>
                    <th>ข้อมูลเดิม</th>
                    <th>ข้อมูลใหม่</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="diff" items="${diffList}">
                    <tr>
                        <td>${diff.fieldName}</td>
                        <td style="color:red;">${diff.oldValue}</td>
                        <td style="color:green; font-weight:bold;">${diff.newValue}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</c:if>

<c:if test="${empty diffList}">
    <p>ข้อมูลเหมือนเดิมทุกอย่าง ไม่มีการเปลี่ยนแปลง</p>
</c:if>
  
  </div>
  
  
  
  <div class="footer">
  
  </div>
  
  
  </div>
   














    






