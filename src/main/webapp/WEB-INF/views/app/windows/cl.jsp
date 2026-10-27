<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<!DOCTYPE html>
<html lang="th">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Appointment and Event Details</title>
    <style>
        :root {
            --bg-color: #f0f0f0;
            --border-color: #d9d9d9;
            --input-border: #7a7a7a;
            --focus-border: #0078d7;
            --text-color: #222;
            --font-stack: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }

 
/* 1. ตรึง Toolbar ไว้บนสุด */
.toolbar {
    position: sticky;
    top: 0;
    z-index: 100;      /* ให้อยู่เหนือชั้นอื่นๆ */
    background: #fff;  /* ต้องใส่สีพื้นหลัง ไม่งั้นเนื้อหาจะทะลุมาข้างหลัง */
}

/* 2. ตรึงหัวตารางไว้ใต้ Toolbar */
/* สมมติ toolbar สูง 32px เราต้องสั่งให้หัวตารางห่างจาก top 32px */
.my-table thead th {
    position: sticky;
    top: 32px;         /* ปรับตามความสูงจริงของ Toolbar */
    z-index: 90;

    border-bottom: 2px solid #dee2e6;
}

    </style>
</head>
<body>

    <div class="cl_window">
      
  



        <div class="window-body">
            <form id="formsegmentation" name="formsegmentation">
                <input type="hidden" name="process_id" class="full-width" value="${process.id }">
                  <input type="hidden" name="edata_id"  value="${edata_id+0 }" readonly>
    <c:if test="${segmentation.id > 0}">
        <input type="hidden" id="id" name="id" value="${segmentation.id}">   
         <input type="hidden" id="process_id" name="process_id" value="${segmentation.process_id}">   
    </c:if>
            
            <div class="top-section">
                <div class="main-form">
                    <div class="form-row">
                        <label>รายการ:</label>
                        <input type="text" name="name" class="full-width" value="${segmentation.name }">
                    </div>
                    <div class="form-row">
                        <label>โดย:</label>
                        <input type="text"  class="full-width" value="${segmentation.user_add }" readonly>
                    </div>
 					<div class="form-row">
                        <label>ปรับปุรงโดย:</label>
                        <input type="text" class="full-width" value="${segmentation.user_edit }" readonly>
                    </div>
					<div class="form-row">
                        <label>เอกสารที่นำไปใช้:</label>
                        <div>
                        <input type="text" class="full-width" value="${segmentation.edata_id_reserve }" readonly>     
                        <c:if test="${segmentation.edata_id_reserve > 55555}"> 
                        <button  type="button" class="sidebar-btn" style="width: 120px;" onclick="getDatadd('/edataUseSegmentation/${segmentation.edata_id_reserve}')">เปิด...</button>
                      </c:if>
                      </div>
                    </div>
                    <div class="form-row">
                        <label>ใช้ร่วมกับโปรโมชั่น:</label>
                        <div>
				                 <select name="promotion">
				    <option value="0">-</option>
				
				    <%-- 1. แสดงโปรโมชั่นที่เคยเลือกไว้ (ป้องกันกรณีที่โปรโมชั่นหมดอายุไปแล้วแต่ข้อมูลเก่ายังต้องโชว์) --%>
				    <c:if test="${not empty userPromotion}">
				        <option value="${userPromotion.id}" <c:if test="${segmentation.promotion == userPromotion.id}">selected</c:if>>
				            ${userPromotion.campaignName}
				        </option>
				    </c:if>
				
				    <%-- 2. วนลูปแสดงโปรโมชั่นอื่นๆ ที่พร้อมใช้งาน (ACTIVE) --%>
				    <c:forEach items="${Promotion}" var="row">
				        <%-- ดักเงื่อนไข: ถ้า ID ของ row ในลูป ไม่ตรงกับ ID ของ userPromotion ถึงจะยอมให้สร้าง option --%>
				        <c:if test="${empty userPromotion or row.id != userPromotion.id}">
				            <option value="${row.id}" <c:if test="${segmentation.promotion == row.id}">selected</c:if>>
				                ${row.campaignName}
				            </option>
				        </c:if>
				    </c:forEach>
				</select>
				                      </div>
                    </div>
                    
                    <div class="separator"></div>

                    <div class="form-row">
                            
                        <label>Status:</label>     
                        	
                        <c:if  test="${segmentation == null ||  segmentation.status == 1 || segmentation.id < 1}">
                        		  <input type="hidden" name="status"  value="1" readonly>
                        		   <input type="text"  class="full-width" value="<spring:message code="data.available"  text="-​" />" readonly>
                        </c:if>
                              <c:if  test="${segmentation.status == 2 }">
                        		  <input type="hidden" name="status"  value="2" readonly>
                        		   <input type="text"  class="full-width" value="<spring:message code="data.active"  text="-​" />" readonly>
                        </c:if>
                               <c:if  test="${segmentation.status == 0 && segmentation.id > 0}">
                        		  <input type="hidden" name="status"  value="0" readonly>
                        		   <input type="text"  class="full-width" value="<spring:message code="data.inactive"  text="-​" />" readonly>
                        </c:if>
                        
                
                 
                              
                        
                    </div>

</form>

                    <div class="separator"></div>


                <div class="form-row">
                

<c:set  var="accessRole"  value="APP-ADMIN-${process.id}" />
          
       
<sec:authorize access="hasAnyAuthority('${accessRole}')" var="hasPermission" />   
<c:if  test="${segmentation.id > 0 }">      
<c:if test="${hasPermission || (segmentation.id_add == Member_id)}">

      <c:if  test="${segmentation != null &&  segmentation.status == 1}">
     <button  type="button" class="sidebar-btn" style="width: 120px;"  
                                 onclick="openwindow('draggable-window3','Quick Filter','9000');getData('/QuickQueryChain2-filemanager/${process.id }/${segmentation.id }', 'mainbody3');" >
                               นำเข้ารายชื่อ...</button>&nbsp;
                               </c:if>

                <button  type="button" class="sidebar-btn" style="width: 120px;"  
                               onclick=" confirmAndGet_closeed2('/delsegmentation-filemanager/${segmentation.id }/${process.id }','wpf-content-indexapp','<spring:message code="policy.deleteconfirm"  text="-​" />','draggable-window3');" >
                               <spring:message code="btn.delete"  text="-​" />...</button>&nbsp;
                               
                                     <button  type="button" class="sidebar-btn" style="width: 120px;"  
                               onclick="confirmAndGet('/unSyncBack-filemanager/${segmentation.id }/${process.id }','mainbody3','<spring:message code="btn.onconfirm"  text="-​" />','draggable-window2');" >
                               นำออก..</button>&nbsp;

             		      
</c:if>               		
        
        
        
      
                   
                        
                          
                      			  <button  type="button" class="sidebar-btn" style="width: 120px;" 
                      			   onclick="confirmAndGet('/copySyncBack-filemanager/${segmentation.id }/${process.id }','wpf-content-indexapp','คุณเเน่ใจจะคัดลอก','draggable-window2');" >คัดลอก...</button>
                           
                 </c:if>                     
                    </div>
                    
                
          
                    
                    
                        
                </div>

                <div class="sidebars" style="width:550px">
              <div id="tm-v2-wrapper" >
<div class="tm-window"  >
            
   
             <div class="tm-tab-bar" style="width:100%"> 
    
 <div class="tm-tab  active"  >InFo</div>

            </div>

            <div >
                

 
	<div id="Form-Tabs-Form-1" >
   <div class="wpf-datagrid-container" style="padding:10px">
   
    <div class="sidebar-row">
                        <label style="width: 125px;">วันที่สร้าง:</label>
                         <input type="text" value="${segmentation.date_add }" style="width: 350px;" readonly>
                    </div>
                          <div class="sidebar-row">
                        <label style="width: 125px;">วันที่ปรับปรุง:</label>
                         <input type="text" value="${segmentation.date_edit }" style="width: 350px;" readonly>
                    </div>
                    <div class="sidebar-row">
                        <label style="width: 125px;">จำนวนข้อมูลที่เลือก:</label>
                         <input type="text" value="${segmentation.total_data_select+0 } ROWS"   style="width: 350px;" readonly>
                         
                         
                        <input type="hidden" id="total_data_select">
                    
                    </div>
                    <br>
                    <hr>
                <br>
                     <div class="sidebar-row">
                        <label style="width: 125px;">เงื่อนไขการเลือก:</label><br>
                   
                    </div>
                       <textarea  rows="5" style="width: 500px;">${segmentation.segmentation_select}</textarea>
                      
</div>
	</div>

            </div>

       </div>
  </div>
         
                    
    
                       
             

                    
                </div>
            
            </div>

            <div class="table-container" >
            <div class=" toolbar" style="min-height: 32px;">
<div class="btn-group" style="color:black">


  
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
 

    
     
    


   <span style="float:right;padding: 0;justify-content: center; /* จัดให้อยู่กึ่งกลางในแนวนอน */
    align-items: center; /* จัดให้อยู่กึ่งกลางในแนวตั้ง */">

      <input type="text" style="padding:3px;" name="keyword"  id="files-filter2" placeholder="Filter" onkeyup="keywordFilter2(this)">
  

       
    
      </span>




    </div>



    
</div>
            <div id="Synctable">
         	</div>
            </div>
            
           
            

     <script type="application/json" id="Syncjsoninput">
    ${segmentation.segmentation_member}
</script>
     
 	 <input type="hidden" id="Syncprocess" name="Syncprocess" value="0">  
        </div>

        <div class="window-footer">
            <button class="primary" onclick="postData('/savesegmentation-filemanager?${_csrf.parameterName}=${_csrf.token}', 'formsegmentation','mainbody3');">OK</button>
            <button onclick="closeed2('draggable-window3')">Cancel</button>
        </div>
    </div>

</body>

  <div id="SaveCl"></div>               
 <script>    
 
 

 
     loadSyncshow();

     
     
     function keywordFilter2(input) {

    	    // 1. รับค่าจาก input
    	    var filterValue = input.value.toLowerCase();

    	    // 2. เลือกแถวทั้งหมด (ข้าม header)
    	    var rows = document.querySelectorAll(".my-table tr:not(:first-child)");

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

</html>