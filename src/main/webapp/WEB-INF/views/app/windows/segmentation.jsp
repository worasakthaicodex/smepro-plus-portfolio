<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>



  <div class="wpf-header-text"> <spring:message code="profile.Segmentation" text="-​" /></div>

<div id="tm-v2-wrapper" >
<div class="tm-window"  >
            
   
             <div class="tm-tab-bar" style="width:100%"> 
    
 <div class="tm-tab  active"  id="Tab-Tab-Form-1" onclick="switchTab('Tab-Form','2','1')"><spring:message code="sc.StaticSnapshot" text="-​" /></div>

            </div>

            <div class="tm-content" >
                

 
	<div id="Form-Tab-Form-1" >
   <div class="wpf-datagrid-container" style="min-height:500px">
   <div class=" toolbar" >
<div class="btn-group" style="color:black">


  
 <div style="display:none">
  <button class="fa-button " title="นำออก" th:onclick="'shows(\'draggable-iadmin\',\'นำออก\'); getData(\'' + @{/export/department }+ '\', \'mainbody-iadmin\')'" style="color:black;" data-original-title="Img">
    <i class="material-icons text-default">
      download
    </i>
   </button>
   <button class="fa-button " title="ค้นหา" data-original-title="Img" 
   th:onclick="'shows(\'draggable-iadmin\',\'ค้นหาเเบบละเอียด\'); getData(\'' + @{/department/search }+ '\', \'mainbody-iadmin\')'">
    <i class="material-icons text-default">

      manage_search
    </i>
   </button>
   <button class="fa-button " title="จัดเรียง" data-original-title="Img" th:onclick="'shows(\'draggable-iadmin\',\'จัดเรียง\'); getData(\'' + @{/department/sort }+ '\', \'mainbody-iadmin26\')'">
    <i class="material-icons text-default">

      swap_vert
    </i>
   </button>
  </div>
 
<span style="margin-left:5px;" class="btn-hover-effect">
  <a style="color:black; cursor: pointer;" title="เพิ่มข้อมูล" data-original-title="Img" onclick="shows2('draggable-window3','Static Snapshot'); getData('/setstaticsnapshot-filemanager/${process.id }/0', 'mainbody3')">
        <img src="/app/system/icon/plus.ico">
   </a>
 </span>
 
<span style="margin-left:5px;" class="btn-hover-effect">
  <a style="color:black; cursor: pointer;" title="กรองข้อมูล" data-original-title="Img"  
   onclick="openwindow('draggable-window6','ตัวกรอง','8500');getData('/segmentationView-filemanager/${process.id}','mainbody6');" >
        <img src="/app/system/icon/search.ico">
   </a>
 </span>

    
     
    


   <span style="float:right;padding: 0;justify-content: center; /* จัดให้อยู่กึ่งกลางในแนวนอน */
    align-items: center; /* จัดให้อยู่กึ่งกลางในแนวตั้ง */">

      <input type="text" style="padding:3px;" name="keyword"  id="files-filter2" required="" onkeyup="keywordFilter3(this)">
      </span>




    </div>



    
</div>
                        <table class="my-table3">
                            <thead>
                                <tr >
                                    <th>#</th>
                               
                                    <th>รายการ</th>
                                    <th>วันที่สร้าง</th>
                                     <th>โดย</th>
                                      <th>จำนวนรายชื่อ</th>
                                <th>เอกสารที่นำไปใช้</th>
                                
                              <th>สถานะ</th>
                         
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${ListSegmentation }" var="rows" varStatus="counts"> 
	                    <tr  ondblclick="shows2('draggable-window3','Static Snapshot'); getData('/editstaticsnapshot-filemanager/${rows.id}/${process.id }', 'mainbody3');" style="cursor:copy;">
	                        <td  style="border-left: 0px solid #bbb;">${counts.count }</td>
	                            <td style="border-left: 0px solid #bbb;">${rows.name }</td>
	                   <td style="border-left: 0px solid #bbb;">${rows.date_add }</td>
	                      <td style="border-left: 0px solid #bbb;">${rows.user_add }</td>
	                       <td style="border-left: 0px solid #bbb;">${rows.total_data_select }</td>
	                        <td style="border-left: 0px solid #bbb;">${rows.edata_id_reserve }</td>
	                            <td style="border-left: 0px solid #bbb;">
	                             <c:if test="${rows.status == 1 }" >
	                             <spring:message code="data.available"  text="-​" />
	                          	</c:if>
	                          	
	                          	   <c:if test="${rows.status == 2 }" >
	                          <spring:message code="data.active"  text="-​" />
	                          	</c:if>
	                          	
	                          	   <c:if test="${rows.status == 0 }" >
	                             <spring:message code="data.inactive"  text="-​" />
	                          	</c:if>
	                                                
	                          
	                            
	                            </td>
	                    </tr>
                      </c:forEach>   
                        
                            </tbody>
                        </table>
                    </div>
	</div>
<div id="Form-Tab-Form-2" style="display:none;">
<div class="wpf-datagrid-container" style="min-height:500px">
<div class=" toolbar" >
<div class="btn-group" style="color:black">

 <span style="margin-left:5px;" class="btn-hover-effect">
  <a title="โหลดใหม่" onclick="getData('/app-filemanager/26', 'microsofts26')" style="cursor: pointer;" data-original-title="List"> 
  
     <img src="/app/system/icon/update.ico">
  </a>
  </span>
  
 <div style="display:none">
  <button class="fa-button " title="นำออก" th:onclick="'shows(\'draggable-iadmin\',\'นำออก\'); getData(\'' + @{/export/department }+ '\', \'mainbody-iadmin\')'" style="color:black;" data-original-title="Img">
    <i class="material-icons text-default">
      download
    </i>
   </button>
   <button class="fa-button " title="ค้นหา" data-original-title="Img" th:onclick="'shows(\'draggable-iadmin\',\'ค้นหาเเบบละเอียด\'); getData(\'' + @{/department/search }+ '\', \'mainbody-iadmin\')'">
    <i class="material-icons text-default">

      manage_search
    </i>
   </button>
   <button class="fa-button " title="จัดเรียง" data-original-title="Img" th:onclick="'shows(\'draggable-iadmin\',\'จัดเรียง\'); getData(\'' + @{/department/sort }+ '\', \'mainbody-iadmin26\')'">
    <i class="material-icons text-default">

      swap_vert
    </i>
   </button>
  </div>
 
<span style="margin-left:5px;" class="btn-hover-effect">
  <a style="color:black; cursor: pointer;" title="เพิ่มข้อมูล" data-original-title="Img" onclick="shows2('draggable-window2','New File');getData('/appform-filemanager/26', 'mainbody2');">
        <img src="/app/system/icon/plus.ico">
   </a>
 </span>

    
     
    


   <span style="float:right;padding: 0;justify-content: center; /* จัดให้อยู่กึ่งกลางในแนวนอน */
    align-items: center; /* จัดให้อยู่กึ่งกลางในแนวตั้ง */">

      <input type="text" style="padding:3px;" name="keyword" th:value="" id="files-filter2" required="">
  
      <a type="button" style="color:black;margin-top:3px; cursor: pointer;" data-original-title="Img" onclick="search('files-filter2','tr_datatable')">
          <img src="/app/system/icon/search 2.ico">
       </a>
 
      </span>




    </div>



    
</div>
                        <table>
                            <thead>
                                <tr >
                                    <th>#</th>
                                    <th>รายการ</th>
                                    <th>วันที่สร้าง</th>
                                      <th>จำนวนรายชื่อ</th>
                                
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${exampleData }" var="rows" varStatus="counts"> 
	                    <tr ondblclick="getData('/profileapp-filemanager/${rows.id}/${process.id}?from=${from+0 }', 'microsofts${process.id}');Syncshow()" style="cursor:copy;">
	                        <td  style="border-left: 0px solid #bbb;">${counts.count }</td>
	                        
	                  <c:forEach items="${heads }" var="row" varStatus="count"> 
                      <td style="border-left: 0px solid #bbb;">${rows[row.mainkey]}</td>
                     </c:forEach> 
	           
	           
	                     
	                    </tr>
                      </c:forEach>   
                        
                            </tbody>
                        </table>
                    </div>
	</div>
            </div>

       </div>
  </div>
  
  
 <script>    
     function keywordFilter3(input) {

    	    // 1. รับค่าจาก input
    	    var filterValue = input.value.toLowerCase();

    	    // 2. เลือกแถวทั้งหมด (ข้าม header)
    	    var rows = document.querySelectorAll(".my-table3 tr:not(:first-child)");

    	    rows.forEach(row => {
    	        let rowText = row.textContent.toLowerCase();

    	        if (rowText.includes(filterValue)) {
    	            row.style.display = "";
    	        } else {
    	            row.style.display = "none";
    	        }
    	    });
    	}
  </script>            
   