<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en">
<head>

   <style>
        /* --- SCOPED CSS: เริ่มต้นด้วย #cal-perm-wrapper --- */
        #cal-perm-wrapper {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            font-size: 12px;
          
        }

        #cal-perm-wrapper * { box-sizing: border-box; }

        /* --- Window Container --- */
        #cal-perm-wrapper .cp-window {
            width: 100%;
            height: 550px;
            background-color: #ffffff;
            border: 1px solid #707070;
            box-shadow: 0 4px 20px rgba(0,0,0,0.15);
            display: flex;
            flex-direction: column;
        }

        /* --- Header --- */
        #cal-perm-wrapper .cp-header {
            height: 30px;
            padding: 0 10px;
            background-color: white;
            display: flex;
            align-items: center;
            justify-content: space-between;
        }
        #cal-perm-wrapper .cp-close { font-size: 16px; color: #666; cursor: pointer; }
        #cal-perm-wrapper .cp-close:hover { color: #e81123; }

        /* --- Body --- */
        #cal-perm-wrapper .cp-body {
            display: flex;
            flex: 1;
            border-top: 1px solid #d9d9d9;
            overflow: hidden;
        }

        /* --- Sidebar --- */
        #cal-perm-wrapper .cp-sidebar {
            width: 180px;
            background-color: #f0f0f0;
            list-style: none;
            margin: 0;
            padding-left: 0;
            border-right: 1px solid #d9d9d9;
        }
        #cal-perm-wrapper .cp-item {
            padding: 4px 15px; cursor: pointer; margin-bottom: 2px;
        }
        #cal-perm-wrapper .cp-item:hover { background-color: #e0e0e0; }
        #cal-perm-wrapper .cp-item.cp-active {
            background-color: #2a8dd4; color: white;
        }

        /* --- Content Area --- */
        #cal-perm-wrapper .cp-content {
            flex: 1;
            padding: 15px 20px;
            background-color: white;
            display: flex;
            flex-direction: column;
        }

        /* Checkbox Top */
        #cal-perm-wrapper .cp-chk-label {
            display: flex; align-items: center; font-weight: 600; margin-bottom: 10px; cursor: pointer;
        }
        #cal-perm-wrapper .cp-chk-label input { margin-right: 8px; }

        /* Table Area */
        #cal-perm-wrapper .cp-table-container {
            flex: 1;
            border: 1px solid #adadad;
            background-color: white;
            margin-bottom: 10px;
            display: flex;
            flex-direction: column;
            overflow: hidden;
        }

        #cal-perm-wrapper .cp-table-header {
            display: grid;
            grid-template-columns: 1fr 1fr; /* Name, Permission Level */
            background-color: #ffffff;
            border-bottom: 1px solid #e0e0e0;
            font-weight: 600; /* Bold header */
            font-size: 12px;
        }
        #cal-perm-wrapper .cp-th { padding: 5px 10px; border-right: 1px solid #e0e0e0; }
        #cal-perm-wrapper .cp-th:last-child { border-right: none; }

        #cal-perm-wrapper .cp-table-row {
            display: grid;
            grid-template-columns: 1fr 1fr;
            cursor: pointer;
        }
        #cal-perm-wrapper .cp-table-row:hover { background-color: #f0f0f0; }
        #cal-perm-wrapper .cp-table-row.cp-selected {
            background-color: #cce8ff; /* Light blue selection */
        }
        
        #cal-perm-wrapper .cp-td {
            padding: 5px 10px;
            display: flex; align-items: center;
        }
        
        /* Green Plus Icon Mockup */
        #cal-perm-wrapper .cp-icon-plus {
            display: inline-block; width: 10px; height: 10px;
            position: relative; margin-right: 8px;
        }
        #cal-perm-wrapper .cp-icon-plus::before,
        #cal-perm-wrapper .cp-icon-plus::after {
            content: ''; position: absolute; background-color: #28a745;
        }
        #cal-perm-wrapper .cp-icon-plus::before { width: 2px; height: 10px; left: 4px; top: 0; }
        #cal-perm-wrapper .cp-icon-plus::after { width: 10px; height: 2px; left: 0; top: 4px; }
        
        /* Person Icon Mockup */
        #cal-perm-wrapper .cp-icon-user {
            margin-right: 5px; color: #555;
        }

        /* Bottom Controls */
        #cal-perm-wrapper .cp-bottom-controls {
            margin-bottom: 5px;
        }
        #cal-perm-wrapper .cp-chk-bottom {
            margin-bottom: 10px; display: flex; align-items: center;
        }
        #cal-perm-wrapper .cp-chk-bottom input { margin-right: 5px; }

        #cal-perm-wrapper .cp-btn-row {
            display: flex; gap: 10px;
        }
        #cal-perm-wrapper .cp-btn {
            height: 24px;
            padding: 0 10px;
            border: 1px solid #adadad;
            background-color: #e1e1e1;
            font-size: 12px;
            font-family: inherit;
            cursor: pointer;
            display: flex; align-items: center; justify-content: center;
        }
        #cal-perm-wrapper .cp-btn:hover {
            background-color: #e5f1fb; border-color: #0078d7;
        }
        #cal-perm-wrapper .cp-btn-arrow::after {
            content: '▼'; font-size: 8px; margin-left: 5px;
        }

        /* --- Footer --- */
        #cal-perm-wrapper .cp-footer {
            height: 45px;
            background-color: #f0f0f0;
            border-top: 1px solid #d9d9d9;
            display: flex;
            align-items: center;
            justify-content: flex-end;
            padding-right: 15px;
            gap: 10px;
        }
        #cal-perm-wrapper .cp-btn-footer {
            width: 75px; height: 25px;
            border: 1px solid #adadad; background-color: #e1e1e1;
            font-size: 12px; cursor: pointer;
        }
        #cal-perm-wrapper .cp-btn-footer:hover {
            background-color: #e5f1fb; border-color: #0078d7;
        }




/* กรอบสวิตช์ */
.switch {
  position: relative;
  display: inline-block;
  width: 50px;
  height: 24px;
  vertical-align: middle;
}

/* ซ่อน checkbox เดิม */
.switch input { 
  opacity: 0;
  width: 0;
  height: 0;
}

/* ตัวเลื่อน (Slider) */
.slider {
  position: absolute;
  cursor: pointer;
  top: 0; left: 0; right: 0; bottom: 0;
  background-color: #ccc; /* สีตอนปิด (สีเทา) */
  transition: .4s;
  border-radius: 34px;
}

.slider:before {
  position: absolute;
  content: "";
  height: 16px;
  width: 16px;
  left: 4px;
  bottom: 4px;
  background-color: white;
  transition: .4s;
  border-radius: 50%;
}

/* สีตอนเปิด (สีเขียว) */
input:checked + .slider {
  background-color: #2196F3; 
}

/* เลื่อนปุ่มไปขวาตอนเปิด */
input:checked + .slider:before {
  transform: translateX(26px);
}


    </style>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Window Properties</title>
    <style>
        /* --- SCOPED CSS: เริ่มต้นด้วย #cal-perm-wrapper --- */
        #cal-perm-wrapper {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            font-size: 12px;
          
        }

        #cal-perm-wrapper * { box-sizing: border-box; }

        /* --- Window Container --- */
        #cal-perm-wrapper .cp-window {
            width: 100%;
            height: 550px;
            background-color: #ffffff;
            border: 1px solid #707070;
            box-shadow: 0 4px 20px rgba(0,0,0,0.15);
            display: flex;
            flex-direction: column;
        }

        /* --- Header --- */
        #cal-perm-wrapper .cp-header {
            height: 30px;
            padding: 0 10px;
            background-color: white;
            display: flex;
            align-items: center;
            justify-content: space-between;
        }
        #cal-perm-wrapper .cp-close { font-size: 16px; color: #666; cursor: pointer; }
        #cal-perm-wrapper .cp-close:hover { color: #e81123; }

        /* --- Body --- */
        #cal-perm-wrapper .cp-body {
            display: flex;
            flex: 1;
            border-top: 1px solid #d9d9d9;
            overflow: hidden;
        }

        /* --- Sidebar --- */
        #cal-perm-wrapper .cp-sidebar {
            width: 180px;
            background-color: #f0f0f0;
            list-style: none;
            margin: 0;
            padding-left: 0;
            border-right: 1px solid #d9d9d9;
        }
        #cal-perm-wrapper .cp-item {
            padding: 4px 15px; cursor: pointer; margin-bottom: 2px;
        }
        #cal-perm-wrapper .cp-item:hover { background-color: #e0e0e0; }
        #cal-perm-wrapper .cp-item.cp-active {
            background-color: #2a8dd4; color: white;
        }

        /* --- Content Area --- */
        #cal-perm-wrapper .cp-content {
            flex: 1;
            padding: 15px 20px;
            background-color: white;
            display: flex;
            flex-direction: column;
        }

        /* Checkbox Top */
        #cal-perm-wrapper .cp-chk-label {
            display: flex; align-items: center; font-weight: 600; margin-bottom: 10px; cursor: pointer;
        }
        #cal-perm-wrapper .cp-chk-label input { margin-right: 8px; }

        /* Table Area */
        #cal-perm-wrapper .cp-table-container {
            flex: 1;
            border: 1px solid #adadad;
            background-color: white;
            margin-bottom: 10px;
            display: flex;
            flex-direction: column;
            overflow: hidden;
        }

        #cal-perm-wrapper .cp-table-header {
            display: grid;
            grid-template-columns: 1fr 1fr; /* Name, Permission Level */
            background-color: #ffffff;
            border-bottom: 1px solid #e0e0e0;
            font-weight: 600; /* Bold header */
            font-size: 12px;
        }
        #cal-perm-wrapper .cp-th { padding: 5px 10px; border-right: 1px solid #e0e0e0; }
        #cal-perm-wrapper .cp-th:last-child { border-right: none; }

        #cal-perm-wrapper .cp-table-row {
            display: grid;
            grid-template-columns: 1fr 1fr;
            cursor: pointer;
        }
        #cal-perm-wrapper .cp-table-row:hover { background-color: #f0f0f0; }
        #cal-perm-wrapper .cp-table-row.cp-selected {
            background-color: #cce8ff; /* Light blue selection */
        }
        
        #cal-perm-wrapper .cp-td {
            padding: 5px 10px;
            display: flex; align-items: center;
        }
        
        /* Green Plus Icon Mockup */
        #cal-perm-wrapper .cp-icon-plus {
            display: inline-block; width: 10px; height: 10px;
            position: relative; margin-right: 8px;
        }
        #cal-perm-wrapper .cp-icon-plus::before,
        #cal-perm-wrapper .cp-icon-plus::after {
            content: ''; position: absolute; background-color: #28a745;
        }
        #cal-perm-wrapper .cp-icon-plus::before { width: 2px; height: 10px; left: 4px; top: 0; }
        #cal-perm-wrapper .cp-icon-plus::after { width: 10px; height: 2px; left: 0; top: 4px; }
        
        /* Person Icon Mockup */
        #cal-perm-wrapper .cp-icon-user {
            margin-right: 5px; color: #555;
        }

        /* Bottom Controls */
        #cal-perm-wrapper .cp-bottom-controls {
            margin-bottom: 5px;
        }
        #cal-perm-wrapper .cp-chk-bottom {
            margin-bottom: 10px; display: flex; align-items: center;
        }
        #cal-perm-wrapper .cp-chk-bottom input { margin-right: 5px; }

        #cal-perm-wrapper .cp-btn-row {
            display: flex; gap: 10px;
        }
        #cal-perm-wrapper .cp-btn {
            height: 24px;
            padding: 0 10px;
            border: 1px solid #adadad;
            background-color: #e1e1e1;
            font-size: 12px;
            font-family: inherit;
            cursor: pointer;
            display: flex; align-items: center; justify-content: center;
        }
        #cal-perm-wrapper .cp-btn:hover {
            background-color: #e5f1fb; border-color: #0078d7;
        }
        #cal-perm-wrapper .cp-btn-arrow::after {
            content: '▼'; font-size: 8px; margin-left: 5px;
        }

        /* --- Footer --- */
        #cal-perm-wrapper .cp-footer {
            height: 45px;
            background-color: #f0f0f0;
            border-top: 1px solid #d9d9d9;
            display: flex;
            align-items: center;
            justify-content: flex-end;
            padding-right: 15px;
            gap: 10px;
        }
        #cal-perm-wrapper .cp-btn-footer {
            width: 75px; height: 25px;
            border: 1px solid #adadad; background-color: #e1e1e1;
            font-size: 12px; cursor: pointer;
        }
        #cal-perm-wrapper .cp-btn-footer:hover {
            background-color: #e5f1fb; border-color: #0078d7;
        }







    </style>
</head>
<body>

    <div id="cal-perm-wrapper">
        <div class="cp-window">
       

            <div class="cp-body">
                <ul class="cp-sidebar">
               <li class="cp-item " onclick="getData('/setworkflow-filemanager/${process.id}', 'mainbody3')">เงื่อนไขการดำเนินการ</li>
         	  
         	  <li class="cp-item" onclick="getData('/configDataset-filemanager/${process.id}', 'mainbody3')">ชุดข้อมูล</li>
         	  <li class="cp-item" onclick="getData('/configDataAccessInterface-filemanager/${process.id}', 'mainbody3')">รูปแบบการเข้าถึงข้อมูลหลัก</li>
     			<li class="cp-item cp-active" onclick="getData('/setRevision-filemanager/${process.id}', 'mainbody3')">คำขอเเก้ไขเอกสาร</li>
     			      <li class="cp-item" onclick="getData('/setsetRenamefile-filemanager/${process.id}', 'mainbody3')">ปรับชื่อเอกสารอัตโนมัติ</li>
     			                  <li class="cp-item" onclick="getData('/setExtension-filemanager/${process.id}', 'mainbody3')">ส่วนขยายระบบ</li>
                </ul>

                <div class="cp-content">
                
                      <form id="config${process.id}" name="config${process.id}">   
          <input type="hidden" name="process_id" value="${process.id}">
<div class="section-header" style="    display: flex;
    align-items: center;
    margin-bottom: 10px;
    white-space: nowrap;">
 
        </div>
  <div class="form-row" style="display: flex;
    align-items: center;
    margin-bottom: 8px;">
      
           <span class="label" style="width: 50%;
    flex-shrink: 0;">สถานะปิดงานที่สามารถขอเเก้ไขได้:</span>
         <select class="form-select" name="closingStatusCanRevise"  style="
         border: 1px solid #7a7a7a;
    padding: 3px;
    font-family: inherit;
    background-color: var(--input-bg);
    outline: none;">
   
    <option value="0"  >-</option>
            <c:forEach var="rows"  items="${closing_status}" varStatus="count">
           
           <option value="${rows.dataset_data_title}" 
    <c:if test="${process.closingStatusCanRevise == rows.dataset_data_title }">selected="selected"</c:if>
>
    ${rows.dataset_data_title}
</option>
           </c:forEach>
  
       

        </select>
</div>
       
       
       
    <div class="section-header" style="    display: flex;
    align-items: center;
    margin-bottom: 10px;
    white-space: nowrap;">
       

        </div>
        
        
        
  <div class="form-row" style="display: flex;
    align-items: center;
    margin-bottom: 8px;">
      
           <span class="label" style="width: 50%;
    flex-shrink: 0;">ปรับสถานะปิดงานหลังจากเเก้ไขเอกสารเป็น:</span>
         <select class="form-select" name="closingStatusAfterRevise"  style="
         border: 1px solid #7a7a7a;
    padding: 3px;
    font-family: inherit;
    background-color: var(--input-bg);
    outline: none;">
   
    <option value="0"  >-</option>
            <c:forEach var="rows"  items="${closing_status}" varStatus="count">
           <option value="${rows.dataset_data_title}" 
    <c:if test="${process.closingStatusAfterRevise == rows.dataset_data_title }">selected="selected"</c:if>
>
    ${rows.dataset_data_title}
</option>
           </c:forEach>
  
      

        </select>
</div>   

  <div class="form-row" style="display: flex;
    align-items: center;
    margin-bottom: 8px;">
      
           <span class="label" style="width: 50%;
    flex-shrink: 0;">สถานะปิดงานที่สามารถเพิ่มเอกสารได้:</span>
         <select class="form-select" name="closingStatusAfterAdd"  style="
         border: 1px solid #7a7a7a;
    padding: 3px;
    font-family: inherit;
    background-color: var(--input-bg);
    outline: none;">
   
    <option value="0"  >-</option>
            <c:forEach var="rows"  items="${closing_status}" varStatus="count">
           <option value="${rows.dataset_data_title}" 
    <c:if test="${process.closingStatusAfterAdd == rows.dataset_data_title }">selected="selected"</c:if>
>
    ${rows.dataset_data_title}
</option>
           </c:forEach>
  
      

        </select>
</div>   



  <div class="form-row" style="display: flex;
    align-items: center;
    margin-bottom: 8px;">
      
           <span class="label" style="width: 90%;
    flex-shrink: 0;" >อนุญาตแก้ไขเอกสารที่ยืนยันชั่วคราวแล้ว :</span>
<input type="hidden" name="canReviseConfirmed" id="canReviseConfirmed" value="${process.canReviseConfirmed == '1' ? '1' : '0'}">

          


<label class="switch">
  <input type="checkbox" 
         ${process.canReviseConfirmed == '1' ? 'checked' : ''}
         onchange="document.getElementById('canReviseConfirmed').value = this.checked ? '1' : '0'">
  <span class="slider round"></span>
</label>
</div>
  
                      </form>



                  

               

                </div>
            </div>

            <div class="cp-footer">
                <button class="cp-btn-footer"onclick="postData('/saveRevision-filemanager?${_csrf.parameterName}=${_csrf.token}', 'config${process.id}', 'savediv${process.id}');closeed2('draggable-window3');">Save</button>
                <button class="cp-btn-footer" onclick="closeed2('draggable-window3')">Cancel</button>
            </div>
        </div>
    </div>

 <div id="savediv${process.id}"></div>
</body>
</html>