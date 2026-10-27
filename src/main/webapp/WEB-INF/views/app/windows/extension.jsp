<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="th">
<head>
<meta charset="UTF-8">
<title>Clark M. Admiral - Amicus Attorney</title>


<style>


    .bottombar{height:52px;background:#e6f0fa;border-top:1px solid #ccc;display:flex;justify-content:space-between;align-items:center;padding:0 20px;}
    .basics button{padding:7px 18px;border:1px solid #999;background:#fff;border-radius:3px;cursor:pointer;font-size:12px;}

    

 .table-row-hover {
    transition: all 0.2s ease;
    border-left: 3px solid transparent; /* เตรียมพื้นที่ขอบไว้ */
}

.table-row-hover:hover {
    background-color: #f8fafc; /* เทาอ่อนมาก ดูคลีน */
    border-left: 3px solid #3b82f6; /* ขีดน้ำเงินเด่นๆ ด้านซ้าย */
    color: #1e40af;
    cursor: pointer;
    /* ตัด box-shadow ออกเพื่อให้ดูไม่ลอยเกินไป */
}
    .basics label {
            width: 161px;
            font-family: 'Segoe UI', sans-serif;
    font-size: 13px;
            color: var(--text-color);
        }


      .basics  input[type="text"], 
       .basics input[type="date"], 
       .basics input[type="time"],
         
      .basics  select {
            padding: 3px 5px;
            
    border: 1px solid #bbb;
            font-family: var(--font-stack);
            font-size: 13px;
            height: 26px;
        }
        
             .basics textarea{
                  padding: 3px 5px;
                    border: 1px solid #bbb;
            font-family: var(--font-stack);
            font-size: 13px;
            width: 100%;
             }
        
            .basics  input[type="text"]:focus, 
      .basics  input[type="date"]:focus, 
      .basics  input[type="time"]:focus,
            .basics  textarea,
     .basics   select:focus {
            border-color: var(--focus-border);
            outline: none;
        }
.wpf-header-text {
    font-size: 18px !important;
    font-weight: normal !important;
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif !important;
    color: #000 !important;
}   
 .deleted {
    text-decoration: line-through;
    color: #999;  /* แนะนำ: เปลี่ยนสีเป็นเทาด้วย เพื่อให้รู้ว่าถูกยกเลิก */
    opacity: 0.7; /* แนะนำ: ทำให้จางลงนิดนึง */
}  



    /* ตกแต่ง Resizer ให้โชว์สวยๆ */
    .resizer {
        position: absolute;
        right: 0;
        top: 0;
        width: 5px;
        cursor: col-resize;
        user-select: none;
    }
    
    .resizer:hover {
        border-right: 2px solid #1890ff;
    }


/* จุดสำคัญ: ตัวคุมการ Stick */
    #ex-${useextension.id}-${process.id } thead th {
      
        top: 0;             
        background-color: #f8f9fa !important; 
        
        /* 1. เพิ่ม background-clip เพื่ออุดรอยรั่วขอบตาราง ไม่ให้สีพื้นหลังโปร่งใสตรงขอบ */
        background-clip: padding-box !important; 
        
        z-index: 100;                
        box-shadow: 0 2px 2px -1px rgba(0, 0, 0, 0.1); 

        /* 2. ห้ามลากคลุมเลือกข้อความ (ห้ามเลือกหัว) */
        -webkit-user-select: none; /* สำหรับ Safari/Chrome */
        -moz-user-select: none;    /* สำหรับ Firefox */
        -ms-user-select: none;     /* สำหรับ IE/Edge */
        user-select: none !important;        /* มาตรฐาน */
        
         position: sticky !important; /* บังคับให้เป็น Sticky */
      
    }

    /* กรณีตารางมี Border-collapse: collapse จะทำให้เส้นหายเวลาเลื่อน */
    /* ให้ใช้สไตล์นี้แก้เรื่องเส้นขอบครับ */
    #ex-${useextension.id}-${process.id } {
        border-collapse: separate; 
        border-spacing: 0;
    }
	.mytableex, 
.mytableex * {
    -webkit-user-select: text !important;
    -moz-user-select: text !important;
    -ms-user-select: text !important;
    user-select: text !important;
}

.mytableex td {
    /* 1. สำคัญที่สุด: ทำให้แสดงผลการเคาะบรรทัด (\n) และตัดคำเมื่อยาวเกินช่อง */
    white-space: pre-wrap !important; 
    
    /* 2. ช่วยตัดคำที่ยาวมากๆ (เช่น URL หรือรหัสที่ไม่มีเว้นวรรค) ไม่ให้ทะลุตาราง */
    word-break: break-word; 
    overflow-wrap: break-word;
    
    /* 3. จัดให้ข้อความอยู่ชิดบน เพื่อความสวยงามเวลาช่องอื่นสูงกว่า */
    vertical-align: top; 
}

/* ถ้าอยากคุมความกว้างไม่ให้กางจนเกินไป */
.mytableex {
    table-layout: auto; /* หรือใช้ fixed ถ้าต้องการคุมความกว้างคอลัมน์ให้เป๊ะ */
    width: 100%;        /* ให้กางเต็ม Container */
}

                                 .disabled-click {
    pointer-events: none; /* ห้ามคลิก */
    opacity: 0.5;         /* ทำให้จางลง */
    cursor: not-allowed;
} 

/* ========================================================
   JERRY RAT BI - SMART ENTRY CARD (แปลงร่างเป็น Classic ERP)
   *** ใช้ HTML คลาสเดิม ไม่ต้องแก้โค้ด HTML แล้ว ***
   ======================================================== */

.smart-entry-card {
    background-color: #f5f5f5; /* พื้นเทาอ่อน */
    border: 1px solid #dcdcdc; /* ขอบเทา */
    border-radius: 0; /* เอาขอบมนทิ้งให้หมด */
    padding: 10px 15px;
    margin-bottom: 15px;
    box-shadow: none; /* เอาเงาทิ้งไปเลย ไม่ต้องแฟนซี */
}

.smart-form-grid {
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
    gap: 15px; 
    align-items: flex-start;
}

.sf-group {
    display: flex;
    flex-direction: column;
    gap: 4px;
}

.sf-group label {
    font-size: 11px;
    font-weight: bold;
    color: #444; /* ตัวหนังสือสีเข้มขึ้น */
    text-transform: none; /* ไม่บังคับตัวพิมพ์ใหญ่แล้ว */
    letter-spacing: normal;
    margin: 0;
}

.sf-group input, 
.sf-group select, 
.sf-group textarea {
    width: 100%;
    padding: 4px 8px;
    border: 1px solid #aaa; /* ขอบดิบๆ ชัดๆ แบบ Classic */
    border-radius: 0; /* ขอบเหลี่ยม */
    font-size: 12px;
    background-color: #fff;
    color: #222;
    height: 28px; /* บังคับความสูง */
    outline: none;
}

/* ปราบ Textarea ให้สูงเท่า Input */
.sf-group textarea {
    height: 28px;
    min-height: 28px;
    resize: vertical; 
    padding-top: 6px;
}

.sf-group input:focus, 
.sf-group select:focus, 
.sf-group textarea:focus {
    border-color: #0A6ED1; /* แค่เปลี่ยนสีขอบตอนพิมพ์ ไม่มีเงาฟุ้งๆ */
}

.sf-group input[readonly] {
    background-color: #e9ecef;
    color: #555;
    border-color: #ccc;
}


.span-2 { grid-column: span 2; }

/* ========================================================
   อัปเดต: เอาปุ่ม Save ขึ้นมาอยู่บรรทัดเดียวกัน
   ======================================================== */

.sf-actions {
    /* เอา grid-column ทิ้งไป เพื่อให้มันต่อแถวเป็นคอลัมน์ปกติ */
    display: flex;
    justify-content: flex-end; /* ดันปุ่มไปชิดขวาของคอลัมน์ */
    align-items: flex-start; 
    margin-top: 0;
    padding-top: 0;
    border-top: none; /* ลบเส้นประทิ้งไป */
}

/* ปุ่ม Save */
.btn-primary {
    background-color: #000;
    color: white;
    border: none;
    height: 28px;
    padding: 0 25px;
    border-radius: 0;
    font-size: 12px;
    font-weight: bold;
    cursor: pointer;
    display: flex;
    align-items: center;
    gap: 6px;
    
    /* 👇 ทริคสำคัญ: ดันปุ่มลงมา 15px เพื่อชดเชยพื้นที่ของ Label 
       ทำให้ปุ่มเสมอกับช่อง Input พอดีเป๊ะ! 👇 */
    margin-top: 15px; 
}

.btn-primary:hover { 
    background-color: #333;
}

/* นำไปใส่ในไฟล์ CSS ของคุณ */
textarea.form-control {
    overflow-y: hidden; /* ซ่อนแถบ Scrollbar แนวตั้งไม่ให้เกะกะ */
    resize: none;       /* ปิดการใช้เมาส์ลากมุมขวาล่างทิ้งไปเลย */
    transition: height 0.1s ease; /* ทำให้ตอนขยายดูนุ่มนวลขึ้น */
}
</style> 
</head>

<body>


<div id="saveex-${useextension.id }-${Tray}">

<c:if test ="${drawTable == true && eformfields.size() > 0 }">


<form action="#" id="extensionForms${useextension.id }-${Tray}"  name="extensionForms${useextension.id}-${Tray}" 
   method="POST" enctype="multipart/form-data" >
       
         <div class="smart-entry-card">
        <div class="smart-form-grid">
            
           <c:set var="keycount" value="0" />
              <c:forEach items="${eformfields }" var="row" varStatus="count"> 
    
          <c:if test ="${row.field_cal_type == null or row.field_cal_type == '-' }">
             <div class="sf-group  <c:if test='${row.field_type.equals("textarea")}'>span-2</c:if>">
                    <label>${row.field_label } </label>
                    
                      <%@ include file="input/common_input2.jspf" %>  
        
                </div>
                </c:if>
                </c:forEach>
           
         <div class="sf-actions">

            <button type="button" class="btn-save btn-primary" onclick="handleButtonClick_${useextension.id}_${Tray}(this)"><i class="fa fa-save"></i> Save</button>
        </div>
        
          </div>
          </div>
      
             
             
             <c:if test="${edata != null }">
               <input type="hidden" name="data_id"    value="${edata.data_id }">  
              </c:if>
              
<input type="hidden" name="eform_id"    value="${useextension.eform_id }">              
<input type="hidden" name="processes_id"    value="${process.id }">              
<input type="hidden" name="id_refer"    value="${item_id }"> 
<input type="hidden" name="extension_id"    value="${useextension.id }">  
<input type="hidden" name="customer"    value="${CustomerText}"> 
<input type="hidden" name="Tray"    value="${Tray}"> 


<c:if test="${from != null }">
            <input type="hidden" name="reference"    value="${from }">  
</c:if>
<c:if test="${from == null  }">
            <input type="hidden" name="reference"  value="${edata['reference']}">  
</c:if>






 </form>       

           
</c:if>
<br>




   <c:if test ="${linkprocess == 1}">
<div class="section" style="max-height:
<c:if test='${Tray != 1}'>500px</c:if>
<c:if test='${Tray == 1}'>300px</c:if>; overflow:auto;">
    <table style="border-collapse: collapse; min-width: max-content;" >
        <thead>
            <!-- Title row -->
            <tr>
                <th colspan="${1 +8}"  class="sec-title" 
                    style="position: sticky; top: 0;  font-weight: bold; z-index: 2;">
                    Details : ${box.size()} rows
                </th>
            </tr>
            <!-- Header row -->
            <tr>
                <th style="position: sticky; top: 34px;  z-index: 1;" width="60">#</th>
                   <th style="position: sticky; top: 34px;  z-index: 1; min-width:150px;">
                      เอกสาร
                    </th>
                        <th style="position: sticky; top: 34px;  z-index: 1; min-width:150px;">
                    บริการที่นำเสนอ
                    </th>
                          <th style="position: sticky; top: 34px;  z-index: 1; min-width:150px;">
                      ราคาเสนอรวม
                    </th>
                      <th style="position: sticky; top: 34px;  z-index: 1; min-width:150px;">
                      ผู้สร้าง
                    </th>
                      <th style="position: sticky; top: 34px;  z-index: 1; min-width:150px;">
                     วันที่สร้าง
                    </th>
              <th style="position: sticky; top: 34px;  z-index: 1; min-width:150px;">
                 ขั้นตอน
                    </th>
                          <th style="position: sticky; top: 34px;  z-index: 1; min-width:150px;">
                 สถานะ
                    </th>
                    <c:if test="${Extension_id == 2 }">
                        <th style="position: sticky; top: 34px;  z-index: 1; min-width:150px;">
                    ดู
                    </th>
                    </c:if>
            </tr>
        </thead>
        <tbody>

             
                      <c:forEach items="${box}" var="rows" varStatus="counts">
                    
               
                    <tr class="table-row-hover  <c:if test='${rows["soft_delete"] != null }'>deleted</c:if>" id="extension-${rows.data_id }" 
                    data-custom="${rows.data_id }" 
                    data-custom2="${item_id}" 
                    data-custom3="${processes_id}" 
                    data-custom4="${useextension.id }"
                    data-custom5="${rows['alert'] }"
                >
                        <td>${counts.count}</td>
					  <td>${rows.data_title} </td>
					  
					  	  <td>${rows.data_value7}</td>
					  	  	  <td>${rows.data_value8}</td>
					  
    				  <td>${rows.data_nameadd}</td>
     				 <td> ${rows.data_dateadd}</td>
     				 
     				 		 <td> ${rows.current_step}</td>
     				 		 		 <td>${rows.closing_status}</td>
     				 
                        <c:if test="${Extension_id == 2 }">
                         <td> 
                         
                         <a onclick="openwindows('draggable-window6','ข้อมูลเอกสาร','850px','9999');getData('/viewRelationsDoc-filemanager/${rows.data_id }','mainbody6');" data-original-title="Img">
            <img src="/app/system/icon/preview.ico">
         </a>
                           </td>
                        </c:if>
                    </tr>
                    
  
                </c:forEach>
                </tr>
       
        </tbody>
    </table>
</div>

</c:if>

     

   <c:if test ="${linkprocess != 1}">
           Details : ${edatalist.size()} rows
    <div class="section" style="max-height:
    <c:if test='${Tray != 1}'>700px !important;</c:if>
<c:if test='${Tray == 1}'>400px !important;</c:if>;
    overflow:auto;">
    <table id="ex-${useextension.id }-${process.id }" class="mytableex">
        <thead>

            <tr>
                <th >#</th>
                <c:forEach items="${eformfields}" var="row">
                    <th>
                        ${row.field_label}
                    </th>
                </c:forEach>
                  <th >โดย</th>
                    
                  <th>Reference ID</th>
            </tr>
        </thead>
        <tbody>
      
             
                      <c:forEach items="${edatalist}" var="rows" varStatus="counts">
                    
               
                    <tr class="table-row-hover   <c:if test='${rows["soft_delete"] != null }'>deleted</c:if>" id="extension-${rows.data_id }" 
                    data-custom="${rows.data_id }" 
                    data-custom2="${item_id}" 
                    data-custom3="${processes_id}" 
                    data-custom4="${useextension.id }"
                     data-custom5="${rows['alert']}"
                       data-custom6="-${Tray}" oncontextmenu="extensionMenu(event,this)">
                        <td>${counts.count}</td>

                        <c:forEach items="${eformfields}" var="row" varStatus="count">
                            <c:set var="datakey" value="data_value${ count.count}" />
                            <td   style="min-width:150px;" >${rows[datakey]}</td>
                        </c:forEach>
                        <td  style="min-width:150px;" >${rows["data_nameadd"]}</td>
                       
                           <td  style="min-width:150px;" >${rows["reference"]} <c:if test='${rows["reference_oldsys"] != null}'>[${rows["reference_oldsys"]}] </c:if></td>
                    </tr>
                </c:forEach>
                </tr>
       
        </tbody>
    </table>
</div>
</c:if>

   <div id="saveextension"></div>
        
         
  
        
 <script>
 
 new SmartTable('#ex-${useextension.id }-${process.id }', {
	   //truncateClass: '.ant-table-cell',
	    sort: true,      // เปิด Sort
	    resize: true     // เปิด Resize
	});
 </script>


 
<script>
// ชื่อฟังก์ชันจะเป็น handleButtonClick_1, handleButtonClick_2 ตาม ID
function handleButtonClick_${useextension.id}_${Tray}(element) {
    element.classList.add('disabled-click');

    postDataCallBack('/saveappbyextension-filemanager?${_csrf.parameterName}=${_csrf.token}', 'extensionForms${useextension.id}-${Tray}','saveex-${useextension.id }-${Tray}');

    
    setTimeout(() => {
        element.classList.remove('disabled-click');
    }, 2000);
}
</script>






</div>

</body>


</html>