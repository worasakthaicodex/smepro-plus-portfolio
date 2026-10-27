<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<html lang="en"  >

    <div id="body2" class="mainbody2"   >
     
        <style>
      
        .datatable thead th {
      
          position: sticky;
          top: 0;
          font-size: 13px;


    white-space: nowrap;
    font-family: 'Lato', sans-serif;

 
    height: 30px;
    background-repeat: no-repeat;
    color: white;
    text-shadow: #012b4d 2px 2px 2px;
    text-align: center;
    background-image: url("https://www.jqueryscript.net/demo/jQuery-Plugin-For-Draggable-Resizable-Table-Columns-colResizable/img/th.png");

}
table {
  border-collapse: collapse;
    text-indent: initial;
    border-spacing: 2px;
    width: 100%;
}


td{
  text-indent: 5px;
    color: #444;
    border-bottom: 1px solid #bbb;
    border-left: 1px solid #bbb;
    font-size: 13px;
     white-space: nowrap;      /* ไม่ให้ขึ้นบรรทัดใหม่ */
  overflow: hidden;         /* ซ่อนข้อความที่ล้น */
  text-overflow: ellipsis;  /* ใส่ ... ต่อท้าย */
  max-width: 150px;         /* จำกัดความกว้างของหัวตาราง */
}     
.sorted{
  font-size: 15px;

}      
 
 

      </style>

<div class="container">

  <div class="menus toolbar" style="min-height: 35px;">
<div class="btn-group" >


 <button class="fa-button" title="โหลดใหม่"  onclick="getData('/department/all', 'mainbodyiadmin${process.id}')"  style="color:black" data-original-title="List"> 
    <i class="material-icons text-default">
    restart_alt
  </i></button>
  
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

      settings
    </i>
   </button>

  
   <span style="float:right;padding: 0;margin-top:-4px" >
    <form action="#"  method="POST" id="myFormsed" >
  
      
  
      <input type="text" style="padding:3px;"  name="keyword"  th:value="${keyword}"  id="files-filter2" required>
  
      <button class="fa-button dark "  type="button" style="color:black;padding:2px" data-original-title="Img"
      onclick="search('files-filter2','tr_datatable')">
        <i class="material-icons text-light" >
   
          search
        </i>
       </button>
      </form>
      </span>
  

    </div>
</div>
</div>

  <div class="datatable_list">
  <c:if test="${tables == 'register' }">
     <%@ include file="Subdata/customer.jspf" %>      
   </c:if>
     <c:if test="${tables == 'product' }">
     <%@ include file="Subdata/product_master.jspf" %>      
   </c:if>
        <c:if test="${tables == 'fg' }">
     <%@ include file="Subdata/fg_master.jspf" %>      
   </c:if>
        <c:if test="${tables == 'stock' }">
     <%@ include file="Subdata/sk_master.jspf" %>      
   </c:if>
</div>

      </div>
     
        
    