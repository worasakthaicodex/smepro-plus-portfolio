<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Image Attributes Replica</title>
    <style>
     :root {
            --bg-color: #f0f0f0;
            --border-color: #d9d9d9;
            --text-color: #000;
            --highlight-blue: #3399ff;
            --input-border: #7a7a7a;
            --light-border: #fff;
        }

        /* Main Window */
        .subsettingwindow {
            width: 100%;

            padding: 8px;
            box-sizing: border-box;
            position: relative;
        }

  .subsettingwindow  input[type="text"] {
            flex-grow: 1;
            border: 1px solid var(--input-border);
            padding: 3px;
        }

			.subsettingwindow select {
			    border: 1px solid var(--input-border);
			   padding: 3px;
			    font-family: inherit;
			    background-color: var(--input-bg);
			    outline: none;
			    width:100%;
			}

      .subsettingwindow   h1.title {
            font-size: 12px;
            margin: 0 0 10px 0;
            font-weight: normal;
        }

        /* Tabs */
       .subsettingwindow  .tabs {
            display: flex;
            margin-bottom: -1px; /* Overlap border */
            padding-left: 2px;
        }

       .subsettingwindow  .tab {
            padding: 4px 8px;
            background-color: var(--bg-color);
            border: 1px solid var(--bg-color);
            cursor: pointer;
            margin-right: 2px;
            position: relative;
            top: 1px;
        }

        .subsettingwindow .tab.active {
            background-color: #fff;
            border: 1px solid #999;
            border-bottom: 1px solid #fff; /* Hide bottom border */
            z-index: 2;
        }

       .subsettingwindow  .tab:hover:not(.active) {
            background-color: #e5e5e5;
        }

        /* Content Panel */
        .panel {
            background-color: #fff;
            border: 1px solid #999;
            padding: 15px;
            height: 380px;
            position: relative;
            z-index: 1;
        }

        /* Section Header with Line */
       .subsettingwindow  .section-header {
            display: flex;
            align-items: center;
            margin-bottom: 10px;
            white-space: nowrap;
        }

       .subsettingwindow  .section-header span {
            margin-right: 5px;
        }

       .subsettingwindow  .section-header .line {
            width: 100%;
            height: 1px;
            background-color: #a0a0a0;
            margin-top: 4px;
        }

        /* Form Elements */
      .subsettingwindow   .radio-group {
            margin-left: 10px;
            margin-bottom: 15px;
            display: flex;
            flex-direction: column;
            gap: 6px;
        }

       .subsettingwindow  .radio-row {
            display: flex;
            align-items: center;
        }

        .subsettingwindow .subsettingwindow  input[type="radio"] {
            margin: 0 6px 0 0;
        }

       .subsettingwindow  .form-row {
            display: flex;
            align-items: center;
            margin-bottom: 8px;
        }

        .subsettingwindow .label {
            width: 120px;
            flex-shrink: 0;
        }

     
        /* Custom Dropdown Simulation */
       .subsettingwindow  .dropdown-container {
            flex-grow: 1;
            position: relative;
        }

       .subsettingwindow  .fake-select {
            border: 1px solid #3399ff; /* Blue border like active */
            padding: 3px;
            background: #fff;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        
        .subsettingwindow .fake-select::after {
            content: "▼";
            font-size: 8px;
            margin-left: 5px;
        }

        /* The Open List */
       .subsettingwindow  .dropdown-list {
            position: absolute;
            top: 100%;
            left: 0;
            width: 100%;
            background: #fff;
            border: 1px solid #666;
            list-style: none;
            padding: 0;
            margin: 0;
            box-shadow: 2px 2px 5px rgba(0,0,0,0.2);
            z-index: 100;
            max-height: 170px;
              min-height: 170px;
            overflow-y: auto;
        }

        .subsettingwindow .dropdown-list li {
            padding: 2px 5px;
            cursor: pointer;
            font-size:11px;
        }

      .subsettingwindow   .dropdown-list li:hover {
            background-color: #3399ff;
            color: white;
        }

        .subsettingwindow .dropdown-list li.selected {
            background-color: #3399ff;
            color: white;
            border: 1px dotted #fff;
        }

        /* Footer Buttons */
        .footers {
            display: flex;
            justify-content: flex-end;
            margin-top: 15px;
            gap: 10px;
        }

       .subsettingwindow  button {
            min-width: 75px;
            padding: 4px 10px;
            background-color: #e1e1e1;
            border: 1px solid #adadad;
            font-size: 12px;
            cursor: pointer;
        }
        
      .subsettingwindow   button:hover {
            border-color: #0078d7;
            background-color: #e5f1fb;
        }

        .subsettingwindow button.primary {
            border: 1px solid #0078d7;
            box-shadow: inset 0 0 0 1px #8dcce8;
        }

       .subsettingwindow  .list-item {
            display: flex;              /* เปิดใช้ Flexbox */
            justify-content: space-between; /* ดันลูกๆ ไปชนขอบซ้าย-ขวา (Label ซ้าย, Badge ขวา) */
            align-items: center;        /* จัดกึ่งกลางแนวตั้ง (Vertical Center) */
            
            padding: 10px 15px;         /* ระยะห่างภายใน */
            border-bottom: 1px solid #eee; /* เส้นคั่นบางๆ */
            cursor: pointer;
            transition: background 0.2s;
        }

       .subsettingwindow  .list-item:hover {
            background-color: #f5f9ff; /* สีพื้นหลังตอนเอาเมาส์ชี้ */
        }

        /* ส่วนของ Label + Checkbox */
        .subsettingwindow .list-item label {
            display: flex;          /* ใช้ Flex เพื่อจัด checkbox กับ text ให้ตรงกัน */
            align-items: center;    /* กึ่งกลางแนวตั้ง */
            gap: 10px;              /* ระยะห่างระหว่าง checkbox กับตัวหนังสือ */
            cursor: pointer;
            width: 100%;            /* ให้พื้นที่คลิกเต็มส่วนที่เหลือ */
            font-size: 12px;
            color: #333;
        }

 

        /* ตกแต่ง Badge (ตัวเลขลำดับ) */
       .subsettingwindow  .order-badge {
            background-color: #007bff; /* สีน้ำเงินสด (เปลี่ยนได้) */
            color: white;
            font-size: 8px;
            font-weight: bold;
            
            /* จัดขนาดและรูปร่าง */
            min-width: 20px;       /* ความกว้างขั้นต่ำ วงกลมจะได้ไม่บี้ */
            height: 20px;
            border-radius: 12px;   /* ทำมุมมน (Pill shape) */
            
            /* จัดตัวเลขให้อยู่ตรงกลางวงกลม */
            display: none;         /* ซ่อนไว้ก่อน (JS จะมาเปิดเป็น flex) */
            align-items: center;
            justify-content: center;
            
            /* ระยะห่าง */
            margin-left: 10px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.2); /* เงาเล็กน้อยให้ดูลอย */
        }
    </style>
</head>
<body onload="loadSavedColumns(); ">

<div class="subsettingwindow">

    <div class="tabs">

        <div class="tab active">Work Flow</div>
    </div>

    <div class="panel">
  <div id="flowsave${process.id}"></div>
<form id="flowdoc${process.id}" name="flowdoc${process.id}">

         <input type="hidden" name="send"  id="send" value="${send.user_id}">
         <input type="hidden" name="app_processes_id" value="${process.id}">
        <input type="hidden" class="form-control newinput" value="${flowstep}[${flow.current_step_int}]">
         <input type="hidden" class="form-control newinput" name="step" value="${flow.current_step_int}">
         <input type="hidden" class="form-control newinput" name="flow_id" value="${flow.id}">
      <input type="hidden" class="form-control newinput" name="data_id" value="${edatas.data_id}">
              <input type="hidden" class="form-control newinput" name="workflow_ticket_edit" value="1">
         
     
        
    <c:if test="${TabFormData.id > 0}">
        <input type="hidden" id="id" name="id" value="${TabFormData.id}">   
    </c:if>
        
        
           <div class="section-header">
            <span>การส่งเอกสาร:</span>
            <div class="line"></div>
        </div>
          <div class="form-row">
            <span class="label">ขั้นตอนต่อไป:</span>
            <input type="text" name="title" value="[${first.description}] <- [${getstep.description}] ">
        </div>
         <div class="form-row">
            <span class="label">ผู้ส่ง:</span>
        <input type="text" class="form-control newinput" name="name_file" value="${send.user_name}" readonly>
        </div>
              <div class="form-row">
            <span class="label">ผุ้รับ:</span>
   
      <select class="form-select" name="to" id="to">
     			<option value="${Creator.user_id }" >${Creator.user_name }</option>
        </select>
  
  
        </div>
  

 <c:if test='${next.endaction_list == "end" }'>   
           <div class="section-header">
            <span>สรุปผล :</span>
            <div class="line"></div>
        </div>
  <div class="form-row">
      
           <span class="label">คำขอ:</span>
         <select class="form-select" name="closing_status">
   
        
         
            <c:forEach var="rows"  items="${next.closingStatus}" varStatus="count">
                <c:if test="${process.closingStatusCanRevise == rows.dataset_data_title 
                || process.closingStatusAfterAdd == rows.dataset_data_title }">
              <option value="${rows.dataset_data_title }" >${rows.dataset_data_title }</option>
              </c:if>
           </c:forEach>
  
          <option value="0" class="tabs" >-</option>

        </select>
     </div>

      </c:if>   

  
  <div class="form-row">
      
           <span class="label">บันทึกเพิ่มเติ่ม:</span>
        <textarea  class="form-control newinput" name="remark"></textarea>
</div>

        
   
</form>
      

    </div>

    <div class="footers" onload="loadSavedColumns()">
        <button class="primary" onclick="flow('/Ticketflowdoc-filemanager?${_csrf.parameterName}=${_csrf.token}'
, 'flowdoc${process.id}','flowsave${process.id}','${process.id}','${process.note}'
);">Flow</button>
        <button onclick="closeed2('draggable-window6')">Cancel</button>
    </div>

</div>

</body>
</html>





 