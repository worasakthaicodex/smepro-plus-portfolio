<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
   <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" rel="stylesheet">
    <style>
.badgealert {
    display: inline-flex;
    justify-content: center;
    align-items: center;

    min-width: 18px;
    height: 18px;
    padding: 0 5px;

    background-color: #e53935;   /* แดงนุ่มลง */
    color: white;
    font-size: 11px;
    font-weight: 600;

    border-radius: 999px;

    margin-left: 6px;  /* ดันไปชิดขวาจากข้อความ */
}


/* จัดการกรอบ Right Panel ให้แสดงด้านขวาและมี Scrollbar */
.wpf-right-panels {
    display: none; /* ซ่อนไว้ก่อน */
width:400px;
    border-left: 1px solid #ccc;
    box-shadow: -2px 0 5px rgba(0,0,0,0.1);
    padding: 15px;
    overflow-y: auto;
    height: 100%;
    z-index: 100;
}

.wpf-right-panels.active {
    display: block; /* แสดงเมื่อถูกเรียก */
}

/* ปรับฟอร์มให้เป็นแนวตั้ง 100% ไม่แบ่งครึ่ง 48% แล้ว */
.wpf-right-panels .main-form {
    width: 100% !important;
    margin-bottom: 15px;
}

.wpf-right-panels .form-row {
    margin-bottom: 10px;
    display: flex;
    flex-direction: column; /* ให้ Label อยู่บน Input */
}

.wpf-right-panels .form-row label {
    font-weight: bold;
    margin-bottom: 5px;
    font-size: 12px;
}

.wpf-right-panels .full-width {
    width: 100%;
    padding: 6px;
    border: 1px solid #ccc;
    border-radius: 4px;
}

/* ตกแต่ง Tab ให้ดูง่ายขึ้น */
.tm-tab-bar {
    display: flex;
    border-bottom: 2px solid #ddd;
    margin-bottom: 15px;
}
.tm-tab {
    padding: 8px 12px;
    cursor: pointer;
    font-size: 13px;
    border-bottom: 2px solid transparent;
    margin-bottom: -2px;
}
.tm-tab.active {
    border-bottom: 2px solid #007bff; /* สีน้ำเงิน ERP */
    color: #007bff;
    font-weight: bold;
}

  
/* ใส่ในไฟล์ CSS ของคุณ */
.loading-overlay {
    /* หัวใจสำคัญ: ใช้ absolute เพื่อให้อยู่แค่ในกรอบแม่ */
    position: absolute; 
    top: 0; left: 0; right: 0; bottom: 0;
    width: 100%; height: 100%;
    background: rgba(255, 255, 255, 0.85);
    display: flex; flex-direction: column; justify-content: center; align-items: center;
    z-index: 1000; /* อยู่บนสุดของกล่องแม่ */
    backdrop-filter: blur(3px);
}

.loader-spinner {
    width: 40px; height: 40px;
    border: 4px solid #f3f3f3; border-top: 4px solid #3498db;
    border-radius: 50%; animation: spin-loader 1s linear infinite;
}

.loading-text {
    margin-top: 10px; font-size: 14px; color: #333; font-weight: bold;
}

@keyframes spin-loader {
    0% { transform: rotate(0deg); }
    100% { transform: rotate(360deg); }
}
      </style>
<c:set  var="accessRole"  value="APP-ADMIN-${process.id}" />
<sec:authorize access="hasAnyAuthority('${accessRole}')" var="hasPermission" />            
      
    <div id="wpf-wrapper">
        <div class="wpf-window">
            

           <div class="wpf-menu-bar">
         <div class="wpf-menu-item">
        <span><spring:message code="profile.CreationEfficiency" text="-​" /></span>
        <div class="wpf-dropdown">
        
         
<c:if test="${process.register == 'Profile'}">
<c:set  var="url"  value="addprofile-filemanager" />
</c:if>
<c:if test="${process.register == 'Product'}">
<c:set  var="url"  value="addproduct-filemanager" />
</c:if>

         <c:if test ="${ process.direct_data_edit_mode == 1 }">
         <c:if test="${hasPermission }">   
            <div class="wpf-drop-item" onclick="openwindows('draggable-window3','AddData','1200px');getData('/${url}/${process.id}', 'mainbody3');">
            <spring:message code="btn.add" text="-​" />
            </div>
          </c:if>  
            </c:if>  
            
        </div>
    </div>
    
 
  
    
      <div class="wpf-menu-item" >
        <span>การตั้งค่า</span>
        <div class="wpf-dropdown">  
<c:if test="${hasPermission }">    
            <div class="wpf-drop-item" onclick="shows2('draggable-window6','Link'); getData('/CustomizeTableHeaders-filemanager/${process.id}', 'mainbody6'); set_title_app('miniwindows','ส่วนขยาย')">ส่วนขยาย</div>
</c:if>   
 <div class="wpf-drop-item" onclick="shows2('draggable-window6','Link');getData('/AutomatedGrading-filemanager/${process.id}', 'mainbody6');set_title_app('miniwindows','การตัดเกรดอัตโนมัติ')">การตัดเกรดอัตโนมัติ</div>
        </div>
    </div>
    
    
    <div class="wpf-menu-item" style="display:none">
        <span>Watchlist</span>
        <div class="wpf-dropdown">
            <div class="wpf-drop-item">Monitor</div>
            <div class="wpf-drop-item">Threshold</div>
        </div>
    </div>

    <div class="wpf-menu-item" style="display:none">
        <span>Validation</span>
        <div class="wpf-dropdown">
            <div class="wpf-drop-item">Duplicate Check</div>
            <div class="wpf-drop-item">Merge Function</div>
           
            </div>
    </div>

     <div class="wpf-menu-item">
        <span>Mass Operations</span>
        <div class="wpf-dropdown">
            <div class="wpf-drop-item">Bulk Edit / Mass Update</div>
            <div class="wpf-drop-item" onclick="shows2('draggable-window6','Import'); getData('/formMassOperationsImport-filemanager/${process.register}/${process.id}', 'mainbody6');">Import</div>
          

<c:if test="${process.register == 'Profile'}">
   <div class="wpf-drop-item" onclick="shows2('draggable-window6','Import [Extension]'); getData('/formMassOperationsExtensionImport-filemanager/${process.id}', 'mainbody6');">Import [Extension]</div>        
</c:if>
 <div class="wpf-drop-item" onclick="shows2('draggable-window6','Import'); getData('/formMassOperationsImport-filemanager/Files/${process.id}', 'mainbody6');">Import Files index</div>
            
            
             <div class="wpf-drop-item">Export</div>
        </div>
    </div>
    

    
    <div style="margin-left:auto; display:flex; align-items:center;">
 

    </div>
</div>
           <c:set  var="accessRole"  value="APP-USER-${process.id}" />
				<c:set  var="accessRole2"  value="APP-ADMIN-${process.id}" />
            <div class="wpf-body">
                      <sec:authorize access="!hasAnyAuthority('${accessRole}','${accessRole2}')">     
  		<font style="color:red;padding:10px">Error Has not Any Authority</font>				
      </sec:authorize>
      
        
    <sec:authorize access="hasAnyAuthority('${accessRole}','${accessRole2}')">   
                <div class="wpf-sidebar">
                    <ul class="wpf-nav-list">
         <li class="wpf-nav-item active" id="tabindex-1" onclick="Loader2.show('wpf-content-indexapp','กำลังโหลดข้อมูล process...');saveActiveTab('tabindex-1');switchtabActive('index','8','1');getData('/indexapp-filemanager/${process.id}', 'microsofts${process.id}');">
         <spring:message code="profile.Data" text="-​" /></li>
       
       <c:if test="${process.register == 'Profile'}">
         <li class="wpf-nav-item" id="tabindex-2" onclick="Loader2.show('wpf-content-indexapp','กำลังโหลดข้อมูล process...');saveActiveTab('tabindex-2');switchtabActive('index','8','2');getData('/GroupingCategorization-filemanager/${process.id}', 'wpf-content-indexapp');">
           <spring:message code="profile.Grouping_Categorization" text="-​" /></li>
         <li class="wpf-nav-item" id="tabindex-3" onclick="Loader2.show('wpf-content-indexapp','กำลังโหลดข้อมูล process...');saveActiveTab('tabindex-3');switchtabActive('index','8','3');getData('/segmentation-filemanager/${process.id}', 'wpf-content-indexapp');">
          <spring:message code="profile.Segmentation" text="-​" /></li>
         <li class="wpf-nav-item" id="tabindex-4"  onclick="Loader2.show('wpf-content-indexapp','กำลังโหลดข้อมูล process...');saveActiveTab('tabindex-4');switchtabActive('index','8','4');getData('/transactionflow-filemanager/${process.id}', 'wpf-content-indexapp');">
         <spring:message code="profile.Audit_History" text="-​" /></li>
        
        
         <li class="wpf-nav-item" id="tabindex-5" style="display:none" onclick="Loader2.show('wpf-content-indexapp','กำลังโหลดข้อมูล process...');saveActiveTab('tabindex-5');switchtabActive('index','8','5');getData('/DataUpdateRequests-filemanager/${process.id}', 'wpf-content-indexapp');"> 
         <spring:message code="profile.DataUpdateRequests" text="-​" /></li>
        
        
         <li class="wpf-nav-item" id="tabindex-6" onclick="Loader2.show('wpf-content-indexapp','กำลังโหลดข้อมูล process...');saveActiveTab('tabindex-6');switchtabActive('index','8','6');getcalendarData('/reportjsonappAll-filemanager', 'wpf-content-indexapp');loadcalendar();"> 
         <spring:message code="profile.Calender" text="-​" /></li>
        
              <li class="wpf-nav-item" id="tabindex-7"  onclick="Loader2.show('wpf-content-indexapp','กำลังโหลดข้อมูล process...');saveActiveTab('tabindex-7');switchtabActive('index','8','7');getData('/HelpDeskprofile-filemanager/${process.id}', 'wpf-content-indexapp');">
         <spring:message code="profile.HelpDesk" text="-" />
         <c:if test="${countOpen > 0 }"><span class="badgealert">${countOpen }</span></c:if></li>
        
        
          </c:if>        
                           <li class="wpf-nav-item" id="tabindex-8" onclick="Loader2.show('wpf-content-indexapp','กำลังโหลดข้อมูล process...');saveActiveTab('tabindex-8');switchtabActive('index','8','8');
                           getData('/customer_insight-filemanager/${process.id}','wpf-content-indexapp') "> 
      Customer Insights
         </li>
                  
                    </ul>
                </div>

                <div class="wpf-content" id="wpf-content-indexapp" style="max-height: 95%">
          
     
     <div id="wpf-searchdata${process.id}" style="max-height: 95%"><%@ include file="indexapp_data.jsp" %>      </div>
                </div>



<div class="wpf-right-panels" id="rightPanelForm">
    <!-- Header ของ Panel -->
    <div style="display: flex; justify-content: space-between; align-items: center; border-bottom: 1px solid #eee; padding-bottom: 10px; margin-bottom: 15px;">
        <h3 style="margin: 0; font-size: 16px;">ค้นหาเเบบละเอียด</h3>
  
          <div style="margin-top: 10px; text-align: right; padding: 10px;">
            <button type="button" class="btn" style="padding: 8px 15px; border: 1px solid #ccc; background: #fff; background: black; border-radius: 4px; cursor: pointer;" onclick="exportTableToCSV('ERP_Data_Export.csv')">นำออก</button>
            <button type="submit" class="btn" style="padding: 8px 15px; background: #007bff; color: #fff; border: none; 
            margin-left: 10px; border-radius: 4px; cursor: pointer;" 
            onclick="postDataNotReset('/indexAppFullSearch-filemanager?${_csrf.parameterName}=${_csrf.token}', 'formprofile${process.id}', 'wpf-searchdata${process.id}');">
                <svg viewBox="0 0 24 24" width="14" height="14" fill="white" style="vertical-align: text-top; margin-right: 4px;">
                    <path d="M15.5 14h-.79l-.28-.27C15.41 12.59 16 11.11 16 9.5 16 5.91 13.09 3 9.5 3S3 5.91 3 9.5 5.91 16 9.5 16c1.61 0 3.09-.59 4.23-1.57l.27.28v.79l5 4.99L20.49 19l-4.99-5zm-6 0C7.01 14 5 11.99 5 9.5S7.01 5 9.5 5 14 7.01 14 9.5 11.99 14 9.5 14z"/>
                </svg>
                ค้นหา
            </button>
        </div>
    </div>

    <!-- เริ่มฟอร์มของคุณ -->
    <form id="formprofile${process.id}" name="formprofile${process.id}" enctype="multipart/form-data">
        <input type="hidden" name="process_id" class="full-width" value="${process.id}">
        <input type="hidden" name="register_type" class="full-width" value="ลูกค้า">
        
        
        <c:if test="${item.id > 0}">
            <input type="hidden" id="id" name="id" value="${item.id}">
            <input type="hidden" id="registerCode" name="customerCode" value="${item.register_code}${item.register_daterun}-${item.register_id}">
        </c:if>
        
        

        <div class="top-section" style="padding: 10px;">
            <!-- รวมเป็น Column เดียว แนวตั้ง 100% -->
            <div class="main-form" style="width: 100%;">
                
        

                <div class="form-row">
            
			              <div class="form-row">
			    <label>ID (เลขที่อ้างอิง):</label>
			    <!-- เปลี่ยน name ให้สอดคล้องกับ ID -->
			    <input type="text" name="search_id" class="full-width" value="${item.id}" placeholder="ระบุ ID ที่ต้องการ">
			</div>
			
                <div class="form-row">
                    <label>ชื่อ:</label>
                    <input type="text" name="register_name" class="full-width" value="${item.register_name }">
                </div>
             
                <div class="form-row">
                    <label>สถานะ:</label>
                    <select name="register_status" class="full-width" multiple>
                        <option value="">-- ทั้งหมด --</option>
                        <c:set var="status" value="ACTIVE" />
                        <c:if test="${item != null }"> <c:set var="status" value="${item.register_status}" /></c:if>
                        <c:forEach items="${Register_status }" var="row">
                            <option value="${row.dataset_data_title }" <c:if test="${status == row.dataset_data_title }">selected</c:if>> ${row.dataset_data_title }</option>
                        </c:forEach>
                    </select>
                </div>
             
                <div class="form-row">
                    <label>หมวดหมู่ (Groups):</label> 
                    <select name="register_groups" class="full-width" multiple>
                        <option value="">-- ทั้งหมด --</option>
                        <c:forEach items="${Register_groups }" var="row">
                            <option value="${row.dataset_data_title }" <c:if test="${item.register_groups == row.dataset_data_title }">selected</c:if>> ${row.dataset_data_title }</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-row">
                    <label>ประเภทธุรกิจ:</label>
                    <select name="business_type" class="full-width" multiple>
                        <option value="">-- ทั้งหมด --</option>
                        <c:forEach items="${Business_category }" var="row">
                            <option value="${row.dataset_data_title }" <c:if test="${item.product_category == row.dataset_data_title }">selected</c:if>> ${row.dataset_data_title }</option>
                        </c:forEach>
                    </select>
                </div>
                 <div class="form-row">
                  <label>ภูมิภาค (Region):</label>
                                            <select name="region" class="full-width" multiple>
                                                <option value="">-- ทั้งหมด --</option>
                                                <c:forEach items="${Region }" var="row">
                                                    <option value="${row.dataset_data_title }" <c:if test="${item.region == row.dataset_data_title }">selected</c:if>> ${row.dataset_data_title }</option>
                                                </c:forEach>
                                            </select>
             </div>
             
                   <div class="form-row">
                     <label>มือถือองค์กร:</label>
                                            <input type="text" class="full-width" name="mobile_number" value="${item.mobile_number}">
                                        </div>
             </div>
             <div class="form-row">
                                            <label>อีเมลบริษัท:</label>
                                            <input type="text" class="full-width" name="register_email" value="${item.register_email}">
                                        </div>
       
                   <div class="form-row">
                                            <label>ชื่อผู้ติดต่อ:</label>
                                            <input type="text" class="full-width" name="contact_person" value="${item.contact_person}">
                                        </div>                     
                     <div class="form-row">
                                            <label>ชื่อผู้อนุมัติ:</label>
                                            <input type="text" class="full-width" name="contact_approvers" value="${item.contact_approvers}">
                                        </div>    
                                        
                                         <div class="form-row">
                                            <label>เกรดลูกค้า:</label>
                                              <select name="register_grade" class="full-width" multiple>
                        <option value="">-- ทั้งหมด --</option>
                    <option value="A">A</option>
                   <option value="B">B</option>
                     <option value="C">C</option>
                     <option value="NEW">NEW</option>
                     <option value="NONE">ไม่มีเกรด</option>
                    </select>
                                        </div>    
                                        
                                        <br>       <br>       <br>       <br>       <br>       <br>              
            </div>
        </div>

        <!-- ปุ่มค้นหาและยกเลิก -->

    </form>
</div>     

</sec:authorize>
            </div>
        </div>
    </div>

<script>

loadthispage();

function exportTableToCSV(filename) {
    // 1. ดึงข้อมูลแถวทั้งหมดจากตาราง
    var rows = document.querySelectorAll("#table-wrapper tr");
    var csvData = [];

    // 2. วนลูปอ่านค่าทีละแถว
    for (var i = 0; i < rows.length; i++) {
        var row = [], cols = rows[i].querySelectorAll("td, th");

        for (var j = 0; j < cols.length; j++) {
            var col = cols[j];
            
            // ดึงข้อความ: ถ้ามี attribute title (ข้อความเต็ม) ให้ใช้ title ถ้าไม่มีให้ใช้ innerText
            var data = col.getAttribute("title") || col.innerText || "";

            // จัดการกับเครื่องหมาย Double Quote (") ในข้อความ และลบช่องว่างหัวท้าย
            data = data.replace(/"/g, '""').trim();

            // ครอบข้อความด้วย Double Quote ("...") เพื่อป้องกันปัญหาเวลาข้อความมีลูกน้ำ (,) หรือขึ้นบรรทัดใหม่
            row.push('"' + data + '"');
        }
        
        // นำข้อมูลแต่ละคอลัมน์มาต่อกันด้วยลูกน้ำ (,)
        csvData.push(row.join(","));
    }

    // 3. รวมแถวทั้งหมดเข้าด้วยกันด้วยการขึ้นบรรทัดใหม่ (\n)
    var csvString = csvData.join("\n");

    // 4. ดาวน์โหลดไฟล์ CSV
    downloadCSV(csvString, filename);
}

function downloadCSV(csv, filename) {
    var csvFile;
    var downloadLink;

    // เพิ่ม BOM (\uFEFF) เข้าไปด้านหน้า เพื่อให้โปรแกรมอย่าง MS Excel อ่านภาษาไทย (UTF-8) ได้ถูกต้อง
    var universalBOM = "\uFEFF";
    
    // สร้าง Blob object เพื่อเก็บข้อมูล CSV
    csvFile = new Blob([universalBOM + csv], { type: "text/csv;charset=utf-8;" });

    // สร้างแท็ก <a> จำลองขึ้นมาเพื่อสั่งให้เบราว์เซอร์ดาวน์โหลดไฟล์
    downloadLink = document.createElement("a");
    downloadLink.download = filename;
    downloadLink.href = window.URL.createObjectURL(csvFile);
    downloadLink.style.display = "none";
    document.body.appendChild(downloadLink);

    // จำลองการคลิกเพื่อดาวน์โหลด
    downloadLink.click();

    // ลบแท็ก <a> ทิ้งเมื่อดาวน์โหลดเสร็จ
    document.body.removeChild(downloadLink);
}
</script>

<script>
    // ฟังก์ชันสำหรับสลับเปิด/ปิด Panel ด้านขวา
    function toggleRightPanel(event) {
        if(event) event.preventDefault();
        var panel = document.getElementById('rightPanelForm');
        panel.classList.toggle('active');
    }

    // ฟังก์ชันสำหรับเปลี่ยน Tab ภายในฟอร์ม
    function switchTab(groupName, totalTabs, activeIndex) {
        // วนลูปซ่อนเนื้อหาและเอาคลาส active ออกจาก Tab ทั้งหมด
        for (let i = 1; i <= totalTabs; i++) {
            document.getElementById('Form-' + groupName + '-' + i).style.display = 'none';
            document.getElementById('Tab-' + groupName + '-' + i).classList.remove('active');
        }
        
        // แสดงเนื้อหาและใส่คลาส active ให้ Tab ที่ถูกเลือก
        document.getElementById('Form-' + groupName + '-' + activeIndex).style.display = 'block';
        document.getElementById('Tab-' + groupName + '-' + activeIndex).classList.add('active');
    }

    // (สคริปต์ TomSelect ของคุณวางต่อตรงนี้ได้เลยตามปกติ)
</script>

 <script>
     
     
     /**
      * ระบบแสดงผลหน้าจอ Loading
      */
      var Loader2 = {
     		    // ใส่ชื่อ ID กล่องที่ต้องการให้มันไปโผล่
     		    show: function(containerId, message = "กำลังโหลด...") {
     		        const container = document.getElementById(containerId);
     		        if (!container) {
     		            console.warn("หาเป้าหมายไม่เจอ:", containerId);
     		            return;
     		        }

     		        // *** บังคับให้กล่องแม่เป็น relative ไม่งั้น overlay จะทะลุออกไปข้างนอก ***
     		        if (window.getComputedStyle(container).position === 'static') {
     		            container.style.position = 'relative';
     		        }

     		        // เช็คว่าในกล่องนี้มี overlay สร้างไว้หรือยัง
     		        let overlay = container.querySelector('.loading-overlay');
     		        
     		        if (!overlay) {
     		            // ถ้ายังไม่มี สร้างใหม่แล้วยัดลงไป
     		            overlay = document.createElement('div');
     		            overlay.className = 'loading-overlay';
     		            overlay.innerHTML = `
     		                <div class="loader-spinner"></div>
     		                <div class="loading-text">${message}</div>
     		            `;
     		            container.appendChild(overlay);
     		        } else {
     		            // ถ้ามีแล้ว แค่เปิดให้แสดงแล้วเปลี่ยนข้อความ
     		            overlay.querySelector('.loading-text').innerText = message;
     		            overlay.style.display = 'flex';
     		        }
     		    },

     		    // สั่งปิด โดยอ้างอิงจาก ID กล่องเดิม
     		    hide: function(containerId) {
     		        const container = document.getElementById(containerId);
     		        if (!container) return;

     		        const overlay = container.querySelector('.loading-overlay');
     		        if (overlay) {
     		            // ปิดการแสดงผล
     		            overlay.style.display = 'none'; 
     		            
     		            // หรือถ้าอยากลบทิ้งไปเลยเพื่อคืนหน่วยความจำ ให้ใช้:
     		            // overlay.remove();
     		        }
     		    }
     		};
         </script>
     